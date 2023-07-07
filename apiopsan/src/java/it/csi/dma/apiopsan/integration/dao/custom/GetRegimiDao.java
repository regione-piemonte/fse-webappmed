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

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.RegimiMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class GetRegimiDao extends LoggerUtil {

	
	private static final String SELECT_REGIMI_PER_CODICE_RUOLO = "select d.codice_regime_operativita ,d.descrizione_regime_operativita "
			+ "from dmacc_t_decodifica_ruoli_pua a, dmacc_d_ruolo b,dmacc_r_ruolo_regime c,dmacc_d_regime_operativita d "
			+ "where a.codice_ruolo_pua = :ruolopua and a.sol = :sol "
			+ "and a.codice_ruolo_fse = b.codice_ruolo "
			+ "and c.id_ruolo =b.id "
			+ "and d.id =c.id_regime and now() > a.data_inserimento "
			+ "and now() > b.data_inserimento "
			+ "and now() > d.data_inserimento";
	
	private static final String SELECT_ID_REGIMI_PER_LOG_AUDIT = "select id "
			+ "from dmacc_d_regime_operativita d "
			+ "where codice_regime_operativita = :codice_regime_operativita "
			+ "and now() > d.data_inserimento";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Codice> selectRegimiPerRuolo(String xCodiceServizio,String ruolo) throws DatabaseException {
		List<Codice> regimi = new ArrayList<Codice>();
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("sol",
				xCodiceServizio).addValue("ruolopua", ruolo);
		try {
			regimi = jdbcTemplate.query(SELECT_REGIMI_PER_CODICE_RUOLO, namedParameters,new RegimiMapper());
			return regimi;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		}catch (Exception e) {
			String methodName = "SELECT_REGIMI_PER_CODICE_RUOLO";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

	public long selectIdRegimiPerLogAudit(String codice_regime_operativita) throws DatabaseException {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codice_regime_operativita",
				codice_regime_operativita);
		try {
			long regimi = jdbcTemplate.queryForObject(SELECT_ID_REGIMI_PER_LOG_AUDIT, namedParameters,Long.class);
			return regimi;
		} catch (EmptyResultDataAccessException e) {
			return 0; 
		}catch (Exception e) {
			String methodName = "SELECT_ID_REGIMI_PER_LOG_AUDIT";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}
}
