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
import it.csi.dma.apiopsan.dto.TipoDocumento;
import java.util.List;
import javax.validation.constraints.*;

public class TipoDocumentoCategoria   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceCategoria = null;
  private String descrizioneCategoria = null;
  private List<TipoDocumento> tipiDocumento = new ArrayList<TipoDocumento>();

  /**
   **/
  

  @JsonProperty("codice_categoria") 
 
  public String getCodiceCategoria() {
    return codiceCategoria;
  }
  public void setCodiceCategoria(String codiceCategoria) {
    this.codiceCategoria = codiceCategoria;
  }

  /**
   **/
  

  @JsonProperty("descrizione_categoria") 
 
  public String getDescrizioneCategoria() {
    return descrizioneCategoria;
  }
  public void setDescrizioneCategoria(String descrizioneCategoria) {
    this.descrizioneCategoria = descrizioneCategoria;
  }

  /**
   **/
  

  @JsonProperty("tipi_documento") 
 
  public List<TipoDocumento> getTipiDocumento() {
    return tipiDocumento;
  }
  public void setTipiDocumento(List<TipoDocumento> tipiDocumento) {
    this.tipiDocumento = tipiDocumento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TipoDocumentoCategoria tipoDocumentoCategoria = (TipoDocumentoCategoria) o;
    return Objects.equals(codiceCategoria, tipoDocumentoCategoria.codiceCategoria) &&
        Objects.equals(descrizioneCategoria, tipoDocumentoCategoria.descrizioneCategoria) &&
        Objects.equals(tipiDocumento, tipoDocumentoCategoria.tipiDocumento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceCategoria, descrizioneCategoria, tipiDocumento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipoDocumentoCategoria {\n");
    
    sb.append("    codiceCategoria: ").append(toIndentedString(codiceCategoria)).append("\n");
    sb.append("    descrizioneCategoria: ").append(toIndentedString(descrizioneCategoria)).append("\n");
    sb.append("    tipiDocumento: ").append(toIndentedString(tipiDocumento)).append("\n");
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

