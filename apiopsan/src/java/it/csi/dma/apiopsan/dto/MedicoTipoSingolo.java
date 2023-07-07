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

public class MedicoTipoSingolo   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cognome = null;
  private String nome = null;
  private String figuraProfessionale = null;

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
  

  @JsonProperty("figura_professionale") 
 
  public String getFiguraProfessionale() {
    return figuraProfessionale;
  }
  public void setFiguraProfessionale(String figuraProfessionale) {
    this.figuraProfessionale = figuraProfessionale;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MedicoTipoSingolo medicoTipoSingolo = (MedicoTipoSingolo) o;
    return Objects.equals(cognome, medicoTipoSingolo.cognome) &&
        Objects.equals(nome, medicoTipoSingolo.nome) &&
        Objects.equals(figuraProfessionale, medicoTipoSingolo.figuraProfessionale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cognome, nome, figuraProfessionale);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MedicoTipoSingolo {\n");
    
    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    figuraProfessionale: ").append(toIndentedString(figuraProfessionale)).append("\n");
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

