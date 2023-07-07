/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.EsitoGetEventi;
import it.csi.dma.apiopsan.dto.Evento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.utils.DecodificaErrore;
import it.csi.dma.apiopsan.integration.dao.utils.ErroreTaccuino;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.TaccuinoServiceExt;
import it.csi.dma.apiopsan.taccuinoservice.Errore;
import it.csi.dma.apiopsan.taccuinoservice.EventoResponse;
import it.csi.dma.apiopsan.taccuinoservice.GetEventiResponse;
import it.csi.dma.apiopsan.taccuinoservice.RisultatoCodice;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class Eventi extends LoggerUtil {


	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;

	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;

	@Autowired
	CodRLogErroreService codRLogErroreService;

	@Autowired
	CodLMessaggiService codLMessaggiService;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	TaccuinoServiceExt taccuinoServiceInt;


	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione, String citId,
			Long taccuinoId, Integer limit, Integer offset, String ordinamento, String dataDa, String dataA,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "execute";
		ErrorBuilder error = null;
		// genero uid
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		// inserisco tabella messaggi e messaggi xml
		long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
				uuidAsString, Constants.GET_EVENTI, citId);
		// inserisco tabella dei log
		long idlog = codRLogErroreService.traceLogInsert(Constants.GET_EVENTI, uuidAsString,
				Constants.CITTADINI_API_SERVICE, citId);
		try {
			// cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
				ruoloFse = ruoloF.getCodiceRuoloFse();
			// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateInfoScreening(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, securityContext, httpHeaders, httpRequest);
 
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore in validate");
			}
			
			GetEventiResponse resEvento = taccuinoServiceInt.getEventi(shibIdentitaCodiceFiscale, xRequestId, xCodiceVerticale, 
					xForwardedFor, xCodiceServizio, ruoloFse, regime, citId, taccuinoId, ordinamento, dataDa, dataA);

			
			List<Evento> listaEventi = new ArrayList<Evento>();
			
			
			
			if (resEvento != null && (resEvento.getEsito() == RisultatoCodice.SUCCESSO))			
			{
					
				EsitoGetEventi esitoGetEventi = new EsitoGetEventi();
				
				if (resEvento.getListaEventi() != null) {
					for (EventoResponse er: resEvento.getListaEventi().getEvento())
					{
						Evento e = new Evento();
						e.setId(er.getId());				
									
						e.setDataCreazione(er.getDataCreazione().toGregorianCalendar().getTime());
						if (er.getDataAggiornamento() != null)
						{
							e.setDataAggiornamento(er.getDataAggiornamento().toGregorianCalendar().getTime());
						}							
						
						e.setData(er.getEvento().getData().toGregorianCalendar().getTime());		
						e.setDescrizione(er.getEvento().getDescrizione());
						
						listaEventi.add(e);
					}
				}
								
				esitoGetEventi.setNumeroEventi(listaEventi.size());				
				listaEventi = Util.subList(listaEventi, offset, limit);			
				esitoGetEventi.setElencoEventi(listaEventi);
						
				// inserimento nel log esito positivo
				codRLogErroreService.traceLogInsert(Constants.GET_EVENTI, uuidAsString,
						Constants.CITTADINI_API_SERVICE, Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esitoGetEventi.toString(),
						Constants.ESITO_SUCCESSO);
				// popolare la tabella di audit solo se esito positivo
				
				String codiceSuccesso = Constants.CODICE_SUCCESSO_GET_EVENTI;
				if (dataDa != null || dataA != null)
					codiceSuccesso = Constants.CODICE_SUCCESSO_GET_EVENTI_DATA;
				
				codRLogErroreService.traceLogAuditInsert(Constants.GET_EVENTI, uuidAsString,
						esitoGetEventi.toString(), codiceSuccesso, shibIdentitaCodiceFiscale,
						collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime,
						citId,null,null,null);

				return Response.status(200).entity(esitoGetEventi).build();
			}
			else if (resEvento != null && (resEvento.getEsito() == RisultatoCodice.FALLIMENTO))

			{
				List<Errore> wsErrors  = resEvento.getErrori();
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
						ErrorBuilder.generateErrorBuilderError(status, listError),"Errore in elenco eventi presenti nel taccuino");
			}


		} catch (DatabaseException e) {
			logError(methodName, "Errore riguardante database:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		} catch (ResponseErrorException e) {
			logError(methodName, "Errore generico response:", e.getMessage());
			error = e.getResponseError();
		} catch (Exception e) {
			logError(methodName, "Errore generico:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}

		error = codRMessaggioErroreService.saveError(error, httpRequest, uuidAsString);
		// aggiornamento record dmacc_l_messaggi e dmacc_l_xml_messaggi
		Response esito = error.generateResponseError();
		// update nessaggi e xml se esito fallimento
		codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),
				Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.GET_EVENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);
		return esito;

	}
}
