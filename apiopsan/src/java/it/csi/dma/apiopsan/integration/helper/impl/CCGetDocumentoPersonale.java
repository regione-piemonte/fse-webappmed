/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.Documento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.documentopersonaleservice.DocumentoPersonaleService;
import it.csi.dma.apiopsan.external.documentopersonaleservice.Errore;
import it.csi.dma.apiopsan.external.documentopersonaleservice.RichiedenteInfo;
import it.csi.dma.apiopsan.external.documentopersonaleservice.Ruolo;
import it.csi.dma.apiopsan.external.documentopersonaleservice.ApplicativoVerticale;
import it.csi.dma.apiopsan.external.documentopersonaleservice.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.external.documentopersonaleservice.DocumentoPersonale;
import it.csi.dma.apiopsan.external.documentopersonaleservice.RisultatoCodice;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
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
public class CCGetDocumentoPersonale extends LoggerUtil {

	@Autowired
	@Qualifier("ValidateGenericMeritWhitMedicoImpl")
	ValidateGenericMeritWhitMedicoImpl validateGeneric;

	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;

	@Autowired
	CodRLogErroreService codRLogErroreService;

	@Autowired
	CodLMessaggiService codLMessaggiService;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	@Autowired
	DocumentoPersonaleService documentoPersonaleService;

	@Autowired
	StatoConsensiService statoConsensiSerivce;

	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "execute";
		ErrorBuilder error = null;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		// inserisco tabella messaggi e messaggi xml
		Long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
				uuidAsString, Constants.CC_GET_DOCUMENTO_PERSONALE, citId);
		// inserisco tabella dei log
		@SuppressWarnings("unused")
		long idlog = codRLogErroreService.traceLogInsert(Constants.CC_GET_DOCUMENTO_PERSONALE, uuidAsString,
				Constants.CITTADINI_API_SERVICE, null);

		Documento documentoPersonale = new Documento();

		try {
			// ricerca ruolo FSE
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
				ruoloFse = ruoloF.getCodiceRuoloFse();

			// validazione
			List<ErroreDettaglioExt> listError = validateGeneric.validateCCGetDocumentoPersonale(
					shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
					ruoloFse, regime, collocazione, citId, idDocumento, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore durante la validazione");
			}

			// Verifica consensi
			StatoConsensiResponse consensiResponse = statoConsensiSerivce.getStatoConsensi(shibIdentitaCodiceFiscale,
					xRequestId, xCodiceServizio, citId, ruoloFse, regime);
			if (consensiResponse == null || consensiResponse.getEsito().name()
					.equals(it.csi.dma.apiopsan.consensoextservice.RisultatoCodice.FALLIMENTO.name())) {
				throw new ResponseErrorException(
						ErrorBuilder.from(StatusEnum.SERVER_ERROR).title("Errore statoConsensiSerivce")
								.detail(consensiResponse != null ? consensiResponse.getErrori() : null, e -> {
									ErroreDettaglioExt d = new ErroreDettaglioExt();
									d.setErroreId(Constants.DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
									d.setChiave(e.getCodice());
									d.setValore(e.getDescrizione());
									return d;
								}),
						"Errore in statoConsensiSerivce");
			} else {
				log.info(consensiResponse.getStatoConsensiOUT().getConsensoConsultazione());

				if (consensiResponse.getStatoConsensiOUT().getConsensoConsultazione().equalsIgnoreCase(Constants.TRUE)) {

					// composizione request
					RichiedenteInfo richiedenteInfo = createRichiedenteInfoRequest(xCodiceVerticale, xCodiceServizio,
							ruolo, shibIdentitaCodiceFiscale, xForwardedFor, xRequestId);

					final Holder<List<Errore>> errori = new Holder<List<Errore>>();
					final Holder<RisultatoCodice> esito = new Holder<RisultatoCodice>();
					final Holder<DocumentoPersonale> documento = new Holder<DocumentoPersonale>();

					documentoPersonaleService.getDocumentoPersonale(idDocumento.toString(), citId, richiedenteInfo,
							errori, esito, documento);

					if (RisultatoCodice.FALLIMENTO.name().equals(esito.value.name())) {
						for (Errore errore : errori.value) {
							ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
							erroreDettaglio.setChiave(errore.getCodice());
							erroreDettaglio.setValore(errore.getDescrizione());
							erroreDettaglio.setErroreId(Constants.DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
							listError.add(erroreDettaglio);
						}
						throw new ResponseErrorException(
								ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),
								"errore in servizio esterno documentoPersonaleService");
					}  else if (documento.value != null) {
						// mappatura risposta
						if (documento.value.getAllegato() != null) {
							byte[] allegato = documento.value.getAllegato();
							//codifico in Base64 se non lo e'
							if (! Util.isBase64Encoded(allegato)) {
								allegato = Base64.getEncoder().encode(documento.value.getAllegato());
							}
							documentoPersonale.setAllegato(new String(allegato));
						}
						documentoPersonale.setTipoAllegato(documento.value.getTipoAllegato());
						if (documento.value.getDataOraAggiornamento() != null) {
							Date dataOraAggiornamento = documento.value.getDataOraAggiornamento().toGregorianCalendar()
									.getTime();
							documentoPersonale.setDataOraAggiornamento(dataOraAggiornamento);
						}
						documentoPersonale.setTrascrizione(documento.value.getTrascrizione());
					}

				} else {
					// ritorna errore FSE_ER_505
					listError.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));
					throw new ResponseErrorException(
							ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
							"errore in servizio esterno RicercaDocumentiINIService");
				}
			}

			// Aggiornamento/Inserimento dei log
			codRLogErroreService.traceLogInsert(Constants.CC_GET_DOCUMENTO_PERSONALE, uuidAsString,
					Constants.CITTADINI_API_SERVICE, Constants.ESITO_SUCCESSO);
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, idDocumento.toString(), Constants.ESITO_SUCCESSO);
			String[] array = new String[2];
			array[0] = idDocumento.toString();
			array[1] = Converter.getDataISO(documentoPersonale.getDataOraAggiornamento());
			codRLogErroreService.traceLogAuditInsert(Constants.CC_GET_DOCUMENTO_PERSONALE, uuidAsString,
					documentoPersonale.toString(), Constants.COD_SUCCESSO_CC_GET_DOC_PERS, shibIdentitaCodiceFiscale,
					collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,idDocumento,null);

			return Response.status(200).entity(documentoPersonale).build();

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
		codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),
				Constants.ESITO_FALLIMENTO);
		codRLogErroreService.traceLogInsert(Constants.CC_GET_DOCUMENTO_PERSONALE, uuidAsString,
				Constants.CITTADINI_API_SERVICE, Constants.ESITO_FALLIMENTO);

		return esito;
	}

	private RichiedenteInfo createRichiedenteInfoRequest(String xCodiceVerticale, String xCodiceServizio, String ruolo,
			String shibIdentitaCodiceFiscale, String xForwardedFor, String xRequestId) {

		RichiedenteInfo richiedenteInfo = new RichiedenteInfo();
		ApplicativoVerticale applicativoVerticale = new ApplicativoVerticale();
		applicativoVerticale.setCodice(xCodiceVerticale);
		richiedenteInfo.setApplicativoVerticale(applicativoVerticale);
		ApplicazioneRichiedente applicazioneRichiedente = new ApplicazioneRichiedente();
		applicazioneRichiedente.setCodice(xCodiceServizio);
		richiedenteInfo.setApplicazione(applicazioneRichiedente);
		Ruolo ruoloWs = new Ruolo();
		ruoloWs.setCodice(ruolo);
		richiedenteInfo.setRuolo(ruoloWs);
		richiedenteInfo.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedenteInfo.setIp(xForwardedFor);
		richiedenteInfo.setNumeroTransazione(xRequestId);

		return richiedenteInfo;
	}

}
