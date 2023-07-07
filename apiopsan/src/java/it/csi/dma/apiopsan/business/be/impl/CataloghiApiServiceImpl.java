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

import it.csi.dma.apiopsan.business.be.CataloghiApi;
import it.csi.dma.apiopsan.integration.helper.impl.TipoDocumento;
import it.csi.dma.apiopsan.integration.helper.impl.ElencoTipoDocumentoCategoria;

@Component
public class CataloghiApiServiceImpl implements CataloghiApi {

	@Autowired
	TipoDocumento tipodocumento;

	@Autowired
	ElencoTipoDocumentoCategoria elencoTipoDocumentoCategoria;

	public Response getTipoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return tipodocumento.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione, regime, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getElencoTipoDocumentoCategoria(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return elencoTipoDocumentoCategoria.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, securityContext, httpHeaders, httpRequest);
	}

}
