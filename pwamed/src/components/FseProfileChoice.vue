<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <div>
    <template v-if="isLoading">
      <csi-inner-loading :showing="true" block/>
    </template>
    <template v-else>
      <csi-page-title no-back class="q-mb-lg">Profilo di Accesso</csi-page-title>

      <csi-banner v-if="rolesOptions.length<=0" class="q-mb-md" type="info">
        Nessun profilo di accesso trovato.
      </csi-banner>

      <!--  RUOLI-->
      <template v-else-if="rolesOptions.length>4 && $q.screen.gt.sm">
        <csi-scroll-horizontal
          controls
          draggable
        >
          <div class="row q-col-gutter-x-lg q-py-md no-wrap items-stretch">
            <div class="q-px-sm"></div>
            <div v-for="(role,index) in rolesOptions" :key="index">
              <q-card
                :class="{'active' : isActiveRole(role.codice)}"
                class="bg-white user-role-system-card q-pa-md fit"
                style="min-width: 150px"
                @click="showPlacements(role)"
              >
                <q-card-section class="items-center full-height" horizontal>
                  <q-card-section v-if="role.icona" class="no-padding q-mr-md">
                    <q-icon :name="role.icona" size="md"/>
                  </q-card-section>
                  <q-card-section class="no-padding">
                    <div class="text-subtitle2"><strong>{{ role.descrizione }}</strong></div>
                  </q-card-section>
                </q-card-section>
              </q-card>
            </div>
            <div class="q-px-sm"></div>
          </div>
        </csi-scroll-horizontal>
      </template>
      <div v-else class="row items-center q-col-gutter-lg q-pb-md">
        <div v-for="(role,index) in rolesOptions" :key="index" class="col-12 col-sm-6 col-md-3 items-stretch">
          <q-card
            :class="{'active' :isActiveRole(role.codice)}"
            class="bg-white user-role-system-card q-pa-md fit"
            @click="showPlacements(role)"
          >
            <q-card-section class="items-center full-height" horizontal>
              <q-card-section v-if="role.icona" class="no-padding q-mr-md">
                <q-icon :name="role.icona" size="md"/>
              </q-card-section>
              <q-card-section class="no-padding">
                <div class="text-subtitle2"><strong>{{ role.descrizione }}</strong></div>
              </q-card-section>
            </q-card-section>
          </q-card>
        </div>
      </div>

      <template v-if="selectedRole">

        <!--  COLLOCAZIONI-->

        <h2 class="text-h5 text-bold q-my-lg">
          Collocazione
        </h2>

        <q-card v-if="isLoadingPlacements" class="q-mb-lg">
          <q-card-section>
            <div class="q-pl-xs">
              <q-item v-for="i in 2" :key="i">
                <q-item-section side top>
                  <q-skeleton height="24px" type="QRadio" width="24px"/>
                </q-item-section>

                <q-item-section>
                  <q-item-label class="q-pl-sm">
                    <q-skeleton type="text" width="80%"/>
                  </q-item-label>
                </q-item-section>
              </q-item>
            </div>
          </q-card-section>
        </q-card>
        <template v-else>
          <csi-banner v-if="placementsOptions.length===0" type="info">
            Non ci sono collocazioni disponibili per il profilo di accesso selezionato
          </csi-banner>
          <q-card v-else class="q-mb-lg">
            <q-card-section>
              <q-list>
                <q-item v-for="(placement, index) in placementsOptions"
                        :key="index"
                        :active="index === selectedPlacementIndex"
                        tag="label"

                >
                  <q-item-section side top>
                    <csi-radio-button
                      :aria-label="placement.label" :value="index === selectedPlacementIndex"
                      @input="onSelectPlacement(placement, index)"
                      size="md"
                    />
                  </q-item-section>

                  <q-item-section>
                    <q-item-label>{{ placement.label_code }}</q-item-label>
                    <q-item-label>{{ placement.descrizione_collocazione }}</q-item-label>
                  </q-item-section>
                </q-item>


              </q-list>
            </q-card-section>
          </q-card>

        </template>

        <!--  REGIMI-->
        <template v-if="selectedPlacement">
          <h2 class="text-h5 text-bold q-my-lg">
            Regime
          </h2>
          <div class="row items-center q-col-gutter-lg q-pb-md" v-if="isLoadingSystems">
            <div class="col-12 col-sm-6 col-md" v-for="i in 4" :key="i">
              <q-card  class="q-mb-lg" >
                <q-card-section>
                  <q-item  class="no-padding">
                    <q-item-section side >
                      <q-skeleton height="32px" type="QAvatar" width="32px"/>
                    </q-item-section>

                    <q-item-section>
                      <q-item-label class="q-pl-sm">
                        <q-skeleton type="text" width="80%"/>
                      </q-item-label>
                    </q-item-section>
                  </q-item>
                </q-card-section>
              </q-card>
            </div>
          </div>
          <template v-else-if="systemOptions.length>4 && $q.screen.gt.sm">
            <csi-scroll-horizontal
              controls
              draggable
            >
              <div class="row q-col-gutter-x-lg items-stretch q-py-md no-wrap">
                <div class="q-px-sm"></div>
                <div v-for="(system,index) in systemOptions" :key="index">
                  <q-card
                    :class="{'active' :isSelectedSystem(system.codice)}"
                    class="bg-white user-role-system-card q-pa-md fit"
                    style="min-width: 150px"
                    @click="onSelectSystem(system)"
                  >
                    <q-card-section class="items-center full-height" horizontal>
                      <q-card-section class="no-padding q-mr-md">
                        <q-icon :name="system.icona" size="md"/>
                      </q-card-section>
                      <q-card-section class="no-padding">
                        <div class="text-subtitle2"><strong>{{ system.descrizione }}</strong></div>
                      </q-card-section>
                    </q-card-section>
                  </q-card>
                </div>
                <div class="q-px-sm"></div>
              </div>
            </csi-scroll-horizontal>
          </template>
          <div v-else class="row items-center q-col-gutter-lg q-pb-md">
            <div v-for="(system,index) in systemOptions" :key="index" class="col-12 col-sm-6 col-md items-stretch">
              <q-card
                :class="{'active' : isSelectedSystem(system.codice)}"
                class="bg-white user-role-system-card q-pa-md fit"
                @click="onSelectSystem(system)"
              >
                <q-card-section class="items-center full-height" horizontal>
                  <q-card-section class="no-padding q-mr-md">
                    <q-icon :name="system.icona" size="md"/>
                  </q-card-section>
                  <q-card-section class="no-padding">
                    <div class="text-subtitle2"><strong>{{ system.descrizione }}</strong></div>
                  </q-card-section>
                </q-card-section>
              </q-card>
            </div>
          </div>


          <csi-buttons v-if="selectedSystem" class="q-my-lg">
            <csi-button @click="$emit('on-confirm')" :loading="confirming">Prosegui</csi-button>
          </csi-buttons>

        </template>




      </template>
    </template>


  </div>
</template>

<script>
import CsiScrollHorizontal from "components/core/CsiScrollHorizontal";
import CsiRadioButton from "components/core/CsiRadioButton";
import {getDoctorSystems, getUserPlacements, getUserRoles, showDocumentFromQRCode} from "src/services/api";
import {apiErrorNotify, isEmpty} from "src/services/utils";
export default {
  name: "FseProfileChoice",
  components: {CsiRadioButton, CsiScrollHorizontal},
  props:{
    //si possono passare dei ruoli o collocazioni predefiniti
    role:{type:Object, default:null},
    placement:{type:String, default:null},
    //si possono nascondere la lista di altri ruoli/collocazioni
    hideRoleList: {type:Boolean, default:false},
    hidePlacementList: {type:Boolean, default:false},
    confirming:{type:Boolean, default:false}
  },
  data() {
    return {
      isLoading:null,
      selectedRole: null,
      selectedPlacement: null,
      selectedPlacementIndex: null,
      isLoadingPlacements: false,
      isLoadingSystems:false,
      isLoadingDocument:false,
      rolesList: [],
      placementsList: [],
      systemSelected: null,

      selectedSystem: null,

    }
  },
  computed: {
    user() {
      return this.$store.getters['getUser']
    },
    systemList(){
      return this.$store.getters['getSystemList']
    },
    rolesOptions() {
      let options = []

      if (this.rolesList?.length > 0) {
        options = this.rolesList.map(role => {
          role.icona = this.getRoleIcon(role)
          return role
        })
      }
      return options
    },
    systemOptions(){
      let options = []

      if (this.systemList?.length > 0) {
        options = this.systemList.map(system => {
          system.icona = this.getSystemIcon(system?.codice)
          return system
        })
      }
      return options
    },
    activeRole(){
      return this.$store.getters['getRoleCode']
    },
    placementsOptions() {
      if (!this.placementsList) return []
      return this.placementsList.map(placement => {
        placement.label_code = `${placement.codice_azienda} - ${placement.codice_collocazione?.replaceAll('@', ' ')}`
        return placement
      })
    },
  },
  async created() {
    this.isLoading=true

    if(!this.hideRoleList){
      try {
        let {data: roles} = await getUserRoles()
        this.rolesList = roles
      } catch (error) {
        let message = "Non Ã¨ stato possibile recuperare l'elenco dei profili di accesso."
        apiErrorNotify({error, message})
      }
    }else if(this.role){
      this.rolesList = [this.role]
    }


    if (this.rolesList?.length === 1 || this.role) {
      let role = this.role ? this.role : this.rolesList[0]
      await this.showPlacements(role)
    }


    this.isLoading=false
  },
  methods: {
    isEmpty,
    isActiveRole(code){
      return code === this.selectedRole?.codice
    },
    async showPlacements(role) {
      if(this.selectedRole === role?.codice) return

      this.$store.dispatch("setUserRole", {role});
      this.selectedRole = role
      this.isLoadingPlacements = true
      try {
        let params = {
          codice_ruolo: role?.codice
        }
        let {data: placements} = await getUserPlacements({params})

        this.placementsList = this.normalizePlacementNames(placements)

        if(placements?.length===1){
          let placement = placements[0]
          this.onSelectPlacement(placement, 0)
        }

      } catch (error) {
        this.placementsList = []
        let message = "Non Ã¨ stato possibile recuperare l'elenco delle collocazioni per il profilo di accesso selezionato."
        apiErrorNotify({error, message})
      } finally {
        this.isLoadingPlacements = false
      }

    },
    onSelectPlacement(placement, index) {
      this.selectedPlacementIndex = index
      this.selectedPlacement = placement

      this.$store.dispatch("setUserPlacement", {placement});

      if(isEmpty(this.systemList))
        this.getSystemList()
    },

    async getSystemList(){

      this.isLoadingSystems = true
      try{
        let {data: systemList} = await getDoctorSystems()
        this.$store.dispatch('setSystemList', {systemList})

      }catch (error) {
        let message = 'Non Ã¨ stato possibile recuperare la lista dei regimi'
        apiErrorNotify({error, message})
      }

      this.isLoadingSystems = false
    },
    getRoleIcon(role) {
      let roleIcon = this.getRoleIconMap(role.codice, this.user?.sesso)

      return `img:icone/ruoli/icona-${roleIcon}.svg`
    },
    onSelectSystem(system) {
      this.selectedSystem = system
      this.$store.dispatch('setSystem', {system})
    },
    getSystemIcon(code) {
      return `img:icone/regimi/ico_${code?.toLowerCase()}.svg`
    },
    getRoleIconMap(code, gender) {

      let roleCode = code.toUpperCase()

      const ICONS_MAP = {
        GENERIC: 'generico',
        MANAGER: 'direzione',
        PHARMACY: 'farmacia',
        NURSE: 'infermieristica',
        DOCTOR: 'medico',
        OPERATOR: 'operatore'
      }
      const GENERIC = ['AAS', 'AUSCONT', 'SPECAMB', 'ASSSAN', 'COLTECN'];
      const MANAGER = ['DAM', 'PERDIRI', 'DIRSTRSEMPL', 'DRS', 'DRPSIC', 'DSA', 'DIR'];
      const OPERATOR = ['COLPREVAMB', 'OPCSI', 'OGC', 'ALTRUO', 'OPEALTR', 'OAM', 'OPI', 'PROGCED', 'PSS']
      const DOCTOR = ['MRP', 'MMG', 'GUARD', 'RSA', 'PLS']
      const PHARMACY = ['FAR']
      const NURSE = ['INF']

      let role = ICONS_MAP.OPERATOR
      if (GENERIC.includes(roleCode))
        role = ICONS_MAP.GENERIC
      else if (MANAGER.includes(roleCode))
        role = ICONS_MAP.MANAGER
      else if (OPERATOR.includes(roleCode))
        role = ICONS_MAP.OPERATOR
      else if (DOCTOR.includes(roleCode))
        role = ICONS_MAP.DOCTOR
      else if (PHARMACY.includes(roleCode))
        role = ICONS_MAP.PHARMACY
      else if (NURSE.includes(roleCode))
        role = ICONS_MAP.NURSE
      else
        role = ICONS_MAP.OPERATOR


      let roleName = role?.toLowerCase();
      let genderName = gender === "F" ? "donna" : "uomo"
      return `${roleName}-${genderName}`
    },

    normalizePlacementNames(placements){
      return  placements?.map(placement => {
        placement.descrizione_collocazione = placement.descrizione_collocazione.replaceAll('@', ' ')
        return placement
      })


    },
    isSelectedSystem(code){
      return code === this.selectedSystem?.codice
    }

  }
}
</script>

<style lang="sass">
.user-role-system-card
  color: #000
  cursor: pointer
  border: 2px solid #fff
  &:hover
    border: 2px solid rgba($csi-primary-light  , 0.6)
    box-shadow: 0px 0px 5px  rgba($csi-primary-light, 0.5)
    -webkit-box-shadow:   0px 0px 5px  rgba($csi-primary-light , 0.5)
    -moz-box-shadow:   0px 0px 5px  rgba($csi-primary-light , 0.5)
  &.active, &.home:hover
    margin: 0
    border: 2px solid $csi-primary-light
    box-shadow: 0px 0px 10px $csi-primary-light
    -webkit-box-shadow:   0px 0px 10px $csi-primary-light
    -moz-box-shadow:   0px 0px 10px $csi-primary-light


</style>
