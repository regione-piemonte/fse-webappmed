/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.csi.dma.apiopsan.documentiservice.dmacc.SmediazioneDocumentiResponse;
import it.csi.dma.apiopsan.dto.Dolore;
import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.EsitoGetDolori;
import it.csi.dma.apiopsan.dto.GiorniApertura;
import it.csi.dma.apiopsan.dto.IndirizzoStudio;
import it.csi.dma.apiopsan.dto.InfoMmgPaziente;
import it.csi.dma.apiopsan.dto.MedicoMmg;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.dto.Studi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;
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
import it.csi.dma.apiopsan.integration.service.ScaricoRefertoServiceApisan;
import it.csi.dma.apiopsan.integration.service.TaccuinoServiceExt;
import it.csi.dma.apiopsan.taccuinoservice.DoloreResponse;
import it.csi.dma.apiopsan.taccuinoservice.Errore;
import it.csi.dma.apiopsan.taccuinoservice.GetDoloriResponse;
import it.csi.dma.apiopsan.exception.ResponseErrorException;

@Service
public class ListaDolori extends LoggerUtil {

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
	ScaricoRefertoServiceApisan scaricoRefertoService;
	
	@Autowired
	DmaccIdxDao dmaccIdxDao;

	@Autowired
	TaccuinoServiceExt taccuinoService;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo, String regime,
			 String collocazione,String citId, Long taccuinoId,
			 Integer limit, Integer offset, String ordinamento, String da, String a,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

	
		String methodName = "execute";
		ErrorBuilder error = null;
		//genero uid 
		 UUID uuid = UUID.randomUUID();
	     String uuidAsString = uuid.toString();
	     //inserisco tabella messaggi e messaggi xml
	    long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
	    		xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, 
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_LISTA_DOLORI, null);
	   
	    
	    //inserisco tabella dei log
//	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_SCANSIONAQRCODE, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateListaDolori(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse,regime, collocazione, citId, taccuinoId, 
					limit, offset, ordinamento, da, a,
					securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			
			//chiamo il servizio documentiService smediazioneDocumenti
			//compongo la chiamata
			
			GetDoloriResponse response = taccuinoService.getDolori(shibIdentitaCodiceFiscale, xRequestId,
					xCodiceVerticale, xForwardedFor, xCodiceServizio, citId, ruoloFse, regime, taccuinoId, a, da,
					ordinamento);

			if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				// dai errore e accoda ad errori
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
						ErrorBuilder.generateErrorBuilderError(status, listError),"Errore in elenco farmaci taccuino");
			} else {
				EsitoGetDolori esitoGetDolori = new EsitoGetDolori();
				List<Dolore> listaDolori = new ArrayList<>();
				if (response.getListaDolori() != null) {
					for (DoloreResponse s : response.getListaDolori().getDolore()) {
						Dolore dolore = new Dolore();
						dolore.setId(s.getId());
						dolore.setDescrizione(s.getDolore().getDescrizione());
						dolore.setAreaInteressata(
								s.getDolore().getAreaInteressata() != null ? s.getDolore().getAreaInteressata() : null);
						dolore.setDataInizio(s.getDolore().getDataInizio() != null
								? s.getDolore().getDataInizio().toGregorianCalendar().getTime()
								: null);
						dolore.setDataFine(s.getDolore().getDataFine() != null
								? s.getDolore().getDataFine().toGregorianCalendar().getTime()
								: null);
						dolore.setDataCreazione(s.getDataCreazione().toGregorianCalendar().getTime());
						dolore.setDataAggiornamento(
								s.getDataAggiornamento() != null ? s.getDataAggiornamento().toGregorianCalendar().getTime()
										: null);
						listaDolori.add(dolore);

					}
				}
				
				esitoGetDolori.setNumeroDolori(listaDolori.size());
				listaDolori = Util.subList(listaDolori, offset, limit);
				esitoGetDolori.setElencoDolori(listaDolori);
				
				String codiceSuccesso = null;
				if ((a != null && !a.isEmpty()) || (da != null && !da.isEmpty())) {
					codiceSuccesso = Constants.CODICE_SUCCESSO_LISTA_DOLORI_INT;
				} else {
					codiceSuccesso = Constants.CODICE_SUCCESSO_LISTA_DOLORI;
				}
				codRLogErroreService.traceLogInsert(Constants.GET_LISTA_DOLORI, uuidAsString,
						Constants.CITTADINI_API_SERVICE, Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esitoGetDolori.toString(),
						Constants.ESITO_SUCCESSO);
				codRLogErroreService.traceLogAuditInsert(Constants.GET_LISTA_DOLORI, uuidAsString,
						esitoGetDolori.toString(), codiceSuccesso, shibIdentitaCodiceFiscale, collocazione,
						xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,null,null,null);
				return Response.status(200).entity(esitoGetDolori).build();
			}
			
		} catch (DatabaseException e) {
			logError(methodName, "Errore riguardante database:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}  catch (ResponseErrorException e) {
			logError(methodName, "Errore generico response:", e.getMessage());
			error = e.getResponseError();
		} catch (Exception e) {
			logError(methodName, "Errore generico:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}
		
		error = codRMessaggioErroreService.saveError(error, httpRequest,uuidAsString);
		//aggiornamento record dmacc_l_messaggi e dmacc_l_xml_messaggi
		Response esito = error.generateResponseError();
		//update nessaggi e xml se esito fallimento
		codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.GET_LISTA_DOLORI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

}


