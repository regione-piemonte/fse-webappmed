/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.exception;
public class UtenteConfiguratoreIPException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762509563188248518L;

	private Integer status;
	private String code;
	
	public UtenteConfiguratoreIPException(String message) {
		super(message);
	}

	public UtenteConfiguratoreIPException(String message,Integer status,String code) {
		super(message);
		this.status= status;
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
