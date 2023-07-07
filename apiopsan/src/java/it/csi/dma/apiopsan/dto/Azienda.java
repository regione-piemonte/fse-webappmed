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

public class Azienda   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codice = null;
  private String descrizione = null;
  private String descrizioneStruttura = null;
  private String descrizioneUnitaOperativa = null;
  private String matricolaUpDipartimentale = null;

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
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_struttura") 
 
  public String getDescrizioneStruttura() {
    return descrizioneStruttura;
  }
  public void setDescrizioneStruttura(String descrizioneStruttura) {
    this.descrizioneStruttura = descrizioneStruttura;
  }

  /**
   **/
  

  @JsonProperty("descrizione_unita_operativa") 
 
  public String getDescrizioneUnitaOperativa() {
    return descrizioneUnitaOperativa;
  }
  public void setDescrizioneUnitaOperativa(String descrizioneUnitaOperativa) {
    this.descrizioneUnitaOperativa = descrizioneUnitaOperativa;
  }

  /**
   **/
  

  @JsonProperty("matricola_up_dipartimentale") 
 
  public String getMatricolaUpDipartimentale() {
    return matricolaUpDipartimentale;
  }
  public void setMatricolaUpDipartimentale(String matricolaUpDipartimentale) {
    this.matricolaUpDipartimentale = matricolaUpDipartimentale;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Azienda azienda = (Azienda) o;
    return Objects.equals(codice, azienda.codice) &&
        Objects.equals(descrizione, azienda.descrizione) &&
        Objects.equals(descrizioneStruttura, azienda.descrizioneStruttura) &&
        Objects.equals(descrizioneUnitaOperativa, azienda.descrizioneUnitaOperativa) &&
        Objects.equals(matricolaUpDipartimentale, azienda.matricolaUpDipartimentale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codice, descrizione, descrizioneStruttura, descrizioneUnitaOperativa, matricolaUpDipartimentale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Azienda {\n");
    
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    descrizioneStruttura: ").append(toIndentedString(descrizioneStruttura)).append("\n");
    sb.append("    descrizioneUnitaOperativa: ").append(toIndentedString(descrizioneUnitaOperativa)).append("\n");
    sb.append("    matricolaUpDipartimentale: ").append(toIndentedString(matricolaUpDipartimentale)).append("\n");
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

