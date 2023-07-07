<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div :class="classes" class="fit fse-document-item " v-on:click.stop="onClickDocument">
    <q-card
      class="fit"
    >
      <!-- VISTA LISTA -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->

      <q-card-section v-if="listView" class="no-padding bg-white">
        <!-- BARRE INFORMATIVE -->
        <!-- ----------------------------------------------------------------------------------------------------------- -->
        <template v-if="isPersonal">
          <csi-card-item-bar type="info">
            Documento autocontribuito
          </csi-card-item-bar>
        </template>

        <template v-if="!isPayed">
          <csi-card-item-bar type="warning">
            Non Ã¨ possibile consultare questo referto per mancato pagamento del ticket
          </csi-card-item-bar>
        </template>

        <template v-if="needMediation">
          <csi-card-item-bar type="warning">
            Il paziente non puÃ² visualizzare questo referto fino a mediazione avvenuta
          </csi-card-item-bar>
        </template>
        <!-- DOCUMENTO -->
        <!-- ----------------------------------------------------------------------------------------------------------- -->


        <div class=" row">
          <div class="row items-start bg-grey-3 q-py-md q-px-md col-12 col-md-3 col-lg-2">
            <div v-if="isSelectable" class="col-auto self-start" :class="{'q-pr-md': $q.screen.lt.md}">
              <q-checkbox ref="selectionCheckbox" v-model="selected" dense size="xs" @input="onSelect"></q-checkbox>
            </div>
            <div class="col row items-center">
              <div :class="[{ 'text-center': $q.screen.gt.xs }, {'q-pr-md': $q.screen.lt.md}]" class="col-auto col-md-12 " >
                <fse-document-item-type-icon :type="typeCode" size="lg"/>
              </div>

              <div :class="{ 'text-center': $q.screen.gt.xs }" class="col-auto col-md-12">
                <div class="text-bold">
                  {{ typeName | empty | caseSentence }}
                </div>
              </div>
            </div>
          </div>

          <div class="row items-start q col-12 col-md q-pa-md ">
            <div class="col-12 col-md row items-start q-col-gutter-md">
              <!-- id documento -->
              <q-item class="col-12  ">
                <q-item-section>
                  <q-item-label caption class="text-black">ID documento</q-item-label>
                  <q-item-label class="text-bold">{{ id | empty }}</q-item-label>
                </q-item-section>
              </q-item>

              <!-- DATA EMISSIONE -->
              <q-item class="col-12  col-md-4">
                <q-item-section>
                  <q-item-label caption class="text-black">Emesso il</q-item-label>
                  <q-item-label class="text-bold">{{ issueDate | date | empty }}</q-item-label>
                </q-item-section>
              </q-item>

              <!-- AZIENDA SANITARIA -->
              <q-item class="col-12 col-md">
                <q-item-section>
                  <q-item-label caption class="text-black">Struttura</q-item-label>
                  <q-item-label class="text-bold">
                    {{ structureName | empty }}

                    <template v-if="aslName">
                  <span class="text-caption text-bold">
                    {{ aslName | empty }}
                  </span>
                    </template>
                  </q-item-label>
                </q-item-section>
              </q-item>

              <!-- MEDICO -->
              <template v-if="doctorReferent && isPersonal">
                <q-item   class="col-12 col-md">
                  <q-item-section>
                    <q-item-label caption class="text-black">Medico</q-item-label>
                    <q-item-label class="text-bold">
                      {{ doctorReferent | empty }}
                    </q-item-label>
                  </q-item-section>
                </q-item>
              </template>
              <div v-if="!associated && !episodes" class="row col-12">

                <fse-document-associated-info :document-metadata="metaData" :is-er="isEr" :nosological="nosological"
                                              @on-search-associated="onSearchAssociated"/>
              </div>

<!--              <div v-if="!associated && hasNre || hasPsAssociated || hasHospitalizationAssociated" class="row col-12">-->


<!--                &lt;!&ndash; RICETTA ELETTRONICA (NRE) &ndash;&gt;-->
<!--                <div v-if="hasNre" class="col-12 col-md">-->
<!--                  <div class="q-mt-md">-->
<!--                    Documento relativo a ricetta elettronica nÂ° {{ nreLabel }}-->
<!--                    <template v-if="hasNreAssociated">-->
<!--                      <a-->
<!--                        class="csi-link on-right"-->
<!--                        href="#"-->
<!--                        @click.prevent="onSearchAssociated(ASSOCIATION_OPERATION_TYPE_MAP.NRE)"-->
<!--                      >-->
<!--                        Mostra tutti-->
<!--                      </a>-->
<!--                    </template>-->
<!--                  </div>-->
<!--                </div>-->

<!--                &lt;!&ndash; PASSAGGIO IN PS &ndash;&gt;-->
<!--                <div v-if="hasPsAssociated" class="col-12 col-md">-->
<!--                  <div class="q-mt-md">-->
<!--                    Documento di passaggio in Pronto soccorso-->
<!--                    <template>-->
<!--                      <a-->
<!--                        class="csi-link on-right"-->
<!--                        href="#"-->
<!--                        @click.prevent="onSearchAssociated(ASSOCIATION_OPERATION_TYPE_MAP.EPISODE_PS)"-->
<!--                      >-->
<!--                        Mostra tutti-->
<!--                      </a>-->
<!--                    </template>-->
<!--                  </div>-->
<!--                </div>-->

<!--                &lt;!&ndash; RICOVERO &ndash;&gt;-->
<!--                <div v-if="hasHospitalizationAssociated" class="col-12 col-md">-->
<!--                  <div class="q-mt-md">-->
<!--                    Documento di ricovero-->
<!--                    <template>-->
<!--                      <a-->
<!--                        class="csi-link on-right"-->
<!--                        href="#"-->
<!--                        @click.prevent="onSearchAssociated(ASSOCIATION_OPERATION_TYPE_MAP.EPISODE_SDO)"-->
<!--                      >-->
<!--                        Mostra tutti-->
<!--                      </a>-->
<!--                    </template>-->
<!--                  </div>-->
<!--                </div>-->
<!--              </div>-->
            </div>

            <div class="col-12 col-md-3">
              <div class="q-my-md">
                <a v-if="!isNoPiedmontPatient" class="csi-link self-center" href="#" @click.prevent="showDocumentDetail">
                  <span class=" text-bold">Maggiori informazioni</span>
                </a>

              </div>

              <fse-document-actions  list-view  :document="document"/>
            </div>

          </div>

        </div>


      </q-card-section>

      <!-- VISTA SCHEDA -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->
      <q-card-section v-else class="no-padding fit column bg-white">
        <!-- DOCUMENTO -->
        <!-- ----------------------------------------------------------------------------------------------------------- -->
        <csi-card-item on-side>
          <template #primary>

            <!-- TIPOLOGIA -->
            <csi-card-item-primary :checkbox="isSelectable" :is-selected="selected" class="relative-position" inline
                                   @on-selected="onSelect">

              <template #icon>
                <fse-document-item-type-icon :type="typeCode" size="lg"/>
              </template>

              <template #text>
                <div class="text-bold">
                  {{ typeName | empty | caseSentence }}
                </div>

              </template>

              <template #iconright>
                <template v-if="showLockIcon">
                  <q-icon v-if="isDocumentMediated"  name="lock_open" size="sm"/>
                  <q-icon v-if="needMediation || !isPayed" class="col-auto q-pl-md"  name="lock" size="sm"/>
                </template>

              </template>


            </csi-card-item-primary>
          </template>

          <template #secondary>

            <div class="full-height column justify-between">
              <!-- BARRE INFORMATIVE -->
              <!-- ----------------------------------------------------------------------------------------------------------- -->

              <template v-if="isPersonal">
                <div class="col-auto">
                  <csi-card-item-bar type="info">
                    Documento autocontribuito
                  </csi-card-item-bar>
                </div>
              </template>
              <template v-if="!isPayed">
                <csi-card-item-bar type="warning">
                  Non Ã¨ possibile consultare questo referto per mancato pagamento del ticket
                </csi-card-item-bar>
              </template>
              <template v-if="needMediation">
                <div class="col-auto">
                  <csi-card-item-bar type="warning">
                    Il paziente non puÃ² visualizzare questo referto fino a mediazione avvenuta
                  </csi-card-item-bar>
                </div>
              </template>

              <div class="q-px-sm q-py-md ">
                <q-list>

                  <!-- id documento -->
                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">ID documento</q-item-label>
                      <q-item-label class="text-bold">{{ id | empty }}</q-item-label>
                    </q-item-section>
                  </q-item>


                  <!-- DATA EMISSIONE -->
                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">Emesso il</q-item-label>
                      <q-item-label class="text-bold">{{ issueDate | date | empty }}</q-item-label>
                    </q-item-section>
                  </q-item>

                  <!-- AZIENDA SANITARIA -->
                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">Struttura</q-item-label>
                      <q-item-label class="text-bold">
                        {{ structureName }}

                        <template v-if="aslName">
                  <span class="text-caption text-bold">
                    {{ aslName }}
                  </span>
                        </template>
                      </q-item-label>
                    </q-item-section>
                  </q-item>

                  <!-- MEDICO -->
                  <q-item v-if="doctorReferent && isPersonal">
                    <q-item-section>
                      <q-item-label caption class="text-black">Medico</q-item-label>
                      <q-item-label class="text-bold">
                        {{ doctorReferent }}
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
              </div>


              <!-- AZIONI -->
              <!-- ----------------------------------------------------------------------------------------------------- -->

              <div class="q-px-lg q-py-md row q-col-gutter-md">
                <a v-if="!isNoPiedmontPatient" class="csi-link col-12" href="#" @click.prevent.stop="showDocumentDetail">
                  <span class=" text-bold">Maggiori informazioni</span>
                </a>

                <template v-if="!associated && !episodes">

                  <fse-document-associated-info :document-metadata="metaData" :is-er="isEr" :nosological="nosological"
                                                @on-search-associated="onSearchAssociated"/>
                </template>

              </div>
            </div>
          </template>

          <template #actions>
            <fse-document-actions :document="document" class="q-mx-md q-mb-md" @on-mediated="onMediated"/>
          </template>
        </csi-card-item>
      </q-card-section>


      <template v-if="showDetailDialog">
        <fse-document-detail-dialog v-model="showDetailDialog" :associated="associated" :document-category="category"
                                    :document-cl="cl" :document-id="id" :type-code="typeCode" :type-name="typeName"/>
      </template>


      <!-- ELENCO DOCUMENTI ASSOCIATI -->
      <template v-if="isAssociatedListDialogVisible">
        <fse-document-associated-list-dialog
          v-model="isAssociatedListDialogVisible"
          :document-cl="cl"
          :document-id="id"
          :operation-type="associatedOperationType"
        />
      </template>


    </q-card>
  </div>

</template>

<script>

import {
  ASSOCIATION_OPERATION_TYPE_LABEL_MAP,
  ASSOCIATION_OPERATION_TYPE_MAP,
  DOCUMENT_CATEGORY_MAP,
} from "../../services/documents/config";


import FseDocumentItemTypeIcon from "./FseDocumentItemTypeIcon";
import CsiCardItemBar from "components/core/CsiCardItemBar";
import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import FseDocumentDetailDialog from "components/documents/FseDocumentDetailDialog";
import {getDocumentDoctorReferent, isDocumentPayed} from "src/services/documents/business-logic";
import { TRANSCRIPTION} from "src/router/routes";
import {getDocumentAssociatedList, getDocumentMediationInfo, setDocumentMediation} from "src/services/api";
import {apiErrorNotify, notifySuccess} from "src/services/utils";
import FseDocumentAssociatedListDialog from "components/documents/FseDocumentAssociatedListDialog";
import FseDocumentAssociatedInfo from "components/documents/FseDocumentAssociatedInfo";
import FseDocumentActions from "components/documents/FseDocumentActions";


export default {
  name: "FseDocumentItem",
  components: {
    FseDocumentActions,
    FseDocumentAssociatedInfo,
    FseDocumentAssociatedListDialog,
    FseDocumentDetailDialog,
    CsiCardItemPrimary,
    CsiCardItem,
    CsiCardItemBar,
    FseDocumentItemTypeIcon
  },
  props: {
    document: {type: Object, required: false, default: () => null},
    selectable: {type: Boolean, required: false, default: false},
    listView: {type: Boolean, required: false, default: false},
    associated: {type: Boolean, required: false, default: false},
    episodes: {type: Boolean, required: false, default: false},
  },
  data() {
    return {
      ASSOCIATION_OPERATION_TYPE_MAP,
      isBookingImage: false,
      isDownloadingImage: false,
      showMediationDialog: false,
      showDetailDialog: false,
      selected: false,
      associatedList: [],
      associatedListByEpisode: [],
      isLoadingAssociated: false,
      isLoadingAssociatedBEpisode: false,
      isAssociatedListDialogVisible: false,
      isAssociatedListByEpisodeDialogVisible: false,
      associatedOperationType: null,
      isLoadingMediation: false,
      isMediated: false,

    };
  },
  computed: {
    patient() {
      return this.$store.getters['getActivePatient']
    },
    isNoPiedmontPatient() {
      return this.$store.getters['isPatientExtraRegion']
    },
    patientTaxCode() {
      return this.patient?.codice_fiscale
    },
    patientHasConsents() {
      return this.patientConsents?.consenso_consultazione && this.patientConsents?.consenso_alimentazione
    },
    patientConsents() {
      return this.patient?.consensi
    },

    user() {
      return this.$store.getters["getUser"];
    },
    category() {
      return this.document?.categoria
    },
    metaData() {
      return this.document?.metadati_documento
    },
    mediationInfo(){
      return this.document?.info_mediazione
    },
    needMediation() {
      return  !this.isMediated && this.mediationInfo?.mediabile
    },
    isDocumentMediated() {
      return this.metaData?.data_smediazione || this.isMediated
    },
    showLockIcon(){
      return this.isDocumentMediated || !this.isPayed || this.needMediation
    },
    id() {
      return  this.document?.id_documento_ilec;
    },
    cl() {
      return this.document?.codice_cl;
    },
    structureName() {
      return this.metaData?.descrizione_struttura;
    },
    aslName() {
      return this.metaData?.descrizione_azienda;
    },
    typeCode() {
      return this.metaData?.codice_tipo_documento;
    },
    typeName() {
      return this.metaData?.descrizione_tipo_documento;
    },
    issueDate() {
      return this.metaData?.data_validazione;
    },
    nreList() {
      return this.metaData?.nre ?? [];
    },
    hasNre() {
      return this.nreList.length > 0;
    },
    nreLabel() {
      return this.nreList.join(" ");
    },
    isPersonal() {
      return this.category === DOCUMENT_CATEGORY_MAP.PERSONAL;
    },
    hasHospitalizationAssociated() {
      return this.metaData?.flag_episodi_collegati && this.document?.numero_nosologico;
    },
    nosological() {
      return this.document?.numero_nosologico;
    },
    isEr() {
      return this.document?.passaggio_ps;
    },
    hasPsAssociated() {
      return this.metaData?.flag_episodi_collegati && this.document?.passaggio_ps;
    },
    hasNreAssociated() {
      return this.metaData?.flag_associazioni_nre;
    },
    classes() {
      let out = [];
      if (this.isPersonal) out.push("fse-document-item--personal");
      if (this.isSelectable) out.push("selectable")
      if (this.selected) out.push("fse-document-item--selected")
      return out;
    },

    doctorReferent() {
      return getDocumentDoctorReferent(this.document);
    },

    transcriptionUrl() {
      let route = {
        name: TRANSCRIPTION.name,
        params: {
          document: this.document,
          id: this.id
        }
      }

      return route
    },

    isPayed() {
      let status = this.metaData?.pagato_ticket
      return isDocumentPayed(status)
    },

    accessionNumbers() {
      return this.document?.accession_number;
    },
    isSelectable(){
     return this.selectable && this.isPayed
    }

  },
  created() {

  },
  methods: {
    async getMediationInfo(){
      try{
       let {data} = await  getDocumentMediationInfo(this.patientTaxCode, this.id)
        this.mediationInfo = data
      }catch (e) {
        console.log(e)
      }
    },
    onClickDocument() {
      if (!this.isSelectable) return

      this.onSelect(!this.selected)
    },
    onSelect(val) {
      this.selected = val
      this.$emit('on-select', val, this.document)
    },
    showDocumentDetail() {

      this.showDetailDialog = true
    },

    async onSearchAssociated(code) {
      this.associatedOperationType = code
      this.isAssociatedListDialogVisible = true;

    },
    onMediated(){
      this.isMediated =true
    }

  }
};
</script>

<style lang="sass">
.fse-document-item.selectable
  border-radius: 4px
  color: #000
  cursor: pointer
  border: 2px solid transparent

  &:hover
    border: 2px solid rgba($csi-primary-light, 0.6)
    box-shadow: 0px 0px 5px rgba($csi-primary-light, 0.5)
    -webkit-box-shadow: 0px 0px 5px rgba($csi-primary-light, 0.5)
    -moz-box-shadow: 0px 0px 5px rgba($csi-primary-light, 0.5)

  &.fse-document-item--selected
    margin: 0
    border: 2px solid $csi-primary-light
    box-shadow: 0px 0px 10px $csi-primary-light
    -webkit-box-shadow: 0px 0px 10px $csi-primary-light
    -moz-box-shadow: 0px 0px 10px $csi-primary-light

.fse-document-item__status-icon
  position: absolute
  left: 16px
  bottom: 8px


//&:hover
//  border: 2px solid rgba($csi-primary-light, 0.6)
//  box-shadow: 0px 0px 5px  rgba($csi-primary-light, 0.5)
//  -webkit-box-shadow:   0px 0px 5px  rgba($csi-primary-light, 0.5)
//  -moz-box-shadow:   0px 0px 5px  rgba($csi-primary-light, 0.5)
//&.active, &.home:hover


</style>
