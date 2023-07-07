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
import it.csi.dma.apiopsan.dto.Sintomo;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetSintomi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroSintomi = null;
  private List<Sintomo> elencoSintomi = new ArrayList<Sintomo>();

  /**
   **/
  

  @JsonProperty("numero_sintomi") 
 
  public Integer getNumeroSintomi() {
    return numeroSintomi;
  }
  public void setNumeroSintomi(Integer numeroSintomi) {
    this.numeroSintomi = numeroSintomi;
  }

  /**
   **/
  

  @JsonProperty("elenco_sintomi") 
 
  public List<Sintomo> getElencoSintomi() {
    return elencoSintomi;
  }
  public void setElencoSintomi(List<Sintomo> elencoSintomi) {
    this.elencoSintomi = elencoSintomi;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetSintomi esitoGetSintomi = (EsitoGetSintomi) o;
    return Objects.equals(numeroSintomi, esitoGetSintomi.numeroSintomi) &&
        Objects.equals(elencoSintomi, esitoGetSintomi.elencoSintomi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroSintomi, elencoSintomi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetSintomi {\n");
    
    sb.append("    numeroSintomi: ").append(toIndentedString(numeroSintomi)).append("\n");
    sb.append("    elencoSintomi: ").append(toIndentedString(elencoSintomi)).append("\n");
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

