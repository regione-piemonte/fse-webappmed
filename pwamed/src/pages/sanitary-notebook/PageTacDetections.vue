<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page>
    <csi-page-title class="q-mb-lg">Rilevazioni</csi-page-title>
    <!-- DESKTOP -->
    <!-- ----------------------------------------------------------------------------------------------------------- -->

    <q-splitter
      id="csi-splitter"
      v-model="splitterModel"
      :class="{'unselected' : !animationCompleted}"
      :separator-class="{'csi-splitter--separator--hide': isMobile}"
      :horizontal="isMobile"
      :limits="[25, 100]"
      disable
      style="height: 100%"
    >

      <!-- MENU -->
      <!-- ----------------------------------------------------------------------------------------------------------- -->
      <template v-slot:before>
        <template v-if="isMobile">
          <csi-scroll-horizontal
            :classes="'row no-wrap items-stretch full-width q-col-gutter-x-lg'"
            controls
            draggable
          >
            <div
              v-for="(menuItem, index) in menuOptions"
              :key="index"
              ref="tacMenuItem"
              class="fse-tac-menu-item self-stretch"
            >

                <fse-tac-menu-item :is-selected="isMenuItemSelected(menuItem)" :menu-item="menuItem"
                                   @on-select="onSelectItem" />


            </div>
          </csi-scroll-horizontal>
        </template>
        <template v-else>
          <div ref="tacDetectionsMenuContainer"
               class="row items-center q-pb-lg justify-center"
               style="margin: 0 auto">

            <q-list id="fse-tac-detections--container "
                    :class="animationCompleted ? 'col-12': 'col-8'" class="row q-col-gutter-md ">
              <div
                v-for="(menuItem, index) in menuOptions"
                :key="index"
                :class="animationCompleted ? 'col-12' : 'col-md-6 ' "
                class="fse-tac-menu-item"

              >
                <fse-tac-menu-item :is-selected="isMenuItemSelected(menuItem)" :menu-item="menuItem" detections
                                   @on-select="onSelectItem"/>
              </div>
            </q-list>

          </div>
        </template>

      </template>

      <template v-slot:after>
        <div class="q-mx-md" :class="{'q-mt-lg': isMobile}">

          <template v-if="menuItemSelected">
            <q-card class="q-mb-lg" flat>
              <q-card-section>
                <fse-filter-data-form :initial-filter="false" @on-filter="onFilter"/>
              </q-card-section>
            </q-card>

            <q-toolbar>
              <q-toolbar-title>
                Andamento dal {{ startDate | date }} al {{ endDate | date }}
              </q-toolbar-title>

              <q-btn
                :flat="visualizationSelected !== VISUALIZATION_MAP.GRAPH"
                :outline="visualizationSelected === VISUALIZATION_MAP.GRAPH"
                class="q-mr-sm"
                color="primary"
                icon="img:icone/istogrammi.svg"
                round
                size="sm"
                @click="visualizationSelected = VISUALIZATION_MAP.GRAPH"
              />
              <q-btn
                :flat="visualizationSelected !== VISUALIZATION_MAP.TABLE"
                :outline="visualizationSelected === VISUALIZATION_MAP.TABLE"
                color="primary"
                icon="grid_on"
                round
                size="sm"
                @click="visualizationSelected = VISUALIZATION_MAP.TABLE"
              />
            </q-toolbar>


            <div v-for="descriptor in menuItemSelected.descriptors" :key="descriptor" class="q-my-lg">
              <template v-if="DESCRIPTOR_CODE_MAP.PRESSURE === descriptor">
                <fse-tac-detection-pressure
                  :end-date="endDate"
                  :menu-item="menuItemSelected"
                  :on-search="searchDetections"
                  :start-date="startDate"
                  :visualization-mode="visualizationSelected"
                  @searched="isDetectionsSearched"/>
              </template>
              <template v-else-if="DESCRIPTOR_CODE_MAP.INSULIN === descriptor">
                <fse-tac-detection-insulin
                  :end-date="endDate"
                  :menu-item="menuItemSelected"
                  :on-search="searchDetections"
                  :start-date="startDate"
                  :visualization-mode="visualizationSelected"
                  @searched="isDetectionsSearched"/>
              </template>

              <template v-else>
                <fse-tac-detections
                  :descriptor="descriptor"
                  :end-date="endDate"
                  :menu-item="menuItemSelected"
                  :on-search="searchDetections"
                  :start-date="startDate"
                  :visualization-mode="visualizationSelected"
                  @searched="isDetectionsSearched"
                />
              </template>


            </div>

          </template>

        </div>


      </template>
    </q-splitter>


  </csi-page>
</template>

<script>


import FseTacMenuItem from "components/sanitary-notebook/FseTacMenuItem";

import {
  DESCRIPTOR_CODE_MAP,
  DETECTIONS_MAP,
  GROUP_CODE_MAP,
  TAC_TABLE_LIMIT,
  VISUALIZATION_MAP
} from "src/services/sanitary-notebook/config";

import CsiScrollHorizontal from "components/core/CsiScrollHorizontal";
import {scrollHorizontalToElement} from "src/services/global/business-logic";

import FseFilterDataForm from "components/FseFilterDataForm";

import FseTacDetections from "components/sanitary-notebook/tac-detections/FseTacDetections";
import {date} from "quasar";
import FseTacDetectionPressure from "components/sanitary-notebook/tac-detections/FseTacDetectionPressure";
import FseTacDetectionInsulin from "components/sanitary-notebook/tac-detections/FseTacDetectionInsulin";
import {Screen} from 'quasar'

const TABS = {
  PERIOD: 'periodo',
  INTERVAL: 'intervallo'
}


const MENU_OPTIONS = [
  {
    name: 'Altezza, peso e circonferenza vita',
    id: GROUP_CODE_MAP.WEIGHT,
    descriptors: [DESCRIPTOR_CODE_MAP.WEIGHT, DESCRIPTOR_CODE_MAP.WAIST_CIRC, DESCRIPTOR_CODE_MAP.HEIGHT]
  },
  {
    name: 'Colesterolo',
    id: GROUP_CODE_MAP.CHOLESTEROL,
    descriptors: [DESCRIPTOR_CODE_MAP.CHOLESTEROL_HDL, DESCRIPTOR_CODE_MAP.CHOLESTEROL_LDL, DESCRIPTOR_CODE_MAP.TRIGLYCERIDES, DESCRIPTOR_CODE_MAP.CHOLESTEROL_TOT]
  },
  {
    name: 'Emoglobina',
    id: GROUP_CODE_MAP.HEMOGLOBIN,
    descriptors: [DESCRIPTOR_CODE_MAP.HEMO_GLYCEMIA]
  },
  {
    name: 'Pressione e frequenza cardiaca',
    id: GROUP_CODE_MAP.PRESSURE,
    descriptors: [DESCRIPTOR_CODE_MAP.PRESSURE, DESCRIPTOR_CODE_MAP.HEART_RATE]
  },
  {
    name: 'Frequenza respiratoria',
    id: GROUP_CODE_MAP.BREATH_FREQ,
    descriptors: [DESCRIPTOR_CODE_MAP.BREATH_FREQ]
  },
  {
    name: 'Glicemia',
    id: GROUP_CODE_MAP.GLYCEMIA,
    descriptors: [DESCRIPTOR_CODE_MAP.GLYCEMIA]
  },
  {
    name: 'Insulina',
    id: GROUP_CODE_MAP.INSULIN,
    descriptors: [DESCRIPTOR_CODE_MAP.INSULIN]
  },
  {
    name: 'Ossimetria',
    id: GROUP_CODE_MAP.OXYMETRY,
    descriptors: [DESCRIPTOR_CODE_MAP.OXYMETRY]
  },
  {
    name: 'Temperatura',
    id: GROUP_CODE_MAP.TEMPERATURE,
    descriptors: [DESCRIPTOR_CODE_MAP.TEMPERATURE]
  }
]
const {formatDate, subtractFromDate, startOfDate, endOfDate} = date;
const TABLE_COLS = [
  {
    name: "start-date",
    label: "Data",
    field: row => row.data,
    format: val => val ? `${formatDate(val, "DD/MM/YYYY")}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "value",
    label: "Misura",
    field: row => row.valore_testuale ? row.valore_testuale : row.valore_numerico,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "misure",
    label: "UnitÃ  di misura",
    field: row => row.unita_misura?.descrizione,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  },
  {
    name: "detection-mode",
    label: "ModalitÃ  rilevazione",
    field: row => row.modalita_rilevazione?.descrizione_regionale,
    format: val => val ? `${val}` : '-',
    align: "left",
    sortable: true
  }

];

export default {
  name: "PageTacDetections",
  components: {
    FseTacDetectionInsulin,
    FseTacDetectionPressure,
    FseTacDetections,
    FseFilterDataForm,
    CsiScrollHorizontal,
    FseTacMenuItem
  },
  data() {
    return {
      Screen,
      data: null,
      TABS,
      VISUALIZATION_MAP,
      DETECTIONS_MAP,
      DESCRIPTOR_CODE_MAP,
      TABLE_COLS,
      tab: TABS.PERIOD,
      splitterModel: 300,
      menuItemSelected: null,
      animationCompleted: false,
      visualizationSelected: VISUALIZATION_MAP.TABLE,
      isLoading: false,
      detectionList: [],
      offset: 0,
      startDate: null,
      endDate: null,
      searchDetections: false
    }
  },

  created() {
    let now = new Date();
    this.endDate = formatDate(now, "YYYY-MM-DD");
    this.startDate = formatDate(
      subtractFromDate(now, {month: 1}),
      "YYYY-MM-DD"
    );
    this.animationComplete = Screen.lt.md || !!this.menuItemSelected
    if (this.menuItemSelected) {
      this.splitterModel = 25
    }
  },
  watch: {
    Screen: {
      immediate: true,
      deep: true,
      handler(val, oldval) {
        if (val.lt.md) {
          this.animationComplete = true
          this.scrollToSelectedDetection()
        } else {
          if (!!this.menuItemSelected) {
            this.animationComplete = true
            this.splitterModel = 25
          }else{
            this.animationComplete = false
          }
        }
      }
    }
  },
  computed: {
    isMobile() {
      return this.$q.screen.lt.md
    },
    sanitaryNotebook() {
      return this.$store.getters["getSanitaryNotebook"]
    },
    menuOptions() {
      let menuList = [...MENU_OPTIONS]
      let detection = this.sanitaryNotebook?.preferenze.find(p => p.codice === "RILEVAZIONI")
      let detections = detection?.gruppi

      for (let i = 0; i < menuList.length; i++) {
        let menuItem = menuList[i]
        let activeDetections = detections.find(p => p.codice === menuItem.id)
        if (activeDetections) {
          menuItem.attivo = true
        }

      }

      return menuList
    },

  },
  methods: {
    scrollToSelectedDetection(){
      let seletedItem = this.menuOptions?.find(item =>item.id == this.menuItemSelected?.id)

      let index = this.menuOptions?.indexOf(seletedItem)
      if (index >= 0) {
        this.$nextTick(() => {
          let card = this.$refs.tacMenuItem[index]
          if(card)
            scrollHorizontalToElement(card)
        })
      }
    },
    async onSelectItem(menuItem, code) {
      this.offset = 0
      this.detectionList = []

      if (this.isMobile) {
        this.onSelectMobile(menuItem, code)
      } else {
        this.onSelectDesktop(menuItem)
      }

      this.searchDetections = true

    },
    onSelectDesktop(menuItem, code, index) {
      this.menuItemSelected = menuItem
      if (!this.animationCompleted) {
        let menuItems = document.querySelectorAll('.fse-tac-menu-item')
        let container = this.$refs.tacDetectionsMenuContainer.getBoundingClientRect().left
        let anime = this.$anime.timeline()
        let itemPosition = menuItems[0].getBoundingClientRect().left

        anime.add(
          {
            targets: '.fse-tac-menu-item',
            translateX: container - itemPosition - 8,
            width: '100%',
            easing: 'easeOutElastic(1,1.5)',
            delay: this.$anime.stagger(100),
            changeComplete: () => {

              menuItems.forEach(item => {
                this.animationCompleted = true
                item.style.transform = 'translateX(0px)'
              })
            }
          }
        ).add({
            targets: '.q-splitter__before',
            width: '25%',
            easing: 'easeOutSine(1.5,1)',
          }
        )

      }
    },
    onSelectMobile(menuItem, code) {
      this.menuItemSelected = menuItem
      let index = this.menuOptions?.indexOf(menuItem)
      let card = this.$refs.tacMenuItem[index]
      if(card)
        scrollHorizontalToElement(card)
    },
    isMenuItemSelected(menuItem,index) {
      return this.menuItemSelected?.id === menuItem.id

    },
    async onFilter({startDate, endDate}) {

      this.offset = 0
      this.detectionList = []
      this.startDate = startDate
      this.endDate = endDate
      if (this.menuItemSelected) {
        this.searchDetections = true
      }

    },
    isDetectionsSearched() {
      this.searchDetections = false
    },

  }
}
</script>

<style lang="sass">
//.fse-tac-menu-item
//  transition: all 2s
.minWidth
  width: 300px
</style>
