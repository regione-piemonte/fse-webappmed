/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per requestCommon complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="requestCommon"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dmaclbluc.dma.csi.it/}richiedente"/&gt;
 *         &lt;element name="taccuino" type="{http://www.w3.org/2001/XMLSchema}long" form="qualified"/&gt;
 *         &lt;element name="paziente" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestCommon", propOrder = {
    "richiedente",
    "taccuino",
    "paziente"
})
@XmlSeeAlso({
    SetDoloreRequest.class,
    SetNotaGeneraleRequest.class,
    SetDietaRequest.class,
    SetImpostazioniTaccuinoRequest.class,
    SetFarmacoRequest.class,
    SetSintomoRequest.class,
    CancellaContattiStruttureRequest.class,
    SetRilevazioniRequest.class,
    SetOscuramentoTaccuinoRequest.class,
    GetDecodificaStrutturaTipoRequest.class,
    CancellaSintomiRequest.class,
    SetContattoStrutturaRequest.class,
    GetDecodificaModalitaRilevazioneRequest.class,
    CancellaRilevazioniRequest.class,
    CancellaEventoRequest.class,
    CancellaFarmacoRequest.class,
    GetDecodificaIntensitaDoloreRequest.class,
    CancellaDoloriRequest.class,
    GetDescrittoriMisurazioneRequest.class,
    GetDecodificaContattoTipoRequest.class,
    SetEventoRequest.class,
    CancellaDietaRequest.class,
    RequestCommonGet.class
})
public class RequestCommon {

    @XmlElement(required = true)
    protected Richiedente richiedente;
    protected long taccuino;
    @XmlElement(required = true)
    protected String paziente;

    /**
     * Recupera il valore della proprietÃ  richiedente.
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
     * Imposta il valore della proprietÃ  richiedente.
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
     * Recupera il valore della proprietÃ  taccuino.
     * 
     */
    public long getTaccuino() {
        return taccuino;
    }

    /**
     * Imposta il valore della proprietÃ  taccuino.
     * 
     */
    public void setTaccuino(long value) {
        this.taccuino = value;
    }

    /**
     * Recupera il valore della proprietÃ  paziente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaziente() {
        return paziente;
    }

    /**
     * Imposta il valore della proprietÃ  paziente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaziente(String value) {
        this.paziente = value;
    }

}
