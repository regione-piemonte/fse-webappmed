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
import javax.validation.constraints.*;

public class ConteggioDocumenti   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long totale = null;
  private String citId = null;

  /**
   **/
  

  @JsonProperty("totale") 
 
  public Long getTotale() {
    return totale;
  }
  public void setTotale(Long totale) {
    this.totale = totale;
  }

  /**
   **/
  

  @JsonProperty("cit_id") 
 
  public String getCitId() {
    return citId;
  }
  public void setCitId(String citId) {
    this.citId = citId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConteggioDocumenti conteggioDocumenti = (ConteggioDocumenti) o;
    return Objects.equals(totale, conteggioDocumenti.totale) &&
        Objects.equals(citId, conteggioDocumenti.citId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totale, citId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConteggioDocumenti {\n");
    
    sb.append("    totale: ").append(toIndentedString(totale)).append("\n");
    sb.append("    citId: ").append(toIndentedString(citId)).append("\n");
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

