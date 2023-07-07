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
import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.Medico;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

public class MetadatiDocumento   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
  
  private String codiceAzienda = null;
  private String codiceDocumentoDipartimentale = null;
  private String descrizioneAzienda = null;
  private String descrizioneStruttura = null;
  private String descrizioneUnitaOperativa = null;
  private Integer matricolaUpDipartimentale = null;
  private Date dataValidazione = null;
  private String codiceTipoDocumento = null;
  private String descrizioneTipoDocumento = null;
  private String tipoFile = null;
  private String codiceDipartimentale = null;
  private String codiceTipoDocumentoAlto = null;
  private String codiceAssettoOrganizzativo = null;
  private String descrizioneAssettoOrganizzativo = null;
  private String idRepositoryCl = null;
  private String identificativoRepository = null;
  private String codiceTipoFirma = null;
  private String descrizioneTipoFirma = null;
  private String importoTicketPagato = null;
  private String importoTicketDaPagare = null;
  private String pagatoTicket = null;
  private String oscuraScaricoCittadino = null;
  private String leggiSpeciali = null;
  private String autoreSmediazione = null;
  private Date dataSmediazione = null;
  private String coccarda = null;
  private Boolean flagEpisodiCollegati = null;
  private Boolean flagAssociazioniNre = null;
  private Boolean flagAssociazioneFigli = null;
  private List<String> accessionNumber = new ArrayList<String>();
  private String statoGenerazionePacchetto = null;
  private List<Medico> medici = new ArrayList<Medico>();
  private Episodio episodio = null;
  private List<String> nre = new ArrayList<String>();
  private String tipoContributo = null;

  /**
   **/
  

  @JsonProperty("codice_azienda") 
 
  public String getCodiceAzienda() {
    return codiceAzienda;
  }
  public void setCodiceAzienda(String codiceAzienda) {
    this.codiceAzienda = codiceAzienda;
  }

  /**
   **/
  

  @JsonProperty("codice_documento_dipartimentale") 
 
  public String getCodiceDocumentoDipartimentale() {
    return codiceDocumentoDipartimentale;
  }
  public void setCodiceDocumentoDipartimentale(String codiceDocumentoDipartimentale) {
    this.codiceDocumentoDipartimentale = codiceDocumentoDipartimentale;
  }

  /**
   **/
  

  @JsonProperty("descrizione_azienda") 
 
  public String getDescrizioneAzienda() {
    return descrizioneAzienda;
  }
  public void setDescrizioneAzienda(String descrizioneAzienda) {
    this.descrizioneAzienda = descrizioneAzienda;
  }

  /**
   **/
  

  @JsonProperty("descrizione_struttura") 
 
  public String getDescrizioneStruttura() {
    return descrizioneStruttura;
  }
  public void setDescrizioneStruttura(String descrizioneStruttura) {
    this.descrizioneStruttura = descrizioneStruttura;
  }

  /**
   **/
  

  @JsonProperty("descrizione_unita_operativa") 
 
  public String getDescrizioneUnitaOperativa() {
    return descrizioneUnitaOperativa;
  }
  public void setDescrizioneUnitaOperativa(String descrizioneUnitaOperativa) {
    this.descrizioneUnitaOperativa = descrizioneUnitaOperativa;
  }

  /**
   **/
  

  @JsonProperty("matricola_up_dipartimentale") 
 
  public Integer getMatricolaUpDipartimentale() {
    return matricolaUpDipartimentale;
  }
  public void setMatricolaUpDipartimentale(Integer matricolaUpDipartimentale) {
    this.matricolaUpDipartimentale = matricolaUpDipartimentale;
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
  

  @JsonProperty("codice_tipo_documento") 
 
  public String getCodiceTipoDocumento() {
    return codiceTipoDocumento;
  }
  public void setCodiceTipoDocumento(String codiceTipoDocumento) {
    this.codiceTipoDocumento = codiceTipoDocumento;
  }

  /**
   **/
  

  @JsonProperty("descrizione_tipo_documento") 
 
  public String getDescrizioneTipoDocumento() {
    return descrizioneTipoDocumento;
  }
  public void setDescrizioneTipoDocumento(String descrizioneTipoDocumento) {
    this.descrizioneTipoDocumento = descrizioneTipoDocumento;
  }

  /**
   **/
  

  @JsonProperty("tipo_file") 
 
  public String getTipoFile() {
    return tipoFile;
  }
  public void setTipoFile(String tipoFile) {
    this.tipoFile = tipoFile;
  }

  /**
   **/
  

  @JsonProperty("codice_dipartimentale") 
 
  public String getCodiceDipartimentale() {
    return codiceDipartimentale;
  }
  public void setCodiceDipartimentale(String codiceDipartimentale) {
    this.codiceDipartimentale = codiceDipartimentale;
  }

  /**
   **/
  

  @JsonProperty("codice_tipo_documento_alto") 
 
  public String getCodiceTipoDocumentoAlto() {
    return codiceTipoDocumentoAlto;
  }
  public void setCodiceTipoDocumentoAlto(String codiceTipoDocumentoAlto) {
    this.codiceTipoDocumentoAlto = codiceTipoDocumentoAlto;
  }

  /**
   **/
  

  @JsonProperty("codice_assetto_organizzativo") 
 
  public String getCodiceAssettoOrganizzativo() {
    return codiceAssettoOrganizzativo;
  }
  public void setCodiceAssettoOrganizzativo(String codiceAssettoOrganizzativo) {
    this.codiceAssettoOrganizzativo = codiceAssettoOrganizzativo;
  }

  /**
   **/
  

  @JsonProperty("descrizione_assetto_organizzativo") 
 
  public String getDescrizioneAssettoOrganizzativo() {
    return descrizioneAssettoOrganizzativo;
  }
  public void setDescrizioneAssettoOrganizzativo(String descrizioneAssettoOrganizzativo) {
    this.descrizioneAssettoOrganizzativo = descrizioneAssettoOrganizzativo;
  }

  /**
   **/
  

  @JsonProperty("id_repository_cl") 
 
  public String getIdRepositoryCl() {
    return idRepositoryCl;
  }
  public void setIdRepositoryCl(String idRepositoryCl) {
    this.idRepositoryCl = idRepositoryCl;
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
  

  @JsonProperty("codice_tipo_firma") 
 
  public String getCodiceTipoFirma() {
    return codiceTipoFirma;
  }
  public void setCodiceTipoFirma(String codiceTipoFirma) {
    this.codiceTipoFirma = codiceTipoFirma;
  }

  /**
   **/
  

  @JsonProperty("descrizione_tipo_firma") 
 
  public String getDescrizioneTipoFirma() {
    return descrizioneTipoFirma;
  }
  public void setDescrizioneTipoFirma(String descrizioneTipoFirma) {
    this.descrizioneTipoFirma = descrizioneTipoFirma;
  }

  /**
   **/
  

  @JsonProperty("importo_ticket_pagato") 
 
  public String getImportoTicketPagato() {
    return importoTicketPagato;
  }
  public void setImportoTicketPagato(String importoTicketPagato) {
    this.importoTicketPagato = importoTicketPagato;
  }

  /**
   **/
  

  @JsonProperty("importo_ticket_da_pagare") 
 
  public String getImportoTicketDaPagare() {
    return importoTicketDaPagare;
  }
  public void setImportoTicketDaPagare(String importoTicketDaPagare) {
    this.importoTicketDaPagare = importoTicketDaPagare;
  }

  /**
   **/
  

  @JsonProperty("pagato_ticket") 
 
  public String getPagatoTicket() {
    return pagatoTicket;
  }
  public void setPagatoTicket(String pagatoTicket) {
    this.pagatoTicket = pagatoTicket;
  }

  /**
   **/
  

  @JsonProperty("oscura_scarico_cittadino") 
 
  public String getOscuraScaricoCittadino() {
    return oscuraScaricoCittadino;
  }
  public void setOscuraScaricoCittadino(String oscuraScaricoCittadino) {
    this.oscuraScaricoCittadino = oscuraScaricoCittadino;
  }

  /**
   **/
  

  @JsonProperty("leggi_speciali") 
 
  public String getLeggiSpeciali() {
    return leggiSpeciali;
  }
  public void setLeggiSpeciali(String leggiSpeciali) {
    this.leggiSpeciali = leggiSpeciali;
  }

  /**
   **/
  

  @JsonProperty("autore_smediazione") 
 
  public String getAutoreSmediazione() {
    return autoreSmediazione;
  }
  public void setAutoreSmediazione(String autoreSmediazione) {
    this.autoreSmediazione = autoreSmediazione;
  }

  /**
   **/
  

  @JsonProperty("data_smediazione") 
 
  public Date getDataSmediazione() {
    return dataSmediazione;
  }
  public void setDataSmediazione(Date dataSmediazione) {
    this.dataSmediazione = dataSmediazione;
  }

  /**
   **/
  

  @JsonProperty("coccarda") 
 
  public String getCoccarda() {
    return coccarda;
  }
  public void setCoccarda(String coccarda) {
    this.coccarda = coccarda;
  }

  /**
   **/
  

  @JsonProperty("flag_episodi_collegati") 
 
  public Boolean isFlagEpisodiCollegati() {
    return flagEpisodiCollegati;
  }
  public void setFlagEpisodiCollegati(Boolean flagEpisodiCollegati) {
    this.flagEpisodiCollegati = flagEpisodiCollegati;
  }

  /**
   **/
  

  @JsonProperty("flag_associazioni_nre") 
 
  public Boolean isFlagAssociazioniNre() {
    return flagAssociazioniNre;
  }
  public void setFlagAssociazioniNre(Boolean flagAssociazioniNre) {
    this.flagAssociazioniNre = flagAssociazioniNre;
  }

  /**
   **/
  

  @JsonProperty("flag_associazione_figli") 
 
  public Boolean isFlagAssociazioneFigli() {
    return flagAssociazioneFigli;
  }
  public void setFlagAssociazioneFigli(Boolean flagAssociazioneFigli) {
    this.flagAssociazioneFigli = flagAssociazioneFigli;
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
  

  @JsonProperty("stato_generazione_pacchetto") 
 
  public String getStatoGenerazionePacchetto() {
    return statoGenerazionePacchetto;
  }
  public void setStatoGenerazionePacchetto(String statoGenerazionePacchetto) {
    this.statoGenerazionePacchetto = statoGenerazionePacchetto;
  }

  /**
   **/
  

  @JsonProperty("medici") 
 
  public List<Medico> getMedici() {
    return medici;
  }
  public void setMedici(List<Medico> medici) {
    this.medici = medici;
  }

  /**
   **/
  

  @JsonProperty("episodio") 
 
  public Episodio getEpisodio() {
    return episodio;
  }
  public void setEpisodio(Episodio episodio) {
    this.episodio = episodio;
  }

  /**
   **/
  

  @JsonProperty("nre") 
 
  public List<String> getNre() {
    return nre;
  }
  public void setNre(List<String> nre) {
    this.nre = nre;
  }

  /**
   **/
  

  @JsonProperty("tipo_contributo") 
 
  public String getTipoContributo() {
    return tipoContributo;
  }
  public void setTipoContributo(String tipoContributo) {
    this.tipoContributo = tipoContributo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadatiDocumento metadatiDocumento = (MetadatiDocumento) o;
    return Objects.equals(codiceAzienda, metadatiDocumento.codiceAzienda) &&
        Objects.equals(codiceDocumentoDipartimentale, metadatiDocumento.codiceDocumentoDipartimentale) &&
        Objects.equals(descrizioneAzienda, metadatiDocumento.descrizioneAzienda) &&
        Objects.equals(descrizioneStruttura, metadatiDocumento.descrizioneStruttura) &&
        Objects.equals(descrizioneUnitaOperativa, metadatiDocumento.descrizioneUnitaOperativa) &&
        Objects.equals(matricolaUpDipartimentale, metadatiDocumento.matricolaUpDipartimentale) &&
        Objects.equals(dataValidazione, metadatiDocumento.dataValidazione) &&
        Objects.equals(codiceTipoDocumento, metadatiDocumento.codiceTipoDocumento) &&
        Objects.equals(descrizioneTipoDocumento, metadatiDocumento.descrizioneTipoDocumento) &&
        Objects.equals(tipoFile, metadatiDocumento.tipoFile) &&
        Objects.equals(codiceDipartimentale, metadatiDocumento.codiceDipartimentale) &&
        Objects.equals(codiceTipoDocumentoAlto, metadatiDocumento.codiceTipoDocumentoAlto) &&
        Objects.equals(codiceAssettoOrganizzativo, metadatiDocumento.codiceAssettoOrganizzativo) &&
        Objects.equals(descrizioneAssettoOrganizzativo, metadatiDocumento.descrizioneAssettoOrganizzativo) &&
        Objects.equals(idRepositoryCl, metadatiDocumento.idRepositoryCl) &&
        Objects.equals(identificativoRepository, metadatiDocumento.identificativoRepository) &&
        Objects.equals(codiceTipoFirma, metadatiDocumento.codiceTipoFirma) &&
        Objects.equals(descrizioneTipoFirma, metadatiDocumento.descrizioneTipoFirma) &&
        Objects.equals(importoTicketPagato, metadatiDocumento.importoTicketPagato) &&
        Objects.equals(importoTicketDaPagare, metadatiDocumento.importoTicketDaPagare) &&
        Objects.equals(pagatoTicket, metadatiDocumento.pagatoTicket) &&
        Objects.equals(oscuraScaricoCittadino, metadatiDocumento.oscuraScaricoCittadino) &&
        Objects.equals(leggiSpeciali, metadatiDocumento.leggiSpeciali) &&
        Objects.equals(autoreSmediazione, metadatiDocumento.autoreSmediazione) &&
        Objects.equals(dataSmediazione, metadatiDocumento.dataSmediazione) &&
        Objects.equals(coccarda, metadatiDocumento.coccarda) &&
        Objects.equals(flagEpisodiCollegati, metadatiDocumento.flagEpisodiCollegati) &&
        Objects.equals(flagAssociazioniNre, metadatiDocumento.flagAssociazioniNre) &&
        Objects.equals(flagAssociazioneFigli, metadatiDocumento.flagAssociazioneFigli) &&
        Objects.equals(accessionNumber, metadatiDocumento.accessionNumber) &&
        Objects.equals(statoGenerazionePacchetto, metadatiDocumento.statoGenerazionePacchetto) &&
        Objects.equals(medici, metadatiDocumento.medici) &&
        Objects.equals(episodio, metadatiDocumento.episodio) &&
        Objects.equals(nre, metadatiDocumento.nre) &&
        Objects.equals(tipoContributo, metadatiDocumento.tipoContributo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codiceAzienda, codiceDocumentoDipartimentale, descrizioneAzienda, descrizioneStruttura, descrizioneUnitaOperativa, matricolaUpDipartimentale, dataValidazione, codiceTipoDocumento, descrizioneTipoDocumento, tipoFile, codiceDipartimentale, codiceTipoDocumentoAlto, codiceAssettoOrganizzativo, descrizioneAssettoOrganizzativo, idRepositoryCl, identificativoRepository, codiceTipoFirma, descrizioneTipoFirma, importoTicketPagato, importoTicketDaPagare, pagatoTicket, oscuraScaricoCittadino, leggiSpeciali, autoreSmediazione, dataSmediazione, coccarda, flagEpisodiCollegati, flagAssociazioniNre, flagAssociazioneFigli, accessionNumber, statoGenerazionePacchetto, medici, episodio, nre, tipoContributo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetadatiDocumento {\n");
    
    sb.append("    codiceAzienda: ").append(toIndentedString(codiceAzienda)).append("\n");
    sb.append("    codiceDocumentoDipartimentale: ").append(toIndentedString(codiceDocumentoDipartimentale)).append("\n");
    sb.append("    descrizioneAzienda: ").append(toIndentedString(descrizioneAzienda)).append("\n");
    sb.append("    descrizioneStruttura: ").append(toIndentedString(descrizioneStruttura)).append("\n");
    sb.append("    descrizioneUnitaOperativa: ").append(toIndentedString(descrizioneUnitaOperativa)).append("\n");
    sb.append("    matricolaUpDipartimentale: ").append(toIndentedString(matricolaUpDipartimentale)).append("\n");
    sb.append("    dataValidazione: ").append(toIndentedString(dataValidazione)).append("\n");
    sb.append("    codiceTipoDocumento: ").append(toIndentedString(codiceTipoDocumento)).append("\n");
    sb.append("    descrizioneTipoDocumento: ").append(toIndentedString(descrizioneTipoDocumento)).append("\n");
    sb.append("    tipoFile: ").append(toIndentedString(tipoFile)).append("\n");
    sb.append("    codiceDipartimentale: ").append(toIndentedString(codiceDipartimentale)).append("\n");
    sb.append("    codiceTipoDocumentoAlto: ").append(toIndentedString(codiceTipoDocumentoAlto)).append("\n");
    sb.append("    codiceAssettoOrganizzativo: ").append(toIndentedString(codiceAssettoOrganizzativo)).append("\n");
    sb.append("    descrizioneAssettoOrganizzativo: ").append(toIndentedString(descrizioneAssettoOrganizzativo)).append("\n");
    sb.append("    idRepositoryCl: ").append(toIndentedString(idRepositoryCl)).append("\n");
    sb.append("    identificativoRepository: ").append(toIndentedString(identificativoRepository)).append("\n");
    sb.append("    codiceTipoFirma: ").append(toIndentedString(codiceTipoFirma)).append("\n");
    sb.append("    descrizioneTipoFirma: ").append(toIndentedString(descrizioneTipoFirma)).append("\n");
    sb.append("    importoTicketPagato: ").append(toIndentedString(importoTicketPagato)).append("\n");
    sb.append("    importoTicketDaPagare: ").append(toIndentedString(importoTicketDaPagare)).append("\n");
    sb.append("    pagatoTicket: ").append(toIndentedString(pagatoTicket)).append("\n");
    sb.append("    oscuraScaricoCittadino: ").append(toIndentedString(oscuraScaricoCittadino)).append("\n");
    sb.append("    leggiSpeciali: ").append(toIndentedString(leggiSpeciali)).append("\n");
    sb.append("    autoreSmediazione: ").append(toIndentedString(autoreSmediazione)).append("\n");
    sb.append("    dataSmediazione: ").append(toIndentedString(dataSmediazione)).append("\n");
    sb.append("    coccarda: ").append(toIndentedString(coccarda)).append("\n");
    sb.append("    flagEpisodiCollegati: ").append(toIndentedString(flagEpisodiCollegati)).append("\n");
    sb.append("    flagAssociazioniNre: ").append(toIndentedString(flagAssociazioniNre)).append("\n");
    sb.append("    flagAssociazioneFigli: ").append(toIndentedString(flagAssociazioneFigli)).append("\n");
    sb.append("    accessionNumber: ").append(toIndentedString(accessionNumber)).append("\n");
    sb.append("    statoGenerazionePacchetto: ").append(toIndentedString(statoGenerazionePacchetto)).append("\n");
    sb.append("    medici: ").append(toIndentedString(medici)).append("\n");
    sb.append("    episodio: ").append(toIndentedString(episodio)).append("\n");
    sb.append("    nre: ").append(toIndentedString(nre)).append("\n");
    sb.append("    tipoContributo: ").append(toIndentedString(tipoContributo)).append("\n");
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

