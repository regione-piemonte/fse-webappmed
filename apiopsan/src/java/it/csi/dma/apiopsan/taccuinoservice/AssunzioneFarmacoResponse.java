/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per assunzioneFarmacoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="assunzioneFarmacoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}commonInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="assunzioneFarmaco" type="{http://dmaclbluc.dma.csi.it/}assunzioneFarmaco" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assunzioneFarmacoResponse", propOrder = {
    "assunzioneFarmaco"
})
public class AssunzioneFarmacoResponse
    extends CommonInfo
{

    protected AssunzioneFarmaco assunzioneFarmaco;

    /**
     * Recupera il valore della proprietÃ  assunzioneFarmaco.
     * 
     * @return
     *     possible object is
     *     {@link AssunzioneFarmaco }
     *     
     */
    public AssunzioneFarmaco getAssunzioneFarmaco() {
        return assunzioneFarmaco;
    }

    /**
     * Imposta il valore della proprietÃ  assunzioneFarmaco.
     * 
     * @param value
     *     allowed object is
     *     {@link AssunzioneFarmaco }
     *     
     */
    public void setAssunzioneFarmaco(AssunzioneFarmaco value) {
        this.assunzioneFarmaco = value;
    }

}
