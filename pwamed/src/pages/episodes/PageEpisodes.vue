<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <!--  FILTRI  -->
    <!-- -----------------------------------------------------------------------------------------------------------  -->
    <q-card class="bg-transparent q-mb-md" flat>
      <q-card-section class="no-padding">
        <div class="row items-center justify-between q-mb-sm">
          <div class="col">
            <q-btn flat icon="filter_list" label="Filtri" text-color="black"
                   @click="openFilters"/>
          </div>

        </div>

        <q-slide-transition>
          <div v-show="isOpenFilters" class="q-pb-sm">
            <div class="row items-end q-col-gutter-x-lg q-col-gutter-y-md q-mb-md">
              <div class="col-md-6 col-12">
                <q-select
                  v-model="filterPeriodCode"
                  :options="periodList"
                  dense
                  label="Periodo"
                  map-options
                  option-label="descrizione"
                  option-value="codice"
                  @input="setFilters"
                />

              </div>

              <div class="col-md-6 col-12">
                <csi-select-multiple
                  id="select-document-type"
                  v-model="filterDocumentType"
                  :options="documentTypeList"
                  clearable
                  dense
                  emit-value
                  label="Tipo documento"
                  option-label="descrizione"
                  option-value="codice"
                  @input="setFilters"
                />


              </div>
              <div class="col-md-6 col-12">
                <q-select
                  v-model="filterEpisodeType"
                  :options="EPISODES_TYPE_LIST"
                  clearable
                  dense
                  emit-value
                  label="Tipo episodio"
                  map-options
                  @input="setFilters"
                />
              </div>

<!--              <div class="col-md-6 col-12">-->
<!--                <csi-buttons>-->
<!--                  <csi-button outline no-min-width @click="setFilters">Cerca</csi-button>-->
<!--                </csi-buttons>-->
<!--              </div>-->

            </div>

          </div>
        </q-slide-transition>
      </q-card-section>
    </q-card>

    <template v-if="isLoadingEpisodesList">

      <q-card flat v-for="i in 4" :key="i" class="q-my-md">
        <q-item>
          <q-item-section>
            <q-skeleton type="text" class="bg-blue-3" width="100%"></q-skeleton>
          </q-item-section>

          <q-item-section side>
            <q-icon name="expand_more" color="blue-3" size="md" />
          </q-item-section>
        </q-item>
      </q-card>
    </template>

    <template v-else-if="episodesList.length <= 0">
      <csi-banner class="q-mt-xl" type="info">
        Nessun episodio trovato in base ai filtri di ricerca
      </csi-banner>
    </template>

    <template v-else>

      <div class="column q-gutter-md">
        <q-card
          v-for="episode in episodesList"
          :key="'episode-container--' + episode.id_episodio"
          flat
        >
          <q-expansion-item
            expand-icon-class="text-primary"
            group="episode-list"
            header-class="text-primary text-bold"
            @before-show="loadEpisodeDocumentList(episode)"
          >
            <template #header>
              <q-item-section>
                {{ episode.id_episodio }}

                <template
                  v-if=" episode.unita_operativa_accettazione"
                >
                  {{ episode.unita_operativa_accettazione }}
                  -
                </template>

                <template
                  v-if="episode.struttura_accettazione"
                >
                  {{ episode.struttura_accettazione }}
                </template>

                <template v-if="episode.descrizione_azienda_accettazione">
                  ({{ episode.descrizione_azienda_accettazione }}),
                </template>

                {{ episode.data_inizio | date }}
              </q-item-section>
            </template>

            <div class="q-pa-md  row  items-stretch q-col-gutter-md">

              <template v-if="isLoadingEpisodeDocumentList">
                <div v-for="i in 6" :key="'list-skeleton--' + i" class="col-lg-4 col-md-6 col-12">
                  <q-card class="fit">
                    <fse-document-item-skeleton/>
                  </q-card>
                </div>
              </template>

              <template v-else>
                <div v-for="document in episodeDocumentList"
                     :key="'episode-container-item--' + document.id_documento_ilec"
                     class="col-xl-4  col-md-6  col-12"
                >

<!--                  <fse-episode-document-item-->
<!--                    :document="document"-->
<!--                  />-->

                  <fse-document-item :document="document" episodes/>

                </div>



              </template>
            </div>
          </q-expansion-item>
        </q-card>
      </div>

      <template v-if="hasMoreEpisodes">
        <div class="q-mt-md text-center">
          <q-btn
            :loading="isLoadingEpisodeListMore"
            dense
            flat
            @click="loadEpisodeListMore"
          >
            Carica altri episodi
          </q-btn>
        </div>
      </template>
    </template>

  </csi-page>
</template>

<script>
import {PERIOD_LIST_FILTER} from "src/services/global/config";
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";
import {getDocumentsTypeList, getEpisodesDocumentList, getEpisodesList} from "src/services/api";
import {DOCUMENT_CATEGORY_MAP, EPISODES_TYPES_MAP} from "src/services/documents/config";
import {apiErrorNotify, isEmpty, orderBy} from "src/services/utils";
import {DOCUMENTS, HOME} from "src/router/routes";
import FseDocumentItem from "components/documents/FseDocumentItem";

const EPISODES_TYPE_LIST = [
  {label: 'Ricovero', value: EPISODES_TYPES_MAP.RICOVERO},
  {label: 'Passaggi in PS', value: EPISODES_TYPES_MAP.PASSAGGI_PS}
]
const LIMIT = 20
export default {
  name: "PageEpisodes",
  components: {FseDocumentItem,  FseDocumentItemSkeleton},
  data() {
    return {
      EPISODES_TYPE_LIST,
      isLoading: false,
      isOpenFilters: true,
      filterPeriodCode: null,
      periodList: [],
      filterDocumentType: null,
      filterEpisodeType: null,
      isLoadingEpisodesList: false,
      episodesList: [],
      isLoadingEpisodeListMore: false,
      isLoadingEpisodeDocumentList: false,
      episodeDocumentList: [],
      offset: 0,
      recoveryEpisodesCount: 0,
      psEpisodesCount: 0,
      totalEpisodesCount: 0
    }
  },
  computed: {
    patient() {
      return this.$store.getters['getActivePatient']
    },
    taxCode() {
      return this.patient?.codice_fiscale
    },
    hasMoreEpisodes() {
      return this.episodesList?.length < this.totalEpisodesCount
    },
    documentTypeList(){
      return this.$store.getters['getDocumentTypeList']
    }
  },
  async created() {
    this.periodList = PERIOD_LIST_FILTER;
    this.filterPeriodCode = this.periodList[0];

    if(!this.patient){
      this.$router.replace(HOME)
      return
    }

    this.isLoadingEpisodesList = true
    if(isEmpty(this.documentTypeList)){
      try{
        let {data:list} = await getDocumentsTypeList()
        this.$store.dispatch('setDocumentTypeList', {list})
      }catch (error) {
        console.log(error)
      }
    }

    await this.loadEpisodesList()
    this.isLoadingEpisodesList = false
  },
  methods: {
    openFilters() {
      this.isOpenFilters = !this.isOpenFilters
    },
    async setFilters() {
      this.episodesList = []
      this.isLoadingEpisodesList = true
      await this.loadEpisodesList()
      this.isLoadingEpisodesList = false
    },
    async loadEpisodesList() {
      let documentTypeList = this.filterDocumentType
      if(isEmpty(this.filterDocumentType)){
        documentTypeList = this.documentTypeList.map(documentType => documentType.codice)
      }

      try {
        let payload = {
          categoria: DOCUMENT_CATEGORY_MAP.FSE,
          filtro_docs:{
            data_inizio: this.filterPeriodCode?.startDate,
            data_fine: this.filterPeriodCode?.endDate,
          },
          limit: LIMIT,
          offset: this.offset,
          tipologia_documento: documentTypeList
        }

        if (this.filterEpisodeType) {
          payload.tipo_episodio = this.filterEpisodeType
          let {data: episodes} = await getEpisodesList(this.taxCode, payload)
          if (episodes?.elenco_episodi)
            this.episodesList = [...this.episodesList, ...episodes?.elenco_episodi]
          this.totalEpisodesCount = episodes?.numero_episodi
        } else {
          let episodes = this.episodesList
          payload.tipo_episodio = EPISODES_TYPES_MAP.RICOVERO
          let {data: iEpisodes} = await getEpisodesList(this.taxCode, payload)
          this.recoveryEpisodesCount = iEpisodes?.numero_episodi
          if (iEpisodes?.elenco_episodi)
            episodes = [...episodes, ...iEpisodes?.elenco_episodi]

          payload.tipo_episodio = EPISODES_TYPES_MAP.PASSAGGI_PS
          let {data: eEpisodes} = await getEpisodesList(this.taxCode, payload)
          this.psEpisodesCount = eEpisodes?.numero_episodi
          if (eEpisodes?.elenco_episodi)
            episodes = [...episodes, ...eEpisodes?.elenco_episodi]

          this.episodesList = orderBy(episodes, ['data_inizio'], ['desc'])
          this.totalEpisodesCount = this.recoveryEpisodesCount + this.psEpisodesCount
        }


      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista degli episodi."
        apiErrorNotify({error, message})
      }


    },
    async loadEpisodeListMore() {
      this.offset += LIMIT
      this.isLoadingEpisodeListMore = true
      await this.loadEpisodesList()
      this.isLoadingEpisodeListMore = false
    },
    async loadEpisodeDocumentList(episode) {
      this.isLoadingEpisodeDocumentList = true
      let idEpisode = episode.id_episodio

      let params = {
        codice_componente_locale: episode.codice_cl
      }
      try {
        let {data: documents} = await getEpisodesDocumentList(this.taxCode, idEpisode, {params})
        this.episodeDocumentList = documents

        // this.episodesList = this.episodesList?.map(episode =>{
        //   if(episode.id_episodio === idEpisode){
        //     episode.documenti = list
        //   }
        //   return episode
        // })
      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei documenti per l'episodio selezionato."
        apiErrorNotify({error, message})
      }


      this.isLoadingEpisodeDocumentList = false
    },
  }
}
</script>

<style scoped>

</style>
