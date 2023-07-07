/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per infoAddendumReferto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="infoAddendumReferto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}codiceDipartimentaleDocumento" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}id" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoAddendumReferto", propOrder = {
    "codiceDipartimentaleDocumento",
    "id"
})
public class InfoAddendumReferto {

    @XmlElement(namespace = "http://dma.csi.it/")
    protected String codiceDipartimentaleDocumento;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected String id;

    /**
     * Recupera il valore della proprietÃ  codiceDipartimentaleDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceDipartimentaleDocumento() {
        return codiceDipartimentaleDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  codiceDipartimentaleDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceDipartimentaleDocumento(String value) {
        this.codiceDipartimentaleDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietÃ  id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
