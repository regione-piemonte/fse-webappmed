/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.MetadatiDocumento;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.Paziente;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.Richiedente;


/**
 * <p>Classe Java per SmediazioneDocumentiRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SmediazioneDocumentiRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}metadatiDocumento" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}numeroMaxProcessiSmediazione" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}periodoMassimoMediazione" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmediazioneDocumentiRequest", propOrder = {
    "richiedente",
    "paziente",
    "metadatiDocumento",
    "numeroMaxProcessiSmediazione",
    "periodoMassimoMediazione"
})
public class SmediazioneDocumentiRequest {

    @XmlElement(namespace = "http://dma.csi.it/")
    protected Richiedente richiedente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Paziente paziente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected MetadatiDocumento metadatiDocumento;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Long numeroMaxProcessiSmediazione;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Long periodoMassimoMediazione;

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
     * Recupera il valore della proprieta' metadatiDocumento.
     * 
     * @return
     *     possible object is
     *     {@link MetadatiDocumento }
     *     
     */
    public MetadatiDocumento getMetadatiDocumento() {
        return metadatiDocumento;
    }

    /**
     * Imposta il valore della proprieta' metadatiDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadatiDocumento }
     *     
     */
    public void setMetadatiDocumento(MetadatiDocumento value) {
        this.metadatiDocumento = value;
    }

    /**
     * Recupera il valore della proprieta' numeroMaxProcessiSmediazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroMaxProcessiSmediazione() {
        return numeroMaxProcessiSmediazione;
    }

    /**
     * Imposta il valore della proprieta' numeroMaxProcessiSmediazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroMaxProcessiSmediazione(Long value) {
        this.numeroMaxProcessiSmediazione = value;
    }

    /**
     * Recupera il valore della proprieta' periodoMassimoMediazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPeriodoMassimoMediazione() {
        return periodoMassimoMediazione;
    }

    /**
     * Imposta il valore della proprieta' periodoMassimoMediazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPeriodoMassimoMediazione(Long value) {
        this.periodoMassimoMediazione = value;
    }

}
