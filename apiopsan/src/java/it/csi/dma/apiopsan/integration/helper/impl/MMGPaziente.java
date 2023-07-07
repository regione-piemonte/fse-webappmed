/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.helper.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.namespace.QName;

import org.apache.cxf.frontend.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.GiorniApertura;
import it.csi.dma.apiopsan.dto.IndirizzoStudio;
import it.csi.dma.apiopsan.dto.InfoMmgPaziente;
import it.csi.dma.apiopsan.dto.MedicoMmg;
import it.csi.dma.apiopsan.dto.Studi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.StatusEnum;
import it.csi.dma.apiopsan.util.validator.impl.ValidateGenericMeritWhitMedicoImpl;
import it.csi.dma.apiopsan.verificaservices.dmacc.RisultatoCodice;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.service.CodLMessaggiService;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.pazienteservice.dma.ApplicazioneRichiedente;
import it.csi.dma.apiopsan.pazienteservice.dma.Errore;
import it.csi.dma.apiopsan.pazienteservice.dma.GiornoAperturaStudio;
import it.csi.dma.apiopsan.pazienteservice.dma.OrarioAperturaStudio;
import it.csi.dma.apiopsan.pazienteservice.dma.Paziente;
import it.csi.dma.apiopsan.pazienteservice.dma.RegimeDMA;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;
import it.csi.dma.apiopsan.pazienteservice.dma.RuoloDMA;
import it.csi.dma.apiopsan.pazienteservice.dma.StudioMedico;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.GetMMGPaziente;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.GetMMGPazienteResponse;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.PazienteService;
import it.csi.dma.apiopsan.pazienteservice.dmaccbl.PazienteService_Service;
import it.csi.dma.apiopsan.exception.ResponseErrorException;

@Service
public class MMGPaziente extends LoggerUtil {
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
	
	public Response execute(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio,String xCodiceVerticale, String ruolo,
			String regime, String collocazione,
			String citId, String idIrec, String idAura,
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
	    		httpHeaders, httpRequest, uuidAsString, Constants.GET_MMGPAZIENTE,citId);
	    //inserisco tabella dei log
	    long idlog = codRLogErroreService.traceLogInsert(Constants.GET_MMGPAZIENTE, uuidAsString, Constants.CITTADINI_API_SERVICE,null);
		try {
			//cerco il ruolo FSE e me lo porto avanti
			RuoloPua ruoloF = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = null;
			if (ruoloF != null)
			ruoloFse = ruoloF.getCodiceRuoloFse();
		// validate
			List<ErroreDettaglioExt> listError = validateMedicGeneric.validate(shibIdentitaCodiceFiscale, xRequestId, 
					xForwardedFor, xCodiceServizio, xCodiceVerticale, ruolo,ruoloFse,regime, collocazione,
					 citId, idIrec, idAura, securityContext, httpHeaders, httpRequest);
			if (!listError.isEmpty()) {
				throw new ResponseErrorException(
						ErrorBuilder.generateErrorBuilderError(StatusEnum.BAD_REQUEST, listError),"errore in validate");
			}
			
			//chiamo il servizio PazienteService GetMMGPaziente
			//compongo la chiamata
			
			GetMMGPaziente datirequestpaziente = new GetMMGPaziente();
			Paziente paziente = new Paziente();
			Richiedente richiedente = new Richiedente();
			ApplicazioneRichiedente applicazione = new ApplicazioneRichiedente();
			RegimeDMA regimedma = new RegimeDMA();
			RuoloDMA ruolodma = new RuoloDMA();
			paziente.setCodiceFiscale(citId);
			paziente.setIdIrec(Long.parseLong(idIrec));
			paziente.setIdAura(Long.parseLong(idAura));
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
			GetMMGPazienteResponse response = new GetMMGPazienteResponse();
			response = functionalCheckMMGPaziente(datirequestpaziente);
			
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
				InfoMmgPaziente result = new InfoMmgPaziente();
				result.setHaMedico(response.getHaMedico().value());
				if (response.getHaMedico().value().equalsIgnoreCase(Constants.SI)) {
				MedicoMmg medico = new MedicoMmg();
				List<Studi> listastudi = new ArrayList<Studi>();
				result.setHaMedico(response.getHaMedico().value());
				medico.setCognome(response.getMedico().getCognome());
				medico.setNome(response.getMedico().getNome());
				medico.setEmail(response.getMedico().getEmail());
				medico.setIdAura(response.getMedico().getIdAura());
				medico.setCodiceFiscale(response.getMedico().getCodiceFiscale());
				result.setMedico(medico);
				for (StudioMedico studio : response.getMedico().getStudi()) {
					Studi studi = new Studi();
					List<GiorniApertura> listaggapertura = new ArrayList<GiorniApertura>();
					studi.setAmbulatorioPubblico(studio.getAmbulatorioPubblico().value());
					studi.setDenominazione(studio.getDenominazione());
					for (GiornoAperturaStudio ggapstudio : studio.getGiorniApertura()) {
						GiorniApertura giorniapertura = new GiorniApertura();
						giorniapertura.setGiorno(ggapstudio.getGiorno());
						for (OrarioAperturaStudio orariap : ggapstudio.getOrari())
						{
							giorniapertura.setOrarioFine(orariap.getOrarioFine());
							giorniapertura.setOrarioInizio(orariap.getOrarioInizio());
							
						}
						listaggapertura.add(giorniapertura);
					}
					IndirizzoStudio indirizzostudio = new IndirizzoStudio();
					indirizzostudio.setCap(studio.getIndirizzoStudio().getCap());
					indirizzostudio.setDescComune(studio.getIndirizzoStudio().getDescrComune());
					indirizzostudio.setIndirizzo(studio.getIndirizzoStudio().getIndirizzo());
					indirizzostudio.setTelPrimario(studio.getTelPrimario());
					indirizzostudio.setEmail(studio.getEmail());
					studi.setGiorniApertura(listaggapertura);
					studi.setIndirizzoStudio(indirizzostudio);
					listastudi.add(studi);
				}
				result.setStudi(listastudi);
				}
				//inserimento nel log esito positivo
				codRLogErroreService.traceLogInsert(Constants.GET_MMGPAZIENTE, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_SUCCESSO);
				codLMessaggiService.traceMessaggiUpdate(id_xml, uuidAsString, result.toString(),Constants.ESITO_SUCCESSO);
				String[] array = new String[1];
				array[0] = citId;
				codRLogErroreService.traceLogAuditInsert(Constants.GET_MMGPAZIENTE, uuidAsString, result.toString(), 
						Constants.CODICE_SUCCESSO_GETMMGPAZIENTE, shibIdentitaCodiceFiscale, 
						collocazione, xForwardedFor, xCodiceVerticale, xCodiceServizio, ruoloFse, regime, citId,array,null,null);
				 return Response.status(200).entity(result).build();
				
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
		codRLogErroreService.traceLogInsert(Constants.GET_MMGPAZIENTE, uuidAsString, Constants.CITTADINI_API_SERVICE,Constants.ESITO_FALLIMENTO);
		return esito;
		
	}
	
	 protected GetMMGPazienteResponse functionalCheckMMGPaziente(GetMMGPaziente datirequest) throws DatabaseException {
			
			URL wsdlURL;
			try {
				String pazienteServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.PAZIENTE_SERVICE_URL) + "?wsdl";
				wsdlURL = new URL(pazienteServiceUrl);
		    PazienteService_Service ss = new PazienteService_Service(wsdlURL, SERVICE_NAME_PAZIENTE);
		    PazienteService port = ss.getPazienteServicePort();

		    org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
		    org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
		  
    
		    GetMMGPazienteResponse getmmgpazienteResponse = port.getMMGPaziente(datirequest);

			return getmmgpazienteResponse;
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Errore chiamata servizio : " + Constants.MMGPAZIENTE_SERVICE);
			}

		}
	 
	 
}


