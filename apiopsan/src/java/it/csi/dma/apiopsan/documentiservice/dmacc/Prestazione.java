/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per prestazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="prestazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idPrestazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="codicePrestazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="descrizionePrestazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dataOra" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="branca" type="{http://dmacc.csi.it/}branca" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="quantita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prestazione", propOrder = {
    "idPrestazione",
    "codicePrestazione",
    "descrizionePrestazione",
    "dataOra",
    "branca",
    "quantita"
})
public class Prestazione {

    protected Long idPrestazione;
    protected String codicePrestazione;
    protected String descrizionePrestazione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOra;
    protected Branca branca;
    protected String quantita;

    /**
     * Recupera il valore della proprietÃ  idPrestazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdPrestazione() {
        return idPrestazione;
    }

    /**
     * Imposta il valore della proprietÃ  idPrestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdPrestazione(Long value) {
        this.idPrestazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  codicePrestazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodicePrestazione() {
        return codicePrestazione;
    }

    /**
     * Imposta il valore della proprietÃ  codicePrestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodicePrestazione(String value) {
        this.codicePrestazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizionePrestazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizionePrestazione() {
        return descrizionePrestazione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizionePrestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizionePrestazione(String value) {
        this.descrizionePrestazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataOra.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOra() {
        return dataOra;
    }

    /**
     * Imposta il valore della proprietÃ  dataOra.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOra(XMLGregorianCalendar value) {
        this.dataOra = value;
    }

    /**
     * Recupera il valore della proprietÃ  branca.
     * 
     * @return
     *     possible object is
     *     {@link Branca }
     *     
     */
    public Branca getBranca() {
        return branca;
    }

    /**
     * Imposta il valore della proprietÃ  branca.
     * 
     * @param value
     *     allowed object is
     *     {@link Branca }
     *     
     */
    public void setBranca(Branca value) {
        this.branca = value;
    }

    /**
     * Recupera il valore della proprietÃ  quantita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantita() {
        return quantita;
    }

    /**
     * Imposta il valore della proprietÃ  quantita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantita(String value) {
        this.quantita = value;
    }

}
