/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl.getDocumento;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.consensoextservice.Errore;
import it.csi.dma.apiopsan.consensoextservice.RisultatoCodice;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.dto.Documento;
import it.csi.dma.apiopsan.dto.EsitoIsDocumentoRestituibile;
import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.Ruolo;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl.RecuperoDocumentoIniRequest;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl.RecuperoDocumentoIniResponse;
import it.csi.dma.apiopsan.external.clepisodioservice.episodioservice.GetDocumentoReq;
import it.csi.dma.apiopsan.external.clepisodioservice.episodioservice.GetDocumentoRes;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.PazienteDao;
import it.csi.dma.apiopsan.integration.dao.custom.ServizioComponenteLocaleDao;
import it.csi.dma.apiopsan.integration.helper.impl.RestituibilitaDocumento;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.StatoConsensiService;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoRequeste;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoResponse;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiRequeste;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiResponse;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;



@Service
public class RecuperaDocumentoPdf extends LoggerUtil{
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
	@Autowired
	private CodLMessaggiService codLMessaggiService;
	
	@Autowired
	private CodRLogErroreService codRLogErroreService;
	
	@Autowired
	private CodRMessaggioErroreService codRMessaggioErroreService;
		
	@Autowired
	private DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	private PazienteDao pazienteDao;
	
	@Autowired
	private DmaccIdxDao dmaccIdxDao;
	
	@Autowired
	private ServizioComponenteLocaleDao servizioComponenteLocaleDao;

	@Autowired
	private StatoConsensiService statoConsensiService;
	
	@Autowired 
	private RecuperoDocumentoINI recuperoDocumentoINI;
	
	@Autowired
	private RicercaDocumentiINI ricercaDocumentiINI;
	
	@Autowired 
	private StatoDocumento statoDocumento;
	
	@Autowired
	private GetDocumentoCL getDocumentoCL;
	
	@Autowired 
	private RecuperoDocumentiIniCL recuperoDocumentiIniCL;
	
	@Autowired
	@Qualifier("ValidateGenericMeritWhitMedicoImpl")
	private ValidateGenericMeritWhitMedicoImpl validateGeneric;
	
	@Autowired
	private RestituibilitaDocumento restituibilitaDocumento;
	
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, 
			String ruolo, String regime, String collocazione,
			String citId, PayloadGetDocumento payloadGetDocumento,
			SecurityContext securityContext, 
			HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		
		String methodName = "execute";
		ErrorBuilder error = null;
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		//log metadati su dmacc_lmessaggi
		long idXml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, ruolo, collocazione, securityContext, httpHeaders, httpRequest,
				uuidAsString, Constants.GET_DOCUMENTO, citId);		
		//log su dmacc_t_log
		long idlog = codRLogErroreService.traceLogInsert(Constants.GET_DOCUMENTO, uuidAsString,
				Constants.CITTADINI_API_SERVICE, null);
		
		try {


			//recupero il ruolo FSE, da passare nei controlli formali e servira' in seguito
			RuoloPua ruoloPua = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo, xCodiceServizio);
			String ruoloFse = null;
			if (ruoloPua != null)
				ruoloFse = ruoloPua.getCodiceRuoloFse();


			//validazioni
			logInfo(methodName,"Validazione GetDocumento");
			List<ErroreDettaglioExt> listError = validateGeneric.validateGetDocumento( shibIdentitaCodiceFiscale,  
					xRequestId,  xForwardedFor, xCodiceServizio,  xCodiceVerticale, 
					ruolo, ruoloFse, regime,  collocazione,
					citId,  payloadGetDocumento,
					securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				logInfo(methodName,"Validazione NON superata");
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore durante la validazione");
			}
			Long idDocumentoIlec = payloadGetDocumento.getIdDocumentoIlec();
			String codiceComponenteLocale = payloadGetDocumento.getCodiceComponenteLocale();
			String firmato = payloadGetDocumento.getFirmato();
			String identificativoRepositoryINI = payloadGetDocumento.getIdentificativoRepository();
			String codiceDocumentoINI = payloadGetDocumento.getCodiceDocumento();

			//recupero idPaziente // se non trovato: 0
			long idPaziente = pazienteDao.selectIdPazientePerCodiceFiscale(citId);

			//controllo se richiedente e' anche refertante, serve poi per stato consenso e oscuramento
			boolean medicoRefertante = statoDocumento.isMedicoRefertante(shibIdentitaCodiceFiscale, idDocumentoIlec, codiceComponenteLocale);
			
			//PO-StatoConsenso
			logInfo(methodName,"chiamata PO-StatoConsenso");
			StatoConsensiResponse statoConsensiResponse = statoConsensiService.getStatoConsensi(shibIdentitaCodiceFiscale,xRequestId,xCodiceServizio,citId,ruoloFse,regime);
			
			verificaStatoConsensiResponse(listError, statoConsensiResponse);
			
			if (!statoConsensiService.isConsensoConsultazione(statoConsensiResponse)
					&& !medicoRefertante) {
				listError.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.FSE_ER_505.getCode()));
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listError),"verifica stato consensi: consensi non forniti");
			}
			
			//verifica esistenza documento
			DmaccidxTDocumento tDocumento = new DmaccidxTDocumento();
			if (! statoConsensiService.isFuoriRegione(statoConsensiResponse)) {
				//paziente piemontese, devo recuperare i metadati documento
				logInfo(methodName,"Verifica esistenza documento");
				tDocumento = statoDocumento.verificaEsistenzaDocumento(listError, idDocumentoIlec, codiceComponenteLocale, idPaziente);
				if (StringUtils.isEmpty(codiceDocumentoINI)) {
					codiceDocumentoINI = tDocumento.getCodiceDocumentoDipartimentale().replaceAll("\\.(?!.*(\\.).*)", "^");  //un punto non seguito da una stringa contente un punto
				}
				if (StringUtils.isEmpty(identificativoRepositoryINI)) {
					identificativoRepositoryINI = tDocumento.getIdRepositoryCl();
				}
			}
			
			//Recupero ruoloIni, serve per la chiamate INI
			Ruolo ruoloxini = dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(ruoloFse);
			String ruoloIni = null;
			if (ruoloxini != null)
				ruoloIni = ruoloxini.getCodiceRuoloIni();
			byte[] docPdf = null;
			
			//PO ricercaDocumenti INI
			logInfo(methodName,"chiamata PO ricercaDocumenti INI");
			RicercaDocumentiRequeste ricercaDocumentiReq = ricercaDocumentiINI.buildRequestForGetDocumento(shibIdentitaCodiceFiscale, ruoloIni, 
					citId, xRequestId, xCodiceServizio, regime, 
					idPaziente, codiceDocumentoINI, identificativoRepositoryINI);

			RicercaDocumentiResponse ricercaDocumentiResponse = ricercaDocumentiINI.getDocumenti(ricercaDocumentiReq);
			ricercaDocumentiINI.verificaRicercaDocumentiResponse(ricercaDocumentiResponse);
			
			//PO recuperoDocumento INI
			logInfo(methodName,"chiamata PO recuperoDocumento INI");
			RecuperoDocumentoRequeste recuperoDocumentoReq = recuperoDocumentoINI.buildRequestForGetDocumento(shibIdentitaCodiceFiscale, ruoloIni,
					citId, xRequestId, xCodiceServizio, regime, 
					codiceDocumentoINI, identificativoRepositoryINI);

			RecuperoDocumentoResponse recuperoDocumentoResponse = recuperoDocumentoINI.getRecuperoDocumento(recuperoDocumentoReq);
			recuperoDocumentoINI.verificaRecuperoDocumentoResponse(recuperoDocumentoResponse);
			//preleva documento
			docPdf = recuperoDocumentoINI.getPdf(recuperoDocumentoResponse);
			

			//verifica applicativo chiamante
			if (! Constants.CODICE_CONTATTO_DIGITALE.equals(xCodiceServizio) ) {
				//non sta chiamando contatto digitale - sta chiamando pic o altro - flusso dx
				
				//verifiche su oscuramento/medico refertante solo per pazienti piemontesi
				if (! statoConsensiService.isFuoriRegione(statoConsensiResponse)) {
					boolean oscurato = statoDocumento.isOscurato(idDocumentoIlec,codiceComponenteLocale, citId);
					if  (docPdf != null  &&  oscurato) {    
						//documento da INI, oscurato
						generaErroreOscuramento();
					}    		
				}
			}
			if (docPdf == null) {
				//tentativo sulla componente locale
				logInfo(methodName,"recupero documento dalla componente locale");
				
				if (! statoConsensiService.isFuoriRegione(statoConsensiResponse)) {
					//assistito piemontese, recupero documento da cl con id_documento_ilec e id_episodio tramite CLEpisodioService
					
					//se arrivato qua, deve esistere il doc in locale
					if (tDocumento.getIdDocumentoIlec() == null || tDocumento.getIdEpisodioIlec() == null) {
						statoDocumento.generaErroreDocumentoNonTrovato(listError);
					}
					
					//verifica restituibilita' documento
					Response restituibilita = restituibilitaDocumento.execute(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
							xCodiceServizio, xCodiceVerticale, ruolo, regime, collocazione, 
							citId, idDocumentoIlec, codiceComponenteLocale, securityContext,
							httpHeaders, httpRequest);
					verificaRestuibilita(restituibilita);
					
					//identifico servizio CL da richiamare
					ServizioComponenteLocale servizioComponenteLocale = servizioComponenteLocaleDao
							.selectServizioByClAndServizioAndOperazione(codiceComponenteLocale, Constants.CL_DOC_LOCALI_SERVIZIO, Constants.CL_DOC_LOCALI_OPERAZIONE);
	
					//recupero storico codici fiscali (in caso il paziente abbia cambiato cf)
					List<String> storicoCodiciFiscali = dmaccIdxDao.findStoricoCfByIdPaziente(idPaziente);
					
					//richiedo documento a CL
					GetDocumentoReq getDocumentoReq = getDocumentoCL.buildRequestForGetDocumento(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
							xCodiceServizio, xCodiceVerticale,
							ruoloFse, regime, collocazione,
							citId, firmato,tDocumento, storicoCodiciFiscali);
					GetDocumentoRes getDocumentoRes = getDocumentoCL.getDocumento(getDocumentoReq, servizioComponenteLocale);
					getDocumentoCL.verificaResponse(getDocumentoRes);
					docPdf = getDocumentoRes.getDocumento().getDocumento();
				} else {
					//assitito fuori regione, recupero documento da cl con codice_documento_dipartimentale tramite CLDocumentiINIService
					
					//recupero il codice cl da identificativo reposoitory
					codiceComponenteLocale = dmaccIdxDao.selectCodiceClByIdRepository(identificativoRepositoryINI);

					//identifico servizio CL da richiamare
					ServizioComponenteLocale servizioComponenteLocale = servizioComponenteLocaleDao
							.selectServizioByClAndServizioAndOperazione(codiceComponenteLocale, Constants.CL_DOC_INI_SERVIZIO, Constants.CL_DOC_INI_OPERAZIONE);					
					RecuperoDocumentoIniRequest recuperoDocumentoIniRequest =  recuperoDocumentiIniCL.buildRequestForGetDocumento(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, 
							xCodiceServizio, xCodiceVerticale,
							ruoloFse, regime, collocazione,
							citId, firmato, codiceDocumentoINI, identificativoRepositoryINI);
					RecuperoDocumentoIniResponse recuperoDocumentoIniResponse = recuperoDocumentiIniCL.getDocumento(recuperoDocumentoIniRequest, servizioComponenteLocale);
					recuperoDocumentiIniCL.verificaResponse(recuperoDocumentoIniResponse);
					docPdf = recuperoDocumentoIniResponse.getDocumento();					
				}	
			}
			Documento documentoResponse = new Documento();
			if (docPdf != null) {
				//risposta positiva
				//codificare in base64 e restituire dentro json
				if (! Util.isBase64Encoded(docPdf)) {
					docPdf = Base64.getEncoder().encode(docPdf);
				}
				documentoResponse.setAllegato(new String(docPdf));
				documentoResponse.setTipoAllegato(MediaType.APPLICATION_PDF_VALUE);

				// inserimento nel log esito positivo
				codRLogErroreService.traceLogInsert(Constants.GET_DOCUMENTO, uuidAsString, Constants.CITTADINI_API_SERVICE,
						Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(idXml, uuidAsString, codiceDocumentoINI, Constants.ESITO_SUCCESSO);
				String[] array = new String[1];
				array[0] = codiceDocumentoINI;
				codRLogErroreService.traceLogAuditInsert(Constants.GET_DOCUMENTO, uuidAsString, codiceDocumentoINI, 
						Constants.CODICE_SUCCESSO_GET_DOCUMENTO, shibIdentitaCodiceFiscale,  //TODO: verifica se codice successo va bene 
						collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,idDocumentoIlec, codiceComponenteLocale); 
				/*
			    return Response					
					//.ok((Object) docPdf)
					//.header("content-disposition", "attachment; filename = test.pdf")
					.ok(docPdf, MediaType.APPLICATION_PDF_VALUE)
					.header("content-disposition", buildHeaderFilename(citId,codiceDocumentoINI))
					.build();
				 */
			}
			return Response.status(200).entity(documentoResponse).build();

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
		codRLogErroreService.traceLogInsert(Constants.GET_DOCUMENTO, uuidAsString, Constants.CITTADINI_API_SERVICE,
				Constants.ESITO_FALLIMENTO);

		return esito;

		
	}

	private void verificaRestuibilita(Response restituibilita) throws ResponseErrorException, DatabaseException {
		List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
		if (restituibilita.getStatus() != Response.Status.OK.getStatusCode()) {
			it.csi.dma.apiopsan.dto.Errore erroreRestituibie = (it.csi.dma.apiopsan.dto.Errore) restituibilita.getEntity();
			ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
			errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
			errorservice.setChiave(erroreRestituibie.getDetail().get(0).getChiave());
			errorservice.setValore(erroreRestituibie.getDetail().get(0).getValore());
			listerrorservice.add(errorservice);
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in restuibilitaDocumento");
		}
		EsitoIsDocumentoRestituibile esitoRestituibile = (EsitoIsDocumentoRestituibile) restituibilita.getEntity();
		if (!esitoRestituibile.isRestituibile()) {
			statoDocumento.generaErroreDocumentoNonTrovato(listerrorservice);
		}	
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
	
	private void generaErroreOscuramento() throws ResponseErrorException,DatabaseException {
		List<ErroreDettaglioExt> listError = new ArrayList<ErroreDettaglioExt>();
		listError.add(validateGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_178.getCode()));
		
		throw new ResponseErrorException(
				ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
				"Documento Oscurato");
	}

}
