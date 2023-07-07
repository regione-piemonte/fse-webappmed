<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
 <csi-page padding class="page-report-detail">
   <csi-page-title class="q-mb-lg" >Referto <span v-if="id">n. {{ id }}</span></csi-page-title>

   <router-view :document="document"></router-view>

 </csi-page>

</template>

<script>

export default {
  name: "PageDocument",
  components: {},
  data() {
    return {
      document: null,
      id:null,
    }
  },
  computed: {

  },
  created() {
    console.log('PAGEDOCUMENT.vue')
    let {id, document} = this.$route.params
    this.document = document
    this.id=id !== 'extra-regione' ? id : null

  },
  methods:{

  }
}
</script>

<style scoped>

</style>
