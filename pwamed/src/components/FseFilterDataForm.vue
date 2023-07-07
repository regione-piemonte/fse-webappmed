<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-form greedy  @submit="onFilter" class="q-mx-sm">
    <div class="row items-center q-col-gutter-x-lg q-col-gutter-y-md">
      <div class="col-12 col-sm-auto">
        <q-input
          type="date"
          v-model="startDate"
          label="Da"
          stack-label
          dense
          reactive-rules
          :rules="[ruleBeforeEndDate]"
          no-error-icon
        />
      </div>

      <div class="col-12 col-sm-auto">
        <q-input
          type="date"
          v-model="endDate"
          label="A"
          stack-label
          dense
          reactive-rules
          :rules="[ruleAfterStartDate]"
          no-error-icon
        />
      </div>

      <div class="col-12 col-sm-auto">
        <csi-buttons>
          <csi-button type="submit" no-min-width outline>
            Applica
          </csi-button>
        </csi-buttons>
      </div>
    </div>
  </q-form>
</template>

<script>
import {date} from "quasar";

const {formatDate, subtractFromDate, startOfDate, endOfDate} = date;

export default {
  name: "FseFilterDataForm",
  props:{
    initialFilter: {type:Boolean, default: true}
  },
  data(){
    return{
      startDate:null,
      endDate:null
    }
  },
  created() {
    let now = new Date();
    this.endDate = formatDate(now, "YYYY-MM-DD");
    this.startDate = formatDate(
      subtractFromDate(now, { month: 1 }),
      "YYYY-MM-DD"
    );
    if(this.initialFilter){
      this.onFilter()
    }

  },
  computed:{
    ruleBeforeEndDate() {
      return v =>
        v < this.endDate ||
        "La data di inizio non puÃ² essere maggiore della data di fine";
    },
    ruleAfterStartDate() {
      return v =>
        v > this.startDate ||
        "La data di fine non puÃ² essere inferiore alla data di inizio";
    }
  },
  methods:{
    onFilter() {
      let filters = {
        startDate: this.startDate,
        endDate: this.endDate
      }

      this.$emit('on-filter', filters)
    }
  }
}
</script>

<style scoped>

</style>
