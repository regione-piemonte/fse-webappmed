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
 * <p>Classe Java per metadatiMedicoPaziente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="metadatiMedicoPaziente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cognomeMMG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="emailMMG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagInvioNotificaMMG" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="metadatiPazienti" type="{http://dma.csi.it/}metadatiPaziente" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadatiMedicoPaziente", propOrder = {
    "cognomeMMG",
    "emailMMG",
    "flagInvioNotificaMMG",
    "metadatiPazienti"
})
public class MetadatiMedicoPaziente {

    protected String cognomeMMG;
    protected String emailMMG;
    @XmlSchemaType(name = "string")
    protected SiNo flagInvioNotificaMMG;
    @XmlElement(nillable = true)
    protected List<MetadatiPaziente> metadatiPazienti;

    /**
     * Recupera il valore della proprietÃ  cognomeMMG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognomeMMG() {
        return cognomeMMG;
    }

    /**
     * Imposta il valore della proprietÃ  cognomeMMG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognomeMMG(String value) {
        this.cognomeMMG = value;
    }

    /**
     * Recupera il valore della proprietÃ  emailMMG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailMMG() {
        return emailMMG;
    }

    /**
     * Imposta il valore della proprietÃ  emailMMG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailMMG(String value) {
        this.emailMMG = value;
    }

    /**
     * Recupera il valore della proprietÃ  flagInvioNotificaMMG.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getFlagInvioNotificaMMG() {
        return flagInvioNotificaMMG;
    }

    /**
     * Imposta il valore della proprietÃ  flagInvioNotificaMMG.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setFlagInvioNotificaMMG(SiNo value) {
        this.flagInvioNotificaMMG = value;
    }

    /**
     * Gets the value of the metadatiPazienti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadatiPazienti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadatiPazienti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadatiPaziente }
     * 
     * 
     */
    public List<MetadatiPaziente> getMetadatiPazienti() {
        if (metadatiPazienti == null) {
            metadatiPazienti = new ArrayList<MetadatiPaziente>();
        }
        return this.metadatiPazienti;
    }

}
