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
import java.util.Date;
import javax.validation.constraints.*;

public class Esenzione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Codice codiceEsenzione = null;
  private Date dataEmissione = null;
  private Date dataScadenza = null;
  private Codice diagnosi = null;
  private Codice enteEmittente = null;

  /**
   **/
  

  @JsonProperty("codice_esenzione") 
 
  public Codice getCodiceEsenzione() {
    return codiceEsenzione;
  }
  public void setCodiceEsenzione(Codice codiceEsenzione) {
    this.codiceEsenzione = codiceEsenzione;
  }

  /**
   **/
  

  @JsonProperty("data_emissione") 
 
  public Date getDataEmissione() {
    return dataEmissione;
  }
  public void setDataEmissione(Date dataEmissione) {
    this.dataEmissione = dataEmissione;
  }

  /**
   **/
  

  @JsonProperty("data_scadenza") 
 
  public Date getDataScadenza() {
    return dataScadenza;
  }
  public void setDataScadenza(Date dataScadenza) {
    this.dataScadenza = dataScadenza;
  }

  /**
   **/
  

  @JsonProperty("diagnosi") 
 
  public Codice getDiagnosi() {
    return diagnosi;
  }
  public void setDiagnosi(Codice diagnosi) {
    this.diagnosi = diagnosi;
  }

  /**
   **/
  

  @JsonProperty("ente_emittente") 
 
  public Codice getEnteEmittente() {
    return enteEmittente;
  }
  public void setEnteEmittente(Codice enteEmittente) {
    this.enteEmittente = enteEmittente;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Esenzione esenzione = (Esenzione) o;
    return Objects.equals(codiceEsenzione, esenzione.codiceEsenzione) &&
        Objects.equals(dataEmissione, esenzione.dataEmissione) &&
        Objects.equals(dataScadenza, esenzione.dataScadenza) &&
        Objects.equals(diagnosi, esenzione.diagnosi) &&
        Objects.equals(enteEmittente, esenzione.enteEmittente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceEsenzione, dataEmissione, dataScadenza, diagnosi, enteEmittente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Esenzione {\n");
    
    sb.append("    codiceEsenzione: ").append(toIndentedString(codiceEsenzione)).append("\n");
    sb.append("    dataEmissione: ").append(toIndentedString(dataEmissione)).append("\n");
    sb.append("    dataScadenza: ").append(toIndentedString(dataScadenza)).append("\n");
    sb.append("    diagnosi: ").append(toIndentedString(diagnosi)).append("\n");
    sb.append("    enteEmittente: ").append(toIndentedString(enteEmittente)).append("\n");
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

