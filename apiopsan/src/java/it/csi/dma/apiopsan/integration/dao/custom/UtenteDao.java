/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class UtenteDao extends LoggerUtil {

	private static final String SELECT_ID_UTENTE_PER_CODICE_FISCALE = "select id from "
			+ "	dmacc_t_utente ccp where ccp.codice_fiscale = :codice_fiscale "
			+ "	and now()::date between data_attivazione::date and coalesce(data_chiusura::date, now()::date);";
	
	private static final String SELECT_ID_PAZIENTE_PER_CODICE_FISCALE = "select id_paziente from "
			+ "dmacc_t_paziente ccp where ccp.codice_fiscale = :codice_fiscale "
			+ "and ccp.data_annullamento is null";
	
	private static final String SELECT_FLAG_INCARICO_PER_CODICE_FISCALE = "select flag_registry_incarico from "
			+ "dmacc_t_paziente ccp where ccp.codice_fiscale = :codice_fiscale "
			+ "and ccp.data_annullamento is null";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public long selectIdUtentePerCodiceFiscale(String codiceFiscale) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice_fiscale", codiceFiscale);
		try {
			List<Long> id = jdbcTemplate.queryForList(SELECT_ID_UTENTE_PER_CODICE_FISCALE, namedParameters,Long.class);
			if (id.size()>0)
			    return id.get(0);
			else
				return 0;
		} catch (EmptyResultDataAccessException e) {
			return 0; 
		} catch (Exception e) {
			String methodName = "SELECT_ID_UTENTE_PER_CODICE_FISCALE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	public long selectIdPazientePerCodiceFiscale(String codiceFiscale) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice_fiscale", codiceFiscale);
		try {
			List<Long> id = jdbcTemplate.queryForList(SELECT_ID_PAZIENTE_PER_CODICE_FISCALE, namedParameters,Long.class);
			if (id.size()>0)
			    return id.get(0);
			else
				return 0;
		} catch (EmptyResultDataAccessException e) {
			return 0; 
		} catch (Exception e) {
			String methodName = "SELECT_ID_PAZIENTE_PER_CODICE_FISCALE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	public String selectFlagIncaricoPerCodiceFiscale(String codiceFiscale) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice_fiscale", codiceFiscale);
		try {
			List<String> flagincarico = jdbcTemplate.queryForList(SELECT_FLAG_INCARICO_PER_CODICE_FISCALE, namedParameters,String.class);
			if (flagincarico.size()>0)
			    return flagincarico.get(0);
			else
				return null;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_FLAG_INCARICO_PER_CODICE_FISCALE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
}
