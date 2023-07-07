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
import it.csi.dma.apiopsan.dto.MedicoMmg;
import it.csi.dma.apiopsan.dto.Studi;
import java.util.List;
import javax.validation.constraints.*;

public class InfoMmgPaziente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String haMedico = null;
  private MedicoMmg medico = null;
  private List<Studi> studi = new ArrayList<Studi>();

  /**
   **/
  

  @JsonProperty("ha_medico") 
 
  public String getHaMedico() {
    return haMedico;
  }
  public void setHaMedico(String haMedico) {
    this.haMedico = haMedico;
  }

  /**
   **/
  

  @JsonProperty("medico") 
 
  public MedicoMmg getMedico() {
    return medico;
  }
  public void setMedico(MedicoMmg medico) {
    this.medico = medico;
  }

  /**
   **/
  

  @JsonProperty("studi") 
 
  public List<Studi> getStudi() {
    return studi;
  }
  public void setStudi(List<Studi> studi) {
    this.studi = studi;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoMmgPaziente infoMmgPaziente = (InfoMmgPaziente) o;
    return Objects.equals(haMedico, infoMmgPaziente.haMedico) &&
        Objects.equals(medico, infoMmgPaziente.medico) &&
        Objects.equals(studi, infoMmgPaziente.studi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(haMedico, medico, studi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoMmgPaziente {\n");
    
    sb.append("    haMedico: ").append(toIndentedString(haMedico)).append("\n");
    sb.append("    medico: ").append(toIndentedString(medico)).append("\n");
    sb.append("    studi: ").append(toIndentedString(studi)).append("\n");
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

