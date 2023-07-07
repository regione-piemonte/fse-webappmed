<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="page-screening-detail" padding>
    <csi-page-title class="q-mb-lg">{{ screeningTypeLabel }}</csi-page-title>
    <div class="row justify-center items-center q-gutter-lg">
      <!--   Generali -->
      <q-card class="col-12 col-md-10">
        <q-card-section>
          <div class="row q-col-gutter-lg items-start">
            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Data</q-item-label>
                <q-item-label class="text-bold">{{ issueDate | date | empty }}</q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Esame</q-item-label>
                <q-item-label class="text-bold ">{{ screeningName | empty }}</q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Esito</q-item-label>
                <q-item-label :class="screeningResultColor" class="text-bold">{{ screeningResult | upperCase |empty }}
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Azienda</q-item-label>
                <q-item-label class="text-bold">{{ asl | empty }}</q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">UnitÃ  operativa</q-item-label>
                <q-item-label class="text-bold">{{ opUnit | empty }}</q-item-label>
              </q-item-section>
            </q-item>
          </div>

        </q-card-section>
      </q-card>

      <!--   Giudizio diagnostico -->
      <q-card class="col-12 col-md-10">
        <q-card-section>
          <div class="text-h5 text-bold">Giudizio diagnostico</div>
        </q-card-section>

        <q-card-section v-for="(diagnosticJudgment, index) in diagnosticJudgments" :key="index">
          <div class="row q-col-gutter-lg items-start">
            <q-item class="col-12 col-md-4" >
              <q-item-section>
                <q-item-label caption class="text-black">Prestazioni</q-item-label>
                <q-item-label v-for="service in diagnosticJudgment.prestazioni" :key="service.codice" class="text-bold">
                  {{ service.descrizione | empty}}
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4" >
              <q-item-section>
                <q-item-label caption class="text-black">Giudizio</q-item-label>
                <q-item-label class="text-bold " v-if="diagnosticJudgment.giudizio">{{diagnosticJudgment.giudizio.descrizione | empty}}</q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Medici</q-item-label>
                <q-item-label class="text-bold" v-for="(doctor, index) in diagnosticJudgment.medici" :key="index">{{doctor.nome | empty}} ({{doctor.figura_professionale | empty}})</q-item-label>
              </q-item-section>
            </q-item>
          </div>

        </q-card-section>
      </q-card>


      <!--   INDICAZIONE FINALE-->
      <q-card class="col-12 col-md-10">
        <q-card-section>
          <div class="text-h5 text-bold">Indicazione finale</div>
        </q-card-section>

        <q-card-section>
          <div class="row q-col-gutter-lg items-start">
            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Giudizio</q-item-label>
                <q-item-label class="text-bold">{{finalIndication | empty}}</q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-4">
              <q-item-section>
                <q-item-label caption class="text-black">Medici</q-item-label>
                <q-item-label class="text-bold" v-for="(doctor,index) in finalIndicationDoctors" :key="index" >
                  {{doctor.nome}} ({{doctor.figura_professionale | empty}})
                </q-item-label>

              </q-item-section>
            </q-item>


          </div>

        </q-card-section>
      </q-card>

      <!--   Trattamento erogato -->
      <q-card class="col-12 col-md-10">
        <q-card-section>
          <div class="text-h5 text-bold">Trattamento erogato</div>
        </q-card-section>
        <q-card-section>
          <div class="row q-col-gutter-lg items-start">
            <q-item class="col-12 col-md-6">
              <q-item-section>
                <q-item-label caption class="text-black">Data</q-item-label>
                <q-item-label class="text-bold">{{ treatmentDate | date | empty }}</q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-6">
              <q-item-section>
                <q-item-label caption class="text-black">Trattamento</q-item-label>
                <template v-if="treatments.length>0">
                  <q-item-label class="text-bold" v-for="(treatment,index) in treatments" :key="index">
                    {{treatment.descrizione | empty }}</q-item-label>
                </template>
                <q-item-label class="text-bold" v-else>-</q-item-label>

              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-6">
              <q-item-section>
                <q-item-label caption class="text-black">Struttura sanitaria</q-item-label>
                <q-item-label class="text-bold">
                  {{treatmentStructure | empty}}

                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-6">
              <q-item-section>
                <q-item-label caption class="text-black">Esito</q-item-label>
                <q-item-label class="text-bold">{{providedTreatment.esito | empty}}</q-item-label>
              </q-item-section>
            </q-item>
          </div>
        </q-card-section>
      </q-card>
    </div>

  </csi-page>
</template>

<script>
import {SCREENING_TYPES_LIST} from "src/services/screening/config";
import {equalsIgnoreCase} from "src/services/utils";
import {SCREENING} from "src/router/routes";

const SCREENING_RESULT_MAP = {
  POSITIVE: 'positivo',
  NEGATIVE: 'negativo'
}
export default {
  name: "PageScreeningDetail",
  data() {
    return {
      screeningType: null,
      screening: null,
      SCREENING_TYPES_LIST
    }
  },
  computed: {
    screeningExamType() {
      return this.screening?.tipo?.codice
    },
    screeningTypeLabel() {
      return this.screeningType?.descrizione ?? ''
    },
    iconName() {
      let screeningType = this.screeningType?.codice
      return screeningType ? `img:icone/screening/${screeningType}.svg` : '';
    },
    screeningName() {
      return this.screening?.tipo?.descrizione
    },
    issueDate() {
      return this.screening?.data
    },
    asl() {
      return this.screening?.azienda_sanitaria?.descrizione
    },
    opUnit() {
      return this.screening?.unita_operativa?.descrizione
    },
    screeningResult() {
      return this.screening?.esito
    },
    screeningResultColor() {
      let color = ''
      if (equalsIgnoreCase(this.screeningResult, SCREENING_RESULT_MAP.POSITIVE)) color = 'text-red-7'
      else if (equalsIgnoreCase(this.screeningResult, SCREENING_RESULT_MAP.NEGATIVE)) color = 'text-green-7'

      return color
    },

    diagnosticJudgments(){
      return this.screening?.giudizi_diagnostici
    },

    finalIndication(){
      return this.screening?.indicazione_finale?.giudizione?.descrizione
    },
    finalIndicationDoctors(){
      return this.screening?.indicazione_finale?.medici
    },
    providedTreatment(){
      return this.screening?.intervento_erogato
    },
    treatments(){
      return this.providedTreatment?.trattamenti ?? []
    },
    treatmentDate(){
      return this.providedTreatment?.data
    },
    treatmentStructure(){
      return this.providedTreatment?.struttura_sanitaria?.descrizione
    }

  },
  created() {
    let {id, screeningType, screening} = this.$route.params

    if (!screening) {
      this.$router.replace(SCREENING)
      return
    }

    this.screeningType = screeningType,
      this.screening = screening
  },
  methods:{
  }
}
</script>

<style scoped>

</style>
