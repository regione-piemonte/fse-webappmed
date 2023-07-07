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
import javax.validation.constraints.*;

public class Paziente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceFiscale = null;
  private String cognome = null;
  private String nome = null;
  private Date dataNascita = null;
  private Codice comuneNascita = null;
  private Codice statoNascita = null;
  private String sesso = null;
  private Boolean flagRegistryIncarico = null;
  private String idAsr = null;
  private String idAura = null;
  private String idIrec = null;
  private String codiceFiscaleMmg = null;

  /**
   **/
  

  @JsonProperty("codice_fiscale") 
 
  public String getCodiceFiscale() {
    return codiceFiscale;
  }
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
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
  

  @JsonProperty("nome") 
 
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   **/
  

  @JsonProperty("data_nascita") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getDataNascita() {
    return dataNascita;
  }
  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  /**
   **/
  

  @JsonProperty("comune_nascita") 
 
  public Codice getComuneNascita() {
    return comuneNascita;
  }
  public void setComuneNascita(Codice comuneNascita) {
    this.comuneNascita = comuneNascita;
  }

  /**
   **/
  

  @JsonProperty("stato_nascita") 
 
  public Codice getStatoNascita() {
    return statoNascita;
  }
  public void setStatoNascita(Codice statoNascita) {
    this.statoNascita = statoNascita;
  }

  /**
   **/
  

  @JsonProperty("sesso") 
 
  public String getSesso() {
    return sesso;
  }
  public void setSesso(String sesso) {
    this.sesso = sesso;
  }

  /**
   **/
  

  @JsonProperty("flag_registry_incarico") 
 
  public Boolean isFlagRegistryIncarico() {
    return flagRegistryIncarico;
  }
  public void setFlagRegistryIncarico(Boolean flagRegistryIncarico) {
    this.flagRegistryIncarico = flagRegistryIncarico;
  }

  /**
   **/
  

  @JsonProperty("id_asr") 
 
  public String getIdAsr() {
    return idAsr;
  }
  public void setIdAsr(String idAsr) {
    this.idAsr = idAsr;
  }

  /**
   **/
  

  @JsonProperty("id_aura") 
 
  public String getIdAura() {
    return idAura;
  }
  public void setIdAura(String idAura) {
    this.idAura = idAura;
  }

  /**
   **/
  

  @JsonProperty("id_irec") 
 
  public String getIdIrec() {
    return idIrec;
  }
  public void setIdIrec(String idIrec) {
    this.idIrec = idIrec;
  }

  /**
   **/
  

  @JsonProperty("codice_fiscale_mmg") 
 
  public String getCodiceFiscaleMmg() {
    return codiceFiscaleMmg;
  }
  public void setCodiceFiscaleMmg(String codiceFiscaleMmg) {
    this.codiceFiscaleMmg = codiceFiscaleMmg;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Paziente paziente = (Paziente) o;
    return Objects.equals(codiceFiscale, paziente.codiceFiscale) &&
        Objects.equals(cognome, paziente.cognome) &&
        Objects.equals(nome, paziente.nome) &&
        Objects.equals(dataNascita, paziente.dataNascita) &&
        Objects.equals(comuneNascita, paziente.comuneNascita) &&
        Objects.equals(statoNascita, paziente.statoNascita) &&
        Objects.equals(sesso, paziente.sesso) &&
        Objects.equals(flagRegistryIncarico, paziente.flagRegistryIncarico) &&
        Objects.equals(idAsr, paziente.idAsr) &&
        Objects.equals(idAura, paziente.idAura) &&
        Objects.equals(idIrec, paziente.idIrec) &&
        Objects.equals(codiceFiscaleMmg, paziente.codiceFiscaleMmg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceFiscale, cognome, nome, dataNascita, comuneNascita, statoNascita, sesso, flagRegistryIncarico, idAsr, idAura, idIrec, codiceFiscaleMmg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Paziente {\n");
    
    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    dataNascita: ").append(toIndentedString(dataNascita)).append("\n");
    sb.append("    comuneNascita: ").append(toIndentedString(comuneNascita)).append("\n");
    sb.append("    statoNascita: ").append(toIndentedString(statoNascita)).append("\n");
    sb.append("    sesso: ").append(toIndentedString(sesso)).append("\n");
    sb.append("    flagRegistryIncarico: ").append(toIndentedString(flagRegistryIncarico)).append("\n");
    sb.append("    idAsr: ").append(toIndentedString(idAsr)).append("\n");
    sb.append("    idAura: ").append(toIndentedString(idAura)).append("\n");
    sb.append("    idIrec: ").append(toIndentedString(idIrec)).append("\n");
    sb.append("    codiceFiscaleMmg: ").append(toIndentedString(codiceFiscaleMmg)).append("\n");
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

