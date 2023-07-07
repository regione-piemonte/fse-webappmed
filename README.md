## WEBAPPMED

## Descrizione del prodotto
Il prodotto è un'applicazione web ad uso dei professionisti sanitari della Regione Piemonte,
le cui funzionalità permettono:
- la consultazione dei documenti prodotti da strutture sanitarie piemontesi e non, presenti nel Fascicolo Sanitario Elettronico (FSE) di tutti gli assisti del Servizio Sanitario Nazionale
- la consultazione dei documenti autocontrubuti dagli assistiti
- la visualizzazione e lo scarico dei pacchetti immagine allegati ai referti radiologici, appoggiandosi alle componenti [DMASS e DMASSIMR](https://github.com/regione-piemonte/imr-fse)
- la consultazione del Profilo Sanitario Sintetico
- la visualizzazione degli esiti degli esami di Screening oncologici regionali
- la visualizzazione delle esenzioni di patologia in possesso del cittadino
- la registrazione dell'avvenuta mediazione (spiegazione) dei documenti inviati come oscurati all'assitito.


## Componenti
Il prodotto è realizzato tramite l'integrazione dei seguenti componenti:

| Componente |Descrizione  |Versione |
|--|--|--|
|[APIOPSAN](https://github.com/regione-piemonte/webappmed-fse/apiopsan) |API REST trasversali per l'operatore sanitario per l'accesso ai dati del FSE | 1.0.0 | 
|[PWAMED](https://github.com/regione-piemonte/webappmed-fse/pwamed) | Front-end della PWA per l'operatore sanitario | 1.0.0 |

## Prerequisiti
I prerequisiti per l'installazione dell'applicativo sono i seguenti:
- Web server: Apache 2.4
- Java: AdoptOpenJDK 11.0.4
- Application Server: WildFly 23.0.0
- ANT: Ant version 1.8.4


## Configurazioni
Si vedano i readme delle singole componenti

## Versionamento
Per la gestione del codice sorgente viene utilizzata la metodologia [Semantic Versioning](https://semver.org/).

## Autori
- BERTI VERONICA
- BORRELLI ANDREA
- ELMI ANDREA
- LOSURDO ANNARITA
- MASTRORILLI MICHELE
- VOLPE MARCO


## Copyrights
© Copyright Regione Piemonte – 2023

## Licenza
SPDX-License-Identifier: EUPL-1.2 (si veda il file [LICENSE.txt](https://github.com/regione-piemonte/webappmed-fse/LICENSE.txt))