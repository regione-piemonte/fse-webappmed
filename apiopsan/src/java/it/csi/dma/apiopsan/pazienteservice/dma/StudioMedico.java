/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per studioMedico complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="studioMedico"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ambulatorioPubblico" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="giorniApertura" type="{http://dma.csi.it/}giornoAperturaStudio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="indirizzoStudio" type="{http://dma.csi.it/}indirizzo" minOccurs="0"/&gt;
 *         &lt;element name="telPrimario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telSecondario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoStudio" type="{http://dma.csi.it/}tipoStudio" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studioMedico", propOrder = {
    "ambulatorioPubblico",
    "denominazione",
    "email",
    "giorniApertura",
    "indirizzoStudio",
    "telPrimario",
    "telSecondario",
    "tipoStudio"
})
public class StudioMedico {

    @XmlSchemaType(name = "string")
    protected SiNo ambulatorioPubblico;
    protected String denominazione;
    protected String email;
    @XmlElement(nillable = true)
    protected List<GiornoAperturaStudio> giorniApertura;
    protected Indirizzo indirizzoStudio;
    protected String telPrimario;
    protected String telSecondario;
    protected TipoStudio tipoStudio;

    /**
     * Recupera il valore della proprietÃ  ambulatorioPubblico.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getAmbulatorioPubblico() {
        return ambulatorioPubblico;
    }

    /**
     * Imposta il valore della proprietÃ  ambulatorioPubblico.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setAmbulatorioPubblico(SiNo value) {
        this.ambulatorioPubblico = value;
    }

    /**
     * Recupera il valore della proprietÃ  denominazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Imposta il valore della proprietÃ  denominazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprietÃ  email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the giorniApertura property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the giorniApertura property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGiorniApertura().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GiornoAperturaStudio }
     * 
     * 
     */
    public List<GiornoAperturaStudio> getGiorniApertura() {
        if (giorniApertura == null) {
            giorniApertura = new ArrayList<GiornoAperturaStudio>();
        }
        return this.giorniApertura;
    }

    /**
     * Recupera il valore della proprietÃ  indirizzoStudio.
     * 
     * @return
     *     possible object is
     *     {@link Indirizzo }
     *     
     */
    public Indirizzo getIndirizzoStudio() {
        return indirizzoStudio;
    }

    /**
     * Imposta il valore della proprietÃ  indirizzoStudio.
     * 
     * @param value
     *     allowed object is
     *     {@link Indirizzo }
     *     
     */
    public void setIndirizzoStudio(Indirizzo value) {
        this.indirizzoStudio = value;
    }

    /**
     * Recupera il valore della proprietÃ  telPrimario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelPrimario() {
        return telPrimario;
    }

    /**
     * Imposta il valore della proprietÃ  telPrimario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelPrimario(String value) {
        this.telPrimario = value;
    }

    /**
     * Recupera il valore della proprietÃ  telSecondario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelSecondario() {
        return telSecondario;
    }

    /**
     * Imposta il valore della proprietÃ  telSecondario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelSecondario(String value) {
        this.telSecondario = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoStudio.
     * 
     * @return
     *     possible object is
     *     {@link TipoStudio }
     *     
     */
    public TipoStudio getTipoStudio() {
        return tipoStudio;
    }

    /**
     * Imposta il valore della proprietÃ  tipoStudio.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoStudio }
     *     
     */
    public void setTipoStudio(TipoStudio value) {
        this.tipoStudio = value;
    }

}
