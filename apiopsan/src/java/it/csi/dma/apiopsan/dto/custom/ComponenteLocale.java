/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

public class ComponenteLocale {
	
	private long idComponenteLocale;
	private String tipoComponenteLocale;
	private String descrizione;
	private String codice;
	private String idRepository;
	private String CodiceUo;
	private long idAziendaSanitaria;
	
	public long getIdComponenteLocale() {
		return idComponenteLocale;
	}
	public void setIdComponenteLocale(long idComponenteLocale) {
		this.idComponenteLocale = idComponenteLocale;
	}
	public String getTipoComponenteLocale() {
		return tipoComponenteLocale;
	}
	public void setTipoComponenteLocale(String tipoComponenteLocale) {
		this.tipoComponenteLocale = tipoComponenteLocale;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getIdRepository() {
		return idRepository;
	}
	public void setIdRepository(String idRepository) {
		this.idRepository = idRepository;
	}
	public String getCodiceUo() {
		return CodiceUo;
	}
	public void setCodiceUo(String codiceUo) {
		CodiceUo = codiceUo;
	}
	public long getIdAziendaSanitaria() {
		return idAziendaSanitaria;
	}
	public void setIdAziendaSanitaria(long idAziendaSanitaria) {
		this.idAziendaSanitaria = idAziendaSanitaria;
	}
	
	
	
}
