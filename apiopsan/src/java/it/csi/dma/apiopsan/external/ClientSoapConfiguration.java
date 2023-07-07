/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.external;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.csi.dma.apiopsan.external.documentopersonaleservice.DocumentoPersonaleService;
import it.csi.dma.apiopsan.external.scaricostudiservice.dmass.ScaricoStudiWSBean;

@Configuration
public class ClientSoapConfiguration {

	// ScaricoStudi
	@Value("${scaricoStudiUserBe}")
	private String scaricoStudiUserBe;
	@Value("${scaricoStudiPassBe}")
	private String scaricoStudiPassBe;
	@Value("${scaricoStudiServiceUrl}")
	private String scaricoStudiServiceUrl;

	// DocumentoPersonale
	@Value("${documentoPersonaleUserBe}")
	private String documentoPersonaleUserBe;
	@Value("${documentoPersonalePassBe}")
	private String documentoPersonalePassBe;
	@Value("${documentoPersonaleServiceUrl}")
	private String documentoPersonaleServiceUrl;

	@Bean(name = "scaricoStudiWSBean")
	public ScaricoStudiWSBean generateProxyScaricoStudi() {
		return proxyFactoryBeanScaricoStudi().create(ScaricoStudiWSBean.class);
	}

	@Bean(name = "proxyFactoryBeanScaricoStudi")
	public JaxWsProxyFactoryBean proxyFactoryBeanScaricoStudi() {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setServiceClass(ScaricoStudiWSBean.class);
		proxyFactory.setAddress(scaricoStudiServiceUrl);
		proxyFactory.getOutInterceptors().add(loggingOutInterceptor());
		proxyFactory.getOutInterceptors().add(createScaricoStudiSecurityInterceptor());
		proxyFactory.getInInterceptors().add(loggingInInterceptor());
		return proxyFactory;
	}

	@Bean
	public SoapInterceptor createScaricoStudiSecurityInterceptor() {
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.MUST_UNDERSTAND, "false");
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		outProps.put(WSHandlerConstants.USER, scaricoStudiUserBe);
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {

			@Override
			public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
				for (Callback callBack : callbacks) {
					if (callBack instanceof WSPasswordCallback) {
						((WSPasswordCallback) callBack).setPassword(scaricoStudiPassBe);
					}
				}
			}
		});

		return new WSS4JOutInterceptor(outProps);
	}

	@Bean(name = "documentoPersonaleWSBean")
	public DocumentoPersonaleService generateProxyDocumentoPersonale() {
		return proxyFactoryBeanDocumentoPersonale().create(DocumentoPersonaleService.class);
	}

	@Bean(name = "proxyFactoryBeanDocumentoPersonale")
	public JaxWsProxyFactoryBean proxyFactoryBeanDocumentoPersonale() {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setServiceClass(DocumentoPersonaleService.class);
		proxyFactory.setAddress(documentoPersonaleServiceUrl);
		proxyFactory.getOutInterceptors().add(loggingOutInterceptor());
		proxyFactory.getOutInterceptors().add(createScaricoStudiSecurityInterceptor());
		proxyFactory.getInInterceptors().add(loggingInInterceptor());
		return proxyFactory;
	}
	
	@Bean
	public SoapInterceptor createDocumentoPersonaleSecurityInterceptor() {
		Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put(WSHandlerConstants.MUST_UNDERSTAND, "false");
		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		outProps.put(WSHandlerConstants.USER, documentoPersonaleUserBe);
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CallbackHandler() {

			@Override
			public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
				for (Callback callBack : callbacks) {
					if (callBack instanceof WSPasswordCallback) {
						((WSPasswordCallback) callBack).setPassword(documentoPersonalePassBe);
					}
				}
			}
		});
		return new WSS4JOutInterceptor(outProps);
	}

	@Bean(name = "logIn")
	public LoggingInInterceptor loggingInInterceptor() {
		return new LoggingInInterceptor();
	}

	@Bean(name = "logOut")
	public LoggingOutInterceptor loggingOutInterceptor() {
		return new LoggingOutInterceptor();
	}

}
