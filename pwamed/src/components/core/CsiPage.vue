<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-page v-bind="attrs" v-on="listeners" class="csi-page q-pb-xl">
    <slot />
  </q-page>
</template>

<script>
export default {
  name: "CsiPage",
  inheritAttrs: false,
  computed: {
    listeners() {
      const { ...listeners } = this.$listeners;
      return listeners;
    },
    attrs() {
      const { ...attrs } = this.$attrs;
      return attrs;
    }
  }
};
</script>

<style lang="sass">
// Impostiamo una larghezza massima alle pagine
@media (min-width: $breakpoint-sm)
  .csi-page
    max-width: $breakpoint-md
    margin: auto


@media (min-width: $breakpoint-md)
  .csi-page
    max-width: $breakpoint-lg
    margin: auto

@media (min-width: $breakpoint-lg)
  .csi-page
    max-width: $breakpoint-xl-min
    margin: auto
</style>
