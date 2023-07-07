<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="csi-page--tac">
    <csi-page-title class="q-mb-lg">Dieta</csi-page-title>

    <!-- FILTRI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <fse-filter-data-form class="q-mt-xl" @on-filter="onFilter"/>


    <!-- VISUALIZZAZIONE DATI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div class="q-mt-xl">
      <template v-if="!isLoading && dietList.length<=0">
        <csi-banner type="info">
          Nessuna dieta trovata per il periodo selezionato
        </csi-banner>
      </template>

      <fse-tac-table-grid
        v-else
        :columns="TABLE_COLS"
        :data="dietList"
        :loading="isLoading"
        :has-more-items="hasMoreItems"
        :loading-more="isLoadingMore"
        @get-more-items="getMoreDietItems"
      />


    </div>

  </csi-page>
</template>

<script>

import {date} from "quasar";
import FseFilterDataForm from "components/FseFilterDataForm";
import {TAC_TABLE_LIMIT} from "src/services/sanitary-notebook/config";
import {getNotebookDietList, getNotebookDrugsList} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {areMoreItems} from "src/services/global/business-logic";

const { startOfDate, endOfDate, formatDate} = date;

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
    name: "breakfast-kcal",
    label: "Colazione (calorie)",
    field: row => row.colazione_calorie,
    format: (val, row) => {
      let kcal = row.colazione_calorie ?? 'N.D.';
      let breakfast = row.colazione_descrizione ?? '-'
      return `${breakfast} (${kcal})`;
    },
    align: "left",
    sortable: true
  },
  {
    name: "lunch-kcal",
    label: "Pranzo (calorie)",
    field: row => row.pranzo_calorie,
    format: (val, row) => {
      let kcal = row.pranzo_calorie ?? 'N.D.';
      let lunch = row.pranzo_descrizione ?? '-'
      return `${lunch} (${kcal})`;
    },
    align: "left",
    sortable: true
  },
  {
    name: "dinner-kcal",
    label: "Cena (Calorie)",
    field: row => row.cena_calorie,
    format: (val, row) => {
      let kcal = row.cena_calorie ?? 'N.D.';
      let dinner = row.cena_descrizione ?? '-'
      return `${dinner} (${kcal})`;
    },
    align: "left",
    sortable: true
  },
  {
    name: "snacks-kcal",
    label: "Spuntini (Calorie)",
    field: row => row.spuntini_calorie,
    format: (val, row) => {
      let kcal = row.spuntini_calorie ?? 'N.D.';
      let snack = row.spuntini_descrizione ?? '-'
      return `${snack} (${kcal})`;
    },
    align: "left",
    sortable: true
  }
];

export default {
  name: "PageTacDiet",
  components: {FseTacTableGrid, FseFilterDataForm},
  data() {
    return {
      TABLE_COLS,
      startDate: null,
      endDate: null,
      dietList: [],
      dietTotalCount: Number.MAX_SAFE_INTEGER,
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
      return areMoreItems(this.dietList, this.dietTotalCount)
    }

  },
  methods: {
    async onFilter({startDate, endDate}) {
      this.startDate = startDate
      this.endDate = endDate

      this.offset = 0
      this.dietList = []
      this.dietTotalCount = Number.MAX_SAFE_INTEGER
      this.startDate = startDate
      this.endDate = endDate

      this.isLoading = true
      await this.getDietList()
      this.isLoading = false
    },
    async getDietList() {
      try {
        let params = {
          limit: TAC_TABLE_LIMIT,
          offset: this.offset,
          ordinamento: "DESC",
          data_da: this.startDate,
          data_a: this.endDate
        }
        let {data} = await getNotebookDietList(this.patientTaxCode, this.sanitaryNoteBookId, {params})
        let list = data?.elenco_diete ?? []
        this.dietList = [...this.dietList, ...list]
        this.offset = this.dietList.length
        this.dietTotalCount = data?.numero_diete


      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista delle diete"
        apiErrorNotify({error, message})
      }

    },
    async getMoreDietItems() {
      this.isLoadingMore = true
      await this.getDietList()
      this.isLoadingMore = false
    }
  }
}


</script>

<style scoped>

</style>
