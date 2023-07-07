/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.MetadatoRicercaType;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.OpzioniType;


/**
 * <p>Classe Java per ricercaDocumentiIniResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaDocumentiIniResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbl.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="metadato" type="{http://dma.csi.it/}metadatoRicercaType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="opzioniResponse" type="{http://dma.csi.it/}opzioniType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaDocumentiIniResponse", propOrder = {
    "metadato",
    "opzioniResponse"
})
public class RicercaDocumentiIniResponse
    extends ServiceResponse
{

    @XmlElement(nillable = true)
    protected List<MetadatoRicercaType> metadato;
    @XmlElement(nillable = true)
    protected List<OpzioniType> opzioniResponse;

    /**
     * Gets the value of the metadato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadatoRicercaType }
     * 
     * 
     */
    public List<MetadatoRicercaType> getMetadato() {
        if (metadato == null) {
            metadato = new ArrayList<MetadatoRicercaType>();
        }
        return this.metadato;
    }

    /**
     * Gets the value of the opzioniResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opzioniResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpzioniResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpzioniType }
     * 
     * 
     */
    public List<OpzioniType> getOpzioniResponse() {
        if (opzioniResponse == null) {
            opzioniResponse = new ArrayList<OpzioniType>();
        }
        return this.opzioniResponse;
    }

}
