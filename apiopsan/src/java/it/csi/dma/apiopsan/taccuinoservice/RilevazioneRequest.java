/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per rilevazioneRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="rilevazioneRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="valoreNumerico" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="valoreTestuale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="codiceUnitaMisura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="codiceGruppo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="codiceDescrittore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="codiceModalita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rilevazioneRequest", propOrder = {
    "valoreNumerico",
    "valoreTestuale",
    "data",
    "codiceUnitaMisura",
    "codiceGruppo",
    "codiceDescrittore",
    "codiceModalita"
})
public class RilevazioneRequest {

    protected Float valoreNumerico;
    protected String valoreTestuale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    protected String codiceUnitaMisura;
    protected String codiceGruppo;
    protected String codiceDescrittore;
    protected String codiceModalita;

    /**
     * Recupera il valore della proprietÃ  valoreNumerico.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getValoreNumerico() {
        return valoreNumerico;
    }

    /**
     * Imposta il valore della proprietÃ  valoreNumerico.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setValoreNumerico(Float value) {
        this.valoreNumerico = value;
    }

    /**
     * Recupera il valore della proprietÃ  valoreTestuale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValoreTestuale() {
        return valoreTestuale;
    }

    /**
     * Imposta il valore della proprietÃ  valoreTestuale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValoreTestuale(String value) {
        this.valoreTestuale = value;
    }

    /**
     * Recupera il valore della proprietÃ  data.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietÃ  data.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceUnitaMisura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceUnitaMisura() {
        return codiceUnitaMisura;
    }

    /**
     * Imposta il valore della proprietÃ  codiceUnitaMisura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceUnitaMisura(String value) {
        this.codiceUnitaMisura = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceGruppo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceGruppo() {
        return codiceGruppo;
    }

    /**
     * Imposta il valore della proprietÃ  codiceGruppo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceGruppo(String value) {
        this.codiceGruppo = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceDescrittore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceDescrittore() {
        return codiceDescrittore;
    }

    /**
     * Imposta il valore della proprietÃ  codiceDescrittore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceDescrittore(String value) {
        this.codiceDescrittore = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceModalita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceModalita() {
        return codiceModalita;
    }

    /**
     * Imposta il valore della proprietÃ  codiceModalita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceModalita(String value) {
        this.codiceModalita = value;
    }

}
