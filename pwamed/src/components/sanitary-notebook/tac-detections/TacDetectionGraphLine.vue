<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <the-graph-line
    :chart-data="chartData"
    :options="chartOptions"
    :height="height"
    class="tac-detection-graph-line"
  />
</template>

<script>
import TheGraphLine from "components/core/TheGraphLine";


export default {
  name: "TacDetectionGraphLine",
  components: {TheGraphLine},
  props: {
    labelList: { type: Array, required: false, default: () => [] },
    datasetList: { type: Array, required: false, default: () => [] },
    height: { type: Number, required: false, default: 300 },
    legend: { type: Boolean, required: false, default: false },
  },
  computed: {
    chartData() {
      return {
        labels: this.labelList,
        datasets: this.datasetList
      };
    },
    chartOptions() {
      let result = {
        legend: { display: false },
        tooltips: { enabled: true },
        animation: { duration: 0 },
        hover: { animationDuration: 0 },
        responsiveAnimationDuration: 0,
        maintainAspectRatio: false
      };

      if (this.legend) {
        result.legend = {
          display: true,
          position: "top",
          align: "center",
          labels: { usePointStyle: true, padding: 16 }
        };
      }

      return result;
    }
  }
}
</script>

<style scoped>

</style>
