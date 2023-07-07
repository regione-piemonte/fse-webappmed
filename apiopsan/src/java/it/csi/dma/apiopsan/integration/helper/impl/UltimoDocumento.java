/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndiceResponse;
import it.csi.dma.apiopsan.consensoextservice.Errore;
import it.csi.dma.apiopsan.consensoextservice.RisultatoCodice;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.EsitoUltimoDocumento;
import it.csi.dma.apiopsan.dto.LuogoProduzioneDocumento;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.Ruolo;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.esenzioniservice.Esenzione;
import it.csi.dma.apiopsan.esenzioniservice.EsenzioniResponse;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.dao.custom.UtenteDao;
import it.csi.dma.apiopsan.integration.service.AccodaRichiestaTrasferimentoIndiceService;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.integration.service.UltimoDocumentoService;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.AziendaSanitaria;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.LuogoASU;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.MetadatiUltimoDocumento;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.StrutturaSanitaria;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.UnitaOperativaSanitaria;
import it.csi.dma.apiopsan.ultimodocumentoservice.dmacc.GetUltimoDocumentoResponse;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Converter;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class UltimoDocumento extends LoggerUtil {
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
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
	DmaccIdxDao dmaccIdxDao;
	
	@Autowired
	UtenteDao utenteDao;
	
	@Autowired
	AccodaRichiestaTrasferimentoIndiceService accodaIndiceSerivce;
	
	@Autowired
	UltimoDocumentoService ultimoDocumentoSerivce;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String regime, String collocazione,
			String citId, String tipoDocumento,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.ULTIMO_DOCUMENTO,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.ULTIMO_DOCUMENTO, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
			Ruolo ruoloxini = dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(ruoloFse);
			String ruoloIni = null;
			if (ruoloxini != null)
			ruoloIni = ruoloxini.getCodiceRuoloIni();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateUltimoDocumento(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, tipoDocumento, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			StatoConsensiResponse response = statoConsensiSerivce.getStatoConsensi(shibIdentitaCodiceFiscale,xRequestId,xCodiceServizio,citId,ruoloFse,regime);
			
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
			if (response.getEsito() == null) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in verifica stato consensi - servizio non disponibile ");

			}
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
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in stato consensi ultimo documento");
			}
			else {
				//dai esito positivo con payload
				StatoConsenso result = new StatoConsenso();
				result.setIdentificativoAssistitoConsenso(response.getStatoConsensiOUT().getIdentificativoAssistitoConsenso());
				result.setIdentificativoInformativaConsensi(response.getStatoConsensiOUT().getIdentificativoInformativaConsensi());
				result.setIdentificativoInformativaCorrente(response.getStatoConsensiOUT().getIdentificativoInformativaCorrente());
				result.setConsensoAlimentazione(Util.stringToBoolean(response.getStatoConsensiOUT().getConsensoAlimentazione(),Constants.TRUE));
				result.setConsensoConsultazione(Util.stringToBoolean(response.getStatoConsensiOUT().getConsensoConsultazione(),Constants.TRUE));
				result.setConsensoPregresso(Util.stringToBoolean(response.getStatoConsensiOUT().getConsensoPregresso(),Constants.TRUE));

//              2022-10-26: NON fare considerazioni su Regione di assistenza: ricerco il PSS anche per i fuori regione				
//				if (!result.getIdentificativoInformativaCorrente().startsWith(Constants.IDENTIFICATIVO_ORGANIZZAZIONE)){
//					//dai errore e accoda ad errori
//					listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_568.getCode()));
//					throw new ResponseErrorException(
//							ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in ultimo documento paziente fuori regione");
//				}
				
				//se diverso da 010 da errore 505

				if ( !result.isConsensoConsultazione()
						&& !Constants.REGIME_EMERGENZA.equals(regime)) {
					//						o	inizia per â010â (assistito Piemonteseâ occorre verificare il valore del tag <consensoConsultazione>.Se:
					//						o	<consensoConsultazione>=False allora il sistema restituisce e traccia lâerrore FSE_ER_505 ed esce (in quanto lâassistito ha negato il consenso alla consultazione)
					listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));

					throw new ResponseErrorException(
							ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in ultimo documento con consenso false e regime non Emergenza");
				} else {
					//se consenso true allora guarda flag registry
					//verifico valore flag registry
					String flagincarico = utenteDao.selectFlagIncaricoPerCodiceFiscale(citId);
					if (flagincarico.equalsIgnoreCase(Constants.N)) {
						//chiamo il servizio trasferimento indice  flag registry N
						AccodaRichiesteTrasferimentoIndiceResponse accodaindice = new AccodaRichiesteTrasferimentoIndiceResponse();
						accodaindice = accodaIndiceSerivce.accodaTrasferimentoIndice(shibIdentitaCodiceFiscale, xRequestId, xCodiceServizio, citId, ruoloFse, regime);
						if (accodaindice.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
							//dai errore e accoda ad errori
							for (it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.Errore errore : accodaindice.getErrori()) {
								ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
								errorservice.setChiave(errore.getCodice());
								errorservice.setValore(errore.getDescrizione());
								errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
								listerrorservice.add(errorservice);
							}

							throw new ResponseErrorException(
									ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in trasferimento indice service ultimo documento");
						}//fine errore accoda
						else {
							//si genera errore 400 e codice di errore CC_ER_160 se esito successo
							listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_160.getCode()));
							throw new ResponseErrorException(
									ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in ultimo documento per successo accoda trasferimento indice");
						}
					}//fine flag n
					else {
						EsitoUltimoDocumento esitoultimo = new EsitoUltimoDocumento();
						String clLocale = null;
						GetUltimoDocumentoResponse resUltimo = ultimoDocumentoSerivce.getUltimoDocumento(shibIdentitaCodiceFiscale, xRequestId, xCodiceServizio, citId, ruoloIni, tipoDocumento, regime);
						if (resUltimo.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
							//dai errore e accoda ad errori
							for (it.csi.dma.apiopsan.ultimodocumentoservice.dma.Errore errore : resUltimo.getErrori()) {
								ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
								errorservice.setChiave(errore.getCodice());
								errorservice.setValore(errore.getDescrizione());
								errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
								listerrorservice.add(errorservice);
							}
							throw new ResponseErrorException(
									ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in ultimo documento service");
						}
						else {
							if (resUltimo.getMetadatiDocumento().size()>0) {
								//prendo unico della list
								MetadatiUltimoDocumento metaultimo = resUltimo.getMetadatiDocumento().get(0);
								//2022-11-24: anche se regime emergenza, non restituire se oscurato
								//2023-04-26: restituisci errore doc non trovato se oscurato
								//TODO: restituisci errore CC_ER_195 se arriva oscurato da CC
								if (metaultimo.getOscuramento().getOscurato().name().equalsIgnoreCase(Constants.SI)) {
									listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_195.getCode()));
									throw new ResponseErrorException(
											ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"ultimo documento non trovato");
							    } else {
							    	//documento non oscurato
									if (metaultimo.getComponenteLocale() != null) {
										clLocale = metaultimo.getComponenteLocale().getCodice();
									}
									List<String> accessionNumber = new ArrayList<String>();
									accessionNumber.add(metaultimo.getAccessionNumber());
									esitoultimo.setAccessionNumber(accessionNumber);
									esitoultimo.setDataValidazione(Converter.getData(metaultimo.getDataValidazione()));
									esitoultimo.setFirmatoDigitalmente(metaultimo.getFirmatoDigitalmente().name());
									esitoultimo.setFlagUltimoDocumento(metaultimo.getFlagUltimoDocumento().equalsIgnoreCase(Constants.S) ? true : false);
									esitoultimo.setFonteOscuramento(metaultimo.getFonteOscuramento());
									esitoultimo.setIdDocumento(Converter.getLong(metaultimo.getIdDocumento()));
									esitoultimo.setIdEpisodio(Converter.getLong(metaultimo.getIdEpisodio()));
									Codice azienda = new Codice();
									azienda.setCodice(metaultimo.getLuogoProduzioneDocumento().getAzienda().getCodice());
									azienda.setDescrizione(metaultimo.getLuogoProduzioneDocumento().getAzienda().getDescrizione());
									Codice struttura = new Codice();
									struttura.setCodice(metaultimo.getLuogoProduzioneDocumento().getStruttura().getCodice());
									struttura.setDescrizione(metaultimo.getLuogoProduzioneDocumento().getStruttura().getDescrizione());
									Codice uo = new Codice();
									uo.setCodice(metaultimo.getLuogoProduzioneDocumento().getUnitaOperativa().getCodice());
									uo.setDescrizione(metaultimo.getLuogoProduzioneDocumento().getStruttura().getDescrizione());
									LuogoProduzioneDocumento luogoprod = new LuogoProduzioneDocumento();
									luogoprod.setAzienda(azienda);
									luogoprod.setStruttura(struttura);
									luogoprod.setUnitaOperativa(uo);
									esitoultimo.setLuogoProduzioneDocumento(luogoprod);
									esitoultimo.setMedicoValidante(metaultimo.getMedicoValidante());
									esitoultimo.setMedicoVisita(metaultimo.getMedicoVisita());
									esitoultimo.setOidDocumento(metaultimo.getOidDocumento());
									esitoultimo.setOidRepository(metaultimo.getOidRepository());
									esitoultimo.setOscurato(metaultimo.getOscuramento().getOscurato().name().equalsIgnoreCase(Constants.SI) ? true : false);
									esitoultimo.setTipoDocumento(metaultimo.getTipoDocumento().getCodice());

									// mappatura risposta
									if (metaultimo.getDocumento() != null) {
										byte[] allegato = metaultimo.getDocumento();
										//codifico in Base64 se non lo e'
										if (! Util.isBase64Encoded(allegato)) {
											allegato = Base64.getEncoder().encode(metaultimo.getDocumento());
										}
										esitoultimo.setDocumento(new String(allegato));
									}
								}
							}
						}
						//inserimento nel log esito positivo
						codRLogErroreService.traceLogInsert(Constants.ULTIMO_DOCUMENTO, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
						codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, resUltimo.toString(),Constants.ESITO_SUCCESSO);
						String[] array = new String[2];
						array[0] = citId;
						if (esitoultimo.getIdDocumento() != null) {
							array[1] = esitoultimo.getIdDocumento().toString();
						}
						codRLogErroreService.traceLogAuditInsert(Constants.ULTIMO_DOCUMENTO, uuidAsString, esitoultimo.toString(), 
								Constants.CODICE_SUCCESSO_ULTIMO_DOCUMENTO, shibIdentitaCodiceFiscale, 
								collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,esitoultimo.getIdDocumento(),clLocale);
						 return Response.status(200).entity(esitoultimo).build();
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
		codRLogErroreService.traceLogInsert(Constants.ULTIMO_DOCUMENTO, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

}


