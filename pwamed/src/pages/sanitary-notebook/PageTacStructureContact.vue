<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="csi-page--tac">
    <csi-page-title class="q-mb-lg">Contatti con le strutture</csi-page-title>

    <!-- FILTRI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <fse-filter-data-form class="q-mt-xl" @on-filter="onFilter"/>


    <!-- VISUALIZZAZIONE DATI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div class="q-mt-xl">
      <template v-if="!isLoading && structureContactList.length<=0">
        <csi-banner type="info">
          Nessun contatto struttura trovato per il periodo selezionato
        </csi-banner>
      </template>

      <fse-tac-table-grid
        v-else
        :columns="TABLE_COLS"
        :data="structureContactList"
        :has-more-items="hasMoreItems"
        :loading="isLoading"
        :loading-more="isLoadingMore"
        @get-more-items="getMoreContacts"

      />


    </div>


  </csi-page>
</template>

<script>
import FseFilterDataForm from "components/FseFilterDataForm";
import {TAC_TABLE_LIMIT} from "src/services/sanitary-notebook/config";
import {getNotebookContacts} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import {date} from "quasar";
import FseTacTableGrid from "components/sanitary-notebook/FseTacTableGrid";
import {SANITARY_NOTEBOOK_MENU} from "src/router/routes";
import {areMoreItems} from "src/services/global/business-logic";

const {
  formatDate
} = date;

const TABLE_COLS = [
  {
    name: "start-date",
    label: "Data inizio",
    field: row => row.data_contatto_inizio,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "end-date",
    label: "Data fine",
    field: row => row.data_contatto_fine,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "structure-type",
    label: "Tipo di struttura",
    field: row => row.tipologia_struttura?.descrizione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "description",
    label: "Nome struttura",
    field: row => row.denominazione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "motivation",
    label: "Motivazione",
    field: row => row.motivazione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "contact-type",
    label: "Tipo di contatto",
    field: row => row.tipo_contatto?.descrizione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },

];

export default {
  name: "PageTacStructureContact",
  components: {FseTacTableGrid, FseFilterDataForm},
  data() {
    return {
      TABLE_COLS,
      startDate: null,
      endDate: null,
      isLoading: false,
      isLoadingMore: false,
      structureContactList: [],
      listTotalCount: Number.MAX_SAFE_INTEGER,
      tablePagination: {
        sortBy: "start-date",
        descending: true,
        page: 1,
        rowsPerPage: 100
      },
      sanitaryNoteBookId: null,
      offset: 0
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    hasMoreItems() {
      return areMoreItems(this.structureContactList, this.listTotalCount)
    }
  },
  async created() {
    this.sanitaryNoteBookId = this.$route.params.id
  },

  methods: {
    async onFilter({startDate, endDate}) {

      this.offset = 0
      this.structureContactList = []
      this.startDate = startDate
      this.endDate = endDate
      this.isLoading = true
      this.listTotalCount = Number.MAX_SAFE_INTEGER

      await this.getContactList()
      this.isLoading = false
    },
    async getMoreContacts() {
      this.isLoadingMore = true
      await this.getContactList()
      this.isLoadingMore = false
    },
    async getContactList() {

      try {
        let params = {
          limit: TAC_TABLE_LIMIT,
          offset: this.offset,
          data_da: this.startDate,
          data_a: this.endDate
        }

        let {data} = await getNotebookContacts(this.patientTaxCode, this.sanitaryNoteBookId, {params})
         let list = data?.elenco_contatti_strutture ?? []
        this.structureContactList = [...this.structureContactList, ...list]
        this.offset = this.structureContactList?.length
        this.listTotalCount = data?.numero_contatti_strutture

      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei contatti delle strutture"
        apiErrorNotify({error, message})
      }

    }


  }
}
</script>

<style scoped>

</style>
