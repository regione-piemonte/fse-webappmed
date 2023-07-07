/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.cldocumentiservice.dmacl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import it.csi.dma.apiopsan.external.cldocumentiservice.dmaclbl.RegistraAvvenutoRitiroRequest;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.external.cldocumentiservice.dmacl package. 
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

    private final static QName _RegistraAvvenutoRitiro_QNAME = new QName("http://dmacl.csi.it/", "registraAvvenutoRitiro");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.external.cldocumentiservice.dmacl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistraAvvenutoRitiroRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistraAvvenutoRitiroRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://dmacl.csi.it/", name = "registraAvvenutoRitiro")
    public JAXBElement<RegistraAvvenutoRitiroRequest> createRegistraAvvenutoRitiro(RegistraAvvenutoRitiroRequest value) {
        return new JAXBElement<RegistraAvvenutoRitiroRequest>(_RegistraAvvenutoRitiro_QNAME, RegistraAvvenutoRitiroRequest.class, null, value);
    }

}
