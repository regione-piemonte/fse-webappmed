/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */


export const getUser = state => {
  return state.user?.richiedente;
};


export const getTaxCode = state => {
  return state.user?.richiedente?.codice_fiscale;
};



export const getRoleCode = state => {
  return state.user?.richiedente?.ruolo?.codice ?? state.activeRole?.codice;
};
export const getPlacementCode = state => {
  return state.user?.richiedente?.collocazione?.codice_collocazione ?? state.activePlacement?.codice_collocazione;
};
export const getRoleParams = (state, getters) => {
  let placementCode = getters["getPlacementCode"]
  let roleCode = getters["getRoleCode"]
  let system = getters["getSeletedSystem"]
  return {
    ruolo: roleCode ?? null,
    collocazione: placementCode ?? null,
    regime: system?.codice ?? null
  }

};

export const getUserInfo = state => {
  return state.userInfo;
};

export const getSeletedSystem = state => {
  return state.selectedSystem;
};


export const getActivePatient= state => {
  return state.activePatient
};

export const getPatientTaxCode = (state, getters) =>{
  let patient = getters["getActivePatient"]
  return patient?.codice_fiscale
}


export const isPatientExtraRegion = (state,getters) =>{
  let patient = getters["getActivePatient"]
  return !!patient?.extra_regione
}
export const getPatientDoctor= state => {
  return state.patientDoctor
};

export const getDocumentTypeList= state => {
  return state.documentTypeList ?? []
};

export const getDocumentCategoriesList= state => {
  return state.documentCategoriesList ?? []
};

export const getSanitaryNotebook= state => {
  return state.patientSanitaryNotebook
};
export const getSanitaryNotebookId= state => {
  return state.patientSanitaryNotebook?.id ?? null
};


export const getSystemList= state => {
  return state.systemList ?? []
};

export const getDocumentTypeFilter= state => {
  return state.documentTypeFilter
};

