<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>

    <template v-if="isLoading">
      <csi-inner-loading :showing="true" block/>
    </template>
    <template v-else-if="!tokenLCCE">
      <csi-banner class="q-my-lg" type="warning">
        Nessun token trovato
      </csi-banner>
    </template>
    <template v-else-if="!user">
      <csi-banner class="q-my-lg" type="warning">
        <template v-if="tokenError">{{ tokenError }}</template>
        <template v-else>Utente non trovato</template>
      </csi-banner>
    </template>

    <template v-else>
      <fse-profile-choice :confirming="isLoadingPatientInfo" :role="userRole" hide-role-list
                          @on-confirm="onConfirmRolesParams"/>


      <!--DIALOGS-->
      <!-- ---------------------------------------------------------------------------------------------------  -->
      <fse-dichiaration-consent-dialog v-model="showDichiarationDialog" :consents="consents"
                                       :patient="isPatientPiedmont ? patient : patientInfo"
                                       @on-confirm="onConfirmConsentDichiaration"/>
      <fse-no-consent-dialog
        v-model="showConsentsDialog"
        :consents="consents"
        :is-obsolete-version="isObsoleteVersion"
        @consents-activation="goToConsentsActivation"
        @consult-reports="showDichiarationDialog = true"
      />
    </template>
  </csi-page>
</template>

<script>
import {getConsents, getDoctorSystems, getPatientList, getUser, setAudit} from "src/services/api";
import {apiErrorNotify, isEmpty} from "src/services/utils";
import FseSystemMenuItem from "components/FseSystemMenuItem";
import {LocalStorage, SessionStorage} from "quasar";
import {AUDIT_CODES, LCCE_SESSION_TOKEN, USER_SESSION_KEY} from "src/services/global/config";
import {APP, CONSENTS_ACTIVATION, DOCUMENTS, PATIENT_INFO} from "src/router/routes";
import FseProfileChoice from "components/FseProfileChoice";
import FseDichiarationConsentDialog from "components/FseDichiarationConsentDialog";
import FseNoConsentDialog from "components/FseNoConsentDialog";
import {consentsVersionControl} from "src/services/global/business-logic";

export default {
  name: "PageEcwdmeHome",
  components: {FseNoConsentDialog, FseDichiarationConsentDialog, FseProfileChoice},
  data() {
    return {
      isLoading: false,
      patientInfo: null,
      patient: null,
      tokenLCCE: null,
      userRole: null,
      isLoadingPatientInfo: false,
      consents: null,
      showDichiarationDialog: false,
      showConsentsDialog: false,
      isPatientPiedmont: false,
      tokenError: null,
      isObsoleteVersion:false
    }
  },
  computed: {
    user() {
      return this.$store.getters["getUser"];
    },
    systemList() {
      return this.$store.getters["getSystemList"]
    },

    systemSelected() {
      return this.$store.getters['getSeletedSystem']
    },
  },
  async created() {
    this.tokenLCCE = this.$route.query?.tokenLCCE
    console.log(' this.tokenLCCE', this.tokenLCCE)

    if (!this.tokenLCCE) return
    this.isLoading = true

    //cancelliamo precedenti sessioni attive
    SessionStorage.set(USER_SESSION_KEY, null)
    //Salviamo il token nello storage perchÃ© ci servirÃ  per tutte le chiamate
    SessionStorage.set(LCCE_SESSION_TOKEN, this.tokenLCCE)


    try {
      this.errorMessage = null

      let {data: user} = await getUser();

      if (user) {
        this.$store.dispatch("setUser", {user});
        // Salvo dati dell'utente sul sessionStorage
        SessionStorage.set(USER_SESSION_KEY, user)

        this.userRole = user.richiedente?.ruolo

        this.patientInfo = {
          codice_fiscale: user.cf_paziente,
          tipo_documento: user.tipo_documento
        }
      }

    } catch (error) {

      if (error.response.status === 500) {
        this.tokenError = "Non Ã¨ stato posssibile recuperare i dati dell'utente. Rieseguire l'accesso."
      }
    }

    this.isLoading = false
  },
  methods: {

    async onConfirmRolesParams() {
      this.isLoadingPatientInfo = true
      let taxCode = this.patientInfo?.codice_fiscale
      this.consents = await this.$store.dispatch('getPatientConsents', taxCode)

      // Se l'indiricativo inizia con '010 vuol dire che si tratta di un paziente piemontese
      if (this.consents?.identificativo_informativa_corrente?.indexOf('010') === 0) {
        try {
          this.isPatientPiedmont = true
          let params = {
            cit_id: taxCode,
          }
          let {data: list} = await getPatientList({params})
          this.patient = list[0]
        } catch (error) {
          let message = "Non Ã¨ stato possibile recuperare le informazioni del pazienti."
          apiErrorNotify({error, message})
        }
      } else {
        this.patient = {
          codice_fiscale: this.patientInfo?.codice_fiscale
        }
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

    },
    async goToConsentsActivation() {

      let patient = {...this.patient}
      patient.consensi = this.consents
      patient.extra_regione = !this.isPatientPiedmont
      await this.$store.dispatch('setActivePatient', {patient})
      await this.$store.dispatch('setDocumentTypeFilter', this.patientInfo.tipo_documento)
      this.$router.push(CONSENTS_ACTIVATION)
    },

    async onConfirmConsentDichiaration() {
      try {
        let payload = {
          cittadino_id: this.patientInfo.codice_fiscale,
          codice_audit: AUDIT_CODES.CONSENTS_CONFIRM
        }

        let response = await setAudit(payload)

        let patient = {...this.patient}
        patient.consensi = this.consents
        patient.extra_regione = !this.isPatientPiedmont
        this.$store.dispatch('setActivePatient', {patient})
        await this.$store.dispatch('setDocumentTypeFilter', this.patientInfo.tipo_documento)
        this.goToPatientInfo()
      } catch (error) {
        let message = "Non Ã¨ stato possibile confermare il consenso"
        apiErrorNotify({error, message})
      }

    },
    goToPatientInfo() {
      this.$router.push(PATIENT_INFO)
    },
  }

}

</script>

<style scoped>

</style>
