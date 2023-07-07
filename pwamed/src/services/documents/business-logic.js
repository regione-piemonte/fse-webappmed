/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {
  DOCUMENT_CATEGORY_MAP,
  IMAGE_STATUS_CODE_LIST_BOOKABLE,
  PAYMENT_STATUS_CODE_LIST_AS_PAYED
} from "src/services/documents/config";
import {apiErrorNotify, deepClone} from "src/services/utils";
import store from "src/store";
import {downloadDocumentFse, downloadDocumentNoPiedmont, downloadDocumentPersonal, setAudit} from "src/services/api";
import { saveAs } from 'file-saver'
export const getDocumentDoctorReferent = (document) =>{
  let doctorList = document?.medici ?? [];

  let doctor = doctorList?.find(
    d => d.tipo_medico === DOCUMENT_DOCTOR_TYPES.REFERENT
  );

  if (!doctor || !doctor.cognome || !doctor.nome) return null;
  return `${doctor.cognome} ${doctor.nome}`;
}


export const isDocumentPayed = (paymentStatus) => {
  let status= paymentStatus ?? null
  let patient = store.getters['getActivePatient']
  let hasConsents = patient?.consensi?.consenso_consultazione && patient?.consensi?.consenso_alimentazione
  return PAYMENT_STATUS_CODE_LIST_AS_PAYED.includes(status) || !hasConsents
}


/**
 * L'immagine puÃ² essere prenotata se:
 *  1. Il referto prevede la possibilitÃ  di generarla
 *  2. Lo stato dell'immagine permette la generazione (es: non Ã¨ giÃ  generata)
 *  3. La richiesta di prenotazione non Ã¨ salvata nel client
 *
 * @param document Il documento da controllare
 * @returns {boolean} true se Ã¨ possibile richiedere l'immagine, false altrimenti
 */
export const canRequestImageFse = document => {
  let status = document?.stato_generazione_pacchetto;
  let isBookable = IMAGE_STATUS_CODE_LIST_BOOKABLE.includes(status);
  return _canGenerateImageFse(document) && isBookable;
};


/**
 * L'immagine del documento puÃ² essere generata se:
 *  1. Il referto Ã¨ scaricabile
 *  2. Il referto deve rientrare tra le categorie che permettono il download dell'immagine
 *  3. Deve avere l'accession number (Questo perchÃ¨ se mandano un referto di radiologia da una ASL su cui non abbiamo
 *     l'integrazione con le immagini NON DEVE essere visualizzato il pulsante di download immagini)
 *
 * @param document Il documento da controllare
 * @returns {boolean} true se puÃ² essere generata un'immagine, false altrimenti
 * @private
 */
// const _canGenerateImageFse = document => {
//   let typeCode = document.tipo_documento?.codice;
//
//   return (
//     canDownloadPdfFse(document) &&
//     DOCUMENT_TYPE_CODE_LIST_IMAGE_DOWNLOADABLE.includes(typeCode) &&
//     hasAccessionNumbersFse(document)
//   );
// };


export const normalizeDocument = document => {
    const metaData = {...  document.metadati_documento }
  metaData.azienda = {
    codice:  metaData.codice_azienda,
    descrizione: metaData.descrizione_azienda,
    descrizione_struttura: metaData.descrizione_struttura,
    descrizione_unita_operativa: metaData.descrizione_unita_operativa,
    matricola_up_dipartimentale: metaData.matricola_up_dipartimentale,
  }

  metaData.tipo_documento = {
      codice: metaData.codice_tipo_documento,
    descrizione: metaData.descrizione_tipo_documento
  }

  metaData.tipo_firma = {
    codice: metaData.codice_tipo_firma,
    descrizione: metaData.descrizione_tipo_firma
  }

  metaData.importo = {
    ticket_pagato: metaData.importo_ticket_pagato,
    ticket_da_pagare: metaData.importo_ticket_da_pagare
  }


  Object.keys(metaData).forEach(key => {
    document[key] = metaData[key]
  })


  return document
}



export const getDocumentFseTypeList =  (validDocTypes) =>{
  let categories =  store.getters['getDocumentCategoriesList']
  let categoryList = deepClone(categories?.filter(c => c.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE))

  let docTypeList = []

  categoryList.forEach(category => {

    if(category.codice_categoria === DOCUMENT_CATEGORY_MAP.FSE){
      let docTypes = category.tipi_documento

      docTypes.forEach(type =>{
        if(validDocTypes?.includes(type.codice)){
          type.codice_categoria = category.codice_categoria
          docTypeList.push(type)
        }
      })
    }
  });

  return docTypeList
}


export const getDocumentFse = async (payload) =>{
  let patientTaxCode = store.getters['getPatientTaxCode']
  if(!payload) return null
  try {
    let {data:document} = await downloadDocumentFse(patientTaxCode, payload)
    return  document
  } catch (error) {
    let message = `Non Ã¨ stato possibile recuperare il referto ${payload.id_documento_ilec ?? ''}`
    apiErrorNotify({error, message})
    return null
  }
}
export const getDocumentPersonal = async (id) =>{
  let patientTaxCode = store.getters['getPatientTaxCode']
  if(!id) return null
  try {
    let {data: document} = await downloadDocumentPersonal(patientTaxCode, id)
   return document
  } catch (error) {
    let message = `Non Ã¨ stato possibile recuperare il documento ${id}`
    apiErrorNotify({error, message})
    return null
  }
}
export const getDocumentExtraRegion = async (payload) =>{
  let patientTaxCode = store.getters['getPatientTaxCode']
  if(!payload) return null
  try {
    let {data:document} = await downloadDocumentNoPiedmont(patientTaxCode, payload)
    return  document
  } catch (error) {
    let message = `Non Ã¨ stato possibile recuperare il referto`
    apiErrorNotify({error, message})
    return null
  }
}

export const getUrlFromBase64 = (document) =>{
  if(!document) return
  let file = document?.allegato
  let fileType = document?.tipo_allegato
  return `data:${fileType};base64, ${file}`

}


