/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per setImpostazioniTaccuinoRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="setImpostazioniTaccuinoRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}requestCommon"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaPreferenze" type="{http://dmaclbluc.dma.csi.it/}listaPreferenzeRequest" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setImpostazioniTaccuinoRequest", propOrder = {
    "listaPreferenze"
})
public class SetImpostazioniTaccuinoRequest
    extends RequestCommon
{

    protected ListaPreferenzeRequest listaPreferenze;

    /**
     * Recupera il valore della proprietÃ  listaPreferenze.
     * 
     * @return
     *     possible object is
     *     {@link ListaPreferenzeRequest }
     *     
     */
    public ListaPreferenzeRequest getListaPreferenze() {
        return listaPreferenze;
    }

    /**
     * Imposta il valore della proprietÃ  listaPreferenze.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPreferenzeRequest }
     *     
     */
    public void setListaPreferenze(ListaPreferenzeRequest value) {
        this.listaPreferenze = value;
    }

}
