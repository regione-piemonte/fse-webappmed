<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card :flat="isDisabled" :bordered="isDisabled" v-if="menuItem" :class="[{'csi-menu-item--selected' : isSelected}]" class="csi-menu-item fit">
    <q-item
      :active="isSelected"
      class="q-py-md fit"
      clickable
      :disable="isDisabled"
      @click="onSelected"
    >
      <q-item-section side>
        <q-icon :name="icon" :size="iconSize"/>
      </q-item-section>
      <q-item-section>
        <q-item-label class="text-bold text-uppercase">{{ itemName | empty }}</q-item-label>
      </q-item-section>
    </q-item>
  </q-card>
</template>

<script>
import {onMapMenuCode} from "src/services/sanitary-notebook/business-logic";

export default {
  name: "FseTacMenuItem",
  props: {
    menuItem: {type: Object, default: null},
    isSelected: {type: Boolean, default: false},
    detections: {type: Boolean, default: false},
  },
  computed: {
    itemName() {
      // return this.detections ? this.menuItem?.descrizione_estesa : this.menuItem?.descrizione
      return this.menuItem?.name
    },
    code() {
      let code = this.menuItem?.id
      return onMapMenuCode(code)
    },
    icon() {
      return `img:icone/taccuino/${this.code}.svg`
    },
    iconSize() {
      return this.$q.screen.gt.sm ? 'md' : 'sm'
    },
    isDisabled() {
      return !this.menuItem?.attivo
    }

  },
  methods: {
    onSelected() {
      this.$emit('on-select', this.menuItem, this.code)
    }
  }
}
</script>

<style lang="sass">
</style>
