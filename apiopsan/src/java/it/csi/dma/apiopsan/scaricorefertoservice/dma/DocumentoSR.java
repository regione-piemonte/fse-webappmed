/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for documentoSR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentoSR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentoFirmato" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="documentoNonFirmato" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="metadatiDocumento" type="{http://dma.csi.it/}metadatiDocumentoSR" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoSR", propOrder = {
    "documentoFirmato",
    "documentoNonFirmato",
    "metadatiDocumento"
})
public class DocumentoSR {

    protected byte[] documentoFirmato;
    protected byte[] documentoNonFirmato;
    protected MetadatiDocumentoSR metadatiDocumento;

    /**
     * Gets the value of the documentoFirmato property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumentoFirmato() {
        return documentoFirmato;
    }

    /**
     * Sets the value of the documentoFirmato property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumentoFirmato(byte[] value) {
        this.documentoFirmato = ((byte[]) value);
    }

    /**
     * Gets the value of the documentoNonFirmato property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumentoNonFirmato() {
        return documentoNonFirmato;
    }

    /**
     * Sets the value of the documentoNonFirmato property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumentoNonFirmato(byte[] value) {
        this.documentoNonFirmato = ((byte[]) value);
    }

    /**
     * Gets the value of the metadatiDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link MetadatiDocumentoSR }
     *     
     */
    public MetadatiDocumentoSR getMetadatiDocumento() {
        return metadatiDocumento;
    }

    /**
     * Sets the value of the metadatiDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadatiDocumentoSR }
     *     
     */
    public void setMetadatiDocumento(MetadatiDocumentoSR value) {
        this.metadatiDocumento = value;
    }

}
