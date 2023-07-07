/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

	@Primary
	@Bean(name = "apiopsanDataSource")
	public JndiObjectFactoryBean dataSource(@Value("${jndi.name}") String jndiName) {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName(jndiName);
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		return jndiObjectFactoryBean;
	}
	
	@Primary
	@Bean(name = "namedParameterJdbcTemplate")
	@DependsOn("apiopsanDataSource")
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
			@Qualifier("apiopsanDataSource") DataSource abcDataSource) {
		return new NamedParameterJdbcTemplate(abcDataSource);
	}

	@Primary
	@Bean(name = "dbTransactionManager")
	@DependsOn("apiopsanDataSource")
	public PlatformTransactionManager dbTransactionManager(@Qualifier("apiopsanDataSource") DataSource codcitDataSource) {
//		codcitDataSource.getConnection().setAutoCommit(false);
		return new DataSourceTransactionManager(codcitDataSource);
	}	

//	@Bean(name = "apiopsanclDataSource")
//	public JndiObjectFactoryBean dataSourceCl(@Value("${jndi.name.cl}") String jndiNameCl) {
//		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//		jndiObjectFactoryBean.setJndiName(jndiNameCl);
//		jndiObjectFactoryBean.setResourceRef(true);
//		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
//		return jndiObjectFactoryBean;
//	}
	
//	@Bean(name = "namedParameterJdbcTemplateCl")
//	@DependsOn("apiopsanclDataSource")
//	public NamedParameterJdbcTemplate namedParameterJdbcTemplateCl(
//			@Qualifier("apiopsanclDataSource") DataSource clDataSource) {
//		return new NamedParameterJdbcTemplate(clDataSource);
//	}

//	@Bean(name = "dbTransactionManagerCl")
//	@DependsOn("apiopsanclDataSource")
//	public PlatformTransactionManager dbTransactionManagerCl(@Qualifier("apiopsanclDataSource") DataSource clDataSource) {
////		clDataSource.getConnection().setAutoCommit(false);
//		return new DataSourceTransactionManager(clDataSource);
//	}
	
}
