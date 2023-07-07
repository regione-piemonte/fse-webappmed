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
        {{ startDate | datetime | empty }}
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
        Descrizione
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ description | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Area interessata
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ area | empty}}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        IntensitÃ 
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        {{ intensity | empty}}
      </div>
    </q-card-section>

  </q-card>
</template>

<script>
export default {
  name: "FseTacTableGridItemPain",
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
    description() {
      return this.row?.descrizione;
    },
    area() {
      return this.row?.area_interessata;
    },
    intensity() {
      return this.row?.intensita?.descrizione;
    }
  },
  created() {},
  methods: {}
};
</script>

<style scoped lang="sass"></style>
