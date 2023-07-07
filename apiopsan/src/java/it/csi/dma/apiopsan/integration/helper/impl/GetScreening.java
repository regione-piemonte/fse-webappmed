/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndiceResponse;
import it.csi.dma.apiopsan.consensoextservice.Errore;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.AccessionNumber;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiResponse;
import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.EsameScreening;
import it.csi.dma.apiopsan.dto.EsitoGetSoloEpisodi;
import it.csi.dma.apiopsan.dto.EsitoInfoScreening;
import it.csi.dma.apiopsan.dto.GiorniApertura;
import it.csi.dma.apiopsan.dto.GiudizioDiagnostico;
import it.csi.dma.apiopsan.dto.IndicazioneFinale;
import it.csi.dma.apiopsan.dto.IndirizzoStudio;
import it.csi.dma.apiopsan.dto.InfoMmgPaziente;
import it.csi.dma.apiopsan.dto.InterventoErogato;
import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.MedicoMmg;
import it.csi.dma.apiopsan.dto.MedicoTipoSingolo;
import it.csi.dma.apiopsan.dto.PayloadComunicazioneConsensi;
import it.csi.dma.apiopsan.dto.Paziente;
import it.csi.dma.apiopsan.dto.Screening;
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
import it.csi.dma.apiopsan.integration.dao.custom.UtenteDao;
import it.csi.dma.apiopsan.integration.service.AccodaRichiestaTrasferimentoIndiceService;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.DocumentiServiceApisan;
import it.csi.dma.apiopsan.integration.service.ScreeningServiceApiSan;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.screeningservice.dmacc.ScreeningResponse;
import it.csi.dma.apiopsan.screeningservice.dmaccl.InformazioniSistemiScreening;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ScreeningEsame;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ScreeningGiudizioDiagnostico;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ScreeningMedico;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ScreeningPrestazione;
import it.csi.dma.apiopsan.screeningservice.dmaccl.ScreeningTrattamento;
import it.csi.dma.apiopsan.exception.ResponseErrorException;

@Service
public class GetScreening extends LoggerUtil {
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
	StatoConsensiService statoConsensiSerivce;
	
	@Autowired
	DocumentiServiceApisan documentiService;
	
	@Autowired
	DmaccIdxDao dmaccIdxDao;
	
	@Autowired
	UtenteDao utenteDao;
	
	@Autowired
	AccodaRichiestaTrasferimentoIndiceService accodaIndiceSerivce;
	
	@Autowired
	ScreeningServiceApiSan screenigService;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		String methodName = "execute";
		ErrorBuilder error = null;
		//genero uid 
		 UUID uuid = UUID.randomUUID();
	     String uuidAsString = uuid.toString();
	     //inserisco tabella messaggi e messaggi xml
	    long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
	    		xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, 
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_SCREENING,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_SCREENING, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
	 
	 		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateInfoScreening(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			
			//chiamo il servizio PazienteService GetMMGPaziente
			//compongo la chiamata
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
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in stato consensi documenti episodio");
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
								ScreeningResponse respScreening = screenigService.getScreening(shibIdentitaCodiceFiscale, xRequestId, xCodiceServizio, ruoloFse, xCodiceVerticale, xForwardedFor, citId, regime);
								if (respScreening.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
									//dai errore e accoda ad errori
									for (Errore errore : response.getErrori()) {
										ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
										errorservice.setChiave(errore.getCodice());
										errorservice.setValore(errore.getDescrizione());
										errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
										listerrorservice.add(errorservice);
									}
									
									throw new ResponseErrorException(
											ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in stato consensi documenti episodio");
								}else {
									//prendo i tipi screening
									HashSet<String> tiposcreening = new HashSet<>();
									for (InformazioniSistemiScreening infosc: respScreening.getInformazioniSistemiScreening()) {
										tiposcreening.add(infosc.getTipo().getDescrizione());
									}
								//prendo il paziente
									Paziente paziente = new Paziente();
									EsitoInfoScreening esito = new EsitoInfoScreening();
									for (InformazioniSistemiScreening infosc: respScreening.getInformazioniSistemiScreening()) {
										paziente.setCodiceFiscale(infosc.getPaziente().getCodiceFiscale());
										paziente.setCognome(infosc.getPaziente().getCognome());
										paziente.setNome(infosc.getPaziente().getNome());
										paziente.setDataNascita(infosc.getPaziente().getDataDiNascita().toGregorianCalendar().getTime());
										paziente.setComuneNascita(new Codice(infosc.getPaziente().getComuneDiNascita().getCodice(),respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getComuneDiNascita().getDescrizione()));
										paziente.setStatoNascita(new Codice(infosc.getPaziente().getStatoDiNascita().getCodice(),respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getStatoDiNascita().getDescrizione()));
										paziente.setSesso(infosc.getPaziente().getSesso().value());
										paziente.setFlagRegistryIncarico(infosc.getPaziente().getFlagRegistryIncarico()!=null ? Util.stringToBoolean(respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getFlagRegistryIncarico(), Constants.TRUE) : null);
										paziente.setIdAsr(infosc.getPaziente().getIdAsr() !=null ? respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getIdAsr().toString() : null);
										paziente.setIdAura(infosc.getPaziente().getIdAura() != null ? respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getIdAura().toString() : null);
										paziente.setIdIrec(infosc.getPaziente().getIdIrec() != null ? respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getIdIrec().toString() : null);
										paziente.setCodiceFiscaleMmg(infosc.getPaziente().getCodiceFiscaleMMG() !=null ? respScreening.getInformazioniSistemiScreening().get(0).getPaziente().getCodiceFiscaleMMG() : null);
										
									}	
									//ciclo sulo screening
									List <Screening> screeningList = new ArrayList<Screening>();
									for (String screen: tiposcreening) {
										Screening screening = new Screening();
										List<EsameScreening> esameScreeningList = new ArrayList<EsameScreening>();
										for (InformazioniSistemiScreening infosc: respScreening.getInformazioniSistemiScreening()) {
											if (infosc.getTipo().getDescrizione().equalsIgnoreCase(screen)) {
												screening.setIdScreening(infosc.getPaziente().getIdScreening());
												screening.setTipoScreening(new Codice(infosc.getTipo().getCodice(),infosc.getTipo().getDescrizione()));
												for (ScreeningEsame e : infosc.getEsami()) {
													EsameScreening esameScr = new EsameScreening();
													esameScr.setData(e.getData().toGregorianCalendar().getTime());
													esameScr.setTipo(new Codice(e.getTipoEsame().getCodice(),e.getTipoEsame().getDescrizione()));
													esameScr.setEsito(e.getEsito());
													esameScr.setAziendaSanitaria(new Codice(e.getAziendaSanitaria().getCodice(),e.getAziendaSanitaria().getDescrizione()));
													esameScr.setUnitaOperativa(new Codice(e.getUnitaOperativa().getCodice(),e.getUnitaOperativa().getDescrizione()));
													List<Codice> prestazioni= new ArrayList<Codice>();
													for(ScreeningPrestazione c : e.getDettaglio().getPrestazioni()) {
														Codice prestazione= new Codice(c.getCodice(),c.getDescrizione());
														prestazioni.add(prestazione);
													}
													esameScr.setPrestazioni(prestazioni);
													List<GiudizioDiagnostico> giudiziDiagn= new ArrayList<GiudizioDiagnostico>();
													for(ScreeningGiudizioDiagnostico g : e.getDettaglio().getGiudiziDiagnostici()) {
														GiudizioDiagnostico giudizio = new GiudizioDiagnostico();
														giudizio.setGiudizio(new Codice(g.getGiudizio().getCodice(),g.getGiudizio().getDescrizione()));
														List<Codice> prestazioniDiagnostica = new ArrayList<Codice>();
														List<MedicoTipoSingolo> medici = new ArrayList<>(); 
														for(ScreeningPrestazione prest : g.getPrestazioni()) {
															Codice prestazioneDiagn= new Codice(prest.getCodice(),prest.getDescrizione());
															prestazioniDiagnostica.add(prestazioneDiagn);
														}
														for(ScreeningMedico mediciScreen : g.getMedici()) {
															MedicoTipoSingolo medico= new MedicoTipoSingolo();
															medico.setNome(mediciScreen.getNome());
															medico.setCognome(mediciScreen.getCognome());
															medico.setFiguraProfessionale(mediciScreen.getFiguraProfessionale());
															medici.add(medico);
														}
														
														giudizio.setMedici(medici);
														giudizio.setPrestazioni(prestazioniDiagnostica);
														giudiziDiagn.add(giudizio);
													}
													
													esameScr.setGiudiziDiagnostici(giudiziDiagn);
													IndicazioneFinale indicazioneFinale = new IndicazioneFinale();
													if (e.getDettaglio().getIndicazioneFinale().getGiudizio()!=null)
													indicazioneFinale.setGiudizione(new Codice(e.getDettaglio().getIndicazioneFinale().getGiudizio().getCodice(),e.getDettaglio().getIndicazioneFinale().getGiudizio().getDescrizione()));
													List<Medico> medicoIndList = new ArrayList<>(); 
													for(ScreeningMedico medicoInd : e.getDettaglio().getIndicazioneFinale().getMedici()) {
														Medico medInd = new Medico();
														medInd.setCognome(medicoInd.getCognome());
														medInd.setNome(medicoInd.getNome());
														medInd.setCodiceFiscale(null); //Da indicazione di Veronica Berti
														medInd.setTipoMedico(null); //Da indicazione di Veronica Berti
														medicoIndList.add(medInd);
													}
													
													indicazioneFinale.setMedici(medicoIndList);
													InterventoErogato interventoErogato = new InterventoErogato();
													if(e.getDettaglio().getInterventoErogato() != null) {
														interventoErogato.setData(e.getDettaglio().getInterventoErogato().getData() != null ? e.getDettaglio().getInterventoErogato().getData().toGregorianCalendar().getTime() : null);
														List<Codice> trattamenti = new ArrayList<Codice>();
														for(ScreeningTrattamento trattamento :	e.getDettaglio().getInterventoErogato().getTrattamenti()) {
															Codice tratt= new Codice(trattamento.getCodice(),trattamento.getDescrizione());
															trattamenti.add(tratt);
														}
														
														interventoErogato.setEsito(e.getDettaglio().getInterventoErogato().getEsito()!= null ? e.getDettaglio().getInterventoErogato().getEsito() : null);
														interventoErogato.setTrattamenti(trattamenti);
														interventoErogato.setStrutturaSanitaria(e.getDettaglio().getInterventoErogato().getStrutturaSanitaria() != null ? new Codice(e.getDettaglio().getInterventoErogato().getStrutturaSanitaria().getCodice(),e.getDettaglio().getInterventoErogato().getStrutturaSanitaria().getDescrizione()) : null);
														esameScr.setInterventoErogato(interventoErogato);
														
													}
													esameScr.setIndicazioneFinale(indicazioneFinale);
													esameScreeningList.add(esameScr);
													
												}
											}
											
										}
										screening.setEsami(esameScreeningList);
										screeningList.add(screening);
									}
									esito.setPaziente(paziente);
									esito.setInfoSistemiScreening(screeningList);
									
									//inserimento nel log esito positivo
								codRLogErroreService.traceLogInsert(Constants.GET_SCREENING, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
								codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.toString(),Constants.ESITO_SUCCESSO);
									return Response.status(200).entity(esito).build();
								}
								}//fine esito positivo restituisco episodi
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
		codRLogErroreService.traceLogInsert(Constants.GET_SCREENING, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}
	
	

}


