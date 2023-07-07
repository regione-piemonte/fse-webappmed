<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div class="fit fse-exemption-item " >
    <q-card
      class="fit"
    >

      <q-card-section class="no-padding fit column bg-white">

        <csi-card-item on-side>
          <template #primary>
            <csi-card-item-primary  class="relative-position" inline >

              <template #icon>
                <q-icon :name="icon" size="lg" />
              </template>

              <template #text>
                <div class="text-bold">
                  {{exemptionName}}
                </div>

              </template>
            </csi-card-item-primary>
          </template>

          <template #secondary>
            <div class="q-px-sm q-py-md full-height column justify-between">

              <div>
                <q-list>
                  <!-- DATA EMISSIONE -->
                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">Emesso il</q-item-label>
                      <q-item-label class="text-bold">{{ issueDate | date | empty }}</q-item-label>
                    </q-item-section>
                  </q-item>


                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">Scadenza il </q-item-label>
                      <q-item-label class="text-bold">{{ deadline | date | empty }}</q-item-label>
                    </q-item-section>
                  </q-item>


                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">Diagnosi</q-item-label>
                      <q-item-label class="text-bold">
                       {{diagnosis}}
                      </q-item-label>
                    </q-item-section>
                  </q-item>

                  <q-item>
                    <q-item-section>
                      <q-item-label caption class="text-black">Emesso da</q-item-label>
                      <q-item-label class="text-bold">
                        {{issuer}}
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
              </div>
            </div>

          </template>



        </csi-card-item>
      </q-card-section>

    </q-card>
  </div>

</template>

<script>

import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import {getIconMap} from "src/services/exemptions/business-logic";

export default {
  name: "FseExemptionItem",
  components: {CsiCardItemPrimary, CsiCardItem},
  props:{
    exemption:{type:Object, default:null}
  },
  computed:{
    code(){
      return this.exemption?.codice_esenzione?.codice
    },
    icon(){
      let icon = getIconMap(this.code)
      return icon ? `img:icone/esenzioni/${icon}.svg` : ''
    },
    exemptionName(){
      return `${this.code} - ${this.exemption?.codice_esenzione?.descrizione}`
    },
    issuer(){
      return this.exemption?.ente_emittente?.descrizione
    },
    issueDate(){
      return this.exemption?.data_emissione
    },
    deadline(){
      return this.exemption?.data_scadenza
    },
    diagnosis(){
      return this.exemption?.diagnosi?.descrizione
    }
  }
}
</script>

<style scoped>

</style>
