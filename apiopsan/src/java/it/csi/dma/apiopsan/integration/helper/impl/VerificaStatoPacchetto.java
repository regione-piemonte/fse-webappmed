/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.RisultatoCodice;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.ScaricoStudiWSBean;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.VerificaStatoRichiesta2Request;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.VerificaStatoRichiesta2Response;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class VerificaStatoPacchetto extends LoggerUtil {

	@Autowired
	ScaricoStudiWSBean scaricoStudiWSBean;

	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	CodLMessaggiService codLMessaggiService;
	
	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;	

	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String codiceFiscale, String idDocumentoIlec, String codCl, String codDocumentoDipartimentale,
			String archivioDocumentoIlec, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		final String methodName = "execute";
		ErrorBuilder error = null;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		long id_xml = 0;
		try {
			
			String citId = !StringUtils.isEmpty( codiceFiscale) ? codiceFiscale: "";
			
		    id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
		    		xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, 
		    		httpHeaders, httpRequest, uuidAsString, Constants.VERIFICA_STATO_PACCHETTO,citId);
		    
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null) {
				ruoloFse = ruoloF.getCodiceRuoloFse();
			}

			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateVerificaStatoPacchetto(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
					xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, codiceFiscale,
					idDocumentoIlec, codCl);
			
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore in validate");
			}

			
			VerificaStatoRichiesta2Response res = scaricoStudiWSBean
					.verificaStatoRichiesta2(newVerificaStatoRichiesta2Request(codiceFiscale, idDocumentoIlec, codCl,
							codDocumentoDipartimentale, archivioDocumentoIlec));
			

			if (res == null || RisultatoCodice.FALLIMENTO.value().equals(res.getEsito())) {

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
			//mappo la risponsta
			it.csi.dma.apiopsan.dto.VerificaStatoPacchetto response = newVerificaStatoPacchetto(res);
			return Response.ok(response).build();
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
		codRLogErroreService.traceLogInsert(Constants.SCARICA_PACCHETTO_IMR, uuidAsString, Constants.PRENOTA_PACCHETTO_IMMAGINI_SERVICE,Constants.ESITO_FALLIMENTO);
		*/
		
		Response esito = error.generateResponseError();
		if(id_xml != 0) {
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, esito.getEntity().toString(),Constants.ESITO_FALLIMENTO);
		}
		return esito;
	}
	
	private it.csi.dma.apiopsan.dto.VerificaStatoPacchetto newVerificaStatoPacchetto(VerificaStatoRichiesta2Response res){
		it.csi.dma.apiopsan.dto.VerificaStatoPacchetto pacchetto = new it.csi.dma.apiopsan.dto.VerificaStatoPacchetto();
		pacchetto.setArvchivioDocumentoIlec(res.getArvchivioDocumentoIlec());
		pacchetto.setChecksum(res.getChecksum());
		pacchetto.setCodDocumentoDipartimentale(res.getCodDocumentoDipartimentale());
		pacchetto.setDirectory(res.getDirectory());
		pacchetto.setIdDocumentoIlec(new BigDecimal(res.getIdDocumentoIlec()));
		pacchetto.setStatoRichiesta(res.getStatoRichiesta());
		pacchetto.setZipName(res.getZipName());
		return pacchetto;
	}
	
	private VerificaStatoRichiesta2Request newVerificaStatoRichiesta2Request(String codiceFiscale,
			String idDocumentoIlec, String codCl, String codDocumentoDipartimentale, String archivioDocumentoIlec) {
		VerificaStatoRichiesta2Request request = new VerificaStatoRichiesta2Request();

		if (!StringUtils.isEmpty(archivioDocumentoIlec)) {
			request.setArvchivioDocumentoIlec(archivioDocumentoIlec);
		}
		if (!StringUtils.isEmpty(codCl)) {
			request.setCodCL(codCl);
		}
		if (!StringUtils.isEmpty(codDocumentoDipartimentale)) {
			request.setCodDocumentoDipartimentale(codDocumentoDipartimentale);
		}
		request.setCodiceFiscale(codiceFiscale);
		if (!StringUtils.isEmpty(idDocumentoIlec)) {
			request.setIdDocumentoIlec(Long.parseLong(idDocumentoIlec));
		}

		return request;
	}
}
