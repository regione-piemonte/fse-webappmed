/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.dma.apiopsan.business.be.AuditApi;
import it.csi.dma.apiopsan.business.be.CataloghiApi;
import it.csi.dma.apiopsan.business.be.CittadiniApi;
import it.csi.dma.apiopsan.business.be.LoginApi;
import it.csi.dma.apiopsan.business.be.OperatoreSanitarioApi;
import it.csi.dma.apiopsan.business.be.RefertiApi;
import it.csi.dma.apiopsan.util.SpringApplicationContextHelper;


@ApplicationPath("/")
public class ApiopsanRestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public ApiopsanRestApplication() {
		
		singletons.add(SpringApplicationContextHelper.getBean(AuditApi.class)); //new AuditApiServiceImpl());
		singletons.add(SpringApplicationContextHelper.getBean(OperatoreSanitarioApi.class)); //new OperatoreSanitarioApiServiceImpl());
		singletons.add(SpringApplicationContextHelper.getBean(CittadiniApi.class)); //new CittadiniApiServiceImpl());
		singletons.add(SpringApplicationContextHelper.getBean(CataloghiApi.class)); //new CataloghiApiServiceImpl());
		singletons.add(SpringApplicationContextHelper.getBean(LoginApi.class)); //new LoginApiServiceImpl());
		singletons.add(SpringApplicationContextHelper.getBean(RefertiApi.class)); //new AuditApiServiceImpl());
		
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}


}
