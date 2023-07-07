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
import it.csi.dma.apiopsan.ricercadocumentiiniservice.CfAssistitoType;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.ElencoCFAssistitoType;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.Errore;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiIN;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiINIService;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiINIService_Service;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiRequeste;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiResponse;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.Richiedente;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;

@Service
public class RicercaDocumentiINI extends LoggerUtil {

	private static final QName SERVICE_NAME_RICERCA_DOC = new QName("http://dmacc.csi.it/",
			"RicercaDocumentiINIService");
	
	private static int MAX_NUM_RETRY = 3;

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	public RicercaDocumentiResponse getDocumenti(RicercaDocumentiRequeste req) {
		
		String methodName = "getDocumenti";

		URL wsdlURL;
		int numRetry = 1;
		try {
			String ricercaDocumentiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.RICERCA_DOCUMENTI_SERVICE) + "?wsdl";
			wsdlURL = new URL(ricercaDocumentiServiceUrl);

			RicercaDocumentiResponse ricercaDocumentiResponse = new RicercaDocumentiResponse();
			
			while (numRetry <= MAX_NUM_RETRY)  {
				try {
					RicercaDocumentiINIService_Service cs = new RicercaDocumentiINIService_Service(wsdlURL,SERVICE_NAME_RICERCA_DOC);
					RicercaDocumentiINIService port = cs.getRicercaDocumentiINIServicePort();
					ricercaDocumentiResponse = port.ricercaDocumenti(req);
					if (isErroreRete(ricercaDocumentiResponse)) {
						if (numRetry >= MAX_NUM_RETRY) {
							logInfo(methodName, "Raggiunto numero massimo di tentativi in chiamata a: " + Constants.RICERCA_DOCUMENTI_SERVICE );
							break;
						}
						Thread.sleep(1000);
						numRetry++;
						continue;
					}
					break;
				} catch (WebApplicationException | WebServiceException e) {
					logInfo(methodName, "WebException in chiamata a: " + Constants.RICERCA_DOCUMENTI_SERVICE + " "+ e.getMessage() );
					if (numRetry >= MAX_NUM_RETRY) {
						logInfo(methodName, "Raggiunto numero massimo di tentativi in chiamata a: " + Constants.RICERCA_DOCUMENTI_SERVICE );
						break;
					}
					Thread.sleep(1000);
					numRetry++;
				}
			}

			return ricercaDocumentiResponse;


		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore non recuperabile chiamata servizio : " + Constants.RICERCA_DOCUMENTI_SERVICE);
		}
	}
	
	
	private boolean isErroreRete(RicercaDocumentiResponse ricercaDocumentiResponse) {
		if (ricercaDocumentiResponse == null) {
			return true;
		}
		if (ricercaDocumentiResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name()) ) {
			for (Errore errore : ricercaDocumentiResponse.getErrori()) {
				if (Constants.CODICE_RISPOSTA_ERRORE_RETE.equals(errore.getCodice())) {
					return true;					
				}
			}
		}
		return false;
	}


	protected RicercaDocumentiRequeste buildRequestForGetDocumento(String shibIdentitaCodiceFiscale, String ruoloINI,
			String citId, String xRequestId, String xCodiceServizio, String regime, long idPaziente, 
			String codiceDocumentoINI, String identificativoRepositoryINI)
			throws DatabaseException {


		RicercaDocumentiRequeste req = new RicercaDocumentiRequeste();
		RicercaDocumentiIN in = new RicercaDocumentiIN();

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
		in.getIdentificativoDocumento().add(codiceDocumentoINI);
		in.setStrutturaUtente(Constants.RICERCA_DOC_STRUT_UTENTE);
		in.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		in.setPresaInCarico(Constants.TRUE.toLowerCase());
		in.setStatoDocumento(Constants.RICERCA_DOC_STATO_DOC);
		in.setTipoAttivita(Constants.RICERCA_DOC_TIPO_ATTIVITA);
		in.setRuoloUtente(ruoloINI);

		Richiedente richiedente = new Richiedente();

		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);

		req.setRicercaDocumentiIN(in);
		req.setRichiedente(richiedente);

		return req;

	}
	
	public void verificaRicercaDocumentiResponse(RicercaDocumentiResponse ricercaDocumentiResponse) throws ResponseErrorException {
		List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();
		
		if (ricercaDocumentiResponse == null) {
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in Ricerca Documenti INI - servizio non disponibile ");

		}
				
		if (ricercaDocumentiResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
			for (Errore errore : ricercaDocumentiResponse.getErrori()) {
				//errore bloccante se codice errore diverso da
				// 2048 - sei RCD/RDA - devi proseguire nel recupero su cl
				// 2007 - consenso negato - lo stato consensi ammette eccezioni, per esempio se richiedente-refertante, per cui non devi bloccarti qua
				if (! (errore.getCodice().equals(Constants.CODICE_RISPOSTA_PAZIENTE_RDA_RCD)
						|| errore.getCodice().equals(Constants.CODICE_RISPOSTA_CONSENSO_NEGATO))) {
					ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
					errorservice.setChiave(errore.getCodice());
					errorservice.setValore(errore.getDescrizione());
					errorservice.setErroreId(errorservice.getErroreId());
					listError.add(errorservice);
				}
			}
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in Ricerca Documenti INI ");
			}
		}		
	}
	
}
