/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.episodioservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetDocumentoResult" type="{http://EpisodioService.WS.DMACL.csi.it}GetDocumentoRes"/&gt;
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
    "getDocumentoResult"
})
@XmlRootElement(name = "GetDocumentoResponse")
public class GetDocumentoResponse {

    @XmlElement(name = "GetDocumentoResult", required = true)
    protected GetDocumentoRes getDocumentoResult;

    /**
     * Recupera il valore della proprieta' getDocumentoResult.
     * 
     * @return
     *     possible object is
     *     {@link GetDocumentoRes }
     *     
     */
    public GetDocumentoRes getGetDocumentoResult() {
        return getDocumentoResult;
    }

    /**
     * Imposta il valore della proprieta' getDocumentoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDocumentoRes }
     *     
     */
    public void setGetDocumentoResult(GetDocumentoRes value) {
        this.getDocumentoResult = value;
    }

}
