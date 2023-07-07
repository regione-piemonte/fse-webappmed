/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per consenso complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="consenso"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="alimentaFse" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="consensoAllaCostituzioneDMA" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="consensoMatriceRuoli" type="{http://dma.csi.it/}consensoMatriceRuolo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="dataDiAggiornamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataDiValidazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="derogaEmergenza" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}modalitaDiAlimentazione" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consenso", propOrder = {
    "alimentaFse",
    "consensoAllaCostituzioneDMA",
    "consensoMatriceRuoli",
    "dataDiAggiornamento",
    "dataDiValidazione",
    "derogaEmergenza",
    "modalitaDiAlimentazione"
})
public class Consenso {

    @XmlSchemaType(name = "string")
    protected SiNo alimentaFse;
    @XmlSchemaType(name = "string")
    protected SiNo consensoAllaCostituzioneDMA;
    @XmlElement(nillable = true)
    protected List<ConsensoMatriceRuolo> consensoMatriceRuoli;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDiAggiornamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDiValidazione;
    @XmlSchemaType(name = "string")
    protected SiNo derogaEmergenza;
    protected ModalitaDiAlimentazione modalitaDiAlimentazione;

    /**
     * Recupera il valore della proprieta' alimentaFse.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getAlimentaFse() {
        return alimentaFse;
    }

    /**
     * Imposta il valore della proprieta' alimentaFse.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setAlimentaFse(SiNo value) {
        this.alimentaFse = value;
    }

    /**
     * Recupera il valore della proprieta' consensoAllaCostituzioneDMA.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getConsensoAllaCostituzioneDMA() {
        return consensoAllaCostituzioneDMA;
    }

    /**
     * Imposta il valore della proprieta' consensoAllaCostituzioneDMA.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setConsensoAllaCostituzioneDMA(SiNo value) {
        this.consensoAllaCostituzioneDMA = value;
    }

    /**
     * Gets the value of the consensoMatriceRuoli property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consensoMatriceRuoli property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsensoMatriceRuoli().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsensoMatriceRuolo }
     * 
     * 
     */
    public List<ConsensoMatriceRuolo> getConsensoMatriceRuoli() {
        if (consensoMatriceRuoli == null) {
            consensoMatriceRuoli = new ArrayList<ConsensoMatriceRuolo>();
        }
        return this.consensoMatriceRuoli;
    }

    /**
     * Recupera il valore della proprieta' dataDiAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDiAggiornamento() {
        return dataDiAggiornamento;
    }

    /**
     * Imposta il valore della proprieta' dataDiAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDiAggiornamento(XMLGregorianCalendar value) {
        this.dataDiAggiornamento = value;
    }

    /**
     * Recupera il valore della proprieta' dataDiValidazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDiValidazione() {
        return dataDiValidazione;
    }

    /**
     * Imposta il valore della proprieta' dataDiValidazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDiValidazione(XMLGregorianCalendar value) {
        this.dataDiValidazione = value;
    }

    /**
     * Recupera il valore della proprieta' derogaEmergenza.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getDerogaEmergenza() {
        return derogaEmergenza;
    }

    /**
     * Imposta il valore della proprieta' derogaEmergenza.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setDerogaEmergenza(SiNo value) {
        this.derogaEmergenza = value;
    }

    /**
     * Recupera il valore della proprieta' modalitaDiAlimentazione.
     * 
     * @return
     *     possible object is
     *     {@link ModalitaDiAlimentazione }
     *     
     */
    public ModalitaDiAlimentazione getModalitaDiAlimentazione() {
        return modalitaDiAlimentazione;
    }

    /**
     * Imposta il valore della proprieta' modalitaDiAlimentazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ModalitaDiAlimentazione }
     *     
     */
    public void setModalitaDiAlimentazione(ModalitaDiAlimentazione value) {
        this.modalitaDiAlimentazione = value;
    }

}
