<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="bg-transparent q-mb-md fse-documents-filter-form" flat>
    <q-card-section class="no-padding">
      <div class="row items-center justify-between q-mb-sm">
        <div class="col">
          <q-btn flat icon="filter_list" label="Filtri" text-color="black"
                 @click="openFilters"/>
        </div>
        <!--    TIPO VISUALIZZAZIONE    -->
        <div v-if="viewMode" class="col-auto text-right">
          <div class="column items-center">
            <div class="text-caption">Visualizza</div>

            <q-btn-toggle
              v-model="viewSelected"
              :options="VIEW_MAP_OPTIONS"
              dense
              flat
              style="border-radius: 0px"
              toggle-color="primary"
              unelevated
              @input="onInputViewMode"
            >
              <template v-slot:cards>
                <q-icon name="apps">
                  <q-tooltip>Schede</q-tooltip>
                </q-icon>
              </template>
              <template v-slot:list>
                <q-icon name="reorder">
                  <q-tooltip>Lista</q-tooltip>
                </q-icon>
              </template>

            </q-btn-toggle>
          </div>

        </div>

      </div>

      <q-slide-transition>
        <div v-show="isOpenFilters" class="q-pb-sm">
          <div class="row items-center q-col-gutter-x-lg q-col-gutter-y-md q-mb-md">
                        <div class="col-md-6 col-12">
                          <q-select
                            v-model="filterPeriod"
                            :options="PERIOD_LIST_FILTER"
                            dense
                            label="Periodo"
                            map-options
                            option-label="descrizione"
                            option-value="codice"
                            @input="setFilters"
                          />

                        </div>

<!--            <div class="col-md-6 col-12">-->
<!--              <q-input v-model="startDate" />-->
<!--            </div>-->
            <div v-if="category" class="col-md-6 col-12">
              <q-select
                v-model="filterCategory"
                :options="categoriesOptions"
                clearable
                dense
                label="Fonte"
                option-label="descrizione_categoria"
                option-value="codice_categoria"
                @input="setFilters"
              />
            </div>


            <div v-if="documentType" class="col-md-6 col-12">
              <csi-select-multiple
                id="select-document-type"
                v-model="filterDocumentType"
                :options="documentTypeOptions"
                clearable
                dense
                label="Tipo documento"
                map-options
                multiple
                option-label="descrizione"
                option-value="codice"
                @input="setFilters"
              />
            </div>

            <div v-if="doctorType" class="col-md-6 col-12">
              <q-select
                v-model="filterDoctorType"
                :options="doctorTypeOptions"
                clearable
                dense
                emit-value
                label="Documenti di cui sono"
                map-options
                @input="setFilters"
              />
            </div>

<!--            <div class="col-md col-12">-->

<!--              <csi-buttons>-->
<!--                <csi-button :loading="loading" no-min-width outline @click="setFilters">Cerca</csi-button>-->
<!--              </csi-buttons>-->


<!--            </div>-->

          </div>

        </div>
      </q-slide-transition>
    </q-card-section>
  </q-card>
</template>

<script>
import {
  DOCUMENT_CATEGORY_LIST, DOCUMENT_CATEGORY_MAP,
  DOCUMENT_DOCTOR_TYPE_LIST, DOCUMENT_DOCTOR_TYPE_MAP,
  VIEW_MAP,
  VIEW_MAP_OPTIONS
} from "src/services/documents/config";
import {FORMAT_DATE, PERIOD_LIST_FILTER} from "src/services/global/config";
import {date} from "quasar";
import {getCategoryTypePair} from "src/services/documents/business-logic";
import {deepClone, orderBy, uniqueElementsBy} from "src/services/utils";

const {startOfDate, endOfDate, formatDate} = date;
const LIMIT = 16
export default {
  name: "FseDocumentsFilterForm",
  props: {
    viewMode: {type: Boolean, default: false},

    category: {type: Boolean, default: false},
    documentType: {type: Boolean, default: false},
    doctorType: {type: Boolean, default: false},
    loading: {type: Boolean, default: false},
    startPeriodCode: {type: String, default: '001'},
    doctorReports: {type: Boolean, default: false},
    documentTypeList: {type: Array, default: () => []},
    categoryList: {type: Array, default: () => []}
  },
  data() {
    return {
      VIEW_MAP_OPTIONS,
      PERIOD_LIST_FILTER,
      DOCUMENT_CATEGORY_LIST,
      DOCUMENT_DOCTOR_TYPE_LIST,
      isOpenFilters: true,
      filterPeriod: null,
      filterCategory: null,
      filterDoctorType: null,
      filterDocumentType: [],
      viewSelected: VIEW_MAP.CARDS,
      aslList: []
    }
  },
  computed: {
    doctorTypeOptions() {
      let list = DOCUMENT_DOCTOR_TYPE_LIST

      if (this.doctorReports) {
        list = list.filter(type => type.value !== DOCUMENT_DOCTOR_TYPE_MAP.BOTH)
      }

      return list
    },
    filterDocumentCategoryCodeList() {
      return this.filterCategory ? [this.filterCategory?.codice_categoria] : this.categoryList.flatMap(c => c.codice_categoria)
    },
    documentTypeOptions() {

      let docTypeList = this.documentTypeList ?? []

      docTypeList = docTypeList.filter(d => this.filterDocumentCategoryCodeList.includes(d.codice_categoria));

      let result = uniqueElementsBy(docTypeList, (a, b) => a.codice === b.codice);
      result = orderBy(result, ["descrizione"]);
      return result;
    },
    categoriesOptions(){
      let list = deepClone(this.categoryList)

      return list.map(category =>{
        if(category.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE)
          category.descrizione_categoria='Inseriti da soggetti SSN'
        else if(category.codice_categoria === DOCUMENT_CATEGORY_MAP.PERSONAL)
          category.descrizione_categoria='Inseriti dal cittadino'

        return category
      })
    },
    defaultDocumentType(){
      return this.$store.getters['getDocumentTypeFilter']
    }
  },
  created() {
    this.filterPeriod = PERIOD_LIST_FILTER.find(p => p.codice === this.startPeriodCode)

    if(this.defaultDocumentType){
      this.filterDocumentType = this.documentTypeOptions.filter(type => type.codice === this.defaultDocumentType)
      this.$store.dispatch('setDocumentTypeFilter', null)
    }
    this.setFilters()
  },
  methods: {
    openFilters() {
      this.isOpenFilters = !this.isOpenFilters
    },
    onInputViewMode(value) {
      this.$emit('on-view-mode', value)
    },
    setFilters() {
      let startDate = this.filterPeriod?.startDate ? formatDate(this.filterPeriod?.startDate, FORMAT_DATE) : null
      let endDate = this.filterPeriod?.endDate ? formatDate(this.filterPeriod?.endDate, FORMAT_DATE) : null
      let filterDoctorType = this.filterDoctorType


      let pairCategoryType = this.getCategoryTypePair()

      let params = {
        filtro_docs: {
          data_inizio: startDate,
          data_fine: endDate
        },
        limit: LIMIT,
        offset: 0,
        tipo_medico: filterDoctorType,
        categoria_tipologia: pairCategoryType
      }

      this.$emit('set-filters', params)
    },
    getCategoryTypePair() {

      let filterType = this.filterDocumentType;
      let typePairList = [];

      if (filterType.length > 0) {
        filterType.forEach(t => {
          typePairList.push({categoria: t.codice_categoria, tipologia: t.codice});
        })
      } else {
        this.documentTypeOptions.forEach(t => {
          typePairList.push({categoria: t.codice_categoria, tipologia: t.codice});
        })
      }


      return typePairList
    }

  }
}
</script>

<style scoped>

</style>
