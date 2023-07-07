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

public class PayloadComunicazioneConsensi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Boolean presaInCarico = null;
  private String strutturaUtente = null;
  private String tipoAttivita = null;
  private String contestoOperativo = null;
  private String identificativoAssistitoConsenso = null;
  private String identificativoAssistitoGenitoreTutore = null;
  private String identificativoGenitoreConsenso = null;
  private String identificativoOrganizzazione = null;
  private String identificativoInformativa = null;
  private Boolean consensoAlimentazione = null;
  private Boolean consensoConsultazione = null;
  private Boolean consensoPregresso = null;

  /**
   **/
  

  @JsonProperty("presa_in_carico") 
 
  public Boolean isPresaInCarico() {
    return presaInCarico;
  }
  public void setPresaInCarico(Boolean presaInCarico) {
    this.presaInCarico = presaInCarico;
  }

  /**
   **/
  

  @JsonProperty("struttura_utente") 
 
  public String getStrutturaUtente() {
    return strutturaUtente;
  }
  public void setStrutturaUtente(String strutturaUtente) {
    this.strutturaUtente = strutturaUtente;
  }

  /**
   **/
  

  @JsonProperty("tipo_attivita") 
 
  public String getTipoAttivita() {
    return tipoAttivita;
  }
  public void setTipoAttivita(String tipoAttivita) {
    this.tipoAttivita = tipoAttivita;
  }

  /**
   **/
  

  @JsonProperty("contesto_operativo") 
 
  public String getContestoOperativo() {
    return contestoOperativo;
  }
  public void setContestoOperativo(String contestoOperativo) {
    this.contestoOperativo = contestoOperativo;
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
  

  @JsonProperty("identificativo_assistito_genitore_tutore") 
 
  public String getIdentificativoAssistitoGenitoreTutore() {
    return identificativoAssistitoGenitoreTutore;
  }
  public void setIdentificativoAssistitoGenitoreTutore(String identificativoAssistitoGenitoreTutore) {
    this.identificativoAssistitoGenitoreTutore = identificativoAssistitoGenitoreTutore;
  }

  /**
   **/
  

  @JsonProperty("identificativo_genitore_consenso") 
 
  public String getIdentificativoGenitoreConsenso() {
    return identificativoGenitoreConsenso;
  }
  public void setIdentificativoGenitoreConsenso(String identificativoGenitoreConsenso) {
    this.identificativoGenitoreConsenso = identificativoGenitoreConsenso;
  }

  /**
   **/
  

  @JsonProperty("identificativo_organizzazione") 
 
  public String getIdentificativoOrganizzazione() {
    return identificativoOrganizzazione;
  }
  public void setIdentificativoOrganizzazione(String identificativoOrganizzazione) {
    this.identificativoOrganizzazione = identificativoOrganizzazione;
  }

  /**
   **/
  

  @JsonProperty("identificativo_informativa") 
 
  public String getIdentificativoInformativa() {
    return identificativoInformativa;
  }
  public void setIdentificativoInformativa(String identificativoInformativa) {
    this.identificativoInformativa = identificativoInformativa;
  }

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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadComunicazioneConsensi payloadComunicazioneConsensi = (PayloadComunicazioneConsensi) o;
    return Objects.equals(presaInCarico, payloadComunicazioneConsensi.presaInCarico) &&
        Objects.equals(strutturaUtente, payloadComunicazioneConsensi.strutturaUtente) &&
        Objects.equals(tipoAttivita, payloadComunicazioneConsensi.tipoAttivita) &&
        Objects.equals(contestoOperativo, payloadComunicazioneConsensi.contestoOperativo) &&
        Objects.equals(identificativoAssistitoConsenso, payloadComunicazioneConsensi.identificativoAssistitoConsenso) &&
        Objects.equals(identificativoAssistitoGenitoreTutore, payloadComunicazioneConsensi.identificativoAssistitoGenitoreTutore) &&
        Objects.equals(identificativoGenitoreConsenso, payloadComunicazioneConsensi.identificativoGenitoreConsenso) &&
        Objects.equals(identificativoOrganizzazione, payloadComunicazioneConsensi.identificativoOrganizzazione) &&
        Objects.equals(identificativoInformativa, payloadComunicazioneConsensi.identificativoInformativa) &&
        Objects.equals(consensoAlimentazione, payloadComunicazioneConsensi.consensoAlimentazione) &&
        Objects.equals(consensoConsultazione, payloadComunicazioneConsensi.consensoConsultazione) &&
        Objects.equals(consensoPregresso, payloadComunicazioneConsensi.consensoPregresso);
  }

  @Override
  public int hashCode() {
    return Objects.hash(presaInCarico, strutturaUtente, tipoAttivita, contestoOperativo, identificativoAssistitoConsenso, identificativoAssistitoGenitoreTutore, identificativoGenitoreConsenso, identificativoOrganizzazione, identificativoInformativa, consensoAlimentazione, consensoConsultazione, consensoPregresso);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadComunicazioneConsensi {\n");
    
    sb.append("    presaInCarico: ").append(toIndentedString(presaInCarico)).append("\n");
    sb.append("    strutturaUtente: ").append(toIndentedString(strutturaUtente)).append("\n");
    sb.append("    tipoAttivita: ").append(toIndentedString(tipoAttivita)).append("\n");
    sb.append("    contestoOperativo: ").append(toIndentedString(contestoOperativo)).append("\n");
    sb.append("    identificativoAssistitoConsenso: ").append(toIndentedString(identificativoAssistitoConsenso)).append("\n");
    sb.append("    identificativoAssistitoGenitoreTutore: ").append(toIndentedString(identificativoAssistitoGenitoreTutore)).append("\n");
    sb.append("    identificativoGenitoreConsenso: ").append(toIndentedString(identificativoGenitoreConsenso)).append("\n");
    sb.append("    identificativoOrganizzazione: ").append(toIndentedString(identificativoOrganizzazione)).append("\n");
    sb.append("    identificativoInformativa: ").append(toIndentedString(identificativoInformativa)).append("\n");
    sb.append("    consensoAlimentazione: ").append(toIndentedString(consensoAlimentazione)).append("\n");
    sb.append("    consensoConsultazione: ").append(toIndentedString(consensoConsultazione)).append("\n");
    sb.append("    consensoPregresso: ").append(toIndentedString(consensoPregresso)).append("\n");
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

