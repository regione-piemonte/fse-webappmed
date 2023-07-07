<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-toolbar class="csi-layout-app-toolbar">
    <slot name="left" />

    <q-toolbar-title
      class="csi-layout-app-toolbar__title"
      shrink
      @click.native="onClick"
    >
      <slot />
    </q-toolbar-title>

    <q-space />

    <slot name="right" />
  </q-toolbar>
</template>

<script>
import {APP, HOME} from "src/router/routes";

export default {
  name: "CsiLayoutAppToolbar",
  methods: {
    onClick() {
       this.$router.push(HOME)
      // let eventName = "click-title";
      // let url = window.location.origin  + '#/';
      //
      // if (eventName in this.$listeners) return this.$emit(eventName, url);
      //
      // window.location.assign(url);
    }
  }
};
</script>

<style lang="sass">
.csi-layout-app-toolbar
  background-color: $secondary
  color: #FFF

.csi-layout-app-toolbar__title
  cursor: pointer
  padding-left: 56px
</style>
