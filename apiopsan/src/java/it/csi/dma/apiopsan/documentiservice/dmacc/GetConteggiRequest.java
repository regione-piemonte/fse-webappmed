/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.RichiedenteInfo;


/**
 * <p>Classe Java per GetConteggiRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetConteggiRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedenteInfo" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="cfPaziente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="categoriaTipologia" type="{http://dmacc.csi.it/}categoriaTipologia" maxOccurs="unbounded" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="tipoConteggio" type="{http://dmacc.csi.it/}tipoConteggio" maxOccurs="unbounded" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetConteggiRequest", propOrder = {
    "richiedente",
    "cfPaziente",
    "categoriaTipologia",
    "tipoConteggio"
})
public class GetConteggiRequest {

    protected RichiedenteInfo richiedente;
    protected String cfPaziente;
    @XmlElement(nillable = true)
    protected List<CategoriaTipologia> categoriaTipologia;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "string")
    protected List<TipoConteggio> tipoConteggio;

    /**
     * Recupera il valore della proprietÃ  richiedente.
     * 
     * @return
     *     possible object is
     *     {@link RichiedenteInfo }
     *     
     */
    public RichiedenteInfo getRichiedente() {
        return richiedente;
    }

    /**
     * Imposta il valore della proprietÃ  richiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link RichiedenteInfo }
     *     
     */
    public void setRichiedente(RichiedenteInfo value) {
        this.richiedente = value;
    }

    /**
     * Recupera il valore della proprietÃ  cfPaziente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfPaziente() {
        return cfPaziente;
    }

    /**
     * Imposta il valore della proprietÃ  cfPaziente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfPaziente(String value) {
        this.cfPaziente = value;
    }

    /**
     * Gets the value of the categoriaTipologia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoriaTipologia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoriaTipologia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaTipologia }
     * 
     * 
     */
    public List<CategoriaTipologia> getCategoriaTipologia() {
        if (categoriaTipologia == null) {
            categoriaTipologia = new ArrayList<CategoriaTipologia>();
        }
        return this.categoriaTipologia;
    }

    /**
     * Gets the value of the tipoConteggio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipoConteggio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoConteggio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoConteggio }
     * 
     * 
     */
    public List<TipoConteggio> getTipoConteggio() {
        if (tipoConteggio == null) {
            tipoConteggio = new ArrayList<TipoConteggio>();
        }
        return this.tipoConteggio;
    }

}
