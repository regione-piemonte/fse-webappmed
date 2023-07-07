/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per pazienteROPVA complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="pazienteROPVA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}paziente"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroSSO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rankAnagrafico" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pazienteROPVA", propOrder = {
    "numeroSSO",
    "rankAnagrafico"
})
public class PazienteROPVA
    extends Paziente
{

    protected String numeroSSO;
    protected int rankAnagrafico;

    /**
     * Recupera il valore della proprieta' numeroSSO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroSSO() {
        return numeroSSO;
    }

    /**
     * Imposta il valore della proprieta' numeroSSO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroSSO(String value) {
        this.numeroSSO = value;
    }

    /**
     * Recupera il valore della proprieta' rankAnagrafico.
     * 
     */
    public int getRankAnagrafico() {
        return rankAnagrafico;
    }

    /**
     * Imposta il valore della proprieta' rankAnagrafico.
     * 
     */
    public void setRankAnagrafico(int value) {
        this.rankAnagrafico = value;
    }

}
