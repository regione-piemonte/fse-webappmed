/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.custom.ComponenteLocale;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.CatalogoServiziOperationMapper;
import it.csi.dma.apiopsan.integration.dao.utils.ComponenteLocaleMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class ComponenteLocaleDao extends LoggerUtil {

	private static final String SELECT_COMPONENTE_LOCALE_PER_CODICE = "select * from "
			+ "	dmacc_t_componente_locale ccp where	ccp.codice = :codice and now() >= data_inserimento;";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ComponenteLocale selectComponenteLocalePerCodice(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
		try {
			ComponenteLocale componente = jdbcTemplate.queryForObject(SELECT_COMPONENTE_LOCALE_PER_CODICE, namedParameters,new ComponenteLocaleMapper());
			return componente;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_COMPONENTE_LOCALE_PER_CODICE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

}
