<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page padding style="margin: 0 auto">

    <div v-if="isLoading" :class="{'reverse': $q.screen.gt.sm}" class="row justify-center items-stretch q-col-gutter-lg">
      <div class="col-12 col-md-3">
        <q-card class="fit" flat>
          <div></div>
          <q-card-section>
            <div class="text-h6 q-mb-lg">Note generali</div>
            <!-- STATO DI SALUTE -->
            <q-item class="q-mb-md no-padding">
              <q-item-section>
                <q-item-label class="text-bold text-caption">
                  Stato di salute
                </q-item-label>
                <q-item-label>
                    <q-skeleton type="text" class="" style="max-width: 200px"/>
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="q-mb-md no-padding">
              <q-item-section>
                <q-item-label class="text-bold text-caption">
                  Stile di vita
                </q-item-label>
                <q-item-label>
                  <q-skeleton type="text" class="" style="max-width: 200px"/>
                </q-item-label>
              </q-item-section>
            </q-item>


          </q-card-section>
        </q-card>
      </div>
      <div class="col-12 col-md">
        <div class="row items-center q-col-gutter-md ">

            <div v-for="i in 8" :key="i" class="col-12 col-sm-6 ">
              <fse-tac-menu-item-skeleton/>
            </div>

        </div>
      </div>
    </div>

    <template v-else-if="isNotebookHidden">
      <csi-banner type="warning">
        Il paziente ha oscurato la consultazione del taccuino
      </csi-banner>
    </template>

    <template v-else-if="noNotebookErr">
      <csi-banner type="warning">
        Taccuino sanitario non consultabile perchÃ© non Ã¨ mai stato creato
      </csi-banner>
    </template>

    <div v-else-if="sanitaryNotebook" :class="{'reverse': $q.screen.gt.sm}" class="row justify-center items-stretch q-col-gutter-lg">

      <div class="col-12 col-md-3">
        <q-card class="fit" flat>
          <div></div>
          <q-card-section>
            <div class="text-h6 q-mb-lg">Note generali</div>
            <!-- STATO DI SALUTE -->
            <q-item class="q-mb-md no-padding">
              <q-item-section>
                <q-item-label class="text-bold text-caption">
                  Stato di salute
                </q-item-label>
                <q-item-label>
                  <template v-if="isLoading">
                    <q-skeleton type="text" class="" style="max-width: 200px"/>
                  </template>
                  <template v-else>
                    {{ healthState | empty }}
                  </template>

                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="q-mb-md no-padding">
              <q-item-section>
                <q-item-label class="text-bold text-caption">
                  Stile di vita
                </q-item-label>
                <q-item-label>
                  <template v-if="isLoading">
                    <q-skeleton type="text" class="" style="max-width: 200px"/>
                  </template>
                  <template v-else>
                    {{ lifestyle | empty }}
                  </template>

                </q-item-label>
              </q-item-section>
            </q-item>


          </q-card-section>
        </q-card>
      </div>
      <div class="col-12 col-md">
        <div class="row items-center q-col-gutter-md ">
          <template v-if="isLoading">
            <div v-for="i in 8" :key="i" class="col-12 col-sm-6 ">
              <fse-tac-menu-item-skeleton/>
            </div>
          </template>
          <template v-else>
            <div v-for="(menuItem, index) in menuOptions" :key="index" class="col-12 col-sm-6">
              <fse-tac-menu-item :is-selected="menuItemSelected===menuItem" :menu-item="menuItem"
                                 @on-select="onSelect"/>
            </div>
          </template>
        </div>
      </div>


    </div>
    <template v-else>
      <csi-banner type="warning">
        Taccuino sanitario non trovato
      </csi-banner>
    </template>

    <q-dialog v-model="showGeneralNote">
      <q-card class="q-mb-lg q-mx-sm" flat>
        <q-card-section>
          <div class="row items-center q-col-gutter-lg">
            <!-- STATO DI SALUTE -->
            <q-item class="col-12 col-md-6">
              <q-item-section>
                <q-item-label class="text-bold text-caption">
                  Stato di salute
                </q-item-label>
                <q-item-label>
                  {{ healthState }}
                </q-item-label>
              </q-item-section>
            </q-item>

            <q-item class="col-12 col-md-6">
              <q-item-section>
                <q-item-label class="text-bold text-caption">
                  Stile di vita
                </q-item-label>
                <q-item-label>
                  {{ lifestyle }}
                </q-item-label>
              </q-item-section>
            </q-item>


          </div>
        </q-card-section>
      </q-card>
    </q-dialog>

  </csi-page>
</template>

<script>


import FseTacMenuItem from "components/sanitary-notebook/FseTacMenuItem";
import {

  TAC_DETECTIONS, TAC_DIET,
  TAC_DRUGS,
  TAC_EVENTS,
  TAC_GENERAL_NOTES,
  TAC_PAIN,
  TAC_STRUCTURE_CONTACT, TAC_SYMPTOMS
} from "src/router/routes";
import {getSanitaryNotebook} from "src/services/api";
import FseTacMenuItemSkeleton from "components/sanitary-notebook/FseTacMenuItemSkeleton";
import {apiErrorNotify, deepClone, isEmpty} from "src/services/utils";
import {ENTITY_CODE_MAP} from "src/services/sanitary-notebook/config";

const MENU_OPTIONS = [
  {
    name: 'Contatti struttura',
    id: ENTITY_CODE_MAP.STRUCTURE_CONTACT,
    url: TAC_STRUCTURE_CONTACT
  },

  {
    name: 'Dieta',
    id: ENTITY_CODE_MAP.DIET,
    url: TAC_DIET
  },
  {
    name: 'Dolori',
    id: ENTITY_CODE_MAP.PAINS,
    url: TAC_PAIN
  },
  {
    name: 'Eventi',
    id: ENTITY_CODE_MAP.EVENTS,
    url: TAC_EVENTS
  },
  {
    name: 'Farmaci',
    id: ENTITY_CODE_MAP.DRUGS,
    url: TAC_DRUGS
  },

  {
    name: 'Rilevazioni',
    id: ENTITY_CODE_MAP.DETECTION,
    url: TAC_DETECTIONS
  },
  {
    name: 'Sintomi',
    id: ENTITY_CODE_MAP.SYMPTOMS,
    url: TAC_SYMPTOMS
  }
]

export default {
  name: "PageTacSanitaryNotebookMenu",
  components: {FseTacMenuItemSkeleton, FseTacMenuItem},
  data() {
    return {
      menuItemSelected: null,
      isLoading: false,
      notebook: null,
      showGeneralNote: false,
      noNotebookErr: false
    }
  },
  computed: {
    patientTaxCode() {
      return this.$store.getters['getPatientTaxCode']
    },
    sanitaryNotebook() {
      return this.$store.getters['getSanitaryNotebook']
    },
    isNotebookHidden(){
      return this.sanitaryNotebook?.oscurato || isEmpty(this.sanitaryNotebook)
    },
    generalNote() {
      return this.sanitaryNotebook?.nota_generale
    },
    healthState() {
      return this.generalNote?.stato_salute
    },
    lifestyle() {
      return this.generalNote?.stile_vita
    },
    menuOptions() {
      let menuList = [...MENU_OPTIONS]
      let preferences = [...this.sanitaryNotebook?.preferenze] ?? []

      for (let i = 0; i < menuList.length; i++) {
        let menuItem = menuList[i]
        let activePreference = preferences?.find(p => p.codice === menuItem.id)
        if (activePreference) {
          menuItem.attivo = true
          menuItem.gruppi = activePreference.gruppi ?? []
        }

      }

      return menuList
    }

  },
  async created() {
    if (!this.patientTaxCode) {
      return
    }

    this.isLoading = true
    if (!this.sanitaryNotebook) {
      try {
        this.noNotebookErr = false
        let {data: notebook} = await getSanitaryNotebook(this.patientTaxCode)

        if(!isEmpty(notebook)){
          notebook.preferenze = this.orderPreferencesByEntity(notebook)
          await this.$store.dispatch('setSanitaryNotebook', {notebook})
        }

      } catch (error) {
        if(error.response.status === 404){
          this.noNotebookErr = true
        }else{
          let message = "Non Ã¨ stato possibile recuperare il taccuino sanitario"
          apiErrorNotify({error, message})
        }

      }
    }


    this.isLoading = false
  },
  methods: {
    onSelect(item, code) {
      this.menuItemSelected = item

      let route = {
        name: item.url.name,
        params: {
          id: this.sanitaryNotebook?.id
        }

      }
      this.$router.push(route)
    },
    orderPreferencesByEntity(notebook) {
      let preferenceList = notebook?.preferenze ? deepClone(notebook?.preferenze) : []

      let preferenceByEntity = preferenceList.reduce((obj, preference) => {
        obj[preference.entita.codice] = obj[preference.entita.codice] || [];
        if (preference.gruppo) obj[preference.entita.codice].push(preference.gruppo);
        return obj;
      }, {});


      let newPreferenceList = Object.keys(preferenceByEntity).map(key => {
        let listItem = preferenceList.find(preference => preference.entita.codice === key);
        listItem = listItem.entita;
        listItem.gruppi = preferenceByEntity[key];
        return listItem;
      });

      return newPreferenceList
    }

  }
}
</script>

<style lang="sass">
.tac-general-note-box
  background-color: #fcfcfc
  border: 1px solid #eaeaea
  border-radius: 5px

</style>
