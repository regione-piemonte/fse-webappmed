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

import it.csi.dma.apiopsan.documentiservice.dmacc.SmediazioneDocumentiResponse;
import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.ApplicativoVerticale;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Richiedente;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl.ScansionaRefertoQRCodeRequest;
import it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl.ScansionaRefertoQRCodeResponse;
import it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl.ScaricoRefertoService;
import it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl.ScaricoRefertoService_Service;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;


@Service
public class ScaricoRefertoServiceApisan extends LoggerUtil {
	private static final QName SERVICE_NAME_SCARICO_REFERTO = new QName("http://dmaccbl.csi.it/", "ScaricoRefertoService");

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
												 
												
	private LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	
	private LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}
	
	private SoapInterceptor createVerificaServicesSecurityInterceptor() throws DatabaseException {
		  Map<String, Object> outProps = new HashMap<String, Object>();
		  CredenzialiServizio credenziali = datiServiziEsterniDao.selectCredenzialiPerServizio(Constants.SCARICOREFERTO_SERVICE);
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
	
	
	public ScansionaRefertoQRCodeResponse scansionaRefertoQRCode(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String qrcode) {

		URL wsdlURL;
		try {	
			String scaricoRefertoServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.SCARICOREFERTO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(scaricoRefertoServiceUrl);
			ScaricoRefertoService_Service cs = new ScaricoRefertoService_Service(wsdlURL, SERVICE_NAME_SCARICO_REFERTO);
			ScaricoRefertoService port = cs.getScaricoRefertoServicePort();

			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

			client.getOutInterceptors().add(loggingOutInterceptor());
			client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());

			ScansionaRefertoQRCodeResponse scansionaRefertoResp = port.scansionaRefertoQRCode(creaScansionaQRCodeRequest(
					shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, qrcode));
			return scansionaRefertoResp;


		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.SCARICOREFERTO_SERVICE);
		}
	}
	
	protected ScansionaRefertoQRCodeRequest creaScansionaQRCodeRequest(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String qrcode) throws DatabaseException {

		ScansionaRefertoQRCodeRequest scansionaQRCodeRequest = new ScansionaRefertoQRCodeRequest();

		scansionaQRCodeRequest.setToken(qrcode);
		
		Richiedente richiedente = new Richiedente();
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale); //Richiedente.CodiceFiscale	shib-Identita-CodiceFiscale (utente loggato)
		richiedente.setNumeroTransazione(xRequestId); //Richiedente.numeroTransazione	x-Request-Id = numerotransazione
		richiedente.setTokenOperazione(xRequestId); //Richiedente.tokenOperazione	x-Request-Id
		
		it.csi.dma.apiopsan.scaricorefertoservice.dma.ApplicazioneRichiedente appRichiedente = new it.csi.dma.apiopsan.scaricorefertoservice.dma.ApplicazioneRichiedente();

		appRichiedente.setCodice("WEBAPP_DMA"); 
		appRichiedente.setDescrizione("WEBAPP_DMA"); 
		richiedente.setApplicazione(appRichiedente);

		RegimeDMA reg = new RegimeDMA();
		reg.setCodice(regime);//		Richiedente.regime.codice	regime
		reg.setDescrizione(regime);//	Richiedente.regime.descrizione	regime
		richiedente.setRegime(reg);
		
		RuoloDMA r = new RuoloDMA();
		r.setCodice(ruoloFse);//		Richiedente.ruolo.codice	ruolo
		richiedente.setRuolo(r);
		
		scansionaQRCodeRequest.setRichiedente(richiedente);
		
	
		return scansionaQRCodeRequest;
	}
	

}
