/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

// SCREENING

export const SCREENING_TYPES_CODES =  {
  CYTOLOGICAL : "CV",
  MAMMOGRAPHIC: "MX",
  COLOR_RECTUM: "CR"
}

export const SCREENING_TYPES_LABEL = {
  [SCREENING_TYPES_CODES.CYTOLOGICAL] : "citologico",
  [SCREENING_TYPES_CODES.MAMMOGRAPHIC]: "mammografico",
  [SCREENING_TYPES_CODES.COLOR_RECTUM]: "colon-retto"
}

export const SCREENING_TYPES_LIST =[
  {codice: SCREENING_TYPES_CODES.CYTOLOGICAL, descrizione: 'Citologico Cervico-Vaginale'},
  {codice: SCREENING_TYPES_CODES.MAMMOGRAPHIC, descrizione: 'Mammografico'},
  {codice: SCREENING_TYPES_CODES.COLOR_RECTUM, descrizione: 'Colon-Retto'}
]

