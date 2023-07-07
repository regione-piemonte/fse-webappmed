/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per consensoPerRuolo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="consensoPerRuolo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="conferimentoConsenso" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="dataDiAggiornamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="oscurato" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="ruoloDMA" type="{http://dma.csi.it/}ruoloDMA" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consensoPerRuolo", propOrder = {
    "conferimentoConsenso",
    "dataDiAggiornamento",
    "oscurato",
    "ruoloDMA"
})
public class ConsensoPerRuolo {

    @XmlSchemaType(name = "string")
    protected SiNo conferimentoConsenso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDiAggiornamento;
    @XmlSchemaType(name = "string")
    protected SiNo oscurato;
    protected RuoloDMA ruoloDMA;

    /**
     * Recupera il valore della proprieta' conferimentoConsenso.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getConferimentoConsenso() {
        return conferimentoConsenso;
    }

    /**
     * Imposta il valore della proprieta' conferimentoConsenso.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setConferimentoConsenso(SiNo value) {
        this.conferimentoConsenso = value;
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
     * Recupera il valore della proprieta' oscurato.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getOscurato() {
        return oscurato;
    }

    /**
     * Imposta il valore della proprieta' oscurato.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setOscurato(SiNo value) {
        this.oscurato = value;
    }

    /**
     * Recupera il valore della proprieta' ruoloDMA.
     * 
     * @return
     *     possible object is
     *     {@link RuoloDMA }
     *     
     */
    public RuoloDMA getRuoloDMA() {
        return ruoloDMA;
    }

    /**
     * Imposta il valore della proprieta' ruoloDMA.
     * 
     * @param value
     *     allowed object is
     *     {@link RuoloDMA }
     *     
     */
    public void setRuoloDMA(RuoloDMA value) {
        this.ruoloDMA = value;
    }

}
