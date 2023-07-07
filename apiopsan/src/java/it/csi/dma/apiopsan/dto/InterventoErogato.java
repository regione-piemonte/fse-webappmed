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
import com.fasterxml.jackson.annotation.JsonFormat;

import it.csi.dma.apiopsan.dto.Codice;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

public class InterventoErogato   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Date data = null;
  private List<Codice> trattamenti = new ArrayList<Codice>();
  private Codice strutturaSanitaria = null;
  private String esito = null;

  /**
   **/
  

  @JsonProperty("data") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   **/
  

  @JsonProperty("trattamenti") 
 
  public List<Codice> getTrattamenti() {
    return trattamenti;
  }
  public void setTrattamenti(List<Codice> trattamenti) {
    this.trattamenti = trattamenti;
  }

  /**
   **/
  

  @JsonProperty("struttura_sanitaria") 
 
  public Codice getStrutturaSanitaria() {
    return strutturaSanitaria;
  }
  public void setStrutturaSanitaria(Codice strutturaSanitaria) {
    this.strutturaSanitaria = strutturaSanitaria;
  }

  /**
   **/
  

  @JsonProperty("esito") 
 
  public String getEsito() {
    return esito;
  }
  public void setEsito(String esito) {
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
    InterventoErogato interventoErogato = (InterventoErogato) o;
    return Objects.equals(data, interventoErogato.data) &&
        Objects.equals(trattamenti, interventoErogato.trattamenti) &&
        Objects.equals(strutturaSanitaria, interventoErogato.strutturaSanitaria) &&
        Objects.equals(esito, interventoErogato.esito);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, trattamenti, strutturaSanitaria, esito);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InterventoErogato {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    trattamenti: ").append(toIndentedString(trattamenti)).append("\n");
    sb.append("    strutturaSanitaria: ").append(toIndentedString(strutturaSanitaria)).append("\n");
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

