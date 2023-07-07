/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.recuperadocumentoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per recuperoDocumentoRequeste complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="recuperoDocumentoRequeste"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="recuperoDocumentoIN" type="{http://dma.csi.it/}recuperoDocumentoIN" minOccurs="0"/&gt;
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recuperoDocumentoRequeste", namespace = "http://dmacc.csi.it/", propOrder = {
    "recuperoDocumentoIN",
    "richiedente"
})
public class RecuperoDocumentoRequeste {

    @XmlElement(namespace = "")
    protected RecuperoDocumentoIN recuperoDocumentoIN;
    @XmlElement(namespace = "")
    protected Richiedente richiedente;

    /**
     * Recupera il valore della proprietÃ  recuperoDocumentoIN.
     * 
     * @return
     *     possible object is
     *     {@link RecuperoDocumentoIN }
     *     
     */
    public RecuperoDocumentoIN getRecuperoDocumentoIN() {
        return recuperoDocumentoIN;
    }

    /**
     * Imposta il valore della proprietÃ  recuperoDocumentoIN.
     * 
     * @param value
     *     allowed object is
     *     {@link RecuperoDocumentoIN }
     *     
     */
    public void setRecuperoDocumentoIN(RecuperoDocumentoIN value) {
        this.recuperoDocumentoIN = value;
    }

    /**
     * Recupera il valore della proprietÃ  richiedente.
     * 
     * @return
     *     possible object is
     *     {@link Richiedente }
     *     
     */
    public Richiedente getRichiedente() {
        return richiedente;
    }

    /**
     * Imposta il valore della proprietÃ  richiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiedente }
     *     
     */
    public void setRichiedente(Richiedente value) {
        this.richiedente = value;
    }

}
