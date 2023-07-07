/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per contattoTipoCodice.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="contattoTipoCodice"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VIS"/&gt;
 *     &lt;enumeration value="RIC"/&gt;
 *     &lt;enumeration value="DIA"/&gt;
 *     &lt;enumeration value="TRATMED"/&gt;
 *     &lt;enumeration value="PS"/&gt;
 *     &lt;enumeration value="ALTRO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "contattoTipoCodice")
@XmlEnum
public enum ContattoTipoCodice {

    VIS,
    RIC,
    DIA,
    TRATMED,
    PS,
    ALTRO;

    public String value() {
        return name();
    }

    public static ContattoTipoCodice fromValue(String v) {
        return valueOf(v);
    }

}
