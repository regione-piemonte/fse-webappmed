/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class CatalogoLogClDao extends LoggerUtil {

//	@Autowired
//	@Qualifier("namedParameterJdbcTemplateCl")
//	private NamedParameterJdbcTemplate jdbcTemplate;
//
//	public static final String SELECT_ERRORE_DESC_BY_COD = "SELECT descrizione_errore FROM dmacl_d_catalogo_log where "
//			+ "codice = :codice and now() >= data_inserimento";
//
//	public String selectErroreDescByCod(String codice) throws DatabaseException {
//		try {
//			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
//			
//			return jdbcTemplate.queryForObject(SELECT_ERRORE_DESC_BY_COD, namedParameters, String.class);
//			
//		} catch (Exception e) {
//			String methodName = "selectErroreDescFromErroreCod";
//			logError(methodName, e.getMessage());
//			throw new DatabaseException(e);
//		}
//	}

}
