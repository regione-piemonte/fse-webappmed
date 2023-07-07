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

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.EsitoIsDocumentoMediabile;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.exception.UtenteConfiguratoreGenException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.StatoDocumento;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.ConfiguratoreService;
import it.csi.dma.apiopsan.integration.service.DocumentiServiceApisan;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class IsMediazioneDocumento extends LoggerUtil {
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
	private StatoDocumento statoDocumento;
	
	@Autowired
	private ConfiguratoreService configuratoreService;
	
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.IS_MEDIAZIONEDOCUMENTI,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.IS_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
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
			//verifico se il documento smediabile
			boolean isMedicoVR = statoDocumento.isMedicoRefertante(shibIdentitaCodiceFiscale, idDocumentoIlec, codiceComponenteLocale);

			EsitoIsDocumentoMediabile esitoMediazione = new EsitoIsDocumentoMediabile();
			Codice risposta = new Codice();
			if (isMedicoVR) {
				//esito positivo esito ok medico
				esitoMediazione.setMediabile(true);
				risposta.setCodice(Constants.RISPOSTA_MED);
				risposta.setDescrizione(Constants.DESCR_RISPOSTA_MED);
				esitoMediazione.setEsito(risposta);
			}
			else {
				boolean isSmediazione = dmaccIdxDao.isSmediazione(idDocumentoIlec, codiceComponenteLocale, citId);
				if (isSmediazione) {
					//esito positivo esito ok no vincoli
					esitoMediazione.setMediabile(true);
					risposta.setCodice(Constants.RISPOSTA_NOVINC);
					risposta.setDescrizione(Constants.DESCR_RISPOSTA_NOVINC);
					esitoMediazione.setEsito(risposta);
				}
				else {
					//chiama servizio configuratore
					boolean smediabile = false;
					//Codice azienda = new Codice();
					String aziendaSmediazione = dmaccIdxDao.aziendaSmediazione(idDocumentoIlec, codiceComponenteLocale, citId);
					String aziendaConfiguratore = configuratoreService.sendGetAzienda(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, ruolo, collocazione);
					if (aziendaSmediazione!=null && aziendaConfiguratore!=null) {
						if (aziendaSmediazione.equalsIgnoreCase(aziendaConfiguratore)) {
							//esisto positivo azienda uguale a quella cui si entra
							//per ora commento in attesa di delibera e spongo esito negativo
//							esitoMediazione.setMediabile(true);
//							risposta.setCodice(Constants.RISPOSTA_AZIENDA);
//							risposta.setDescrizione(Constants.DESCR_RISPOSTA_AZIENDA);
//							esitoMediazione.setEsito(risposta);
//							smediabile = true;
							//da eliminare dopo delibera
							esitoMediazione.setMediabile(false);
							risposta.setCodice(Constants.RISPOSTA_AZIENDA);
							risposta.setDescrizione(Constants.DESCR_RISPOSTA_NEGATIVA_AZIENDA);
							esitoMediazione.setEsito(risposta);
							smediabile = true;
						}
					}
					if (!smediabile) {
						//esco con errore
						//esisto negativo non smediabile
						esitoMediazione.setMediabile(false);
						risposta.setCodice(Constants.RISPOSTA_NEGATIVA);
						risposta.setDescrizione(Constants.DESCR_RISPOSTA_NEGATIVA);
						esitoMediazione.setEsito(risposta);
					}
				}
			}
					codRLogErroreService.traceLogInsert(Constants.IS_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
					codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esitoMediazione.toString(),Constants.ESITO_SUCCESSO);
					return Response.status(200).entity(esitoMediazione).build();

			
		} catch (DatabaseException e) {
			logError(methodName, "Errore riguardante database:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}  catch (ResponseErrorException e) {
			logError(methodName, "Errore generico response:", e.getMessage());
			error = e.getResponseError();
		} catch (UtenteConfiguratoreGenException e) {
			logError(methodName, "Errore generico:", e.getMessage());
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
				ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
				errorservice.setChiave(e.getCode());
				errorservice.setValore(e.getMessage());
				errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
				listerrorservice.add(errorservice);
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice);
		}
		catch (Exception e) {
			logError(methodName, "Errore generico:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}
		error = codRMessaggioErroreService.saveError(error, httpRequest,uuidAsString);
		//aggiornamento record dmacc_l_messaggi e dmacc_l_xml_messaggi
		Response esito = error.generateResponseError();
		//update nessaggi e xml se esito fallimento
		codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.IS_MEDIAZIONEDOCUMENTI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

}


