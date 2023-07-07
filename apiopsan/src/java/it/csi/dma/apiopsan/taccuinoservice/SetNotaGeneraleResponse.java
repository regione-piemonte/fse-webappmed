/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per setNotaGeneraleResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="setNotaGeneraleResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notaGenerale" type="{http://dmaclbluc.dma.csi.it/}notaGenerale" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setNotaGeneraleResponse", propOrder = {
    "notaGenerale"
})
public class SetNotaGeneraleResponse
    extends ServiceResponse
{

    protected NotaGenerale notaGenerale;

    /**
     * Recupera il valore della proprietÃ  notaGenerale.
     * 
     * @return
     *     possible object is
     *     {@link NotaGenerale }
     *     
     */
    public NotaGenerale getNotaGenerale() {
        return notaGenerale;
    }

    /**
     * Imposta il valore della proprietÃ  notaGenerale.
     * 
     * @param value
     *     allowed object is
     *     {@link NotaGenerale }
     *     
     */
    public void setNotaGenerale(NotaGenerale value) {
        this.notaGenerale = value;
    }

}
