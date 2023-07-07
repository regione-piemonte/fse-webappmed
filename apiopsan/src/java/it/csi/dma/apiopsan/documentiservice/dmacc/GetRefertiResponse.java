/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import it.csi.dma.apiopsan.documentiservice.dma.ComponenteLocaleRisposta;
import it.csi.dma.apiopsan.documentiservice.dma.Episodio;
import it.csi.dma.apiopsan.documentiservice.dma.MetadatiDocumento;


/**
 * <p>Classe Java per GetRefertiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GetRefertiResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmacc.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dma.csi.it/}componentiLocaliInterrogate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}episodi" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}metadatiDocumento" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}numeroTotaleDocumenti"/&gt;
 *         &lt;element ref="{http://dma.csi.it/}numeroTotaleEpisodi"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRefertiResponse", propOrder = {
    "componentiLocaliInterrogate",
    "episodi",
    "metadatiDocumento",
    "numeroTotaleDocumenti",
    "numeroTotaleEpisodi"
})
public class GetRefertiResponse
    extends ServiceResponse
{

    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<ComponenteLocaleRisposta> componentiLocaliInterrogate;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<Episodio> episodi;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected List<MetadatiDocumento> metadatiDocumento;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected int numeroTotaleDocumenti;
    @XmlElement(namespace = "http://dma.csi.it/")
    protected int numeroTotaleEpisodi;

    /**
     * Gets the value of the componentiLocaliInterrogate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componentiLocaliInterrogate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponentiLocaliInterrogate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteLocaleRisposta }
     * 
     * 
     */
    public List<ComponenteLocaleRisposta> getComponentiLocaliInterrogate() {
        if (componentiLocaliInterrogate == null) {
            componentiLocaliInterrogate = new ArrayList<ComponenteLocaleRisposta>();
        }
        return this.componentiLocaliInterrogate;
    }

    /**
     * Gets the value of the episodi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the episodi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEpisodi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Episodio }
     * 
     * 
     */
    public List<Episodio> getEpisodi() {
        if (episodi == null) {
            episodi = new ArrayList<Episodio>();
        }
        return this.episodi;
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
     * Recupera il valore della proprietÃ  numeroTotaleDocumenti.
     * 
     */
    public int getNumeroTotaleDocumenti() {
        return numeroTotaleDocumenti;
    }

    /**
     * Imposta il valore della proprietÃ  numeroTotaleDocumenti.
     * 
     */
    public void setNumeroTotaleDocumenti(int value) {
        this.numeroTotaleDocumenti = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroTotaleEpisodi.
     * 
     */
    public int getNumeroTotaleEpisodi() {
        return numeroTotaleEpisodi;
    }

    /**
     * Imposta il valore della proprietÃ  numeroTotaleEpisodi.
     * 
     */
    public void setNumeroTotaleEpisodi(int value) {
        this.numeroTotaleEpisodi = value;
    }

}
