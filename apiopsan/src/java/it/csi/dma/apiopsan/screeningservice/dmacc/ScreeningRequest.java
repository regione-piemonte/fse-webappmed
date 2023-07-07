/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.screeningservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.dma.apiopsan.screeningservice.dmaccl.PazienteSOL;
import it.csi.dma.apiopsan.screeningservice.dmaccl.Richiedente;



/**
 * <p>Java class for screeningRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="screeningRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paziente" type="{http://dma.csi.it/}pazienteSOL" minOccurs="0"/>
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedente" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "screeningRequest", propOrder = {
    "paziente",
    "richiedente"
})
public class ScreeningRequest {

    protected PazienteSOL paziente;
    protected Richiedente richiedente;

    /**
     * Gets the value of the paziente property.
     * 
     * @return
     *     possible object is
     *     {@link PazienteSOL }
     *     
     */
    public PazienteSOL getPaziente() {
        return paziente;
    }

    /**
     * Sets the value of the paziente property.
     * 
     * @param value
     *     allowed object is
     *     {@link PazienteSOL }
     *     
     */
    public void setPaziente(PazienteSOL value) {
        this.paziente = value;
    }

    /**
     * Gets the value of the richiedente property.
     * 
     * @return
     *     possible object is
     *     {@link Richiedente }
     *     
     */
    public Richiedente getRichiedente() {
        return richiedente;
    }

    /**
     * Sets the value of the richiedente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiedente }
     *     
     */
    public void setRichiedente(Richiedente value) {
        this.richiedente = value;
    }

}
