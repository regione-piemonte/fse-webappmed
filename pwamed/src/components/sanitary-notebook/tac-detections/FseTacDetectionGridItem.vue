<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card flat bordered class="fse-tac-grid-item">
    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Data
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ date | datetime | empty }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        ModalitÃ  rilevazione
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ detectionMode | empty }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Misura
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ value | number }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        UnitÃ  di misura
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ measure | empty }}
      </div>
    </q-card-section>


  </q-card>
</template>

<script>
export default {
  name: "FseTacDetectionGridItem",
  props: {
    row: { type: Object, required: false, default: null }
  },
  data() {
    return {};
  },
  computed: {
    date() {
      return this.row?.data;
    },
    detectionMode() {
      return this.row?.modalita_rilevazione?.descrizione_regionale;
    },
    value() {
      return this.row?.valore_numerico;
    },
    measure() {
      return this.row?.unita_misura?.codice;
    }
  },
  created() {},
  methods: {}
}
</script>

<style lang="sass">




</style>
