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

public class PayloadGetDocumento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idDocumentoIlec = null;
  private String codiceComponenteLocale = null;
  private String firmato = null;
  private String codiceDocumento = null;
  private String identificativoRepository = null;

  /**
   **/
  

  @JsonProperty("id_documento_ilec") 
 
  public Long getIdDocumentoIlec() {
    return idDocumentoIlec;
  }
  public void setIdDocumentoIlec(Long idDocumentoIlec) {
    this.idDocumentoIlec = idDocumentoIlec;
  }

  /**
   **/
  

  @JsonProperty("codice_componente_locale") 
 
  public String getCodiceComponenteLocale() {
    return codiceComponenteLocale;
  }
  public void setCodiceComponenteLocale(String codiceComponenteLocale) {
    this.codiceComponenteLocale = codiceComponenteLocale;
  }

  /**
   **/
  

  @JsonProperty("firmato") 
 
  public String getFirmato() {
    return firmato;
  }
  public void setFirmato(String firmato) {
    this.firmato = firmato;
  }

  /**
   **/
  

  @JsonProperty("codice_documento") 
 
  public String getCodiceDocumento() {
    return codiceDocumento;
  }
  public void setCodiceDocumento(String codiceDocumento) {
    this.codiceDocumento = codiceDocumento;
  }

  /**
   * S -&gt; restiuisce il documento firmato (per scarico documento) N-&gt; restituisce il documento non firmato (per visualizzazione del documento) Nel caso di necessit? di visualizzazione del documento CADES, passare N. In tutti gli altri casi (visualizzazione e download di CDA/PADES/CADES) passare sempre il valore S
   **/
  

  @JsonProperty("identificativo_repository") 
 
  public String getIdentificativoRepository() {
    return identificativoRepository;
  }
  public void setIdentificativoRepository(String identificativoRepository) {
    this.identificativoRepository = identificativoRepository;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadGetDocumento payloadGetDocumento = (PayloadGetDocumento) o;
    return Objects.equals(idDocumentoIlec, payloadGetDocumento.idDocumentoIlec) &&
        Objects.equals(codiceComponenteLocale, payloadGetDocumento.codiceComponenteLocale) &&
        Objects.equals(firmato, payloadGetDocumento.firmato) &&
        Objects.equals(codiceDocumento, payloadGetDocumento.codiceDocumento) &&
        Objects.equals(identificativoRepository, payloadGetDocumento.identificativoRepository);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idDocumentoIlec, codiceComponenteLocale, firmato, codiceDocumento, identificativoRepository);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadGetDocumento {\n");
    
    sb.append("    idDocumentoIlec: ").append(toIndentedString(idDocumentoIlec)).append("\n");
    sb.append("    codiceComponenteLocale: ").append(toIndentedString(codiceComponenteLocale)).append("\n");
    sb.append("    firmato: ").append(toIndentedString(firmato)).append("\n");
    sb.append("    codiceDocumento: ").append(toIndentedString(codiceDocumento)).append("\n");
    sb.append("    identificativoRepository: ").append(toIndentedString(identificativoRepository)).append("\n");
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

