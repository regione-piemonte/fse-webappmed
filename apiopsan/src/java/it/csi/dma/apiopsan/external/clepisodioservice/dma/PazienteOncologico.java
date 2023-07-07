/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per pazienteOncologico complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="pazienteOncologico"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}paziente"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroSSO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pazienteOncologico", propOrder = {
    "numeroSSO"
})
public class PazienteOncologico
    extends Paziente
{

    @XmlElement(required = true)
    protected String numeroSSO;

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

}
