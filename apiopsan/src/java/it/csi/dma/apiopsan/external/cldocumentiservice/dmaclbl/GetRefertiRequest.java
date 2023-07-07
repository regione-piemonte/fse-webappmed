/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.ArrayIdIrecCFPaziente;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.Paziente;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.Richiedente;


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
 *         &lt;element name="criterioOrdinamento" type="{http://dmaclbl.csi.it/}criterioOrdinamento" minOccurs="0"/&gt;
 *         &lt;element name="dataFineRicerca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioRicerca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="listaArrayIdIrecCFPaziente" type="{http://dma.csi.it/}ArrayIdIrecCFPaziente" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element name="timeoutChiamanteInSec" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="tipoMedico" type="{http://dmaclbl.csi.it/}tipoMedico" minOccurs="0"/&gt;
 *         &lt;element name="tipoOrdinamento" type="{http://dmaclbl.csi.it/}tipoOrdinamento" minOccurs="0"/&gt;
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
    "listaArrayIdIrecCFPaziente",
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
    @XmlElement(nillable = true)
    protected List<ArrayIdIrecCFPaziente> listaArrayIdIrecCFPaziente;
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
     * Recupera il valore della proprieta' criterioOrdinamento.
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
     * Imposta il valore della proprieta' criterioOrdinamento.
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
     * Recupera il valore della proprieta' dataFineRicerca.
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
     * Imposta il valore della proprieta' dataFineRicerca.
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
     * Recupera il valore della proprieta' dataInizioRicerca.
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
     * Imposta il valore della proprieta' dataInizioRicerca.
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
     * Gets the value of the listaArrayIdIrecCFPaziente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaArrayIdIrecCFPaziente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaArrayIdIrecCFPaziente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayIdIrecCFPaziente }
     * 
     * 
     */
    public List<ArrayIdIrecCFPaziente> getListaArrayIdIrecCFPaziente() {
        if (listaArrayIdIrecCFPaziente == null) {
            listaArrayIdIrecCFPaziente = new ArrayList<ArrayIdIrecCFPaziente>();
        }
        return this.listaArrayIdIrecCFPaziente;
    }

    /**
     * Recupera il valore della proprieta' paziente.
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
     * Imposta il valore della proprieta' paziente.
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
     * Recupera il valore della proprieta' richiedente.
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
     * Imposta il valore della proprieta' richiedente.
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
     * Recupera il valore della proprieta' timeoutChiamanteInSec.
     * 
     */
    public int getTimeoutChiamanteInSec() {
        return timeoutChiamanteInSec;
    }

    /**
     * Imposta il valore della proprieta' timeoutChiamanteInSec.
     * 
     */
    public void setTimeoutChiamanteInSec(int value) {
        this.timeoutChiamanteInSec = value;
    }

    /**
     * Recupera il valore della proprieta' tipoMedico.
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
     * Imposta il valore della proprieta' tipoMedico.
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
     * Recupera il valore della proprieta' tipoOrdinamento.
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
     * Imposta il valore della proprieta' tipoOrdinamento.
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
