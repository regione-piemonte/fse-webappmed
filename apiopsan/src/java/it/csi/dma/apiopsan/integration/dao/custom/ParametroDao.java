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

import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class ParametroDao extends LoggerUtil {

	private static final String SELECT_PARAMETRO_VALORE = "select valore from "
			+ "dmacc_t_configurazione where chiave = :chiave";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public String selectValoreParametroFromParametroNome(String parametroNome) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("chiave", parametroNome);
		try {
			return jdbcTemplate.queryForObject(SELECT_PARAMETRO_VALORE, namedParameters, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			var methodName = "selectValoreParametroFromParametroNome";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}
	
	public <T> T selectValoreParametroFromParametroNomeV2(String parametroNome, Class<T> clazz) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("chiave", parametroNome);
		try {
			return jdbcTemplate.queryForObject(SELECT_PARAMETRO_VALORE, namedParameters, clazz);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			var methodName = "selectValoreParametroFromParametroNome";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

}
