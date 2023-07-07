/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.ComponenteLocaleRisposta;
import it.csi.dma.apiopsan.documentiservice.dma.ServiceResponse;


/**
 * <p>Classe Java per getDocumentoResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDocumentoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documento" type="{http://dmacc.csi.it/}documentoDMA" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="componenteLocaleRisposta" type="{http://dma.csi.it/}componenteLocaleRisposta" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="urlDocumentoDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="authDocumentoDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="documentoSuDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDocumentoResponse", propOrder = {
    "documento",
    "componenteLocaleRisposta",
    "urlDocumentoDipartimentale",
    "authDocumentoDipartimentale",
    "documentoSuDipartimentale"
})
public class GetDocumentoResponse
    extends ServiceResponse
{

    protected DocumentoDMA documento;
    protected ComponenteLocaleRisposta componenteLocaleRisposta;
    protected String urlDocumentoDipartimentale;
    protected String authDocumentoDipartimentale;
    protected String documentoSuDipartimentale;

    /**
     * Recupera il valore della proprietÃ  documento.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoDMA }
     *     
     */
    public DocumentoDMA getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprietÃ  documento.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoDMA }
     *     
     */
    public void setDocumento(DocumentoDMA value) {
        this.documento = value;
    }

    /**
     * Recupera il valore della proprietÃ  componenteLocaleRisposta.
     * 
     * @return
     *     possible object is
     *     {@link ComponenteLocaleRisposta }
     *     
     */
    public ComponenteLocaleRisposta getComponenteLocaleRisposta() {
        return componenteLocaleRisposta;
    }

    /**
     * Imposta il valore della proprietÃ  componenteLocaleRisposta.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponenteLocaleRisposta }
     *     
     */
    public void setComponenteLocaleRisposta(ComponenteLocaleRisposta value) {
        this.componenteLocaleRisposta = value;
    }

    /**
     * Recupera il valore della proprietÃ  urlDocumentoDipartimentale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlDocumentoDipartimentale() {
        return urlDocumentoDipartimentale;
    }

    /**
     * Imposta il valore della proprietÃ  urlDocumentoDipartimentale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlDocumentoDipartimentale(String value) {
        this.urlDocumentoDipartimentale = value;
    }

    /**
     * Recupera il valore della proprietÃ  authDocumentoDipartimentale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthDocumentoDipartimentale() {
        return authDocumentoDipartimentale;
    }

    /**
     * Imposta il valore della proprietÃ  authDocumentoDipartimentale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthDocumentoDipartimentale(String value) {
        this.authDocumentoDipartimentale = value;
    }

    /**
     * Recupera il valore della proprietÃ  documentoSuDipartimentale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoSuDipartimentale() {
        return documentoSuDipartimentale;
    }

    /**
     * Imposta il valore della proprietÃ  documentoSuDipartimentale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoSuDipartimentale(String value) {
        this.documentoSuDipartimentale = value;
    }

}
