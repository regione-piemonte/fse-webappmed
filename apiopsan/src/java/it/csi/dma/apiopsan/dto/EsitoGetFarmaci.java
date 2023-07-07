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
import it.csi.dma.apiopsan.dto.Farmaco;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetFarmaci   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroFarmaci = null;
  private List<Farmaco> elencoFarmaci = new ArrayList<Farmaco>();

  /**
   **/
  

  @JsonProperty("numero_farmaci") 
 
  public Integer getNumeroFarmaci() {
    return numeroFarmaci;
  }
  public void setNumeroFarmaci(Integer numeroFarmaci) {
    this.numeroFarmaci = numeroFarmaci;
  }

  /**
   **/
  

  @JsonProperty("elenco_farmaci") 
 
  public List<Farmaco> getElencoFarmaci() {
    return elencoFarmaci;
  }
  public void setElencoFarmaci(List<Farmaco> elencoFarmaci) {
    this.elencoFarmaci = elencoFarmaci;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetFarmaci esitoGetFarmaci = (EsitoGetFarmaci) o;
    return Objects.equals(numeroFarmaci, esitoGetFarmaci.numeroFarmaci) &&
        Objects.equals(elencoFarmaci, esitoGetFarmaci.elencoFarmaci);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroFarmaci, elencoFarmaci);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetFarmaci {\n");
    
    sb.append("    numeroFarmaci: ").append(toIndentedString(numeroFarmaci)).append("\n");
    sb.append("    elencoFarmaci: ").append(toIndentedString(elencoFarmaci)).append("\n");
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

