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

import it.csi.dma.apiopsan.dto.custom.Ruolo;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.RuoloMapper;
import it.csi.dma.apiopsan.integration.dao.utils.RuoloPuaMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class DmaccTDecodificaRuoliPuaDao extends LoggerUtil {

	public static final String SELECT_RUOLO = "SELECT " 
											+ "* " 
											+ "FROM " 
											+ "	DMACC_T_DECODIFICA_RUOLI_PUA DTDRP "
											+ "WHERE " 
											+ "DTDRP.SOL = :sol " 
											+ "AND DTDRP.CODICE_RUOLO_PUA = :ruolo ";
	
	public static final String SELECT_RUOLO_TAB_RUOLI = "SELECT " 
			+ " * " 
			+ "FROM " 
			+ "	DMACC_D_RUOLO "
			+ "WHERE " 
			+ "CODICE_RUOLO = :ruolo ";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public RuoloPua selectRuolo(String ruolo, String xCodiceServizio) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ruolo", ruolo).addValue("sol",
				xCodiceServizio);

		try {
			RuoloPua selected = jdbcTemplate.queryForObject(SELECT_RUOLO, namedParameters, new RuoloPuaMapper());
			return selected;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectErroreDescFromErroreCod";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}

	public Ruolo selectRuoloFromTableRuolo(String ruolo) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ruolo", ruolo);

		try {
			Ruolo selected = jdbcTemplate.queryForObject(SELECT_RUOLO_TAB_RUOLI, namedParameters, new RuoloMapper());
			return selected;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectRuoloFromTableRuolo";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
}
