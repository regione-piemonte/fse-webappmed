/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.episodioservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.MetadatiDocumento;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Paziente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Richiedente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.SiNo;


/**
 * <p>Classe Java per GetDocumentoReq complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetDocumentoReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://EpisodioService.WS.DMACL.csi.it}Ens_Request"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element name="paziente" type="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element name="metadatiDocumento" type="{http://dma.csi.it/}metadatiDocumento" minOccurs="0"/&gt;
 *         &lt;element name="firmatoDigitalmente" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="criptato" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="storicoPaziente" type="{http://dma.csi.it/}paziente" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetDocumentoReq", propOrder = {
    "richiedente",
    "paziente",
    "metadatiDocumento",
    "firmatoDigitalmente",
    "criptato",
    "storicoPaziente"
})
public class GetDocumentoReq
    extends EnsRequest
{

    protected Richiedente richiedente;
    protected Paziente paziente;
    protected MetadatiDocumento metadatiDocumento;
    @XmlSchemaType(name = "string")
    protected SiNo firmatoDigitalmente;
    @XmlSchemaType(name = "string")
    protected SiNo criptato;
    @XmlElement(nillable = true)
    protected List<Paziente> storicoPaziente;

    /**
     * Recupera il valore della proprieta' richiedente.
     * 
     * @return
     *     possible object is
     *     {@link Richiedente }
     *     
     */
    public Richiedente getRichiedente() {
        return richiedente;
    }

    /**
     * Imposta il valore della proprieta' richiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link Richiedente }
     *     
     */
    public void setRichiedente(Richiedente value) {
        this.richiedente = value;
    }

    /**
     * Recupera il valore della proprieta' paziente.
     * 
     * @return
     *     possible object is
     *     {@link Paziente }
     *     
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     * Imposta il valore della proprieta' paziente.
     * 
     * @param value
     *     allowed object is
     *     {@link Paziente }
     *     
     */
    public void setPaziente(Paziente value) {
        this.paziente = value;
    }

    /**
     * Recupera il valore della proprieta' metadatiDocumento.
     * 
     * @return
     *     possible object is
     *     {@link MetadatiDocumento }
     *     
     */
    public MetadatiDocumento getMetadatiDocumento() {
        return metadatiDocumento;
    }

    /**
     * Imposta il valore della proprieta' metadatiDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link MetadatiDocumento }
     *     
     */
    public void setMetadatiDocumento(MetadatiDocumento value) {
        this.metadatiDocumento = value;
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
     * Recupera il valore della proprieta' criptato.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getCriptato() {
        return criptato;
    }

    /**
     * Imposta il valore della proprieta' criptato.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setCriptato(SiNo value) {
        this.criptato = value;
    }

    /**
     * Gets the value of the storicoPaziente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storicoPaziente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoricoPaziente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Paziente }
     * 
     * 
     */
    public List<Paziente> getStoricoPaziente() {
        if (storicoPaziente == null) {
            storicoPaziente = new ArrayList<Paziente>();
        }
        return this.storicoPaziente;
    }

}
