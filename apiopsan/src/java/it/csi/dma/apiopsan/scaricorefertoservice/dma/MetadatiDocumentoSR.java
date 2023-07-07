/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for metadatiDocumentoSR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="metadatiDocumentoSR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceDocumentoDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceDocumentoScaricabile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="componenteLocale" type="{http://dma.csi.it/}componenteLocale" minOccurs="0"/>
 *         &lt;element name="dataEpisodio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataValidazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="firmatoDigitalmente" type="{http://dma.csi.it/}siNo" minOccurs="0"/>
 *         &lt;element name="flagFirmatoDigitalmente" type="{http://dma.csi.it/}siNo" minOccurs="0"/>
 *         &lt;element name="idDocumentoCL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="luogoProduzioneDocumento" type="{http://dma.csi.it/}luogoASU" minOccurs="0"/>
 *         &lt;element name="medicoValidante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDiDownload" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroGiorniDownload" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pagatoTicket" type="{http://dma.csi.it/}pagatoTicketStato" minOccurs="0"/>
 *         &lt;element ref="{http://dma.csi.it/}paziente" minOccurs="0"/>
 *         &lt;element name="pinBruciato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="richiestoPinCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="scaricabileSeTicketNonPagato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="soggettoALeggiSpeciali" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoDocumento" type="{http://dma.csi.it/}tipoDocumento" minOccurs="0"/>
 *         &lt;element name="tipoFileDocumento" type="{http://dma.csi.it/}tipoFileDocumento" minOccurs="0"/>
 *         &lt;element name="oscuraScaricoCittadino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scaricabileFinoAl" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAggiornamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataAnnullamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataCancellazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="accessionNumbers" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codiceEpisodioDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codicePazienteDipartimentale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataRefertazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="medicoRefertante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroVolteScaricato" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoReferto" type="{http://dma.csi.it/}tipoReferto" minOccurs="0"/>
 *         &lt;element name="ticketPagato" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="ticketDaPagare" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="dataDisponibilitaReferto" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInserimentoROL" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataScadenza" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tipoAssociazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idDocumentoParent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoReferto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataSostituzione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="elencoPrestazioni" type="{http://dma.csi.it/}prestazione" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="elencoNre" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="idDocumentoFascicolo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEpisodioDocumentoFascicolo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadatiDocumentoSR", propOrder = {
    "accessionNumber",
    "codiceDocumentoDipartimentale",
    "codiceDocumentoScaricabile",
    "componenteLocale",
    "dataEpisodio",
    "dataValidazione",
    "firmatoDigitalmente",
    "flagFirmatoDigitalmente",
    "idDocumentoCL",
    "luogoProduzioneDocumento",
    "medicoValidante",
    "numDiDownload",
    "numeroGiorniDownload",
    "pagatoTicket",
    "paziente",
    "pinBruciato",
    "richiestoPinCode",
    "scaricabileSeTicketNonPagato",
    "soggettoALeggiSpeciali",
    "tipoDocumento",
    "tipoFileDocumento",
    "oscuraScaricoCittadino",
    "scaricabileFinoAl",
    "dataInserimento",
    "dataAggiornamento",
    "dataAnnullamento",
    "dataCancellazione",
    "accessionNumbers",
    "codiceEpisodioDipartimentale",
    "codicePazienteDipartimentale",
    "dataRefertazione",
    "medicoRefertante",
    "numeroVolteScaricato",
    "tipoReferto",
    "ticketPagato",
    "ticketDaPagare",
    "dataDisponibilitaReferto",
    "dataInserimentoROL",
    "dataScadenza",
    "tipoAssociazione",
    "idDocumentoParent",
    "statoReferto",
    "dataSostituzione",
    "elencoPrestazioni",
    "elencoNre",
    "idDocumentoFascicolo",
    "idEpisodioDocumentoFascicolo"
})
public class MetadatiDocumentoSR {

    protected String accessionNumber;
    protected String codiceDocumentoDipartimentale;
    protected String codiceDocumentoScaricabile;
    protected ComponenteLocale componenteLocale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEpisodio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataValidazione;
    protected SiNo firmatoDigitalmente;
    protected SiNo flagFirmatoDigitalmente;
    protected String idDocumentoCL;
    protected LuogoASU luogoProduzioneDocumento;
    protected String medicoValidante;
    protected int numDiDownload;
    protected int numeroGiorniDownload;
    protected PagatoTicketStato pagatoTicket;
    protected Paziente paziente;
    protected boolean pinBruciato;
    protected boolean richiestoPinCode;
    protected boolean scaricabileSeTicketNonPagato;
    protected boolean soggettoALeggiSpeciali;
    protected TipoDocumento tipoDocumento;
    protected TipoFileDocumento tipoFileDocumento;
    protected String oscuraScaricoCittadino;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scaricabileFinoAl;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAggiornamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAnnullamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCancellazione;
    @XmlElement(nillable = true)
    protected List<String> accessionNumbers;
    protected String codiceEpisodioDipartimentale;
    protected String codicePazienteDipartimentale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRefertazione;
    protected String medicoRefertante;
    protected int numeroVolteScaricato;
    protected TipoReferto tipoReferto;
    protected Float ticketPagato;
    protected Float ticketDaPagare;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDisponibilitaReferto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimentoROL;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataScadenza;
    protected String tipoAssociazione;
    protected String idDocumentoParent;
    protected String statoReferto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSostituzione;
    @XmlElement(nillable = true)
    protected List<Prestazione> elencoPrestazioni;
    @XmlElement(nillable = true)
    protected List<String> elencoNre;
    protected Long idDocumentoFascicolo;
    protected Long idEpisodioDocumentoFascicolo;

    /**
     * Gets the value of the accessionNumber property.
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
     * Sets the value of the accessionNumber property.
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
     * Gets the value of the codiceDocumentoDipartimentale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceDocumentoDipartimentale() {
        return codiceDocumentoDipartimentale;
    }

    /**
     * Sets the value of the codiceDocumentoDipartimentale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceDocumentoDipartimentale(String value) {
        this.codiceDocumentoDipartimentale = value;
    }

    /**
     * Gets the value of the codiceDocumentoScaricabile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceDocumentoScaricabile() {
        return codiceDocumentoScaricabile;
    }

    /**
     * Sets the value of the codiceDocumentoScaricabile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceDocumentoScaricabile(String value) {
        this.codiceDocumentoScaricabile = value;
    }

    /**
     * Gets the value of the componenteLocale property.
     * 
     * @return
     *     possible object is
     *     {@link ComponenteLocale }
     *     
     */
    public ComponenteLocale getComponenteLocale() {
        return componenteLocale;
    }

    /**
     * Sets the value of the componenteLocale property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponenteLocale }
     *     
     */
    public void setComponenteLocale(ComponenteLocale value) {
        this.componenteLocale = value;
    }

    /**
     * Gets the value of the dataEpisodio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEpisodio() {
        return dataEpisodio;
    }

    /**
     * Sets the value of the dataEpisodio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEpisodio(XMLGregorianCalendar value) {
        this.dataEpisodio = value;
    }

    /**
     * Gets the value of the dataValidazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataValidazione() {
        return dataValidazione;
    }

    /**
     * Sets the value of the dataValidazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataValidazione(XMLGregorianCalendar value) {
        this.dataValidazione = value;
    }

    /**
     * Gets the value of the firmatoDigitalmente property.
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
     * Sets the value of the firmatoDigitalmente property.
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
     * Gets the value of the flagFirmatoDigitalmente property.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getFlagFirmatoDigitalmente() {
        return flagFirmatoDigitalmente;
    }

    /**
     * Sets the value of the flagFirmatoDigitalmente property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setFlagFirmatoDigitalmente(SiNo value) {
        this.flagFirmatoDigitalmente = value;
    }

    /**
     * Gets the value of the idDocumentoCL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumentoCL() {
        return idDocumentoCL;
    }

    /**
     * Sets the value of the idDocumentoCL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumentoCL(String value) {
        this.idDocumentoCL = value;
    }

    /**
     * Gets the value of the luogoProduzioneDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link LuogoASU }
     *     
     */
    public LuogoASU getLuogoProduzioneDocumento() {
        return luogoProduzioneDocumento;
    }

    /**
     * Sets the value of the luogoProduzioneDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link LuogoASU }
     *     
     */
    public void setLuogoProduzioneDocumento(LuogoASU value) {
        this.luogoProduzioneDocumento = value;
    }

    /**
     * Gets the value of the medicoValidante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicoValidante() {
        return medicoValidante;
    }

    /**
     * Sets the value of the medicoValidante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicoValidante(String value) {
        this.medicoValidante = value;
    }

    /**
     * Gets the value of the numDiDownload property.
     * 
     */
    public int getNumDiDownload() {
        return numDiDownload;
    }

    /**
     * Sets the value of the numDiDownload property.
     * 
     */
    public void setNumDiDownload(int value) {
        this.numDiDownload = value;
    }

    /**
     * Gets the value of the numeroGiorniDownload property.
     * 
     */
    public int getNumeroGiorniDownload() {
        return numeroGiorniDownload;
    }

    /**
     * Sets the value of the numeroGiorniDownload property.
     * 
     */
    public void setNumeroGiorniDownload(int value) {
        this.numeroGiorniDownload = value;
    }

    /**
     * Gets the value of the pagatoTicket property.
     * 
     * @return
     *     possible object is
     *     {@link PagatoTicketStato }
     *     
     */
    public PagatoTicketStato getPagatoTicket() {
        return pagatoTicket;
    }

    /**
     * Sets the value of the pagatoTicket property.
     * 
     * @param value
     *     allowed object is
     *     {@link PagatoTicketStato }
     *     
     */
    public void setPagatoTicket(PagatoTicketStato value) {
        this.pagatoTicket = value;
    }

    /**
     * Gets the value of the paziente property.
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
     * Sets the value of the paziente property.
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
     * Gets the value of the pinBruciato property.
     * 
     */
    public boolean isPinBruciato() {
        return pinBruciato;
    }

    /**
     * Sets the value of the pinBruciato property.
     * 
     */
    public void setPinBruciato(boolean value) {
        this.pinBruciato = value;
    }

    /**
     * Gets the value of the richiestoPinCode property.
     * 
     */
    public boolean isRichiestoPinCode() {
        return richiestoPinCode;
    }

    /**
     * Sets the value of the richiestoPinCode property.
     * 
     */
    public void setRichiestoPinCode(boolean value) {
        this.richiestoPinCode = value;
    }

    /**
     * Gets the value of the scaricabileSeTicketNonPagato property.
     * 
     */
    public boolean isScaricabileSeTicketNonPagato() {
        return scaricabileSeTicketNonPagato;
    }

    /**
     * Sets the value of the scaricabileSeTicketNonPagato property.
     * 
     */
    public void setScaricabileSeTicketNonPagato(boolean value) {
        this.scaricabileSeTicketNonPagato = value;
    }

    /**
     * Gets the value of the soggettoALeggiSpeciali property.
     * 
     */
    public boolean isSoggettoALeggiSpeciali() {
        return soggettoALeggiSpeciali;
    }

    /**
     * Sets the value of the soggettoALeggiSpeciali property.
     * 
     */
    public void setSoggettoALeggiSpeciali(boolean value) {
        this.soggettoALeggiSpeciali = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumento }
     *     
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumento }
     *     
     */
    public void setTipoDocumento(TipoDocumento value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the tipoFileDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoFileDocumento }
     *     
     */
    public TipoFileDocumento getTipoFileDocumento() {
        return tipoFileDocumento;
    }

    /**
     * Sets the value of the tipoFileDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFileDocumento }
     *     
     */
    public void setTipoFileDocumento(TipoFileDocumento value) {
        this.tipoFileDocumento = value;
    }

    /**
     * Gets the value of the oscuraScaricoCittadino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOscuraScaricoCittadino() {
        return oscuraScaricoCittadino;
    }

    /**
     * Sets the value of the oscuraScaricoCittadino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOscuraScaricoCittadino(String value) {
        this.oscuraScaricoCittadino = value;
    }

    /**
     * Gets the value of the scaricabileFinoAl property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScaricabileFinoAl() {
        return scaricabileFinoAl;
    }

    /**
     * Sets the value of the scaricabileFinoAl property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScaricabileFinoAl(XMLGregorianCalendar value) {
        this.scaricabileFinoAl = value;
    }

    /**
     * Gets the value of the dataInserimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimento() {
        return dataInserimento;
    }

    /**
     * Sets the value of the dataInserimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimento(XMLGregorianCalendar value) {
        this.dataInserimento = value;
    }

    /**
     * Gets the value of the dataAggiornamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAggiornamento() {
        return dataAggiornamento;
    }

    /**
     * Sets the value of the dataAggiornamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAggiornamento(XMLGregorianCalendar value) {
        this.dataAggiornamento = value;
    }

    /**
     * Gets the value of the dataAnnullamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAnnullamento() {
        return dataAnnullamento;
    }

    /**
     * Sets the value of the dataAnnullamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAnnullamento(XMLGregorianCalendar value) {
        this.dataAnnullamento = value;
    }

    /**
     * Gets the value of the dataCancellazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCancellazione() {
        return dataCancellazione;
    }

    /**
     * Sets the value of the dataCancellazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCancellazione(XMLGregorianCalendar value) {
        this.dataCancellazione = value;
    }

    /**
     * Gets the value of the accessionNumbers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessionNumbers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessionNumbers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAccessionNumbers() {
        if (accessionNumbers == null) {
            accessionNumbers = new ArrayList<String>();
        }
        return this.accessionNumbers;
    }

    /**
     * Gets the value of the codiceEpisodioDipartimentale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEpisodioDipartimentale() {
        return codiceEpisodioDipartimentale;
    }

    /**
     * Sets the value of the codiceEpisodioDipartimentale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEpisodioDipartimentale(String value) {
        this.codiceEpisodioDipartimentale = value;
    }

    /**
     * Gets the value of the codicePazienteDipartimentale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodicePazienteDipartimentale() {
        return codicePazienteDipartimentale;
    }

    /**
     * Sets the value of the codicePazienteDipartimentale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodicePazienteDipartimentale(String value) {
        this.codicePazienteDipartimentale = value;
    }

    /**
     * Gets the value of the dataRefertazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRefertazione() {
        return dataRefertazione;
    }

    /**
     * Sets the value of the dataRefertazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRefertazione(XMLGregorianCalendar value) {
        this.dataRefertazione = value;
    }

    /**
     * Gets the value of the medicoRefertante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicoRefertante() {
        return medicoRefertante;
    }

    /**
     * Sets the value of the medicoRefertante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicoRefertante(String value) {
        this.medicoRefertante = value;
    }

    /**
     * Gets the value of the numeroVolteScaricato property.
     * 
     */
    public int getNumeroVolteScaricato() {
        return numeroVolteScaricato;
    }

    /**
     * Sets the value of the numeroVolteScaricato property.
     * 
     */
    public void setNumeroVolteScaricato(int value) {
        this.numeroVolteScaricato = value;
    }

    /**
     * Gets the value of the tipoReferto property.
     * 
     * @return
     *     possible object is
     *     {@link TipoReferto }
     *     
     */
    public TipoReferto getTipoReferto() {
        return tipoReferto;
    }

    /**
     * Sets the value of the tipoReferto property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoReferto }
     *     
     */
    public void setTipoReferto(TipoReferto value) {
        this.tipoReferto = value;
    }

    /**
     * Gets the value of the ticketPagato property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTicketPagato() {
        return ticketPagato;
    }

    /**
     * Sets the value of the ticketPagato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTicketPagato(Float value) {
        this.ticketPagato = value;
    }

    /**
     * Gets the value of the ticketDaPagare property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTicketDaPagare() {
        return ticketDaPagare;
    }

    /**
     * Sets the value of the ticketDaPagare property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTicketDaPagare(Float value) {
        this.ticketDaPagare = value;
    }

    /**
     * Gets the value of the dataDisponibilitaReferto property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDisponibilitaReferto() {
        return dataDisponibilitaReferto;
    }

    /**
     * Sets the value of the dataDisponibilitaReferto property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDisponibilitaReferto(XMLGregorianCalendar value) {
        this.dataDisponibilitaReferto = value;
    }

    /**
     * Gets the value of the dataInserimentoROL property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimentoROL() {
        return dataInserimentoROL;
    }

    /**
     * Sets the value of the dataInserimentoROL property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimentoROL(XMLGregorianCalendar value) {
        this.dataInserimentoROL = value;
    }

    /**
     * Gets the value of the dataScadenza property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Sets the value of the dataScadenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
    }

    /**
     * Gets the value of the tipoAssociazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAssociazione() {
        return tipoAssociazione;
    }

    /**
     * Sets the value of the tipoAssociazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAssociazione(String value) {
        this.tipoAssociazione = value;
    }

    /**
     * Gets the value of the idDocumentoParent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumentoParent() {
        return idDocumentoParent;
    }

    /**
     * Sets the value of the idDocumentoParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumentoParent(String value) {
        this.idDocumentoParent = value;
    }

    /**
     * Gets the value of the statoReferto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoReferto() {
        return statoReferto;
    }

    /**
     * Sets the value of the statoReferto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoReferto(String value) {
        this.statoReferto = value;
    }

    /**
     * Gets the value of the dataSostituzione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSostituzione() {
        return dataSostituzione;
    }

    /**
     * Sets the value of the dataSostituzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSostituzione(XMLGregorianCalendar value) {
        this.dataSostituzione = value;
    }

    /**
     * Gets the value of the elencoPrestazioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoPrestazioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoPrestazioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prestazione }
     * 
     * 
     */
    public List<Prestazione> getElencoPrestazioni() {
        if (elencoPrestazioni == null) {
            elencoPrestazioni = new ArrayList<Prestazione>();
        }
        return this.elencoPrestazioni;
    }

    /**
     * Gets the value of the elencoNre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elencoNre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElencoNre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getElencoNre() {
        if (elencoNre == null) {
            elencoNre = new ArrayList<String>();
        }
        return this.elencoNre;
    }

    /**
     * Gets the value of the idDocumentoFascicolo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDocumentoFascicolo() {
        return idDocumentoFascicolo;
    }

    /**
     * Sets the value of the idDocumentoFascicolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDocumentoFascicolo(Long value) {
        this.idDocumentoFascicolo = value;
    }

    /**
     * Gets the value of the idEpisodioDocumentoFascicolo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEpisodioDocumentoFascicolo() {
        return idEpisodioDocumentoFascicolo;
    }

    /**
     * Sets the value of the idEpisodioDocumentoFascicolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEpisodioDocumentoFascicolo(Long value) {
        this.idEpisodioDocumentoFascicolo = value;
    }

}
