/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadSearchTuttiDoc  implements Serializable {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private static final long serialVersionUID = -7672020599940582805L;

  private String tipoMedico = null;
  private List<CategoriaTipologia> categoriaTipologia = new ArrayList<CategoriaTipologia>();
  private FiltroDate filtroDocs = null;
  private Integer limit = null;
  private Integer offset = null;

  /**
   **/
  

  @JsonProperty("tipo_medico") 
 
  public String getTipoMedico() {
    return tipoMedico;
  }
  public void setTipoMedico(String tipoMedico) {
    this.tipoMedico = tipoMedico;
  }

  /**
   **/
  

  @JsonProperty("categoria_tipologia") 
 
  public List<CategoriaTipologia> getCategoriaTipologia() {
    return categoriaTipologia;
  }
  public void setCategoriaTipologia(List<CategoriaTipologia> categoriaTipologia) {
    this.categoriaTipologia = categoriaTipologia;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadSearchTuttiDoc payloadSearchTuttiDoc = (PayloadSearchTuttiDoc) o;
    return Objects.equals(tipoMedico, payloadSearchTuttiDoc.tipoMedico) &&
        Objects.equals(categoriaTipologia, payloadSearchTuttiDoc.categoriaTipologia) &&
        Objects.equals(filtroDocs, payloadSearchTuttiDoc.filtroDocs) &&
        Objects.equals(limit, payloadSearchTuttiDoc.limit) &&
        Objects.equals(offset, payloadSearchTuttiDoc.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipoMedico, categoriaTipologia, filtroDocs, limit, offset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadSearchTuttiDoc {\n");
    
    sb.append("    tipoMedico: ").append(toIndentedString(tipoMedico)).append("\n");
    sb.append("    categoriaTipologia: ").append(toIndentedString(categoriaTipologia)).append("\n");
    sb.append("    filtroDocs: ").append(toIndentedString(filtroDocs)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
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

