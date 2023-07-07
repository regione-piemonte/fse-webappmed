/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per rilevazioneResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="rilevazioneResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}commonInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rilevazione" type="{http://dmaclbluc.dma.csi.it/}rilevazione" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rilevazioneResponse", propOrder = {
    "rilevazione"
})
public class RilevazioneResponse
    extends CommonInfo
{

    protected Rilevazione rilevazione;

    /**
     * Recupera il valore della proprietÃ  rilevazione.
     * 
     * @return
     *     possible object is
     *     {@link Rilevazione }
     *     
     */
    public Rilevazione getRilevazione() {
        return rilevazione;
    }

    /**
     * Imposta il valore della proprietÃ  rilevazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Rilevazione }
     *     
     */
    public void setRilevazione(Rilevazione value) {
        this.rilevazione = value;
    }

}
