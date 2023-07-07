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
import javax.validation.constraints.*;

public class EsitoIsDocumentoRestituibile   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Boolean restituibile = null;
  private Codice esito = null;

  /**
   **/
  

  @JsonProperty("restituibile") 
 
  public Boolean isRestituibile() {
    return restituibile;
  }
  public void setRestituibile(Boolean restituibile) {
    this.restituibile = restituibile;
  }

  /**
   **/
  

  @JsonProperty("esito") 
 
  public Codice getEsito() {
    return esito;
  }
  public void setEsito(Codice esito) {
    this.esito = esito;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoIsDocumentoRestituibile esitoIsDocumentoRestituibile = (EsitoIsDocumentoRestituibile) o;
    return Objects.equals(restituibile, esitoIsDocumentoRestituibile.restituibile) &&
        Objects.equals(esito, esitoIsDocumentoRestituibile.esito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restituibile, esito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoIsDocumentoRestituibile {\n");
    
    sb.append("    restituibile: ").append(toIndentedString(restituibile)).append("\n");
    sb.append("    esito: ").append(toIndentedString(esito)).append("\n");
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

