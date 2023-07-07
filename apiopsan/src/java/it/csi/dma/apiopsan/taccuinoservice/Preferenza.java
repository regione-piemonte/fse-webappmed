/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per preferenza complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="preferenza"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="visibile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="entita" type="{http://dmaclbluc.dma.csi.it/}entita" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="gruppo" type="{http://dmaclbluc.dma.csi.it/}gruppo" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preferenza", propOrder = {
    "id",
    "visibile",
    "entita",
    "gruppo"
})
public class Preferenza {

    protected Long id;
    protected String visibile;
    protected Entita entita;
    protected Gruppo gruppo;

    /**
     * Recupera il valore della proprietÃ  id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietÃ  id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietÃ  visibile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisibile() {
        return visibile;
    }

    /**
     * Imposta il valore della proprietÃ  visibile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisibile(String value) {
        this.visibile = value;
    }

    /**
     * Recupera il valore della proprietÃ  entita.
     * 
     * @return
     *     possible object is
     *     {@link Entita }
     *     
     */
    public Entita getEntita() {
        return entita;
    }

    /**
     * Imposta il valore della proprietÃ  entita.
     * 
     * @param value
     *     allowed object is
     *     {@link Entita }
     *     
     */
    public void setEntita(Entita value) {
        this.entita = value;
    }

    /**
     * Recupera il valore della proprietÃ  gruppo.
     * 
     * @return
     *     possible object is
     *     {@link Gruppo }
     *     
     */
    public Gruppo getGruppo() {
        return gruppo;
    }

    /**
     * Imposta il valore della proprietÃ  gruppo.
     * 
     * @param value
     *     allowed object is
     *     {@link Gruppo }
     *     
     */
    public void setGruppo(Gruppo value) {
        this.gruppo = value;
    }

}
