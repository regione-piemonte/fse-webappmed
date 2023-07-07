/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl.getDocumento;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.CfAssistitoType;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.ElencoCFAssistitoType;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.Errore;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.Richiedente;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl.CLDocumentiINIService;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl.CLDocumentiINIServicePortType;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl.RecuperoDocumentoIniRequest;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl.RecuperoDocumentoIniResponse;
import it.csi.dma.apiopsan.util.ClientPasswordCallback;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;


@Component
public class RecuperoDocumentiIniCL extends LoggerUtil {

	private static final QName SERVICE_CL_DOCUMENTI_INI = new QName("http://dmaclbl.csi.it", "CLDocumentiINIService");
	
	@Value("${clEpisodioUserBe}")
	private String clEpisodioUserBe;
	
	@Value("${clEpisodioPassBe}")
	private String clEpisodioPassBe;
	

	
	public RecuperoDocumentoIniResponse getDocumento(RecuperoDocumentoIniRequest req, ServizioComponenteLocale servizioComponenteLocale) {
		
		String methodName = "getDocumento";

		URL wsdlURL;
		try {
			String url = servizioComponenteLocale.getServizioUrl();
			wsdlURL = new URL(url);
			
			RecuperoDocumentoIniResponse documentoRes = new RecuperoDocumentoIniResponse();	
			
			Service cs = Service.create(CLDocumentiINIService.SERVICE);
			cs.addPort(CLDocumentiINIService.SERVICE, SOAPBinding.SOAP12HTTP_BINDING, url);
			
			CLDocumentiINIServicePortType port = cs.getPort(CLDocumentiINIService.SERVICE, CLDocumentiINIServicePortType.class);
			
			Client   client      = ClientProxy.getClient(port);
	        Endpoint cxfEndpoint = client.getEndpoint();
			
			Map<String, Object> outProps = new HashMap<String, Object>();
	        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN+ " " + WSHandlerConstants.TIMESTAMP);
	        ClientPasswordCallback clientPasswordCallback = new ClientPasswordCallback();
	        clientPasswordCallback.setPasswordDMA(clEpisodioUserBe);
	        clientPasswordCallback.setUserDMA(clEpisodioPassBe);
	        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, clientPasswordCallback);
	        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST); 
	        outProps.put(WSHandlerConstants.USER, clEpisodioUserBe); 
	        
	        WSS4JOutInterceptor outInterceptor = new WSS4JOutInterceptor(outProps);
	        cxfEndpoint.getOutInterceptors().add(outInterceptor);
	        
			BindingProvider bindingProvider = (BindingProvider)port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
			
			documentoRes = port.getRecuperoDocumentiIni(req);
			
			return documentoRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore non recuperabile chiamata servizio in CL: " + Constants.CL_DOC_INI_SERVIZIO);
		}
	}
	
	public RecuperoDocumentoIniRequest buildRequestForGetDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, 
			String ruoloFse, String regime, String collocazione,
			String citId, String firmato,  String identificativoDocumento, String identificativoRepository) {
		
		RecuperoDocumentoIniRequest req = new RecuperoDocumentoIniRequest();

		// Richiedente
		Richiedente richiedente = new Richiedente();
		
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		
		//TODO: verificare che codice applicazione inviare, con xCodiceServizio ottengo:
		// CL_ER_002
		// Il parametro richiedente.applicazione contiene il valore DMAWA non corretto 
		//    
		//gli unici codici che funzionano sono WEBAPP_DMA e WEBAPP_CM e SANMEDCOD 
				
		if (Constants.CODICE_CONTATTO_DIGITALE.equals(xCodiceVerticale)) {
			//passare SANMEDCOD, perche' e' stata introdotta logica sull'oscuramento in base all'app chiamante anche nella CL 
			ap.setCodice(Constants.CODICE_CONTATTO_DIGITALE);
		} else {
			//quando non e' SANMEDCOD, passare sempre WEBAPP_DMA
			ap.setCodice("WEBAPP_DMA");
		}

		
		RuoloDMA ruolo= new RuoloDMA();
		ruolo.setCodice(ruoloFse);
		RegimeDMA regimeDma = new RegimeDMA();
		regimeDma.setCodice(regime);
		regimeDma.setDescrizione(regime);
		
		richiedente.setRuolo(ruolo);
		richiedente.setApplicazione(ap);
		richiedente.setTokenOperazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setRegime(regimeDma);
		richiedente.setNumeroTransazione(xRequestId);
		req.setRichiedente(richiedente);
		
		// composizione dell'elenco assistito con il codice fiscale da ricercare
		ElencoCFAssistitoType elenco = new ElencoCFAssistitoType();
		CfAssistitoType assistito = new CfAssistitoType();
		assistito.setAttivo("true");
		assistito.setCf(citId);
		elenco.getCfAssistito().add(assistito);
		req.setElencoCFAssistito(elenco);
		
		req.setIdentificativoDocumento(identificativoDocumento);
		req.setIdentificativoRepository(identificativoRepository);
		req.setIdentificativoAssistito(citId);
		req.setIdentificativoOrganizzazione(Constants.IDENTIFICATIVO_ORGANIZZAZIONE);
		req.setIdentificativoUtente(shibIdentitaCodiceFiscale);

		return req;
		
	}
	
	public void verificaResponse(RecuperoDocumentoIniResponse res)  throws ResponseErrorException  {

		List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();

		if (res.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {			
			for (Errore errore : res.getErrori()) {
				ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
				errorservice.setChiave(errore.getCodice());
				errorservice.setValore(errore.getDescrizione());
				errorservice.setErroreId(errorservice.getErroreId());
				listError.add(errorservice);				
			}
		throw new ResponseErrorException(
				ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in Recupero documento da Componente Locale tramite codice_documento ");		

		}
	}
	
}
