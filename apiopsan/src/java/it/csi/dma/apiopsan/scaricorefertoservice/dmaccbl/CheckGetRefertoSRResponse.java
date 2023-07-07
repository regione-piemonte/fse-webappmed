/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.dma.apiopsan.scaricorefertoservice.dma.ComponenteLocale;


/**
 * <p>Java class for checkGetRefertoSRResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkGetRefertoSRResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://dmaccbl.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="componentiLocali" type="{http://dma.csi.it/}componenteLocale" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkGetRefertoSRResponse", propOrder = {
    "componentiLocali"
})
public class CheckGetRefertoSRResponse
    extends ServiceResponse
{

    @XmlElement(nillable = true)
    protected List<ComponenteLocale> componentiLocali;

    /**
     * Gets the value of the componentiLocali property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componentiLocali property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponentiLocali().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteLocale }
     * 
     * 
     */
    public List<ComponenteLocale> getComponentiLocali() {
        if (componentiLocali == null) {
            componentiLocali = new ArrayList<ComponenteLocale>();
        }
        return this.componentiLocali;
    }

}
