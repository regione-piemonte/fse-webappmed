<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>

  <div class="fit fse-document-item-skeleton ">
    <q-card
      class="fit"
    >
      <!-- VISTA LISTA -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->

      <q-card-section v-if="listView" class="no-padding bg-white">
        <div class=" row">

          <div class="row items-center bg-grey-3 q-py-md q-px-md col-12 col-md-3 col-lg-2">
            <div :class="[{'q-pr-md': $q.screen.lt.md}]" class="col-auto col-md-12 ">
              <div class="column items-center">
                <q-skeleton type="QAvatar" size="32px" class="bg-primary-light"/>
              </div>

            </div>

            <div :class="{ 'text-center': $q.screen.gt.xs }" class="col col-md-12">
              <div class="text-bold">
                <q-skeleton type="text" class="bg-grey-5" style="max-width: 200px"/>
              </div>
            </div>

          </div>




          <div class="row items-start q-col-gutter-md col-12 col-md q-pa-md ">

            <q-item class="col-12">
              <q-item-section>
                <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
              </q-item-section>


            </q-item>

            <!-- DATA EMISSIONE -->
            <q-item class="col-12  col-md-auto">
              <q-item-section>
                <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
              </q-item-section>


            </q-item>

            <!-- AZIENDA SANITARIA -->
            <q-item class="col-12 col-md">
              <q-item-section>
                <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                <q-item-label class="text-bold"><q-skeleton type="text" width="160px" /></q-item-label>
              </q-item-section>
            </q-item>

          </div>

          <!-- AZIONI -->
          <!-- ----------------------------------------------------------------------------------------------------- -->
          <div class="col col-12  col-md-auto q-pa-md">
            <div class="full-width  q-gutter-sm text-right">
              <div class="full-width text-center">
                <q-skeleton type="text" width="160px" class="bg-blue-3" />
              </div>

              <q-skeleton
                type="QBtn"
                width="140px"
                class="inline-block bg-blue-8"
              />
            </div>
          </div>


        </div>

      </q-card-section>

      <!-- VISTA SCHEDA -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->

      <q-card-section v-else class="no-padding fit column bg-white">


        <!-- DOCUMENTO -->
        <!-- ----------------------------------------------------------------------------------------------------------- -->
        <csi-card-item on-side>
          <template #primary>

            <!-- TIPOLOGIA -->
            <csi-card-item-primary  class="relative-position" inline >

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

            <div class="full-height column justify-between">
              <div class="q-px-sm q-py-md ">
                <q-list>
                  <!-- DATA EMISSIONE -->
                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                      <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                    </q-item-section>
                  </q-item>

                  <!-- AZIENDA SANITARIA -->
                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                      <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                    </q-item-section>
                  </q-item>


                </q-list>
              </div>

              <!-- AZIONI -->
              <!-- ----------------------------------------------------------------------------------------------------- -->

              <div class="q-px-lg q-py-md">
                <q-skeleton type="text" width="160px" class="bg-blue-3" />
              </div>

            </div>

          </template>


          <template #actions>
            <div class="column q-gutter-sm items-stretch q-px-md q-mb-md">

              <q-skeleton
                type="QBtn"
                width="100%"
                class="inline-block bg-blue-8"
              />

            </div>


          </template>
        </csi-card-item>
      </q-card-section>

    </q-card>
  </div>


</template>

<script>
import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";

export default {
  name: "FseDocumentItemSkeleton",
  components: {CsiCardItemPrimary, CsiCardItem },
  props: {
    listView:{type:Boolean, default: false},
    selectable:{type:Boolean, default: false},
  },
  data() {
    return {};
  },
  computed: {},
  created() {},
  methods: {}
};
</script>

<style lang="scss"></style>
