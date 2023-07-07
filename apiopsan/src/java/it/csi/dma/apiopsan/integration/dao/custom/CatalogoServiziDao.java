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

import it.csi.dma.apiopsan.dto.custom.CatalogoServiziOperation;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.CatalogoServiziOperationMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class CatalogoServiziDao extends LoggerUtil {

	private static final String SELECT_SERVIZIO_PER_NOME = "select codice_servizio,nome_servizio from "
			+ "	dmacc_d_catalogo_servizi_operation a where a.nome_operation = :nome_operation";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public CatalogoServiziOperation selectServizioPerNome(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nome_operation", codice);
		try {
			CatalogoServiziOperation codice_servizio = jdbcTemplate.queryForObject(SELECT_SERVIZIO_PER_NOME, namedParameters,new CatalogoServiziOperationMapper());
			return codice_servizio;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_SERVIZIO_PER_NOME";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}
}
