/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.screeningservice.dmaccl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for pazienteSOL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pazienteSOL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleMMG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comuneDiNascita" type="{http://dma.csi.it/}comuneDiNascita" minOccurs="0"/>
 *         &lt;element name="dataDecesso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataDiNascita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataRiconduzione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deleghe" type="{http://dma.csi.it/}delega" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flag_registry_incarico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAsr" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idAura" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idIlec" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idIrec" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idPazienteRicondotto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mailPerAccessoDMA" type="{http://dma.csi.it/}siNo" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sesso" type="{http://dma.csi.it/}sesso" minOccurs="0"/>
 *         &lt;element name="statoDiNascita" type="{http://dma.csi.it/}statoDiNascita" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pazienteSOL", propOrder = {
    "codiceFiscale",
    "codiceFiscaleMMG",
    "cognome",
    "comuneDiNascita",
    "dataDecesso",
    "dataDiNascita",
    "dataRiconduzione",
    "deleghe",
    "flagRegistryIncarico",
    "idAsr",
    "idAura",
    "idIlec",
    "idIrec",
    "idPazienteRicondotto",
    "mailPerAccessoDMA",
    "nome",
    "sesso",
    "statoDiNascita"
})
public class PazienteSOL {

    protected String codiceFiscale;
    protected String codiceFiscaleMMG;
    protected String cognome;
    protected ComuneDiNascita comuneDiNascita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDecesso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDiNascita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRiconduzione;
    @XmlElement(nillable = true)
    protected List<Delega> deleghe;
    @XmlElement(name = "flag_registry_incarico")
    protected String flagRegistryIncarico;
    protected Long idAsr;
    protected Long idAura;
    protected Long idIlec;
    protected Long idIrec;
    protected Long idPazienteRicondotto;
    protected SiNo mailPerAccessoDMA;
    protected String nome;
    protected Sesso sesso;
    protected StatoDiNascita statoDiNascita;

    /**
     * Gets the value of the codiceFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Gets the value of the codiceFiscaleMMG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleMMG() {
        return codiceFiscaleMMG;
    }

    /**
     * Sets the value of the codiceFiscaleMMG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleMMG(String value) {
        this.codiceFiscaleMMG = value;
    }

    /**
     * Gets the value of the cognome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the value of the cognome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Gets the value of the comuneDiNascita property.
     * 
     * @return
     *     possible object is
     *     {@link ComuneDiNascita }
     *     
     */
    public ComuneDiNascita getComuneDiNascita() {
        return comuneDiNascita;
    }

    /**
     * Sets the value of the comuneDiNascita property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComuneDiNascita }
     *     
     */
    public void setComuneDiNascita(ComuneDiNascita value) {
        this.comuneDiNascita = value;
    }

    /**
     * Gets the value of the dataDecesso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDecesso() {
        return dataDecesso;
    }

    /**
     * Sets the value of the dataDecesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDecesso(XMLGregorianCalendar value) {
        this.dataDecesso = value;
    }

    /**
     * Gets the value of the dataDiNascita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Sets the value of the dataDiNascita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDiNascita(XMLGregorianCalendar value) {
        this.dataDiNascita = value;
    }

    /**
     * Gets the value of the dataRiconduzione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRiconduzione() {
        return dataRiconduzione;
    }

    /**
     * Sets the value of the dataRiconduzione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRiconduzione(XMLGregorianCalendar value) {
        this.dataRiconduzione = value;
    }

    /**
     * Gets the value of the deleghe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deleghe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeleghe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Delega }
     * 
     * 
     */
    public List<Delega> getDeleghe() {
        if (deleghe == null) {
            deleghe = new ArrayList<Delega>();
        }
        return this.deleghe;
    }

    /**
     * Gets the value of the flagRegistryIncarico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagRegistryIncarico() {
        return flagRegistryIncarico;
    }

    /**
     * Sets the value of the flagRegistryIncarico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagRegistryIncarico(String value) {
        this.flagRegistryIncarico = value;
    }

    /**
     * Gets the value of the idAsr property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAsr() {
        return idAsr;
    }

    /**
     * Sets the value of the idAsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAsr(Long value) {
        this.idAsr = value;
    }

    /**
     * Gets the value of the idAura property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAura() {
        return idAura;
    }

    /**
     * Sets the value of the idAura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAura(Long value) {
        this.idAura = value;
    }

    /**
     * Gets the value of the idIlec property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdIlec() {
        return idIlec;
    }

    /**
     * Sets the value of the idIlec property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdIlec(Long value) {
        this.idIlec = value;
    }

    /**
     * Gets the value of the idIrec property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdIrec() {
        return idIrec;
    }

    /**
     * Sets the value of the idIrec property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdIrec(Long value) {
        this.idIrec = value;
    }

    /**
     * Gets the value of the idPazienteRicondotto property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdPazienteRicondotto() {
        return idPazienteRicondotto;
    }

    /**
     * Sets the value of the idPazienteRicondotto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdPazienteRicondotto(Long value) {
        this.idPazienteRicondotto = value;
    }

    /**
     * Gets the value of the mailPerAccessoDMA property.
     * 
     * @return
     *     possible object is
     *     {@link SiNo }
     *     
     */
    public SiNo getMailPerAccessoDMA() {
        return mailPerAccessoDMA;
    }

    /**
     * Sets the value of the mailPerAccessoDMA property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiNo }
     *     
     */
    public void setMailPerAccessoDMA(SiNo value) {
        this.mailPerAccessoDMA = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the sesso property.
     * 
     * @return
     *     possible object is
     *     {@link Sesso }
     *     
     */
    public Sesso getSesso() {
        return sesso;
    }

    /**
     * Sets the value of the sesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sesso }
     *     
     */
    public void setSesso(Sesso value) {
        this.sesso = value;
    }

    /**
     * Gets the value of the statoDiNascita property.
     * 
     * @return
     *     possible object is
     *     {@link StatoDiNascita }
     *     
     */
    public StatoDiNascita getStatoDiNascita() {
        return statoDiNascita;
    }

    /**
     * Sets the value of the statoDiNascita property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoDiNascita }
     *     
     */
    public void setStatoDiNascita(StatoDiNascita value) {
        this.statoDiNascita = value;
    }

}
