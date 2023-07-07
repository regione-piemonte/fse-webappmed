<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="page-report-images" >

    <csi-banner class="q-mb-lg" type="info">
      Qualora lâAzienda Sanitaria e/o lâUnitÃ  Operativa indicate nel referto differiscano rispetto a quelle indicate
      nellâelenco episodi, fa fede lâindicazione riportata nel referto.
    </csi-banner>


    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading" block/>
    </template>
    <template v-else>
      <div class="text-h1 text-bold q-pb-md">Immagini</div>

      <q-card class="full-width">
        <q-card-section>
          <q-table :columns="imagesTableColumns"
                   :data="accessionNumbers"
                   :grid="$q.screen.lt.sm"
                   class="report-image-table"
                   dense
                   flat

                   hide-bottom
          >
            <template v-slot:body-cell-action="props">
              <q-td :props="props" >
                <a class="csi-link" href="#" @click.prevent="showImage(props.row)"> Visualizza immagine</a>
              </q-td>
            </template>

            <template v-slot:item="props">
              <div class="q-pa-xs col-xs-12 col-sm-6 col-md-4">
                <q-card bordered flat>
                  <q-card-section class="q-pa-sm">
                    <q-item class="no-padding">
                      <q-item-section>
                        <q-item-label class="text-caption">Accension Number</q-item-label>
                        <q-item-label class="text-bold">{{ props.row }}</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item class="no-padding" v-if="canShowImage">
                      <q-item-section >
                        <q-item-label class="text-caption">Viewer a bassa risoluzione</q-item-label>
                        <q-item-label><a class="csi-link" href="#" @click.prevent="showImage(props.row)"> Visualizza
                          immagine</a></q-item-label>

                      </q-item-section>
                    </q-item>
                  </q-card-section>
                </q-card>
              </div>
            </template>


          </q-table>


        </q-card-section>

        <q-card-section>
          <q-item>
            <q-item-section>
              <q-item-label caption class="text-black">Stato pacchetto</q-item-label>

              <q-item-label class="text-bold">{{imageStatusLabel | empty}}</q-item-label>
              <q-item-label v-if="isImageBookable" class="q-pt-sm">
                <q-btn :loading="isBookingImage" color="primary" @click="onBookImage">Prenota il pacchetto immagini
                </q-btn>
              </q-item-label>
              <q-item-label v-else-if="canShowImage" class="q-pt-sm">
                <q-btn :loading="isDownloadingImage" color="primary" @click="onDownloadImage">Scarica pacchetto
                </q-btn>
              </q-item-label>
            </q-item-section>
          </q-item>

        </q-card-section>

        <q-card-section>
          <q-btn :loading="isUpdating" color="primary" flat @click="getImageStatus">Aggiorna</q-btn>
        </q-card-section>
      </q-card>

      <q-dialog v-model="showImageInfoDialog" persistent>
        <q-card style="max-width: 800px">
          <q-toolbar>
            <q-toolbar-title>
              Uso previsto e informazioni sulla sicurezza
            </q-toolbar-title>
            <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
          </q-toolbar>
          <q-card-section>
            <p>Le immagini Dicom visualizzate <strong>non sono destinate ad uso diagnostico</strong>.<br>
              Il presente viewer puÃ² essere utilizzato solo come software a scopo di consultazione, ricerca o
              insegnamento. <br>
              <strong>Non puÃ² essere destinato ad uso diagnostico</strong> ed utilizzato a fini clinici e/o per la cura
              del paziente. Non Ã¨ un dispositivo medico con marchio CE.</p>

            <csi-buttons class="q-mt-md">
              <csi-button @click="goToImageDetail" :loading="isSettingAudit">Accetta</csi-button>
              <csi-button v-close-popup color="negative" outline>Rifiuta</csi-button>
            </csi-buttons>

          </q-card-section>
        </q-card>

      </q-dialog>
    </template>


  </div>
</template>

<script>
import {IMAGE_DETAIL} from "src/router/routes";
import {createImageBooking, getDocumentImageDownloadUrl, getDocumentImageStatus, setAudit} from "src/services/api";
import {apiErrorNotify, notifySuccess} from "src/services/utils";
import {
  DOCUMENT_CATEGORY_MAP,
  IMAGE_STATUS_CODE_LIST_BOOKABLE,
  IMAGE_STATUS_CODE_LIST_DOWNLOADABLE,
  IMAGE_STATUS_LABEL_MAP
} from "src/services/documents/config";
import {openURL} from "quasar";
import {AUDIT_CODES} from "src/services/global/config";

export default {
  name: "PageReportImages",
  props:{
    document:{type:Object, default: null}
  },
  data() {
    return {
      isLoading: false,
      id: null,
      imageStatus: null,
      showImageInfoDialog: false,
      selectedImage: null,
      isUpdating: false,
      isBookingImage: false,
      isDownloadingImage:false,
      IMAGE_DETAIL,
      isSettingAudit:false
    }
  },
  computed: {
    patientTaxCode() {
      let patient = this.$store.getters['getActivePatient']
      return patient?.codice_fiscale
    },

    metadata() {
      return this.document?.metadati_documento
    },
    accessionNumbers() {
      return this.document?.metadati_documento?.accession_number ?? []
    },
    isImageBookable() {
      return IMAGE_STATUS_CODE_LIST_BOOKABLE.includes(this.imageStatus)
    },
    canShowImage(){
      return IMAGE_STATUS_CODE_LIST_DOWNLOADABLE.includes(this.imageStatus)
    },
    imageStatusLabel(){
      return IMAGE_STATUS_LABEL_MAP[this.imageStatus] ?? ''
    },
    cl() {
      return this.document?.codice_cl;
    },
    isPersonal() {
      return this.category === DOCUMENT_CATEGORY_MAP.PERSONAL;
    },
    category() {
      return this.document?.categoria ?? DOCUMENT_CATEGORY_MAP.FSE
    },
    imagesTableColumns(){
      let list = [
        {
          name: 'accessionNr',
          required: true,
          label: 'Accension Number',
          align: 'left',
          field: row => row
        }
      ]

      if(this.canShowImage){
        list.push({
          name: 'action',
          required: true,
          label: 'Viewer a bassa risoluzione',
          align: 'left',
          field: ''
        })
      }

      return list


    }
  },
  async created() {

    let {id, document} = this.$route.params

    this.isLoading = true


    this.id = id

    await this.getImageStatus()

    this.isLoading = false
  },
  methods: {
    async goToImageDetail() {
        this.isSettingAudit = true
      try{
        let payload = {
          cittadino_id: this.patientTaxCode,
          codice_audit: AUDIT_CODES.IMAGE_SHOWN
        }

        let response = await setAudit(payload)
      }catch (e) {

      }
      this.isSettingAudit = false

      let route = {
        name: IMAGE_DETAIL.name,
       params:{
         accessionNr: this.selectedImage
       }
      }

      this.$router.push(route)
    },

    showImage(accensionNr) {

      this.selectedImage = accensionNr
      this.showImageInfoDialog = true
    },
    async getImageStatus() {
      this.isUpdating = true
      let params = {
        codice_fiscale: this.patientTaxCode,
        id_documento_ilec: this.id,
        cod_cl: this.cl,
        cod_documento_dipartimentale: this.metadata?.codice_documento_dipartimentale,
        archivio_documento_ilec: 'FSE',

      }
      try {
        let {data: status} = await getDocumentImageStatus({params})
        this.imageStatus = status?.stato_richiesta
      } catch (error) {
        let message = "Non Ã¨ stato possibile verificare lo stato del pacchetto"
        apiErrorNotify({error, message})
      }

      this.isUpdating = false
    },

    async onBookImage() {
      this.isBookingImage = true

      let payload = {
        cit_id: this.patientTaxCode,
        id_referto: this.metadata?.codice_documento_dipartimentale,
        periodo_conservazione: '30',
        pin: '12345',
        accession_number: this.accessionNumbers,
        cod_cl: this.cl
      }
      let imageBookingPromise = createImageBooking(payload)


      let auditPayload = {
        cittadino_id: this.patientTaxCode,
        codice_audit: AUDIT_CODES.IMAGE_BOOKING
      }

      let auditPromise =  setAudit(auditPayload)
      try {

        let response = await imageBookingPromise
         await this.getImageStatus()

        notifySuccess("Pacchetto immagini prenotati con successo")
      } catch (error) {
        let message = "non Ã¨ stato possibile prenotare il pacchetto immagini"
        apiErrorNotify({error, message})
      }

      try{
        let response = await auditPromise
      }catch (e) {
      }
      this.isBookingImage = false
    },
    async onDownloadImage() {
      this.isDownloadingImage = true;
      let auditPayload = {
        cittadino_id: this.patientTaxCode,
        codice_audit: AUDIT_CODES.IMAGE_DOWNLOAD
      }
      let auditPromise = setAudit(auditPayload)


      try {
        let payload = {
          cf_assistito: this.patientTaxCode,
          id_documento_ilec: this.id,
          cod_cl: this.cl
        }

        let url = await getDocumentImageDownloadUrl(payload)

        openURL(url);
      } catch (error) {
        let message = "Non Ã¨ stato possibile scaricare il pacchetto";
        apiErrorNotify({error, message});
      }

      try{
        let response = await auditPromise
      }catch (e) {

      }
      this.showDownloadImageDialog = false;
      this.isDownloadingImage = false;
    },


  }
}
</script>

<style lang="sass">
.report-image-table
  max-width: 600px

  thead tr th
    font-weight: normal !important
    border: none

  tbody tr
    height: 32px !important

    td
      font-weight: bold
      font-size: 14px
      border: none

</style>
