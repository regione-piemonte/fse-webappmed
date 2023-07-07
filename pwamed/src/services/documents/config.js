/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */


// TIPOLOGIE DOCUMENTO
// ---------------------------------------------------------------------------------------------------------------------
// Mappa dei possibili tipi di documento
export const DOCUMENT_TYPE_CODE_MAP = {
  // 1       DEA_VERBALE         VERBALE PS
  VERBAL: "DEA_VERBALE",
  // 2       DEA_TRIAGE          TRIAGE
  TRIAGE: "DEA_TRIAGE",
  // 3       REFERTO             REFERTO
  REPORT: "REFERTO",
  // 4       ANAMNESI            ANAMNESI
  HISTORY: "ANAMNESI",
  // 5       SDO                 SDO
  SDO: "SDO",
  // 6       LET_DIMISSIONE      LETTERA DI DIMISSIONE
  DISCHARGE: "LET_DIMISSIONE",
  // 7       REFERTO_CICLO       REFERTO-CICLO
  CYCLE_REPORT: "REFERTO_CICLO",
  // 8       ATTO_OPERATORIO     ATTO OPERATORIO
  OPERATIVE_ACT: "ATTO_OPERATORIO",
  // 9       REFERTO_LIS         REFERTO LIS
  LIS_REPORT: "REFERTO_LIS",
  // 10      REFERTO_RIS         REFERTO REFERTO DI RADIOLOGIA
  RADIOLOGY_REPORT: "REFERTO_RIS",
  // 11      REFERTO_AP          REFERTO DI ANATOMIA PATOLOGICA
  PATHOLOGICAL_ANATOMY_REPORT: "REFERTO_AP",
  // 12      34105-7             LETTERA DI DIMISSIONE OSPEDALIERA
  DISCHARGE_LETTER: "34105-7",
  // 13      59258-4             VERBALE DI PRONTO SOCCORSO
  FIRST_AID_REPORT: "59258-4",
  // 14      11502-2             REFERTO DI LABORATORIO
  LAB_REPORT: "11502-2",
  // 15      68604-8             REFERTO DI RADIOLOGIA
  RADIOLOGY_REPORT_2: "68604-8",
  // 16      11526-1             REFERTO DI ANATOMIA PATOLOGICA
  PATHOLOGICAL_ANATOMY_REPORT_2: "11526-1",
  // 17      11488-4             REFERTO SPECIALISTICO
  SPECIALIZED_REPORT: "11488-4",
  // CODICI DI RICETTE che non hanno a che fare con ROL
  // 18      57833-6             PRESCRIZIONE FARMACEUTICA
  // 23      57832-8             PRESCRIZIONE DIAGNOSTICA O SPECIALISTICA
  // 24      29304-3             EROGAZIONE FARMACEUTICA
  // 26      81223-0             EROGAZIONE SPECIALISTICA
  // CODICI DI VACCINAZIONI che non hanno a che fare con ROL
  // 27      REG-87273-9         SCHEDA VACCINALE
  VACCINATION_CARD: "REG-87273-9",
  VACCINATION_CARD_2: "87273-9",
  // 28      REG-82593-5         CERTIFICATO VACCINALE
  VACCINATION_CERTIFICATE: "REG-82593-5",
  VACCINATION_CERTIFICATE_2: "82593-5"
};

export const DOCUMENT_DOCTOR_TYPES_LABEL = {
  R: "Refertante",
  V: "Validante"
};


export const DOCUMENT_DOCTOR_TYPE_MAP = {
  REFERENT: "REFERTANTE",
  VALIDATING: "VALIDANTE",
  BOTH: "VALIDANTEOREFERTANTE"
};

export const DOCUMENT_CATEGORY_MAP = {
  FSE: "FSE",
  PERSONAL: "PERSONALE"
};

export const DOCUMENT_PERSONAL_CONTRIBUTION_TYPE_MAP = {
  // TEXT: "string",
  TRANSCRIPTION: "TRASCRIZIONE",
  ATTACHMENT: "ALLEGATO"
};

export const EPISODES_TYPES_MAP = {
  RICOVERO : 'I',
  PASSAGGI_PS: 'E'
}


// IMMAGINE
// ---------------------------------------------------------------------------------------------------------------------
// Mappa dei possibili stati della richiesta del download dell'immagine
export const IMAGE_STATUS_CODE_MAP = {
  NULL: null,
  ERRORE_WS: "ERRORE_WS",
  NON_PRESENTE: "RICHIESTA_NON_PRESENTE",
  SCARICATO: "SCARICATO",
  DA_ELABORARE: "DA_ELABORARE",
  ELAB_IN_CORSO: "ELAB_IN_CORSO",
  DISPONIBILE: "DISPONIBILE",
  INVIO_MAIL_IN_CORSO: "INVIO_MAIL_IN_CORSO",
  ERRORE_INVIO_MAIL: "ERRORE_INVIO_MAIL",
  NOTIFICATO: "NOTIFICATO",
  COMPOSIZIONE_PACCHETTO_SOSPESA: "COMPOSIZIONE_PACCHETTO_SOSPESA",
  ERRORE_COMPONI_PACCHETTO: "ERRORE_COMPONI_PACCHETTO",
  PACCHETTO_DA_CANCELLARE: "PACCHETTO_DA_CANCELLARE",
  PACCHETTO_IN_CANCELLAZIONE: "PACCHETTO_IN_CANCELLAZIONE",
  CANCELLAZIONE_PACCHETTO_SOSPESA: "CANCELLAZIONE_PACCHETTO_SOSPESA",
  ERRORE_IN_CANCELLAZIONE_PACCHETTO: "ERRORE_IN_CANCELLAZIONE_PACCHETTO",
  PACCHETTO_CANCELLATO: "PACCHETTO_CANCELLATO"
};

export const IMAGE_STATUS_LABEL_MAP = {
  [IMAGE_STATUS_CODE_MAP.NULL]: '',
  [IMAGE_STATUS_CODE_MAP.ERRORE_WS]: "Errore WS",
  [IMAGE_STATUS_CODE_MAP.NON_PRESENTE]: "Richiesta non presente",
  [IMAGE_STATUS_CODE_MAP.SCARICATO]: "Scaricato",
  [IMAGE_STATUS_CODE_MAP.DA_ELABORARE]: "Da elaborare",
  [IMAGE_STATUS_CODE_MAP.DISPONIBILE]: "Disponibile",
  [IMAGE_STATUS_CODE_MAP.INVIO_MAIL_IN_CORSO]: "Invio mail in corso",
  [IMAGE_STATUS_CODE_MAP.ERRORE_INVIO_MAIL]: "Errore invio mail",
  [IMAGE_STATUS_CODE_MAP.NOTIFICATO]: "Notificato",
  [IMAGE_STATUS_CODE_MAP.COMPOSIZIONE_PACCHETTO_SOSPESA]: "Composizione pacchetto sospesa",
  [IMAGE_STATUS_CODE_MAP.ERRORE_COMPONI_PACCHETTO]: "Errore componi pacchetto",
  [IMAGE_STATUS_CODE_MAP.PACCHETTO_DA_CANCELLARE]: "Pacchetto da cancellare",
  [IMAGE_STATUS_CODE_MAP.PACCHETTO_IN_CANCELLAZIONE]: "Pacchetto in cancellazione",
  [IMAGE_STATUS_CODE_MAP.CANCELLAZIONE_PACCHETTO_SOSPESA]: "Cancellazione pacchetto sospesa",
  [IMAGE_STATUS_CODE_MAP.ERRORE_IN_CANCELLAZIONE_PACCHETTO]: "Errore in cancellazione pacchetto",
  [IMAGE_STATUS_CODE_MAP.PACCHETTO_CANCELLATO]: "Pacchetto cancellato"
};

// quali sono gli stati dell'immagine per cui l'utente puÃ² prenotarla?
export const IMAGE_STATUS_CODE_LIST_BOOKABLE = [
  IMAGE_STATUS_CODE_MAP.NULL,
  IMAGE_STATUS_CODE_MAP.NON_PRESENTE,
  IMAGE_STATUS_CODE_MAP.PACCHETTO_DA_CANCELLARE,
  IMAGE_STATUS_CODE_MAP.PACCHETTO_IN_CANCELLAZIONE,
  IMAGE_STATUS_CODE_MAP.PACCHETTO_CANCELLATO,
  IMAGE_STATUS_CODE_MAP.CANCELLAZIONE_PACCHETTO_SOSPESA,
  IMAGE_STATUS_CODE_MAP.ERRORE_IN_CANCELLAZIONE_PACCHETTO
];

// quali sono i tipi di referto per cui Ã¨ disponibile download delle immagini?
export const DOCUMENT_TYPE_CODE_LIST_IMAGE_DOWNLOADABLE = [
  DOCUMENT_TYPE_CODE_MAP.RADIOLOGY_REPORT,
  DOCUMENT_TYPE_CODE_MAP.RADIOLOGY_REPORT_2
];

// Quali sono gli stati per cui l'immagine risulta "in eleborazione"
export const IMAGE_STATUS_CODE_LIST_IN_ELABORATION = [
  IMAGE_STATUS_CODE_MAP.ELAB_IN_CORSO,
  IMAGE_STATUS_CODE_MAP.DA_ELABORARE,
];

// Quali sono gli stati dell'immagine per cui l'utente deve contattare l'assistenza?
export const IMAGE_STATUS_CODE_LIST_ERROR = [
  IMAGE_STATUS_CODE_MAP.ERRORE_WS,
  IMAGE_STATUS_CODE_MAP.ERRORE_COMPONI_PACCHETTO,
  IMAGE_STATUS_CODE_MAP.COMPOSIZIONE_PACCHETTO_SOSPESA
];

// quali sono gli stati dell'immagine per cui l'utente puÃ² scaricarla?
export const IMAGE_STATUS_CODE_LIST_DOWNLOADABLE = [
  IMAGE_STATUS_CODE_MAP.DISPONIBILE,
  IMAGE_STATUS_CODE_MAP.INVIO_MAIL_IN_CORSO,
  IMAGE_STATUS_CODE_MAP.NOTIFICATO,
  IMAGE_STATUS_CODE_MAP.ERRORE_INVIO_MAIL,
  IMAGE_STATUS_CODE_MAP.SCARICATO
];

// PAGAMENTO
// ---------------------------------------------------------------------------------------------------------------------
export const PAYMENT_STATUS_CODE_MAP = {
  // Il cittadino ha pagato completamente il documento
  PAYED: "S",
  // Il cittadino non ha ancora pagato il documento
  NOT_PAYED: "N",
  // Il cittadino ha pagato solo parzialmente il documento
  PARTIALLY_PAYED: "P",
  // Non si conosce lo stato attuale del pagamento
  UNDEFINED: "U",
  // Il cittadino Ã¨ esente dal pagamento del documento
  EXEMPT: "E",
  // Al cittadino spetta un rimborso per questo documento
  REFUND: "R",
  // Questo documento Ã¨ sempre scaricabile. Indipendentemente dal fatto che
  // debba essere pagato o meno
  FREE: "F",
  NULL: null
};


// Quali sono gli stati di pagamento per cui Ã¨ ammesso il download del pagamento?
export const PAYMENT_STATUS_CODE_LIST_AS_PAYED = [
  PAYMENT_STATUS_CODE_MAP.NULL,
  PAYMENT_STATUS_CODE_MAP.PAYED,
  PAYMENT_STATUS_CODE_MAP.EXEMPT,
  PAYMENT_STATUS_CODE_MAP.UNDEFINED,
  PAYMENT_STATUS_CODE_MAP.FREE,
  PAYMENT_STATUS_CODE_MAP.REFUND
];


export const DOCUMENT_DOCTOR_TYPE_LIST = [
  {label: 'Refertante', value: DOCUMENT_DOCTOR_TYPE_MAP.REFERENT},
  {label: 'Validante', value: DOCUMENT_DOCTOR_TYPE_MAP.VALIDATING},
  {label: 'Validante o refertante', value: DOCUMENT_DOCTOR_TYPE_MAP.BOTH}
]

export const DOCUMENT_CATEGORY_LIST = [
  {label: 'FSE', value: DOCUMENT_CATEGORY_MAP.FSE},
  {label: 'Personale', value: DOCUMENT_CATEGORY_MAP.PERSONAL}
]


export const VIEW_MAP = {
  LIST: 'list',
  CARDS: 'cards'
}
export const VIEW_MAP_OPTIONS = [
  {value: VIEW_MAP.CARDS, slot: 'cards'},
  {value: VIEW_MAP.LIST, slot: 'list'},

]


export const ASSOCIATION_OPERATION_TYPE_MAP = {
  NRE: "NRE",
  EPISODE_PS: "NUMERO_PASSAGGIO_PS",
  EPISODE_SDO: "NUMERO_SDO"
};


export const ASSOCIATION_OPERATION_TYPE_LABEL_MAP = {
  [ASSOCIATION_OPERATION_TYPE_MAP.NRE] : 'NRE',
  [ASSOCIATION_OPERATION_TYPE_MAP.EPISODE_PS] : 'passaggio in PS',
  [ASSOCIATION_OPERATION_TYPE_MAP.EPISODE_SDO] : 'ricovero'
}


export const DOCUMENT_TYPE_PRESCRIPTION_LIST = [
  "57833-6","57832-8", "29304-3", "81223-0"
]


export const DOCUMENT_TYPE_PSS_LIST = [
  "BDS", "60591-5"
]


export const DOCUMENT_TYPE_EXEMPTION_LIST = [
  "REG-ESE-11488-4", "57827-8"
]

export const DOCUMENT_TYPE_VACCINATIONS_LIST = [
  "82593-5", "97500-3", "REG-87273-9", "87273-9", "REG-82593-5"
]


export const DOCUMENT_SIGN_CODE_MAP = {
  GOLD: "G",
  SILVER: "S",
  BRONZE: "B"
};


export const DOCUMENT_SIGN_CODE_MAP_ICON = {
  [DOCUMENT_SIGN_CODE_MAP.GOLD]: "gold",
  [DOCUMENT_SIGN_CODE_MAP.SILVER]: "silver",
  [DOCUMENT_SIGN_CODE_MAP.BRONZE]: "bronze"
};


