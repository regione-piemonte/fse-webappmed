/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.documentopersonaleservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java per getDocumentoPersonaleResponse complex type.
 * 
 * &lt;p&gt;Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="getDocumentoPersonaleResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;extension base="{http://dma.csi.it/}serviceResponse"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="documento" type="{http://dma.csi.it/}documentoPersonale" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/extension&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDocumentoPersonaleResponse", propOrder = {
    "documento"
})
public class GetDocumentoPersonaleResponse
    extends ServiceResponse
{

    protected DocumentoPersonale documento;

    /**
     * Recupera il valore della proprieta' documento.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoPersonale }
     *     
     */
    public DocumentoPersonale getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprieta' documento.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoPersonale }
     *     
     */
    public void setDocumento(DocumentoPersonale value) {
        this.documento = value;
    }

}
