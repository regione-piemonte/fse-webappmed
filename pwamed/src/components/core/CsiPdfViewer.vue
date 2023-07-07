<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="csi-policy full-width">
    <div class="relative-position">
      <template v-if="useIframe">
        <iframe
          :class="iframeClasses"
          :src="fileSrc"
          :style="iframeStyles"
          class="csi-policy__frame "
          frameborder="0"
          @load="stopLoading"
        >
        </iframe>
      </template>

      <template v-else>
        <div
          :class="iframeClasses"
          :style="iframeStyles"
          class="csi-policy__frame scroll q-px-md"
        >
          <slot/>
        </div>
      </template>

      <!-- LOADING -->
      <!-- --------------------------------------------------------------------------------------------------------- -->
      <csi-inner-loading :showing="isLoading" class="bg-transparent"/>
    </div>

    <!-- DOWNLOAD PDF -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->
    <div v-if="download" class="q-pa-sm row justify-end">
      <a :href="src" class="csi-link " target="_blank" @click.prevent="onDownloadFile">
        <q-icon class="q-pr-xs" name="file_download"/>
        Scarica
      </a>
    </div>
  </div>
</template>

<script>
import {saveAs} from 'file-saver'
import {isEmpty} from "src/services/utils";

export default {
  name: "CsiPdfViewer",
  props: {
    src: {type: String, required: false, default: ""},
    download: {type: Boolean, required: false, default: false},
    toolbar: {type: Boolean, required: false, default: true},
    defaultZoom: {type: Boolean, required: false, default: true},
    srcPdf: {type: String, required: false, default: ""},
    fileName: {type: String, required: false, default: "documento"},
    iframeClasses: {type: [Array, Object], required: false, default: null},
    iframeStyles: {type: Object, required: false, default: () => ({})}
  },
  data() {
    return {
      isLoading: false
    };
  },
  computed: {
    useIframe() {
      return !this.$slots.default;
    },
    fileSrc() {
      if(isEmpty(this.src)) return  null
      let url = this.src + '#toolbar='
      url += this.toolbar ? '1' : '0'
      // if (this.defaultZoom)
      //   url += '&zoom=95'

      return url
    }
  },
  created() {
  },
  methods: {
    stopLoading() {
      this.isLoading = false;
    },
    onDownloadFile() {
      saveAs(this.src, this.fileName)
    }
  }
};
</script>

<style lang="sass">
.csi-policy__frame
  background-color: $grey-3
  width: 100%
  height: 50vh
  border: 1px solid $grey-5
  border-radius: 4px
</style>
