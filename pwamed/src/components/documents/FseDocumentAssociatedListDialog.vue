<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-dialog
    v-bind="attrs"
    v-on="listeners"
    :maximized="$q.screen.lt.md"
    class="fse-associate-list-dialog"
  >
    <q-card :class="classes" class="fse-associate-list-dialog--card">
      <q-toolbar>
        <q-toolbar-title>
          Documenti associati per {{ ASSOCIATION_OPERATION_TYPE_LABEL_MAP[this.operationType] }}
        </q-toolbar-title>

        <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
      </q-toolbar>

      <q-card-section>
        <div :class="$q.screen.gt.sm ? 'items-stretch': 'items-start'" class="row q-col-gutter-md">


          <template v-if="isLoadingAssociated">
            <div v-for="i in 2" :key="i" class="col-12 col-md-6 ">
              <fse-document-item-skeleton/>
            </div>

          </template>
          <template v-else>
            <div v-for="document in orderedDocumentList" :key="'document-association--' + document.id_documento_ilec"
                 class="col-12 col-md-6 ">
              <fse-document-item
                :document="document"
                associated
                class="col-12 col-md-6 "
              />
            </div>
          </template>


        </div>


      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>

import {getDocumentAssociatedList} from "src/services/api";
import {ASSOCIATION_OPERATION_TYPE_LABEL_MAP} from "src/services/documents/config";
import {apiErrorNotify, orderBy} from "src/services/utils";
import FseDocumentItemSkeleton from "components/documents/FseDocumentItemSkeleton";

export default {
  name: "FseDocumentAssociatedListDialog",
  inheritAttrs: false,
  // Nel nostro caso abbiamo:
  // <fse-document-item>
  //    <fse-document-associated-list-by-nre-dialog>
  //        <fse-document-item>
  // Come possiamo notare fse-document-item Ã¨ un componente ricorsivo ed in questi casi siamo costretti ad importarlo
  // dinamicamente
  components: {FseDocumentItemSkeleton, FseDocumentItem: () => import("./FseDocumentItem")},
  props: {
    documentId: {type: [String, Number], required: true, default: null},
    documentCl: {type: String, required: true, default: null},
    operationType: {type: String, required: true, default: null}
  },
  data() {
    return {
      ASSOCIATION_OPERATION_TYPE_LABEL_MAP,
      isLoadingAssociated: false,
      documentList: [],

    };
  },
  computed: {
    attrs() {
      const {...attrs} = this.$attrs;
      return attrs;
    },
    listeners() {
      const {...listeners} = this.$listeners;
      return listeners;
    },
    patientTaxCode() {
      let patient = this.$store.getters['getActivePatient']
      return patient.codice_fiscale
    },
    classes() {
      let result = [];

      if (this.$q.screen.gt.sm) {
        result.push("min-width");
      }

      return result
    },
    orderedDocumentList() {
      const IMPORTANT_CODE_TYPES = ['59258-4', '34105-7']

      let list = this.documentList.map(doc => {
        doc.data_validazione = doc.metadati_documento?.data_validazione
        return doc
      })

      list = orderBy(list, ['data_validazione'], ['desc'])

      for (let i = 0; i < list.length; i++) {
        let doc = list[i]
        if (IMPORTANT_CODE_TYPES.includes(doc.metadati_documento?.codice_tipo_documento)) {
          list.splice(i, 1);
          list.unshift(doc);
        }
      }
      return list
    }

  },
  async created() {

    this.isLoadingAssociated = true
    let params = {
      codice_componente_locale: this.documentCl,
      tipo_correlazione_documento: this.operationType
    };

    try {
      let {data} = await getDocumentAssociatedList(this.patientTaxCode, this.documentId, {
        params
      });
      let documentList = data
      this.documentList = documentList?.filter(doc => doc.id_documento_ilec !== this.documentId);

    } catch (error) {
      let message =
        `Non Ã¨ stato possibile caricare i documenti associati per ${ASSOCIATION_OPERATION_TYPE_LABEL_MAP[this.operationType]}`;
      apiErrorNotify({error, message});
    }
    this.isLoadingAssociated = false
  },
  methods: {}
};
</script>

<style lang="sass">
.fse-associate-list-dialog--card
  max-width: 800px !important

  &.min-width
    width: 750px !important
</style>
