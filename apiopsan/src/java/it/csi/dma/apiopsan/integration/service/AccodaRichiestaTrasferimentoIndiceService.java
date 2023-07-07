/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.interceptor.SoapInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.Paziente;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.Richiedente;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndice;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndiceResponse;
import it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndiceService_Service;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class AccodaRichiestaTrasferimentoIndiceService extends LoggerUtil {

	private static final QName SERVICE_NAME_ACCODATRASF_INDICE = new QName("http://dmacc.csi.it/",
			"AccodaRichiesteTrasferimentoIndiceService");

	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	public AccodaRichiesteTrasferimentoIndiceResponse accodaTrasferimentoIndice(String shibIdentitaCodiceFiscale, String xRequestId,
			String xCodiceServizio, String citId, String ruoloFse, String regime) {

		URL wsdlURL;

		try {
			String consensiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.ACCODATRASFINDICE_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(consensiServiceUrl);
			AccodaRichiesteTrasferimentoIndiceService_Service cs = new AccodaRichiesteTrasferimentoIndiceService_Service(wsdlURL,
					SERVICE_NAME_ACCODATRASF_INDICE);
			it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc.AccodaRichiesteTrasferimentoIndiceService port = cs.getAccodaRichiesteTrasferimentoIndiceServicePort();
			
			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();

			AccodaRichiesteTrasferimentoIndice req = accodaRichiestaTrasdfrimentoIndice(shibIdentitaCodiceFiscale, ruoloFse, citId,
					xRequestId, xCodiceServizio, regime);

			AccodaRichiesteTrasferimentoIndiceResponse accodaIndiceResponse = port.accodaRichiesteTrasferimentoIndice(req);

			return accodaIndiceResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.ACCODA_SERVICE);
		}
	}

	protected AccodaRichiesteTrasferimentoIndice accodaRichiestaTrasdfrimentoIndice(String shibIdentitaCodiceFiscale, String ruoloFse,
			String citId, String xRequestId, String xCodiceServizio, String regime)
			throws DatabaseException {

		AccodaRichiesteTrasferimentoIndice req = new AccodaRichiesteTrasferimentoIndice();
		Paziente paziente = new Paziente();
		Richiedente richiedente = new Richiedente();
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		RuoloDMA ruolo = new RuoloDMA();
		ruolo.setCodice(ruoloFse);
		
		RegimeDMA regimedma = new RegimeDMA();
		regimedma.setCodice(regime);
	
		ap.setCodice(xCodiceServizio);
		paziente.setCodiceFiscale(citId);
		
		richiedente.setNumeroTransazione(xRequestId);
		richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
		richiedente.setTokenOperazione(xRequestId);
		richiedente.setRuolo(ruolo);
		richiedente.setRegime(regimedma);
		
    	req.setPaziente(paziente);
		req.setRichiedente(richiedente);

		return req;

	}
	
}
