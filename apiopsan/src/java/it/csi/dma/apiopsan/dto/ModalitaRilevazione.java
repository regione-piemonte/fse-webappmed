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
import javax.validation.constraints.*;

public class ModalitaRilevazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String descrizioneRegionale = null;
  private String descrizioneNazionale = null;

  /**
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   **/
  

  @JsonProperty("descrizione_regionale") 
 
  public String getDescrizioneRegionale() {
    return descrizioneRegionale;
  }
  public void setDescrizioneRegionale(String descrizioneRegionale) {
    this.descrizioneRegionale = descrizioneRegionale;
  }

  /**
   **/
  

  @JsonProperty("descrizione_nazionale") 
 
  public String getDescrizioneNazionale() {
    return descrizioneNazionale;
  }
  public void setDescrizioneNazionale(String descrizioneNazionale) {
    this.descrizioneNazionale = descrizioneNazionale;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModalitaRilevazione modalitaRilevazione = (ModalitaRilevazione) o;
    return Objects.equals(codice, modalitaRilevazione.codice) &&
        Objects.equals(descrizioneRegionale, modalitaRilevazione.descrizioneRegionale) &&
        Objects.equals(descrizioneNazionale, modalitaRilevazione.descrizioneNazionale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, descrizioneRegionale, descrizioneNazionale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModalitaRilevazione {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizioneRegionale: ").append(toIndentedString(descrizioneRegionale)).append("\n");
    sb.append("    descrizioneNazionale: ").append(toIndentedString(descrizioneNazionale)).append("\n");
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

