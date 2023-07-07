<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div v-if="office" class="q-pa-lg row items-start q-col-gutter-md">
    <q-item class="col-12 col-md">
      <q-item-section>
        <q-item-label caption class="text-black">Denominazione</q-item-label>
        <q-item-label class="text-bold">{{ office.denominazione | empty}}</q-item-label>
      </q-item-section>
    </q-item>


    <q-item class="col-12 col-md-6">
      <q-item-section>
        <q-item-label caption class="text-black">Indirizzo</q-item-label>
        <q-item-label class="text-bold">{{ addressLabel | empty }}</q-item-label>
      </q-item-section>
    </q-item>

    <q-item class="col-12 col-md-6">
      <q-item-section>
        <q-item-label caption class="text-black">Numero di telefono</q-item-label>
        <q-item-label class="text-bold">{{ telephone | empty }}</q-item-label>
      </q-item-section>
    </q-item>

    <q-item class="col-12 col-md-6">
      <q-item-section>
        <q-item-label caption class="text-black">Indirizzo email</q-item-label>
        <q-item-label class="text-bold">{{ email | empty }}</q-item-label>
      </q-item-section>
    </q-item>


    <q-item class="col-12 col-md-6">
      <q-item-section>
        <q-item-label caption class="text-black">Orario</q-item-label>
        <q-item-label v-if="officeHoursList.length <=0" class="text-italic text-caption text-accent">
          Orario non disponibile
        </q-item-label>
        <template v-else>
          <q-item-label v-for="(hour,index) in officeHoursList" :key="index" class="text-bold row items-center">
            <div class="col col-sm-2 col-md-4">
              {{ hour.giorno }}
            </div>
            <div class="col">
              {{ hour.orario_inizio }} - {{ hour.orario_fine }}
            </div>
          </q-item-label>
        </template>

      </q-item-section>
    </q-item>
  </div>
</template>

<script>
export default {
  name: "FseMmgOfficeItem",
  props: {
    office: {type: Object, default: null}
  },
  computed: {
    address() {
      return this.office?.indirizzo_studio
    },
    addressLabel() {
      if (this.address) {
        return `${this.address.indirizzo?? ''} - ${this.address.cap ?? ''} ${this.address.desc_comune ?? ''}`
      } else {
        return ''
      }
    },
    telephone() {
      return this.address?.tel_primario
    },
    email() {
      return this.address?.email
    },
    officeHoursList() {
      return this.office?.giorni_apertura ?? []
    }
  }
}
</script>

<style scoped>

</style>
