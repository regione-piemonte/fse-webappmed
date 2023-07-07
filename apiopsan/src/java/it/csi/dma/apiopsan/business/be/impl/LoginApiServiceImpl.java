/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.business.be.LoginApi;
import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Utente;
import it.csi.dma.apiopsan.dto.UtenteRichiedente;
import it.csi.dma.apiopsan.dto.custom.Ruolo;
import it.csi.dma.apiopsan.dto.custom.UserInfo;
import it.csi.dma.apiopsan.dto.custom.configuratore.HttpHeaderParam;
import it.csi.dma.apiopsan.exception.UtenteConfiguratoreGenException;
import it.csi.dma.apiopsan.exception.UtenteConfiguratoreIPException;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.service.LoginService;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.ApiHeaderParamEnum;
import it.csi.dma.apiopsan.util.enumerator.ConfiguratoreEnum;

@Service
public class LoginApiServiceImpl extends LoggerUtil implements LoginApi {

	public static final String AUTH_SHIBBOLETH = "Shib-Identita-CodiceFiscale";

	public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";

	public static final String PATIENT_SESSIONATTR = "appDatacurrentPatient";

	public static final String DOCUMENT_SESSIONATTR = "appDatacurrentDocument";

	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

	@Autowired
	private LoginService loginService;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	@Override
	public Response getCollocazioni(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, @NotNull String codiceRuolo,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			logInfo("getCollocazioni", "BEGIN");
			
			logInfo("getCollocazioni ", "params:",
					ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
					ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
					ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwardedFor,
					ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + xCodiceServizio,
					ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + xCodiceVerticale);
			
			String shib = shibIdentitaCodiceFiscale;
			if (StringUtils.isBlank(shib)) {
				shib = getCfByShiIdentitaDigitale(httpHeaders);
			}
			if (StringUtils.isBlank(shib)) {
				shib = getCfByAuthShibboleth(httpHeaders);
				if (StringUtils.isBlank(shib)) {

					return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorCode(),
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorMessage(),
							ErrorBuilder.buidErrorListByFieldNotValidException(
									"shibIdentitaCodiceFiscale and identitaDigitale", "null"));
				}
			}

			HttpHeaderParam headerParam = new HttpHeaderParam(shib, xRequestId, xForwardedFor, xCodiceServizio, null);
			return Response.ok(loginService.getCollocazioni(headerParam, codiceRuolo)).build();
		} catch (UtenteConfiguratoreIPException ex) {
			logError("getCollocazioni", ex.getMessage());
			return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE.getErrorCode(),
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE.getErrorMessage());
		} catch (UtenteConfiguratoreGenException ex) {
			logError("getCollocazioni", ex.getMessage());
			return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE_GEN.getErrorCode(), String.format(
							ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE_GEN.getErrorMessage(), ex.getLocalizedMessage()));
		} catch (Exception e) {
			logError("getCollocazioni", e.getMessage());
			return ErrorBuilder.generateResponseError(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public Response getRuoli(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			logInfo("getRuoli", "BEGIN");
			
			logInfo("getRuoli ", "params:",
					ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
					ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
					ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwardedFor,
					ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + xCodiceServizio,
					ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + xCodiceVerticale);
			
			String shib = shibIdentitaCodiceFiscale;
			if (StringUtils.isBlank(shib)) {
				shib = getCfByShiIdentitaDigitale(httpHeaders);
			}
			if (StringUtils.isBlank(shib)) {
				shib = getCfByAuthShibboleth(httpHeaders);
				if (StringUtils.isBlank(shib)) {
					return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorCode(),
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorMessage(),
							ErrorBuilder.buidErrorListByFieldNotValidException(
									"shibIdentitaCodiceFiscale and identitaDigitale", "null"));
				}
			}

			HttpHeaderParam headerParam = new HttpHeaderParam(shib, xRequestId, xForwardedFor, xCodiceServizio, null);
			return Response.ok(loginService.getRuoli(headerParam)).build();
		} catch (UtenteConfiguratoreIPException ex) {
			logError("getRuoli", ex.getMessage());
			return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE.getErrorCode(),
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE.getErrorMessage());
		} catch (UtenteConfiguratoreGenException ex) {
			logError("getRuoli", ex.getMessage());
			return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE_GEN.getErrorCode(), String.format(
							ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE_GEN.getErrorMessage(), ex.getLocalizedMessage()));
		} catch (Exception e) {
			logError("getRuoli", e.getMessage());
			return ErrorBuilder.generateResponseError(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public Response getInfoUtenteLoggato(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String token, String tokenLCCE,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			logInfo("getInfoUtenteLoggato", "BEGIN");
			
			logInfo("getInfoUtenteLoggato ", "params:",
					ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
					ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
					ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwardedFor,
					ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + xCodiceServizio,
					ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + xCodiceVerticale,
					ApiHeaderParamEnum.TOKEN.getCode() + " " + token,
					"TokenLCCE" + " " + tokenLCCE);
			
			Utente utente = new Utente();
			if (!StringUtils.isBlank(tokenLCCE)) {
				logInfo("getInfoUtenteLoggato", "TokenLCCE presente");
				if (httpRequest.getSession().getAttribute(USERINFO_SESSIONATTR) != null) {
					UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute(USERINFO_SESSIONATTR);
					UtenteRichiedente utenterichiedente = new UtenteRichiedente();
					utenterichiedente.setCodiceFiscale(userInfo.getCodFisc());
					utenterichiedente.setCognome(userInfo.getCognome());
					utenterichiedente.setNome(userInfo.getNome());
					Ruolo tabruolo = dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(userInfo.getRuolo());
					Codice ruolo = new Codice();
					ruolo.setCodice(tabruolo.getCodiceRuolo());
					ruolo.setDescrizione(tabruolo.getDescrizioneRuolo());
					utenterichiedente.setRuolo(ruolo);
					utente.setRichiedente(utenterichiedente);
				}
			} else {
				logInfo("getInfoUtenteLoggato", "TokenLCCE non presente");
				if (StringUtils.isBlank(token)) {
					logInfo("getInfoUtenteLoggato", "token nullo");
					return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorCode(),
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorMessage(),
							ErrorBuilder.buidErrorListByFieldNotValidException("token", token));
				}
				String shib = shibIdentitaCodiceFiscale;
				if (StringUtils.isBlank(shib)) {
					shib = getCfByShiIdentitaDigitale(httpHeaders);
				}
				if (StringUtils.isBlank(shib)) {
					return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorCode(),
							ConfiguratoreEnum.BAD_REQUEST_400_PAYLOAD_INVALID.getErrorMessage(),
							ErrorBuilder.buidErrorListByFieldNotValidException(
									"shibIdentitaCodiceFiscale and identitaDigitale", "null"));
				}
				HttpHeaderParam headerParam = new HttpHeaderParam(shib, xRequestId, xForwardedFor, xCodiceServizio,
						token);
				utente = loginService.getInfoProfiloUtente(headerParam);
			}

			if (httpRequest.getSession().getAttribute(PATIENT_SESSIONATTR) != null) {
				utente.setCfPaziente((String) httpRequest.getSession().getAttribute(PATIENT_SESSIONATTR));
			} else
				utente.setCfPaziente(null);

			if (httpRequest.getSession().getAttribute(DOCUMENT_SESSIONATTR) != null) {
				utente.setTipoDocumento((String) httpRequest.getSession().getAttribute(DOCUMENT_SESSIONATTR));
			} else
				utente.setTipoDocumento(null);

			return Response.ok(utente).build();
		} catch (UtenteConfiguratoreIPException ex) {
			logError("getInfoUtenteLoggato", ex.getMessage());
			return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE.getErrorCode(),
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE.getErrorMessage());
		} catch (UtenteConfiguratoreGenException ex) {
			logError("getInfoUtenteLoggato", ex.getMessage());
			return ErrorBuilder.generateResponseError(Status.BAD_REQUEST,
					ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE_GEN.getErrorCode(), String.format(
							ConfiguratoreEnum.ECCEZIONE_CONFIGURATORE_GEN.getErrorMessage(), ex.getLocalizedMessage()));
		} catch (Exception e) {
			logError("getInfoUtenteLoggato", e.getMessage());
			return ErrorBuilder.generateResponseError(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	private String getCfByShiIdentitaDigitale(HttpHeaders httpHeaders) {
		String result = null;
		try {
			List<String> marker = httpHeaders.getRequestHeader(AUTH_ID_MARKER);
			if (marker != null && marker.size() > 0) {
				result = marker.get(0).split("/")[0];
			}

		} catch (Exception e) {
			logError("getInfoUtenteLoggato", "impossibile recuperare cd da " + AUTH_ID_MARKER);
		}
		return result;
	}

	private String getCfByAuthShibboleth(HttpHeaders httpHeaders) {
		log.info("getCfByAuthShibboleth::BEGIN");
		String result = null;
		try {
			List<String> marker = httpHeaders.getRequestHeader(AUTH_SHIBBOLETH);
			if (marker != null && marker.size() > 0) {
				result = marker.get(0);
				log.info("getCfByAuthShibboleth::result " + result);
			}

		} catch (Exception e) {
			logError("getInfoUtenteLoggato", "impossibile recuperare cd da " + AUTH_SHIBBOLETH);
		}

		log.info("getCfByAuthShibboleth::END");
		return result;
	}

}
