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

public class DocumentoFr   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String documento = null;
  private String tipoMime = null;
  private String identificativoOrgDoc = null;
  private String identificativoRepository = null;
  private String identificativoDocumento = null;

  /**
   **/
  

  @JsonProperty("documento") 
 
  public String getDocumento() {
    return documento;
  }
  public void setDocumento(String documento) {
    this.documento = documento;
  }

  /**
   **/
  

  @JsonProperty("tipo_mime") 
 
  public String getTipoMime() {
    return tipoMime;
  }
  public void setTipoMime(String tipoMime) {
    this.tipoMime = tipoMime;
  }

  /**
   **/
  

  @JsonProperty("identificativo_org_doc") 
 
  public String getIdentificativoOrgDoc() {
    return identificativoOrgDoc;
  }
  public void setIdentificativoOrgDoc(String identificativoOrgDoc) {
    this.identificativoOrgDoc = identificativoOrgDoc;
  }

  /**
   **/
  

  @JsonProperty("identificativo_repository") 
 
  public String getIdentificativoRepository() {
    return identificativoRepository;
  }
  public void setIdentificativoRepository(String identificativoRepository) {
    this.identificativoRepository = identificativoRepository;
  }

  /**
   **/
  

  @JsonProperty("identificativo_documento") 
 
  public String getIdentificativoDocumento() {
    return identificativoDocumento;
  }
  public void setIdentificativoDocumento(String identificativoDocumento) {
    this.identificativoDocumento = identificativoDocumento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentoFr documentoFr = (DocumentoFr) o;
    return Objects.equals(documento, documentoFr.documento) &&
        Objects.equals(tipoMime, documentoFr.tipoMime) &&
        Objects.equals(identificativoOrgDoc, documentoFr.identificativoOrgDoc) &&
        Objects.equals(identificativoRepository, documentoFr.identificativoRepository) &&
        Objects.equals(identificativoDocumento, documentoFr.identificativoDocumento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documento, tipoMime, identificativoOrgDoc, identificativoRepository, identificativoDocumento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DocumentoFr {\n");
    
    sb.append("    documento: ").append(toIndentedString(documento)).append("\n");
    sb.append("    tipoMime: ").append(toIndentedString(tipoMime)).append("\n");
    sb.append("    identificativoOrgDoc: ").append(toIndentedString(identificativoOrgDoc)).append("\n");
    sb.append("    identificativoRepository: ").append(toIndentedString(identificativoRepository)).append("\n");
    sb.append("    identificativoDocumento: ").append(toIndentedString(identificativoDocumento)).append("\n");
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

