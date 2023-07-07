/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.EsitoGetSoloEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
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
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Converter;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class SoloEpisodi extends LoggerUtil {
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
	private static final QName SERVICE_NAME = new QName("http://dmacc.csi.it/", "ConsensoINIExtService");
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
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String regime, String collocazione,
			String citId, PayloadSearchEpisodi payloadSearchEpisodi,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.POST_SOLOEPISODI,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.POST_SOLOEPISODI, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateSoloEpisodi(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, payloadSearchEpisodi, securityContext, httpHeaders, httpRequest);
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
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in stato consensi post soloepisodi");
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
				//analizzo singoli casi
//				â¢	esito  âSUCCESSOâ occorre verificare il valore del campo âidentificativoInformativaCorrenteâ . Se tale campo contiene un valore che:
//					o	NON inizia per 010 (assistito NON piemontese) allora il sistema restituisce e traccia lâerrore FSE_ER_568 ed esce 
//				(in quanto la visualizzazione per episodi Ã¨ consentita solo per gli assistiti piemontesi.
				if (!result.getIdentificativoInformativaCorrente().startsWith(Constants.IDENTIFICATIVO_ORGANIZZAZIONE)){
					//dai errore e accoda ad errori
					listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_568.getCode()));
					throw new ResponseErrorException(
							ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in post soloepisodi paziente fuori regione");
				}
				else {
					if (!result.isConsensoConsultazione()) {
//						o	inizia per â010â (assistito Piemonteseâ occorre verificare il valore del tag <consensoConsultazione>.Se:
//						o	<consensoConsultazione>=False allora il sistema restituisce e traccia lâerrore FSE_ER_505 ed esce (in quanto lâassistito ha negato il consenso alla consultazione)
						listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));
						
						throw new ResponseErrorException(
								ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in post soloepisodi paziente piemontese con consenso false");
					}
					else {
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
											ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in trasferimento indice service post soloepisodi");
								}//fine errore accoda
								else {
									//si genera errore 400 e codice di errore CC_ER_160 se esito successo
									listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_160.getCode()));
									throw new ResponseErrorException(
											ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in post soloepisodi per successo accoda trasferimento indice");
								}
							}//fine flag n
							else {
								EsitoGetSoloEpisodi elencoEpisodi = new EsitoGetSoloEpisodi();
								List<Episodio> listaepisodi = new ArrayList<Episodio>();
								//numero in ciascuno non va bene al massimo nella lista
								listaepisodi = dmaccIdxDao.selectListaEpisodiNoOscurati(payloadSearchEpisodi.getTipologiaDocumento(), payloadSearchEpisodi.getTipoEpisodio(), 
										citId, payloadSearchEpisodi.getFiltroDocs().getDataInizio(), payloadSearchEpisodi.getFiltroDocs().getDataFine());
								List<Episodio> episodio = (ArrayList) listaepisodi.stream().distinct().collect(Collectors.toList());
								elencoEpisodi.setNumeroEpisodi(episodio.size());
								//prendo solo quelli delimitati da limit e offset
								
								episodio = Util.subList(episodio,payloadSearchEpisodi.getOffset(),payloadSearchEpisodi.getLimit());
								elencoEpisodi.setElencoEpisodi(episodio);
							
							//inserimento nel log esito positivo
							codRLogErroreService.traceLogInsert(Constants.POST_SOLOEPISODI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
							codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, elencoEpisodi.toString(),Constants.ESITO_SUCCESSO);
							String[] array = new String[4];
							array[0] = payloadSearchEpisodi.getTipologiaDocumento().toString();
							array[1] = payloadSearchEpisodi.getTipoEpisodio();
							array[2] = Converter.getDataISO(payloadSearchEpisodi.getFiltroDocs().getDataInizio());
							array[3] = Converter.getDataISO(payloadSearchEpisodi.getFiltroDocs().getDataFine());
							codRLogErroreService.traceLogAuditInsert(Constants.POST_SOLOEPISODI, uuidAsString, elencoEpisodi.toString(), 
									Constants.CODICE_SUCCESSO_POSTSOLOEPISODI, shibIdentitaCodiceFiscale, 
									collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,null,null);
							 return Response.status(200).entity(elencoEpisodi).build();
							}
					}
			}//fine stato consensi
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
		codRLogErroreService.traceLogInsert(Constants.POST_SOLOEPISODI, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

}


