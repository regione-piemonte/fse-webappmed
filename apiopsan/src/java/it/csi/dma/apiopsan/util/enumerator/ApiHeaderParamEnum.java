/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.enumerator;

public enum ApiHeaderParamEnum {

	SHIB_IDENTITA_CODICEFISCALE("Shib-Identita-CodiceFiscale"),
	X_REQUEST_ID("X-Request-Id"),
	X_APPLICAZIONE_CODICE("X-Applicazione-Codice"),
	X_FORWARDED_FOR("X-Forwarded-For"),
	TOKEN("Token"),
	AUTHORIZATION("Authorization"),
	X_CODICE_SERVIZIO("X-Codice-Servizio"),
	USER_AGENT("User-Agent");
	
	private final String code;

	private ApiHeaderParamEnum(String inCode) {
		this.code = inCode;
	}

	public String getCode() {
		return code;
	}

	
}
