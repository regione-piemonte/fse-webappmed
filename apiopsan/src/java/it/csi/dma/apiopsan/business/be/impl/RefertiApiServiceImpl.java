/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.business.be.impl;

import it.csi.dma.apiopsan.business.be.*;

import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.integration.helper.impl.PrenotaPacchettoImmagini;
import it.csi.dma.apiopsan.integration.helper.impl.ScansionaQRCode;

import javax.ws.rs.core.Context;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.business.be.RefertiApi;
import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.integration.helper.impl.VerificaStatoPacchetto;

@Service
public class RefertiApiServiceImpl implements RefertiApi {

	@Autowired
	PrenotaPacchettoImmagini prenotaPacchettoImmaginiService;

	@Autowired
	VerificaStatoPacchetto verificaStatoPacchetto;
	
	@Autowired
	ScansionaQRCode scansionaQRCode;

	public Response prenotaPacchettoImmagini(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			ScaricoStudi scaricoStudi, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		// do some magic!
		return prenotaPacchettoImmaginiService.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, scaricoStudi, securityContext,
				httpHeaders, httpRequest);
	}

	public Response scansionaQRCode(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String qrcode, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		// method execute presente nella classe scansionaQRCode nel package integration
		return scansionaQRCode.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, qrcode, securityContext, httpHeaders, httpRequest);
	}

	public Response verificaStatoPacchetto(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String codiceFiscale, String idDocumentoIlec, String codCl, String codDocumentoDipartimentale,
			String archivioDocumentoIlec, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		// do some magic!
		return verificaStatoPacchetto.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, codiceFiscale, idDocumentoIlec, codCl,
				codDocumentoDipartimentale, archivioDocumentoIlec, securityContext, httpHeaders, httpRequest);
	}
}
