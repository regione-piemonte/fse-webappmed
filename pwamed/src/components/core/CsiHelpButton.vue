<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
  <q-btn class="csi-help-button" dense flat icon="help" no-caps round>
    <q-tooltip anchor="top middle">Aiuto</q-tooltip>
    <q-menu
      :value="isMenuVisible"
      anchor="bottom left"
      auto-close
      class="csi-help-button__menu"
    >
      <q-list class="csi-help-button__menu__list" style="min-width: 120px">
        <q-item clickable href="https://servizi.regione.piemonte.it/catalogo/fascicolo-sanitario-elettronico-per-medici" target="_blank"
                title="Manuali"
                type="a">
          <q-item-section>
            <q-item-label>
              Manuali
            </q-item-label>
          </q-item-section>
        </q-item>

        <q-item clickable href="https://assistenzapua.sistemapiemonte.it/#/fascicolosanitario" target="_blank" title="Assistenza"
                type="a">
          <q-item-section>
            <q-item-label>
              Assistenza
            </q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-menu>
  </q-btn>
</template>

<script>

export default {
  name: "CsiHelpButton",
  data() {
    return {
      isMenuVisible: false,

    };
  },
  computed: {},
  methods: {
    showMenu() {
      this.isMenuVisible = true;
    },
    hideMenu() {
      this.isMenuVisible = false;
    },
    updateMenu(val) {
      this.isMenuVisible = val;
    },

  }
};
</script>
