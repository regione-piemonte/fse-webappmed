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

import it.csi.dma.apiopsan.dto.custom.DmaccTDocumentiQrcode;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccIdxDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.helper.impl.getDocumento.StatoDocumento;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.integration.service.ScaricoRefertoServiceApisan;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Documento;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Errore;
import it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl.ScansionaRefertoQRCodeResponse;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;

@Service
public class ScansionaQRCode extends LoggerUtil {
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
	ScaricoRefertoServiceApisan scaricoRefertoService;
	
	@Autowired
	DmaccIdxDao dmaccIdxDao;
	
	@Autowired 
	private StatoDocumento statoDocumento;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String regime, String collocazione,
			String qrcode,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_SCANSIONAQRCODE, null);
	   
	    
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_SCANSIONAQRCODE, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateScansionaQRCode(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, qrcode,
					securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			
			//recupera metadati documento da qrcode (serve per oscuramenti e deleghe)
			DmaccTDocumentiQrcode documentoQrcode = statoDocumento.metadatiDocumentoDaQrcode(qrcode);
			String citId = documentoQrcode.getCodiceFiscale();
			String codCl = documentoQrcode.getCodCl();
			Long idDocumento = documentoQrcode.getIdDocumento();
			
			//verifica oscuramento
			verificaOscuramento(listError, citId, codCl, idDocumento);
			
			//chiamo il servizio CC ScaricoRefertoService scansionaRefertoQRCode
			//compongo la chiamata
			ScansionaRefertoQRCodeResponse response = scaricoRefertoService.scansionaRefertoQRCode(shibIdentitaCodiceFiscale, xRequestId,
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo, ruoloFse, regime, collocazione, qrcode);	
			
			List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
			if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				//errore
				for (Errore errore : response.getErrori()) {
					ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
					errorservice.setChiave(errore.getCodice());
					errorservice.setValore(errore.getDescrizione());
					errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
					listerrorservice.add(errorservice);
				}
				
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in service scansionaRefertoQrCode");
			}
			else {
//					EsitoScansionaQRCode esito = new EsitoScansionaQRCode();
					Documento documento = response.getDocumento();
					//inserimento nel log esito positivo
					//inserimento nel log esito positivo
					//bloccato non ci troviamo con analisi
					codRLogErroreService.traceLogInsert(Constants.GET_SCANSIONAQRCODE, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
					codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, response.getEsito().toString(),Constants.ESITO_SUCCESSO);
					String[] array = new String[1];
					array[0] = documento.getCodiceDocumentoDipartimentale();
					codRLogErroreService.traceLogAuditInsert(Constants.GET_SCANSIONAQRCODE, uuidAsString, response.toString(), 
							Constants.CODICE_SUCCESSO_SCANSIONAQRCODE, shibIdentitaCodiceFiscale, 
							collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, null,array,null,null);
					return Response.status(200).entity(documento.getDocumentoStream()).build();
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
		codRLogErroreService.traceLogInsert(Constants.GET_SCANSIONAQRCODE, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}

	private void verificaOscuramento(List<ErroreDettaglioExt> listError, String citId, String codCl, Long idDocumento)
			throws DatabaseException, ResponseErrorException {
		//verifica oscuramento
		if (statoDocumento.isOscurato(idDocumento, codCl, citId)) {
			listError.add(validateMedicGeneric.getValueGenericErrorNoParam(CodeErrorEnum.CC_ER_195.getCode()));
			throw new ResponseErrorException(
					ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
					"Verifica oscuramento: Documento non trovato o oscurato");	
		}
	}

}


