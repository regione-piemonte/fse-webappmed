/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDecodificaIntensitaDoloreResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDecodificaIntensitaDoloreResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaIntensitaDolore" type="{http://dmaclbluc.dma.csi.it/}listaIntensitaDolore" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDecodificaIntensitaDoloreResponse", propOrder = {
    "listaIntensitaDolore"
})
public class GetDecodificaIntensitaDoloreResponse
    extends ServiceResponse
{

    protected ListaIntensitaDolore listaIntensitaDolore;

    /**
     * Recupera il valore della proprietÃ  listaIntensitaDolore.
     * 
     * @return
     *     possible object is
     *     {@link ListaIntensitaDolore }
     *     
     */
    public ListaIntensitaDolore getListaIntensitaDolore() {
        return listaIntensitaDolore;
    }

    /**
     * Imposta il valore della proprietÃ  listaIntensitaDolore.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaIntensitaDolore }
     *     
     */
    public void setListaIntensitaDolore(ListaIntensitaDolore value) {
        this.listaIntensitaDolore = value;
    }

}
