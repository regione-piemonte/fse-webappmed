<!--
  - Copyright Regione Piemonte - 2023
  - SPDX-License-Identifier: EUPL-1.2
  -->

<template>
    <q-dialog v-bind="attrs" v-on="listeners" class="fse-consents-professional-dialog" :maximized="$q.screen.lt.md"
        persistent>
        <q-card style="max-width: 800px">
            <q-toolbar>
                <q-toolbar-title>
                    Decreto del presidente del consiglio dei ministri <br>
                    29 Settembre, n. 178
                </q-toolbar-title>
                <q-btn v-close-popup aria-label="chiudi finestra" flat icon="close" round />
            </q-toolbar>
            <q-card-section>
                <p>
                    Regolamento in materia di fascicolo sanitario elettronico. (15G00192) (GU Serie Generale n.263 del
                    11-11-2015)
                    <br />
                    Entrata in vigore del provvedimento: 26/11/2015
                </p>
                <p>
                    I profili di accesso alle informazioni disponibili nel FSE sono definiti in funzione del ruolo che
                    ciascun soggetto assume. Tali ruoli, in sede di prima applicazione, sono elencati di seguito:
                <ul>
                    <li>Farmacista: operatore sanitario della farmacia abilitato alla professione.</li>
                    <li>Operatore Amministrativo: persona che opera presso le strutture del sistema sanitario e
                        socio-sanitario (es. AO, ASL, MMG) con mansioni amministrative.</li>
                    <li>Direttore Sanitario: medico che svolge attivitÃ  direttive all'interno di una Direzione Sanitaria
                        delle
                        strutture del sistema sanitario e socio-sanitario.</li>
                    <li>Medico di Medicina Generale / Pediatra di Libera Scelta: medico convenzionato con la ASL per
                        svolgere
                        attivitÃ  di MMG/PLS.</li>
                    <li>Direttore Amministrativo: persona che svolge attivitÃ  direttive all'interno della Direzione
                        Amministrativa di strutture del sistema sanitario e socio-sanitario.</li>
                    <li>Medico ed altri dirigenti sanitari (biologo, psicologo, chimico, fi sico): professionisti che
                        operano
                        nell'ambito dei servizi sanitari delle strutture del sistema sanitario e socio-sanitario.</li>
                    <li>Medico RSA: medico che opera presso una struttura che eroga assistenza di tipo residenziale.</li>
                    <li>Infermiere o altro Professionista Sanitario: professionista sanitario che opera nell'ambito delle
                        strutture del sistema sanitario e socio-sanitario.</li>
                    <li>Medico Rete di Patologia: medico che opera nel contesto di una rete di patologia.</li>
                    <li>Professionista del sociale: professionista che svolge la sua attivitÃ  sia nel settore sociale che in
                        quello sanitario, in servizi di tipo socio-assistenziale e socio-sanitario, residenziali o
                        semiresidenziali, in ambiente ospedaliero e al domicilio dell'utente.</li>
                    <li>Assistito: cittadino che usufruisce di servizi sanitari e socio-sanitari.</li>
                </ul>

                </p>
            </q-card-section>
        </q-card>
    </q-dialog>

    
</template>

<script>
export default {
    name: "FseConsentProfessionalDialog",
    computed: {
        attrs() {
            const { ...attrs } = this.$attrs;
            return attrs;
        },
        listeners() {
            const { ...listeners } = this.$listeners;
            return listeners;
        },
    }
}
</script>