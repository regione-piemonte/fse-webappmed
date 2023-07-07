<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="q-pa-md">
    <div class="text-h1 q-mb-lg ">Cerca assistiti</div>

    <q-tabs
      v-model="patientSearchTab"
      active-color="primary"
      align="left"
      indicator-color="primary"
      mobile-arrows
      no-caps
    >
      <q-tab :name="TABS.PIEDMONT" label="Assistito piemontese"/>
      <q-tab :name="TABS.EXTRA_REGION" label="Assistito di altre regioni"/>
    </q-tabs>


    <q-tab-panels
      v-model="patientSearchTab"
      animated
      class="bg-transparent"
    >
      <!-- ASSISTITO PIEMONTESE -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->
      <q-tab-panel :name="TABS.PIEDMONT">

        <!-- FILTRI -->
        <!-- ---------------------------------------------- -->
        <q-card>
          <q-card-section class="q-px-none">
            <q-card class="bg-transparent" flat>
              <q-card-section :horizontal="$q.screen.gt.md" class="q-pa-none">
                <q-form greedy @submit="onSearchPatientByAnagraphics">
                  <q-card-section class="column col-12 col-md-6 q-py-none">
                    <div class="col row item-center q-col-gutter-md">
                      <q-input
                        v-model="patientName"
                        :rules="[ruleRequired, ruleMinLength]"
                        class="col-12 col-md-6"
                        clearable
                        dense
                        label="Nome"
                        lazy-rules="ondemand"
                        no-error-icon
                      />
                      <q-input
                        v-model="patientSurname"
                        :rules="[ruleRequired, ruleMinLength]"
                        class="col-12 col-md-6"
                        clearable
                        dense
                        label="Cognome"
                        lazy-rules="ondemand"
                        no-error-icon
                      />

                      <q-input v-model="patientBirthdate" class="col-12 col-md-6" clearable dense
                               label="Data di nascita" stack-label type="date"/>
                    </div>
                    <div class="col-auto q-mt-lg self-end">
                      <q-btn :loading="isLoading && !patientTaxCode" color="primary" outline type="submit">
                        Cerca
                      </q-btn>
                    </div>

                  </q-card-section>
                </q-form>

                <q-separator :class="{'q-my-lg': $q.screen.lt.lg}" :vertical="$q.screen.gt.md"/>
                <q-card-section class="column col-12 col-md-6 q-py-none">
                  <q-input v-model="patientTaxCode" :mask="TAX_CODE_MASK" class="col" clearable dense
                           label="Codice fiscale"/>
                  <div class="col-auto q-mt-md self-end">
                    <q-btn :loading="isLoading && !!patientTaxCode" color="primary" outline
                           @click="onSearchPatientByTaxCode">
                      Cerca
                    </q-btn>
                  </div>

                </q-card-section>
              </q-card-section>
            </q-card>


          </q-card-section>
        </q-card>


        <!-- RISULTATI -->
        <!-- ---------------------------------------------- -->
        <div v-if="isFirstPiedmontSearch" class="q-mt-lg q-pt-md">
          <q-separator/>
          <div class="text-h6 q-my-lg">Risultati della ricerca</div>

          <div v-if="isLoading" class="row items-stretch q-col-gutter-lg">
            <div v-for="i in 4" :key="i"
                 class="col-12 col-md-6  self-stretch">
              <fse-patient-piedmont-item-skeleton/>
            </div>
          </div>
          <template v-else>


            <template v-if="patientList.length<=0">
              <csi-banner no-icon type="info">
                Nessun risultato in base ai filtri impostati
              </csi-banner>
            </template>
            <div v-else class="row items-stretch q-col-gutter-lg">
              <div v-for="patient in patientList" :key="patient.codice_fiscale"
                   class="col-12 col-md-6  self-stretch">
                <fse-patient-piedmont-item
                  :patient="patient"
                />

              </div>


            </div>
          </template>
        </div>


      </q-tab-panel>


      <!-- ASSISTITO FUORI REGIONE -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->
      <q-tab-panel :name="TABS.EXTRA_REGION">

        <!-- FILTRI -->
        <!-- ---------------------------------------------- -->
        <q-card>
          <q-card-section>
            <div class="row items-center q-col-gutter-md full-width">
              <div class="col-12 col-md">
                <q-input v-model="patientTaxCode" :mask="TAX_CODE_MASK" clearable dense label="Codice fiscale"/>
              </div>

              <div class="q-mt-md col-12 col-md-auto text-right">
                <q-btn :loading="isLoading" color="primary" outline @click="onSearchPatientExtraRegion">
                  Cerca
                </q-btn>
              </div>
            </div>
          </q-card-section>
        </q-card>

        <!-- RISULTATI -->
        <!-- ---------------------------------------------- -->
        <div v-if="isFirstExtraRegionSearch" class="q-mt-lg q-pt-md">
          <template v-if="isLoading">
            <q-separator/>
          </template>
          <template v-else>
            <q-separator/>
            <div class="text-h6 q-my-lg">Risultati della ricerca</div>
            <div v-if="patientExtraRegion" class="row items-stretch q-col-gutter-lg">
              <div class="col-12 col-md-6  self-stretch">
                <template v-if="isPatientPiedmont">
                  <fse-patient-piedmont-item
                    :patient="patientExtraRegion"
                  />
                </template>
                <template v-else>
                  <fse-patient-extra-region-item
                    :patient="patientExtraRegion"

                  />
                </template>


              </div>


            </div>
            <template v-else>
              <csi-banner no-icon type="info">
                Nessun risultato in base ai filtri impostati
              </csi-banner>
            </template>

          </template>
        </div>

      </q-tab-panel>

    </q-tab-panels>
  </div>
</template>

<script>

import FsePatientPiedmontItem from "components/patient/FsePatientPiedmontItem";
import FsePatientExtraRegionItem from "components/patient/FsePatientExtraRegionItem";
import { getPatientList} from "src/services/api";
import {apiErrorNotify, isEmpty} from "src/services/utils";
import FsePatientPiedmontItemSkeleton from "components/patient/FsePatientPiedmontItemSkeleton";
import {TAX_CODE_MASK} from "src/services/tax-code";

const LIMIT = 20
const TABS = {
  PIEDMONT: 'P',
  EXTRA_REGION: 'ER'
}

export default {
  name: "FsePatientSearchTabs",
  components: {FsePatientPiedmontItemSkeleton, FsePatientExtraRegionItem, FsePatientPiedmontItem},
  props: {},
  data() {
    return {
      TABS,
      TAX_CODE_MASK,
      isLoading: false,
      showAdhesionDialog: false,
      isFirstPiedmontSearch: false,
      isFirstExtraRegionSearch: false,
      splitterModel: 110,
      patientSearchTab: TABS.PIEDMONT,
      patientName: null,
      patientSurname: null,
      patientBirthdate: null,
      patientTaxCode: null,
      isSearching: false,
      menuItemLeft: 0,
      animationComplete: false,
      offset: 0,
      patientList: [],
      patientExtraRegion: null,
      patientConsents: null,
      isPatientPiedmont: false,
      consents: null
    }
  },
  computed: {
    systemSelected() {
      return this.$store.getters['getSeletedSystem']
    },
    ruleRequired() {
      return (v) => !!v || "Campo obbligatorio"
    },
    ruleMinLength() {
      return (v) => v?.length >= 2 || `Inserire almeno 2 caratteri`
    },
  },
  created() {

  },

  methods: {

    onSearchPatientByAnagraphics() {
      this.patientTaxCode = null

      let params = {
        nome: this.patientName,
        cognome: this.patientSurname,
        data_nascita: this.patientBirthdate,
      }
      this.isFirstPiedmontSearch = true

      this.onSearchPatientPiedmont(params)

    },
    onSearchPatientByTaxCode() {
      this.patientName = null
      this.patientSurname = null
      this.patientBirthdate = null
      let params = {
        cit_id: this.patientTaxCode,
      }
      this.isFirstPiedmontSearch = true
      this.onSearchPatientPiedmont(params)
    },
    async onSearchPatientPiedmont(params) {
      this.isLoading = true
      this.patientList = []
      try {
        let {data: list} = await getPatientList({params})

        this.patientList = list

      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare la lista dei pazienti."
        apiErrorNotify({error, message})
      }

      this.isLoading = false

    },
    async onSearchPatientExtraRegion() {
      this.isFirstExtraRegionSearch = true
      this.isLoading = true
      this.patientExtraRegion = null
      this.isPatientPiedmont = false
      this.consents =  await this.$store.dispatch('getPatientConsents', this.patientTaxCode)
      this.patientExtraRegion = {
        codice_fiscale: this.patientTaxCode
      }
      // Se l'indiricativo inizia con '010 vuol dire che si tratta di un paziente piemontese
      if (this.consents?.identificativo_informativa_corrente?.indexOf('010') === 0) {
        try {
          this.isPatientPiedmont = true
          let params = {
            cit_id: this.patientTaxCode,
          }
          let {data: list} = await getPatientList({params})
          this.patientExtraRegion = list[0]
        } catch (error) {
          let message = "Non Ã¨ stato possibile recuperare le informazioni del pazienti."
          apiErrorNotify({error, message})
        }
      }
      this.patientExtraRegion.consensi = this.consents
      this.isLoading = false
    }

  }
}
</script>

<style scoped>

</style>
