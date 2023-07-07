/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per cancellaEventoRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="cancellaEventoRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}requestCommon"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idEvento" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancellaEventoRequest", propOrder = {
    "idEvento"
})
public class CancellaEventoRequest
    extends RequestCommon
{

    protected long idEvento;

    /**
     * Recupera il valore della proprietÃ  idEvento.
     * 
     */
    public long getIdEvento() {
        return idEvento;
    }

    /**
     * Imposta il valore della proprietÃ  idEvento.
     * 
     */
    public void setIdEvento(long value) {
        this.idEvento = value;
    }

}
