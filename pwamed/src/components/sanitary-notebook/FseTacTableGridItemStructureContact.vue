<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="tac-table-grid-item">
    <q-card-section>
      <div class="text-bold text-caption">
        Data inizio
      </div>
      <div class="tac-table-grid-item__field-value">
        {{ startDate | date("DD/MM/YYYY") | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="text-bold text-caption">
        Data fine
      </div>
      <div class="tac-table-grid-item__field-value">
        {{ endDate |  date("DD/MM/YYYY") | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="text-bold text-caption">
        Nome struttura
      </div>
      <div class="text-bold text-caption">
        {{ description | empty }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="text-bold text-caption">
        Tipo di struttura
      </div>
      <div class="tac-table-grid-item__field-value-label">
        {{ structureTypeLabel  | empty }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="text-bold text-caption">
        Motivazione
      </div>
      <div class="tac-table-grid-item__field-value-label">
        {{ motivation  | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="text-bold text-caption">
        Tipo di contatto
      </div>
      <div class="tac-table-grid-item__field-value-label">
        {{ contactTypeLabel  | empty }}
      </div>
    </q-card-section>


  </q-card>
</template>

<script>
import {STRUCTURE_TYPE_CODE_MAP} from "src/services/sanitary-notebook/config";

export default {
  name: "FseTacTableGridItemStructureContact",
  props: {
    row: { type: Object, required: false, default: null }
  },
  data() {
    return {};
  },
  computed: {
    isDelegationTacWeak() {
      return this.$store.getters["isDelegationTacWeak"];
    },
    startDate() {
      return this.row?.data_contatto_inizio;
    },
    endDate() {
      return this.row?.data_contatto_fine;
    },
    structureTypeLabel() {
      return this.row?.tipologia_struttura?.descrizione;
    },
    contactTypeLabel() {
      return this.row?.tipologia_contatto?.descrizione;
    },
    description() {
      return this.row?.denominazione;
    },
    motivation() {
      return this.row?.motivazione;
    }
  },
  created() {},
  methods: {}
};
</script>

<style  lang="sass">
.tac-table-grid-item__field-label
  font-weight: bold

</style>
