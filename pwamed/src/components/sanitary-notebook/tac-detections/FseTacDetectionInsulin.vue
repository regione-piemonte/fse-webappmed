<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<!--
Tabella creata ad hoc perchÃ© vogliamo che le rilevazioni dell'insulina
siano raggruppate in una sola tabella.

Le rilevazioni di insulina e insulina farm sono oggetti distinti e per fare il match utlizziamo la data di entrambe
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
    <template v-else-if="visualizationMode === VISUALIZATION_MAP.GRAPH">
      <template v-if="isLoading">
        <q-expansion-item default-opened >

          <template #header>
            <q-item-section class="text-h6">
              {{ descriptorName | empty }}
            </q-item-section>
          </template>

          <q-card-section >
            <q-skeleton height="200px" square class="full-width"/>
          </q-card-section>

        </q-expansion-item>
      </template>
      <template v-else-if="detectionListGroupedByMeasure.length>0">
        <q-expansion-item default-opened  v-for="([
                              measure,
                              list
                            ], index) in detectionListGroupedByMeasure"
                          :key="'detection-graph--' + measure">

          <template #header>
            <q-item-section class="text-h6">
              {{ descriptorName | empty }} ({{ measure | empty }})
            </q-item-section>
          </template>

          <q-card-section class="q-pt-none">
            <q-card flat >
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
        <q-expansion-item default-opened >

          <template #header>
            <q-item-section class="text-h6">
              {{ descriptorName | empty }}
            </q-item-section>
          </template>

          <q-card-section >
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
import {DESCRIPTOR_CODE_MAP, TAC_TABLE_LIMIT, VISUALIZATION_MAP} from "src/services/sanitary-notebook/config";
import {
  exportTableToCSV,
  getDetectionDatasetPointList,
} from "src/services/sanitary-notebook/business-logic";
import TacDetectionGraphLineSingle from "components/sanitary-notebook/tac-detections/TacDetectionGraphLineSingle";
import {date} from "quasar";
import {apiErrorNotify, groupBy, isEmpty, keyBy, orderBy, sortBy} from "src/services/utils";
import {getNotebookDetectionList} from "src/services/api";
import TacTableGridSkeleton from "components/sanitary-notebook/TacTableGridSkeleton";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {areMoreItems} from "src/services/global/business-logic";


const { formatDate,  startOfDate, endOfDate } = date;

const TABLE_COLS = [
  {
    name: "date",
    label: "Data",
    field: row => row.insulin.data,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY HH:mm")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "value",
    label: "Insulina",
    field: row => row.insulin?.valore_numerico,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },

  {
    name: "misure",
    label: "UnitÃ  di misura",
    field: row => row.insulin?.unita_misura?.codice,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "farm",
    label: "Insulina farm",
    field: row => row.insulinFarm?.valore_testuale,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "detection-mode",
    label: "ModalitÃ  rilevazione",
    field: row => row.insulin?.modalita_rilevazione?.descrizione_regionale,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  }

];
export default {
  name: "FseTacDetectionInsulin",
  components: {TacDetectionGraphLineSingle, FseTacTableGrid, TacTableGridSkeleton},
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
      detectionInsulinList:[],
      detectionInsulinFarmList:[],
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
          this.detectionsTotalCount = Number.MAX_SAFE_INTEGER
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
    detectionListGroupedByMeasure(){
      if(isEmpty(this.detectionInsulinList)) return []
      let list = this.detectionInsulinList
      let result = groupBy(list, d => d.unita_misura?.codice);
      result = Object.entries(result);
      return sortBy(result, ([key, list]) => key);
    },
    detectionList(){
      let insulinFarmKeyedByDate = keyBy(this.detectionInsulinFarmList, el =>
        formatDate(el.data, "YYYY-MM-DDTHH:mm")
      );

      return this.detectionInsulinList.map(el => {
        let key = formatDate(el.data, "YYYY-MM-DDTHH:mm");
        return { insulin: el, insulinFarm: insulinFarmKeyedByDate[key] };
      });

    },
    // detectionPressMinListGroupedByMeasure() {
    //   return groupBy(this.detectionPressMinList, d => d.unita_misura?.codice);
    // },
    // detectionPressMaxListGroupedByMeasure() {
    //   return groupBy(this.detectionPressMaxList, d => d.unita_misura?.codice);
    // },
    // chartLabelListPress() {
    //   let list = [...this.detectionPressMinList, ...this.detectionPressMaxList];
    //   let result = orderBy(list, ["data"], ["asc"]);
    //   result = result.map(d => formatDate(d.data, "DD/MM/YYYY HH:mm"));
    //   return [...new Set(result)];
    // },
    // chartDatasetListPress() {
    //   let result = [];
    //
    //   Object.entries(this.detectionPressMinListGroupedByMeasure).forEach(
    //     ([key, list]) => {
    //       result.push({
    //         label: `Pressione diastolica (minima) - ${key}`,
    //         data: getDetectionDatasetPointList(list),
    //         backgroundColor: "#f2cb05",
    //         borderColor: "#f2cb05",
    //         lineTension: 0,
    //         fill: false,
    //         spanGaps: true
    //       });
    //     }
    //   );
    //
    //   Object.entries(this.detectionPressMaxListGroupedByMeasure).forEach(
    //     ([key, list]) => {
    //       result.push({
    //         label: `Pressione sistolica (massima) - ${key}`,
    //         data: getDetectionDatasetPointList(list),
    //         backgroundColor: "#d68800",
    //         borderColor: "#d68800",
    //         lineTension: 0,
    //         fill: false,
    //         spanGaps: true
    //       });
    //     }
    //   );
    //
    //   return result;
    // },
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
      let payloadInsulin = {
        lista_codice_gruppo: [id],
        lista_codice_descrittore: [DESCRIPTOR_CODE_MAP.INSULIN]
      }
      let insulinPromise = await getNotebookDetectionList(this.patientTaxCode, this.sanitaryNoteBookId, payloadInsulin, {params})

      let payloadInsulinFarm = {
        lista_codice_gruppo: [id],
        lista_codice_descrittore: [DESCRIPTOR_CODE_MAP.INSULIN_FARM]
      }
      let insulinFarmPromise = await getNotebookDetectionList(this.patientTaxCode, this.sanitaryNoteBookId, payloadInsulinFarm, {params})


      try {

        let { data: insulinDetectionResponse } = await insulinPromise;
        let { data: insulinFarmDetectionResponse } = await insulinFarmPromise;

        let insulinDetectionList = insulinDetectionResponse?.elenco_rilevazioni ?? []
        let insulinFarmDetectionList = insulinFarmDetectionResponse?.elenco_rilevazioni ?? []
        this.detectionInsulinList = [...this.detectionInsulinList, ...insulinDetectionList]
        this.detectionInsulinFarmList = [...this.detectionInsulinFarmList, ...insulinFarmDetectionList]
        this.offset = this.detectionInsulinList.length
        this.detectionsTotalCount = insulinDetectionList?.numero_rilevazioni ?? 0

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
