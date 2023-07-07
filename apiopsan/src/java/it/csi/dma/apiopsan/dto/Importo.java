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

public class Importo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Integer ticketDaPagare = null;
  private Integer ticketPagato = null;

  /**
   **/
  

  @JsonProperty("ticket_da_pagare") 
 
  public Integer getTicketDaPagare() {
    return ticketDaPagare;
  }
  public void setTicketDaPagare(Integer ticketDaPagare) {
    this.ticketDaPagare = ticketDaPagare;
  }

  /**
   **/
  

  @JsonProperty("ticket_pagato") 
 
  public Integer getTicketPagato() {
    return ticketPagato;
  }
  public void setTicketPagato(Integer ticketPagato) {
    this.ticketPagato = ticketPagato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Importo importo = (Importo) o;
    return Objects.equals(ticketDaPagare, importo.ticketDaPagare) &&
        Objects.equals(ticketPagato, importo.ticketPagato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketDaPagare, ticketPagato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Importo {\n");
    
    sb.append("    ticketDaPagare: ").append(toIndentedString(ticketDaPagare)).append("\n");
    sb.append("    ticketPagato: ").append(toIndentedString(ticketPagato)).append("\n");
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

