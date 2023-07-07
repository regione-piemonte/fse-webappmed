/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.validator.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.interceptor.SoapInterceptor;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.TipoDocumento;
import it.csi.dma.apiopsan.dto.custom.ComponenteLocale;
import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.ApplicativoVerticaleDao;
import it.csi.dma.apiopsan.integration.dao.custom.ApplicazioneRichiedenteDao;
import it.csi.dma.apiopsan.integration.dao.custom.CatalogoLogClDao;
import it.csi.dma.apiopsan.integration.dao.custom.CodDErroreDao;
import it.csi.dma.apiopsan.integration.dao.custom.ComponenteLocaleDao;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.integration.dao.custom.GetRegimiDao;
import it.csi.dma.apiopsan.integration.dao.custom.ParametroDao;
import it.csi.dma.apiopsan.integration.dao.custom.PazienteDao;
import it.csi.dma.apiopsan.integration.dao.custom.TipoDocumentoDao;
import it.csi.dma.apiopsan.integration.dao.custom.UtenteDao;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.Converter;
import it.csi.dma.apiopsan.util.CreateTemplateMessage;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;
import it.csi.dma.apiopsan.util.enumerator.CodeErrorEnum;
import it.csi.dma.apiopsan.util.enumerator.ErrorParamEnum;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaService;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaService_Service;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaUtenteAbilitatoRequest;
import it.csi.dma.apiopsan.verificaservices.dmacc.VerificaUtenteAbilitatoResponse;



public abstract class BaseValidate extends LoggerUtil {

	@Autowired
	CodDErroreDao codDErroreDao;
	
	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;
	
	@Autowired
	ApplicativoVerticaleDao applicativoVerticaleDao;

	@Autowired
	ApplicazioneRichiedenteDao applicazioneRichiedenteDao;
	
	@Autowired
	ComponenteLocaleDao componenteLocaleDao;
	
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;
	
	@Autowired
	ParametroDao parametroDao;
	
	@Autowired
	TipoDocumentoDao tipoDocumentoDao;
	
	@Autowired 
	private UtenteDao utenteDao;
	
	@Autowired 
	private PazienteDao pazienteDao;
	
//	@Autowired
//	private CatalogoLogClDao catalogoLogClDao;
	
	@Autowired
	GetRegimiDao regimiDao;

    private static final QName SERVICE_NAME_VERIFICA = new QName("http://dmacc.csi.it/", "VerificaService");

//	@Value("${verificaServiceUrl}")
//	private String verificaServiceUrl;
/*
 * I metodi "check" restituiscono true se il controllo fallisce e bisogna dare segnalazione di  errore
 * restituisce false se il controllo ha avuto esito positivo
 */
	protected ErroreDettaglioExt getValueGenericError(String key, String param) throws DatabaseException {
		String value = codDErroreDao.selectErroreDescFromErroreCod(key);

		if(param!=null) {
			Map<String, Object> parameter = generateParamList("5", param);
			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		}
		return  setError(key, value);
	}
	
	protected String getSuccessAuditInsert(String key, String param) throws DatabaseException {
		String value = codDErroreDao.selectSuccessoDescFromCod(key);

		if(param!=null) {
			Map<String, Object> parameter = generateParamList("0", param);
			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		}
		return  value;
	}
	
	protected String getSuccessAuditInsertArray(String key, String[] param) throws DatabaseException {
		String value = codDErroreDao.selectSuccessoDescFromCod(key);
		if (param != null) {
		if(param.length>0) {
			Map<String, Object> parameter = new HashMap<String, Object>();
			for (Integer i=0; i<param.length ; i++) {
				if (param[i]!=null)
				parameter.put(i.toString(), param[i]);
			}
			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		}
		}
		return  value;
	}
	
	
	protected String getSuccessAuditInsert(String key, String Operatore, String Assistito) throws DatabaseException {
		String value = codDErroreDao.selectSuccessoDescFromCod(key);

		Map<String, Object> parameter = new HashMap<String, Object>();
		if(Operatore!=null) 
			parameter.put("0", Operatore);
			else
				parameter.put("0", null);
			if(Assistito!=null) 
			parameter.put("1", Assistito);
			else
			parameter.put("1", null);	
			
			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		return  value;
	}
	
	protected String getSuccessAuditInsert(String key, String citId, String cognome, String nome, String dataNascita) throws DatabaseException {
		String value = codDErroreDao.selectSuccessoDescFromCod(key);

		Map<String, Object> parameter = new HashMap<String, Object>();
		
		if(cognome!=null) 
			parameter.put("0", cognome);
		else
			parameter.put("0", null);
		if(nome!=null) 
			parameter.put("1", nome);
		else
			parameter.put("1", null);
		if(dataNascita!=null) 
			parameter.put("2", dataNascita);
		else
			parameter.put("2", null);
		if(citId!=null) 
			parameter.put("3", citId);
		else
			parameter.put("3", null);
		
		value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		
		return  value;
	}
	
	protected String getSuccessAuditInsert(String key, Map<String, Object> parameter) throws DatabaseException {
		String value = codDErroreDao.selectSuccessoDescFromCod(key);
		value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		return  value;
	}
	
	protected String getErrorLogInsert(String key, String param, String param1,String param2) throws DatabaseException {
		String value = codDErroreDao.selectErroreDescFromErroreCod(key);

		if(param!=null) {
			Map<String, Object> parameter = generateParamList("3", param);
			parameter.put("4", param1);
			parameter.put("5", param2);
			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		}
		return  value;
	}

	protected ErroreDettaglioExt getValueFormalError(String key, String param,String fieldValue) throws DatabaseException {
		String value = codDErroreDao.selectErroreDescFromErroreCod(key);

		if(param!=null) {
			Map<String, Object> parameter = generateParamList("5", param);
			parameter.put("6", fieldValue);
			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
		}
		
		return setError(key,value);
	}
	
	private Map<String, Object> generateParamList(String paramKey, String paramValue) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put(paramKey, paramValue);
		return parameter;
	}

	private ErroreDettaglioExt setError(String key, String value) {
		ErroreDettaglioExt erroreDettaglio = new ErroreDettaglioExt();
		erroreDettaglio.setChiave(key);
		erroreDettaglio.setValore(value);
		return erroreDettaglio;
	}

	protected boolean formalCheckCF(String cf) {
		
		if(cf.length() != 11 && cf.length() != 16)
			return true;

		String regexCF 	 = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
		String regexPIVA = "[0-9]{11}";
		
		if(cf.length() == 16 &&  !Pattern.matches(regexCF, cf)) {
			return true;
		}

		if(cf.length() == 11 &&  !Pattern.matches(regexPIVA, cf)) {
			return true;
		}
		
	    return false;
	}
	
	protected boolean formalCheckPresenzaUtente(String shibIdentitaCodiceFiscale) throws DatabaseException {
		
		long id = utenteDao.selectIdUtentePerCodiceFiscale(shibIdentitaCodiceFiscale);
		if (id == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean formalCheckPresenzaPaziente(String citId) throws DatabaseException {
		
		long id = pazienteDao.selectIdPazientePerCodiceFiscale(citId);
		if (id == 0) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean formalCheckPresenzaRuolo(String ruolo, String xCodiceServizio) throws DatabaseException {
		
		RuoloPua existingRole = dmaccTDecodificaRuoliPuaDao.selectRuolo(ruolo,xCodiceServizio);
		if(existingRole == null)
			return true ;
		else
			return false;
		
	}
	
	protected boolean formalCheckPresenzaRegimi(String codiceRegime) throws DatabaseException {
		
		long regime = regimiDao.selectIdRegimiPerLogAudit(codiceRegime);
		if(regime == 0)
			return true ;
		else
			return false;
		
	}
	
	protected boolean formalCheckPresenzaAudit(String codiceAudit) throws DatabaseException {
		String value = codDErroreDao.selectSuccessoDescFromCod(codiceAudit);
		if(value == null)
			return true ;
		else
			return false;
		
	}

	protected boolean formalCheckCodiceVerticale(String codice) throws DatabaseException {
		
		int existingRole = applicativoVerticaleDao.selectApplicativoVerticalePerCodice(codice);
		if(existingRole == 0)
			return true ;
		else
			return false;
		
	}
	
	protected boolean formalCheckCodiceServizio(String codice) throws DatabaseException {
		
		int existingRole = applicazioneRichiedenteDao.selectApplicativoRichiedentePerCodice(codice);
		if(existingRole == 0)
			return true ;
		else
			return false;
		
	}
	
	protected boolean formalCheckComponenteLocale(String codice) throws DatabaseException {
		
		ComponenteLocale componente = componenteLocaleDao.selectComponenteLocalePerCodice(codice);
		if(componente == null)
			return true ;
		else
			return false;
		
	}
	
	protected boolean formalCheckCitId(String citId) {
		
		if (Util.isValorizzato(citId)) {
		if(citId.length() < 11 || citId.length() > 16)
			return true;
		}
	    return false;
	}		
	
	protected boolean formalCheckDataNascita(String dataNascita) {
		return Util.isData(dataNascita,"yyyy-MM-dd",null);
	}
	
	protected boolean formalCheckCategoria(String categoria) {
		
		if (Util.isValorizzato(categoria)) {
		if(!categoria.equalsIgnoreCase(Constants.PERSONALE) && !categoria.equalsIgnoreCase(Constants.FSE))
			return true;
		}
	    return false;
	}	
	
	protected boolean formalCheckTipoMedico(String tipoMedico) {
		
		if (Util.isValorizzato(tipoMedico)) {
		if(!tipoMedico.equalsIgnoreCase(Constants.TIPO_MEDICO_REFERTANTE) && !tipoMedico.equalsIgnoreCase(Constants.TIPO_MEDICO_VALIDANTE) && !tipoMedico.equalsIgnoreCase(Constants.TIPO_MEDICO_VALIDANTEOREFERTANTE))
			return true;
		}
	    return false;
	}	
	
	
	protected boolean formalCheckLenStringSearch(String chiave, String stringa) throws DatabaseException {
		
		String valore = parametroDao.selectValoreParametroFromParametroNome(chiave);
		if(valore == null)
			return true ;
		else {
			//controlla la lunghezza del valore
			if (stringa.length()< Converter.getInt(valore)) {
				return true;
			}
		}
			return false;
		
	}
	
	protected boolean formalCheckDateRicerca(Date dataInizio, Date dataFine) throws DatabaseException {
		
		if (dataInizio == null || dataFine == null)
			return false;
		if (dataInizio.compareTo(dataFine) > 0) {
			return true ;
		}
		return false;
	}

	protected boolean formalCheckTipoDocumento(String tipodoc) throws DatabaseException {
		
		TipoDocumento tipo = tipoDocumentoDao.selectTipoDocumento(tipodoc);
		if(tipo == null)
			return true ;
		else
			return false;
		
	}
	
	protected boolean formalCheckCategoriaTipoDocumento(String categoria,String tipodoc) throws DatabaseException {
		
		CategoriaTipologia tipo = tipoDocumentoDao.selectCategoriaTipoDocumento(categoria, tipodoc);

		return tipo == null;
	}
	
	protected boolean formalCheckTipoCorrelazioneDocumento(String tipodoc) throws DatabaseException {
		
		for (String tipoDocumento : Constants.TIPO_CORRELLAZIONE_DOCUMENTO) {
			
			if(tipoDocumento.equalsIgnoreCase(tipodoc)) {
				return false;
			}
		}
		return true;
	}

	protected VerificaUtenteAbilitatoResponse functionalCheckUtenteAbilitato(VerificaUtenteAbilitatoRequest verificaUtenteAbilitato) throws DatabaseException {
		
		URL wsdlURL;
		try {
			String verificaServiceUrl = datiServiziEsterniDao.selectUrlServizioPerCodice(Constants.VERIFICA_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(verificaServiceUrl);
        VerificaService_Service ss = new VerificaService_Service(wsdlURL, SERVICE_NAME_VERIFICA);
        VerificaService port = ss.getVerificaServicePort();
	
        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
        //org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
        
        client.getOutInterceptors().add(loggingOutInterceptor());
		client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
		client.getInInterceptors().add(loggingInInterceptor());
        
        VerificaUtenteAbilitatoResponse verificaUtenteAbilitatoResp = port.verificaUtenteAbilitato(verificaUtenteAbilitato);
		return verificaUtenteAbilitatoResp;
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.VERIFICA_SERVICE);
		}

	}

	
	private LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	
	private LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}
	
	private SoapInterceptor createVerificaServicesSecurityInterceptor() throws DatabaseException {
		  Map<String, Object> outProps = new HashMap<String, Object>();
		  CredenzialiServizio credenziali = datiServiziEsterniDao.selectCredenzialiPerServizio(Constants.VERIFICA_SERVICE);
		  outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		    outProps.put(WSHandlerConstants.USER, credenziali.getUsername());
		    outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		    outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_NONCE, "true");
		    outProps.put(WSHandlerConstants.ADD_USERNAMETOKEN_CREATED, "true");
		    outProps.put(WSHandlerConstants.MUST_UNDERSTAND, "false");
		    outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {

		    	@Override
		        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		            for(Callback callBack:callbacks){
		                if(callBack instanceof WSPasswordCallback){
		                    ((WSPasswordCallback)callBack).setPassword(credenziali.getPassword());
		                }
		            }
		        }
            });


		    return new WSS4JOutInterceptor(outProps);
	}
	
	public ErroreDettaglioExt getValueGenericErrorNoParam(String key) throws DatabaseException {
		String value = codDErroreDao.selectErroreDescFromErroreCod(key);

			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, null);

		return  setError(key, value);
	}	
	
	public void validateTipologiaDocumento(String tipologia, List<ErroreDettaglioExt> result) 
			throws DatabaseException {
		
		if (tipologia != null && formalCheckTipoDocumento(tipologia)) {
				result.add(getValueFormalError(CodeErrorEnum.TipoDocumentoNonValido.getCode(), 
						ErrorParamEnum.TIPOLOGIA_DOCUMENTO.getCode(), tipologia));
			
		}
	}
	
	public void validateCategoriaDocumento(String categoria, List<ErroreDettaglioExt> result) 
			throws DatabaseException {
		
		if (!categoria.equals(Constants.FSE) && !categoria.equals(Constants.PERSONALE)) {
				result.add(getValueFormalError(CodeErrorEnum.CategoriaTipoDocumentoNonValido.getCode(), 
						ErrorParamEnum.CATEGORIA.getCode(), categoria));
			
		}
	}
	
	public void validateUtentePerCodiceFiscale(String shibIdentitaCodiceFiscale, List<ErroreDettaglioExt> result) 
			throws DatabaseException {
		
		long id = utenteDao.selectIdUtentePerCodiceFiscale(shibIdentitaCodiceFiscale);
		if (id==0) {
			 result.add(getValueFormalError(CodeErrorEnum.FSE_ER_562.getCode(),
						ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(),shibIdentitaCodiceFiscale)); 
		}
	}
	
	public void validateMaxIntervalloDate(Date dataInizio, Date dataFine, List<ErroreDettaglioExt> result) 
			throws DatabaseException {
		
		int maxIntervallo = parametroDao.selectValoreParametroFromParametroNomeV2(Constants.RICERCADOC_COUNT_MAX_INTERVALLO, 
				Integer.class);
		
		long diffInMillies = Math.abs(dataFine.getTime() - dataInizio.getTime());
	    long giorni = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    if (giorni > maxIntervallo) {
	    	result.add(getValueFormalError(CodeErrorEnum.IntevalloDateSuperato.getCode(),
					ErrorParamEnum.FILTRO_DATE.getCode(), String.valueOf(giorni))); 
	    }
		
	}
	
	public void validatePayloadSearchTuttiDoc(PayloadSearchTuttiDoc payloadSearchTuttiDoc, List<ErroreDettaglioExt> result) 
			throws DatabaseException {
		
		if (payloadSearchTuttiDoc.getCategoriaTipologia() == null || 
				payloadSearchTuttiDoc.getCategoriaTipologia().size() == 0) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CATEGORIA_TIPOLOGIA_DOCUMENTO.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getFiltroDocs() == null && 
				payloadSearchTuttiDoc.getFiltroDocs().getDataInizio() != null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.DATA_INIZIO.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getFiltroDocs() == null && 
				payloadSearchTuttiDoc.getFiltroDocs().getDataFine() != null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.DATA_FINE.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getLimit() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.LIMIT.getCode()));
		}
		
		if (payloadSearchTuttiDoc.getOffset() == null) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.OFFSET.getCode()));
		}
		
		validateDateRicerca(payloadSearchTuttiDoc.getFiltroDocs().getDataInizio(), 
				payloadSearchTuttiDoc.getFiltroDocs().getDataFine(), result);
		
		

	}
	
	public void validateDateRicerca(Date dataInizio, Date dataFine, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (formalCheckDateRicerca(dataInizio, dataFine)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_206.getCode(), ErrorParamEnum.DATA_INIZIO.getCode() + " " + ErrorParamEnum.DATA_FINE.getCode(),
					dataInizio + " " + dataFine));
		}
	}

	public void validateCittadinoId(String citId, List<ErroreDettaglioExt> result) throws DatabaseException {
		/*
		if (StringUtils.isEmpty(citId)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.CIT_ID.getCode()));
		}
		*/
		
		if (!StringUtils.isEmpty(citId) && formalCheckCitId(citId)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_566.getCode(), ErrorParamEnum.CIT_ID.getCode(), citId));
		}
	}
	
	public void validateShibbolethCodiceFiscale(String shibIdentitaCodiceFiscale, List<ErroreDettaglioExt> result) 
			throws DatabaseException {
		
		if (StringUtils.isEmpty(shibIdentitaCodiceFiscale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode()));		
			return;
		}
		
		if (formalCheckCF(shibIdentitaCodiceFiscale)) {
			result.add(
					getValueFormalError(CodeErrorEnum.FSE_ER_562.getCode(), ErrorParamEnum.SHIB_IDENTITA_CODICEFISCALE.getCode(), shibIdentitaCodiceFiscale));
		}
		
		validateUtentePerCodiceFiscale(shibIdentitaCodiceFiscale, result);
	}
	
	public void validateRequestId(String xRequestId, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (StringUtils.isEmpty(xRequestId)) {
			result.add(
					getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.X_REQUEST_ID.getCode()));
		}
	}
	
	public void validateForwardedFor(String xForwardedFor, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (StringUtils.isEmpty(xForwardedFor)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_FORWARDED_FOR.getCode()));
		}
	}
	
	public void validateCodiceServizioFor(String xCodiceServizio, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (StringUtils.isEmpty(xCodiceServizio)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_SERVIZIO.getCode()));
			return;
		}
		
		if (formalCheckCodiceServizio(xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.CC_ER_002.getCode(), ErrorParamEnum.X_CODICE_SERVIZIO.getCode(), xCodiceServizio));
		}
	}
	
	public void validateCollocazione(String collocazione, List<ErroreDettaglioExt> result) throws DatabaseException {

		if (StringUtils.isEmpty(collocazione)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.COLLOCAZIONE.getCode()));
		}
	}
	
	public void validateRuolo(String ruolo, String xCodiceServizio, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (StringUtils.isEmpty(ruolo)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(), ErrorParamEnum.RUOLO.getCode()));
			return;
		}
		

		if (formalCheckPresenzaRuolo(ruolo, xCodiceServizio)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_509.getCode(), ErrorParamEnum.RUOLO.getCode(), ruolo));
		}
	}
	
	public void validateCodiceVerticale(String xCodiceVerticale, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (StringUtils.isEmpty(xCodiceVerticale)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.X_CODICE_VERTICALE.getCode()));
			return;
		}
		
		if (formalCheckCodiceVerticale(xCodiceVerticale)) {
			result.add(getValueFormalError(CodeErrorEnum.FSE_ER_529.getCode(), ErrorParamEnum.X_CODICE_VERTICALE.getCode(), xCodiceVerticale));
		}
	}
	
	public void validateRegime(String regime, List<ErroreDettaglioExt> result) throws DatabaseException {
		if (StringUtils.isEmpty(regime)) {
			result.add(getValueGenericError(CodeErrorEnum.CC_ER_001.getCode(),
					ErrorParamEnum.REGIME.getCode()));
		}
	}
	
	
//	public ErroreDettaglioExt getValueFormalErrorFromCl(String codiceErrore, String param, String fieldValue)
//			throws DatabaseException {
//
//		String value = catalogoLogClDao.selectErroreDescByCod(codiceErrore);
//
//		if (param != null) {
//			Map<String, Object> parameter = generateParamList("5", param);
//			parameter.put("6", fieldValue);
//			value = CreateTemplateMessage.generateTextByTemplateAndMap(value, parameter);
//		}
//
//		return setError(codiceErrore, value);
//	}
		
}
