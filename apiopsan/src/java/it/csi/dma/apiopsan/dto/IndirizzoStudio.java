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

public class IndirizzoStudio   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cap = null;
  private String descComune = null;
  private String indirizzo = null;
  private String telPrimario = null;
  private String email = null;

  /**
   **/
  

  @JsonProperty("cap") 
 
  public String getCap() {
    return cap;
  }
  public void setCap(String cap) {
    this.cap = cap;
  }

  /**
   **/
  

  @JsonProperty("desc_comune") 
 
  public String getDescComune() {
    return descComune;
  }
  public void setDescComune(String descComune) {
    this.descComune = descComune;
  }

  /**
   **/
  

  @JsonProperty("indirizzo") 
 
  public String getIndirizzo() {
    return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   **/
  

  @JsonProperty("tel_primario") 
 
  public String getTelPrimario() {
    return telPrimario;
  }
  public void setTelPrimario(String telPrimario) {
    this.telPrimario = telPrimario;
  }

  /**
   **/
  

  @JsonProperty("email") 
 
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndirizzoStudio indirizzoStudio = (IndirizzoStudio) o;
    return Objects.equals(cap, indirizzoStudio.cap) &&
        Objects.equals(descComune, indirizzoStudio.descComune) &&
        Objects.equals(indirizzo, indirizzoStudio.indirizzo) &&
        Objects.equals(telPrimario, indirizzoStudio.telPrimario) &&
        Objects.equals(email, indirizzoStudio.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cap, descComune, indirizzo, telPrimario, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndirizzoStudio {\n");
    
    sb.append("    cap: ").append(toIndentedString(cap)).append("\n");
    sb.append("    descComune: ").append(toIndentedString(descComune)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    telPrimario: ").append(toIndentedString(telPrimario)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

