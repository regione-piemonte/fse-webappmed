/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.dma.apiopsan.business.be.AuditApi;
import it.csi.dma.apiopsan.util.AppServletContextListener;

public class SpringApplicationContextHelper implements ApplicationContextAware {

	private static ApplicationContext appContext;

	private static Map<String, Object> beanCache = new HashMap<>();

	private static Map<String, Object> restEasyServiceCache = new HashMap<>();
	
	// Private constructor prevents instantiation from other classes
    private SpringApplicationContextHelper() {}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appContext = applicationContext;
	}
	


	@SuppressWarnings("unchecked")
	private static <T> T getBean(Class<T> clazz, boolean cacheable) {
		String beanName = clazz.getName();
		
		if (cacheable && beanCache.containsKey(beanName)) {
			return (T) beanCache.get(beanName);
		}
		
		Object bean = appContext.getBean(clazz);
				
		if (cacheable) {
			beanCache.put(beanName, bean);
		}
		
		return (T) bean;
		
	}
	
	private static Object getBean(String beanName, boolean cacheable) {

		if (cacheable && beanCache.containsKey(beanName)) {
			return beanCache.get(beanName);
		}
		
		Object bean = null;
		
		if (appContext.containsBean(beanName)) {
			bean = appContext.getBean(beanName);
		} else {
			bean = appContext.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));
		}
		
		if (cacheable) {
			beanCache.put(beanName, bean);
		}
		
		return bean;
	}

	public static Object getBean(String beanName) {
		return getBean(beanName, true);
	}

	public static <T> T getBean(Class<T> clazz) {
		return getBean(clazz, true);
	}


}
