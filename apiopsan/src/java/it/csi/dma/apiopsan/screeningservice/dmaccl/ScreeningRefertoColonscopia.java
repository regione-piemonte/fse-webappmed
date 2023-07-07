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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for screeningRefertoColonscopia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="screeningRefertoColonscopia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="colonscopiaInDH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visionePulizia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sedeRaggiunta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colonscopiaNonCompleta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presenzaPolipi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presenzaLesioneSospettaPerCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="necessitaDiSupportoFarmacologico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="altreAnomalieRilevate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="complicanzeImmediate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="necessitaDiAssistenzaOspedaliera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="polipi" type="{http://dma.csi.it/}screeningPolipoColon" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "screeningRefertoColonscopia", propOrder = {
    "colonscopiaInDH",
    "visionePulizia",
    "sedeRaggiunta",
    "colonscopiaNonCompleta",
    "motivo",
    "presenzaPolipi",
    "presenzaLesioneSospettaPerCA",
    "necessitaDiSupportoFarmacologico",
    "altreAnomalieRilevate",
    "complicanzeImmediate",
    "necessitaDiAssistenzaOspedaliera",
    "polipi"
})
public class ScreeningRefertoColonscopia {

    protected String colonscopiaInDH;
    protected String visionePulizia;
    protected String sedeRaggiunta;
    protected String colonscopiaNonCompleta;
    protected String motivo;
    protected String presenzaPolipi;
    protected String presenzaLesioneSospettaPerCA;
    protected String necessitaDiSupportoFarmacologico;
    protected String altreAnomalieRilevate;
    protected String complicanzeImmediate;
    protected String necessitaDiAssistenzaOspedaliera;
    @XmlElement(nillable = true)
    protected List<ScreeningPolipoColon> polipi;

    /**
     * Gets the value of the colonscopiaInDH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonscopiaInDH() {
        return colonscopiaInDH;
    }

    /**
     * Sets the value of the colonscopiaInDH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonscopiaInDH(String value) {
        this.colonscopiaInDH = value;
    }

    /**
     * Gets the value of the visionePulizia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisionePulizia() {
        return visionePulizia;
    }

    /**
     * Sets the value of the visionePulizia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisionePulizia(String value) {
        this.visionePulizia = value;
    }

    /**
     * Gets the value of the sedeRaggiunta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSedeRaggiunta() {
        return sedeRaggiunta;
    }

    /**
     * Sets the value of the sedeRaggiunta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSedeRaggiunta(String value) {
        this.sedeRaggiunta = value;
    }

    /**
     * Gets the value of the colonscopiaNonCompleta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonscopiaNonCompleta() {
        return colonscopiaNonCompleta;
    }

    /**
     * Sets the value of the colonscopiaNonCompleta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonscopiaNonCompleta(String value) {
        this.colonscopiaNonCompleta = value;
    }

    /**
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

    /**
     * Gets the value of the presenzaPolipi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresenzaPolipi() {
        return presenzaPolipi;
    }

    /**
     * Sets the value of the presenzaPolipi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresenzaPolipi(String value) {
        this.presenzaPolipi = value;
    }

    /**
     * Gets the value of the presenzaLesioneSospettaPerCA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresenzaLesioneSospettaPerCA() {
        return presenzaLesioneSospettaPerCA;
    }

    /**
     * Sets the value of the presenzaLesioneSospettaPerCA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresenzaLesioneSospettaPerCA(String value) {
        this.presenzaLesioneSospettaPerCA = value;
    }

    /**
     * Gets the value of the necessitaDiSupportoFarmacologico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNecessitaDiSupportoFarmacologico() {
        return necessitaDiSupportoFarmacologico;
    }

    /**
     * Sets the value of the necessitaDiSupportoFarmacologico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNecessitaDiSupportoFarmacologico(String value) {
        this.necessitaDiSupportoFarmacologico = value;
    }

    /**
     * Gets the value of the altreAnomalieRilevate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltreAnomalieRilevate() {
        return altreAnomalieRilevate;
    }

    /**
     * Sets the value of the altreAnomalieRilevate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltreAnomalieRilevate(String value) {
        this.altreAnomalieRilevate = value;
    }

    /**
     * Gets the value of the complicanzeImmediate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplicanzeImmediate() {
        return complicanzeImmediate;
    }

    /**
     * Sets the value of the complicanzeImmediate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplicanzeImmediate(String value) {
        this.complicanzeImmediate = value;
    }

    /**
     * Gets the value of the necessitaDiAssistenzaOspedaliera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNecessitaDiAssistenzaOspedaliera() {
        return necessitaDiAssistenzaOspedaliera;
    }

    /**
     * Sets the value of the necessitaDiAssistenzaOspedaliera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNecessitaDiAssistenzaOspedaliera(String value) {
        this.necessitaDiAssistenzaOspedaliera = value;
    }

    /**
     * Gets the value of the polipi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the polipi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolipi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScreeningPolipoColon }
     * 
     * 
     */
    public List<ScreeningPolipoColon> getPolipi() {
        if (polipi == null) {
            polipi = new ArrayList<ScreeningPolipoColon>();
        }
        return this.polipi;
    }

}
