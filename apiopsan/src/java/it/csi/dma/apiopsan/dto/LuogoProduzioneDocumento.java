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

public class LuogoProduzioneDocumento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Codice azienda = null;
  private Codice struttura = null;
  private Codice unitaOperativa = null;

  /**
   **/
  

  @JsonProperty("azienda") 
 
  public Codice getAzienda() {
    return azienda;
  }
  public void setAzienda(Codice azienda) {
    this.azienda = azienda;
  }

  /**
   **/
  

  @JsonProperty("struttura") 
 
  public Codice getStruttura() {
    return struttura;
  }
  public void setStruttura(Codice struttura) {
    this.struttura = struttura;
  }

  /**
   **/
  

  @JsonProperty("unita_operativa") 
 
  public Codice getUnitaOperativa() {
    return unitaOperativa;
  }
  public void setUnitaOperativa(Codice unitaOperativa) {
    this.unitaOperativa = unitaOperativa;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LuogoProduzioneDocumento luogoProduzioneDocumento = (LuogoProduzioneDocumento) o;
    return Objects.equals(azienda, luogoProduzioneDocumento.azienda) &&
        Objects.equals(struttura, luogoProduzioneDocumento.struttura) &&
        Objects.equals(unitaOperativa, luogoProduzioneDocumento.unitaOperativa);
  }

  @Override
  public int hashCode() {
    return Objects.hash(azienda, struttura, unitaOperativa);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LuogoProduzioneDocumento {\n");
    
    sb.append("    azienda: ").append(toIndentedString(azienda)).append("\n");
    sb.append("    struttura: ").append(toIndentedString(struttura)).append("\n");
    sb.append("    unitaOperativa: ").append(toIndentedString(unitaOperativa)).append("\n");
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

