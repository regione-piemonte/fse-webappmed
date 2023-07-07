/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.ricercadocumentiiniservice.fse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per metadatoRicercaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="metadatoRicercaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipologiaStrutturaProdDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoAssistito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipoMime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoOrgDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoRepository" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VersioneOggettoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipologiaDocumentoAlto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipologiaDocumentoMedio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TipologiaDocumentoBasso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoUnivocoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataValidazioneDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RuoloAutore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IstituzioneAutore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CodiceFiscaleAutore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SpecialitaAutore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TelecAutore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="HashDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SizeDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AssettoOrganizzativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentificativoPaziente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LinguaDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataInizioPrestazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataFinePrestazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RiferimentoDocPrescr" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ConservazioneSost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LivelloConfidenzialita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RegoleAccesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DocumentoFirmato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Descrizione" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="RegimeDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="eventCodeList" type="{http://tipodatiricercadocumenti.xsd.fse.ini.finanze.it}eventCodeListType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="elencoOpzioniMetadati" type="{http://tipodatiricercadocumenti.xsd.fse.ini.finanze.it}opzioniType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadatoRicercaType", propOrder = {
    "tipologiaStrutturaProdDoc",
    "identificativoAssistito",
    "tipoMime",
    "identificativoOrgDoc",
    "identificativoRepository",
    "identificativoDocumento",
    "versioneOggettoDocumento",
    "tipologiaDocumentoAlto",
    "tipologiaDocumentoMedio",
    "tipologiaDocumentoBasso",
    "identificativoUnivocoDocumento",
    "dataValidazioneDocumento",
    "ruoloAutore",
    "istituzioneAutore",
    "codiceFiscaleAutore",
    "specialitaAutore",
    "telecAutore",
    "hashDoc",
    "sizeDoc",
    "assettoOrganizzativo",
    "identificativoPaziente",
    "linguaDocumento",
    "dataInizioPrestazione",
    "dataFinePrestazione",
    "riferimentoDocPrescr",
    "conservazioneSost",
    "livelloConfidenzialita",
    "regoleAccesso",
    "lid",
    "documentoFirmato",
    "descrizione",
    "regimeDocumento",
    "eventCodeList",
    "elencoOpzioniMetadati"
})
public class MetadatoRicercaType {

    @XmlElement(name = "TipologiaStrutturaProdDoc")
    protected String tipologiaStrutturaProdDoc;
    @XmlElement(name = "IdentificativoAssistito")
    protected String identificativoAssistito;
    @XmlElement(name = "TipoMime")
    protected String tipoMime;
    @XmlElement(name = "IdentificativoOrgDoc")
    protected String identificativoOrgDoc;
    @XmlElement(name = "IdentificativoRepository")
    protected String identificativoRepository;
    @XmlElement(name = "IdentificativoDocumento")
    protected String identificativoDocumento;
    @XmlElement(name = "VersioneOggettoDocumento")
    protected String versioneOggettoDocumento;
    @XmlElement(name = "TipologiaDocumentoAlto")
    protected String tipologiaDocumentoAlto;
    @XmlElement(name = "TipologiaDocumentoMedio")
    protected String tipologiaDocumentoMedio;
    @XmlElement(name = "TipologiaDocumentoBasso")
    protected String tipologiaDocumentoBasso;
    @XmlElement(name = "IdentificativoUnivocoDocumento")
    protected String identificativoUnivocoDocumento;
    @XmlElement(name = "DataValidazioneDocumento")
    protected String dataValidazioneDocumento;
    @XmlElement(name = "RuoloAutore")
    protected String ruoloAutore;
    @XmlElement(name = "IstituzioneAutore")
    protected String istituzioneAutore;
    @XmlElement(name = "CodiceFiscaleAutore")
    protected String codiceFiscaleAutore;
    @XmlElement(name = "SpecialitaAutore")
    protected String specialitaAutore;
    @XmlElement(name = "TelecAutore")
    protected String telecAutore;
    @XmlElement(name = "HashDoc")
    protected String hashDoc;
    @XmlElement(name = "SizeDoc")
    protected String sizeDoc;
    @XmlElement(name = "AssettoOrganizzativo")
    protected String assettoOrganizzativo;
    @XmlElement(name = "IdentificativoPaziente")
    protected String identificativoPaziente;
    @XmlElement(name = "LinguaDocumento")
    protected String linguaDocumento;
    @XmlElement(name = "DataInizioPrestazione")
    protected String dataInizioPrestazione;
    @XmlElement(name = "DataFinePrestazione")
    protected String dataFinePrestazione;
    @XmlElement(name = "RiferimentoDocPrescr")
    protected List<String> riferimentoDocPrescr;
    @XmlElement(name = "ConservazioneSost")
    protected String conservazioneSost;
    @XmlElement(name = "LivelloConfidenzialita")
    protected String livelloConfidenzialita;
    @XmlElement(name = "RegoleAccesso")
    protected String regoleAccesso;
    @XmlElement(name = "LID")
    protected String lid;
    @XmlElement(name = "DocumentoFirmato")
    protected String documentoFirmato;
    @XmlElement(name = "Descrizione")
    protected List<String> descrizione;
    @XmlElement(name = "RegimeDocumento")
    protected String regimeDocumento;
    @XmlElement(nillable = true)
    protected List<EventCodeListType> eventCodeList;
    @XmlElement(nillable = true)
    protected List<OpzioniType> elencoOpzioniMetadati;

    /**
     * Recupera il valore della proprietÃ  tipologiaStrutturaProdDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologiaStrutturaProdDoc() {
        return tipologiaStrutturaProdDoc;
    }

    /**
     * Imposta il valore della proprietÃ  tipologiaStrutturaProdDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologiaStrutturaProdDoc(String value) {
        this.tipologiaStrutturaProdDoc = value;
    }

    /**
     * Recupera il valore della proprietÃ  identificativoAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoAssistito() {
        return identificativoAssistito;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoAssistito(String value) {
        this.identificativoAssistito = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoMime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMime() {
        return tipoMime;
    }

    /**
     * Imposta il valore della proprietÃ  tipoMime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMime(String value) {
        this.tipoMime = value;
    }

    /**
     * Recupera il valore della proprietÃ  identificativoOrgDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoOrgDoc() {
        return identificativoOrgDoc;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoOrgDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoOrgDoc(String value) {
        this.identificativoOrgDoc = value;
    }

    /**
     * Recupera il valore della proprietÃ  identificativoRepository.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoRepository() {
        return identificativoRepository;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoRepository.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoRepository(String value) {
        this.identificativoRepository = value;
    }

    /**
     * Recupera il valore della proprietÃ  identificativoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoDocumento() {
        return identificativoDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoDocumento(String value) {
        this.identificativoDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  versioneOggettoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersioneOggettoDocumento() {
        return versioneOggettoDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  versioneOggettoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersioneOggettoDocumento(String value) {
        this.versioneOggettoDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipologiaDocumentoAlto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologiaDocumentoAlto() {
        return tipologiaDocumentoAlto;
    }

    /**
     * Imposta il valore della proprietÃ  tipologiaDocumentoAlto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologiaDocumentoAlto(String value) {
        this.tipologiaDocumentoAlto = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipologiaDocumentoMedio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologiaDocumentoMedio() {
        return tipologiaDocumentoMedio;
    }

    /**
     * Imposta il valore della proprietÃ  tipologiaDocumentoMedio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologiaDocumentoMedio(String value) {
        this.tipologiaDocumentoMedio = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipologiaDocumentoBasso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologiaDocumentoBasso() {
        return tipologiaDocumentoBasso;
    }

    /**
     * Imposta il valore della proprietÃ  tipologiaDocumentoBasso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologiaDocumentoBasso(String value) {
        this.tipologiaDocumentoBasso = value;
    }

    /**
     * Recupera il valore della proprietÃ  identificativoUnivocoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoDocumento() {
        return identificativoUnivocoDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoUnivocoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoDocumento(String value) {
        this.identificativoUnivocoDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataValidazioneDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataValidazioneDocumento() {
        return dataValidazioneDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  dataValidazioneDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataValidazioneDocumento(String value) {
        this.dataValidazioneDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  ruoloAutore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuoloAutore() {
        return ruoloAutore;
    }

    /**
     * Imposta il valore della proprietÃ  ruoloAutore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuoloAutore(String value) {
        this.ruoloAutore = value;
    }

    /**
     * Recupera il valore della proprietÃ  istituzioneAutore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIstituzioneAutore() {
        return istituzioneAutore;
    }

    /**
     * Imposta il valore della proprietÃ  istituzioneAutore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIstituzioneAutore(String value) {
        this.istituzioneAutore = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceFiscaleAutore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleAutore() {
        return codiceFiscaleAutore;
    }

    /**
     * Imposta il valore della proprietÃ  codiceFiscaleAutore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleAutore(String value) {
        this.codiceFiscaleAutore = value;
    }

    /**
     * Recupera il valore della proprietÃ  specialitaAutore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialitaAutore() {
        return specialitaAutore;
    }

    /**
     * Imposta il valore della proprietÃ  specialitaAutore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialitaAutore(String value) {
        this.specialitaAutore = value;
    }

    /**
     * Recupera il valore della proprietÃ  telecAutore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelecAutore() {
        return telecAutore;
    }

    /**
     * Imposta il valore della proprietÃ  telecAutore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelecAutore(String value) {
        this.telecAutore = value;
    }

    /**
     * Recupera il valore della proprietÃ  hashDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashDoc() {
        return hashDoc;
    }

    /**
     * Imposta il valore della proprietÃ  hashDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashDoc(String value) {
        this.hashDoc = value;
    }

    /**
     * Recupera il valore della proprietÃ  sizeDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSizeDoc() {
        return sizeDoc;
    }

    /**
     * Imposta il valore della proprietÃ  sizeDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSizeDoc(String value) {
        this.sizeDoc = value;
    }

    /**
     * Recupera il valore della proprietÃ  assettoOrganizzativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssettoOrganizzativo() {
        return assettoOrganizzativo;
    }

    /**
     * Imposta il valore della proprietÃ  assettoOrganizzativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssettoOrganizzativo(String value) {
        this.assettoOrganizzativo = value;
    }

    /**
     * Recupera il valore della proprietÃ  identificativoPaziente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoPaziente() {
        return identificativoPaziente;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoPaziente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoPaziente(String value) {
        this.identificativoPaziente = value;
    }

    /**
     * Recupera il valore della proprietÃ  linguaDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinguaDocumento() {
        return linguaDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  linguaDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinguaDocumento(String value) {
        this.linguaDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataInizioPrestazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInizioPrestazione() {
        return dataInizioPrestazione;
    }

    /**
     * Imposta il valore della proprietÃ  dataInizioPrestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInizioPrestazione(String value) {
        this.dataInizioPrestazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataFinePrestazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFinePrestazione() {
        return dataFinePrestazione;
    }

    /**
     * Imposta il valore della proprietÃ  dataFinePrestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFinePrestazione(String value) {
        this.dataFinePrestazione = value;
    }

    /**
     * Gets the value of the riferimentoDocPrescr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the riferimentoDocPrescr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRiferimentoDocPrescr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRiferimentoDocPrescr() {
        if (riferimentoDocPrescr == null) {
            riferimentoDocPrescr = new ArrayList<String>();
        }
        return this.riferimentoDocPrescr;
    }

    /**
     * Recupera il valore della proprietÃ  conservazioneSost.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConservazioneSost() {
        return conservazioneSost;
    }

    /**
     * Imposta il valore della proprietÃ  conservazioneSost.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConservazioneSost(String value) {
        this.conservazioneSost = value;
    }

    /**
     * Recupera il valore della proprietÃ  livelloConfidenzialita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivelloConfidenzialita() {
        return livelloConfidenzialita;
    }

    /**
     * Imposta il valore della proprietÃ  livelloConfidenzialita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivelloConfidenzialita(String value) {
        this.livelloConfidenzialita = value;
    }

    /**
     * Recupera il valore della proprietÃ  regoleAccesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegoleAccesso() {
        return regoleAccesso;
    }

    /**
     * Imposta il valore della proprietÃ  regoleAccesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegoleAccesso(String value) {
        this.regoleAccesso = value;
    }

    /**
     * Recupera il valore della proprietÃ  lid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLID() {
        return lid;
    }

    /**
     * Imposta il valore della proprietÃ  lid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLID(String value) {
        this.lid = value;
    }

    /**
     * Recupera il valore della proprietÃ  documentoFirmato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoFirmato() {
        return documentoFirmato;
    }

    /**
     * Imposta il valore della proprietÃ  documentoFirmato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoFirmato(String value) {
        this.documentoFirmato = value;
    }

    /**
     * Gets the value of the descrizione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the descrizione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescrizione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDescrizione() {
        if (descrizione == null) {
            descrizione = new ArrayList<String>();
        }
        return this.descrizione;
    }

    /**
     * Recupera il valore della proprietÃ  regimeDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegimeDocumento() {
        return regimeDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  regimeDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegimeDocumento(String value) {
        this.regimeDocumento = value;
    }

    /**
     * Gets the value of the eventCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventCodeListType }
     * 
     * 
     */
    public List<EventCodeListType> getEventCodeList() {
        if (eventCodeList == null) {
            eventCodeList = new ArrayList<EventCodeListType>();
        }
        return this.eventCodeList;
    }

    /**
     * Gets the value of the elencoOpzioniMetadati property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoOpzioniMetadati property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoOpzioniMetadati().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpzioniType }
     * 
     * 
     */
    public List<OpzioniType> getElencoOpzioniMetadati() {
        if (elencoOpzioniMetadati == null) {
            elencoOpzioniMetadati = new ArrayList<OpzioniType>();
        }
        return this.elencoOpzioniMetadati;
    }

}
