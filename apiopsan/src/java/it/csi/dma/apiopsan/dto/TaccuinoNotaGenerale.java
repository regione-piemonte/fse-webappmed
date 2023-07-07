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

public class TaccuinoNotaGenerale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private String statoSalute = null;
  private String stileVita = null;
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
  

  @JsonProperty("stato_salute") 
 
  public String getStatoSalute() {
    return statoSalute;
  }
  public void setStatoSalute(String statoSalute) {
    this.statoSalute = statoSalute;
  }

  /**
   **/
  

  @JsonProperty("stile_vita") 
 
  public String getStileVita() {
    return stileVita;
  }
  public void setStileVita(String stileVita) {
    this.stileVita = stileVita;
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
    TaccuinoNotaGenerale taccuinoNotaGenerale = (TaccuinoNotaGenerale) o;
    return Objects.equals(id, taccuinoNotaGenerale.id) &&
        Objects.equals(statoSalute, taccuinoNotaGenerale.statoSalute) &&
        Objects.equals(stileVita, taccuinoNotaGenerale.stileVita) &&
        Objects.equals(dataCreazione, taccuinoNotaGenerale.dataCreazione) &&
        Objects.equals(dataAggiornamento, taccuinoNotaGenerale.dataAggiornamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, statoSalute, stileVita, dataCreazione, dataAggiornamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaccuinoNotaGenerale {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    statoSalute: ").append(toIndentedString(statoSalute)).append("\n");
    sb.append("    stileVita: ").append(toIndentedString(stileVita)).append("\n");
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

