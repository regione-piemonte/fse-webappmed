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
import java.util.List;
import javax.validation.constraints.*;

public class ScaricoStudi   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String citId = null;
  private String idReferto = null;
  private Integer periodoConservazione = null;
  private String pin = null;
  private List<String> accessionNumber = new ArrayList<String>();
  private String codCl = null;

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
  

  @JsonProperty("id_referto") 
 
  public String getIdReferto() {
    return idReferto;
  }
  public void setIdReferto(String idReferto) {
    this.idReferto = idReferto;
  }

  /**
   **/
  

  @JsonProperty("periodo_conservazione") 
 
  public Integer getPeriodoConservazione() {
    return periodoConservazione;
  }
  public void setPeriodoConservazione(Integer periodoConservazione) {
    this.periodoConservazione = periodoConservazione;
  }

  /**
   **/
  

  @JsonProperty("pin") 
 
  public String getPin() {
    return pin;
  }
  public void setPin(String pin) {
    this.pin = pin;
  }

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
  

  @JsonProperty("cod_cl") 
 
  public String getCodCl() {
    return codCl;
  }
  public void setCodCl(String codCl) {
    this.codCl = codCl;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaricoStudi scaricoStudi = (ScaricoStudi) o;
    return Objects.equals(citId, scaricoStudi.citId) &&
        Objects.equals(idReferto, scaricoStudi.idReferto) &&
        Objects.equals(periodoConservazione, scaricoStudi.periodoConservazione) &&
        Objects.equals(pin, scaricoStudi.pin) &&
        Objects.equals(accessionNumber, scaricoStudi.accessionNumber) &&
        Objects.equals(codCl, scaricoStudi.codCl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(citId, idReferto, periodoConservazione, pin, accessionNumber, codCl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaricoStudi {\n");
    
    sb.append("    citId: ").append(toIndentedString(citId)).append("\n");
    sb.append("    idReferto: ").append(toIndentedString(idReferto)).append("\n");
    sb.append("    periodoConservazione: ").append(toIndentedString(periodoConservazione)).append("\n");
    sb.append("    pin: ").append(toIndentedString(pin)).append("\n");
    sb.append("    accessionNumber: ").append(toIndentedString(accessionNumber)).append("\n");
    sb.append("    codCl: ").append(toIndentedString(codCl)).append("\n");
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

