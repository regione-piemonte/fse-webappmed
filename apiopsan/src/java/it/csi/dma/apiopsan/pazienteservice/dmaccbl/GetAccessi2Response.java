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
import it.csi.dma.apiopsan.pazienteservice.dma.AccessoConsultazione;
import it.csi.dma.apiopsan.pazienteservice.dma.AccessoOperazione;
import it.csi.dma.apiopsan.pazienteservice.dma.ServiceResponse;


/**
 * <p>Classe Java per GetAccessi2Response complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetAccessi2Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}consultazioni" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}operazioni" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAccessi2Response", propOrder = {
    "consultazioni",
    "operazioni"
})
public class GetAccessi2Response
    extends ServiceResponse
{

    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<AccessoConsultazione> consultazioni;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<AccessoOperazione> operazioni;

    /**
     * Gets the value of the consultazioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consultazioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsultazioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoConsultazione }
     * 
     * 
     */
    public List<AccessoConsultazione> getConsultazioni() {
        if (consultazioni == null) {
            consultazioni = new ArrayList<AccessoConsultazione>();
        }
        return this.consultazioni;
    }

    /**
     * Gets the value of the operazioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operazioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperazioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoOperazione }
     * 
     * 
     */
    public List<AccessoOperazione> getOperazioni() {
        if (operazioni == null) {
            operazioni = new ArrayList<AccessoOperazione>();
        }
        return this.operazioni;
    }

}
