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
import io.swagger.annotations.ApiModel;
import it.csi.dma.apiopsan.dto.Funzionalita;
import it.csi.dma.apiopsan.dto.UtenteRichiedente;
import java.util.List;
import javax.validation.constraints.*;

public class Utente   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private UtenteRichiedente richiedente = null;
  private List<Funzionalita> funzionalitaAbilitate = new ArrayList<Funzionalita>();
  private String cfPaziente = null;
  private String tipoDocumento = null;

  /**
   **/
  

  @JsonProperty("richiedente") 
 
  public UtenteRichiedente getRichiedente() {
    return richiedente;
  }
  public void setRichiedente(UtenteRichiedente richiedente) {
    this.richiedente = richiedente;
  }

  /**
   **/
  

  @JsonProperty("funzionalita_abilitate") 
 
  public List<Funzionalita> getFunzionalitaAbilitate() {
    return funzionalitaAbilitate;
  }
  public void setFunzionalitaAbilitate(List<Funzionalita> funzionalitaAbilitate) {
    this.funzionalitaAbilitate = funzionalitaAbilitate;
  }

  /**
   **/
  

  @JsonProperty("cf_paziente") 
 
  public String getCfPaziente() {
    return cfPaziente;
  }
  public void setCfPaziente(String cfPaziente) {
    this.cfPaziente = cfPaziente;
  }

  /**
   **/
  

  @JsonProperty("tipo_documento") 
 
  public String getTipoDocumento() {
    return tipoDocumento;
  }
  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Utente utente = (Utente) o;
    return Objects.equals(richiedente, utente.richiedente) &&
        Objects.equals(funzionalitaAbilitate, utente.funzionalitaAbilitate) &&
        Objects.equals(cfPaziente, utente.cfPaziente) &&
        Objects.equals(tipoDocumento, utente.tipoDocumento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(richiedente, funzionalitaAbilitate, cfPaziente, tipoDocumento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Utente {\n");
    
    sb.append("    richiedente: ").append(toIndentedString(richiedente)).append("\n");
    sb.append("    funzionalitaAbilitate: ").append(toIndentedString(funzionalitaAbilitate)).append("\n");
    sb.append("    cfPaziente: ").append(toIndentedString(cfPaziente)).append("\n");
    sb.append("    tipoDocumento: ").append(toIndentedString(tipoDocumento)).append("\n");
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

