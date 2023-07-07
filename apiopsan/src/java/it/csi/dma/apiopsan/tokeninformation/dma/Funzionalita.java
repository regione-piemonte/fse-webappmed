/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.tokeninformation.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per funzionalita complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="funzionalita"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceFunzionalita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceFunzionalitaPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneFunzionalita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneFunzionalitaPadre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "funzionalita", propOrder = {
    "codiceFunzionalita",
    "codiceFunzionalitaPadre",
    "descrizioneFunzionalita",
    "descrizioneFunzionalitaPadre"
})
public class Funzionalita {

    protected String codiceFunzionalita;
    protected String codiceFunzionalitaPadre;
    protected String descrizioneFunzionalita;
    protected String descrizioneFunzionalitaPadre;

    /**
     * Recupera il valore della proprietÃ  codiceFunzionalita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFunzionalita() {
        return codiceFunzionalita;
    }

    /**
     * Imposta il valore della proprietÃ  codiceFunzionalita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFunzionalita(String value) {
        this.codiceFunzionalita = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceFunzionalitaPadre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFunzionalitaPadre() {
        return codiceFunzionalitaPadre;
    }

    /**
     * Imposta il valore della proprietÃ  codiceFunzionalitaPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFunzionalitaPadre(String value) {
        this.codiceFunzionalitaPadre = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneFunzionalita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneFunzionalita() {
        return descrizioneFunzionalita;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneFunzionalita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneFunzionalita(String value) {
        this.descrizioneFunzionalita = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneFunzionalitaPadre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneFunzionalitaPadre() {
        return descrizioneFunzionalitaPadre;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneFunzionalitaPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneFunzionalitaPadre(String value) {
        this.descrizioneFunzionalitaPadre = value;
    }

}
