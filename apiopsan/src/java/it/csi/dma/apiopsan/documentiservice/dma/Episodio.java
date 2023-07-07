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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per episodio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="episodio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="componenteLocale" type="{http://dma.csi.it/}componenteLocale" minOccurs="0"/&gt;
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idEpisodio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="luogoAccettazione" type="{http://dma.csi.it/}luogoASU" minOccurs="0"/&gt;
 *         &lt;element name="luogoDimissione" type="{http://dma.csi.it/}luogoASU" minOccurs="0"/&gt;
 *         &lt;element name="metadatiDocumento" type="{http://dma.csi.it/}metadatiDocumento" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="numeroNosologico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroPassaggioPS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stato" type="{http://dma.csi.it/}episodioStato" minOccurs="0"/&gt;
 *         &lt;element name="tipo" type="{http://dma.csi.it/}tipoEpisodio" minOccurs="0"/&gt;
 *         &lt;element name="visibilita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "episodio", propOrder = {
    "componenteLocale",
    "dataFine",
    "dataInizio",
    "idEpisodio",
    "luogoAccettazione",
    "luogoDimissione",
    "metadatiDocumento",
    "numeroNosologico",
    "numeroPassaggioPS",
    "stato",
    "tipo",
    "visibilita"
})
public class Episodio {

    protected ComponenteLocale componenteLocale;
    protected String dataFine;
    protected String dataInizio;
    protected String idEpisodio;
    protected LuogoASU luogoAccettazione;
    protected LuogoASU luogoDimissione;
    @XmlElement(nillable = true)
    protected List<MetadatiDocumento> metadatiDocumento;
    protected String numeroNosologico;
    protected String numeroPassaggioPS;
    protected EpisodioStato stato;
    protected TipoEpisodio tipo;
    protected String visibilita;

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
     * Recupera il valore della proprietÃ  dataFine.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprietÃ  dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFine(String value) {
        this.dataFine = value;
    }

    /**
     * Recupera il valore della proprietÃ  dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprietÃ  dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInizio(String value) {
        this.dataInizio = value;
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
     * Recupera il valore della proprietÃ  luogoAccettazione.
     * 
     * @return
     *     possible object is
     *     {@link LuogoASU }
     *     
     */
    public LuogoASU getLuogoAccettazione() {
        return luogoAccettazione;
    }

    /**
     * Imposta il valore della proprietÃ  luogoAccettazione.
     * 
     * @param value
     *     allowed object is
     *     {@link LuogoASU }
     *     
     */
    public void setLuogoAccettazione(LuogoASU value) {
        this.luogoAccettazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  luogoDimissione.
     * 
     * @return
     *     possible object is
     *     {@link LuogoASU }
     *     
     */
    public LuogoASU getLuogoDimissione() {
        return luogoDimissione;
    }

    /**
     * Imposta il valore della proprietÃ  luogoDimissione.
     * 
     * @param value
     *     allowed object is
     *     {@link LuogoASU }
     *     
     */
    public void setLuogoDimissione(LuogoASU value) {
        this.luogoDimissione = value;
    }

    /**
     * Gets the value of the metadatiDocumento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadatiDocumento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadatiDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadatiDocumento }
     * 
     * 
     */
    public List<MetadatiDocumento> getMetadatiDocumento() {
        if (metadatiDocumento == null) {
            metadatiDocumento = new ArrayList<MetadatiDocumento>();
        }
        return this.metadatiDocumento;
    }

    /**
     * Recupera il valore della proprietÃ  numeroNosologico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroNosologico() {
        return numeroNosologico;
    }

    /**
     * Imposta il valore della proprietÃ  numeroNosologico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroNosologico(String value) {
        this.numeroNosologico = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroPassaggioPS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPassaggioPS() {
        return numeroPassaggioPS;
    }

    /**
     * Imposta il valore della proprietÃ  numeroPassaggioPS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPassaggioPS(String value) {
        this.numeroPassaggioPS = value;
    }

    /**
     * Recupera il valore della proprietÃ  stato.
     * 
     * @return
     *     possible object is
     *     {@link EpisodioStato }
     *     
     */
    public EpisodioStato getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietÃ  stato.
     * 
     * @param value
     *     allowed object is
     *     {@link EpisodioStato }
     *     
     */
    public void setStato(EpisodioStato value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietÃ  tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoEpisodio }
     *     
     */
    public TipoEpisodio getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietÃ  tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEpisodio }
     *     
     */
    public void setTipo(TipoEpisodio value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietÃ  visibilita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisibilita() {
        return visibilita;
    }

    /**
     * Imposta il valore della proprietÃ  visibilita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisibilita(String value) {
        this.visibilita = value;
    }

}
