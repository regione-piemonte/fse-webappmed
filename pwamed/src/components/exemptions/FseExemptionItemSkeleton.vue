<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="fit fse-exemption-item-skeleton " >
    <q-card
      class="fit"
    >

      <q-card-section class="no-padding fit column bg-white">

        <csi-card-item on-side>
          <template #primary>
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


                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                      <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                    </q-item-section>
                  </q-item>


                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
                      <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
                    </q-item-section>
                  </q-item>

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



        </csi-card-item>
      </q-card-section>

    </q-card>
  </div>

</template>

<script>

import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";

export default {
  name: "FseExemptionItemSkeleton",
  components: {CsiCardItemPrimary, CsiCardItem},

}
</script>

<style scoped>

</style>
