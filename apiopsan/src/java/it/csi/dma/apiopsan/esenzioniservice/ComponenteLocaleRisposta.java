/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.esenzioniservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per componenteLocaleRisposta complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="componenteLocaleRisposta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}componenteLocale"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errori" type="{http://dma.csi.it/}errore" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="stato" type="{http://dma.csi.it/}componenteLocaleRispostaStato" minOccurs="0"/&gt;
 *         &lt;element name="tempoMaxAttesaCLInSec" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="tempoMaxRestituzioneDatiInSec" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "componenteLocaleRisposta", propOrder = {
    "errori",
    "stato",
    "tempoMaxAttesaCLInSec",
    "tempoMaxRestituzioneDatiInSec"
})
public class ComponenteLocaleRisposta
    extends ComponenteLocale
{

    @XmlElement(nillable = true)
    protected List<Errore> errori;
    protected ComponenteLocaleRispostaStato stato;
    protected long tempoMaxAttesaCLInSec;
    protected long tempoMaxRestituzioneDatiInSec;

    /**
     * Gets the value of the errori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Errore }
     * 
     * 
     */
    public List<Errore> getErrori() {
        if (errori == null) {
            errori = new ArrayList<Errore>();
        }
        return this.errori;
    }

    /**
     * Recupera il valore della proprietÃ  stato.
     * 
     * @return
     *     possible object is
     *     {@link ComponenteLocaleRispostaStato }
     *     
     */
    public ComponenteLocaleRispostaStato getStato() {
        return stato;
    }

    /**
     * Imposta il valore della proprietÃ  stato.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponenteLocaleRispostaStato }
     *     
     */
    public void setStato(ComponenteLocaleRispostaStato value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della proprietÃ  tempoMaxAttesaCLInSec.
     * 
     */
    public long getTempoMaxAttesaCLInSec() {
        return tempoMaxAttesaCLInSec;
    }

    /**
     * Imposta il valore della proprietÃ  tempoMaxAttesaCLInSec.
     * 
     */
    public void setTempoMaxAttesaCLInSec(long value) {
        this.tempoMaxAttesaCLInSec = value;
    }

    /**
     * Recupera il valore della proprietÃ  tempoMaxRestituzioneDatiInSec.
     * 
     */
    public long getTempoMaxRestituzioneDatiInSec() {
        return tempoMaxRestituzioneDatiInSec;
    }

    /**
     * Imposta il valore della proprietÃ  tempoMaxRestituzioneDatiInSec.
     * 
     */
    public void setTempoMaxRestituzioneDatiInSec(long value) {
        this.tempoMaxRestituzioneDatiInSec = value;
    }

}
