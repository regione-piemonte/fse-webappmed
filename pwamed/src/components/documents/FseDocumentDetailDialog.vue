<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-dialog
    v-bind="attrs"
    v-on="listeners"
    :maximized="$q.screen.lt.md"
    class="fse-document-detail-dialog">
    <q-card style="min-width: 80%">
      <q-toolbar class="q-mb-sm q-px-lg">
        <q-toolbar-title>
          Dettaglio referto #{{ documentId }}
        </q-toolbar-title>
        <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
      </q-toolbar>
      <template v-if="isLoading">
        <fse-document-detail-skeleton />
      </template>
      <template v-else-if="document">
        <q-card-section class="no-padding">
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

                <template #iconright v-if="signIcon">
                  <q-icon  :name="signIcon"  size="md"/>
                </template>

              </csi-card-item-primary>
            </template>

            <template #secondary>


              <div class="q-pa-lg row items-start q-col-gutter-md">

                <!-- DATA EMISSIONE -->
                <q-item class="col-12 col-md">
                  <q-item-section>
                    <q-item-label caption class="text-black">Emesso il</q-item-label>
                    <q-item-label class="text-bold">{{ issueDate | date | empty }}</q-item-label>
                  </q-item-section>
                </q-item>

                <!-- MEDICO -->
                <q-item class="col-12 col-md-6">
                  <q-item-section>
                    <q-item-label caption class="text-black">Medici</q-item-label>
                    <q-item-label class="text-bold" v-for="(doctor, index) in doctors" :key="index">
                      {{ doctor| empty }}</q-item-label>
                  </q-item-section>
                </q-item>


                <!-- AZIENDA SANITARIA -->
                <q-item class="col-12 col-md-6">
                  <q-item-section>
                    <q-item-label caption class="text-black">Azienda</q-item-label>
                    <q-item-label class="text-bold">{{ aslName | empty }}</q-item-label>
                  </q-item-section>
                </q-item>

                <!-- STRUTTURA -->
                <q-item class="col-12 col-md-6">
                  <q-item-section>
                    <q-item-label caption class="text-black">Struttura</q-item-label>
                    <q-item-label class="text-bold">{{ structureName | empty }}</q-item-label>
                  </q-item-section>
                </q-item>


                <!-- UNITA OPERATIVA -->
                <q-item class="col-12 col-md-6">
                  <q-item-section>
                    <q-item-label caption class="text-black">UnitÃ  operativa</q-item-label>
                    <q-item-label class="text-bold">{{ opUnitName | empty }}</q-item-label>
                  </q-item-section>
                </q-item>

                <!-- ASSETTO ORGANIZZATIVO -->
                <q-item class="col-12 col-md-6">
                  <q-item-section>
                    <q-item-label caption class="text-black">Assetto organizzativo</q-item-label>
                    <q-item-label class="text-bold">{{ organizationalStructure | empty }}</q-item-label>
                  </q-item-section>
                </q-item>
                <template v-if="mediationDate">
                  <!-- DOCUMENTO MEDIATO -->
                  <q-item class="col-12 col-md-6" >
                    <q-item-section>
                      <q-item-label caption class="text-black">Data mediazione</q-item-label>
                      <q-item-label class="text-bold">{{mediationDate | date | empty}}</q-item-label>
                    </q-item-section>
                  </q-item>

                  <!-- DOCUMENTO MEDIATO -->
                  <q-item class="col-12 col-md-6">
                    <q-item-section>
                      <q-item-label caption class="text-black">Autore mediazione</q-item-label>
                      <q-item-label class="text-bold">{{mediationAuthor  | empty}}</q-item-label>
                    </q-item-section>
                  </q-item>
                </template>


                <template v-if="!associated">
                  <fse-document-associated-info :document-metadata="metadata" :is-er="isEr" :nosological="nosological"
                                                @on-search-associated="onSearchAssociated"/>
                </template>

              </div>

            </template>

            <template #actions>
              <div class="q-pa-md">
                <fse-document-actions detail :document="document"/>
              </div>

            </template>
          </csi-card-item>
        </q-card-section>
      </template>
      <template v-else>
        <csi-banner type="warning" class="q-ma-lg">
          Non Ã¨ stato possibile recuperare il referto.
        </csi-banner>
      </template>


      <!-- ELENCO DOCUMENTI ASSOCIATI -->
      <template v-if="isAssociatedListDialogVisible">
        <fse-document-associated-list-dialog
          v-model="isAssociatedListDialogVisible"
          :document-cl="documentCl"
          :document-id="documentId"
          :operation-type="associatedOperationType"
        />
      </template>


      <template v-if="healthServices && healthServices.length>0">
        <div  class="q-mt-sm q-mx-lg q-mb-xl">
          <q-list bordered separator>
            <q-item class="q-pb-sm">
              <q-item-section>
                <q-item-label class="text-h6">{{ typeCode === "97500-3" ? "Motivazione certificati verde Covid" :  "Prestazioni" }}</q-item-label>
              </q-item-section>
            </q-item>


            <q-item v-for="(service, index) in healthServices" :key="index">
              <q-item-section>
                <q-item-label>{{service.descrizione | empty}} <span v-if="service.data_prestazione">&nbsp; ({{service.data_prestazione | date('DD/MM/YYYY')}})</span></q-item-label>
              </q-item-section>
            </q-item>
          </q-list>

        </div>




      </template>
    </q-card>
  </q-dialog>
</template>

<script>
import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import FseDocumentItemTypeIcon from "components/documents/FseDocumentItemTypeIcon";
import {
  DOCUMENT_CATEGORY_MAP,
  DOCUMENT_DOCTOR_TYPES_LABEL,
  DOCUMENT_SIGN_CODE_MAP_ICON
} from "src/services/documents/config";
import {getDocumentDoctorReferent} from "src/services/documents/business-logic";
import {getDocumentDetail, getDocumentHealthServices} from "src/services/api";
import {apiErrorNotify, DEFAULT_FORMAT_DATE} from "src/services/utils";
import FseDocumentDetailSkeleton from "components/documents/FseDocumentDetailSkeleton";
import FseDocumentAssociatedInfo from "components/documents/FseDocumentAssociatedInfo";
import FseDocumentAssociatedListDialog from "components/documents/FseDocumentAssociatedListDialog";
import FseDocumentActions from "components/documents/FseDocumentActions";
import {date} from "quasar";
let {formatDate} = date
export default {
  name: "FseDocumentDetailDialog",
  components: {
    FseDocumentActions,
    FseDocumentAssociatedListDialog,
    FseDocumentAssociatedInfo,
    FseDocumentDetailSkeleton, FseDocumentItemTypeIcon, CsiCardItemPrimary, CsiCardItem},
  props: {
    documentId: {type: [Number,String], required: true, default:null},
    documentCl:{type: String, required: true, default:null},
    documentCategory:{type: String, required: true, default:"FSE"},
    associated:  {type: Boolean, required: false, default: false},
    typeCode:{type: String, required: true, default:null},
    typeName:{type: String, required: true, default:null},
  },
  data() {
    return {
      isLoading: false,
      document: null,
      associatedOperationType:null,
      isAssociatedListDialogVisible:false,
      healthServices: null,
      healthServiceColumns:[
        {
          name: 'name',
          required: true,
          label: 'Prestazione',
          align: 'left',
          field: row => row.descrizione,
          format: val => `${val}`,
          sortable: true
        },
        {
          name: 'date',
          required: true,
          label: 'Data prestazione',
          align: 'left',
          field: row => row.data_prestazione,
          format: val => `${val ? formatDate(val, 'DD/MM/YYYY') : '-'}`,
          sortable: true
        },
      ]

    }
  },
  computed: {
    patient() {
      return this.$store.getters['getActivePatient']
    },
    patientTaxCode() {
      return this.patient?.codice_fiscale
    },
    attrs() {
      const {...attrs} = this.$attrs;
      return attrs;
    },
    listeners() {
      const {...listeners} = this.$listeners;
      return listeners;
    },
    metadata(){
      return this.document?.metadati_documento
    },

    issueDate() {
      return this.metadata?.data_validazione;
    },
    doctors(){
      let doctors = this.metadata?.medici ?? []
      doctors = doctors.map(doctor => {
        let lastName = doctor.cognome ?? "";
        let firstName = doctor.nome ?? "";
        let type = doctor.tipo_medico ? DOCUMENT_DOCTOR_TYPES_LABEL[doctor.tipo_medico] : null
        let fullName =  [firstName, lastName]
          .map(el => el.trim())
          .filter(el => !!el)
          .join(" ");

        if(type){
          fullName = `${fullName} (${type})`
        }
        return fullName
      })
      return doctors
    },
    doctorReferent() {
      return getDocumentDoctorReferent(this.metadata);
    },
    structureName() {
      return this.metadata?.descrizione_struttura;
    },
    aslName() {
      return this.metadata?.descrizione_azienda;
    },
    opUnitName() {
      return this.metadata?.descrizione_unita_operativa;
    },
    organizationalStructure() {
      return this.metadata?.descrizione_assetto_organizzativo
    },
    mediationDate(){
      return this.metadata?.data_smediazione
    },
    mediationAuthor(){
      return this.metadata?.autore_smediazione
    },
    nreList() {
      return this.metadata?.nre ?? [];
    },
    hasNre() {
      return this.nreList.length > 0;
    },
    nosological() {
      return this.document?.numero_nosologico;
    },
    isEr() {
      return this.document?.numero_passaggio_ps;
    },
    sign() {
      return this.metadata?.coccarda;
    },
    signIcon(){
      return this.sign ? `img:icone/documenti/icon-${DOCUMENT_SIGN_CODE_MAP_ICON[this.sign]}.svg` : null
    }
  },
  async created() {
    if(!this.document){
      this.isLoading = true


      let params = {
        codice_componente_locale : this.documentCl,
        categoria: this.documentCategory,
      }

      let documentPromise = getDocumentDetail(this.patientTaxCode, this.documentId, {params})
      let healthServicePromise = null
      if(this.documentCategory === DOCUMENT_CATEGORY_MAP.FSE){
        let params = {
          codice_componente_locale : this.documentCl,
        }
        healthServicePromise = getDocumentHealthServices(this.patientTaxCode, this.documentId, {params})
      }

      try{
        let {data: document} = await documentPromise
        this.document = document ?? {}
      }catch (error) {
        let message = "Non Ã¨ stato possibile recuperare il documento."
        apiErrorNotify({error, message})
      }
      if(healthServicePromise){
        try{
          let response = await healthServicePromise
          this.healthServices = response.data
        }catch (error) {
          let message = "Non Ã¨ stato possibile recuperare la lista delle prestazioni."
          apiErrorNotify({error, message})
        }
      }

      // this.healthServices = [{
      //   id: 1,
      //   codice: 'codie',
      //   descrizione: 'prestazione 1',
      //   data_prestazione: '2022-10-04',
      //   branca:{
      //     codice: 'branca1',
      //     descrizone: 'branca 1'
      //   }
      // },
      //   {
      //     id: 2,
      //     codice: 'codie',
      //     descrizione: 'prestazione 2',
      //     data_prestazione: '2022-10-03',
      //     branca:{
      //       codice: 'branca2',
      //       descrizone: 'branca 2'
      //     }
      //   }
      // ]

      this.isLoading = false
    }
  },
  methods:{
    async onSearchAssociated(code) {
      this.associatedOperationType = code
      this.isAssociatedListDialogVisible = true;

    },
  }
}
</script>

<style scoped>

</style>
