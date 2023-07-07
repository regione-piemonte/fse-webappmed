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
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.StatoDocumento;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class DettaglioDoc extends LoggerUtil {

	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;

	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;

	@Autowired
	CodRLogErroreService codRLogErroreService;

	@Autowired
	CodLMessaggiService codLMessaggiService;

	@Autowired
	CatalogoServiziDao catalogoServiziDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	@Autowired
	DmaccIdxDao dmaccIdxDao;

	@Autowired
	StatoConsensiService statoConsensiSerivce;
	
	@Autowired 
	private StatoDocumento statoDocumento;

	private final int DEFAULT_ERRORE_ID = 1;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale, String categoria, String tipoMedico,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = "execute";
		ErrorBuilder error = null;
		// genero uid
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		// inserisco tabella messaggi e messaggi xml
		long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
				uuidAsString, Constants.GET_DETTAGLIO_DOC, citId);
		// inserisco tabella dei log
		long idlog = codRLogErroreService.traceLogInsert(Constants.GET_DETTAGLIO_DOC, uuidAsString,
				Constants.CITTADINI_API_SERVICE, citId);
		try {
			// cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
				ruoloFse = ruoloF.getCodiceRuoloFse();
			// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateDettaglioDoc(shibIdentitaCodiceFiscale,
					xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione,
					citId, idDocumentoIlec, codiceComponenteLocale, categoria, securityContext, httpHeaders,
					httpRequest);
 
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore in validate");
			}
			
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();

			StatoConsensiResponse r = statoConsensiSerivce.getStatoConsensi(shibIdentitaCodiceFiscale, xRequestId,
					xCodiceServizio, citId, ruoloFse, regime);

			if (r.getEsito() == null) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"errore in verifica stato consensi - servizio non disponibile ");

			}
			
			if (r.getEsito() == RisultatoCodice.SUCCESSO) {

				StatoConsenso result = new StatoConsenso();
				result.setIdentificativoAssistitoConsenso(r.getStatoConsensiOUT().getIdentificativoAssistitoConsenso());
				result.setIdentificativoInformativaConsensi(
						r.getStatoConsensiOUT().getIdentificativoInformativaConsensi());
				result.setIdentificativoInformativaCorrente(
						r.getStatoConsensiOUT().getIdentificativoInformativaCorrente());
				result.setConsensoAlimentazione(
						Util.stringToBoolean(r.getStatoConsensiOUT().getConsensoAlimentazione(), Constants.TRUE));
				result.setConsensoConsultazione(
						Util.stringToBoolean(r.getStatoConsensiOUT().getConsensoConsultazione(), Constants.TRUE));
				result.setConsensoPregresso(
						Util.stringToBoolean(r.getStatoConsensiOUT().getConsensoPregresso(), Constants.TRUE));
				
				
				if (!result.getIdentificativoInformativaCorrente().startsWith(Constants.IDENTIFICATIVO_ORGANIZZAZIONE)){
					listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_568.getCode()));
					throw new ResponseErrorException(
							ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in GetDettaglioDoc paziente fuori regione");
				}
				else if (!result.isConsensoConsultazione()) {
					listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));
					throw new ResponseErrorException(
							ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in GetDettaglioDoc paziente piemontese con consenso false");
				}
					
				

			} else {
				List<Errore> wsErrors = r.getErrori();
				for (Errore wsError : wsErrors) {
					ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
					erroreDettaglio.setChiave(wsError.getCodice());
					erroreDettaglio.setValore(wsError.getDescrizione());
					erroreDettaglio.setErroreId(DEFAULT_ERRORE_ID);
					listError.add(erroreDettaglio);
				}

				error = ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError);
				Response esito = error.generateResponseError();
				return esito;
			}
			
			
			
			SintesiDocumento documento =  dmaccIdxDao.selectDettaglioDoc(shibIdentitaCodiceFiscale, categoria, tipoMedico, citId, idDocumentoIlec, codiceComponenteLocale);
			if (documento == null
					|| (!categoria.equalsIgnoreCase(Constants.PERSONALE)
							&& !statoDocumento.isMedicoRefertante(shibIdentitaCodiceFiscale, idDocumentoIlec, codiceComponenteLocale)
							&& statoDocumento.isOscurato(idDocumentoIlec, codiceComponenteLocale, citId))) {
				listerrorservice.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_195.getCode()));
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),"errore in GetDettaglioDoc Documento non trovato");

			}
			// inserimento nel log esito positivo
			codRLogErroreService.traceLogInsert(Constants.GET_DETTAGLIO_DOC, uuidAsString,
					Constants.CITTADINI_API_SERVICE, Constants.ESITO_SUCCESSO);
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, documento.toString(),
					Constants.ESITO_SUCCESSO);
			// popolare la tabella di audit solo se esito positivo
			String[] array = new String[2];
			array[0] = idDocumentoIlec.toString();
			array[1] = regime;
			codRLogErroreService.traceLogAuditInsert(Constants.GET_DETTAGLIO_DOC, uuidAsString,
					documento.toString(), Constants.CODICE_SUCCESSO_GET_DETTAGLIO_DOC, shibIdentitaCodiceFiscale,
					collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime,
					citId,array,idDocumentoIlec,codiceComponenteLocale);

			return Response.status(200).entity(documento).build();

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
		codRLogErroreService.traceLogInsert(Constants.GET_DETTAGLIO_DOC, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);
		return esito;

	}
}
