/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per setRilevazioniResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="setRilevazioniResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaRilevazioni" type="{http://dmaclbluc.dma.csi.it/}listaRilevazioni" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setRilevazioniResponse", propOrder = {
    "listaRilevazioni"
})
public class SetRilevazioniResponse
    extends ServiceResponse
{

    protected ListaRilevazioni listaRilevazioni;

    /**
     * Recupera il valore della proprietÃ  listaRilevazioni.
     * 
     * @return
     *     possible object is
     *     {@link ListaRilevazioni }
     *     
     */
    public ListaRilevazioni getListaRilevazioni() {
        return listaRilevazioni;
    }

    /**
     * Imposta il valore della proprietÃ  listaRilevazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaRilevazioni }
     *     
     */
    public void setListaRilevazioni(ListaRilevazioni value) {
        this.listaRilevazioni = value;
    }

}
