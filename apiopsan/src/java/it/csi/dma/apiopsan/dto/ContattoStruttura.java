/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import it.csi.dma.apiopsan.dto.Codice;
import java.util.Date;
import javax.validation.constraints.*;

public class ContattoStruttura   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private String denominazione = null;
  private Date dataContattoInizio = null;
  private Date dataContattoFine = null;
  private String motivazione = null;
  private String medicinaNonConvTipoContatto = null;
  private Codice tipologiaContatto = null;
  private Codice tipologiaStruttura = null;
  private Date dataCreazione = null;
  private Date dataAggiornamento = null;

  /**
   **/
  

  @JsonProperty("id") 
 
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   **/
  

  @JsonProperty("denominazione") 
 
  public String getDenominazione() {
    return denominazione;
  }
  public void setDenominazione(String denominazione) {
    this.denominazione = denominazione;
  }

  /**
   **/
  

  @JsonProperty("data_contatto_inizio") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataContattoInizio() {
    return dataContattoInizio;
  }
  public void setDataContattoInizio(Date dataContattoInizio) {
    this.dataContattoInizio = dataContattoInizio;
  }

  /**
   **/
  

  @JsonProperty("data_contatto_fine") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataContattoFine() {
    return dataContattoFine;
  }
  public void setDataContattoFine(Date dataContattoFine) {
    this.dataContattoFine = dataContattoFine;
  }

  /**
   **/
  

  @JsonProperty("motivazione") 
 
  public String getMotivazione() {
    return motivazione;
  }
  public void setMotivazione(String motivazione) {
    this.motivazione = motivazione;
  }

  /**
   **/
  

  @JsonProperty("medicina_non_conv_tipo_contatto") 
 
  public String getMedicinaNonConvTipoContatto() {
    return medicinaNonConvTipoContatto;
  }
  public void setMedicinaNonConvTipoContatto(String medicinaNonConvTipoContatto) {
    this.medicinaNonConvTipoContatto = medicinaNonConvTipoContatto;
  }

  /**
   **/
  

  @JsonProperty("tipologia_contatto") 
 
  public Codice getTipologiaContatto() {
    return tipologiaContatto;
  }
  public void setTipologiaContatto(Codice tipologiaContatto) {
    this.tipologiaContatto = tipologiaContatto;
  }

  /**
   **/
  

  @JsonProperty("tipologia_struttura") 
 
  public Codice getTipologiaStruttura() {
    return tipologiaStruttura;
  }
  public void setTipologiaStruttura(Codice tipologiaStruttura) {
    this.tipologiaStruttura = tipologiaStruttura;
  }

  /**
   **/
  

  @JsonProperty("data_creazione") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
  }

  /**
   **/
  

  @JsonProperty("data_aggiornamento") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataAggiornamento() {
    return dataAggiornamento;
  }
  public void setDataAggiornamento(Date dataAggiornamento) {
    this.dataAggiornamento = dataAggiornamento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContattoStruttura contattoStruttura = (ContattoStruttura) o;
    return Objects.equals(id, contattoStruttura.id) &&
        Objects.equals(denominazione, contattoStruttura.denominazione) &&
        Objects.equals(dataContattoInizio, contattoStruttura.dataContattoInizio) &&
        Objects.equals(dataContattoFine, contattoStruttura.dataContattoFine) &&
        Objects.equals(motivazione, contattoStruttura.motivazione) &&
        Objects.equals(medicinaNonConvTipoContatto, contattoStruttura.medicinaNonConvTipoContatto) &&
        Objects.equals(tipologiaContatto, contattoStruttura.tipologiaContatto) &&
        Objects.equals(tipologiaStruttura, contattoStruttura.tipologiaStruttura) &&
        Objects.equals(dataCreazione, contattoStruttura.dataCreazione) &&
        Objects.equals(dataAggiornamento, contattoStruttura.dataAggiornamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, denominazione, dataContattoInizio, dataContattoFine, motivazione, medicinaNonConvTipoContatto, tipologiaContatto, tipologiaStruttura, dataCreazione, dataAggiornamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContattoStruttura {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    denominazione: ").append(toIndentedString(denominazione)).append("\n");
    sb.append("    dataContattoInizio: ").append(toIndentedString(dataContattoInizio)).append("\n");
    sb.append("    dataContattoFine: ").append(toIndentedString(dataContattoFine)).append("\n");
    sb.append("    motivazione: ").append(toIndentedString(motivazione)).append("\n");
    sb.append("    medicinaNonConvTipoContatto: ").append(toIndentedString(medicinaNonConvTipoContatto)).append("\n");
    sb.append("    tipologiaContatto: ").append(toIndentedString(tipologiaContatto)).append("\n");
    sb.append("    tipologiaStruttura: ").append(toIndentedString(tipologiaStruttura)).append("\n");
    sb.append("    dataCreazione: ").append(toIndentedString(dataCreazione)).append("\n");
    sb.append("    dataAggiornamento: ").append(toIndentedString(dataAggiornamento)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

