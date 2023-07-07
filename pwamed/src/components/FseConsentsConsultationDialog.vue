<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
    <q-dialog v-bind="attrs" v-on="listeners" class="fse-consents-consultation-dialog" :maximized="$q.screen.lt.md"
        persistent>

        <q-card style="max-width: 800px">
            <q-toolbar>
                <q-toolbar-title>
                    Consenso alla consultazione dei dati e documenti del<br />
                    Fascicolo Sanitario Elettronico da parte dei professionisti sanitari.
                </q-toolbar-title>
                <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round />
            </q-toolbar>
            <q-card-section>
                <p>
                    Per permettere la consultazione dei dati e documenti contenuti nel FSE al
                    <span class="fse-consent-professional-click" @click="onConsentProfessionalClick()">
                        <b>
                            professionista sanitario
                            <q-icon name="info" />
                        </b>
                    </span>
                    , viene richiesto di esprimere uno specifico consenso.
                    <br />
                    Il mancato conferimento del consenso alla consultazione non comporta in ogni caso
                    alcuna conseguenza sulla garanzia dell'erogazione delle prestazioni
                    del servizio sanitario nazionale.
                    <br>
                    E' inoltre sempre possibile, pur avendo fornito il consenso alla consultazione
                    del proprio FSE, decidere di non far conoscere solo alcuni dati e documenti
                    clinici mediante <b>l'oscuramento puntuale sul singolo documento o su gruppi di
                        dato come nel caso delle Esenzioni per Patologia e delle misurazioni
                        registrate nel Taccuino.</b>
                    <br>
                    In questo caso il sistema garantisce che nessun professionista sanitario
                    venga a conoscenza dell'esistenza di tali dati e del fatto che &egrave;
                    stata espressa la volont&agrave; di oscurarli.

                </p>
            </q-card-section>
        </q-card>


        <fse-consent-professional-dialog v-model="showConsultationProfessionalDialog" />

    </q-dialog>
</template>

<script>
import FseConsentProfessionalDialog from "src/components/FseConsentProfessionalDialog"


export default {
    name: "FseConsentsConsultationDialog",
    components: { FseConsentProfessionalDialog },
    computed: {
        attrs() {
            const { ...attrs } = this.$attrs;
            return attrs;
        },
        listeners() {
            const { ...listeners } = this.$listeners;
            return listeners;
        }
    },
    data() {
        return {
            showConsultationProfessionalDialog: false
        }
    },
    methods: {
        onConsentProfessionalClick() {
            this.showConsultationProfessionalDialog = true;
        }
    }
}
</script>

<style>
.fse-consent-professional-click {
    cursor: pointer;
}
</style>