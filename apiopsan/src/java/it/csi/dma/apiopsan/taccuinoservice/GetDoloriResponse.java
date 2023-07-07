/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDoloriResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDoloriResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaDolori" type="{http://dmaclbluc.dma.csi.it/}listaDolori" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDoloriResponse", propOrder = {
    "listaDolori"
})
public class GetDoloriResponse
    extends ServiceResponse
{

    protected ListaDolori listaDolori;

    /**
     * Recupera il valore della proprietÃ  listaDolori.
     * 
     * @return
     *     possible object is
     *     {@link ListaDolori }
     *     
     */
    public ListaDolori getListaDolori() {
        return listaDolori;
    }

    /**
     * Imposta il valore della proprietÃ  listaDolori.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDolori }
     *     
     */
    public void setListaDolori(ListaDolori value) {
        this.listaDolori = value;
    }

}
