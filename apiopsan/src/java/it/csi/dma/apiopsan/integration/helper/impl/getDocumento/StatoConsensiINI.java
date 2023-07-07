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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.consensoextservice.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.consensoextservice.ConsensoINIExtService;
import it.csi.dma.apiopsan.consensoextservice.ConsensoINIExtService_Service;
import it.csi.dma.apiopsan.consensoextservice.Errore;
import it.csi.dma.apiopsan.consensoextservice.Paziente;
import it.csi.dma.apiopsan.consensoextservice.RegimeDMA;
import it.csi.dma.apiopsan.consensoextservice.RichiedenteExt;
import it.csi.dma.apiopsan.consensoextservice.RisultatoCodice;
import it.csi.dma.apiopsan.consensoextservice.RuoloDMA;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiExtRequeste;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiIN;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class StatoConsensiINI extends LoggerUtil {
	
	private static final QName SERVICE_NAME_CONSENSO = new QName("http://dmacc.csi.it/", "ConsensoINIExtService");
	
	private static int MAX_NUM_RETRY = 3;
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
	
	@Autowired
	@Qualifier("ValidateGenericMeritWhitMedicoImpl")
	private ValidateGenericMeritWhitMedicoImpl validateGeneric;
												  
	public StatoConsensiResponse getStatoConsensi(StatoConsensiExtRequeste req) {
		
		String methodName = "getStatoConsensi";
				
		URL wsdlURL;
		int numRetry = 1;
		try {
			String consensiServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.STATO_CONSENSI_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(consensiServiceUrl);
			
			StatoConsensiResponse statoConsensiResponse = new StatoConsensiResponse();
			

			while (numRetry <= MAX_NUM_RETRY)  {
				try {
					ConsensoINIExtService_Service cs = new ConsensoINIExtService_Service(wsdlURL, SERVICE_NAME_CONSENSO);
					ConsensoINIExtService port = cs.getConsensoINIExtServicePort();		
					statoConsensiResponse = port.statoConsensi(req);
					break;				
				} catch (WebApplicationException | WebServiceException e){
					logInfo(methodName, "WebException in chiamata a: " + Constants.STATO_CONSENSI_SERVICE_URL + " "+ e.getMessage() );
					if (numRetry >= MAX_NUM_RETRY) {
						logInfo(methodName, "Raggiunto numero massimo di tentativi in chiamata a: " + Constants.STATO_CONSENSI_SERVICE_URL );
						break;
					}
					Thread.sleep(1000);
					numRetry++;
				}
			}	
			return statoConsensiResponse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore non recuperabile chiamata servizio: " + Constants.CONSENSI_EXT_SERVICE);
		}
	}
	
	public void verificaStatoConsensiResponse(StatoConsensiResponse statoConsensiResponse, boolean medicoRefertante) throws ResponseErrorException,DatabaseException {
		List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();
		if (statoConsensiResponse == null) {
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in verifica stato consensi - servizio non disponibile ");

		}
				
		if (statoConsensiResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
			//dai errore e accoda ad errori
			for (Errore errore : statoConsensiResponse.getErrori()) {
				ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
				errorservice.setChiave(errore.getCodice());
				errorservice.setValore(errore.getDescrizione());
				errorservice.setErroreId(errorservice.getErroreId());
				listError.add(errorservice);
			}
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in verifica stato consensi");
		}
		
		if (!isConsensoConsultazione(statoConsensiResponse)
				&& !medicoRefertante) {
			listError.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"verifica stato consensi: consensi non forniti");
		}
	}	

	
	public boolean isFuoriRegione(StatoConsensiResponse statoConsensiResponse) {
		if (!statoConsensiResponse.getStatoConsensiOUT().getIdentificativoInformativaCorrente().startsWith(Constants.IDENTIFICATIVO_ORGANIZZAZIONE)){
			//il paziente e' fuori regione
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isConsensoConsultazione(StatoConsensiResponse statoConsensiResponse) {
		if (Constants.TRUE.equalsIgnoreCase(statoConsensiResponse.getStatoConsensiOUT().getConsensoConsultazione())) {
			return true;
		} else {
			return false;
		}
	}
	
	public StatoConsensiExtRequeste buildStatoConsensiRequest(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, String citId, String ruoloFse, String regime) throws DatabaseException {
		
		StatoConsensiExtRequeste statoConsensiReq = new StatoConsensiExtRequeste();
		
		Paziente paz = new Paziente();
		paz.setCodiceFiscale(citId);
		
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		ap.setCodice(xCodiceServizio);
		
		RichiedenteExt rich = new RichiedenteExt();	
		rich.setApplicazione(ap);
		rich.setCodiceFiscale(shibIdentitaCodiceFiscale);
		rich.setNumeroTransazione(xRequestId);
		
		RegimeDMA regimedma = new RegimeDMA();
		regimedma.setCodice(regime);
		
		RuoloDMA ruoldma = new RuoloDMA();
		ruoldma.setCodice(ruoloFse);
		
		rich.setRegime(regimedma);
		rich.setRuolo(ruoldma);
		rich.setTokenOperazione(xRequestId);
		
		
		StatoConsensiIN statoIn = new StatoConsensiIN();
		
		statoIn.setContestoOperativo(Constants.STATO_CONSENSI_CONTESTO_OPERATIVO);
		statoIn.setIdentificativoAssistitoConsenso(citId);
		statoIn.setIdentificativoAssistitoGenitoreTutore(citId); //TODO chiedere e serve
		statoIn.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		statoIn.setIdentificativoOrganizzazione(Constants.IDENTIFICATIVO_ORGANIZZAZIONE);
		statoIn.setPresaInCarico(Constants.TRUE.toLowerCase());
		statoIn.setRuoloUtente(ruoloFse);
		statoIn.setStrutturaUtente(Constants.STRUTTURA_UTENTE);
		statoIn.setTipoAttivita(Constants.TIPO_ATTIVITA);
		
		statoConsensiReq.setPaziente(paz);
		statoConsensiReq.setRichiedente(rich);	
		statoConsensiReq.setStatoConsensiIN(statoIn);
		return statoConsensiReq;		
	}
	

}
