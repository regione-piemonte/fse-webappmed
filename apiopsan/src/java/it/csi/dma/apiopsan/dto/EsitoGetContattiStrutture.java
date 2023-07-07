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
import it.csi.dma.apiopsan.dto.ContattoStruttura;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetContattiStrutture   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroContattiStrutture = null;
  private List<ContattoStruttura> elencoContattiStrutture = new ArrayList<ContattoStruttura>();

  /**
   **/
  

  @JsonProperty("numero_contatti_strutture") 
 
  public Integer getNumeroContattiStrutture() {
    return numeroContattiStrutture;
  }
  public void setNumeroContattiStrutture(Integer numeroContattiStrutture) {
    this.numeroContattiStrutture = numeroContattiStrutture;
  }

  /**
   **/
  

  @JsonProperty("elenco_contatti_strutture") 
 
  public List<ContattoStruttura> getElencoContattiStrutture() {
    return elencoContattiStrutture;
  }
  public void setElencoContattiStrutture(List<ContattoStruttura> elencoContattiStrutture) {
    this.elencoContattiStrutture = elencoContattiStrutture;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetContattiStrutture esitoGetContattiStrutture = (EsitoGetContattiStrutture) o;
    return Objects.equals(numeroContattiStrutture, esitoGetContattiStrutture.numeroContattiStrutture) &&
        Objects.equals(elencoContattiStrutture, esitoGetContattiStrutture.elencoContattiStrutture);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroContattiStrutture, elencoContattiStrutture);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetContattiStrutture {\n");
    
    sb.append("    numeroContattiStrutture: ").append(toIndentedString(numeroContattiStrutture)).append("\n");
    sb.append("    elencoContattiStrutture: ").append(toIndentedString(elencoContattiStrutture)).append("\n");
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

