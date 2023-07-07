<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="row q-col-gutter-md">
    <!-- RICETTA ELETTRONICA (NRE) -->
    <div v-if="hasNre" class="col-12">
      <div>
        Documento relativo a ricetta elettronica nÂ° {{ nreLabel }}
        <template v-if="hasNreAssociated">
          <a
            class="csi-link on-right"
            href="#"
            @click.prevent="onSearchAssociated(ASSOCIATION_OPERATION_TYPE_MAP.NRE)"
          >
            Mostra correlati
          </a>
        </template>
      </div>
    </div>

    <!-- PASSAGGIO IN PS -->
    <div v-if="hasPsAssociated" class="col-12">
      <div>
        Documento di passaggio in Pronto soccorso
        <template>
          <a
            class="csi-link on-right"
            href="#"
            @click.prevent="onSearchAssociated(ASSOCIATION_OPERATION_TYPE_MAP.EPISODE_PS)"
          >
            Mostra correlati
          </a>
        </template>
      </div>
    </div>

    <!-- RICOVERO -->
    <div v-if="hasHospitalizationAssociated">
      <div>
        Documento di ricovero
        <template>
          <a
            class="csi-link on-right"
            href="#"
            @click.prevent="onSearchAssociated(ASSOCIATION_OPERATION_TYPE_MAP.EPISODE_SDO)"
          >
            Mostra correlati
          </a>
        </template>
      </div>
    </div>



  </div>
</template>

<script>
import {ASSOCIATION_OPERATION_TYPE_MAP} from "src/services/documents/config";

export default {
  name: "FseDocumentAssociatedInfo",
  components: {},
  props: {
    documentMetadata: {type: Object, default: null},
    isEr: {type: String, default: ''},
    nosological: {type: String, default: ''},
  },
  data() {
    return {
      ASSOCIATION_OPERATION_TYPE_MAP,

    }
  },
  computed: {
    nreList() {
      return this.documentMetadata?.nre ?? [];
    },
    hasNre() {
      return this.nreList.length > 0;
    },
    nreLabel() {
      return this.nreList.join(" ");
    },
    hasPsAssociated() {
      return this.documentMetadata?.flag_episodi_collegati && this.isEr;
    },
    hasNreAssociated() {
      return this.documentMetadata?.flag_associazioni_nre;
    },
    hasHospitalizationAssociated() {
      return this.documentMetadata?.flag_episodi_collegati && this.nosological;
    },

  },
  methods: {
    onSearchAssociated(code) {
      this.$emit('on-search-associated', code)

    },
  }
}
</script>

<style scoped>

</style>
