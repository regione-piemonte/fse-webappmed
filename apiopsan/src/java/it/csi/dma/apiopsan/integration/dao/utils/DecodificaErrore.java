/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.csi.dma.apiopsan.util.enumerator.StatusEnum;

public class DecodificaErrore {
	
	 private static final Map<String, ErroreTaccuino> myMap;
	    static {
	    	Map<String, ErroreTaccuino> aMap = new HashMap<String, ErroreTaccuino>();
	    	
			
	        ErroreTaccuino e001 = new ErroreTaccuino();
	        e001.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e001.setCodice(9001);
	        e001.setTitle("Campi obbligatori mancanti");
	        e001.setDescrizione("Il parametro applicativo Verticale deve essere valorizzato");
	        e001.setTipologia("Bloccante");
	        aMap.put("UC_ERR_001", e001);

	        ErroreTaccuino e002 = new ErroreTaccuino();
	        e002.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e002.setCodice(9001);
	        e002.setTitle("Campi obbligatori mancanti");
	        e002.setDescrizione("Il parametro Applicazione deve essere valorizzato");
	        e002.setTipologia("Bloccante");
	        aMap.put("UC_ERR_002", e002);

	        ErroreTaccuino e003 = new ErroreTaccuino();
	        e003.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e003.setCodice(9001);
	        e003.setTitle("Campi obbligatori mancanti");
	        e003.setDescrizione("Il parametro codice fiscale richiedente deve essere valorizzato");
	        e003.setTipologia("Bloccante");
	        aMap.put("UC_ERR_003", e003);

	        ErroreTaccuino e004 = new ErroreTaccuino();
	        e004.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e004.setCodice(9001);
	        e004.setTitle("Campi obbligatori mancanti");
	        e004.setDescrizione("Il parametro numero transazione deve essere valorizzato");
	        e004.setTipologia("Bloccante");
	        aMap.put("UC_ERR_004", e004);

	        ErroreTaccuino e005 = new ErroreTaccuino();
	        e005.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e005.setCodice(9001);
	        e005.setTitle("Campi obbligatori mancanti");
	        e005.setDescrizione("Il parametro identificativo IP deve essere valorizzato");
	        e005.setTipologia("Bloccante");
	        aMap.put("UC_ERR_005", e005);

	        ErroreTaccuino e006 = new ErroreTaccuino();
	        e006.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e006.setCodice(9001);
	        e006.setTitle("Campi obbligatori mancanti");
	        e006.setDescrizione("Il parametro Ruolo Utente deve essere valorizzato");
	        e006.setTipologia("Bloccante");
	        aMap.put("UC_ERR_006", e006);

	        ErroreTaccuino e007 = new ErroreTaccuino();
	        e007.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e007.setCodice(9001);
	        e007.setTitle("Campi obbligatori mancanti");
	        e007.setDescrizione("Il parametro Codice fiscale Paziente deve essere valorizzato");
	        e007.setTipologia("Bloccante");
	        aMap.put("UC_ERR_007", e007);
	        
	        ErroreTaccuino e008 = new ErroreTaccuino();
	        e008.setStatus(StatusEnum.NOT_FOUND.getStatus());
	        e008.setCodice(9004);
	        e008.setTitle("Valori incongruenti");
	        e008.setDescrizione("Paziente inesistente, non Ã¨ possibile visualizzare il taccuino");
	        e008.setTipologia("Bloccante");
	        aMap.put("UC_ERR_008", e008);

	        ErroreTaccuino e009 = new ErroreTaccuino();
	        e009.setStatus(StatusEnum.FORBIDDEN.getStatus());
	        e009.setCodice(9003);
	        e009.setTitle("Azione non consentita, l'utente non ha i permessi necessari");
	        e009.setDescrizione("Taccuino non consultabile perchÃ© il paziente ha il FSE Ã¨ chiuso");
	        e009.setTipologia("Bloccante");
	        aMap.put("UC_ERR_009", e009);

	        ErroreTaccuino e010 = new ErroreTaccuino();
	        e010.setStatus(StatusEnum.FORBIDDEN.getStatus());
	        e010.setCodice(9003);
	        e010.setTitle("Azione non consentita, l'utente non ha i permessi necessari");
	        e010.setDescrizione("Il richiedente non coincide con il paziente o con un suo delegato");
	        e010.setTipologia("Bloccante");
	        aMap.put("UC_ERR_010", e010);

	        ErroreTaccuino e011 = new ErroreTaccuino();
	        e011.setStatus(StatusEnum.NOT_FOUND.getStatus());
	        e011.setCodice(9004);
	        e011.setTitle("Valori incongruenti");
	        e011.setDescrizione("Taccuino non consultabile perchÃ© non Ã¨ mai stato creato");
	        e011.setTipologia("Bloccante");
	        aMap.put("UC_ERR_011", e011);

	        ErroreTaccuino e012 = new ErroreTaccuino();
	        e012.setStatus(StatusEnum.FORBIDDEN.getStatus());
	        e012.setCodice(9003);
	        e012.setTitle("Azione non consentita, l'utente non ha i permessi necessari");
	        e012.setDescrizione("L'utente non ha i permessi per visualizzare il taccuino");
	        e012.setTipologia("Bloccante");
	        aMap.put("UC_ERR_012", e012);
	        
	        ErroreTaccuino e013 = new ErroreTaccuino();
	        e013.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e013.setCodice(9001);
	        e013.setTitle("Campi obbligatori mancanti");
	        e013.setDescrizione("Il parametro identificativo Taccuino deve essere valorizzato");
	        e013.setTipologia("Bloccante");
	        aMap.put("UC_ERR_013", e013);
	        
	        ErroreTaccuino e018 = new ErroreTaccuino();
	        e018.setStatus(StatusEnum.NOT_FOUND.getStatus());
	        e018.setCodice(9004);
	        e018.setTitle("Valori incongruenti");
	        e018.setDescrizione("L'identificativo taccuino inviato non esiste o non appartiene al paziente indicato");
	        e018.setTipologia("Bloccante");
	        aMap.put("UC_ERR_018", e018);

	        ErroreTaccuino e019 = new ErroreTaccuino();
	        e019.setStatus(StatusEnum.FORBIDDEN.getStatus());
	        e019.setCodice(9003);
	        e019.setTitle("Azione non consentita, l'utente non ha i permessi necessari");
	        e019.setDescrizione("Il delegato non puÃ² visualizzare il taccuino perchÃ© il paziente ha il FSE chiuso");
	        e019.setTipologia("Bloccante");
	        aMap.put("UC_ERR_019", e019);
	        
	        ErroreTaccuino e023 = new ErroreTaccuino();
		    e023.setStatus(StatusEnum.NOT_FOUND.getStatus());
		    e023.setCodice(9004);
		    e023.setTitle("Valori incongruenti");
		    e023.setDescrizione("Applicazione non abilitata ad accedere a DMA");
		    e023.setTipologia("Bloccante");
			aMap.put("UC_ERR_023", e023);	
			
		    ErroreTaccuino e071 = new ErroreTaccuino();
	        e071.setStatus(StatusEnum.SERVER_ERROR.getStatus());
	        e071.setCodice(9999);
	        e071.setTitle("Errore di sistema");
	        e071.setDescrizione("Si e' verificato un errore. Si prega di riprovare");
	        e071.setTipologia("Bloccante");
	        aMap.put("UC_ERR_071", e071);

	        ErroreTaccuino e076 = new ErroreTaccuino();
	        e076.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e076.setCodice(9002);
	        e076.setTitle("Valori non validi");
	        e076.setDescrizione("Codice fiscale non valido");
	        e076.setTipologia("Bloccante");
	        aMap.put("UC_ERR_076", e076);

	        ErroreTaccuino e079 = new ErroreTaccuino();
	        e079.setStatus(StatusEnum.BAD_REQUEST.getStatus());
	        e079.setCodice(9002);
	        e079.setTitle("Valori non validi");
	        e079.setDescrizione("Il valore del parametro applicativo verticale non Ã¨ valido");
	        e079.setTipologia("Bloccante");
	        aMap.put("UC_ERR_079", e079);

	        myMap = Collections.unmodifiableMap(aMap);
	    }
	

	public static ErroreTaccuino getData(String key)		
	{		
		return myMap.get(key);
	}

	

}
