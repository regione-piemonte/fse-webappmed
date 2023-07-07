/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.ComponenteLocale;
import it.csi.dma.apiopsan.pazienteservice.dma.NotificaPresenzaDatiTipo;
import it.csi.dma.apiopsan.pazienteservice.dma.Paziente;
import it.csi.dma.apiopsan.pazienteservice.dma.PresenzaDatiILECRuolo;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;


/**
 * <p>Classe Java per notificaPresenzaDatiILEC complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="notificaPresenzaDatiILEC"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element name="paziente" type="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element name="componenteLocale" type="{http://dma.csi.it/}componenteLocale" minOccurs="0"/&gt;
 *         &lt;element name="numeroEpisodi" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="numeroDocumenti" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="tipo" type="{http://dma.csi.it/}notificaPresenzaDatiTipo" minOccurs="0"/&gt;
 *         &lt;element name="presenzaDatiILECRuoli" type="{http://dma.csi.it/}presenzaDatiILECRuolo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="flagEsenzioniOscurato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagLeggiSpeciali" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notificaPresenzaDatiILEC", propOrder = {
    "richiedente",
    "paziente",
    "componenteLocale",
    "numeroEpisodi",
    "numeroDocumenti",
    "tipo",
    "presenzaDatiILECRuoli",
    "flagEsenzioniOscurato",
    "flagLeggiSpeciali"
})
public class NotificaPresenzaDatiILEC {

    protected Richiedente richiedente;
    protected Paziente paziente;
    protected ComponenteLocale componenteLocale;
    protected int numeroEpisodi;
    protected int numeroDocumenti;
    @XmlSchemaType(name = "string")
    protected NotificaPresenzaDatiTipo tipo;
    @XmlElement(nillable = true)
    protected List<PresenzaDatiILECRuolo> presenzaDatiILECRuoli;
    protected String flagEsenzioniOscurato;
    protected String flagLeggiSpeciali;

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
     * Recupera il valore della proprietÃ  componenteLocale.
     * 
     * @return
     *     possible object is
     *     {@link ComponenteLocale }
     *     
     */
    public ComponenteLocale getComponenteLocale() {
        return componenteLocale;
    }

    /**
     * Imposta il valore della proprietÃ  componenteLocale.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponenteLocale }
     *     
     */
    public void setComponenteLocale(ComponenteLocale value) {
        this.componenteLocale = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroEpisodi.
     * 
     */
    public int getNumeroEpisodi() {
        return numeroEpisodi;
    }

    /**
     * Imposta il valore della proprietÃ  numeroEpisodi.
     * 
     */
    public void setNumeroEpisodi(int value) {
        this.numeroEpisodi = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroDocumenti.
     * 
     */
    public int getNumeroDocumenti() {
        return numeroDocumenti;
    }

    /**
     * Imposta il valore della proprietÃ  numeroDocumenti.
     * 
     */
    public void setNumeroDocumenti(int value) {
        this.numeroDocumenti = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipo.
     * 
     * @return
     *     possible object is
     *     {@link NotificaPresenzaDatiTipo }
     *     
     */
    public NotificaPresenzaDatiTipo getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietÃ  tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link NotificaPresenzaDatiTipo }
     *     
     */
    public void setTipo(NotificaPresenzaDatiTipo value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the presenzaDatiILECRuoli property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the presenzaDatiILECRuoli property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPresenzaDatiILECRuoli().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PresenzaDatiILECRuolo }
     * 
     * 
     */
    public List<PresenzaDatiILECRuolo> getPresenzaDatiILECRuoli() {
        if (presenzaDatiILECRuoli == null) {
            presenzaDatiILECRuoli = new ArrayList<PresenzaDatiILECRuolo>();
        }
        return this.presenzaDatiILECRuoli;
    }

    /**
     * Recupera il valore della proprietÃ  flagEsenzioniOscurato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagEsenzioniOscurato() {
        return flagEsenzioniOscurato;
    }

    /**
     * Imposta il valore della proprietÃ  flagEsenzioniOscurato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagEsenzioniOscurato(String value) {
        this.flagEsenzioniOscurato = value;
    }

    /**
     * Recupera il valore della proprietÃ  flagLeggiSpeciali.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagLeggiSpeciali() {
        return flagLeggiSpeciali;
    }

    /**
     * Imposta il valore della proprietÃ  flagLeggiSpeciali.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagLeggiSpeciali(String value) {
        this.flagLeggiSpeciali = value;
    }

}
