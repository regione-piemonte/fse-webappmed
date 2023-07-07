/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.enumerator;

public enum RestituibileEnum {
		MEDICO_REFERTANTE(true,"001","Documento restituito al refertante e/o validante"),
		OSCURATO_ESENZIONI(false,"010","Documento non restituito perchÃ¨ esenzioni oscurate"),
		OSCURATO_NRE(false,"011","Documento non restituito perchÃ¨ con NRE oscurato"),
		OSCURATO_GENITORE(false,"012","Documento non restituito perchÃ¨ oscurato a genitore"),
		OSCURATO_PUNTUALE(false,"006","Documento non restituito perchÃ¨ oscurato"),
		TICKET_PAGATO(true,"002", "Documento restituito perchÃ¨ non oscurato, pagato ticket e consenso alla consultazione S"),
		REGIME_EMERGENZA(true,"003","Documento restituito perchÃ¨ regime Emergenza"),
		TICKET_NON_PAGATO(false,"007", "Documento non restituito perchÃ¨ ticket non pagato" ),
		CONSENSO_NEGATO(false,"008", "Documento non restituito per consenso alla consultazione negato"),
		OSCURATO_PUNTUALE_PSS(false,"005","Documento PSS non restituito perchÃ¨ oscurato"),
		OSCURATO_GENITORE_PSS(false,"013","Documento PSS non restituito perchÃ¨ oscurato a genitore"),
		CONSENSO_NEGATO_PSS(false,"009","Documento non restituito per consenso alla consultazione negato"),
		REGIME_EMERGENZA_PSS(true,"004","Documento PSS restituito perchÃ¨ regime Emergenza"),
		;
		
	    private final boolean restituibile;
		private final String code;
		private final String desc;
		
		RestituibileEnum(boolean restituibile, String code, String desc) {
			this.restituibile = restituibile;
			this.code = code;
			this.desc = desc;
		}

		public boolean isRestituibile() {
			return restituibile;
		}

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

}
