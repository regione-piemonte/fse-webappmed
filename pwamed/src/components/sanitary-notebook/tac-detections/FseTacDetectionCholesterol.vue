<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div>
    <!-- VISUALIZZAZIONE GRAFICO -->
    <!-- ----------------------------------------------------------------------------------------------------- -->
    <div v-if="visualization === VISUALIZATION_MAP.GRAPH">
      <q-card >
        <q-card-section
          v-for="([
                  measure,
                  list
                ], index) in detectionTotListGroupedByMeasure"
          :key="'weight--' + measure"
        >
          <div class="text-bold text-h6">TOT - {{ measure }}</div>

          <tac-detection-graph-line-single
            :detection-list="list"
            color="#00a120"
            class="q-mt-md"
          />

          <q-separator class="q-mt-xl q-mb-sm"  v-if="index < detectionTotListGroupedByMeasure.length-1" />
        </q-card-section>
        <q-separator inset spaced />
        <q-card-section
          v-for="([measure, list], index) in detectionHdlListGroupedByMeasure"
          :key="'hdl--' + measure"
        >
          <div class="text-bold text-h6">HDL - {{ measure }}</div>

          <tac-detection-graph-line-single
            :detection-list="list"
            color="#f2cb05"
            class="q-mt-md"
          />

          <q-separator class="q-mt-xl q-mb-sm"  v-if="index < detectionHdlListGroupedByMeasure.length-1" />
        </q-card-section>

        <q-separator inset spaced />

        <q-card-section
          v-for="([measure, list], index) in detectionLdlListGroupedByMeasure"
          :key="'ldl--' + measure"
        >
          <div class="text-bold text-h6">LDL - {{ measure }}</div>

          <tac-detection-graph-line-single
            :detection-list="list"
            color="#d68800"
            class="q-mt-md"
          />

          <q-separator class="q-mt-xl q-mb-sm"  v-if="index < detectionLdlListGroupedByMeasure.length-1" />
        </q-card-section>

        <q-separator inset spaced />

        <q-card-section
          v-for="([measure, list], index)  in detectionTriglyceridesListGroupedByMeasure"
          :key="'triglycerides--' + measure"
        >
          <div class="text-bold text-h6">Trigliceridi - {{ measure }}</div>

          <tac-detection-graph-line-single
            :detection-list="list"
            color="#00a120"
            class="q-mt-md"
          />

          <q-separator class="q-mt-xl q-mb-sm"  v-if="index < detectionTriglyceridesListGroupedByMeasure.length-1" />
        </q-card-section>
      </q-card>
    </div>

    <!-- VISUALIZZAZIONE TABELLA -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div v-if="visualization === VISUALIZATION_MAP.TABLE" class="column q-gutter-md">

      <q-card>
        <q-expansion-item default-opened>
          <template #header>
            <q-item-section class="text-h6 text-bold">
              TOT
            </q-item-section>
          </template>

          <fse-tac-detection-list-table :detection-list="detectionTotList"/>
        </q-expansion-item>
      </q-card>

      <q-card>
        <q-expansion-item default-opened>
          <template #header>
            <q-item-section class="text-h6 text-bold">
              HDL
            </q-item-section>
          </template>

          <fse-tac-detection-list-table :detection-list="detectionHdlList"/>
        </q-expansion-item>
      </q-card>

      <q-card>
        <q-expansion-item default-opened>
          <template #header>
            <q-item-section class="text-h6 text-bold">
              LDL
            </q-item-section>
          </template>

          <fse-tac-detection-list-table :detection-list="detectionLdlList"/>
        </q-expansion-item>
      </q-card>

      <q-card>
        <q-expansion-item default-opened>
          <template #header>
            <q-item-section class="text-h6 text-bold">
              Trigliceridi
            </q-item-section>
          </template>

          <fse-tac-detection-list-table :detection-list="detectionTriglyceridesList"/>
        </q-expansion-item>
      </q-card>

    </div>


  </div>
</template>

<script>
import {VISUALIZATION_MAP} from "src/services/sanitary-notebook/config";
import FseTacDetectionListTable from "components/sanitary-notebook/tac-detections/FseTacDetectionListTable";
import {getDetectionEntryListByMeasure} from "src/services/sanitary-notebook/business-logic";
import TacDetectionGraphLineSingle from "components/sanitary-notebook/tac-detections/TacDetectionGraphLineSingle";

export default {
  name: "FseTacDetectionCholesterol",
  components: {TacDetectionGraphLineSingle, FseTacDetectionListTable},
  props: {
    visualization: {type: Number, default: 1},
    startDate: {type: [Date, String], default: null},
    endDate: {type: [Date, String], default: null}
  },
  data() {
    return {
      VISUALIZATION_MAP,
      detectionTotList:[],
      detectionHdlList:[],
      detectionLdlList:[],
      detectionTriglyceridesList:[]
    }
  },
  computed:{
    detectionTotListGroupedByMeasure(){
      return getDetectionEntryListByMeasure(this.detectionTotList);
    },
    detectionHdlListGroupedByMeasure(){
      return getDetectionEntryListByMeasure(this.detectionHdlList);
    },
    detectionLdlListGroupedByMeasure(){
      return getDetectionEntryListByMeasure(this.detectionLdlList);
    },
    detectionTriglyceridesListGroupedByMeasure(){
      return getDetectionEntryListByMeasure(this.detectionTriglyceridesList);
    }
  },
  created(){
    this.detectionHdlList = [
      {
        "id": 1513,
        "data": "2022-03-07T21:06:00.000+0100",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_HDL",
          "descrizione": "Colesterolo HDL",
          "descrizione_estesa": "Colesterolo HDL"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 39950,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:07:17.589+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_HDL",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1451,
        "data": "2021-10-14T07:18:00.000+0200",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_HDL",
          "descrizione": "Colesterolo HDL",
          "descrizione_estesa": "Colesterolo HDL"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 1110,
        "valore_testuale": null,
        "data_creazione": "2021-10-14T07:19:16.675+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_HDL",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      }
    ];
    this.detectionLdlList = [
      {
        "id": 1514,
        "data": "2022-03-07T21:06:00.000+0100",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_LDL",
          "descrizione": "Colesterolo LDL",
          "descrizione_estesa": "Colesterolo LDL"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 15,
        "valore_testuale": null,
        "data_creazione": "2022-03-07T21:07:17.605+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_LDL",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1452,
        "data": "2021-10-14T07:18:00.000+0200",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_LDL",
          "descrizione": "Colesterolo LDL",
          "descrizione_estesa": "Colesterolo LDL"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 110,
        "valore_testuale": null,
        "data_creazione": "2021-10-14T07:19:16.680+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_LDL",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      }
    ];
    this.detectionTotList = [
      {
        "id": 1565,
        "data": "2022-03-08T09:38:00.000+0100",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_TOT",
          "descrizione": "Colesterolo Totale",
          "descrizione_estesa": "Colesterolo Totale"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 8,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T09:38:08.271+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_TOT",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1553,
        "data": "2022-03-08T08:47:00.000+0100",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_TOT",
          "descrizione": "Colesterolo Totale",
          "descrizione_estesa": "Colesterolo Totale"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 100,
        "valore_testuale": null,
        "data_creazione": "2022-03-08T08:47:33.575+0100",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_TOT",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      },
      {
        "id": 1453,
        "data": "2021-10-14T07:18:00.000+0200",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "COL_TOT",
          "descrizione": "Colesterolo Totale",
          "descrizione_estesa": "Colesterolo Totale"
        },
        "modalita": {
          "codice": "AUT",
          "descrizione_regionale": "Autonoma",
          "descrizione_nazionale": "autonoma"
        },
        "valore_numerico": 220,
        "valore_testuale": null,
        "data_creazione": "2021-10-14T07:19:16.685+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "COL_TOT",
        "modalita_codice": "AUT",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      }
    ];
    this.detectionTriglyceridesList = [
      {
        "id": 1591,
        "data": "2022-07-05T11:47:00.000+0200",
        "gruppo": {
          "codice": "COL_GR",
          "descrizione": "Colesterolo",
          "descrizione_estesa": "Colesterolo"
        },
        "descrittore": {
          "codice": "TRIGL",
          "descrizione": "Trigliceridi",
          "descrizione_estesa": "Trigliceridi"
        },
        "modalita": {
          "codice": "MMG/PLS",
          "descrizione_regionale": "MMG/PLS",
          "descrizione_nazionale": "MMG"
        },
        "valore_numerico": 1,
        "valore_testuale": null,
        "data_creazione": "2022-07-21T11:47:31.103+0200",
        "data_aggiornamento": null,
        "unita_misura_codice": "mg/dL",
        "gruppo_codice": "COL_GR",
        "descrittore_codice": "TRIGL",
        "modalita_codice": "MMG/PLS",
        "unita_misura": {
          "codice": "mg/dL",
          "descrizione": "milligrammi per decilitro"
        }
      }
    ];
  }
}
</script>

<style scoped>

</style>
