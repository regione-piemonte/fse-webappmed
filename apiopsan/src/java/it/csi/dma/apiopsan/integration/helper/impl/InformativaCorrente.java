/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;
					
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.consensoextservice.Errore;
import it.csi.dma.apiopsan.consensoextservice.RecuperoInformativaResponse;
import it.csi.dma.apiopsan.consensoextservice.RisultatoCodice;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.Ruolo;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class InformativaCorrente extends LoggerUtil {
	
																									  
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
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	StatoConsensiService statoConsensiSerivce;
	
	
	
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;

	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, 
			String regime, String collocazione,
			String citId, String identificativoStruttura, String identificativoOrganizzazione, String idInformativa,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_INFORMATIVA,null);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_INFORMATIVA, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
			//serve per il recpero informativa
			Ruolo ruoloxini = dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(ruoloFse);
			String ruoloIni = null;
			if (ruoloxini != null)
			ruoloIni = ruoloxini.getCodiceRuoloIni();
			
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateInformativa(shibIdentitaCodiceFiscale,  xRequestId,  xForwardedFor,
							  xCodiceServizio,  xCodiceVerticale,  ruolo,ruoloFse, regime, collocazione,
							  citId,identificativoStruttura,identificativoOrganizzazione,idInformativa,
							  securityContext,
							  httpHeaders,   httpRequest);
																			  
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			//chiamo il servizio StatoConsensiSerivce getInformativa
			//compongo la chiamata
			RecuperoInformativaResponse response = statoConsensiSerivce.getInformativa(xCodiceServizio,shibIdentitaCodiceFiscale,xRequestId,regime, ruoloIni,  identificativoStruttura, identificativoOrganizzazione,idInformativa );		
			
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
			if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				//dai errore e accoda ad errori
				for (Errore errore : response.getErrori()) {
					ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
					errorservice.setChiave(errore.getCodice());
					errorservice.setValore(errore.getDescrizione());
					errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
					listerrorservice.add(errorservice);
				}
				
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in stato consensi informativa");
			}
			else {
				//dai esito positivo

				if (response.getInformativaOUT() != null) {
					byte[] byteArray = response.getInformativaOUT().getInformativa();
				    System.out.println("LUNGHEZZA="+byteArray.length);
					String fileName = null;
					File reportOut = null;
					try {
						fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
						reportOut = File.createTempFile(fileName, ".pdf");
						FileUtils.writeByteArrayToFile(reportOut, byteArray);
						
						// inserimento nel log esito positivo
						codRLogErroreService.traceLogInsert(Constants.GET_INFORMATIVA, uuidAsString, Constants.CITTADINI_API_SERVICE,
								Constants.ESITO_SUCCESSO);
						codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, response.getInformativaOUT().getIdentificativoInformativa(), Constants.ESITO_SUCCESSO);
						String[] array = new String[1];
						array[0] = citId;
						codRLogErroreService.traceLogAuditInsert(Constants.GET_INFORMATIVA, uuidAsString, response.getInformativaOUT().getIdentificativoInformativa(), 
								Constants.CODICE_SUCCESSO_INFORMATIVA, shibIdentitaCodiceFiscale, 
								collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,null,null); // ?? controllare firma

						return Response.status(200).entity(reportOut).build();

						
					}
					catch (FileNotFoundException e) 
				        {logError(methodName, "File Not Found.:", e.getMessage());
						 error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);} 
					catch (IOException e) 
						{ 
						logError(methodName, "Error Reading The File.:", e.getMessage());
						error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
						}
				}
				 
		
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
		codRLogErroreService.traceLogInsert(Constants.GET_INFORMATIVA, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

}


