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
public class ApplicazioneRichiedenteDao extends LoggerUtil {

	private static final String SELECT_APPLIVATIVO_RICHIEDENTE_PER_CODICE = "select count(*) from "
			+ "	dmacc_d_applicazione_richiedente ccp where ccp.codice_applicazione = :codice and now() >= data_inserimento;";
	
	private static final String SELECT_ID_APPLIVATIVO_RICHIEDENTE_PER_CODICE = "select id from "
			+ "	dmacc_d_applicazione_richiedente ccp where ccp.codice_applicazione = :codice and now() >= data_inserimento;";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public int selectApplicativoRichiedentePerCodice(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
		try {
			Integer count = jdbcTemplate.queryForObject(SELECT_APPLIVATIVO_RICHIEDENTE_PER_CODICE, namedParameters,Integer.class);
			return count.intValue();
		} catch (Exception e) {
			String methodName = "SELECT_APPLIVATIVO_RICHIEDENTE_PER_CODICE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

	public long selectIdApplicativoRichiedentePerCodice(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
		try {
			long id = jdbcTemplate.queryForObject(SELECT_ID_APPLIVATIVO_RICHIEDENTE_PER_CODICE, namedParameters,long.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			return 0; 
		} catch (Exception e) {
			String methodName = "selectIdApplicativoRichiedentePerCodice";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}
}
