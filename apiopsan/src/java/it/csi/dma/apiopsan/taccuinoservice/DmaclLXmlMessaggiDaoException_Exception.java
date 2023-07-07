/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.taccuinoservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.5.3
 * 2022-09-29T12:11:09.581+02:00
 * Generated source version: 3.5.3
 */

@WebFault(name = "DmaclLXmlMessaggiDaoException", targetNamespace = "http://dmaclbluc.dma.csi.it/")
public class DmaclLXmlMessaggiDaoException_Exception extends Exception {

    private it.csi.dma.apiopsan.taccuinoservice.DmaclLXmlMessaggiDaoException faultInfo;

    public DmaclLXmlMessaggiDaoException_Exception() {
        super();
    }

    public DmaclLXmlMessaggiDaoException_Exception(String message) {
        super(message);
    }

    public DmaclLXmlMessaggiDaoException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public DmaclLXmlMessaggiDaoException_Exception(String message, it.csi.dma.apiopsan.taccuinoservice.DmaclLXmlMessaggiDaoException dmaclLXmlMessaggiDaoException) {
        super(message);
        this.faultInfo = dmaclLXmlMessaggiDaoException;
    }

    public DmaclLXmlMessaggiDaoException_Exception(String message, it.csi.dma.apiopsan.taccuinoservice.DmaclLXmlMessaggiDaoException dmaclLXmlMessaggiDaoException, java.lang.Throwable cause) {
        super(message, cause);
        this.faultInfo = dmaclLXmlMessaggiDaoException;
    }

    public it.csi.dma.apiopsan.taccuinoservice.DmaclLXmlMessaggiDaoException getFaultInfo() {
        return this.faultInfo;
    }
}
