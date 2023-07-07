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
public class ApplicativoVerticaleDao extends LoggerUtil {

	private static final String SELECT_APPLIVATIVO_VERTICALE_PER_CODICE = "select count(*) from "
			+ "	dmacc_d_applicativo_verticale ccp where	ccp.codice = :codice and now() >= data_inserimento;";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public int selectApplicativoVerticalePerCodice(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
		try {
			Integer count = jdbcTemplate.queryForObject(SELECT_APPLIVATIVO_VERTICALE_PER_CODICE, namedParameters,Integer.class);
			return count.intValue();
		} catch (EmptyResultDataAccessException e) {
			return 0; 
		} catch (Exception e) {
			String methodName = "SELECT_APPLIVATIVO_PER_CODICE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

}
