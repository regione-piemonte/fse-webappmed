/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndiceResponse;
import it.csi.dma.apiopsan.dto.Azienda;
import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.MetadatiDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.TipoDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.EsitoGetTuttiDoc;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.dto.custom.SintesiDocumentoEsteso;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.AccessionNumberDao;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoAssettoOrganizzativoDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.DocumentoDao;
import it.csi.dma.apiopsan.integration.dao.custom.MedicoDao;
import it.csi.dma.apiopsan.integration.dao.custom.ParametroDao;
import it.csi.dma.apiopsan.integration.dao.custom.TipoDocumentoDao;
import it.csi.dma.apiopsan.integration.dao.custom.UtenteDao;
import it.csi.dma.apiopsan.integration.service.AccodaRichiestaTrasferimentoIndiceService;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.RicercaDocumentiService;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.MetadatoRicercaType;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiOUT;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiResponse;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RisultatoCodice;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.GestionePSS;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class RicercaDocumenti extends LoggerUtil {

	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;

	@Autowired
	@Qualifier("ValidateGenericMeritWhitMedicoImpl")
	protected ValidateGenericMeritWhitMedicoImpl validateGeneric;

	@Autowired
	protected CodRMessaggioErroreService codRMessaggioErroreService;

	@Autowired
	protected CodRLogErroreService codRLogErroreService;

	@Autowired
	protected CodLMessaggiService codLMessaggiService;

	@Autowired
	protected DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	@Autowired
	protected RicercaDocumentiService ricercaDocumentiService;

	@Autowired
	protected AccodaRichiestaTrasferimentoIndiceService accodaIndiceSerivce;

	@Autowired
	protected CatalogoAssettoOrganizzativoDao catalogoAssettoOrganizzativoDao;

	@Autowired
	protected TipoDocumentoDao tipoDocumentoDao;

	@Autowired
	protected DocumentoDao documentoDao;

	@Autowired
	protected AccessionNumberDao accessionNumberDao;

	@Autowired
	protected MedicoDao medicoDao;

	@Autowired
	protected UtenteDao utenteDao;
	
	@Autowired
	protected GestionePSS gestionePSS;
	
	@Autowired
	protected ParametroDao parametroDao;
	
	
	protected String getRuoloFse(String ruolo, String xCodiceServizio) throws DatabaseException {
		// ricerca ruolo FSE
		RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
		String ruoloFse = null;
		if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		
		return ruoloFse;
	}
	
	protected void verificaValidazione(List<ErroreDettaglioExt> listError) throws ResponseErrorException {
		if (!listError.isEmpty()) {
			logInfo(Constants.METODO_EXECUTE,"Validazione Ricerca Documenti NON superata");
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
					"errore durante la validazione");
		}
	}

	protected RicercaDocumentiResponse eseguiRicercaIni(String shibIdentitaCodiceFiscale,
			String xRequestId, String xCodiceServizio, String citId, String ruoloFse, String regime, 
			PayloadSearchTuttiDoc payloadSearchTuttiDoc) {
		
		logInfo(Constants.METODO_EXECUTE,"Chiamata a INI - Ricerca Documenti");
		RicercaDocumentiResponse response = ricercaDocumentiService.getDocumenti(shibIdentitaCodiceFiscale,
				xRequestId, xCodiceServizio, citId, ruoloFse, regime, payloadSearchTuttiDoc);
		logInfo(Constants.METODO_EXECUTE,"Risposta INI: " + response.toString());
		
		return response;

	}
	
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, PayloadSearchTuttiDoc payloadSearchTuttiDoc, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		
		ErrorBuilder error = null;
		String uuidAsString = getUUID();

		// inserisco tabella messaggi e messaggi xml
		Long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
				uuidAsString, Constants.GET_TUTTI_DOC, citId);
		// inserisco tabella dei log
		@SuppressWarnings("unused")
		long idlog = codRLogErroreService.traceLogInsert(Constants.GET_TUTTI_DOC, uuidAsString,
				Constants.CITTADINI_API_SERVICE, null);

		List<SintesiDocumento> documenti = new ArrayList<SintesiDocumento>();
		EsitoGetTuttiDoc esitodoc = new EsitoGetTuttiDoc();

		try {
			// ricerca ruolo FSE
			String ruoloFse = getRuoloFse(ruolo, xCodiceServizio);
						
			logInfo(Constants.METODO_EXECUTE,"Validazione Ricerca Documenti"); 
			List<ErroreDettaglioExt> listError = validateGeneric.validateRicercaDocumenti(shibIdentitaCodiceFiscale,
					xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione,
					citId, payloadSearchTuttiDoc, securityContext, httpHeaders, httpRequest);
			
			verificaValidazione(listError);
			
			RicercaDocumentiResponse response = eseguiRicercaIni(shibIdentitaCodiceFiscale, xRequestId, xCodiceServizio, citId, ruoloFse, 
					regime, payloadSearchTuttiDoc);
			
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();

			if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				logInfo(Constants.METODO_EXECUTE,"Esito chiamata INI: " + RisultatoCodice.FALLIMENTO.name() );
				// ricerca errori specifici
				for (it.csi.dma.apiopsan.ricercadocumentiiniservice.Errore errore : response.getErrori()) {
					logInfo(Constants.METODO_EXECUTE,"Errore chiamata INI: " + errore.getCodice() );					
					// ricerca errore 2007 da rimappare con FSE_ER_505
					if (errore.getCodice().equals(Constants.CODICE_RISPOSTA_CONSENSO_NEGATO)) {

						listerrorservice
								.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));
						throw new ResponseErrorException(
								ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),
								"errore in servizio esterno RicercaDocumentiINIService");

					} else if (errore.getCodice().equals(Constants.CODICE_RISPOSTA_PAZIENTE_RDA_RCD)) {
						// cittadino per cui la regione chiamante coincide con RDA/RCD

						String registry = utenteDao.selectFlagIncaricoPerCodiceFiscale(citId);
						if (registry.equalsIgnoreCase(Constants.N)) {
							logInfo(Constants.METODO_EXECUTE,"Flag incarico: N - AccodaRichiesteTrasferimentoIndice");	 
							// chiamare AccodaRichiesteTrasferimentoIndice
							AccodaRichiesteTrasferimentoIndiceResponse accodaRichiesteResponse = accodaIndiceSerivce
									.accodaTrasferimentoIndice(shibIdentitaCodiceFiscale, xRequestId, xCodiceServizio,
											citId, ruoloFse, regime);

							if (accodaRichiesteResponse.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
								// In caso di errore ritornare la risposta del servizio chiamato
								for (it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.Errore errTransIdx : accodaRichiesteResponse
										.getErrori()) {

									ErroreDettaglioExt erroreDettaglioExtRichiesteTrasf = createErroreDettaglioExt(
											errTransIdx);
									listerrorservice.add(erroreDettaglioExtRichiesteTrasf);

								}

								throw new ResponseErrorException(ErrorBuilder
										.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),
										"errore in servizio AccodaTrasferimentoIndice");

							} else {
								// in caso di successo rimappare la risposta in errore CC_ER_160
								listerrorservice.add(
										validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_160.getCode()));
								throw new ResponseErrorException(
										ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST,
												listerrorservice),
										"risposta 400 con CC_ER_160 in caso di AccodaTrasferimentoIndice success ");

							}

						} else {
							// ricerca dati in replica
							logInfo(Constants.METODO_EXECUTE, "Ricerca documenti in replica");
							documenti = documentoDao.selectDocumentiCategoria(shibIdentitaCodiceFiscale, xRequestId,
									xCodiceServizio, citId, ruoloFse, regime, payloadSearchTuttiDoc);
							String nrecs = documenti != null ? Integer.toString(documenti.size()) : "0";
							logInfo(Constants.METODO_EXECUTE, "Trovati documenti: " + nrecs);
							

							// 13/10/2022 - Il numero dei documenti lo ricavo dalla query precedente
							//Integer total = documentoDao.selectCountDocumentiCategoria(shibIdentitaCodiceFiscale,
							//		xRequestId, xCodiceServizio, citId, ruoloFse, regime, payloadSearchTuttiDoc);
							
							// ricerca accession_number e medici							
							for (SintesiDocumento doc : documenti) {								
								if (doc.getCategoria().equals(Constants.FSE)) {
									
									List<String> accNumberList = accessionNumberDao
											.selectAccessionNumberByIdDoc(doc.getIdDocumentoIlec(), doc.getCodiceCl());
									doc.getMetadatiDocumento().setAccessionNumber(accNumberList);
									List<Medico> medici = medicoDao.selectMediciByIdDoc(doc.getIdDocumentoIlec(),
											doc.getCodiceCl());
									doc.getMetadatiDocumento().setMedici(medici);
									
								}
							}

							esitodoc.setNumeroDocumenti(documenti != null ? documenti.size() : null);							
							esitodoc.setElencoDocumenti(documenti);
							
						}

					} else {
						// restituire stesso errore ritornato dal servizio INI
						ErroreDettaglioExt erroreDettaglioExtIni = createErroreDettaglioExt(errore);
						listerrorservice.add(erroreDettaglioExtIni);

						throw new ResponseErrorException(
								ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),
								"errore in servizio esterno RicercaDocumentiINIService");

					}
				}
			} else {
				
				logInfo(Constants.METODO_EXECUTE,"Esito chiamata INI: successo - cittadino fuori regione");
				// cittadino fuori regione, per cui la risposta contiene i dati ricercati
				Set<String> tipologie = mappaTipologie(payloadSearchTuttiDoc);
				esitodoc = mapRicercaDocumentiOUT(response.getRicercaDocumentiOUT(), citId, tipologie);
			}
			
			// Aggiornamento/Inserimento dei log
			codRLogErroreService.traceLogInsert(Constants.GET_TUTTI_DOC, uuidAsString,
					Constants.CITTADINI_API_SERVICE, Constants.ESITO_SUCCESSO);
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esitodoc.toString(),
					Constants.ESITO_SUCCESSO);
			codRLogErroreService.traceLogAuditInsert(Constants.GET_TUTTI_DOC, uuidAsString, esitodoc.toString(), 
					Constants.CODICE_SUCCESSO_GET_TUTTI_DOC, shibIdentitaCodiceFiscale, 
					collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,null,null,null);

			return Response.status(200).entity(esitodoc).build();

		} catch (DatabaseException e) {
			logError(Constants.METODO_EXECUTE, "Errore riguardante database:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		} catch (ResponseErrorException e) {
			logError(Constants.METODO_EXECUTE, "Errore generico response:", e.getMessage());
			error = e.getResponseError();
		} catch (Exception e) {
			logError(Constants.METODO_EXECUTE, "Errore generico:", e.getMessage());
			error = ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, null);
		}

		error = codRMessaggioErroreService.saveError(error, httpRequest, uuidAsString);
		// aggiornamento record dmacc_l_messaggi e dmacc_l_xml_messaggi
		Response esito = error.generateResponseError();
		// update nessaggi e xml se esito fallimento
		codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),
				Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.GET_TUTTI_DOC, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);

		return esito;
	}

	private ErroreDettaglioExt createErroreDettaglioExt(
			it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.Errore errore) {

		ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
		erroreDettaglio.setChiave(errore.getCodice());
		erroreDettaglio.setValore(errore.getDescrizione());
		erroreDettaglio.setErroreId(erroreDettaglio.getErroreId() != null ? erroreDettaglio.getErroreId()
				: DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);

		return erroreDettaglio;
	}

	private ErroreDettaglioExt createErroreDettaglioExt(it.csi.dma.apiopsan.ricercadocumentiiniservice.Errore errore) {

		ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
		erroreDettaglio.setChiave(errore.getCodice());
		erroreDettaglio.setValore(errore.getDescrizione());
		erroreDettaglio.setErroreId(erroreDettaglio.getErroreId() != null ? erroreDettaglio.getErroreId()
				: DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);

		return erroreDettaglio;
	}

	protected EsitoGetTuttiDoc mapRicercaDocumentiOUT(RicercaDocumentiOUT out, String citId, Set<String> tipologie)
			throws ParseException, DatabaseException, Exception {
		
		List<SintesiDocumento> documenti = new ArrayList<SintesiDocumento>();
		EsitoGetTuttiDoc esitotuttidoc = new EsitoGetTuttiDoc();
				
		try {
			for (MetadatoRicercaType metadato : out.getMetadato()) {
				//se ci sono filtri su tipologie FSE, mappo nella response solo i doc che soddisfano i filtri sulla tipologia
				if (tipologie.isEmpty()
						|| tipologie.contains(metadato.getTipologiaDocumentoMedio())) {
					SintesiDocumentoEsteso doc = new SintesiDocumentoEsteso();
					MetadatiDocumento metadatiDocumento = new MetadatiDocumento();	

					String descAssetto = catalogoAssettoOrganizzativoDao
							.selectDescrizioneAssettoOrgByCodice(metadato.getAssettoOrganizzativo());
					metadatiDocumento.setDescrizioneAssettoOrganizzativo(descAssetto);

					if (metadato.getIstituzioneAutore() != null) {
						Azienda azienda = new Azienda();
						String[] parts = metadato.getIstituzioneAutore().split("\\^");
						if (parts.length > 0 && parts[0] != null)
							azienda.setDescrizioneStruttura(parts[0]);
						metadatiDocumento.setCodiceAzienda(azienda.getCodice());
						metadatiDocumento.setDescrizioneStruttura(azienda.getDescrizioneStruttura());
					}

					doc.setCategoria(Constants.FSE);
					doc.setCitId(citId);
					doc.setCodiceCl(null);
					metadatiDocumento.setCodiceDocumentoDipartimentale(metadato.getIdentificativoDocumento());
					metadatiDocumento.setCodiceTipoDocumentoAlto(metadato.getTipologiaDocumentoAlto());

					if (metadato.getDataValidazioneDocumento() != null) {
						Date dataVal = new SimpleDateFormat("yyyyMMddHHmmss").parse(metadato.getDataValidazioneDocumento());
						metadatiDocumento.setDataValidazione(dataVal);
					}

					metadatiDocumento.setLeggiSpeciali(metadato.getLivelloConfidenzialita().equals("V") ? "S" : "N");


					if (metadato.getCodiceFiscaleAutore() != null) {
						Medico medico = new Medico();
						String[] parts = metadato.getCodiceFiscaleAutore().split("\\^");
						if (parts.length >= 1 && !StringUtils.isBlank(parts[0]) )
							medico.setCodiceFiscale(parts[0]);
						if (parts.length >= 2 && !StringUtils.isBlank(parts[1]) )
							medico.setNome(parts[1]);
						if (parts.length >= 3 && !StringUtils.isBlank(parts[2]) )
							medico.setCognome(parts[2]);
						List<Medico> medici = new ArrayList<>();
						medici.add(medico);
						metadatiDocumento.setMedici(medici);
					}

					// mapping del tipo documento
					TipoDocumento td = tipoDocumentoDao.selectTipoDocumento(metadato.getTipologiaDocumentoMedio());
					metadatiDocumento.setCodiceTipoDocumento(td.getCodice());
					metadatiDocumento.setDescrizioneTipoDocumento(td.getDescrizione());

					metadatiDocumento.setTipoFile(metadato.getTipologiaDocumentoBasso());

					metadatiDocumento.setIdentificativoRepository(metadato.getIdentificativoRepository());

					// paginazione per servizio INI va ignorata
					doc.setMetadatiDocumento(metadatiDocumento);

					documenti.add(doc);
				}
			}
		
		} catch(Exception e) {
			throw e;
		}
		
		esitotuttidoc.setNumeroDocumenti(documenti.size());
		esitotuttidoc.setElencoDocumenti(documenti);
		
		return esitotuttidoc;
	}
	
	protected Set<String> mappaTipologie(PayloadSearchTuttiDoc payload) {
		Set<String> tipologie = new HashSet<>();
		if (payload != null && payload.getCategoriaTipologia() != null) {
			for (CategoriaTipologia tipo : payload.getCategoriaTipologia()) {
				if (Constants.FSE.equals(tipo.getCategoria()) && tipo.getTipologia() != null)
					tipologie.add(tipo.getTipologia());
			}
		}
		return tipologie;
	}

}
