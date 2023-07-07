/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.custom.DatiServiziEsterniDao;
import it.csi.dma.apiopsan.integration.dao.custom.DmaccTDecodificaRuoliPuaDao;
import it.csi.dma.apiopsan.tokeninformation.dmacc.GetTokenInformationRequest;
import it.csi.dma.apiopsan.tokeninformation.dmacc.GetTokenInformationResponse;
import it.csi.dma.apiopsan.tokeninformation.dmacc.TokenInformationService;
import it.csi.dma.apiopsan.tokeninformation.dmacc.TokenInformationService_Service;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Service
public class GetTokenInformationService extends LoggerUtil {

	private static final QName SERVICE_NAME_TOKEN_INFORMATION = new QName("http://dmacc.csi.it/",
			"TokenInformationService");
	@Autowired
	DatiServiziEsterniDao datiServiziEsterniDao;

	@Autowired
	DmaccTDecodificaRuoliPuaDao dmaccTDecodificaRuoliPuaDao;

	public GetTokenInformationResponse verificaToken(String xRequestId, String xCodiceServizio, String token) {

		URL wsdlURL;

		try {
			String consensiServiceUrl = datiServiziEsterniDao
					.selectUrlServizioPerCodice(Constants.TOKEN_INFORMATION_SERVICE_URL) + "?wsdl";
			wsdlURL = new URL(consensiServiceUrl);
			TokenInformationService_Service cs = new TokenInformationService_Service(wsdlURL,
					SERVICE_NAME_TOKEN_INFORMATION);
			TokenInformationService port = cs.getTokenInformationService();
			
			org.apache.cxf.endpoint.Client client = ClientProxy.getClient(port);
			org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
			
			client.getOutInterceptors().add(loggingOutInterceptor());
			client.getOutInterceptors().add(createVerificaServicesSecurityInterceptor());
			client.getInInterceptors().add(loggingInInterceptor());

			GetTokenInformationRequest req = createRicercaTokenRequest(xRequestId,xCodiceServizio,token);

			GetTokenInformationResponse tokenResponse = port.getTokenInformation(req);

			return tokenResponse;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errore chiamata servizio : " + Constants.TOKEN_INFORMATION_SERVICE);
		}
	}

	protected GetTokenInformationRequest createRicercaTokenRequest(String xRequestId, String xCodiceServizio, String token)
			throws DatabaseException {

		
		GetTokenInformationRequest req = new GetTokenInformationRequest();
		
		req.setApplicazione(xCodiceServizio);
		req.setIpBrowser(xRequestId);
		req.setToken(token);
	
		return req;

	}
	
	private LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	
	private LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}
	
	private SoapInterceptor createVerificaServicesSecurityInterceptor() throws DatabaseException {
		  Map<String, Object> outProps = new HashMap<String, Object>();
		  CredenzialiServizio credenziali = datiServiziEsterniDao.selectCredenzialiPerServizio(Constants.TOKEN_INFORMATION_SERVICE);
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
}
