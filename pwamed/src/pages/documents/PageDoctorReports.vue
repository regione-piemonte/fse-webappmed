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
      :doctor-reports="!patientHasConsents"
      :document-type-list="documentTypeList"
      :start-period-code="filterPeriodCode"
      doctor-type
      document-type
      view-mode
      @set-filters="setFilters"
      @on-view-mode="setViewMode"
    />

    <!--  BANNER  -->
    <!-- -----------------------------------------------------------------------------------------------------------  -->
    <csi-banner v-if="hasDocuments" actions-inline type="info">
      Ã possibile selezionare piÃ¹ referti per confrontarli in un'unica pagina
      <template v-slot:action>
        <csi-buttons>
          <csi-button outline @click="compareDocuments">Confronta referti</csi-button>
        </csi-buttons>
      </template>
    </csi-banner>

    <template v-if="!isLoadingDocumentList && !hasDocuments">
      <csi-banner class="q-mt-xl" type="info">
        Nessun documento trovato in base ai filtri di ricerca
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

              <fse-document-item
                :document="document"
                selectable
                @on-select="onSelectDocument"
              />


            </div>


            <template v-if="hasMoreDocuments">
              <div class="q-mt-md text-center col-12">
                <q-btn
                  :loading="isLoadingDocumentListMore"
                  dense
                  flat
                  @click="loadDocumentListMore"
                >
                  Carica altri documenti
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

              <fse-document-item
                :document="document"
                list-view
                selectable
                @on-select="onSelectDocument"
              />


            </div>


            <template v-if="hasMoreDocuments">
              <div class="q-mt-md text-center col-12">
                <q-btn
                  :loading="isLoadingDocumentListMore"
                  dense
                  flat
                  @click="loadDocumentListMore"
                >
                  Carica altri documenti
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
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";
import FseDocumentItem from "components/documents/FseDocumentItem";
import {
  DOCUMENT_CATEGORY_LIST,
  DOCUMENT_CATEGORY_MAP,
  DOCUMENT_DOCTOR_TYPE_LIST,
  DOCUMENT_DOCTOR_TYPE_MAP,
  DOCUMENT_TYPE_EXEMPTION_LIST,
  DOCUMENT_TYPE_PRESCRIPTION_LIST,
  DOCUMENT_TYPE_PSS_LIST,
  VIEW_MAP,
  VIEW_MAP_OPTIONS
} from "src/services/documents/config";
import {PERIOD_LIST_FILTER} from "src/services/global/config";
import {apiErrorNotify, deepClone} from "src/services/utils";
import {getDoctorReports} from "src/services/api";
import {REPORT_COMPARE} from "src/router/routes";

export default {
  name: "PageDoctorReports",
  components: {FseDocumentItem, FseDocumentItemSkeleton, FseDocumentsFilterForm},
  data() {
    return {
      VIEW_MAP_OPTIONS,
      VIEW_MAP,
      DOCUMENT_DOCTOR_TYPE_LIST,
      DOCUMENT_CATEGORY_LIST,
      PERIOD_LIST_FILTER,
      viewSelected: VIEW_MAP.CARDS,
      // isOpenFilters: false,
      aslList: [],
      periodList: [],
      sourceList: [],
      documentAuthorizationList: [],
      documentList: [],
      isLoadingDocumentList: false,
      isLoadingDocumentListMore: false,
      offset: 0,
      selectedDocumentsList: [],
      filterPeriodCode: null,
      filterParams: {},
      documentListCount: Number.MAX_SAFE_INTEGER
    }
  },
  computed: {
    patient() {
      return this.$store.getters['getActivePatient']
    },
    patientTaxCode() {
      return this.patient?.codice_fiscale
    },
    patientHasConsents() {
      return this.patientConsents?.consenso_consultazione && this.patientConsents?.consenso_alimentazione
    },
    patientConsents() {
      return this.patient?.consensi
    },
    hasDocuments() {
      return this.documentList?.length > 0
    },
    hasMoreDocuments() {
      return this.documentList?.length < this.documentListCount
    },
    categoryList() {
      let categories =  this.$store.getters['getDocumentCategoriesList']
      return categories?.filter(c => c.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE)
    },
    documentTypeList() {
      let categoryList = deepClone(this.categoryList)

      let docTypeList = []
      categoryList.forEach(category => {
        let docTypes = category.tipi_documento
        const excludedDocTypes = [...DOCUMENT_TYPE_PRESCRIPTION_LIST, ...DOCUMENT_TYPE_PSS_LIST, ...DOCUMENT_TYPE_EXEMPTION_LIST]


        docTypes.forEach(type => {
          type.codice_categoria = category.codice_categoria
          if (category.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE && excludedDocTypes.includes(type.codice)) {
            return
          }
          docTypeList.push(type)
        })

      });

      return docTypeList
    }
  },
  async created() {
    this.filterPeriodCode = PERIOD_LIST_FILTER[0].codice;

  },
  methods: {
    async loadDocumentList() {
      if (!this.hasMoreDocuments) return
      let documentList = []

      let payload = {
        filtro_docs: this.filterParams.filtro_docs,
        cit_id: this.patientTaxCode,
        limit: this.filterParams.limit,
        offset: this.offset,
        tipo_medico: this.filterParams.tipo_medico ?? DOCUMENT_DOCTOR_TYPE_MAP.BOTH
      }
      try {
        let {data: documents} = await getDoctorReports(this.patientTaxCode, payload)
        documentList = documents
      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei documenti."
        apiErrorNotify({error, message})
      }


      this.documentList = [...this.documentList, ...documentList]


    },

    openFilters() {
      this.isOpenFilters = !this.isOpenFilters
    },
    async setFilters(params) {
      this.offset = 0
      this.documentList = []
      this.isLoadingDocumentList = true
      this.documentListCount = Number.MAX_SAFE_INTEGER
      this.filterParams = {...params}
      this.isLoadingDocumentList = true
      await this.loadDocumentList()
      this.isLoadingDocumentList = false
    },
    setViewMode(mode) {
      this.viewSelected = mode
    },
    async loadDocumentListMore() {
      this.isLoadingDocumentListMore = true
      this.offset = this.documentList?.length
      await this.loadDocumentList()
      this.isLoadingDocumentListMore = false
    },
    onSelectDocument(isSelected, document) {
      let index = this.selectedDocumentsList.indexOf(document)
      if (isSelected) {
        if (index < 0)
          this.selectedDocumentsList.push(document)
      } else {
        this.selectedDocumentsList.splice(index, 1)
      }

    },
    compareDocuments() {
      if (this.selectedDocumentsList?.length <= 1) {
        this.$q.notify({
          type: 'negative',
          message: 'Selezionare almeno due documenti'
        })

        return
      }
      let route = {
        name: REPORT_COMPARE.name,
        params: {
          patientId: this.patientId,
          documentList: this.selectedDocumentsList
        }
      }

      this.$router.push(route)
    }
  }
}
</script>

<style scoped>

</style>
