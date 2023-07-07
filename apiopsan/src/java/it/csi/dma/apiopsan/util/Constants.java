/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util;

public class Constants {
	public final static String COMPONENT_NAME = "apiopsan";
	public static final String COMPONENT_NAME_CAMMEL_CASE = "apiOpsan";	
	public static final String CODICE_SERVIZIO_SANSOL = "SANSOL";
	public static final String CODICE_VERTICALE = "APIOPSAN";	
	public static final String CODICE_SERVIZIO = "DMAWA";
	public static final String ERRORE_NON_CODIFICATO = "Errore non codificato";
	public static final String ERRORE_NON_CODIFICATO_INTERNO = "Errore non codificato per errore in rimappatura";
	public static final Boolean COMPONENT_ACTIVE = true;
	public static final String COMPONENT_DESCRIPTION = "api Operatore Sanitario v 1.0.0";
	public static final String CONTEXT_CHIAVE_ID = "chiaveid";
	public static final String CONTEXT_TEMPO_PARTENZA = "tempopartenza";
	public static final String UTENTE_APPLICATIVO = "APIOPSAN_APP";
	public static final String OPERATORE_SANITARIO_SERVICE = "OperatoreSanitarioApiServiceImpl";
	public static final String CATALOGHI_SERVICE = "CataloghiApiServiceImpl";
	public static final String SI = "SI";
	public static final String NO = "NO";
	public static final int UNO = 1;
	public static final int DUE = 2;
	public static final int TRE = 3;
	public static final int QUATTRO = 4;
	public static final String BLOCCANTE = "Bloccante";
	public static final String ESITO_FALLIMENTO = "FALLIMENTO";
	public static final String ESITO_SUCCESSO = "SUCCESSO";
	public static final String VERIFICA_SERVICE = "VERIFICASERVICE";
	public static final String VERIFICA_SERVICE_URL = "VerificaCr";
	public static final String PAZIENTE_SERVICE_URL = "PazienteSe";
	public static final String DOCUMENTI_INI_SERVICE_URL = "DocumentSe";
	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";
	public static final String S = "S";
	public static final String N = "N";
	public static final String LENGTH_PAZIENT_SEARCH = "LENGTH_PAZIENT_SEARCH";
	public static final String QUERY_LIMIT_RICERCA_PAZIENTE = "QUERY_LIMIT_RICERCA_PAZIENTE";
	public static final String ESENZIONE = "ESEN";
	public static final String FSE = "FSE";
	public static final String PERSONALE = "PERSONALE";
	public static final String CODICE_TIPO_DOC_PSS = "60591-5";
	
	public static final String RICERCADOC_COUNT_INTERVALLO = "RICERCADOC_COUNT_INTERVALLO";
	public static final String RICERCADOC_COUNT_MAX_INTERVALLO = "RICERCADOC_COUNT_MAX_INTERVALLO";
	
	public static final String CODICE_CONTATTO_DIGITALE = "SANMEDCOD";
	
	public static final String TIPO_MEDICO_REFERTANTE = "REFERTANTE";
	public static final String TIPO_MEDICO_VALIDANTE = "VALIDANTE";
	public static final String TIPO_MEDICO_VALIDANTEOREFERTANTE = "VALIDANTEOREFERTANTE";

	public static final String REGIME_EMERGENZA = "EM";
	
	//GETREGMI
	public static final String GET_REGIMI = "GetRegimi";
	public static final String CODICE_SUCCESSO_GETREGIMI = "WA_GET_REG";
	//GETMMGPAZIENTE
	public static final String GET_MMGPAZIENTE = "GetMMGPazienteApi";
	public static final String MMGPAZIENTE_SERVICE = "MMGPAZIENTESERVICE";
	public static final String CODICE_SUCCESSO_GETMMGPAZIENTE = "WA_DAT_MED";
	//GETRicercaGPAZIENTE
	public static final String GET_RICERCAPAZIENTE = "GetRicercaPaziente";
	public static final String RICERCAPAZIENTE_SERVICE = "RICERCAPAZIENTESERVICE";
	public static final String CODICE_SUCCESSO_RICERCA_PAZIENTE = "WA_RIC_PAZ";
	//servizi per log

	//Servizio STATO_CONSENSI
	public static final String CONSENSI_EXT_SERVICE = "CONSENSI_EXT_SERVICE";
	public static final String STATO_CONSENSI_SERVICE_URL = "ConsensoEx";
	public static final String CITTADINI_API_SERVICE = "CittadiniApiServiceImpl";	
	public static final String STATO_CONSENSI = "StatoConsensi";
	public static final String CODICE_SUCCESSO_STATO_CONSENSO = "VIS_CON_DMA_OS";	
	
	
	//Servizio esterno Stato Consensi
	public static final String IDENTIFICATIVO_ORGANIZZAZIONE = "010";
	public static final String STRUTTURA_UTENTE = "010";
	public static final String STATO_CONSENSI_CONTESTO_OPERATIVO = "CONSENT";
	public static final String TIPO_ATTIVITA = "READ";
	public static final String TIPO_ATTIVITA_CREATE="CREATE";
	
	//GETDOCUMENTIEPISODIO
	public static final String GET_DOCEPISODIO = "GetDocumentiEpisodioApi";
	public static final String DOCUMENTIEPISODIO_SERVICE = "DOCUMENTIEPISODIOSERVICE";
	public static final String CODICE_SUCCESSO_DOCUMENTIEPISODIO = "WA_VIS_DOC_EPI";
	
	//SoloEpisodi
	public static final String POST_SOLOEPISODI = "PostSoloEpisodiApi";
	public static final String SOLOEPISODI_SERVICE = "SOLOEPISODISERVICE";
	public static final String CODICE_SUCCESSO_POSTSOLOEPISODI = "WA_VIS_EPI";
	

	//GETINFORMATIVA
	public static final String GET_INFORMATIVA = "GetInformativaApi";
	public static final String INFORMATIVA_SERVICE = "INFORMATIVASERVICE";
	public static final String CODICE_SUCCESSO_INFORMATIVA = "WA_VIS_INFORM";
	public static final String INFORMATIVA = "Informativa";	
		
	//GET_MIEI_REFERTI
	public static final String GET_MIEI_REFERTI = "GetMieiRefertiDocs";
	public static final String CODICE_SUCCESSO_GETMIEIREFERTI = "WA_MIEI_REF";
	
	//GETTIPODOCUMENTO
		public static final String GET_TIPODOCUMENTO = "GetTipoDocumento";
		public static final String CODICE_SUCCESSO_GETTIPODOCUMENTO = "WA_GET_TIPDOC";

	//GET_ELENCO_TIPO_DOCUMENTO_CATEGORIA
	public static final String GET_ELENCO_TIPO_DOCUMENTO_CATEGORIA = "getElencoTipoDocumentoCategoria";
	public static final String CODICE_SUCCESSO_ELENCO_TIPO_DOCUMENTO_CATEGORIA = "WA_GET_ELENCOTIPDOC";	
		
	public static final String CONTESTO_OPERATIVO = "TREATMENT";
	public static final String STATO_DOCUMENTO = "APPROVED";
	
	//Ricerca Documenti getTuttiDoc
	public static final String GET_TUTTI_DOC = "GetTuttiDoc";
	public static final String RICERCA_DOCUMENTI_SERVICE = "DocumentIn";
	public static final String CODICE_SUCCESSO_GET_TUTTI_DOC = "WA_VIS_DOC";
	public static final String GET_TUTTI_DOC_COUNT = "GetTuttiDocCount";
	public static final String CODICE_SUCCESSO_GET_TUTTI_DOC_COUNT = "WA_VIS_CONT";
	
	//Servizio esterno RicercaDocumentiINI
	public static final String RICERCA_DOC_ID_ORG = "010";
	public static final String RICERCA_DOC_STRUT_UTENTE = "010";
	public static final String RICERCA_DOC_CONTESTO_OP = "TREATMENT";
	public static final String RICERCA_DOC_TIPO_ATTIVITA = "READ";
	public static final String RICERCA_DOC_STATO_DOC = "APPROVED";	
	public static final String CODICE_RISPOSTA_PAZIENTE_RDA_RCD = "2048";	
	public static final String CODICE_RISPOSTA_CONSENSO_NEGATO = "2007";
	public static final String CODICE_RISPOSTA_ERRORE_RETE = "9906";
	
	//Servizio esterno RecuperoDocumentoINI
	public static final String CODICE_RISPOSTA_RECUPERO_SENZA_RICERCA = "2052";
	
	public static final String SCHEDA_VACCINALE_REGIONALE = "REG-87273-9";
	
	
	
	//Servizio GET DOCUMENTI CORRELATI
	public static final String DOCUMENTI_SERVICE = "DOCUMENTISERVICE";
	public static final String DOCUMENTI_SERVICE_URL = "DocumentSe";
	public static final String GET_DOCUMENTICORRELLATI = "GetDocumentiCorrellatiApi";
	public static final String DOCUMENTICORRELLATI_SERVICE = "DOCUMENTICORRELLATISERVICE";
	public static final String[] TIPO_CORRELLAZIONE_DOCUMENTO= {"NRE","NUMERO_SDO","PADRE_FIGLIO","NUMERO_PASSAGGIO_PS"};
	public static final String DOC_NRE="NRE";
	public static final String DOC_SDO="NUMERO_SDO";	
	public static final String PADRE_FIGLIO="PADRE_FIGLIO";
	public static final String PASSAGGIO_PS="NUMERO_PASSAGGIO_PS";
	public static final String CODICE_SUCCESSO_DOCUMENTICORRELATI = "WA_DOC_CORR";
		
	//Servizio  getDettaglioDoc
	public static final String GET_DETTAGLIO_DOC = "GetDettaglioDoc";
	public static final String CODICE_SUCCESSO_GET_DETTAGLIO_DOC = "WA_VIS_DETT_DOC";
	
	//Servizio getDettaglioPrestazioni
	public static final String GET_DETTAGLIO_PRESTAZIONI = "GetDettaglioDoc"; //TODO: da censire su dmacc_d_catalogo_servizi_operation.nome_operation
	public static final String GET_DETTAGLIO_PRESTAZIONI_SERVIZIO_CL = "DocumentBL"; //dmacc_d_servizio_componente_locale.codice 
	public static final String GET_DETTAGLIO_PRESTAZIONI_OPERAZIONE_CL = "getDettaglioDocumento"; //dmacc_d_operazione_servizio_componente_locale.codice 
	
	
	//Servizio ESENZIONI
		public static final String GET_ESENZIONI = "GetEsenzioniApi";	
		public static final String CODICE_SUCCESSO_ESENZIONI = "WA_VIS_ESE";
		public static final String ESENZIONI_SERVICE_URL = "EsenzioniS";
		public static final String ESENZIONI_SERVICE = "ESENZIONISERVICE";
		public static final String ACCODATRASFINDICE_SERVICE_URL = "AccodaIndS";
		public static final String ACCODA_SERVICE = "ACCODASERVICE";
		
	//Servizio Comunicazione Consensi
		public static final String COMUNICAZIONE_CONSENSI="ComConsensiApi";
		public static final String CODICE_SUCCESSO_COMUNICAZIONE_CONSENSI = "WA_CON_DMA";
		public static final String CATEGORIA_PERSONALE = "PERSONALE";
		public static final String CATEGORIA_FSE = "FSE";

	//Servizio GetScreening
		public static final String GET_SCREENING="GetScreeningApi";
		public static final String SCREENING_SERVICE_URL="ScreeningS";
		public static final String SCREENING_SERVICE = "SCREENINGSERVICE";
		
		//Recupera Documenti post Recupera Documento
		public static final String RECUPERA_DOCUMENTI_SERVICE_PO = "RecuperoPo";
		public static final String RECUPERA_DOC_PO = "RecuperoDocumentoPOApi";

	//Servizio getDocumento
	public static final String GET_DOCUMENTO = "GetDocumento";
	public static final String CL_DOC_LOCALI_SERVIZIO = "Episodio";       //dmacc_d_servizio_componente_locale.codice 
	public static final String CL_DOC_LOCALI_OPERAZIONE = "getDocumento"; //dmacc_d_operazione_servizio_componente_locale.codice 
	public static final String CL_DOC_INI_SERVIZIO = "RecuperaDo";       //dmacc_d_servizio_componente_locale.codice 
	public static final String CL_DOC_INI_OPERAZIONE = "getRecuperoDocumentiIni"; //dmacc_d_operazione_servizio_componente_locale.codice     
	public static final String CODICE_SUCCESSO_GET_DOCUMENTO = "WA_SEL_DOC";
	
	//SEervizio prenota immmagini
	public static final String WINDOWS = "WINDOWS";
	public static final String PIN_IMR = "12345";
	public static final String SCARICA_PACCHETTO_IMR = "ScaricaPacchettoImr";
	public static final String CODICE_SUCCESSO_SCARICA_PACCHETTO_IMR = "IMR_RIC_SCA_PAC";
	public static final String PRENOTA_PACCHETTO_IMMAGINI_SERVICE =  "PrenotaPacchettoImmagini";
	
	//servizio 
	public static final String VERIFICA_STATO_PACCHETTO = "VerStatoPacchetto";

	
	// SET MEDIAZIONE DOCUMENTI
	public static final String SET_MEDIAZIONEDOCUMENTI = "SetMediazioneDocumentiApi";
	public static final String CODICE_SUCCESSO_MEDIAZIONEDOCUMENTI = "WA_SMED_DOC";
	
	// GET SCANSIONA QRCODE
	public static final String GET_SCANSIONAQRCODE = "GetScansionaQRCodeApi";
	public static final String CODICE_SUCCESSO_SCANSIONAQRCODE = "WA_VIS_REF_SCAN_QR_CODE";
	public static final String SCARICOREFERTO_SERVICE_URL = "ScarRefSe";
	public static final String SCARICOREFERTO_SERVICE = "SCARICOREFERTOSERVICE";

	public static final int DEFAULT_COD_R_MESSAGGIO_ERRORE_ERRORE_ID = 1;
	// SERVICE API
	public static final String REFERTI_API_SERVICE = "RefertiApiServiceImpl";	
	
	//Servizio CCGetDocumentoPersonale
	public static final String CC_GET_DOCUMENTO_PERSONALE = "GetDocumentoPersonale";
	public static final String COD_SUCCESSO_CC_GET_DOC_PERS = "WA_SEL_DOC_UC";
	
	public static final String CC_SET_AUDIT = "putSetAudit";

	public static final String GET_TACCUINO_SERVICE = "GET_TACCUINO_SERVICE";
	public static final String GET_TACCUINO= "GetTaccuino";
	//TODO nmicheli chiedere che valore mettere
	public static final String CODICE_SUCCESSO_GET_TACCUINO = "WA_VIS_TAC_MED";
	public static final String TACCUINO_SERVICE_URL = "TaccuinSer";
	public static final String TACCUINO_APPLLICATIVO_VERTICALE = "FSETAC";
	public static final String TACCUINO_APPLICAZIONE = "WEBAPP_DMA";
	
	//UltimoDocumento
	public static final String ULTIMO_DOCUMENTO = "UltimoDocumentoApi";
	public static final String CODICE_SUCCESSO_ULTIMO_DOCUMENTO = "WA_ULT_PSS_BDS";
		
	//ultimo Documento service
	public static final String ULTIMO_DOCUMENTO_SERVICE_URL = "UltimoDocu";
	public static final String ULTIMO_DOCUMENTO_PSS = "ULTIMODOCUMENTOSERVICE_SERVICE";
	
	//Servizio Taccuino GetFarmaci
	public static final String GET_FARMACI = "getFarmaci";
	public static final String CODICE_SUCCESSO_GET_FARMACI = "WA_VIS_FARM_TAC_MED";
	public static final String CODICE_SUCCESSO_GET_FARMACI_DATA = "WA_VIS_FARM_TAC_MED_DATA";
	
	//Servizio Taccuino GetDiete
	public static final String GET_DIETE = "getDiete";
	public static final String CODICE_SUCCESSO_GET_DIETE = "WA_VIS_DIETE_TAC_MED";
	public static final String CODICE_SUCCESSO_GET_DIETE_DATA = "WA_VIS_DIETE_TAC_MED_DATA";
	
	//Servizio Taccuino GetEventi
	public static final String GET_EVENTI = "getEventi";
	public static final String CODICE_SUCCESSO_GET_EVENTI = "WA_VIS_EVEN_TAC_MED";
	public static final String CODICE_SUCCESSO_GET_EVENTI_DATA = "WA_VIS_EVEN_TAC_MED_DATA";
	
	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DES = "DES";
	
	//getContattiStrutture
	public static final String CONTATTI_STRUTTURE = "getContattiStrutture";
	public static final String CODICE_SUCCESSO_CONTATTI_STRUTTURE="WA_VIS_CONT_STRUT_TAC_MED";
	public static final String CODICE_SUCCESSO_CONTATTI_STRUTTURE_INT="WA_VIS_CONT_STRUT_TAC_MED_DATA";
	
	//getSintomi
	public static final String LISTA_SINTOMI="getSintomi";
	public static final String CODICE_SUCCESSO_LISTA_SINTOMI="WA_VIS_SINT_TAC_MED";
	public static final String CODICE_SUCCESSO_LISTA_SINTOMI_INT="WA_VIS_SINT_TAC_MED_DATA";
	 
	//getRilevazioni
	public static final String LISTA_RILEVAZIONI="getRilevazioni";
	public static final String CODICE_SUCCESSO_LISTA_RILEVAZIONI="WA_VIS_RIL_TAC_MED";
	public static final String CODICE_SUCCESSO_LISTA_RILEVAZIONI_INT="WA_VIS_RIL_TAC_MED_DATA";
	
	//getDolori
    public static final String GET_LISTA_DOLORI="getDolori";
    public static final String CODICE_SUCCESSO_LISTA_DOLORI="WA_VIS_DOL_TAC_MED";
    public static final String CODICE_SUCCESSO_LISTA_DOLORI_INT="WA_VIS_DOL_TAC_MED_DATA";
    
    //tokeninformation
    public static final String TOKEN_INFORMATION_SERVICE_URL = "TokenInfor";
	public static final String TOKEN_INFORMATION_SERVICE = "TOKENINFORMATIONSERVICE";
	public static final String TOKEN_INFORMATION = "TokenInformationApi";
	//iride
	public static final String TIPO_DOCUMENTO = "TIPO_DOCUMENTO";
	
	// IS MEDIAZIONE DOCUMENTI
	public static final String IS_MEDIAZIONEDOCUMENTI = "IsMediazioneDocumentiApi";
	public static final String RISPOSTA_MED = "000";
	public static final String RISPOSTA_NOVINC = "001";
	public static final String RISPOSTA_AZIENDA = "002";
	public static final String DESCR_RISPOSTA_MED = "Documento smediabile dal refertante e/o validante";
	public static final String DESCR_RISPOSTA_NOVINC = "Documento smediabile perchÃ¨ non soggetto a vincoli";
	public static final String DESCR_RISPOSTA_AZIENDA = "Documento smediabile perchÃ¨ prodotto nella stessa Azienda del richiedente";
	public static final String RISPOSTA_NEGATIVA = "003";
	public static final String DESCR_RISPOSTA_NEGATIVA = "Documento NON smediabile perchÃ¨ prodotto in una Azienda diversa da quella del richiedente";
	public static final String DESCR_RISPOSTA_NEGATIVA_AZIENDA = "Documento NON smediabile sebbene prodotto nella stessa Azienda del richiedente";
	
	// IS DOC RESTITUIBILE
	public static final String IS_DOC_RESTITUIBILE = "GetDocumento";   //TODO: da censire su dmacc_d_catalogo_servizi_operation.nome_operation = IsDocumentoRestituibile
	
	//Nomi Metodi
	public static final String METODO_EXECUTE = "execute";

	//Sessione
	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

	public static final String PATIENT_SESSIONATTR = "appDatacurrentPatient";

	public static final String DOCUMENT_SESSIONATTR = "appDatacurrentDocument";

	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}
}


