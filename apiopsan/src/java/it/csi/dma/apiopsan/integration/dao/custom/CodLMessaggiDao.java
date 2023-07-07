/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.custom.LMessaggi;
import it.csi.dma.apiopsan.dto.custom.LMessaggiXml;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class CodLMessaggiDao extends LoggerUtil {

	@Value("${encryption_key}")
	private String encryptionKey;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SQL_INSERT_L_MESSAGGI = "INSERT INTO dmacc_l_messaggi "
			+ " (id_xml,wso2_id, servizio_xml, uuid, chiamante, stato_xml, data_ricezione,  "
			+ " data_ins, id_messaggio_orig, cf_utente, ruolo_utente, data_invio_servizio, codice_servizio, appl_verticale, ip_richiedente,cf_assistito ) "
			+ " VALUES(nextval('seq_dmacc_l_messaggi'),:wso2_id, :servizio_xml, :wso2_id, :chiamante, :stato_xml, now(), now(), :id_messaggio_orig, "
			+ "   :cf_utente, :ruolo_utente, now(), :codice_servizio, :appl_verticale, :ip_richiedente, :cf_assistito)";
		
	private static final String SQL_UPDATE_L_MESSAGGI = "UPDATE dmacc_l_messaggi set cod_esito_risposta_servizio = :cod_esito_risposta_servizio, "
			+ "data_risposta = now(), stato_xml = :stato_xml,  info_aggiuntive_errore = :info_aggiuntive_errore, data_risposta_servizio = now(), data_mod = now() "
			+ "where id_xml = :id_xml";
	
	private static final String SQL_INSERT_L_XML_MESSAGGI = "INSERT INTO dmacc_l_xml_messaggi "
			+ " (id,wso2_id, xml_in, data_inserimento) "
			+ " VALUES(nextval('seq_dmacc_l_xml_messaggi'),:wso2_id,pgp_sym_encrypt(:xml_in, '@encryption_key@'),  now())";
	
	
	private static final String SQL_UPDATE_L_XML_MESSAGGI = "UPDATE dmacc_l_xml_messaggi set xml_out = pgp_sym_encrypt(:xml_out, '@encryption_key@') "
			+ "where wso2_id = :wso2_id";

	public long insertMessaggi(LMessaggi lMessaggi) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("wso2_id", lMessaggi.getWso2Id(), Types.VARCHAR);
		params.addValue("servizio_xml", lMessaggi.getServizioXml(), Types.VARCHAR);
		params.addValue("chiamante", lMessaggi.getChiamante(), Types.VARCHAR);
		params.addValue("stato_xml", lMessaggi.getStatoXml(), Types.INTEGER);
		params.addValue("cf_utente", lMessaggi.getCfUtente(), Types.VARCHAR);
		params.addValue("id_messaggio_orig", lMessaggi.getIdMessaggioOrig(), Types.VARCHAR);

		params.addValue("ruolo_utente", lMessaggi.getRuoloUtente(), Types.VARCHAR);
		
		params.addValue("codice_servizio",  lMessaggi.getCodiceServizio(), Types.VARCHAR);
		params.addValue("appl_verticale", lMessaggi.getApplVerticale(), Types.VARCHAR);

		params.addValue("ip_richiedente", lMessaggi.getIpRichiedente(), Types.VARCHAR);
		params.addValue("cf_assistito", lMessaggi.getCfAssistito(), Types.VARCHAR);

		jdbcTemplate.update(SQL_INSERT_L_MESSAGGI, params, keyHolder, new String[] { "id_xml" });
		return keyHolder.getKey().longValue();
	}

	public int updateMessaggi(LMessaggi lMessaggi, Long id_xml) {

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("cod_esito_risposta_servizio", lMessaggi.getCodEsitoRispostaServizio(), Types.VARCHAR);
		params.addValue("stato_xml", lMessaggi.getStatoXml(), Types.INTEGER);
		params.addValue("info_aggiuntive_errore", lMessaggi.getInfoAggiuntiveErrore(), Types.VARCHAR);
		params.addValue("id_xml", id_xml, Types.BIGINT);

		return jdbcTemplate.update(SQL_UPDATE_L_MESSAGGI, params);
	}
	
	public long insertMessaggiXml(LMessaggiXml lMessaggiXml) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("wso2_id", lMessaggiXml.getWso2Id(), Types.VARCHAR);
		params.addValue("xml_in", lMessaggiXml.getXmlIn(),  Types.BINARY);
		

		String sql = replaceKey(SQL_INSERT_L_XML_MESSAGGI, encryptionKey);
		
		jdbcTemplate.update(sql, params, keyHolder, new String[] { "id" });
		return keyHolder.getKey().longValue();
	}

	public int updateMessaggiXml(LMessaggiXml lMessaggiXml, String wso2_id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("xml_out", lMessaggiXml.getXmlOut()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,  Types.BINARY);
		params.addValue("wso2_id", wso2_id, Types.VARCHAR);

		String sql = replaceKey(SQL_UPDATE_L_XML_MESSAGGI, encryptionKey);
		
		return jdbcTemplate.update(sql, params);
	}
}
