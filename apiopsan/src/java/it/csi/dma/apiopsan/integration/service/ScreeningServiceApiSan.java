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


import it.csi.dma.apiopsan.documentiservice.dma.RichiedenteInfo;
import it.csi.dma.apiopsan.documentiservice.dma.Ruolo;
import it.csi.dma.apiopsan.documentiservice.dma.TipoCorrelazioneDocumento;
import it.csi.dma.apiopsan.documentiservice.dmacc.DocumentiService;
import it.csi.dma.apiopsan.documentiservice.dmacc.DocumentiService_Service;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiRequest;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.IdentificativoDocumento;
import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.screeningservice.dmacc.ScreeningRequest;
import it.csi.dma.apiopsan.screeningservice.dmacc.ScreeningResponse;
import it.csi.dma.apiopsan.screeningservice.dmacc.ScreeningService;
import it.csi.dma.apiopsan.screeningservice.dmacc.ScreeningService_Service;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ApplicativoVerticale;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.screeningservice.dmaccl.PazienteSOL;
import it.csi.dma.apiopsan.screeningservice.dmaccl.RegimeDMA;
import it.csi.dma.apiopsan.screeningservice.dmaccl.Richiedente;
import it.csi.dma.apiopsan.screeningservice.dmaccl.RuoloDMA;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;


@Service
public class ScreeningServiceApiSan extends LoggerUtil {
	private static final QName SERVICE_NAME_SCREENING = new QName("http://dmacc.csi.it/", "ScreeningService");
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
												  
	public ScreeningResponse getScreening(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio,
			String ruoloFse,String xCodiceVerticale,String xForwardedFor,String citId,String regime) {
		URL wsdlURL;
		try {
			String screeningServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.SCREENING_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(screeningServiceUrl);
			ScreeningService_Service cs = new ScreeningService_Service(wsdlURL, SERVICE_NAME_SCREENING);
			ScreeningService port = cs.getScreeningServicePort();
			
			

			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
			
			client.getOutInterceptors().add(loggingOutInterceptor());
			client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());
			
				
			ScreeningResponse screeningResponse = port.getScreening(creareGetScreeningReq(shibIdentitaCodiceFiscale, xRequestId, xCodiceServizio, ruoloFse, xCodiceVerticale, xForwardedFor, citId, regime));;
			return screeningResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.SCREENING_SERVICE);
		}
	}

	protected ScreeningRequest creareGetScreeningReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio,
		String ruoloFse,String xCodiceVerticale,String xForwardedFor,String citId,String regime) throws DatabaseException {
		
		
	ScreeningRequest screeningReq = new ScreeningRequest();
		
	PazienteSOL paziente = new PazienteSOL();
	paziente.setCodiceFiscale(citId);
	
	Richiedente richiedente = new Richiedente();
	ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
	ap.setCodice(xCodiceServizio);
	
	RuoloDMA ruolo= new RuoloDMA();
	ruolo.setCodice(ruoloFse);
	
	ApplicativoVerticale appVert= new ApplicativoVerticale();
	appVert.setCodice(xCodiceVerticale);
	
	RegimeDMA regimeDma = new RegimeDMA();
	regimeDma.setCodice(regime);
	
	richiedente.setRuolo(ruolo);
	richiedente.setApplicativoVerticale(appVert);
	richiedente.setApplicazione(ap);
	richiedente.setTokenOperazione(xRequestId);
	richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
	richiedente.setRegime(regimeDma);
	richiedente.setNumeroTransazione(xRequestId);
	
	screeningReq.setPaziente(paziente);
	screeningReq.setRichiedente(richiedente);
	
	
		
	
		return screeningReq;
		
	}
	
	private LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	
	private LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}
	
	private SoapInterceptor createVerificaServicesSecurityInterceptor() throws DatabaseException {
		  Map<String, Object> outProps = new HashMap<String, Object>();
		  CredenzialiServizio credenziali = datiServiziEsterniDao.selectCredenzialiPerServizio(Constants.DOCUMENTI_SERVICE);
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
