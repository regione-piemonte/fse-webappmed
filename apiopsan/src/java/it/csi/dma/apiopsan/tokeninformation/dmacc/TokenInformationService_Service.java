/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.tokeninformation.dmacc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.5.3
 * 2022-10-29T18:45:38.640+02:00
 * Generated source version: 3.5.3
 *
 */
@WebServiceClient(name = "TokenInformationService",
                  wsdlLocation = "file:/C:/csi/Fascicolo/TokenInformationService.wsdl",
                  targetNamespace = "http://dmacc.csi.it/")
public class TokenInformationService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://dmacc.csi.it/", "TokenInformationService");
    public final static QName TokenInformationService = new QName("http://dmacc.csi.it/", "TokenInformationService");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/csi/Fascicolo/TokenInformationService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(TokenInformationService_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "file:/C:/csi/Fascicolo/TokenInformationService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public TokenInformationService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public TokenInformationService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TokenInformationService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public TokenInformationService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public TokenInformationService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public TokenInformationService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns TokenInformationService
     */
    @WebEndpoint(name = "TokenInformationService")
    public TokenInformationService getTokenInformationService() {
        return super.getPort(TokenInformationService, TokenInformationService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TokenInformationService
     */
    @WebEndpoint(name = "TokenInformationService")
    public TokenInformationService getTokenInformationService(WebServiceFeature... features) {
        return super.getPort(TokenInformationService, TokenInformationService.class, features);
    }

}
