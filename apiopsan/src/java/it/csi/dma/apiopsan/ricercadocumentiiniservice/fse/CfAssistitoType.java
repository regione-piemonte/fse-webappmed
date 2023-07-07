/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.ricercadocumentiiniservice.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per cfAssistitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="cfAssistitoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cf" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="attivo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cfAssistitoType", propOrder = {
    "cf",
    "attivo"
})
public class CfAssistitoType {

    @XmlElement(required = true)
    protected String cf;
    @XmlElement(required = true)
    protected String attivo;

    /**
     * Recupera il valore della proprietÃ  cf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCf() {
        return cf;
    }

    /**
     * Imposta il valore della proprietÃ  cf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCf(String value) {
        this.cf = value;
    }

    /**
     * Recupera il valore della proprietÃ  attivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttivo() {
        return attivo;
    }

    /**
     * Imposta il valore della proprietÃ  attivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttivo(String value) {
        this.attivo = value;
    }

}
