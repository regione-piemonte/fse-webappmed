/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.ElencoCFAssistitoType;
import it.csi.dma.apiopsan.pazienteservice.dma.Paziente;
import it.csi.dma.apiopsan.pazienteservice.dma.Richiedente;


/**
 * <p>Classe Java per alimentazioneElencoCodiciFiscali complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="alimentazioneElencoCodiciFiscali"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}listaElencoCodiciFiscali" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alimentazioneElencoCodiciFiscali", propOrder = {
    "listaElencoCodiciFiscali",
    "paziente",
    "richiedente"
})
public class AlimentazioneElencoCodiciFiscali {

    @XmlElement(namespace = "http://dma.csi.it/")
    protected ElencoCFAssistitoType listaElencoCodiciFiscali;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Paziente paziente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected Richiedente richiedente;

    /**
     * Recupera il valore della proprietÃ  listaElencoCodiciFiscali.
     * 
     * @return
     *     possible object is
     *     {@link ElencoCFAssistitoType }
     *     
     */
    public ElencoCFAssistitoType getListaElencoCodiciFiscali() {
        return listaElencoCodiciFiscali;
    }

    /**
     * Imposta il valore della proprietÃ  listaElencoCodiciFiscali.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoCFAssistitoType }
     *     
     */
    public void setListaElencoCodiciFiscali(ElencoCFAssistitoType value) {
        this.listaElencoCodiciFiscali = value;
    }

    /**
     * Recupera il valore della proprietÃ  paziente.
     * 
     * @return
     *     possible object is
     *     {@link Paziente }
     *     
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     * Imposta il valore della proprietÃ  paziente.
     * 
     * @param value
     *     allowed object is
     *     {@link Paziente }
     *     
     */
    public void setPaziente(Paziente value) {
        this.paziente = value;
    }

    /**
     * Recupera il valore della proprietÃ  richiedente.
     * 
     * @return
     *     possible object is
     *     {@link Richiedente }
     *     
     */
    public Richiedente getRichiedente() {
        return richiedente;
    }

    /**
     * Imposta il valore della proprietÃ  richiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiedente }
     *     
     */
    public void setRichiedente(Richiedente value) {
        this.richiedente = value;
    }

}
