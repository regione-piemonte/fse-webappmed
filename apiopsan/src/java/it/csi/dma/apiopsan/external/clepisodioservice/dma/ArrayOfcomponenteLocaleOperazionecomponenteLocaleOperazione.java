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
 * <p>Classe Java per ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="componenteLocaleOperazione" type="{http://dma.csi.it/}componenteLocaleOperazione" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione", propOrder = {
    "componenteLocaleOperazione"
})
public class ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione {

    @XmlElement(nillable = true)
    protected List<ComponenteLocaleOperazione> componenteLocaleOperazione;

    /**
     * Gets the value of the componenteLocaleOperazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componenteLocaleOperazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponenteLocaleOperazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteLocaleOperazione }
     * 
     * 
     */
    public List<ComponenteLocaleOperazione> getComponenteLocaleOperazione() {
        if (componenteLocaleOperazione == null) {
            componenteLocaleOperazione = new ArrayList<ComponenteLocaleOperazione>();
        }
        return this.componenteLocaleOperazione;
    }

}
