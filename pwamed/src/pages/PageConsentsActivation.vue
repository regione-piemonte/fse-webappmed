<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <csi-page-title class="q-mb-lg">
      <template v-if="hasFse">
        Modifica consenso alla consultazione del Fascicolo Sanitario
      </template>
      <template v-else>Attivazione del Fascicolo Sanitario</template>
    </csi-page-title>

    <template v-if="isLoadingInformative">
      <csi-inner-loading :showing="isLoadingInformative" block/>
    </template>

    <template v-else-if="!isConsentComplete">
      <q-stepper
        ref="stepper"
        v-model="currentStep"
        :contracted="$q.screen.lt.md"
        class="bg-transparent"
        flat
        header-nav
      >
        <!-- Step 1 -->
        <!-- --------------------------------------------------------------------------------------------------------- -->
        <q-step
          :done="currentStep > STEPS.NOTICE"
          :name="STEPS.NOTICE"
          prefix="1"
          title="Informativa"
        >
          <div class="text-h1 q-mb-md">Informativa</div>


          <div class="q-my-mb">

            <csi-pdf-viewer :src="informative" file-name="informativa"  />
          </div>


          <div class="row q-col-gutter-md items-center justify-between">
            <div class="col-12 col-md">
              <q-toggle v-model="isNoticeShown">All'assistito Ã¨ stata mostrata l'informativa</q-toggle>
            </div>

            <div class="col-12 col-md-auto">
              <csi-buttons between>
                <csi-button :disabled="!isNoticeShown" @click="onStepNoticeNext">Conferma</csi-button>
                <csi-button :to="HOME" outline>Annulla</csi-button>
              </csi-buttons>


            </div>

          </div>

        </q-step>

        <!-- Step 2 -->
        <!-- --------------------------------------------------------------------------------------------------------- -->
        <q-step
          :done="currentStep > STEPS.CONSENT"
          :name="STEPS.CONSENT"
          prefix="2"
          title="Consenso"
        >
        
        <div class="text-h1 q-mb-md">
          <span  class="fse-consent-consultation-click" @click="onConsentsConsultationClick()">
          Consenso alla consultazione <q-icon name="info"></q-icon>     
        </span>

        </div>
          <q-toggle v-model="isConsentAcepted" disable>L'assistito acconsente alla consultazione del Fascicolo da parte degli
            operatori
            sanitari e sociosanitari
          </q-toggle>


          <csi-banner type="info" class="q-my-md">
            Se il soggetto Ã¨ minorenne, occorre indicare il codice fiscale del genitore o tutore
          </csi-banner>


          <div class="q-gutter-sm">
            <q-radio v-model="parentType" :val="AUDIT_CODES.CONSENTS_TAXCODE_PARENT" label="Genitore" />
            <q-radio v-model="parentType" :val="AUDIT_CODES.CONSENTS_TAXCODE_TUTOR" label="Tutore" />
          </div>

          <q-input v-model="parentTaxCode" class="q-mt-md" :disable="!parentType"
                   label="Codice fiscale"/>

          <csi-buttons between class=" q-mt-lg">
            <csi-button @click="onStepConsentNext">Conferma</csi-button>
            <csi-button outline @click="onStepPrevious">Indietro</csi-button>
            <csi-button :to="HOME" outline>Annulla</csi-button>
          </csi-buttons>

         
   
        </q-step>

        <!-- Step 3 -->
        <!-- --------------------------------------------------------------------------------------------------------- -->
        <q-step
          :done="currentStep > STEPS.SUMMARY"
          :name="STEPS.SUMMARY"
          prefix="3"
          title="Riepilogo"
        >
          <div class="text-h1 q-mb-md">Riepilogo</div>


          <div>
            <ul>
              <li>L'assistito ha preso visione dell'informativa sul trattamento dei dati personali</li>
              <li>L'assistito ha scelto di dare il consenso alla consultazione del Fascicolo da parte degli operatori sanitari e
                sociosanitari
              </li>

            </ul>

          </div>


          <csi-buttons between class=" q-mt-lg">
            <csi-button :loading="isSendingConsent" @click="onStepSummaryNext">Conferma</csi-button>
            <csi-button outline @click="onStepPrevious">Indietro</csi-button>
            <csi-button :to="HOME" outline>Annulla</csi-button>
          </csi-buttons>
        </q-step>

      </q-stepper>
    </template>

    <template v-else>
      <csi-banner type="positive">
        L'assistito ha dato il consenso alla consultazione del Fascicolo Sanitario da parte degli operatori sanitari e
        sociosanitari
      </csi-banner>
      <p>

      </p>
      <csi-banner type="warning">
        <p>
          <b>Importante:</b>
          Prima  di accedere al Fascicolo Sanitario Elettronico occorre stampare e far firmare all'assistito 
          il modulo che riepiloga le operazioni le operazioni effettuate e conservarlo.
        </p>
      </csi-banner>
      

      <csi-buttons class="q-mt-lg">
        <csi-button :to="PATIENT_INFO">Vai al fascicolo</csi-button>
        <csi-button outline @click="printConsents" :loading="isPrinting">Stampa</csi-button>
      </csi-buttons>
    </template>

    <fse-consents-print :is-new-fse="!hasFse" />
    <fse-consents-consultation-dialog v-model="showConsentsConsultationDialog" />
  </csi-page>

</template>

<script>
import {HOME, PATIENT_INFO} from "src/router/routes";
import {getConsents, getInformative, setAudit, setConsents} from "src/services/api";
import {apiErrorNotify, convertToBlobUrl, isEmpty} from "src/services/utils";
import CsiPdfViewer from "components/core/CsiPdfViewer";
import FseConsentsPrint from "components/FseConsentsPrint";
import {AUDIT_CODES, IS_PROD} from "src/services/global/config";
import FseConsentsConsultationDialog from "src/components/FseConsentsConsultationDialog";

const STEPS = {
  NOTICE: 1,
  CONSENT: 2,
  SUMMARY: 3
};
export default {
  name: "PageConsentsActivation",
  components: { FseConsentsPrint, CsiPdfViewer, FseConsentsConsultationDialog},
  data() {
    return {
      STEPS,
      PATIENT_INFO,
      HOME,
      AUDIT_CODES,
      isLoadingInformative: false,
      isSendingConsent: false,
      currentStep: STEPS.NOTICE,
      isNoticeShown: false,
      isConsentAcepted: true,
      parentTaxCode: '',
      parentType:null,
      isConsentComplete: false,
      informative: null,
      isPrinting:false,
      showConsentsConsultationDialog: false

    }
  },
  computed: {
    patient() {
      return this.$store.getters['getActivePatient']
    },
    patientTaxCode() {
      return this.patient?.codice_fiscale
    },
    hasFse() {
      return this.patient?.consensi?.consenso_alimentazione
    },

    consents() {
      return this.patient?.consensi
    },

  },
  async created() {

    if (!this.patient) {
      this.$router.replace(HOME)
      return
    }

    if (this.isConsentComplete) return

    this.isLoadingInformative = true
    let regionCode =  this.consents?.identificativo_informativa_corrente?.substring(0, 3) ?? '010';
      let idInformative = `${regionCode}^last`
    let params = {
      identificativo_struttura: '010',
      identificativo_organizzazione: '010',
      id_informativa: idInformative

    }

    try {
      console.log('consents', this.consents)
      let response = await getInformative(this.patientTaxCode, {params})
      this.informative = convertToBlobUrl(response.data)
    } catch (error) {
      let message = "Non Ã¨ stato possibile recuperare l'informativa"
      apiErrorNotify({error, message})
    }
    this.isLoadingInformative = false
  },
  methods: {

    onStepNoticeNext() {
      if (!this.isNoticeShown) {
        return
      }

      this.currentStep = STEPS.CONSENT
    },
    onStepConsentNext() {
      if (!this.isConsentAcepted) {
        return
      }

      this.currentStep = STEPS.SUMMARY
    },
    async onStepSummaryNext() {
      this.isSendingConsent = true
      console.log('consents', this.consents)
      let parentTaxCode = !isEmpty(this.parentTaxCode) ? this.parentTaxCode :  this.consents?.identificativo_assistito_consenso
      let payload = {
        presa_in_carico: true,
        struttura_utente: '------',
        tipo_attivita: "CREATE",
        contesto_operativo: "CONSENT",
        identificativo_assistito_consenso: this.consents?.identificativo_assistito_consenso,
        identificativo_assistito_genitore_tutore: parentTaxCode,
        identificativo_genitore_consenso: parentTaxCode,
        identificativo_organizzazione: '010',
        identificativo_informativa: this.consents?.identificativo_informativa_corrente,
        consenso_alimentazione: true,
        consenso_consultazione: this.isConsentAcepted,
        consenso_pregresso: false
      }
      try {
        let response = await setConsents(this.patientTaxCode, payload)
        await this.setParentAudit()
        let {data: consents} = await getConsents(this.patientTaxCode)
        this.$store.dispatch('setPatientConsents', {consents})
        this.isConsentComplete = true

      } catch (error) {
        let message = "Non Ã¨ stato possibile modificare i consensi"
        apiErrorNotify({error, message})
      }

      this.isSendingConsent = false

    },
    async setParentAudit(){
      //Audit per indicare se il cf Ã¨ di tutore o genitore
      if(this.parentType && this.parentTaxCode){
        let auditPayload = {
          cittadino_id: this.patientTaxCode,
          codice_audit: this.parentType
        }

        let auditPromise =  await setAudit(auditPayload)
      }


    },
    onStepPrevious() {
      this.$refs.stepper.previous();
    },

   async printConsents(){
      this.isPrinting= true

      // log di audit di visualizzazione dati di stampa di modifica dei consensi
      try{
        let payload = {
          cittadino_id: this.patientTaxCode,
          codice_audit: AUDIT_CODES.CONSENTS_PRINT
        }
        let response = await setAudit(payload)
      }catch (e) {

      }


     this.isPrinting= false
      const id= "fse-consents-print"
      const prtHtml = document.getElementById(id).innerHTML;
      // Get all stylesheets HTML
      let stylesHtml = "";
      for (const node of [
        ...document.querySelectorAll('link[rel="stylesheet"], style')
      ]) {
        stylesHtml += node.outerHTML;
      }
      // Open the print window
      const WinPrint = window.open(
        "",
        "_blank"
      );
      WinPrint.document.write(`<!DOCTYPE html>
                  <html>
                    <head>
                      ${stylesHtml}
                    </head>
                    <body class="print-page">
                      ${prtHtml}
                    </body>
                  </html>`);

      // WinPrint.document.close();
      setTimeout(function() {
        WinPrint.focus();
        WinPrint.print();
      }, 500);

      //WinPrint.close();
    },
    onConsentsConsultationClick() {
      this.showConsentsConsultationDialog = true;
    }
  }
}
</script>

<style scoped>
.fse-consent-consultation-click{
  cursor: pointer;
}
</style>
