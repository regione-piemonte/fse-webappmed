/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.screeningservice.dmaccl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for screeningPolipoColon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="screeningPolipoColon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="polipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sede" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dimensione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="polipectomiaBiopsia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recupero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dimIsto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoIsto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displasia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "screeningPolipoColon", propOrder = {
    "polipo",
    "tipo",
    "sede",
    "dimensione",
    "polipectomiaBiopsia",
    "recupero",
    "dimIsto",
    "tipoIsto",
    "displasia"
})
public class ScreeningPolipoColon {

    protected String polipo;
    protected String tipo;
    protected String sede;
    protected String dimensione;
    protected String polipectomiaBiopsia;
    protected String recupero;
    protected String dimIsto;
    protected String tipoIsto;
    protected String displasia;

    /**
     * Gets the value of the polipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolipo() {
        return polipo;
    }

    /**
     * Sets the value of the polipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolipo(String value) {
        this.polipo = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the sede property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSede() {
        return sede;
    }

    /**
     * Sets the value of the sede property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSede(String value) {
        this.sede = value;
    }

    /**
     * Gets the value of the dimensione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensione() {
        return dimensione;
    }

    /**
     * Sets the value of the dimensione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensione(String value) {
        this.dimensione = value;
    }

    /**
     * Gets the value of the polipectomiaBiopsia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolipectomiaBiopsia() {
        return polipectomiaBiopsia;
    }

    /**
     * Sets the value of the polipectomiaBiopsia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolipectomiaBiopsia(String value) {
        this.polipectomiaBiopsia = value;
    }

    /**
     * Gets the value of the recupero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecupero() {
        return recupero;
    }

    /**
     * Sets the value of the recupero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecupero(String value) {
        this.recupero = value;
    }

    /**
     * Gets the value of the dimIsto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimIsto() {
        return dimIsto;
    }

    /**
     * Sets the value of the dimIsto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimIsto(String value) {
        this.dimIsto = value;
    }

    /**
     * Gets the value of the tipoIsto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIsto() {
        return tipoIsto;
    }

    /**
     * Sets the value of the tipoIsto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIsto(String value) {
        this.tipoIsto = value;
    }

    /**
     * Gets the value of the displasia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplasia() {
        return displasia;
    }

    /**
     * Sets the value of the displasia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplasia(String value) {
        this.displasia = value;
    }

}
