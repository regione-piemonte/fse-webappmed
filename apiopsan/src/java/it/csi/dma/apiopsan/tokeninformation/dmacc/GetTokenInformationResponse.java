/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.tokeninformation.dmacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.tokeninformation.dma.ParametriLogin;
import it.csi.dma.apiopsan.tokeninformation.dmac.Richiedente;


/**
 * <p>Classe Java per getTokenInformationResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getTokenInformationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmacc.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dmac.csi.it/}richiedente" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}codiceFiscaleAssistito" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}parametriLogin" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTokenInformationResponse", propOrder = {
    "richiedente",
    "codiceFiscaleAssistito",
    "parametriLogin"
})
public class GetTokenInformationResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "http://dmac.csi.it/")
    protected Richiedente richiedente;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected String codiceFiscaleAssistito;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected ParametriLogin parametriLogin;

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
     * Recupera il valore della proprietÃ  codiceFiscaleAssistito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleAssistito() {
        return codiceFiscaleAssistito;
    }

    /**
     * Imposta il valore della proprietÃ  codiceFiscaleAssistito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleAssistito(String value) {
        this.codiceFiscaleAssistito = value;
    }

    /**
     * Recupera il valore della proprietÃ  parametriLogin.
     * 
     * @return
     *     possible object is
     *     {@link ParametriLogin }
     *     
     */
    public ParametriLogin getParametriLogin() {
        return parametriLogin;
    }

    /**
     * Imposta il valore della proprietÃ  parametriLogin.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametriLogin }
     *     
     */
    public void setParametriLogin(ParametriLogin value) {
        this.parametriLogin = value;
    }

}
