/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {httpAuth, httpPublic} from "src/boot/http";
import store from "src/store";
import {uid} from "quasar";

const SECONDARY_APP_CODE = "WEBAPP_DMA"

// CONFIGURATORE
// ----------------------------------------------------------------------------------------------------------

export const getUser = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/login/utente`;
  httpConfig.headers = httpConfig.headers || {}
  httpConfig.headers["X-Codice-Servizio"] = process.env.APP_CODE_PUA;
  // httpConfig.headers["X-Codice-Verticale"] =  process.env.APP_CODE_PUA;

  return httpPublic.get(url, httpConfig);
};

export const getUserRoles = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/login/ruoli`;
  return httpPublic.get(url, httpConfig);
};

export const getUserPlacements = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/login/collocazioni`;
  return httpPublic.get(url, httpConfig);
};


// INFO MEDICO
// ----------------------------------------------------------------------------------------------------------


export const getDoctorSystems = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/operatore-sanitario/regimi`;
  return httpAuth.get(url, httpConfig);
};

export const getDoctorReports = (taxCode, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/operatore-sanitario/miei-referti/_search`;
  return httpAuth.post(url, payload, httpConfig);
};

export const setAudit = (payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/audit`;
  return httpAuth.put(url, payload, httpConfig);
};


// PAZIENTI
// ----------------------------------------------------------------------------------------------------------
export const getPatientList = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/_search`;
  return httpAuth.get(url, httpConfig);
};
export const getMmgInfo = (taxCode, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/info-mmg`;
  return httpAuth.get(url, httpConfig);
};

export const getConsents = (taxCode, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/consenso`;
  return httpAuth.get(url, httpConfig);
};
export const setConsents = (taxCode, payload, httpConfig = {}) => {
  httpConfig.headers = {
    "X-Codice-Verticale": SECONDARY_APP_CODE
  }
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/comunicazione-consensi`;
  return httpAuth.post(url, payload, httpConfig);
};

export const getInformative = (taxCode, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/informativa`;
  httpConfig.responseType = 'blob'
  return httpAuth.get(url, httpConfig);
};

// DOCUMENTI
// ----------------------------------------------------------------------------------------------------------

export const getDocumentCategories = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cataloghi/elenco-tipodocumento-categoria`;
  return httpPublic.get(url, httpConfig);
};


export const getDocumentsList = (taxCode, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/_search`;
  return httpAuth.post(url, payload, httpConfig);
};

export const getDocumentDetail = (taxCode, documentIlec, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/${documentIlec}`;
  return httpAuth.get(url, httpConfig);
};


export const getDocumentHealthServices = (taxCode, documentIlec, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/${documentIlec}/prestazioni`;
  return httpAuth.get(url, httpConfig);
};


export const getDocumentAssociatedList = (taxCode, documentIlec, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/${documentIlec}/correlati`;
  return httpAuth.get(url, httpConfig);
};

export const setDocumentMediation = (taxCode, documentIlec, httpConfig = {}) => {
  httpConfig.headers = {
    "X-Codice-Verticale": SECONDARY_APP_CODE,
    "X-Codice-Servizio": SECONDARY_APP_CODE
  }

  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/${documentIlec}/mediazione`;
  return httpAuth.put(url, {}, httpConfig);
};


export const showDocumentFromQRCode = (httpConfig = {}) => {
  httpConfig.headers = {
    "X-Codice-Verticale": SECONDARY_APP_CODE,
    "X-Codice-Servizio":SECONDARY_APP_CODE
  }
  const url = `/apiopsan/api/v1/referti/qrcode`;
  httpConfig.responseType = 'blob'
  return httpAuth.get(url, httpConfig);
};


export const downloadDocumentFse = (taxCode, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documento/_search`;
  return httpAuth.post(url, payload, httpConfig);
};

export const downloadDocumentNoPiedmont = (taxCode, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/fo/documento`;
  return httpAuth.post(url, payload, httpConfig);
};

export const downloadDocumentPersonal = (idPatient, idDocument, httpConfig = {}) => {
  let url = `/apiopsan/api/v1/cittadini/${idPatient}/documenti/${idDocument}/personale`;
  return httpAuth.get(url, httpConfig);
};


export const getDocumentMediationInfo = (idPatient, idDocument, httpConfig = {}) => {
  let url = `/apiopsan/api/v1/cittadini/${idPatient}/documenti/${idDocument}/smediazione`;
  return httpAuth.get(url, httpConfig);
};


// IMMAGINI
// ----------------------------------------------------------------------------------------------------------

export const createImageBooking = (payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/referti/prenotazione-pacchetto-immagini`;
  return httpAuth.post(url, payload, httpConfig);
};

export const getDocumentImageStatus = (httpConfig = {}) => {
  const url = `/apiopsan/api/v1/referti/verifica-stato-pacchetto/`;
  return httpAuth.get(url, httpConfig);
};


export const getDocumentImageDownloadUrl = (payload, httpConfig = {}) => {
  let cfAssistito = payload.cf_assistito;
  let idDocumentoIlec = payload.id_documento_ilec;
  let codCL = payload.cod_cl;
  let appCode = "APIOPSAN" //process.env.APP_CODE
  let roleParams = store.getters["getRoleParams"]
  let codRuolo = roleParams?.ruolo
  let codRegime =  roleParams?.regime;
  let idCollocazione = roleParams?.collocazione;
  let requestId = uid()
  let baseUrl = process.env.APP_IS_PROD
    ? "https://ap-tint-dmass.nivolapiemonte.it/dmasssrv/scaricoPacchettoOperatoreSanitario"
    : "https://ts-ap-tint-dmass.nivolapiemonte.it/dmasssrv/scaricoPacchettoOperatoreSanitario";

  const url = `${baseUrl}?cfAssistito=${cfAssistito}&idDocumentoIlec=${idDocumentoIlec}&codCL=${codCL}&archivioDocumentoIlec=FSE&codApplicazione=${appCode}&codVerticale=${appCode}&codRuolo=${codRuolo}&codRegime=${codRegime}&idCollocazione=${idCollocazione}&pin=12345&requestId=${requestId}`;
  return httpAuth.getUri({url, ...httpConfig});
};

// EPISODI
// ----------------------------------------------------------------------------------------------------------
export const getEpisodesList = (taxCode, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/episodi/_search`;
  return httpAuth.post(url, payload, httpConfig);
};
export const getEpisodesDocumentList = (taxCode, idEpisode, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/episodi/${idEpisode}/documenti`;
  return httpAuth.get(url, payload, httpConfig);
};

export const getDocumentsTypeList = (taxCode, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cataloghi/tipodocumento`;
  return httpAuth.get(url, httpConfig);
};

// ESENZIONI
// ----------------------------------------------------------------------------------------------------------
export const getExemptionsList = (taxCode, httpConfig = {}) => {
  httpConfig.headers = {
    "X-Codice-Servizio": SECONDARY_APP_CODE
  }
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/esenzioni`;
  return httpAuth.get(url, httpConfig);
};


// SCREENING
// ----------------------------------------------------------------------------------------------------------
export const getScreeningList = (taxCode, httpConfig = {}) => {
  httpConfig.headers = {
    "X-Codice-Verticale": SECONDARY_APP_CODE,
    "X-Codice-Servizio": SECONDARY_APP_CODE
  }

  const url = `/apiopsan/api/v1/cittadini/${taxCode}/screening`;
  return httpAuth.get(url, httpConfig);
};


// PROFILO SANITARIO SINTETICO
// ----------------------------------------------------------------------------------------------------------
export const getDocumentLast = (taxCode, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/documenti/ultimo`;
  return httpAuth.get(url, httpConfig);
};


// TACCUINO
// ----------------------------------------------------------------------------------------------------------
export const getSanitaryNotebook = (taxCode, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuino`;
  return httpAuth.get(url, httpConfig);
};

export const getNotebookSymptomList = (taxCode, idNotebook, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/sintomi`;
  return httpAuth.get(url, httpConfig);
};

export const getNotebookPainList = (taxCode, idNotebook, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/dolori`;
  return httpAuth.get(url, httpConfig);
};

export const getNotebookContacts = (taxCode, idNotebook, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/contatti-strutture`;
  return httpAuth.get(url, httpConfig);
};

export const getNotebookDrugsList = (taxCode, idNotebook, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/farmaci`;
  return httpAuth.get(url, httpConfig);
};

export const getNotebookDietList = (taxCode, idNotebook, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/diete`;
  return httpAuth.get(url, httpConfig);
};

export const getNotebookEventList = (taxCode, idNotebook, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/eventi`;
  return httpAuth.get(url, httpConfig);
};
export const getNotebookDetectionList = (taxCode, idNotebook, payload, httpConfig = {}) => {
  const url = `/apiopsan/api/v1/cittadini/${taxCode}/taccuini/${idNotebook}/rilevazioni/_search`;
  return httpAuth.post(url, payload, httpConfig);
};
