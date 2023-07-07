<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="page-roles-list" padding>

    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading"  />
    </template>
    <template v-else-if="!qrCode">
      <csi-banner type="negative">
        QRCode non valido
      </csi-banner>
    </template>

    <template v-else-if="!document">
      <fse-profile-choice :confirming="isLoadingDocument" @on-confirm="showDocument"/>
    </template>

    <template v-else>
      <csi-pdf-viewer :iframe-styles="{height:'100vh'}" :src="document" file-name="documento"  />
    </template>

  </csi-page>
</template>

<script>
import {apiErrorNotify, convertToBlobUrl, isEmpty} from "src/services/utils";
import CsiRadioButton from "components/core/CsiRadioButton";
import CsiScrollHorizontal from "components/core/CsiScrollHorizontal";
import {getDoctorSystems, getUserPlacements, getUserRoles, showDocumentFromQRCode} from "src/services/api";
import FseProfileChoice from "components/FseProfileChoice";
import CsiPdfViewer from "components/core/CsiPdfViewer";

export default {
  name: "PageQrCodeHome",
  components: {CsiPdfViewer, FseProfileChoice},
  data() {
    return {
      isLoading:null,
      selectedRole: null,
      selectedPlacement: null,
      selectedPlacementIndex: null,
      isLoadingPlacements: false,
      isLoadingSystems:false,
      isLoadingDocument:false,
      rolesList: [],
      placementsList: [],
      systemSelected: null,
      document:null,
      selectedSystem: null,
      qrCode: null
    }
  },
  computed: {
    user() {
      return this.$store.getters['getUser']
    },

  },
  async created() {
    let {tokenQRCode} = this.$route.query

    if(!tokenQRCode){
      return
    }

    this.isLoading=true
    this.qrCode=tokenQRCode


    this.isLoading=false
  },
  methods: {
    isEmpty,
    async showDocument() {
      this.document=null
      this.isLoadingDocument= true
      let params = {
        qrcode: this.qrCode
      }
      try{
        let response  = await showDocumentFromQRCode({params})
        this.document = convertToBlobUrl(response.data)
      }catch (error) {
        let message = "Non Ã¨ stato possibile recuperare il documento"
        apiErrorNotify({error, message})
      }


      this.isLoadingDocument= false
    },

  }
}
</script>

<style lang="sass">


</style>
