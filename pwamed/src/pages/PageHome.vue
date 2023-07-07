<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <csi-page>
    <div class="row justify-center">
      <csi-banner class="q-mb-lg col-12 col-md-8" type="fse">
        Per accedere al fascicolo in qualitÃ  di soggetto abilitato alla consultazione dei dati e documenti
        clinico-sanitari disponibili nel FSE, ai sensi del DPCM n.178/2015 e s.m.i., deve indicare il Ruolo ed il Regime
        cui Ã¨ coinvolto effettivamente nel processo di cura in atto. <strong>La specifica del motivo di accesso Ã¨
        obbligatoria per poter andare avanti.</strong>
      </csi-banner>
    </div>


    <template v-if="isLoading">
      <csi-inner-loading :showing="isLoading" block/>
    </template>
    <template v-else-if="systemList.length<=0">
      <csi-banner type="warning">
        Non Ã¨ stato possibile recuperare la lista dei regimi.
      </csi-banner>
    </template>


    <template v-else>

        <q-splitter
          id="csi-splitter"
          v-model="splitterModel"
          :class="{'unselected' : !animationComplete}"
          :horizontal="isMobile"
          :separator-class="{'csi-splitter--separator--hide': isMobile}"
          :limits="[25, 100]"
          disable
          style="height: 100%"
        >
          <!-- REGIMI -->
          <!-- ----------------------------------------------------------------------------------------------------------- -->
          <template v-slot:before>
            <!-- MOBILE -->
            <!-- ----------------------------------------------------------------------------------------------------------- -->
            <template v-if="isMobile">
              <q-item key="header" class="q-py-none text-h1  text-black ">Regime</q-item>

              <div class="full-width">
                <csi-scroll-horizontal
                  :classes="'row no-wrap items-stretch full-width q-col-gutter-x-lg'"
                  controls
                  draggable
                >
                  <div
                    v-for="(system,index) in systemList"
                    :key="system.codice"
                    ref="systemMenuItem"
                    class="self-stretch"
                    @click="onSelectedSystemMobile(system, index)"
                  >

                    <fse-system-menu-item :system="system" class="fit"/>


                    <!--              <q-item-->
                    <!--                style="height:100px"-->
                    <!--                :active="isSystemSelected(system)"-->
                    <!--                active-class="csi-menu-item&#45;&#45;selected"-->
                    <!--                class="csi-menu-item fse-system-item"-->
                    <!--                clickable-->

                    <!--              >-->
                    <!--                <q-item-section side>-->
                    <!--                  <q-icon :name="getSystemIcon(system.codice)" size="lg"/>-->
                    <!--                </q-item-section>-->
                    <!--                <q-item-section>-->
                    <!--                  <q-item-label><strong>{{ system.descrizione | upperCase }}</strong></q-item-label>-->
                    <!--                </q-item-section>-->
                    <!--              </q-item>-->

                  </div>
                </csi-scroll-horizontal>

              </div>


            </template>

            <!-- DESKTOP -->
            <!-- ----------------------------------------------------------------------------------------------------------- -->
            <template v-else>
              <div ref="fseSystemMenuContainer"
                   v-show="!isMobile"
                   class="q-pa-md row fse-system-menu-container justify-center"
                   style="margin: 0 auto">

                <q-list ref="fseSystemMenuList" :class="animationComplete ? 'col-12': 'col-3'">

                  <q-item key="header" class="q-pa-none text-h1  text-black fse-system-item">Regime</q-item>
                  <div
                    v-for="system in systemList"
                    :key="system.codice"
                    style="width:280px"
                    class="fse-system-item"
                  >

                    <fse-system-menu-item class="q-my-md " :system="system" @on-select="onSelectedSystemDesktop"/>

                  </div>

                </q-list>
              </div>

            </template>

          </template>

          <!-- CERCA ASSISTITO -->
          <!-- ----------------------------------------------------------------------------------------------------------- -->
          <template v-slot:after>

            <div v-if="systemSelected" :class="{'q-mt-lg' : isMobile}" >
              <fse-patient-search-tabs
                :loading="isSearchingPatients"
                :patient-extra-region="patientExtraRegion"
                :patient-list="patientList"
              />
            </div>
          </template>
        </q-splitter>


    </template>


  </csi-page>
</template>

<script>
import {Screen} from 'quasar'
import CsiScrollHorizontal from "components/core/CsiScrollHorizontal";
import FsePatientSearchTabs from "components/patient/FsePatientSearchTabs";
import {scrollHorizontalToElement} from "src/services/global/business-logic";
import {getDoctorSystems, getPatientList} from "src/services/api";
import {apiErrorNotify, isEmpty} from "src/services/utils";

import FseSystemMenuItem from "components/FseSystemMenuItem";

const TABS = {
  PIEDMONT: 'P',
  EXTRA_REGION: 'ER'
}

export default {
  name: "PageHome",
  components: { FseSystemMenuItem, FsePatientSearchTabs, CsiScrollHorizontal,},
  data() {
    return {
      TABS,
      Screen,
      isPageCreated: false,
      showAdhesionDialog: false,
      isLoading: false,
      splitterModel: 110,
      patientList: [],
      systemSelectedAnimation: false,
      menuItemLeft: 0,
      animationComplete: false,
      isSearchingPatients: false,
      patientExtraRegion: null,

    }
  },
  watch: {
    //Ressettiamo l'animazione della lista regimi in caso di ridimensionamento della pagina
    Screen: {
      immediate: true,
      deep: true,
      handler(val, oldval) {
        if (val.lt.md) {
          this.animationComplete = true
          this.scrollToSelectedSystem()
        } else {
          if (this.isPageCreated && !!this.systemSelected) {
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
    systemList() {
      return this.$store.getters["getSystemList"]
    },
    systemSelected() {
      return this.$store.getters['getSeletedSystem']
    },

    isMobile(){
      return this.$q.screen.lt.md
    }
  },
  async created() {
    this.isLoading = true
    if (isEmpty(this.systemList)) {
      try {
        //lista regimi
        let {data: systemList} = await getDoctorSystems()
        this.$store.dispatch('setSystemList', {systemList})
        if (this.systemSelected)
          this.scrollToSelectedSystem()
      } catch (error) {
        let message = 'Non Ã¨ stato possibile recuperare la lista dei regimi'
        apiErrorNotify({error, message})
      }
    }
    // Se il regime Ã¨ stato selezionato o siamo su schermo mobile non attiviamo le animazioni
    this.animationComplete = Screen.lt.md || !!this.systemSelected
    if (this.systemSelected) {
      this.splitterModel = 25
    }

    this.isPageCreated = true
    this.isLoading = false
  },
  methods: {
    //Scroll per lista in versione mobile
    scrollToSelectedSystem() {
      let seletedId = this.systemList?.find(s => s.codice == this.systemSelected?.codice)
      let index = this.systemList?.indexOf(seletedId)

      if (index >= 0) {
        this.$nextTick(() => {
          this.scrollToMenuItem(index)
        })
      }
    },
    isSystemSelected(system) {
      return this.systemSelected?.codice === system?.codice
    },
    onSelectedSystemMobile(system, index) {
      this.$store.dispatch('setSystem', {system})
      this.scrollToMenuItem(index)
    },

    onSelectedSystemDesktop(system) {
      this.$store.dispatch('setSystem', {system})
      if (!this.animationComplete) {
        const container = this.$refs.fseSystemMenuContainer.getBoundingClientRect().left
        let menuItems = document.querySelectorAll('.fse-system-item')
      console.log('menuItems', menuItems)
        let anime = this.$anime.timeline()
        const itemPosition = menuItems[0].getBoundingClientRect().left
        console.log('itemPosition', itemPosition)
        anime.add(
          {
            targets: '.fse-system-item',
            translateX: container - itemPosition + 16,
            easing: 'easeOutElastic(1, 1.1)',
            delay: (el, i) => 100*i,
            changeComplete: () => {
              console.log('changeComplete')
              menuItems.forEach(item => {
                item.style.transform= 'translateX(0)'
                this.animationComplete = true

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
    scrollToMenuItem(index) {
      let card = this.$refs.systemMenuItem[index]
      if (card)
        scrollHorizontalToElement(card)
    },
    getSystemIcon(code) {
      return `img:icone/regimi/ico_${code?.toLowerCase()}.svg`
    },
    printConsents() {
      const id = "fse-consents-print"
      const prtHtml = document.getElementById(id).innerHTML;
      // Get all stylesheets HTML
      let stylesHtml = "";
      for (const node of [
        ...document.querySelectorAll('link[rel="stylesheet"], style')
      ]) {
        stylesHtml += node.outerHTML;
      }
      // Open the print window
      const WinPrint = window.open(
        "",
        "_blank"
      );
      WinPrint.document.write(`<!DOCTYPE html>
                  <html>
                    <head>
                      ${stylesHtml}
                    </head>
                    <body class="print-page">
                      ${prtHtml}
                    </body>
                  </html>`);

      // WinPrint.document.close();
      setTimeout(function () {
        WinPrint.focus();
        WinPrint.print();
      }, 300);

      //WinPrint.close();
    }
  },

}
</script>

<style lang="sass">

</style>
