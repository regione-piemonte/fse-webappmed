/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per etichetta complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="etichetta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idEtichetta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="testo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="tipoEtichetta" type="{http://dma.csi.it/}tipoEtichetta" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "etichetta", propOrder = {
    "idEtichetta",
    "testo",
    "tipoEtichetta"
})
public class Etichetta {

    @XmlElement(namespace = "")
    protected Long idEtichetta;
    @XmlElement(namespace = "")
    protected String testo;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "string")
    protected TipoEtichetta tipoEtichetta;

    /**
     * Recupera il valore della proprietÃ  idEtichetta.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEtichetta() {
        return idEtichetta;
    }

    /**
     * Imposta il valore della proprietÃ  idEtichetta.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEtichetta(Long value) {
        this.idEtichetta = value;
    }

    /**
     * Recupera il valore della proprietÃ  testo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Imposta il valore della proprietÃ  testo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTesto(String value) {
        this.testo = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoEtichetta.
     * 
     * @return
     *     possible object is
     *     {@link TipoEtichetta }
     *     
     */
    public TipoEtichetta getTipoEtichetta() {
        return tipoEtichetta;
    }

    /**
     * Imposta il valore della proprietÃ  tipoEtichetta.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEtichetta }
     *     
     */
    public void setTipoEtichetta(TipoEtichetta value) {
        this.tipoEtichetta = value;
    }

}
