/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;

import it.csi.dma.apiopsan.taccuinoservice.GetDieteRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetDieteResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetEventiRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetEventiResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetContattiStruttureRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetContattiStruttureResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetDoloriRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetDoloriResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetFarmaciRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetFarmaciResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetRilevazioniRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetRilevazioniResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetSintomiRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetSintomiResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetTaccuinoRequest;
import it.csi.dma.apiopsan.taccuinoservice.GetTaccuinoResponse;
import it.csi.dma.apiopsan.taccuinoservice.Richiedente;
import it.csi.dma.apiopsan.taccuinoservice.TaccuinoService;
import it.csi.dma.apiopsan.taccuinoservice.TaccuinoServiceImplService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Converter;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;

@Service
public class TaccuinoServiceExt extends LoggerUtil {
	private static final QName SERVICE_NAME_TACCUINO = new QName("http://dmaclbluc.dma.csi.it/", "TaccuinoServiceImplService");
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
	
	private LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	
	private LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}
	
	public GetTaccuinoResponse getTaccuino(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, String xForwardedFor, String xCodiceServizio, String citId, 
			String ruoloFse, String regime) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        client.getOutInterceptors().add(loggingOutInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	
			GetTaccuinoResponse getTaccuinoResponse = port.getTaccuino(creareTaccuniReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, 
					ruoloFse, regime));
			return getTaccuinoResponse;

		} catch (Exception e) {
			logError("getTaccuino", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}
	

	public GetFarmaciResponse getFarmaci(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio, String ruoloFse, String regime, String citId, Long taccuinoId,
			String ordinamento, String dataDa, String dataA) {
		URL wsdlURL;		
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	 
	        
	        GetFarmaciResponse getFarmaciResponse = port.getFarmaci(creareFarmaciReq(shibIdentitaCodiceFiscale,xRequestId,
	        		xCodiceVerticale, xForwardedFor, xCodiceServizio, ruoloFse, regime, citId, taccuinoId,
	        		ordinamento, dataDa, dataA));
			return getFarmaciResponse;

		} catch (Exception e) {
			logError("getFarmaci", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}
	
	
	private GetTaccuinoRequest creareTaccuniReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio,String citId, String ruoloFse, String regime) {
		
		GetTaccuinoRequest request  = new GetTaccuinoRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		return request;
	}
	
	private GetFarmaciRequest creareFarmaciReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio, String ruoloFse, String regime, String citId, Long taccuinoId,
			String ordinamento, String dataDa, String dataA) {
		
		GetFarmaciRequest request  = new GetFarmaciRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		
		request.setTaccuino(taccuinoId);
		
		request.setOrdinamento(ordinamento);
		
		if(dataA!=null)
			request.setDataA(convertToCalendar(dataA));
		
		if(dataDa!=null)
			request.setDataDa(convertToCalendar(dataDa));
		return request;
	}
	
	private GetDieteRequest creareDieteReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio, String ruoloFse, String regime, String citId, Long taccuinoId,
		    String ordinamento, String dataDa, String dataA) {
		
		GetDieteRequest request  = new GetDieteRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		
		request.setTaccuino(taccuinoId);
		
		request.setOrdinamento(ordinamento);
		
		if(dataA!=null)
			request.setDataA(convertToCalendar(dataA));
		
		if(dataDa!=null)
			request.setDataDa(convertToCalendar(dataDa));
		
		return request;
	}
	
	
	public GetDieteResponse getDiete(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, 
			String xForwardedFor, String xCodiceServizio, String ruoloFse, String regime, String citId, Long taccuinoId,
		    String ordinamento, String dataDa, String dataA) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	        
	        GetDieteResponse getDieteResponse = port.getDiete(creareDieteReq(shibIdentitaCodiceFiscale,xRequestId,
	        		xCodiceVerticale, xForwardedFor, xCodiceServizio, ruoloFse, regime, citId, taccuinoId,
	        		ordinamento, dataDa, dataA));
			return getDieteResponse;

		} catch (Exception e) {
			logError("getDiete", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}
	

	private GetEventiRequest creareEventiReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio, String ruoloFse, String regime, String citId, Long taccuinoId,
			String ordinamento, String dataDa, String dataA) {
		
		GetEventiRequest request  = new GetEventiRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		
		request.setTaccuino(taccuinoId);

		request.setOrdinamento(ordinamento);
		
		if(dataA!=null)
			request.setDataA(convertToCalendar(dataA));
		
		if(dataDa!=null)
			request.setDataDa(convertToCalendar(dataDa));
		return request;
	}
	
	public GetEventiResponse getEventi(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, 
			String xForwardedFor, String xCodiceServizio, String ruoloFse, String regime, String citId, Long taccuinoId, 
			String ordinamento, String dataDa, String dataA) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	        
	        GetEventiResponse getEventiResponse = port.getEventi(creareEventiReq(shibIdentitaCodiceFiscale,xRequestId,
	        		xCodiceVerticale, xForwardedFor, xCodiceServizio, ruoloFse, regime, citId, taccuinoId,
	        		ordinamento, dataDa, dataA));
			return getEventiResponse;

		} catch (Exception e) {
			logError("getEventi", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}


	public GetSintomiResponse getSintomi(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, String xForwardedFor, String xCodiceServizio, String citId, String ruoloFse, String regime, Long taccuino,String dataDa, String dataA,String ordinamento) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	        GetSintomiResponse getSintomiResponse = port.getSintomi(creareSintomiReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, ruoloFse, regime, taccuino,dataA,dataDa,ordinamento));
			return getSintomiResponse;

		} catch (Exception e) {
			logError("getSintomi", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}
	
	


	public GetContattiStruttureResponse getContattiStruttura(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, String xForwardedFor, String xCodiceServizio, String citId, String ruoloFse, String regime, Long taccuino,String dataDa, String dataA,String ordinamento) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	        GetContattiStruttureResponse getContattiStrutturaResponse = port.getContattiStrutture(creareContattiStruttureReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, ruoloFse, regime, taccuino,dataA,dataDa,ordinamento));
			return getContattiStrutturaResponse;

		} catch (Exception e) {
			logError("getContattiStrutture", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}
	
	public GetRilevazioniResponse getDettaglioRilevazioni(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, String xForwardedFor, String xCodiceServizio, String citId, String ruoloFse, String regime, Long taccuino,String dataDa, String dataA,String ordinamento,List<String> listaCodiceGruppo,List<String> listaCodiceDescrittore) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	        GetRilevazioniResponse getDettaglioRilevazioniResponse = port.getRilevazioni(creareRilevazioniReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, ruoloFse, regime,taccuino,dataA,dataDa,ordinamento,listaCodiceGruppo,listaCodiceDescrittore));
			return getDettaglioRilevazioniResponse;

		} catch (Exception e) {
			logError("getRilevazioni", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}
	
	private GetSintomiRequest creareSintomiReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio,String citId, String ruoloFse, String regime, Long taccuino,String dataDa, String dataA,String ordinamento) {
		
		GetSintomiRequest request  = new GetSintomiRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		
		request.setTaccuino(taccuino);
		
		request.setOrdinamento(ordinamento);
		
		if(dataA!=null)
			request.setDataA(convertToCalendar(dataA));
		
		if(dataDa!=null)
			request.setDataDa(convertToCalendar(dataDa));
		

		return request;
	}
	
	private GetContattiStruttureRequest creareContattiStruttureReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio,String citId, String ruoloFse, String regime, Long taccuino,String dataDa, String dataA,String ordinamento) {
		
		GetContattiStruttureRequest request  = new GetContattiStruttureRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		
		request.setTaccuino(taccuino);

		request.setOrdinamento(ordinamento);
		
		if(dataA!=null)
			request.setDataA(convertToCalendar(dataA));
		
		if(dataDa!=null)
			request.setDataDa(convertToCalendar(dataDa));

		return request;
	}
	
	private GetRilevazioniRequest creareRilevazioniReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio,String citId, String ruoloFse, String regime, Long taccuino,String dataDa, String dataA,String ordinamento,List<String> listaCodiceGruppo,List<String> listaCodiceDescrittore) {
		
		GetRilevazioniRequest request  = new GetRilevazioniRequest();
		

	   	Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		listaCodiceGruppo.forEach(codice->{
			request.getListaCodiceGruppo().add(codice);
		});
		listaCodiceDescrittore.forEach(codice->{
			request.getListaCodiceDescrittore().add(codice);
		});
		request.setRichiedente(richiedente);
		request.setPaziente(citId);
		
		request.setTaccuino(taccuino);

		request.setOrdinamento(ordinamento);
		
		if(dataA!=null)
			request.setDataA(convertToCalendar(dataA));
		
		if(dataDa!=null)
			request.setDataDa(convertToCalendar(dataDa));
		

		return request;
	}


	private String getAccessToken() throws DatabaseException {
	
		  CredenzialiServizio credenziali = datiServiziEsterniDao.selectCredenzialiPerServizio(Constants.GET_TACCUINO_SERVICE);
		  return Util.getBasicAuthenticationHeader(credenziali.getUsername(),credenziali.getPassword());
	}
	
	private XMLGregorianCalendar convertToCalendar(String date) {
		
		Date data = Converter.getDataWithoutTime(date);
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		if(data!=null)
			gc.setTime(data);
		
		try {
			if(data!=null)
				xmlDate= DatatypeFactory.newInstance().newXMLGregorianCalendar(gc); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return xmlDate;
	}


	public GetDoloriResponse getDolori(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale, String xForwardedFor, String xCodiceServizio, String citId, String ruoloFse, String regime, Long taccuino,String a,String da,String ordinamento) {
		URL wsdlURL;
		try {
			String taccuinoServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.TACCUINO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(taccuinoServiceUrl);
			
			TaccuinoServiceImplService ts = new TaccuinoServiceImplService(wsdlURL, SERVICE_NAME_TACCUINO);
	        TaccuinoService port = ts.getTaccuinoServicePort();
	        
	        String accessToken = getAccessToken();
	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
	        
	        Map<String, List<String>> headers = new HashMap<String, List<String>>();
	        headers.put("Authorization", Arrays.asList(accessToken));
	        client.getRequestContext().put(Message.PROTOCOL_HEADERS, headers);
	     	        
	        GetDoloriResponse getDoloriResponse = port.getDolori(creareDoloriReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, ruoloFse, regime, taccuino,a,da,ordinamento));
			return getDoloriResponse;

		} catch (Exception e) {
			logError("getTaccuino", e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.GET_TACCUINO_SERVICE);
		}
	}



	private GetDoloriRequest creareDoloriReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceVerticale,
			String xForwardedFor, String xCodiceServizio,String citId, String ruoloFse, String regime, Long taccuino,String a,String da,String ordinamento) {
		
		GetDoloriRequest request  = new GetDoloriRequest();
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicativoVerticale(Constants.TACCUINO_APPLLICATIVO_VERTICALE);
		richiedente.setApplicazione(Constants.TACCUINO_APPLICAZIONE);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIdentificativoIp(xForwardedFor);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setRuolo(ruoloFse);
		richiedente.setRegime(regime);
		
		request.setRichiedente(richiedente);
		
		request.setPaziente(citId);
		
		request.setTaccuino(taccuino);

		request.setOrdinamento(ordinamento);
		
		if(a!=null && !a.isEmpty())
			request.setDataA(convertToCalendar(a));
		
		if(da!=null && !da.isEmpty())
			request.setDataDa(convertToCalendar(da));
		

		return request;
	}


}
