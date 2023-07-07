<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <!--  FILTRI  -->
    <!-- -----------------------------------------------------------------------------------------------------------  -->
    <fse-documents-filter-form
      :start-period-code="filterPeriodCode"
      category
      doctor-type
      document-type
      :category-list="categoryList"
      :document-type-list="documentTypeList"
      view-mode
      @set-filters="setFilters"
      @on-view-mode="setViewMode"
    />


    <template v-if="!isLoadingDocumentList && !hasDocuments">
      <csi-banner class="q-mt-xl" type="info">
        Nessuna ricetta trovata in base ai filtri di ricerca
      </csi-banner>
    </template>

    <template v-else>
      <!--  SCHEDE  -->
      <!-- -----------------------------------------------------------------------------------------------------------  -->
      <template v-if="viewSelected === VIEW_MAP.CARDS">
        <div class=" row  items-stretch  q-mt-xl q-col-gutter-md">
          <template v-if="isLoadingDocumentList">
            <div v-for="i in 10" :key="'list-skeleton--' + i" class="col-lg-4 col-md-6 col-12">
              <q-card class="fit">
                <fse-document-item-skeleton/>
              </q-card>
            </div>

          </template>

          <template v-else>
            <div v-for="document in documentList"
                 :key="'list--' + document.id_documento_ilec"
                 class="col-xl-4  col-md-6  col-12"
            >

              <fse-document-item :document="document"/>
            </div>




            <template v-if="hasMoreDocuments" class="row items-center  q-pt-md" style="">
              <div class="q-mt-md text-center col-12">
                <q-btn
                  :loading="isLoadingDocumentListMore"
                  dense
                  flat
                  @click="loadDocumentListMore"
                >
                  Carica altre ricette
                </q-btn>
              </div>
            </template>
          </template>
        </div>
      </template>

      <!--  LISTA  -->
      <!-- -----------------------------------------------------------------------------------------------------------  -->
      <template v-if="viewSelected === VIEW_MAP.LIST">
        <div class=" row  q-mt-xl q-col-gutter-md">
          <template v-if="isLoadingDocumentList">
            <div v-for="i in 10" :key="'list-skeleton--' + i" class="col-12">
              <q-card class="fit">
                <fse-document-item-skeleton/>
              </q-card>
            </div>

          </template>

          <template v-else>
            <div v-for="document in documentList"
                 :key="'list--' + document.id_documento_ilec"
                 class="col-12"
            >

              <fse-document-item :document="document" list-view/>

            </div>


            <template v-if="hasMoreDocuments">
              <div class="q-mt-md text-center col-12">
                <q-btn
                  :loading="isLoadingDocumentListMore"
                  dense
                  flat
                  @click="loadDocumentListMore"
                >
                  Carica altre ricette
                </q-btn>
              </div>
            </template>
          </template>
        </div>
      </template>
    </template>



  </csi-page>
</template>

<script>
import {
  DOCUMENT_CATEGORY_LIST, DOCUMENT_CATEGORY_MAP,
  DOCUMENT_DOCTOR_TYPE_LIST, DOCUMENT_TYPE_EXEMPTION_LIST, DOCUMENT_TYPE_PRESCRIPTION_LIST,
  VIEW_MAP,
  VIEW_MAP_OPTIONS
} from "src/services/documents/config";
import {PERIOD_LIST_FILTER} from "src/services/global/config";
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";
import FseDocumentItem from "components/documents/FseDocumentItem";


import FseDocumentsFilterForm from "components/documents/FseDocumentsFilterForm";
import {getDocumentFseTypeList} from "src/services/documents/business-logic";


export default {
  name: "PagePrescriptions",
  components: {FseDocumentsFilterForm, FseDocumentItem, FseDocumentItemSkeleton},
  data(){
    return{
      VIEW_MAP_OPTIONS,
      VIEW_MAP,
      DOCUMENT_DOCTOR_TYPE_LIST,
      DOCUMENT_CATEGORY_LIST,
      PERIOD_LIST_FILTER,
      viewSelected: VIEW_MAP.CARDS,
      filterPeriodCode: null,
      offset:0,
      documentList:[],
      isLoadingDocumentList:false,
      isLoadingDocumentListMore: false,
      filterParams:null,
      documentListCount: Number.MAX_SAFE_INTEGER
    }
  },
  computed:{
    patientTaxCode() {
      let patient =  this.$store.getters['getActivePatient']
      return patient?.codice_fiscale
    },
    hasDocuments(){
      return this.documentList?.length>0
    },
    categoryList() {
      let categories =  this.$store.getters['getDocumentCategoriesList']
      return categories?.filter(c => c.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE)
    },
    documentTypeList() {
      return getDocumentFseTypeList(DOCUMENT_TYPE_PRESCRIPTION_LIST)
    },
    hasMoreDocuments(){
      return this.documentList?.length < this.documentListCount
    }
  },
  created() {
    this.filterPeriodCode = PERIOD_LIST_FILTER[0].codice;
  },
  methods:{
    async setFilters(params) {
      this.offset = 0
      this.documentList = []
      this.filterParams = params
      this.documentListCount= Number.MAX_SAFE_INTEGER
      this.isLoadingDocumentList = true
      await this.loadDocumentList()
      this.isLoadingDocumentList = false
    },
    setViewMode(mode) {
      this.viewSelected = mode
    },
    async loadDocumentList() {
      if(!this.hasMoreDocuments) return
      let payload = {...this.filterParams}
      payload.offset = this.offset

      let documents = await this.$store.dispatch('getDocumentList', payload)

      let documentList = documents?.elenco_documenti ?? []
      this.documentListCount= documents?.numero_documenti ?? 0

      this.documentList = [...this.documentList, ...documentList]

    },
    async loadDocumentListMore() {
      this.isLoadingDocumentListMore = true
      this.offset = this.documentList?.length
      await this.loadDocumentList()
      this.isLoadingDocumentListMore = false
    },
  }
}
</script>

<style scoped>

</style>
