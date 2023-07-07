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
import it.csi.dma.apiopsan.dto.Codice;
import java.util.List;
import javax.validation.constraints.*;

public class PayloadSetAudit   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String cittadinoId = null;
  private String codiceAudit = null;
  private List<Codice> parametri = new ArrayList<Codice>();

  /**
   **/
  

  @JsonProperty("cittadino_id") 
 
  public String getCittadinoId() {
    return cittadinoId;
  }
  public void setCittadinoId(String cittadinoId) {
    this.cittadinoId = cittadinoId;
  }

  /**
   **/
  

  @JsonProperty("codice_audit") 
 
  public String getCodiceAudit() {
    return codiceAudit;
  }
  public void setCodiceAudit(String codiceAudit) {
    this.codiceAudit = codiceAudit;
  }

  /**
   **/
  

  @JsonProperty("parametri") 
 
  public List<Codice> getParametri() {
    return parametri;
  }
  public void setParametri(List<Codice> parametri) {
    this.parametri = parametri;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadSetAudit payloadSetAudit = (PayloadSetAudit) o;
    return Objects.equals(cittadinoId, payloadSetAudit.cittadinoId) &&
        Objects.equals(codiceAudit, payloadSetAudit.codiceAudit) &&
        Objects.equals(parametri, payloadSetAudit.parametri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cittadinoId, codiceAudit, parametri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadSetAudit {\n");
    
    sb.append("    cittadinoId: ").append(toIndentedString(cittadinoId)).append("\n");
    sb.append("    codiceAudit: ").append(toIndentedString(codiceAudit)).append("\n");
    sb.append("    parametri: ").append(toIndentedString(parametri)).append("\n");
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

