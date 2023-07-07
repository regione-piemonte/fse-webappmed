/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="linkImmagineStazioneRefertante" type="{http://dma.csi.it/}linkImmagineStazioneRefertante" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante", propOrder = {
    "linkImmagineStazioneRefertante"
})
public class ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante {

    @XmlElement(nillable = true)
    protected List<LinkImmagineStazioneRefertante> linkImmagineStazioneRefertante;

    /**
     * Gets the value of the linkImmagineStazioneRefertante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkImmagineStazioneRefertante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkImmagineStazioneRefertante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkImmagineStazioneRefertante }
     * 
     * 
     */
    public List<LinkImmagineStazioneRefertante> getLinkImmagineStazioneRefertante() {
        if (linkImmagineStazioneRefertante == null) {
            linkImmagineStazioneRefertante = new ArrayList<LinkImmagineStazioneRefertante>();
        }
        return this.linkImmagineStazioneRefertante;
    }

}
