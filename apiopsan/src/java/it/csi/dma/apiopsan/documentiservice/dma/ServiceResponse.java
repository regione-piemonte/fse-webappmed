/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dmacc.Errore;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetConteggiResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDettaglioDocumentoResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiOSCResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiCorrelatiResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentiEpisodioResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetDocumentoResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetSoloEpisodiResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.GetTuttiDocsResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.RegistraAvvenutoRitiroResponse;
import it.csi.dma.apiopsan.documentiservice.dmacc.RisultatoCodice;
import it.csi.dma.apiopsan.documentiservice.dmacc.VerificaDocumentoScaricabileResponse;


/**
 * <p>Classe Java per serviceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="serviceResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errori" type="{http://dmacc.csi.it/}errore" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="esito" type="{http://dmacc.csi.it/}risultatoCodice" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceResponse", propOrder = {
    "errori",
    "esito"
})
@XmlSeeAlso({
    GetConteggiResponse.class,
    GetDettaglioDocumentoResponse.class,
    GetDocumentiEpisodioResponse.class,
    GetSoloEpisodiResponse.class,
    GetDocumentiCorrelatiOSCResponse.class,
    GetDocumentiCorrelatiResponse.class,
    GetDocumentoResponse.class,
    GetTuttiDocsResponse.class,
    RegistraAvvenutoRitiroResponse.class,
    VerificaDocumentoScaricabileResponse.class
})
public class ServiceResponse {

    protected List<Errore> errori;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "string")
    protected RisultatoCodice esito;

    /**
     * Gets the value of the errori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Errore }
     * 
     * 
     */
    public List<Errore> getErrori() {
        if (errori == null) {
            errori = new ArrayList<Errore>();
        }
        return this.errori;
    }

    /**
     * Recupera il valore della proprietÃ  esito.
     * 
     * @return
     *     possible object is
     *     {@link RisultatoCodice }
     *     
     */
    public RisultatoCodice getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietÃ  esito.
     * 
     * @param value
     *     allowed object is
     *     {@link RisultatoCodice }
     *     
     */
    public void setEsito(RisultatoCodice value) {
        this.esito = value;
    }

}
