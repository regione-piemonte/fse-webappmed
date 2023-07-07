/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl.getDocumento;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Component;

import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.RichiedenteInfo;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.Ruolo;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmacc.ApplicativoVerticale;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmacc.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmacc.Errore;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.DocumentiService;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.DocumentiService_Service;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.GetDettaglioDocumentoRequest;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.GetDettaglioDocumentoResponse;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;


@Component
public class GetDettaglioDocumentoCL extends LoggerUtil {
	
	private static final QName SERVICE_NAME_GET_DETTAGLIO_DOC = new QName("http://dmaclbl.csi.it/",
			"DocumentiService");
	
	public GetDettaglioDocumentoResponse getDettaglioDocumento(GetDettaglioDocumentoRequest req, ServizioComponenteLocale servizioComponenteLocale) {
		String methodName = "getDettaglioDocumento";

		URL wsdlURL;
		try {
			String url = servizioComponenteLocale.getServizioUrl()  + "?wsdl";
			wsdlURL = new URL(url);
			GetDettaglioDocumentoResponse dettaglioDocumentoRes = new GetDettaglioDocumentoResponse();	
			
			DocumentiService_Service cs = new DocumentiService_Service(wsdlURL,SERVICE_NAME_GET_DETTAGLIO_DOC);				
			DocumentiService port = cs.getDocumentiServicePort();
			dettaglioDocumentoRes = port.getDettaglioDocumentoNOAS(req);
			return dettaglioDocumentoRes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore non recuperabile chiamata servizio: " + Constants.GET_DETTAGLIO_PRESTAZIONI_SERVIZIO_CL);
		}		
	}
	
	public GetDettaglioDocumentoRequest buildRequest(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, 
			String ruoloFse, 
			String citId,  Long idDocumentoIlec, String codTipoDocumento) {
		
		GetDettaglioDocumentoRequest req = new GetDettaglioDocumentoRequest();

		// Richiedente
		RichiedenteInfo richiedente = new RichiedenteInfo();
		
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		//passare WEBAPP_DMA oppure WEBAPP_CM, con DMAWA non funziona
		ap.setCodice("WEBAPP_DMA");
		
		ApplicativoVerticale av = new ApplicativoVerticale();
		av.setCodice(xCodiceVerticale);

		Ruolo ruolo= new Ruolo();
		ruolo.setCodice(ruoloFse);
		
		richiedente.setRuolo(ruolo);
		richiedente.setApplicazione(ap);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setIp(xForwardedFor);
		req.setRichiedente(richiedente);
		
		req.setCfPaziente(citId);
		req.setIdDocumentoIlec(idDocumentoIlec);
		req.setFlagROL(Constants.N);
		req.setTipoDocumentoMedio(codTipoDocumento);
		return req;
		
	}
	
	public void verificaResponse(GetDettaglioDocumentoResponse res)  throws ResponseErrorException  {

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
				ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in getDettaglioDocumenti da Componente Locale");		

		}
	}

}
