/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.Prestazione;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.dto.custom.LMessaggi;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.MedicoMapper;
import it.csi.dma.apiopsan.integration.dao.utils.PrestazioneMapper;
import it.csi.dma.apiopsan.integration.dao.utils.StatoConsensoMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class DmaCacheDao extends LoggerUtil {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public static final String SELECT_TABLE_CACHE_CONSENSO = 
			"select a.consenso_alimentazione, a.consenso_consultazione, a.consenso_pregresso, "
			+ "convert_from(pgp_sym_decrypt_bytea(a.identificativo_assistito_consenso, '@encryption_key@'),'UTF8') as identificativo_assistito_consenso, a.identificativo_informativa_consensi, a.identificativo_informativa_corrente "
			+ "from dma_cache_ser_ini a "
			+ "where convert_from(pgp_sym_decrypt_bytea(a.identificativo_assistito_consenso, '@encryption_key@'),'UTF8') = :identificativo_assistito_consenso "
			+ "order by a.data_ins desc";
	
	public static final String INSERT_TABLE_CACHE_CONSENSO = "INSERT INTO dma_cache_ser_ini "
			+ "(cf, consenso_alimentazione, consenso_consultazione, consenso_pregresso, "
			+ "identificativo_assistito_consenso, identificativo_informativa_consensi, identificativo_informativa_corrente) "
			+ "VALUES(pgp_sym_encrypt(:cf, '@encryption_key@'), :consenso_alimentazione, "
			+ ":consenso_consultazione, :consenso_pregresso, pgp_sym_encrypt(:identificativo_assistito_consenso, '@encryption_key@'), "
			+ ":identificativo_informativa_consensi,:identificativo_informativa_corrente)";
	
	
	public static final String DELETE_TABLE_CACHE_CONSENSO = 
			"delete from dma_cache_ser_ini "
			+ "where convert_from(pgp_sym_decrypt_bytea(identificativo_assistito_consenso, '@encryption_key@'),'UTF8') = :identificativo_assistito_consenso";
				
	public StatoConsenso SelectCache(String citId) throws DatabaseException {
		try {
			SqlParameterSource params = new MapSqlParameterSource().addValue("identificativo_assistito_consenso", citId);

			List<StatoConsenso> elencochache = jdbcTemplate.query(SELECT_TABLE_CACHE_CONSENSO, params, new StatoConsensoMapper());

			if (elencochache != null && elencochache.size() > 0)
				return elencochache.get(0);

			return null;

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "SELECT_TABLE_CACHE_CONSENSO";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public long insertTableCache(StatoConsenso statoconsenso, String cfShib) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("cf", cfShib, Types.VARCHAR);
		params.addValue("consenso_alimentazione", statoconsenso.isConsensoAlimentazione(), Types.BOOLEAN);
		params.addValue("consenso_consultazione", statoconsenso.isConsensoConsultazione(), Types.BOOLEAN);
		params.addValue("consenso_pregresso", statoconsenso.isConsensoPregresso(), Types.BOOLEAN);
		params.addValue("identificativo_assistito_consenso", statoconsenso.getIdentificativoAssistitoConsenso(), Types.VARCHAR);
		params.addValue("identificativo_informativa_consensi", statoconsenso.getIdentificativoInformativaConsensi(), Types.VARCHAR);
		params.addValue("identificativo_informativa_corrente", statoconsenso.getIdentificativoInformativaCorrente(), Types.VARCHAR);

		jdbcTemplate.update(INSERT_TABLE_CACHE_CONSENSO, params, keyHolder, new String[] { "cache_id" });
		return keyHolder.getKey().longValue();
	}
	
	public int DeleteTableCache(String citId) {

		SqlParameterSource params = new MapSqlParameterSource().addValue("identificativo_assistito_consenso", citId);
		
		return jdbcTemplate.update(DELETE_TABLE_CACHE_CONSENSO, params);
		
	}
}
