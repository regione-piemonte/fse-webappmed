<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div  class="page-transcription">
    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading" block />
    </template>
    <q-card v-else class="full-width">

        <csi-card-item on-side>
          <template #primary>


            <!-- TIPOLOGIA -->
            <csi-card-item-primary class="relative-position q-px-lg" inline>

              <template #icon>
                <fse-document-item-type-icon :type="typeCode" size="lg"/>
              </template>

              <template #text>
                <div class="text-bold">
                  {{ typeName | caseSentence | empty  }}
                </div>

              </template>

            </csi-card-item-primary>
          </template>

          <template #secondary>
            <div class="q-pa-lg row items-start q-col-gutter-md">

              <!-- DATA EMISSIONE -->
              <q-item class="col-12 col-sm-6 col-md">
                <q-item-section>
                  <q-item-label caption class="text-black">Emesso il</q-item-label>
                  <q-item-label class="text-bold">{{ issueDate | date | empty }}</q-item-label>
                </q-item-section>
              </q-item>

              <!-- STRUTTURA -->
              <q-item class="col-12 col-sm-6 col-md">
                <q-item-section>
                  <q-item-label caption class="text-black">Struttura</q-item-label>
                  <q-item-label class="text-bold">{{ structureName | empty }}</q-item-label>
                </q-item-section>
              </q-item>

              <!-- MEDICO -->
              <q-item class="col-12 col-md">
                <q-item-section>
                  <q-item-label caption class="text-black">Medici</q-item-label>
                  <template v-if="doctors && doctors.length>0">
                    <q-item-label class="text-bold" v-for="(doctor, index) in doctors" :key="index">
                      {{ doctor| empty }}</q-item-label>
                  </template>
                  <q-item-label class="text-bold" v-else>-</q-item-label>
                </q-item-section>
              </q-item>

              <q-item  class="col-12 ">
                <q-item-section>
                  <q-item-label caption class="text-black">Trascrizione</q-item-label>
                  <q-item-label class="text-bold">{{transcription |empty}}</q-item-label>
                </q-item-section>
              </q-item>

            </div>

          </template>
        </csi-card-item>

    </q-card>

  </div>
</template>

<script>
import {DOCUMENTS} from "src/router/routes";
import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import FseDocumentItemTypeIcon from "components/documents/FseDocumentItemTypeIcon";
import {getDocumentDoctorReferent} from "src/services/documents/business-logic";
import {downloadDocumentPersonal} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import {DOCUMENT_CATEGORY_MAP, DOCUMENT_DOCTOR_TYPES_LABEL} from "src/services/documents/config";

export default {
name: "PageTranscription",
  components: {FseDocumentItemTypeIcon, CsiCardItemPrimary, CsiCardItem},
  props:{
    document:{type:Object, default: null}
  },
  data() {
    return {
      transcription:null,
      id:null,
      isLoading:false
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    metadata(){
      return this.document?.metadati_documento
    },
    typeCode() {
      return this.metadata?.codice_tipo_documento;
    },
    typeName() {
      return this.metadata?.descrizione_tipo_documento;
    },
    issueDate() {
      return this.metadata?.data_validazione;
    },
    doctors(){
      let doctors = this.metadata?.medici ?? []
      doctors = doctors.map(doctor => {
        return `${doctor.cognome} ${doctor.nome} (${doctor.tipo_medico ? DOCUMENT_DOCTOR_TYPES_LABEL[doctor.tipo_medico] : '-'})`;
      })
      return doctors
    },
    structureName() {
      return this.metadata?.descrizione_struttura;
    },
    cl() {
      return this.document?.codice_cl;
    },
    category() {
      return this.document?.categoria ?? DOCUMENT_CATEGORY_MAP.FSE
    },

  },
  async created() {
    let {id, document} = this.$route.params

    this.isLoading = true

    this.id = id


    try {
      let response  = await downloadDocumentPersonal(this.patientTaxCode, this.id)
      this.transcription = response.data?.trascrizione

    } catch (error) {
      let message = "non Ã¨ stato possibile recuperare la transcrizione."
      apiErrorNotify({error, message})
    }
    this.isLoading=false
  }
}
</script>

<style scoped>

</style>
