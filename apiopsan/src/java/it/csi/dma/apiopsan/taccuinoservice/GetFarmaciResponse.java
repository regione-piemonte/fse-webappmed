/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getFarmaciResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getFarmaciResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaAssunzioneFarmaci" type="{http://dmaclbluc.dma.csi.it/}listaAssunzioneFarmaci" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFarmaciResponse", propOrder = {
    "listaAssunzioneFarmaci"
})
public class GetFarmaciResponse
    extends ServiceResponse
{

    protected ListaAssunzioneFarmaci listaAssunzioneFarmaci;

    /**
     * Recupera il valore della proprietÃ  listaAssunzioneFarmaci.
     * 
     * @return
     *     possible object is
     *     {@link ListaAssunzioneFarmaci }
     *     
     */
    public ListaAssunzioneFarmaci getListaAssunzioneFarmaci() {
        return listaAssunzioneFarmaci;
    }

    /**
     * Imposta il valore della proprietÃ  listaAssunzioneFarmaci.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaAssunzioneFarmaci }
     *     
     */
    public void setListaAssunzioneFarmaci(ListaAssunzioneFarmaci value) {
        this.listaAssunzioneFarmaci = value;
    }

}
