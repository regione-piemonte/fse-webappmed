/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.GetTokenInformationService;
import it.csi.dma.apiopsan.tokeninformation.dma.Errore;
import it.csi.dma.apiopsan.tokeninformation.dmacc.GetTokenInformationResponse;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.SpringApplicationContextHelper;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;
import it.csi.iride2.policy.entity.Identita;
import it.csi.iride2.policy.exceptions.MalformedIdTokenException;

/**
 * Inserisce in sessione:
 * <ul>
 * <li>l'identit&agrave; digitale relativa all'utente autenticato.
 * <li>l'oggetto <code>currentUser</code>
 * </ul>
 * Funge da adapter tra il filter del metodo di autenticaizone previsto e la
 * logica applicativa.
 *
 * @author CSIPiemonte
 */
public class IrideIdAdapterFilter extends LoggerUtil implements Filter {

	public static final String IRIDE_ID_SESSIONATTR = "iride2_id";

	public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";

	public static final String AUTH_SHIBBOLETH = "Shib-Identita-CodiceFiscale";
	
	public static final String TOKENLCCE = "tokenLCCE=";

	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;

	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fchn)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		Identita identita = new Identita();
		it.csi.dma.apiopsan.dto.custom.UserInfo userInfo = new it.csi.dma.apiopsan.dto.custom.UserInfo();
		boolean tokenlccefound = false;
		//String querypar = null;
		// verifichiamo prima se viene passato un token
		logInfo("Iride", "queryparam: " + hreq.getQueryString());
		if (Util.isValorizzato(hreq.getQueryString())) {
			if (hreq.getQueryString().contains(TOKENLCCE)) {
				logInfo("Iride", "esiste lcce: " + hreq.getQueryString());
//				String[] elencoqueryparam = hreq.getQueryString().split("&");
//				if (elencoqueryparam.length == 0)
//					querypar = hreq.getQueryString();
//				else
//					for (int i = 0; i < elencoqueryparam.length; i++) {
//						if (elencoqueryparam[i].contains(TOKENLCCE)) {
//							querypar = elencoqueryparam[i];
//							break;
//						}
//					}
//				logInfo("Iride", "queryparam: " + querypar);
				tokenlccefound = true;
				GetTokenInformationService tokenService = (GetTokenInformationService) SpringApplicationContextHelper
						.getBean("tokenService");
				CodRMessaggioErroreService codRMessaggioErroreService = (CodRMessaggioErroreService) SpringApplicationContextHelper
						.getBean("codRMessaggioErroreService");
				CodRLogErroreService codRLogErroreService = (CodRLogErroreService) SpringApplicationContextHelper
						.getBean("codRLogErroreService");
				String methodName = "execute";
				ErrorBuilder error = null;
				UUID uuid = UUID.randomUUID();
				String uuidAsString = uuid.toString();
				// inserisco tabella dei log
				codRLogErroreService.traceLogInsert(Constants.TOKEN_INFORMATION, uuidAsString,
						Constants.TOKEN_INFORMATION_SERVICE, null);
				String valtoken = hreq.getParameter("tokenLCCE"); //) querypar.split("=")[1];
				GetTokenInformationResponse resptoken = tokenService.verificaToken(hreq.getRemoteAddr(),
						Constants.CODICE_SERVIZIO, valtoken);
				List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
				if (resptoken.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
					// dai errore e accoda ad errori
					for (Errore errore : resptoken.getErrori().getErrore()) {
						ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
						errorservice.setChiave(errore.getCodice());
						errorservice.setValore(errore.getDescrizione());
						errorservice.setErroreId(errorservice.getErroreId() != null ? errorservice.getErroreId()
								: DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
						listerrorservice.add(errorservice);
					}
					error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice);
					error = codRMessaggioErroreService.saveError(error, hreq, uuidAsString);
					codRLogErroreService.traceLogInsert(Constants.TOKEN_INFORMATION, uuidAsString,
							Constants.TOKEN_INFORMATION_SERVICE, Constants.ESITO_FALLIMENTO);
					LOG.error(
							"[IrideIdAdapterFilter::doFilter] Tentativo di verifica token della getinformationtoken scaduto o non valido");
					throw new ServletException("errore in get token information : " + listerrorservice.toString());
				} else {
					userInfo.setNome(resptoken.getRichiedente().getNome());
					userInfo.setCognome(resptoken.getRichiedente().getCognome());
					userInfo.setEnte("--");
					userInfo.setRuolo(resptoken.getRichiedente().getRuolo());
					userInfo.setCodFisc(resptoken.getRichiedente().getCodiceFiscale());
					userInfo.setLivAuth(0);
					userInfo.setCommunity("lcce");
					
					Identita id = new Identita();
					id.setNome(resptoken.getRichiedente().getNome());
					id.setCognome(resptoken.getRichiedente().getCognome());
					id.setCodFiscale(resptoken.getRichiedente().getCodiceFiscale());
					id.setLivelloAutenticazione(0);
					id.setIdProvider("lcce");
					
					logInfo("[IrideIdAdapterFilter::doFilter] resptoken.getRichiedente().getCodiceFiscale() " , resptoken.getRichiedente().getCodiceFiscale());
					logInfo("[IrideIdAdapterFilter::doFilter] Shib-Identita-CodiceFiscale " , hreq.getHeader(AUTH_SHIBBOLETH));
					hreq.getSession().setAttribute(Constants.USERINFO_SESSIONATTR, userInfo);
					if (Util.isValorizzato(resptoken.getCodiceFiscaleAssistito()))
						hreq.getSession().setAttribute(Constants.PATIENT_SESSIONATTR, resptoken.getCodiceFiscaleAssistito());
					if (resptoken.getParametriLogin() != null) {
						if (Util.isValorizzato(resptoken.getParametriLogin().getCodice())) {
							if (resptoken.getParametriLogin().getCodice().equalsIgnoreCase(Constants.TIPO_DOCUMENTO))
								hreq.getSession().setAttribute(Constants.DOCUMENT_SESSIONATTR,
										resptoken.getParametriLogin().getValore());
						}
					}
				}
			}
		}
		if (!tokenlccefound) {
			logInfo("Iride", "non lcce ");
			if (hreq.getSession().getAttribute(IRIDE_ID_SESSIONATTR) == null) {
				String marker = getToken(hreq);
				if (marker != null) {
					try {
						identita = new Identita(normalizeToken(marker));
						LOG.debug("[IrideIdAdapterFilter::doFilter] Inserito in sessione marcatore IRIDE:" + identita);
						hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
						userInfo.setNome(identita.getNome());
						userInfo.setCognome(identita.getCognome());
						userInfo.setEnte("--");
						userInfo.setRuolo("--");
						userInfo.setCodFisc(identita.getCodFiscale());
						userInfo.setLivAuth(identita.getLivelloAutenticazione());
						userInfo.setCommunity(identita.getIdProvider());
						hreq.getSession().setAttribute(Constants.USERINFO_SESSIONATTR, userInfo);
					} catch (MalformedIdTokenException e) {
						LOG.error("[IrideIdAdapterFilter::doFilter] " + e.toString(), e);
					}
				} else {
					// il marcatore deve sempre essere presente altrimenti e' una
					// condizione di errore (escluse le pagine home e di servizio)
					if (mustCheckPage(hreq.getRequestURI())) {
						LOG.error(
								"[IrideIdAdapterFilter::doFilter] Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
						throw new ServletException(
								"Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
					}
				}
			} else {
				// replace cf se diverso da user shibboleth
				String cf_iride = (String) hreq.getHeader(AUTH_ID_MARKER);
				if (cf_iride.contains("/")) {
					if (cf_iride.split("/").length > 0) {
						cf_iride = cf_iride.split("/")[0];
					}
				}
				String cf_shibboleth = (String) hreq.getHeader(AUTH_SHIBBOLETH);
				if (!cf_iride.equalsIgnoreCase(cf_shibboleth)) {
					identita = (Identita) hreq.getSession().getAttribute(IRIDE_ID_SESSIONATTR);
					identita.setCodFiscale(cf_shibboleth);
					hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
					hreq.getSession().setAttribute(Constants.USERINFO_SESSIONATTR, identita);
				}
			}
		}
		fchn.doFilter(req, resp);

	}

	private boolean mustCheckPage(String requestURI) {

		return true;
	}

	public void destroy() {
		// NOP
	}

	private static final String DEVMODE_INIT_PARAM = "devmode";

	private boolean devmode = false;

	public void init(FilterConfig fc) throws ServletException {
		String sDevmode = fc.getInitParameter(DEVMODE_INIT_PARAM);
		if ("true".equals(sDevmode)) {
			devmode = true;
		} else {
			devmode = false;
		}
	}

	public String getToken(HttpServletRequest httpreq) throws ServletException {
		String marker = (String) httpreq.getHeader(AUTH_ID_MARKER);
		if (marker == null && devmode) {
			return getTokenDevMode(httpreq);
		} else if (marker == null ) {
			LOG.error(
					"[IrideIdAdapterFilter::doFilter] Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
			throw new ServletException(
					"Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
		} else {
			try {
				// gestione dell'encoding
				String decodedMarker = new String(marker.getBytes("ISO-8859-1"), "UTF-8");
				return decodedMarker;
			} catch (java.io.UnsupportedEncodingException e) {
				// se la decodifica non funziona comunque sempre meglio restituire
				// il marker originale non decodificato
				return marker;
			}
		}
	}

	private String getTokenDevMode(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getParameter(AUTH_ID_MARKER);
		return marker;
	}

	private String normalizeToken(String token) {
		return token;
	}


}
