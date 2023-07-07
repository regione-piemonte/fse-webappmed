/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per descrittoreMisurazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="descrittoreMisurazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idDescrittoreMisurazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idGruppoMisurazioni" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneBreve" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="unitaMisura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="valoreMax" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="valoreMin" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="valoreNormale" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="formattazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="valoreNumerico" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="posizione" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "descrittoreMisurazione", propOrder = {
    "idDescrittoreMisurazione",
    "idGruppoMisurazioni",
    "descrizione",
    "descrizioneBreve",
    "unitaMisura",
    "valoreMax",
    "valoreMin",
    "valoreNormale",
    "formattazione",
    "valoreNumerico",
    "posizione"
})
public class DescrittoreMisurazione {

    protected Long idDescrittoreMisurazione;
    protected Long idGruppoMisurazioni;
    protected String descrizione;
    protected String descrizioneBreve;
    protected String unitaMisura;
    protected Float valoreMax;
    protected Float valoreMin;
    protected Float valoreNormale;
    protected String formattazione;
    protected boolean valoreNumerico;
    protected int posizione;

    /**
     * Recupera il valore della proprietÃ  idDescrittoreMisurazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDescrittoreMisurazione() {
        return idDescrittoreMisurazione;
    }

    /**
     * Imposta il valore della proprietÃ  idDescrittoreMisurazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDescrittoreMisurazione(Long value) {
        this.idDescrittoreMisurazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  idGruppoMisurazioni.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdGruppoMisurazioni() {
        return idGruppoMisurazioni;
    }

    /**
     * Imposta il valore della proprietÃ  idGruppoMisurazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdGruppoMisurazioni(Long value) {
        this.idGruppoMisurazioni = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneBreve.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneBreve() {
        return descrizioneBreve;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneBreve.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneBreve(String value) {
        this.descrizioneBreve = value;
    }

    /**
     * Recupera il valore della proprietÃ  unitaMisura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitaMisura() {
        return unitaMisura;
    }

    /**
     * Imposta il valore della proprietÃ  unitaMisura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitaMisura(String value) {
        this.unitaMisura = value;
    }

    /**
     * Recupera il valore della proprietÃ  valoreMax.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getValoreMax() {
        return valoreMax;
    }

    /**
     * Imposta il valore della proprietÃ  valoreMax.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setValoreMax(Float value) {
        this.valoreMax = value;
    }

    /**
     * Recupera il valore della proprietÃ  valoreMin.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getValoreMin() {
        return valoreMin;
    }

    /**
     * Imposta il valore della proprietÃ  valoreMin.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setValoreMin(Float value) {
        this.valoreMin = value;
    }

    /**
     * Recupera il valore della proprietÃ  valoreNormale.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getValoreNormale() {
        return valoreNormale;
    }

    /**
     * Imposta il valore della proprietÃ  valoreNormale.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setValoreNormale(Float value) {
        this.valoreNormale = value;
    }

    /**
     * Recupera il valore della proprietÃ  formattazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormattazione() {
        return formattazione;
    }

    /**
     * Imposta il valore della proprietÃ  formattazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormattazione(String value) {
        this.formattazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  valoreNumerico.
     * 
     */
    public boolean isValoreNumerico() {
        return valoreNumerico;
    }

    /**
     * Imposta il valore della proprietÃ  valoreNumerico.
     * 
     */
    public void setValoreNumerico(boolean value) {
        this.valoreNumerico = value;
    }

    /**
     * Recupera il valore della proprietÃ  posizione.
     * 
     */
    public int getPosizione() {
        return posizione;
    }

    /**
     * Imposta il valore della proprietÃ  posizione.
     * 
     */
    public void setPosizione(int value) {
        this.posizione = value;
    }

}
