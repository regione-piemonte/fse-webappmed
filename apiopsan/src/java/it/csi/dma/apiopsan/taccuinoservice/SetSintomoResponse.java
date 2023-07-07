/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per setSintomoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="setSintomoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sintomo" type="{http://dmaclbluc.dma.csi.it/}sintomoResponse" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setSintomoResponse", propOrder = {
    "sintomo"
})
public class SetSintomoResponse
    extends ServiceResponse
{

    protected SintomoResponse sintomo;

    /**
     * Recupera il valore della proprietÃ  sintomo.
     * 
     * @return
     *     possible object is
     *     {@link SintomoResponse }
     *     
     */
    public SintomoResponse getSintomo() {
        return sintomo;
    }

    /**
     * Imposta il valore della proprietÃ  sintomo.
     * 
     * @param value
     *     allowed object is
     *     {@link SintomoResponse }
     *     
     */
    public void setSintomo(SintomoResponse value) {
        this.sintomo = value;
    }

}
