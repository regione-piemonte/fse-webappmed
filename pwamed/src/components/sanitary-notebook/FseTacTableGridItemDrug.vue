<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card flat bordered class="fse-tac-grid-item">
    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Data assunzione
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ date | datetime }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Farmaco
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ drug }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        QuantitÃ 
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ amount }}
      </div>
    </q-card-section>


  </q-card>
</template>

<script>
export default {
  name: "FseTacTableGridItemDrug",
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
    drug() {
      return this.row?.farmaco;
    },
    amount() {
      return this.row?.quantita;
    }
  },
  created() {},
  methods: {}
};
</script>

<style scoped lang="sass"></style>
