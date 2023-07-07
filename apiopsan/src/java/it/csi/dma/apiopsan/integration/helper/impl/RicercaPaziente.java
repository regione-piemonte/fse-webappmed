/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.GiorniApertura;
import it.csi.dma.apiopsan.dto.IndirizzoStudio;
import it.csi.dma.apiopsan.dto.Paziente;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Converter;
import it.csi.dma.apiopsan.util.CreateTemplateMessage;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.ErrorParamEnum;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.CodDErroreDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.dao.custom.ParametroDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.pazienteservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.pazienteservice.dma.Errore;
import it.csi.dma.apiopsan.pazienteservice.dma.GiornoAperturaStudio;
import it.csi.dma.apiopsan.pazienteservice.dma.OrarioAperturaStudio;
import it.csi.dma.apiopsan.pazienteservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;
import it.csi.dma.apiopsan.pazienteservice.dma.RisultatoCodice;
import it.csi.dma.apiopsan.pazienteservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.pazienteservice.dma.Sesso;
import it.csi.dma.apiopsan.pazienteservice.dma.StudioMedico;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.PazienteService;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.PazienteService_Service;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.RicercaPazienteResponse;
import it.csi.dma.apiopsan.exception.ResponseErrorException;

@Service
public class RicercaPaziente extends LoggerUtil {
	private static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
    private static final QName SERVICE_NAME_PAZIENTE = new QName("http://dmaccbl.csi.it/", "PazienteService");
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
	ParametroDao parametroDao;
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String regime, String collocazione,
			String citId, String cognome, String nome,String dataNascita,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_RICERCAPAZIENTE,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_RICERCAPAZIENTE, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validate(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, cognome, nome,dataNascita, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			
			//chiamo il servizio PazienteService RicercaPaziente
			//compongo la chiamata
			
			it.csi.dma.apiopsan.pazienteservice.dmaccbl.RicercaPaziente datirequestpaziente = new it.csi.dma.apiopsan.pazienteservice.dmaccbl.RicercaPaziente();
			it.csi.dma.apiopsan.pazienteservice.dma.Paziente paziente = new it.csi.dma.apiopsan.pazienteservice.dma.Paziente();
			Richiedente richiedente = new Richiedente();
			ApplicazioneRichiedente applicazione = new ApplicazioneRichiedente();
			RegimeDMA regimedma = new RegimeDMA();
			RuoloDMA ruolodma = new RuoloDMA();
			if (Util.isValorizzato(citId)) {
				paziente.setCodiceFiscale(citId);
			}
			else if (Util.isValorizzato(cognome) && Util.isValorizzato(nome)) {
				paziente.setCognome(cognome);
				paziente.setNome(nome);			
				if (Util.isValorizzato(dataNascita)) {
					XMLGregorianCalendar xmlDataNascita = Converter.convertToCalendar(dataNascita);
					paziente.setDataDiNascita(xmlDataNascita);
				}
			}
			datirequestpaziente.setPaziente(paziente);
			richiedente.setCodiceFiscale(shibIdentitaCodiceFiscale);
			richiedente.setNumeroTransazione(xRequestId);
			richiedente.setTokenOperazione(xRequestId);
			applicazione.setCodice(xCodiceServizio);
			richiedente.setApplicazione(applicazione);
			regimedma.setCodice(regime);
			richiedente.setRegime(regimedma);
			ruolodma.setCodice(ruoloFse);
			richiedente.setRuolo(ruolodma);
			datirequestpaziente.setRichiedente(richiedente);
			RicercaPazienteResponse response = new RicercaPazienteResponse();
			response = functionalCheckRicercaPaziente(datirequestpaziente);
			
		   if (response.getEsito().name().equals(RisultatoCodice.FALLIMENTO.name())) {
				//dai errore e accoda ad errori
				List<ErroreDettaglioExt> listerrorservice = new ArrayList<ErroreDettaglioExt>();
				for (Errore errore : response.getErrori()) {
					ErroreDettaglioExt errorservice = new ErroreDettaglioExt();
					errorservice.setChiave(errore.getCodice());
					errorservice.setValore(errore.getDescrizione());
					errorservice.setErroreId(errorservice.getErroreId()!=null?errorservice.getErroreId():DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID);
					listerrorservice.add(errorservice);
				}
				
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.SERVER_ERROR, listerrorservice),"errore in service paziente");
			}
			else {
				//dai esito positivo con payload
				List<Paziente> elencopazienti = new ArrayList<Paziente>();
				for (it.csi.dma.apiopsan.pazienteservice.dma.Paziente paz : response.getPazienti()) {
				Paziente result = new Paziente();
				result.setCodiceFiscale(paz.getCodiceFiscale());
				result.setCognome(paz.getCognome());
				result.setNome(paz.getNome());
				Codice nascita = new Codice();
				nascita.setCodice(paz.getComuneDiNascita().getCodice());
				nascita.setDescrizione(paz.getComuneDiNascita().getDescrizione());
				result.setComuneNascita(nascita);
				Codice statonascita = new Codice();
				statonascita.setCodice(paz.getStatoDiNascita().getCodice());
				statonascita.setDescrizione(paz.getStatoDiNascita().getDescrizione());
				result.setStatoNascita(statonascita);
				result.setDataNascita(Converter.getData(paz.getDataDiNascita()));
				result.setSesso(paz.getSesso().value());
				result.setFlagRegistryIncarico(paz.getFlagRegistryIncarico().equalsIgnoreCase(Constants.S) ? true : false);
				result.setIdAura(paz.getIdAura().toString());
				if (Util.isValorizzato(paz.getIdIrec()))
				result.setIdIrec(paz.getIdIrec().toString());
				else
					result.setIdIrec(null);
				if (paz.getIdAsr()!=null)
				result.setIdAsr(paz.getIdAsr().toString());
				else
					result.setIdAsr(null);
				result.setCodiceFiscaleMmg(paz.getCodiceFiscaleMMG());
				elencopazienti.add(result);
				}
				boolean listaridotta = false;
				String valore = parametroDao.selectValoreParametroFromParametroNome(Constants.QUERY_LIMIT_RICERCA_PAZIENTE);
				if (elencopazienti.size() > Converter.getInt(valore)) {
				elencopazienti = elencopazienti.subList(0, Converter.getInt(valore));
				listaridotta = true;
				}
				//inserimento nel log esito positivo
				codRLogErroreService.traceLogInsert(Constants.GET_RICERCAPAZIENTE, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, elencopazienti.toString(),Constants.ESITO_SUCCESSO);
				//popolare la tabella di audit solo se esito positivo
				String[] array = new String[4];
				array[0] = cognome;
				array[1] = nome;
				array[2] = dataNascita;
				array[3] = citId;
				codRLogErroreService.traceLogAuditInsert(Constants.GET_RICERCAPAZIENTE, uuidAsString, elencopazienti.toString(), 
						Constants.CODICE_SUCCESSO_RICERCA_PAZIENTE, shibIdentitaCodiceFiscale, 
						collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId, array,null,null);
				if (listaridotta) {
					return Response.status(206).entity(elencopazienti).build();
				}
				else {
				 return Response.status(200).entity(elencopazienti).build();
				}
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
		codRLogErroreService.traceLogInsert(Constants.GET_RICERCAPAZIENTE, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}
	
	 protected RicercaPazienteResponse functionalCheckRicercaPaziente(it.csi.dma.apiopsan.pazienteservice.dmaccbl.RicercaPaziente datirequest) throws DatabaseException {
			
			URL wsdlURL;
			try {
				String pazienteServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.PAZIENTE_SERVICE_URL) + "?wsdl";
				wsdlURL = new URL(pazienteServiceUrl);
		    PazienteService_Service ss = new PazienteService_Service(wsdlURL, SERVICE_NAME_PAZIENTE);
		    PazienteService port = ss.getPazienteServicePort();

		    org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		    org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
		  
    
		    RicercaPazienteResponse getricercapazienteResponse = port.ricercaPaziente(datirequest);

			return getricercapazienteResponse;
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Errore chiamata servizio : " + Constants.RICERCAPAZIENTE_SERVICE);
			}

		}
	 
}


