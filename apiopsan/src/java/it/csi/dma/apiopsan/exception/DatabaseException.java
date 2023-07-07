/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.exception;

public class DatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8763582816142055237L;

	public DatabaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DatabaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DatabaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
