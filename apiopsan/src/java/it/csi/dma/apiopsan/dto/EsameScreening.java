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
import com.fasterxml.jackson.annotation.JsonFormat;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.GiudizioDiagnostico;
import it.csi.dma.apiopsan.dto.IndicazioneFinale;
import it.csi.dma.apiopsan.dto.InterventoErogato;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

public class EsameScreening   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Date data = null;
  private Codice tipo = null;
  private String esito = null;
  private Codice aziendaSanitaria = null;
  private Codice unitaOperativa = null;
  private List<Codice> prestazioni = new ArrayList<Codice>();
  private List<GiudizioDiagnostico> giudiziDiagnostici = new ArrayList<GiudizioDiagnostico>();
  private IndicazioneFinale indicazioneFinale = null;
  private InterventoErogato interventoErogato = null;

  /**
   **/
  

  @JsonProperty("data") 
  @JsonFormat(pattern = "yyyy-MM-dd")
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   **/
  

  @JsonProperty("tipo") 
 
  public Codice getTipo() {
    return tipo;
  }
  public void setTipo(Codice tipo) {
    this.tipo = tipo;
  }

  /**
   **/
  

  @JsonProperty("esito") 
 
  public String getEsito() {
    return esito;
  }
  public void setEsito(String esito) {
    this.esito = esito;
  }

  /**
   **/
  

  @JsonProperty("azienda_sanitaria") 
 
  public Codice getAziendaSanitaria() {
    return aziendaSanitaria;
  }
  public void setAziendaSanitaria(Codice aziendaSanitaria) {
    this.aziendaSanitaria = aziendaSanitaria;
  }

  /**
   **/
  

  @JsonProperty("unita_operativa") 
 
  public Codice getUnitaOperativa() {
    return unitaOperativa;
  }
  public void setUnitaOperativa(Codice unitaOperativa) {
    this.unitaOperativa = unitaOperativa;
  }

  /**
   **/
  

  @JsonProperty("prestazioni") 
 
  public List<Codice> getPrestazioni() {
    return prestazioni;
  }
  public void setPrestazioni(List<Codice> prestazioni) {
    this.prestazioni = prestazioni;
  }

  /**
   **/
  

  @JsonProperty("giudizi_diagnostici") 
 
  public List<GiudizioDiagnostico> getGiudiziDiagnostici() {
    return giudiziDiagnostici;
  }
  public void setGiudiziDiagnostici(List<GiudizioDiagnostico> giudiziDiagnostici) {
    this.giudiziDiagnostici = giudiziDiagnostici;
  }

  /**
   **/
  

  @JsonProperty("indicazione_finale") 
 
  public IndicazioneFinale getIndicazioneFinale() {
    return indicazioneFinale;
  }
  public void setIndicazioneFinale(IndicazioneFinale indicazioneFinale) {
    this.indicazioneFinale = indicazioneFinale;
  }

  /**
   **/
  

  @JsonProperty("intervento_erogato") 
 
  public InterventoErogato getInterventoErogato() {
    return interventoErogato;
  }
  public void setInterventoErogato(InterventoErogato interventoErogato) {
    this.interventoErogato = interventoErogato;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsameScreening esameScreening = (EsameScreening) o;
    return Objects.equals(data, esameScreening.data) &&
        Objects.equals(tipo, esameScreening.tipo) &&
        Objects.equals(esito, esameScreening.esito) &&
        Objects.equals(aziendaSanitaria, esameScreening.aziendaSanitaria) &&
        Objects.equals(unitaOperativa, esameScreening.unitaOperativa) &&
        Objects.equals(prestazioni, esameScreening.prestazioni) &&
        Objects.equals(giudiziDiagnostici, esameScreening.giudiziDiagnostici) &&
        Objects.equals(indicazioneFinale, esameScreening.indicazioneFinale) &&
        Objects.equals(interventoErogato, esameScreening.interventoErogato);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, tipo, esito, aziendaSanitaria, unitaOperativa, prestazioni, giudiziDiagnostici, indicazioneFinale, interventoErogato);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsameScreening {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    esito: ").append(toIndentedString(esito)).append("\n");
    sb.append("    aziendaSanitaria: ").append(toIndentedString(aziendaSanitaria)).append("\n");
    sb.append("    unitaOperativa: ").append(toIndentedString(unitaOperativa)).append("\n");
    sb.append("    prestazioni: ").append(toIndentedString(prestazioni)).append("\n");
    sb.append("    giudiziDiagnostici: ").append(toIndentedString(giudiziDiagnostici)).append("\n");
    sb.append("    indicazioneFinale: ").append(toIndentedString(indicazioneFinale)).append("\n");
    sb.append("    interventoErogato: ").append(toIndentedString(interventoErogato)).append("\n");
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

