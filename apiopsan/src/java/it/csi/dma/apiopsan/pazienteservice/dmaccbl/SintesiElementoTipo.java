/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dmaccbl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per sintesiElementoTipo.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="sintesiElementoTipo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="EPISODI"/&gt;
 *     &lt;enumeration value="DOCUMENTI"/&gt;
 *     &lt;enumeration value="ROPVA"/&gt;
 *     &lt;enumeration value="FARMACI"/&gt;
 *     &lt;enumeration value="VACCINAZIONI"/&gt;
 *     &lt;enumeration value="SCREENING"/&gt;
 *     &lt;enumeration value="ESENZIONI"/&gt;
 *     &lt;enumeration value="PABI"/&gt;
 *     &lt;enumeration value="MARARE"/&gt;
 *     &lt;enumeration value="DOCUMENTI_PERSONALI"/&gt;
 *     &lt;enumeration value="MISURAZIONI"/&gt;
 *     &lt;enumeration value="RILEVAZIONI_ALIMENTARI"/&gt;
 *     &lt;enumeration value="NOTE"/&gt;
 *     &lt;enumeration value="ASSUNZIONE_FARMACI"/&gt;
 *     &lt;enumeration value="APPREG"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "sintesiElementoTipo")
@XmlEnum
public enum SintesiElementoTipo {

    EPISODI,
    DOCUMENTI,
    ROPVA,
    FARMACI,
    VACCINAZIONI,
    SCREENING,
    ESENZIONI,
    PABI,
    MARARE,
    DOCUMENTI_PERSONALI,
    MISURAZIONI,
    RILEVAZIONI_ALIMENTARI,
    NOTE,
    ASSUNZIONE_FARMACI,
    APPREG;

    public String value() {
        return name();
    }

    public static SintesiElementoTipo fromValue(String v) {
        return valueOf(v);
    }

}
