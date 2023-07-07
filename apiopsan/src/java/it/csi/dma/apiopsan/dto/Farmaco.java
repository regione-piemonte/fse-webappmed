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

public class Farmaco   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private String descrizione = null;
  private String quantita = null;
  private Date dataAssunzione = null;
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
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   **/
  

  @JsonProperty("quantita") 
 
  public String getQuantita() {
    return quantita;
  }
  public void setQuantita(String quantita) {
    this.quantita = quantita;
  }

  /**
   **/
  

  @JsonProperty("data_assunzione") 
 
  public Date getDataAssunzione() {
    return dataAssunzione;
  }
  public void setDataAssunzione(Date dataAssunzione) {
    this.dataAssunzione = dataAssunzione;
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
    Farmaco farmaco = (Farmaco) o;
    return Objects.equals(id, farmaco.id) &&
        Objects.equals(descrizione, farmaco.descrizione) &&
        Objects.equals(quantita, farmaco.quantita) &&
        Objects.equals(dataAssunzione, farmaco.dataAssunzione) &&
        Objects.equals(dataCreazione, farmaco.dataCreazione) &&
        Objects.equals(dataAggiornamento, farmaco.dataAggiornamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descrizione, quantita, dataAssunzione, dataCreazione, dataAggiornamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Farmaco {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
    sb.append("    dataAssunzione: ").append(toIndentedString(dataAssunzione)).append("\n");
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

