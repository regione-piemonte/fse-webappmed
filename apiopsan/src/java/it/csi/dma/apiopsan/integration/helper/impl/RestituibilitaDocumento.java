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

import it.csi.dma.apiopsan.consensoextservice.Errore;
import it.csi.dma.apiopsan.consensoextservice.RisultatoCodice;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.EsitoIsDocumentoRestituibile;
import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.PazienteDao;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.StatoDocumento;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.RestituibileEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class RestituibilitaDocumento extends LoggerUtil {
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;

	@Autowired
	private ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;
	
	@Autowired
	private DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	@Autowired
	private CodRMessaggioErroreService codRMessaggioErroreService;
	
	@Autowired
	private CodRLogErroreService codRLogErroreService;
	
	@Autowired
	private CodLMessaggiService codLMessaggiService;	
	
	@Autowired 
	private StatoDocumento statoDocumento;
	
	@Autowired
	private PazienteDao pazienteDao;
	
	@Autowired
	private StatoConsensiService statoConsensiService;
	
	@Autowired
	private DmaccIdxDao dmaccIdxDao;
	
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		String methodName = "execute";
		ErrorBuilder error = null;
		//genero uid 
		 UUID uuid = UUID.randomUUID();
	     String uuidAsString = uuid.toString();
	     //inserisco tabella messaggi e messaggi xml
	    long idXml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
	    		xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, 
	    		httpHeaders, httpRequest, uuidAsString, Constants.IS_DOC_RESTITUIBILE,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.IS_DOC_RESTITUIBILE, uuidAsString, Constants.CITTADINI_API_SERVICE,null);

	    try {	    	
			//recupero il ruolo FSE, da passare nei controlli formali e servira' in seguito
			RuoloPua ruoloPua = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloPua != null)
				ruoloFse = ruoloPua.getCodiceRuoloFse();
			
			//TODO: validazioni
			List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();
			listError = validateMedicGeneric.validateRestituibilitaDocumento(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, citId,
					idDocumentoIlec, codiceComponenteLocale, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
					
	    	EsitoIsDocumentoRestituibile esito = new EsitoIsDocumentoRestituibile();
	    	 	
	    	
			//recupero idPaziente // se non trovato: 0
			long idPaziente = pazienteDao.selectIdPazientePerCodiceFiscale(citId);
			// verificare presenza del documento per idDocumentoIlec, codiceCl e citId
			DmaccidxTDocumento tDocumento = statoDocumento.verificaEsistenzaDocumento(listError, idDocumentoIlec, codiceComponenteLocale, idPaziente);

    		//richiedente e' anche refertante, 
    		if (statoDocumento.isMedicoRefertante(shibIdentitaCodiceFiscale, idDocumentoIlec, codiceComponenteLocale)) {
    			return Response.status(200).entity(buildEsito(RestituibileEnum.MEDICO_REFERTANTE)).build();
    		}

    		StatoConsensiResponse statoConsensiResponse = statoConsensiService.getStatoConsensi(shibIdentitaCodiceFiscale,xRequestId,xCodiceServizio,citId,ruoloFse,regime);			
    		verificaStatoConsensiResponse(listError, statoConsensiResponse);
			
	    	//chiamata da SANMEDCOD
	    	if (Constants.CODICE_CONTATTO_DIGITALE.equals(xCodiceServizio))  {
				if (!statoConsensiService.isConsensoConsultazione(statoConsensiResponse)) {
					esito = buildEsito(RestituibileEnum.CONSENSO_NEGATO);
				} else if (!statoDocumento.pagatoTicket(listError, tDocumento)) {
					esito = buildEsito(RestituibileEnum.TICKET_NON_PAGATO);
				} else {
					esito = buildEsito(RestituibileEnum.TICKET_PAGATO);
				}
	    	} else {
	    	//chiamata da DMAWA
	    		if (!statoConsensiService.isConsensoConsultazione(statoConsensiResponse)) {
	    			//consenso negato
	    			if (! Constants.CODICE_TIPO_DOC_PSS.equalsIgnoreCase(tDocumento.getCodTipoDocumento())) {
	    				//no PSS
	    				esito = buildEsito(RestituibileEnum.CONSENSO_NEGATO);
	    			} else {
	    				//yes PSS
	    				if (dmaccIdxDao.isDocumentoOscurato(idDocumentoIlec, codiceComponenteLocale)) {
	    					//PSS oscurato
	    					esito = buildEsito(RestituibileEnum.OSCURATO_PUNTUALE_PSS);
	    				} else if (dmaccIdxDao.isDocumentoOscuratoAGenitore(idDocumentoIlec, codiceComponenteLocale)) { 
	    					//PSS oscurato a genitore
	    					esito = buildEsito(RestituibileEnum.OSCURATO_GENITORE_PSS);	    				
	    				} else {
	    					if (Constants.REGIME_EMERGENZA.equals(regime)) {
	    						//PSS no oscurato - no consenso - emergenza
	    						esito = buildEsito(RestituibileEnum.REGIME_EMERGENZA_PSS);
	    					} else {
	    						//PSS no oscurato - no consenso - no emergenza
	    						esito = buildEsito(RestituibileEnum.CONSENSO_NEGATO_PSS);
	    					}
	    				}
	    			}			
	    		} else {
	    			//consenso accordato
	    			if (dmaccIdxDao.isDocumentoOscuratoByEsenzione(idDocumentoIlec, codiceComponenteLocale, citId)) {
	    				// oscurato esenzione
	    				esito = buildEsito(RestituibileEnum.OSCURATO_ESENZIONI);
	    			} else if (dmaccIdxDao.isDocumentoOscuratoByRicetta(idDocumentoIlec, codiceComponenteLocale, citId)) {
	    				//oscurato ricetta nre
	    				esito = buildEsito(RestituibileEnum.OSCURATO_NRE);
	    			} else if (dmaccIdxDao.isDocumentoOscurato(idDocumentoIlec, codiceComponenteLocale)) {
	    				//oscurato puntuale
	    				esito = buildEsito(RestituibileEnum.OSCURATO_PUNTUALE);
	    			} else if (dmaccIdxDao.isDocumentoOscuratoAGenitore(idDocumentoIlec, codiceComponenteLocale)) {
	    				//oscurato a genitore
	    				esito = buildEsito(RestituibileEnum.OSCURATO_GENITORE);
	    			} else {
	    				//no oscurato
	    				if (statoDocumento.pagatoTicket(listError, tDocumento)) {
	    					//pagato
	    					esito = buildEsito(RestituibileEnum.TICKET_PAGATO);
	    				} else {
	    					//non pagato
	    					if (Constants.REGIME_EMERGENZA.equals(regime)) {
	    						//no oscurato - no pagato - emergenza
	    						esito = buildEsito(RestituibileEnum.REGIME_EMERGENZA);
	    					} else {
	    						//no oscurato - no pagato - no emergenza
	    						esito = buildEsito(RestituibileEnum.TICKET_NON_PAGATO);
	    					}
	    				}
	    			}
	    		}
	    	}
			return Response.status(200).entity(esito).build();
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
		codLMessaggiService.traceMessaggiUpdate(idXml, uuidAsString, esito.getEntity().toString(),
				Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.IS_DOC_RESTITUIBILE, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);

		return esito;
		
	}


	private EsitoIsDocumentoRestituibile buildEsito(RestituibileEnum esitoEnum) {
		EsitoIsDocumentoRestituibile esito = new EsitoIsDocumentoRestituibile();
		esito.setRestituibile(esitoEnum.isRestituibile());
		Codice codice = new Codice(esitoEnum.getCode(),esitoEnum.getDesc());
		esito.setEsito(codice);
		return esito;
	}

	private void verificaStatoConsensiResponse(List<ErroreDettaglioExt> listError, StatoConsensiResponse statoConsensiResponse)
			throws ResponseErrorException {
		if (statoConsensiResponse.getEsito() == null) {
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in verifica stato consensi - servizio non disponibile ");

		}
		List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
		if (statoConsensiResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
			//dai errore e accoda ad errori
			for (Errore errore : statoConsensiResponse.getErrori()) {
				ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
				errorservice.setChiave(errore.getCodice());
				errorservice.setValore(errore.getDescrizione());
				errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
				listerrorservice.add(errorservice);
			}
			
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in stato consensi Recupera documento pdf");
		}
	}
	
}
