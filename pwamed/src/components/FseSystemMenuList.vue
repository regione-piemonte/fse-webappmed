<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-list ref="fseSystemMenuList">

    <q-item key="header" class="q-pa-none text-h1  text-black fse-system-item">Regime</q-item>
    <div
      v-for="system in systemList"
      :key="system.codice"
      style="width:280px"
    >
      <q-item
        :active="isSystemSelected(system)"
        active-class="csi-menu-item--selected"
        class="q-my-md csi-menu-item fse-system-item"
        clickable
        @click="$emit('on-select', system)"
      >
        <q-item-section side>
          <q-icon :name="getSystemIcon(system.codice)" size="lg"/>
        </q-item-section>
        <q-item-section>
          <q-item-label><strong>{{ system.descrizione | upperCase }}</strong></q-item-label>
        </q-item-section>
      </q-item>
    </div>

  </q-list>
</template>

<script>
export default {
  name: "FseSystemMenuList",
  props:{

  },
  computed:{
    systemList(){
      return this.$store.getters["getSystemList"]
    },
    systemSelected(){
      return this.$store.getters['getSeletedSystem']
    },
  },
  methods:{
    isSystemSelected(system){
      return this.systemSelected?.codice === system?.codice
    },
    getSystemIcon(code){
      return `img:icone/regimi/ico_${code?.toLowerCase()}.svg`
    },
  }
}
</script>

<style scoped>

</style>
