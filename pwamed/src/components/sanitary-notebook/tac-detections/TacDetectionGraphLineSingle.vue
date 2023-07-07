<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <the-graph-line
    :chart-data="chartData"
    :options="chartOptions"
    :height="height"
    class="tac-detection-graph-line-single"
  />
</template>

<script>
import TheGraphLine from "components/core/TheGraphLine";
import {orderBy} from "src/services/utils";

import { date } from "quasar";
import {getDetectionDatasetPointList} from "src/services/sanitary-notebook/business-logic";

const { formatDate } = date;

export default {
  name: "TacDetectionGraphLineSingle",
  components: {TheGraphLine},
  props: {
    detectionList: { type: Array, required: false, default: () => [] },
    height: { type: Number, required: false, default: 300 },
    color: { type: String, required: false, default: "green" }
  },
  computed: {
    chartData() {
      let labelList = orderBy(this.detectionList, ["data"], ["asc"]);
      labelList = labelList.map(d => formatDate(d.data, "DD/MM/YYYY HH:mm"));
      labelList = [...new Set(labelList)];

      let datasetList = [
        {
          data: getDetectionDatasetPointList(this.detectionList),
          backgroundColor: this.color,
          borderColor: this.color,
          lineTension: 0,
          fill: false,
          spanGaps: true
        }
      ];

      return {
        labels: labelList,
        datasets: datasetList
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
