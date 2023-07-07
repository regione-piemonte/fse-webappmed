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

import java.util.Date;
import javax.validation.constraints.*;

public class Sintomo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private String descrizione = null;
  private String areaInteressata = null;
  private Date dataInizio = null;
  private Date dataFine = null;
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
  

  @JsonProperty("area_interessata") 
 
  public String getAreaInteressata() {
    return areaInteressata;
  }
  public void setAreaInteressata(String areaInteressata) {
    this.areaInteressata = areaInteressata;
  }

  /**
   **/
  

  @JsonProperty("data_inizio") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataInizio() {
    return dataInizio;
  }
  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  /**
   **/
  

  @JsonProperty("data_fine") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataFine() {
    return dataFine;
  }
  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
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
    Sintomo sintomo = (Sintomo) o;
    return Objects.equals(id, sintomo.id) &&
        Objects.equals(descrizione, sintomo.descrizione) &&
        Objects.equals(areaInteressata, sintomo.areaInteressata) &&
        Objects.equals(dataInizio, sintomo.dataInizio) &&
        Objects.equals(dataFine, sintomo.dataFine) &&
        Objects.equals(dataCreazione, sintomo.dataCreazione) &&
        Objects.equals(dataAggiornamento, sintomo.dataAggiornamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descrizione, areaInteressata, dataInizio, dataFine, dataCreazione, dataAggiornamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sintomo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    areaInteressata: ").append(toIndentedString(areaInteressata)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
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

