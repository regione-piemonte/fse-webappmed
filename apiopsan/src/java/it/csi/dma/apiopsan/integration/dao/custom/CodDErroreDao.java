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
public class CodDErroreDao extends LoggerUtil {

	public static final String SELECT_ERRORE_DESC = "SELECT descrizione_errore FROM dmacc_d_catalogo_log where "
			+ "codice= :erroreCod and now() >= data_inserimento;";
	
	public static final String SELECT_ERRORE_ID = "SELECT id FROM dmacc_d_catalogo_log where "
			+ "codice= :erroreCod and now() >= data_inserimento;";
	
	public static final String SELECT_SUCCESSO_DESC = "SELECT descrizione FROM dmacc_d_catalogo_log_audit where "
			+ "codice= :erroreCod and now() >= data_inserimento;";
	
	public static final String SELECT_SUCCESSO_ID = "SELECT id FROM dmacc_d_catalogo_log_audit where "
			+ "codice= :erroreCod and now() >= data_inserimento;";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public String selectErroreDescFromErroreCod(String erroreCod) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("erroreCod", erroreCod);
		try {
			return jdbcTemplate.queryForObject(SELECT_ERRORE_DESC, namedParameters, String.class);
		}  catch (Exception e) {
			String methodName = "selectErroreDescFromErroreCod";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public long selectErroreIdFromErroreCod(String erroreCod) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("erroreCod", erroreCod);
		try {
			return jdbcTemplate.queryForObject(SELECT_ERRORE_ID, namedParameters, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		} catch (Exception e) {
			String methodName = "selectErroreIdFromErroreCod";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	public String selectSuccessoDescFromCod(String erroreCod) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("erroreCod", erroreCod);
		try {
			return jdbcTemplate.queryForObject(SELECT_SUCCESSO_DESC, namedParameters, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectSuccessoDescFromCod";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public long selectSuccessoIdFromCod(String erroreCod) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("erroreCod", erroreCod);
		try {
			return jdbcTemplate.queryForObject(SELECT_SUCCESSO_ID, namedParameters, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		} catch (Exception e) {
			String methodName = "selectSuccessoIdFromCod";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
}
