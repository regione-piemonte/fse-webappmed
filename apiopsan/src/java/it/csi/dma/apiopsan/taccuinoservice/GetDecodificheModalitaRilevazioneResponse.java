/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDecodificheModalitaRilevazioneResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDecodificheModalitaRilevazioneResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaModalitaRilevazione" type="{http://dmaclbluc.dma.csi.it/}listaModalitaRilevazione" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDecodificheModalitaRilevazioneResponse", propOrder = {
    "listaModalitaRilevazione"
})
public class GetDecodificheModalitaRilevazioneResponse
    extends ServiceResponse
{

    protected ListaModalitaRilevazione listaModalitaRilevazione;

    /**
     * Recupera il valore della proprietÃ  listaModalitaRilevazione.
     * 
     * @return
     *     possible object is
     *     {@link ListaModalitaRilevazione }
     *     
     */
    public ListaModalitaRilevazione getListaModalitaRilevazione() {
        return listaModalitaRilevazione;
    }

    /**
     * Imposta il valore della proprietÃ  listaModalitaRilevazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaModalitaRilevazione }
     *     
     */
    public void setListaModalitaRilevazione(ListaModalitaRilevazione value) {
        this.listaModalitaRilevazione = value;
    }

}
