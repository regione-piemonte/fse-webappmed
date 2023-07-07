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

import it.csi.dma.apiopsan.documentiservice.dmacc.ApplicativoVerticale;
import it.csi.dma.apiopsan.documentiservice.dmacc.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.documentiservice.dma.ComponenteLocale;
import it.csi.dma.apiopsan.documentiservice.dma.MetadatiDocumento;
import it.csi.dma.apiopsan.documentiservice.dma.Paziente;
import it.csi.dma.apiopsan.documentiservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.documentiservice.dma.Richiedente;
import it.csi.dma.apiopsan.documentiservice.dma.RichiedenteInfo;
import it.csi.dma.apiopsan.documentiservice.dma.Ruolo;
import it.csi.dma.apiopsan.documentiservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.documentiservice.dma.TipoCorrelazioneDocumento;
import it.csi.dma.apiopsan.documentiservice.dmacc.DocumentiService;
import it.csi.dma.apiopsan.documentiservice.dmacc.DocumentiService_Service;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiRequest;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.IdentificativoDocumento;
import it.csi.dma.apiopsan.documentiservice.dmacc.SmediazioneDocumentiRequest;
import it.csi.dma.apiopsan.documentiservice.dmacc.SmediazioneDocumentiResponse;
import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaService;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaService_Service;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaUtenteAbilitatoResponse;


@Service
public class DocumentiServiceApisan extends LoggerUtil {
	private static final QName SERVICE_NAME_DOCUMENTI = new QName("http://dmacc.csi.it/", "DocumentiService");
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
												  
	public GetDocumentiCorrelatiResponse getDocumentiCorrelati(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, String citId, String ruoloFse, String tipoCorrelazioneDocumento,String idDocumento,String codiceCl,String xCodiceVerticale,String xForwardedFor) {
		URL wsdlURL;
		try {
			String documentiServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.DOCUMENTI_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(documentiServiceUrl);
			DocumentiService_Service cs = new DocumentiService_Service(wsdlURL, SERVICE_NAME_DOCUMENTI);
			DocumentiService port = cs.getDocumentiServicePort();
			
			

			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
			
			client.getOutInterceptors().add(loggingOutInterceptor());
			client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());
			
			GetDocumentiCorrelatiResponse documentiCorrelatiResp = port.getDocumentiCorrelati(creareDocumenticorrelatiReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceServizio,citId,
					ruoloFse,tipoCorrelazioneDocumento,idDocumento,codiceCl,xCodiceVerticale,xForwardedFor));
			return documentiCorrelatiResp;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.DOCUMENTI_SERVICE);
		}
	}

	protected GetDocumentiCorrelatiRequest creareDocumenticorrelatiReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, String citId,
		String ruoloFse, String tipoCorrelazioneDocumento,String idDocumento,String codiceCl,String xCodiceVerticale,String xForwardedFor) throws DatabaseException {
		
		
	GetDocumentiCorrelatiRequest documentoReq = new GetDocumentiCorrelatiRequest();
		
	
	IdentificativoDocumento iddocumento = new IdentificativoDocumento();
	iddocumento.setCodiceCL(codiceCl);
	iddocumento.setIdDocumentoIlec(idDocumento);
	documentoReq.setDocumento(iddocumento);
	TipoCorrelazioneDocumento tipocorr = null;
	switch (tipoCorrelazioneDocumento) {
		case "NRE" : 
			tipocorr=TipoCorrelazioneDocumento.NRE;
		break;
		case "NUMERO_PASSAGGIO_PS" : 
			tipocorr=TipoCorrelazioneDocumento.NUMERO_PASSAGGIO_PS;
		break;
		case "NUMERO_SDO" : 
			tipocorr=TipoCorrelazioneDocumento.NUMERO_SDO;
		break;
		case "PADRE_FIGLIO" :
			tipocorr= TipoCorrelazioneDocumento.PADRE_FIGLIO;
		break;
	}
	
	documentoReq.setCfPaziente(citId);
	documentoReq.setTipoCorrelazioneDocumento(tipocorr);
	
	ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
	ap.setCodice(xCodiceServizio);
	Ruolo ruolo = new Ruolo();
	ruolo.setCodice(ruoloFse);
	
		RichiedenteInfo rich = new RichiedenteInfo();	
		rich.setCodiceFiscale(shibIdentitaCodiceFiscale);
		rich.setNumeroTransazione(xRequestId);
		rich.setRuolo(ruolo);
		rich.setIp(xForwardedFor);
		
		ApplicativoVerticale appVert= new ApplicativoVerticale();
		appVert.setCodice(xCodiceVerticale);
		rich.setApplicativoVerticale(appVert);
		rich.setApplicazione(ap);
		
		
		documentoReq.setRichiedente(rich);
		
	
		return documentoReq;
		
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
	
	public SmediazioneDocumentiResponse smediazioneDocumenti(String xCodiceServizio, String shibIdentitaCodiceFiscale,
			String numeroTransazione, String regime, String ruolo, String ruoloFse, String tokenOperazione, String collocazioneCodice,
			String collocazioneDesc, String citId, Long idDocumento, String codiceComponenteLocale) {

		URL wsdlURL;
		try {	
			String documentiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.DOCUMENTI_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(documentiServiceUrl);
			DocumentiService_Service cs = new DocumentiService_Service(wsdlURL, SERVICE_NAME_DOCUMENTI);
			DocumentiService port = cs.getDocumentiServicePort();

			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

			client.getOutInterceptors().add(loggingOutInterceptor());
			client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());

			SmediazioneDocumentiResponse mediazioneDocumentiResponse = port.smediazioneDocumenti(creaSmediazioneRequest(
					xCodiceServizio, shibIdentitaCodiceFiscale, numeroTransazione, regime, ruolo, ruoloFse, tokenOperazione,
					collocazioneCodice, collocazioneDesc, citId, idDocumento, codiceComponenteLocale));
			return mediazioneDocumentiResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.DOCUMENTI_SERVICE);
		}
	}
	
	protected SmediazioneDocumentiRequest creaSmediazioneRequest(String xCodiceServizio,
			String shibIdentitaCodiceFiscale, String numeroTransazione, String regime, String ruolo, String ruoloFse,
			String tokenOperazione, String collocazioneCodice, String collocazioneDesc, String citId,
			Long idDocumento, String codiceComponenteLocale) throws DatabaseException {

		SmediazioneDocumentiRequest mediazioneDocRequest = new SmediazioneDocumentiRequest();

//		Richiedente.Collocazione.Codice	Collocazione_codice

		Richiedente richiedente = new Richiedente();
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale); //Richiedente.CodiceFiscale	shib-Identita-CodiceFiscale (utente loggato)
		richiedente.setNumeroTransazione(numeroTransazione); //Richiedente.numeroTransazione	x-Request-Id = numerotransazione
		richiedente.setTokenOperazione(tokenOperazione); //Richiedente.tokenOperazione	x-Request-Id
		
		it.csi.dma.apiopsan.documentiservice.dma.ApplicazioneRichiedente appRichiedente = new it.csi.dma.apiopsan.documentiservice.dma.ApplicazioneRichiedente();
		appRichiedente.setCodice(xCodiceServizio); //		Richiedente.applicazione.codice	x-Codice-Servizio
		appRichiedente.setDescrizione(xCodiceServizio); //  	Richiedente.applicazione.descrizione x-Codice-Servizio

		richiedente.setApplicazione(appRichiedente);

		RegimeDMA reg = new RegimeDMA();
		reg.setCodice(regime);//		Richiedente.regime.codice	regime
		richiedente.setRegime(reg);
		
		RuoloDMA r = new RuoloDMA();
		r.setCodice(ruoloFse);//		Richiedente.ruolo.codice	ruolo
		richiedente.setRuolo(r);
		
		mediazioneDocRequest.setRichiedente(richiedente);
		
		Paziente paziente = new Paziente();
		paziente.setCodiceFiscale(citId); //		Paziente.CodiceFiscale	citid
		mediazioneDocRequest.setPaziente(paziente);
		
		MetadatiDocumento metadati = new MetadatiDocumento();
		metadati.setIdDocumento(idDocumento.toString()); //		metadatiDocumento.idDocumento	idDocumento
		
		ComponenteLocale componenteLocale = new ComponenteLocale();
		componenteLocale.setCodice(codiceComponenteLocale); //		metadatiDocumento.codCL	Codice_componente_locale
		metadati.setComponenteLocale(componenteLocale);
		mediazioneDocRequest.setMetadatiDocumento(metadati);

		return mediazioneDocRequest;
	}

}
