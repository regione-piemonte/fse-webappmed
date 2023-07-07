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

import it.csi.dma.apiopsan.scaricorefertoservice.dma.DocumentoSR;


/**
 * <p>Java class for addPageQRCodeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPageQRCodeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://dmaccbl.csi.it/}serviceResponse">
 *       &lt;sequence>
 *         &lt;element name="listaReferti" type="{http://dma.csi.it/}documentoSR" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPageQRCodeResponse", propOrder = {
    "listaReferti"
})
public class AddPageQRCodeResponse
    extends ServiceResponse
{

    @XmlElement(nillable = true)
    protected List<DocumentoSR> listaReferti;

    /**
     * Gets the value of the listaReferti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaReferti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaReferti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoSR }
     * 
     * 
     */
    public List<DocumentoSR> getListaReferti() {
        if (listaReferti == null) {
            listaReferti = new ArrayList<DocumentoSR>();
        }
        return this.listaReferti;
    }

}
