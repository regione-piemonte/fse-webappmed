/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {
  getConsents,
  getDocumentCategories,
  getDocumentDetail,
  getDocumentMediationInfo,
  getDocumentsList
} from "src/services/api";
import {apiErrorNotify} from "src/services/utils";
import {SessionStorage} from "quasar";
import {LCCE_SESSION_TOKEN} from "src/services/global/config";
import {DOCUMENT_CATEGORY_MAP} from "src/services/documents/config";

export const setUser = (context, {user}) => {
  context.commit("SET_USER", {user});
};

export const setUserRole = (context, {role}) => {
  context.commit("SET_USER_ROLE", {role});
};
export const setUserPlacement = (context, {placement}) => {
  context.commit("SET_USER_PLACEMENT", {placement});
};

export const setUserInfo = (context, {info}) => {
  context.commit("SET_USER_INFO", {info});
};
export const setSystemList = (context, {systemList}) => {
  context.commit("SET_SYSTEM_LIST", {systemList});
};

export const setSystem = (context, {system}) => {
  context.commit("SET_SYSTEM", {system});
};


export const setActivePatient = (context, {patient}) => {
  context.commit("SET_ACTIVE_PATIENT", {patient});
};
export const setPatientConsents = (context, {consents}) => {
  context.commit("SET_PATIENT_CONSENTS", {consents});
};


export const setPatientDoctor = (context, {doctor}) => {
  context.commit("SET_PATIENT_DOCTOR", {doctor});
};


export const setDocumentTypeList = (context, {list}) => {
  context.commit("SET_DOCUMENT_TYPE_LIST", {list});
};

export const setDocumentCategoriesList = (context, {categories}) => {
  context.commit("SET_DOCUMENT_CATEGORIES_LIST", {categories});
};


export const setDocumentDetail = async (context, options) => {
  let taxCode = context.getters["getPatientTaxCode"]

  let id = options?.id
  let params = options?.params
  try {
    let {data: document} = await getDocumentDetail(taxCode, id, {params})
    return document
  } catch (error) {
    let message = "Non Ã¨ stato possibile recuperare il documento."
    apiErrorNotify({error, message})
  }
}

export const setCategoriesList = async (context) => {
  //CHIAMIAMO il servizio lista categorie
  try {
    let {data: categories} = await getDocumentCategories()
    context.commit("SET_DOCUMENT_CATEGORIES_LIST", {categories});

  } catch (error) {
    let message = "Non Ã¨ stato possibile recuperare la lista delle categorie."
    apiErrorNotify({error, message})
  }
}
//CHIAMIAMO il servizio consensi
export const getPatientConsents = async (context, taxCode) => {
  let consents = null
  try {
    let {data} = await getConsents(taxCode)
    consents = data

  } catch (error) {


    if (error.response.status === 400) {
      let errorDetail = error.response.data?.detail ?? []
      let consentsErrorMessage = errorDetail[0]?.valore ?? null
      let errorKey = errorDetail[0]?.chiave?.toString()
      //nessun consenso fornito
      if (errorKey === '2035') {
        consents = {}
        return consents
      } else if (consentsErrorMessage) {
        //Mostriamo il messaggio di errore che arriva dal servizio
        consents = {
          errore: consentsErrorMessage
        }
        //Se l'assistito Ã¨ deceduto/emigrato o cf cancellato:
        const errorTaxCodeCanceled = ['2074', '2073', '2059']
        if (errorTaxCodeCanceled.includes(errorKey)) {
          consents.errore_cf_annullato = true
        }

        return consents
      }
    }
    let message = "Non Ã¨ stato possibile recuperare lo stato dei consensi del paziente"
    apiErrorNotify({error, message})


  }

  return consents
}


export const getDocumentList = async (context, payload) => {
  let taxCode = context.getters["getPatientTaxCode"]
  let documentList = []
  let documentListCount = 0
  try {
    let {data: documents} = await getDocumentsList(taxCode, payload)
    documentList = documents?.elenco_documenti ?? []
    documentListCount = documents?.numero_documenti ?? 0

  } catch (error) {
    let message = "Non Ã¨ stato possibile recuperare la lista dei documenti."
    apiErrorNotify({error, message})
    return null
  }
  // controlliamo che il documento sia smediabile
  for (let i = 0; i < documentList.length; i++) {
    let document = documentList[i]
    if (document.categoria === DOCUMENT_CATEGORY_MAP.FSE && document.metadati_documento?.oscura_scarico_cittadino === 'S') {
      let id = document.id_documento_ilec
      let params = {
        codice_componente_locale: document.codice_cl
      }
      try {
        let {data: mediationInfo} = await getDocumentMediationInfo(taxCode, id, {params})
        documentList[i].info_mediazione = mediationInfo
      } catch (e) {

      }

    }

  }
  return {
    elenco_documenti: documentList,
    numero_documenti: documentListCount
  }


}

export const setSanitaryNotebook = (context, {notebook}) => {
  context.commit("SET_SANITARY_NOTEBOOK", {notebook});
};

export const setDocumentTypeFilter = (context, docType) => {
  context.commit("SET_DOCUMENT_TYPE_FILTER", docType);
};

export const resetLCCE = (context) => {
  context.commit("RESET_LCCE");
};
