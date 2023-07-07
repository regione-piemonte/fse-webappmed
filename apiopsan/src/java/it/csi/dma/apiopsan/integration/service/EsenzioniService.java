/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.interceptor.SoapInterceptor;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.esenzioniservice.ApplicativoVerticale;
import it.csi.dma.apiopsan.esenzioniservice.ApplicazioneRichiedente;

import it.csi.dma.apiopsan.esenzioniservice.EsenzioniRequest;
import it.csi.dma.apiopsan.esenzioniservice.EsenzioniResponse;
import it.csi.dma.apiopsan.esenzioniservice.EsenzioniService_Service;
import it.csi.dma.apiopsan.esenzioniservice.PazienteSOL;
import it.csi.dma.apiopsan.esenzioniservice.RegimeDMA;
import it.csi.dma.apiopsan.esenzioniservice.Richiedente;
import it.csi.dma.apiopsan.esenzioniservice.RuoloDMA;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class EsenzioniService extends LoggerUtil {

	private static final QName SERVICE_NAME_ESENZIONI = new QName("http://dmacc.csi.it/",
			"EsenzioniService");

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	public EsenzioniResponse getEsenzioni(String shibIdentitaCodiceFiscale, String xRequestId,
			String xCodiceServizio, String xCodiceVerticale, String citId, String ruoloFse, String regime) {

		URL wsdlURL;

		try {
			String consensiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.ESENZIONI_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(consensiServiceUrl);
			EsenzioniService_Service cs = new EsenzioniService_Service(wsdlURL,
					SERVICE_NAME_ESENZIONI);
			it.csi.dma.apiopsan.esenzioniservice.EsenzioniService port = cs.getEsenzioniServicePort();
			
			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
			
			client.getOutInterceptors().add(loggingOutInterceptor());
			client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());

			EsenzioniRequest req = createRicercaEsenzioniRequest(shibIdentitaCodiceFiscale, ruoloFse, citId,
					xRequestId, xCodiceServizio, xCodiceVerticale, regime);

			EsenzioniResponse esenzioniResponse = port.getEsenzioni(req);

			return esenzioniResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.ESENZIONI_SERVICE);
		}
	}

	protected EsenzioniRequest createRicercaEsenzioniRequest(String shibIdentitaCodiceFiscale, String ruoloFse,
			String citId, String xRequestId, String xCodiceServizio, String xCodiceVerticale, String regime)
			throws DatabaseException {

		EsenzioniRequest req = new EsenzioniRequest();
		PazienteSOL paziente = new PazienteSOL();
		Richiedente richiedente = new Richiedente();
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		ApplicativoVerticale av = new ApplicativoVerticale();
		RuoloDMA ruolo = new RuoloDMA();
		ruolo.setCodice(ruoloFse);
		
		RegimeDMA regimedma = new RegimeDMA();
		regimedma.setCodice(regime);
	
		ap.setCodice(xCodiceServizio);
		av.setCodice(xCodiceVerticale);
		paziente.setCodiceFiscale(citId);
		
		richiedente.setApplicazione(ap);
		richiedente.setApplicativoVerticale(av);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);
		richiedente.setRuolo(ruolo);
		richiedente.setRegime(regimedma);
		
    	req.setPaziente(paziente);
		req.setRichiedente(richiedente);

		return req;

	}
	
	private LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	
	private LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}
	
	private SoapInterceptor createVerificaServicesSecurityInterceptor() throws DatabaseException {
		  Map<String, Object> outProps = new HashMap<String, Object>();
		  CredenzialiServizio credenziali = datiServiziEsterniDao.selectCredenzialiPerServizio(Constants.ESENZIONI_SERVICE);
		  outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		    outProps.put(WSHandlerConstants.USER, credenziali.getUsername());
		    outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		    outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_NONCE, "true");
		    outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_CREATED, "true");
		    outProps.put(WSHandlerConstants.MUST_UNDERSTAND, "false");
		    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {

		    	@Override
		        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		            for(Callback callBack:callbacks){
		                if(callBack instanceof WSPasswordCallback){
		                    ((WSPasswordCallback)callBack).setPassword(credenziali.getPassword());
		                }
		            }
		        }
            });


		    return new WSS4JOutInterceptor(outProps);
	}
}
