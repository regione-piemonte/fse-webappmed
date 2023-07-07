<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-item
    :active="isSelected"
    active-class="csi-menu-item--selected"
    class="csi-menu-item fse-system-menu-item"
    clickable
    @click="$emit('on-select',system)"
  >
    <q-item-section side>
      <q-icon :name="icon" size="lg"/>
    </q-item-section>
    <q-item-section>
      <q-item-label><strong>{{ description | upperCase }}</strong></q-item-label>
    </q-item-section>
  </q-item>
</template>

<script>
export default {
  name: "FseSystemMenuItem",
  props:{
    system:{type:Object, required:true, default:null}
  },
  computed:{
    systemSelected(){
      return this.$store.getters['getSeletedSystem']
    },
    code(){
      return this.system?.codice
    },
    description(){
      return this.system?.descrizione
    },
    isSelected(){
      return this.systemSelected?.codice === this.code
    },
    icon(){
      return `img:icone/regimi/ico_${this.code?.toLowerCase()}.svg`
    }
  }
}
</script>

<style lang="sass">
.fse-system-menu-item
  max-width: 400px


</style>
