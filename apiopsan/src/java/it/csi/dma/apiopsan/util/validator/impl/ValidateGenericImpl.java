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

import org.springframework.util.StringUtils;

import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.ErrorParamEnum;
import it.csi.dma.apiopsan.util.validator.ValidateGeneric;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Util;

public class ValidateGenericImpl extends BaseValidate implements ValidateGeneric {
	/*
	 * PRIMO LIVELLO DI VERIFICA : CAMPI OBBLIGATORI
	 */
	//validate getregimi
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
		String methodName = "validate";
		logInfo(methodName, "BEGIN");

//		2. Verifica parametri in input (Criteri di validazione della richiesta)
//		2a) Obbligatorieta'
		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);		
		
		return result;
	}
	
	//validate getconsenso
	@Override
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione,String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
		String methodName = "validate";
		logInfo(methodName, "BEGIN");

//		2. Verifica parametri in input (Criteri di validazione della richiesta)
//		2a) Obbligatorieta'
		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);		
		
		//inserimento controllo specifico citid
		
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		
		return result;
	}
	
	//validate getmmgpaziente
		@Override
		public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
				String citId, String idIrec, String idAura,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//			2. Verifica parametri in input (Criteri di validazione della richiesta)
//			2a) Obbligatorieta'
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);		
			
			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CIT_ID.getCode()));
			}
			
			if (StringUtils.isEmpty(idAura)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.ID_AURA.getCode()));
			}
			
			if (StringUtils.isEmpty(idIrec)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.ID_IREC.getCode()));
			}
			
			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.REGIME.getCode()));
			}
			
			return result;
		}
	
		//validate ricercapaziente
				@Override
				public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
						String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
						String citId, String cognome, String nome,String dataNascita,
						SecurityContext securityContext,
						HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
					String methodName = "validate";
					logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorieta'
					List<ErroreDettaglioExt> result = new ArrayList<>();

					result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
							xCodiceVerticale, ruolo, collocazione);		
					
					//se sono valorizzati sia citid che nome e cognome la ricerca va per citid quindi non controllare
					
				    if (StringUtils.isEmpty(citId) && StringUtils.isEmpty(cognome)) {
						result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
								ErrorParamEnum.DATI_PAZIENTE.getCode()));
					}
					
					if (StringUtils.isEmpty(regime)) {
						result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
								ErrorParamEnum.REGIME.getCode()));
					}
					
					return result;
				}
		
	private List<ErroreDettaglioExt> commonCheck(List<ErroreDettaglioExt> result, String shibIdentitaCodiceFiscale,
			String xRequestId, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo,
			String collocazione) throws DatabaseException {

		if (StringUtils.isEmpty(xRequestId)) {
			result.add(
					getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.X_REQUEST_ID.getCode()));
		}

		if (StringUtils.isEmpty(shibIdentitaCodiceFiscale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode()));
		}

		if (StringUtils.isEmpty(xForwardedFor)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_FORWARDED_FOR.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceServizio)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_SERVIZIO.getCode()));
		}

		if (StringUtils.isEmpty(collocazione)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.COLLOCAZIONE.getCode()));
		}

		if (StringUtils.isEmpty(ruolo)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.RUOLO.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceVerticale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_VERTICALE.getCode()));
		}

		return result;
	}

	//validate getdocuemntoepisodio
			@Override
			public List<ErroreDettaglioExt> validateDocumentoEpisodio(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
					String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
					String citId, Integer idEpisodio, String codiceComponenteLocale,
					SecurityContext securityContext,
					HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
				String methodName = "validate";
				logInfo(methodName, "BEGIN");

//				2. Verifica parametri in input (Criteri di validazione della richiesta)
//				2a) Obbligatorieta'
				List<ErroreDettaglioExt> result = new ArrayList<>();

				result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
						xCodiceVerticale, ruolo, collocazione);		
				
				if (StringUtils.isEmpty(citId)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.CIT_ID.getCode()));
				}
				
				if (StringUtils.isEmpty(idEpisodio)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.ID_EPISODIO.getCode()));
				}
				
				if (StringUtils.isEmpty(codiceComponenteLocale)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.CODICE_CL.getCode()));
				}
				
				if (StringUtils.isEmpty(regime)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.REGIME.getCode()));
				}
				
				return result;
			}
			
			//validate postsoloepisodi
			@Override
			public List<ErroreDettaglioExt> validateSoloEpisodi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
					String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String regime, String collocazione,
					String citId, PayloadSearchEpisodi payloadSearchEpisodi,
					SecurityContext securityContext,
					HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
				String methodName = "validate";
				logInfo(methodName, "BEGIN");

//				2. Verifica parametri in input (Criteri di validazione della richiesta)
//				2a) Obbligatorieta'
				List<ErroreDettaglioExt> result = new ArrayList<>();

				result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
						xCodiceVerticale, ruolo, collocazione);		
				
				if (StringUtils.isEmpty(citId)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.CIT_ID.getCode()));
				}
				
				if (payloadSearchEpisodi.getTipologiaDocumento().size()==0) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.TIPOLOGIA_DOCUMENTO.getCode()));
				}
				
				if (StringUtils.isEmpty(payloadSearchEpisodi.getFiltroDocs().getDataInizio())) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.DATA_INIZIO.getCode()));
				}
				
				if (StringUtils.isEmpty(payloadSearchEpisodi.getFiltroDocs().getDataFine())) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.DATA_FINE.getCode()));
				}
				
				if (StringUtils.isEmpty(regime)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.REGIME.getCode()));
				}
				
				if (StringUtils.isEmpty(payloadSearchEpisodi.getCategoria())) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.CATEGORIA.getCode()));
				}
				
//				if (StringUtils.isEmpty(payloadSearchEpisodi.getTipoEpisodio())) {
//					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
//							ErrorParamEnum.TIPO_EPISODIO.getCode()));
//				}
				
				return result;
			
			}
			
			//validate gettipodocumento
			@Override
			public List<ErroreDettaglioExt> validateTipoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
					String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione,String regime,
					SecurityContext securityContext,
					HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
				String methodName = "validate";
				logInfo(methodName, "BEGIN");

//				2. Verifica parametri in input (Criteri di validazione della richiesta)
//				2a) Obbligatoriet
				List<ErroreDettaglioExt> result = new ArrayList<>();

				result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
						xCodiceVerticale, ruolo, collocazione);
				
				if (StringUtils.isEmpty(regime)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.REGIME.getCode()));
				}
				
				return result;
			}
			//validate getInformativa
			@Override
			public List<ErroreDettaglioExt> validateInformativa(String shibIdentitaCodiceFiscale, String xRequestId,
					String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
					String regime, String collocazione, String citId, String identificativoStruttura,
					String identificativoOrganizzazione, String idInformativa, SecurityContext securityContext,
					HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {
				String methodName = "validate";
				logInfo(methodName, "BEGIN");

//				2. Verifica parametri in input (Criteri di validazione della richiesta)
//				2a) Obbligatorieta'
				List<ErroreDettaglioExt> result = new ArrayList<>();

				result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
						xCodiceVerticale, ruolo, collocazione);		
				
				//inserimento controllo specifico citid
				
				if (StringUtils.isEmpty(citId)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.CIT_ID.getCode()));
				}
				if (StringUtils.isEmpty(regime)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.REGIME.getCode()));
				}
				if (StringUtils.isEmpty(identificativoOrganizzazione)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.IDENTIFICATIVO_ORGANIZZAZIONE.getCode()));
				}
				if (StringUtils.isEmpty(identificativoStruttura)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.IDENTIFICATIVO_STRUTTURA.getCode()));
				}
				if (StringUtils.isEmpty(idInformativa)) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
							ErrorParamEnum.ID_INFORMATIVA.getCode()));
				}
				
				return result;
			}
			
	
			protected boolean formalCheckTipoCorrelazioneDocumento(String tipodoc) throws DatabaseException {
				
				
				for (String tipoDocumento : Constants.TIPO_CORRELLAZIONE_DOCUMENTO) {
					
					if(tipoDocumento.equalsIgnoreCase(tipodoc)) {
						return false;
					}
					
				}
				
				return true;
				
			}
			
			
			
			
	//validate getdocuemntiCorrellati
		@Override
		public List<ErroreDettaglioExt> validateDocumentiCorrellati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId, Long idDocumentoIlec, String codiceComponenteLocale,String tipoCorrelazioneDocumento,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//						2. Verifica parametri in input (Criteri di validazione della richiesta)
//						2a) Obbligatorieta'
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);		
			
			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CIT_ID.getCode()));
			}
			
			if (StringUtils.isEmpty(idDocumentoIlec)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.ID_EPISODIO.getCode()));
			}
			
			if (StringUtils.isEmpty(codiceComponenteLocale)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CODICE_CL.getCode()));
			}
			
			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.REGIME.getCode()));
			}
			
			if (StringUtils.isEmpty(tipoCorrelazioneDocumento)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.REGIME.getCode()));
			}
			
			return result;
		}		
			
	@Override
	public List<ErroreDettaglioExt> validateRicercaDocumenti(String shibIdentitaCodiceFiscale,
			String xRequestId, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale,
			String ruolo, String ruoloFse, String regime, String collocazione, String citId,
			PayloadSearchTuttiDoc payloadSearchTuttiDoc, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws DatabaseException {

		String methodName = "validateRicercaDocumenti";
		logInfo(methodName, "BEGIN");

		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);		
	

		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
		
		if (StringUtils.isEmpty(collocazione)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.COLLOCAZIONE.getCode()));
		}
		
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getCategoriaTipologia() == null || 
				payloadSearchTuttiDoc.getCategoriaTipologia().size() == 0) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CATEGORIA_TIPOLOGIA_DOCUMENTO.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getFiltroDocs() == null && 
				payloadSearchTuttiDoc.getFiltroDocs().getDataInizio() != null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.DATA_INIZIO.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getFiltroDocs() == null && 
				payloadSearchTuttiDoc.getFiltroDocs().getDataFine() != null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.DATA_FINE.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getLimit() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.LIMIT.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getOffset() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.OFFSET.getCode()));
		}
		
		return result;
		
	}
	
	
	@Override
	public List<ErroreDettaglioExt> validateMieiReferti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione, String regime,
			PayloadSearchMieiReferti payloadSearchMieiReferti, SecurityContext securityContext,
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
		String methodName = "validate";
		logInfo(methodName, "BEGIN");

		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);
					
		//check parmaetri valorizzati
		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
		
		
		if (StringUtils.isEmpty(payloadSearchMieiReferti.getCitId())) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		
		if (payloadSearchMieiReferti.getFiltroDocs() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.FILTRO_DATE.getCode()));
		}
		
		if (payloadSearchMieiReferti.getFiltroDocs() != null && payloadSearchMieiReferti.getFiltroDocs().getDataInizio() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.DATA_INIZIO.getCode()));
		}
		
		if (payloadSearchMieiReferti.getFiltroDocs() != null && payloadSearchMieiReferti.getFiltroDocs().getDataFine() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.DATA_FINE.getCode()));
		}
		
		if (StringUtils.isEmpty(payloadSearchMieiReferti.getLimit())) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.LIMIT.getCode()));
		}
		
		if (StringUtils.isEmpty(payloadSearchMieiReferti.getOffset())) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.OFFSET.getCode()));
		}
		
		if (StringUtils.isEmpty(payloadSearchMieiReferti.getTipoMedico())) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.TIPO_MEDICO.getCode()));
		}
		
		
		//check formale
		validateCittadinoId(payloadSearchMieiReferti.getCitId(), result);
		
		if (payloadSearchMieiReferti.getFiltroDocs() != null ) {
			validateDateRicerca(payloadSearchMieiReferti.getFiltroDocs().getDataInizio(),
					payloadSearchMieiReferti.getFiltroDocs().getDataFine(), result);
		}
		
		if (formalCheckTipoMedico(payloadSearchMieiReferti.getTipoMedico())) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_567.getCode(),
					ErrorParamEnum.TIPO_MEDICO.getCode(),payloadSearchMieiReferti.getTipoMedico()));
		}
				
		return result;
	}
	
	
	
	@Override
	public List<ErroreDettaglioExt> validateDettaglioDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione,
		    String citId, Long idDocumentoIlec, String codiceComponenteLocale, String categoria, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {
		String methodName = "validate";
		logInfo(methodName, "BEGIN");

		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);
					
		//check parmaetri valorizzati
		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
		
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		
		if (StringUtils.isEmpty(idDocumentoIlec)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
		}
		
		if (StringUtils.isEmpty(codiceComponenteLocale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CODICE_CL.getCode()));
		}
		
		if (StringUtils.isEmpty(categoria)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CATEGORIA.getCode()));
		}
		return result;
	}
	
	
	@Override
	public List<ErroreDettaglioExt> validateInfoScreening(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String regime, String collocazione,
			String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {
		String methodName = "validate";
		logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorieta'
		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);		
		
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		
		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
		
		return result;
	}
	
	@Override
	public List<ErroreDettaglioExt> validateComunicazioneConsensi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale,Boolean consensoAlimentazione,Boolean consensoConsultazione,Boolean consensoPregresso, String ruolo,String ruoloFse,
			String regime, String collocazione,
			String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest,String identificativoOrganizzazione, String IdentificativoAssistitoConsenso) throws DatabaseException {
		String methodName = "validate";
		logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorieta'
		List<ErroreDettaglioExt> result = new ArrayList<>();

		if (StringUtils.isEmpty(xRequestId)) {
			result.add(
					getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.X_REQUEST_ID.getCode()));
		}

		if (StringUtils.isEmpty(shibIdentitaCodiceFiscale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode()));
		}

		if (StringUtils.isEmpty(xForwardedFor)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_FORWARDED_FOR.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceServizio)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_SERVIZIO.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceVerticale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_VERTICALE.getCode()));
		}	
		
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		
		if (consensoAlimentazione== null || StringUtils.isEmpty(Util.booleanToString(consensoAlimentazione))) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CONSENSO_ALIMENTAZIONE.getCode()));
		}
		
		if (consensoConsultazione== null || StringUtils.isEmpty(Util.booleanToString(consensoConsultazione))) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CONSENSO_CONSULATAZIONE.getCode()));
		}
		
		if (consensoPregresso == null || StringUtils.isEmpty(Util.booleanToString(consensoPregresso))) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CONSENSO_PREGRESSO.getCode()));
		}
		
		if (StringUtils.isEmpty(identificativoOrganizzazione)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.IDENTIFICATIVO_ORGANIZZAZIONE.getCode()));
		}
		
		if (StringUtils.isEmpty(IdentificativoAssistitoConsenso)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.IDENTIFICATIVO_ASSISTITO_CONSENSO.getCode()));
		}
		
		return result;
	}

	@Override
	public List<ErroreDettaglioExt> validateElencoTipoDocumentoCategoria(String shibIdentitaCodiceFiscale,
			String xRequestId, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {

		List<ErroreDettaglioExt> result = new ArrayList<>();
		
		if (StringUtils.isEmpty(xRequestId)) {
			result.add(
					getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.X_REQUEST_ID.getCode()));
		}

		if (StringUtils.isEmpty(shibIdentitaCodiceFiscale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode()));
		}

		if (StringUtils.isEmpty(xForwardedFor)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_FORWARDED_FOR.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceServizio)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_SERVIZIO.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceVerticale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_VERTICALE.getCode()));
		}
		
		return result;
		
	}

	@Override
	public List<ErroreDettaglioExt> validateGetDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String citId, PayloadGetDocumento payloadGetDocumento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException {

		List<ErroreDettaglioExt> result = new ArrayList<>();
		
		Long idDocumentoIlec = payloadGetDocumento.getIdDocumentoIlec();
		String codiceComponenteLocale = payloadGetDocumento.getCodiceComponenteLocale();
		String identificativoRepositoryINI = payloadGetDocumento.getIdentificativoRepository();
		String codiceDocumentoINI = payloadGetDocumento.getCodiceDocumento();
		
		//2.	2. Verifica parametri in input 
		//a)	Obbligatorieta'
		
		// check obbligo su parametri comuni
		//      TODO - TOASK: collocazione e' obbligatorio o no?	 Su specifiche SER18 pare di no, ma su specifice SER15 pure non e' richiesto ma viene controllato	
		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);
		
		//check obbligo su parametri specifici
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
		
		//sono obbligatori: codice_documento e identificativo_repository (per la ricerca su ini)
		//oppure: id_documento_ilec e codice_componente_locale (se paziente in regione e quindi documento indicizzato)
		
		if ( StringUtils.isEmpty(codiceDocumentoINI)
				|| StringUtils.isEmpty(identificativoRepositoryINI) ) {
		
			if (StringUtils.isEmpty(codiceComponenteLocale)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CODICE_CL.getCode()));			
			}
			
			if (idDocumentoIlec == null) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
			}
		} else if ( StringUtils.isEmpty(codiceComponenteLocale)
				|| idDocumentoIlec == null) {
			
			if (StringUtils.isEmpty(codiceDocumentoINI)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CODICE_DOCUMENTO.getCode()));			
			}
			if (StringUtils.isEmpty(identificativoRepositoryINI)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.IDENTIFICATIVO_REPOSITORY.getCode()));			
			}	
		}
		//TODO - TOASK: whatabout  questi altri parametri:
		// firmato -  presente in yaml ma non in specifiche
		
		return result;

	}
	
	//validate recuperadocumento
		@Override
		public List<ErroreDettaglioExt> validateRecuperaDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,String collocazione,String citId,
				PayloadRecuperaDocumento payloadRecuperaDocumento,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//			2. Verifica parametri in input (Criteri di validazione della richiesta)
//			2a) Obbligatorieta'
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);		
			
			//inserimento controllo specifico citid
			
			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CIT_ID.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getContestoOperativo())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CONTESTO_OPERATIVO.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getIdentificativoDocumento())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.IDENTIFICATIVO_DOCUMENTO.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getIdentificativoOrganizzazione())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.IDENTIFICATIVO_ORGANIZZAZIONE.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getIdentificativoOrgDoc())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.IDENTIFICATIVO_ORGANIZZAZIONE_DOC.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getIdentificativoRepository())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.IDENTIFICATIVO_REPOSITORY.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getPresaInCarico())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.PRESA_IN_CARICO.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getStrutturaUtente())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.STRUTTURA_UTENTE.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getTipoAttivita())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.TIPO_ATTIVITA.getCode()));
			}
			
			if (StringUtils.isEmpty(payloadRecuperaDocumento.getTipoDocumento())) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.TIPO_DOCUMENTO.getCode()));
			}
			
			return result;
		}

		//validate sMediazioneDocumenti
		@Override
		public List<ErroreDettaglioExt> validateMediazioneDocumenti(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione, String citId, Long idDocumentoIlec, String codiceComponenteLocale,
				SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
				throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorieta'
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);

			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
			}

			if (StringUtils.isEmpty(idDocumentoIlec)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
			}

			if (StringUtils.isEmpty(codiceComponenteLocale)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CODICE_CL.getCode()));
			}

			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.REGIME.getCode()));
			}

			return result;
		}

		//validate RestituibilitaDocumento
		@Override
		public List<ErroreDettaglioExt> validateRestituibilitaDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione, String citId, Long idDocumentoIlec, String codiceComponenteLocale,
				SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
				throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");
			
			return validateMediazioneDocumenti(shibIdentitaCodiceFiscale, xRequestId,
					 xForwardedFor,  xCodiceServizio,  xCodiceVerticale,  ruolo, ruoloFse,
					 regime,  collocazione,  citId,  idDocumentoIlec,  codiceComponenteLocale,
					 securityContext, httpHeaders,  httpRequest);
		}
		
		@Override
		public List<ErroreDettaglioExt> validatePrenotazioneImmagini(String shibIdentitaCodiceFiscale,String xRequestId,String xForwardedFor,String xCodiceServizio,
				String xCodiceVerticale,String ruolo,String ruoloFse, String regime,String collocazione,ScaricoStudi scaricoStudi,SecurityContext securityContext, 
				HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) throws DatabaseException {
			String methodName = "validatePrenotazioneImmagini";
			logInfo(methodName, "BEGIN");

//			2.A Verifica parametri in input (Criteri di validazione della richiesta)
//			2a) ObbligatorietA
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);

			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.REGIME.getCode()));
			}
			
			if(scaricoStudi == null) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
			}else {
				if(StringUtils.isEmpty(scaricoStudi.getCitId())) {
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
				}
				
				if(StringUtils.isEmpty(scaricoStudi.getIdReferto())){
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
				}
				
				if(StringUtils.isEmpty(scaricoStudi.getCodCl() )){
					result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CODICE_CL.getCode()));
				}
			}
			
			return result;
		}

	public List<ErroreDettaglioExt> validateDettaglioPrestazioniDoc(String shibIdentitaCodiceFiscale, String xRequestId,
		String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
		String regime, Long idDocumentoIlec, String citId, String codiceComponenteLocale,
		SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException { 

		String methodName = "validateDettaglioPrestazioniDoc";
		logInfo(methodName, "BEGIN");

		List<ErroreDettaglioExt> result = new ArrayList<>();

		if (StringUtils.isEmpty(shibIdentitaCodiceFiscale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode()));
		}

		if (StringUtils.isEmpty(xRequestId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_REQUEST_ID.getCode()));
		}
		
		if (StringUtils.isEmpty(xForwardedFor)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_FORWARDED_FOR.getCode()));
		}

		if (StringUtils.isEmpty(xCodiceServizio)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_SERVIZIO.getCode()));
		}		

		if (StringUtils.isEmpty(xCodiceVerticale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_VERTICALE.getCode()));
		}	

		if (StringUtils.isEmpty(ruolo)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), 
					ErrorParamEnum.RUOLO.getCode()));
		}

		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
				
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}	
		
		if (idDocumentoIlec == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
		}	
		
		if (StringUtils.isEmpty(codiceComponenteLocale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CODICE_CL.getCode()));
		}	
		
		return result;
	}	
	
	
	public List<ErroreDettaglioExt> validateVerificaStatoPacchetto(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione,
			String codiceFiscale, String idDocumentoIlec, String codCl) throws DatabaseException {
		String methodName = "validateVerificaStatoPacchetto";
		logInfo(methodName, "BEGIN");

//		2.A Verifica parametri in input (Criteri di validazione della richiesta)
//		2a) ObbligatorietA
		List<ErroreDettaglioExt> result = new ArrayList<>();

		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);

		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.REGIME.getCode()));
		}
		
		if (StringUtils.isEmpty(codiceFiscale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
		}
		if (StringUtils.isEmpty(idDocumentoIlec)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
		}
		if (StringUtils.isEmpty(codCl)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CODICE_CL.getCode()));
		}		

		
		return result;
	}

	public List<ErroreDettaglioExt> validateCCGetDocumentoPersonale(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String citId, Long idDocumento) throws DatabaseException {

		List<ErroreDettaglioExt> result = new ArrayList<>();
		
		result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
				xCodiceVerticale, ruolo, collocazione);

		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.REGIME.getCode()));
		}
		
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
		}
		
		if (StringUtils.isEmpty(idDocumento)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.ID_DOCUMENTO_ILEC.getCode()));
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
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//			2. Verifica parametri in input (Criteri di validazione della richiesta)
//			2a) Obbligatorieta'
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);		
			
			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.CIT_ID.getCode()));
			}
			
			if (StringUtils.isEmpty(tipoDocumento)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.TIPOLOGIA_DOCUMENTO.getCode()));
			}
			
			
			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.REGIME.getCode()));
			}
			
			return result;
		}
		
		// validate contattiStrutture
		@Override
		public List<ErroreDettaglioExt> validateContattiStrutture(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String collocazione, String citId, SecurityContext securityContext, HttpHeaders httpHeaders,
				@Context HttpServletRequest httpRequest) throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorieta'
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);

			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
			}

			return result;
		}
		
		@Override
		public List<ErroreDettaglioExt> validateScansionaQRCode(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio,String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione,
				String qrcode,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException {
			
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorietï¿½
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);

			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.REGIME.getCode()));
			}
			
			if (StringUtils.isEmpty(qrcode)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_175.getCode(), ErrorParamEnum.TOKEN_DOCUMENTO.getCode()));
			}		
			
			return result;
		}

		@Override
		public List<ErroreDettaglioExt> validateListaDolori(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,String regime,
				String collocazione, String citId, Long taccuinoId, Integer limit, Integer offset, String ordinamento,
				String da, String a, SecurityContext securityContext, HttpHeaders httpHeaders,
				HttpServletRequest httpRequest) throws DatabaseException {
			String methodName = "validate";
			logInfo(methodName, "BEGIN");

//					2. Verifica parametri in input (Criteri di validazione della richiesta)
//					2a) Obbligatorietï¿½
			List<ErroreDettaglioExt> result = new ArrayList<>();

			result = commonCheck(result, shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio,
					xCodiceVerticale, ruolo, collocazione);

			if (StringUtils.isEmpty(citId)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.CIT_ID.getCode()));
			}

			if (StringUtils.isEmpty(regime)) {
				result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
						ErrorParamEnum.REGIME.getCode()));
			}
			return result;
		}


}
