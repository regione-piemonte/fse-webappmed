/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.recuperadocumentoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per recuperoDocumentoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="recuperoDocumentoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmacc.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="recuperoDocumentoOUT" type="{http://dma.csi.it/}recuperoDocumentoOUT" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recuperoDocumentoResponse", namespace = "http://dmacc.csi.it/", propOrder = {
    "recuperoDocumentoOUT"
})
public class RecuperoDocumentoResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "")
    protected RecuperoDocumentoOUT recuperoDocumentoOUT;

    /**
     * Recupera il valore della proprietÃ  recuperoDocumentoOUT.
     * 
     * @return
     *     possible object is
     *     {@link RecuperoDocumentoOUT }
     *     
     */
    public RecuperoDocumentoOUT getRecuperoDocumentoOUT() {
        return recuperoDocumentoOUT;
    }

    /**
     * Imposta il valore della proprietÃ  recuperoDocumentoOUT.
     * 
     * @param value
     *     allowed object is
     *     {@link RecuperoDocumentoOUT }
     *     
     */
    public void setRecuperoDocumentoOUT(RecuperoDocumentoOUT value) {
        this.recuperoDocumentoOUT = value;
    }

}
