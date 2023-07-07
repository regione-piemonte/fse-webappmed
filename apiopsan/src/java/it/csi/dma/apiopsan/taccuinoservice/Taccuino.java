/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per taccuino complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="taccuino"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="oscurato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="notaGenerale" type="{http://dmaclbluc.dma.csi.it/}notaGenerale" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="listaPreferenze" type="{http://dmaclbluc.dma.csi.it/}listaPreferenze" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taccuino", propOrder = {
    "id",
    "oscurato",
    "notaGenerale",
    "listaPreferenze"
})
public class Taccuino {

    protected Long id;
    protected String oscurato;
    protected NotaGenerale notaGenerale;
    protected ListaPreferenze listaPreferenze;

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
     * Recupera il valore della proprietÃ  oscurato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOscurato() {
        return oscurato;
    }

    /**
     * Imposta il valore della proprietÃ  oscurato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOscurato(String value) {
        this.oscurato = value;
    }

    /**
     * Recupera il valore della proprietÃ  notaGenerale.
     * 
     * @return
     *     possible object is
     *     {@link NotaGenerale }
     *     
     */
    public NotaGenerale getNotaGenerale() {
        return notaGenerale;
    }

    /**
     * Imposta il valore della proprietÃ  notaGenerale.
     * 
     * @param value
     *     allowed object is
     *     {@link NotaGenerale }
     *     
     */
    public void setNotaGenerale(NotaGenerale value) {
        this.notaGenerale = value;
    }

    /**
     * Recupera il valore della proprietÃ  listaPreferenze.
     * 
     * @return
     *     possible object is
     *     {@link ListaPreferenze }
     *     
     */
    public ListaPreferenze getListaPreferenze() {
        return listaPreferenze;
    }

    /**
     * Imposta il valore della proprietÃ  listaPreferenze.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaPreferenze }
     *     
     */
    public void setListaPreferenze(ListaPreferenze value) {
        this.listaPreferenze = value;
    }

}
