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

import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class Referti extends LoggerUtil {
	
	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;
	
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
	DmaccIdxDao dmaccIdxDao;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String collocazione, String regime, PayloadSearchMieiReferti payloadSearchMieiReferti,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_MIEI_REFERTI,payloadSearchMieiReferti.getCitId());
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_MIEI_REFERTI, uuidAsString, Constants.OPERATORE_SANITARIO_SERVICE,payloadSearchMieiReferti.getCitId());
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
			// validate			
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateMieiReferti(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, collocazione, regime, payloadSearchMieiReferti, securityContext, httpHeaders, httpRequest);
			
			
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
		
		
			List<SintesiDocumento> listadocumenti = new ArrayList<SintesiDocumento>();
			
			listadocumenti = dmaccIdxDao.selectMieiReferti(shibIdentitaCodiceFiscale, payloadSearchMieiReferti.getCitId(),payloadSearchMieiReferti.getTipoMedico(),payloadSearchMieiReferti.getFiltroDocs().getDataInizio(),
																	payloadSearchMieiReferti.getFiltroDocs().getDataFine());
			
			listadocumenti = Util.subList(listadocumenti,payloadSearchMieiReferti.getOffset(),payloadSearchMieiReferti.getLimit());
				
			
			
			//inserimento nel log esito positivo
			codRLogErroreService.traceLogInsert(Constants.GET_MIEI_REFERTI, uuidAsString, Constants.OPERATORE_SANITARIO_SERVICE,Constants.ESITO_SUCCESSO);
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, listadocumenti.toString(),Constants.ESITO_SUCCESSO);
			//popolare la tabella di audit solo se esito positivo
			codRLogErroreService.traceLogAuditInsert(Constants.GET_MIEI_REFERTI, uuidAsString, listadocumenti.toString(), 
					Constants.CODICE_SUCCESSO_GETMIEIREFERTI, shibIdentitaCodiceFiscale, 
					collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, null, payloadSearchMieiReferti.getCitId(),null,null,null);
			
			 return Response.status(200).entity(listadocumenti).build();
		
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
		codRLogErroreService.traceLogInsert(Constants.GET_MIEI_REFERTI, uuidAsString, Constants.OPERATORE_SANITARIO_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}
}


