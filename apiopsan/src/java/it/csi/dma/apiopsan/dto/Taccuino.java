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
import it.csi.dma.apiopsan.dto.TaccuinoNotaGenerale;
import it.csi.dma.apiopsan.dto.TaccuinoPreferenza;
import java.util.List;
import javax.validation.constraints.*;

public class Taccuino   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private Boolean oscurato = null;
  private List<TaccuinoPreferenza> preferenze = new ArrayList<TaccuinoPreferenza>();
  private TaccuinoNotaGenerale notaGenerale = null;

  /**
   **/
  

  @JsonProperty("id") 
 
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   **/
  

  @JsonProperty("oscurato") 
 
  public Boolean isOscurato() {
    return oscurato;
  }
  public void setOscurato(Boolean oscurato) {
    this.oscurato = oscurato;
  }

  /**
   **/
  

  @JsonProperty("preferenze") 
 
  public List<TaccuinoPreferenza> getPreferenze() {
    return preferenze;
  }
  public void setPreferenze(List<TaccuinoPreferenza> preferenze) {
    this.preferenze = preferenze;
  }

  /**
   **/
  

  @JsonProperty("nota_generale") 
 
  public TaccuinoNotaGenerale getNotaGenerale() {
    return notaGenerale;
  }
  public void setNotaGenerale(TaccuinoNotaGenerale notaGenerale) {
    this.notaGenerale = notaGenerale;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Taccuino taccuino = (Taccuino) o;
    return Objects.equals(id, taccuino.id) &&
        Objects.equals(oscurato, taccuino.oscurato) &&
        Objects.equals(preferenze, taccuino.preferenze) &&
        Objects.equals(notaGenerale, taccuino.notaGenerale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, oscurato, preferenze, notaGenerale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Taccuino {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    oscurato: ").append(toIndentedString(oscurato)).append("\n");
    sb.append("    preferenze: ").append(toIndentedString(preferenze)).append("\n");
    sb.append("    notaGenerale: ").append(toIndentedString(notaGenerale)).append("\n");
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

