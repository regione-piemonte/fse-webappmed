/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmacc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.external.cldocumentiservice.dmacc package. 
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

    private final static QName _SmediazioneDocumenti_QNAME = new QName("http://dmacc.csi.it/", "smediazioneDocumenti");
    private final static QName _SmediazioneDocumentiResponse_QNAME = new QName("http://dmacc.csi.it/", "smediazioneDocumentiResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.external.cldocumentiservice.dmacc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SmediazioneDocumentiRequest }
     * 
     */
    public SmediazioneDocumentiRequest createSmediazioneDocumentiRequest() {
        return new SmediazioneDocumentiRequest();
    }

    /**
     * Create an instance of {@link SmediazioneDocumentiResponse }
     * 
     */
    public SmediazioneDocumentiResponse createSmediazioneDocumentiResponse() {
        return new SmediazioneDocumentiResponse();
    }

    /**
     * Create an instance of {@link ApplicativoVerticale }
     * 
     */
    public ApplicativoVerticale createApplicativoVerticale() {
        return new ApplicativoVerticale();
    }

    /**
     * Create an instance of {@link Codifica }
     * 
     */
    public Codifica createCodifica() {
        return new Codifica();
    }

    /**
     * Create an instance of {@link ApplicazioneRichiedente }
     * 
     */
    public ApplicazioneRichiedente createApplicazioneRichiedente() {
        return new ApplicazioneRichiedente();
    }

    /**
     * Create an instance of {@link Errore }
     * 
     */
    public Errore createErrore() {
        return new Errore();
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
     * Create an instance of {@link ElementiSmediazione }
     * 
     */
    public ElementiSmediazione createElementiSmediazione() {
        return new ElementiSmediazione();
    }

    /**
     * Create an instance of {@link EnsRequest }
     * 
     */
    public EnsRequest createEnsRequest() {
        return new EnsRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentiRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentiRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "smediazioneDocumenti")
    public JAXBElement<SmediazioneDocumentiRequest> createSmediazioneDocumenti(SmediazioneDocumentiRequest value) {
        return new JAXBElement<SmediazioneDocumentiRequest>(_SmediazioneDocumenti_QNAME, SmediazioneDocumentiRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentiResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentiResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "smediazioneDocumentiResponse")
    public JAXBElement<SmediazioneDocumentiResponse> createSmediazioneDocumentiResponse(SmediazioneDocumentiResponse value) {
        return new JAXBElement<SmediazioneDocumentiResponse>(_SmediazioneDocumentiResponse_QNAME, SmediazioneDocumentiResponse.class, null, value);
    }

}
