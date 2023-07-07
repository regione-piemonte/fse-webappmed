/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.util.Date;

public class DmaccTDocumentiQrcode {
	private Long id;
	private Long idPaziente;
	private String codiceFiscale;
	private Long idComponenteLocale;
	private String codCl;
	private Long idReferto;
	private Long idDocumento;
	private Long idEpisodio;	
	private String codiceDocumentoDipartimentale;
	private String token;
	private Date dataInserimento;
	private Date dataAggiornamento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPaziente() {
		return idPaziente;
	}
	public void setIdPaziente(Long idPaziente) {
		this.idPaziente = idPaziente;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Long getIdComponenteLocale() {
		return idComponenteLocale;
	}
	public void setIdComponenteLocale(Long idComponenteLocale) {
		this.idComponenteLocale = idComponenteLocale;
	}
	public String getCodCl() {
		return codCl;
	}
	public void setCodCl(String codCl) {
		this.codCl = codCl;
	}
	public Long getIdReferto() {
		return idReferto;
	}
	public void setIdReferto(Long idReferto) {
		this.idReferto = idReferto;
	}
	public Long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public Long getIdEpisodio() {
		return idEpisodio;
	}
	public void setIdEpisodio(Long idEpisodio) {
		this.idEpisodio = idEpisodio;
	}
	public String getCodiceDocumentoDipartimentale() {
		return codiceDocumentoDipartimentale;
	}
	public void setCodiceDocumentoDipartimentale(String codiceDocumentoDipartimentale) {
		this.codiceDocumentoDipartimentale = codiceDocumentoDipartimentale;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	
	
}
