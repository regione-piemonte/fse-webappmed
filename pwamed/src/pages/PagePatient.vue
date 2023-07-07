<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div v-if="patientTaxCode">
    <keep-alive :keep-alive="keepAlive" :exclude="keepNotAlive">
      <router-view />
    </keep-alive>

  </div>

</template>

<script>

import {HOME} from "src/router/routes";
import PageDocumentsList from "pages/documents/PageDocumentsList";
import PageEpisodes from "pages/episodes/PageEpisodes";
import PagePrescriptions from "pages/prescriptions/PagePrescriptions";
import PageVaccinations from "pages/vaccinations/PageVaccinations";
import PagePatientProfileSynthetic from "pages/PagePatientProfileSynthetic";
import PageExemptions from "pages/exemptions/PageExemptions";
import PageScreening from "pages/screening/PageScreening";
import PageTacSanitaryNotebookMenu from "pages/sanitary-notebook/PageTacSanitaryNotebookMenu";
import PageDocument from "pages/documents/PageDocument";
import PageScreeningDetail from "pages/screening/PageScreeningDetail";
import PageTacDetections from "pages/sanitary-notebook/PageTacDetections";

export default {
  name: "PagePatientInfo",
  components: {},
  data(){
    return{
      keepAlive:[
        PageDocumentsList.name,
        PageEpisodes.name,
        PagePrescriptions.name,
        PageVaccinations.name,
        PagePatientProfileSynthetic.name,
        PageExemptions.name,
        PageScreening.name,
        PageTacSanitaryNotebookMenu.name
      ],
      keepNotAlive:[
        PageDocument.name,
        PageScreeningDetail.name,
        PageTacDetections.name
      ]
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },

  },
  created() {
    if(!this.patientTaxCode){
      this.$router.replace(HOME)
    }

  }
}
</script>

<style lang="sass">


</style>
