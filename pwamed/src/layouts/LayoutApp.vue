<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-layout class="cod-layout-app" view="hHh lpr fff">
    <!-- HEADER -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <csi-layout-header menu @click-menu="toggleMenu">

      <template #right>
        <div :class="$q.screen.lt.md ? 'q-gutter-x-sm' : 'q-gutter-x-md'" class="row justify-end">
          <q-btn
            v-if="selectedSystem"
            class="csi-sources-button"
            dense flat icon="img:icone/ico-fonti-collegate.svg" no-caps round target="_blank" title="fonti collegate"
            type="a" href="https://www.salutepiemonte.it/servizi/fonti-fascicolo-sanitario-elettronico"
          >
            <q-tooltip anchor="top middle">Fonti collegate</q-tooltip>
          </q-btn>
          <csi-help-button/>
          <template v-if="user">
            <csi-layout-header-profile-button
              :name="user.nome"
              :surname="user.cognome"
              :tax-code="user.codice_fiscale"
            />
          </template>
        </div>
      </template>


      <!-- TOOLBAR -->
      <!-- --------------------------------------------------------------------------------------------------------- -->
      <template #after>
        <csi-layout-app-toolbar>
          Fascicolo Sanitario Elettronico
        </csi-layout-app-toolbar>

      </template>
    </csi-layout-header>

    <!-- PAGINE -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <q-page-container>
      <router-view/>
    </q-page-container>

    <!-- FOOTER -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <csi-layout-footer/>


  </q-layout>
</template>

<script>

import CsiLayoutHeader from "components/core/CsiLayoutHeader";
import CsiHelpButton from "components/core/CsiHelpButton";
import CsiLayoutHeaderProfileButton from "components/core/CsiLayoutHeaderProfileButton";
import CsiLayoutAppToolbar from "components/core/CsiLayoutAppToolbar";
import CsiLayoutFooter from "components/core/CsiLayoutFooter";


export default {
  name: "LayoutApp",
  components: {

    CsiLayoutFooter,
    CsiLayoutAppToolbar,
    CsiLayoutHeaderProfileButton,
    CsiHelpButton,
    CsiLayoutHeader
  },
  data() {
    return {
      isMenuVisible: false,
    };
  },
  computed: {
    user() {
      return this.$store.getters["getUser"];
    },
    selectedSystem(){
      return this.$store.getters["getSeletedSystem"];
    }
  },
  async created() {
  },
  methods: {
    toggleMenu() {
      this.isMenuVisible = !this.isMenuVisible;
    }
  }
};
</script>

<style lang="sass">
.csi-menu-list__logo
  width: 100%
  max-width: 250px
  height: auto

.csi-notification-list__body > .csi-notification-list-item:not(:last-of-type)
  border-bottom: 1px solid rgba(0, 0, 0, .12)

.csi-notification-list-empty
  text-align: center
  padding: map-get($space-lg, 'y') map-get($space-lg, 'x')
  color: $csi-text-faded-color

.csi-notification-list-item-contacts-activation
  background-color: $blue-2
  padding: map-get($space-md, 'y') map-get($space-md, 'x')
</style>
