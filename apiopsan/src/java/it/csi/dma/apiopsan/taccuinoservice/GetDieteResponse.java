/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDieteResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDieteResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaDiete" type="{http://dmaclbluc.dma.csi.it/}listaDiete" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDieteResponse", propOrder = {
    "listaDiete"
})
public class GetDieteResponse
    extends ServiceResponse
{

    protected ListaDiete listaDiete;

    /**
     * Recupera il valore della proprietÃ  listaDiete.
     * 
     * @return
     *     possible object is
     *     {@link ListaDiete }
     *     
     */
    public ListaDiete getListaDiete() {
        return listaDiete;
    }

    /**
     * Imposta il valore della proprietÃ  listaDiete.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDiete }
     *     
     */
    public void setListaDiete(ListaDiete value) {
        this.listaDiete = value;
    }

}
