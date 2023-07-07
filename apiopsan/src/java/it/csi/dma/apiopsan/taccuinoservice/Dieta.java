/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per dieta complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dieta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="colazioneDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="colazioneCalorie" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="pranzoDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pranzoCalorie" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cenaDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cenaCalorie" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="spuntiniDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="spuntiniCalorie" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dieta", propOrder = {
    "data",
    "colazioneDescrizione",
    "colazioneCalorie",
    "pranzoDescrizione",
    "pranzoCalorie",
    "cenaDescrizione",
    "cenaCalorie",
    "spuntiniDescrizione",
    "spuntiniCalorie"
})
public class Dieta {

    @XmlElement(namespace = "")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    @XmlElement(namespace = "")
    protected String colazioneDescrizione;
    @XmlElement(namespace = "")
    protected Integer colazioneCalorie;
    @XmlElement(namespace = "")
    protected String pranzoDescrizione;
    @XmlElement(namespace = "")
    protected Integer pranzoCalorie;
    @XmlElement(namespace = "")
    protected String cenaDescrizione;
    @XmlElement(namespace = "")
    protected Integer cenaCalorie;
    @XmlElement(namespace = "")
    protected String spuntiniDescrizione;
    @XmlElement(namespace = "")
    protected Integer spuntiniCalorie;

    /**
     * Recupera il valore della proprietÃ  data.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietÃ  data.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietÃ  colazioneDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColazioneDescrizione() {
        return colazioneDescrizione;
    }

    /**
     * Imposta il valore della proprietÃ  colazioneDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColazioneDescrizione(String value) {
        this.colazioneDescrizione = value;
    }

    /**
     * Recupera il valore della proprietÃ  colazioneCalorie.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getColazioneCalorie() {
        return colazioneCalorie;
    }

    /**
     * Imposta il valore della proprietÃ  colazioneCalorie.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setColazioneCalorie(Integer value) {
        this.colazioneCalorie = value;
    }

    /**
     * Recupera il valore della proprietÃ  pranzoDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPranzoDescrizione() {
        return pranzoDescrizione;
    }

    /**
     * Imposta il valore della proprietÃ  pranzoDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPranzoDescrizione(String value) {
        this.pranzoDescrizione = value;
    }

    /**
     * Recupera il valore della proprietÃ  pranzoCalorie.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPranzoCalorie() {
        return pranzoCalorie;
    }

    /**
     * Imposta il valore della proprietÃ  pranzoCalorie.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPranzoCalorie(Integer value) {
        this.pranzoCalorie = value;
    }

    /**
     * Recupera il valore della proprietÃ  cenaDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCenaDescrizione() {
        return cenaDescrizione;
    }

    /**
     * Imposta il valore della proprietÃ  cenaDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCenaDescrizione(String value) {
        this.cenaDescrizione = value;
    }

    /**
     * Recupera il valore della proprietÃ  cenaCalorie.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCenaCalorie() {
        return cenaCalorie;
    }

    /**
     * Imposta il valore della proprietÃ  cenaCalorie.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCenaCalorie(Integer value) {
        this.cenaCalorie = value;
    }

    /**
     * Recupera il valore della proprietÃ  spuntiniDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpuntiniDescrizione() {
        return spuntiniDescrizione;
    }

    /**
     * Imposta il valore della proprietÃ  spuntiniDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpuntiniDescrizione(String value) {
        this.spuntiniDescrizione = value;
    }

    /**
     * Recupera il valore della proprietÃ  spuntiniCalorie.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpuntiniCalorie() {
        return spuntiniCalorie;
    }

    /**
     * Imposta il valore della proprietÃ  spuntiniCalorie.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpuntiniCalorie(Integer value) {
        this.spuntiniCalorie = value;
    }

}
