/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl.getDocumento;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.custom.DmaccTDocumentiQrcode;
import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

/**
 * @author 2133
 * Servizio per recuperare informazioni sul documento su DMA in componente centrale - da utilizzare per pazienti di regione Piemonte
 * Serve i seguenti punti funzionali
 * - verifica esistenza documento su dmaccidx_t_documento
 * - verifica stato pagamento ticket
 * - verifica se documento e' oscurato (oscuramento puntuale OR per ricetta OR per esenzione)
 * - verifica se il chiamante e' anche il medico refertante del documento
 * - recupera metadati documento a partire da qrcode per lo scarico
 *
 */

@Service
public class StatoDocumento {
	
	private static final String TICKET_PAGATO = "S";
	private static final String TICKET_RIMBORSATO = "R";
	private static final String TICKET_ESENTE_SOGGETTO = "E";
	private static final String TICKET_ESENTE_REFERTO = "F";
	
	@Autowired
	private DmaccIdxDao dmaccIdxDao;
	
	@Autowired
	@Qualifier("ValidateGenericMeritWhitMedicoImpl")
	private ValidateGenericMeritWhitMedicoImpl validateGeneric;
	
	public DmaccidxTDocumento verificaEsistenzaDocumento(List<ErroreDettaglioExt> listError, Long idDocumentoIlec,
			String codiceComponenteLocale, long idPaziente) throws DatabaseException, ResponseErrorException {
		// verifica che il documento sia indicizzato sul db locale e trovi i metadati
		
		if (StringUtils.isBlank(codiceComponenteLocale) 
				|| idDocumentoIlec == null) {
			generaErroreDocumentoNonTrovato(listError);
		}
		DmaccidxTDocumento documento = dmaccIdxDao.selectMetadatiDocumento(idPaziente, idDocumentoIlec, codiceComponenteLocale);
		if (documento == null ) {
			generaErroreDocumentoNonTrovato(listError);			
		}
		return documento;
	}

	
	public boolean pagatoTicket(List<ErroreDettaglioExt> listError, DmaccidxTDocumento documento ) throws DatabaseException,ResponseErrorException {
		if (documento == null ) {
			generaErroreDocumentoNonTrovato(listError);			
		}
		String statoPagamento = documento.getPagatoTicket();
		if (statoPagamento == null  
				|| TICKET_PAGATO.equals(statoPagamento)
				|| TICKET_RIMBORSATO.equals(statoPagamento) 
				|| TICKET_ESENTE_SOGGETTO.equals(statoPagamento)
				|| TICKET_ESENTE_REFERTO.equals(statoPagamento) ) {
			return true;
		} else {
			return false;
		}
	}
	

	public void generaErroreDocumentoNonTrovato(List<ErroreDettaglioExt> listError)
			throws DatabaseException, ResponseErrorException {
		listError.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_195.getCode()));
		throw new ResponseErrorException(
				ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"Verifica esistenza documento: non trovato");
	}
	
	public boolean isMedicoRefertante(String shibIdentitaCodiceFiscale, Long idDocumentoIlec,
			String codiceComponenteLocale) throws DatabaseException {
		boolean result = false;
		if (idDocumentoIlec != null 
				&& !StringUtils.isBlank(codiceComponenteLocale)) {
			List<Medico> mediciRefertanti =  dmaccIdxDao.getMedico(codiceComponenteLocale,idDocumentoIlec);
			if (mediciRefertanti != null && mediciRefertanti.size() > 0) {
				for (Medico m: mediciRefertanti) {
					if (shibIdentitaCodiceFiscale.equalsIgnoreCase(m.getCodiceFiscale())) {
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	public boolean isOscurato(long idDocumentoIlec,String codiceComponenteLocale, String citId) throws DatabaseException {
		if (dmaccIdxDao.isDocumentoOscurato(idDocumentoIlec, codiceComponenteLocale)
				|| dmaccIdxDao.isDocumentoOscuratoAGenitore(idDocumentoIlec, codiceComponenteLocale)
				|| dmaccIdxDao.isDocumentoOscuratoByRicetta(idDocumentoIlec, codiceComponenteLocale, citId)
				|| dmaccIdxDao.isDocumentoOscuratoByEsenzione(idDocumentoIlec, codiceComponenteLocale, citId)) {
			return true;
		} else {
			return false;
		}
	}	
	
	public DmaccTDocumentiQrcode metadatiDocumentoDaQrcode(String qrcode) throws DatabaseException, ResponseErrorException {
		DmaccTDocumentiQrcode tDocumentiQrcode = dmaccIdxDao.selectDocumentoByQrcode(qrcode);
		
		//se col qr code non trovo nessun record, genera errore documento non trovato
		if (tDocumentiQrcode == null) {
			List<ErroreDettaglioExt> listError = new ArrayList<>();
			generaErroreDocumentoNonTrovato(listError);
		//per documenti molto vecchi non c'e' id_documento, provo a recuperarlo tramite codice_documento_dipartimentale (OID)
		} else if (tDocumentiQrcode.getIdDocumento() == null) {
			tDocumentiQrcode.setIdDocumento(dmaccIdxDao.selectIdDocumento(tDocumentiQrcode.getIdPaziente(), 
					tDocumentiQrcode.getCodiceDocumentoDipartimentale(), 
					tDocumentiQrcode.getCodCl()));
		}
		return tDocumentiQrcode;
	}
}
