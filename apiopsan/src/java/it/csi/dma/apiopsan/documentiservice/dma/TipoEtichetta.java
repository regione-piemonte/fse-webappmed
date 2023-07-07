/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dma;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tipoEtichetta.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="tipoEtichetta"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PERSONALE"/&gt;
 *     &lt;enumeration value="ANATOMICA"/&gt;
 *     &lt;enumeration value="ENTRAMBE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoEtichetta")
@XmlEnum
public enum TipoEtichetta {

    PERSONALE,
    ANATOMICA,
    ENTRAMBE;

    public String value() {
        return name();
    }

    public static TipoEtichetta fromValue(String v) {
        return valueOf(v);
    }

}
