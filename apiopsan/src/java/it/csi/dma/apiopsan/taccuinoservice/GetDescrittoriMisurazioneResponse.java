/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getDescrittoriMisurazioneResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getDescrittoriMisurazioneResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dmaclbluc.dma.csi.it/}serviceResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listaDescrittoreMisurazione" type="{http://dmaclbluc.dma.csi.it/}listaDescrittoreMisurazione" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDescrittoriMisurazioneResponse", propOrder = {
    "listaDescrittoreMisurazione"
})
public class GetDescrittoriMisurazioneResponse
    extends ServiceResponse
{

    protected ListaDescrittoreMisurazione listaDescrittoreMisurazione;

    /**
     * Recupera il valore della proprietÃ  listaDescrittoreMisurazione.
     * 
     * @return
     *     possible object is
     *     {@link ListaDescrittoreMisurazione }
     *     
     */
    public ListaDescrittoreMisurazione getListaDescrittoreMisurazione() {
        return listaDescrittoreMisurazione;
    }

    /**
     * Imposta il valore della proprietÃ  listaDescrittoreMisurazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDescrittoreMisurazione }
     *     
     */
    public void setListaDescrittoreMisurazione(ListaDescrittoreMisurazione value) {
        this.listaDescrittoreMisurazione = value;
    }

}
