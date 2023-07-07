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
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;


/**
 * <p>Classe Java per getCLDaInvocare complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getCLDaInvocare"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="includiTipiCL" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="pazienteIdAura" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="pazienteIdIrec" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCLDaInvocare", propOrder = {
    "includiTipiCL",
    "pazienteIdAura",
    "pazienteIdIrec",
    "richiedente"
})
public class GetCLDaInvocare {

    @XmlElement(nillable = true)
    protected List<String> includiTipiCL;
    protected Long pazienteIdAura;
    protected Long pazienteIdIrec;
    protected Richiedente richiedente;

    /**
     * Gets the value of the includiTipiCL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includiTipiCL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludiTipiCL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIncludiTipiCL() {
        if (includiTipiCL == null) {
            includiTipiCL = new ArrayList<String>();
        }
        return this.includiTipiCL;
    }

    /**
     * Recupera il valore della proprietÃ  pazienteIdAura.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPazienteIdAura() {
        return pazienteIdAura;
    }

    /**
     * Imposta il valore della proprietÃ  pazienteIdAura.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPazienteIdAura(Long value) {
        this.pazienteIdAura = value;
    }

    /**
     * Recupera il valore della proprietÃ  pazienteIdIrec.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPazienteIdIrec() {
        return pazienteIdIrec;
    }

    /**
     * Imposta il valore della proprietÃ  pazienteIdIrec.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPazienteIdIrec(Long value) {
        this.pazienteIdIrec = value;
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

}
