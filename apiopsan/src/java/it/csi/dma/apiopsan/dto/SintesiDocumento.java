/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SintesiDocumento  implements Serializable {
  private static final long serialVersionUID = -5562291681359480500L;
// verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String categoria = null;
  private String citId = null;
  private String codiceCl = null;
  private Long idDocumentoIlec = null;
  private String numeroNosologico = null;
  private String passaggioPs = null;
  private Boolean pazientePiemontese = null;
  private MetadatiDocumento metadatiDocumento = null;
  private String recuperoPregresso = null;

  /**
   **/
  

  @JsonProperty("categoria") 
 
  public String getCategoria() {
    return categoria;
  }
  public void setCategoria(String categoria) {
    this.categoria = categoria;
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
  

  @JsonProperty("id_documento_ilec") 
 
  public Long getIdDocumentoIlec() {
    return idDocumentoIlec;
  }
  public void setIdDocumentoIlec(Long idDocumentoIlec) {
    this.idDocumentoIlec = idDocumentoIlec;
  }

  /**
   **/
  

  @JsonProperty("numero_nosologico") 
 
  public String getNumeroNosologico() {
    return numeroNosologico;
  }
  public void setNumeroNosologico(String numeroNosologico) {
    this.numeroNosologico = numeroNosologico;
  }

  /**
   **/
  

  @JsonProperty("passaggio_ps") 
 
  public String getPassaggioPs() {
    return passaggioPs;
  }
  public void setPassaggioPs(String passaggioPs) {
    this.passaggioPs = passaggioPs;
  }

  /**
   **/
  

  @JsonProperty("paziente_piemontese") 
 
  public Boolean isPazientePiemontese() {
    return pazientePiemontese;
  }
  public void setPazientePiemontese(Boolean pazientePiemontese) {
    this.pazientePiemontese = pazientePiemontese;
  }

  /**
   **/
  

  @JsonProperty("metadati_documento") 
 
  public MetadatiDocumento getMetadatiDocumento() {
    return metadatiDocumento;
  }
  public void setMetadatiDocumento(MetadatiDocumento metadatiDocumento) {
    this.metadatiDocumento = metadatiDocumento;
  }

  /**
   **/
  

  @JsonProperty("recupero_pregresso") 
 
  public String getRecuperoPregresso() {
    return recuperoPregresso;
  }
  public void setRecuperoPregresso(String recuperoPregresso) {
    this.recuperoPregresso = recuperoPregresso;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SintesiDocumento sintesiDocumento = (SintesiDocumento) o;
    return Objects.equals(categoria, sintesiDocumento.categoria) &&
        Objects.equals(citId, sintesiDocumento.citId) &&
        Objects.equals(codiceCl, sintesiDocumento.codiceCl) &&
        Objects.equals(idDocumentoIlec, sintesiDocumento.idDocumentoIlec) &&
        Objects.equals(numeroNosologico, sintesiDocumento.numeroNosologico) &&
        Objects.equals(passaggioPs, sintesiDocumento.passaggioPs) &&
        Objects.equals(pazientePiemontese, sintesiDocumento.pazientePiemontese) &&
        Objects.equals(metadatiDocumento, sintesiDocumento.metadatiDocumento) &&
        Objects.equals(recuperoPregresso, sintesiDocumento.recuperoPregresso);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoria, citId, codiceCl, idDocumentoIlec, numeroNosologico, passaggioPs, pazientePiemontese, metadatiDocumento, recuperoPregresso);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SintesiDocumento {\n");
    
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    citId: ").append(toIndentedString(citId)).append("\n");
    sb.append("    codiceCl: ").append(toIndentedString(codiceCl)).append("\n");
    sb.append("    idDocumentoIlec: ").append(toIndentedString(idDocumentoIlec)).append("\n");
    sb.append("    numeroNosologico: ").append(toIndentedString(numeroNosologico)).append("\n");
    sb.append("    passaggioPs: ").append(toIndentedString(passaggioPs)).append("\n");
    sb.append("    pazientePiemontese: ").append(toIndentedString(pazientePiemontese)).append("\n");
    sb.append("    metadatiDocumento: ").append(toIndentedString(metadatiDocumento)).append("\n");
    sb.append("    recuperoPregresso: ").append(toIndentedString(recuperoPregresso)).append("\n");
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

