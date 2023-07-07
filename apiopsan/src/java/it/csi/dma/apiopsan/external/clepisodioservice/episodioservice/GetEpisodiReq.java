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
import javax.xml.datatype.XMLGregorianCalendar;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.ArrayIdIrecCFPaziente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Paziente;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.Richiedente;


/**
 * <p>Classe Java per GetEpisodiReq complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetEpisodiReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://EpisodioService.WS.DMACL.csi.it}Ens_Request"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dataFineRicerca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioRicerca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="paziente" type="{http://dma.csi.it/}paziente" minOccurs="0"/&gt;
 *         &lt;element name="listaArrayIdIrecCFPaziente" type="{http://dma.csi.it/}ArrayIdIrecCFPaziente" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="richiedente" type="{http://dma.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element name="timeoutChiamanteInSec" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="codiceAssettoOrganizzativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipologiaDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEpisodiReq", propOrder = {
    "dataFineRicerca",
    "dataInizioRicerca",
    "paziente",
    "listaArrayIdIrecCFPaziente",
    "richiedente",
    "timeoutChiamanteInSec",
    "codiceAssettoOrganizzativo",
    "codiceTipologiaDocumento"
})
public class GetEpisodiReq
    extends EnsRequest
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineRicerca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioRicerca;
    protected Paziente paziente;
    @XmlElement(nillable = true)
    protected List<ArrayIdIrecCFPaziente> listaArrayIdIrecCFPaziente;
    protected Richiedente richiedente;
    protected Long timeoutChiamanteInSec;
    protected String codiceAssettoOrganizzativo;
    protected String codiceTipologiaDocumento;

    /**
     * Recupera il valore della proprieta' dataFineRicerca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineRicerca() {
        return dataFineRicerca;
    }

    /**
     * Imposta il valore della proprieta' dataFineRicerca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineRicerca(XMLGregorianCalendar value) {
        this.dataFineRicerca = value;
    }

    /**
     * Recupera il valore della proprieta' dataInizioRicerca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioRicerca() {
        return dataInizioRicerca;
    }

    /**
     * Imposta il valore della proprieta' dataInizioRicerca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioRicerca(XMLGregorianCalendar value) {
        this.dataInizioRicerca = value;
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
     * Gets the value of the listaArrayIdIrecCFPaziente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaArrayIdIrecCFPaziente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaArrayIdIrecCFPaziente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayIdIrecCFPaziente }
     * 
     * 
     */
    public List<ArrayIdIrecCFPaziente> getListaArrayIdIrecCFPaziente() {
        if (listaArrayIdIrecCFPaziente == null) {
            listaArrayIdIrecCFPaziente = new ArrayList<ArrayIdIrecCFPaziente>();
        }
        return this.listaArrayIdIrecCFPaziente;
    }

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
     * Recupera il valore della proprieta' timeoutChiamanteInSec.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTimeoutChiamanteInSec() {
        return timeoutChiamanteInSec;
    }

    /**
     * Imposta il valore della proprieta' timeoutChiamanteInSec.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimeoutChiamanteInSec(Long value) {
        this.timeoutChiamanteInSec = value;
    }

    /**
     * Recupera il valore della proprieta' codiceAssettoOrganizzativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAssettoOrganizzativo() {
        return codiceAssettoOrganizzativo;
    }

    /**
     * Imposta il valore della proprieta' codiceAssettoOrganizzativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAssettoOrganizzativo(String value) {
        this.codiceAssettoOrganizzativo = value;
    }

    /**
     * Recupera il valore della proprieta' codiceTipologiaDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipologiaDocumento() {
        return codiceTipologiaDocumento;
    }

    /**
     * Imposta il valore della proprieta' codiceTipologiaDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipologiaDocumento(String value) {
        this.codiceTipologiaDocumento = value;
    }

}
