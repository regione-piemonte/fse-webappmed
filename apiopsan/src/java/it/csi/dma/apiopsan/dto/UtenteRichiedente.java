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
import io.swagger.annotations.ApiModel;
import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.UtenteRichiedenteCollocazione;
import javax.validation.constraints.*;

public class UtenteRichiedente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceFiscale = null;
  private String nome = null;
  private String cognome = null;
  private Codice ruolo = null;
  private UtenteRichiedenteCollocazione collocazione = null;

  /**
   * Il codice fiscale dell&#39;utente
   **/
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Il nome dell&#39;utente
   **/
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Il cognome dell&#39;utente
   **/
  

  @JsonProperty("cognome") 
 
  public String getCognome() {
    return cognome;
  }
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   **/
  

  @JsonProperty("ruolo") 
 
  public Codice getRuolo() {
    return ruolo;
  }
  public void setRuolo(Codice ruolo) {
    this.ruolo = ruolo;
  }

  /**
   **/
  

  @JsonProperty("collocazione") 
 
  public UtenteRichiedenteCollocazione getCollocazione() {
    return collocazione;
  }
  public void setCollocazione(UtenteRichiedenteCollocazione collocazione) {
    this.collocazione = collocazione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UtenteRichiedente utenteRichiedente = (UtenteRichiedente) o;
    return Objects.equals(codiceFiscale, utenteRichiedente.codiceFiscale) &&
        Objects.equals(nome, utenteRichiedente.nome) &&
        Objects.equals(cognome, utenteRichiedente.cognome) &&
        Objects.equals(ruolo, utenteRichiedente.ruolo) &&
        Objects.equals(collocazione, utenteRichiedente.collocazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale, nome, cognome, ruolo, collocazione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UtenteRichiedente {\n");
    
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    ruolo: ").append(toIndentedString(ruolo)).append("\n");
    sb.append("    collocazione: ").append(toIndentedString(collocazione)).append("\n");
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

