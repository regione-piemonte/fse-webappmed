/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.dma.apiopsan.scaricorefertoservice.dma.ComponenteLocale;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Paziente;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Richiedente;



/**
 * <p>Java class for checkGetRefertoSR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkGetRefertoSR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/>
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/>
 *         &lt;element ref="{http://dma.csi.it/}componenteLocale" minOccurs="0"/>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pinCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="richiestoPinCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="numeroGiorniDownload" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pinBruciato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkGetRefertoSR", propOrder = {
    "richiedente",
    "paziente",
    "componenteLocale",
    "idDocumento",
    "pinCode",
    "richiestoPinCode",
    "numeroGiorniDownload",
    "pinBruciato"
})
public class CheckGetRefertoSR {

    @XmlElement(namespace = "http://dma.csi.it/")
    protected Richiedente richiedente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Paziente paziente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected ComponenteLocale componenteLocale;
    protected String idDocumento;
    protected String pinCode;
    protected boolean richiestoPinCode;
    protected int numeroGiorniDownload;
    protected boolean pinBruciato;

    /**
     * Gets the value of the richiedente property.
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
     * Sets the value of the richiedente property.
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
     * Gets the value of the paziente property.
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
     * Sets the value of the paziente property.
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
     * Gets the value of the componenteLocale property.
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
     * Sets the value of the componenteLocale property.
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
     * Gets the value of the idDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumento() {
        return idDocumento;
    }

    /**
     * Sets the value of the idDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumento(String value) {
        this.idDocumento = value;
    }

    /**
     * Gets the value of the pinCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Sets the value of the pinCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinCode(String value) {
        this.pinCode = value;
    }

    /**
     * Gets the value of the richiestoPinCode property.
     * 
     */
    public boolean isRichiestoPinCode() {
        return richiestoPinCode;
    }

    /**
     * Sets the value of the richiestoPinCode property.
     * 
     */
    public void setRichiestoPinCode(boolean value) {
        this.richiestoPinCode = value;
    }

    /**
     * Gets the value of the numeroGiorniDownload property.
     * 
     */
    public int getNumeroGiorniDownload() {
        return numeroGiorniDownload;
    }

    /**
     * Sets the value of the numeroGiorniDownload property.
     * 
     */
    public void setNumeroGiorniDownload(int value) {
        this.numeroGiorniDownload = value;
    }

    /**
     * Gets the value of the pinBruciato property.
     * 
     */
    public boolean isPinBruciato() {
        return pinBruciato;
    }

    /**
     * Sets the value of the pinBruciato property.
     * 
     */
    public void setPinBruciato(boolean value) {
        this.pinBruciato = value;
    }

}
