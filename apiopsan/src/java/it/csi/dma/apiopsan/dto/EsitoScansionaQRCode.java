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

public class EsitoScansionaQRCode   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Boolean consensoAlimentazione = null;
  private Boolean consensoConsultazione = null;
  private Boolean consensoPregresso = null;
  private String identificativoAssistitoConsenso = null;
  private String identificativoInformativaConsensi = null;
  private String identificativoInformativaCorrente = null;

  /**
   **/
  

  @JsonProperty("consenso_alimentazione") 
 
  public Boolean isConsensoAlimentazione() {
    return consensoAlimentazione;
  }
  public void setConsensoAlimentazione(Boolean consensoAlimentazione) {
    this.consensoAlimentazione = consensoAlimentazione;
  }

  /**
   **/
  

  @JsonProperty("consenso_consultazione") 
 
  public Boolean isConsensoConsultazione() {
    return consensoConsultazione;
  }
  public void setConsensoConsultazione(Boolean consensoConsultazione) {
    this.consensoConsultazione = consensoConsultazione;
  }

  /**
   **/
  

  @JsonProperty("consenso_pregresso") 
 
  public Boolean isConsensoPregresso() {
    return consensoPregresso;
  }
  public void setConsensoPregresso(Boolean consensoPregresso) {
    this.consensoPregresso = consensoPregresso;
  }

  /**
   **/
  

  @JsonProperty("identificativo_assistito_consenso") 
 
  public String getIdentificativoAssistitoConsenso() {
    return identificativoAssistitoConsenso;
  }
  public void setIdentificativoAssistitoConsenso(String identificativoAssistitoConsenso) {
    this.identificativoAssistitoConsenso = identificativoAssistitoConsenso;
  }

  /**
   **/
  

  @JsonProperty("identificativo_informativa_consensi") 
 
  public String getIdentificativoInformativaConsensi() {
    return identificativoInformativaConsensi;
  }
  public void setIdentificativoInformativaConsensi(String identificativoInformativaConsensi) {
    this.identificativoInformativaConsensi = identificativoInformativaConsensi;
  }

  /**
   **/
  

  @JsonProperty("identificativo_informativa_corrente") 
 
  public String getIdentificativoInformativaCorrente() {
    return identificativoInformativaCorrente;
  }
  public void setIdentificativoInformativaCorrente(String identificativoInformativaCorrente) {
    this.identificativoInformativaCorrente = identificativoInformativaCorrente;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoScansionaQRCode statoConsenso = (EsitoScansionaQRCode) o;
    return Objects.equals(consensoAlimentazione, statoConsenso.consensoAlimentazione) &&
        Objects.equals(consensoConsultazione, statoConsenso.consensoConsultazione) &&
        Objects.equals(consensoPregresso, statoConsenso.consensoPregresso) &&
        Objects.equals(identificativoAssistitoConsenso, statoConsenso.identificativoAssistitoConsenso) &&
        Objects.equals(identificativoInformativaConsensi, statoConsenso.identificativoInformativaConsensi) &&
        Objects.equals(identificativoInformativaCorrente, statoConsenso.identificativoInformativaCorrente);
  }

  @Override
  public int hashCode() {
    return Objects.hash(consensoAlimentazione, consensoConsultazione, consensoPregresso, identificativoAssistitoConsenso, identificativoInformativaConsensi, identificativoInformativaCorrente);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatoConsenso {\n");
    
    sb.append("    consensoAlimentazione: ").append(toIndentedString(consensoAlimentazione)).append("\n");
    sb.append("    consensoConsultazione: ").append(toIndentedString(consensoConsultazione)).append("\n");
    sb.append("    consensoPregresso: ").append(toIndentedString(consensoPregresso)).append("\n");
    sb.append("    identificativoAssistitoConsenso: ").append(toIndentedString(identificativoAssistitoConsenso)).append("\n");
    sb.append("    identificativoInformativaConsensi: ").append(toIndentedString(identificativoInformativaConsensi)).append("\n");
    sb.append("    identificativoInformativaCorrente: ").append(toIndentedString(identificativoInformativaCorrente)).append("\n");
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

