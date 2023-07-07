/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.ServiceResponse;


/**
 * <p>Classe Java per getDettaglioDocumentoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDettaglioDocumentoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prestazioni" type="{http://dmaclbl.csi.it/}prestazioni" minOccurs="0"/&gt;
 *         &lt;element name="infoAggiuntive" type="{http://dmaclbl.csi.it/}infoAggiuntive" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDettaglioDocumentoResponse", propOrder = {
    "prestazioni",
    "infoAggiuntive"
})
public class GetDettaglioDocumentoResponse
    extends ServiceResponse
{

    protected Prestazioni prestazioni;
    @XmlElement(nillable = true)
    protected List<InfoAggiuntive> infoAggiuntive;

    /**
     * Recupera il valore della proprieta' prestazioni.
     * 
     * @return
     *     possible object is
     *     {@link Prestazioni }
     *     
     */
    public Prestazioni getPrestazioni() {
        return prestazioni;
    }

    /**
     * Imposta il valore della proprieta' prestazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link Prestazioni }
     *     
     */
    public void setPrestazioni(Prestazioni value) {
        this.prestazioni = value;
    }

    /**
     * Gets the value of the infoAggiuntive property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infoAggiuntive property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfoAggiuntive().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoAggiuntive }
     * 
     * 
     */
    public List<InfoAggiuntive> getInfoAggiuntive() {
        if (infoAggiuntive == null) {
            infoAggiuntive = new ArrayList<InfoAggiuntive>();
        }
        return this.infoAggiuntive;
    }

}
