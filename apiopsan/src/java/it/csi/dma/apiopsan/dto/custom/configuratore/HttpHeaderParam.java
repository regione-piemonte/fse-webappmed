/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom.configuratore;

import java.io.Serializable;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

public class HttpHeaderParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shibIdentitaCodiceFiscale;
	private String xRequestId;
	private String xForwardedFor;
	private String xCodiceServizio;
	private String profiloCod;
	private String strutturaCod;
	private String token;
	
	public String getShibIdentitaCodiceFiscale() {
		return shibIdentitaCodiceFiscale;
	}
	public void setShibIdentitaCodiceFiscale(String shibIdentitaCodiceFiscale) {
		this.shibIdentitaCodiceFiscale = shibIdentitaCodiceFiscale;
	}
	public String getxRequestId() {
		return xRequestId;
	}
	public void setxRequestId(String xRequestId) {
		this.xRequestId = xRequestId;
	}
	public String getxForwardedFor() {
		return xForwardedFor;
	}
	public void setxForwardedFor(String xForwardedFor) {
		this.xForwardedFor = xForwardedFor;
	}
	public String getxCodiceServizio() {
		return xCodiceServizio;
	}
	public void setxCodiceServizio(String xCodiceServizio) {
		this.xCodiceServizio = xCodiceServizio;
	}
	public String getProfiloCod() {
		return profiloCod;
	}
	public void setProfiloCod(String profiloCod) {
		this.profiloCod = profiloCod;
	}
	public String getStrutturaCod() {
		return strutturaCod;
	}
	public void setStrutturaCod(String strutturaCod) {
		this.strutturaCod = strutturaCod;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public HttpHeaderParam(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String token) {
		super();
		this.shibIdentitaCodiceFiscale = shibIdentitaCodiceFiscale;
		this.xRequestId = xRequestId;
		this.xForwardedFor = xForwardedFor;
		this.xCodiceServizio = xCodiceServizio;
		this.token = token;
	}
	
	public HttpHeaderParam(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String profiloCod, String strutturaCod) {
		super();
		this.shibIdentitaCodiceFiscale = shibIdentitaCodiceFiscale;
		this.xRequestId = xRequestId;
		this.xForwardedFor = xForwardedFor;
		this.xCodiceServizio = xCodiceServizio;
		this.profiloCod = profiloCod;
		this.strutturaCod = strutturaCod;
	}
	@Override
	public String toString() {
		return "HttpHeaderParam [shibIdentitaCodiceFiscale=" + shibIdentitaCodiceFiscale + ", xRequestId=" + xRequestId
				+ ", xForwardedFor=" + xForwardedFor + ", xCodiceServizio=" + xCodiceServizio + ", profiloCod="
				+ profiloCod + ", strutturaCod=" + strutturaCod + ", token=" + token + "]";
	}
	
}
