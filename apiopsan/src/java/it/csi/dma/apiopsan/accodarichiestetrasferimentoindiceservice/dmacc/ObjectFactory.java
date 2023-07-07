/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.accodarichiestetrasferimentoindiceservice.dmacc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.accodarichiestatrasferimentoindiceservice.dmacc package. 
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

    private final static QName _AccodaRichiesteTrasferimentoIndice_QNAME = new QName("http://dmacc.csi.it/", "accodaRichiesteTrasferimentoIndice");
    private final static QName _AccodaRichiesteTrasferimentoIndiceResponse_QNAME = new QName("http://dmacc.csi.it/", "accodaRichiesteTrasferimentoIndiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.accodarichiestatrasferimentoindiceservice.dmacc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccodaRichiesteTrasferimentoIndice }
     * 
     */
    public AccodaRichiesteTrasferimentoIndice createAccodaRichiesteTrasferimentoIndice() {
        return new AccodaRichiesteTrasferimentoIndice();
    }

    /**
     * Create an instance of {@link AccodaRichiesteTrasferimentoIndiceResponse }
     * 
     */
    public AccodaRichiesteTrasferimentoIndiceResponse createAccodaRichiesteTrasferimentoIndiceResponse() {
        return new AccodaRichiesteTrasferimentoIndiceResponse();
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link EnsResponse }
     * 
     */
    public EnsResponse createEnsResponse() {
        return new EnsResponse();
    }

    /**
     * Create an instance of {@link EnsMessagebody }
     * 
     */
    public EnsMessagebody createEnsMessagebody() {
        return new EnsMessagebody();
    }

    /**
     * Create an instance of {@link EnsRequest }
     * 
     */
    public EnsRequest createEnsRequest() {
        return new EnsRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccodaRichiesteTrasferimentoIndice }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AccodaRichiesteTrasferimentoIndice }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "accodaRichiesteTrasferimentoIndice")
    public JAXBElement<AccodaRichiesteTrasferimentoIndice> createAccodaRichiesteTrasferimentoIndice(AccodaRichiesteTrasferimentoIndice value) {
        return new JAXBElement<AccodaRichiesteTrasferimentoIndice>(_AccodaRichiesteTrasferimentoIndice_QNAME, AccodaRichiesteTrasferimentoIndice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccodaRichiesteTrasferimentoIndiceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AccodaRichiesteTrasferimentoIndiceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "accodaRichiesteTrasferimentoIndiceResponse")
    public JAXBElement<AccodaRichiesteTrasferimentoIndiceResponse> createAccodaRichiesteTrasferimentoIndiceResponse(AccodaRichiesteTrasferimentoIndiceResponse value) {
        return new JAXBElement<AccodaRichiesteTrasferimentoIndiceResponse>(_AccodaRichiesteTrasferimentoIndiceResponse_QNAME, AccodaRichiesteTrasferimentoIndiceResponse.class, null, value);
    }

}
