/*
 * Copyright Regione Piemonte - 2023
 * SPDX-License-Identifier: EUPL-1.2
 */

import {groupBy, notifyError, orderBy, sortBy} from "src/services/utils";
import {date, exportFile} from "quasar";

const { formatDate} = date;

/**
 * Data una lista di rilevazioni permette di raggrupparle per unitÃ  di misura.
 *
 * @returns {[String, Array]} - Restituisce una entry in cui il primo elemento Ã¨ il codice dell'unitÃ  di misura ed il secondo Ã¨ l'elenco delle rilevazioni associate
 */
export const getDetectionEntryListByMeasure = list => {
  let result = groupBy(list, d => d.unita_misura?.codice);
  result = Object.entries(result);
  return sortBy(result, ([key, list]) => key);
};


/**
 * Dato un elenco di rilevazioni, genera un elenco di punti {x, y} ordinati per data in cui la y Ã¨ il valore numerico
 * della rilevazione e la x Ã¨ la data formattata in DD MMM HH:mm
 *
 * @typedef {Object} DetectionDatasetPoint
 * @property {Number} x - Il valore numerico della rilevazione
 * @property {String} y - La data della rilevazione formattata
 *
 * @param detectionList L'elenco delle rilevazioni da cui ottenere l'elenco dei punti
 * @returns {DetectionDatasetPoint[]} - L'elenco dei punti {x, y}
 */
export const getDetectionDatasetPointList = detectionList => {
  return orderBy(detectionList, ["data"], ["asc"]).map(el => {
    return {
      x: formatDate(el.data, "DD MMM HH:mm"),
      y: el.valore_numerico ?? null
    };
  });
};



export const onMapMenuCode = (code) =>{
  let newCode = code?.replaceAll('_', '-')
  return newCode?.toLowerCase() ?? ''
}


export const getEmptyListMessage = (type, isFem=false) =>{
  let msg = `Nessun ${type} trovato`
  if(isFem){
    msg = `Nessuna ${type} trovata`
  }


  return   `${msg} per il periodo selezionato`
}


export const  wrapCsvValue = (val, formatFn) =>{
  let formatted = formatFn !== void 0
    ? formatFn(val)
    : val

  formatted = formatted === void 0 || formatted === null
    ? ''
    : String(formatted)

  formatted = formatted.split('"').join('""')
    /**
     * Excel accepts \n and \r in strings, but some other CSV parsers do not
     * Uncomment the next two lines to escape new lines
     */
    .split('\n').join('\\n')
    .split('\r').join('\\r')

  return `"${formatted}"`
}


export const  exportTableToCSV = (columns, list, name) => {
  const content = [columns?.map(col => wrapCsvValue(col.label)).join(';')].concat(
    list?.map(row => columns?.map(col => wrapCsvValue(
      typeof col.field === 'function'
        ? col.field(row)
        : row[col.field === void 0 ? col.name : col.field],
      col.format
    )).join(';'))
  ).join('\r\n')

  let descriptorName = name?.toLowerCase().replaceAll(' ', '-')
  const fileName= descriptorName ? `rilevazioni-${descriptorName}.csv` : 'rilevazioni.csv'
  const status = exportFile(
    fileName,
    content,
    'text/csv'
  )

  if (status !== true) {
    notifyError('Impossibile scaricare il file')
  }
}

