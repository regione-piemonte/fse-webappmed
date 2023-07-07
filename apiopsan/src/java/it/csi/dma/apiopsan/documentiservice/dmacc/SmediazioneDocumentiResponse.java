/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SmediazioneDocumentiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SmediazioneDocumentiResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmacc.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="elementiSmediazione" type="{http://dmacc.csi.it/}ElementiSmediazione" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmediazioneDocumentiResponse", propOrder = {
    "elementiSmediazione"
})
public class SmediazioneDocumentiResponse
    extends ServiceResponse
{

    protected ElementiSmediazione elementiSmediazione;

    /**
     * Recupera il valore della proprietÃ  elementiSmediazione.
     * 
     * @return
     *     possible object is
     *     {@link ElementiSmediazione }
     *     
     */
    public ElementiSmediazione getElementiSmediazione() {
        return elementiSmediazione;
    }

    /**
     * Imposta il valore della proprietÃ  elementiSmediazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementiSmediazione }
     *     
     */
    public void setElementiSmediazione(ElementiSmediazione value) {
        this.elementiSmediazione = value;
    }

}
