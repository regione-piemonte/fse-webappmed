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

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  it.csi.dma.apiopsan.documentiservice.dma.Errore;
import it.csi.dma.apiopsan.documentiservice.dmacc.SmediazioneDocumentiResponse;
import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.EsitoIsDocumentoMediabile;
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
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.DocumentiServiceApisan;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.exception.UtenteConfiguratoreGenException;

@Service
public class MediazioneDocumento extends LoggerUtil {
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
	DocumentiServiceApisan documentiService;
	
	@Autowired
	DmaccIdxDao dmaccIdxDao;
	
	@Autowired
	IsMediazioneDocumento isSmediazioneDocumento;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.SET_MEDIAZIONEDOCUMENTI,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.SET_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateMediazioneDocumenti(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
					idDocumentoIlec, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			//chiamo il servizio is smediazione
			Response res = isSmediazioneDocumento.execute(shibIdentitaCodiceFiscale, 
					xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, 
					regime, collocazione, citId, idDocumentoIlec, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);
			if (res.getStatus()!=200) {
				it.csi.dma.apiopsan.dto.Errore erroreMediazione = (it.csi.dma.apiopsan.dto.Errore) res.getEntity();
				List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
				ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
				errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
				errorservice.setChiave(erroreMediazione.getDetail().get(0).getChiave());
				errorservice.setValore(erroreMediazione.getDetail().get(0).getValore());
				listerrorservice.add(errorservice);
			throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in configuratore");
			}
			EsitoIsDocumentoMediabile esitoMediazione = (EsitoIsDocumentoMediabile) res.getEntity();
			if (!esitoMediazione.isMediabile()) {
				//chiudo i log del getmediazione prima di uscire
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, res.getEntity().toString(),Constants.ESITO_FALLIMENTO);
				codRLogErroreService.traceLogInsert(Constants.SET_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
				return Response.status(200).entity(esitoMediazione).build();
			}
			//chiamo il servizio documentiService smediazioneDocumenti
			//compongo la chiamata
			SmediazioneDocumentiResponse response = documentiService.smediazioneDocumenti(
					xCodiceServizio, shibIdentitaCodiceFiscale, xRequestId, regime, ruolo, ruoloFse, xRequestId, 
					collocazione, collocazione, citId, idDocumentoIlec, codiceComponenteLocale);	
			
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
			if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				//errore
				for (Errore errore : response.getErrori()) {
					ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
					errorservice.setChiave(errore.getCodice());
					errorservice.setValore(errore.getDescrizione());
					errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
					listerrorservice.add(errorservice);
				}
				
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in service mediazioneDocumenti");
			}
			else {
					//inserimento nel log esito positivo
					//inserimento nel log esito positivo
					//bloccato non ci troviamo con analisi
					codRLogErroreService.traceLogInsert(Constants.SET_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
					codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, response.getEsito().toString(),Constants.ESITO_SUCCESSO);
					String[] array = new String[2];
					array[0] = idDocumentoIlec.toString();
					array[1] = citId;
					codRLogErroreService.traceLogAuditInsert(Constants.SET_MEDIAZIONEDOCUMENTI, uuidAsString, response.toString(), 
							Constants.CODICE_SUCCESSO_MEDIAZIONEDOCUMENTI, shibIdentitaCodiceFiscale, 
							collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,idDocumentoIlec, codiceComponenteLocale);
					 return Response.status(200).build();
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
		codRLogErroreService.traceLogInsert(Constants.SET_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

}


