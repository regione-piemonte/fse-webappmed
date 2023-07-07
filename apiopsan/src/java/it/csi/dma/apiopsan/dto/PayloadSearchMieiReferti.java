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
import it.csi.dma.apiopsan.dto.FiltroDate;
import javax.validation.constraints.*;

public class PayloadSearchMieiReferti   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String citId = null;
  private FiltroDate filtroDocs = null;
  private Integer limit = null;
  private Integer offset = null;
  private String tipoMedico = null;

  /**
   **/
  

  @JsonProperty("cit_id") 
 
  public String getCitId() {
    return citId;
  }
  public void setCitId(String citId) {
    this.citId = citId;
  }

  /**
   **/
  

  @JsonProperty("filtro_docs") 
 
  public FiltroDate getFiltroDocs() {
    return filtroDocs;
  }
  public void setFiltroDocs(FiltroDate filtroDocs) {
    this.filtroDocs = filtroDocs;
  }

  /**
   **/
  

  @JsonProperty("limit") 
 
  public Integer getLimit() {
    return limit;
  }
  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  /**
   **/
  

  @JsonProperty("offset") 
 
  public Integer getOffset() {
    return offset;
  }
  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  /**
   **/
  

  @JsonProperty("tipo_medico") 
 
  public String getTipoMedico() {
    return tipoMedico;
  }
  public void setTipoMedico(String tipoMedico) {
    this.tipoMedico = tipoMedico;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadSearchMieiReferti payloadSearchMieiReferti = (PayloadSearchMieiReferti) o;
    return Objects.equals(citId, payloadSearchMieiReferti.citId) &&
        Objects.equals(filtroDocs, payloadSearchMieiReferti.filtroDocs) &&
        Objects.equals(limit, payloadSearchMieiReferti.limit) &&
        Objects.equals(offset, payloadSearchMieiReferti.offset) &&
        Objects.equals(tipoMedico, payloadSearchMieiReferti.tipoMedico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(citId, filtroDocs, limit, offset, tipoMedico);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadSearchMieiReferti {\n");
    
    sb.append("    citId: ").append(toIndentedString(citId)).append("\n");
    sb.append("    filtroDocs: ").append(toIndentedString(filtroDocs)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    tipoMedico: ").append(toIndentedString(tipoMedico)).append("\n");
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

