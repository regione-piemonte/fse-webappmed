<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<!--
Tabella creata ad hoc perchÃ© vogliamo che le rilevazioni della pressione minima e massima
siano raggruppate in una sola tabella.

Le rilevazioni di pressione minima e massima sono oggetti distinti e per fare il match utlizziamo la data di entrambe
le rilevazioni. Se le due date sono le stesse (al minuto) possiamo ipotizzare che la rilevazione minima e massima
siano state fatte insieme.
-->

<template>
  <q-card>
    <!-- VISUALIZZAZIONE TABELLA -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <q-expansion-item default-opened v-if="visualizationMode === VISUALIZATION_MAP.TABLE">

      <template #header>
        <q-item-section class="text-h6">
          {{ descriptorName | empty }}
        </q-item-section>
      </template>

      <q-card-section >

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


    <!-- VISUALIZZAZIONE GRAFICO -->
    <!-- ----------------------------------------------------------------------------------------------------- -->
    <template v-if="visualizationMode === VISUALIZATION_MAP.GRAPH">
      <q-expansion-item default-opened >

        <template #header>
          <q-item-section class="text-h6">
            {{ descriptorName | empty }}
          </q-item-section>
        </template>

        <q-card-section class="q-pt-none">

          <!-- VISUALIZZAZIONE GRAFICO -->
          <!-- ----------------------------------------------------------------------------------------------------- -->
          <div >
            <q-card flat>
              <q-card-section
              >
                          <tac-detection-graph-line
                            :label-list="chartLabelListPress"
                            :dataset-list="chartDatasetListPress"
                            :height="300"
                            legend
                          />
              </q-card-section>
            </q-card>
          </div>
        </q-card-section>

      </q-expansion-item>
    </template>


  </q-card>

</template>

<script>
import {
  DESCRIPTOR_CODE_MAP,
  DESCRIPTOR_CODE_MAP_LABEL,
  TAC_TABLE_LIMIT,
  VISUALIZATION_MAP
} from "src/services/sanitary-notebook/config";
import { date } from "quasar";
import {apiErrorNotify,  groupBy, keyBy, orderBy} from "src/services/utils";
import TacDetectionGraphLine from "components/sanitary-notebook/tac-detections/TacDetectionGraphLine";
import {exportTableToCSV, getDetectionDatasetPointList} from "src/services/sanitary-notebook/business-logic";
import TacTableDetectionPressure from "components/sanitary-notebook/tac-detections/TacTableDetectionPressure";
import TacTableGridSkeleton from "components/sanitary-notebook/TacTableGridSkeleton";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {getNotebookDetectionList} from "src/services/api";
import {areMoreItems} from "src/services/global/business-logic";

const { formatDate,  startOfDate, endOfDate } = date;

const TABLE_COLS = [
  {
    name: "date",
    label: "Data",
    field: row => row.min.data,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY HH:mm")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "value-min",
    label: "Diastolica (minima)",
    field: row => row.min?.valore_numerico,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "value-max",
    label: "Sistolica (massima)",
    field: row => row.max?.valore_numerico,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "misure",
    label: "UnitÃ  di misura",
    field: row => row.min?.unita_misura?.codice,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "detection-mode",
    label: "ModalitÃ  rilevazione",
    field: row => row.min?.modalita_rilevazione?.descrizione_regionale,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  }

];


const PRESSURE_DESCRIPTORS =  [DESCRIPTOR_CODE_MAP.DIASTOLIC_PRESSURE,DESCRIPTOR_CODE_MAP.SYSTOLIC_PRESSURE]

export default {
  name: "FseTacDetectionPressure",
  components: {TacDetectionGraphLine, FseTacTableGrid, TacTableGridSkeleton},
  props: {
    menuItem: {type: Object, default: null},
    startDate: {type: [String, Date], default: null},
    endDate: {type: [String, Date], default: null},
    onSearch: {type: Boolean, default: false},
    visualizationMode: {type: [String, Number], default: VISUALIZATION_MAP.TABLE},

  },
  data() {
    return {
      VISUALIZATION_MAP,
      TABLE_COLS,
      detectionPressMinList:[],
      detectionPressMaxList:[],
      detectionsTotalCount: Number.MAX_SAFE_INTEGER,
      isLoading: false,
      isLoadingMore: false,
      offset:0
    }
  },
  watch: {
    onSearch: {
      immediate: true,
      async handler(val, oldVal) {

        if (val) {
          this.offset = 0
          this.detectionPressMinList = []
          this.detectionPressMaxList = []
          this.detectionsTotalCount= Number.MAX_SAFE_INTEGER
          this.isLoading = true
          await this.getDetectionsList()
          this.isLoading = false
        }
      }
    }
  },
  computed:{
    hasMoreItems() {
      return areMoreItems(this.detectionList, this.detectionsTotalCount)
    },

    descriptorName() {
      return this.menuItem?.name
    },
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },

    sanitaryNoteBookId() {
      let notebook = this.$store.getters["getSanitaryNotebook"]
      return notebook?.id
    },
    detectionList(){
      let pressMaxKeyedByDate = keyBy(this.detectionPressMaxList, el =>
        formatDate(el.data, "YYYY-MM-DDTHH:mm")
      );

      return this.detectionPressMinList.map(el => {
        let key = formatDate(el.data, "YYYY-MM-DDTHH:mm");
        return { min: el, max: pressMaxKeyedByDate[key] };
      });

    },
    detectionPressMinListGroupedByMeasure() {
      return groupBy(this.detectionPressMinList, d => d.unita_misura?.codice);
    },
    detectionPressMaxListGroupedByMeasure() {
      return groupBy(this.detectionPressMaxList, d => d.unita_misura?.codice);
    },
    chartLabelListPress() {
      let list = [...this.detectionPressMinList, ...this.detectionPressMaxList];
      let result = orderBy(list, ["data"], ["asc"]);
      result = result.map(d => formatDate(d.data, "DD/MM/YYYY HH:mm"));
      return [...new Set(result)];
    },
    chartDatasetListPress() {
      let result = [];

      Object.entries(this.detectionPressMinListGroupedByMeasure).forEach(
        ([key, list]) => {
          result.push({
            label: `Pressione diastolica (minima) - ${key}`,
            data: getDetectionDatasetPointList(list),
            backgroundColor: "#f2cb05",
            borderColor: "#f2cb05",
            lineTension: 0,
            fill: false,
            spanGaps: true
          });
        }
      );

      Object.entries(this.detectionPressMaxListGroupedByMeasure).forEach(
        ([key, list]) => {
          result.push({
            label: `Pressione sistolica (massima) - ${key}`,
            data: getDetectionDatasetPointList(list),
            backgroundColor: "#d68800",
            borderColor: "#d68800",
            lineTension: 0,
            fill: false,
            spanGaps: true
          });
        }
      );

      return result;
    },
  },
  created(){

  },
  methods: {
    async getDetectionsList() {

      if (!this.startDate && !this.endDate) return

      let id = this.menuItem.id

      let params = {
        limit: TAC_TABLE_LIMIT,
        offset: this.offset,
        ordinamento: "DESC",
        data_da: this.startDate,
        data_a: this.endDate
      }
      let payloadPresMin = {
        lista_codice_gruppo: [id],
        lista_codice_descrittore: [DESCRIPTOR_CODE_MAP.DIASTOLIC_PRESSURE]
      }
      let promisePresMin = await getNotebookDetectionList(this.patientTaxCode, this.sanitaryNoteBookId, payloadPresMin, {params})

      let payloadPresMax = {
        lista_codice_gruppo: [id],
        lista_codice_descrittore: [DESCRIPTOR_CODE_MAP.SYSTOLIC_PRESSURE]
      }
      let promisePresMax = await getNotebookDetectionList(this.patientTaxCode, this.sanitaryNoteBookId, payloadPresMax, {params})


      try {

        let { data: pressMinDetectionResponse } = await promisePresMin;
        let { data: pressMaxDetectionResponse } = await promisePresMax;

        let pressMinDetectionList = pressMinDetectionResponse?.elenco_rilevazioni ?? [];
        let pressMaxDetectionList = pressMaxDetectionResponse?.elenco_rilevazioni ?? [];
        this.detectionPressMinList = [...this.detectionPressMinList, ...pressMinDetectionList]
        this.detectionPressMaxList = [...this.detectionPressMinList, ...pressMaxDetectionList]


        this.offset = this.detectionPressMinList.length

        this.detectionsTotalCount= pressMinDetectionResponse?.numero_rilevazioni ?? 0;


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
