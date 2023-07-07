/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eventoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eventoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}commonInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="evento" type="{http://dmaclbluc.dma.csi.it/}evento" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventoResponse", propOrder = {
    "evento"
})
public class EventoResponse
    extends CommonInfo
{

    protected Evento evento;

    /**
     * Recupera il valore della proprietÃ  evento.
     * 
     * @return
     *     possible object is
     *     {@link Evento }
     *     
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Imposta il valore della proprietÃ  evento.
     * 
     * @param value
     *     allowed object is
     *     {@link Evento }
     *     
     */
    public void setEvento(Evento value) {
        this.evento = value;
    }

}
