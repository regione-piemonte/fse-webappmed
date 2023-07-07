/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.dma.apiopsan.dto.custom.EpisodioEsteso;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Date;
import javax.validation.constraints.*;

public class Episodio  implements Comparable<Episodio> {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long idEpisodio = null;
  private String codiceTipoEpisodio = null;
  private String descrizioneTipoEpisodio = null;
  private String citId = null;
  private String codiceCl = null;
  private Date dataInizio = null;
  private Date dataFine = null;
  private String aziendaAccettazione = null;
  private String descrizioneAziendaAccettazione = null;
  private String matricolaAccettazione = null;
  private String strutturaAccettazione = null;
  private String unitaOperativaAccettazione = null;
  private String aziendaDimissione = null;
  private String descrizioneAziendaDimissione = null;
  private String matricolaDimissione = null;
  private String strutturaDimissione = null;
  private String unitaOperativaDimissione = null;
  
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
  

  @JsonProperty("codice_tipo_episodio") 
 
  public String getCodiceTipoEpisodio() {
    return codiceTipoEpisodio;
  }
  public void setCodiceTipoEpisodio(String codiceTipoEpisodio) {
    this.codiceTipoEpisodio = codiceTipoEpisodio;
  }

  /**
   **/
  

  @JsonProperty("descrizione_tipo_episodio") 
 
  public String getDescrizioneTipoEpisodio() {
    return descrizioneTipoEpisodio;
  }
  public void setDescrizioneTipoEpisodio(String descrizioneTipoEpisodio) {
    this.descrizioneTipoEpisodio = descrizioneTipoEpisodio;
  }

  /**
   **/
  

  @JsonProperty("cit_id") 
 
  public String getCitId() {
    return citId;
  }
  public void setCitId(String citId) {
    this.citId = citId;
  }

  /**
   **/
  

  @JsonProperty("codice_cl") 
 
  public String getCodiceCl() {
    return codiceCl;
  }
  public void setCodiceCl(String codiceCl) {
    this.codiceCl = codiceCl;
  }

  /**
   **/
  

  @JsonProperty("data_inizio") 
 
  public Date getDataInizio() {
    return dataInizio;
  }
  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  /**
   **/
  

  @JsonProperty("data_fine") 
 
  public Date getDataFine() {
    return dataFine;
  }
  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
  }

  /**
   **/
  

  @JsonProperty("azienda_accettazione") 
 
  public String getAziendaAccettazione() {
    return aziendaAccettazione;
  }
  public void setAziendaAccettazione(String aziendaAccettazione) {
    this.aziendaAccettazione = aziendaAccettazione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_azienda_accettazione") 
 
  public String getDescrizioneAziendaAccettazione() {
    return descrizioneAziendaAccettazione;
  }
  public void setDescrizioneAziendaAccettazione(String descrizioneAziendaAccettazione) {
    this.descrizioneAziendaAccettazione = descrizioneAziendaAccettazione;
  }

  /**
   **/
  

  @JsonProperty("matricola_accettazione") 
 
  public String getMatricolaAccettazione() {
    return matricolaAccettazione;
  }
  public void setMatricolaAccettazione(String matricolaAccettazione) {
    this.matricolaAccettazione = matricolaAccettazione;
  }

  /**
   **/
  

  @JsonProperty("struttura_accettazione") 
 
  public String getStrutturaAccettazione() {
    return strutturaAccettazione;
  }
  public void setStrutturaAccettazione(String strutturaAccettazione) {
    this.strutturaAccettazione = strutturaAccettazione;
  }

  /**
   **/
  

  @JsonProperty("unita_operativa_accettazione") 
 
  public String getUnitaOperativaAccettazione() {
    return unitaOperativaAccettazione;
  }
  public void setUnitaOperativaAccettazione(String unitaOperativaAccettazione) {
    this.unitaOperativaAccettazione = unitaOperativaAccettazione;
  }

  /**
   **/
  

  @JsonProperty("azienda_dimissione") 
 
  public String getAziendaDimissione() {
    return aziendaDimissione;
  }
  public void setAziendaDimissione(String aziendaDimissione) {
    this.aziendaDimissione = aziendaDimissione;
  }

  /**
   **/
  

  @JsonProperty("descrizione_azienda_dimissione") 
 
  public String getDescrizioneAziendaDimissione() {
    return descrizioneAziendaDimissione;
  }
  public void setDescrizioneAziendaDimissione(String descrizioneAziendaDimissione) {
    this.descrizioneAziendaDimissione = descrizioneAziendaDimissione;
  }

  /**
   **/
  

  @JsonProperty("matricola_dimissione") 
 
  public String getMatricolaDimissione() {
    return matricolaDimissione;
  }
  public void setMatricolaDimissione(String matricolaDimissione) {
    this.matricolaDimissione = matricolaDimissione;
  }

  /**
   **/
  

  @JsonProperty("struttura_dimissione") 
 
  public String getStrutturaDimissione() {
    return strutturaDimissione;
  }
  public void setStrutturaDimissione(String strutturaDimissione) {
    this.strutturaDimissione = strutturaDimissione;
  }

  /**
   **/
  

  @JsonProperty("unita_operativa_dimissione") 
 
  public String getUnitaOperativaDimissione() {
    return unitaOperativaDimissione;
  }
  public void setUnitaOperativaDimissione(String unitaOperativaDimissione) {
    this.unitaOperativaDimissione = unitaOperativaDimissione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Episodio episodio = (Episodio) o;
    return Objects.equals(idEpisodio, episodio.idEpisodio) &&
        Objects.equals(codiceTipoEpisodio, episodio.codiceTipoEpisodio) &&
        Objects.equals(descrizioneTipoEpisodio, episodio.descrizioneTipoEpisodio) &&
        Objects.equals(citId, episodio.citId) &&
        Objects.equals(codiceCl, episodio.codiceCl) &&
        Objects.equals(dataInizio, episodio.dataInizio) &&
        Objects.equals(dataFine, episodio.dataFine) &&
        Objects.equals(aziendaAccettazione, episodio.aziendaAccettazione) &&
        Objects.equals(descrizioneAziendaAccettazione, episodio.descrizioneAziendaAccettazione) &&
        Objects.equals(matricolaAccettazione, episodio.matricolaAccettazione) &&
        Objects.equals(strutturaAccettazione, episodio.strutturaAccettazione) &&
        Objects.equals(unitaOperativaAccettazione, episodio.unitaOperativaAccettazione) &&
        Objects.equals(aziendaDimissione, episodio.aziendaDimissione) &&
        Objects.equals(descrizioneAziendaDimissione, episodio.descrizioneAziendaDimissione) &&
        Objects.equals(matricolaDimissione, episodio.matricolaDimissione) &&
        Objects.equals(strutturaDimissione, episodio.strutturaDimissione) &&
        Objects.equals(unitaOperativaDimissione, episodio.unitaOperativaDimissione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEpisodio, codiceTipoEpisodio, descrizioneTipoEpisodio, citId, codiceCl, dataInizio, dataFine, aziendaAccettazione, descrizioneAziendaAccettazione, matricolaAccettazione, strutturaAccettazione, unitaOperativaAccettazione, aziendaDimissione, descrizioneAziendaDimissione, matricolaDimissione, strutturaDimissione, unitaOperativaDimissione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Episodio {\n");
    
    sb.append("    idEpisodio: ").append(toIndentedString(idEpisodio)).append("\n");
    sb.append("    codiceTipoEpisodio: ").append(toIndentedString(codiceTipoEpisodio)).append("\n");
    sb.append("    descrizioneTipoEpisodio: ").append(toIndentedString(descrizioneTipoEpisodio)).append("\n");
    sb.append("    citId: ").append(toIndentedString(citId)).append("\n");
    sb.append("    codiceCl: ").append(toIndentedString(codiceCl)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
    sb.append("    aziendaAccettazione: ").append(toIndentedString(aziendaAccettazione)).append("\n");
    sb.append("    descrizioneAziendaAccettazione: ").append(toIndentedString(descrizioneAziendaAccettazione)).append("\n");
    sb.append("    matricolaAccettazione: ").append(toIndentedString(matricolaAccettazione)).append("\n");
    sb.append("    strutturaAccettazione: ").append(toIndentedString(strutturaAccettazione)).append("\n");
    sb.append("    unitaOperativaAccettazione: ").append(toIndentedString(unitaOperativaAccettazione)).append("\n");
    sb.append("    aziendaDimissione: ").append(toIndentedString(aziendaDimissione)).append("\n");
    sb.append("    descrizioneAziendaDimissione: ").append(toIndentedString(descrizioneAziendaDimissione)).append("\n");
    sb.append("    matricolaDimissione: ").append(toIndentedString(matricolaDimissione)).append("\n");
    sb.append("    strutturaDimissione: ").append(toIndentedString(strutturaDimissione)).append("\n");
    sb.append("    unitaOperativaDimissione: ").append(toIndentedString(unitaOperativaDimissione)).append("\n");
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
  @Override
	public int compareTo(Episodio o) {
		return (this.getDataInizio()).compareTo(o.getDataInizio());
	}
	
}

