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
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.business.be.CittadiniApi;
import it.csi.dma.apiopsan.dto.PayloadComunicazioneConsensi;
import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.PayloadGetRilevazioni;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.integration.helper.impl.CCGetDocumentoPersonale;
import it.csi.dma.apiopsan.integration.helper.impl.ComunicazioneConsensi;
import it.csi.dma.apiopsan.integration.helper.impl.ContattiStrutture;
import it.csi.dma.apiopsan.integration.helper.impl.DettaglioDoc;
import it.csi.dma.apiopsan.integration.helper.impl.DettaglioPrestazioniDoc;
import it.csi.dma.apiopsan.integration.helper.impl.DettaglioTaccuino;
import it.csi.dma.apiopsan.integration.helper.impl.Diete;
import it.csi.dma.apiopsan.integration.helper.impl.DocumentiCorrelati;
import it.csi.dma.apiopsan.integration.helper.impl.DocumentiEpisodio;
import it.csi.dma.apiopsan.integration.helper.impl.Esenzioni;
import it.csi.dma.apiopsan.integration.helper.impl.Eventi;
import it.csi.dma.apiopsan.integration.helper.impl.Farmaci;
import it.csi.dma.apiopsan.integration.helper.impl.GetScreening;
import it.csi.dma.apiopsan.integration.helper.impl.InformativaCorrente;
import it.csi.dma.apiopsan.integration.helper.impl.IsMediazioneDocumento;
import it.csi.dma.apiopsan.integration.helper.impl.ListaDolori;
import it.csi.dma.apiopsan.integration.helper.impl.ListaSintomi;
import it.csi.dma.apiopsan.integration.helper.impl.MMGPaziente;
import it.csi.dma.apiopsan.integration.helper.impl.MediazioneDocumento;
import it.csi.dma.apiopsan.integration.helper.impl.RecuperoDocumentoPO;
import it.csi.dma.apiopsan.integration.helper.impl.RestituibilitaDocumento;
import it.csi.dma.apiopsan.integration.helper.impl.RicercaDocumenti;
import it.csi.dma.apiopsan.integration.helper.impl.RicercaPaziente;
import it.csi.dma.apiopsan.integration.helper.impl.Rilevazioni;
import it.csi.dma.apiopsan.integration.helper.impl.SoloEpisodi;
import it.csi.dma.apiopsan.integration.helper.impl.StatoConsensi;
import it.csi.dma.apiopsan.integration.helper.impl.UltimoDocumento;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.RecuperaDocumentoPdf;

@Service
public class CittadiniApiServiceImpl implements CittadiniApi {

	@Autowired
	private StatoConsensi statoConsensi;

	@Autowired
	private DettaglioDoc dettaglioDoc;

	@Autowired
	MMGPaziente mmgpaziente;

	@Autowired
	DocumentiEpisodio documentiEpisodio;

	@Autowired
	RecuperaDocumentoPdf recuperaDocumentoPdf;
	
	@Autowired
	DocumentiCorrelati documentiCorrelati;

	@Autowired
	SoloEpisodi soloEpisodi;

	@Autowired
	private RicercaPaziente ricercapziente;

	@Autowired
	private RicercaDocumenti ricercaDocumenti;
	
	@Autowired
	InformativaCorrente informativaCorrente;
	
	@Autowired
	ComunicazioneConsensi comunicazioneConsensi;
	
	@Autowired
	private RecuperoDocumentoPO recuperoDocumentoPo;
	
	@Autowired
	GetScreening screening;

	@Autowired
	private Esenzioni esenzioni;

	@Autowired
	MediazioneDocumento mediazioneDocumento;
	
	@Autowired
	DettaglioPrestazioniDoc dettaglioPrestazioniDoc;
	
	@Autowired
	CCGetDocumentoPersonale ccGetDocumentoPersonale;
	
	@Autowired
	UltimoDocumento ultimoDocumento;
	
	@Autowired
	DettaglioTaccuino dettaglioTaccuino;
	
	@Autowired
	Farmaci farmaci;
	
	@Autowired
	Eventi eventi;
	
	@Autowired
	Diete diete;
	
	@Autowired
	ListaSintomi listaSintomi;
	
	@Autowired
	ContattiStrutture contattiStrutture;
	
	@Autowired
	ListaDolori listaDolori;
	
	@Autowired
	Rilevazioni rilevazioni;
	
	@Autowired
	IsMediazioneDocumento ismediazioneDocumento;
	
	@Autowired
	RestituibilitaDocumento restituibilitaDocumento;
	
	public Response comunicazioneConsenso(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, PayloadComunicazioneConsensi payloadComunicazioneConsensi, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// do some magic!
		return comunicazioneConsensi.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, citId, payloadComunicazioneConsensi, securityContext, httpHeaders, httpRequest);
	}

	public Response getContattiStrutture(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// do some magic!
		return contattiStrutture.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, limit, offset, ordinamento, dataDa, dataA, securityContext, httpHeaders, httpRequest);
	}

	public Response getDiete(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return diete.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
				xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, limit, offset, ordinamento, dataDa, dataA, 
				securityContext, httpHeaders, httpRequest);
	}

	public Response getDocumentiCorrelati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, String tipoCorrelazioneDocumento,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// do some magic!
		return documentiCorrelati.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale,
				tipoCorrelazioneDocumento, securityContext, httpHeaders, httpRequest);
	}

	public Response getDocumentiEpisodio(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Integer idEpisodio, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// do some magic!
		return documentiEpisodio.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, idEpisodio, codiceComponenteLocale,
				securityContext, httpHeaders, httpRequest);
	}

	public Response getDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, PayloadGetDocumento payloadGetDocumento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return recuperaDocumentoPdf.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, 
				ruolo, regime, collocazione, citId, payloadGetDocumento, securityContext, 
				httpHeaders, httpRequest);
	}

	public Response getDolori(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// method execute presente nella classe listaDolori nel package integration
		return listaDolori.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, limit, offset, ordinamento, dataDa, dataA,
				securityContext, httpHeaders, httpRequest);
	}

	public Response getEsenzioni(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		return esenzioni.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, securityContext, httpHeaders, httpRequest);
	}

	public Response getEventi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return eventi.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
				xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, limit, offset, ordinamento, dataDa, dataA, 
				securityContext, httpHeaders, httpRequest);
	}

	public Response getFarmaci(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return farmaci.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
				xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, limit, offset, ordinamento, dataDa, dataA, 
				securityContext, httpHeaders, httpRequest);
	}

	public Response getInformativa(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, String identificativoStruttura, String identificativoOrganizzazione, String idInformativa,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// do some magic!
		return informativaCorrente.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, identificativoStruttura,
				identificativoOrganizzazione, idInformativa, securityContext, httpHeaders, httpRequest);

	}

	public Response getMMGPaziente(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, String idIrec, String idAura, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		return mmgpaziente.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, idIrec, idAura, securityContext, httpHeaders,
				httpRequest);
	}

	public Response getDettaglioDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, String categoria, String tipoMedico,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return dettaglioDoc.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale,
				categoria, tipoMedico, securityContext, httpHeaders, httpRequest);
	}

	public Response getRilevazioni(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, PayloadGetRilevazioni payloadGetRilevazioni, Integer limit, Integer offset,
			String ordinamento, String dataDa, String dataA, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		return rilevazioni.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, payloadGetRilevazioni, limit, offset, ordinamento, dataDa, dataA, securityContext, httpHeaders, httpRequest);
	}

	public Response getSintomi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return listaSintomi.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, citId, taccuinoId, limit, offset, ordinamento, dataDa, dataA, securityContext, httpHeaders, httpRequest);
	}

	public Response getSoloEpisodi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, PayloadSearchEpisodi payloadSearchEpisodi, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return soloEpisodi.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, payloadSearchEpisodi, securityContext,
				httpHeaders, httpRequest);
	}

	public Response getTaccuino(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return dettaglioTaccuino.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
				xCodiceVerticale, ruolo, regime, collocazione, citId, securityContext, httpHeaders, httpRequest);	
	}

	public Response getTuttiDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, PayloadSearchTuttiDoc payloadSearchTuttiDoc, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return ricercaDocumenti.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, payloadSearchTuttiDoc, securityContext,
				httpHeaders, httpRequest);
	}
	

	public Response infoScreening(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		// do some magic!
		return screening.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, citId, securityContext, httpHeaders, httpRequest);
	}

	public Response mediazioneDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// method execute presente nella classe mediazioneDocumento nel package integration
		return mediazioneDocumento.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
	   			  xCodiceVerticale, ruolo, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale,
	   			  securityContext, httpHeaders, httpRequest);
	}

	public Response recuperaDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String collocazione, String citId,
			PayloadRecuperaDocumento payloadRecuperaDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		// do some magic!
		return recuperoDocumentoPo.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione, citId, payloadRecuperaDocumento, securityContext,
				httpHeaders, httpRequest);
	}

	public Response ricercaPaziente(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, String nome, String cognome, String dataNascita, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		// do some magic!
		return ricercapziente.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, cognome, nome, dataNascita, securityContext,
				httpHeaders, httpRequest);
	}

	public Response statoConsenso(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		return statoConsensi.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getDettaglioPrestazioniDoc(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		return dettaglioPrestazioniDoc.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, citId, idDocumentoIlec, codiceComponenteLocale, 
				securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getUltimoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, String tipoDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return ultimoDocumento.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione,citId, tipoDocumento, 
				securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response getDocumentoPersonale(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return ccGetDocumentoPersonale.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, regime, collocazione, citId, idDocumentoIlec, securityContext, httpHeaders,
				httpRequest);
	}

	@Override
	public Response isDocumentoSmediabile(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		return ismediazioneDocumento.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
	   			  xCodiceVerticale, ruolo, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale,
	   			  securityContext, httpHeaders, httpRequest);
	}

	@Override
	public Response isDocumentoRestituibile(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		return restituibilitaDocumento.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, 
	   			  xCodiceVerticale, ruolo, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale,
	   			  securityContext, httpHeaders, httpRequest);
	}
	
}
