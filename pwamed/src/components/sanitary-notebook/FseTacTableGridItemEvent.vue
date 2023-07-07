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
        {{ date | datetime }}
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
  name: "FseTacTableGridItemEvent",
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
    date() {
      return this.row?.data;
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
