/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.ServiceResponse;


/**
 * <p>Classe Java per GetDettaglioDocumentoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDettaglioDocumentoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documento" type="{http://dmacc.csi.it/}documento" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="documentoAreaRossa" type="{http://dmacc.csi.it/}documentoAreaRossa" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDettaglioDocumentoResponse", propOrder = {
    "documento",
    "documentoAreaRossa"
})
public class GetDettaglioDocumentoResponse
    extends ServiceResponse
{

    protected Documento documento;
    protected DocumentoAreaRossa documentoAreaRossa;

    /**
     * Recupera il valore della proprietÃ  documento.
     * 
     * @return
     *     possible object is
     *     {@link Documento }
     *     
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprietÃ  documento.
     * 
     * @param value
     *     allowed object is
     *     {@link Documento }
     *     
     */
    public void setDocumento(Documento value) {
        this.documento = value;
    }

    /**
     * Recupera il valore della proprietÃ  documentoAreaRossa.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoAreaRossa }
     *     
     */
    public DocumentoAreaRossa getDocumentoAreaRossa() {
        return documentoAreaRossa;
    }

    /**
     * Imposta il valore della proprietÃ  documentoAreaRossa.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoAreaRossa }
     *     
     */
    public void setDocumentoAreaRossa(DocumentoAreaRossa value) {
        this.documentoAreaRossa = value;
    }

}
