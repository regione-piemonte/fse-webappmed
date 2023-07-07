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
import it.csi.dma.apiopsan.dto.Dolore;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetDolori   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroDolori = null;
  private List<Dolore> elencoDolori = new ArrayList<Dolore>();

  /**
   **/
  

  @JsonProperty("numero_dolori") 
 
  public Integer getNumeroDolori() {
    return numeroDolori;
  }
  public void setNumeroDolori(Integer numeroDolori) {
    this.numeroDolori = numeroDolori;
  }

  /**
   **/
  

  @JsonProperty("elenco_dolori") 
 
  public List<Dolore> getElencoDolori() {
    return elencoDolori;
  }
  public void setElencoDolori(List<Dolore> elencoDolori) {
    this.elencoDolori = elencoDolori;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetDolori esitoGetDolori = (EsitoGetDolori) o;
    return Objects.equals(numeroDolori, esitoGetDolori.numeroDolori) &&
        Objects.equals(elencoDolori, esitoGetDolori.elencoDolori);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroDolori, elencoDolori);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetDolori {\n");
    
    sb.append("    numeroDolori: ").append(toIndentedString(numeroDolori)).append("\n");
    sb.append("    elencoDolori: ").append(toIndentedString(elencoDolori)).append("\n");
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

