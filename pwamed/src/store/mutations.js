/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {SessionStorage} from "quasar";
import {LCCE_SESSION_TOKEN} from "src/services/global/config";

export const SET_USER = (state, {user}) => {
  state.user = user;
};
export const SET_USER_ROLE = (state, {role}) => {
  state.activeRole = role;
};

export const SET_USER_PLACEMENT = (state, {placement}) => {
  state.activePlacement = placement;
};


export const SET_USER_INFO = (state, {info}) => {
  state.userInfo = info;
};


export const SET_SYSTEM_LIST = (state, {systemList}) => {
  state.systemList = systemList;
};

export const SET_SYSTEM = (state, {system}) => {
  state.selectedSystem = system;
};

export const SET_ACTIVE_PATIENT = (state, {patient}) => {
  state.patientSanitaryNotebook = null;
  state.patientDoctor = null;
  state.activePatient = patient;
};
export const SET_PATIENT_DOCTOR = (state, {doctor}) => {
  state.patientDoctor = doctor;
};

export const SET_DOCUMENT_TYPE_LIST = (state, {list}) => {
  state.documentTypeList = list;
};

export const SET_DOCUMENT_CATEGORIES_LIST = (state, {categories}) => {
  state.documentCategoriesList = categories;
};


export const SET_PATIENT_CONSENTS = (state, {consents}) => {
  state.activePatient = Object.assign({}, state.activePatient, {consensi: consents})
}

export const SET_SANITARY_NOTEBOOK = (state, {notebook}) => {
  state.patientSanitaryNotebook = notebook;
};
export const SET_DOCUMENT_TYPE_FILTER = (state, docType) => {
  state.documentTypeFilter = docType;
};

export const RESET_LCCE = (state) => {
  let tokenLCCE = SessionStorage.getItem(LCCE_SESSION_TOKEN)
  if (tokenLCCE) SessionStorage.remove(LCCE_SESSION_TOKEN)
  state.documentTypeFilter = null;

};


