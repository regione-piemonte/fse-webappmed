/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for componenteLocaleOperazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="componenteLocaleOperazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://dma.csi.it/}codifica">
 *       &lt;sequence>
 *         &lt;element name="TimeoutMaxElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "componenteLocaleOperazione", propOrder = {
    "timeoutMaxElaborazione"
})
public class ComponenteLocaleOperazione
    extends Codifica
{

    @XmlElement(name = "TimeoutMaxElaborazione")
    protected Long timeoutMaxElaborazione;

    /**
     * Gets the value of the timeoutMaxElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTimeoutMaxElaborazione() {
        return timeoutMaxElaborazione;
    }

    /**
     * Sets the value of the timeoutMaxElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimeoutMaxElaborazione(Long value) {
        this.timeoutMaxElaborazione = value;
    }

}
