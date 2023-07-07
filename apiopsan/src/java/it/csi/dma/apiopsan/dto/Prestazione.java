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

public class Prestazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private String codice = null;
  private String descrizione = null;
  private Date dataPrestazione = null;
  private Codice branca = null;

  /**
   * id della prestazione
   **/
  

  @JsonProperty("id") 
 
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * codice della prestazione
   **/
  

  @JsonProperty("codice") 
 
  public String getCodice() {
    return codice;
  }
  public void setCodice(String codice) {
    this.codice = codice;
  }

  /**
   * descrizione della prestazione
   **/
  

  @JsonProperty("descrizione") 
 
  public String getDescrizione() {
    return descrizione;
  }
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * data della prestazione
   **/
  

  @JsonProperty("data_prestazione") 
 
  public Date getDataPrestazione() {
    return dataPrestazione;
  }
  public void setDataPrestazione(Date dataPrestazione) {
    this.dataPrestazione = dataPrestazione;
  }

  /**
   **/
  

  @JsonProperty("branca") 
 
  public Codice getBranca() {
    return branca;
  }
  public void setBranca(Codice branca) {
    this.branca = branca;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Prestazione prestazione = (Prestazione) o;
    return Objects.equals(id, prestazione.id) &&
        Objects.equals(codice, prestazione.codice) &&
        Objects.equals(descrizione, prestazione.descrizione) &&
        Objects.equals(dataPrestazione, prestazione.dataPrestazione) &&
        Objects.equals(branca, prestazione.branca);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codice, descrizione, dataPrestazione, branca);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Prestazione {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    dataPrestazione: ").append(toIndentedString(dataPrestazione)).append("\n");
    sb.append("    branca: ").append(toIndentedString(branca)).append("\n");
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

