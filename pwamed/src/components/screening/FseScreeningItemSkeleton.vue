<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="fit fse-screening-item-skeleton">
    <q-card-section class="no-padding fit column bg-white">

      <csi-card-item on-side>
        <template #primary>
          <!-- TIPOLOGIA -->
          <csi-card-item-primary  class="relative-position" inline>
            <template #icon>
              <q-skeleton type="QAvatar" size="32px" class="bg-primary-light"/>
            </template>

            <template #text>
              <div class="text-bold">
                <q-skeleton type="text" class="bg-grey-5" style="max-width: 200px"/>
              </div>

            </template>
          </csi-card-item-primary>
        </template>

        <template #secondary>
          <div class="q-px-sm q-py-md full-height column justify-between">

            <div>
              <q-list>
                <!-- DATA EMISSIONE -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                    <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                  </q-item-section>
                </q-item>

                <!-- ESITO -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                    <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                  </q-item-section>
                </q-item>

                <!-- AZIENDA -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                    <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                  </q-item-section>
                </q-item>


                <!-- UNITA' ORGANIZZATIVA -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                    <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </div>

          </div>

        </template>


        <template #actions>
          <div class="column q-gutter-sm text-right items-stretch q-px-md q-mb-md">
            <div>
              <q-skeleton
                type="QBtn"
                style="min-width: 200px"
                class="inline-block bg-blue-8"
              />
            </div>


          </div>



        </template>
      </csi-card-item>
    </q-card-section>
  </q-card>
</template>

<script>
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import CsiCardItem from "components/core/CsiCardItem";
import {SCREENING_DETAIL} from "src/router/routes";

export default {
  name: "FseScreeningItemSkeleton",
  props:{
    screening :{type:Object, default: null}
  },
  components: {CsiCardItem, CsiCardItemPrimary},
  computed: {
    screeningType(){
      return this.screening?.tipo_esame?.codice
    },
    iconName(){
      return this.screeningType ? `img:icone/screening/${this.screeningType}.svg` : '';
    },
    screeningName(){
      return this.screening?.tipo_esame?.descrizione
    },
    issueDate(){
      return this.screening?.data
    },
    result(){
      return this.screening?.esito
    },
    asl(){
      return this.screening?.azienda_sanitaria?.descrizione
    },
    opUnit(){
      return this.screening?.unita_operativa?.descrizione
    }
  },
  methods:{
    gotToDetail(id){
      let route = {
        name: SCREENING_DETAIL.name,
        params:{
          id: id
        }
      }

      this.$router.push(route)
    }
  }
}
</script>

<style scoped>

</style>
