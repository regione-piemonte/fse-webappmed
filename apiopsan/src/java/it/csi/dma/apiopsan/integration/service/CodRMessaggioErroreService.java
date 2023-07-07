/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import javax.servlet.http.HttpServletRequest;

import it.csi.dma.apiopsan.util.ErrorBuilder;

public interface CodRMessaggioErroreService {

	ErrorBuilder saveError(ErrorBuilder error, HttpServletRequest httpRequest,String uid);

}