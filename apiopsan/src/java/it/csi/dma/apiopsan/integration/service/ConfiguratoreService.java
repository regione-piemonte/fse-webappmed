/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Collocazione;
import it.csi.dma.apiopsan.dto.Ruolo;
import it.csi.dma.apiopsan.dto.UtenteRichiedenteCollocazione;
import it.csi.dma.apiopsan.dto.custom.configuratore.ErroreConfiguratore;
import it.csi.dma.apiopsan.dto.custom.configuratore.HttpHeaderParam;
import it.csi.dma.apiopsan.dto.custom.configuratore.TokenInformazione;
import it.csi.dma.apiopsan.exception.UtenteConfiguratoreGenException;
import it.csi.dma.apiopsan.exception.UtenteConfiguratoreIPException;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.ApiHeaderParamEnum;

@Service
public class ConfiguratoreService extends LoggerUtil {

	@Autowired
	private EnvironmentConfiguration environmentConfiguration;

	private static final String LOGIN_TOKEN_INFORMATION = "v1/login/token-information2";
	private static final String LOGIN_RUOLI = "v1/login/ruoli";
	private static final String LOGIN_COLLOCAZIONI = "v1/login/collocazioni";
	private static final String LOGIN_AZIENDA = "v1/utenti/{0}/azienda";
	private static final String BASIC_AUTH = "Basic";
	

	private static final String ERRORE_IP_NON_CONGRUENTE = "AUTH_ER_602";

	private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public TokenInformazione sendGetMeByToken(HttpHeaderParam params) throws Exception {
		return sendGetTokenInformation(params.getShibIdentitaCodiceFiscale(), params.getxRequestId(),
				params.getxForwardedFor(), params.getToken(), params.getxCodiceServizio());
	}

	public List<Ruolo> sendGetRuoli(HttpHeaderParam params) throws Exception {
		return sendGetRuoli(params.getShibIdentitaCodiceFiscale(), params.getxRequestId(), params.getxForwardedFor(),
				params.getxCodiceServizio());
	}

	public List<Collocazione> sendGetCollocazioni(HttpHeaderParam params, String ruolo) throws Exception {
		return sendGetCollocazioni(params.getShibIdentitaCodiceFiscale(), params.getxRequestId(),
				params.getxForwardedFor(), params.getxCodiceServizio(), ruolo);
	}

	@SuppressWarnings("deprecation")
	public TokenInformazione sendGetTokenInformation(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwadedFor, String token, String codiceServizio) throws Exception {
		TokenInformazione result = null;
		String xForwadedForInHeader = extractXForwadedFor(xForwadedFor);
		String url = getUrl(LOGIN_TOKEN_INFORMATION);
		logInfo("sendGetTokenInformation - url ", url);
		logInfo("sendGetTokenInformation ", "params:",
				ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
				ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
				ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwadedFor,
				ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + codiceServizio,
				ApiHeaderParamEnum.TOKEN.getCode() + " " + token,
				ApiHeaderParamEnum.AUTHORIZATION.getCode() + " " + getBasicAuth());

		logInfo("sendGetTokenInformation",
				ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + "trunc: " + xForwadedForInHeader);
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader(ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale)
				.setHeader(ApiHeaderParamEnum.X_REQUEST_ID.getCode(), xRequestId)
				.setHeader(ApiHeaderParamEnum.X_FORWARDED_FOR.getCode(), xForwadedForInHeader)
				.setHeader(ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode(), codiceServizio)
				.setHeader(ApiHeaderParamEnum.TOKEN.getCode(), token)
				.setHeader(ApiHeaderParamEnum.AUTHORIZATION.getCode(), getBasicAuth()).build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			logInfo("sendGetTokenInformation: ", String.valueOf(response.statusCode()));
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			logInfo("sendGetTokenInformation: ", String.valueOf(response.statusCode()));
			logInfo("configuratore -response: ", String.valueOf(response.toString()));

			if (response.statusCode() == 200) {
				result = mapper.readValue(response.body(), TokenInformazione.class);
				logInfo("configuratore -body: ", result.toString());
			} else {
				logInfo("configuratore -body: ", response.body());
				logError("sendGetTokenInformationException", response.body());

				ErroreConfiguratore errore = mapper.readValue(response.body(), ErroreConfiguratore.class);

				if (ERRORE_IP_NON_CONGRUENTE.equals(errore.getCodice())) {
					throw new UtenteConfiguratoreIPException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				} else {
					throw new UtenteConfiguratoreGenException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				}

			}
		} catch (UtenteConfiguratoreIPException | UtenteConfiguratoreGenException uEx) {
			throw uEx;
		} catch (Exception e) {
			logError("sendGetTokenInformationException", e.getMessage());
			throw new Exception(e);
		}
		return result;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ruolo> sendGetRuoli(String shibIdentitaCodiceFiscale, String xRequestId, String xForwadedFor,
			String codiceServizio) throws Exception {
		List<Ruolo> result = null;
		String xForwadedForInHeader = extractXForwadedFor(xForwadedFor);
		String url = getUrl(LOGIN_RUOLI);
		logInfo("sendGetRuoli - url ", url);
		logInfo("sendGetRuoli ", "params:",
				ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
				ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
				ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwadedFor,
				ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + codiceServizio,
				ApiHeaderParamEnum.AUTHORIZATION.getCode() + " " + getBasicAuth());

		logInfo("sendGetRuoli", ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + "trunc: " + xForwadedForInHeader);
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader(ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale)
				.setHeader(ApiHeaderParamEnum.X_REQUEST_ID.getCode(), xRequestId)
				.setHeader(ApiHeaderParamEnum.X_FORWARDED_FOR.getCode(), xForwadedForInHeader)
				.setHeader(ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode(), codiceServizio)
				.setHeader(ApiHeaderParamEnum.AUTHORIZATION.getCode(), getBasicAuth()).build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			logInfo("sendGetRuoli: ", String.valueOf(response.statusCode()));
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			logInfo("sendGetRuoli: ", String.valueOf(response.statusCode()));
			logInfo("configuratore -response: ", String.valueOf(response.toString()));

			if (response.statusCode() == 200) {
				result = mapper.readValue(response.body(), new TypeReference<List<Ruolo>>() {
				});
				logInfo("configuratore -body: ", result.toString());
			} else {
				logInfo("configuratore -body: ", response.body());
				logError("sendGetRuoli", response.body());

				ErroreConfiguratore errore = mapper.readValue(response.body(), ErroreConfiguratore.class);

				if (ERRORE_IP_NON_CONGRUENTE.equals(errore.getCodice())) {
					throw new UtenteConfiguratoreIPException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				} else {
					throw new UtenteConfiguratoreGenException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				}

			}
		} catch (UtenteConfiguratoreIPException | UtenteConfiguratoreGenException uEx) {
			throw uEx;
		} catch (Exception e) {
			logError("sendGetRuoli", e.getMessage());
			throw new Exception(e);
		}
		return result;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Collocazione> sendGetCollocazioni(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwadedFor, String codiceServizio, String ruolo) throws Exception {
		List<Collocazione> result = null;
		String xForwadedForInHeader = extractXForwadedFor(xForwadedFor);
		String url = getUrl(LOGIN_COLLOCAZIONI) + "?codice_ruolo=" + ruolo;
		logInfo("sendGetCollocazioni - url ", url);
		logInfo("sendGetCollocazioni ", "params:",
				ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
				ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
				ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwadedFor,
				ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + codiceServizio,
				ApiHeaderParamEnum.AUTHORIZATION.getCode() + " " + getBasicAuth());

		logInfo("sendGetCollocazioni", ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + "trunc: " + xForwadedForInHeader);
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader(ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale)
				.setHeader(ApiHeaderParamEnum.X_REQUEST_ID.getCode(), xRequestId)
				.setHeader(ApiHeaderParamEnum.X_FORWARDED_FOR.getCode(), xForwadedForInHeader)
				.setHeader(ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode(), codiceServizio)
				.setHeader(ApiHeaderParamEnum.AUTHORIZATION.getCode(), getBasicAuth()).build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			logInfo("sendGetCollocazioni: ", String.valueOf(response.statusCode()));
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			logInfo("sendGetCollocazioni: ", String.valueOf(response.statusCode()));
			logInfo("configuratore -response: ", String.valueOf(response.toString()));

			if (response.statusCode() == 200) {
				result = mapper.readValue(response.body(), new TypeReference<List<Collocazione>>() {
				});
				logInfo("configuratore -body: ", result.toString());
			} else {
				logInfo("configuratore -body: ", response.body());
				logError("sendGetCollocazioni", response.body());

				ErroreConfiguratore errore = mapper.readValue(response.body(), ErroreConfiguratore.class);

				if (ERRORE_IP_NON_CONGRUENTE.equals(errore.getCodice())) {
					throw new UtenteConfiguratoreIPException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				} else {
					throw new UtenteConfiguratoreGenException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				}

			}
		} catch (UtenteConfiguratoreIPException | UtenteConfiguratoreGenException uEx) {
			throw uEx;
		} catch (Exception e) {
			logError("sendGetCollocazioni", e.getMessage());
			throw new Exception(e);
		}
		return result;

	}
		
	@SuppressWarnings({ "deprecation", "unchecked" })
	public String sendGetAzienda(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwadedFor, String codiceServizio, String ruolo,String collocazione) throws Exception {
		String result = null;
		String xForwadedForInHeader = extractXForwadedFor(xForwadedFor);
		String url = getUrl(LOGIN_AZIENDA.replace("{0}", shibIdentitaCodiceFiscale)) + "?ruolo_codice=" + ruolo + 
				"&applicazione_codice=" + codiceServizio + "&collocazione_codice=" + collocazione;
		logInfo("sendGetAzienda ", "params:",
				ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode() + " " + shibIdentitaCodiceFiscale,
				ApiHeaderParamEnum.X_REQUEST_ID.getCode() + " " + xRequestId,
				ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + " " + xForwadedForInHeader,
				ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode() + " " + codiceServizio,
				ApiHeaderParamEnum.AUTHORIZATION.getCode() + " " + getBasicAuth());

		logInfo("sendGetAzienda", ApiHeaderParamEnum.X_FORWARDED_FOR.getCode() + "trunc: " + xForwadedForInHeader);
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader(ApiHeaderParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale)
				.setHeader(ApiHeaderParamEnum.X_REQUEST_ID.getCode(), xRequestId)
				.setHeader(ApiHeaderParamEnum.X_FORWARDED_FOR.getCode(), xForwadedForInHeader)
				.setHeader(ApiHeaderParamEnum.X_CODICE_SERVIZIO.getCode(), codiceServizio)
				.setHeader(ApiHeaderParamEnum.AUTHORIZATION.getCode(), getBasicAuth()).build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			logInfo("sendGetAzienda: ", String.valueOf(response.statusCode()));
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			logInfo("sendGetAzienda: ", String.valueOf(response.statusCode()));
			logInfo("configuratore -response: ", String.valueOf(response.toString()));
			if (response.statusCode() == 200) {
				result = response.body();
				if (result!=null) {
					if (result.length()==6) {
						result = result.substring(3);
				}
			}
				if (result!=null) {
					logInfo("configuratore -body: ", result.toString());
				}
			} else {
				logInfo("configuratore -body: ", response.body());
				logError("sendGetAzienda", response.body());

				ErroreConfiguratore errore = mapper.readValue(response.body(), ErroreConfiguratore.class);

				if (ERRORE_IP_NON_CONGRUENTE.equals(errore.getCodice())) {
					throw new UtenteConfiguratoreIPException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				} else {
					throw new UtenteConfiguratoreGenException(errore.getDescrizione(), errore.getStatus(),
							errore.getCodice());
				}

			}
		} catch (UtenteConfiguratoreIPException | UtenteConfiguratoreGenException uEx) {
			throw uEx;
		} catch (Exception e) {
			logError("sendGetAzienda", e.getMessage());
			throw new Exception(e);
		}
		
		return result;

	}

	private String getBasicAuth() {
		StringBuffer sb = new StringBuffer();
		sb.append(BASIC_AUTH).append(" ").append(environmentConfiguration.getAuthB64Configuratore());
		return sb.toString();
	}

	private String extractXForwadedFor(String xForwadedFor) {
		String result = "";
		if (xForwadedFor.contains(",")) {
			result = xForwadedFor.split(",")[0].trim();
		} else {
			result = xForwadedFor;
		}
		return result;
	}

	private String getUrl(String loginTokenInformation) {
		StringBuffer sb = new StringBuffer();
		sb.append(environmentConfiguration.getBaseUrlConfiguratore()).append(loginTokenInformation);
		return sb.toString();
	}
}
