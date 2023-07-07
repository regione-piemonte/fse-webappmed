/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {
  GENDER_OPTIONS, SYSTEMS_CODE_MAP,
} from "src/services/global/config";

export const DEFAULT_SCROLL_DURATION = 500;


import {getScrollTarget, setScrollPosition, setHorizontalScrollPosition} from "quasar/src/utils/scroll";

import {extractBirthDayAndGenderPart, GENDERS} from "src/services/tax-code";
import store from "src/store";

export const getGenderLabel = (code) => {
  return GENDER_OPTIONS.find(g => g.value === code)?.label
}

export const getPatientSelectionMessage = (nPatients, limit) => {
  let msg = ''
  if (nPatients > 0) {
    if (nPatients === limit) {
      msg = `Tutti gli ${nPatients} assistiti in questa pagina sono stati selezionati`
    } else if (nPatients === 1) {
      msg = `${nPatients} assistito selezionato `
    } else {
      msg = `${nPatients} assistiti selezionati `
    }
  }
  return msg
}


export const scrollToElement = (element, duration = DEFAULT_SCROLL_DURATION) => {
  let target = getScrollTarget(element);
  let offset = element.offsetTop;
  setScrollPosition(target, offset, duration)
};

export const scrollHorizontalToElement = (element, duration = DEFAULT_SCROLL_DURATION) => {
  let target = getScrollTarget(element);
  let offset = element.offsetLeft;

  setHorizontalScrollPosition(target, offset, duration)
};

export const getGender = (taxCode = "") => {
  let dayAndGenderPart = extractBirthDayAndGenderPart(taxCode);
  let number = Number(dayAndGenderPart);
  if (isNaN(number)) return undefined;
  return number > 40 ? GENDERS.FEMALE : GENDERS.MALE;
};

export const areMoreItems = (list, totalCount) => {
  return (list?.length < totalCount) && totalCount > 0
}

// Controlliamo che non abbia un versione dell'informativa scaduta . Questo controllo non viene fatto se il regime Ã¨ assistenza
export const consentsVersionControl = (consents) => {
  const currentInformative = consents?.identificativo_informativa_corrente ?? ''
  let currentInformativeRegion = currentInformative.slice(0, 3)
  let currentInformativeCode = currentInformative.slice(currentInformative.lastIndexOf('^') + 1);
  const consentInformative = consents?.identificativo_informativa_consensi ?? ''
  let consentInformativeRegion = consentInformative.slice(0, 3)
  let consentInformativeCode = consentInformative.slice(consentInformative.lastIndexOf('^') + 1);
  let activeSystem = store.getters['getSeletedSystem'];
  let isEmergencySystem = activeSystem?.codice === SYSTEMS_CODE_MAP.EMERGENZA

  return isEmergencySystem || (currentInformativeCode === consentInformativeCode) || (currentInformativeRegion !== consentInformativeRegion)

}
