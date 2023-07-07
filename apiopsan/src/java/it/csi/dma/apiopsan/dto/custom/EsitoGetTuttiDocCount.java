/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.dma.apiopsan.dto.BaseDTO;
import it.csi.dma.apiopsan.dto.ConteggioDocumenti;

public class EsitoGetTuttiDocCount extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 2089571279320514698L;
	
	private Integer numeroDocumenti = null;
	private ConteggioDocumenti conteggioDocumenti = new ConteggioDocumenti();

	/**
	 **/

	@JsonProperty("numero_documenti")

	public Integer getNumeroDocumenti() {
		return numeroDocumenti;
	}

	public void setNumeroDocumenti(Integer numeroDocumenti) {
		this.numeroDocumenti = numeroDocumenti;
	}

	/**
	 **/

	@JsonProperty("conteggio_documenti")

	public ConteggioDocumenti getConteggioDocumenti() {
		return conteggioDocumenti;
	}

	public void setConteggioDocumenti(ConteggioDocumenti conteggioDocumenti) {
		this.conteggioDocumenti = conteggioDocumenti;
	}
	
	public void addConteggioDocumento(Integer numeroDocumenti, String cittadinoId) {
		getConteggioDocumenti().setTotale(numeroDocumenti.longValue());
		getConteggioDocumenti().setCitId(cittadinoId);
		
	}
	

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EsitoGetTuttiDocCount esitoGetSoloEpisodi = (EsitoGetTuttiDocCount) o;
		return Objects.equals(numeroDocumenti, esitoGetSoloEpisodi.numeroDocumenti);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroDocumenti, conteggioDocumenti);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class EsitoGetTuttiDocCount {\n");

		sb.append("    numeroDocumenti: ").append(toIndentedString(numeroDocumenti)).append("\n");
		sb.append("    elencoDocumenti: ").append(toIndentedString(conteggioDocumenti)).append("\n");
		sb.append("}");
		return sb.toString();
	}


}
