/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.recuperadocumentoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per recuperoDocumentoIN complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="recuperoDocumentoIN"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contestoOperativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="criptato" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneOrganizzazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="elencoCFAssistito" type="{http://dma.csi.it/}elencoCFAssistitoType" minOccurs="0"/&gt;
 *         &lt;element name="idEpisodio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idIrec" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="identificativoAssistito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoOrgDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoOrganizzazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoRepository" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pinCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="presaInCarico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ruoloUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="strutturaUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoAttivita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recuperoDocumentoIN", propOrder = {
    "contestoOperativo",
    "criptato",
    "descrizioneOrganizzazione",
    "elencoCFAssistito",
    "idEpisodio",
    "idIrec",
    "identificativoAssistito",
    "identificativoDocumento",
    "identificativoOrgDoc",
    "identificativoOrganizzazione",
    "identificativoRepository",
    "identificativoUtente",
    "pinCode",
    "presaInCarico",
    "ruoloUtente",
    "strutturaUtente",
    "tipoAttivita",
    "tipoDocumento"
})
public class RecuperoDocumentoIN {

    protected String contestoOperativo;
    @XmlSchemaType(name = "string")
    protected SiNo criptato;
    protected String descrizioneOrganizzazione;
    protected ElencoCFAssistitoType elencoCFAssistito;
    protected String idEpisodio;
    protected Long idIrec;
    protected String identificativoAssistito;
    protected String identificativoDocumento;
    protected String identificativoOrgDoc;
    protected String identificativoOrganizzazione;
    protected String identificativoRepository;
    protected String identificativoUtente;
    protected String pinCode;
    protected String presaInCarico;
    protected String ruoloUtente;
    protected String strutturaUtente;
    protected String tipoAttivita;
    protected String tipoDocumento;

    /**
     * Recupera il valore della proprietÃ  contestoOperativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContestoOperativo() {
        return contestoOperativo;
    }

    /**
     * Imposta il valore della proprietÃ  contestoOperativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContestoOperativo(String value) {
        this.contestoOperativo = value;
    }

    /**
     * Recupera il valore della proprietÃ  criptato.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getCriptato() {
        return criptato;
    }

    /**
     * Imposta il valore della proprietÃ  criptato.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setCriptato(SiNo value) {
        this.criptato = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneOrganizzazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneOrganizzazione() {
        return descrizioneOrganizzazione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneOrganizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneOrganizzazione(String value) {
        this.descrizioneOrganizzazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  elencoCFAssistito.
     * 
     * @return
     *     possible object is
     *     {@link ElencoCFAssistitoType }
     *     
     */
    public ElencoCFAssistitoType getElencoCFAssistito() {
        return elencoCFAssistito;
    }

    /**
     * Imposta il valore della proprietÃ  elencoCFAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoCFAssistitoType }
     *     
     */
    public void setElencoCFAssistito(ElencoCFAssistitoType value) {
        this.elencoCFAssistito = value;
    }

    /**
     * Recupera il valore della proprietÃ  idEpisodio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEpisodio() {
        return idEpisodio;
    }

    /**
     * Imposta il valore della proprietÃ  idEpisodio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEpisodio(String value) {
        this.idEpisodio = value;
    }

    /**
     * Recupera il valore della proprietÃ  idIrec.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdIrec() {
        return idIrec;
    }

    /**
     * Imposta il valore della proprietÃ  idIrec.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdIrec(Long value) {
        this.idIrec = value;
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
     * Recupera il valore della proprietÃ  identificativoOrganizzazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoOrganizzazione() {
        return identificativoOrganizzazione;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoOrganizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoOrganizzazione(String value) {
        this.identificativoOrganizzazione = value;
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
     * Recupera il valore della proprietÃ  identificativoUtente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUtente() {
        return identificativoUtente;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUtente(String value) {
        this.identificativoUtente = value;
    }

    /**
     * Recupera il valore della proprietÃ  pinCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Imposta il valore della proprietÃ  pinCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinCode(String value) {
        this.pinCode = value;
    }

    /**
     * Recupera il valore della proprietÃ  presaInCarico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresaInCarico() {
        return presaInCarico;
    }

    /**
     * Imposta il valore della proprietÃ  presaInCarico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresaInCarico(String value) {
        this.presaInCarico = value;
    }

    /**
     * Recupera il valore della proprietÃ  ruoloUtente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuoloUtente() {
        return ruoloUtente;
    }

    /**
     * Imposta il valore della proprietÃ  ruoloUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuoloUtente(String value) {
        this.ruoloUtente = value;
    }

    /**
     * Recupera il valore della proprietÃ  strutturaUtente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrutturaUtente() {
        return strutturaUtente;
    }

    /**
     * Imposta il valore della proprietÃ  strutturaUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrutturaUtente(String value) {
        this.strutturaUtente = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoAttivita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAttivita() {
        return tipoAttivita;
    }

    /**
     * Imposta il valore della proprietÃ  tipoAttivita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAttivita(String value) {
        this.tipoAttivita = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

}
