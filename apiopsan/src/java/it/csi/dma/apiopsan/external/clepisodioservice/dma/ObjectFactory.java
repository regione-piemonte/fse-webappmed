/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/


package it.csi.dma.apiopsan.external.clepisodioservice.dma;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.csi.dma.apiopsan.external.clepisodioservice.dma package. 
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

    private final static QName _Consenso_QNAME = new QName("http://dma.csi.it/", "consenso");
    private final static QName _ModalitaDiAlimentazione_QNAME = new QName("http://dma.csi.it/", "modalitaDiAlimentazione");
    private final static QName _ArrayIdIrecCFPazienteIdIrec_QNAME = new QName("http://dma.csi.it/", "idIrec");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.csi.dma.apiopsan.external.clepisodioservice.dma
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Consenso }
     * 
     */
    public Consenso createConsenso() {
        return new Consenso();
    }

    /**
     * Create an instance of {@link ModalitaDiAlimentazione }
     * 
     */
    public ModalitaDiAlimentazione createModalitaDiAlimentazione() {
        return new ModalitaDiAlimentazione();
    }

    /**
     * Create an instance of {@link Richiedente }
     * 
     */
    public Richiedente createRichiedente() {
        return new Richiedente();
    }

    /**
     * Create an instance of {@link ArrayIdIrecCFPaziente }
     * 
     */
    public ArrayIdIrecCFPaziente createArrayIdIrecCFPaziente() {
        return new ArrayIdIrecCFPaziente();
    }

    /**
     * Create an instance of {@link ApplicazioneRichiedente }
     * 
     */
    public ApplicazioneRichiedente createApplicazioneRichiedente() {
        return new ApplicazioneRichiedente();
    }

    /**
     * Create an instance of {@link Codifica }
     * 
     */
    public Codifica createCodifica() {
        return new Codifica();
    }

    /**
     * Create an instance of {@link RegimeDMA }
     * 
     */
    public RegimeDMA createRegimeDMA() {
        return new RegimeDMA();
    }

    /**
     * Create an instance of {@link RuoloDMA }
     * 
     */
    public RuoloDMA createRuoloDMA() {
        return new RuoloDMA();
    }

    /**
     * Create an instance of {@link AziendaSanitaria }
     * 
     */
    public AziendaSanitaria createAziendaSanitaria() {
        return new AziendaSanitaria();
    }

    /**
     * Create an instance of {@link Profilo }
     * 
     */
    public Profilo createProfilo() {
        return new Profilo();
    }

    /**
     * Create an instance of {@link Paziente }
     * 
     */
    public Paziente createPaziente() {
        return new Paziente();
    }

    /**
     * Create an instance of {@link ComuneDiNascita }
     * 
     */
    public ComuneDiNascita createComuneDiNascita() {
        return new ComuneDiNascita();
    }

    /**
     * Create an instance of {@link ConsensoMatriceRuolo }
     * 
     */
    public ConsensoMatriceRuolo createConsensoMatriceRuolo() {
        return new ConsensoMatriceRuolo();
    }

    /**
     * Create an instance of {@link StatoDiNascita }
     * 
     */
    public StatoDiNascita createStatoDiNascita() {
        return new StatoDiNascita();
    }

    /**
     * Create an instance of {@link PazienteOncologico }
     * 
     */
    public PazienteOncologico createPazienteOncologico() {
        return new PazienteOncologico();
    }

    /**
     * Create an instance of {@link PazienteROPVA }
     * 
     */
    public PazienteROPVA createPazienteROPVA() {
        return new PazienteROPVA();
    }

    /**
     * Create an instance of {@link MetadatiDocumento }
     * 
     */
    public MetadatiDocumento createMetadatiDocumento() {
        return new MetadatiDocumento();
    }

    /**
     * Create an instance of {@link ComponenteLocale }
     * 
     */
    public ComponenteLocale createComponenteLocale() {
        return new ComponenteLocale();
    }

    /**
     * Create an instance of {@link ComponenteLocaleTipo }
     * 
     */
    public ComponenteLocaleTipo createComponenteLocaleTipo() {
        return new ComponenteLocaleTipo();
    }

    /**
     * Create an instance of {@link ArrayOfcomponenteLocaleServiziocomponenteLocaleServizio }
     * 
     */
    public ArrayOfcomponenteLocaleServiziocomponenteLocaleServizio createArrayOfcomponenteLocaleServiziocomponenteLocaleServizio() {
        return new ArrayOfcomponenteLocaleServiziocomponenteLocaleServizio();
    }

    /**
     * Create an instance of {@link ComponenteLocaleServizio }
     * 
     */
    public ComponenteLocaleServizio createComponenteLocaleServizio() {
        return new ComponenteLocaleServizio();
    }

    /**
     * Create an instance of {@link ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione }
     * 
     */
    public ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione createArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione() {
        return new ArrayOfcomponenteLocaleOperazionecomponenteLocaleOperazione();
    }

    /**
     * Create an instance of {@link ComponenteLocaleOperazione }
     * 
     */
    public ComponenteLocaleOperazione createComponenteLocaleOperazione() {
        return new ComponenteLocaleOperazione();
    }

    /**
     * Create an instance of {@link ComponenteLocaleRisposta }
     * 
     */
    public ComponenteLocaleRisposta createComponenteLocaleRisposta() {
        return new ComponenteLocaleRisposta();
    }

    /**
     * Create an instance of {@link ArrayOferroreerrore }
     * 
     */
    public ArrayOferroreerrore createArrayOferroreerrore() {
        return new ArrayOferroreerrore();
    }

    /**
     * Create an instance of {@link Errore }
     * 
     */
    public Errore createErrore() {
        return new Errore();
    }

    /**
     * Create an instance of {@link ComponenteLocaleRispostaStato }
     * 
     */
    public ComponenteLocaleRispostaStato createComponenteLocaleRispostaStato() {
        return new ComponenteLocaleRispostaStato();
    }

    /**
     * Create an instance of {@link ArrayOfconsensoPerRuoloconsensoPerRuolo }
     * 
     */
    public ArrayOfconsensoPerRuoloconsensoPerRuolo createArrayOfconsensoPerRuoloconsensoPerRuolo() {
        return new ArrayOfconsensoPerRuoloconsensoPerRuolo();
    }

    /**
     * Create an instance of {@link ConsensoPerRuolo }
     * 
     */
    public ConsensoPerRuolo createConsensoPerRuolo() {
        return new ConsensoPerRuolo();
    }

    /**
     * Create an instance of {@link LuogoASU }
     * 
     */
    public LuogoASU createLuogoASU() {
        return new LuogoASU();
    }

    /**
     * Create an instance of {@link Oscuramento }
     * 
     */
    public Oscuramento createOscuramento() {
        return new Oscuramento();
    }

    /**
     * Create an instance of {@link StrutturaSanitaria }
     * 
     */
    public StrutturaSanitaria createStrutturaSanitaria() {
        return new StrutturaSanitaria();
    }

    /**
     * Create an instance of {@link UnitaOperativaSanitaria }
     * 
     */
    public UnitaOperativaSanitaria createUnitaOperativaSanitaria() {
        return new UnitaOperativaSanitaria();
    }

    /**
     * Create an instance of {@link ArrayOfprestazioneprestazione }
     * 
     */
    public ArrayOfprestazioneprestazione createArrayOfprestazioneprestazione() {
        return new ArrayOfprestazioneprestazione();
    }

    /**
     * Create an instance of {@link Prestazione }
     * 
     */
    public Prestazione createPrestazione() {
        return new Prestazione();
    }

    /**
     * Create an instance of {@link Disciplina }
     * 
     */
    public Disciplina createDisciplina() {
        return new Disciplina();
    }

    /**
     * Create an instance of {@link ValoriPrestazione }
     * 
     */
    public ValoriPrestazione createValoriPrestazione() {
        return new ValoriPrestazione();
    }

    /**
     * Create an instance of {@link Branca }
     * 
     */
    public Branca createBranca() {
        return new Branca();
    }

    /**
     * Create an instance of {@link TipoDocumento }
     * 
     */
    public TipoDocumento createTipoDocumento() {
        return new TipoDocumento();
    }

    /**
     * Create an instance of {@link AssettoOrganizzativo }
     * 
     */
    public AssettoOrganizzativo createAssettoOrganizzativo() {
        return new AssettoOrganizzativo();
    }

    /**
     * Create an instance of {@link TipoFileDocumento }
     * 
     */
    public TipoFileDocumento createTipoFileDocumento() {
        return new TipoFileDocumento();
    }

    /**
     * Create an instance of {@link CatalogoLog }
     * 
     */
    public CatalogoLog createCatalogoLog() {
        return new CatalogoLog();
    }

    /**
     * Create an instance of {@link Configurazione }
     * 
     */
    public Configurazione createConfigurazione() {
        return new Configurazione();
    }

    /**
     * Create an instance of {@link ConsensoMatriceTipoDato }
     * 
     */
    public ConsensoMatriceTipoDato createConsensoMatriceTipoDato() {
        return new ConsensoMatriceTipoDato();
    }

    /**
     * Create an instance of {@link EpisodioStato }
     * 
     */
    public EpisodioStato createEpisodioStato() {
        return new EpisodioStato();
    }

    /**
     * Create an instance of {@link TipoCartellaPersonale }
     * 
     */
    public TipoCartellaPersonale createTipoCartellaPersonale() {
        return new TipoCartellaPersonale();
    }

    /**
     * Create an instance of {@link TipoContributo }
     * 
     */
    public TipoContributo createTipoContributo() {
        return new TipoContributo();
    }

    /**
     * Create an instance of {@link TipoEpisodio }
     * 
     */
    public TipoEpisodio createTipoEpisodio() {
        return new TipoEpisodio();
    }

    /**
     * Create an instance of {@link DocumentoDMA }
     * 
     */
    public DocumentoDMA createDocumentoDMA() {
        return new DocumentoDMA();
    }

    /**
     * Create an instance of {@link ArrayOflinkImmaginelinkImmagine }
     * 
     */
    public ArrayOflinkImmaginelinkImmagine createArrayOflinkImmaginelinkImmagine() {
        return new ArrayOflinkImmaginelinkImmagine();
    }

    /**
     * Create an instance of {@link LinkImmagine }
     * 
     */
    public LinkImmagine createLinkImmagine() {
        return new LinkImmagine();
    }

    /**
     * Create an instance of {@link ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante }
     * 
     */
    public ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante createArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante() {
        return new ArrayOflinkImmagineStazioneRefertantelinkImmagineStazioneRefertante();
    }

    /**
     * Create an instance of {@link LinkImmagineStazioneRefertante }
     * 
     */
    public LinkImmagineStazioneRefertante createLinkImmagineStazioneRefertante() {
        return new LinkImmagineStazioneRefertante();
    }

    /**
     * Create an instance of {@link Episodio }
     * 
     */
    public Episodio createEpisodio() {
        return new Episodio();
    }

    /**
     * Create an instance of {@link ArrayOfmetadatiDocumentometadatiDocumento }
     * 
     */
    public ArrayOfmetadatiDocumentometadatiDocumento createArrayOfmetadatiDocumentometadatiDocumento() {
        return new ArrayOfmetadatiDocumentometadatiDocumento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Consenso }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Consenso }{@code >}
     */
    @XmlElementDecl(namespace = "http://dma.csi.it/", name = "consenso")
    public JAXBElement<Consenso> createConsenso(Consenso value) {
        return new JAXBElement<Consenso>(_Consenso_QNAME, Consenso.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModalitaDiAlimentazione }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModalitaDiAlimentazione }{@code >}
     */
    @XmlElementDecl(namespace = "http://dma.csi.it/", name = "modalitaDiAlimentazione")
    public JAXBElement<ModalitaDiAlimentazione> createModalitaDiAlimentazione(ModalitaDiAlimentazione value) {
        return new JAXBElement<ModalitaDiAlimentazione>(_ModalitaDiAlimentazione_QNAME, ModalitaDiAlimentazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://dma.csi.it/", name = "idIrec", scope = ArrayIdIrecCFPaziente.class)
    public JAXBElement<Long> createArrayIdIrecCFPazienteIdIrec(Long value) {
        return new JAXBElement<Long>(_ArrayIdIrecCFPazienteIdIrec_QNAME, Long.class, ArrayIdIrecCFPaziente.class, value);
    }

}
