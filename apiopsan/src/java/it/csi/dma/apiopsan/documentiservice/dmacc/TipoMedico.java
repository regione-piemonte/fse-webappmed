/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tipoMedico.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="tipoMedico"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VALIDANTE"/&gt;
 *     &lt;enumeration value="REFERTANTE"/&gt;
 *     &lt;enumeration value="ENTRAMBI"/&gt;
 *     &lt;enumeration value="VALIDANTEOREFERTANTE"/&gt;
 *     &lt;enumeration value="UNDEFINED_VALUE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoMedico")
@XmlEnum
public enum TipoMedico {

    VALIDANTE,
    REFERTANTE,
    ENTRAMBI,
    VALIDANTEOREFERTANTE,
    UNDEFINED_VALUE;

    public String value() {
        return name();
    }

    public static TipoMedico fromValue(String v) {
        return valueOf(v);
    }

}
