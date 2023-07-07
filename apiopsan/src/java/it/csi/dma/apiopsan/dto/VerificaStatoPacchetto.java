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
import java.math.BigDecimal;
import javax.validation.constraints.*;

public class VerificaStatoPacchetto   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String statoRichiesta = null;
  private String zipName = null;
  private String directory = null;
  private String checksum = null;
  private String arvchivioDocumentoIlec = null;
  private String codDocumentoDipartimentale = null;
  private BigDecimal idDocumentoIlec = null;

  /**
   **/
  

  @JsonProperty("stato_richiesta") 
 
  public String getStatoRichiesta() {
    return statoRichiesta;
  }
  public void setStatoRichiesta(String statoRichiesta) {
    this.statoRichiesta = statoRichiesta;
  }

  /**
   **/
  

  @JsonProperty("zip_name") 
 
  public String getZipName() {
    return zipName;
  }
  public void setZipName(String zipName) {
    this.zipName = zipName;
  }

  /**
   **/
  

  @JsonProperty("directory") 
 
  public String getDirectory() {
    return directory;
  }
  public void setDirectory(String directory) {
    this.directory = directory;
  }

  /**
   **/
  

  @JsonProperty("checksum") 
 
  public String getChecksum() {
    return checksum;
  }
  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  /**
   **/
  

  @JsonProperty("arvchivio_documento_ilec") 
 
  public String getArvchivioDocumentoIlec() {
    return arvchivioDocumentoIlec;
  }
  public void setArvchivioDocumentoIlec(String arvchivioDocumentoIlec) {
    this.arvchivioDocumentoIlec = arvchivioDocumentoIlec;
  }

  /**
   **/
  

  @JsonProperty("cod_documento_dipartimentale") 
 
  public String getCodDocumentoDipartimentale() {
    return codDocumentoDipartimentale;
  }
  public void setCodDocumentoDipartimentale(String codDocumentoDipartimentale) {
    this.codDocumentoDipartimentale = codDocumentoDipartimentale;
  }

  /**
   **/
  

  @JsonProperty("id_documento_ilec") 
 
  public BigDecimal getIdDocumentoIlec() {
    return idDocumentoIlec;
  }
  public void setIdDocumentoIlec(BigDecimal idDocumentoIlec) {
    this.idDocumentoIlec = idDocumentoIlec;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerificaStatoPacchetto verificaStatoPacchetto = (VerificaStatoPacchetto) o;
    return Objects.equals(statoRichiesta, verificaStatoPacchetto.statoRichiesta) &&
        Objects.equals(zipName, verificaStatoPacchetto.zipName) &&
        Objects.equals(directory, verificaStatoPacchetto.directory) &&
        Objects.equals(checksum, verificaStatoPacchetto.checksum) &&
        Objects.equals(arvchivioDocumentoIlec, verificaStatoPacchetto.arvchivioDocumentoIlec) &&
        Objects.equals(codDocumentoDipartimentale, verificaStatoPacchetto.codDocumentoDipartimentale) &&
        Objects.equals(idDocumentoIlec, verificaStatoPacchetto.idDocumentoIlec);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statoRichiesta, zipName, directory, checksum, arvchivioDocumentoIlec, codDocumentoDipartimentale, idDocumentoIlec);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerificaStatoPacchetto {\n");
    
    sb.append("    statoRichiesta: ").append(toIndentedString(statoRichiesta)).append("\n");
    sb.append("    zipName: ").append(toIndentedString(zipName)).append("\n");
    sb.append("    directory: ").append(toIndentedString(directory)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    arvchivioDocumentoIlec: ").append(toIndentedString(arvchivioDocumentoIlec)).append("\n");
    sb.append("    codDocumentoDipartimentale: ").append(toIndentedString(codDocumentoDipartimentale)).append("\n");
    sb.append("    idDocumentoIlec: ").append(toIndentedString(idDocumentoIlec)).append("\n");
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

