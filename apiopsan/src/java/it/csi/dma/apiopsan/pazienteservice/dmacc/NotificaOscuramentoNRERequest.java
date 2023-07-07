/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmacc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.Paziente;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;


/**
 * <p>Classe Java per NotificaOscuramentoNRERequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="NotificaOscuramentoNRERequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}componenteLocale" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}nre" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}idDocumentoIlec" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}fonteOscuramento" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}oscurato" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificaOscuramentoNRERequest", propOrder = {
    "richiedente",
    "paziente",
    "componenteLocale",
    "nre",
    "idDocumentoIlec",
    "fonteOscuramento",
    "oscurato"
})
public class NotificaOscuramentoNRERequest {

    @XmlElement(namespace = "http://dma.csi.it/")
    protected Richiedente richiedente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Paziente paziente;
    @XmlElement(namespace = "http://dma.csi.it/", nillable = true)
    protected Object componenteLocale;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<String> nre;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Long idDocumentoIlec;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected String fonteOscuramento;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected String oscurato;

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
     *     {@link Object }
     *     
     */
    public Object getComponenteLocale() {
        return componenteLocale;
    }

    /**
     * Imposta il valore della proprietÃ  componenteLocale.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setComponenteLocale(Object value) {
        this.componenteLocale = value;
    }

    /**
     * Gets the value of the nre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNre() {
        if (nre == null) {
            nre = new ArrayList<String>();
        }
        return this.nre;
    }

    /**
     * Recupera il valore della proprietÃ  idDocumentoIlec.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDocumentoIlec() {
        return idDocumentoIlec;
    }

    /**
     * Imposta il valore della proprietÃ  idDocumentoIlec.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDocumentoIlec(Long value) {
        this.idDocumentoIlec = value;
    }

    /**
     * Recupera il valore della proprietÃ  fonteOscuramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFonteOscuramento() {
        return fonteOscuramento;
    }

    /**
     * Imposta il valore della proprietÃ  fonteOscuramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFonteOscuramento(String value) {
        this.fonteOscuramento = value;
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
