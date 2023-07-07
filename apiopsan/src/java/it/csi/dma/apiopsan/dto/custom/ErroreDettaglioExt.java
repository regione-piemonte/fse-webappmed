/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import it.csi.dma.apiopsan.dto.ErroreDettaglio;

public class ErroreDettaglioExt extends ErroreDettaglio  {
	private Integer erroreId;

	public Integer getErroreId() {
		return erroreId;
	}

	public void setErroreId(Integer erroreId) {
		this.erroreId = erroreId;
	}
	
	public ErroreDettaglio wrap() {
		ErroreDettaglio erroreDettaglio = new ErroreDettaglio();
		erroreDettaglio.setChiave(this.getChiave());
		erroreDettaglio.setValore(this.getValore());
		return erroreDettaglio;
		
	}
}

