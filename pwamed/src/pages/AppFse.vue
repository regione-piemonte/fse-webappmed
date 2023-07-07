<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding>
    <template v-if="isLoading">
      <csi-inner-loading :showing="true" block/>
    </template>
    <template v-else-if="noToken">
      <csi-banner class="q-my-lg" type="warning">
        Nessun token trovato
      </csi-banner>
    </template>
    <template v-else-if="!user && !qrCode">
      <csi-banner v-if="errorMessage" class="q-my-lg" type="negative">
        {{ errorMessage }}
      </csi-banner>
      <csi-banner v-else class="q-my-lg" type="warning">
        Utente non trovato
      </csi-banner>
    </template>
    <template v-else>
      <router-view/>
    </template>


  </csi-page>
</template>

<script>

import {SessionStorage} from 'quasar'
import {TOKEN, USER} from "src/services/mocks";

import {IS_DEV, USER_SESSION_KEY} from "src/services/global/config";
import {getUser} from "src/services/api";
import {
  APP,
  CONSENTS_ACTIVATION,
  DOCUMENTS,
  ECWDME_HOME,
  HOME,
  LCCE_HOME,
  PATIENT_INFO,
  QR_CODE_HOME
} from "src/router/routes";
import {apiErrorNotify} from "src/services/utils";


export default {
  name: "AppFse",
  components: {},
  data() {
    return {
      isLoading: false,
      noToken: false,
      token: null,
      qrCode: null,
      errorMessage: ''
    };
  },
  computed: {
    user() {
      return this.$store.getters["getUser"];
    },

  },
  async created() {

    console.debug("AppFse.vue:", {route: {...this.$route}});
    this.isLoading = true;

    let {token, tokenQRCode} = this.$route?.query;


    //CARICAMENTO DATI IN AMBIENTE DI SVILUPPO:
    // DOBBIAMO AGGIUNGERE MANUALMENTE IL TOKEN
    if (IS_DEV) {
      //  Da utilizzare nel caso si vuole lavorare sulle pagine LCCE (CHIAMATE DI CONTESTO) - Simula accesso senza shibboleth
      const IS_LCCE = false

      if (!IS_LCCE) {
        token = TOKEN;
      }
      //  cerco prima l'utente nel sessionstorage
      let user = SessionStorage.getItem(USER_SESSION_KEY)
      console.debug("AppFSE.vue saved user", user);
      if(!user)
        user = USER
      if (user)
        this.$store.dispatch("setUser", {user});

    }
    console.debug("AppFse.vue setToken:", {token});
    this.token = token

    //GESTIONE URL DA QRCODE
    // tokenQRCode = QRCODE
    console.debug("AppFse.vue setQRCode:", {tokenQRCode});
    if (tokenQRCode) {
      //  Cancelliamo il token LCCE precedentemente salvato
      await this.$store.dispatch('resetLCCE')
      this.qrCode = tokenQRCode
      let route = {
        name: QR_CODE_HOME.name,
        query: {
          tokenQRCode: tokenQRCode
        }
      }
      if (this.$route.name !== QR_CODE_HOME.name)
        this.$router.replace(route)


      this.isLoading = false;
      return

    } else if (!this.token) {
      // Se non c'Ã¨ il TOKEN cerco l'utente nel sessionstorage
      let user = SessionStorage.getItem(USER_SESSION_KEY)
      console.debug("AppFse.vue saved user", user);
      if (user)
        this.$store.dispatch("setUser", {user});
      else {
        this.noToken = true;
        this.isLoading = false;
        return;
      }

    } else {
      //  Cancelliamo il token LCCE precedentemente salvato
      await this.$store.dispatch('resetLCCE')
      let params = {
        token: this.token
      };
      console.debug("AppFse.vue !this.user");
      try {
        this.errorMessage = null
        let {data: user} = await getUser({params});

        this.$store.dispatch("setUser", {user});
        // Salvo dati dell'utente sul sessionStorage
        SessionStorage.set(USER_SESSION_KEY, user)

      } catch (error) {
        this.errorMessage = error?.response?.data?.title
      }
    }
// Reindirizzamento sulla homepage
    if (this.$route.name === APP.name) {
      await this.$router.push(HOME)
    }

    this.isLoading = false;
  },


}
</script>

<style scoped>

</style>
