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
 * <p>Classe Java per dietaResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dietaResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}commonInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dieta" type="{http://dmaclbluc.dma.csi.it/}dieta" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dietaResponse", propOrder = {
    "dieta"
})
public class DietaResponse
    extends CommonInfo
{

    @XmlElement(namespace = "")
    protected Dieta dieta;

    /**
     * Recupera il valore della proprietÃ  dieta.
     * 
     * @return
     *     possible object is
     *     {@link Dieta }
     *     
     */
    public Dieta getDieta() {
        return dieta;
    }

    /**
     * Imposta il valore della proprietÃ  dieta.
     * 
     * @param value
     *     allowed object is
     *     {@link Dieta }
     *     
     */
    public void setDieta(Dieta value) {
        this.dieta = value;
    }

}
