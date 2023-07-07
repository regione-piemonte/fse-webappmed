/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per cancellaDietaRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="cancellaDietaRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}requestCommon"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identificativoDieta" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancellaDietaRequest", propOrder = {
    "identificativoDieta"
})
public class CancellaDietaRequest
    extends RequestCommon
{

    protected long identificativoDieta;

    /**
     * Recupera il valore della proprietÃ  identificativoDieta.
     * 
     */
    public long getIdentificativoDieta() {
        return identificativoDieta;
    }

    /**
     * Imposta il valore della proprietÃ  identificativoDieta.
     * 
     */
    public void setIdentificativoDieta(long value) {
        this.identificativoDieta = value;
    }

}
