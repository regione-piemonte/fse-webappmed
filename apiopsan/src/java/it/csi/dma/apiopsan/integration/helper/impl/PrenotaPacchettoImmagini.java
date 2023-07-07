/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.RisultatoCodice;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.ScaricoStudiRequest;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.ScaricoStudiResponse;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.ScaricoStudiWSBean;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class PrenotaPacchettoImmagini extends LoggerUtil {

	@Autowired
	ScaricoStudiWSBean scaricoStudiWSBean;

	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;

	@Autowired
	CodRLogErroreService codRLogErroreService;

	@Autowired
	CodLMessaggiService codLMessaggiService;

	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			ScaricoStudi scaricoStudi, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		final String methodName = "execute";
		ErrorBuilder error = null;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		long id_xml = 0;
		try {
			String citId = (scaricoStudi != null && scaricoStudi.getCitId() != null) ? scaricoStudi.getCitId() : "";

			id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
					uuidAsString, Constants.SCARICA_PACCHETTO_IMR, citId);

			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null) {
				ruoloFse = ruoloF.getCodiceRuoloFse();
			}
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validatePrenotazioneImmagini(
					shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
					regime, collocazione, ruoloFse, scaricoStudi, securityContext, httpHeaders, httpRequest);

			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore in validate");
			}

			ScaricoStudiResponse res = scaricoStudiWSBean.scaricoStudi(newScaricoStudiRequest(scaricoStudi));

			if (res == null || RisultatoCodice.FALLIMENTO.equals(res.getEsito())) {

				// TODO RITORNO TUTTI GLI ERRORI IN DETTAGLIO COME SCRITTO DA ANALISI MA DEVO
				// VERIFICARE COME RITORNARLI DA DATABASE

				throw new ResponseErrorException(ErrorBuilder.from(StatusEnum.SERVER_ERROR)
						.title("Errore servizio scarica studi").detail(res != null ? res.getErrori() : null, e -> {
							ErroreDettaglioExt d = new ErroreDettaglioExt();
							d.setErroreId(Constants.DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
							d.setChiave(e.getCodice());
							d.setValore(e.getDescrizione());
							return d;
						}), "Errore in chiamata servizio scarico studi");
			}			

			ScaricoStudi scarico = new ScaricoStudi();
			scarico.setCitId(res.getEsito());

			/*
			 * Non Ã¨ per il momento previsto da analisi
			 * codRLogErroreService.traceLogInsert(Constants.SCARICA_PACCHETTO_IMR,
			 * uuidAsString, Constants.PRENOTA_PACCHETTO_IMMAGINI_SERVICE,
			 * Constants.ESITO_SUCCESSO);
			 */
			String[] array = new String[3];
			array[0] = shibIdentitaCodiceFiscale;
			array[1] = scaricoStudi.getCitId();
			array[2] = scaricoStudi.getIdReferto();
			codRLogErroreService.traceLogAuditInsert(Constants.SCARICA_PACCHETTO_IMR, uuidAsString,
					Response.ok().toString(), Constants.CODICE_SUCCESSO_SCARICA_PACCHETTO_IMR,
					shibIdentitaCodiceFiscale, collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse,
					regime, scaricoStudi.getCitId(),array,null,null);

			return Response.ok().build();
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

		/*
		 * Non Ã¨ per il momento previsto da analisi
		 * codRLogErroreService.traceLogInsert(Constants.SCARICA_PACCHETTO_IMR,
		 * uuidAsString,
		 * Constants.PRENOTA_PACCHETTO_IMMAGINI_SERVICE,Constants.ESITO_FALLIMENTO);
		 */

		Response esito = error.generateResponseError();
		if (id_xml != 0) {
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),
					Constants.ESITO_FALLIMENTO);
		}
		return esito;
	}

	private ScaricoStudiRequest newScaricoStudiRequest(ScaricoStudi scaricoStudi) {
		ScaricoStudiRequest request = new ScaricoStudiRequest();
		request.setAcessionNumbers(getAccessionNumbers(scaricoStudi)); // FIXME
		request.setAsr(scaricoStudi.getCodCl());
		request.setCodiceFiscale(scaricoStudi.getCitId());
		request.setIdReferto(scaricoStudi.getIdReferto());
		if (scaricoStudi.getPeriodoConservazione() != null) {
			request.setPeriodoConservazione(scaricoStudi.getPeriodoConservazione().toString());
		}
		request.setPin(Constants.PIN_IMR);
		request.setSistemaOperativo(Constants.WINDOWS);

		return request;
	}

	private String getAccessionNumbers(ScaricoStudi ref) {
		String accessionNumbers = "";

		for (String ans : ref.getAccessionNumber()) {

			if ("".equals(accessionNumbers)) {
				accessionNumbers = ans;
			} else {
				accessionNumbers = accessionNumbers + "|-|" + ans;
			}
		}
		return accessionNumbers;
	}
}
