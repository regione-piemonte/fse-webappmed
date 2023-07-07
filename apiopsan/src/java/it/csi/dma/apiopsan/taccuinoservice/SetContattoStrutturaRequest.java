/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per setContattoStrutturaRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="setContattoStrutturaRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}requestCommon"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contattoStruttura" type="{http://dmaclbluc.dma.csi.it/}contattoStruttura" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setContattoStrutturaRequest", propOrder = {
    "contattoStruttura"
})
public class SetContattoStrutturaRequest
    extends RequestCommon
{

    protected ContattoStruttura contattoStruttura;

    /**
     * Recupera il valore della proprietÃ  contattoStruttura.
     * 
     * @return
     *     possible object is
     *     {@link ContattoStruttura }
     *     
     */
    public ContattoStruttura getContattoStruttura() {
        return contattoStruttura;
    }

    /**
     * Imposta il valore della proprietÃ  contattoStruttura.
     * 
     * @param value
     *     allowed object is
     *     {@link ContattoStruttura }
     *     
     */
    public void setContattoStruttura(ContattoStruttura value) {
        this.contattoStruttura = value;
    }

}
