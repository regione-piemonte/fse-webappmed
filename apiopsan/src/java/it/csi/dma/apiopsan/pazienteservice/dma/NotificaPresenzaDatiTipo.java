/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per notificaPresenzaDatiTipo.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="notificaPresenzaDatiTipo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AS"/&gt;
 *     &lt;enumeration value="ROPVA"/&gt;
 *     &lt;enumeration value="IMR"/&gt;
 *     &lt;enumeration value="DOCUMENTI_PERSONALI"/&gt;
 *     &lt;enumeration value="MISURAZIONI"/&gt;
 *     &lt;enumeration value="RILEVAZIONI_ALIMENTARI"/&gt;
 *     &lt;enumeration value="ASSUNZIONE_FARMACI"/&gt;
 *     &lt;enumeration value="NOTE"/&gt;
 *     &lt;enumeration value="APPLICAZIONE_REGIONALE"/&gt;
 *     &lt;enumeration value="UNDEFINED_VALUE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "notificaPresenzaDatiTipo")
@XmlEnum
public enum NotificaPresenzaDatiTipo {

    AS,
    ROPVA,
    IMR,
    DOCUMENTI_PERSONALI,
    MISURAZIONI,
    RILEVAZIONI_ALIMENTARI,
    ASSUNZIONE_FARMACI,
    NOTE,
    APPLICAZIONE_REGIONALE,
    UNDEFINED_VALUE;

    public String value() {
        return name();
    }

    public static NotificaPresenzaDatiTipo fromValue(String v) {
        return valueOf(v);
    }

}
