/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.verificaservices.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.dma.apiopsan.verificaservices.dma.DatiDocumentoResponse;
import it.csi.dma.apiopsan.verificaservices.dma.ServiceResponse;


/**
 * <p>Classe Java per verificaPinResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="verificaPinResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datiDocumentoResponse" type="{http://dma.csi.it/}datiDocumentoResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificaPinResponse", propOrder = {
    "datiDocumentoResponse"
})
public class VerificaPinResponse
    extends ServiceResponse
{

    protected DatiDocumentoResponse datiDocumentoResponse;

    /**
     * Recupera il valore della proprietÃ  datiDocumentoResponse.
     * 
     * @return
     *     possible object is
     *     {@link DatiDocumentoResponse }
     *     
     */
    public DatiDocumentoResponse getDatiDocumentoResponse() {
        return datiDocumentoResponse;
    }

    /**
     * Imposta il valore della proprietÃ  datiDocumentoResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiDocumentoResponse }
     *     
     */
    public void setDatiDocumentoResponse(DatiDocumentoResponse value) {
        this.datiDocumentoResponse = value;
    }

}
