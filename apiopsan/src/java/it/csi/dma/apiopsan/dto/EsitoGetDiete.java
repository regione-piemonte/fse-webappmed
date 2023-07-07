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
import it.csi.dma.apiopsan.dto.Dieta;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetDiete   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroDiete = null;
  private List<Dieta> elencoDiete = new ArrayList<Dieta>();

  /**
   **/
  

  @JsonProperty("numero_diete") 
 
  public Integer getNumeroDiete() {
    return numeroDiete;
  }
  public void setNumeroDiete(Integer numeroDiete) {
    this.numeroDiete = numeroDiete;
  }

  /**
   **/
  

  @JsonProperty("elenco_diete") 
 
  public List<Dieta> getElencoDiete() {
    return elencoDiete;
  }
  public void setElencoDiete(List<Dieta> elencoDiete) {
    this.elencoDiete = elencoDiete;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetDiete esitoGetDiete = (EsitoGetDiete) o;
    return Objects.equals(numeroDiete, esitoGetDiete.numeroDiete) &&
        Objects.equals(elencoDiete, esitoGetDiete.elencoDiete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroDiete, elencoDiete);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetDiete {\n");
    
    sb.append("    numeroDiete: ").append(toIndentedString(numeroDiete)).append("\n");
    sb.append("    elencoDiete: ").append(toIndentedString(elencoDiete)).append("\n");
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

