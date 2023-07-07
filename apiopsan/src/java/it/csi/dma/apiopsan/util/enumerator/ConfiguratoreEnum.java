/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.enumerator;

public enum ConfiguratoreEnum {
	// PAZIENTE GESTITO DA ALTRE STRUTTURE
	FORBIDDEN_403_ACCESSO_VIETATO_AL_PAZIENTE("SEPAC_001","Accesso vietato al paziente"),
	BAD_REQUEST_400_PAZIENTE_ESISTENTE_STESSA_STRUTTURA("SEPAC_002","Paziente esistente nella stessa strutturra"),
	BAD_REQUEST_400_PAZIENTE_ESCLUSO("SEPAC_003","Paziente escluso in precendenza"),
	BAD_REQUEST_400_PAZIENTE_PRESENTE_SU_AURA("SEPAC_004","Paziente presente su aura"),
	BAD_REQUEST_400_PAYLOAD_INVALID("400","Payload non valido"),
	BAD_REQUEST_400_CFSTP_NOT_IN_ANAGRAFICA_AND_AURA("400","Paziente non trovato in anagrafica, stp non presenti in aura"),
	NOT_FOUND_404_PAZIENTE_NON_TROVATO("404", "Paziente non trovato"),
	NOT_FOUND_404_TEST_NON_TROVATO("404", "Test non trovato"),
	ECCEZIONE_CONFIGURATORE("SEPAC_005","Ip Address non congruente con quello fornito al momento della generazione del token"),
	ECCEZIONE_CONFIGURATORE_GEN("SEPAC_006","%s"),
	FORBIDDEN_403_PAZIENTE_NON_ASSOCIATO_AL_CP("SEPAC_007","Paziente non ancora in carico a centro prescrittore (test negativo/test non effettuato) "),
	BAD_REQUEST_400_APPUNTAMENTO_ATTIVO("SEPAC_008","Il paziente possiede un appuntamento"),
	UNAUTHORIZED_401("401","Operazione non concessa per questo ruolo: %s");
	private final String errorCode;
	private final String errorMessage;
	
	
	private ConfiguratoreEnum(String errorCode,String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	
}
