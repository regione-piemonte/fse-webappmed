/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getContattiStruttureResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getContattiStruttureResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaContattiStrutture" type="{http://dmaclbluc.dma.csi.it/}listaContattiStrutture" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getContattiStruttureResponse", propOrder = {
    "listaContattiStrutture"
})
public class GetContattiStruttureResponse
    extends ServiceResponse
{

    protected ListaContattiStrutture listaContattiStrutture;

    /**
     * Recupera il valore della proprietÃ  listaContattiStrutture.
     * 
     * @return
     *     possible object is
     *     {@link ListaContattiStrutture }
     *     
     */
    public ListaContattiStrutture getListaContattiStrutture() {
        return listaContattiStrutture;
    }

    /**
     * Imposta il valore della proprietÃ  listaContattiStrutture.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaContattiStrutture }
     *     
     */
    public void setListaContattiStrutture(ListaContattiStrutture value) {
        this.listaContattiStrutture = value;
    }

}
