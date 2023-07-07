<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div id="q-app">
    <router-view />
  </div>
</template>
<script>
import AOS from "aos";
import "aos/dist/aos.css";

export default {
  name: "App",
  components: {
  },
  created() {
    AOS.init({
      once: true
    });
  }
};
</script>
