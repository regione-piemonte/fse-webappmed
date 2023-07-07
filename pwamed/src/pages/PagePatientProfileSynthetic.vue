<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <template v-if="isLoading">
      <div class="row items-stretch q-col-gutter-lg">
        <div class="col-12 col-md-3">
          <q-card class="fit">
            <div class="bg-grey-3 q-mb-sm q-pa-md">
              <q-skeleton type="text" class="bg-grey-5" style="max-width: 200px"/>
            </div>
            <q-card-section class="q-pt-none" >
              <q-list class="q-mt-md">
                <!-- DATA VALIDAZIONE -->
                <q-item class="no-padding q-mb-md">
                  <q-item-section>
                    <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                    <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                  </q-item-section>
                </q-item>

                <!-- MEDICO -->
                <q-item class="no-padding">
                  <q-item-section>
                    <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                    <q-item-label class="text-bold"><q-skeleton type="text" width="100px" /></q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>

            </q-card-section>
          </q-card>
        </div>


        <div class="col-12 col-md">

          <q-card flat bordered class="fit bg-grey-5" style="min-height: 500px">
            <q-skeleton  height="100%" />
          </q-card>

        </div>
      </div>
    </template>

    <template v-else-if="document">
      <div class="row items-stretch q-col-gutter-lg">
        <div class="col-12 col-md-3">
          <q-card class="fit">
            <div class="bg-grey-3 q-mb-sm q-pa-md">
              <div class="text-h5 ">Referto n. {{id}}</div>
            </div>
            <q-card-section class="q-pt-none" >
              <q-list class="q-mt-md">
                <!-- DATA VALIDAZIONE -->
                <q-item class="no-padding q-mb-md">
                  <q-item-section>
                    <q-item-label caption class="text-black">Validato il</q-item-label>
                    <q-item-label class="text-bold">{{ validationDate | date | empty }}</q-item-label>
                  </q-item-section>
                </q-item>


                <!-- MEDICO -->
                <q-item class="no-padding">
                  <q-item-section>
                    <q-item-label caption class="text-black">Medico</q-item-label>
                    <q-item-label class="text-bold">
                      {{ doctorVisit | empty }}
                    </q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>

            </q-card-section>

          </q-card>
        </div>


        <div class="col-12 col-md">
          <csi-pdf-viewer :src="documentPdf" :iframe-classes="['full-width']" :iframe-styles="{minHeight: '500px'}"/>
        </div>
      </div>


    </template>

    <div class="q-my-md" v-else>
      <csi-banner type="info">
        <template v-if="withoutPSS">
          Il paziente non ha nessun profilo sanitario sintetico
        </template>
        <template v-else>
          Nessun documento disponibile
        </template>
      </csi-banner>
    </div>

  </csi-page>
</template>

<script>
import {
  DOCUMENT_CATEGORY_LIST, DOCUMENT_CATEGORY_MAP,
  DOCUMENT_DOCTOR_TYPE_LIST, DOCUMENT_TYPE_PSS_LIST,
  VIEW_MAP,
  VIEW_MAP_OPTIONS
} from "src/services/documents/config";
import {PERIOD_LIST_FILTER} from "src/services/global/config";
import {getDocumentLast, getDocumentsTypeList} from "src/services/api";
import {apiErrorNotify, convertToBlobUrl, isEmpty} from "src/services/utils";
import {date} from "quasar";
import FseDocumentsFilterForm from "components/documents/FseDocumentsFilterForm";
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";
import FseDocumentItem from "components/documents/FseDocumentItem";
import {getDocumentFseTypeList, getUrlFromBase64} from "src/services/documents/business-logic";
import FseDocumentItemTypeIcon from "components/documents/FseDocumentItemTypeIcon";
import CsiPdfViewer from "components/core/CsiPdfViewer";
import {HOME} from "src/router/routes";

export default {
  name: "PagePatientProfileSynthetic",
  components: {CsiPdfViewer},
  data() {
    return {
      document: null,
      isLoading: false,
      isHiddenDocument:false,
      withoutPSS:false
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    id(){
      return this.document?.id_documento;
    },
    typeCode() {
      return this.document?.tipo_documento;
    },
    typeName() {
      let documentType = this.documentTypeList?.find(type => type.codice === this.typeCode)
      return documentType?.descrizione ?? ''
    },
    validationDate(){
      return this.document?.data_validazione
    },
    aslName(){
    return this.document?.luogo_produzione_documento?.azienda?.descrizione
    },
    structureName(){
      return this.document?.luogo_produzione_documento?.struttura?.descrizione
    },
    doctorVisit(){
      return this.document?.medico_visita
    },
    doctorValidate(){
      return this.document?.medico_validante
    },
    documentPdf(){
     let document  =  {
       allegato : this.document?.documento,
       tipo_allegato: 'application/pdf'
     }

      return  getUrlFromBase64(document)
    },
    documentTypeList(){
      return this.$store.getters['getDocumentTypeList']
    }
  },
  async created() {
    if (!this.patientTaxCode) {
      this.$router.replace(HOME)
      return
    }
    this.isHiddenDocument = false
    this.withoutPSS =false
    this.isLoading = true
    let params={
      tipo_documento: "60591-5"
    }
    let documentPromise =  getDocumentLast(this.patientTaxCode, {params})
    let documentTypePromise = null
    if(isEmpty(this.documentTypeList)){
      documentTypePromise =  getDocumentsTypeList()
    }


    try {
      let {data: document} = await documentPromise
      this.document = document
    } catch (error) {
      let errorDetail = error.response.data?.detail ?? []
      if (error.response.status === 500 ) {
        if(errorDetail[0]?.chiave === 'CC_ER_171')
          this.isHiddenDocument = true
        else if(errorDetail[0]?.chiave === 'CC_ER_155')
          this.withoutPSS = true
      }else{
        let message = "Non Ã¨ stato possibile recuperare il documento"
        apiErrorNotify({error, message})
      }

    }

    if(documentTypePromise){
      try{
        let {data:list} = await documentTypePromise
        this.$store.dispatch('setDocumentTypeList', {list})
      }catch (error) {
        console.log(error)
      }
    }


   this.isLoading = false
  },
  methods: {
    openPdf(){
    //  let url = convertToBlobUrl(this.documentPdf)
        window.open(this.documentPdf)
    }
  }
}
</script>

<style scoped>

</style>
