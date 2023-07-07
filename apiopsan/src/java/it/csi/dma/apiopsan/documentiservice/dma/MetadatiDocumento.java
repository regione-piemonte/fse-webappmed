/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per metadatiDocumento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="metadatiDocumento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accessionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="assettoOrganizzativo" type="{http://dma.csi.it/}assettoOrganizzativo" minOccurs="0"/&gt;
 *         &lt;element name="componenteLocale" type="{http://dma.csi.it/}componenteLocale" minOccurs="0"/&gt;
 *         &lt;element name="consultabile" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="dataOscuramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataSbloccoMediato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataValidazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firmatoDigitalmente" type="{http://dma.csi.it/}siNo" minOccurs="0"/&gt;
 *         &lt;element name="flagUltimoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fonteOscuramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idEpisodio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="livelloConfidenzialita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="luogoProduzioneDocumento" type="{http://dma.csi.it/}luogoASU" minOccurs="0"/&gt;
 *         &lt;element name="mediato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="medicoValidante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="medicoVisita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nreList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="oidDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oidDocumentoParent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oidRepository" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oscuramento" type="{http://dma.csi.it/}oscuramento" minOccurs="0"/&gt;
 *         &lt;element name="pagatoTicket" type="{http://dma.csi.it/}pagatoTicketStato" minOccurs="0"/&gt;
 *         &lt;element name="prestazioniAssociate" type="{http://dma.csi.it/}prestazione" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ticketDaPagare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ticketPagato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoAssociazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoDocumento" type="{http://dma.csi.it/}tipoDocumento" minOccurs="0"/&gt;
 *         &lt;element name="tipoFileDocumento" type="{http://dma.csi.it/}tipoFileDocumento" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadatiDocumento", propOrder = {
    "accessionNumber",
    "assettoOrganizzativo",
    "componenteLocale",
    "consultabile",
    "dataOscuramento",
    "dataSbloccoMediato",
    "dataValidazione",
    "firmatoDigitalmente",
    "flagUltimoDocumento",
    "fonteOscuramento",
    "idDocumento",
    "idEpisodio",
    "livelloConfidenzialita",
    "luogoProduzioneDocumento",
    "mediato",
    "medicoValidante",
    "medicoVisita",
    "nreList",
    "oidDocumento",
    "oidDocumentoParent",
    "oidRepository",
    "oscuramento",
    "pagatoTicket",
    "prestazioniAssociate",
    "ticketDaPagare",
    "ticketPagato",
    "tipoAssociazione",
    "tipoDocumento",
    "tipoFileDocumento"
})
public class MetadatiDocumento {

    protected String accessionNumber;
    protected AssettoOrganizzativo assettoOrganizzativo;
    protected ComponenteLocale componenteLocale;
    @XmlSchemaType(name = "string")
    protected SiNo consultabile;
    protected String dataOscuramento;
    protected String dataSbloccoMediato;
    protected String dataValidazione;
    @XmlSchemaType(name = "string")
    protected SiNo firmatoDigitalmente;
    protected String flagUltimoDocumento;
    protected String fonteOscuramento;
    protected String idDocumento;
    protected String idEpisodio;
    protected String livelloConfidenzialita;
    protected LuogoASU luogoProduzioneDocumento;
    protected String mediato;
    protected String medicoValidante;
    protected String medicoVisita;
    @XmlElement(nillable = true)
    protected List<String> nreList;
    protected String oidDocumento;
    protected String oidDocumentoParent;
    protected String oidRepository;
    protected Oscuramento oscuramento;
    @XmlSchemaType(name = "string")
    protected PagatoTicketStato pagatoTicket;
    @XmlElement(nillable = true)
    protected List<Prestazione> prestazioniAssociate;
    protected String ticketDaPagare;
    protected String ticketPagato;
    protected String tipoAssociazione;
    protected TipoDocumento tipoDocumento;
    protected TipoFileDocumento tipoFileDocumento;

    /**
     * Recupera il valore della proprietÃ  accessionNumber.
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
     * Imposta il valore della proprietÃ  accessionNumber.
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
     * Recupera il valore della proprietÃ  assettoOrganizzativo.
     * 
     * @return
     *     possible object is
     *     {@link AssettoOrganizzativo }
     *     
     */
    public AssettoOrganizzativo getAssettoOrganizzativo() {
        return assettoOrganizzativo;
    }

    /**
     * Imposta il valore della proprietÃ  assettoOrganizzativo.
     * 
     * @param value
     *     allowed object is
     *     {@link AssettoOrganizzativo }
     *     
     */
    public void setAssettoOrganizzativo(AssettoOrganizzativo value) {
        this.assettoOrganizzativo = value;
    }

    /**
     * Recupera il valore della proprietÃ  componenteLocale.
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
     * Imposta il valore della proprietÃ  componenteLocale.
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
     * Recupera il valore della proprietÃ  consultabile.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getConsultabile() {
        return consultabile;
    }

    /**
     * Imposta il valore della proprietÃ  consultabile.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setConsultabile(SiNo value) {
        this.consultabile = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataOscuramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOscuramento() {
        return dataOscuramento;
    }

    /**
     * Imposta il valore della proprietÃ  dataOscuramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOscuramento(String value) {
        this.dataOscuramento = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataSbloccoMediato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSbloccoMediato() {
        return dataSbloccoMediato;
    }

    /**
     * Imposta il valore della proprietÃ  dataSbloccoMediato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSbloccoMediato(String value) {
        this.dataSbloccoMediato = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataValidazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataValidazione() {
        return dataValidazione;
    }

    /**
     * Imposta il valore della proprietÃ  dataValidazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataValidazione(String value) {
        this.dataValidazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  firmatoDigitalmente.
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
     * Imposta il valore della proprietÃ  firmatoDigitalmente.
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
     * Recupera il valore della proprietÃ  flagUltimoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagUltimoDocumento() {
        return flagUltimoDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  flagUltimoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagUltimoDocumento(String value) {
        this.flagUltimoDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  fonteOscuramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFonteOscuramento() {
        return fonteOscuramento;
    }

    /**
     * Imposta il valore della proprietÃ  fonteOscuramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFonteOscuramento(String value) {
        this.fonteOscuramento = value;
    }

    /**
     * Recupera il valore della proprietÃ  idDocumento.
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
     * Imposta il valore della proprietÃ  idDocumento.
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
     * Recupera il valore della proprietÃ  idEpisodio.
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
     * Imposta il valore della proprietÃ  idEpisodio.
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
     * Recupera il valore della proprietÃ  livelloConfidenzialita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivelloConfidenzialita() {
        return livelloConfidenzialita;
    }

    /**
     * Imposta il valore della proprietÃ  livelloConfidenzialita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivelloConfidenzialita(String value) {
        this.livelloConfidenzialita = value;
    }

    /**
     * Recupera il valore della proprietÃ  luogoProduzioneDocumento.
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
     * Imposta il valore della proprietÃ  luogoProduzioneDocumento.
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
     * Recupera il valore della proprietÃ  mediato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediato() {
        return mediato;
    }

    /**
     * Imposta il valore della proprietÃ  mediato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediato(String value) {
        this.mediato = value;
    }

    /**
     * Recupera il valore della proprietÃ  medicoValidante.
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
     * Imposta il valore della proprietÃ  medicoValidante.
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
     * Recupera il valore della proprietÃ  medicoVisita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicoVisita() {
        return medicoVisita;
    }

    /**
     * Imposta il valore della proprietÃ  medicoVisita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicoVisita(String value) {
        this.medicoVisita = value;
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
     * Recupera il valore della proprietÃ  oidDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOidDocumento() {
        return oidDocumento;
    }

    /**
     * Imposta il valore della proprietÃ  oidDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOidDocumento(String value) {
        this.oidDocumento = value;
    }

    /**
     * Recupera il valore della proprietÃ  oidDocumentoParent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOidDocumentoParent() {
        return oidDocumentoParent;
    }

    /**
     * Imposta il valore della proprietÃ  oidDocumentoParent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOidDocumentoParent(String value) {
        this.oidDocumentoParent = value;
    }

    /**
     * Recupera il valore della proprietÃ  oidRepository.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOidRepository() {
        return oidRepository;
    }

    /**
     * Imposta il valore della proprietÃ  oidRepository.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOidRepository(String value) {
        this.oidRepository = value;
    }

    /**
     * Recupera il valore della proprietÃ  oscuramento.
     * 
     * @return
     *     possible object is
     *     {@link Oscuramento }
     *     
     */
    public Oscuramento getOscuramento() {
        return oscuramento;
    }

    /**
     * Imposta il valore della proprietÃ  oscuramento.
     * 
     * @param value
     *     allowed object is
     *     {@link Oscuramento }
     *     
     */
    public void setOscuramento(Oscuramento value) {
        this.oscuramento = value;
    }

    /**
     * Recupera il valore della proprietÃ  pagatoTicket.
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
     * Imposta il valore della proprietÃ  pagatoTicket.
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
     * Gets the value of the prestazioniAssociate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prestazioniAssociate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrestazioniAssociate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prestazione }
     * 
     * 
     */
    public List<Prestazione> getPrestazioniAssociate() {
        if (prestazioniAssociate == null) {
            prestazioniAssociate = new ArrayList<Prestazione>();
        }
        return this.prestazioniAssociate;
    }

    /**
     * Recupera il valore della proprietÃ  ticketDaPagare.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketDaPagare() {
        return ticketDaPagare;
    }

    /**
     * Imposta il valore della proprietÃ  ticketDaPagare.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketDaPagare(String value) {
        this.ticketDaPagare = value;
    }

    /**
     * Recupera il valore della proprietÃ  ticketPagato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketPagato() {
        return ticketPagato;
    }

    /**
     * Imposta il valore della proprietÃ  ticketPagato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketPagato(String value) {
        this.ticketPagato = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipoAssociazione.
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
     * Imposta il valore della proprietÃ  tipoAssociazione.
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
     * Recupera il valore della proprietÃ  tipoDocumento.
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
     * Imposta il valore della proprietÃ  tipoDocumento.
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
     * Recupera il valore della proprietÃ  tipoFileDocumento.
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
     * Imposta il valore della proprietÃ  tipoFileDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFileDocumento }
     *     
     */
    public void setTipoFileDocumento(TipoFileDocumento value) {
        this.tipoFileDocumento = value;
    }

}
