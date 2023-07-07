/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Prestazione;
import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.GetDettaglioDocumentoRequest;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.GetDettaglioDocumentoResponse;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.PazienteDao;
import it.csi.dma.apiopsan.integration.dao.custom.ServizioComponenteLocaleDao;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.GetDettaglioDocumentoCL;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.StatoDocumento;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class DettaglioPrestazioniDoc extends LoggerUtil {

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
	private PazienteDao pazienteDao;
	
	@Autowired 
	private StatoDocumento statoDocumento;
	
	@Autowired
	private ServizioComponenteLocaleDao servizioComponenteLocaleDao;
	
	@Autowired
	private GetDettaglioDocumentoCL getDettaglioDocumentoCL;

	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo, String regime, String citId,
			Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		String methodName = "execute";
		ErrorBuilder error = null;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		// inserisco tabella messaggi e messaggi xml
		Long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, null, securityContext, httpHeaders, httpRequest, uuidAsString,
				Constants.GET_DETTAGLIO_PRESTAZIONI, citId);
		// inserisco tabella dei log
		@SuppressWarnings("unused")
		long idlog = codRLogErroreService.traceLogInsert(Constants.GET_DETTAGLIO_PRESTAZIONI, uuidAsString,
				Constants.CITTADINI_API_SERVICE, null);

		try {
			// ricerca ruolo FSE
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
				ruoloFse = ruoloF.getCodiceRuoloFse();

			List<ErroreDettaglioExt> listError = validateGeneric.validateDettaglioPrestazioniDoc(
					shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,
					ruoloFse, regime, idDocumentoIlec, citId, codiceComponenteLocale, securityContext, httpHeaders,
					httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore durante la validazione");
			}

			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
			
			//recupero idPaziente // se non trovato: 0
			long idPaziente = pazienteDao.selectIdPazientePerCodiceFiscale(citId);			
			// verificare presenza del documento per idDocumentoIlec, codiceCl e citId
			DmaccidxTDocumento tDocumento = statoDocumento.verificaEsistenzaDocumento(listError, idDocumentoIlec, codiceComponenteLocale, idPaziente);
	        
			// Verifica oscuramento: se oscurato e chiamante non e' refertante, torna errore        
			if ( statoDocumento.isOscurato(idDocumentoIlec, codiceComponenteLocale, citId)
					&& !statoDocumento.isMedicoRefertante(shibIdentitaCodiceFiscale, idDocumentoIlec, codiceComponenteLocale)) {
				listerrorservice.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_195.getCode()));
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listerrorservice),
						"Verifica oscuramento: Documento non trovato");
			}
	
			//determino url della cl da chiamare per recuperare prestazioni
			ServizioComponenteLocale servizioComponenteLocale = servizioComponenteLocaleDao.selectServizioByClAndServizioAndOperazione(
							codiceComponenteLocale, 
							Constants.GET_DETTAGLIO_PRESTAZIONI_SERVIZIO_CL, 
							Constants.GET_DETTAGLIO_PRESTAZIONI_OPERAZIONE_CL);

			
			//chiamata al servizio getDettaglioDocumento in cl che ritorna le prestazioni
			GetDettaglioDocumentoRequest getDettaglioDocumentoReq = getDettaglioDocumentoCL.buildRequest(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
					xCodiceServizio, xCodiceVerticale, 
					ruoloFse, 
					citId, idDocumentoIlec, tDocumento.getCodTipoDocumento());
			GetDettaglioDocumentoResponse getDettaglioDocumentoRes = getDettaglioDocumentoCL.getDettaglioDocumento(getDettaglioDocumentoReq, servizioComponenteLocale);
			getDettaglioDocumentoCL.verificaResponse(getDettaglioDocumentoRes);
			List<Prestazione> prestazioni = estraiPrestazioni(getDettaglioDocumentoRes);

			
			return Response.status(200).entity(prestazioni).build();

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
		codRLogErroreService.traceLogInsert(Constants.GET_DETTAGLIO_PRESTAZIONI, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);

		return esito;
	}

	private List<Prestazione> estraiPrestazioni(GetDettaglioDocumentoResponse getDettaglioDocumentoRes) {
		List<Prestazione> prestazioni = new ArrayList<Prestazione>();
		if (getDettaglioDocumentoRes != null
				&& getDettaglioDocumentoRes.getPrestazioni() != null
				&& getDettaglioDocumentoRes.getPrestazioni().getPrestazioneSOLList() != null
				&& !getDettaglioDocumentoRes.getPrestazioni().getPrestazioneSOLList().isEmpty()) {
			for (it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.Prestazione pIn: getDettaglioDocumentoRes.getPrestazioni().getPrestazioneSOLList()) {
				Prestazione pOut = new Prestazione();
				pOut.setId(pIn.getIdPrestazione());
				pOut.setCodice(pIn.getCodicePrestazione());
				pOut.setDescrizione(pIn.getDescrizionePrestazione());
				if (pIn.getBrancaSOL() != null) {
					Codice codice = new Codice(pIn.getBrancaSOL().getCodice(),pIn.getBrancaSOL().getDescrizione());
					pOut.setBranca(codice);
				}
				if (pIn.getDataOra() != null) {
					GregorianCalendar gc = pIn.getDataOra().toGregorianCalendar();
					if (gc != null) {
						pOut.setDataPrestazione(gc.getTime());
					}
				}
				prestazioni.add(pOut);
			}	
		}
		if (getDettaglioDocumentoRes != null
				&& getDettaglioDocumentoRes.getInfoAggiuntive() != null
				&& !getDettaglioDocumentoRes.getInfoAggiuntive().isEmpty()) {
			for (it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.InfoAggiuntive infoIn: getDettaglioDocumentoRes.getInfoAggiuntive()) {
				Prestazione pOut = new Prestazione();
				pOut.setCodice(infoIn.getCodice());
				pOut.setDescrizione(infoIn.getDescrizione());
				prestazioni.add(pOut);
			}
		}
		return prestazioni;
	}
	

}
