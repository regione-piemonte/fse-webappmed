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
import java.util.List;
import javax.validation.constraints.*;

public class PayloadSearchEpisodi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String categoria = null;
  private FiltroDate filtroDocs = null;
  private Integer limit = null;
  private Integer offset = null;
  private String tipoEpisodio = null;
  private List<String> tipologiaDocumento = new ArrayList<String>();

  /**
   **/
  

  @JsonProperty("categoria") 
 
  public String getCategoria() {
    return categoria;
  }
  public void setCategoria(String categoria) {
    this.categoria = categoria;
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
  

  @JsonProperty("tipo_episodio") 
 
  public String getTipoEpisodio() {
    return tipoEpisodio;
  }
  public void setTipoEpisodio(String tipoEpisodio) {
    this.tipoEpisodio = tipoEpisodio;
  }

  /**
   **/
  

  @JsonProperty("tipologia_documento") 
 
  public List<String> getTipologiaDocumento() {
    return tipologiaDocumento;
  }
  public void setTipologiaDocumento(List<String> tipologiaDocumento) {
    this.tipologiaDocumento = tipologiaDocumento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadSearchEpisodi payloadSearchEpisodi = (PayloadSearchEpisodi) o;
    return Objects.equals(categoria, payloadSearchEpisodi.categoria) &&
        Objects.equals(filtroDocs, payloadSearchEpisodi.filtroDocs) &&
        Objects.equals(limit, payloadSearchEpisodi.limit) &&
        Objects.equals(offset, payloadSearchEpisodi.offset) &&
        Objects.equals(tipoEpisodio, payloadSearchEpisodi.tipoEpisodio) &&
        Objects.equals(tipologiaDocumento, payloadSearchEpisodi.tipologiaDocumento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoria, filtroDocs, limit, offset, tipoEpisodio, tipologiaDocumento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadSearchEpisodi {\n");
    
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    filtroDocs: ").append(toIndentedString(filtroDocs)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    tipoEpisodio: ").append(toIndentedString(tipoEpisodio)).append("\n");
    sb.append("    tipologiaDocumento: ").append(toIndentedString(tipologiaDocumento)).append("\n");
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

