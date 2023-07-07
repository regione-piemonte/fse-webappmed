/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.sql.Timestamp;

public class LLog {

	private long id;
	private Timestamp dataInserimento;
	private String codiceTokenOperazione;
	private String informazioniTracciate;
	private long idCatalogoLog;
	private String idTransazione;
	private String codiceServizio;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public String getCodiceTokenOperazione() {
		return codiceTokenOperazione;
	}
	public void setCodiceTokenOperazione(String codiceTokenOperazione) {
		this.codiceTokenOperazione = codiceTokenOperazione;
	}
	public String getInformazioniTracciate() {
		return informazioniTracciate;
	}
	public void setInformazioniTracciate(String informazioniTracciate) {
		this.informazioniTracciate = informazioniTracciate;
	}
	public long getIdCatalogoLog() {
		return idCatalogoLog;
	}
	public void setIdCatalogoLog(long idCatalogoLog) {
		this.idCatalogoLog = idCatalogoLog;
	}
	public String getIdTransazione() {
		return idTransazione;
	}
	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
}
