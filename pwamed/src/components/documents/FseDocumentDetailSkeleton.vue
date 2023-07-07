<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card-section class="no-padding">
    <csi-card-item on-side>
      <template #primary>


        <!-- TIPOLOGIA -->
        <csi-card-item-primary class="relative-position q-px-lg" inline>

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


        <div class="q-pa-lg row items-start q-col-gutter-md">

          <!-- DATA EMISSIONE -->
          <q-item class="col-12 col-md">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>

          <!-- MEDICO -->
          <q-item class="col-12 col-md-6">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>


          <!-- AZIENDA SANITARIA -->
          <q-item class="col-12 col-md-6">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>

          <!-- STRUTTURA -->
          <q-item class="col-12 col-md-6">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>


          <!-- UNITA OPERATIVA -->
          <q-item class="col-12 col-md-6">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>

          <!-- ASSETTO ORGANIZZATIVO -->
          <q-item class="col-12 col-md-6">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>

          <!-- DOCUMENTO MEDIATO -->
          <q-item class="col-12 col-md-6">
            <q-item-section>
              <q-item-label caption class="text-black"> <q-skeleton type="text" width="120px" class="bg-grey-6" /></q-item-label>
              <q-item-label class="text-bold"><q-skeleton type="text" width="80px" /></q-item-label>
            </q-item-section>
          </q-item>

        </div>

      </template>

      <template #actions>



      </template>
    </csi-card-item>
  </q-card-section>
</template>

<script>
import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";

export default {
name: "FseDocumentDetailSkeleton",
  components: { CsiCardItemPrimary, CsiCardItem}
}
</script>

<style scoped>

</style>
