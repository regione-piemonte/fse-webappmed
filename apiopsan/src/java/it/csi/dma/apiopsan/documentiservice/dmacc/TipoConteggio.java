/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.documentiservice.dmacc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tipoConteggio.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="tipoConteggio"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AREA_BLU"/&gt;
 *     &lt;enumeration value="ETICHETTA"/&gt;
 *     &lt;enumeration value="EPISODIO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoConteggio")
@XmlEnum
public enum TipoConteggio {

    AREA_BLU,
    ETICHETTA,
    EPISODIO;

    public String value() {
        return name();
    }

    public static TipoConteggio fromValue(String v) {
        return valueOf(v);
    }

}
