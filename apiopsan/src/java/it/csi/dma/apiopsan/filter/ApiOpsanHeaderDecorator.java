/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import it.csi.dma.apiopsan.dto.custom.UserInfo;



@Provider
@PreMatching
public class ApiOpsanHeaderDecorator implements ContainerRequestFilter {
	private static final String X_FORWARDED_FOR = "X-Forwarded-For";
	private static final Logger logger = Logger.getLogger(ApiOpsanHeaderDecorator.class);
	public static final String AUTH_SHIBBOLETH = "Shib-Identita-CodiceFiscale";
	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

    @Context
    HttpServletRequest webRequest;
	
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
    	logger.info("[ApiOpsanHeaderDecorator::filter] BEGIN");
		try {
			if (webRequest.getSession().getAttribute(USERINFO_SESSIONATTR) != null) {
				UserInfo userInfo = (UserInfo) webRequest.getSession().getAttribute(USERINFO_SESSIONATTR);
				logger.info("[ApiOpsanHeaderDecorator::filter] Uente loggato - viene forzato " + AUTH_SHIBBOLETH
						+ " :: " + userInfo.getCodFisc());
				logger.info("[ApiOpsanHeaderDecorator::filter] ctx==null :: " + (ctx==null));

				ctx.getHeaders().add(AUTH_SHIBBOLETH, userInfo.getCodFisc());
				
				if (!ctx.getHeaders().containsKey(X_FORWARDED_FOR)) {
					ctx.getHeaders().add(X_FORWARDED_FOR, webRequest.getRemoteAddr());	
					logger.info("[ApiOpsanHeaderDecorator::filter] Viene aggiunto X_FORWARDED_FOR  :: " + webRequest.getRemoteAddr());
				} else {
					String xff = ctx.getHeaders().getFirst(X_FORWARDED_FOR);
					if (!xff.contains(webRequest.getRemoteAddr())) {
						xff += ", " + webRequest.getRemoteAddr();
						ctx.getHeaders().put(X_FORWARDED_FOR, Arrays.asList(xff));
					}
					logger.info("[ApiOpsanHeaderDecorator::filter] X_FORWARDED_FOR presente :: " + xff);
					
				}
				
			} else {
				logger.info("[ApiOpsanHeaderDecorator::filter] Utente non loggato.");
			}
		} catch (Exception e) {
			logger.warn("[ApiOpsanHeaderDecorator::filter] ", e);
		}

    	logger.info("[ApiOpsanHeaderDecorator::filter] END");
		
    }
}