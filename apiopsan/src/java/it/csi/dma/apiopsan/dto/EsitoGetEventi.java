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
import it.csi.dma.apiopsan.dto.Evento;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetEventi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroEventi = null;
  private List<Evento> elencoEventi = new ArrayList<Evento>();

  /**
   **/
  

  @JsonProperty("numero_eventi") 
 
  public Integer getNumeroEventi() {
    return numeroEventi;
  }
  public void setNumeroEventi(Integer numeroEventi) {
    this.numeroEventi = numeroEventi;
  }

  /**
   **/
  

  @JsonProperty("elenco_eventi") 
 
  public List<Evento> getElencoEventi() {
    return elencoEventi;
  }
  public void setElencoEventi(List<Evento> elencoEventi) {
    this.elencoEventi = elencoEventi;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetEventi esitoGetEventi = (EsitoGetEventi) o;
    return Objects.equals(numeroEventi, esitoGetEventi.numeroEventi) &&
        Objects.equals(elencoEventi, esitoGetEventi.elencoEventi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroEventi, elencoEventi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetEventi {\n");
    
    sb.append("    numeroEventi: ").append(toIndentedString(numeroEventi)).append("\n");
    sb.append("    elencoEventi: ").append(toIndentedString(elencoEventi)).append("\n");
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

