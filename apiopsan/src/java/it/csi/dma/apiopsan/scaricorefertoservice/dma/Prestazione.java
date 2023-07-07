/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prestazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prestazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://dma.csi.it/}codifica">
 *       &lt;sequence>
 *         &lt;element name="idPrestazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="branca" type="{http://dma.csi.it/}branca" minOccurs="0"/>
 *         &lt;element name="valoriPrestazione" type="{http://dma.csi.it/}valoriPrestazione" minOccurs="0"/>
 *         &lt;element name="dataOraPrestazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataOra" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prestazione", propOrder = {
    "idPrestazione",
    "branca",
    "valoriPrestazione",
    "dataOraPrestazione",
    "dataOra"
})
public class Prestazione
    extends Codifica
{

    protected Long idPrestazione;
    protected Branca branca;
    protected ValoriPrestazione valoriPrestazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraPrestazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOra;

    /**
     * Gets the value of the idPrestazione property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdPrestazione() {
        return idPrestazione;
    }

    /**
     * Sets the value of the idPrestazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdPrestazione(Long value) {
        this.idPrestazione = value;
    }

    /**
     * Gets the value of the branca property.
     * 
     * @return
     *     possible object is
     *     {@link Branca }
     *     
     */
    public Branca getBranca() {
        return branca;
    }

    /**
     * Sets the value of the branca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Branca }
     *     
     */
    public void setBranca(Branca value) {
        this.branca = value;
    }

    /**
     * Gets the value of the valoriPrestazione property.
     * 
     * @return
     *     possible object is
     *     {@link ValoriPrestazione }
     *     
     */
    public ValoriPrestazione getValoriPrestazione() {
        return valoriPrestazione;
    }

    /**
     * Sets the value of the valoriPrestazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValoriPrestazione }
     *     
     */
    public void setValoriPrestazione(ValoriPrestazione value) {
        this.valoriPrestazione = value;
    }

    /**
     * Gets the value of the dataOraPrestazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraPrestazione() {
        return dataOraPrestazione;
    }

    /**
     * Sets the value of the dataOraPrestazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraPrestazione(XMLGregorianCalendar value) {
        this.dataOraPrestazione = value;
    }

    /**
     * Gets the value of the dataOra property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOra() {
        return dataOra;
    }

    /**
     * Sets the value of the dataOra property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOra(XMLGregorianCalendar value) {
        this.dataOra = value;
    }

}
