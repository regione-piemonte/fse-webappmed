/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.custom.CatalogoServiziOperation;
import it.csi.dma.apiopsan.dto.custom.LMessaggi;
import it.csi.dma.apiopsan.dto.custom.LMessaggiXml;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.CodLMessaggiDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.util.Constants;

@Service
public class CodLMessaggiServiceImpl implements CodLMessaggiService {
	
	@Autowired
	private CodLMessaggiDao codLMessaggiDao;
	
	@Autowired
	CatalogoServiziDao catalogoServiziDao;


	@Override
	public int updateMessaggi(LMessaggi lMessaggi, Long id) {
		
		return codLMessaggiDao.updateMessaggi(lMessaggi, id);
	}
	
	@Override
	public long traceMessaggiInsert(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String collocazione,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, String uuidAsString,String inf_aggiuntive, String citId)  {
		CatalogoServiziOperation codiceServizio=null;
		try {
			codiceServizio = catalogoServiziDao.selectServizioPerNome(inf_aggiuntive);
	     //inserisco la chiamata nella L_messaggi
			LMessaggiXml messaggiolog = new LMessaggiXml();
			messaggiolog.setWso2Id(uuidAsString);
			messaggiolog.setUuid(uuidAsString);
			messaggiolog.setServizioXml(inf_aggiuntive);
			messaggiolog.setChiamante(xCodiceServizio);
			messaggiolog.setStatoXml(Constants.UNO);
			messaggiolog.setIdMessaggioOrig(null);
			messaggiolog.setCfUtente(shibIdentitaCodiceFiscale);
			messaggiolog.setRuoloUtente(ruolo);
			messaggiolog.setApplVerticale(xCodiceVerticale);
			messaggiolog.setIpRichiedente(xForwardedFor);
			messaggiolog.setCodiceServizio(codiceServizio.getCodiceServizio());
			if (citId!=null)
				messaggiolog.setCfAssistito(citId);
			long id_xml = codLMessaggiDao.insertMessaggi(messaggiolog);
			
			String xmlin = "{ Method : " + httpRequest.getMethod() + System.lineSeparator();
			xmlin = xmlin + "URI: " + httpRequest.getRequestURI() + System.lineSeparator();
			Enumeration<String> headerNames = httpRequest.getHeaderNames();
			while(headerNames.hasMoreElements()) {
			  String headerName = headerNames.nextElement();
			  xmlin = xmlin + "Header Name : " + headerName + ", Value : " + httpRequest.getHeader(headerName) + System.lineSeparator();
			}
			if (httpRequest.getQueryString() != null)
			xmlin = xmlin + httpRequest.getQueryString() + System.lineSeparator();

			xmlin = xmlin + "}";
			
			messaggiolog.setXmlIn(xmlin);
			
			long id = codLMessaggiDao.insertMessaggiXml(messaggiolog);
			return id_xml;
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public void traceMessaggiUpdate(long id_xml,String uuidAsString, String esito,String tipo_esito) {
	//aggiornamento record dmacc_l_messaggi e dmacc_l_xml_messaggi
			LMessaggiXml messaggiologerrore = new LMessaggiXml();
			messaggiologerrore.setStatoXml(Constants.DUE);
			messaggiologerrore.setInfoAggiuntiveErrore(null);
			messaggiologerrore.setCodEsitoRispostaServizio(tipo_esito);
			codLMessaggiDao.updateMessaggi(messaggiologerrore, id_xml);
			messaggiologerrore.setXmlOut(esito);
			codLMessaggiDao.updateMessaggiXml(messaggiologerrore, uuidAsString);
	}
	
	public long insertMessage(String shibIdentitaCodiceFiscale, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo,
			String citId, String uuidAsString) throws DatabaseException {
		CatalogoServiziOperation codiceServizio = catalogoServiziDao.selectServizioPerNome(Constants.CC_SET_AUDIT);
		LMessaggi messaggiolog = new LMessaggi();
		messaggiolog.setWso2Id(uuidAsString);
		messaggiolog.setUuid(uuidAsString);
		messaggiolog.setServizioXml(Constants.CC_SET_AUDIT);
		messaggiolog.setChiamante(xCodiceServizio);
		messaggiolog.setStatoXml(Constants.UNO);
		messaggiolog.setIdMessaggioOrig(null);
		messaggiolog.setCfUtente(shibIdentitaCodiceFiscale);
		messaggiolog.setRuoloUtente(ruolo);
		messaggiolog.setApplVerticale(xCodiceVerticale);
		messaggiolog.setIpRichiedente(xForwardedFor);
		messaggiolog.setCodiceServizio(codiceServizio.getCodiceServizio());
		if (citId!=null)
			messaggiolog.setCfAssistito(citId);
		return codLMessaggiDao.insertMessaggi(messaggiolog);
	}
}
