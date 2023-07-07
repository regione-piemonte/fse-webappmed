/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per presenzaDatiILECRuolo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="presenzaDatiILECRuolo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceRuolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numDocumenti" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="numEpisodi" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "presenzaDatiILECRuolo", propOrder = {
    "codiceRuolo",
    "numDocumenti",
    "numEpisodi"
})
public class PresenzaDatiILECRuolo {

    protected String codiceRuolo;
    protected int numDocumenti;
    protected int numEpisodi;

    /**
     * Recupera il valore della proprietÃ  codiceRuolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRuolo() {
        return codiceRuolo;
    }

    /**
     * Imposta il valore della proprietÃ  codiceRuolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRuolo(String value) {
        this.codiceRuolo = value;
    }

    /**
     * Recupera il valore della proprietÃ  numDocumenti.
     * 
     */
    public int getNumDocumenti() {
        return numDocumenti;
    }

    /**
     * Imposta il valore della proprietÃ  numDocumenti.
     * 
     */
    public void setNumDocumenti(int value) {
        this.numDocumenti = value;
    }

    /**
     * Recupera il valore della proprietÃ  numEpisodi.
     * 
     */
    public int getNumEpisodi() {
        return numEpisodi;
    }

    /**
     * Imposta il valore della proprietÃ  numEpisodi.
     * 
     */
    public void setNumEpisodi(int value) {
        this.numEpisodi = value;
    }

}
