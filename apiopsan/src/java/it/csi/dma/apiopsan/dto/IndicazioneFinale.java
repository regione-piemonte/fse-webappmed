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
import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Medico;
import java.util.List;
import javax.validation.constraints.*;

public class IndicazioneFinale   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Codice giudizione = null;
  private List<Medico> medici = new ArrayList<Medico>();

  /**
   **/
  

  @JsonProperty("giudizione") 
 
  public Codice getGiudizione() {
    return giudizione;
  }
  public void setGiudizione(Codice giudizione) {
    this.giudizione = giudizione;
  }

  /**
   **/
  

  @JsonProperty("medici") 
 
  public List<Medico> getMedici() {
    return medici;
  }
  public void setMedici(List<Medico> medici) {
    this.medici = medici;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndicazioneFinale indicazioneFinale = (IndicazioneFinale) o;
    return Objects.equals(giudizione, indicazioneFinale.giudizione) &&
        Objects.equals(medici, indicazioneFinale.medici);
  }

  @Override
  public int hashCode() {
    return Objects.hash(giudizione, medici);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndicazioneFinale {\n");
    
    sb.append("    giudizione: ").append(toIndentedString(giudizione)).append("\n");
    sb.append("    medici: ").append(toIndentedString(medici)).append("\n");
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

