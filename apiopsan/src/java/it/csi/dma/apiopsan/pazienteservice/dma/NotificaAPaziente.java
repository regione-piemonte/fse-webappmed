/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per notificaAPaziente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="notificaAPaziente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="canaleNotifica" type="{http://dma.csi.it/}canaleNotifica" minOccurs="0"/&gt;
 *         &lt;element name="eventoNotifica" type="{http://dma.csi.it/}eventoNotifica" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificaAPaziente", propOrder = {
    "canaleNotifica",
    "eventoNotifica"
})
public class NotificaAPaziente {

    protected CanaleNotifica canaleNotifica;
    protected EventoNotifica eventoNotifica;

    /**
     * Recupera il valore della proprietÃ  canaleNotifica.
     * 
     * @return
     *     possible object is
     *     {@link CanaleNotifica }
     *     
     */
    public CanaleNotifica getCanaleNotifica() {
        return canaleNotifica;
    }

    /**
     * Imposta il valore della proprietÃ  canaleNotifica.
     * 
     * @param value
     *     allowed object is
     *     {@link CanaleNotifica }
     *     
     */
    public void setCanaleNotifica(CanaleNotifica value) {
        this.canaleNotifica = value;
    }

    /**
     * Recupera il valore della proprietÃ  eventoNotifica.
     * 
     * @return
     *     possible object is
     *     {@link EventoNotifica }
     *     
     */
    public EventoNotifica getEventoNotifica() {
        return eventoNotifica;
    }

    /**
     * Imposta il valore della proprietÃ  eventoNotifica.
     * 
     * @param value
     *     allowed object is
     *     {@link EventoNotifica }
     *     
     */
    public void setEventoNotifica(EventoNotifica value) {
        this.eventoNotifica = value;
    }

}
