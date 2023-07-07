/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.validator.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.PayloadSetAudit;
import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.ErrorParamEnum;

@Service("ValidateGenericMeritImpl")
public class ValidateGenericMeritImpl extends ValidateGenericImpl {

	/*
	 * SECONDO LIVELLO DI VERIFICA : VERIFICHE FORMALI
	 */
	// validate getregimi
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String collocazione, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
				ruoloFse, collocazione, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		return result;
	}

	// validate getmmgpaziente
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, String idIrec, String idAura,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
				ruoloFse, regime, collocazione, citId, idIrec, idAura, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);

		return result;
	}

	// validate ricercapaziente
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, String cognome, String nome,
			String dataNascita, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
				ruoloFse, regime, collocazione, citId, cognome, nome, dataNascita, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		if (formalCheckDataNascita(dataNascita)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_205.getCode(), ErrorParamEnum.DATA_NASCITA.getCode(), dataNascita));
		}
		// aggiungo il controllo sulla lunghezza min di nome e cognome
		if (Util.isValorizzato(nome)) {
			if (formalCheckLenStringSearch(Constants.LENGTH_PAZIENT_SEARCH, nome)) {
				result.add(getValueFormalError(CodeErrorEnum.CC_ER_125.getCode(), ErrorParamEnum.NOME.getCode(), nome));
			}
		}

		if (Util.isValorizzato(cognome)) {
			if (formalCheckLenStringSearch(Constants.LENGTH_PAZIENT_SEARCH, cognome)) {
				result.add(getValueFormalError(CodeErrorEnum.CC_ER_125.getCode(), ErrorParamEnum.COGNOME.getCode(), cognome));
			}
		}

		return result;
	}

	// validate getdocumentoepisodio
	@Override
	public List<ErroreDettaglioExt> validateDocumentoEpisodio(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, Integer idEpisodio,
			String codiceComponenteLocale, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateDocumentoEpisodio(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId, idEpisodio, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		if (formalCheckComponenteLocale(codiceComponenteLocale)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_007.getCode(), ErrorParamEnum.CODICE_CL.getCode(), codiceComponenteLocale));
		}

		return result;
	}

	private List<ErroreDettaglioExt> formalCheck(List<ErroreDettaglioExt> result, String shibIdentitaCodiceFiscale, String xCodiceServizio,
			String xCodiceVerticale, String ruolo) throws DatabaseException {

		if (formalCheckCF(shibIdentitaCodiceFiscale)) {
			result.add(
					getValueFormalError(CodeErrorEnum.FSE_ER_562.getCode(), ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale));
		}

		if (formalCheckCodiceVerticale(xCodiceVerticale)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_529.getCode(), ErrorParamEnum.X_CODICE_VERTICALE.getCode(), xCodiceVerticale));
		}

		if (formalCheckCodiceServizio(xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.X_CODICE_SERVIZIO.getCode(), xCodiceServizio));
		}

		if (formalCheckPresenzaRuolo(ruolo, xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_509.getCode(), ErrorParamEnum.RUOLO.getCode(), ruolo));
		}
		return result;
	}

	// validate postsoloepisodi
	@Override
	public List<ErroreDettaglioExt> validateSoloEpisodi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, PayloadSearchEpisodi payloadSearchEpisodi,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateSoloEpisodi(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, regime, collocazione, citId, payloadSearchEpisodi, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);



		for (String tipodoc : payloadSearchEpisodi.getTipologiaDocumento()) {
			validateTipologiaDocumento(tipodoc, result);
			
		}

		validateDateRicerca(payloadSearchEpisodi.getFiltroDocs().getDataInizio(), 
				payloadSearchEpisodi.getFiltroDocs().getDataFine(), result);

		if (formalCheckCategoria(payloadSearchEpisodi.getCategoria())) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_570.getCode(), ErrorParamEnum.CATEGORIA.getCode(), payloadSearchEpisodi.getCategoria()));
		}
		return result;
	}

	// validate getTuttiDoc
	@Override
	public List<ErroreDettaglioExt> validateRicercaDocumenti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId,
			PayloadSearchTuttiDoc payloadSearchTuttiDoc, SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateRicercaDocumenti(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId, payloadSearchTuttiDoc, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		validateDateRicerca(payloadSearchTuttiDoc.getFiltroDocs().getDataInizio(), payloadSearchTuttiDoc.getFiltroDocs().getDataFine(), result);

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		for (CategoriaTipologia categoriaTipologia : payloadSearchTuttiDoc.getCategoriaTipologia()) {
			validateTipologiaDocumento(categoriaTipologia.getTipologia(), result);
			validateCategoriaDocumento(categoriaTipologia.getCategoria(), result);
		}

		return result;
	}

	// validate gettipodocumento
	@Override
	public List<ErroreDettaglioExt> validateTipoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String collocazione, String regime, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateTipoDocumento(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, collocazione, regime, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		return result;
	}

	// validate getInformativa
	public List<ErroreDettaglioExt> validateInformativa(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, String identificativoStruttura,
			String identificativoOrganizzazione, String idInformativa, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {
		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateInformativa(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, regime, collocazione, citId, identificativoStruttura, identificativoOrganizzazione, idInformativa, securityContext,
				httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validateMieiReferti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String collocazione, String regime, PayloadSearchMieiReferti payloadSearchMieiReferti,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateMieiReferti(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, collocazione, regime, payloadSearchMieiReferti, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		return result;
	}

	// validate getdocumentiCorrellati
	@Override
	public List<ErroreDettaglioExt> validateDocumentiCorrellati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId,
			Long idDocumentoIlec, String codiceComponenteLocale, String tipoCorrelazioneDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateDocumentiCorrellati(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale, tipoCorrelazioneDocumento,
				securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		if (formalCheckComponenteLocale(codiceComponenteLocale)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_007.getCode(), ErrorParamEnum.CODICE_CL.getCode(), codiceComponenteLocale));
		}

		if (formalCheckTipoCorrelazioneDocumento(tipoCorrelazioneDocumento)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.TIPO_CORRELAZIONE_DOCUMENTO.getCode(), tipoCorrelazioneDocumento));
		}

		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validateDettaglioDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, Long idDocumentoIlec,
			String codiceComponenteLocale, String categoria, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateDettaglioDoc(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale, categoria, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validateInfoScreening(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateInfoScreening(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, regime, collocazione, citId, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


//			if (formalCheckComponenteLocale(codiceComponenteLocale)) {
//				result.add(getValueFormalError(CodeErrorEnum.CC_ER_007 .getCode(),
//						ErrorParamEnum.CODICE_CL.getCode(),codiceComponenteLocale));
//			}
//			
//			if(formalCheckTipoCorrelazioneDocumento(tipoCorrelazioneDocumento)) {
//				result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(),
//						ErrorParamEnum.TIPO_CORRELAZIONE_DOCUMENTO.getCode(),tipoCorrelazioneDocumento));
//			}
//			
		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validateComunicazioneConsensi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, Boolean consensoAlimentazione, Boolean consensoConsultazione, Boolean consensoPregresso,
			String ruolo, String ruoloFse, String regime, String collocazione, String citId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, String identificativoOrganizzazione, String IdentificativoAssistitoConsenso) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateComunicazioneConsensi(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, consensoAlimentazione, consensoConsultazione, consensoPregresso, ruolo, ruoloFse, regime, collocazione, citId,
				securityContext, httpHeaders, httpRequest, identificativoOrganizzazione,IdentificativoAssistitoConsenso);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validateGetDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, PayloadGetDocumento payloadGetDocumento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {

		List<ErroreDettaglioExt> result = super.validateGetDocumento(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
				ruolo, ruoloFse, regime, collocazione, citId, payloadGetDocumento, securityContext, httpHeaders, httpRequest);

		if (!result.isEmpty()) {
			return result;
		}

		//controlli conformita' formale di

		// SPECIFICHE DMA-MED-SER-18-V01 || CODICE || DBT5ISAN
		// Codice Applicazione richiedente - alias X-Codice-Servizio - alias
		// xCodiceServizio - alias "Codice Servizio" - D_APPLICAZIONE_RICHIEDENTE
		// Codice Verticale richiedente - alias X-Codice-Appl-Verticale - alias
		// xCodiceVerticale - alias "Codice applicazione richiedente" -
		// D_APPLICATIVO_VERTICALE
		// Shib-Identita-CodiceFiscale - dmacc_t_utente
		// CF Paziente
		// CodiceComponenteLocale
		String codiceComponenteLocale = payloadGetDocumento.getCodiceComponenteLocale();		
		
		// shibIdentitaCodiceFiscale
		if (formalCheckCF(shibIdentitaCodiceFiscale)) {
			// cf non formalmente corretto
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale));
		} else if (formalCheckPresenzaUtente(shibIdentitaCodiceFiscale)) {
			// cf non esiste in t_utente
			result.add(
					getValueFormalError(CodeErrorEnum.FSE_ER_562.getCode(), ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale));
		}

		// xCodiceServizio
		if (formalCheckCodiceServizio(xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.X_CODICE_SERVIZIO.getCode(), xCodiceServizio));
		}

		// xCodiceVerticale
		if (formalCheckCodiceVerticale(xCodiceVerticale)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_529.getCode(), ErrorParamEnum.X_CODICE_VERTICALE.getCode(), xCodiceVerticale));
		}

		// citId
		validateCittadinoId(citId, result);

		/*
		 * Posso esserci fuori regione, non necessariamente presenti in t_paziente
		else if (formalCheckPresenzaPaziente(citId)) {
			// cf non presente in t_paziente
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.CIT_ID.getCode(), citId));

		}
		*/

		// verifica esistenza codiceComponenteLocale se presente
		if (! StringUtils.isBlank(codiceComponenteLocale)) {
			if (formalCheckComponenteLocale(codiceComponenteLocale)) {
				result.add(getValueFormalError(CodeErrorEnum.CC_ER_007.getCode(), ErrorParamEnum.CODICE_CL.getCode(), codiceComponenteLocale));
			}
		}

		// b) Verifica del ruolo
		if (StringUtils.isBlank(ruoloFse)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_509.getCode(), ErrorParamEnum.RUOLO.getCode(), ruolo));
		}

		return result;

	}

	// validate recuperadocumento
	@Override
	public List<ErroreDettaglioExt> validateRecuperaDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String ruoloFse, String collocazione, String citId, PayloadRecuperaDocumento payloadRecuperaDocumento,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
				ruoloFse, collocazione, citId, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		return result;
	}

	// validate sMediazioneDocumenti
	@Override
	public List<ErroreDettaglioExt> validateMediazioneDocumenti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId,
			Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateMediazioneDocumenti(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale, securityContext, httpHeaders,
				httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		if (formalCheckComponenteLocale(codiceComponenteLocale)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_007.getCode(), ErrorParamEnum.CODICE_CL.getCode(), codiceComponenteLocale));
		}

		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validatePrenotazioneImmagini(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, ScaricoStudi scaricoStudi,
			SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
		String methodName = "validatePrenotazioneImmagini";
		logInfo(methodName, "BEGIN");
		List<ErroreDettaglioExt> result = super.validatePrenotazioneImmagini(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, scaricoStudi, securityContext, httpHeaders, httpRequest);

		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		return result;
	}

	public List<ErroreDettaglioExt> validateDettaglioPrestazioniDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, Long idDocumentoIlec, String citId,
			String codiceComponenteLocale, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {

		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validateDettaglioPrestazioniDoc(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, idDocumentoIlec, citId, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		return result;
	}

	public List<ErroreDettaglioExt> validateVerificaStatoPacchetto(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String codiceFiscale,
			String idDocumentoIlec, String codCl) throws DatabaseException {
		String methodName = "validateVerificaStatoPacchetto";
		logInfo(methodName, "BEGIN");
		List<ErroreDettaglioExt> result = super.validateVerificaStatoPacchetto(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, codiceFiscale, idDocumentoIlec, codCl);

		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(codiceFiscale, result);

		try {
			Integer.parseInt(idDocumentoIlec);
		}catch(NumberFormatException e) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(),
					ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode(),xCodiceServizio));
		}
		return result;
	}

	public List<ErroreDettaglioExt> validateCCGetDocumentoPersonale(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione, String citId, Long idDocumento)
			throws DatabaseException {

		String methodName = "validateCCGetDocumentoPersonale";
		logInfo(methodName, "BEGIN");
		List<ErroreDettaglioExt> result = super.validateCCGetDocumentoPersonale(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId, idDocumento);

		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo);

		validateCittadinoId(citId, result);


		return result;
	}

	public List<ErroreDettaglioExt> validateAudit(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor, String xCodiceServizio,
			String xCodiceVerticale, String ruolo,  String collocazione, PayloadSetAudit payloadSetAudit, String regime, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws DatabaseException{
		// CONTROLLI GENERICI
		List<ErroreDettaglioExt> result = super.validate(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
				null, collocazione, securityContext, httpHeaders, httpRequest);

		// --- errori del merito ---
		if (!result.isEmpty()) {
			return result;
		}

		result = formalCheckAudit(result, shibIdentitaCodiceFiscale, xCodiceServizio, xCodiceVerticale, ruolo, payloadSetAudit.getCodiceAudit(), regime);

		return result;
	}

	private List<ErroreDettaglioExt> formalCheckAudit(List<ErroreDettaglioExt> result, String shibIdentitaCodiceFiscale, String xCodiceServizio,
			String xCodiceVerticale, String ruolo, String codiceAudit, String codiceRegime) throws DatabaseException {
//		if (formalCheckCF(shibIdentitaCodiceFiscale)) {
//			result.add(
//					getValueFormalError(CodeErrorEnum.FSE_ER_562.getCode(), ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale));
//		}
//
//		if (formalCheckCodiceVerticale(xCodiceVerticale)) {
//			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_529.getCode(), ErrorParamEnum.X_CODICE_VERTICALE.getCode(), xCodiceVerticale));
//		}

		if (StringUtils.isEmpty(codiceAudit)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),	ErrorParamEnum.CODICE_AUDIT.getCode()));
		}
		if (StringUtils.isEmpty(codiceRegime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.REGIME.getCode()));
		}
		
		if (formalCheckCodiceServizio(xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.X_CODICE_SERVIZIO.getCode(), xCodiceServizio));
		}

		if (formalCheckPresenzaRuolo(ruolo, xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_509.getCode(), ErrorParamEnum.RUOLO.getCode(), ruolo));
		}
		if (formalCheckPresenzaAudit(codiceAudit)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.AUDIT.getCode(), ruolo));
		}
		if (formalCheckPresenzaRegimi(codiceRegime)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.REGIME.getCode(), ruolo));
		}
		
		
		return result;
	}
	
	//validate ultimodocumento
			@Override
			public List<ErroreDettaglioExt> validateUltimoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
					String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
					String citId, String tipoDocumento,
					SecurityContext securityContext,
					HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
				
				// CONTROLLI GENERICI
				List<ErroreDettaglioExt> result = super.validateUltimoDocumento( shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
						  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,regime,
						  collocazione,citId,tipoDocumento,
						  securityContext,
						  httpHeaders,   httpRequest);
				
				// --- errori del merito ---
				if (!result.isEmpty()) {
					return result;
				}
				
				result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio,
						xCodiceVerticale, ruolo);	
				
				validateCittadinoId(citId, result);

				
				validateTipologiaDocumento(tipoDocumento, result);
				
				return result;
			}
			
			//validate contattiStrutture
			@Override
			public List<ErroreDettaglioExt> validateContattiStrutture(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
					String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse, String collocazione,
					String citId,SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
				
				// CONTROLLI GENERICI
				List<ErroreDettaglioExt> result = super.validateContattiStrutture(shibIdentitaCodiceFiscale,xRequestId, xForwardedFor,
						 xCodiceServizio,  xCodiceVerticale,  ruolo,ruoloFse,collocazione,
						 citId, securityContext,  httpHeaders,  httpRequest);
				
				// --- errori del merito ---
				if (!result.isEmpty()) {
					return result;
				}
				
				result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio,
						xCodiceVerticale, ruolo);	
				
				validateCittadinoId(citId, result);

			
				return result;
			}
			
			//validate validateScansionaQRCode
			@Override
			public List<ErroreDettaglioExt> validateScansionaQRCode(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
					String xCodiceServizio,String xCodiceVerticale, String ruolo, String ruoloFse,
					String regime, String collocazione,
					String qrcode,
					SecurityContext securityContext,
					HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {
				
				// CONTROLLI GENERICI
				List<ErroreDettaglioExt> result = super.validateScansionaQRCode(shibIdentitaCodiceFiscale, xRequestId,
						xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, qrcode,
						securityContext, httpHeaders, httpRequest);

				// --- errori del merito ---
				if (!result.isEmpty()) {
					return result;
				}
				
				result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio,
						xCodiceVerticale, ruolo);	


				return result;
			}		
			
			
			//validate validateListaDolori
			@Override
			public List<ErroreDettaglioExt> validateListaDolori(String shibIdentitaCodiceFiscale, String xRequestId,
					String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,String regime,
					String collocazione, String citId, Long taccuinoId, Integer limit, Integer offset, String ordinamento,
					String da, String a, SecurityContext securityContext, HttpHeaders httpHeaders,
					HttpServletRequest httpRequest) throws DatabaseException {
				
				// CONTROLLI GENERICI
				List<ErroreDettaglioExt> result = super.validateListaDolori(shibIdentitaCodiceFiscale, xRequestId,
						xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse,regime, collocazione, citId,
						taccuinoId, limit, offset, ordinamento, da, a, securityContext, httpHeaders, httpRequest);

				// --- errori del merito ---
				if (!result.isEmpty()) {
					return result;
				}
				
				result = formalCheck(result, shibIdentitaCodiceFiscale, xCodiceServizio,
						xCodiceVerticale, ruolo);	
				
				validateCittadinoId(citId, result);

				

				return result;
			}		
			
}
