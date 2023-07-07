/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.Etichetta;


/**
 * <p>Classe Java per etichettaNumeroDocumenti complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="etichettaNumeroDocumenti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="etichetta" type="{http://dma.csi.it/}etichetta" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numero_documenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "etichettaNumeroDocumenti", propOrder = {
    "etichetta",
    "numeroDocumenti"
})
public class EtichettaNumeroDocumenti {

    protected Etichetta etichetta;
    @XmlElement(name = "numero_documenti")
    protected Integer numeroDocumenti;

    /**
     * Recupera il valore della proprietÃ  etichetta.
     * 
     * @return
     *     possible object is
     *     {@link Etichetta }
     *     
     */
    public Etichetta getEtichetta() {
        return etichetta;
    }

    /**
     * Imposta il valore della proprietÃ  etichetta.
     * 
     * @param value
     *     allowed object is
     *     {@link Etichetta }
     *     
     */
    public void setEtichetta(Etichetta value) {
        this.etichetta = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroDocumenti.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroDocumenti() {
        return numeroDocumenti;
    }

    /**
     * Imposta il valore della proprietÃ  numeroDocumenti.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroDocumenti(Integer value) {
        this.numeroDocumenti = value;
    }

}
