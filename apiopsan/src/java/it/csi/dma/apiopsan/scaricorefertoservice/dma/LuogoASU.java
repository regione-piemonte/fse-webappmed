/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for luogoASU complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="luogoASU">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="azienda" type="{http://dma.csi.it/}aziendaSanitaria" minOccurs="0"/>
 *         &lt;element name="regione" type="{http://dma.csi.it/}regione" minOccurs="0"/>
 *         &lt;element name="struttura" type="{http://dma.csi.it/}strutturaSanitaria" minOccurs="0"/>
 *         &lt;element name="unitaOperativa" type="{http://dma.csi.it/}unitaOperativaSanitaria" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "luogoASU", propOrder = {
    "azienda",
    "regione",
    "struttura",
    "unitaOperativa"
})
public class LuogoASU {

    protected AziendaSanitaria azienda;
    protected Regione regione;
    protected StrutturaSanitaria struttura;
    protected UnitaOperativaSanitaria unitaOperativa;

    /**
     * Gets the value of the azienda property.
     * 
     * @return
     *     possible object is
     *     {@link AziendaSanitaria }
     *     
     */
    public AziendaSanitaria getAzienda() {
        return azienda;
    }

    /**
     * Sets the value of the azienda property.
     * 
     * @param value
     *     allowed object is
     *     {@link AziendaSanitaria }
     *     
     */
    public void setAzienda(AziendaSanitaria value) {
        this.azienda = value;
    }

    /**
     * Gets the value of the regione property.
     * 
     * @return
     *     possible object is
     *     {@link Regione }
     *     
     */
    public Regione getRegione() {
        return regione;
    }

    /**
     * Sets the value of the regione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Regione }
     *     
     */
    public void setRegione(Regione value) {
        this.regione = value;
    }

    /**
     * Gets the value of the struttura property.
     * 
     * @return
     *     possible object is
     *     {@link StrutturaSanitaria }
     *     
     */
    public StrutturaSanitaria getStruttura() {
        return struttura;
    }

    /**
     * Sets the value of the struttura property.
     * 
     * @param value
     *     allowed object is
     *     {@link StrutturaSanitaria }
     *     
     */
    public void setStruttura(StrutturaSanitaria value) {
        this.struttura = value;
    }

    /**
     * Gets the value of the unitaOperativa property.
     * 
     * @return
     *     possible object is
     *     {@link UnitaOperativaSanitaria }
     *     
     */
    public UnitaOperativaSanitaria getUnitaOperativa() {
        return unitaOperativa;
    }

    /**
     * Sets the value of the unitaOperativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitaOperativaSanitaria }
     *     
     */
    public void setUnitaOperativa(UnitaOperativaSanitaria value) {
        this.unitaOperativa = value;
    }

}
