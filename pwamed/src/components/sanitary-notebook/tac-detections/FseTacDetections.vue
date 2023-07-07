<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card>
    <!-- VISUALIZZAZIONE TABELLA -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <template v-if="visualizationMode === VISUALIZATION_MAP.TABLE">
      <q-expansion-item default-opened>

        <template #header>
          <q-item-section class="text-h6">
            {{ descriptorName | empty }}
          </q-item-section>
        </template>

        <q-card-section>

          <template v-if="isLoading">
            <tac-table-grid-skeleton :columns="TABLE_COLS"/>
          </template>
          <template v-else-if="detectionList.length>0">

            <fse-tac-table-grid
              :columns="TABLE_COLS"
              :data="detectionList"
              :has-more-items="hasMoreItems"
              :loading="isLoading"
              :loading-more="isLoadingMore"
              @get-more-items="getMoreDetections"
            />

          <csi-buttons class="q-mt-xs">
            <csi-button no-min-width outline @click="exportToCSV">
              Esporta CSV
            </csi-button
            >
          </csi-buttons>

          </template>

          <template v-else>
            <csi-banner type="info">
              Nessuna rilevazione trovata per il periodo selezionato
            </csi-banner>
          </template>


        </q-card-section>

      </q-expansion-item>
    </template>


    <!-- VISUALIZZAZIONE GRAFICO -->
    <!-- ----------------------------------------------------------------------------------------------------- -->
    <template v-else-if="visualizationMode === VISUALIZATION_MAP.GRAPH">
      <template v-if="isLoading">
        <q-expansion-item default-opened>

          <template #header>
            <q-item-section class="text-h6">
              {{ descriptorName | empty }}
            </q-item-section>
          </template>

          <q-card-section>
            <q-skeleton class="full-width" height="200px" square/>
          </q-card-section>

        </q-expansion-item>
      </template>
      <template v-else-if="detectionListGroupedByMeasure.length>0">
        <q-expansion-item v-for="([
                              measure,
                              list
                            ], index) in detectionListGroupedByMeasure" :key="'detection-graph--' + measure"
                          default-opened>

          <template #header>
            <q-item-section class="text-h6">
              {{ descriptorName | empty }} ({{ measure | empty }})
            </q-item-section>
          </template>

          <q-card-section class="q-pt-none">
            <q-card flat>
              <q-card-section>
                <tac-detection-graph-line-single
                  :detection-list="list"
                  class="q-mt-md"
                  color="#f2cb05"
                />
                <q-separator v-if="index < detectionListGroupedByMeasure.length-1" class="q-mt-xl q-mb-sm"/>
              </q-card-section>
            </q-card>

          </q-card-section>

        </q-expansion-item>
      </template>
      <template v-else>
        <q-expansion-item default-opened>

          <template #header>
            <q-item-section class="text-h6">
              {{ descriptorName | empty }}
            </q-item-section>
          </template>

          <q-card-section>
            <csi-banner type="info">
              Nessuna rilevazione trovata per il periodo selezionato
            </csi-banner>
          </q-card-section>

        </q-expansion-item>
      </template>


    </template>


  </q-card>


</template>

<script>
import {
  DESCRIPTOR_CODE_MAP_LABEL,
  DETECTIONS_MAP, GRAPH_COLORS_MAP, GROUP_CODE_MAP,
  TAC_TABLE_LIMIT,
  VISUALIZATION_MAP
} from "src/services/sanitary-notebook/config";

import TacTableGridSkeleton from "components/sanitary-notebook/TacTableGridSkeleton";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {getNotebookDetectionList} from "src/services/api";
import {apiErrorNotify, groupBy, isEmpty, sortBy} from "src/services/utils";
import {date, exportFile} from "quasar";
import TacDetectionGraphLineSingle from "components/sanitary-notebook/tac-detections/TacDetectionGraphLineSingle";
import {areMoreItems} from "src/services/global/business-logic";
import {exportTableToCSV, wrapCsvValue} from "src/services/sanitary-notebook/business-logic";


const {
  formatDate
} = date;

const TABLE_COLS = [
  {
    name: "start-date",
    label: "Data",
    field: row => row.data,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "value",
    label: "Misura",
    field: row => row.valore_testuale ? row.valore_testuale : row.valore_numerico,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "misure",
    label: "UnitÃ  di misura",
    field: row => row.unita_misura?.codice,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "detection-mode",
    label: "ModalitÃ  rilevazione",
    field: row => row.modalita_rilevazione?.descrizione_regionale,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  }

];
export default {
  name: "FseTacDetections",
  components: {TacDetectionGraphLineSingle, FseTacTableGrid, TacTableGridSkeleton},
  props: {
    menuItem: {type: Object, default: null},
    descriptor: {type: String, default: null},
    startDate: {type: [String, Date], default: null},
    endDate: {type: [String, Date], default: null},
    onSearch: {type: Boolean, default: false},
    visualizationMode: {type: Number, default: VISUALIZATION_MAP.TABLE},
    index: {type: Number, default: 0},
  },
  data() {
    return {
      TABLE_COLS,
      DETECTIONS_MAP,
      VISUALIZATION_MAP,
      GRAPH_COLORS_MAP,
      offset: 0,
      detectionList: [],
      detectionsTotalCount: Number.MAX_SAFE_INTEGER,
      isLoading: false,
      isLoadingMore: false
    }
  },
  watch: {
    onSearch: {
      immediate: true,
      async handler(val, oldVal) {

        if (val) {
          this.offset = 0
          this.detectionList = []
          this.detectionsTotalCount = Number.MAX_SAFE_INTEGER
          this.isLoading = true
          await this.getDetectionsList()
          this.isLoading = false
        }
      }
    }
  },

  computed: {
    hasMoreItems() {
      return areMoreItems(this.detectionList, this.detectionsTotalCount)
    },

    descriptorName() {
      return DESCRIPTOR_CODE_MAP_LABEL[this.descriptor]
    },
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },

    sanitaryNoteBookId() {
      let notebook = this.$store.getters["getSanitaryNotebook"]
      return notebook?.id
    },
    detectionListGroupedByMeasure() {
      if (isEmpty(this.detectionList)) return []
      let list = this.detectionList
      let result = groupBy(list, d => d.unita_misura?.codice);
      result = Object.entries(result);
      return sortBy(result, ([key, list]) => key);
    }
  },
  created() {


  },
  methods: {
    async getDetectionsList() {

      if (!this.startDate && !this.endDate) return


      try {
        let params = {
          limit: TAC_TABLE_LIMIT,
          offset: this.offset,
          ordinamento: "DESC",
          data_da: this.startDate,
          data_a: this.endDate
        }


        let payload = {
          lista_codice_gruppo: [this.menuItem.id],
          lista_codice_descrittore: [this.descriptor]
        }
        let {data: detectionsResponse} = await getNotebookDetectionList(this.patientTaxCode, this.sanitaryNoteBookId, payload, {params})
        let list = detectionsResponse?.elenco_rilevazioni ?? []

        this.detectionList = [...this.detectionList, ...list]
        this.offset = this.detectionList.length
        this.detectionsTotalCount = detectionsResponse?.numero_rilevazioni ?? 0

      } catch (error) {
        let message = `Non Ã¨ stato possibile recuperare le rilevazioni di ${this.descriptorName}`
        apiErrorNotify({error, message})
      }
      this.$emit('searched')

    },
    async getMoreDetections() {
      this.isLoadingMore = true
      await this.getDetectionsList()
      this.isLoadingMore = false
    },

    exportToCSV() {
      exportTableToCSV(TABLE_COLS, this.detectionList, this.descriptorName)
    },
  }
}
</script>

<style scoped>

</style>
