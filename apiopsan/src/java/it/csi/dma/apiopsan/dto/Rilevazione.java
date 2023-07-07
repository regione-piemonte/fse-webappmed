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
import it.csi.dma.apiopsan.dto.CodiceExt;
import it.csi.dma.apiopsan.dto.ModalitaRilevazione;
import java.util.Date;
import javax.validation.constraints.*;

public class Rilevazione   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private Long id = null;
  private Integer valoreNumerico = null;
  private String valoreTestuale = null;
  private Date data = null;
  private Date dataCreazione = null;
  private Date dataAggiornamento = null;
  private Codice unitaMisura = null;
  private CodiceExt gruppo = null;
  private CodiceExt descrittore = null;
  private ModalitaRilevazione modalitaRilevazione = null;

  /**
   **/
  

  @JsonProperty("id") 
 
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   **/
  

  @JsonProperty("valore_numerico") 
 
  public Integer getValoreNumerico() {
    return valoreNumerico;
  }
  public void setValoreNumerico(Integer valoreNumerico) {
    this.valoreNumerico = valoreNumerico;
  }

  /**
   **/
  

  @JsonProperty("valore_testuale") 
 
  public String getValoreTestuale() {
    return valoreTestuale;
  }
  public void setValoreTestuale(String valoreTestuale) {
    this.valoreTestuale = valoreTestuale;
  }

  /**
   **/
  

  @JsonProperty("data") 
 
  public Date getData() {
    return data;
  }
  public void setData(Date data) {
    this.data = data;
  }

  /**
   **/
  

  @JsonProperty("data_creazione") 
 
  public Date getDataCreazione() {
    return dataCreazione;
  }
  public void setDataCreazione(Date dataCreazione) {
    this.dataCreazione = dataCreazione;
  }

  /**
   **/
  

  @JsonProperty("data_aggiornamento") 
 
  public Date getDataAggiornamento() {
    return dataAggiornamento;
  }
  public void setDataAggiornamento(Date dataAggiornamento) {
    this.dataAggiornamento = dataAggiornamento;
  }

  /**
   **/
  

  @JsonProperty("unita_misura") 
 
  public Codice getUnitaMisura() {
    return unitaMisura;
  }
  public void setUnitaMisura(Codice unitaMisura) {
    this.unitaMisura = unitaMisura;
  }

  /**
   **/
  

  @JsonProperty("gruppo") 
 
  public CodiceExt getGruppo() {
    return gruppo;
  }
  public void setGruppo(CodiceExt gruppo) {
    this.gruppo = gruppo;
  }

  /**
   **/
  

  @JsonProperty("descrittore") 
 
  public CodiceExt getDescrittore() {
    return descrittore;
  }
  public void setDescrittore(CodiceExt descrittore) {
    this.descrittore = descrittore;
  }

  /**
   **/
  

  @JsonProperty("modalita_rilevazione") 
 
  public ModalitaRilevazione getModalitaRilevazione() {
    return modalitaRilevazione;
  }
  public void setModalitaRilevazione(ModalitaRilevazione modalitaRilevazione) {
    this.modalitaRilevazione = modalitaRilevazione;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rilevazione rilevazione = (Rilevazione) o;
    return Objects.equals(id, rilevazione.id) &&
        Objects.equals(valoreNumerico, rilevazione.valoreNumerico) &&
        Objects.equals(valoreTestuale, rilevazione.valoreTestuale) &&
        Objects.equals(data, rilevazione.data) &&
        Objects.equals(dataCreazione, rilevazione.dataCreazione) &&
        Objects.equals(dataAggiornamento, rilevazione.dataAggiornamento) &&
        Objects.equals(unitaMisura, rilevazione.unitaMisura) &&
        Objects.equals(gruppo, rilevazione.gruppo) &&
        Objects.equals(descrittore, rilevazione.descrittore) &&
        Objects.equals(modalitaRilevazione, rilevazione.modalitaRilevazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, valoreNumerico, valoreTestuale, data, dataCreazione, dataAggiornamento, unitaMisura, gruppo, descrittore, modalitaRilevazione);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rilevazione {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    valoreNumerico: ").append(toIndentedString(valoreNumerico)).append("\n");
    sb.append("    valoreTestuale: ").append(toIndentedString(valoreTestuale)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    dataCreazione: ").append(toIndentedString(dataCreazione)).append("\n");
    sb.append("    dataAggiornamento: ").append(toIndentedString(dataAggiornamento)).append("\n");
    sb.append("    unitaMisura: ").append(toIndentedString(unitaMisura)).append("\n");
    sb.append("    gruppo: ").append(toIndentedString(gruppo)).append("\n");
    sb.append("    descrittore: ").append(toIndentedString(descrittore)).append("\n");
    sb.append("    modalitaRilevazione: ").append(toIndentedString(modalitaRilevazione)).append("\n");
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

