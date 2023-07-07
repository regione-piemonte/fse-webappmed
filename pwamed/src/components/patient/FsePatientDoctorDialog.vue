<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-dialog
    v-bind="attrs"
    v-on="listeners"
    class="fse-patient-doctor-dialog"
    :maximized="$q.screen.lt.md"
    >
    <q-card style="min-width: 80%">
      <q-toolbar>
        <q-toolbar-title>
         Medico curante
        </q-toolbar-title>
        <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round/>
      </q-toolbar>
      <q-card-section class="no-padding">
        <csi-card-item on-side>
          <template #primary>


            <!-- TIPOLOGIA -->
            <csi-card-item-primary class="relative-position q-px-lg" inline>

              <template #icon>
                <q-icon :name="doctorIcon" size="lg"/>
              </template>

              <template #text>
                <div class="text-bold">
                  Dati del medico
                </div>

              </template>
            </csi-card-item-primary>
          </template>

          <template #secondary>
            <div class="q-pa-lg row items-start q-col-gutter-md">
              <q-item class="col-12 col-md">
                <q-item-section>
                  <q-item-label caption class="text-black">Nome e cognome</q-item-label>
                  <q-item-label class="text-bold">{{doctorFullname | empty}}</q-item-label>
                </q-item-section>
              </q-item>


              <q-item class="col-12 col-md-6">
                <q-item-section>
                  <q-item-label caption class="text-black">Codice fiscale</q-item-label>
                  <q-item-label class="text-bold">{{doctorTaxCode | empty}}</q-item-label>
                </q-item-section>
              </q-item>

<!--              <q-item class="col-12 col-md-6" v-if="email">-->
<!--                <q-item-section>-->
<!--                  <q-item-label caption class="text-black">Indirizzo email</q-item-label>-->
<!--                  <q-item-label class="text-bold">{{email | empty}}</q-item-label>-->
<!--                </q-item-section>-->
<!--              </q-item>-->
            </div>

          </template>
        </csi-card-item>

        <csi-card-item on-side>
          <template #primary>


            <!-- TIPOLOGIA -->
            <csi-card-item-primary class="relative-position q-px-lg" inline>

              <template #icon>
                <q-icon name="img:icone/ambulatorio.svg" size="lg"/>
              </template>

              <template #text>
                <div class="text-bold">
                 Ambulatori
                </div>

              </template>
            </csi-card-item-primary>
          </template>

          <template #secondary>
            <q-card-section class="no-padding" v-for="(office, index) in doctorOfficeList" :key="index">
              <fse-mmg-office-item :office="office" />
              <q-separator spaced v-if="index < doctorOfficeList.length-1" />
            </q-card-section>
          </template>
        </csi-card-item>
      </q-card-section>
    </q-card>
  </q-dialog>

</template>

<script>
import CsiCardItem from "components/core/CsiCardItem";
import CsiCardItemPrimary from "components/core/CsiCardItemPrimary";
import {startCase} from "src/services/utils";
import FseMmgOfficeItem from "components/FseMmgOfficeItem";
export default {
name: "FsePatientDoctorDialog",
  components: {FseMmgOfficeItem, CsiCardItemPrimary, CsiCardItem},
  props:{
    patientId:{type:Object, default: null},
    doctor:{type:Object, default:null}
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
    doctorIcon() {
      let doctorIcon = this.doctor?.sesso === 'F' ? 'medico-donna' : 'medico-uomo'
      return `img:icone/${doctorIcon}.svg`
    },
    doctorFullname(){
      let doctorInfo = this.doctor?.medico
      if(doctorInfo){
        return `${startCase(doctorInfo.nome)} ${startCase(doctorInfo.cognome)}`
      }
      return ''
    },
    doctorTaxCode(){
      return this.doctor?.medico?.codice_fiscale
    },
    doctorOfficeList(){
      return this.doctor?.studi ?? []
    },
    email(){
      return this.doctor?.medico?.email
    }
  }
}
</script>

<style scoped>

</style>
