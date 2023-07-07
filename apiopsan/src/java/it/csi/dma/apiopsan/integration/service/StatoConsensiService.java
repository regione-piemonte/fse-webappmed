/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.consensoextservice.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.consensoextservice.ComunicazioneConsensiExtRequeste;
import it.csi.dma.apiopsan.consensoextservice.ComunicazioneConsensiIN;
import it.csi.dma.apiopsan.consensoextservice.ComunicazioneConsensiResponse;
import it.csi.dma.apiopsan.consensoextservice.ConsensoINIExtService;
import it.csi.dma.apiopsan.consensoextservice.ConsensoINIExtService_Service;
import it.csi.dma.apiopsan.consensoextservice.InformativaIN;
import it.csi.dma.apiopsan.consensoextservice.Paziente;
import it.csi.dma.apiopsan.consensoextservice.RecuperoInformativaExtRequeste;
import it.csi.dma.apiopsan.consensoextservice.RecuperoInformativaResponse;
import it.csi.dma.apiopsan.consensoextservice.RegimeDMA;
import it.csi.dma.apiopsan.consensoextservice.RichiedenteExt;
import it.csi.dma.apiopsan.consensoextservice.RisultatoCodice;
import it.csi.dma.apiopsan.consensoextservice.RuoloDMA;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiExtRequeste;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiIN;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiOUT;
import it.csi.dma.apiopsan.consensoextservice.StatoConsensiResponse;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaCacheDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;


@Service
public class StatoConsensiService extends LoggerUtil {
	private static final QName SERVICE_NAME_CONSENSO = new QName("http://dmacc.csi.it/", "ConsensoINIExtService");
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
	
	@Autowired
	DmaCacheDao dmaCacheDao;
												  
	public StatoConsensiResponse getStatoConsensi(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, String citId, String ruoloFse, String regime) {
		URL wsdlURL;
		try {
			StatoConsensiResponse statoConsensiResponse = new StatoConsensiResponse();
			StatoConsenso statoincache = new StatoConsenso();
			//prima di invocare il servizio verifico se esiste un record nella tabella di cache
			statoincache = dmaCacheDao.SelectCache(citId);
			if (statoincache !=null) {
				StatoConsensiOUT statoout = new StatoConsensiOUT();
				statoConsensiResponse.setEsito(RisultatoCodice.SUCCESSO);
				statoout.setConsensoAlimentazione(Util.booleanToString(statoincache.isConsensoAlimentazione()));
				statoout.setConsensoConsultazione(Util.booleanToString(statoincache.isConsensoConsultazione()));
				statoout.setConsensoPregresso(Util.booleanToString(statoincache.isConsensoPregresso()));
				statoout.setIdentificativoAssistitoConsenso(statoincache.getIdentificativoAssistitoConsenso());
				statoout.setIdentificativoInformativaConsensi(statoincache.getIdentificativoInformativaConsensi());
				statoout.setIdentificativoInformativaCorrente(statoincache.getIdentificativoInformativaCorrente());
				statoConsensiResponse.setStatoConsensiOUT(statoout);
			}
			else {
			String consensiServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.STATO_CONSENSI_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(consensiServiceUrl);
			ConsensoINIExtService_Service cs = new ConsensoINIExtService_Service(wsdlURL, SERVICE_NAME_CONSENSO);
			ConsensoINIExtService port = cs.getConsensoINIExtServicePort();
			
			statoConsensiResponse = port.statoConsensi(creareConsensiReq(shibIdentitaCodiceFiscale,xRequestId,xCodiceServizio,citId,ruoloFse,regime));
			statoincache = new StatoConsenso();
				//se stato consensi successo inserisco in tabella cache
				if (statoConsensiResponse.getEsito()!=null && statoConsensiResponse.getEsito().name().equals(RisultatoCodice.SUCCESSO.name())) {
					statoincache.setConsensoAlimentazione(Util.stringToBoolean(statoConsensiResponse.getStatoConsensiOUT().getConsensoAlimentazione(), Constants.TRUE));
					statoincache.setConsensoConsultazione(Util.stringToBoolean(statoConsensiResponse.getStatoConsensiOUT().getConsensoConsultazione(), Constants.TRUE));
					statoincache.setConsensoPregresso(Util.stringToBoolean(statoConsensiResponse.getStatoConsensiOUT().getConsensoPregresso(), Constants.TRUE));
					statoincache.setIdentificativoAssistitoConsenso(statoConsensiResponse.getStatoConsensiOUT().getIdentificativoAssistitoConsenso());
					statoincache.setIdentificativoInformativaConsensi(statoConsensiResponse.getStatoConsensiOUT().getIdentificativoInformativaConsensi());
					statoincache.setIdentificativoInformativaCorrente(statoConsensiResponse.getStatoConsensiOUT().getIdentificativoInformativaCorrente());
					dmaCacheDao.insertTableCache(statoincache, shibIdentitaCodiceFiscale);
				}
			}
			return statoConsensiResponse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.CONSENSI_EXT_SERVICE);
		}
	}

protected StatoConsensiExtRequeste creareConsensiReq(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, String citId, String ruoloFse, String regime) throws DatabaseException {
		
		
		StatoConsensiExtRequeste statoConsensiReq = new StatoConsensiExtRequeste();
		
		
		Paziente paz = new Paziente();
		paz.setCodiceFiscale(citId);
		
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		ap.setCodice(xCodiceServizio);
		
		RichiedenteExt rich = new RichiedenteExt();	
		rich.setApplicazione(ap);
		rich.setCodiceFiscale(shibIdentitaCodiceFiscale);
		rich.setNumeroTransazione(xRequestId);
		
		RegimeDMA regimedma = new RegimeDMA();
		regimedma.setCodice(regime);
		
		RuoloDMA ruoldma = new RuoloDMA();
		ruoldma.setCodice(ruoloFse);
		
		rich.setRegime(regimedma);
		rich.setRuolo(ruoldma);
		rich.setTokenOperazione(xRequestId);
		
		
		StatoConsensiIN statoIn = new StatoConsensiIN();
		
		statoIn.setContestoOperativo(Constants.STATO_CONSENSI_CONTESTO_OPERATIVO);
		statoIn.setIdentificativoAssistitoConsenso(citId);
		statoIn.setIdentificativoAssistitoGenitoreTutore(citId); //TODO chiedere e serve
		statoIn.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		statoIn.setIdentificativoOrganizzazione(Constants.IDENTIFICATIVO_ORGANIZZAZIONE);
		statoIn.setPresaInCarico(Constants.TRUE.toLowerCase());
		statoIn.setRuoloUtente(ruoloFse);
		statoIn.setStrutturaUtente(Constants.STRUTTURA_UTENTE);
		statoIn.setTipoAttivita(Constants.TIPO_ATTIVITA);
		
		statoConsensiReq.setPaziente(paz);
		statoConsensiReq.setRichiedente(rich);	
		statoConsensiReq.setStatoConsensiIN(statoIn);
		return statoConsensiReq;
		
	}
																	
	public RecuperoInformativaResponse getInformativa(String xCodiceServizio, String shibIdentitaCodiceFiscale, String xRequestId,String regime, String ruoloFse, String identificativoStruttura, String identificativoOrganizzazione,String idInformativa ) {
	URL wsdlURL;
	try {
		String consensiServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.STATO_CONSENSI_SERVICE_URL) + "?wsdl";
		wsdlURL = new URL(consensiServiceUrl);
		ConsensoINIExtService_Service cs = new ConsensoINIExtService_Service(wsdlURL, SERVICE_NAME_CONSENSO);
		ConsensoINIExtService port = cs.getConsensoINIExtServicePort();
		
		

		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
		
		RecuperoInformativaResponse recuperoInformativaResponse = port.recuperoInformativa(creareInformativaReq(xCodiceServizio, shibIdentitaCodiceFiscale, xRequestId, regime, ruoloFse, identificativoStruttura, identificativoOrganizzazione,idInformativa));
		
		return recuperoInformativaResponse;

	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Errore chiamata servizio : " + Constants.CONSENSI_EXT_SERVICE);
	}
}
	
 protected RecuperoInformativaExtRequeste creareInformativaReq(String xCodiceServizio, String shibIdentitaCodiceFiscale, String xRequestId,String regime, String ruoloFse, String identificativoStruttura, String identificativoOrganizzazione,String idInformativa) throws DatabaseException {
		
		
	 RecuperoInformativaExtRequeste informativaReq = new RecuperoInformativaExtRequeste();
		
	    InformativaIN informativa = new InformativaIN();
	 	informativa.setIdentificativoInformativa(idInformativa);
	 	informativa.setIdentificativoOrganizzazione(Constants.IDENTIFICATIVO_ORGANIZZAZIONE);
	 	informativa.setIdentificativoUtente(shibIdentitaCodiceFiscale);
	 	informativa.setRuoloUtente(ruoloFse);
	 	informativa.setTipoAttivita(Constants.TIPO_ATTIVITA);
		informativa.setStrutturaUtente(Constants.STRUTTURA_UTENTE);
		
		ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
		ap.setCodice(xCodiceServizio);
		
		RichiedenteExt rich = new RichiedenteExt();	
		rich.setApplicazione(ap);
		rich.setCodiceFiscale(shibIdentitaCodiceFiscale);
		rich.setNumeroTransazione(xRequestId);
		rich.setTokenOperazione(xRequestId);
		RegimeDMA regimedma = new RegimeDMA();
		regimedma.setCodice(regime);
		rich.setRegime(regimedma);
		RuoloDMA ruolodma = new RuoloDMA();
		ruolodma.setCodice(ruoloFse);
		rich.setRuolo(ruolodma);
		
		informativaReq.setInformativaIN(informativa);
		informativaReq.setRichiedente(rich);
		
		
		return informativaReq;
		
	}
 
	public ComunicazioneConsensiResponse getComunicazioneConsensi(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio, String identificativoAssistitoConsensi, String ruoloFse, String regime,boolean consensoAlimentazione,boolean consensoConsultazione,boolean consensoPregresso, String idInformativa,String contestoOperativo,String assistitoGenitoreTutore,String assititoGenitoreConsenso) {
		URL wsdlURL;
		try {
			String consensiServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.STATO_CONSENSI_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(consensiServiceUrl);
			ConsensoINIExtService_Service cs = new ConsensoINIExtService_Service(wsdlURL, SERVICE_NAME_CONSENSO);
			ConsensoINIExtService port = cs.getConsensoINIExtServicePort();
			
			

			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
			
			ComunicazioneConsensiResponse statoConsensiResp = port.comunicazioneConsensi(creareComunicazioneReq(xCodiceServizio, shibIdentitaCodiceFiscale, xRequestId, regime, ruoloFse, identificativoAssistitoConsensi, idInformativa, consensoAlimentazione, consensoConsultazione, consensoPregresso, contestoOperativo,assistitoGenitoreTutore,assititoGenitoreConsenso));
			return statoConsensiResp;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.CONSENSI_EXT_SERVICE);
		}
	}
	
	 protected ComunicazioneConsensiExtRequeste creareComunicazioneReq(String xCodiceServizio, String shibIdentitaCodiceFiscale, String xRequestId,String regime, String ruoloFse, String identificativoAssistitoConsensi,
			 String idInformativa,boolean consensoAlimentazione,boolean consensoConsultazione,boolean consensoPregresso,String contestoOperativo,String assistitoGenitoreTutore,String assititoGenitoreConsenso) throws DatabaseException {
			
			
		 ComunicazioneConsensiExtRequeste informativaReq = new ComunicazioneConsensiExtRequeste();
			
		 	ComunicazioneConsensiIN informativa = new ComunicazioneConsensiIN();
		 	informativa.setIdentificativoInformativa(idInformativa);
		 	informativa.setIdentificativoOrganizzazione(Constants.IDENTIFICATIVO_ORGANIZZAZIONE);
		 	informativa.setIdentificativoUtente(shibIdentitaCodiceFiscale);
		 	informativa.setRuoloUtente(ruoloFse);
		 	informativa.setTipoAttivita(Constants.TIPO_ATTIVITA_CREATE);
			informativa.setStrutturaUtente(Constants.STATO_CONSENSI_CONTESTO_OPERATIVO);
			informativa.setConsensoAlimentazione(Util.booleanToString(consensoAlimentazione));
			informativa.setConsensoConsultazione(Util.booleanToString(consensoConsultazione));
			informativa.setConsensoPregresso(Util.booleanToString(consensoPregresso));
			informativa.setIdentificativoAssistitoConsenso(identificativoAssistitoConsensi);
			informativa.setIdentificativoAssistitoGenitoreTutore(assistitoGenitoreTutore);
			informativa.setIdentificativoGenitoreConsenso(assititoGenitoreConsenso);
			informativa.setContestoOperativo(contestoOperativo);
			informativa.setPresaInCarico(Util.booleanToString(true));
			
			ApplicazioneRichiedente ap = new ApplicazioneRichiedente();
			ap.setCodice(xCodiceServizio);
			
			Paziente paziente = new Paziente();
			paziente.setCodiceFiscale(identificativoAssistitoConsensi);
			
			
			RichiedenteExt rich = new RichiedenteExt();	
			rich.setApplicazione(ap);
			rich.setCodiceFiscale(shibIdentitaCodiceFiscale);
			rich.setNumeroTransazione(xRequestId);
			rich.setTokenOperazione(xRequestId);
			RegimeDMA regimedma = new RegimeDMA();
			regimedma.setCodice(regime);
			rich.setRegime(regimedma);
			RuoloDMA ruolodma = new RuoloDMA();
			ruolodma.setCodice(ruoloFse);
			rich.setRuolo(ruolodma);
			
			informativaReq.setComunicazioneConsensiIN(informativa);
			informativaReq.setRichiedente(rich);
			informativaReq.setPaziente(paziente);
			
			
			return informativaReq;
			
		}
	 
	 public boolean isFuoriRegione(StatoConsensiResponse statoConsensiResponse) {
			if (!statoConsensiResponse.getStatoConsensiOUT().getIdentificativoInformativaCorrente().startsWith(Constants.IDENTIFICATIVO_ORGANIZZAZIONE)){
				//il paziente e' fuori regione
				return true;
			} else {
				return false;
			}
		}
		
		public boolean isConsensoConsultazione(StatoConsensiResponse statoConsensiResponse) {
			if (Constants.TRUE.equalsIgnoreCase(statoConsensiResponse.getStatoConsensiOUT().getConsensoConsultazione())) {
				return true;
			} else {
				return false;
			}
		}

		public int deleteStatoConsenso(String citId) {
			
			return dmaCacheDao.DeleteTableCache(citId);
			
		}
}
