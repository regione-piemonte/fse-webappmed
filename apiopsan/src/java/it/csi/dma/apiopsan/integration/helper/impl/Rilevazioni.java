/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.CodiceExt;
import it.csi.dma.apiopsan.dto.EsitoGetRilevazioni;
import it.csi.dma.apiopsan.dto.ModalitaRilevazione;
import it.csi.dma.apiopsan.dto.PayloadGetRilevazioni;
import it.csi.dma.apiopsan.dto.Rilevazione;
import it.csi.dma.apiopsan.dto.Sintomo;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.dao.utils.DecodificaErrore;
import it.csi.dma.apiopsan.integration.dao.utils.ErroreTaccuino;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.integration.service.TaccuinoServiceExt;
import it.csi.dma.apiopsan.taccuinoservice.Errore;
import it.csi.dma.apiopsan.taccuinoservice.GetRilevazioniResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetSintomiResponse;
import it.csi.dma.apiopsan.taccuinoservice.RilevazioneResponse;
import it.csi.dma.apiopsan.taccuinoservice.SintomoResponse;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;

@Service
public class Rilevazioni extends LoggerUtil {
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
  //  private static final QName SERVICE_NAME_DOCUMENTI_INI = new QName("http://dmacc.csi.it/", "RicercaDocumentiINIService");

	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;
	
	@Autowired
	GetRegimiDao getRegimiDao;

	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;
	
	@Autowired
	CodRLogErroreService codRLogErroreService;
	
	@Autowired
	CodLMessaggiService codLMessaggiService;
	
	@Autowired
	CatalogoServiziDao catalogoServiziDao;
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
	
	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	StatoConsensiService statoConsensiSerivce;
	
	@Autowired
	TaccuinoServiceExt taccuinoService;
	
	@Autowired
	DmaccIdxDao dmaccIdxDao;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, PayloadGetRilevazioni payloadGetRilevazioni, Integer limit, Integer offset,
			String ordinamento, String dataDa, String dataA, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		String methodName = "execute";
		ErrorBuilder error = null;
		//genero uid 
		 UUID uuid = UUID.randomUUID();
	     String uuidAsString = uuid.toString();
	     //inserisco tabella messaggi e messaggi xml
	    long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
	    		xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, 
	    		httpHeaders, httpRequest, uuidAsString, Constants.LISTA_RILEVAZIONI,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.LISTA_RILEVAZIONI, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
	  
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate stessa del contrattiStrutture
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateInfoScreening(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			
			GetRilevazioniResponse response = taccuinoService.getDettaglioRilevazioni(shibIdentitaCodiceFiscale, xRequestId, xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, ruoloFse, regime, taccuinoId,dataA,dataDa,ordinamento,payloadGetRilevazioni.getListaCodiceGruppo(),payloadGetRilevazioni.getListaCodiceDescrittore());
			
			if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				List<Errore> wsErrors  = response.getErrori();
				StatusEnum status = null;
	        	for(Errore wsError : wsErrors) {
	        		ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();     		
	        		
	        		ErroreTaccuino e =  DecodificaErrore.getData(wsError.getCodice());	        			
	        		if (e == null)  {	        				    
	        			if (status == null) {
		        			status =  StatusEnum.BAD_REQUEST;			
		        		}
		        		erroreDettaglio.setChiave(wsError.getCodice());
		        		erroreDettaglio.setValore("Codice errore " + wsError.getCodice() + " non in anagrafica locale - " + wsError.getDescrizione());
		        		erroreDettaglio.setErroreId(1);	        		
		        		listError.add(erroreDettaglio);
	        			
	        		} else
	        		{
	               		if (status == null) {
		        			status =  StatusEnum.findByStatus(e.getStatus());   			
		        		}
		        		erroreDettaglio.setChiave(e.getCodice()+"");
		        		erroreDettaglio.setValore(e.getTitle() + " - " + e.getDescrizione());
		        		erroreDettaglio.setErroreId(1);	        		
		        		listError.add(erroreDettaglio);
	        		}
	 
	        	}
	        	
	        	error = ErrorBuilder.generateErrorBuilderError(status, listError);
	        	
	        	throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(status, listError),"Errore in elenco rilevazioni presenti nel taccuino");
			}else {
				EsitoGetRilevazioni esitoRilevazioni = new EsitoGetRilevazioni();
				List<Rilevazione> listaRilevazioni = new ArrayList<>();
				if (response.getListaRilevazioni() != null)
				{
					for(RilevazioneResponse s : response.getListaRilevazioni().getRilevazione()) {
						Rilevazione rilevazione = new Rilevazione();
						rilevazione.setId(s.getId());
						rilevazione.setValoreNumerico(s.getRilevazione().getValoreNumerico() != null ? s.getRilevazione().getValoreNumerico().intValue() : null  );
						rilevazione.setValoreTestuale(s.getRilevazione().getValoreTestuale());
						rilevazione.setData(s.getRilevazione().getData() != null ? s.getRilevazione().getData().toGregorianCalendar().getTime() : null);
						rilevazione.setDataCreazione(s.getDataCreazione() != null ? s.getDataCreazione().toGregorianCalendar().getTime() : null);
						rilevazione.setDataAggiornamento(s.getDataAggiornamento() != null ? s.getDataAggiornamento().toGregorianCalendar().getTime() : null);
						rilevazione.setUnitaMisura(s.getRilevazione().getUnitaMisura() != null ? new Codice(s.getRilevazione().getUnitaMisura().getCodice(), s.getRilevazione().getUnitaMisura().getDescrizione()) : null );
						if(s.getRilevazione().getGruppo() != null) {
							CodiceExt gruppo= new CodiceExt();
							gruppo.setCodice(s.getRilevazione().getGruppo().getCodice());
							gruppo.setDescrizione(s.getRilevazione().getGruppo().getDescrizione());
							gruppo.setDescrizioneEstesa(s.getRilevazione().getGruppo().getDescrizioneEstesa());
							rilevazione.setGruppo(gruppo);
						  }
						if(s.getRilevazione().getDescrittore() != null) {
							CodiceExt descrittore= new CodiceExt();
							descrittore.setCodice(s.getRilevazione().getDescrittore().getCodice());
							descrittore.setDescrizione(s.getRilevazione().getDescrittore().getDescrizione());
							descrittore.setDescrizioneEstesa(s.getRilevazione().getDescrittore().getDescrizioneEstesa());
							rilevazione.setDescrittore(descrittore);
						}
						
						if(s.getRilevazione().getModalitaRilevazione() != null) {
							ModalitaRilevazione modalita= new ModalitaRilevazione();
							modalita.setCodice(s.getRilevazione().getModalitaRilevazione().getCodice());
							modalita.setDescrizioneRegionale(s.getRilevazione().getModalitaRilevazione().getDescrizioneRegionale());
							modalita.setDescrizioneNazionale(s.getRilevazione().getModalitaRilevazione().getDescrizioneNazionale());
							rilevazione.setModalitaRilevazione(modalita);
						}
						
						listaRilevazioni.add(rilevazione);
							
					}
				}
		
				esitoRilevazioni.setNumeroRilevazioni(listaRilevazioni.size());
				
				listaRilevazioni = Util.subList(listaRilevazioni, offset, limit);
				esitoRilevazioni.setElencoRilevazioni(listaRilevazioni);
				
				String codiceSuccesso= null;
				if(dataA != null || dataDa !=null) {
					codiceSuccesso=Constants.CODICE_SUCCESSO_LISTA_RILEVAZIONI_INT;
				}else {
					codiceSuccesso=Constants.CODICE_SUCCESSO_LISTA_RILEVAZIONI;
				}
				codRLogErroreService.traceLogInsert(Constants.LISTA_RILEVAZIONI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esitoRilevazioni.toString(),Constants.ESITO_SUCCESSO);
				codRLogErroreService.traceLogAuditInsert(Constants.LISTA_RILEVAZIONI, uuidAsString,esitoRilevazioni.toString(), 
						codiceSuccesso, shibIdentitaCodiceFiscale, 
						collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,null,null,null);
				return Response.status(200).entity(esitoRilevazioni).build();
			}
		
			
		} catch (DatabaseException e) {
			logError(methodName, "Errore riguardante database:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}  catch (ResponseErrorException e) {
			logError(methodName, "Errore generico response:", e.getMessage());
			error = e.getResponseError();
		} catch (Exception e) {
			e.printStackTrace();
			logError(methodName, "Errore generico:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}
		
		error = codRMessaggioErroreService.saveError(error, httpRequest,uuidAsString);
		//aggiornamento record dmacc_l_messaggi e dmacc_l_xml_messaggi
		Response esito = error.generateResponseError();
		//update nessaggi e xml se esito fallimento
		codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.LISTA_RILEVAZIONI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}
	


}


