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
        {{ date | date }}
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Colazione (calorie)
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        <template v-if="!breakfastKcal">-</template>
        <template v-else>
          {{ breakfastDescription }}
          ({{ breakfastKcal | number }})
        </template>
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Pranzo (calorie)
      </div>
      <div class="fse-tac-grid-item__field-value-label">
        <template v-if="!lunchKcal">-</template>
        <template v-else>
          {{ lunchDescription }}
          ({{ lunchKcal | number }})
        </template>
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Cena (calorie)
      </div>
      <div class="fse-tac-grid-item__field-value">
        <template v-if="!dinnerKcal">-</template>
        <template v-else>
          {{ dinnerDescription }}
          ({{ dinnerKcal | number }})
        </template>
      </div>
    </q-card-section>

    <q-card-section>
      <div class="fse-tac-grid-item__field-label">
        Spuntini (calorie)
      </div>
      <div class="fse-tac-grid-item__field-value">
        <template v-if="!snacksKcal">-</template>
        <template v-else>
          {{ snacksDescription }}
          ({{ snacksKcal | number }})
        </template>
      </div>
    </q-card-section>


  </q-card>
</template>

<script>
export default {
  name: "FseTacTableGridItemDiet",
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
    breakfastDescription() {
      return this.row?.colazione_descrizione;
    },
    breakfastKcal() {
      return this.row?.colazione_calorie;
    },
    lunchDescription() {
      return this.row?.pranzo_descrizione;
    },
    lunchKcal() {
      return this.row?.pranzo_calorie;
    },
    dinnerDescription() {
      return this.row?.cena_descrizione;
    },
    dinnerKcal() {
      return this.row?.cena_calorie;
    },
    snacksDescription() {
      return this.row?.spuntini_descrizione;
    },
    snacksKcal() {
      return this.row?.spuntini_calorie;
    }
  },
  created() {},
  methods: {}
};
</script>

<style scoped lang="sass"></style>
