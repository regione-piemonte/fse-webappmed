<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card flat bordered class="fse-tac-grid-item">
    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Data inizio
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ startDate | datetime }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Data fine
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ endDate | datetime | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Area interessata
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ area }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Descrizione
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ description }}
      </div>
    </q-card-section>


  </q-card>
</template>

<script>
export default {
  name: "FseTacTableGridItemSymptom",
  props: {
    row: { type: Object, required: false, default: null }
  },
  data() {
    return {};
  },
  computed: {
    startDate() {
      return this.row?.data_inizio;
    },
    endDate() {
      return this.row?.data_fine;
    },
    area() {
      return this.row?.area_interessata;
    },
    description() {
      return this.row?.descrizione;
    }
  },
  created() {},
  methods: {}
};
</script>

<style scoped lang="sass"></style>
