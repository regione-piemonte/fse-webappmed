/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.RiepilogoPaziente;


/**
 * <p>Classe Java per getRiepilogoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getRiepilogoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaccbl.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="riepilogoPaziente" type="{http://dma.csi.it/}riepilogoPaziente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRiepilogoResponse", propOrder = {
    "riepilogoPaziente"
})
public class GetRiepilogoResponse
    extends ServiceResponse
{

    protected RiepilogoPaziente riepilogoPaziente;

    /**
     * Recupera il valore della proprietÃ  riepilogoPaziente.
     * 
     * @return
     *     possible object is
     *     {@link RiepilogoPaziente }
     *     
     */
    public RiepilogoPaziente getRiepilogoPaziente() {
        return riepilogoPaziente;
    }

    /**
     * Imposta il valore della proprietÃ  riepilogoPaziente.
     * 
     * @param value
     *     allowed object is
     *     {@link RiepilogoPaziente }
     *     
     */
    public void setRiepilogoPaziente(RiepilogoPaziente value) {
        this.riepilogoPaziente = value;
    }

}
