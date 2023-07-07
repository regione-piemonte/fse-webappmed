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

import it.csi.dma.apiopsan.dto.custom.LLog;
import it.csi.dma.apiopsan.dto.custom.LLogAudit;
import it.csi.dma.apiopsan.dto.custom.LMessaggi;
import it.csi.dma.apiopsan.dto.custom.LMessaggiXml;
import it.csi.dma.apiopsan.dto.custom.MessaggioErrore;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class CodLLogAuditDao extends LoggerUtil {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SQL_INSERT_L_LOG = "INSERT INTO "
			+ "DMACC_T_LOG "
			+ " (id, codice_token_operazione,informazioni_tracciate ,id_catalogo_log,id_transazione,data_inserimento,codice_servizio) VALUES "
			+ "(nextval('seq_dmacc_t_log'), :codice_token_operazione , :informazioni_tracciate , :id_catalogo_log , :id_transazione , now(), :codice_servizio)";
	
	private static final String SQL_INSERT_L_LOG_AUDIT = "insert into "
	+ "dmacc_t_log_audit "
	+ " (id,codice_token_operazione,informazioni_tracciate,id_paziente,id_catalogo_log_audit,id_transazione,data_inserimento,"
	+ "id_regime,id_applicazione_richiedente, appl_verticale,id_utente,id_ruolo,collocazione,id_contesto_operativo,iddocumento,"
	+ "componentelocale,ip_richiedente,nome_servizio,ip_chiamante,codice_servizio,codice_fiscale_utente) values "
	+ "(nextval('seq_dmacc_t_log_audit'),:codice_token_operazione , :informazioni_tracciate , :id_paziente , :id_catalogo_log_audit , :id_transazione ,"
	+ " now() , :id_regime , :id_applicazione_richiedente, :appl_verticale,:id_utente,:id_ruolo,"
	+ " :collocazione,:id_contesto_operativo,:iddocumento,:componentelocale,:ip_richiedente,:nome_servizio,:ip_chiamante,:codice_servizio,"
	+ "pgp_sym_encrypt(:codice_fiscale_utente, '@encryption_key@') )";
	
	
	public long insertLog(LLog lLog) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("codice_token_operazione", lLog.getCodiceTokenOperazione(), Types.VARCHAR);
		params.addValue("informazioni_tracciate", lLog.getInformazioniTracciate(), Types.VARCHAR);
		params.addValue("id_catalogo_log", lLog.getIdCatalogoLog(), Types.BIGINT);
		params.addValue("id_transazione", lLog.getIdTransazione(), Types.VARCHAR);
		params.addValue("codice_servizio",  lLog.getCodiceServizio(), Types.VARCHAR);
	
		jdbcTemplate.update(SQL_INSERT_L_LOG, params, keyHolder, new String[] { "id" });
		return keyHolder.getKey().longValue();
	}

	
	public long insertLogAudit(LLogAudit lLogAudit) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("codice_token_operazione", lLogAudit.getCodiceTokenOperazione(), Types.VARCHAR);
		params.addValue("informazioni_tracciate", lLogAudit.getInformazioniTracciate(), Types.VARCHAR);
		params.addValue("id_catalogo_log_audit", lLogAudit.getIdCatalogoLog(), Types.BIGINT);
		params.addValue("id_transazione", lLogAudit.getIdTransazione(), Types.VARCHAR);
		params.addValue("codice_servizio",  lLogAudit.getCodiceServizio(), Types.VARCHAR);
		
		params.addValue("id_paziente", lLogAudit.getIdPaziente(), Types.BIGINT);
		params.addValue("id_regime", lLogAudit.getIdRegime(), Types.BIGINT);
		params.addValue("id_applicazione_richiedente", lLogAudit.getIdApplicazioneRichiedente(), Types.BIGINT);
		params.addValue("id_utente", lLogAudit.getIdUtente(), Types.BIGINT);
		params.addValue("id_ruolo",  lLogAudit.getIdRuolo(), Types.BIGINT);
		
		params.addValue("collocazione", lLogAudit.getCollocazione(), Types.VARCHAR);
		params.addValue("id_contesto_operativo", lLogAudit.getIdContestoOperativo(), Types.BIGINT);
		params.addValue("iddocumento", lLogAudit.getIddocumento(), Types.BIGINT);
		params.addValue("componentelocale", lLogAudit.getComponentelocale(), Types.VARCHAR);
		params.addValue("ip_richiedente",  lLogAudit.getIpRichiedente(), Types.VARCHAR);
		
		params.addValue("nome_servizio", lLogAudit.getNomeServizio(), Types.VARCHAR);
		params.addValue("ip_chiamante", lLogAudit.getIpChiamante(), Types.VARCHAR);
		params.addValue("codice_fiscale_utente",  lLogAudit.getCodiceFiscaleUtente(), Types.VARCHAR);
		params.addValue("appl_verticale", lLogAudit.getApplicazioneVerticale(), Types.VARCHAR);

		jdbcTemplate.update(SQL_INSERT_L_LOG_AUDIT, params, keyHolder, new String[] { "id" });
		return keyHolder.getKey().longValue();
	}

	public int[] insert(List<LLog> logErrorList) {
		
		SqlParameterSource[] parameters = SqlParameterSourceUtils.createBatch(logErrorList.toArray());	
		return jdbcTemplate.batchUpdate(SQL_INSERT_L_LOG ,parameters);
	}
}
