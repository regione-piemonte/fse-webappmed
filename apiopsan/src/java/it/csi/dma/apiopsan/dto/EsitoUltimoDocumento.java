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
import it.csi.dma.apiopsan.dto.LuogoProduzioneDocumento;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;
import java.io.File;

public class EsitoUltimoDocumento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private List<String> accessionNumber = new ArrayList<String>();
  private Date dataValidazione = null;
  private String firmatoDigitalmente = null;
  private Boolean flagUltimoDocumento = null;
  private String fonteOscuramento = null;
  private Long idDocumento = null;
  private Long idEpisodio = null;
  private LuogoProduzioneDocumento luogoProduzioneDocumento = null;
  private String medicoValidante = null;
  private String medicoVisita = null;
  private String oidDocumento = null;
  private String oidRepository = null;
  private Boolean oscurato = null;
  private String tipoDocumento = null;
  private String documento = null;

  /**
   **/
  

  @JsonProperty("accession_number") 
 
  public List<String> getAccessionNumber() {
    return accessionNumber;
  }
  public void setAccessionNumber(List<String> accessionNumber) {
    this.accessionNumber = accessionNumber;
  }

  /**
   **/
  

  @JsonProperty("data_validazione") 
 
  public Date getDataValidazione() {
    return dataValidazione;
  }
  public void setDataValidazione(Date dataValidazione) {
    this.dataValidazione = dataValidazione;
  }

  /**
   **/
  

  @JsonProperty("firmato_digitalmente") 
 
  public String getFirmatoDigitalmente() {
    return firmatoDigitalmente;
  }
  public void setFirmatoDigitalmente(String firmatoDigitalmente) {
    this.firmatoDigitalmente = firmatoDigitalmente;
  }

  /**
   **/
  

  @JsonProperty("flag_ultimo_documento") 
 
  public Boolean isFlagUltimoDocumento() {
    return flagUltimoDocumento;
  }
  public void setFlagUltimoDocumento(Boolean flagUltimoDocumento) {
    this.flagUltimoDocumento = flagUltimoDocumento;
  }

  /**
   **/
  

  @JsonProperty("fonte_oscuramento") 
 
  public String getFonteOscuramento() {
    return fonteOscuramento;
  }
  public void setFonteOscuramento(String fonteOscuramento) {
    this.fonteOscuramento = fonteOscuramento;
  }

  /**
   **/
  

  @JsonProperty("id_documento") 
 
  public Long getIdDocumento() {
    return idDocumento;
  }
  public void setIdDocumento(Long idDocumento) {
    this.idDocumento = idDocumento;
  }

  /**
   **/
  

  @JsonProperty("id_episodio") 
 
  public Long getIdEpisodio() {
    return idEpisodio;
  }
  public void setIdEpisodio(Long idEpisodio) {
    this.idEpisodio = idEpisodio;
  }

  /**
   **/
  

  @JsonProperty("luogo_produzione_documento") 
 
  public LuogoProduzioneDocumento getLuogoProduzioneDocumento() {
    return luogoProduzioneDocumento;
  }
  public void setLuogoProduzioneDocumento(LuogoProduzioneDocumento luogoProduzioneDocumento) {
    this.luogoProduzioneDocumento = luogoProduzioneDocumento;
  }

  /**
   **/
  

  @JsonProperty("medico_validante") 
 
  public String getMedicoValidante() {
    return medicoValidante;
  }
  public void setMedicoValidante(String medicoValidante) {
    this.medicoValidante = medicoValidante;
  }

  /**
   **/
  

  @JsonProperty("medico_visita") 
 
  public String getMedicoVisita() {
    return medicoVisita;
  }
  public void setMedicoVisita(String medicoVisita) {
    this.medicoVisita = medicoVisita;
  }

  /**
   **/
  

  @JsonProperty("oid_documento") 
 
  public String getOidDocumento() {
    return oidDocumento;
  }
  public void setOidDocumento(String oidDocumento) {
    this.oidDocumento = oidDocumento;
  }

  /**
   **/
  

  @JsonProperty("oid_repository") 
 
  public String getOidRepository() {
    return oidRepository;
  }
  public void setOidRepository(String oidRepository) {
    this.oidRepository = oidRepository;
  }

  /**
   **/
  

  @JsonProperty("oscurato") 
 
  public Boolean isOscurato() {
    return oscurato;
  }
  public void setOscurato(Boolean oscurato) {
    this.oscurato = oscurato;
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
  

  @JsonProperty("documento") 
 
  public String getDocumento() {
    return documento;
  }
  public void setDocumento(String documento) {
    this.documento = documento;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsitoUltimoDocumento esitoUltimoDocumento = (EsitoUltimoDocumento) o;
    return Objects.equals(accessionNumber, esitoUltimoDocumento.accessionNumber) &&
        Objects.equals(dataValidazione, esitoUltimoDocumento.dataValidazione) &&
        Objects.equals(firmatoDigitalmente, esitoUltimoDocumento.firmatoDigitalmente) &&
        Objects.equals(flagUltimoDocumento, esitoUltimoDocumento.flagUltimoDocumento) &&
        Objects.equals(fonteOscuramento, esitoUltimoDocumento.fonteOscuramento) &&
        Objects.equals(idDocumento, esitoUltimoDocumento.idDocumento) &&
        Objects.equals(idEpisodio, esitoUltimoDocumento.idEpisodio) &&
        Objects.equals(luogoProduzioneDocumento, esitoUltimoDocumento.luogoProduzioneDocumento) &&
        Objects.equals(medicoValidante, esitoUltimoDocumento.medicoValidante) &&
        Objects.equals(medicoVisita, esitoUltimoDocumento.medicoVisita) &&
        Objects.equals(oidDocumento, esitoUltimoDocumento.oidDocumento) &&
        Objects.equals(oidRepository, esitoUltimoDocumento.oidRepository) &&
        Objects.equals(oscurato, esitoUltimoDocumento.oscurato) &&
        Objects.equals(tipoDocumento, esitoUltimoDocumento.tipoDocumento) &&
        Objects.equals(documento, esitoUltimoDocumento.documento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessionNumber, dataValidazione, firmatoDigitalmente, flagUltimoDocumento, fonteOscuramento, idDocumento, idEpisodio, luogoProduzioneDocumento, medicoValidante, medicoVisita, oidDocumento, oidRepository, oscurato, tipoDocumento, documento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsitoUltimoDocumento {\n");
    
    sb.append("    accessionNumber: ").append(toIndentedString(accessionNumber)).append("\n");
    sb.append("    dataValidazione: ").append(toIndentedString(dataValidazione)).append("\n");
    sb.append("    firmatoDigitalmente: ").append(toIndentedString(firmatoDigitalmente)).append("\n");
    sb.append("    flagUltimoDocumento: ").append(toIndentedString(flagUltimoDocumento)).append("\n");
    sb.append("    fonteOscuramento: ").append(toIndentedString(fonteOscuramento)).append("\n");
    sb.append("    idDocumento: ").append(toIndentedString(idDocumento)).append("\n");
    sb.append("    idEpisodio: ").append(toIndentedString(idEpisodio)).append("\n");
    sb.append("    luogoProduzioneDocumento: ").append(toIndentedString(luogoProduzioneDocumento)).append("\n");
    sb.append("    medicoValidante: ").append(toIndentedString(medicoValidante)).append("\n");
    sb.append("    medicoVisita: ").append(toIndentedString(medicoVisita)).append("\n");
    sb.append("    oidDocumento: ").append(toIndentedString(oidDocumento)).append("\n");
    sb.append("    oidRepository: ").append(toIndentedString(oidRepository)).append("\n");
    sb.append("    oscurato: ").append(toIndentedString(oscurato)).append("\n");
    sb.append("    tipoDocumento: ").append(toIndentedString(tipoDocumento)).append("\n");
    sb.append("    documento: ").append(toIndentedString(documento)).append("\n");
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

