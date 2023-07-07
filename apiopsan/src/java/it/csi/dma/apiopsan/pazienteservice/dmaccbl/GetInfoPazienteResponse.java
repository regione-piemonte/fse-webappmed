/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.ContattiPaziente;


/**
 * <p>Classe Java per getInfoPazienteResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getInfoPazienteResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaccbl.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}contattiPaziente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getInfoPazienteResponse", propOrder = {
    "contattiPaziente"
})
public class GetInfoPazienteResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "http://dma.csi.it/")
    protected ContattiPaziente contattiPaziente;

    /**
     * Recupera il valore della proprietÃ  contattiPaziente.
     * 
     * @return
     *     possible object is
     *     {@link ContattiPaziente }
     *     
     */
    public ContattiPaziente getContattiPaziente() {
        return contattiPaziente;
    }

    /**
     * Imposta il valore della proprietÃ  contattiPaziente.
     * 
     * @param value
     *     allowed object is
     *     {@link ContattiPaziente }
     *     
     */
    public void setContattiPaziente(ContattiPaziente value) {
        this.contattiPaziente = value;
    }

}
