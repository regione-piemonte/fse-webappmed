/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl.getDocumento;

import java.net.URL;
import java.text.SimpleDateFormat;
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

import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.AziendaSanitaria;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.ComponenteLocale;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Errore;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.LuogoASU;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.MetadatiDocumento;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Paziente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Richiedente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.SiNo;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.TipoDocumento;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.TipoFileDocumento;
import it.csi.dma.apiopsan.external.clepisodioservice.dmaclbl.PagatoTicketStato;
import it.csi.dma.apiopsan.external.clepisodioservice.episodioservice.CLEpisodioService;
import it.csi.dma.apiopsan.external.clepisodioservice.episodioservice.CLEpisodioServicePortType;
import it.csi.dma.apiopsan.external.clepisodioservice.episodioservice.GetDocumentoReq;
import it.csi.dma.apiopsan.external.clepisodioservice.episodioservice.GetDocumentoRes;
import it.csi.dma.apiopsan.util.ClientPasswordCallback;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;


@Component
public class GetDocumentoCL extends LoggerUtil {

	private static final QName SERVICE_CL_EPISODIO = new QName("http://EpisodioService.WS.DMACL.csi.it", "CLEpisodioService");
	
	@Value("${clEpisodioUserBe}")
	private String clEpisodioUserBe;
	
	@Value("${clEpisodioPassBe}")
	private String clEpisodioPassBe;
	

	
	public GetDocumentoRes getDocumento(GetDocumentoReq req, ServizioComponenteLocale servizioComponenteLocale) {
		
		String methodName = "getDocumento";

		URL wsdlURL;
		try {
			String url = servizioComponenteLocale.getServizioUrl();
			wsdlURL = new URL(url);
			
			GetDocumentoRes documentoRes = new GetDocumentoRes();	
			

			//CLEpisodioService cs = new CLEpisodioService(wsdlURL,SERVICE_CL_EPISODIO);
			Service cs = Service.create(CLEpisodioService.SERVICE);
			cs.addPort(CLEpisodioService.SERVICE, SOAPBinding.SOAP12HTTP_BINDING, url);
			
			CLEpisodioServicePortType port = cs.getPort(CLEpisodioService.SERVICE, CLEpisodioServicePortType.class);
			
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
			
			documentoRes = port.getDocumento(req);
			
			return documentoRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore non recuperabile chiamata servizio: " + Constants.RECUPERA_DOCUMENTI_SERVICE_PO);
		}
	}
	
	public GetDocumentoReq buildRequestForGetDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, 
			String ruoloFse, String regime, String collocazione,
			String citId, String firmato,  DmaccidxTDocumento tDocumento, List<String> storicoCodiciFiscali) {
		
		GetDocumentoReq req = new GetDocumentoReq();

		// Richiedente
		Richiedente richiedente = new Richiedente();
		
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		
		//TODO: verificare che codice applicazione inviare, con xCodiceServizio ottengo:
		// CL_ER_002
		// Il parametro richiedente.applicazione contiene il valore DMAWA non corretto 
		//    
		//gli unici codici che funzionano sono WEBAPP_DMA e WEBAPP_CM e SANMEDCOD 
				
		if (Constants.CODICE_CONTATTO_DIGITALE.equals(xCodiceVerticale) 
				|| Constants.CODICE_CONTATTO_DIGITALE.equals(xCodiceServizio)) {
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
		
		Paziente paziente = new Paziente();
		paziente.setCodiceFiscale(citId);
		//se non metto id paziente Irec va in errore CL_ERR_000
		paziente.setIdIrec(tDocumento.getIdPazIrec());
		req.setPaziente(paziente);
		

		MetadatiDocumento metadatiDocumento = new MetadatiDocumento();
		//metadatiDocumento.setAccessionNumber("1234"); // non sembra richiesto
		ComponenteLocale componenteLocale = new ComponenteLocale();
		componenteLocale.setCodice(tDocumento.getCodCl());

		metadatiDocumento.setComponenteLocale(componenteLocale);
		metadatiDocumento.setConsultabile(SiNo.SI);
		//metadatiDocumento.setDataValidazione(new SimpleDateFormat("yyyy-MM-dd").format(tDocumento.getDataValidazione()));
		//metadatiDocumento.setFirmatoDigitalmente(transcodificaSiNo(tDocumento.getFirmatoDigitalmente()));
		metadatiDocumento.setIdDocumento(tDocumento.getIdDocumentoIlec().toString());
		metadatiDocumento.setIdEpisodio(tDocumento.getIdEpisodioIlec().toString());
		
		AziendaSanitaria aziendaSanitaria = new AziendaSanitaria();
		LuogoASU luogoASU = new LuogoASU();
		aziendaSanitaria.setCodice(tDocumento.getCodiceAziendaSanitaria());
		luogoASU.setAzienda(aziendaSanitaria);
		metadatiDocumento.setLuogoProduzioneDocumento(luogoASU);
		
		//metadatiDocumento.setPagatoTicket(PagatoTicketStato.valueOf(tDocumento.getPagatoTicket()));
		TipoDocumento tipoDocumento = new TipoDocumento();
		tipoDocumento.setCodice(tDocumento.getCodTipoDocumento());
		TipoFileDocumento tipoFileDocumento = new TipoFileDocumento();
		tipoFileDocumento.setCodice(tDocumento.getCodTipoFile());
		metadatiDocumento.setTipoDocumento(tipoDocumento);
		metadatiDocumento.setTipoFileDocumento(tipoFileDocumento);

		req.setMetadatiDocumento(metadatiDocumento);
		
		req.setCriptato(SiNo.NO);
		
		//req.setFirmatoDigitalmente(transcodificaSiNo(firmato));
		req.setFirmatoDigitalmente(SiNo.NO);
		
		if (storicoCodiciFiscali != null && storicoCodiciFiscali.size()>0) {
			for (String codice: storicoCodiciFiscali) {
				Paziente storico = new Paziente();
				storico.setCodiceFiscale(codice);
				req.getStoricoPaziente().add(storico);
			}
		}
		return req;
		
	}
	
	public void verificaResponse(GetDocumentoRes res)  throws ResponseErrorException  {

		List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();

		if (res.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {			
			for (Errore errore : res.getErrori().getErrore()) {
				ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
				errorservice.setChiave(errore.getCodice());
				errorservice.setValore(errore.getDescrizione());
				errorservice.setErroreId(errorservice.getErroreId());
				listError.add(errorservice);				
			}
		throw new ResponseErrorException(
				ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in Recupero documento da Componente Locale");		

		}
	}
	
	private SiNo transcodificaSiNo(String valoreDaDB) {
		if (Constants.S.equals(valoreDaDB)) {
			return SiNo.SI;
		} else if (Constants.N.contentEquals(valoreDaDB)) {
			return SiNo.NO;
		} else {
			return null;
		}	
	}
}
