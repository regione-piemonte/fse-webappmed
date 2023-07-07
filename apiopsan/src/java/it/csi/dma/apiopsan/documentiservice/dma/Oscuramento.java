/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per oscuramento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="oscuramento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fonteOscuramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oscurato" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="tipoDiDato" type="{http://dma.csi.it/}consensoMatriceTipoDato" minOccurs="0"/&gt;
 *         &lt;element name="valoreCalcolato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oscuramento", propOrder = {
    "fonteOscuramento",
    "id1",
    "id2",
    "id3",
    "id4",
    "id5",
    "oscurato",
    "tipoDiDato",
    "valoreCalcolato"
})
public class Oscuramento {

    protected String fonteOscuramento;
    protected String id1;
    protected String id2;
    protected String id3;
    protected String id4;
    protected String id5;
    @XmlSchemaType(name = "string")
    protected SiNo oscurato;
    protected ConsensoMatriceTipoDato tipoDiDato;
    protected String valoreCalcolato;

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
     * Recupera il valore della proprietÃ  id1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId1() {
        return id1;
    }

    /**
     * Imposta il valore della proprietÃ  id1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId1(String value) {
        this.id1 = value;
    }

    /**
     * Recupera il valore della proprietÃ  id2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId2() {
        return id2;
    }

    /**
     * Imposta il valore della proprietÃ  id2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId2(String value) {
        this.id2 = value;
    }

    /**
     * Recupera il valore della proprietÃ  id3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId3() {
        return id3;
    }

    /**
     * Imposta il valore della proprietÃ  id3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId3(String value) {
        this.id3 = value;
    }

    /**
     * Recupera il valore della proprietÃ  id4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId4() {
        return id4;
    }

    /**
     * Imposta il valore della proprietÃ  id4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId4(String value) {
        this.id4 = value;
    }

    /**
     * Recupera il valore della proprietÃ  id5.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId5() {
        return id5;
    }

    /**
     * Imposta il valore della proprietÃ  id5.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId5(String value) {
        this.id5 = value;
    }

    /**
     * Recupera il valore della proprietÃ  oscurato.
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
     * Imposta il valore della proprietÃ  oscurato.
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
     * Recupera il valore della proprietÃ  tipoDiDato.
     * 
     * @return
     *     possible object is
     *     {@link ConsensoMatriceTipoDato }
     *     
     */
    public ConsensoMatriceTipoDato getTipoDiDato() {
        return tipoDiDato;
    }

    /**
     * Imposta il valore della proprietÃ  tipoDiDato.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsensoMatriceTipoDato }
     *     
     */
    public void setTipoDiDato(ConsensoMatriceTipoDato value) {
        this.tipoDiDato = value;
    }

    /**
     * Recupera il valore della proprietÃ  valoreCalcolato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValoreCalcolato() {
        return valoreCalcolato;
    }

    /**
     * Imposta il valore della proprietÃ  valoreCalcolato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValoreCalcolato(String value) {
        this.valoreCalcolato = value;
    }

}
