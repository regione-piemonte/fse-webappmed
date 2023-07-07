<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-dialog
    v-bind="attrs"
    v-on="listeners"
    :maximized="$q.screen.lt.md"
    class="fse-dichiaration-consent-dialog"
    persistent
  >
    <q-card style="max-width: 800px">
      <q-toolbar>
        <q-toolbar-title>
          Dichiarazione d'accesso alla consultazione clinica del FSE
        </q-toolbar-title>
        <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
      </q-toolbar>
      <q-card-section>
        <p>Dichiaro di accedere al fascicolo dellâassistito â
          <span v-if="patientName">{{ patientName | upperCase | empty }} (CF
          <strong>{{ patientTaxCode }}</strong>)</span>
          <span v-else><strong>{{ patientTaxCode }}</strong></span>
          â in qualitÃ  di operatore sanitario coinvolto effettivamente nel
          processo di cura nel Ruolo <strong>{{ activeRoleDescription | empty }}</strong> e
          Regime <strong>{{ systemName | upperCase | empty }}</strong>.

        </p>
        <p>
          <strong>Accedendo dichiaro, altresÃ¬, che le informazioni che tratterÃ² sono esclusivamente quelle pertinenti al
            processo di cura in atto
            <template v-if="noConsents && isEmergencySystem">
              e che lâaccesso avviene anche in assenza del consenso alla consultazione trattandosi di un caso di
              emergenza sanitaria (come previsto dallâ art. 12, comma 5, d.l. n. 179/2012 e dallâ art. 9 del
              Regolamento) che ammette il trattamento dei dati sulla salute qualora sia ânecessario per tutelare un
              interesse vitale
              dell'interessato o di un'altra persona fisica qualora l'interessato si trovi nell'incapacitÃ  fisica o
              giuridica di prestare il proprio consenso
            </template>
            .
          </strong>
        </p>

        <p v-if="noConsents && isEmergencySystem">
          L'accesso alla partizione del FSE denominata âProfilo sanitario sinteticoâ avviene nel caso in cui l'
          interessato si trova in una condizione di imminente pericolo di vita e non Ã¨ in grado di manifestare il
          proprio consenso, solo per il tempo strettamente necessario ad assicurare le indispensabili cure
          all'interessato e, in ogni caso, fino a quando lo stesso non sia di nuovo in grado di esprime la propria
          volontÃ  al riguardo.
        </p>
        <div class="text-h6 q-mb-sm">Informativa</div>
        <p>
          Per la normativa vigente in materia di FSE, privacy e sicurezza, La informiamo che i Suoi dati e ogni accesso
          alle informazioni contenute
          nel FSE Ã¨ registrato in apposita sezione a disposizione dellâassistito, che puÃ² prenderne visione in qualunque
          momento accedendo al proprio FSE per via telematica e/o attraverso servizio di notiï¬ca. Se la dichiarazione Ã¨
          corretta, puÃ² cliccare su dichiaro altrimenti puÃ² procedere alla loro modifica
        </p>


        <csi-buttons class="q-mt-md">
          <csi-button @click="$emit('on-confirm')">Confermo dichiarazione</csi-button>
          <csi-button v-close-popup outline>Annulla</csi-button>
        </csi-buttons>

      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>

import {SYSTEMS_CODE_MAP} from "src/services/global/config";

export default {
  name: "FseDichiarationConsentDialog",
  props: {
    patient: {type: Object, default: null},
    consents: {type: Object, default: null}
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
    patientName() {
      let name = this.patient?.nome
      let surname = this.patient?.cognome

      return name && surname ? `${name} ${surname}` : null
    },
    patientTaxCode() {
      return this.patient?.codice_fiscale
    },
    activeRoleDescription() {
      let user = this.$store.getters['getUser'];
      return user?.ruolo?.descrizione ?? ''


    },
    activeSystem() {
      return this.$store.getters['getSeletedSystem'];
    },
    systemName() {
      return this.activeSystem?.descrizione
    },
    isEmergencySystem() {
      return this.activeSystem?.codice === SYSTEMS_CODE_MAP.EMERGENZA
    },
    noConsents() {
      return !this.consents?.consenso_consultazione
    }
  }
}
</script>

<style scoped>

</style>
