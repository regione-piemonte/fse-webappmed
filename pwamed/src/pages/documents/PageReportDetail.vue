<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="page-report-images">

    <csi-banner class="q-mb-md" type="info">
      Qualora lâAzienda Sanitaria e/o lâUnitÃ  Operativa indicate nel referto differiscano rispetto a quelle indicate
      nellâelenco episodi, fa fede lâindicazione riportata nel referto.
    </csi-banner>

    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading" block/>
    </template>
    <template v-else-if="documentUrl">
      <csi-banner class="q-mb-md" type="warning">
        Se non vedi il tuo referto Ã¨ possibile che siano da impostare correttamente alcuni parametri del tuo browser.
        <a class="csi-link" target="_blank">Clicca qui</a> per la tua guida.
        <br>Ã comunque possibile scaricarlo sul tuo pc cliccando
        <a :href="documentUrl" class="csi-link" @click.prevent.stop="downloadReport">qui</a>.
      </csi-banner>

      <csi-banner v-if="hasImages" actions-inline class="q-mb-md" type="warning">
        Questo referto contiene <strong>immagini</strong> da visualizzare o scaricare
        <template #action>
          <q-btn :to="imagesUrl" outline>Vai alle immagini</q-btn>
        </template>
      </csi-banner>
      <div class="q-my-lg">
        <csi-pdf-viewer :file-name="'referto_'+ id" :iframe-classes="['full-width']"
                        :iframe-styles="{minHeight: '500px'}"
                        :src="documentUrl"/>

      </div>


    </template>
    <template v-else>
      <csi-banner type="negative">
        Non Ã¨ stato possibile recuperare il documento
      </csi-banner>
    </template>

  </div>

</template>

<script>
import {REPORT_IMAGES} from "src/router/routes";
import CsiPdfViewer from "components/core/CsiPdfViewer";

import {saveAs} from 'file-saver'
import {
  getDocumentFse,
  getDocumentPersonal,
  getUrlFromBase64
} from "src/services/documents/business-logic";
import {DOCUMENT_CATEGORY_MAP} from "src/services/documents/config";


export default {
  name: "PageReportDetail",
  components: {CsiPdfViewer},
  props:{
    document:{type:Object, default: null}
  },
  data() {
    return {
      showPdf: true,
      id: null,
      isLoading: false,
      documentInfo:null
    }
  },
  computed: {

    patient(){
      return this.$store.getters['getActivePatient']
    },
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    metadata() {
      return this.document?.metadati_documento
    },
    imagesUrl() {
      return {
        name: REPORT_IMAGES.name,
        params: {
          id: this.id,
          document: this.document
        }
      }
    },
    transcription(){
      return this.documentInfo?.trascrizione
    },
    documentUrl(){
      return getUrlFromBase64(this.documentInfo)
    },
    hasImages() {
      return this.metadata?.accession_number?.length > 0
    },
    isNoPiedmontPatient() {
      return this.$store.getters['isPatientExtraRegion']
    },
    cl() {
      return this.document?.codice_cl;
    },
    docIlec(){
      return this.document?.id_documento_ilec;
    },
    isPersonal() {
      return this.category === DOCUMENT_CATEGORY_MAP.PERSONAL;
    },
    category() {
      return this.document?.categoria ?? DOCUMENT_CATEGORY_MAP.FSE
    },
  },
  async created() {
    console.log('PAGEREPORT.vue')
    let {id} = this.$route.params

    this.isLoading = true

    this.id =  id


    if(this.isPersonal){
      this.documentInfo = await getDocumentPersonal(this.id)
    }else {
      let idRepository = this.metadata?.id_repository_cl ??  this.metadata?.identificativo_repository
      let payload = {
        firmato: 'S',
        codice_documento: this.metadata?.codice_documento_dipartimentale,
        identificativo_repository: idRepository
      }
      if(this.docIlec){
        payload.id_documento_ilec= this.docIlec

      }
      if(this.cl){
        payload.codice_componente_locale= this.cl
      }

      this.documentInfo = await getDocumentFse(payload)
    }


    this.isLoading = false
  },
  methods: {
    downloadReport() {
      let documentName = `referto_${this.id}`
      saveAs(this.documentUrl, documentName)
    }

  }
}
</script>

<style scoped>

</style>
