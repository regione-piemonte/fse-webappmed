/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.dma.apiopsan.dto.PayloadSetAudit;
import it.csi.dma.apiopsan.dto.custom.LMessaggi;
import it.csi.dma.apiopsan.exception.DatabaseException;

public interface CodLMessaggiService {

	int updateMessaggi(LMessaggi lMessaggi, Long id);
 
	long traceMessaggiInsert(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String collocazione,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, String uuidAsString,String inf_aggiuntive, String citId);

	void traceMessaggiUpdate(long id_xml, String uuidAsString, String esito, String tipo_esito);
	
	public long insertMessage(String shibIdentitaCodiceFiscale, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo,
			String citId, String uuidAsString) throws DatabaseException;

}