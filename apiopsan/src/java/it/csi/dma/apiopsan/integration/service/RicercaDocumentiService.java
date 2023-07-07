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

import it.csi.dma.apiopsan.ricercadocumentiiniservice.CfAssistitoType;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.ElencoCFAssistitoType;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiIN;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiINIService;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiINIService_Service;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiRequeste;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.RicercaDocumentiResponse;
import it.csi.dma.apiopsan.ricercadocumentiiniservice.Richiedente;
import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class RicercaDocumentiService extends LoggerUtil {

	private static final QName SERVICE_NAME_RICERCA_DOC = new QName("http://dmacc.csi.it/",
			"RicercaDocumentiINIService");

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	public RicercaDocumentiResponse getDocumenti(String shibIdentitaCodiceFiscale, String xRequestId,
			String xCodiceServizio, String citId, String ruoloFse, String regime, PayloadSearchTuttiDoc payload) {

		URL wsdlURL;

		try {
			String ricercaDocumentiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.RICERCA_DOCUMENTI_SERVICE) + "?wsdl";
			wsdlURL = new URL(ricercaDocumentiServiceUrl);
			RicercaDocumentiINIService_Service cs = new RicercaDocumentiINIService_Service(wsdlURL,
					SERVICE_NAME_RICERCA_DOC);
			RicercaDocumentiINIService port = cs.getRicercaDocumentiINIServicePort();

			RicercaDocumentiRequeste req = createRicercaDocumentiRequest(shibIdentitaCodiceFiscale, ruoloFse, citId,
					xRequestId, xCodiceServizio, regime, payload);

			RicercaDocumentiResponse ricercaDocumentiResponse = port.ricercaDocumenti(req);

			return ricercaDocumentiResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.RICERCA_DOCUMENTI_SERVICE);
		}
	}

	protected RicercaDocumentiRequeste createRicercaDocumentiRequest(String shibIdentitaCodiceFiscale, String ruoloFse,
			String citId, String xRequestId, String xCodiceServizio, String regime, PayloadSearchTuttiDoc payload)
			throws DatabaseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String ruoloINI = dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(ruoloFse).getCodiceRuoloIni();

		RicercaDocumentiRequeste req = new RicercaDocumentiRequeste();
		RicercaDocumentiIN in = new RicercaDocumentiIN();

		in.setContestoOperativo(Constants.RICERCA_DOC_CONTESTO_OP);
		in.setIdentificativoAssistito(citId);

		// composizione dell'elenco assistito con il codice fiscale da ricercare
		ElencoCFAssistitoType elenco = new ElencoCFAssistitoType();
		CfAssistitoType assistito = new CfAssistitoType();
		assistito.setAttivo("S");
		assistito.setCf(citId);
		elenco.getCfAssistito().add(assistito);
		in.setElencoCFAssistito(elenco);

		in.setIdentificativoOrganizzazione(Constants.RICERCA_DOC_ID_ORG);
		in.setStrutturaUtente(Constants.RICERCA_DOC_STRUT_UTENTE);
		in.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		in.setPresaInCarico(Constants.TRUE.toLowerCase());
		in.setStatoDocumento(Constants.RICERCA_DOC_STATO_DOC);
		in.setTipoAttivita(Constants.RICERCA_DOC_TIPO_ATTIVITA);
		in.setRuoloUtente(ruoloINI);

		// impostazione filtro di ricerca
		if (payload.getFiltroDocs() != null && payload.getFiltroDocs().getDataInizio() != null) {
			in.setDataRicercaDA(formatter.format(payload.getFiltroDocs().getDataInizio()));
		}
		if (payload.getFiltroDocs() != null && payload.getFiltroDocs().getDataFine() != null) {
			in.setDataRicercaA(formatter.format(payload.getFiltroDocs().getDataFine()));
		}

		for (CategoriaTipologia tipo : payload.getCategoriaTipologia()) {
			if (tipo.getCategoria().equals(Constants.FSE) && tipo.getTipologia() != null)
				in.getTipologiaDocumento().add(tipo.getTipologia());
		}

		Richiedente richiedente = new Richiedente();

		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);

		req.setRicercaDocumentiIN(in);
		req.setRichiedente(richiedente);

		return req;

	}
}
