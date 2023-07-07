/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dmaccbl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import it.csi.dma.apiopsan.scaricorefertoservice.dmacc.ImpostaDataRitiroRefertoSRResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dmaccbl package. 
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

    private final static QName _ImpostaDataRitiroRefertoSRResponse_QNAME = new QName("http://dmaccbl.csi.it/", "impostaDataRitiroRefertoSRResponse");
    private final static QName _ScansionaRefertoQRCodeResponse_QNAME = new QName("http://dmaccbl.csi.it/", "scansionaRefertoQRCodeResponse");
    private final static QName _ImpostaDataRitiroRefertoSR_QNAME = new QName("http://dmaccbl.csi.it/", "impostaDataRitiroRefertoSR");
    private final static QName _ScansionaRefertoQRCodeRequest_QNAME = new QName("http://dmaccbl.csi.it/", "scansionaRefertoQRCodeRequest");
    private final static QName _CheckGetRefertoSRResponse_QNAME = new QName("http://dmaccbl.csi.it/", "checkGetRefertoSRResponse");
    private final static QName _AddPageQRCode_QNAME = new QName("http://dmaccbl.csi.it/", "addPageQRCode");
    private final static QName _CheckVerificaDelega_QNAME = new QName("http://dmaccbl.csi.it/", "checkVerificaDelega");
    private final static QName _GetCLDaContattareSRResponse_QNAME = new QName("http://dmaccbl.csi.it/", "getCLDaContattareSRResponse");
    private final static QName _GetCLDaContattareSR_QNAME = new QName("http://dmaccbl.csi.it/", "getCLDaContattareSR");
    private final static QName _CheckVerificaDelegaResponse_QNAME = new QName("http://dmaccbl.csi.it/", "checkVerificaDelegaResponse");
    private final static QName _CheckGetRefertoSR_QNAME = new QName("http://dmaccbl.csi.it/", "checkGetRefertoSR");
    private final static QName _ScansionaRefertoQRCode_QNAME = new QName("http://dmaccbl.csi.it/", "scansionaRefertoQRCode");
    private final static QName _AddPageQRCodeResponse_QNAME = new QName("http://dmaccbl.csi.it/", "addPageQRCodeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dmaccbl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckGetRefertoSR }
     * 
     */
    public CheckGetRefertoSR createCheckGetRefertoSR() {
        return new CheckGetRefertoSR();
    }

    /**
     * Create an instance of {@link ServiceResponse }
     * 
     */
    public ServiceResponse createServiceResponse() {
        return new ServiceResponse();
    }

    /**
     * Create an instance of {@link ScansionaRefertoQRCodeResponse }
     * 
     */
    public ScansionaRefertoQRCodeResponse createScansionaRefertoQRCodeResponse() {
        return new ScansionaRefertoQRCodeResponse();
    }

    /**
     * Create an instance of {@link AddPageQRCodeResponse }
     * 
     */
    public AddPageQRCodeResponse createAddPageQRCodeResponse() {
        return new AddPageQRCodeResponse();
    }

    /**
     * Create an instance of {@link CheckGetRefertoSRResponse }
     * 
     */
    public CheckGetRefertoSRResponse createCheckGetRefertoSRResponse() {
        return new CheckGetRefertoSRResponse();
    }

    /**
     * Create an instance of {@link ImpostaDataRitiroRefertoSRRequest }
     * 
     */
    public ImpostaDataRitiroRefertoSRRequest createImpostaDataRitiroRefertoSRRequest() {
        return new ImpostaDataRitiroRefertoSRRequest();
    }

    /**
     * Create an instance of {@link GetCLDaContattareSRResponse }
     * 
     */
    public GetCLDaContattareSRResponse createGetCLDaContattareSRResponse() {
        return new GetCLDaContattareSRResponse();
    }

    /**
     * Create an instance of {@link GetCLDaContattareSR }
     * 
     */
    public GetCLDaContattareSR createGetCLDaContattareSR() {
        return new GetCLDaContattareSR();
    }

    /**
     * Create an instance of {@link CheckVerificaDelega }
     * 
     */
    public CheckVerificaDelega createCheckVerificaDelega() {
        return new CheckVerificaDelega();
    }

    /**
     * Create an instance of {@link CheckVerificaDelegaResponse }
     * 
     */
    public CheckVerificaDelegaResponse createCheckVerificaDelegaResponse() {
        return new CheckVerificaDelegaResponse();
    }

    /**
     * Create an instance of {@link AddPageQRCodeRequest }
     * 
     */
    public AddPageQRCodeRequest createAddPageQRCodeRequest() {
        return new AddPageQRCodeRequest();
    }

    /**
     * Create an instance of {@link ScansionaRefertoQRCodeRequest }
     * 
     */
    public ScansionaRefertoQRCodeRequest createScansionaRefertoQRCodeRequest() {
        return new ScansionaRefertoQRCodeRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImpostaDataRitiroRefertoSRResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "impostaDataRitiroRefertoSRResponse")
    public JAXBElement<ImpostaDataRitiroRefertoSRResponse> createImpostaDataRitiroRefertoSRResponse(ImpostaDataRitiroRefertoSRResponse value) {
        return new JAXBElement<ImpostaDataRitiroRefertoSRResponse>(_ImpostaDataRitiroRefertoSRResponse_QNAME, ImpostaDataRitiroRefertoSRResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScansionaRefertoQRCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "scansionaRefertoQRCodeResponse")
    public JAXBElement<ScansionaRefertoQRCodeResponse> createScansionaRefertoQRCodeResponse(ScansionaRefertoQRCodeResponse value) {
        return new JAXBElement<ScansionaRefertoQRCodeResponse>(_ScansionaRefertoQRCodeResponse_QNAME, ScansionaRefertoQRCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImpostaDataRitiroRefertoSRRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "impostaDataRitiroRefertoSR")
    public JAXBElement<ImpostaDataRitiroRefertoSRRequest> createImpostaDataRitiroRefertoSR(ImpostaDataRitiroRefertoSRRequest value) {
        return new JAXBElement<ImpostaDataRitiroRefertoSRRequest>(_ImpostaDataRitiroRefertoSR_QNAME, ImpostaDataRitiroRefertoSRRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScansionaRefertoQRCodeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "scansionaRefertoQRCodeRequest")
    public JAXBElement<ScansionaRefertoQRCodeRequest> createScansionaRefertoQRCodeRequest(ScansionaRefertoQRCodeRequest value) {
        return new JAXBElement<ScansionaRefertoQRCodeRequest>(_ScansionaRefertoQRCodeRequest_QNAME, ScansionaRefertoQRCodeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckGetRefertoSRResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "checkGetRefertoSRResponse")
    public JAXBElement<CheckGetRefertoSRResponse> createCheckGetRefertoSRResponse(CheckGetRefertoSRResponse value) {
        return new JAXBElement<CheckGetRefertoSRResponse>(_CheckGetRefertoSRResponse_QNAME, CheckGetRefertoSRResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPageQRCodeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "addPageQRCode")
    public JAXBElement<AddPageQRCodeRequest> createAddPageQRCode(AddPageQRCodeRequest value) {
        return new JAXBElement<AddPageQRCodeRequest>(_AddPageQRCode_QNAME, AddPageQRCodeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckVerificaDelega }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "checkVerificaDelega")
    public JAXBElement<CheckVerificaDelega> createCheckVerificaDelega(CheckVerificaDelega value) {
        return new JAXBElement<CheckVerificaDelega>(_CheckVerificaDelega_QNAME, CheckVerificaDelega.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCLDaContattareSRResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "getCLDaContattareSRResponse")
    public JAXBElement<GetCLDaContattareSRResponse> createGetCLDaContattareSRResponse(GetCLDaContattareSRResponse value) {
        return new JAXBElement<GetCLDaContattareSRResponse>(_GetCLDaContattareSRResponse_QNAME, GetCLDaContattareSRResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCLDaContattareSR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "getCLDaContattareSR")
    public JAXBElement<GetCLDaContattareSR> createGetCLDaContattareSR(GetCLDaContattareSR value) {
        return new JAXBElement<GetCLDaContattareSR>(_GetCLDaContattareSR_QNAME, GetCLDaContattareSR.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckVerificaDelegaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "checkVerificaDelegaResponse")
    public JAXBElement<CheckVerificaDelegaResponse> createCheckVerificaDelegaResponse(CheckVerificaDelegaResponse value) {
        return new JAXBElement<CheckVerificaDelegaResponse>(_CheckVerificaDelegaResponse_QNAME, CheckVerificaDelegaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckGetRefertoSR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "checkGetRefertoSR")
    public JAXBElement<CheckGetRefertoSR> createCheckGetRefertoSR(CheckGetRefertoSR value) {
        return new JAXBElement<CheckGetRefertoSR>(_CheckGetRefertoSR_QNAME, CheckGetRefertoSR.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScansionaRefertoQRCodeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "scansionaRefertoQRCode")
    public JAXBElement<ScansionaRefertoQRCodeRequest> createScansionaRefertoQRCode(ScansionaRefertoQRCodeRequest value) {
        return new JAXBElement<ScansionaRefertoQRCodeRequest>(_ScansionaRefertoQRCode_QNAME, ScansionaRefertoQRCodeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPageQRCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmaccbl.csi.it/", name = "addPageQRCodeResponse")
    public JAXBElement<AddPageQRCodeResponse> createAddPageQRCodeResponse(AddPageQRCodeResponse value) {
        return new JAXBElement<AddPageQRCodeResponse>(_AddPageQRCodeResponse_QNAME, AddPageQRCodeResponse.class, null, value);
    }

}
