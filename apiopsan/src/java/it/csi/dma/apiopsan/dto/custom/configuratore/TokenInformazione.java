/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom.configuratore;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.dma.apiopsan.dto.Funzionalita;

public class TokenInformazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5561818645809850349L;

	@JsonProperty("richiedente")
	private Richiedente richiedente;

	@JsonProperty("parametri_login")
	private List<ParametroLogin> parametriLogin;

	@JsonProperty("funzionalita")
	private List<Funzionalita> funzionalita;

	public Richiedente getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(Richiedente richiedente) {
		this.richiedente = richiedente;
	}

	public List<ParametroLogin> getParametriLogin() {
		return parametriLogin;
	}

	public void setParametriLogin(List<ParametroLogin> parametriLogin) {
		this.parametriLogin = parametriLogin;
	}

	public List<Funzionalita> getFunzionalita() {
		return funzionalita;
	}

	public void setFunzionalita(List<Funzionalita> funzionalita) {
		this.funzionalita = funzionalita;
	}

	@Override
	public String toString() {
		return "TokenInformazione [richiedente=" + richiedente + ", parametriLogin=" + parametriLogin
				+ ", funzionalita=" + funzionalita + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
