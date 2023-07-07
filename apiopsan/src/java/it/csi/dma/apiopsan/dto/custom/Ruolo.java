/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.sql.Date;

public class Ruolo {
	
	Integer id;
	String ruoloDpcm;
	String codiceRuolo;
	String descrizioneRuolo;
	String codiceRuoloIni;
	String descrizioneRuoloIni;
	Date dataInserimento;
	String categoriaRuolo;
	Date dataaggiornamento;
	String flagVisibilePerConsenso;

	
	public Date getDataaggiornamento() {
		return dataaggiornamento;
	}
	public void setDataaggiornamento(Date dataaggiornamento) {
		this.dataaggiornamento = dataaggiornamento;
	}
	
	public String getFlagVisibilePerConsenso() {
		return flagVisibilePerConsenso;
	}
	public void setFlagVisibilePerConsenso(String flagVisibilePerConsenso) {
		this.flagVisibilePerConsenso = flagVisibilePerConsenso;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public String getRuoloDpcm() {
		return ruoloDpcm;
	}
	public void setRuoloDpcm(String ruoloDpcm) {
		this.ruoloDpcm = ruoloDpcm;
	}
	public String getCodiceRuolo() {
		return codiceRuolo;
	}
	public void setCodiceRuolo(String codiceRuolo) {
		this.codiceRuolo = codiceRuolo;
	}
	public String getDescrizioneRuolo() {
		return descrizioneRuolo;
	}
	public void setDescrizioneRuolo(String descrizioneRuolo) {
		this.descrizioneRuolo = descrizioneRuolo;
	}
	public String getCodiceRuoloIni() {
		return codiceRuoloIni;
	}
	public void setCodiceRuoloIni(String codiceRuoloIni) {
		this.codiceRuoloIni = codiceRuoloIni;
	}
	public String getDescrizioneRuoloIni() {
		return descrizioneRuoloIni;
	}
	public void setDescrizioneRuoloIni(String descrizioneRuoloIni) {
		this.descrizioneRuoloIni = descrizioneRuoloIni;
	}
	public String getCategoriaRuolo() {
		return categoriaRuolo;
	}
	public void setCategoriaRuolo(String categoriaRuolo) {
		this.categoriaRuolo = categoriaRuolo;
	}
	
}
