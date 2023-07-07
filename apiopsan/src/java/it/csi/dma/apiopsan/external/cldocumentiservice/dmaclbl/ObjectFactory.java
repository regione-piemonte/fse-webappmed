/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import it.csi.dma.apiopsan.external.cldocumentiservice.dma.RichiedenteInfo;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl package. 
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

    private final static QName _GetRefertiResponse_QNAME = new QName("http://dmaclbl.csi.it/", "GetRefertiResponse");
    private final static QName _GetRefertiRequest_QNAME = new QName("http://dmaclbl.csi.it/", "getRefertiRequest");
    private final static QName _RichiedenteInfo_QNAME = new QName("http://dmaclbl.csi.it/", "richiedenteInfo");
    private final static QName _GetDettaglioDocumento_QNAME = new QName("http://dmaclbl.csi.it/", "getDettaglioDocumento");
    private final static QName _RegistraAvvenutoRitiroResponse_QNAME = new QName("http://dmaclbl.csi.it/", "registraAvvenutoRitiroResponse");
    private final static QName _SmediazioneDocumentiRequest_QNAME = new QName("http://dmaclbl.csi.it/", "smediazioneDocumentiRequest");
    private final static QName _SmediazioneDocumentiResponse_QNAME = new QName("http://dmaclbl.csi.it/", "smediazioneDocumentiResponse");
    private final static QName _GetRefertiDocs_QNAME = new QName("http://dmaclbl.csi.it/", "getRefertiDocs");
    private final static QName _GetMieiRefertiDocsResponse_QNAME = new QName("http://dmaclbl.csi.it/", "getMieiRefertiDocsResponse");
    private final static QName _GetMieiRefertiResponse_QNAME = new QName("http://dmaclbl.csi.it/", "getMieiRefertiResponse");
    private final static QName _GetDettaglioDocumentoNOAS_QNAME = new QName("http://dmaclbl.csi.it/", "getDettaglioDocumentoNOAS");
    private final static QName _GetDettaglioDocumentoNOASResponse_QNAME = new QName("http://dmaclbl.csi.it/", "GetDettaglioDocumentoNOASResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRefertiResponse }
     * 
     */
    public GetRefertiResponse createGetRefertiResponse() {
        return new GetRefertiResponse();
    }

    /**
     * Create an instance of {@link GetRefertiRequest }
     * 
     */
    public GetRefertiRequest createGetRefertiRequest() {
        return new GetRefertiRequest();
    }

    /**
     * Create an instance of {@link GetDettaglioDocumentoRequest }
     * 
     */
    public GetDettaglioDocumentoRequest createGetDettaglioDocumentoRequest() {
        return new GetDettaglioDocumentoRequest();
    }

    /**
     * Create an instance of {@link RegistraAvvenutoRitiroResponse }
     * 
     */
    public RegistraAvvenutoRitiroResponse createRegistraAvvenutoRitiroResponse() {
        return new RegistraAvvenutoRitiroResponse();
    }

    /**
     * Create an instance of {@link SmediazioneDocumentoRequest }
     * 
     */
    public SmediazioneDocumentoRequest createSmediazioneDocumentoRequest() {
        return new SmediazioneDocumentoRequest();
    }

    /**
     * Create an instance of {@link SmediazioneDocumentoResponse }
     * 
     */
    public SmediazioneDocumentoResponse createSmediazioneDocumentoResponse() {
        return new SmediazioneDocumentoResponse();
    }

    /**
     * Create an instance of {@link GetDettaglioDocumentoResponse }
     * 
     */
    public GetDettaglioDocumentoResponse createGetDettaglioDocumentoResponse() {
        return new GetDettaglioDocumentoResponse();
    }

    /**
     * Create an instance of {@link Prestazioni }
     * 
     */
    public Prestazioni createPrestazioni() {
        return new Prestazioni();
    }

    /**
     * Create an instance of {@link Prestazione }
     * 
     */
    public Prestazione createPrestazione() {
        return new Prestazione();
    }

    /**
     * Create an instance of {@link BrancaSOL }
     * 
     */
    public BrancaSOL createBrancaSOL() {
        return new BrancaSOL();
    }

    /**
     * Create an instance of {@link InfoAggiuntive }
     * 
     */
    public InfoAggiuntive createInfoAggiuntive() {
        return new InfoAggiuntive();
    }

    /**
     * Create an instance of {@link RegistraAvvenutoRitiroRequest }
     * 
     */
    public RegistraAvvenutoRitiroRequest createRegistraAvvenutoRitiroRequest() {
        return new RegistraAvvenutoRitiroRequest();
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRefertiResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRefertiResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "GetRefertiResponse")
    public JAXBElement<GetRefertiResponse> createGetRefertiResponse(GetRefertiResponse value) {
        return new JAXBElement<GetRefertiResponse>(_GetRefertiResponse_QNAME, GetRefertiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRefertiRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRefertiRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getRefertiRequest")
    public JAXBElement<GetRefertiRequest> createGetRefertiRequest(GetRefertiRequest value) {
        return new JAXBElement<GetRefertiRequest>(_GetRefertiRequest_QNAME, GetRefertiRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RichiedenteInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RichiedenteInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "richiedenteInfo")
    public JAXBElement<RichiedenteInfo> createRichiedenteInfo(RichiedenteInfo value) {
        return new JAXBElement<RichiedenteInfo>(_RichiedenteInfo_QNAME, RichiedenteInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDettaglioDocumentoRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDettaglioDocumentoRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getDettaglioDocumento")
    public JAXBElement<GetDettaglioDocumentoRequest> createGetDettaglioDocumento(GetDettaglioDocumentoRequest value) {
        return new JAXBElement<GetDettaglioDocumentoRequest>(_GetDettaglioDocumento_QNAME, GetDettaglioDocumentoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistraAvvenutoRitiroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistraAvvenutoRitiroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "registraAvvenutoRitiroResponse")
    public JAXBElement<RegistraAvvenutoRitiroResponse> createRegistraAvvenutoRitiroResponse(RegistraAvvenutoRitiroResponse value) {
        return new JAXBElement<RegistraAvvenutoRitiroResponse>(_RegistraAvvenutoRitiroResponse_QNAME, RegistraAvvenutoRitiroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentoRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentoRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "smediazioneDocumentiRequest")
    public JAXBElement<SmediazioneDocumentoRequest> createSmediazioneDocumentiRequest(SmediazioneDocumentoRequest value) {
        return new JAXBElement<SmediazioneDocumentoRequest>(_SmediazioneDocumentiRequest_QNAME, SmediazioneDocumentoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SmediazioneDocumentoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "smediazioneDocumentiResponse")
    public JAXBElement<SmediazioneDocumentoResponse> createSmediazioneDocumentiResponse(SmediazioneDocumentoResponse value) {
        return new JAXBElement<SmediazioneDocumentoResponse>(_SmediazioneDocumentiResponse_QNAME, SmediazioneDocumentoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRefertiRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRefertiRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getRefertiDocs")
    public JAXBElement<GetRefertiRequest> createGetRefertiDocs(GetRefertiRequest value) {
        return new JAXBElement<GetRefertiRequest>(_GetRefertiDocs_QNAME, GetRefertiRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRefertiResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRefertiResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getMieiRefertiDocsResponse")
    public JAXBElement<GetRefertiResponse> createGetMieiRefertiDocsResponse(GetRefertiResponse value) {
        return new JAXBElement<GetRefertiResponse>(_GetMieiRefertiDocsResponse_QNAME, GetRefertiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRefertiResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetRefertiResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getMieiRefertiResponse")
    public JAXBElement<GetRefertiResponse> createGetMieiRefertiResponse(GetRefertiResponse value) {
        return new JAXBElement<GetRefertiResponse>(_GetMieiRefertiResponse_QNAME, GetRefertiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDettaglioDocumentoRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDettaglioDocumentoRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "getDettaglioDocumentoNOAS")
    public JAXBElement<GetDettaglioDocumentoRequest> createGetDettaglioDocumentoNOAS(GetDettaglioDocumentoRequest value) {
        return new JAXBElement<GetDettaglioDocumentoRequest>(_GetDettaglioDocumentoNOAS_QNAME, GetDettaglioDocumentoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDettaglioDocumentoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDettaglioDocumentoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmaclbl.csi.it/", name = "GetDettaglioDocumentoNOASResponse")
    public JAXBElement<GetDettaglioDocumentoResponse> createGetDettaglioDocumentoNOASResponse(GetDettaglioDocumentoResponse value) {
        return new JAXBElement<GetDettaglioDocumentoResponse>(_GetDettaglioDocumentoNOASResponse_QNAME, GetDettaglioDocumentoResponse.class, null, value);
    }

}
