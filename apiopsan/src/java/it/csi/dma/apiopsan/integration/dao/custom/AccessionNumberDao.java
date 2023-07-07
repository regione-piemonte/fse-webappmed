/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.util.ArrayList;
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
public class AccessionNumberDao extends LoggerUtil {

	public static final String SELECT_BY_DOC = "SELECT accession_number " 
			+ " FROM dmaccidx_t_accession_number "
			+ " WHERE id_doc_ilec = :idDoc AND cod_cl = :codCl ";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<String> selectAccessionNumberByIdDoc(Long idDoc, String codCl) throws DatabaseException {
		try {

			SqlParameterSource params = new MapSqlParameterSource()
					.addValue("idDoc", idDoc)
					.addValue("codCl", codCl);

			List<String> acc = jdbcTemplate.queryForList(SELECT_BY_DOC, params, String.class);

			return acc;

		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<String>();
		} catch (Exception e) {
			String methodName = "selectAccessionNumberByIdDoc";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}

}
