/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.MessaggioErrore;
import it.csi.dma.apiopsan.integration.dao.custom.CodRMessaggioErroreDao;
import it.csi.dma.apiopsan.integration.service.CodRMessaggioErroreService;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.ErrorBuilder;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CodRMessaggioErroreServiceImpl extends LoggerUtil implements CodRMessaggioErroreService {

	@Autowired
	CodRMessaggioErroreDao codRMessaggioErroreDao;

	@Override
	public ErrorBuilder saveError(ErrorBuilder error, HttpServletRequest httpRequest, String uid) {
		String methodName = "saveError";
		try {
			List<MessaggioErrore> messaggioErroreList = new ArrayList<>();
			if (error.getDetal() != null && !error.getDetal().isEmpty()) {
				
				for (ErroreDettaglioExt erroreDettaglio : error.getDetal()) {
					MessaggioErrore messaggioErrrore = new MessaggioErrore();
					messaggioErrrore.setInformazioniAggiuntive(erroreDettaglio.getValore());
					messaggioErrrore.setCodErrore(erroreDettaglio.getChiave());
					messaggioErrrore.setDescrErrore(erroreDettaglio.getValore());
					messaggioErrrore.setWso2Id(uid);
					messaggioErrrore.setTipoErrore(Constants.BLOCCANTE);
					Object contextChiaveId = httpRequest.getAttribute(Constants.CONTEXT_CHIAVE_ID);
					if(contextChiaveId instanceof Long) {
						messaggioErrrore.setId((Long) contextChiaveId);
					} else {
						logError(methodName, "non trovato messaggio errore id inserito default 0 ");
						messaggioErrrore.setId(null);
					}
					
					messaggioErroreList.add(messaggioErrrore);
				}
				codRMessaggioErroreDao.insert(messaggioErroreList);

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
}
