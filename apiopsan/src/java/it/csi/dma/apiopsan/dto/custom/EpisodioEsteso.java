/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import it.csi.dma.apiopsan.dto.Episodio;

public class EpisodioEsteso extends Episodio {

	private Long idDocumentoIlec;
	private String classe;

	public Long getIdDocumentoIlec() {
		return idDocumentoIlec;
	}

	public void setIdDocumentoIlec(Long idDocumentoIlec) {
		this.idDocumentoIlec = idDocumentoIlec;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	

}
