<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <csi-banner type="info" class="q-mb-lg">
      Come previsto dalla normativa, le eventuali esenzioni a maggior tutela non vengono mostrate
    </csi-banner>


    <div class="row  items-stretch q-col-gutter-md">

      <template v-if="isLoadingExemptionList">
        <div v-for="i in 6" :key="'list-skeleton--' + i" class="col-lg-4 col-md-6 col-12">
          <q-card class="fit">
            <fse-exemption-item-skeleton/>
          </q-card>
        </div>
      </template>

      <template v-else-if="exemptionList.length>0">
        <div v-for="(exemption,index) in exemptionList"
             :key="'exemption-container-item--' + index"
             class="col-xl-4  col-md-6  col-12"
        >
          <fse-exemption-item :exemption="exemption"/>

        </div>

      </template>

      <div v-else class="q-ma-sm">

        <csi-banner type="info">
          Il paziente non ha esenzioni
        </csi-banner>
      </div>
    </div>

<!--    <div class="column q-gutter-md">-->
<!--      <q-card flat>-->
<!--        <q-expansion-item-->
<!--          default-opened-->
<!--          expand-icon-class="text-primary"-->
<!--          header-class="text-primary text-bold"-->
<!--          label="Esenzioni"-->
<!--          @before-show="loadExemptionList"-->
<!--        >-->

<!--          <div class="q-pa-md  row  items-stretch q-col-gutter-md">-->

<!--            <template v-if="isLoadingExemptionList">-->
<!--              <div v-for="i in 4" :key="'list-skeleton&#45;&#45;' + i" class="col-lg-4 col-md-6 col-12">-->
<!--                <q-card class="fit">-->
<!--                  <fse-exemption-item-skeleton/>-->
<!--                </q-card>-->
<!--              </div>-->
<!--            </template>-->

<!--            <template v-else-if="exemptionList.length>0">-->
<!--              <div v-for="(exemption,index) in exemptionList"-->
<!--                   :key="'exemption-container-item&#45;&#45;' + index"-->
<!--                   class="col-xl-4  col-md-6  col-12"-->
<!--              >-->
<!--                <fse-exemption-item :exemption="exemption"/>-->

<!--              </div>-->

<!--            </template>-->

<!--            <div v-else class="q-ma-sm">-->

<!--              <csi-banner type="info">-->
<!--                Il paziente non ha esenzioni-->
<!--              </csi-banner>-->
<!--            </div>-->
<!--          </div>-->
<!--        </q-expansion-item>-->
<!--      </q-card>-->

<!--&lt;!&ndash;      <q-card flat>&ndash;&gt;-->
<!--&lt;!&ndash;        <q-expansion-item&ndash;&gt;-->
<!--&lt;!&ndash;          default-opened&ndash;&gt;-->
<!--&lt;!&ndash;          expand-icon-class="text-primary"&ndash;&gt;-->
<!--&lt;!&ndash;          header-class="text-primary text-bold"&ndash;&gt;-->
<!--&lt;!&ndash;          label="Documenti"&ndash;&gt;-->
<!--&lt;!&ndash;          @before-show="loadDocumentList"&ndash;&gt;-->
<!--&lt;!&ndash;        >&ndash;&gt;-->
<!--&lt;!&ndash;          &lt;!&ndash;  FILTRI  &ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;          &lt;!&ndash; -&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;  &ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;          <div class="q-px-md q-mt-sm">&ndash;&gt;-->
<!--&lt;!&ndash;            <fse-documents-filter-form&ndash;&gt;-->
<!--&lt;!&ndash;              :document-type-list="documentTypeList"&ndash;&gt;-->
<!--&lt;!&ndash;              :category-list="categoryList"&ndash;&gt;-->
<!--&lt;!&ndash;              :start-period-code="filterPeriodCode"&ndash;&gt;-->
<!--&lt;!&ndash;              document-type&ndash;&gt;-->
<!--&lt;!&ndash;              doctor-type&ndash;&gt;-->
<!--&lt;!&ndash;              @set-filters="setFilters"&ndash;&gt;-->
<!--&lt;!&ndash;            />&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--&lt;!&ndash;          <div class="q-pa-md  row  items-stretch q-col-gutter-md">&ndash;&gt;-->
<!--&lt;!&ndash;            <template v-if="isLoadingDocumentList">&ndash;&gt;-->
<!--&lt;!&ndash;              <div v-for="i in 4" :key="'document-list-skeleton&#45;&#45;' + i" class="col-lg-4 col-md-6 col-12">&ndash;&gt;-->
<!--&lt;!&ndash;                <q-card class="fit">&ndash;&gt;-->
<!--&lt;!&ndash;                  <fse-document-item-skeleton/>&ndash;&gt;-->
<!--&lt;!&ndash;                </q-card>&ndash;&gt;-->
<!--&lt;!&ndash;              </div>&ndash;&gt;-->
<!--&lt;!&ndash;            </template>&ndash;&gt;-->

<!--&lt;!&ndash;            <template v-else-if="documentList.length>0">&ndash;&gt;-->
<!--&lt;!&ndash;              <div v-for="(document,index) in documentList"&ndash;&gt;-->
<!--&lt;!&ndash;                   :key="'document-container-item&#45;&#45;' + index"&ndash;&gt;-->
<!--&lt;!&ndash;                   class="col-xl-4  col-md-6  col-12"&ndash;&gt;-->
<!--&lt;!&ndash;              >&ndash;&gt;-->
<!--&lt;!&ndash;                <fse-document-item :document="document"/>&ndash;&gt;-->

<!--&lt;!&ndash;              </div>&ndash;&gt;-->

<!--&lt;!&ndash;              <template v-if="hasMoreDocuments">&ndash;&gt;-->
<!--&lt;!&ndash;                <div class="q-mt-md text-center col-12">&ndash;&gt;-->
<!--&lt;!&ndash;                  <q-btn&ndash;&gt;-->
<!--&lt;!&ndash;                    :loading="isLoadingMoreDocumentList"&ndash;&gt;-->
<!--&lt;!&ndash;                    dense&ndash;&gt;-->
<!--&lt;!&ndash;                    flat&ndash;&gt;-->
<!--&lt;!&ndash;                    @click="loadDocumentListMore"&ndash;&gt;-->
<!--&lt;!&ndash;                  >&ndash;&gt;-->
<!--&lt;!&ndash;                    Carica altri documenti&ndash;&gt;-->
<!--&lt;!&ndash;                  </q-btn>&ndash;&gt;-->
<!--&lt;!&ndash;                </div>&ndash;&gt;-->
<!--&lt;!&ndash;              </template>&ndash;&gt;-->
<!--&lt;!&ndash;            </template>&ndash;&gt;-->

<!--&lt;!&ndash;            <div v-else class="q-ml-md">&ndash;&gt;-->

<!--&lt;!&ndash;              <csi-banner type="info">&ndash;&gt;-->
<!--&lt;!&ndash;                Nessun documento trovato in base ai filtri di ricerca&ndash;&gt;-->
<!--&lt;!&ndash;              </csi-banner>&ndash;&gt;-->
<!--&lt;!&ndash;            </div>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--&lt;!&ndash;        </q-expansion-item>&ndash;&gt;-->
<!--&lt;!&ndash;      </q-card>&ndash;&gt;-->
<!--    </div>-->
  </csi-page>
</template>

<script>
import FseExemptionItem from "components/exemptions/FseExemptionItem";
import FseDocumentItem from "components/documents/FseDocumentItem";
import { getExemptionsList} from "src/services/api";
import {apiErrorNotify, deepClone} from "src/services/utils";
import {FORMAT_DATE, PERIOD_LIST_FILTER} from "src/services/global/config";
import {date} from "quasar";
import {
  DOCUMENT_CATEGORY_MAP,
  DOCUMENT_DOCTOR_TYPE_MAP, DOCUMENT_TYPE_EXEMPTION_LIST,
  DOCUMENT_TYPE_PRESCRIPTION_LIST, DOCUMENT_TYPE_PSS_LIST
} from "src/services/documents/config";
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";
import FseExemptionItemSkeleton from "components/exemptions/FseExemptionItemSkeleton";
import FseDocumentsFilterForm from "components/documents/FseDocumentsFilterForm";
import {getDocumentFseTypeList} from "src/services/documents/business-logic";

const { startOfDate, endOfDate, formatDate} = date;


export default {
  name: "PageExemptions",
  components: {
    FseExemptionItemSkeleton,  FseExemptionItem
  },
  data() {
    return {
      isLoadingExemptionList: false,
      isLoadingDocumentList: false,
      isLoadingMoreDocumentList: false,
      exemptionList: [],
      documentList: [],
      offset: 0,
      filterPeriodCode: null,
      endDate: null,
      startDate: null,
      filterParams: null,
      isFirstLoading: true,
      documentListCount: Number.MAX_SAFE_INTEGER

    }
  },
  computed: {
    patientTaxCode() {
      let patient = this.$store.getters['getActivePatient']
      return patient?.codice_fiscale
    },
    categoryList() {
      let categories =  this.$store.getters['getDocumentCategoriesList']
      return categories?.filter(c => c.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE)
    },
    documentTypeList() {
      return getDocumentFseTypeList(DOCUMENT_TYPE_EXEMPTION_LIST)
    },
    hasMoreDocuments(){
      return this.documentList?.length < this.documentListCount
    }
  },
  created() {
    let period = PERIOD_LIST_FILTER.find(p => p.codice === '005')
    this.filterPeriodCode = period.codice;

    this.loadExemptionList()


  },
  methods: {
    async loadExemptionList() {
      this.isLoadingExemptionList = true
      this.isLoadingExemptionList = true
      try {

        let {data: exemptions} = await getExemptionsList(this.patientTaxCode )
        this.exemptionList = exemptions


      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista delle esenzioni."
        apiErrorNotify({error, message})
      }

      this.isLoadingExemptionList = false
    },
    // async loadDocumentList() {
    //   if(!this.hasMoreDocuments) return
    //   this.isLoadingDocumentList = true
    //   let payload = {...this.filterParams}
    //   payload.offset = this.offset
    //   try {
    //     let {data: documents} = await getDocumentsList(this.patientTaxCode, payload)
    //     this.documentList = documents.elenco_documenti ?? []
    //     this.documentListCount= documents.numero_documenti ?? 0
    //
    //   } catch (error) {
    //     let message = "Non Ã¨ stato possibile recuperare la lista dei documenti."
    //     apiErrorNotify({error, message})
    //   }
    //   this.isFirstLoading = false
    //   this.isLoadingDocumentList = false
    // },
    // async loadDocumentListMore() {
    //   this.isLoadingMoreDocumentList = true
    //   this.offset = this.documentList?.length
    //   await this.loadDocumentList()
    //   this.isLoadingMoreDocumentList = false
    // },
    // async setFilters(params) {
    //   this.offset = 0
    //   this.documentList = []
    //   this.filterParams = params
    //   this.documentListCount= Number.MAX_SAFE_INTEGER
    //   this.isLoadingDocumentList = true
    //   await this.loadDocumentList()
    //   this.isLoadingDocumentList = false
    //
    //
    // },


  }
}
</script>

<style scoped>

</style>
