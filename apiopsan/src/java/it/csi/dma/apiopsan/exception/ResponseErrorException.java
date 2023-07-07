/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.exception;

import it.csi.dma.apiopsan.util.ErrorBuilder;

public class ResponseErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2134345368882934388L;

	private final transient ErrorBuilder responseError;
	private Integer status;
	

	public ResponseErrorException(ErrorBuilder errorBuilder, Throwable message) {
		super(message);
		this.responseError = errorBuilder;
	}

	public ResponseErrorException(ErrorBuilder errorBuilder, String message) {
		super(message);
		this.responseError = errorBuilder;
	}
	
	public ResponseErrorException(ErrorBuilder errorBuilder, String message, Integer status) {
		super(message);
		this.responseError = errorBuilder;
		this.status = status;
	}

	@SuppressWarnings("unused")
	private ResponseErrorException() {
		this.responseError = null;
	}

	public ErrorBuilder getResponseError() {
		return responseError;
	}
	
	public Integer getStatus() {
		return status;
	}

}
