<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-dialog
    v-bind="attrs"
    v-on="listeners"
    :maximized="$q.screen.lt.md"
    class="fse-no-consent-dialog"
    persistent
  >
    <q-card style="max-width: 800px">
      <q-toolbar>
        <q-toolbar-title>
          Consensi
        </q-toolbar-title>
        <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
      </q-toolbar>
      <q-card-section>
        <p v-if="consentError">
          {{ consentError }}
        </p>
        <template v-else-if="isObsoleteVersion">
          <p>
            PoichÃ¨ l'informativa Ã¨ stata aggiornata Ã¨ necessario che l'assistito prenda visione della nuova versione del
            documento e comunichi nuovamente il valore del consenso alla consultazione.
          </p>
        </template>
        <template v-else>
          <p> Non Ã¨ possibile accedere al Fascicolo sanitario del paziente.</p>

          <p v-if="hasFse">
            Il paziente non ha fornito il consenso alla consultazione.
            L'assistito desidera cambiare il consenso alla consultazione?
          </p>
          <p v-else>
            Il paziente non ha il Fascicolo Sanitari attivo. L'assistito desidera aprire il fascicolo?
          </p>
        </template>


        <p v-if="isEmergencySystem">Puoi consultare i referti che hai redatto/validato <span v-if="!hidePSS">e il PSS</span></p>
        <p v-else>Puoi consultare i referti che hai redatto/validato</p>
        <csi-buttons class="q-mt-md">
          <template v-if="!consentError">
          <csi-button @click="$emit('consents-activation')">{{goToConsentsText}}</csi-button>
          </template>
          <csi-button @click="$emit('consult-reports')">Consulta i referti</csi-button>
          <csi-button v-close-popup outline>Annulla</csi-button>
        </csi-buttons>

      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import {SYSTEMS_CODE_MAP} from "src/services/global/config";

export default {
  name: "FseNoConsentDialog",
  props: {
    consents: {type: Object, default: null},
    isObsoleteVersion: {type: Boolean, default: false}
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
    hasFse() {
      return this.consents?.consenso_alimentazione
    },
    hasConsent() {
      return this.consents?.consenso_consultazione
    },
    isEmergencySystem() {
      let selectedSystem = this.$store.getters['getSeletedSystem']
      return selectedSystem?.codice === SYSTEMS_CODE_MAP.EMERGENZA
    },
    consentError() {
      return this.consents?.errore
    },
    goToConsentsText(){
      let text=''
      if(this.isObsoleteVersion){
        text = 'Risottometti i consensi'
      }else if(this.hasFse){text = 'Cambia consensi'}
      else{
        text= 'Apri fascicolo'
      }

      return text
    },
    hidePSS(){
      return this.consents?.errore_cf_annullato
    }
  }
}
</script>

<style scoped>

</style>
