/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.Azienda;


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
 *         &lt;element name="idEpisodio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="aziendaAccettazione" type="{http://dma.csi.it/}azienda" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="aziendaDimissione" type="{http://dma.csi.it/}azienda" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="descrizioneStrutturaAccettazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="descrizioneStrutturaDimissione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="descrizioneUnitaOperativaAccettazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="descrizioneUnitaOperativaDimissione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="matricolaAccettazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="matricolaDimissione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numeroPassaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numeroNosologico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="tipoEpisodio" type="{http://dmacc.csi.it/}tipoEpisodio" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="codiceCL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="unqualified"/&gt;
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
    "idEpisodio",
    "dataInizio",
    "dataFine",
    "aziendaAccettazione",
    "aziendaDimissione",
    "descrizioneStrutturaAccettazione",
    "descrizioneStrutturaDimissione",
    "descrizioneUnitaOperativaAccettazione",
    "descrizioneUnitaOperativaDimissione",
    "matricolaAccettazione",
    "matricolaDimissione",
    "numeroPassaggio",
    "numeroNosologico",
    "tipoEpisodio",
    "codiceCL"
})
public class Episodio {

    protected BigDecimal idEpisodio;
    protected String dataInizio;
    protected String dataFine;
    protected Azienda aziendaAccettazione;
    protected Azienda aziendaDimissione;
    protected String descrizioneStrutturaAccettazione;
    protected String descrizioneStrutturaDimissione;
    protected String descrizioneUnitaOperativaAccettazione;
    protected String descrizioneUnitaOperativaDimissione;
    protected String matricolaAccettazione;
    protected String matricolaDimissione;
    protected String numeroPassaggio;
    protected String numeroNosologico;
    protected TipoEpisodio tipoEpisodio;
    protected String codiceCL;

    /**
     * Recupera il valore della proprietÃ  idEpisodio.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdEpisodio() {
        return idEpisodio;
    }

    /**
     * Imposta il valore della proprietÃ  idEpisodio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdEpisodio(BigDecimal value) {
        this.idEpisodio = value;
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
     * Recupera il valore della proprietÃ  aziendaAccettazione.
     * 
     * @return
     *     possible object is
     *     {@link Azienda }
     *     
     */
    public Azienda getAziendaAccettazione() {
        return aziendaAccettazione;
    }

    /**
     * Imposta il valore della proprietÃ  aziendaAccettazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Azienda }
     *     
     */
    public void setAziendaAccettazione(Azienda value) {
        this.aziendaAccettazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  aziendaDimissione.
     * 
     * @return
     *     possible object is
     *     {@link Azienda }
     *     
     */
    public Azienda getAziendaDimissione() {
        return aziendaDimissione;
    }

    /**
     * Imposta il valore della proprietÃ  aziendaDimissione.
     * 
     * @param value
     *     allowed object is
     *     {@link Azienda }
     *     
     */
    public void setAziendaDimissione(Azienda value) {
        this.aziendaDimissione = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneStrutturaAccettazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneStrutturaAccettazione() {
        return descrizioneStrutturaAccettazione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneStrutturaAccettazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneStrutturaAccettazione(String value) {
        this.descrizioneStrutturaAccettazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneStrutturaDimissione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneStrutturaDimissione() {
        return descrizioneStrutturaDimissione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneStrutturaDimissione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneStrutturaDimissione(String value) {
        this.descrizioneStrutturaDimissione = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneUnitaOperativaAccettazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneUnitaOperativaAccettazione() {
        return descrizioneUnitaOperativaAccettazione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneUnitaOperativaAccettazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneUnitaOperativaAccettazione(String value) {
        this.descrizioneUnitaOperativaAccettazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  descrizioneUnitaOperativaDimissione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneUnitaOperativaDimissione() {
        return descrizioneUnitaOperativaDimissione;
    }

    /**
     * Imposta il valore della proprietÃ  descrizioneUnitaOperativaDimissione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneUnitaOperativaDimissione(String value) {
        this.descrizioneUnitaOperativaDimissione = value;
    }

    /**
     * Recupera il valore della proprietÃ  matricolaAccettazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricolaAccettazione() {
        return matricolaAccettazione;
    }

    /**
     * Imposta il valore della proprietÃ  matricolaAccettazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricolaAccettazione(String value) {
        this.matricolaAccettazione = value;
    }

    /**
     * Recupera il valore della proprietÃ  matricolaDimissione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricolaDimissione() {
        return matricolaDimissione;
    }

    /**
     * Imposta il valore della proprietÃ  matricolaDimissione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricolaDimissione(String value) {
        this.matricolaDimissione = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroPassaggio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPassaggio() {
        return numeroPassaggio;
    }

    /**
     * Imposta il valore della proprietÃ  numeroPassaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPassaggio(String value) {
        this.numeroPassaggio = value;
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
     * Recupera il valore della proprietÃ  tipoEpisodio.
     * 
     * @return
     *     possible object is
     *     {@link TipoEpisodio }
     *     
     */
    public TipoEpisodio getTipoEpisodio() {
        return tipoEpisodio;
    }

    /**
     * Imposta il valore della proprietÃ  tipoEpisodio.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEpisodio }
     *     
     */
    public void setTipoEpisodio(TipoEpisodio value) {
        this.tipoEpisodio = value;
    }

    /**
     * Recupera il valore della proprietÃ  codiceCL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCL() {
        return codiceCL;
    }

    /**
     * Imposta il valore della proprietÃ  codiceCL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCL(String value) {
        this.codiceCL = value;
    }

}
