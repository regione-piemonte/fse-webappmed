<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>


    <div class="column q-gutter-md">
      <template v-if="isLoading">
        <q-card v-for="i in 3" :key="i" class="q-my-md" flat>
          <q-item>
            <q-item-section>
              <q-skeleton class="bg-blue-3" type="text" width="100%"></q-skeleton>
            </q-item-section>

            <q-item-section side>
              <q-icon color="blue-3" name="expand_more" size="md"/>
            </q-item-section>
          </q-item>
        </q-card>
      </template>

      <template v-else-if="screeningTypeList.length>0">
        <q-card
          v-for="screeningType in screeningTypeList"
          :key="'screening-container--' + screeningType.id_screening"
          flat
        >
          <template v-if="screeningType.tipo_screening">
            <q-expansion-item
              :label="screeningType.tipo_screening.descrizione"
              expand-icon-class="text-primary"
              group="screening-list"
              header-class="text-primary text-bold"
              @before-show="loadScreeningList(screeningType)"
            >


              <div class="q-pa-md  row  items-stretch q-col-gutter-md">

                <template v-if="isLoading">
                  <div v-for="i in 4" :key="'list-skeleton--' + i" class="col-lg-4 col-md-6 col-12">
                    <q-card class="fit">
                      <fse-screening-item-skeleton/>
                    </q-card>
                  </div>
                </template>

                <template v-else>
                  <div v-for="(screening,index) in screeningList"
                       :key="'screening-container-item--' + index"
                       class="col-xl-4  col-md-6  col-12"
                  >
                    <fse-screening-item
                      :screening="screening"
                      :screening-type="screeningType.tipo_screening"
                      :screening-id="screeningType.id_screening"
                    />

                  </div>

                </template>
              </div>
            </q-expansion-item>
          </template>

        </q-card>
      </template>

      <template v-else>
        <csi-banner type="info">
          Nessuno screening trovato
        </csi-banner>

      </template>

    </div>

  </csi-page>
</template>

<script>


import {SCREENING_TYPES_CODES, SCREENING_TYPES_LIST} from "src/services/screening/config";
import FseScreeningItem from "components/screening/FseScreeningItem";
import {getScreeningList} from "src/services/api";
import {apiErrorNotify, orderBy} from "src/services/utils";
import FseScreeningItemSkeleton from "components/screening/FseScreeningItemSkeleton";

export default {
  name: "PageScreening",
  components: {
    FseScreeningItem,
    FseScreeningItemSkeleton,

  },
  data() {
    return {
      SCREENING_TYPES_LIST,
      SCREENING_TYPES_CODES,
      isLoading: false,
      screeningList: [],
      screeningTypeList: [],
      patient: null
    }
  },
  computed: {
    patientTaxCode() {
      let patient = this.$store.getters['getActivePatient']
      return patient?.codice_fiscale
    },
  },
  async created() {
    this.isLoading = true
    try {
      let response = await getScreeningList(this.patientTaxCode)
      this.patient = response?.data?.paziente
      this.screeningTypeList = response.data?.info_sistemi_screening ?? []
    } catch (error) {
      let message = "Non Ã¨ stato possibile recuperare la lista degli screening"
      apiErrorNotify({error, message})
    }


    this.isLoading = false
  },
  methods: {
    loadScreeningList(screeningType) {

      let screenings = screeningType.esami ?? []
      this.screeningList = orderBy(screenings, ['data', ['desc']])

    },

  }
}
</script>

<style scoped>

</style>
