/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per strutturaTipoCodice.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="strutturaTipoCodice"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="STRUT_SAN"/&gt;
 *     &lt;enumeration value="MNC"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "strutturaTipoCodice")
@XmlEnum
public enum StrutturaTipoCodice {

    STRUT_SAN,
    MNC;

    public String value() {
        return name();
    }

    public static StrutturaTipoCodice fromValue(String v) {
        return valueOf(v);
    }

}
