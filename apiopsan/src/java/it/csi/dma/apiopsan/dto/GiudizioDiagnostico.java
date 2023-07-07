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
import it.csi.dma.apiopsan.dto.MedicoTipoSingolo;
import java.util.List;
import javax.validation.constraints.*;

public class GiudizioDiagnostico   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Codice giudizio = null;
  private List<Codice> prestazioni = new ArrayList<Codice>();
  private List<MedicoTipoSingolo> medici = new ArrayList<MedicoTipoSingolo>();

  /**
   **/
  

  @JsonProperty("giudizio") 
 
  public Codice getGiudizio() {
    return giudizio;
  }
  public void setGiudizio(Codice giudizio) {
    this.giudizio = giudizio;
  }

  /**
   **/
  

  @JsonProperty("prestazioni") 
 
  public List<Codice> getPrestazioni() {
    return prestazioni;
  }
  public void setPrestazioni(List<Codice> prestazioni) {
    this.prestazioni = prestazioni;
  }

  /**
   **/
  

  @JsonProperty("medici") 
 
  public List<MedicoTipoSingolo> getMedici() {
    return medici;
  }
  public void setMedici(List<MedicoTipoSingolo> medici) {
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
    GiudizioDiagnostico giudizioDiagnostico = (GiudizioDiagnostico) o;
    return Objects.equals(giudizio, giudizioDiagnostico.giudizio) &&
        Objects.equals(prestazioni, giudizioDiagnostico.prestazioni) &&
        Objects.equals(medici, giudizioDiagnostico.medici);
  }

  @Override
  public int hashCode() {
    return Objects.hash(giudizio, prestazioni, medici);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GiudizioDiagnostico {\n");
    
    sb.append("    giudizio: ").append(toIndentedString(giudizio)).append("\n");
    sb.append("    prestazioni: ").append(toIndentedString(prestazioni)).append("\n");
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

