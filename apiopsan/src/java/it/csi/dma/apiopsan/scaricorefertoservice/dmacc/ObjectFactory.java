/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.scaricorefertoservice.dmacc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import it.csi.dma.apiopsan.scaricorefertoservice.dma.CollocazioneReport;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.ComponenteLocale;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Paziente;
import it.csi.dma.apiopsan.scaricorefertoservice.dma.Richiedente;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dmacc package. 
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

    private final static QName _Paziente_QNAME = new QName("http://dmacc.csi.it/", "paziente");
    private final static QName _ComponenteLocale_QNAME = new QName("http://dmacc.csi.it/", "componenteLocale");
    private final static QName _CollocazioneReport_QNAME = new QName("http://dmacc.csi.it/", "collocazioneReport");
    private final static QName _Collocazione_QNAME = new QName("http://dmacc.csi.it/", "collocazione");
    private final static QName _Richiedente_QNAME = new QName("http://dmacc.csi.it/", "richiedente");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dmacc
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link ImpostaDataRitiroRefertoSRResponse }
     * 
     */
    public ImpostaDataRitiroRefertoSRResponse createImpostaDataRitiroRefertoSRResponse() {
        return new ImpostaDataRitiroRefertoSRResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Paziente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "paziente")
    public JAXBElement<Paziente> createPaziente(Paziente value) {
        return new JAXBElement<Paziente>(_Paziente_QNAME, Paziente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComponenteLocale }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "componenteLocale")
    public JAXBElement<ComponenteLocale> createComponenteLocale(ComponenteLocale value) {
        return new JAXBElement<ComponenteLocale>(_ComponenteLocale_QNAME, ComponenteLocale.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CollocazioneReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "collocazioneReport")
    public JAXBElement<CollocazioneReport> createCollocazioneReport(CollocazioneReport value) {
        return new JAXBElement<CollocazioneReport>(_CollocazioneReport_QNAME, CollocazioneReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CollocazioneReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "collocazione")
    public JAXBElement<CollocazioneReport> createCollocazione(CollocazioneReport value) {
        return new JAXBElement<CollocazioneReport>(_Collocazione_QNAME, CollocazioneReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Richiedente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dmacc.csi.it/", name = "richiedente")
    public JAXBElement<Richiedente> createRichiedente(Richiedente value) {
        return new JAXBElement<Richiedente>(_Richiedente_QNAME, Richiedente.class, null, value);
    }

}
