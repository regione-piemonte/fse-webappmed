<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="page-report-image-detail">
    <q-card flat>

      <q-card-section>

        <object id="htmlViewer"
                :credentials="credential"
                :accessionNumber="accessionNr"
                align="top"
                data="whtml/index.html"
                keyImagesOnly="true"
                serviceurl="https://tst-isan.ruparpiemonte.it/o3-dpacs-wado-jb1/"
                style="height: 561px;
                width: 100%;">

          <param name="allowScriptAccess" value="sameDomain">

        </object>

      </q-card-section>
    </q-card>
  </div>

</template>

<script>
export default {
  name: "PageReportImageDetail",
  data() {
    return {
      accessionNr: null,
      credential: "QWRtaW5pc3RyYXRvcjoyMWI3MDJjNDc4ZTE1YjM4OTFiZjQxNDJiZTA1MTI4NzdmNzdkZjYw"
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    }
  },
  created() {
    let {accessionNr} = this.$route.params
    this.accessionNr = accessionNr

  }
}
</script>

<style scoped>

</style>
