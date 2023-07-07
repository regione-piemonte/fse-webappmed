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
 * <p>Classe Java per dolore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dolore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="areaInteressata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="intensitaValore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="intensitaDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dolore", propOrder = {
    "descrizione",
    "areaInteressata",
    "intensitaValore",
    "intensitaDescrizione",
    "dataInizio",
    "dataFine"
})
public class Dolore {

    @XmlElement(namespace = "")
    protected String descrizione;
    @XmlElement(namespace = "")
    protected String areaInteressata;
    @XmlElement(namespace = "")
    protected Integer intensitaValore;
    @XmlElement(namespace = "")
    protected String intensitaDescrizione;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizio;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;

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
     * Recupera il valore della proprietÃ  areaInteressata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaInteressata() {
        return areaInteressata;
    }

    /**
     * Imposta il valore della proprietÃ  areaInteressata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaInteressata(String value) {
        this.areaInteressata = value;
    }

    /**
     * Recupera il valore della proprietÃ  intensitaValore.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntensitaValore() {
        return intensitaValore;
    }

    /**
     * Imposta il valore della proprietÃ  intensitaValore.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntensitaValore(Integer value) {
        this.intensitaValore = value;
    }

    /**
     * Recupera il valore della proprietÃ  intensitaDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntensitaDescrizione() {
        return intensitaDescrizione;
    }

    /**
     * Imposta il valore della proprietÃ  intensitaDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntensitaDescrizione(String value) {
        this.intensitaDescrizione = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprietÃ  dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataFine.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprietÃ  dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

}
