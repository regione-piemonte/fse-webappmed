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
 *         &lt;element name="GetEpisodiResult" type="{http://EpisodioService.WS.DMACL.csi.it}GetEpisodiRes"/&gt;
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
    "getEpisodiResult"
})
@XmlRootElement(name = "GetEpisodiResponse")
public class GetEpisodiResponse {

    @XmlElement(name = "GetEpisodiResult", required = true)
    protected GetEpisodiRes getEpisodiResult;

    /**
     * Recupera il valore della proprieta' getEpisodiResult.
     * 
     * @return
     *     possible object is
     *     {@link GetEpisodiRes }
     *     
     */
    public GetEpisodiRes getGetEpisodiResult() {
        return getEpisodiResult;
    }

    /**
     * Imposta il valore della proprieta' getEpisodiResult.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEpisodiRes }
     *     
     */
    public void setGetEpisodiResult(GetEpisodiRes value) {
        this.getEpisodiResult = value;
    }

}
