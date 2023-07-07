/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.util.Date;

public class DmaccidxTDocumento {
	private Long idDocumentoIlec;
	private Long idEpisodioIlec;
	private Long idPazIrec;
	private String idRepositoryCl;
	private String codiceAziendaSanitaria;
	private String codCl;
	private String codiceDocumentoDipartimentale;
	private Date dataValidazione;
	private String firmatoDigitalmente;

	private String pagatoTicket;
	private String codTipoDocumento;
	private String codTipoFile;
	public Long getIdDocumentoIlec() {
		return idDocumentoIlec;
	}
	public void setIdDocumentoIlec(Long idDocumentoIlec) {
		this.idDocumentoIlec = idDocumentoIlec;
	}
	public Long getIdEpisodioIlec() {
		return idEpisodioIlec;
	}
	public void setIdEpisodioIlec(Long idEpisodioIlec) {
		this.idEpisodioIlec = idEpisodioIlec;
	}
	public Long getIdPazIrec() {
		return idPazIrec;
	}
	public void setIdPazIrec(Long idPazIrec) {
		this.idPazIrec = idPazIrec;
	}
	public String getIdRepositoryCl() {
		return idRepositoryCl;
	}
	public void setIdRepositoryCl(String idRepositoryCl) {
		this.idRepositoryCl = idRepositoryCl;
	}
	public String getCodiceAziendaSanitaria() {
		return codiceAziendaSanitaria;
	}
	public void setCodiceAziendaSanitaria(String codiceAziendaSanitaria) {
		this.codiceAziendaSanitaria = codiceAziendaSanitaria;
	}
	public String getCodCl() {
		return codCl;
	}
	public void setCodCl(String codCl) {
		this.codCl = codCl;
	}
	public String getCodiceDocumentoDipartimentale() {
		return codiceDocumentoDipartimentale;
	}
	public void setCodiceDocumentoDipartimentale(String codiceDocumentoDipartimentale) {
		this.codiceDocumentoDipartimentale = codiceDocumentoDipartimentale;
	}
	public Date getDataValidazione() {
		return dataValidazione;
	}
	public void setDataValidazione(Date dataValidazione) {
		this.dataValidazione = dataValidazione;
	}
	public String getFirmatoDigitalmente() {
		return firmatoDigitalmente;
	}
	public void setFirmatoDigitalmente(String firmatoDigitalmente) {
		this.firmatoDigitalmente = firmatoDigitalmente;
	}
	public String getPagatoTicket() {
		return pagatoTicket;
	}
	public void setPagatoTicket(String pagatoTicket) {
		this.pagatoTicket = pagatoTicket;
	}
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getCodTipoFile() {
		return codTipoFile;
	}
	public void setCodTipoFile(String codTipoFile) {
		this.codTipoFile = codTipoFile;
	}
	
	

}
