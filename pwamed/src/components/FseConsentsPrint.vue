<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div id="fse-consents-print">
    <div
      class="fse-consents-print--container q-pa-xl row justify-center"
    >
      <div
        class="column q-col-gutter-y-lg items-stretch fit print-page-container"
        style="width:auto; max-width:600px"
      >
        <div class="col-auto">
          <h1 class="text-h1">Fascicolo Sanitario Elettronico</h1>
          <hr/>
        </div>

        <div class="col q-mt-lg">
          <q-card class="print-consents-fse--card " flat>

            <q-card-section class="no-padding">
              In data <strong>{{ now | date }}</strong> alle ore <strong>{{ now | time }}</strong>, 
              l'operatore . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
              <br> 
              ha effettuato una variazione dei consensi per l'assistito <strong>{{ patientFullName }}</strong>
              codice fiscale <strong>{{ patientTaxCode }}</strong>,
              nato a <strong>{{ patientBirthPlace }}</strong>  il <strong>{{ patientBirthDate | date }}</strong>.
            </q-card-section>

            <q-card-section class="no-padding">
               <p>&nbsp;</p>
               <p>Per minori, indicare il soggetto richiedente: (nome, cognome) . . . . . . . . . . . . . . . . . . . . . . . . . . 
               <br>con codice fiscale . . . . . . . . . . . . . . . . . . . . . . . . . . . . .&nbsp;</p>
               <p>&nbsp;</p>
             </q-card-section>  

            <q-card-section class="no-padding">
              <p>In particolare:</p>
                <ol>
                <li>&Egrave; stata presa visione dell&rsquo;informativa del fascicolo sanitario elettronico.</li>
                <li>&Egrave; stato dato il consenso alla consultazione del fascicolo da parte di operatori socio-sanitari/sanitari/amministrativi.</li>
                </ol>
              <p>Si precisa che le operazioni effettuate sono tracciate nel sistema del FSE e disponibili al cittadino nella sezione del &ldquo;Profilo&rdquo; del portale SalutePiemonte. Se sono state abilitate le notifiche dal cittadino, tali accessi saranno anche inviati secondo le preferenze impostate.</p>
              <p>&nbsp;</p>
              <p>Firma dell'operatore&nbsp;</p>
              <p>&nbsp;. . . . . . . . . . . . . . . . . . . . . . . . . . . . .&nbsp;</p>
              <p>Firma dell'assistito o di chi ne fa le veci</p>
              <p>&nbsp;. . . . . . . . . . . . . . . . . . . . . . . . . . . . .&nbsp;</p>
            </q-card-section>
          </q-card>
        </div>

        <div class="col-auto">
          <hr/>
          <div class="logo-container q-my-md row">
            <div class="col-6 text-left">
              <img
                alt="Regione Piemonte"
                class="logo-regione q-pr-sm"
                src="/../dmawa/images/logo-regione-piemonte-positivo.svg"
              />
            </div>

            <div class="col-6 text-right">
              <img
                alt="Logo CSI"
                class="logo-csi q-pl-sm"
                src="/../dmawa/images/logo-csi-positivo.svg"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FseConsentsPrint",
  props: {
    isNewFse: {type: Boolean, default: true}
  },
  data() {
    return {
      now: new Date()
    }
  },
  computed: {
    user() {
      return this.$store.getters['getUser']
    },
    taxCode() {
      return this.user?.codice_fiscale
    },
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    patientFullName() {
      let patient = this.$store.getters['getActivePatient']
      let fullName = patient?.nome ?? ""
      fullName += " " 
      fullName += patient?.cognome ?? ""
      return fullName
    },
    patientBirthPlace() {
      let patient = this.$store.getters['getActivePatient']
      let birthPlace = patient?.comune_nascita?.descrizione ?? "-----"
      return birthPlace
    },
    patientBirthDate() {
      let patient = this.$store.getters['getActivePatient']
      let birthDate = patient?.data_nascita ?? "-----"
      return birthDate
    }
  }
}
</script>

<style lang="sass">
.fse-consents-print--container
  display: none
.print-page
  background-color: #ffffff !important
  .fse-consents-print--container
    display: block !important
    width: 100vw
    margin: 0 auto
    min-height: 100vh
    max-width: 800px
    .print-page-container
      min-height: 90vh
    hr
      background-color: $csi-primary
    .print-consents-fse--card
      border-color: $csi-primary
      .with-border
        border-right: 1px solid $csi-primary
    .logo-regione
      width: 100%
      max-width: 180px
      height: auto
    .logo-csi
      width: 100%
      max-width: 160px
      height: auto
</style>
