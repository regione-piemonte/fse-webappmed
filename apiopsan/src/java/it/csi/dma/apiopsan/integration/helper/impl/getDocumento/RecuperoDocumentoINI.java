/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl.getDocumento;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.recuperadocumentoservice.CfAssistitoType;
import it.csi.dma.apiopsan.recuperadocumentoservice.ElencoCFAssistitoType;
import it.csi.dma.apiopsan.recuperadocumentoservice.Errore;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoIN;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoINIService;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoINIService_Service;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoRequeste;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoResponse;
import it.csi.dma.apiopsan.recuperadocumentoservice.Richiedente;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;

@Service
public class RecuperoDocumentoINI extends LoggerUtil {

	private static final QName SERVICE_NAME_RECUPERA_DOC = new QName("http://dmacc.csi.it/",
			"RecuperoDocumentoINIService");
	
	private static int MAX_NUM_RETRY = 3;

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
	
	public RecuperoDocumentoResponse getRecuperoDocumento(RecuperoDocumentoRequeste req) {
		
		String methodName = "getRecuperoDocumento";

		URL wsdlURL;
		int numRetry = 1;
		try {
			String url = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.RECUPERA_DOCUMENTI_SERVICE_PO) + "?wsdl";
			wsdlURL = new URL(url);
			
			RecuperoDocumentoResponse recuperoDocumentoResponse = new RecuperoDocumentoResponse();	
			
			while (numRetry <= MAX_NUM_RETRY)  {
				try {
					RecuperoDocumentoINIService_Service cs = new RecuperoDocumentoINIService_Service(wsdlURL,SERVICE_NAME_RECUPERA_DOC);				
					RecuperoDocumentoINIService port = cs.getRecuperoDocumentoINIServicePort();
					recuperoDocumentoResponse = port.recuperoDocumento(req);
					if (isErroreRete(recuperoDocumentoResponse)) {
						if (numRetry >= MAX_NUM_RETRY) {
							logInfo(methodName, "Raggiunto numero massimo di tentativi in chiamata a: " + Constants.RECUPERA_DOCUMENTI_SERVICE_PO );
							break;
						}
						Thread.sleep(1000);
						numRetry++;
						continue;
					}
					break;
				} catch (WebApplicationException | WebServiceException e){
					logInfo(methodName, "WebException in chiamata a: " + Constants.RECUPERA_DOCUMENTI_SERVICE_PO + " "+ e.getMessage() );
					if (numRetry >= MAX_NUM_RETRY) {
						logInfo(methodName, "Raggiunto numero massimo di tentativi in chiamata a: " + Constants.RECUPERA_DOCUMENTI_SERVICE_PO );
						break;
					}
					Thread.sleep(1000);
					numRetry++;
				}
			}
			return recuperoDocumentoResponse;
		} catch (Exception e) {
			logError(methodName, "Errore : " + e.getMessage());
//			e.printStackTrace();
			throw new RuntimeException("Errore non recuperabile chiamata servizio: " + Constants.RECUPERA_DOCUMENTI_SERVICE_PO);
		}
	}
	
	private boolean isErroreRete(RecuperoDocumentoResponse recuperoDocumentoResponse) {
		if (recuperoDocumentoResponse == null) {
			return true;
		} 
		if (recuperoDocumentoResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
			for (Errore errore : recuperoDocumentoResponse.getErrori()) {
				if (Constants.CODICE_RISPOSTA_ERRORE_RETE.equals(errore.getCodice())) {
					return true;					
				}
			}
		}
		return false;
	}

	public byte[] getPdf(RecuperoDocumentoResponse res) {
		if (res != null  
				&& res.getEsito().name().equals(RisultatoCodice.SUCCESSO.name()) 
				&& res.getRecuperoDocumentoOUT() != null) {
			return res.getRecuperoDocumentoOUT().getDocumento();
		} else {
			return null;
		}		
	}

	public void verificaRecuperoDocumentoResponse(RecuperoDocumentoResponse recuperoDocumentoResponse) throws ResponseErrorException {
		List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();
		
		if (recuperoDocumentoResponse == null) {
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in Recupero Documento da INI - servizio non disponibile ");

		}
				
		if (recuperoDocumentoResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
			for (Errore errore : recuperoDocumentoResponse.getErrori()) {
				//errore bloccante se codice errore diverso da
				// 2048 - sei RCD/RDA - devi proseguire nel recupero su cl
				// 2007 - consenso negato - lo stato consensi ammette eccezioni, per esempio se richiedente-refertante, per cui non devi bloccarti qua
				if (! (errore.getCodice().equals(Constants.CODICE_RISPOSTA_PAZIENTE_RDA_RCD)
						||  errore.getCodice().equals(Constants.CODICE_RISPOSTA_CONSENSO_NEGATO)
						||  errore.getCodice().equals(Constants.CODICE_RISPOSTA_RECUPERO_SENZA_RICERCA)
						//TODO: verificare perche' risponde errore 103 se sei RDA  e non con 2048 come atteso
						|| errore.getCodice().equals("CC_ER_103") )) {
					ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
					errorservice.setChiave(errore.getCodice());
					errorservice.setValore(errore.getDescrizione());
					errorservice.setErroreId(errorservice.getErroreId());
					listError.add(errorservice);
				}
			}
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in Recupero Documento da INI");
			}
		}		
	}
	
	
	public RecuperoDocumentoRequeste buildRequestForGetDocumento(String shibIdentitaCodiceFiscale, String ruoloINI,
			String citId, String xRequestId, String xCodiceServizio, String regime, 
			String identificativoDocumento, String identificativoRepository )
			throws DatabaseException {


		RecuperoDocumentoRequeste req = new RecuperoDocumentoRequeste();
		RecuperoDocumentoIN in = new RecuperoDocumentoIN();

		in.setContestoOperativo(Constants.RICERCA_DOC_CONTESTO_OP);
		in.setIdentificativoAssistito(citId);

		// composizione dell'elenco assistito con il codice fiscale da ricercare
		ElencoCFAssistitoType elenco = new ElencoCFAssistitoType();
		CfAssistitoType assistito = new CfAssistitoType();
		assistito.setAttivo("S");
		assistito.setCf(citId);
		elenco.getCfAssistito().add(assistito);
		in.setElencoCFAssistito(elenco);

		in.setIdentificativoOrganizzazione(Constants.RICERCA_DOC_ID_ORG);
		in.setStrutturaUtente(Constants.RICERCA_DOC_STRUT_UTENTE);
		in.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		in.setPresaInCarico(Constants.TRUE.toLowerCase());
		in.setTipoAttivita(Constants.RICERCA_DOC_TIPO_ATTIVITA);
		in.setRuoloUtente(ruoloINI);
		
		in.setIdentificativoDocumento(identificativoDocumento);
		in.setIdentificativoRepository(identificativoRepository);
		

		Richiedente richiedente = new Richiedente();

		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);

		req.setRecuperoDocumentoIN(in);
		req.setRichiedente(richiedente);

		return req;

	}	



}
