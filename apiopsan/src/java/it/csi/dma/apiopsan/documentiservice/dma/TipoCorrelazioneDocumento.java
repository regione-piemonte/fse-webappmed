/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dma;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tipoCorrelazioneDocumento.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="tipoCorrelazioneDocumento"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NRE"/&gt;
 *     &lt;enumeration value="NUMERO_SDO"/&gt;
 *     &lt;enumeration value="NUMERO_PASSAGGIO_PS"/&gt;
 *     &lt;enumeration value="PADRE_FIGLIO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoCorrelazioneDocumento")
@XmlEnum
public enum TipoCorrelazioneDocumento {

    NRE,
    NUMERO_SDO,
    NUMERO_PASSAGGIO_PS,
    PADRE_FIGLIO;

    public String value() {
        return name();
    }

    public static TipoCorrelazioneDocumento fromValue(String v) {
        return valueOf(v);
    }

}
