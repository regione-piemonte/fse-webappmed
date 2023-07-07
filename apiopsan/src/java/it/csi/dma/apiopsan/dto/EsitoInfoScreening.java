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
import it.csi.dma.apiopsan.dto.Paziente;
import it.csi.dma.apiopsan.dto.Screening;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoInfoScreening   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Paziente paziente = null;
  private List<Screening> infoSistemiScreening = new ArrayList<Screening>();

  /**
   **/
  

  @JsonProperty("paziente") 
 
  public Paziente getPaziente() {
    return paziente;
  }
  public void setPaziente(Paziente paziente) {
    this.paziente = paziente;
  }

  /**
   **/
  

  @JsonProperty("info_sistemi_screening") 
 
  public List<Screening> getInfoSistemiScreening() {
    return infoSistemiScreening;
  }
  public void setInfoSistemiScreening(List<Screening> infoSistemiScreening) {
    this.infoSistemiScreening = infoSistemiScreening;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoInfoScreening esitoInfoScreening = (EsitoInfoScreening) o;
    return Objects.equals(paziente, esitoInfoScreening.paziente) &&
        Objects.equals(infoSistemiScreening, esitoInfoScreening.infoSistemiScreening);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paziente, infoSistemiScreening);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoInfoScreening {\n");
    
    sb.append("    paziente: ").append(toIndentedString(paziente)).append("\n");
    sb.append("    infoSistemiScreening: ").append(toIndentedString(infoSistemiScreening)).append("\n");
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

