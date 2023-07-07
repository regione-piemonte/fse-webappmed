<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-card class="fit fse-screening-item">
    <q-card-section class="no-padding fit column bg-white">

      <csi-card-item on-side>
        <template #primary>
          <!-- TIPOLOGIA -->
          <csi-card-item-primary  class="relative-position" inline>
            <template #icon>
              <q-icon :name="iconName" size="lg"/>
            </template>

            <template #text>
              <div class="text-bold">
                {{ screeningName | empty | caseSentence }}
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

                <!-- ESITO -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black">Esito</q-item-label>
                    <q-item-label class="text-bold">
                      {{ result | empty }}
                    </q-item-label>
                  </q-item-section>
                </q-item>

                <!-- AZIENDA -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black">Azienda</q-item-label>
                    <q-item-label class="text-bold">
                      {{ asl | empty }}
                    </q-item-label>
                  </q-item-section>
                </q-item>


                <!-- UNITA' ORGANIZZATIVA -->
                <q-item>
                  <q-item-section>
                    <q-item-label caption class="text-black">UnitÃ  organizzativa</q-item-label>
                    <q-item-label class="text-bold">
                      {{ opUnit | empty }}
                    </q-item-label>
                  </q-item-section>
                </q-item>
              </q-list>
            </div>

          </div>

        </template>


        <template #actions>
          <div class="q-ma-md">
            <csi-buttons>
              <csi-button @click="gotToDetail" :loading="isSettingAudit">
                Consulta esito
              </csi-button>
            </csi-buttons>

          </div>



        </template>
      </csi-card-item>
    </q-card-section>
  </q-card>
</template>

<script>
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import CsiCardItem from "components/core/CsiCardItem";
import {SCREENING_DETAIL} from "src/router/routes";
import {setAudit} from "src/services/api";
import {AUDIT_CODES} from "src/services/global/config";

export default {
  name: "FseScreeningItem",
  props:{
    screening :{type:Object, default: null},
    screeningType:{type:Object, default: null},
    screeningId:{type:String, default: null}
  },
  data(){
    return{
      isSettingAudit:false
    }
  },
  components: {CsiCardItem, CsiCardItemPrimary},
  computed: {
    screeningExamType(){
      return this.screening?.tipo?.codice
    },
    iconName(){
      let screeningType = this.screeningType?.codice
      return screeningType ? `img:icone/screening/${screeningType}.svg` : '';
    },
    screeningName(){
      return this.screening?.tipo?.descrizione
    },
    issueDate(){
      return this.screening?.data
    },
    result(){
      return this.screening?.esito
    },
    asl(){
      return this.screening?.azienda_sanitaria?.descrizione
    },
    opUnit(){
      return this.screening?.unita_operativa?.descrizione
    },
    patientTaxCode(){
      return this.$store.getters["getPatientTaxCode"]
    }
  },
  methods:{
    async gotToDetail(){
      this.isSettingAudit = true
      // log di audit di visualizzazione dati di dettaglio dello screening
      try{
        let payload = {
          cittadino_id: this.patientTaxCode,
          codice_audit: AUDIT_CODES.SCREENING_DETAIL,
          parametri:[this.screening.tipo]
        }
        let response = await setAudit(payload)
      }catch (e) {

      }
      this.isSettingAudit = false

      let  screeningExamType = this.screening?.tipo?.codice
      let id= screeningExamType + '-' + this.screeningId
      let route = {
        name: SCREENING_DETAIL.name,
        params:{
          id: id,
          screening: this.screening,
          screeningType:this.screeningType
        }
      }

      this.$router.push(route)
    }
  }
}
</script>

<style scoped>

</style>
