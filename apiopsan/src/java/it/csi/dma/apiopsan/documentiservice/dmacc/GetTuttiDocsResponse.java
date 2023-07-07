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
import it.csi.dma.apiopsan.documentiservice.dma.ServiceResponse;


/**
 * <p>Classe Java per getTuttiDocsResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getTuttiDocsResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documenti" type="{http://dmacc.csi.it/}documento" maxOccurs="unbounded" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="numeroDocumenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="unqualified"/&gt;
 *         &lt;element name="documentoAreaRossa" type="{http://dmacc.csi.it/}documentoAreaRossa" maxOccurs="unbounded" minOccurs="0" form="unqualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTuttiDocsResponse", propOrder = {
    "documenti",
    "numeroDocumenti",
    "documentoAreaRossa"
})
public class GetTuttiDocsResponse
    extends ServiceResponse
{

    @XmlElement(nillable = true)
    protected List<Documento> documenti;
    protected Integer numeroDocumenti;
    @XmlElement(nillable = true)
    protected List<DocumentoAreaRossa> documentoAreaRossa;

    /**
     * Gets the value of the documenti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documenti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumenti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Documento }
     * 
     * 
     */
    public List<Documento> getDocumenti() {
        if (documenti == null) {
            documenti = new ArrayList<Documento>();
        }
        return this.documenti;
    }

    /**
     * Recupera il valore della proprietÃ  numeroDocumenti.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroDocumenti() {
        return numeroDocumenti;
    }

    /**
     * Imposta il valore della proprietÃ  numeroDocumenti.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroDocumenti(Integer value) {
        this.numeroDocumenti = value;
    }

    /**
     * Gets the value of the documentoAreaRossa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoAreaRossa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoAreaRossa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAreaRossa }
     * 
     * 
     */
    public List<DocumentoAreaRossa> getDocumentoAreaRossa() {
        if (documentoAreaRossa == null) {
            documentoAreaRossa = new ArrayList<DocumentoAreaRossa>();
        }
        return this.documentoAreaRossa;
    }

}
