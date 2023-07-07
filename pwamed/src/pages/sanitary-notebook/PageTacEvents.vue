<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="csi-page--tac">
    <csi-page-title class="q-mb-lg">Eventi</csi-page-title>

    <!-- FILTRI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <fse-filter-data-form class="q-mt-xl" @on-filter="onFilter"/>


    <!-- VISUALIZZAZIONE DATI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div class="q-mt-xl">
      <template v-if="!isLoading && eventList.length<=0">
        <csi-banner type="info">
          Nessun evento trovato per il periodo selezionato
        </csi-banner>
      </template>

      <fse-tac-table-grid
        v-else
        :columns="TABLE_COLS"
        :data="eventList"
        :loading="isLoading"
        :has-more-items="hasMoreItems"
        :loading-more="isLoadingMore"
        @get-more-items="getMoreEventItems"
      />


    </div>


  </csi-page>
</template>

<script>

import FseFilterDataForm from "components/FseFilterDataForm";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {TAC_TABLE_LIMIT} from "src/services/sanitary-notebook/config";
import {getNotebookEventList, getNotebookPainList} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import {date} from "quasar";
import {areMoreItems} from "src/services/global/business-logic";
const TABLE_COLS = [
  {
    name: "date",
    label: "Data",
    field: row => row.data,
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
    sortable: true
  },

];
const {formatDate,  startOfDate, endOfDate} = date;

export default {
  name: "PageTacEvents",
  components: {FseTacTableGrid, FseFilterDataForm},
  data() {
    return {
      TABLE_COLS,
      startDate: null,
      endDate: null,
      eventList: [],
      eventTotalCount: Number.MAX_SAFE_INTEGER,
      isLoading: false,
      isLoadingMore: false,
      offset: 0,
      sanitaryNoteBookId: null
    }
  },
  computed:{
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    hasMoreItems() {
      return areMoreItems(this.eventList, this.eventTotalCount)
    }
  },
  created() {
    this.sanitaryNoteBookId = this.$route.params.id

  },

  methods: {
    async onFilter({startDate, endDate}) {
      this.startDate = startDate
      this.endDate = endDate

      this.offset = 0
      this.eventList = []
      this.eventTotalCount = Number.MAX_SAFE_INTEGER
      this.startDate = startDate
      this.endDate = endDate

      this.isLoading = true
      await this.getEventList()
      this.isLoading = false
    },
    async getEventList() {
      try {
        let params = {
          limit: TAC_TABLE_LIMIT,
          offset: this.offset,
          ordinamento: "DESC",
          data_da: this.startDate,
          data_a: this.endDate
        }
        let {data} = await getNotebookEventList(this.patientTaxCode, this.sanitaryNoteBookId, {params})
        let list = data?.elenco_eventi ?? []
        this.eventList = [...this.eventList, ...list]
        this.offset = this.eventList.length
        this.eventTotalCount = data?.numero_eventi

      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei dolori"
        apiErrorNotify({error, message})
      }

    },
    async getMoreEventItems() {
      this.isLoadingMore = true
      await this.getEventList()
      this.isLoadingMore = false
    }
  }
}
</script>

<style scoped>

</style>
