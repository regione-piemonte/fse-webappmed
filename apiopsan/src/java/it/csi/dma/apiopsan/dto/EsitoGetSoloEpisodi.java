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
import it.csi.dma.apiopsan.dto.Episodio;
import java.util.List;
import javax.validation.constraints.*;

public class EsitoGetSoloEpisodi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer numeroEpisodi = null;
  private List<Episodio> elencoEpisodi = new ArrayList<Episodio>();

  /**
   **/
  

  @JsonProperty("numero_episodi") 
 
  public Integer getNumeroEpisodi() {
    return numeroEpisodi;
  }
  public void setNumeroEpisodi(Integer numeroEpisodi) {
    this.numeroEpisodi = numeroEpisodi;
  }

  /**
   **/
  

  @JsonProperty("elenco_episodi") 
 
  public List<Episodio> getElencoEpisodi() {
    return elencoEpisodi;
  }
  public void setElencoEpisodi(List<Episodio> elencoEpisodi) {
    this.elencoEpisodi = elencoEpisodi;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoGetSoloEpisodi esitoGetSoloEpisodi = (EsitoGetSoloEpisodi) o;
    return Objects.equals(numeroEpisodi, esitoGetSoloEpisodi.numeroEpisodi) &&
        Objects.equals(elencoEpisodi, esitoGetSoloEpisodi.elencoEpisodi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroEpisodi, elencoEpisodi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoGetSoloEpisodi {\n");
    
    sb.append("    numeroEpisodi: ").append(toIndentedString(numeroEpisodi)).append("\n");
    sb.append("    elencoEpisodi: ").append(toIndentedString(elencoEpisodi)).append("\n");
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

