/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per setRilevazioniRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="setRilevazioniRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}requestCommon"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaRilevazioni" type="{http://dmaclbluc.dma.csi.it/}listaRilevazioniRequest" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setRilevazioniRequest", propOrder = {
    "listaRilevazioni"
})
public class SetRilevazioniRequest
    extends RequestCommon
{

    protected ListaRilevazioniRequest listaRilevazioni;

    /**
     * Recupera il valore della proprietÃ  listaRilevazioni.
     * 
     * @return
     *     possible object is
     *     {@link ListaRilevazioniRequest }
     *     
     */
    public ListaRilevazioniRequest getListaRilevazioni() {
        return listaRilevazioni;
    }

    /**
     * Imposta il valore della proprietÃ  listaRilevazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaRilevazioniRequest }
     *     
     */
    public void setListaRilevazioni(ListaRilevazioniRequest value) {
        this.listaRilevazioni = value;
    }

}
