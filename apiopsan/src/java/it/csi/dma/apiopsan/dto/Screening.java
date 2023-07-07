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
import it.csi.dma.apiopsan.dto.EsameScreening;
import java.util.List;
import javax.validation.constraints.*;

public class Screening   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String idScreening = null;
  private Codice tipoScreening = null;
  private List<EsameScreening> esami = new ArrayList<EsameScreening>();

  /**
   **/
  

  @JsonProperty("id_screening") 
 
  public String getIdScreening() {
    return idScreening;
  }
  public void setIdScreening(String idScreening) {
    this.idScreening = idScreening;
  }

  /**
   **/
  

  @JsonProperty("tipo_screening") 
 
  public Codice getTipoScreening() {
    return tipoScreening;
  }
  public void setTipoScreening(Codice tipoScreening) {
    this.tipoScreening = tipoScreening;
  }

  /**
   **/
  

  @JsonProperty("esami") 
 
  public List<EsameScreening> getEsami() {
    return esami;
  }
  public void setEsami(List<EsameScreening> esami) {
    this.esami = esami;
  }

	  @Override
	public int hashCode() {
		return Objects.hash(esami, idScreening, tipoScreening);
	}
	  
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screening other = (Screening) obj;
		return Objects.equals(esami, other.esami) && Objects.equals(idScreening, other.idScreening)
				&& Objects.equals(tipoScreening, other.tipoScreening);
	}
	
@Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Screening {\n");
    
    sb.append("    idScreening: ").append(toIndentedString(idScreening)).append("\n");
    sb.append("    tipoScreening: ").append(toIndentedString(tipoScreening)).append("\n");
    sb.append("    esami: ").append(toIndentedString(esami)).append("\n");
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

