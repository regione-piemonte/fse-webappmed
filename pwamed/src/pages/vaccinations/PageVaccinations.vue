<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <!--  FILTRI  -->
    <!-- -----------------------------------------------------------------------------------------------------------  -->
    <fse-documents-filter-form
      :category-list="categoryList"
      :document-type-list="documentTypeList"
      :start-period-code="filterPeriodCode"
      doctor-type
      document-type
      view-mode
      @set-filters="setFilters"
      @on-view-mode="setViewMode"
    />


    <template v-if="!isLoadingVaccinationList && !hasVaccinations">
      <csi-banner class="q-mt-xl" type="info">
        Nessuna vaccinazione trovata in base ai filtri di ricerca
      </csi-banner>
    </template>


    <template v-else>
      <!--  SCHEDE  -->
      <!-- -----------------------------------------------------------------------------------------------------------  -->
      <template v-if="viewSelected === VIEW_MAP.CARDS">
        <div class=" row  items-stretch  q-mt-xl q-col-gutter-md">
          <template v-if="isLoadingVaccinationList">
            <div v-for="i in 10" :key="'list-skeleton--' + i" class="col-lg-4 col-md-6 col-12">
              <q-card class="fit">
                <fse-document-item-skeleton/>
              </q-card>
            </div>

          </template>

          <template v-else>
            <div v-for="document in vaccinationList"
                 :key="'list--' + document.id_documento_ilec"
                 class="col-xl-4  col-md-6  col-12"
            >

              <fse-document-item :document="document"/>
            </div>

            <div v-if="hasMoreVaccinations" class="row items-center  q-pt-md" style="">
              <q-btn
                :loading="isLoadingMoreVaccinationList"
                dense
                flat
                @click="loadVaccinationListMore"
              >
                Carica altre vaccinazioni
              </q-btn>
            </div>

          </template>
        </div>
      </template>

      <!--  LISTA  -->
      <!-- -----------------------------------------------------------------------------------------------------------  -->
      <template v-if="viewSelected === VIEW_MAP.LIST">
        <div class=" row  q-mt-xl q-col-gutter-md">
          <template v-if="isLoadingVaccinationList">
            <div v-for="i in 10" :key="'list-skeleton--' + i" class="col-12">
              <q-card class="fit">
                <fse-document-item-skeleton/>
              </q-card>
            </div>

          </template>

          <template v-else>
            <div v-for="document in vaccinationList"
                 :key="'list--' + document.id_documento_ilec"
                 class="col-12"
            >

              <fse-document-item :document="document" list-view/>

            </div>


            <template v-if="hasMoreVaccinations">
              <div class="q-mt-md text-center col-12">
                <q-btn
                  :loading="isLoadingMoreVaccinationList"
                  dense
                  flat
                  @click="loadVaccinationListMore"
                >
                  Carica altre vaccinazioni
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
import FseDocumentsFilterForm from "components/documents/FseDocumentsFilterForm";
import {getDocumentFseTypeList} from "src/services/documents/business-logic";
import {
  DOCUMENT_CATEGORY_MAP,
  DOCUMENT_TYPE_VACCINATIONS_LIST,
  VIEW_MAP,
  VIEW_MAP_OPTIONS
} from "src/services/documents/config";
import {PERIOD_LIST_FILTER} from "src/services/global/config";
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";
import FseDocumentItem from "components/documents/FseDocumentItem";

export default {
  name: "PageVaccinations",
  components: {FseDocumentItem, FseDocumentItemSkeleton, FseDocumentsFilterForm},
  data() {
    return {
      VIEW_MAP_OPTIONS,
      VIEW_MAP,
      isLoadingVaccinationList: false,
      isLoadingMoreVaccinationList: false,
      vaccinationList: [],
      offset: 0,
      filterPeriodCode: null,
      endDate: null,
      startDate: null,
      filterParams: null,
      isFirstLoading: true,
      vaccinationListCount: Number.MAX_SAFE_INTEGER,
      viewSelected: VIEW_MAP.CARDS,
    }
  },
  computed:{
    patientTaxCode() {
      let patient =  this.$store.getters['getActivePatient']
      return patient?.codice_fiscale
    },
    categoryList() {
      let categories =  this.$store.getters['getDocumentCategoriesList']
      return categories?.filter(c => c.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE)
    },
    documentTypeList() {
      return getDocumentFseTypeList(DOCUMENT_TYPE_VACCINATIONS_LIST)
    },
    hasVaccinations(){
      return this.vaccinationList?.length>0
    },
    hasMoreVaccinations(){
      return this.vaccinationList?.length < this.vaccinationListCount
    }
  },
  created() {
    let period = PERIOD_LIST_FILTER.find(p => p.codice === '005')
    this.filterPeriodCode = period.codice;



  },
  methods:{
    async loadVaccinationList(){
      if(!this.hasMoreVaccinations) return
      let payload = {...this.filterParams}
      payload.offset = this.offset

      let documents = await this.$store.dispatch('getDocumentList', payload)

      let documentList = documents?.elenco_documenti ?? []
      this.vaccinationListCount= documents?.numero_documenti ?? 0
      this.vaccinationList = [...this.vaccinationList, ...documentList]

    },
    setViewMode(mode) {
      this.viewSelected = mode
    },
    async setFilters(params) {
      this.offset = 0
      this.vaccinationList = []
      this.filterParams = params
      this.vaccinationListCount= Number.MAX_SAFE_INTEGER
      this.isLoadingVaccinationList= true
      await this.loadVaccinationList()
      this.isLoadingVaccinationList = false
    },
    async loadVaccinationListMore(){
      this.isLoadingMoreVaccinationList = true
      this.offset = this.vaccinationList?.length
      await this.loadVaccinationList()
      this.isLoadingMoreVaccinationList = false
    }
  }
}
</script>

<style scoped>

</style>
