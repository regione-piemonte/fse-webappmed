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

public class PayloadRecuperaDocumento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String identificativoDocumento = null;
  private String identificativoRepository = null;
  private String identificativoOrgDoc = null;
  private String identificativoOrganizzazione = null;
  private String descrizioneOrganizzazione = null;
  private String strutturaUtente = null;
  private String contestoOperativo = null;
  private String tipoDocumento = null;
  private String presaInCarico = null;
  private String tipoAttivita = null;

  /**
   **/
  

  @JsonProperty("identificativo_documento") 
 
  public String getIdentificativoDocumento() {
    return identificativoDocumento;
  }
  public void setIdentificativoDocumento(String identificativoDocumento) {
    this.identificativoDocumento = identificativoDocumento;
  }

  /**
   **/
  

  @JsonProperty("identificativo_repository") 
 
  public String getIdentificativoRepository() {
    return identificativoRepository;
  }
  public void setIdentificativoRepository(String identificativoRepository) {
    this.identificativoRepository = identificativoRepository;
  }

  /**
   **/
  

  @JsonProperty("identificativo_org_doc") 
 
  public String getIdentificativoOrgDoc() {
    return identificativoOrgDoc;
  }
  public void setIdentificativoOrgDoc(String identificativoOrgDoc) {
    this.identificativoOrgDoc = identificativoOrgDoc;
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
  

  @JsonProperty("descrizione_organizzazione") 
 
  public String getDescrizioneOrganizzazione() {
    return descrizioneOrganizzazione;
  }
  public void setDescrizioneOrganizzazione(String descrizioneOrganizzazione) {
    this.descrizioneOrganizzazione = descrizioneOrganizzazione;
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
  

  @JsonProperty("contesto_operativo") 
 
  public String getContestoOperativo() {
    return contestoOperativo;
  }
  public void setContestoOperativo(String contestoOperativo) {
    this.contestoOperativo = contestoOperativo;
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

  /**
   **/
  

  @JsonProperty("presa_in_carico") 
 
  public String getPresaInCarico() {
    return presaInCarico;
  }
  public void setPresaInCarico(String presaInCarico) {
    this.presaInCarico = presaInCarico;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayloadRecuperaDocumento payloadRecuperaDocumento = (PayloadRecuperaDocumento) o;
    return Objects.equals(identificativoDocumento, payloadRecuperaDocumento.identificativoDocumento) &&
        Objects.equals(identificativoRepository, payloadRecuperaDocumento.identificativoRepository) &&
        Objects.equals(identificativoOrgDoc, payloadRecuperaDocumento.identificativoOrgDoc) &&
        Objects.equals(identificativoOrganizzazione, payloadRecuperaDocumento.identificativoOrganizzazione) &&
        Objects.equals(descrizioneOrganizzazione, payloadRecuperaDocumento.descrizioneOrganizzazione) &&
        Objects.equals(strutturaUtente, payloadRecuperaDocumento.strutturaUtente) &&
        Objects.equals(contestoOperativo, payloadRecuperaDocumento.contestoOperativo) &&
        Objects.equals(tipoDocumento, payloadRecuperaDocumento.tipoDocumento) &&
        Objects.equals(presaInCarico, payloadRecuperaDocumento.presaInCarico) &&
        Objects.equals(tipoAttivita, payloadRecuperaDocumento.tipoAttivita);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificativoDocumento, identificativoRepository, identificativoOrgDoc, identificativoOrganizzazione, descrizioneOrganizzazione, strutturaUtente, contestoOperativo, tipoDocumento, presaInCarico, tipoAttivita);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayloadRecuperaDocumento {\n");
    
    sb.append("    identificativoDocumento: ").append(toIndentedString(identificativoDocumento)).append("\n");
    sb.append("    identificativoRepository: ").append(toIndentedString(identificativoRepository)).append("\n");
    sb.append("    identificativoOrgDoc: ").append(toIndentedString(identificativoOrgDoc)).append("\n");
    sb.append("    identificativoOrganizzazione: ").append(toIndentedString(identificativoOrganizzazione)).append("\n");
    sb.append("    descrizioneOrganizzazione: ").append(toIndentedString(descrizioneOrganizzazione)).append("\n");
    sb.append("    strutturaUtente: ").append(toIndentedString(strutturaUtente)).append("\n");
    sb.append("    contestoOperativo: ").append(toIndentedString(contestoOperativo)).append("\n");
    sb.append("    tipoDocumento: ").append(toIndentedString(tipoDocumento)).append("\n");
    sb.append("    presaInCarico: ").append(toIndentedString(presaInCarico)).append("\n");
    sb.append("    tipoAttivita: ").append(toIndentedString(tipoAttivita)).append("\n");
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

