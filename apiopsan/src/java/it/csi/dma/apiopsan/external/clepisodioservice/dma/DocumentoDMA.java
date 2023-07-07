/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per documentoDMA complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="documentoDMA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accessionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="firmatoDigitalmente" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="idDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idEpisodio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="linkImmagini" type="{http://dma.csi.it/}linkImmagine" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="linkImmaginiStazioneRefertante" type="{http://dma.csi.it/}linkImmagineStazioneRefertante" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="oscurato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaNre" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoDMA", propOrder = {
    "accessionNumber",
    "documento",
    "firmatoDigitalmente",
    "idDipartimentale",
    "idDocumento",
    "idEpisodio",
    "linkImmagini",
    "linkImmaginiStazioneRefertante",
    "oscurato",
    "listaNre"
})
public class DocumentoDMA {

    protected String accessionNumber;
    protected byte[] documento;
    @XmlSchemaType(name = "string")
    protected SiNo firmatoDigitalmente;
    protected String idDipartimentale;
    protected String idDocumento;
    protected String idEpisodio;
    @XmlElement(nillable = true)
    protected List<LinkImmagine> linkImmagini;
    @XmlElement(nillable = true)
    protected List<LinkImmagineStazioneRefertante> linkImmaginiStazioneRefertante;
    protected String oscurato;
    @XmlElement(nillable = true)
    protected List<String> listaNre;

    /**
     * Recupera il valore della proprieta' accessionNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessionNumber() {
        return accessionNumber;
    }

    /**
     * Imposta il valore della proprieta' accessionNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessionNumber(String value) {
        this.accessionNumber = value;
    }

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
     * Recupera il valore della proprieta' firmatoDigitalmente.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getFirmatoDigitalmente() {
        return firmatoDigitalmente;
    }

    /**
     * Imposta il valore della proprieta' firmatoDigitalmente.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setFirmatoDigitalmente(SiNo value) {
        this.firmatoDigitalmente = value;
    }

    /**
     * Recupera il valore della proprieta' idDipartimentale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDipartimentale() {
        return idDipartimentale;
    }

    /**
     * Imposta il valore della proprieta' idDipartimentale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDipartimentale(String value) {
        this.idDipartimentale = value;
    }

    /**
     * Recupera il valore della proprieta' idDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumento() {
        return idDocumento;
    }

    /**
     * Imposta il valore della proprieta' idDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumento(String value) {
        this.idDocumento = value;
    }

    /**
     * Recupera il valore della proprieta' idEpisodio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEpisodio() {
        return idEpisodio;
    }

    /**
     * Imposta il valore della proprieta' idEpisodio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEpisodio(String value) {
        this.idEpisodio = value;
    }

    /**
     * Gets the value of the linkImmagini property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkImmagini property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkImmagini().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkImmagine }
     * 
     * 
     */
    public List<LinkImmagine> getLinkImmagini() {
        if (linkImmagini == null) {
            linkImmagini = new ArrayList<LinkImmagine>();
        }
        return this.linkImmagini;
    }

    /**
     * Gets the value of the linkImmaginiStazioneRefertante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkImmaginiStazioneRefertante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkImmaginiStazioneRefertante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinkImmagineStazioneRefertante }
     * 
     * 
     */
    public List<LinkImmagineStazioneRefertante> getLinkImmaginiStazioneRefertante() {
        if (linkImmaginiStazioneRefertante == null) {
            linkImmaginiStazioneRefertante = new ArrayList<LinkImmagineStazioneRefertante>();
        }
        return this.linkImmaginiStazioneRefertante;
    }

    /**
     * Recupera il valore della proprieta' oscurato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOscurato() {
        return oscurato;
    }

    /**
     * Imposta il valore della proprieta' oscurato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOscurato(String value) {
        this.oscurato = value;
    }

    /**
     * Gets the value of the listaNre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaNre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaNre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListaNre() {
        if (listaNre == null) {
            listaNre = new ArrayList<String>();
        }
        return this.listaNre;
    }

}
