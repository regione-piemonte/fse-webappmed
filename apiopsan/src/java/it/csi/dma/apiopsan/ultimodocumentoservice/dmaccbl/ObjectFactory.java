/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.ultimodocumentoservice.dmaccbl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import it.csi.dma.apiopsan.ultimodocumentoservice.dmacc.GetUltimoDocumentoRequest;
import it.csi.dma.apiopsan.ultimodocumentoservice.dmacc.GetUltimoDocumentoResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.ultimodocumentoservice.dma.apiopsan.ultimodocumentoservice.dmaccbl package. 
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

    private final static QName _GetUltimoDocumentoRequest_QNAME = new QName("http://dmaccbl.csi.it/", "getUltimoDocumentoRequest");
    private final static QName _GetUltimoDocumentoResponse_QNAME = new QName("http://dmaccbl.csi.it/", "getUltimoDocumentoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.ultimodocumentoservice.dma.apiopsan.ultimodocumentoservice.dmaccbl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUltimoDocumentoRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUltimoDocumentoRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "getUltimoDocumentoRequest")
    public JAXBElement<GetUltimoDocumentoRequest> createGetUltimoDocumentoRequest(GetUltimoDocumentoRequest value) {
        return new JAXBElement<GetUltimoDocumentoRequest>(_GetUltimoDocumentoRequest_QNAME, GetUltimoDocumentoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUltimoDocumentoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUltimoDocumentoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "getUltimoDocumentoResponse")
    public JAXBElement<GetUltimoDocumentoResponse> createGetUltimoDocumentoResponse(GetUltimoDocumentoResponse value) {
        return new JAXBElement<GetUltimoDocumentoResponse>(_GetUltimoDocumentoResponse_QNAME, GetUltimoDocumentoResponse.class, null, value);
    }

}
