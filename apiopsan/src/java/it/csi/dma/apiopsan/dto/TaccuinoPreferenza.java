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
import it.csi.dma.apiopsan.dto.TaccuinoEntita;
import it.csi.dma.apiopsan.dto.TaccuinoGruppo;
import javax.validation.constraints.*;

public class TaccuinoPreferenza   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private TaccuinoEntita entita = null;
  private TaccuinoGruppo gruppo = null;

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
  

  @JsonProperty("entita") 
 
  public TaccuinoEntita getEntita() {
    return entita;
  }
  public void setEntita(TaccuinoEntita entita) {
    this.entita = entita;
  }

  /**
   **/
  

  @JsonProperty("gruppo") 
 
  public TaccuinoGruppo getGruppo() {
    return gruppo;
  }
  public void setGruppo(TaccuinoGruppo gruppo) {
    this.gruppo = gruppo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaccuinoPreferenza taccuinoPreferenza = (TaccuinoPreferenza) o;
    return Objects.equals(id, taccuinoPreferenza.id) &&
        Objects.equals(entita, taccuinoPreferenza.entita) &&
        Objects.equals(gruppo, taccuinoPreferenza.gruppo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, entita, gruppo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaccuinoPreferenza {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    entita: ").append(toIndentedString(entita)).append("\n");
    sb.append("    gruppo: ").append(toIndentedString(gruppo)).append("\n");
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

