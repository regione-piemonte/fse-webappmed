/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per codifica complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="codifica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="riferimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "codifica", propOrder = {
    "codice",
    "descrizione",
    "riferimento"
})
@XmlSeeAlso({
    ModalitaDiAlimentazione.class,
    ApplicazioneRichiedente.class,
    RegimeDMA.class,
    RuoloDMA.class,
    AziendaSanitaria.class,
    Profilo.class,
    ComuneDiNascita.class,
    StatoDiNascita.class,
    ComponenteLocaleTipo.class,
    ComponenteLocaleServizio.class,
    ComponenteLocaleOperazione.class,
    ComponenteLocale.class,
    Errore.class,
    ComponenteLocaleRispostaStato.class,
    StrutturaSanitaria.class,
    UnitaOperativaSanitaria.class,
    Prestazione.class,
    Disciplina.class,
    Branca.class,
    TipoDocumento.class,
    AssettoOrganizzativo.class,
    TipoFileDocumento.class,
    CatalogoLog.class,
    Configurazione.class,
    ConsensoMatriceTipoDato.class,
    EpisodioStato.class,
    TipoCartellaPersonale.class,
    TipoContributo.class,
    TipoEpisodio.class
})
public class Codifica {

    protected String codice;
    protected String descrizione;
    protected String riferimento;

    /**
     * Recupera il valore della proprieta' codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprieta' codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprieta' descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprieta' descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprieta' riferimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiferimento() {
        return riferimento;
    }

    /**
     * Imposta il valore della proprieta' riferimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiferimento(String value) {
        this.riferimento = value;
    }

}
