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
import java.util.Date;
import javax.validation.constraints.*;

public class Documento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String allegato = null;
  private String tipoAllegato = null;
  private String nomeFile = null;
  private String trascrizione = null;
  private Date dataOraAggiornamento = null;

  /**
   **/
  

  @JsonProperty("allegato") 
 
  public String getAllegato() {
    return allegato;
  }
  public void setAllegato(String allegato) {
    this.allegato = allegato;
  }

  /**
   **/
  

  @JsonProperty("tipo_allegato") 
 
  public String getTipoAllegato() {
    return tipoAllegato;
  }
  public void setTipoAllegato(String tipoAllegato) {
    this.tipoAllegato = tipoAllegato;
  }

  /**
   **/
  

  @JsonProperty("nome_file") 
 
  public String getNomeFile() {
    return nomeFile;
  }
  public void setNomeFile(String nomeFile) {
    this.nomeFile = nomeFile;
  }

  /**
   **/
  

  @JsonProperty("trascrizione") 
 
  public String getTrascrizione() {
    return trascrizione;
  }
  public void setTrascrizione(String trascrizione) {
    this.trascrizione = trascrizione;
  }

  /**
   **/
  

  @JsonProperty("data_ora_aggiornamento") 
 
  public Date getDataOraAggiornamento() {
    return dataOraAggiornamento;
  }
  public void setDataOraAggiornamento(Date dataOraAggiornamento) {
    this.dataOraAggiornamento = dataOraAggiornamento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Documento documento = (Documento) o;
    return Objects.equals(allegato, documento.allegato) &&
        Objects.equals(tipoAllegato, documento.tipoAllegato) &&
        Objects.equals(nomeFile, documento.nomeFile) &&
        Objects.equals(trascrizione, documento.trascrizione) &&
        Objects.equals(dataOraAggiornamento, documento.dataOraAggiornamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allegato, tipoAllegato, nomeFile, trascrizione, dataOraAggiornamento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Documento {\n");
    
    sb.append("    allegato: ").append(toIndentedString(allegato)).append("\n");
    sb.append("    tipoAllegato: ").append(toIndentedString(tipoAllegato)).append("\n");
    sb.append("    nomeFile: ").append(toIndentedString(nomeFile)).append("\n");
    sb.append("    trascrizione: ").append(toIndentedString(trascrizione)).append("\n");
    sb.append("    dataOraAggiornamento: ").append(toIndentedString(dataOraAggiornamento)).append("\n");
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

