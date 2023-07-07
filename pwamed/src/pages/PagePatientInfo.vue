<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page class="page-patient-info">
    <csi-page-title :back="HOME" class="q-mb-lg">Scheda Paziente {{ patientTaxCode }}</csi-page-title>
    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading" block/>
    </template>

    <div v-else class="row items-stretch">
      <!--  CARD INFORMAZIONI PAZIENTE  -->
      <!-- -----------------------------------------------------------------------------------------------------------  -->
      <div v-if="isPiedmontPatient" class="col-lg-2 col-12">
        <q-card class="fit page-patient-info__card-info" flat>
          <q-card-section>
            <q-item class="q-py-md q-px-none " dense>
              <q-item-section side top>
                <q-icon :name="patientIcon" size="md"/>
              </q-item-section>
              <q-item-section>
                <q-item-label class="text-h6"><strong>{{ fullName }}</strong></q-item-label>
                <q-item-label caption class="text-black"> {{ gender === 'F' ? 'Nata' : 'Nato' }} il:
                  {{ birthDate | date('DD/MM/YYYY') }} ({{ age }} anni)
                </q-item-label>
                <!--                <q-item-label class="q-mt-xs">-->
                <!--                  <a class="csi-link" href="#" @click.prevent="showContacts=true">Contatti</a>-->
                <!--                </q-item-label>-->
              </q-item-section>

            </q-item>

            <template v-if="doctor">
              <div class="q-mt-lg q-mb-md text-body2">Medico curante</div>

              <q-item v-if="doctor" class="q-px-none" clickable @click="onShowDoctorDetail">
                <q-item-section side top>
                  <q-icon :name="doctorIcon" size="md"/>
                </q-item-section>
                <q-item-section>
                  <q-item-label class="text-h6"><strong>{{ doctorFullname | empty }}</strong></q-item-label>
                  <q-item-label caption class="text-black">Dal 19/01/2020</q-item-label>
                  <q-item-label caption class="text-black q-pt-md csi-wrap-word"  v-if="doctorEmail">
                    Email:  <strong>{{doctorEmail}}</strong>
                  </q-item-label>
                </q-item-section>
              </q-item>
            </template>

          </q-card-section>
        </q-card>
      </div>


      <!--  PAGINE  -->
      <!-- -----------------------------------------------------------------------------------------------------------  -->
      <div class="col-lg col-12">
        <q-tabs v-model="tab"
                active-color="primary"
                align="left"
                indicator-color="primary"
                no-caps
                outside-arrows

        >


          <q-route-tab v-for="route in routeList" v-show="route.visibile" :key="route.name" :label="route.label" :name="route.name"
                       :to="route.url" exact  @click="setRouteAudit(route.auditCode)"/>

        </q-tabs>

        <transition
          enter-active-class="animated fadeIn csi-animation-duration--300"
          leave-active-class="animated fadeOut csi-animation-duration--300"
          mode="out-in">
          <!--          <keep-alive >-->
          <router-view></router-view>
          <!--         </keep-alive>-->
        </transition>

      </div>
    </div>

    <fse-patient-doctor-dialog v-model="showDoctorDetail" :doctor="doctorInfo" :patient="patient"/>

  </csi-page>
</template>

<script>
import {
  DOCUMENTS,
  EPISODES,
  HOME,
  PRESCRIPTIONS,
  VACCINATIONS,
  PROFILE_SYNTHETIC,
  EXEMPTIONS,
  SCREENING,
  SANITARY_NOTEBOOK_MENU,
  PATIENT, PATIENT_INFO
} from "src/router/routes";

import {date} from "quasar";
import FsePatientDoctorDialog from "components/patient/FsePatientDoctorDialog";
import {getMmgInfo, getPatientList, setAudit} from "src/services/api";
import {apiErrorNotify, startCase} from "src/services/utils";
import {getGender} from "src/services/global/business-logic";
import {AUDIT_CODES, SYSTEMS_CODE_MAP} from "src/services/global/config";
import PageDocumentsList from "pages/documents/PageDocumentsList";
import PageEpisodes from "pages/episodes/PageEpisodes";
import PagePrescriptions from "pages/prescriptions/PagePrescriptions";
import PageVaccinations from "pages/vaccinations/PageVaccinations";
import PagePatientProfileSynthetic from "pages/PagePatientProfileSynthetic";
import PageExemptions from "pages/exemptions/PageExemptions";
import PageScreening from "pages/screening/PageScreening";
import PageTacSanitaryNotebookMenu from "pages/sanitary-notebook/PageTacSanitaryNotebookMenu";

const {getDateDiff, addToDate, extractDate} = date;


export default {
  name: "PagePatientInfo",
  components: {FsePatientDoctorDialog},
  data() {
    return {
      DOCUMENTS,
      EPISODES,
      PRESCRIPTIONS,
      VACCINATIONS,
      PROFILE_SYNTHETIC,
      EXEMPTIONS,
      SCREENING,
      SANITARY_NOTEBOOK_MENU,
      HOME,
      isLoading: false,
      showContacts: false,
      showDoctorDetail: false,
      tab: null,
      hasNoDoctor: false,
      keepAlive: [
        PageDocumentsList.name,
        PageEpisodes.name,
        PagePrescriptions.name,
        PageVaccinations.name,
        PagePatientProfileSynthetic.name,
        PageExemptions.name,
        PageScreening.name,
        PageTacSanitaryNotebookMenu.name
      ],

    }
  },
  computed: {
    routeList() {
      return [
        {
          name: DOCUMENTS.name,
          url: DOCUMENTS,
          label: this.DocumentListLabel,
          auditCode: this.hasFse ? "WA_ACC_DOC_CLINICI" : "WA_ACC_MIEI_REFERTI",
          visibile: true
        },
        {
          name: EPISODES.name,
          url: EPISODES,
          label: "Episodi di ricovero e di PS",
          auditCode: "WA_ACC_EPISODI",
          visibile: this.hasFse && (this.isPiedmontPatient && this.hasConsents)
        },
        {
          name: PRESCRIPTIONS.name,
          url: PRESCRIPTIONS,
          label: "Ricette",
          auditCode: "WA_ACC_RICETTE",
          visibile: this.hasFse && this.hasConsents
        },
        {
          name: VACCINATIONS.name,
          url: VACCINATIONS,
          label: "Vaccinazioni",
          auditCode: "WA_ACC_VACCINAZIONI",
          visibile: this.hasFse && this.hasConsents
        },
        {
          name: PROFILE_SYNTHETIC.name,
          url: PROFILE_SYNTHETIC,
          label: "Profilo sanitari sintetico",
          auditCode: "WA_ACC_PSS",
          visibile: !this.hidePSS && this.hasFse && (this.hasConsents || this.isEmergencySystem)
        },
        {
          name: EXEMPTIONS.name,
          url: EXEMPTIONS,
          label: "Esenzioni",
          auditCode: "WA_ACC_ESENZIONI",
          visibile: this.hasFse && this.hasConsents
        },
        {
          name: SCREENING.name,
          url: SCREENING,
          label: "Screening",
          auditCode: "WA_ACC_SCREENING",
          visibile: this.hasFse && (this.hasConsents && this.isPiedmontPatient)
        },
        {
          name: SANITARY_NOTEBOOK_MENU.name,
          url: SANITARY_NOTEBOOK_MENU,
          label: "Dati del taccuino personale",
          auditCode: "WA_ACC_TACCUINO",
          visibile: this.hasFse && (this.hasConsents && this.isPiedmontPatient)
        },
      ]


    },
    patient() {
      return this.$store.getters['getActivePatient']
    },
    patientTaxCode() {
      return this.patient?.codice_fiscale
    },
    patientIcon() {
      let patientIcon = this.gender === 'F' ? 'avatar-donna' : 'avatar-uomo'
      return `img:icone/${patientIcon}.svg`
    },
    doctor() {
      return this.doctorInfo?.medico
    },
    doctorFullname() {
      if (this.doctor) {
        return `Dott. ${startCase(this.doctor.nome)}  ${startCase(this.doctor.cognome)}`
      }
      return ''
    },
    doctorEmail(){
      return this.doctorInfo?.email
    },
    doctorIcon() {
      let doctorGender = getGender(this.doctor?.codiceFiscale)
      let doctorIcon = doctorGender === 'F' ? 'avatar-medico-donna' : 'avatar-medico-uomo'
      return `img:icone/${doctorIcon}.svg`
    },
    fullName() {
      return `${this.patient?.nome} ${this.patient?.cognome}`
    },
    gender() {
      return this.patient?.sesso
    },
    birthDate() {
      return this.patient?.data_nascita
    },
    age() {
      const TODAY = new Date()

      // Controlliamo l'etÃ  tenendo conto che il compleanno dell'utente di questo anno sia giÃ  passato
      // altrimenti riduciamo di 1 l'etÃ  dal momento che non ha ancora compiuto gli anni
      let age = getDateDiff(TODAY, this.birthDate, "years");
      let max = addToDate(this.birthDate, {year: age});
      let diff = getDateDiff(TODAY, max, "days");
      if (diff < 0)
        age -= 1;

      return age
    },
    doctorInfo() {
      return this.$store.getters['getPatientDoctor']
    },
    isPiedmontPatient() {
      let isExtraRegion = this.$store.getters['isPatientExtraRegion']
      return !isExtraRegion
    },
    isEmergencySystem() {
      let selectedSystem = this.$store.getters['getSeletedSystem']
      return selectedSystem?.codice === SYSTEMS_CODE_MAP.EMERGENZA
    },
    hasConsents() {
      return this.patient?.consensi?.consenso_consultazione
    },
    categoriesList() {
      return this.$store.getters['getDocumentCategoriesList']
    },
    hasFse() {
      return this.patient?.consensi?.consenso_alimentazione
    },
    hidePSS(){
      return this.patient?.consensi?.errore_cf_annullato
    },
    DocumentListLabel() {
      if (this.hasFse) {
        if (this.hasConsents) {
          return 'Documenti clinici e autocontribuiti'
        } else {
          return 'Documenti clinici'
        }
      } else {
        return 'I tuoi referti'
      }
    }
  },
  async created() {
    console.log("----PagePatientInfo.vue-----")

    if (!this.patientTaxCode) {
      this.$router.replace(HOME)
      return
    }
    this.isLoading = true

    let params = {
      id_irec: this.patient?.id_irec,
      id_aura: this.patient?.id_aura
    }
    if (!this.doctorInfo && this.isPiedmontPatient) {
      try {
        let {data: doctor} = await getMmgInfo(this.patientTaxCode, {params})

        if (doctor?.ha_medico === "SI") {
          this.$store.dispatch('setPatientDoctor', {doctor})

        } else if (doctor?.ha_medico === "NO")
          this.hasNoDoctor = true

      } catch (error) {
        let message = "non Ã¨ stato possibile recuperare i dati del medico curante"
        apiErrorNotify({error, message})
      }
    }

    if (!this.categoriesList?.length) {
      await this.$store.dispatch('setCategoriesList')

    }

    await  this.handleNavigation(this.$route)
   // await this.$router.push(DOCUMENTS)
    this.isLoading = false


    // this.handleNavigation(this.$route)
  },
  methods: {
    async setRouteAudit(code){
      try{
        let payload = {
          cittadino_id: this.patientTaxCode,
          codice_audit: code
        }

        let response = await setAudit(payload)
      }catch (e) {
        console.error(e)
      }

    },
    async handleNavigation(to, from, next) {
      // Quando la route target Ã¨ il dettaglio paziente vuol dire che si tratta del primo accesso
      // In questo caso gestiamo dove redirigere l'utente
      console.log("_______to.name PATIENT_INFO", to.name)
      if (to.name === PATIENT_INFO.name) {
        // Di default effettuiamo il redirect alla lista di documenti
        let route = DOCUMENTS
        next ? next(route) : this.$router.push(route)
        await this.setRouteAudit('WA_ACC_DOC_CLINICI')
      } else if (next) {
        next()
      }
    },
   async onShowDoctorDetail(){
      this.showDoctorDetail=true
      try {
        let payload = {
          cittadino_id: this.patientTaxCode,
          codice_audit: AUDIT_CODES.MMG_DETAIL
        }
        let response = await setAudit(payload)
      } catch (error) {
        console.error(error)
      }
    }
  }
}
</script>

<style lang="sass">
.page-patient-info
  &__card-info
    background-color: rgba($accent, 0.15)

</style>
