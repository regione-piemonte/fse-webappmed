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
import java.util.Date;
import javax.validation.constraints.*;

public class Dieta   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private Date data = null;
  private String colazioneDescrizione = null;
  private Integer colazioneCalorie = null;
  private String pranzoDescrizione = null;
  private Integer pranzoCalorie = null;
  private String cenaDescrizione = null;
  private Integer cenaCalorie = null;
  private String spuntiniDescrizione = null;
  private Integer spuntiniCalorie = null;
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
  

  @JsonProperty("data") 
 
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   **/
  

  @JsonProperty("colazione_descrizione") 
 
  public String getColazioneDescrizione() {
    return colazioneDescrizione;
  }
  public void setColazioneDescrizione(String colazioneDescrizione) {
    this.colazioneDescrizione = colazioneDescrizione;
  }

  /**
   **/
  

  @JsonProperty("colazione_calorie") 
 
  public Integer getColazioneCalorie() {
    return colazioneCalorie;
  }
  public void setColazioneCalorie(Integer colazioneCalorie) {
    this.colazioneCalorie = colazioneCalorie;
  }

  /**
   **/
  

  @JsonProperty("pranzo_descrizione") 
 
  public String getPranzoDescrizione() {
    return pranzoDescrizione;
  }
  public void setPranzoDescrizione(String pranzoDescrizione) {
    this.pranzoDescrizione = pranzoDescrizione;
  }

  /**
   **/
  

  @JsonProperty("pranzo_calorie") 
 
  public Integer getPranzoCalorie() {
    return pranzoCalorie;
  }
  public void setPranzoCalorie(Integer pranzoCalorie) {
    this.pranzoCalorie = pranzoCalorie;
  }

  /**
   **/
  

  @JsonProperty("cena_descrizione") 
 
  public String getCenaDescrizione() {
    return cenaDescrizione;
  }
  public void setCenaDescrizione(String cenaDescrizione) {
    this.cenaDescrizione = cenaDescrizione;
  }

  /**
   **/
  

  @JsonProperty("cena_calorie") 
 
  public Integer getCenaCalorie() {
    return cenaCalorie;
  }
  public void setCenaCalorie(Integer cenaCalorie) {
    this.cenaCalorie = cenaCalorie;
  }

  /**
   **/
  

  @JsonProperty("spuntini_descrizione") 
 
  public String getSpuntiniDescrizione() {
    return spuntiniDescrizione;
  }
  public void setSpuntiniDescrizione(String spuntiniDescrizione) {
    this.spuntiniDescrizione = spuntiniDescrizione;
  }

  /**
   **/
  

  @JsonProperty("spuntini_calorie") 
 
  public Integer getSpuntiniCalorie() {
    return spuntiniCalorie;
  }
  public void setSpuntiniCalorie(Integer spuntiniCalorie) {
    this.spuntiniCalorie = spuntiniCalorie;
  }

  /**
   **/
  

  @JsonProperty("data_creazione") 
 
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
  }

  /**
   **/
  

  @JsonProperty("data_aggiornamento") 
 
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
    Dieta dieta = (Dieta) o;
    return Objects.equals(id, dieta.id) &&
        Objects.equals(data, dieta.data) &&
        Objects.equals(colazioneDescrizione, dieta.colazioneDescrizione) &&
        Objects.equals(colazioneCalorie, dieta.colazioneCalorie) &&
        Objects.equals(pranzoDescrizione, dieta.pranzoDescrizione) &&
        Objects.equals(pranzoCalorie, dieta.pranzoCalorie) &&
        Objects.equals(cenaDescrizione, dieta.cenaDescrizione) &&
        Objects.equals(cenaCalorie, dieta.cenaCalorie) &&
        Objects.equals(spuntiniDescrizione, dieta.spuntiniDescrizione) &&
        Objects.equals(spuntiniCalorie, dieta.spuntiniCalorie) &&
        Objects.equals(dataCreazione, dieta.dataCreazione) &&
        Objects.equals(dataAggiornamento, dieta.dataAggiornamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, data, colazioneDescrizione, colazioneCalorie, pranzoDescrizione, pranzoCalorie, cenaDescrizione, cenaCalorie, spuntiniDescrizione, spuntiniCalorie, dataCreazione, dataAggiornamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Dieta {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    colazioneDescrizione: ").append(toIndentedString(colazioneDescrizione)).append("\n");
    sb.append("    colazioneCalorie: ").append(toIndentedString(colazioneCalorie)).append("\n");
    sb.append("    pranzoDescrizione: ").append(toIndentedString(pranzoDescrizione)).append("\n");
    sb.append("    pranzoCalorie: ").append(toIndentedString(pranzoCalorie)).append("\n");
    sb.append("    cenaDescrizione: ").append(toIndentedString(cenaDescrizione)).append("\n");
    sb.append("    cenaCalorie: ").append(toIndentedString(cenaCalorie)).append("\n");
    sb.append("    spuntiniDescrizione: ").append(toIndentedString(spuntiniDescrizione)).append("\n");
    sb.append("    spuntiniCalorie: ").append(toIndentedString(spuntiniCalorie)).append("\n");
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

