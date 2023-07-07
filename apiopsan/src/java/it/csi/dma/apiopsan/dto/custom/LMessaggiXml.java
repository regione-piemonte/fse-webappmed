/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.dto.custom;


public class LMessaggiXml extends LMessaggi {

	private String xmlIn;
	private String xmlOut;
	private long id;
	
	public String getXmlIn() {
		return xmlIn;
	}
	public void setXmlIn(String xmlIn) {
		this.xmlIn = xmlIn;
	}
	public String getXmlOut() {
		return xmlOut;
	}
	public void setXmlOut(String xmlOut) {
		this.xmlOut = xmlOut;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
