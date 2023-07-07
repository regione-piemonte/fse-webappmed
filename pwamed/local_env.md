# DMAWA | Salute Piemonte Operatori

Descrizione progetto

## Getting started
### Installa le dipendenza
```bash
npm install
```

### Avvia il progetto in modalità sviluppo (hot-code reloading, error reporting, etc.)
```bash
npm run dev
```

### Builda l'app per test
```bash
npm run build:test
```

### Builda l'app per produzione
```bash
npm run build:prod
```

### Controlli prima di partire con lo sviluppo

1. Nel `package.json` modificare `name`, `description`, `productName` ed `author` in base al progetto.

2. Modificare `APP_CODE` in tutti gli ambienti (cartella `envs`) in base al codice applicativo dell'app che si sta sviluppando.


3. Modifica eventualmente il componente `src/pages/AppFse` per caricare i dati necessari all'avvio dell'app

4. Modifica eventualmente il componente `src/App` per aggiungere/rimuovere guardie

5. Modifica eventualmente il file `src/index.template.html` per aggiornare le versioni dei browser supportati


### Index preconfigurata

Il file `src/index.template.html` è già stato preconfigurato per permettere all'applicazione di far parte della PWA
del portale.

Inoltre nell'index è presente anche la gestione dei browser obsoleti.

### Stili preconfigurati

Le variabili di Quasar sono sovrascritte per avere un look & feel coerente tra tutti i verticali.


### Più componenti

Sono stati aggiunti dei componenti in più rispetto a quelli offerti da quasar che permettono di uniformare
l'aspetto tra le varie app del portale "Salute Piemonte".

Alcuni componenti sono stati costruiti ex-novo per offrire maggiori funzionalità permettendo agli sviluppatori di
lavorare più rapidamente

### Distinzione tra ambianti

Sono stati configurati 5 ambienti

1. Sviluppo
2. Test
3. Produzione
4. Test tok
5. Prod tok

E' possibile customizzare il comportamento di ogni ambiente tramite le variabili d'ambiente presenti nella
cartella `envs`.

Per sapere su quale ambiente sta girando la nostra applicazioni sono presenti dei flag ad hoc:
```js
process.env.APP_ENV // 'dev', 'test', 'prod'
process.env.APP_IS_DEV
process.env.APP_IS_TEST
process.env.APP_IS_PROD
// per gestire le chiamate di contesto senza shibboleth:
process.env.APP_IS_TEST_TOK
process.env.APP_IS_PROD_TOK
```

### Layout predefinito

Il layout è già stato implementato e nella maggior parte dei casi non dovrebbe richiedere modifiche.
Tuttavia, se necessario, è possibile modificarlo per adeguarlo all'applicazione che si sta sviluppando.

### configurazione iniziale

1. __AppFse__

    Ha il compito di effettuare le chiamate alle API per caricare nello store tutti i dati necessari all'avvio dell'applicazione e gestire le chiamate di contesto.

    ACCESSO CON QRCODE :
   Se presente la query param `tokenQRCode` viene fatto un reindirizzamento alla pagina dedicata, senza chiamare altri servizi.

    ACCESSO CON TOKEN :
   Se presente la query param  `token` viene chiamato il servizio `/login/user` che restituisce le informazioni base dell'utente loggato. Se il token non esiste o l'utente non è loggato compare un messaggio di errore.

    ACCESSO CON TOKENLCCE:
    rReindirizzamento alla pagina dedicata, ma una volta salvato nella SessionStorage non viene più fatto alcun controllo sul token.


2. __PageQrCodeHome__
   Ha il compito di effettuare le chiamate alle API per caricare la lista dei ruoli, collocazioni e regimi utili per la decodifica del qrCode.

3. __PageLcceHome e PageEcwdmeHome__
   Ha il compito di effettuare la chiamata  al servizio il servizio `/login/user` che restituisce le informazioni base dell'utente loggato e successivamente i servizi necessati per caricare la lista dei ruoli, collocazioni e regimi utili per la navigazione sulle pagine successive.


__Headers e parametri fissi__

Tramite il metodo `apiInterceptorRequestId` lato front-end vengono passati i seguenti dati ai servizi:

Headers:
X-Request-Id
X-Codice-Servizio
X-Codice-Verticale
ruolo (ricavato dal servizio login/utente)
collocazione (ricavato dal servizio login/utente)
regime


### Store predefinito

Nello store Vuex è già presente la gestione delle informazioni principali di cui tutta l'app ha bisogno per lavorare:

1. __user__

    Contiene le informazioni di base relative all'utente loggato.
    Se questo dato è popolato significa che l'utente è loggato, altrimenti vale `null` e significa che l'utente non è loggato

2. __tokenLCCE__
    Contiene il token passato inizialmente come query param

3. __paziente__
   Contiene le informazioni del paziente selezionato


### Routes predefinite

La struttura di base delle routes è già stata definita.
Questo permette di iniziare subito a lavorare sulla homepage o comunque di configurare solo le nuove pagine
necessarie all'applicazione da sviluppare.

### API predefinite

Il client HTTP (axios) è già preconfigurato nel file `src/boot/http` mentre le chiamate alle API necessarie sono
presenti nel file `src/services/api`


### Viewer Immagini
Sulla cartella `/public/whtml` è presente il programma che permette la visualizzazione delle immagini


### Gestione in ambiente di DEV
### Gestione in ambiente di DEV

Il token generato dal configuratore viene inserito sulla const `TOKEN` presente su `src/services/mocks.js`

Lato front-end  vengono passati i seguenti headers mockati sul servizi tramite il metodo `apiInterceptorRequestId` :
X-Forwarded-for
Shib-Iride-IdentitaDigitale
Shib-Identita-CodiceFiscale

L'utente shibboleth (il CODICE FISCALE )è simulato tramite la variabile `MOCK_USER_CF_IRIDE` presente su `src/services/mocks.js`

Per consetire un corretto reindirizzamento dal PUNTO UNICO DI ACCESSO è stato necessario modificare le configurazioni sul file `quasar.conf.js`:

- vueRouterMode deve essere impostato a "history"
- publicPath: 'dmawa' (il nome della sottocartella)
- aggiunto .htaccess in cartella /public

sul file envs/dev.env.js è stata inserita la base URL utilizzata (APP_API_BASE_URL_AUTH e APP_API_BASE_URL_PUBLIC)
