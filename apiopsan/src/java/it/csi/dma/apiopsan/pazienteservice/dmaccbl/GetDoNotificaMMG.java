/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;


/**
 * <p>Classe Java per getDoNotificaMMG complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDoNotificaMMG"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}tipoDati" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}dataInizioRicerca" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}dataFineRicerca" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDoNotificaMMG", propOrder = {
    "richiedente",
    "tipoDati",
    "dataInizioRicerca",
    "dataFineRicerca"
})
public class GetDoNotificaMMG {

    @XmlElement(namespace = "http://dma.csi.it/")
    protected Richiedente richiedente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected String tipoDati;
    @XmlElement(namespace = "http://dma.csi.it/")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioRicerca;
    @XmlElement(namespace = "http://dma.csi.it/")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineRicerca;

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

    /**
     * Recupera il valore della proprietÃ  tipoDati.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDati() {
        return tipoDati;
    }

    /**
     * Imposta il valore della proprietÃ  tipoDati.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDati(String value) {
        this.tipoDati = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataInizioRicerca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioRicerca() {
        return dataInizioRicerca;
    }

    /**
     * Imposta il valore della proprietÃ  dataInizioRicerca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioRicerca(XMLGregorianCalendar value) {
        this.dataInizioRicerca = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataFineRicerca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineRicerca() {
        return dataFineRicerca;
    }

    /**
     * Imposta il valore della proprietÃ  dataFineRicerca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineRicerca(XMLGregorianCalendar value) {
        this.dataFineRicerca = value;
    }

}
