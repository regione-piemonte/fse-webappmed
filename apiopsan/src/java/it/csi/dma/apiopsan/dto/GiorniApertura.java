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

public class GiorniApertura   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String giorno = null;
  private String orarioFine = null;
  private String orarioInizio = null;

  /**
   **/
  

  @JsonProperty("giorno") 
 
  public String getGiorno() {
    return giorno;
  }
  public void setGiorno(String giorno) {
    this.giorno = giorno;
  }

  /**
   **/
  

  @JsonProperty("orario_fine") 
 
  public String getOrarioFine() {
    return orarioFine;
  }
  public void setOrarioFine(String orarioFine) {
    this.orarioFine = orarioFine;
  }

  /**
   **/
  

  @JsonProperty("orario_inizio") 
 
  public String getOrarioInizio() {
    return orarioInizio;
  }
  public void setOrarioInizio(String orarioInizio) {
    this.orarioInizio = orarioInizio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GiorniApertura giorniApertura = (GiorniApertura) o;
    return Objects.equals(giorno, giorniApertura.giorno) &&
        Objects.equals(orarioFine, giorniApertura.orarioFine) &&
        Objects.equals(orarioInizio, giorniApertura.orarioInizio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(giorno, orarioFine, orarioInizio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GiorniApertura {\n");
    
    sb.append("    giorno: ").append(toIndentedString(giorno)).append("\n");
    sb.append("    orarioFine: ").append(toIndentedString(orarioFine)).append("\n");
    sb.append("    orarioInizio: ").append(toIndentedString(orarioInizio)).append("\n");
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

