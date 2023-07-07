/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.screeningservice.dmaccl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for screeningPolipoClisma complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="screeningPolipoClisma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="polipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sede" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dimensione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lesioneSospettaCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "screeningPolipoClisma", propOrder = {
    "polipo",
    "sede",
    "dimensione",
    "lesioneSospettaCA"
})
public class ScreeningPolipoClisma {

    protected String polipo;
    protected String sede;
    protected String dimensione;
    protected String lesioneSospettaCA;

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
     * Gets the value of the lesioneSospettaCA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLesioneSospettaCA() {
        return lesioneSospettaCA;
    }

    /**
     * Sets the value of the lesioneSospettaCA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLesioneSospettaCA(String value) {
        this.lesioneSospettaCA = value;
    }

}
