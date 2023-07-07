<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-avatar class="csi-layout-header-profile-button" @click="showMenu">
    <div class="csi-layout-header-profile-button__text">
      <slot>{{ avatarText }}</slot>
    </div>
  </q-avatar>
</template>

<script>
import {PROFILE} from "src/router/routes";

export default {
  name: "CsiLayoutHeaderProfileButton",
  props: {
    name: { type: String, required: false, default: "" },
    surname: { type: String, required: false, default: "" },
    taxCode: { type: String, required: false, default: "" },
    iconProfile: { type: String, required: false, default: "person" },
    iconLogout: { type: String, required: false, default: "exit_to_app" }
  },
  data() {
    return {
      isMenuVisible: false,
      PROFILE
    };
  },
  computed: {
    avatarText() {
      let n = this.name ? this.name.charAt(0) : "";
      let c = this.surname ? this.surname.charAt(0) : "";
      let result = `${c}${n}`;
      return result.trim();
    }
  },
  methods: {
    showMenu() {
      this.isMenuVisible = true;
    },
    hideMenu() {
      this.isMenuVisible = false;
    },
    updateMenu(val) {
      this.isMenuVisible = val;
    },
  }
};
</script>

<style lang="sass">
.csi-layout-header-profile-button
  background-color: $accent


.csi-layout-header-profile-button
  font-size: 32px !important

.csi-layout-header-profile-button__text
  text-transform: uppercase
  font-size: 14px
</style>
