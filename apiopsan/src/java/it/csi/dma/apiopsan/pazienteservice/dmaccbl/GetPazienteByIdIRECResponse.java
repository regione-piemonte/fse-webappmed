/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.Paziente;


/**
 * <p>Classe Java per getPazienteByIdIRECResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getPazienteByIdIRECResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaccbl.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paziente" type="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPazienteByIdIRECResponse", propOrder = {
    "paziente"
})
public class GetPazienteByIdIRECResponse
    extends ServiceResponse
{

    @XmlElementRef(name = "paziente", type = JAXBElement.class, required = false)
    protected JAXBElement<Paziente> paziente;

    /**
     * Recupera il valore della proprietÃ  paziente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Paziente }{@code >}
     *     
     */
    public JAXBElement<Paziente> getPaziente() {
        return paziente;
    }

    /**
     * Imposta il valore della proprietÃ  paziente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Paziente }{@code >}
     *     
     */
    public void setPaziente(JAXBElement<Paziente> value) {
        this.paziente = value;
    }

}
