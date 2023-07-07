/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.SintesiDocumentoMapper;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.GestionePSS;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;

@Repository
public class DocumentoDao extends LoggerUtil {
	
	@Value("${encryption_key}")
	private String encryptionKey;
	
	@Autowired
	GestionePSS gestionePSS;
	
	public static final String SELECT_COUNT_FSE = "SELECT "
			+   "   doc.id_documento_ilec, tipodoc.descr_tipo_documento, "
			+   "	null as nome_medico, episodio.id_episodio_ilec, "
			+   "	tipoepisodio.descrizione_tipo_episodio, paziente.codice_fiscale "
			+   " FROM dmacc_t_paziente paziente, "
			+   "	dmaccidx_t_documento doc, "
			+   "	dmaccidx_t_episodio episodio, "
			+   "	dmaccidx_d_tipo_documento tipodoc, "
			+   "	dmaccidx_d_tipo_episodio tipoepisodio, "
			+   "	dmaccidx_t_oscuramento_documento osc, "
			+   "	dmacc_t_oscuramento_esenzioni esenz "
			+   " WHERE "
			+   "	paziente.id_paziente = doc.id_paz_irec "
			+   "	AND episodio.id_episodio_ilec = doc.id_episodio_ilec "
			+   "	AND episodio.id_paz_irec = doc.id_paz_irec "
			+   "	AND episodio.cod_cl = doc.cod_cl "
			+   "	AND episodio.data_fine is not null "
			+   "	AND tipodoc.cod_tipo_documento = doc.cod_tipo_documento "
			+   "	AND tipoepisodio.codice_tipo_episodio = episodio.codice_tipo_episodio "
			+   "	AND osc.id_documento_ilec = doc.id_documento_ilec "
			+   "	AND osc.cod_cl = doc.cod_cl "
			+   "	AND esenz.id_paziente = paziente.id_paziente "
			+   "	AND esenz.chiuso = 'N' "
			+   "	AND ( esenz.oscurato != 'S' or (esenz.oscurato = 'S' and tipodoc.classe = 'ESEN' "
			+   "		and osc.id_documento_ilec in ( "
			+   "			select id_documento_ilec from dmaccidx_t_doc_medico "
			+   "			where codice_identificativo_medico = :shibIdentitaCodiceFiscale "
			+   "				and tipo_medico in ('V', 'R') "
			+   "				and id_documento_ilec = doc.id_documento_ilec "			
			+   "				and cod_cl = doc.cod_cl "
			+   "       ) "
			+   "   )) "
			+   "	AND doc.data_validazione >= :dataDa "
			+   "	AND doc.data_validazione <= :dataA "
 			+   "	AND doc.cod_tipo_documento in (:fseDocTipo) "	
			+   "	AND doc.data_annullamento is null "
			+   "	AND (osc.oscurato != 'S' OR ( "
			+   "		osc.oscurato = 'S' and osc.id_documento_ilec in ( "	
			+   "			select id_documento_ilec from dmaccidx_t_doc_medico "
			+   "			where codice_identificativo_medico = :shibIdentitaCodiceFiscale "
			+   "				and tipo_medico in ('V', 'R') "
			+   "				and id_documento_ilec = doc.id_documento_ilec "
			+   "				and cod_cl = doc.cod_cl "
			+   "			) "
			+   "		)"
			+   "	)"	
			+   "	AND paziente.codice_fiscale = :citId "
			+   "	AND (doc.id_documento_ilec not in ( "
			+   "		select nre_osc.id_documento_ilec "
			+   "		from dmacc_t_nre_oscurati nre_osc, "
			+   "			fdw_source.dmaccidx_t_doc_nre nre "
			+   "		where nre.id_documento_ilec = doc.id_documento_ilec "
			+   "			and nre.cod_cl = doc.cod_cl "
			+   "			and nre.nre_rif_doc = nre_osc.nre "
			+   "			and nre_osc.id_paziente = paziente.id_paziente"
			+   "	    ) or doc.id_documento_ilec in ( select id_documento_ilec "
			+   "            from dmaccidx_t_doc_medico "
			+   "            where codice_identificativo_medico = :shibIdentitaCodiceFiscale "
			+   "                and tipo_medico in ('V', 'R') "
			+   "                and id_documento_ilec = doc.id_documento_ilec"
			+   "                and cod_cl = doc.cod_cl "
			+   "       )"
			+   "   ) ";
	
	public static final String SELECT_COUNT_PERSONALE = " SELECT meta.id_documento as id_documento_ilec, "
			+   "    tipodoc.descr_tipo_documento, "
			+   "    meta.medico as nome_medico, null as id_episodio_ilec,"
			+   "	 null as descrizione_tipo_episodio, paziente.codice_fiscale "
			+   " FROM dmacc_t_paziente paziente, "
			+   "	 dmaccidx_t_metadati_documento meta, "
			+   "    dmaccidx_t_oscuramento_doc_personale osc, "
			+   "	 dmaccidx_d_tipo_documento tipodoc "
			+   " WHERE paziente.id_paziente = meta.id_paz_irec "
			+   "	AND osc.id_documento_personale = meta.id_documento"
			+   "   AND osc.oscurato != 'S' "
			+   "   AND meta.data_emissione >= :dataDa "
			+   "   AND meta.data_emissione <= :dataA "
			+   "   AND tipodoc.cod_tipo_documento = meta.cod_tipo_documento "
			+   "   AND paziente.codice_fiscale = :citId "
			+   "   AND meta.cod_tipo_documento in (:persDocTipo) ";
			
	public static final String SELECT_DOC_FSE = "SELECT doc.id_documento_ilec, doc.cod_cl, "
			+   "	doc.codice_documento_dipartimentale, doc.codice_azienda_sanitaria,"
			+   "	(pgp_sym_decrypt(doc.descrizione_azienda_sanitaria::bytea, '@encryption_key@'))::varchar as descrizione_azienda_sanitaria, "
			+   "   (pgp_sym_decrypt(doc.descrizione_struttura::bytea, '@encryption_key@'))::varchar as descrizione_struttura, "
			+   "	(pgp_sym_decrypt(doc.descrizione_uo::bytea, '@encryption_key@'))::varchar as descrizione_uo, "
			+   "   doc.matricola_up_dip, "
			+   "	doc.data_validazione, doc.cod_tipo_documento, null as tipo_contributo, "
			+   "	tipodoc.descr_tipo_documento, "
			+   "	doc.cod_tipo_file, doc.codice_dipartimentale, "
			+   "	doc.cod_tipo_documento_alto, "
			+   "   doc.codice_tipo_assetto_organizzativo, "
			+   "	(pgp_sym_decrypt(doc.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar as descrizione_tipo_assetto_organizzativo, "
			+   "   doc.id_repository_cl, "
			+   "	doc.cod_tipo_firma, doc.descrizione_tipo_firma, "
			+   "	doc.ticket_pagato, doc.ticket_da_pagare, "
			+   "	doc.oscura_scarico_cittadino, doc.leggi_speciali, "
			+   "	doc.autore_smediazione,	doc.data_smediazione, "
			+   "	doc.coccarda, "
			+   "   decode(doc.flag_episodi_collegati, 'S', 'true', 'N', 'false')::boolean as flag_episodi_collegati, "
			+   "   decode(doc.flag_associazioni_nre, 'S', 'true', 'N', 'false')::boolean as flag_associazioni_nre, "
			+   "   decode(doc.flag_associazioni_figli, 'S', 'true', 'N', 'false')::boolean as flag_associazioni_figli, "	
			+   "	doc.stato_generazione_pacchetto, "
			+   "	null as nome_medico, "
			+   "	episodio.id_episodio_ilec, episodio.data_inizio, "
			+   "	episodio.data_fine, episodio.codice_azienda_sanitaria_acc, "
			+   "	(pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar as descrizione_azienda_sanitaria_acc, "
			+   "   episodio.codice_azienda_sanitaria_dim, "
			+   "	(pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar as descrizione_azienda_sanitaria_dim,	"
			+   "   (pgp_sym_decrypt(episodio.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar as descrizione_struttura_acc, "
			+   "	(pgp_sym_decrypt(episodio.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar as descrizione_struttura_dim, "
			+   "   (pgp_sym_decrypt(episodio.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar as descrizione_uo_acc, "
			+   "	(pgp_sym_decrypt(episodio.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar as descrizione_uo_dim, "
			+   "   episodio.matricola_up_acc_dip, "
			+   "	episodio.matricola_up_dim_dip, episodio.codice_tipo_episodio, "
			+   "	tipoepisodio.descrizione_tipo_episodio, "
			+ 	" 	paziente.codice_fiscale as citId, "
			+   "   'FSE' as categoria, "
			+   "   doc.numero_nosologico as numero_nosologico, "
			+   "   doc.numero_passaggio_ps as numero_passaggio_ps, "
			+   "   doc.pagato_ticket "
			+   "FROM dmacc_t_paziente paziente, "
			+   "	dmaccidx_t_documento doc, "
			+   "	dmaccidx_t_episodio episodio, "
			+   "	dmaccidx_d_tipo_documento tipodoc, "
			+   "	dmaccidx_d_tipo_episodio tipoepisodio, "
			+   "	dmaccidx_t_oscuramento_documento osc, "
			+   "	dmacc_t_oscuramento_esenzioni esenz "
			+   "WHERE "
			+   "	paziente.id_paziente = doc.id_paz_irec "
			+   "	AND episodio.id_episodio_ilec = doc.id_episodio_ilec "
			+   "	AND episodio.id_paz_irec = doc.id_paz_irec "
			+   "	AND episodio.cod_cl = doc.cod_cl "
			+   "	AND episodio.data_fine is not null "
			+   "	AND tipodoc.cod_tipo_documento = doc.cod_tipo_documento "
			+   "	AND tipoepisodio.codice_tipo_episodio = episodio.codice_tipo_episodio "
			+   "	AND osc.id_documento_ilec = doc.id_documento_ilec "
			+   "	AND osc.cod_cl = doc.cod_cl "
			+   "	AND esenz.id_paziente = paziente.id_paziente "
			+   "	AND esenz.chiuso = 'N' "
			+   "   AND (( "
			+   "		doc.id_documento_ilec not in ( "
			+   "			select id_documento_ilec "
			+   "		from dmaccidx_t_oscuramento_documento_genitore genit"
			+   "	 	where "
			+   "		    genit.id_documento_ilec = doc.id_documento_ilec "
			+   "			and genit.cod_cl = doc.cod_cl "
			+   "			and genit.oscurato = 'S')) "
			+   "		) "
			+   "   and( "
			+   "       (tipodoc.classe is null or tipodoc.classe != 'ESEN' "
			+   "        and osc.id_documento_ilec in (  "
			+   "           select id_documento_ilec from dmaccidx_t_doc_medico " 
			+   "            where codice_identificativo_medico = :shibIdentitaCodiceFiscale " 
			+   "              and tipo_medico in ('V', 'R') "
			+   "              and id_documento_ilec = doc.id_documento_ilec " 
			+   "              and cod_cl = doc.cod_cl "
			+   "          )"
			+ "         ) "
			+   "      or (tipodoc.classe = 'ESEN' AND esenz.chiuso = 'N' and esenz.oscurato != 'S') "
			+   "      or (tipodoc.classe = 'ESEN' AND esenz.chiuso = 'N' and esenz.oscurato = 'S' "
			+   "          and osc.id_documento_ilec in ( "
			+   "            select id_documento_ilec from dmaccidx_t_doc_medico " 
			+   "             where codice_identificativo_medico = :shibIdentitaCodiceFiscale " 
			+   "               and tipo_medico in ('V', 'R') "
			+   "               and id_documento_ilec = doc.id_documento_ilec " 
			+   "               and cod_cl = doc.cod_cl "
			+   "          )"
			+ "           ) "
			+   "      ) "
			+   "	AND doc.data_validazione >= :dataDa "
			+   "	AND doc.data_validazione <= :dataA "
 			+   "	AND doc.cod_tipo_documento in (:fseDocTipo) "	
			+   "	AND doc.data_annullamento is null "
			+   "	AND (osc.oscurato != 'S' OR ( "
			+   "		osc.oscurato = 'S' and osc.id_documento_ilec in ( "	
			+   "			select id_documento_ilec from dmaccidx_t_doc_medico "
			+   "			where codice_identificativo_medico = :shibIdentitaCodiceFiscale "
			+   "				and tipo_medico in ('V', 'R') "
			+   "				and id_documento_ilec = doc.id_documento_ilec "
			+   "				and cod_cl = doc.cod_cl "
			+   "			) "
			+   "		)"
			+   "	)"
			+   "	AND paziente.codice_fiscale = :citId "
			+   "	AND (doc.id_documento_ilec not in ( "
			+   "		select nre.id_documento_ilec "
			+   "		from dmacc_t_nre_oscurati nre_osc, "
			+   "			fdw_source.dmaccidx_t_doc_nre nre "
			+   "		where nre.id_documento_ilec = doc.id_documento_ilec "
			+   "			and nre.cod_cl = doc.cod_cl "
			+   "			and nre.nre_rif_doc = nre_osc.nre "
			+   "			and nre_osc.id_paziente = paziente.id_paziente"
			+   "	    ) or doc.id_documento_ilec in ( select id_documento_ilec "
			+   "            from dmaccidx_t_doc_medico "
			+   "            where codice_identificativo_medico = :shibIdentitaCodiceFiscale "
			+   "                and tipo_medico in ('V', 'R') "
			+   "                and id_documento_ilec = doc.id_documento_ilec"
			+   "                and cod_cl = doc.cod_cl "
			+   "       )"
			+   "   ) ";
			
	
	public static final String SELECT_DOC_PERSONALE = "SELECT meta.id_documento id_documento_ilec, 'CLUC' as cod_cl, "
			+ "   null as codice_documento_dipartimentale, null as codice_azienda_sanitaria, "
			+ "   null as descrizione_azienda_sanitaria, meta.struttura_sanitaria as descrizione_struttura, "
			+ "   meta.unita_operativa as descrizione_uo, null as matricola_up_dip, "
			+ "   meta.data_emissione as data_validazione, meta.cod_tipo_documento, meta.tipo_contributo, "
			+ "   tipodoc.descr_tipo_documento, "
			+ "   null as cod_tipo_file, null as codice_dipartimentale, "
			+ "   null as cod_tipo_documento_alto, null as codice_tipo_assetto_organizzativo, "
			+ "   null as descrizione_tipo_assetto_organizzativo, null as id_repository_cl, "
			+ "   null as cod_tipo_firma, null as descrizione_tipo_firma, "
			+ "   null as ticket_pagato, null as ticket_da_pagare, "
			+ "   null as oscura_scarico_cittadino, null as leggi_speciali, "
			+ "   null as autore_smediazione, null as data_smediazione, "
			+ "   null as coccarda, null as flag_episodi_collegati, "
			+ "   null as flag_associazioni_nre, null as flag_associazioni_figli, "
			+ "   null as stato_generazione_pacchetto, "
			+ "   meta.medico as nome_medico, "
			+ "   null as id_episodio_ilec, null as data_inizio, "
			+ "   null as data_fine, null as codice_azienda_sanitaria_acc, "
			+ "   null as descrizione_azienda_sanitaria_acc, null as codice_azienda_sanitaria_dim, "
			+ "   null as descrizione_azienda_sanitaria_dim, null as descrizione_struttura_acc, "
			+ "   null as descrizione_struttura_dim, null as descrizione_uo_acc, "
			+ "   null as descrizione_uo_dim, null as matricola_up_acc_dip, "
			+ "   null as matricola_up_dim_dip, null as codice_tipo_episodio, "
			+ "   null as descrizione_tipo_episodio, "
			+ "   paziente.codice_fiscale as citId, "
			+ "   'PERSONALE' as categoria, "
			+ "   null as numero_nosologico, "
			+ "   null as numero_passaggio_ps, "
			+ "   null as pagato_ticket "
			+ "FROM dmacc_t_paziente paziente, "
			+ "	  dmaccidx_t_metadati_documento meta, "
			+ "   dmaccidx_t_oscuramento_doc_personale osc, "
			+ "	  dmaccidx_d_tipo_documento tipodoc "
			+ "WHERE "
			+ "	  paziente.id_paziente = meta.id_paz_irec "
			+ "	  AND osc.id_documento_personale = meta.id_documento"
			+ "   AND osc.oscurato != 'S' "
			+ "   AND meta.data_emissione >= :dataDa "
			+ "   AND meta.data_emissione <= :dataA "
			+ "   AND tipodoc.cod_tipo_documento = meta.cod_tipo_documento "
			+ "   AND paziente.codice_fiscale = :citId "
			+ "   AND meta.cod_tipo_documento in (:persDocTipo) ";
	
	public static final String ORDER_BY = "	order by data_validazione desc ";
	public static final String LIMIT_OFFSET = "	LIMIT :limit OFFSET :offset ";
	
	public static final String FILTRO_TIPO_MEDICO = " AND doc.id_documento_ilec in ( "
			+   " 	    select id_documento_ilec from dmaccidx_t_doc_medico "
			+   " 		where codice_identificativo_medico = :shibIdentitaCodiceFiscale "
			+   " 			and tipo_medico in (:tipoMedico)"
			+   " 			and id_documento_ilec = doc.id_documento_ilec"
			+   " 			and cod_cl = doc.cod_cl "
			+   " 	)";		

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<SintesiDocumento> selectDocumentiCategoria(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio,
			String citId, String ruoloFse, String regime, PayloadSearchTuttiDoc payload) throws DatabaseException {
		try {

			String query = "";
			String queryDocFse = SELECT_DOC_FSE;
			
			List<String> fseDocTipo = mapCategoriaTipologia(Constants.FSE, payload.getCategoriaTipologia());
			List<String> persDocTipo = mapCategoriaTipologia(Constants.PERSONALE, payload.getCategoriaTipologia());	
			List<String> tipoMedico = mapTipoMedicoToArray(payload.getTipoMedico());
			
			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue("dataDa", Util.getStartOfDay(payload.getFiltroDocs().getDataInizio()));
			params.addValue("dataA",  Util.getEndOfDay(payload.getFiltroDocs().getDataFine()));
			params.addValue("fseDocTipo", fseDocTipo); //documenti caricati dalla ASL
			params.addValue("persDocTipo", persDocTipo); //documenti personali caricati dal cittadino
			params.addValue("shibIdentitaCodiceFiscale", shibIdentitaCodiceFiscale);
			params.addValue("citId", citId);
			if (tipoMedico.size() > 0) {
				params.addValue("tipoMedico", tipoMedico);
				queryDocFse += FILTRO_TIPO_MEDICO;
			}
						
			if (persDocTipo.size() > 0 && fseDocTipo.size() > 0) {
				query = queryDocFse + " UNION ALL " + SELECT_DOC_PERSONALE + ORDER_BY;
			} else if (fseDocTipo.size() > 0 && persDocTipo.size() == 0) {
				query = queryDocFse + ORDER_BY;
			} else if (persDocTipo.size() > 0 && fseDocTipo.size() == 0) {
				query = SELECT_DOC_PERSONALE + ORDER_BY;
			} 
			
			if((payload.getLimit() != null && payload.getLimit() > 0) 
					&& (payload.getOffset() != null && payload.getOffset() >= 0)) {
				
				params.addValue("limit", payload.getLimit());
				params.addValue("offset", payload.getOffset());
				
				query += LIMIT_OFFSET;
			}	
			logInfo("params" + params.getValues().toString());
			query = replaceKey(query, encryptionKey);
			List<SintesiDocumento> documenti = jdbcTemplate.query(query, params, new SintesiDocumentoMapper());

			/*
			 * CASO PSS
			 * va restituito solo l'ultimo documento, quindi occorre eliminare dai risultati i piu' vecchi
			 */
			gestionePSS.postProcessingDB(documenti);
			
			return documenti;

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectDocumentiCategoria";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public Integer selectCountDocumentiCategoria(String shibIdentitaCodiceFiscale, String xRequestId, String xCodiceServizio,
			String citId, String ruoloFse, String regime, PayloadSearchTuttiDoc payload) throws DatabaseException {
		try {
			
			/*
			 * 2023-02-14
			 * Per ora gestisco il count cosi in modo da avere un'unica implementazione
			 * ed un'unica gestione del caso pss
			 * 
			 */
			payload.setLimit(0);
			payload.setOffset(0);
			
			List<SintesiDocumento> documenti = selectDocumentiCategoria(shibIdentitaCodiceFiscale, 
					xRequestId, xCodiceServizio, citId, ruoloFse, regime, payload);
			
			return documenti.size();

			/*
			String query = "";
			String queryDocFse = SELECT_COUNT_FSE;
			
			List<String> fseDocTipo = mapCategoriaTipologia(Constants.FSE, payload.getCategoriaTipologia());
			List<String> persDocTipo = mapCategoriaTipologia(Constants.PERSONALE, payload.getCategoriaTipologia());
			List<String> tipoMedico = mapTipoMedicoToArray(payload.getTipoMedico());
						
			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue("dataDa", payload.getFiltroDocs().getDataInizio());
			params.addValue("dataA",  payload.getFiltroDocs().getDataFine());
			params.addValue("fseDocTipo", fseDocTipo);
			params.addValue("persDocTipo", persDocTipo);
			params.addValue("shibIdentitaCodiceFiscale", shibIdentitaCodiceFiscale);
			params.addValue("citId", citId);
			if (tipoMedico.size() > 0) {
				params.addValue("tipoMedico", tipoMedico);
				queryDocFse += FILTRO_TIPO_MEDICO;
			}
						
			if (persDocTipo.size() > 0 && fseDocTipo.size() > 0) {
				query = "WITH total AS ( " + queryDocFse + " UNION ALL " + SELECT_COUNT_PERSONALE + ") select count(*) from total";
			} else if (fseDocTipo.size() > 0 && persDocTipo.size() == 0) {
				query = "WITH total AS ( " + queryDocFse + ") select count(*) from total";
			} else if (persDocTipo.size() > 0 && fseDocTipo.size() == 0) {
				query = "WITH total AS ( " + SELECT_COUNT_PERSONALE + ") select count(*) from total";
			} 
			
			query = replaceKey(query);
			Integer total = jdbcTemplate.queryForObject(query, params, Integer.class);

			return total;
			*/

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectCountDocumentiCategoria";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
		
	// metodo per separare i documenti di categoria FSE da quelli di categoria PERSONALE
	private List<String> mapCategoriaTipologia(String cat, List<CategoriaTipologia> list) {

		List<String> result = new ArrayList<>();

		for (CategoriaTipologia tipo : list) {
			if (tipo.getCategoria().equals(cat) && tipo.getTipologia() != null)
				result.add(tipo.getTipologia());
		}

		return result;
	}
		
	private List<String> mapTipoMedicoToArray(String tipoMedicoInput) {

		List<String> siglaTipoMedico = new ArrayList<>();

		if (tipoMedicoInput != null) {
			switch (tipoMedicoInput.toUpperCase()) {
			case Constants.TIPO_MEDICO_VALIDANTE:
				siglaTipoMedico.add("V");
				break;
			case Constants.TIPO_MEDICO_REFERTANTE:
				siglaTipoMedico.add("R");
				break;
			case Constants.TIPO_MEDICO_VALIDANTEOREFERTANTE:
				siglaTipoMedico.add("V");
				siglaTipoMedico.add("R");
				break;
			}
		}

		return siglaTipoMedico;
	}
	

}
