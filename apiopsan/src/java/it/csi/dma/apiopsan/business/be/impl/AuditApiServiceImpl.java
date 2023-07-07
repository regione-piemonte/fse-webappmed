/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.business.be.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import it.csi.dma.apiopsan.business.be.AuditApi;
import it.csi.dma.apiopsan.dto.PayloadSetAudit;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritImpl;

@Component
public class AuditApiServiceImpl implements AuditApi {

	@Autowired
	CodRLogErroreService codRLogErroreService;
	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;
	@Autowired
	CodLMessaggiService codLMessaggiService;
	@Autowired
	@Qualifier("ValidateGenericMeritImpl")
	private ValidateGenericMeritImpl validateGeneric;

	public Response setAuditOpSan(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale,
			String ruolo, String regime, String collocazione, PayloadSetAudit payloadSetAudit, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		Response response = null;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		try {
			List<ErroreDettaglioExt> errors = validateGeneric.validateAudit(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
					ruolo, collocazione, payloadSetAudit,regime,securityContext, httpHeaders, httpRequest);
			if(errors.isEmpty()){
				codRLogErroreService.insertLogAudit(xRequestId, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xCodiceVerticale, ruolo, regime,
					collocazione, payloadSetAudit);
				response = Response.status(200).build();
			} else {
				ErrorBuilder generateErrorBuilderError = ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, errors);
				codRMessaggioErroreService.saveError(generateErrorBuilderError, httpRequest, xRequestId);
				codLMessaggiService.insertMessage(shibIdentitaCodiceFiscale, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, payloadSetAudit.getCittadinoId(), uuidAsString);
				response = generateErrorBuilderError.generateResponseError();
			}
		} catch (DatabaseException e) {
			Long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
					uuidAsString, Constants.CC_SET_AUDIT, payloadSetAudit.getCittadinoId());
			ErrorBuilder generateErrorBuilderError = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
			codRMessaggioErroreService.saveError( generateErrorBuilderError, httpRequest, xRequestId);
			response = generateErrorBuilderError.generateResponseError();

		}
		return response;
	
	}

	
}
