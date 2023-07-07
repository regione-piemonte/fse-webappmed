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

import it.csi.dma.apiopsan.dto.TipoDocumentoCategoria;
import it.csi.dma.apiopsan.dto.TipoDocumento;
import it.csi.dma.apiopsan.dto.custom.Categoria;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.exception.ResponseErrorException;
import it.csi.dma.apiopsan.integration.dao.custom.TipoDocumentoCategoriaDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;

@Service
public class ElencoTipoDocumentoCategoria extends LoggerUtil {

	@Autowired
	ValidateGenericMeritWhitMedicoImpl validateMedicGeneric;

	@Autowired
	TipoDocumentoCategoriaDao tipoDocumentoCategoriaDao;

	@Autowired
	CodRMessaggioErroreService codRMessaggioErroreService;

	@Autowired
	CodRLogErroreService codRLogErroreService;

	@Autowired
	CodLMessaggiService codLMessaggiService;

	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		String methodName = "ElencoTipoDocumentoCategoria.execute";
		ErrorBuilder error = null;
		// genero uid
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		// inserisco tabella messaggi e messaggi xml
		long id_xml = codLMessaggiService.traceMessaggiInsert(shibIdentitaCodiceFiscale, xRequestId, xForwardedFor,
				xCodiceServizio, xCodiceVerticale, null, null, securityContext, httpHeaders, httpRequest, uuidAsString,
				Constants.GET_ELENCO_TIPO_DOCUMENTO_CATEGORIA, null);

		// inserisco tabella dei log
		codRLogErroreService.traceLogInsert(Constants.GET_ELENCO_TIPO_DOCUMENTO_CATEGORIA, uuidAsString,
				Constants.CATALOGHI_SERVICE, null);
		try {

			// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validateElencoTipoDocumentoCategoria(
					shibIdentitaCodiceFiscale, xRequestId, xForwardedFor, xCodiceServizio, xCodiceVerticale,
					securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),
						"errore in validate");
			}

			List<TipoDocumentoCategoria> list = new ArrayList<>();

			List<Categoria> categorie = tipoDocumentoCategoriaDao.selectAllCategorie();
			for (Categoria cat : categorie) {
				List<TipoDocumento> tipiDoc= tipoDocumentoCategoriaDao.selectTipoDocumentoByCategoria(cat.getCodiceCategoria());
				if(tipiDoc != null && tipiDoc.size() > 0) {
					TipoDocumentoCategoria tipoDocCat = new TipoDocumentoCategoria();
					tipoDocCat.setCodiceCategoria(cat.getCodiceCategoria());
					tipoDocCat.setDescrizioneCategoria(cat.getDescrizioneCategoria());				
					tipoDocCat.setTipiDocumento(tipiDoc);
					list.add(tipoDocCat);
				}
			}

			// inserimento nel log esito positivo
			codRLogErroreService.traceLogInsert(Constants.GET_ELENCO_TIPO_DOCUMENTO_CATEGORIA, uuidAsString,
					Constants.CATALOGHI_SERVICE, Constants.ESITO_SUCCESSO);
			codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, list.toString(), Constants.ESITO_SUCCESSO);

			return Response.status(200).entity(list).build();

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
		codRLogErroreService.traceLogInsert(Constants.GET_TIPODOCUMENTO, uuidAsString, Constants.CATALOGHI_SERVICE,
				Constants.ESITO_FALLIMENTO);

		return esito;

	}

}
