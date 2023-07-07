<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div>
    <!-- VISUALIZZAZIONE GRAFICO -->
    <!-- ----------------------------------------------------------------------------------------------------- -->
    <div v-if="visualization === VISUALIZATION_MAP.GRAPH">
      <q-card v-if="detectionListGroupedByMeasure">
        <q-card-section
          v-for="([
                  measure,
                  list
                ], index) in detectionListGroupedByMeasure"
          :key="'weight--' + measure"
        >
          <div class="text-h6 text-bold">Ossimetria - {{ measure }}</div>

          <tac-detection-graph-line-single
            :detection-list="list"
            color="#f2cb05"
            class="q-mt-md"
          />

          <q-separator class="q-mt-xl q-mb-sm"  v-if="index < detectionListGroupedByMeasure.length-1" />
        </q-card-section>
      </q-card>
    </div>

    <!-- VISUALIZZAZIONE TABELLA -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div v-if="visualization === VISUALIZATION_MAP.TABLE">

      <fse-tac-detection-list-table :detection-list="detectionList"/>
    </div>


  </div>
</template>

<script>
import {VISUALIZATION_MAP} from "src/services/sanitary-notebook/config";
import FseTacDetectionListTable from "components/sanitary-notebook/tac-detections/FseTacDetectionListTable";
import {getDetectionEntryListByMeasure} from "src/services/sanitary-notebook/business-logic";
import TacDetectionGraphLineSingle from "components/sanitary-notebook/tac-detections/TacDetectionGraphLineSingle";

export default {
  name: "FseTacDetectionOximetry",
  components: {TacDetectionGraphLineSingle, FseTacDetectionListTable},
  props: {
    visualization: {type: Number, default: 1},
    startDate: {type: [Date, String], default: null},
    endDate: {type: [Date, String], default: null}
  },
  data() {
    return {
      VISUALIZATION_MAP,
      detectionList:[]
    }
  },
  computed:{
    detectionListGroupedByMeasure(){
      return getDetectionEntryListByMeasure(this.detectionList);
    }
  },
  created(){
    this.detectionList =[
      {
        "id": 1519,
        "data": "2022-03-07T21:09:00.000+0100",
        "gruppo": {
          "codice": "OSS_GR",
          "descrizione": "Ossimetria",
          "descrizione_estesa": "Ossimetria"
        },
        "descrittore": {
          "codice": "OSS",
          "descrizione": "Ossimetria",
          "descrizione_estesa": "Ossimetria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 666,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:09:30.193+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "SpO2%",
        "gruppo_codice": "OSS_GR",
        "descrittore_codice": "OSS",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "SpO2%",
          "descrizione": "% di saturazione dell'ossigeno"
        }
      },
      {
        "id": 1554,
        "data": "2022-03-08T08:48:00.000+0100",
        "gruppo": {
          "codice": "OSS_GR",
          "descrizione": "Ossimetria",
          "descrizione_estesa": "Ossimetria"
        },
        "descrittore": {
          "codice": "OSS",
          "descrizione": "Ossimetria",
          "descrizione_estesa": "Ossimetria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 66,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:48:26.213+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "SpO2%",
        "gruppo_codice": "OSS_GR",
        "descrittore_codice": "OSS",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "SpO2%",
          "descrizione": "% di saturazione dell'ossigeno"
        }
      },
      {
        "id": 1566,
        "data": "2022-03-08T09:38:00.000+0100",
        "gruppo": {
          "codice": "OSS_GR",
          "descrizione": "Ossimetria",
          "descrizione_estesa": "Ossimetria"
        },
        "descrittore": {
          "codice": "OSS",
          "descrizione": "Ossimetria",
          "descrizione_estesa": "Ossimetria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 88,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:38:53.580+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "SpO2%",
        "gruppo_codice": "OSS_GR",
        "descrittore_codice": "OSS",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "SpO2%",
          "descrizione": "% di saturazione dell'ossigeno"
        }
      }
    ]
  }
}
</script>

<style scoped>

</style>
