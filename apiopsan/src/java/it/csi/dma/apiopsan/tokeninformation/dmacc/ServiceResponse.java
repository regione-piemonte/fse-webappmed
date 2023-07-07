/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.tokeninformation.dmacc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.tokeninformation.dma.Codifica;


/**
 * <p>Classe Java per serviceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="serviceResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmacc.csi.it/}Ens_Response"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}errori" minOccurs="0"/&gt;
 *         &lt;element name="esito" type="{http://dmacc.csi.it/}risultatoCodice" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}codifiche" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceResponse", propOrder = {
    "errori",
    "esito",
    "codifiche"
})
@XmlSeeAlso({
    GetTokenInformation2Response.class,
    GetTokenInformationResponse.class
})
public class ServiceResponse
    extends EnsResponse
{

    @XmlElement(namespace = "http://dma.csi.it/")
    protected Errori errori;
    @XmlElement(namespace = "")
    @XmlSchemaType(name = "string")
    protected RisultatoCodice esito;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<Codifica> codifiche;

    /**
     * Recupera il valore della proprietÃ  errori.
     * 
     * @return
     *     possible object is
     *     {@link Errori }
     *     
     */
    public Errori getErrori() {
        return errori;
    }

    /**
     * Imposta il valore della proprietÃ  errori.
     * 
     * @param value
     *     allowed object is
     *     {@link Errori }
     *     
     */
    public void setErrori(Errori value) {
        this.errori = value;
    }

    /**
     * Recupera il valore della proprietÃ  esito.
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
     * Imposta il valore della proprietÃ  esito.
     * 
     * @param value
     *     allowed object is
     *     {@link RisultatoCodice }
     *     
     */
    public void setEsito(RisultatoCodice value) {
        this.esito = value;
    }

    /**
     * Gets the value of the codifiche property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codifiche property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodifiche().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Codifica }
     * 
     * 
     */
    public List<Codifica> getCodifiche() {
        if (codifiche == null) {
            codifiche = new ArrayList<Codifica>();
        }
        return this.codifiche;
    }

}
