/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RicercaDocumentiRichiesta_QNAME = new QName("http://dmaclbl.csi.it/", "ricercaDocumentiRichiesta");
    private final static QName _GetRicercaDocumentiIniResponse_QNAME = new QName("http://dmaclbl.csi.it/", "getRicercaDocumentiIniResponse");
    private final static QName _RecuperoDocumentoRichiesta_QNAME = new QName("http://dmaclbl.csi.it/", "recuperoDocumentoRichiesta");
    private final static QName _GetRecuperoDocumentiIniResponse_QNAME = new QName("http://dmaclbl.csi.it/", "getRecuperoDocumentiIniResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.external.cldocumentiiniservice.dmaclbl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RicercaDocumentiIniRequest }
     * 
     */
    public RicercaDocumentiIniRequest createRicercaDocumentiIniRequest() {
        return new RicercaDocumentiIniRequest();
    }

    /**
     * Create an instance of {@link RicercaDocumentiIniResponse }
     * 
     */
    public RicercaDocumentiIniResponse createRicercaDocumentiIniResponse() {
        return new RicercaDocumentiIniResponse();
    }

    /**
     * Create an instance of {@link RecuperoDocumentoIniRequest }
     * 
     */
    public RecuperoDocumentoIniRequest createRecuperoDocumentoIniRequest() {
        return new RecuperoDocumentoIniRequest();
    }

    /**
     * Create an instance of {@link RecuperoDocumentoIniResponse }
     * 
     */
    public RecuperoDocumentoIniResponse createRecuperoDocumentoIniResponse() {
        return new RecuperoDocumentoIniResponse();
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDocumentiIniRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaDocumentiIniRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "ricercaDocumentiRichiesta")
    public JAXBElement<RicercaDocumentiIniRequest> createRicercaDocumentiRichiesta(RicercaDocumentiIniRequest value) {
        return new JAXBElement<RicercaDocumentiIniRequest>(_RicercaDocumentiRichiesta_QNAME, RicercaDocumentiIniRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RicercaDocumentiIniResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RicercaDocumentiIniResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getRicercaDocumentiIniResponse")
    public JAXBElement<RicercaDocumentiIniResponse> createGetRicercaDocumentiIniResponse(RicercaDocumentiIniResponse value) {
        return new JAXBElement<RicercaDocumentiIniResponse>(_GetRicercaDocumentiIniResponse_QNAME, RicercaDocumentiIniResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecuperoDocumentoIniRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RecuperoDocumentoIniRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "recuperoDocumentoRichiesta")
    public JAXBElement<RecuperoDocumentoIniRequest> createRecuperoDocumentoRichiesta(RecuperoDocumentoIniRequest value) {
        return new JAXBElement<RecuperoDocumentoIniRequest>(_RecuperoDocumentoRichiesta_QNAME, RecuperoDocumentoIniRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecuperoDocumentoIniResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RecuperoDocumentoIniResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getRecuperoDocumentiIniResponse")
    public JAXBElement<RecuperoDocumentoIniResponse> createGetRecuperoDocumentiIniResponse(RecuperoDocumentoIniResponse value) {
        return new JAXBElement<RecuperoDocumentoIniResponse>(_GetRecuperoDocumentiIniResponse_QNAME, RecuperoDocumentoIniResponse.class, null, value);
    }

}
