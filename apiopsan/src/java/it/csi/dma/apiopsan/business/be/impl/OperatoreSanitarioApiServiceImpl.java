/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.dma.apiopsan.business.be.OperatoreSanitarioApi;
import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.integration.helper.impl.Referti;
import it.csi.dma.apiopsan.integration.helper.impl.Regimi;

@Component
public class OperatoreSanitarioApiServiceImpl implements OperatoreSanitarioApi {

	@Autowired
	Regimi regimi;
	
	@Autowired
	Referti referti;

	public Response getMieiReferti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			PayloadSearchMieiReferti payloadSearchMieiReferti, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		return referti.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, collocazione, regime, payloadSearchMieiReferti, securityContext, httpHeaders, httpRequest);
	}

	public Response getRegimi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,  String collocazione,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return regimi.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, collocazione, securityContext, httpHeaders, httpRequest);
	}
}
