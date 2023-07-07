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
                  list,
                ], index) in detectionListGroupedByMeasure"
          :key="'weight--' + measure"
        >
          <div class="text-h6 text-bold">Emoglobina - {{ measure }}</div>

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

import FseTacDetectionListTable from "components/sanitary-notebook/tac-detections/FseTacDetectionListTable";
import {getDetectionEntryListByMeasure} from "src/services/sanitary-notebook/business-logic";
import TacDetectionGraphLineSingle from "components/sanitary-notebook/tac-detections/TacDetectionGraphLineSingle";
import {VISUALIZATION_MAP} from "src/services/sanitary-notebook/config";

export default {
  name: "FseTacDetectionHemoglobin",
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
        "id": 1568,
        "data": "2022-03-08T09:39:00.000+0100",
        "gruppo": {
          "codice": "EMO_GR",
          "descrizione": "Emoglobina",
          "descrizione_estesa": "Emoglobina"
        },
        "descrittore": {
          "codice": "EMO_GLIC",
          "descrizione": "Emoglobina glicata",
          "descrizione_estesa": "Emoglobina glicata"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 60,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:39:11.597+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "%",
        "gruppo_codice": "EMO_GR",
        "descrittore_codice": "EMO_GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "%",
          "descrizione": "percentuale"
        }
      },
      {
        "id": 1556,
        "data": "2022-03-08T08:49:00.000+0100",
        "gruppo": {
          "codice": "EMO_GR",
          "descrizione": "Emoglobina",
          "descrizione_estesa": "Emoglobina"
        },
        "descrittore": {
          "codice": "EMO_GLIC",
          "descrizione": "Emoglobina glicata",
          "descrizione_estesa": "Emoglobina glicata"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 67,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:49:57.619+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "%",
        "gruppo_codice": "EMO_GR",
        "descrittore_codice": "EMO_GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "%",
          "descrizione": "percentuale"
        }
      },
      {
        "id": 1516,
        "data": "2022-03-07T21:07:00.000+0100",
        "gruppo": {
          "codice": "EMO_GR",
          "descrizione": "Emoglobina",
          "descrizione_estesa": "Emoglobina"
        },
        "descrittore": {
          "codice": "EMO_GLIC",
          "descrizione": "Emoglobina glicata",
          "descrizione_estesa": "Emoglobina glicata"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 55560,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:07:42.744+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mmol/L",
        "gruppo_codice": "EMO_GR",
        "descrittore_codice": "EMO_GLIC",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mmol/L",
          "descrizione": "millimoli per litro"
        }
      }
    ]
  }
}
</script>

<style scoped>

</style>
