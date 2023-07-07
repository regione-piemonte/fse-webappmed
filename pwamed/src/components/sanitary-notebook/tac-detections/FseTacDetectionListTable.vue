<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-table
    :columns="TABLE_COLS"
    :data="detectionList"
    hide-pagination
    hide-bottom
    :rows-per-page-options="[0]"
    flat
    dense
    :grid="$q.screen.lt.md"
    card-container-class="q-col-gutter-md q-px-md"
  >

    <template #item="props">
      <div class="q-pa-sm col-xs-12 col-sm-6">
        <q-card class="q-py-sm">

          <q-card-section v-for="col in props.cols" :key="col.name" class="q-py-sm">
            <div class="fse-tac-grid-item__field-label">
              {{ col.label }}
            </div>
            <div class="fse-tac-grid-item__field-value">
              {{ col.value }}
            </div>
          </q-card-section>
        </q-card>
      </div>
    </template>

  </q-table>
</template>

<script>

import {date} from "quasar";

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
    field: row => row.unita_misura?.descrizione,
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
  name: "FseTacDetectionListTable",
  components: {},
  props: {
    detectionList: { type: Array, required: false, default: () => [] },
  },
  data() {
    return {
      TABLE_COLS
    };
  },
  computed: {
    isDelegationTacWeak() {
      return this.$store.getters["isDelegationTacWeak"];
    },

  },
  created() {},
  methods: {

  }
}
</script>

<style scoped>

</style>
