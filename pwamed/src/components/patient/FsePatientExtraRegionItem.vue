<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="fit cursor-pointer">
    <q-card-section>
      <q-item class="q-px-none" dense>
        <q-item-section side>
          <q-icon :name="icon" size="md"/>
        </q-item-section>
        <q-item-section>
          <q-item-label>{{ taxCode }}</q-item-label>
        </q-item-section>
      </q-item>

      <csi-buttons class="q-mt-md">
        <csi-button @click="onSelectPatient">Vai al fascicolo</csi-button>
      </csi-buttons>
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
import FseDichiarationConsentDialog from "components/FseDichiarationConsentDialog";
import {CONSENTS_ACTIVATION, PATIENT, PATIENT_INFO} from "src/router/routes";
import { setAudit} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import FseNoConsentDialog from "components/FseNoConsentDialog";
import {consentsVersionControl} from "src/services/global/business-logic";
import {AUDIT_CODES} from "src/services/global/config";
export default {
  name: "FsePatientExtraRegionItem",
  components: {FseNoConsentDialog, FseDichiarationConsentDialog},
  props: {
    patient: {
      type: Object, default: null
    }
  },
  data() {
    return {
      showDichiarationDialog: false,
      showConsentsDialog:false,
      consents: null,
      isObsoleteVersion:false

    }
  },
  computed: {
    icon() {
      let patientIcon = this.patient?.sesso === 'F' ? 'avatar-donna' : 'avatar-uomo'
      return `img:icone/${patientIcon}.svg`
    },
    taxCode() {
      return this.patient?.codice_fiscale
    },
  },
  created() {
    this.consents = this.patient?.consensi
  },
  methods:{
    async onSelectPatient() {
      this.isLoading = true
      if (!this.consents) {
        this.consents =  await this.$store.dispatch('getPatientConsents', this.taxCode)
      }


      // Se le versioni non sono aggiornate deve risottomettere i consensi
      this.isObsoleteVersion = !consentsVersionControl(this.consents)
      if(this.isObsoleteVersion){
        this.showConsentsDialog = true
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
        patient.extra_regione = true
        this.$store.dispatch('setActivePatient', {patient})


      } catch (error) {
        let message = "Non Ã¨ stato possibile confermare il consenso"
        apiErrorNotify({error, message})
      }
      this.goToPatientInfo()
      this.isLoading = false

    },
    goToConsentsActivation() {

      let patient = {...this.patient}
      patient.consensi = this.consents
      patient.extra_regione = true
      this.$store.dispatch('setActivePatient', {patient})

      this.$router.push(CONSENTS_ACTIVATION)
    },
    goToPatientInfo(){
      this.$router.push(PATIENT_INFO)
    }

  }
}
</script>

<style scoped>

</style>
