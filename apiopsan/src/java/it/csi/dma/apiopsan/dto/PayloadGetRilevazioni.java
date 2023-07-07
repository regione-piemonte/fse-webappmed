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
import java.util.List;
import javax.validation.constraints.*;

public class PayloadGetRilevazioni   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<String> listaCodiceGruppo = new ArrayList<String>();
  private List<String> listaCodiceDescrittore = new ArrayList<String>();

  /**
   **/
  

  @JsonProperty("lista_codice_gruppo") 
 
  public List<String> getListaCodiceGruppo() {
    return listaCodiceGruppo;
  }
  public void setListaCodiceGruppo(List<String> listaCodiceGruppo) {
    this.listaCodiceGruppo = listaCodiceGruppo;
  }

  /**
   **/
  

  @JsonProperty("lista_codice_descrittore") 
 
  public List<String> getListaCodiceDescrittore() {
    return listaCodiceDescrittore;
  }
  public void setListaCodiceDescrittore(List<String> listaCodiceDescrittore) {
    this.listaCodiceDescrittore = listaCodiceDescrittore;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadGetRilevazioni payloadGetRilevazioni = (PayloadGetRilevazioni) o;
    return Objects.equals(listaCodiceGruppo, payloadGetRilevazioni.listaCodiceGruppo) &&
        Objects.equals(listaCodiceDescrittore, payloadGetRilevazioni.listaCodiceDescrittore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(listaCodiceGruppo, listaCodiceDescrittore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadGetRilevazioni {\n");
    
    sb.append("    listaCodiceGruppo: ").append(toIndentedString(listaCodiceGruppo)).append("\n");
    sb.append("    listaCodiceDescrittore: ").append(toIndentedString(listaCodiceDescrittore)).append("\n");
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

