/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.external.cldocumentiiniservice.dma.OpzioniType;


/**
 * <p>Classe Java per recuperoDocumentoIniResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="recuperoDocumentoIniResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbl.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="identificativoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoOrgDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoRepository" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nreList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="opzioniResponse" type="{http://dma.csi.it/}opzioniType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="tipoMime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recuperoDocumentoIniResponse", propOrder = {
    "documento",
    "idDocumento",
    "identificativoDocumento",
    "identificativoOrgDoc",
    "identificativoRepository",
    "nreList",
    "opzioniResponse",
    "tipoMime"
})
public class RecuperoDocumentoIniResponse
    extends ServiceResponse
{

    protected byte[] documento;
    protected Long idDocumento;
    protected String identificativoDocumento;
    protected String identificativoOrgDoc;
    protected String identificativoRepository;
    @XmlElement(nillable = true)
    protected List<String> nreList;
    @XmlElement(nillable = true)
    protected List<OpzioniType> opzioniResponse;
    protected String tipoMime;

    /**
     * Recupera il valore della proprieta' documento.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocumento() {
        return documento;
    }

    /**
     * Imposta il valore della proprieta' documento.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocumento(byte[] value) {
        this.documento = value;
    }

    /**
     * Recupera il valore della proprieta' idDocumento.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDocumento() {
        return idDocumento;
    }

    /**
     * Imposta il valore della proprieta' idDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDocumento(Long value) {
        this.idDocumento = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoDocumento() {
        return identificativoDocumento;
    }

    /**
     * Imposta il valore della proprieta' identificativoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoDocumento(String value) {
        this.identificativoDocumento = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoOrgDoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoOrgDoc() {
        return identificativoOrgDoc;
    }

    /**
     * Imposta il valore della proprieta' identificativoOrgDoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoOrgDoc(String value) {
        this.identificativoOrgDoc = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoRepository.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoRepository() {
        return identificativoRepository;
    }

    /**
     * Imposta il valore della proprieta' identificativoRepository.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoRepository(String value) {
        this.identificativoRepository = value;
    }

    /**
     * Gets the value of the nreList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nreList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNreList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNreList() {
        if (nreList == null) {
            nreList = new ArrayList<String>();
        }
        return this.nreList;
    }

    /**
     * Gets the value of the opzioniResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opzioniResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpzioniResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OpzioniType }
     * 
     * 
     */
    public List<OpzioniType> getOpzioniResponse() {
        if (opzioniResponse == null) {
            opzioniResponse = new ArrayList<OpzioniType>();
        }
        return this.opzioniResponse;
    }

    /**
     * Recupera il valore della proprieta' tipoMime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMime() {
        return tipoMime;
    }

    /**
     * Imposta il valore della proprieta' tipoMime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMime(String value) {
        this.tipoMime = value;
    }

}
