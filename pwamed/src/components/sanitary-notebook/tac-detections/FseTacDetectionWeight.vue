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
          <div class="text-h6 text-bold">Peso - {{ measure }}</div>

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
  name: "FseTacDetectionWeight",
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
        "id": 1581,
        "data": "2022-05-19T09:49:00.000+0200",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 65,
        "valore_testuale": null,
        "data_creazione": "2022-05-19T09:49:34.656+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      },
      {
        "id": 1574,
        "data": "2022-03-08T09:59:00.000+0100",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 78,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:59:30.571+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      },
      {
        "id": 1547,
        "data": "2022-03-08T08:41:00.000+0100",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 68,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:41:57.860+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      },
      {
        "id": 1542,
        "data": "2022-03-08T08:35:00.000+0100",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 68,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:35:54.967+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      },
      {
        "id": 1539,
        "data": "2022-03-08T08:32:00.000+0100",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 1111,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:32:13.147+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      },
      {
        "id": 1507,
        "data": "2022-03-07T21:06:00.000+0100",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 68,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:06:28.832+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      },
      {
        "id": 1505,
        "data": "2022-03-07T15:52:00.000+0100",
        "gruppo": {
          "codice": "PESO_GR",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "descrittore": {
          "codice": "PESO",
          "descrizione": "Peso",
          "descrizione_estesa": "Peso"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 58,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T15:53:05.017+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Kg",
        "gruppo_codice": "PESO_GR",
        "descrittore_codice": "PESO",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Kg",
          "descrizione": "chilogrammi"
        }
      }
    ]
  }
}
</script>

<style scoped>

</style>
