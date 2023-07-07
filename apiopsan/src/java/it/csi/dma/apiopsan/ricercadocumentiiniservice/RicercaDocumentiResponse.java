/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.ricercadocumentiiniservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaDocumentiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaDocumentiResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmacc.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaDocumentiOUT" type="{http://dma.csi.it/}ricercaDocumentiOUT" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDocumentiResponse", namespace = "http://dmacc.csi.it/", propOrder = {
    "ricercaDocumentiOUT"
})
public class RicercaDocumentiResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected RicercaDocumentiOUT ricercaDocumentiOUT;

    /**
     * Recupera il valore della proprietÃ  ricercaDocumentiOUT.
     * 
     * @return
     *     possible object is
     *     {@link RicercaDocumentiOUT }
     *     
     */
    public RicercaDocumentiOUT getRicercaDocumentiOUT() {
        return ricercaDocumentiOUT;
    }

    /**
     * Imposta il valore della proprietÃ  ricercaDocumentiOUT.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaDocumentiOUT }
     *     
     */
    public void setRicercaDocumentiOUT(RicercaDocumentiOUT value) {
        this.ricercaDocumentiOUT = value;
    }

}
