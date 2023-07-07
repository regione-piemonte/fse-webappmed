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

import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.MedicoMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class MedicoDao extends LoggerUtil {

	public static final String SELECT_BY_DOC = "SELECT cognome_medico, nome_medico, "
			+ " codice_identificativo_medico, tipo_medico " 
			+ " FROM fdw_source.dmaccidx_t_doc_medico "
			+ " WHERE id_documento_ilec = :idDoc AND cod_cl = :codCl ";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Medico> selectMediciByIdDoc(Long idDoc, String codCl) throws DatabaseException {
		try {

			SqlParameterSource params = new MapSqlParameterSource()
					.addValue("idDoc", idDoc)
					.addValue("codCl", codCl);

			List<Medico> medici = jdbcTemplate.query(SELECT_BY_DOC, params, new MedicoMapper());

			return medici;

		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Medico>();
		} catch (Exception e) {
			String methodName = "selectMediciByIdDoc";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}

}
