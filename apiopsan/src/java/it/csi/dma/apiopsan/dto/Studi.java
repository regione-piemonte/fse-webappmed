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
import it.csi.dma.apiopsan.dto.GiorniApertura;
import it.csi.dma.apiopsan.dto.IndirizzoStudio;
import java.util.List;
import javax.validation.constraints.*;

public class Studi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String ambulatorioPubblico = null;
  private String denominazione = null;
  private List<GiorniApertura> giorniApertura = new ArrayList<GiorniApertura>();
  private IndirizzoStudio indirizzoStudio = null;

  /**
   **/
  

  @JsonProperty("ambulatorio_pubblico") 
 
  public String getAmbulatorioPubblico() {
    return ambulatorioPubblico;
  }
  public void setAmbulatorioPubblico(String ambulatorioPubblico) {
    this.ambulatorioPubblico = ambulatorioPubblico;
  }

  /**
   **/
  

  @JsonProperty("denominazione") 
 
  public String getDenominazione() {
    return denominazione;
  }
  public void setDenominazione(String denominazione) {
    this.denominazione = denominazione;
  }

  /**
   **/
  

  @JsonProperty("giorni_apertura") 
 
  public List<GiorniApertura> getGiorniApertura() {
    return giorniApertura;
  }
  public void setGiorniApertura(List<GiorniApertura> giorniApertura) {
    this.giorniApertura = giorniApertura;
  }

  /**
   **/
  

  @JsonProperty("indirizzo_studio") 
 
  public IndirizzoStudio getIndirizzoStudio() {
    return indirizzoStudio;
  }
  public void setIndirizzoStudio(IndirizzoStudio indirizzoStudio) {
    this.indirizzoStudio = indirizzoStudio;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Studi studi = (Studi) o;
    return Objects.equals(ambulatorioPubblico, studi.ambulatorioPubblico) &&
        Objects.equals(denominazione, studi.denominazione) &&
        Objects.equals(giorniApertura, studi.giorniApertura) &&
        Objects.equals(indirizzoStudio, studi.indirizzoStudio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ambulatorioPubblico, denominazione, giorniApertura, indirizzoStudio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Studi {\n");
    
    sb.append("    ambulatorioPubblico: ").append(toIndentedString(ambulatorioPubblico)).append("\n");
    sb.append("    denominazione: ").append(toIndentedString(denominazione)).append("\n");
    sb.append("    giorniApertura: ").append(toIndentedString(giorniApertura)).append("\n");
    sb.append("    indirizzoStudio: ").append(toIndentedString(indirizzoStudio)).append("\n");
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

