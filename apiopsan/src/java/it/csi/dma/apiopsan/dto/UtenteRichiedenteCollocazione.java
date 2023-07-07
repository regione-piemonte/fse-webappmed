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

public class UtenteRichiedenteCollocazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceCollocazione = null;
  private String descrizioneCollocazione = null;
  private String codiceAzienda = null;
  private String descrizioneAzienda = null;

  /**
   **/
  

  @JsonProperty("codice_collocazione") 
 
  public String getCodiceCollocazione() {
    return codiceCollocazione;
  }
  public void setCodiceCollocazione(String codiceCollocazione) {
    this.codiceCollocazione = codiceCollocazione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_collocazione") 
 
  public String getDescrizioneCollocazione() {
    return descrizioneCollocazione;
  }
  public void setDescrizioneCollocazione(String descrizioneCollocazione) {
    this.descrizioneCollocazione = descrizioneCollocazione;
  }

  /**
   **/
  

  @JsonProperty("codice_azienda") 
 
  public String getCodiceAzienda() {
    return codiceAzienda;
  }
  public void setCodiceAzienda(String codiceAzienda) {
    this.codiceAzienda = codiceAzienda;
  }

  /**
   **/
  

  @JsonProperty("descrizione_azienda") 
 
  public String getDescrizioneAzienda() {
    return descrizioneAzienda;
  }
  public void setDescrizioneAzienda(String descrizioneAzienda) {
    this.descrizioneAzienda = descrizioneAzienda;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UtenteRichiedenteCollocazione utenteRichiedenteCollocazione = (UtenteRichiedenteCollocazione) o;
    return Objects.equals(codiceCollocazione, utenteRichiedenteCollocazione.codiceCollocazione) &&
        Objects.equals(descrizioneCollocazione, utenteRichiedenteCollocazione.descrizioneCollocazione) &&
        Objects.equals(codiceAzienda, utenteRichiedenteCollocazione.codiceAzienda) &&
        Objects.equals(descrizioneAzienda, utenteRichiedenteCollocazione.descrizioneAzienda);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceCollocazione, descrizioneCollocazione, codiceAzienda, descrizioneAzienda);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UtenteRichiedenteCollocazione {\n");
    
    sb.append("    codiceCollocazione: ").append(toIndentedString(codiceCollocazione)).append("\n");
    sb.append("    descrizioneCollocazione: ").append(toIndentedString(descrizioneCollocazione)).append("\n");
    sb.append("    codiceAzienda: ").append(toIndentedString(codiceAzienda)).append("\n");
    sb.append("    descrizioneAzienda: ").append(toIndentedString(descrizioneAzienda)).append("\n");
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

