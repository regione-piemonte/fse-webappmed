<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="fit">
    <q-card-section class="column q-pa-none  items-stretch justify-between full-height">
      <q-item class="q-py-md  bg-grey-3 " dense >
        <q-item-section side>
          <q-skeleton type="QAvatar" size="32px" class="bg-primary-light"/>
        </q-item-section>
        <q-item-section>
          <q-item-label><q-skeleton type="text" class="bg-grey-5" style="max-width: 200px"/></q-item-label>
          <q-item-label><q-skeleton type="text" style="max-width: 150px" /></q-item-label>
        </q-item-section>
      </q-item>
      <div class="col full-height q-pa-md">
        <div class="row items-start q-col-gutter-lg">
          <div class="col-12 col-md-6">
            <div class=" text-caption"><q-skeleton type="text" style="max-width: 100px"/></div>
            <div><q-skeleton type="text" class="bg-grey-5" style="max-width: 150px"/></div>
          </div>
          <div class="col-12 col-md-6">
            <div class=" text-caption"><q-skeleton type="text" style="max-width: 100px"/></div>
            <div><strong><q-skeleton type="text" class="bg-grey-5" style="max-width: 150px"/></strong></div>
          </div>
          <div class="col-12 col-md">
            <div class=" text-caption"><q-skeleton type="text" style="max-width: 100px"/></div>
            <div><strong><q-skeleton type="text" class="bg-grey-5" style="max-width: 150px"/></strong></div>
          </div>
        </div>

      </div>
      <div class="q-pa-md col-auto full-width">
        <div class="row full-width justify-end">
          <q-skeleton
            type="QBtn"
            :width="$q.screen.lt.sm ? '100%' : '150px'"
            class="bg-blue-8"
          />
        </div>


<!--        <csi-buttons>-->
<!--          <csi-button no-min-width @click="showDichiarationDialog=true">Vai al fascicolo</csi-button>-->
<!--        </csi-buttons>-->

      </div>
    </q-card-section>


  </q-card>
</template>

<script>
export default {
name: "FsePatientPiedmontItemSkeleton"
}
</script>

<style scoped>

</style>
