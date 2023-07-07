/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.esenzioniservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per esenzione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="esenzione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}codifica"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="diagnosi" type="{http://dma.csi.it/}diagnosiEsenzione" minOccurs="0"/&gt;
 *         &lt;element name="enteEmittente" type="{http://dma.csi.it/}enteEmittenteEsenzione" minOccurs="0"/&gt;
 *         &lt;element name="dataEmissione" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="dataScadenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="codiceAttestato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oscurato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esenzione", propOrder = {
    "diagnosi",
    "enteEmittente",
    "dataEmissione",
    "dataScadenza",
    "codiceAttestato",
    "oscurato"
})
public class Esenzione
    extends Codifica
{

    protected DiagnosiEsenzione diagnosi;
    protected EnteEmittenteEsenzione enteEmittente;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataEmissione;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataScadenza;
    protected String codiceAttestato;
    protected String oscurato;

    /**
     * Recupera il valore della proprietÃ  diagnosi.
     * 
     * @return
     *     possible object is
     *     {@link DiagnosiEsenzione }
     *     
     */
    public DiagnosiEsenzione getDiagnosi() {
        return diagnosi;
    }

    /**
     * Imposta il valore della proprietÃ  diagnosi.
     * 
     * @param value
     *     allowed object is
     *     {@link DiagnosiEsenzione }
     *     
     */
    public void setDiagnosi(DiagnosiEsenzione value) {
        this.diagnosi = value;
    }

    /**
     * Recupera il valore della proprietÃ  enteEmittente.
     * 
     * @return
     *     possible object is
     *     {@link EnteEmittenteEsenzione }
     *     
     */
    public EnteEmittenteEsenzione getEnteEmittente() {
        return enteEmittente;
    }

    /**
     * Imposta il valore della proprietÃ  enteEmittente.
     * 
     * @param value
     *     allowed object is
     *     {@link EnteEmittenteEsenzione }
     *     
     */
    public void setEnteEmittente(EnteEmittenteEsenzione value) {
        this.enteEmittente = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataEmissione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEmissione() {
        return dataEmissione;
    }

    /**
     * Imposta il valore della proprietÃ  dataEmissione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEmissione(XMLGregorianCalendar value) {
        this.dataEmissione = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataScadenza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta il valore della proprietÃ  dataScadenza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceAttestato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAttestato() {
        return codiceAttestato;
    }

    /**
     * Imposta il valore della proprietÃ  codiceAttestato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAttestato(String value) {
        this.codiceAttestato = value;
    }

    /**
     * Recupera il valore della proprietÃ  oscurato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOscurato() {
        return oscurato;
    }

    /**
     * Imposta il valore della proprietÃ  oscurato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOscurato(String value) {
        this.oscurato = value;
    }

}
