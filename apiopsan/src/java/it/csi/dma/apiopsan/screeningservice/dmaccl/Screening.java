/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.screeningservice.dmaccl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for screening complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="screening">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipo" type="{http://dma.csi.it/}screeningTipo" minOccurs="0"/>
 *         &lt;element name="paziente" type="{http://dma.csi.it/}pazienteScreening" minOccurs="0"/>
 *         &lt;element name="esami" type="{http://dma.csi.it/}screeningEsame" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "screening", propOrder = {
    "tipo",
    "paziente",
    "esami"
})
@XmlSeeAlso({
    InformazioniSistemiScreening.class
})
public class Screening {

    protected ScreeningTipo tipo;
    protected PazienteScreening paziente;
    @XmlElement(nillable = true)
    protected List<ScreeningEsame> esami;

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link ScreeningTipo }
     *     
     */
    public ScreeningTipo getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScreeningTipo }
     *     
     */
    public void setTipo(ScreeningTipo value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the paziente property.
     * 
     * @return
     *     possible object is
     *     {@link PazienteScreening }
     *     
     */
    public PazienteScreening getPaziente() {
        return paziente;
    }

    /**
     * Sets the value of the paziente property.
     * 
     * @param value
     *     allowed object is
     *     {@link PazienteScreening }
     *     
     */
    public void setPaziente(PazienteScreening value) {
        this.paziente = value;
    }

    /**
     * Gets the value of the esami property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the esami property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEsami().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScreeningEsame }
     * 
     * 
     */
    public List<ScreeningEsame> getEsami() {
        if (esami == null) {
            esami = new ArrayList<ScreeningEsame>();
        }
        return this.esami;
    }

}
