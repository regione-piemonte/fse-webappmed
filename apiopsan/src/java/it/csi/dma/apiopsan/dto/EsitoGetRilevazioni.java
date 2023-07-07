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
import it.csi.dma.apiopsan.dto.Rilevazione;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetRilevazioni   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroRilevazioni = null;
  private List<Rilevazione> elencoRilevazioni = new ArrayList<Rilevazione>();

  /**
   **/
  

  @JsonProperty("numero_rilevazioni") 
 
  public Integer getNumeroRilevazioni() {
    return numeroRilevazioni;
  }
  public void setNumeroRilevazioni(Integer numeroRilevazioni) {
    this.numeroRilevazioni = numeroRilevazioni;
  }

  /**
   **/
  

  @JsonProperty("elenco_rilevazioni") 
 
  public List<Rilevazione> getElencoRilevazioni() {
    return elencoRilevazioni;
  }
  public void setElencoRilevazioni(List<Rilevazione> elencoRilevazioni) {
    this.elencoRilevazioni = elencoRilevazioni;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetRilevazioni esitoGetRilevazioni = (EsitoGetRilevazioni) o;
    return Objects.equals(numeroRilevazioni, esitoGetRilevazioni.numeroRilevazioni) &&
        Objects.equals(elencoRilevazioni, esitoGetRilevazioni.elencoRilevazioni);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroRilevazioni, elencoRilevazioni);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetRilevazioni {\n");
    
    sb.append("    numeroRilevazioni: ").append(toIndentedString(numeroRilevazioni)).append("\n");
    sb.append("    elencoRilevazioni: ").append(toIndentedString(elencoRilevazioni)).append("\n");
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

