/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.CredenzialiServizioMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class DatiServiziEsterniDao extends LoggerUtil {

	@Value("${encryption_key}")
	private String encryptionKey;
	
	private static final String SELECT_CREDENZIALI_SERVIZIO = "select codice_servizio as codiceServizio,username,"
			+ " convert_from(pgp_sym_decrypt_bytea(PASSWORD, '@encryption_key@'),'UTF8') AS password from "
			+ "	dmacc_d_credenziali_servizi ccp where ccp.codice_servizio = :codice and now()::date BETWEEN data_inizio_validita::date and COALESCE(data_fine_validita::date, now()::date);";
	
	private static final String SELECT_URL_SERVIZIO_PER_CODICE = "select url_servizio from dmacc_d_servizio_componente_locale a "
			+ "	where a.codice = :servizio";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public CredenzialiServizio selectCredenzialiPerServizio(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
		try {
			String query = replaceKey(SELECT_CREDENZIALI_SERVIZIO, encryptionKey);
			CredenzialiServizio credenziali = jdbcTemplate.queryForObject(query , namedParameters,new CredenzialiServizioMapper());
			
			return credenziali;
		} catch (Exception e) {
			String methodName = "SELECT_CREDENZIALI_SERVIZIO";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

	public String selectUrlServizioPerCodice(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("servizio", codice);
		try {
			String codice_servizio = jdbcTemplate.queryForObject(SELECT_URL_SERVIZIO_PER_CODICE, namedParameters,String.class);
			return codice_servizio;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_URL_SERVIZIO_PER_CODICE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}
	
}
