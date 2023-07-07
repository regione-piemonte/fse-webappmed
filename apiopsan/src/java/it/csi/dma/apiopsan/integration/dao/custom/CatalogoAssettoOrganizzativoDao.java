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
public class CatalogoAssettoOrganizzativoDao extends LoggerUtil {

	private static final String SELECT_DESC_BY_CODICE = "select descrizione_tipo_assetto_organizzativo from "
			+ "	dmacc_d_catalogo_tipo_assetto_organizzativo a where codice_tipo_assetto_organizzativo = :codice";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public String selectDescrizioneAssettoOrgByCodice(String codice) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice", codice);
		try {

			String desc = jdbcTemplate.queryForObject(SELECT_DESC_BY_CODICE, namedParameters, String.class);
			return desc;

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "SELECT_DESC_BY_CODICE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}
	}

}
