/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.PayloadSetAudit;
import it.csi.dma.apiopsan.dto.custom.CatalogoServiziOperation;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.LLog;
import it.csi.dma.apiopsan.dto.custom.LLogAudit;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.ApplicazioneRichiedenteDao;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoServiziDao;
import it.csi.dma.apiopsan.integration.dao.custom.CodDErroreDao;
import it.csi.dma.apiopsan.integration.dao.custom.CodLLogAuditDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.dao.custom.UtenteDao;
import it.csi.dma.apiopsan.integration.service.CodRLogErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.validator.impl.BaseValidate;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CodRLogErroreServiceImpl extends BaseValidate implements CodRLogErroreService {

	@Autowired
	CodLLogAuditDao codLLogAuditDao;
	
	@Autowired
	CodDErroreDao codDErroreDao;
	
	@Autowired
	CatalogoServiziDao catalogoServiziDao;
	
	@Autowired
	ApplicazioneRichiedenteDao applicazioneRichiedenteDao;
	
	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	UtenteDao utenteDao;
	
	@Autowired
	GetRegimiDao regimiDao;
	

	@Override
	public ErrorBuilder saveLog(ErrorBuilder error, HttpServletRequest httpRequest, String uid, String codiceServizio) {
		String methodName = "saveLog";
		try {
			List<LLog> llogList = new ArrayList<>();
			if (error.getDetal() != null && !error.getDetal().isEmpty()) {
				
				for (ErroreDettaglioExt erroreDettaglio : error.getDetal()) {
					LLog logErrore = new LLog();
					logErrore.setInformazioniTracciate(erroreDettaglio.getValore());
					logErrore.setCodiceTokenOperazione(null);
					logErrore.setIdCatalogoLog(codDErroreDao.selectErroreIdFromErroreCod(erroreDettaglio.getChiave()));
					logErrore.setIdTransazione(uid);
					logErrore.setCodiceServizio(codiceServizio);
					
					Object contextChiaveId = httpRequest.getAttribute(Constants.CONTEXT_CHIAVE_ID);
					if(contextChiaveId instanceof Long) {
						logErrore.setId((Long) contextChiaveId);
					} else {
						logError(methodName, "non trovato messaggio errore id inserito default 0 ");
						logErrore.setId(0);
					}
					
					llogList.add(logErrore);
				}
				codLLogAuditDao.insert(llogList);

			}
		} catch (Exception e) {
			logError(methodName, "errore in salvataggio errore dettaglio",e.getMessage());
			ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
			erroreDettaglio.setValore(e.getMessage());
			erroreDettaglio.setChiave(CodeErrorEnum.FSE_COD_FATAL.getCode());
			error.detail(erroreDettaglio);
		}
		return error;

	}
	
	@Override
	public long traceLogInsert(String infaggiuntive,String uuidAsString, String serviceimpl,String tipo_esito) {
		//inserimento tabella di dmacc_t_log
		LLogAudit llog = new LLogAudit();
		CatalogoServiziOperation codiceServizio= new CatalogoServiziOperation();
		try {
		codiceServizio = catalogoServiziDao.selectServizioPerNome(infaggiuntive);
		llog.setCodiceTokenOperazione(null);
		if (tipo_esito!=null) {
			String logreplace = getErrorLogInsert(CodeErrorEnum.CC_LOG_002.getCode(), infaggiuntive, serviceimpl,tipo_esito);
			llog.setInformazioniTracciate(logreplace);
			llog.setIdCatalogoLog(codDErroreDao.selectErroreIdFromErroreCod(CodeErrorEnum.CC_LOG_002.getCode()));
		}
		else {
		llog.setInformazioniTracciate(codDErroreDao.selectErroreDescFromErroreCod(CodeErrorEnum.CC_LOG_003.getCode()).replace("{0}",serviceimpl + "." +infaggiuntive));
		llog.setIdCatalogoLog(codDErroreDao.selectErroreIdFromErroreCod(CodeErrorEnum.CC_LOG_003.getCode()));
		}
		llog.setIdTransazione(uuidAsString);
		llog.setCodiceServizio(codiceServizio.getCodiceServizio());
		long idlog = codLLogAuditDao.insertLog(llog);
		return idlog;
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public void traceLogAuditInsert(String infaggiuntive,String uuidAsString, String listaout,String codsuccesso, String shibIdentitaCodiceFiscale,
			String collocazione,String xForwardedFor, String xCodiceVerticale, String xCodiceServizio, String ruolo, String regime, 
			String citId,String[] array,Long iddocumento,String codiceCl) {
		//inserimento tabella di dmacc_t_log
		LLogAudit llog = new LLogAudit();
		CatalogoServiziOperation codiceServizio= new CatalogoServiziOperation();
		try {
			LLogAudit logaudit = new LLogAudit();
			String logreplace = getSuccessAuditInsertArray(codsuccesso, array);
			logaudit.setInformazioniTracciate(logreplace);
			logaudit.setIdCatalogoLog(codDErroreDao.selectSuccessoIdFromCod(codsuccesso));
			logaudit.setCodiceFiscaleUtente(shibIdentitaCodiceFiscale);
			logaudit.setIdTransazione(uuidAsString);
			logaudit.setCodiceTokenOperazione(uuidAsString);
			logaudit.setCollocazione(collocazione);
			logaudit.setIpChiamante(xForwardedFor);
			logaudit.setCodiceServizio(catalogoServiziDao.selectServizioPerNome(infaggiuntive).getCodiceServizio());
			logaudit.setNomeServizio(catalogoServiziDao.selectServizioPerNome(infaggiuntive).getNomeServizio());
			logaudit.setApplicazioneVerticale(xCodiceVerticale);
			logaudit.setIdApplicazioneRichiedente(applicazioneRichiedenteDao.selectIdApplicativoRichiedentePerCodice(xCodiceServizio));
			logaudit.setIdRuolo(dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(ruolo).getId());
			long selectIdUtentePerCodiceFiscale = utenteDao.selectIdUtentePerCodiceFiscale(shibIdentitaCodiceFiscale);
			if(selectIdUtentePerCodiceFiscale!=0) {
				logaudit.setIdUtente(selectIdUtentePerCodiceFiscale);
			}
			if (Util.isValorizzato(regime))
				logaudit.setIdRegime(regimiDao.selectIdRegimiPerLogAudit(regime));
			else
				logaudit.setIdRegime(null);
			if (Util.isValorizzato(citId))
				logaudit.setIdPaziente(utenteDao.selectIdPazientePerCodiceFiscale(citId));
			else
				logaudit.setIdPaziente(null);
			if (iddocumento!=null)
				logaudit.setIddocumento(iddocumento);
			else
				logaudit.setIddocumento(null);
			if (Util.isValorizzato(codiceCl))
				logaudit.setComponentelocale(codiceCl);
			else
				logaudit.setComponentelocale(null);
			codLLogAuditDao.insertLogAudit(logaudit);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void insertLogAudit(String xRequestId, String xCodiceServizio, String shibIdentitaCodiceFiscale, String xforward, String applicazioneVerticale, String ruolo, String regime, String collocazione, PayloadSetAudit payloadSetAudit) {
		LLogAudit llog = new LLogAudit();
		CatalogoServiziOperation codiceServizio= new CatalogoServiziOperation();
		try {
			LLogAudit logaudit = new LLogAudit();
			logaudit.setCodiceTokenOperazione(xRequestId);
			if (Util.isValorizzato(payloadSetAudit.getCittadinoId())) {
				logaudit.setIdPaziente(utenteDao.selectIdPazientePerCodiceFiscale(payloadSetAudit.getCittadinoId()));
			}
			String informazioniTracciate =  getSuccessAuditInsertArray(payloadSetAudit.getCodiceAudit(), createParams(payloadSetAudit));
			logaudit.setInformazioniTracciate(informazioniTracciate );
			logaudit.setIdCatalogoLog(codDErroreDao.selectSuccessoIdFromCod(payloadSetAudit.getCodiceAudit()));
			logaudit.setDataInserimento(new Timestamp(new Date().getTime()));
			logaudit.setIdTransazione(xRequestId);
			RuoloPua ruoloPua = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
			String ruoloFse = ruoloPua.getCodiceRuoloFse();
			logaudit.setIdRuolo(dmaccTDecodificaRuoliPuaDao.selectRuoloFromTableRuolo(ruoloFse).getId());
			if (Util.isValorizzato(regime)) {
				logaudit.setIdRegime(regimiDao.selectIdRegimiPerLogAudit(regime));
			}
			logaudit.setIdApplicazioneRichiedente(applicazioneRichiedenteDao.selectIdApplicativoRichiedentePerCodice(xCodiceServizio));
			long selectIdUtentePerCodiceFiscale = utenteDao.selectIdUtentePerCodiceFiscale(shibIdentitaCodiceFiscale);
			if(selectIdUtentePerCodiceFiscale!=0) {
				logaudit.setIdUtente(selectIdUtentePerCodiceFiscale);
			}
			logaudit.setCollocazione(collocazione);
			logaudit.setIpChiamante(xforward);
			logaudit.setCodiceFiscaleUtente(shibIdentitaCodiceFiscale);
			logaudit.setApplicazioneVerticale(applicazioneVerticale);
			logaudit.setCodiceServizio(xCodiceServizio);
			logaudit.setCodiceServizio(catalogoServiziDao.selectServizioPerNome(Constants.CC_SET_AUDIT).getCodiceServizio());
			logaudit.setNomeServizio(catalogoServiziDao.selectServizioPerNome(Constants.CC_SET_AUDIT).getNomeServizio());
			codLLogAuditDao.insertLogAudit(logaudit);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String[] createParams(PayloadSetAudit payloadSetAudit) {
		List<String> parametri = new ArrayList<String>();
		
		parametri.add(payloadSetAudit.getCittadinoId());
		if (payloadSetAudit.getParametri() != null) {
			List<Codice> codice = payloadSetAudit.getParametri();
			for(Codice parametro : codice) {
				parametri.add(parametro.getCodice());
				parametri.add(parametro.getDescrizione());
			}
		}
		
		return parametri.toArray(String[]::new);
	}
}
