/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.dma.apiopsan.dto.BaseDTO;
import it.csi.dma.apiopsan.dto.SintesiDocumento;

import java.util.List;

public class EsitoGetTuttiDoc extends BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 8929322672208510016L;
	// verra' utilizzata la seguente strategia serializzazione degli attributi:
	// [explicit-as-modeled]

	private Integer numeroDocumenti = null;
	private List<SintesiDocumento> elencoDocumenti = new ArrayList<SintesiDocumento>();

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

	@JsonProperty("elenco_documenti")

	public List<SintesiDocumento> getElencoDocumenti() {
		return elencoDocumenti;
	}

	public void setElencoDocumenti(List<SintesiDocumento> elencoDocumenti) {
		this.elencoDocumenti = elencoDocumenti;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EsitoGetTuttiDoc esitoGetSoloEpisodi = (EsitoGetTuttiDoc) o;
		return Objects.equals(numeroDocumenti, esitoGetSoloEpisodi.numeroDocumenti)
				&& Objects.equals(elencoDocumenti, esitoGetSoloEpisodi.elencoDocumenti);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroDocumenti, elencoDocumenti);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class EsitoGetTuttiDoc {\n");

		sb.append("    numeroDocumenti: ").append(toIndentedString(numeroDocumenti)).append("\n");
		sb.append("    elencoDocumenti: ").append(toIndentedString(elencoDocumenti)).append("\n");
		sb.append("}");
		return sb.toString();
	}

}
