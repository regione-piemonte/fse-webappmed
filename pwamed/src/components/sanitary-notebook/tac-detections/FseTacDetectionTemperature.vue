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
          <div class="text-h6 text-bold">Temperatura - {{ measure }}</div>

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
  name: "FseTacDetectionTemperature",
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
        "id": 1583,
        "data": "2022-05-19T09:49:00.000+0200",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 36.5,
        "valore_testuale": null,
        "data_creazione": "2022-05-19T09:49:42.770+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1575,
        "data": "2022-03-08T10:02:00.000+0100",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 39.599998474121094,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T10:02:17.700+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1560,
        "data": "2022-03-08T09:20:00.000+0100",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 35,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:20:13.049+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1548,
        "data": "2022-03-08T08:44:00.000+0100",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 39,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:44:29.940+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1543,
        "data": "2022-03-08T08:35:00.000+0100",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 39,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:36:04.703+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1508,
        "data": "2022-03-07T21:06:00.000+0100",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 38,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:06:35.496+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1447,
        "data": "2021-10-14T07:18:00.000+0200",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 37.29999923706055,
        "valore_testuale": null,
        "data_creazione": "2021-10-14T07:18:32.874+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1434,
        "data": "2021-10-01T08:32:00.000+0200",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 39,
        "valore_testuale": null,
        "data_creazione": "2021-10-01T08:32:19.220+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1435,
        "data": "2021-09-30T08:32:00.000+0200",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 38,
        "valore_testuale": null,
        "data_creazione": "2021-10-01T08:32:32.166+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1436,
        "data": "2021-09-28T08:32:00.000+0200",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 37.5,
        "valore_testuale": null,
        "data_creazione": "2021-10-01T08:32:38.741+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      },
      {
        "id": 1433,
        "data": "2021-09-27T08:31:00.000+0200",
        "gruppo": {
          "codice": "TEMP_GR",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "descrittore": {
          "codice": "TEMP",
          "descrizione": "Temperatura",
          "descrizione_estesa": "Temperatura"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 37.5,
        "valore_testuale": null,
        "data_creazione": "2021-10-01T08:32:08.447+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "Â°C",
        "gruppo_codice": "TEMP_GR",
        "descrittore_codice": "TEMP",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "Â°C",
          "descrizione": "gradi centigradi"
        }
      }
    ]
  }
}
</script>

<style scoped>

</style>
