/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per criterioOrdinamento.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="criterioOrdinamento"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DATA"/&gt;
 *     &lt;enumeration value="TIPO_DOC"/&gt;
 *     &lt;enumeration value="MEDICO"/&gt;
 *     &lt;enumeration value="REGIONE"/&gt;
 *     &lt;enumeration value="AZIENDA"/&gt;
 *     &lt;enumeration value="STRUTTURA"/&gt;
 *     &lt;enumeration value="UNITA_OPERATIVA"/&gt;
 *     &lt;enumeration value="MEDIATO"/&gt;
 *     &lt;enumeration value="DA_MEDIARE"/&gt;
 *     &lt;enumeration value="UNDEFINED_VALUE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "criterioOrdinamento")
@XmlEnum
public enum CriterioOrdinamento {

    DATA,
    TIPO_DOC,
    MEDICO,
    REGIONE,
    AZIENDA,
    STRUTTURA,
    UNITA_OPERATIVA,
    MEDIATO,
    DA_MEDIARE,
    UNDEFINED_VALUE;

    public String value() {
        return name();
    }

    public static CriterioOrdinamento fromValue(String v) {
        return valueOf(v);
    }

}
