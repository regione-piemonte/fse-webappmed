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

public class EsitoIsDocumentoMediabile   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Boolean mediabile = null;
  private Codice esito = null;

  /**
   **/
  

  @JsonProperty("mediabile") 
 
  public Boolean isMediabile() {
    return mediabile;
  }
  public void setMediabile(Boolean mediabile) {
    this.mediabile = mediabile;
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
    EsitoIsDocumentoMediabile esitoIsDocumentoMediabile = (EsitoIsDocumentoMediabile) o;
    return Objects.equals(mediabile, esitoIsDocumentoMediabile.mediabile) &&
        Objects.equals(esito, esitoIsDocumentoMediabile.esito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mediabile, esito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoIsDocumentoMediabile {\n");
    
    sb.append("    mediabile: ").append(toIndentedString(mediabile)).append("\n");
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

