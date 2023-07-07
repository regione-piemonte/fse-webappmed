/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.custom.MessaggioErrore;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class CodRMessaggioErroreDao extends LoggerUtil {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

//	private static final String SQL_INSERT_R_MESSAGGIO_ERRORE ="INSERT INTO dmacc_l_errori (wso2_id, cod_errore, descr_errore, tipo_errore,data_ins, informazioni_aggiuntive) " 
//			+ "VALUES(:wsoId ,  :codErrore, "
//			+ "(select descrizione_errore from dmacc_d_catalogo_log where codice =:codErrore ) " 
//			+ ",'bloccante', NOW(),  :informazioniAggiuntive); ";
	
	private static final String SQL_INSERT_R_MESSAGGIO_ERRORE ="INSERT INTO dmacc_l_errori (id, wso2_id, cod_errore, descr_errore, tipo_errore,data_ins, informazioni_aggiuntive) " 
			+ "VALUES(nextval('seq_dmacc_l_errori'), :wso2Id ,  :codErrore , :descrErrore , :tipoErrore , "
			+ " NOW(),  :informazioniAggiuntive )";

	public long insert(MessaggioErrore messaggioErrore) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("wso2Id", messaggioErrore.getWso2Id(), Types.VARCHAR);
		params.addValue("codErrore", messaggioErrore.getCodErrore(), Types.VARCHAR);
		params.addValue("descrErrore", messaggioErrore.getDescrErrore(), Types.VARCHAR);
		params.addValue("informazioniAggiuntive", messaggioErrore.getInformazioniAggiuntive(), Types.VARCHAR);
		params.addValue("tipoErrore",messaggioErrore.getTipoErrore(), Types.VARCHAR);

		jdbcTemplate.update(SQL_INSERT_R_MESSAGGIO_ERRORE, params, keyHolder, new String[] { "id" });
		return keyHolder.getKey().longValue();
	}
	
	public int[] insert(List<MessaggioErrore> messaggioErroreList) {
		
		SqlParameterSource[] parameters = SqlParameterSourceUtils.createBatch(messaggioErroreList.toArray());	
		return jdbcTemplate.batchUpdate(SQL_INSERT_R_MESSAGGIO_ERRORE ,parameters);
	}


}
