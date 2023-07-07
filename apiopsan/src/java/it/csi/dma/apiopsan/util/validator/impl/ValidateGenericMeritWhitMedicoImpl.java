/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.validator.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.UtenteDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.ErrorParamEnum;
import it.csi.dma.apiopsan.verificaservices.dma.RichiedenteInfo;
import it.csi.dma.apiopsan.verificaservices.dma.Ruolo;
import it.csi.dma.apiopsan.verificaservices.dmacc.ApplicativoVerticale;
import it.csi.dma.apiopsan.verificaservices.dmacc.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.verificaservices.dmacc.Errore;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaUtenteAbilitatoRequest;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaUtenteAbilitatoResponse;

@Service("ValidateGenericMeritWhitMedicoImpl")
public class ValidateGenericMeritWhitMedicoImpl extends ValidateGenericMeritImpl {
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
	
	@Autowired
	UtenteDao utenteDao;
	
	//validate regimi
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione,  SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse, collocazione,
				securityContext, httpHeaders, httpRequest);
		
		if (!result.isEmpty()) {
			return result;
		}
		
		List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
		errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
		result.addAll(errori);
        
        if (!result.isEmpty()) {
			return result;
		}
        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
        
		return result;
	}
	
	//validate getmmgpaziente
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
			String citId, String idIrec, String idAura,  SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
				  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
				  collocazione,citId,idIrec,idAura,
				  securityContext,
				  httpHeaders,   httpRequest);
		
		if (!result.isEmpty()) {
			return result;
		}
		
		List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
		errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
		result.addAll(errori);
        
        if (!result.isEmpty()) {
			return result;
		}
        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
        
		return result;
	}
	
	//validate getricercapaziente
		@Override
		public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
				String citId, String cognome, String nome,String dataNascita, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
					  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
					  collocazione,citId,cognome,nome,dataNascita,
					  securityContext,
					  httpHeaders,   httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);
	        
	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
	
		//validate getdocumentoepisodi
		@Override
		public List<ErroreDettaglioExt> validateDocumentoEpisodio(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
				String citId, Integer idEpisodio, String codiceComponenteLocale,  SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateDocumentoEpisodio(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
					  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
					  collocazione,citId,idEpisodio,codiceComponenteLocale,
					  securityContext,
					  httpHeaders,   httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		//validate  postsoloepisodi
				@Override
				public List<ErroreDettaglioExt> validateSoloEpisodi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
						String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
						String citId, PayloadSearchEpisodi payloadSearchEpisodi, SecurityContext securityContext,
						HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

					List<ErroreDettaglioExt> result = super.validateSoloEpisodi(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
							  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
							  collocazione,citId,payloadSearchEpisodi,
							  securityContext,
							  httpHeaders,   httpRequest);
					
					if (!result.isEmpty()) {
						return result;
					}
					
					List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
					errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
					result.addAll(errori);

			        if (!result.isEmpty()) {
						return result;
					}
			        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
			        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
			        
					return result;
				}
		
	private VerificaUtenteAbilitatoRequest createVerificaUtenteAbilitatoRequest(String xCodiceVerticale,
			String xCodiceServizio, String shibIdentitaCodiceFiscale, String xForwardedFor, String xRequestId,
			String ruolo) {
		// â¢ numero di transazione= x-Request-Id ***********
		// â¢ CF richiedente= Shib-Identita-CodiceFiscale ***********
		// â¢ Codice ruolo=ruolo ??????
		// â¢ Codice Applicazione richiedente= x-Codice-Servizio ***********
		// â¢ Codice Regime di operativitÃ =AMB ????????
		// â¢ Applicativo Verticale.codice= x-Codice-Verticale ***********
		// â¢ Ip=primo ip di x-Forwarded-for ***********
		// â¢ cfUtente= Shib-Identita-CodiceFiscale ????????
		// â¢ ruoloUtente=ruolo ***********

		VerificaUtenteAbilitatoRequest verificaUtenteAbilitatoRequest = new VerificaUtenteAbilitatoRequest();
		RichiedenteInfo richiedenteInfo = new RichiedenteInfo();
		ApplicativoVerticale applicativoVerticale = new ApplicativoVerticale();
		ApplicazioneRichiedente applicazioneRichiedente = new ApplicazioneRichiedente();
		Ruolo ruoloWs = new Ruolo();

		applicativoVerticale.setCodice(xCodiceVerticale);
		richiedenteInfo.setApplicativoVerticale(applicativoVerticale);

		applicazioneRichiedente.setCodice(xCodiceServizio);
		richiedenteInfo.setApplicazione(applicazioneRichiedente);

		ruoloWs.setCodice(ruolo);
		richiedenteInfo.setRuolo(ruoloWs);

		richiedenteInfo.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedenteInfo.setIp(xForwardedFor);
		richiedenteInfo.setNumeroTransazione(xRequestId);
		verificaUtenteAbilitatoRequest.setRichiedente(richiedenteInfo);

		return verificaUtenteAbilitatoRequest;
	}
	
	private List<ErroreDettaglioExt> callServiceAbilitato (String xCodiceVerticale, String xCodiceServizio, 
			String shibIdentitaCodiceFiscale, String xForwardedFor, String xRequestId,String ruoloFse) throws DatabaseException{
		
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor,
					xRequestId, ruoloFse, errori);
			
			return errori;
	}
	
	private void callServiceAbilitato (String xCodiceVerticale, String xCodiceServizio, String shibIdentitaCodiceFiscale, String xForwardedFor, String xRequestId,
			String ruoloFse, List<ErroreDettaglioExt> errori) throws DatabaseException{
		
		VerificaUtenteAbilitatoRequest verificaUtenteAbilitato = createVerificaUtenteAbilitatoRequest(
				xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);

		VerificaUtenteAbilitatoResponse  verificaUtenteAbilitatoResp = functionalCheckUtenteAbilitato(verificaUtenteAbilitato);
		
        if(verificaUtenteAbilitatoResp.getEsito().equals(RisultatoCodice.FALLIMENTO)) {
        	List<Errore> wsErrors  =verificaUtenteAbilitatoResp.getErrori();
        	for(Errore wsError : wsErrors) {
        		ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
        		erroreDettaglio.setChiave(wsError.getCodice());
        		erroreDettaglio.setValore(wsError.getDescrizione());
        		erroreDettaglio.setErroreId(erroreDettaglio.getErroreId()!=null?erroreDettaglio.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
        		errori.add(erroreDettaglio);
        	}	
        }
				
	}

	public List<ErroreDettaglioExt> validateRicercaDocumenti(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String citId, PayloadSearchTuttiDoc payloadSearchTuttiDoc,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {

		List<ErroreDettaglioExt> result = super.validateRicercaDocumenti(shibIdentitaCodiceFiscale, xRequestId,
				xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
				payloadSearchTuttiDoc, securityContext, httpHeaders, httpRequest);

		if (!result.isEmpty()) {
			return result;
		}
		
		List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
		errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor,
				xRequestId, ruoloFse);
		result.addAll(errori);

		if (!result.isEmpty()) {
			return result;
		}
	
		// verifica SHIB_IDENTITA_CODICE_FISCALE
		validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
        
		return result;

	}
	
	public List<ErroreDettaglioExt> validateRicercaDocumentiCount(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String citId, PayloadSearchTuttiDoc payloadSearchTuttiDoc,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {

		List<ErroreDettaglioExt> result = new ArrayList<>();

		validateShibbolethCodiceFiscale(shibIdentitaCodiceFiscale, result);
		
		validateRequestId(xRequestId, result);
		
		validateForwardedFor(xForwardedFor, result);
		
		validateCodiceServizioFor(xCodiceServizio, result);
		
		validateCodiceVerticale(xCodiceVerticale, result);
		
		validateRuolo(ruolo, xCodiceServizio, result);
		
		validateRuolo(ruoloFse, xCodiceServizio, result);
		
		validateRegime(regime, result);
		
		validateCollocazione(collocazione, result);
		
		validateCittadinoId(citId, result);
		
		validatePayloadSearchTuttiDoc(payloadSearchTuttiDoc, result);
		
		callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor,
				xRequestId, ruoloFse, result);
		
//		List<ErroreDettaglioExt> result = validateRicercaDocumenti(shibIdentitaCodiceFiscale, xRequestId,
//				xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
//				payloadSearchTuttiDoc, securityContext, httpHeaders, httpRequest);

		if (!result.isEmpty()) {
			return result;
		}
		
		
		/*
		 * Nuova verifica Intervallo Date Massimo
		 * RICERCADOC_COUNT_MAX_INTERVALLO
		 */
		validateMaxIntervalloDate(payloadSearchTuttiDoc.getFiltroDocs().getDataInizio(), 
				payloadSearchTuttiDoc.getFiltroDocs().getDataFine(), result);
		
		validateCatalogoTipologiaDocumento(payloadSearchTuttiDoc.getCategoriaTipologia(), result);
		
		// verifica SHIB_IDENTITA_CODICE_FISCALE
		//validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
        
		
		return result;

	}
	

	private void validateCatalogoTipologiaDocumento(List<CategoriaTipologia> categoriaTipologia,
			List<ErroreDettaglioExt> result) throws DatabaseException {
		
		for (CategoriaTipologia item : categoriaTipologia) {
			if (formalCheckCategoriaTipoDocumento(item.getCategoria(), item.getTipologia())) {
				result.add(getValueFormalError(CodeErrorEnum.CategoriaTipoDocumentoNonValido.getCode(),
						ErrorParamEnum.CATEGORIA_TIPO_DOCUMENTO.getCode(), item.getCategoria() + "-"+ item.getTipologia())); 
			}
		}
		
	}

		//validate tipodocumento
		@Override
		public List<ErroreDettaglioExt> validateTipoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione, String regime, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateTipoDocumento(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse, collocazione,regime,
					securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);
	        
	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		//validate getInformativa
		@Override
		public List<ErroreDettaglioExt> validateInformativa(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione,
				String citId, String identificativoStruttura, String identificativoOrganizzazione, String idInformativa,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateInformativa(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
					  xCodiceServizio,  xCodiceVerticale,  ruolo,ruoloFse, regime, collocazione,
					  citId,identificativoStruttura,identificativoOrganizzazione,idInformativa,
					  securityContext,
					  httpHeaders,   httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		@Override
		public List<ErroreDettaglioExt> validateMieiReferti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione, String regime,
				PayloadSearchMieiReferti payloadSearchMieiReferti, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateMieiReferti(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse, collocazione,  regime, payloadSearchMieiReferti,
					securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);
	        
	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		

		//validate getdocumentoCorrellati
		@Override
		public List<ErroreDettaglioExt> validateDocumentiCorrellati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId, Long idDocumentoIlec, String codiceComponenteLocale,String tipoCorrelazioneDocumento,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateDocumentiCorrellati(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
					  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
					  collocazione,citId,idDocumentoIlec,codiceComponenteLocale,tipoCorrelazioneDocumento,
					  securityContext,
					  httpHeaders,   httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		
		
		@Override
		public List<ErroreDettaglioExt> validateDettaglioDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse, String regime,String collocazione,
			    String citId, Long idDocumentoIlec, String codiceComponenteLocale, String categoria, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateDettaglioDoc(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse, regime, collocazione,
				    citId,  idDocumentoIlec,  codiceComponenteLocale,  categoria,
					securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);
	        
	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		//utilizzato da tutti i processi che controllano il ruolo il regime e citid
	//	contattistrutture diete eventi farmaci sintomi rilevazioni dettagliotaccuino screening statoconsensi
		@Override
		public List<ErroreDettaglioExt> validateInfoScreening(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateInfoScreening(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
					  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
					  collocazione,citId,
					  securityContext,
					  httpHeaders,   httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		@Override
		public List<ErroreDettaglioExt> validateComunicazioneConsensi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale,Boolean consensoAlimentazione,Boolean consensoConsultazione,Boolean consensoPregresso, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest,String identificativoOrganizzazione, String IdentificativoAssistitoConsenso) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateComunicazioneConsensi(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, consensoAlimentazione, 
					consensoConsultazione,consensoPregresso, ruolo, ruoloFse, regime, collocazione, citId, securityContext, httpHeaders, httpRequest,
					identificativoOrganizzazione, IdentificativoAssistitoConsenso);
			
//			if (!result.isEmpty()) {
//				return result;
//			}
//			
//			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
//			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
//			result.addAll(errori);
//
//	        if (!result.isEmpty()) {
//				return result;
//			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
			validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	       
			return result;
		}
		
		@Override
		public List<ErroreDettaglioExt> validateGetDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione, String citId, PayloadGetDocumento payloadGetDocumento,
				SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
				throws DatabaseException {
			
			List<ErroreDettaglioExt> result = super.validateGetDocumento(shibIdentitaCodiceFiscale,  
					xRequestId,  xForwardedFor, xCodiceServizio,  xCodiceVerticale, 
					ruolo, ruoloFse, regime,  collocazione,
					citId,  payloadGetDocumento,
					securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			//TODO
			//3.	Call VerificaUtenteAbilitato
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor,
					xRequestId, ruoloFse);
			result.addAll(errori);
			
			
			return result;
		}

		//validate recuperaDocumento
		@Override
		public List<ErroreDettaglioExt> validateRecuperaDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione,String citId, 
				PayloadRecuperaDocumento payloadRecuperaDocumento,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse, collocazione,citId,
					securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			/*
			 * Richiamo servizio verifica utente abilitato
			 */
			 
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);
	        
	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		//validate sMediazioneDocumenti
		@Override
		public List<ErroreDettaglioExt> validateMediazioneDocumenti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione,
				String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateMediazioneDocumenti(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
					idDocumentoIlec, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}

		//validate RestituibilitaDocumento
		@Override
		public List<ErroreDettaglioExt> validateRestituibilitaDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione,
				String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateRestituibilitaDocumento(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
					idDocumentoIlec, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        

			 //check codice servizio no DMAWA, quest'api non deve essere chiamata
			 //TODO: gestire logiche di risposta se chimanate <> DMAWA
			 if (!Constants.CODICE_SERVIZIO.equals(xCodiceServizio)
					 && !Constants.CODICE_CONTATTO_DIGITALE.equals(xCodiceServizio)) {
				 result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.X_CODICE_SERVIZIO.getCode(), xCodiceServizio));
			 }
			return result;
		}		
		
		@Override
		public List<ErroreDettaglioExt> validatePrenotazioneImmagini(String shibIdentitaCodiceFiscale,String xRequestId,String xForwardedFor,String xCodiceServizio,
				String xCodiceVerticale,String ruolo,String regime,String collocazione, String ruoloFse, ScaricoStudi scaricoStudi,SecurityContext securityContext, 
				HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) throws DatabaseException{
			String methodName = "validatePrenotazioneImmagini";
			logInfo(methodName, "BEGIN");
			
			List<ErroreDettaglioExt> result =  super.validatePrenotazioneImmagini(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, 
					collocazione, scaricoStudi, securityContext, httpHeaders, httpRequest);
	        if (!result.isEmpty()) {
				return result;
			}
	        	        
			result = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);

	        return result;
		}
		
	public List<ErroreDettaglioExt> validateDettaglioPrestazioniDoc(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, Long idDocumentoIlec, String citId, String codiceComponenteLocale, SecurityContext securityContext, 
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

		List<ErroreDettaglioExt> result = super.validateDettaglioPrestazioniDoc(shibIdentitaCodiceFiscale,
				xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, idDocumentoIlec,
				citId, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);

		if (!result.isEmpty()) {
			return result;
		}
		
		List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
		errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor,
				xRequestId, ruoloFse);
		result.addAll(errori);

		if (!result.isEmpty()) {
			return result;
		}
		
		// verifica SHIB_IDENTITA_CODICE_FISCALE
		validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
        
		
        		
		return result;

	}	
	
	public List<ErroreDettaglioExt> validateVerificaStatoPacchetto(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione,
			String codiceFiscale, String idDocumentoIlec, String codCl) throws DatabaseException {
		String methodName = "validateVerificaStatoPacchetto";
		logInfo(methodName, "BEGIN");
		
		List<ErroreDettaglioExt> result =  super.validateVerificaStatoPacchetto(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, codiceFiscale, idDocumentoIlec, codCl);
        if (!result.isEmpty()) {
			return result;
		}
        	        
		result = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);

        return result;
		
	}

	public List<ErroreDettaglioExt> validateCCGetDocumentoPersonale(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String citId, Long idDocumento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

		List<ErroreDettaglioExt> result =  super.validateCCGetDocumentoPersonale(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
				xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId, idDocumento);
        if (!result.isEmpty()) {
			return result;
		}
        	        
		result = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);

        return result;
	}
	
	//validate  utlimodocumento
		@Override
		public List<ErroreDettaglioExt> validateUltimoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
				String citId, String tipoDocumento, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateUltimoDocumento(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
					  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
					  collocazione,citId,tipoDocumento,
					  securityContext,
					  httpHeaders,   httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		//validate  contattiStrutture
		@Override
		public List<ErroreDettaglioExt> validateContattiStrutture(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse, String collocazione,
				String citId,SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateContattiStrutture(shibIdentitaCodiceFiscale,xRequestId, xForwardedFor,
					 xCodiceServizio,  xCodiceVerticale,  ruolo,ruoloFse,collocazione,
					 citId, securityContext,  httpHeaders,  httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
		
		//validate validateListaDolori
		@Override
		public List<ErroreDettaglioExt> validateScansionaQRCode(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio,String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione,
				String qrcode,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateScansionaQRCode(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, qrcode,
					securityContext, httpHeaders, httpRequest);

			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        
			return result;
		}
			
		//validate validateListaDolori
		@Override
		public List<ErroreDettaglioExt> validateListaDolori(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,String regime,
				String collocazione, String citId, Long taccuinoId, Integer limit, Integer offset, String ordinamento,
				String da, String a, SecurityContext securityContext, HttpHeaders httpHeaders,
				HttpServletRequest httpRequest) throws DatabaseException {

			List<ErroreDettaglioExt> result = super.validateListaDolori(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
					taccuinoId, limit, offset, ordinamento, da, a, securityContext, httpHeaders, httpRequest);
			
			if (!result.isEmpty()) {
				return result;
			}
			
			List<ErroreDettaglioExt> errori = new ArrayList<ErroreDettaglioExt>();
			errori = callServiceAbilitato(xCodiceVerticale, xCodiceServizio, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId, ruoloFse);
			result.addAll(errori);

	        if (!result.isEmpty()) {
				return result;
			}
	        
	        validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	        //commentato il servizio ed implementato il metodo di verifica nella tabella utente serve per evitare errori nella fase di logging

	        return result;
		}
}
