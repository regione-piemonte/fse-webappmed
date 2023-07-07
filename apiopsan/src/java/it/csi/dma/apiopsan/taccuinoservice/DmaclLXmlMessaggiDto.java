/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dmaclLXmlMessaggiDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dmaclLXmlMessaggiDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dataInserimento" type="{http://dmaclbluc.dma.csi.it/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="wso2Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xmlIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xmlInServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xmlOut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xmlOutServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dmaclLXmlMessaggiDto", propOrder = {
    "dataInserimento",
    "id",
    "wso2Id",
    "xmlIn",
    "xmlInServizio",
    "xmlOut",
    "xmlOutServizio"
})
public class DmaclLXmlMessaggiDto {

    @XmlElement(namespace = "")
    protected Timestamp dataInserimento;
    @XmlElement(namespace = "")
    protected Long id;
    @XmlElement(namespace = "")
    protected String wso2Id;
    @XmlElement(namespace = "")
    protected String xmlIn;
    @XmlElement(namespace = "")
    protected String xmlInServizio;
    @XmlElement(namespace = "")
    protected String xmlOut;
    @XmlElement(namespace = "")
    protected String xmlOutServizio;

    /**
     * Recupera il valore della proprietÃ  dataInserimento.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getDataInserimento() {
        return dataInserimento;
    }

    /**
     * Imposta il valore della proprietÃ  dataInserimento.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setDataInserimento(Timestamp value) {
        this.dataInserimento = value;
    }

    /**
     * Recupera il valore della proprietÃ  id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietÃ  id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietÃ  wso2Id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWso2Id() {
        return wso2Id;
    }

    /**
     * Imposta il valore della proprietÃ  wso2Id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWso2Id(String value) {
        this.wso2Id = value;
    }

    /**
     * Recupera il valore della proprietÃ  xmlIn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlIn() {
        return xmlIn;
    }

    /**
     * Imposta il valore della proprietÃ  xmlIn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlIn(String value) {
        this.xmlIn = value;
    }

    /**
     * Recupera il valore della proprietÃ  xmlInServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlInServizio() {
        return xmlInServizio;
    }

    /**
     * Imposta il valore della proprietÃ  xmlInServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlInServizio(String value) {
        this.xmlInServizio = value;
    }

    /**
     * Recupera il valore della proprietÃ  xmlOut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlOut() {
        return xmlOut;
    }

    /**
     * Imposta il valore della proprietÃ  xmlOut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlOut(String value) {
        this.xmlOut = value;
    }

    /**
     * Recupera il valore della proprietÃ  xmlOutServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlOutServizio() {
        return xmlOutServizio;
    }

    /**
     * Imposta il valore della proprietÃ  xmlOutServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlOutServizio(String value) {
        this.xmlOutServizio = value;
    }

}
