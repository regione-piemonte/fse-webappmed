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
          <div class="text-h6 text-bold">Glicemia - {{ measure }}</div>

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
  name: "FseTacDetectionGlycemia",
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
    this.detectionList = [
      {
        "id": 1567,
        "data": "2022-03-08T09:38:00.000+0100",
        "gruppo": {
          "codice": "GLIC_GR",
          "descrizione": "Glicemia",
          "descrizione_estesa": "Glicemia"
        },
        "descrittore": {
          "codice": "GLIC",
          "descrizione": "Glic. dig.",
          "descrizione_estesa": "Glicemia a digiuno"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 1300,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:39:04.034+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "GLIC_GR",
        "descrittore_codice": "GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1555,
        "data": "2022-03-08T08:48:00.000+0100",
        "gruppo": {
          "codice": "GLIC_GR",
          "descrizione": "Glicemia",
          "descrizione_estesa": "Glicemia"
        },
        "descrittore": {
          "codice": "GLIC",
          "descrizione": "Glic. dig.",
          "descrizione_estesa": "Glicemia a digiuno"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 660,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:48:37.672+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "GLIC_GR",
        "descrittore_codice": "GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1515,
        "data": "2022-03-07T21:07:00.000+0100",
        "gruppo": {
          "codice": "GLIC_GR",
          "descrizione": "Glicemia",
          "descrizione_estesa": "Glicemia"
        },
        "descrittore": {
          "codice": "GLIC",
          "descrizione": "Glic. dig.",
          "descrizione_estesa": "Glicemia a digiuno"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 900,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:07:31.982+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "GLIC_GR",
        "descrittore_codice": "GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1437,
        "data": "2021-10-01T08:32:00.000+0200",
        "gruppo": {
          "codice": "GLIC_GR",
          "descrizione": "Glicemia",
          "descrizione_estesa": "Glicemia"
        },
        "descrittore": {
          "codice": "GLIC",
          "descrizione": "Glic. dig.",
          "descrizione_estesa": "Glicemia a digiuno"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 100,
        "valore_testuale": null,
        "data_creazione": "2021-10-01T08:32:51.455+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "GLIC_GR",
        "descrittore_codice": "GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      }
    ]
  }
}
</script>

<style scoped>

</style>
