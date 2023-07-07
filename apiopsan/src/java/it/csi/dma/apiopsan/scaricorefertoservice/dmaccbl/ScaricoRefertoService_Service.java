/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


/*
 * 
 */

package it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.7
 * Wed Sep 14 15:36:17 CEST 2022
 * Generated source version: 2.2.7
 * 
 */


@WebServiceClient(name = "ScaricoRefertoService", 
		wsdlLocation = "file:../ScaricoRefertoService.wsdl",
                  targetNamespace = "http://dmaccbl.csi.it/") 
public class ScaricoRefertoService_Service extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://dmaccbl.csi.it/", "ScaricoRefertoService");
    public final static QName ScaricoRefertoServicePort = new QName("http://dmaccbl.csi.it/", "ScaricoRefertoServicePort");
    static {
        URL url = null;
        try {
            url = new URL("https://tst-bejava-dma.isan.csi.it/dmaccrti/ScaricoRefertoService?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from https://tst-bejava-dma.isan.csi.it/dmaccrti/ScaricoRefertoService?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ScaricoRefertoService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ScaricoRefertoService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ScaricoRefertoService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns ScaricoRefertoService
     */
    @WebEndpoint(name = "ScaricoRefertoServicePort")
    public ScaricoRefertoService getScaricoRefertoServicePort() {
        return super.getPort(ScaricoRefertoServicePort, ScaricoRefertoService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ScaricoRefertoService
     */
    @WebEndpoint(name = "ScaricoRefertoServicePort")
    public ScaricoRefertoService getScaricoRefertoServicePort(WebServiceFeature... features) {
        return super.getPort(ScaricoRefertoServicePort, ScaricoRefertoService.class, features);
    }

}
