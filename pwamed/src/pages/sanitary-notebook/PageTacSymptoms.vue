<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="csi-page--tac">
    <csi-page-title class="q-mb-lg">Sintomi</csi-page-title>

    <!-- FILTRI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <fse-filter-data-form class="q-mt-xl" @on-filter="onFilter"/>

    <!-- VISUALIZZAZIONE DATI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div class="q-mt-xl">
      <template v-if="!isLoading && symptomList.length<=0">
        <csi-banner type="info">
          Nessun sintomo trovato per il periodo selezionato
        </csi-banner>
      </template>

      <fse-tac-table-grid
        v-else
        :columns="TABLE_COLS"
        :data="symptomList"
        :loading="isLoading"
        :has-more-items="hasMoreItems"
        :loading-more="isLoadingMore"
        @get-more-items="getMoreSymptoms"
      />

    </div>


  </csi-page>
</template>

<script>
import FseFilterDataForm from "components/FseFilterDataForm";

import {getNotebookSymptomList} from "src/services/api";
import {date} from "quasar";

import {apiErrorNotify} from "src/services/utils";
import {TAC_TABLE_LIMIT} from "src/services/sanitary-notebook/config";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {areMoreItems} from "src/services/global/business-logic";

const {formatDate} = date;

const TABLE_COLS = [
  {
    name: "start-date",
    label: "Data inizio",
    field: row => row.data_inizio,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "end-date",
    label: "Data fine",
    field: row => row.data_fine,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "description",
    label: "Descrizione",
    field: row => row.descrizione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: false
  },
  {
    name: "area",
    label: "Area interessata",
    field: row => row.area_interessata,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: false
  }
];


export default {
  name: "PageTacSymptoms",
  components: {FseTacTableGrid, FseFilterDataForm},
  data() {
    return {
      TABLE_COLS,
      startDate: null,
      endDate: null,
      symptomList: [],
      symptomTotalCount: Number.MAX_SAFE_INTEGER,
      isLoading: false,
      isLoadingMore: false,
      offset: 0,
      sanitaryNoteBookId: null
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    hasMoreItems() {
      return areMoreItems(this.symptomList, this.symptomTotalCount)
    }
  },
  created() {
    this.sanitaryNoteBookId = this.$route.params.id

  },

  methods: {
    async onFilter({startDate, endDate}) {
      this.offset = 0
      this.symptomList = []
      this.symptomTotalCount = Number.MAX_SAFE_INTEGER
      this.startDate = startDate
      this.endDate = endDate

      this.isLoading = true
      await this.getSymptomsList()
      this.isLoading = false
    },
    async getSymptomsList() {
      try {
        let params = {
          limit: TAC_TABLE_LIMIT,
          offset: this.offset,
          ordinamento: "DESC",
          data_da: this.startDate,
          data_a: this.endDate
        }
        let {data} = await getNotebookSymptomList(this.patientTaxCode, this.sanitaryNoteBookId, {params})
        let list = data?.elenco_sintomi ?? []
        this.symptomList = [...this.symptomList, ...list]
        this.offset = this.symptomList.length
        this.symptomTotalCount = data?.numero_sintomi ?? 0

      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei sintomi"
        apiErrorNotify({error, message})
      }

    },
    async getMoreSymptoms() {
      this.isLoadingMore = true
      await this.getSymptomsList()
      this.isLoadingMore = false
    }
  }
}
</script>

<style scoped>

</style>
