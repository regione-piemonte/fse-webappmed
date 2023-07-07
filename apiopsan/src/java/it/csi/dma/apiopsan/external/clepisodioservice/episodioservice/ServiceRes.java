/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.episodioservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.external.clepisodioservice.dma.RisultatoCodice;


/**
 * <p>Classe Java per ServiceRes complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ServiceRes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://EpisodioService.WS.DMACL.csi.it}Ens_Response"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codifiche" type="{http://EpisodioService.WS.DMACL.csi.it}ArrayOfcodificacodifica" minOccurs="0"/&gt;
 *         &lt;element name="errori" type="{http://EpisodioService.WS.DMACL.csi.it}ArrayOferroreerrore" minOccurs="0"/&gt;
 *         &lt;element name="esito" type="{http://dma.csi.it/}risultatoCodice" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRes", propOrder = {
    "codifiche",
    "errori",
    "esito"
})
@XmlSeeAlso({
    GetEpisodiRes.class
})
public class ServiceRes
    extends EnsResponse
{

    protected ArrayOfcodificacodifica codifiche;
    protected ArrayOferroreerrore errori;
    @XmlSchemaType(name = "string")
    protected RisultatoCodice esito;

    /**
     * Recupera il valore della proprieta' codifiche.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfcodificacodifica }
     *     
     */
    public ArrayOfcodificacodifica getCodifiche() {
        return codifiche;
    }

    /**
     * Imposta il valore della proprieta' codifiche.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfcodificacodifica }
     *     
     */
    public void setCodifiche(ArrayOfcodificacodifica value) {
        this.codifiche = value;
    }

    /**
     * Recupera il valore della proprieta' errori.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOferroreerrore }
     *     
     */
    public ArrayOferroreerrore getErrori() {
        return errori;
    }

    /**
     * Imposta il valore della proprieta' errori.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOferroreerrore }
     *     
     */
    public void setErrori(ArrayOferroreerrore value) {
        this.errori = value;
    }

    /**
     * Recupera il valore della proprieta' esito.
     * 
     * @return
     *     possible object is
     *     {@link RisultatoCodice }
     *     
     */
    public RisultatoCodice getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprieta' esito.
     * 
     * @param value
     *     allowed object is
     *     {@link RisultatoCodice }
     *     
     */
    public void setEsito(RisultatoCodice value) {
        this.esito = value;
    }

}
