<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page  class="page-report-compare">
    <csi-page-title class="q-mb-lg">Confronta referti</csi-page-title>

    <csi-banner class="q-mb-lg" type="info">
      Qualora lâAzienda Sanitaria e/o lâUnitÃ  Operativa indicate nel referto differiscano rispetto a quelle indicate
      nellâelenco episodi, fa fede lâindicazione riportata nel referto.
    </csi-banner>

    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading" block />
    </template>

    <div class="row items-stretch q-col-gutter-md" v-else>
      <div
        v-for="(document,index) in documentList"
        :key="`document-${index}`"
        class="col-12 "
        :class="documentListLength>3 ? 'col-md-4' : 'col-md'"
      >
        <div class="text-h6 q-mb-md" >
          Referto n. {{document.id_documento_ilec ?? index}}</div>
        <template v-if="document.trascrizione">

        <div class="full-height">
          <q-card :style="cardStyle" bordered flat>

            <csi-card-item-bar type="warning">
                 Trascrizione inserita dall'utente
            </csi-card-item-bar>
            <q-card-section>
              {{document.trascrizione | empty}}
            </q-card-section>
          </q-card>
        </div>

        </template>

        <template v-else>
          <div class="text-bold text-caption q-mb-sm">

          </div>

          <csi-pdf-viewer   :src="document.url_documento" :iframe-classes="['full-width']" :iframe-styles="{minHeight: '600px'}" :file-name="'referto_'+ document.id_documento_ilec" />
        </template>


    </div>
    </div>


  </csi-page>
</template>

<script>
import CsiPdfViewer from "components/core/CsiPdfViewer";
import {DOCUMENTS} from "src/router/routes";
import {
  getDocumentExtraRegion,
  getDocumentFse,
  getDocumentPersonal,
  getUrlFromBase64
} from "src/services/documents/business-logic";
import {DOCUMENT_CATEGORY_MAP} from "src/services/documents/config";
import CsiCardItemBar from "components/core/CsiCardItemBar";

export default {
name: "PageReportCompare",
  components: {CsiCardItemBar, CsiPdfViewer},
  data() {
    return {
      showPdf: true,
      documentList: null,
      isLoading:false
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    isNoPiedmontPatient() {
      return this.$store.getters['isPatientExtraRegion']
    },
    documentListLength(){
      return this.documentList?.length ?? 0
    },
    cardStyle(){
      if(this.$q.screen.gt.sm){
        return 'min-height : 600px'
      } else {
        return ''
      }
    }
  },
  async created() {
    console.log('PAGEREPORTCOMPARE.vue')
    let {documentList} = this.$route.params

    if (!documentList) {
      this.$router.replace(DOCUMENTS)
    }
    this.isLoading = true

    this.documentList = [...documentList]
    for(let i=0; i<this.documentListLength; i++){
      let document = this.documentList[i]
      let isPersonal = document?.categoria === DOCUMENT_CATEGORY_MAP.PERSONAL;
      let documentInfo = null
      if(isPersonal){
       documentInfo = await getDocumentPersonal(document.id_documento_ilec)
      }else {
        let metadata =  document?.metadati_documento
        let idRepository = metadata?.id_repository_cl ??   metadata?.identificativo_repository
        let payload = {
          id_documento_ilec: document.id_documento_ilec ?? null,
          codice_componente_locale: document.codice_cl ?? null,
          firmato: 'S',
          codice_documento: metadata?.codice_documento_dipartimentale,
          identificativo_repository: idRepository
        }

        documentInfo = await getDocumentFse(payload)
      }
      let documentUrl = null
      if(documentInfo){
        if(documentInfo.trascrizione){
          this.documentList[i].trascrizione = documentInfo.trascrizione
        }else{
          documentUrl = getUrlFromBase64(documentInfo)
        }
      }
      if(documentUrl) {
        this.documentList[i].url_documento = documentUrl
      }


    }

    this.isLoading = false


  }
}
</script>

<style scoped>

</style>
