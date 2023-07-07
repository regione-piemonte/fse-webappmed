/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.pazienteservice.dma.ComponenteLocale;


/**
 * <p>Classe Java per getCLDaInvocareResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getCLDaInvocareResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaccbl.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="componenteLocale" type="{http://dma.csi.it/}componenteLocale" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCLDaInvocareResponse", propOrder = {
    "componenteLocale"
})
public class GetCLDaInvocareResponse
    extends ServiceResponse
{

    @XmlElement(nillable = true)
    protected List<ComponenteLocale> componenteLocale;

    /**
     * Gets the value of the componenteLocale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componenteLocale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponenteLocale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteLocale }
     * 
     * 
     */
    public List<ComponenteLocale> getComponenteLocale() {
        if (componenteLocale == null) {
            componenteLocale = new ArrayList<ComponenteLocale>();
        }
        return this.componenteLocale;
    }

}
