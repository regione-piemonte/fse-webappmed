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
  <q-table
    :columns="tableCols"
    :data="tableData"
    :pagination="tablePagination"
    flat
    dense
    :grid="$q.screen.lt.md"
    card-container-class="q-col-gutter-md q-px-md"
  >

    <!-- GRID ITEM -->
    <!-- --------- -->
    <template #item="props">
      <div class="col-12 col-sm-6">
        <q-card flat bordered class="relative-position">
          <template v-for="col in props.cols">
              <q-card-section :key="col.name">
                <div class="fse-tac-grid-item__field-label">
                  {{ col.label }}
                </div>
                <div class="fse-tac-grid-item__field-value">
                  {{ col.value }}
                </div>
              </q-card-section>
          </template>
        </q-card>
      </div>
    </template>
  </q-table>
</template>

<script>
import { date } from "quasar";

import { keyBy } from "src/services/utils";
import {datetime, number, empty} from "boot/filters";

const { formatDate } = date;

export default {
  name: "TacTableDetectionPressure",
  props: {
    detectionPressMinList: { type: Array, required: false, default: () => [] },
    detectionPressMaxList: { type: Array, required: false, default: () => [] }
  },
  data() {
    return {
      tablePagination: {
        sortBy: "date",
        descending: true,
        page: 1,
        rowsPerPage: 25
      }
    };
  },
  computed: {
    isDelegationTacWeak() {
      return this.$store.getters["isDelegationTacWeak"];
    },
    tableCols() {
      let result = [];

      result.push({
        name: "date",
        label: "Data",
        field: row => row.min.data,
        format: (val, row) => empty(datetime(val)),
        align: "left",
        sortable: true
      });

      result.push({
        name: "value",
        label: "Diastolica (minima)",
        field: row => row.min?.valore_numerico,
        format: (val, row) => empty(number(val)),
        align: "left",
        sortable: true
      });

      result.push({
        name: "value-max",
        label: "Sistolica (massima)",
        field: row => row.max?.valore_numerico,
        format: (val, row) => {
          return empty(number(val));
        },
        align: "left",
        sortable: true
      });

      result.push({
        name: "misure",
        label: "UnitÃ  di misura",
        field: row => row.min?.unita_misura?.codice,
        format: (val, row) => empty(val),
        align: "left"
      });

      result.push({
        name: "detection-mode",
        label: "ModalitÃ  rilevazione",
        field: row => row.min?.modalita?.descrizione_regionale,
        format: (val, row) => empty(val),
        align: "left",
        sortable: true
      });


      return result;
    },
    tableData() {
      let pressMaxKeyedByDate = keyBy(this.detectionPressMaxList, el =>
        formatDate(el.data, "YYYY-MM-DDTHH:mm")
      );

      return this.detectionPressMinList.map(el => {
        let key = formatDate(el.data, "YYYY-MM-DDTHH:mm");
        return { min: el, max: pressMaxKeyedByDate[key] };
      });
    }
  },
  created() {},
  methods: {
  }
};
</script>

<style scoped lang="sass"></style>
