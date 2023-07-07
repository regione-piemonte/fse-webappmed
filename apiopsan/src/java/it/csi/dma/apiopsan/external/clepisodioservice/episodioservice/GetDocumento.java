/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.episodioservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestDc" type="{http://EpisodioService.WS.DMACL.csi.it}GetDocumentoReq" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestDc"
})
@XmlRootElement(name = "GetDocumento")
public class GetDocumento {

    protected GetDocumentoReq requestDc;

    /**
     * Recupera il valore della proprieta' requestDc.
     * 
     * @return
     *     possible object is
     *     {@link GetDocumentoReq }
     *     
     */
    public GetDocumentoReq getRequestDc() {
        return requestDc;
    }

    /**
     * Imposta il valore della proprieta' requestDc.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDocumentoReq }
     *     
     */
    public void setRequestDc(GetDocumentoReq value) {
        this.requestDc = value;
    }

}
