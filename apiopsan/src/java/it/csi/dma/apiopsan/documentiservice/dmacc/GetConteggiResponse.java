/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.ServiceResponse;


/**
 * <p>Classe Java per GetConteggiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetConteggiResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroDocumentiTotali" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="etichettaToNumeroDocumenti" type="{http://dmacc.csi.it/}etichettaNumeroDocumenti" maxOccurs="unbounded" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numeroDocumentiSenzaEtichette" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numeroEpisodi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetConteggiResponse", propOrder = {
    "numeroDocumentiTotali",
    "etichettaToNumeroDocumenti",
    "numeroDocumentiSenzaEtichette",
    "numeroEpisodi"
})
public class GetConteggiResponse
    extends ServiceResponse
{

    protected Integer numeroDocumentiTotali;
    @XmlElement(nillable = true)
    protected List<EtichettaNumeroDocumenti> etichettaToNumeroDocumenti;
    protected Integer numeroDocumentiSenzaEtichette;
    protected Integer numeroEpisodi;

    /**
     * Recupera il valore della proprietÃ  numeroDocumentiTotali.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroDocumentiTotali() {
        return numeroDocumentiTotali;
    }

    /**
     * Imposta il valore della proprietÃ  numeroDocumentiTotali.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroDocumentiTotali(Integer value) {
        this.numeroDocumentiTotali = value;
    }

    /**
     * Gets the value of the etichettaToNumeroDocumenti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the etichettaToNumeroDocumenti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEtichettaToNumeroDocumenti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EtichettaNumeroDocumenti }
     * 
     * 
     */
    public List<EtichettaNumeroDocumenti> getEtichettaToNumeroDocumenti() {
        if (etichettaToNumeroDocumenti == null) {
            etichettaToNumeroDocumenti = new ArrayList<EtichettaNumeroDocumenti>();
        }
        return this.etichettaToNumeroDocumenti;
    }

    /**
     * Recupera il valore della proprietÃ  numeroDocumentiSenzaEtichette.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroDocumentiSenzaEtichette() {
        return numeroDocumentiSenzaEtichette;
    }

    /**
     * Imposta il valore della proprietÃ  numeroDocumentiSenzaEtichette.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroDocumentiSenzaEtichette(Integer value) {
        this.numeroDocumentiSenzaEtichette = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroEpisodi.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroEpisodi() {
        return numeroEpisodi;
    }

    /**
     * Imposta il valore della proprietÃ  numeroEpisodi.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroEpisodi(Integer value) {
        this.numeroEpisodi = value;
    }

}
