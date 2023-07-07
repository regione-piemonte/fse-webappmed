/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.screeningservice.dmaccl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pazienteScreening complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pazienteScreening">
 *   &lt;complexContent>
 *     &lt;extension base="{http://dma.csi.it/}paziente">
 *       &lt;sequence>
 *         &lt;element name="idScreening" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pazienteScreening", propOrder = {
    "idScreening"
})
public class PazienteScreening
    extends Paziente
{

    protected String idScreening;

    /**
     * Gets the value of the idScreening property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdScreening() {
        return idScreening;
    }

    /**
     * Sets the value of the idScreening property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdScreening(String value) {
        this.idScreening = value;
    }

}
