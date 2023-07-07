<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card flat bordered class="fse-tac-grid-item">
    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Data creazione
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ createDate | datetime | empty }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Data aggiornamento
      </div>
      <div class="fse-tac-grid-item__field-value">
        {{ editDate | datetime | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Stato salute
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ healthStatus | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
       Stile di vita
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ lifeStyle | empty}}
      </div>
    </q-card-section>


  </q-card>
</template>

<script>
export default {
  name: "FseTacTableGridItemGeneralNotes",
  props: {
    row: { type: Object, required: false, default: null }
  },
  data() {
    return {};
  },
  computed: {

    createDate() {
      return this.row?.data_creazione;
    },
    editDate() {
      return this.row?.data_aggiornamento;
    },
    healthStatus() {
      return this.row?.stato_salute;
    },
    lifeStyle() {
      return this.row?.stile_vita;
    },

  },
  created() {},
  methods: {}
};
</script>

<style scoped lang="sass"></style>
