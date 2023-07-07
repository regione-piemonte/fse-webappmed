/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDecodificaContattoTipoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDecodificaContattoTipoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaContattoTipo" type="{http://dmaclbluc.dma.csi.it/}listaContattoTipo" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDecodificaContattoTipoResponse", propOrder = {
    "listaContattoTipo"
})
public class GetDecodificaContattoTipoResponse
    extends ServiceResponse
{

    protected ListaContattoTipo listaContattoTipo;

    /**
     * Recupera il valore della proprietÃ  listaContattoTipo.
     * 
     * @return
     *     possible object is
     *     {@link ListaContattoTipo }
     *     
     */
    public ListaContattoTipo getListaContattoTipo() {
        return listaContattoTipo;
    }

    /**
     * Imposta il valore della proprietÃ  listaContattoTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaContattoTipo }
     *     
     */
    public void setListaContattoTipo(ListaContattoTipo value) {
        this.listaContattoTipo = value;
    }

}
