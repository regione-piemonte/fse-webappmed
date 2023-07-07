/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per contattiPaziente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="contattiPaziente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="indirizzoEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="notificheAPaziente" type="{http://dma.csi.it/}notificaAPaziente" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="foto" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="cittadinanza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="aslDiResidenza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="residenza" type="{http://dma.csi.it/}indirizzo" minOccurs="0"/&gt;
 *         &lt;element name="aslDiDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="domicilio" type="{http://dma.csi.it/}indirizzo" minOccurs="0"/&gt;
 *         &lt;element name="numeroTesseraTeam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroTesseraSanitaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contattiPaziente", propOrder = {
    "indirizzoEmail",
    "telefono",
    "notificheAPaziente",
    "foto",
    "cittadinanza",
    "aslDiResidenza",
    "residenza",
    "aslDiDomicilio",
    "domicilio",
    "numeroTesseraTeam",
    "numeroTesseraSanitaria"
})
public class ContattiPaziente {

    protected String indirizzoEmail;
    protected String telefono;
    @XmlElement(nillable = true)
    protected List<NotificaAPaziente> notificheAPaziente;
    protected byte[] foto;
    protected String cittadinanza;
    protected String aslDiResidenza;
    protected Indirizzo residenza;
    protected String aslDiDomicilio;
    protected Indirizzo domicilio;
    protected String numeroTesseraTeam;
    protected String numeroTesseraSanitaria;

    /**
     * Recupera il valore della proprietÃ  indirizzoEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    /**
     * Imposta il valore della proprietÃ  indirizzoEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoEmail(String value) {
        this.indirizzoEmail = value;
    }

    /**
     * Recupera il valore della proprietÃ  telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Imposta il valore della proprietÃ  telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Gets the value of the notificheAPaziente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notificheAPaziente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotificheAPaziente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NotificaAPaziente }
     * 
     * 
     */
    public List<NotificaAPaziente> getNotificheAPaziente() {
        if (notificheAPaziente == null) {
            notificheAPaziente = new ArrayList<NotificaAPaziente>();
        }
        return this.notificheAPaziente;
    }

    /**
     * Recupera il valore della proprietÃ  foto.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * Imposta il valore della proprietÃ  foto.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFoto(byte[] value) {
        this.foto = value;
    }

    /**
     * Recupera il valore della proprietÃ  cittadinanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCittadinanza() {
        return cittadinanza;
    }

    /**
     * Imposta il valore della proprietÃ  cittadinanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCittadinanza(String value) {
        this.cittadinanza = value;
    }

    /**
     * Recupera il valore della proprietÃ  aslDiResidenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAslDiResidenza() {
        return aslDiResidenza;
    }

    /**
     * Imposta il valore della proprietÃ  aslDiResidenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAslDiResidenza(String value) {
        this.aslDiResidenza = value;
    }

    /**
     * Recupera il valore della proprietÃ  residenza.
     * 
     * @return
     *     possible object is
     *     {@link Indirizzo }
     *     
     */
    public Indirizzo getResidenza() {
        return residenza;
    }

    /**
     * Imposta il valore della proprietÃ  residenza.
     * 
     * @param value
     *     allowed object is
     *     {@link Indirizzo }
     *     
     */
    public void setResidenza(Indirizzo value) {
        this.residenza = value;
    }

    /**
     * Recupera il valore della proprietÃ  aslDiDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAslDiDomicilio() {
        return aslDiDomicilio;
    }

    /**
     * Imposta il valore della proprietÃ  aslDiDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAslDiDomicilio(String value) {
        this.aslDiDomicilio = value;
    }

    /**
     * Recupera il valore della proprietÃ  domicilio.
     * 
     * @return
     *     possible object is
     *     {@link Indirizzo }
     *     
     */
    public Indirizzo getDomicilio() {
        return domicilio;
    }

    /**
     * Imposta il valore della proprietÃ  domicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link Indirizzo }
     *     
     */
    public void setDomicilio(Indirizzo value) {
        this.domicilio = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroTesseraTeam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTesseraTeam() {
        return numeroTesseraTeam;
    }

    /**
     * Imposta il valore della proprietÃ  numeroTesseraTeam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTesseraTeam(String value) {
        this.numeroTesseraTeam = value;
    }

    /**
     * Recupera il valore della proprietÃ  numeroTesseraSanitaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTesseraSanitaria() {
        return numeroTesseraSanitaria;
    }

    /**
     * Imposta il valore della proprietÃ  numeroTesseraSanitaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTesseraSanitaria(String value) {
        this.numeroTesseraSanitaria = value;
    }

}
