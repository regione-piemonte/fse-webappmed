<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div>
    <q-skeleton
      type="text"
      width="140px"
      height="40px"
      class="bg-grey-6"
    />

    <div class="lt-sm">
      <q-skeleton type="text" width="100%" class="q-mt-md bg-grey-6" />
      <q-skeleton type="text" width="100%" class="bg-grey-6" />
    </div>
    <div class="gt-xs">
      <q-skeleton type="text" width="300px" class="q-mt-md bg-grey-6" />
      <q-skeleton type="text" width="200px" class="bg-grey-6" />
    </div>



    <div v-for="i in 2" :key="i">
      <q-skeleton type="text" width="80%" class="q-mt-lg" />
      <q-skeleton type="text" width="83%" />
      <q-skeleton type="text" width="74%" />
      <q-skeleton type="text" width="45%" />
    </div>
  </div>
</template>

<script>
export default {
  name: "FsePolicySkeleton",
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {},
  created() {},
  methods: {}
};
</script>

<style lang="sass"></style>
