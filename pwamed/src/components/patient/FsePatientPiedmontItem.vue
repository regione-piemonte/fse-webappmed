<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="fit cursor-pointer">
    <q-card-section class="column q-pa-none  items-stretch justify-between full-height">
      <q-item class="q-py-md  bg-grey-3 " dense>
        <q-item-section side>
          <q-icon :name="icon" size="md"/>
        </q-item-section>
        <q-item-section>
          <q-item-label><strong>{{ fullName }}</strong></q-item-label>
          <q-item-label>{{ taxCode }}</q-item-label>
        </q-item-section>
      </q-item>
      <div class="col full-height q-pa-md">
        <div class="row items-start q-col-gutter-lg">
          <div class="col-12 col-md-6">
            <div class=" text-caption">Data di nascita</div>
            <div><strong>{{ birthDate | date }}</strong></div>
          </div>
          <div class="col-12 col-md-6">
            <div class=" text-caption">Sesso</div>
            <div><strong>{{ gender | empty }}</strong></div>
          </div>
          <div class="col-12 col-md">
            <div class=" text-caption">Luogo di nascita</div>
            <div><strong>{{ birthPlace | empty }}</strong></div>
          </div>
        </div>

      </div>
      <div class="q-pa-md col-auto">

        <csi-buttons>
          <csi-button :loading="isLoading" no-min-width @click="onSelectPatient">Vai al fascicolo</csi-button>
        </csi-buttons>

      </div>
    </q-card-section>
    <!--DIALOGS-->
    <!-- ---------------------------------------------------------------------------------------------------  -->
    <fse-dichiaration-consent-dialog v-model="showDichiarationDialog" :consents="consents" :patient="patient" @on-confirm="onConfirmConsentDichiaration"/>
    <fse-no-consent-dialog
      v-model="showConsentsDialog"
      :consents="consents"
      :is-obsolete-version="isObsoleteVersion"
      @consents-activation="goToConsentsActivation"
      @consult-reports="showDichiarationDialog = true"
    />
  </q-card>
</template>

<script>

import CsiCardItemBar from "components/core/CsiCardItemBar";
import {CONSENTS_ACTIVATION, PATIENT, PATIENT_INFO} from "src/router/routes";
import FseDichiarationConsentDialog from "components/FseDichiarationConsentDialog";
import {date} from "quasar";
import {apiErrorNotify, startCase} from "src/services/utils";
import { setAudit} from "src/services/api";
import FseNoConsentDialog from "components/FseNoConsentDialog";
import {consentsVersionControl} from "src/services/global/business-logic";
import {AUDIT_CODES} from "src/services/global/config";

const {subtractFromDate, startOfDate, endOfDate, formatDate} = date;
export default {
  name: "FsePatientPiedmontItem",
  components: {FseNoConsentDialog, FseDichiarationConsentDialog},
  props: {
    patient: {
      type: Object, default: null,
    },

  },
  data() {
    return {
      showDichiarationDialog: false,
      showConsentsDialog: false,
      isLoading: false,
      consents: null,
      consentsErrorMessage:null,
      isObsoleteVersion:false
    }
  },
  computed: {
    icon() {
      let patientIcon = this.patient?.sesso === 'F' ? 'avatar-donna' : 'avatar-uomo'
      return `img:icone/${patientIcon}.svg`
    },
    fullName() {
      return `${this.patient?.nome} ${this.patient?.cognome}`
    },
    taxCode() {
      return this.patient?.codice_fiscale
    },
    birthDate() {
      return this.patient?.data_nascita
    },
    gender() {
      let gender = ''
      if (this.patient?.sesso === 'F')
        gender = 'Femmina'
      else
        gender = 'Maschio'

      return gender
    },

    birthPlace() {
      let city = this.patient?.comune_nascita?.descrizione
      let nation = this.patient?.stato_nascita?.descrizione
      if (city)
        city = startCase(city) + ' - '

      nation = startCase(nation)

      return (`${city}${nation}`)
    }
  },
  created() {
    this.consents = this.patient?.consensi
  },
  methods: {
    async onSelectPatient() {
      this.isLoading = true
      if (!this.consents) {
        this.consents = await this.$store.dispatch('getPatientConsents', this.taxCode)

      }

      // Se le versioni non sono aggiornate deve risottomettere i consensi
      this.isObsoleteVersion = !consentsVersionControl(this.consents)
      if(this.isObsoleteVersion){
        //@TODO teporaneamente tolto il controllo e si passa direttamente al paziente (come richiesto da BErti)
        //this.showConsentsDialog = true
        this.showDichiarationDialog = true
      }else if (this.consents?.consenso_alimentazione && this.consents?.consenso_consultazione) {
        this.showDichiarationDialog = true
      } else {
        this.showConsentsDialog = true
      }


      this.isLoading = false
    },
    async onConfirmConsentDichiaration() {
      this.isLoading = true
      try {
        let payload = {
          cittadino_id: this.taxCode,
          codice_audit: AUDIT_CODES.CONSENTS_CONFIRM
        }

        let response = await setAudit(payload)

        let patient = {...this.patient}
        patient.consensi = this.consents
        this.$store.dispatch('setActivePatient', {patient})

        this.goToPatientInfo()
      } catch (error) {
        let message = "Non Ã¨ stato possibile confermare il consenso"
        apiErrorNotify({error, message})
      }

      this.isLoading = false

    },
    goToPatientInfo() {

      this.$router.push(PATIENT_INFO)
    },
    goToConsentsActivation() {

      let patient = {...this.patient}
      patient.consensi = this.consents

      console.log(patient)
      this.$store.dispatch('setActivePatient', {patient})

      this.$router.push(CONSENTS_ACTIVATION)
    }

  }
}
</script>

<style scoped>

</style>
