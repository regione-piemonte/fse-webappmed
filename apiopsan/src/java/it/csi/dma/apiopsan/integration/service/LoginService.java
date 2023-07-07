/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Collocazione;
import it.csi.dma.apiopsan.dto.Funzionalita;
import it.csi.dma.apiopsan.dto.Ruolo;
import it.csi.dma.apiopsan.dto.Utente;
import it.csi.dma.apiopsan.dto.UtenteRichiedente;
import it.csi.dma.apiopsan.dto.UtenteRichiedenteCollocazione;
import it.csi.dma.apiopsan.dto.custom.configuratore.HttpHeaderParam;
import it.csi.dma.apiopsan.dto.custom.configuratore.Richiedente;
import it.csi.dma.apiopsan.dto.custom.configuratore.TokenInformazione;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class LoginService extends LoggerUtil {

	@Autowired
	private ConfiguratoreService configuratoreService;

	public Utente getInfoProfiloUtente(HttpHeaderParam httHeaderParams) throws Exception {
		logInfo("getInfoProfiloUtente", "token: " + httHeaderParams.getToken());
		Utente result = new Utente();

		TokenInformazione configuratoreResponse = configuratoreService.sendGetMeByToken(httHeaderParams);
		List<Ruolo> ruoli = configuratoreService.sendGetRuoli(httHeaderParams);
		if (configuratoreResponse != null) {
			UtenteRichiedente utenteRichiedente = fromTokenInformazioneRichiedenteToUtenteRichiedente(
					configuratoreResponse.getRichiedente(), ruoli);
			result.setRichiedente(utenteRichiedente);
			result.setFunzionalitaAbilitate(
					buildProfili(configuratoreResponse, utenteRichiedente.getCollocazione().getCodiceAzienda()));
		}

		return result;

	}

	public List<Ruolo> getRuoli(HttpHeaderParam httHeaderParams) throws Exception {
		logInfo("getInfoProfiloUtente", "token: " + httHeaderParams.getToken());

		return configuratoreService.sendGetRuoli(httHeaderParams);
	}

	public List<Collocazione> getCollocazioni(HttpHeaderParam httHeaderParams, String ruolo) throws Exception {
		logInfo("getInfoProfiloUtente", "token: " + httHeaderParams.getToken());

		return configuratoreService.sendGetCollocazioni(httHeaderParams, ruolo);
	}

	private List<Funzionalita> buildProfili(TokenInformazione tokenInfo, String enteCode) {
		List<Funzionalita> result = new ArrayList<Funzionalita>();
		Map<String, String> profiliMap = getProfiliByTokenInfoFunzionalita(tokenInfo.getFunzionalita());
		Funzionalita tmp = new Funzionalita();
		for (String profiloCode : profiliMap.keySet()) {
			tmp.setCodice(profiloCode);
			tmp.setDescrizione(profiliMap.get(profiloCode));
			result.add(tmp);
			tmp = new Funzionalita();
		}
		return result;
	}

	private Map<String, String> getProfiliByTokenInfoFunzionalita(List<Funzionalita> list) {
		Map<String, String> map = new HashMap<String, String>();
		for (Funzionalita fun : list) {
			if (StringUtils.isNotBlank(fun.getCodiceFunzionalitaPadre())
					&& StringUtils.isNotBlank(fun.getDescrizioneFunzionalitaPadre())) {
				if (!map.containsKey(fun.getCodiceFunzionalitaPadre())) {
					logInfo("getProfiliByTokenInfoFunzionalita", "profilo:" + fun.getCodiceFunzionalitaPadre() + ", "
							+ fun.getDescrizioneFunzionalitaPadre());
					map.put(fun.getCodiceFunzionalitaPadre(), fun.getDescrizioneFunzionalitaPadre());
				}
			}
		}
		return map;
	}

	private UtenteRichiedente fromTokenInformazioneRichiedenteToUtenteRichiedente(Richiedente richiedente,
			List<Ruolo> ruoli) {
		UtenteRichiedente result = new UtenteRichiedente();
		result.setNome(richiedente.getNome());
		result.setCognome(richiedente.getCognome());
		result.setCodiceFiscale(richiedente.getCodiceFiscale());
		result.setCollocazione(fromTokenInfoCollocazioneToUtenteRichiedenteCollocazione(richiedente.getCollocazione()));

		// Info RUOLO
		String codiceRuolo = richiedente.getRuolo();
		String descrizioneRuolo = null;
		Iterator<Ruolo> iterator = ruoli.iterator();
		while (iterator.hasNext()) {
			Ruolo item = iterator.next();

			if (item.getCodice().equalsIgnoreCase(codiceRuolo)) {
				descrizioneRuolo = item.getDescrizione();
			}
		}
		Codice ruolo = new Codice();
		ruolo.setCodice(codiceRuolo);
		ruolo.setDescrizione(descrizioneRuolo);
		result.setRuolo(ruolo);

		return result;
	}

	private UtenteRichiedenteCollocazione fromTokenInfoCollocazioneToUtenteRichiedenteCollocazione(
			Collocazione collocazione) {
		UtenteRichiedenteCollocazione result = new UtenteRichiedenteCollocazione();
		result.setCodiceCollocazione(collocazione.getCodiceCollocazione());
		result.setDescrizioneCollocazione(collocazione.getDescrizioneCollocazione());
		result.setCodiceAzienda(collocazione.getCodiceAzienda());
		result.setDescrizioneAzienda(collocazione.getDescrizioneAzienda());
		return result;
	}
}
