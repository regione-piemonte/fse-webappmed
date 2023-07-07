/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {getGenderLabel} from "src/services/global/business-logic";
import {date} from "quasar";

const {
  formatDate, subtractFromDate, startOfDate, endOfDate
} = date;

export const FORMAT_DATE = 'YYYY-MM-DD'
export const IS_DEV = process.env.APP_IS_DEV;
export const IS_TEST = process.env.APP_IS_TEST;
export const IS_PROD = process.env.APP_IS_PROD;

export const LCCE_SESSION_TOKEN = 'TOKEN_LCCE'
export const USER_SESSION_KEY = 'USER'
export const GENDER_OPTIONS =[
  {label: 'Maschio', value:'M'},
  {label: 'Femmina', value:'F'},
]

export const ROWS_PER_PAGE = 3

export const TABLE_PATIENT_COLUMNS = [
  {
    name: 'surname',
    required: true,
    label: 'Cognome',
    align: 'left',
    field: row => row.cognome,
    format: val => `${val}`,
    sortable: true
  },
  {
    name: 'name',
    required: true,
    label: 'Nome',
    align: 'left',
    field: row => row.nome,
    format: val => `${val}`,
    sortable: true
  },
  {
    name: 'taxCode',
    required: true,
    label: 'Codice Fiscale',
    align: 'left',
    field: row => row.codice_fiscale,
    format: val => `${val}`,
    sortable: true
  },
  {
    name: 'birthDate',
    required: true,
    label: 'Data di nascita',
    align: 'left',
    field: row => row.data_nascita,
    format: val => `${formatDate(val, "DD/MM/YYYY")}`,
    sortable: true
  },
  {
    name: 'gender',
    required: true,
    label: 'Sesso',
    align: 'left',
    field: row => row.sesso,
    format: val => `${getGenderLabel(val)}`,
    sortable: true
  },


]

export const PATIENT_ENABLEMENT_MAP = {
  PATIENT_LIST: 'list',
  ALL: 'all',
  NONE: 'nome'
}



export const PERIOD_LIST_FILTER =[
  // {
  //   codice: "001",
  //   descrizione: "Oggi",
  //   startDate: startOfDate(now, "day"),
  //   endDate: endOfDate(now, "day")
  // },
  // {
  //   codice: "002",
  //   descrizione: "Ultima settimana",
  //   startDate: startOfDate(subtractFromDate(now, { days: 7 }), "day"),
  //   endDate: endOfDate(now, "day")
  // },
  {
    codice: "001",
    descrizione: "Ultimo mese",
    startDate: formatDate(startOfDate(subtractFromDate(new Date(), {month: 1}), "day"), ),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "002",
    descrizione: "Ultimi 2 mesi",
    startDate: formatDate(startOfDate(subtractFromDate(new Date(), {month: 2}), "day"), ),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "003",
    descrizione: "Ultimi 3 mesi",
    startDate: formatDate(startOfDate(subtractFromDate(new Date(), {month: 3}), "day"), ),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "004",
    descrizione: "Ultimi 6 mesi",
    startDate: startOfDate(subtractFromDate(new Date(), {month: 6}), "day"),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "005",
    descrizione: "Ultimo anno",
    startDate: startOfDate(subtractFromDate(new Date(), {year: 1}), "day"),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "006",
    descrizione: "Ultimi 2 anni",
    startDate: startOfDate(subtractFromDate(new Date(), {year: 2}), "day"),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "007",
    descrizione: "Ultimi 3 anni",
    startDate: startOfDate(subtractFromDate(new Date(), {year: 3}), "day"),
    endDate: endOfDate(new Date(), "day")
  },
  {
    codice: "008",
    descrizione: "Ultimi 5 anni",
    startDate: startOfDate(subtractFromDate(new Date(), {year: 5}), "day"),
    endDate: endOfDate(new Date(), "day")
  }
];



export const SYSTEMS_CODE_MAP ={
  PRONTO_SOCCORSO: 'PS',
  RICOVERO: 'RIC',
  VISITA_AMBULATORIALE: 'AMB',
  MEDICINA_CONVENZIONATA: 'MC',
  AMBULATORIALE_CASA_SALUTE: 'ACS',
  SUPPORTO_ATTIVITA_CITTADINO: 'SAC',
  SUPPORTO_APPROPRIATEZZA_PRESCRITTIVA: 'SAP',
  EMERGENZA_SALVAGUARDIA_COLLETTIVO: 'EMC',
  EMERGENZA: 'EM',
  REFERTO_COVID_19: 'COV'
}




export const AUDIT_CODES = {
  CONSENTS_CONFIRM : "WA_HP_FSECITT",
  CONSENTS_TAXCODE_PARENT: "WA_SEL_GEN",
  CONSENTS_TAXCODE_TUTOR: "WA_SEL_TUT",
  SCREENING_DETAIL: "WA_VIS_DET_SCREEN",
  CONSENTS_PRINT: "WA_STA_CONS",
  MMG_DETAIL: "WA_VIS_MMG",
  IMAGE_BOOKING: "WA_RIC_SCA_PAC",
  IMAGE_DOWNLOAD:"WA_IMR_SCA_PAC",
  IMAGE_SHOWN:"WA_IMR_RIC_VIS_IMM"
}
