/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per sintomoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="sintomoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}commonInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sintomo" type="{http://dmaclbluc.dma.csi.it/}sintomo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sintomoResponse", propOrder = {
    "sintomo"
})
public class SintomoResponse
    extends CommonInfo
{

    @XmlElement(namespace = "")
    protected Sintomo sintomo;

    /**
     * Recupera il valore della proprietÃ  sintomo.
     * 
     * @return
     *     possible object is
     *     {@link Sintomo }
     *     
     */
    public Sintomo getSintomo() {
        return sintomo;
    }

    /**
     * Imposta il valore della proprietÃ  sintomo.
     * 
     * @param value
     *     allowed object is
     *     {@link Sintomo }
     *     
     */
    public void setSintomo(Sintomo value) {
        this.sintomo = value;
    }

}
