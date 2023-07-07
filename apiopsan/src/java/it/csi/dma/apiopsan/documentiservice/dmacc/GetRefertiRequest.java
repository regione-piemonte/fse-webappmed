/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import it.csi.dma.apiopsan.documentiservice.dma.Paziente;
import it.csi.dma.apiopsan.documentiservice.dma.Richiedente;


/**
 * <p>Classe Java per getRefertiRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getRefertiRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="criterioOrdinamento" type="{http://dmacc.csi.it/}criterioOrdinamento" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dataFineRicerca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dataInizioRicerca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numElementiVisualizzati" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/&gt;
 *         &lt;element name="numPagina" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element name="timeoutChiamanteInSec" type="{http://www.w3.org/2001/XMLSchema}int" form="unqualified"/&gt;
 *         &lt;element name="tipoMedico" type="{http://dmacc.csi.it/}tipoMedico" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="tipoOrdinamento" type="{http://dmacc.csi.it/}tipoOrdinamento" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRefertiRequest", propOrder = {
    "criterioOrdinamento",
    "dataFineRicerca",
    "dataInizioRicerca",
    "numElementiVisualizzati",
    "numPagina",
    "paziente",
    "richiedente",
    "timeoutChiamanteInSec",
    "tipoMedico",
    "tipoOrdinamento"
})
public class GetRefertiRequest {

    @XmlSchemaType(name = "string")
    protected CriterioOrdinamento criterioOrdinamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineRicerca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioRicerca;
    protected int numElementiVisualizzati;
    protected int numPagina;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Paziente paziente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Richiedente richiedente;
    protected int timeoutChiamanteInSec;
    @XmlSchemaType(name = "string")
    protected TipoMedico tipoMedico;
    @XmlSchemaType(name = "string")
    protected TipoOrdinamento tipoOrdinamento;

    /**
     * Recupera il valore della proprietÃ  criterioOrdinamento.
     * 
     * @return
     *     possible object is
     *     {@link CriterioOrdinamento }
     *     
     */
    public CriterioOrdinamento getCriterioOrdinamento() {
        return criterioOrdinamento;
    }

    /**
     * Imposta il valore della proprietÃ  criterioOrdinamento.
     * 
     * @param value
     *     allowed object is
     *     {@link CriterioOrdinamento }
     *     
     */
    public void setCriterioOrdinamento(CriterioOrdinamento value) {
        this.criterioOrdinamento = value;
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
     * Recupera il valore della proprietÃ  numElementiVisualizzati.
     * 
     */
    public int getNumElementiVisualizzati() {
        return numElementiVisualizzati;
    }

    /**
     * Imposta il valore della proprietÃ  numElementiVisualizzati.
     * 
     */
    public void setNumElementiVisualizzati(int value) {
        this.numElementiVisualizzati = value;
    }

    /**
     * Recupera il valore della proprietÃ  numPagina.
     * 
     */
    public int getNumPagina() {
        return numPagina;
    }

    /**
     * Imposta il valore della proprietÃ  numPagina.
     * 
     */
    public void setNumPagina(int value) {
        this.numPagina = value;
    }

    /**
     * Recupera il valore della proprietÃ  paziente.
     * 
     * @return
     *     possible object is
     *     {@link Paziente }
     *     
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     * Imposta il valore della proprietÃ  paziente.
     * 
     * @param value
     *     allowed object is
     *     {@link Paziente }
     *     
     */
    public void setPaziente(Paziente value) {
        this.paziente = value;
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

    /**
     * Recupera il valore della proprietÃ  timeoutChiamanteInSec.
     * 
     */
    public int getTimeoutChiamanteInSec() {
        return timeoutChiamanteInSec;
    }

    /**
     * Imposta il valore della proprietÃ  timeoutChiamanteInSec.
     * 
     */
    public void setTimeoutChiamanteInSec(int value) {
        this.timeoutChiamanteInSec = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoMedico.
     * 
     * @return
     *     possible object is
     *     {@link TipoMedico }
     *     
     */
    public TipoMedico getTipoMedico() {
        return tipoMedico;
    }

    /**
     * Imposta il valore della proprietÃ  tipoMedico.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMedico }
     *     
     */
    public void setTipoMedico(TipoMedico value) {
        this.tipoMedico = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoOrdinamento.
     * 
     * @return
     *     possible object is
     *     {@link TipoOrdinamento }
     *     
     */
    public TipoOrdinamento getTipoOrdinamento() {
        return tipoOrdinamento;
    }

    /**
     * Imposta il valore della proprietÃ  tipoOrdinamento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoOrdinamento }
     *     
     */
    public void setTipoOrdinamento(TipoOrdinamento value) {
        this.tipoOrdinamento = value;
    }

}
