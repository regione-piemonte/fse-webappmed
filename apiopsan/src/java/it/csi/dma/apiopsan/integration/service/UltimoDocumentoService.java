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


import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.Paziente;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.Richiedente;
import it.csi.dma.apiopsan.ultimodocumentoservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.ultimodocumentoservice.dmacc.GetUltimoDocumentoRequest;
import it.csi.dma.apiopsan.ultimodocumentoservice.dmacc.GetUltimoDocumentoResponse;
import it.csi.dma.apiopsan.ultimodocumentoservice.dmaccbl.UltimoDocumentoService_Service;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class UltimoDocumentoService extends LoggerUtil {

	private static final QName SERVICE_NAME_ULTIMO_DOCUMENTO = new QName("http://dmaccbl.csi.it/",
			"UltimoDocumentoService");

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	public GetUltimoDocumentoResponse getUltimoDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
			String xCodiceServizio, String citId, String ruoloIni, String tipoDocumento,String regime) {

		URL wsdlURL;

		try {
			String ultimoDocumentoServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.ULTIMO_DOCUMENTO_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(ultimoDocumentoServiceUrl);
			UltimoDocumentoService_Service cs = new UltimoDocumentoService_Service(wsdlURL,
					SERVICE_NAME_ULTIMO_DOCUMENTO);
			it.csi.dma.apiopsan.ultimodocumentoservice.dmaccbl.UltimoDocumentoService port = cs.getUltimoDocumentoServicePort();

			GetUltimoDocumentoRequest req = createUltimoDocumentoRequest(shibIdentitaCodiceFiscale, xRequestId,
					xCodiceServizio, citId,ruoloIni, tipoDocumento,regime);

			GetUltimoDocumentoResponse ultimoDocumentoResponse = port.getUltimoDocumento(req);

			return ultimoDocumentoResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.ULTIMO_DOCUMENTO_PSS);
		}
	}

	protected GetUltimoDocumentoRequest createUltimoDocumentoRequest(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, 
			String citId,String ruoloIni,String tipoDocumento, String regime)
			throws DatabaseException {


		GetUltimoDocumentoRequest req = new GetUltimoDocumentoRequest();
		Paziente paziente = new Paziente();
		paziente.setCodiceFiscale(citId);
		
		ApplicazioneRichiedente applicazione = new ApplicazioneRichiedente();
		applicazione.setCodice(xCodiceServizio);
		
		RuoloDMA ruolo = new RuoloDMA();
		ruolo.setCodice(ruoloIni);
		
		RegimeDMA regimedma = new RegimeDMA();
		regimedma.setCodice(regime);
		
		Richiedente richiedente = new Richiedente();
		richiedente.setApplicazione(applicazione);
		richiedente.setRegime(regimedma);
		richiedente.setRuolo(ruolo);
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);

		req.setPaziente(paziente);
		req.setRichiedente(richiedente);
		req.setTipoDocumento(tipoDocumento);

		return req;

	}
}
