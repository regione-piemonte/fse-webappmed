/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import javax.servlet.http.HttpServletRequest;

import it.csi.dma.apiopsan.dto.PayloadSetAudit;
import it.csi.dma.apiopsan.util.ErrorBuilder;

public interface CodRLogErroreService {

	ErrorBuilder saveLog(ErrorBuilder error, HttpServletRequest httpRequest,String uid, String codiceservizio);

	long traceLogInsert(String inf_aggiuntive, String uuidAsString, String serviceimpl, String tipo_esito);

	void traceLogAuditInsert(String inf_aggiuntive, String uuidAsString, String listaout, String cod_successo,
			String shibIdentitaCodiceFiscale, String collocazione, String xForwardedFor, String xCodiceVerticale,
			String xCodiceServizio, String ruolo, String Regime, String citId, String[] array,Long iddocumento,String codiceCl);
	
	void insertLogAudit(String xRequestId, String xCodiceServizio, String shibIdentitaCodiceFiscale, String xforward, String applicazioneVerticale, String ruolo, String regime, String collocazione, PayloadSetAudit payloadSetAudit);

}
