/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.pazienteservice.dma;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eventoNotificaTipoCodice.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="eventoNotificaTipoCodice"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NUOVO_DOCUMENTO"/&gt;
 *     &lt;enumeration value="ANNULLA_DOCUMENTO"/&gt;
 *     &lt;enumeration value="SOSTITUZIONE_DOCUMENTO"/&gt;
 *     &lt;enumeration value="SCADENZA_DOCUMENTO"/&gt;
 *     &lt;enumeration value="NUOVO_DOCUMENTO_RIMBORSO_IMPORTI"/&gt;
 *     &lt;enumeration value="NUOVO_DOCUMENTO_RIMBORSO"/&gt;
 *     &lt;enumeration value="AGGIORNAMENTO_STATO_TICKET"/&gt;
 *     &lt;enumeration value="SOSTITUZIONE_DOCUMENTO_RIMBORSO_IMPORTI"/&gt;
 *     &lt;enumeration value="SOSTITUZIONE_DOCUMENTO_RIMBORSO"/&gt;
 *     &lt;enumeration value="NUOVO_DOCUMENTO_NON_SCARICABILE"/&gt;
 *     &lt;enumeration value="ANNULLAMENTO_DOCUMENTO_NON_SCARICABILE"/&gt;
 *     &lt;enumeration value="SOSTITUZIONE_DOCUMENTO_NON_SCARICABILE"/&gt;
 *     &lt;enumeration value="AGGIORNAMENTO_STATO_TICKET_NON_SCARICABILE"/&gt;
 *     &lt;enumeration value="UNDEFINED_VALUE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "eventoNotificaTipoCodice")
@XmlEnum
public enum EventoNotificaTipoCodice {

    NUOVO_DOCUMENTO,
    ANNULLA_DOCUMENTO,
    SOSTITUZIONE_DOCUMENTO,
    SCADENZA_DOCUMENTO,
    NUOVO_DOCUMENTO_RIMBORSO_IMPORTI,
    NUOVO_DOCUMENTO_RIMBORSO,
    AGGIORNAMENTO_STATO_TICKET,
    SOSTITUZIONE_DOCUMENTO_RIMBORSO_IMPORTI,
    SOSTITUZIONE_DOCUMENTO_RIMBORSO,
    NUOVO_DOCUMENTO_NON_SCARICABILE,
    ANNULLAMENTO_DOCUMENTO_NON_SCARICABILE,
    SOSTITUZIONE_DOCUMENTO_NON_SCARICABILE,
    AGGIORNAMENTO_STATO_TICKET_NON_SCARICABILE,
    UNDEFINED_VALUE;

    public String value() {
        return name();
    }

    public static EventoNotificaTipoCodice fromValue(String v) {
        return valueOf(v);
    }

}
