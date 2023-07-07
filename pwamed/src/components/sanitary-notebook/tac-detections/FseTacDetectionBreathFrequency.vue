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
          <div class="text-h6 text-bold">Frequenza - {{ measure }}</div>

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
  name: "FseTacDetectionBreathFrequency",
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
        "id": 1587,
        "data": "2022-05-19T09:49:00.000+0200",
        "gruppo": {
          "codice": "FREQ_RESP_GR",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "descrittore": {
          "codice": "FREQ_RESP",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 120,
        "valore_testuale": null,
        "data_creazione": "2022-05-19T09:50:03.860+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "atti per minuto",
        "gruppo_codice": "FREQ_RESP_GR",
        "descrittore_codice": "FREQ_RESP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "atti per minuto",
          "descrizione": "atti per minuto"
        }
      },
      {
        "id": 1564,
        "data": "2022-03-08T09:37:00.000+0100",
        "gruppo": {
          "codice": "FREQ_RESP_GR",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "descrittore": {
          "codice": "FREQ_RESP",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 6,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:38:00.590+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "atti per minuto",
        "gruppo_codice": "FREQ_RESP_GR",
        "descrittore_codice": "FREQ_RESP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "atti per minuto",
          "descrizione": "atti per minuto"
        }
      },
      {
        "id": 1552,
        "data": "2022-03-08T08:47:00.000+0100",
        "gruppo": {
          "codice": "FREQ_RESP_GR",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "descrittore": {
          "codice": "FREQ_RESP",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 44,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:47:21.707+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "atti per minuto",
        "gruppo_codice": "FREQ_RESP_GR",
        "descrittore_codice": "FREQ_RESP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "atti per minuto",
          "descrizione": "atti per minuto"
        }
      },
      {
        "id": 1551,
        "data": "2022-03-08T08:46:00.000+0100",
        "gruppo": {
          "codice": "FREQ_RESP_GR",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "descrittore": {
          "codice": "FREQ_RESP",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 68,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:46:09.160+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "atti per minuto",
        "gruppo_codice": "FREQ_RESP_GR",
        "descrittore_codice": "FREQ_RESP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "atti per minuto",
          "descrizione": "atti per minuto"
        }
      },
      {
        "id": 1512,
        "data": "2022-03-07T21:06:00.000+0100",
        "gruppo": {
          "codice": "FREQ_RESP_GR",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "descrittore": {
          "codice": "FREQ_RESP",
          "descrizione": "Freq. resp.",
          "descrizione_estesa": "Frequenza respiratoria"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 65,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:06:56.423+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "atti per minuto",
        "gruppo_codice": "FREQ_RESP_GR",
        "descrittore_codice": "FREQ_RESP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "atti per minuto",
          "descrizione": "atti per minuto"
        }
      }
    ]
  }
}
</script>

<style scoped>

</style>
