/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.enumerator;

public enum ErrorParamEnum {
	
	X_REQUEST_ID("X-Request-Id (Numero Transazione)"),
	SHIB_IDENTITA_CODICEFISCALE("Codice Fiscale Richiedente"),
	X_FORWARDED_FOR("X-Forwarded-For (IP Richiedente)"),
	X_CODICE_SERVIZIO("Codice Servizio"),
	X_CODICE_VERTICALE("Codice verticale richiedente"),
	RUOLO("Ruolo"),
	AUDIT("Audit"),
	COLLOCAZIONE("Collocazione"),
	ID_AURA("IdAura"),
	ID_IREC("IdIrec"),
	REGIME("Regime"),
	CIT_ID("Cit-Id"),
	IDENTIFICATIVO_ASSISTITO_CONSENSO("IdentificativoAssistitoConsenso"),
	LIMIT("Limit"),
	OFFSET("Offset"),
	ID_EPISODIO("IdEpisodio"),
	CODICE_CL("CodiceCL"),
	DATA_NASCITA("Data Nascita"),
	NOME("Nome"),
	COGNOME("Cognome"),
	TIPOLOGIA_DOCUMENTO("Tipologia documento"),
	CATEGORIA_TIPO_DOCUMENTO("Categoria Tipologia documento"),
	TIPO_EPISODIO("Tipo Episodio"),
	TIPO_MEDICO("Tipo Medico"),
	CATEGORIA("Categoria"),
	FILTRO_DATE("Filtro date"),
	DATA_INIZIO("Data inizio"),
	DATA_FINE("Data fine"),
	DATI_PAZIENTE("Codice Fiscale o Nome e Cognome"), 
	CATEGORIA_TIPOLOGIA_DOCUMENTO("Categoria o tipologia documento"),
	TIPO_CORRELAZIONE_DOCUMENTO("Tipo correlazione documento"),
	IDENTIFICATIVO_ORGANIZZAZIONE("Identificativo organizzazione"),
	IDENTIFICATIVO_STRUTTURA("Identificativo struttura"),
	ID_INFORMATIVA("Id informativa"),
	ID_DOCUMENTO_ILEC("idDocumentoIlec"),
	CODICE_DOCUMENTO("Codice Documento"),
	CONSENSO_ALIMENTAZIONE("Consenso all alimentazione"),
	CONSENSO_CONSULATAZIONE("Consenso alla consultazione"),
	IDENTIFICATIVO_DOCUMENTO("Identificativo documento"),
	IDENTIFICATIVO_ORGANIZZAZIONE_DOC("Identificativo organizzazione documento"),
	CONTESTO_OPERATIVO("contesto operativo"),
	IDENTIFICATIVO_REPOSITORY("Identificativo repository"),
	PRESA_IN_CARICO("Presa in carico"),
	STRUTTURA_UTENTE("Struttura utente"),
	TIPO_ATTIVITA("Tipo attivita'"),
	TIPO_DOCUMENTO("Tipo documento"),
	CONSENSO_PREGRESSO("Consenso al pregresso"),
	DOCUMENTO_OSCURATO("Documento oscurato"),
	TOKEN_DOCUMENTO("Token del documento"),
    CODICE_AUDIT("Codice Audit");
	
	
	
	private final String code;
	
	ErrorParamEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
