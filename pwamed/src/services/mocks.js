/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

//export const MOCK_USER_CF_IRIDE = "AAAAAA00A11C000K"
export const MOCK_USER_CF_IRIDE = "TRNLCU77B44L736O"
export const MOCK_USER_IDENTITY_IRIDE = `${MOCK_USER_CF_IRIDE}/CSI%20PIEMONTE/DEMO%2020/0/0/0/31-12-2012`

export const TOKEN = "1cd68590-0039-4046-952b-14999ecf4e59"
export const QRCODE = "42b29955-8c29-4d35-a2e6-5a12385f1962"

export const USER = {
  "richiedente": {
    "nome": "Mario",
    "cognome": "Rossi",
    "codice_fiscale": "TRNLCU77B44L736O",
    "ruolo": {
      "codice": "MMG",
      "descrizione": "MEdico"
    },
    "collocazione": {
      "codice_collocazione": "010203",
      "descrizione_collocazione": "A.S.L. getTORINO 3",
      "codice_azienda": "010203",
      "descrizione_azienda": "A.S.L. TORINO 3"
    }
  },
  "parametri_login": [
    {
      "codice": "string",
      "valore": "string"
    }
  ],
  "funzionalita": [
    {
      "codice": "string",
      "descrizione": "string",
      "codice_funzionalita_padre": "string",
      "descrizione_funzionalita_padre": "string"
    }
  ]
}


