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

import it.csi.dma.apiopsan.dto.Taccuino;
import it.csi.dma.apiopsan.dto.TaccuinoEntita;
import it.csi.dma.apiopsan.dto.TaccuinoGruppo;
import it.csi.dma.apiopsan.dto.TaccuinoNotaGenerale;
import it.csi.dma.apiopsan.dto.TaccuinoPreferenza;
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
import it.csi.dma.apiopsan.taccuinoservice.GetTaccuinoResponse;
import it.csi.dma.apiopsan.taccuinoservice.Preferenza;
import it.csi.dma.apiopsan.taccuinoservice.RisultatoCodice;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class DettaglioTaccuino extends LoggerUtil {


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
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime,String collocazione, String citId,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "execute";
		ErrorBuilder error = null;
		// genero uid
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		// inserisco tabella messaggi e messaggi xml
		long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
				uuidAsString, Constants.GET_TACCUINO, citId);
		// inserisco tabella dei log
		long idlog = codRLogErroreService.traceLogInsert(Constants.GET_TACCUINO, uuidAsString,
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
			
			
			GetTaccuinoResponse resTaccuino = taccuinoServiceInt.getTaccuino(shibIdentitaCodiceFiscale, xRequestId, xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, 
					ruoloFse,regime);
			
			if (resTaccuino != null && (resTaccuino.getEsito() == RisultatoCodice.SUCCESSO))			
			{
			
				Taccuino taccuino =  new Taccuino();
				
				it.csi.dma.apiopsan.taccuinoservice.Taccuino taccuinoEx =  resTaccuino.getTaccuino();
				
				if (taccuinoEx != null) {
					it.csi.dma.apiopsan.taccuinoservice.NotaGenerale notagenEx = taccuinoEx.getNotaGenerale();
					
					
					TaccuinoNotaGenerale notaGenerale = new TaccuinoNotaGenerale();
					if (notagenEx.getDataAggiornamento() != null) {
						notaGenerale.setDataAggiornamento(notagenEx.getDataAggiornamento().toGregorianCalendar().getTime());
					}
					if (notagenEx.getDataCreazione() != null) {
						notaGenerale.setDataCreazione(notagenEx.getDataCreazione().toGregorianCalendar().getTime());
					}
					notaGenerale.setId(null); //TODO nmicheli cosa metto?
					notaGenerale.setStatoSalute(notagenEx.getStatoSalute());
					notaGenerale.setStileVita(notagenEx.getStileVita());
				
					taccuino.setNotaGenerale(notaGenerale);
					taccuino.setOscurato(taccuinoEx.getOscurato().equals(Constants.S));
					taccuino.setId(taccuinoEx.getId());
					
					List<TaccuinoPreferenza> listaPreferenze = new ArrayList<>();
					
					List<it.csi.dma.apiopsan.taccuinoservice.Preferenza> listaPrefEx =  taccuinoEx.getListaPreferenze().getPreferenza();
					for (Preferenza p: listaPrefEx)
					{
						TaccuinoPreferenza tacPref =  new TaccuinoPreferenza();
						
						TaccuinoEntita taccuinoEntita = new TaccuinoEntita();
						taccuinoEntita.setCodice(p.getEntita().getCodice());
						taccuinoEntita.setDescrizione(p.getEntita().getDescrizione());
		
						tacPref.setEntita(taccuinoEntita);
						
						if (p.getGruppo() != null)
						{					
							TaccuinoGruppo tacGrup = new TaccuinoGruppo();
							tacGrup.setCodice(p.getGruppo().getCodice());
							tacGrup.setDescrizione(p.getGruppo().getDescrizione());
							tacGrup.setDescrizioneEstesa(p.getGruppo().getDescrizioneEstesa());						
							tacPref.setGruppo(tacGrup);
						}
			
						tacPref.setId(p.getId());
						
						listaPreferenze.add(tacPref);
						
					}
					
					taccuino.setPreferenze(listaPreferenze);				
				}
							

				// inserimento nel log esito positivo
				codRLogErroreService.traceLogInsert(Constants.GET_TACCUINO, uuidAsString,
						Constants.CITTADINI_API_SERVICE, Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, taccuino.toString(),
						Constants.ESITO_SUCCESSO);
				// popolare la tabella di audit solo se esito positivo
				String[] array = new String[1];
				array[0] = shibIdentitaCodiceFiscale;
				codRLogErroreService.traceLogAuditInsert(Constants.GET_TACCUINO, uuidAsString,
						taccuino.toString(), Constants.CODICE_SUCCESSO_GET_TACCUINO, shibIdentitaCodiceFiscale,
						collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime,
						citId,array,null,null);

				return Response.status(200).entity(taccuino).build();
			}
			else if (resTaccuino != null && (resTaccuino.getEsito() == RisultatoCodice.FALLIMENTO))

			{
				List<Errore> wsErrors  = resTaccuino.getErrori();
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
						ErrorBuilder.generateErrorBuilderError(status, listError),"Errore chiamata getTaccuino");
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
		codRLogErroreService.traceLogInsert(Constants.GET_TACCUINO, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);
		return esito;

	}
}
