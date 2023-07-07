/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.MetadatoRicercaType;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiResponse;

@Component
public class GestionePSS {

	public void postProcessingDB(List<SintesiDocumento> documenti) {
		
		Iterator<SintesiDocumento> iterator = documenti.iterator();
		SintesiDocumento pss = null;
		while(iterator.hasNext()) {
			SintesiDocumento doc = iterator.next();
			if (pss == null && doc.getMetadatiDocumento().getCodiceTipoDocumento().equals(Constants.CODICE_TIPO_DOC_PSS)) {
				pss = doc;
				continue;
			}
			
			if (pss != null && doc != null) {
				iterator.remove();
			}
		}
		
	}
	
	public void postProcessingINI(RicercaDocumentiResponse response) {
		Iterator<MetadatoRicercaType> iterator = response.getRicercaDocumentiOUT().getMetadato().iterator();
		MetadatoRicercaType pss = null;
		while(iterator.hasNext()) {
			MetadatoRicercaType doc = iterator.next();
			if (pss == null && doc.getTipologiaDocumentoMedio().equals(Constants.CODICE_TIPO_DOC_PSS)) {
				pss = doc;
				continue;
			}
			
			if (pss != null && doc != null) {
				iterator.remove();
			}
		}
	}
}
