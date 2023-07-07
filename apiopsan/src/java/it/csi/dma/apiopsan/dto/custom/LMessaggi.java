/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;

import java.sql.Timestamp;

public class LMessaggi {

	private String wso2Id;
	private String servizioXml;
	private String uuid;
	private String chiamante;
	private String idMessaggioOrig;
	private String cfUtente;
	private Integer statoXml;
	private Timestamp dataRicezione;
	private Timestamp dataIns;
	private Timestamp dataInvioServizio;
	private Timestamp dataRispostaServizio;
	private Timestamp dataMod;
	private Timestamp dataRisposta;
	private String ruoloUtente;
	private String codiceServizio;
	private String applVerticale;
	private String ipRichiedente;
	private long idXml;
	private String codEsitoRispostaServizio;
	private String infoAggiuntiveErrore;
	private String cfAssistito;
	
	public String getWso2Id() {
		return wso2Id;
	}
	public void setWso2Id(String wso2Id) {
		this.wso2Id = wso2Id;
	}
	public String getServizioXml() {
		return servizioXml;
	}
	public void setServizioXml(String servizioXml) {
		this.servizioXml = servizioXml;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getChiamante() {
		return chiamante;
	}
	public void setChiamante(String chiamante) {
		this.chiamante = chiamante;
	}
	public String getIdMessaggioOrig() {
		return idMessaggioOrig;
	}
	public void setIdMessaggioOrig(String idMessaggioOrig) {
		this.idMessaggioOrig = idMessaggioOrig;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public Integer getStatoXml() {
		return statoXml;
	}
	public void setStatoXml(Integer statoXml) {
		this.statoXml = statoXml;
	}
	public Timestamp getDataRicezione() {
		return dataRicezione;
	}
	public void setDataRicezione(Timestamp dataRicezione) {
		this.dataRicezione = dataRicezione;
	}
	public Timestamp getDataIns() {
		return dataIns;
	}
	public void setDataIns(Timestamp dataIns) {
		this.dataIns = dataIns;
	}
	public Timestamp getDataRispostaServizio() {
		return dataRispostaServizio;
	}
	public void setDataRispostaServizio(Timestamp dataRispostaServizio) {
		this.dataRispostaServizio = dataRispostaServizio;
	}
	public Timestamp getDataMod() {
		return dataMod;
	}
	public void setDataMod(Timestamp dataMod) {
		this.dataMod = dataMod;
	}
	public Timestamp getDataRisposta() {
		return dataRisposta;
	}
	public void setDataRisposta(Timestamp dataRisposta) {
		this.dataRisposta = dataRisposta;
	}
	public String getRuoloUtente() {
		return ruoloUtente;
	}
	public void setRuoloUtente(String ruoloUtente) {
		this.ruoloUtente = ruoloUtente;
	}
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public String getApplVerticale() {
		return applVerticale;
	}
	public void setApplVerticale(String applVerticale) {
		this.applVerticale = applVerticale;
	}
	public String getIpRichiedente() {
		return ipRichiedente;
	}
	public void setIpRichiedente(String ipRichiedente) {
		this.ipRichiedente = ipRichiedente;
	}
	public long getIdXml() {
		return idXml;
	}
	public void setIdXml(long idXml) {
		this.idXml = idXml;
	}
	public String getInfoAggiuntiveErrore() {
		return infoAggiuntiveErrore;
	}
	public void setInfoAggiuntiveErrore(String infoAggiuntiveErrore) {
		this.infoAggiuntiveErrore = infoAggiuntiveErrore;
	}
	public Timestamp getDataInvioServizio() {
		return dataInvioServizio;
	}
	public void setDataInvioServizio(Timestamp dataInvioServizio) {
		this.dataInvioServizio = dataInvioServizio;
	}
	public String getCodEsitoRispostaServizio() {
		return codEsitoRispostaServizio;
	}
	public void setCodEsitoRispostaServizio(String codEsitoRispostaServizio) {
		this.codEsitoRispostaServizio = codEsitoRispostaServizio;
	}
	public String getCfAssistito() {
		return cfAssistito;
	}
	public void setCfAssistito(String cfAssistito) {
		this.cfAssistito = cfAssistito;
	}
	
}
