/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.net.URL;
import java.text.SimpleDateFormat;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.recuperadocumentoservice.CfAssistitoType;
import it.csi.dma.apiopsan.recuperadocumentoservice.ElencoCFAssistitoType;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoIN;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoINIService;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoINIService_Service;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoRequeste;
import it.csi.dma.apiopsan.recuperadocumentoservice.RecuperoDocumentoResponse;
import it.csi.dma.apiopsan.recuperadocumentoservice.Richiedente;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class RecuperoDocumentoService extends LoggerUtil {

	private static final QName SERVICE_NAME_RECUPERA_DOC = new QName("http://dmacc.csi.it/",
			"RecuperoDocumentoINIService");

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	public RecuperoDocumentoResponse getRecuperoDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
			String xCodiceServizio, String citId, String ruoloIni, PayloadRecuperaDocumento payload) {

		URL wsdlURL;

		try {
			String ricercaDocumentiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.RECUPERA_DOCUMENTI_SERVICE_PO) + "?wsdl";
			wsdlURL = new URL(ricercaDocumentiServiceUrl);
			RecuperoDocumentoINIService_Service cs = new RecuperoDocumentoINIService_Service(wsdlURL,
					SERVICE_NAME_RECUPERA_DOC);
			RecuperoDocumentoINIService port = cs.getRecuperoDocumentoINIServicePort();

			RecuperoDocumentoRequeste req = createRecuperoDocumentoRequest(shibIdentitaCodiceFiscale, xRequestId,
					xCodiceServizio, citId,ruoloIni, payload);

			RecuperoDocumentoResponse recuperoDocumentoResponse = port.recuperoDocumento(req);

			return recuperoDocumentoResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.RECUPERA_DOCUMENTI_SERVICE_PO);
		}
	}

	protected RecuperoDocumentoRequeste createRecuperoDocumentoRequest(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, 
			String citId,String ruoloIni,PayloadRecuperaDocumento payload)
			throws DatabaseException {


		RecuperoDocumentoRequeste req = new RecuperoDocumentoRequeste();
		RecuperoDocumentoIN in = new RecuperoDocumentoIN();

//		// composizione dell'elenco assistito con il codice fiscale da ricercare
//		ElencoCFAssistitoType elenco = new ElencoCFAssistitoType();
//		CfAssistitoType assistito = new CfAssistitoType();
//		assistito.setAttivo("S");
//		assistito.setCf(citId);
//		elenco.getCfAssistito().add(assistito);
//		in.setElencoCFAssistito(elenco);
		in.setContestoOperativo(payload.getContestoOperativo());
		in.setIdentificativoAssistito(citId);
		in.setIdentificativoOrganizzazione(payload.getIdentificativoOrganizzazione());
		in.setDescrizioneOrganizzazione(payload.getDescrizioneOrganizzazione());
		in.setStrutturaUtente(payload.getStrutturaUtente());
		in.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		in.setPresaInCarico(payload.getPresaInCarico().toLowerCase());
		in.setTipoAttivita(payload.getTipoAttivita());
		in.setRuoloUtente(ruoloIni);
		in.setTipoDocumento(payload.getTipoDocumento());
		in.setIdentificativoDocumento(payload.getIdentificativoDocumento());
		in.setIdentificativoOrgDoc(payload.getIdentificativoOrgDoc());
		in.setIdentificativoRepository(payload.getIdentificativoRepository());

		Richiedente richiedente = new Richiedente();

		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);

		req.setRecuperoDocumentoIN(in);
		req.setRichiedente(richiedente);

		return req;

	}
}
