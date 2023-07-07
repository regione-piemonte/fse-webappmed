/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dma;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pagatoTicketStato.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="pagatoTicketStato">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="F"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "pagatoTicketStato")
@XmlEnum
public enum PagatoTicketStato {

    S,
    N,
    E,
    P,
    U,
    R,
    F;

    public String value() {
        return name();
    }

    public static PagatoTicketStato fromValue(String v) {
        return valueOf(v);
    }

}
