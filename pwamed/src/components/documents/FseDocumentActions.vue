<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div :class="[($q.screen.lt.md && listView) || (detail && $q.screen.gt.sm) ? 'items-end' : 'items-stretch']"
       class="column q-gutter-sm">
    <template v-if="isDocumentMediated">
      <div class="row items-center justify-center">

        <div class="text-green-7 text-bold ">
          Documento mediato
        </div>
      </div>
    </template>

    <template v-if="isPersonal">
      <q-btn v-if="contributionType === DOCUMENT_PERSONAL_CONTRIBUTION_TYPE_MAP.TRANSCRIPTION" :to="transcriptionUrl"
             color="primary"
             outline>
        Visualizza trascrizione
      </q-btn>

      <q-btn v-if="contributionType === DOCUMENT_PERSONAL_CONTRIBUTION_TYPE_MAP.ATTACHMENT"
             color="primary" @click.stop="goToReportDetail">
        Consulta referto
      </q-btn>

<!--      <q-btn v-if="canShowPersonalImage" color="primary">-->
<!--        Visualizza immagini-->
<!--      </q-btn>-->
    </template>
    <template v-else-if="isFse">
      <q-btn v-if="isPayed" color="primary" @click.stop="goToReportDetail">
        Consulta referto
      </q-btn>
      <template v-if="needMediation">
        <q-btn class="col" color="primary" outline @click.stop="showMediationDialog = true">
          Media documento
        </q-btn>
      </template>

      <template v-else-if="isPayed">
        <q-btn v-if="canBookingImage" :loading="isBookingImage" color="primary" outline @click.stop="onBookImage">
          Prenota immagine
        </q-btn>


        <q-btn-dropdown v-if="canShowImage" color="primary" label="Immagini" outline>
          <q-list>
            <q-item v-close-popup clickable @click.stop="showDownloadImageDialog = true">
              <q-item-section>
                <q-item-label>Scarica</q-item-label>
              </q-item-section>
            </q-item>

            <q-item v-close-popup :to="imageDetailUrl" clickable>
              <q-item-section>
                <q-item-label>Visualizza</q-item-label>
              </q-item-section>
            </q-item>

          </q-list>
        </q-btn-dropdown>

      </template>
    </template>


    <q-dialog v-model="showMediationDialog" :maximized="$q.screen.lt.md">
      <q-card style="max-width: 800px">
        <q-toolbar>
          <q-toolbar-title>
            Mediazione
          </q-toolbar-title>
          <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
        </q-toolbar>
        <q-card-section>
          <csi-banner type="warning">
            Non sarÃ  possibile rioscurare un documento al paziente una volta confermata lâavvenuta mediazione.
          </csi-banner>

          <csi-buttons class="q-mt-md">
            <csi-button :loading="isLoadingMediation" @click.stop="onConfirmMediation">Confermo mediazione</csi-button>
            <csi-button v-close-popup outline>Annulla</csi-button>
          </csi-buttons>
        </q-card-section>

      </q-card>

    </q-dialog>


    <q-dialog
      ref="downloadImageDialog"
      v-model="showDownloadImageDialog"
    >
      <q-card style="max-width: 800px">
        <q-toolbar>
          <q-toolbar-title>
            Scarica immagine
          </q-toolbar-title>

          <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
        </q-toolbar>

        <q-card-section class="q-gutter-y-md">
          <csi-banner class="q-mt-md" type="info">
            <strong>Attenzione!</strong><br>
            Il file contenente le immagini potrebbe avere dimensioni considerevoli e richiedere lunghi tempi di
            attesa.
            <!--            <br>-->
            <!--            Puoi consultare <a class="lms-link"-->
            <!--                               href="https://www.salutepiemonte.it/cms/sites/default/files/documentazione/tempi_attesa_ritiro_referti.pdf"-->
            <!--                               target="_blank">una stima dei tempi medi</a> calcolati in base alla tipologia d'immagine e alla connessione-->
            <!--            disponibile.-->
          </csi-banner>

          <csi-buttons>
            <csi-button :loading="isDownloadingImage" @click="onDownloadImage">Scarica immagine</csi-button>
            <csi-button v-close-popup outline>Annulla</csi-button>
          </csi-buttons>
        </q-card-section>


      </q-card>
    </q-dialog>

  </div>

</template>

<script>
import {REPORT_DETAIL, REPORT_IMAGES, TRANSCRIPTION} from "src/router/routes";
import {
  DOCUMENT_CATEGORY_MAP,
  DOCUMENT_PERSONAL_CONTRIBUTION_TYPE_MAP,
  DOCUMENT_TYPE_CODE_MAP, IMAGE_STATUS_CODE_LIST_BOOKABLE, IMAGE_STATUS_CODE_LIST_DOWNLOADABLE
} from "src/services/documents/config";
import {getUrlFromBase64, isDocumentPayed} from "src/services/documents/business-logic";
import {
  createImageBooking,
  downloadDocumentPersonal, getDocumentImageDownloadUrl, getDocumentImageStatus, setAudit,
  setDocumentMediation
} from "src/services/api";
import {apiErrorNotify, notifySuccess} from "src/services/utils";
import {saveAs} from 'file-saver'
import {openURL} from "quasar";
import {AUDIT_CODES} from "src/services/global/config";

export default {
  name: "FseDocumentActions",
  props: {
    document: {type: Object, default: null},
    listView: {type: Boolean, default: false},
    detail: {type: Boolean, default: false}
  },
  data() {
    return {
      isMediated: false,
      DOCUMENT_PERSONAL_CONTRIBUTION_TYPE_MAP,
      showMediationDialog: false,
      showDownloadImageDialog: false,
      isLoadingMediation: false,
      isDownloadingImage: false,
      isBookingImage: false,
      isDownloadingDocument: false,
      imageStatus: null
    }
  },
  created() {
    this.imageStatus = this.metaData?.stato_generazione_pacchetto
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    isNoPiedmontPatient() {
      return this.$store.getters['isPatientExtraRegion']
    },
    id() {
      return this.document?.id_documento_ilec;
    },
    cl() {
      return this.document?.codice_cl;
    },
    isPersonal() {
      return this.category === DOCUMENT_CATEGORY_MAP.PERSONAL;
    },
    isFse() {
      return this.category === DOCUMENT_CATEGORY_MAP.FSE;
    },
    category() {
      return this.document?.categoria ?? DOCUMENT_CATEGORY_MAP.FSE
    },
    contributionType() {
      return this.metaData?.tipo_contributo

    },
    transcriptionUrl() {
      let route = {
        name: TRANSCRIPTION.name,
        params: {
          id: this.id,
          document: this.document
        }

      }

      return route
    },
    isPayed() {
      let status = this.metaData?.pagato_ticket
      return isDocumentPayed(status)
    },
    metaData() {
      return this.document?.metadati_documento
    },
    mediationInfo(){
      return this.document?.info_mediazione
    },
    needMediation() {
      return  !this.isMediated && !!this.mediationInfo?.mediabile
    },
    accessionNumbers() {
      return this.metaData?.accession_number ?? []
    },
    canBookingImage() {
      return this.accessionNumbers?.length > 0 &&
        this.metaData?.codice_tipo_documento === DOCUMENT_TYPE_CODE_MAP.RADIOLOGY_REPORT_2 &&
        IMAGE_STATUS_CODE_LIST_BOOKABLE.includes(this.imageStatus);
    },
    canShowImage() {

      return this.accessionNumbers?.length > 0 &&
        this.metaData?.codice_tipo_documento === DOCUMENT_TYPE_CODE_MAP.RADIOLOGY_REPORT_2 &&
        IMAGE_STATUS_CODE_LIST_DOWNLOADABLE.includes(this.imageStatus);
    },
    canShowPersonalImage() {
      return this.contributionType === DOCUMENT_PERSONAL_CONTRIBUTION_TYPE_MAP.ATTACHMENT &&
        (this.accessionNumbers?.length > 0 || this.metaData?.tipo_file === 'jpeg')
    },
    isDocumentMediated() {
      return this.metaData?.data_smediazione || this.isMediated
    },
    imageDetailUrl() {
      return {
        name: REPORT_IMAGES.name,
        params: {
          id: this.id,
          document: this.document
        }

      }
    }
  },
  methods: {
    async onConfirmMediation() {
      this.isLoadingMediation = true
      let params = {
        codice_componente_locale: this.cl
      }

      try {
        let response = await setDocumentMediation(this.patientTaxCode, this.id, {params})
        this.isMediated = true
        this.$emit('on-mediated')
      } catch (error) {
        let message = "Non Ã¨ stato possibile mediare il documento. Riprovare piÃ¹ tardi"
        apiErrorNotify({error, message})
      }
      this.isLoadingMediation = false
      notifySuccess("Operazione effettuata con successo")
      this.showMediationDialog = false
    },
    async onBookImage() {
      this.isBookingImage = true

      let payload = {
        cit_id: this.patientTaxCode,
        id_referto: this.metaData?.codice_documento_dipartimentale,
        periodo_conservazione: '30',
        pin: '12345',
        accession_number: this.accessionNumbers,
        cod_cl: this.cl
      }

      let imageBookingPromise = createImageBooking(payload)
      let auditPayload = {
        cittadino_id: this.patientTaxCode,
        codice_audit: AUDIT_CODES.IMAGE_BOOKING
      }

      let auditPromise =  setAudit(auditPayload)

      try {
        let response = await imageBookingPromise
        await this.verifyImageStatus()
      } catch (error) {
        let message = "Non Ã¨ stato possibile prenotare l'immagine"
        apiErrorNotify({error, message})
      }

      try{
        let response = await auditPromise
      }catch (e) {
      }

      this.isBookingImage = false
    },
    async verifyImageStatus() {
      let params = {
        codice_fiscale: this.patientTaxCode,
        id_documento_ilec: this.id,
        cod_cl: this.cl,
        cod_documento_dipartimentale: this.metaData?.codice_documento_dipartimentale,
        archivio_documento_ilec: "FSE"
      };
      try {
        let {data} = await getDocumentImageStatus({params});
        this.imageStatus = data?.stato_richiesta;
      } catch (e) {
      }
    },
    goToReportDetail() {
      let id=this.id ?? 'extra-regione'
      let route = {
        name: REPORT_DETAIL.name,
        params: {
          id: id,
          document: this.document
        }
      }
      console.log(id)
      this.$router.push(route)
    },
    async downloadDocumentPersonal() {
      this.isDownloadingDocument = true

      try {
        let {data: document} = await downloadDocumentPersonal(this.patientTaxCode, this.id)
        let url = getUrlFromBase64(document)
        let documentName = `documento_${this.id}`
        saveAs(url, documentName)
      } catch (error) {
        let message = "non Ã¨ stato possibile scaricare il documento."
        apiErrorNotify({error, message})
      }
      this.isDownloadingDocument = false

    },
    async onDownloadImage() {
      this.isDownloadingImage = true;

      let auditPayload = {
        cittadino_id: this.patientTaxCode,
        codice_audit: AUDIT_CODES.IMAGE_DOWNLOAD
      }
      let auditPromise = setAudit(auditPayload)

      try {
        let payload = {
          cf_assistito: this.patientTaxCode,
          id_documento_ilec: this.id,
          cod_cl: this.cl
        }
        let url = await  getDocumentImageDownloadUrl(payload)

        openURL(url);
      } catch (error) {
        let message = "Non Ã¨ stato possibile scaricare l'immagine";
        apiErrorNotify({error, message});
      }

      try{
        let response = await auditPromise
      }catch (e) {

      }

      this.showDownloadImageDialog = false;
      this.isDownloadingImage = false;
    }
  }
}
</script>

<style scoped>

</style>
