/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfiguration {

	private static final Logger log = Logger.getLogger(EnvironmentConfiguration.class);

	@Value("${configuratore.base.url}")
	private String baseUrlConfiguratore;

	@Value("${configuratore.auth.base64}")
	private String authB64Configuratore;

	public String getBaseUrlConfiguratore() {
		return baseUrlConfiguratore;
	}

	public void setBaseUrlConfiguratore(String baseUrlConfiguratore) {
		this.baseUrlConfiguratore = baseUrlConfiguratore;
	}

	public String getAuthB64Configuratore() {
		return authB64Configuratore;
	}

	public void setAuthB64Configuratore(String authB64Configuratore) {
		this.authB64Configuratore = authB64Configuratore;
	}

}
