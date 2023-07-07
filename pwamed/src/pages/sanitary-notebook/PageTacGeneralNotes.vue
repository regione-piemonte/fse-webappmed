<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="csi-page--tac">
    <csi-page-title class="q-mb-lg">Note generali</csi-page-title>

    <!-- FILTRI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <fse-filter-data-form class="q-mt-xl" @on-filter="onFilter"/>


    <!-- VISUALIZZAZIONE DATI -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div class="q-mt-xl">
      <q-card class="relative-position">
        <q-toolbar>
          <q-toolbar-title>
            Dal {{ startDate | date }} al {{ endDate | date }}
          </q-toolbar-title>
        </q-toolbar>

        <!-- VISUALIZZAZIONE TABELLA -->
        <!-- ----------------------------------------------------------------------------------------------------- -->
        <q-table
          :columns="TABLE_COLS"
          :data="generalNotesList"
          :grid="$q.screen.lt.md"
          :pagination="tablePagination"
          card-container-class="q-col-gutter-md q-px-md"
          dense
          flat
        >
          <template #body-cell-create-date="props">
            <q-td :props="props">
              {{ props.row.data_creazione  | datetime }}
            </q-td>
          </template>

          <template #body-cell-edit-date="props">
            <q-td :props="props">
              {{ props.row.data_aggiornamento | datetime | empty }}
            </q-td>
          </template>


          <!-- GRID ITEM -->
          <!-- --------- -->
          <template #item="props">
            <div class="col-12 col-sm-6">
              <fse-tac-table-grid-item-general-notes
                :row="props.row"
              />
            </div>
          </template>
        </q-table>
      </q-card>
    </div>


  </csi-page>
</template>

<script>
import FseFilterDataForm from "components/FseFilterDataForm";
import FseTacTableGridItemGeneralNotes from "components/sanitary-notebook/FseTacTableGridItemGeneralNotes";

const TABLE_COLS = [
  {
    name: "create-date",
    label: "Data creazione",
    field: row => row.data_creazione,
    align: "left",
    sortable: true
  },
  {
    name: "edit-date",
    label: "Data aggiornamento",
    field: row => row.data_aggiornamento,
    align: "left",
    sortable: true
  },
  {
    name: "health-status",
    label: "Stato salute",
    field: row => {
      return row.stato_salute
    },
    align: "left",
    sortable: true
  },
  {
    name: "life-style",
    label: "Stile di vita",
    field: row => row.stile_vita,
    align: "left",
    sortable: true
  },


];


export default {
  name: "PageTacGeneralNotes",
  components: {FseTacTableGridItemGeneralNotes, FseFilterDataForm},
  data() {
    return {
      TABLE_COLS,
      startDate: null,
      endDate: null,
      generalNotesList: [],
      tablePagination: {
        sortBy: "start-date",
        descending: true,
        page: 1,
        rowsPerPage: 100
      }
    }
  },
  created() {
    this.generalNotesList = [{
      data_creazione: new Date(),
      data_aggiornamento: new Date(),
      stato_salute: 'Lorem Ipsum',
      stile_vita: 'Dettaglio stile di vita'
    }]
  },

  methods: {
    onFilter({startDate, endDate}) {
      this.startDate = startDate
      this.endDate = endDate
    }
  }
}
</script>

<style scoped>

</style>
