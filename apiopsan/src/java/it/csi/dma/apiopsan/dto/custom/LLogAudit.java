/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.sql.Timestamp;

public class LLogAudit extends LLog{
	
	
	private Long idPaziente;
	private Long idRegime;
	private long idApplicazioneRichiedente;
	private Long idUtente;
	private long idRuolo;
	private String collocazione;
	private long idContestoOperativo;
	private Long iddocumento;
	private String componentelocale;
	private String ipRichiedente;
	private String nomeServizio;
	private String ipChiamante;
	private String codiceFiscaleUtente;
	private String applicazioneVerticale;
	
	public Long getIdPaziente() {
		return idPaziente;
	}
	public void setIdPaziente(Long idPaziente) {
		this.idPaziente = idPaziente;
	}
	public Long getIdRegime() {
		return idRegime;
	}
	public void setIdRegime(Long idRegime) {
		this.idRegime = idRegime;
	}
	public long getIdApplicazioneRichiedente() {
		return idApplicazioneRichiedente;
	}
	public void setIdApplicazioneRichiedente(long idApplicazioneRichiedente) {
		this.idApplicazioneRichiedente = idApplicazioneRichiedente;
	}
	public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public long getIdRuolo() {
		return idRuolo;
	}
	public void setIdRuolo(long idRuolo) {
		this.idRuolo = idRuolo;
	}
	public String getCollocazione() {
		return collocazione;
	}
	public void setCollocazione(String collocazione) {
		this.collocazione = collocazione;
	}
	public long getIdContestoOperativo() {
		return idContestoOperativo;
	}
	public void setIdContestoOperativo(long idContestoOperativo) {
		this.idContestoOperativo = idContestoOperativo;
	}
	public Long getIddocumento() {
		return iddocumento;
	}
	public void setIddocumento(Long iddocumento) {
		this.iddocumento = iddocumento;
	}
	public String getComponentelocale() {
		return componentelocale;
	}
	public void setComponentelocale(String componentelocale) {
		this.componentelocale = componentelocale;
	}
	public String getIpRichiedente() {
		return ipRichiedente;
	}
	public void setIpRichiedente(String ipRichiedente) {
		this.ipRichiedente = ipRichiedente;
	}
	public String getNomeServizio() {
		return nomeServizio;
	}
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}
	public String getIpChiamante() {
		return ipChiamante;
	}
	public void setIpChiamante(String ipChiamante) {
		this.ipChiamante = ipChiamante;
	}
	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}
	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}
	public String getApplicazioneVerticale() {
		return applicazioneVerticale;
	}
	public void setApplicazioneVerticale(String applicazioneVerticale) {
		this.applicazioneVerticale = applicazioneVerticale;
	}
}
