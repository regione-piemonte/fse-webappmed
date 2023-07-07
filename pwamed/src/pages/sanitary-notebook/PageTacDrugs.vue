<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="csi-page--tac">
    <csi-page-title class="q-mb-lg">Farmaci</csi-page-title>


    <!-- FILTRI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <fse-filter-data-form class="q-mt-xl" @on-filter="onFilter"/>


    <!-- VISUALIZZAZIONE DATI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div class="q-mt-xl">
      <template v-if="!isLoading && drugsList.length<=0">
        <csi-banner type="info">
          Nessun farmaco trovato per il periodo selezionato
        </csi-banner>
      </template>

      <fse-tac-table-grid
        v-else
        :columns="TABLE_COLS"
        :data="drugsList"
        :loading="isLoading"
        :has-more-items="hasMoreItems"
        :loading-more="isLoadingMore"
        @get-more-items="getMoreDrugsItems"
      />


    </div>

  </csi-page>
</template>

<script>
import {date} from "quasar";
import FseFilterDataForm from "components/FseFilterDataForm";
import {TAC_TABLE_LIMIT} from "src/services/sanitary-notebook/config";
import {getNotebookDrugsList, getNotebookPainList} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {areMoreItems} from "src/services/global/business-logic";

const {formatDate,  startOfDate, endOfDate} = date;

const TABLE_COLS = [
  {
    name: "date",
    label: "Data assunzione",
    field: row => row.data_assunzione,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "drug",
    label: "Farmaco",
    field: row => row.descrizione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "amount",
    label: "QuantitÃ ",
    field: row => row.quantita,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: false
  },

];
export default {
  name: "PageTacDrugs",
  components: {FseTacTableGrid, FseFilterDataForm},
  data() {
    return {
      TABLE_COLS,
      startDate: null,
      endDate: null,
      drugsList: [],
      drugsTotalCount: Number.MAX_SAFE_INTEGER,
      isLoading: false,
      isLoadingMore: false,
      offset: 0,
      sanitaryNoteBookId: null
    }
  },
  created() {
    this.sanitaryNoteBookId = this.$route.params.id

  },
  computed: {

      patientTaxCode() {
        return this.$store.getters['getPatientTaxCode']
      },
      hasMoreItems() {
        return areMoreItems(this.drugsList, this.drugsTotalCount)
      }

  },
  methods: {
    async onFilter({startDate, endDate}) {
      this.startDate = startDate
      this.endDate = endDate

      this.offset = 0
      this.drugsList = []
      this.drugsTotalCount = Number.MAX_SAFE_INTEGER
      this.startDate = startDate
      this.endDate = endDate

      this.isLoading = true
      await this.getDrugsList()
      this.isLoading = false
    },
    async getDrugsList() {
      try {
        let params = {
          limit: TAC_TABLE_LIMIT,
          offset: this.offset,
          ordinamento: "DESC",
          data_da: this.startDate,
          data_a: this.endDate
        }
        let {data} = await getNotebookDrugsList(this.patientTaxCode, this.sanitaryNoteBookId, {params})
        let list = data?.elenco_farmaci ?? []
        this.drugsList = [...this.drugsList, ...list]
        this.offset = this.drugsList.length

        this.drugsTotalCount = data?.numero_farmaci ?? 0


      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei farmaci"
        apiErrorNotify({error, message})
      }

    },
    async getMoreDrugsItems() {
      this.isLoadingMore = true
      await this.getDrugsList()
      this.isLoadingMore = false
    }
  }
}
</script>

<style scoped>

</style>
