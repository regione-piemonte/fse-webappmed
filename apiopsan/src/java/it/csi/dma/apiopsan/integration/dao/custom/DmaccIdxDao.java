/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.custom.DmaccTDocumentiQrcode;
import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;
import it.csi.dma.apiopsan.dto.custom.EpisodioEsteso;
import it.csi.dma.apiopsan.dto.custom.SintesiDocumentoEsteso;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.DmaccTDocumentiQrcodeMapper;
import it.csi.dma.apiopsan.integration.dao.utils.DmaccidxTDocumentoMapper;
import it.csi.dma.apiopsan.integration.dao.utils.EpisodioEstesoMapper;
import it.csi.dma.apiopsan.integration.dao.utils.MedicoMapper;
import it.csi.dma.apiopsan.integration.dao.utils.SintesiDocumentoDettaglioDocMapper;
import it.csi.dma.apiopsan.integration.dao.utils.SintesiDocumentoEstesoMapper;
import it.csi.dma.apiopsan.integration.dao.utils.SintesiMieiRefertiMapper;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;
import it.csi.dma.apiopsan.util.Util;

@Repository
public class DmaccIdxDao extends LoggerUtil {

	private static final String SELECT_DMACCIDX_T_DOCUMENTO_NON_OSCURATO = "select distinct "
			+ "c.codice_fiscale AS citId,b.id_episodio_ilec,a.id_documento_ilec,b.cod_cl,b.data_inizio,b.data_fine, "
			+ "b.codice_azienda_sanitaria_acc, "
			+ "(pgp_sym_decrypt(b.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_acc , "
			+ "(pgp_sym_decrypt(b.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_acc , "
			+ "(pgp_sym_decrypt(b.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar AS descrizione_uo_acc , "
			+ "b.matricola_up_acc_dip , "
			+ "b.codice_azienda_sanitaria_dim , "
			+ "(pgp_sym_decrypt(b.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_dim , "
			+ "(pgp_sym_decrypt(b.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_dim , "
			+ "(pgp_sym_decrypt(b.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar AS descrizione_uo_dim , "
			+ "b.matricola_up_dim_dip , "
			+ "b.codice_tipo_episodio ,f.descrizione_tipo_episodio, "
			+ "case when e.classe !='ESEN' then 'NOESEN' "
			+ "when e.classe is null then 'NOESEN' "
			+ "else e.classe "
			+ "end as classe "
			+ "from dmaccidx_t_documento a,dmaccidx_t_episodio b, dmacc_t_paziente c,dmaccidx_t_oscuramento_documento d, "
			+ "dmacc_d_catalogo_tipo_documento e,dmaccidx_d_tipo_episodio f "
			+ "where c.codice_fiscale = :citId "
			+ "and a.cod_tipo_documento in (:tipodocumento) "
			+ "and a.data_annullamento is null "
			+ "and c.id_paziente=a.id_paz_irec "
			+ "and data_validazione::date between :datainizioricerca::date and :datafinericerca::date "
			+ "and b.id_episodio_ilec = a.id_episodio_ilec "
			+ "and d.id_documento_ilec = a.id_documento_ilec " 
			+ "and d.oscurato !='S' "
			+ "and e.codice_tipo_doc = a.cod_tipo_documento "
			+ "and b.id_paz_irec = a.id_paz_irec "
			+ "and b.id_paz_irec =c.id_paziente "
			+ "and b.cod_cl = a.cod_cl "
			+ "and b.data_annullamento is null " 
			+ "and f.codice_tipo_episodio = b.codice_tipo_episodio " 
			+ "and d.cod_cl = a.cod_cl "
			+ "and b.cod_cl = d.cod_cl "
			+ "and b.data_fine is not null "
			+ "and (a.id_documento_ilec not in ( "
			+ "		select id_documento_ilec  "
			+ "		from dmaccidx_t_oscuramento_documento_genitore ogen "
			+ "		where ogen.id_documento_ilec = a.id_documento_ilec "
			+ "	    and ogen.cod_cl = a.cod_cl  "
			+ "	    and ogen.oscurato = 'S'))";
	
	private static final String CONTIPOEPISODIO = "and b.codice_tipo_episodio = :tipoepisodio ";
	
	private static final String DAT_INI_ORDER_BY = "order by b.data_inizio desc ";

	private static final String SELECT_DMACC_T_DOC_NRE_OSCURATI = 
			"with tabnre as(select distinct b.nre_rif_doc  from dmaccidx_t_documento a,dmaccidx_t_doc_nre b,dmacc_t_paziente c "
			+ "where a.id_documento_ilec = b.id_documento_ilec " 
			+ "and a.cod_cl =b.cod_cl "
			+ "and c.id_paziente=a.id_paz_irec "
			+ "and c.codice_fiscale = :citId "
			+ "and a.id_documento_ilec = :idDocumentoIlec) "
			+ "select count(distinct a.nre) from dmacc_t_nre_oscurati a,dmacc_t_paziente b,tabnre c "
			+ "where b.id_paziente=a.id_paziente " 
			+ "and a.nre = c.nre_rif_doc "
			+ "and b.codice_fiscale = :citId";
	
	private static final String SELECT_DMACC_T_DOC_NRE_OSCURATI_EX = 
			"with tabnre as(select distinct b.nre_rif_doc from dmaccidx_t_documento a,dmaccidx_t_doc_nre b,dmacc_t_paziente c "
			+ "where a.id_documento_ilec = b.id_documento_ilec " 
			+ "and a.cod_cl =b.cod_cl "
			+ "and c.id_paziente=a.id_paz_irec "
			+ "and c.codice_fiscale = :citId "
			+ "and a.id_documento_ilec = :idDocumentoIlec and b.cod_cl = :codiceCL) "
			+ "select count(distinct a.nre) from dmacc_t_nre_oscurati a,dmacc_t_paziente b,tabnre c "
			+ "where b.id_paziente=a.id_paziente " 
			+ "and a.nre = c.nre_rif_doc "
			+ "and b.codice_fiscale = :citId";
	
	private static final String SELECT_DMACC_T_DOC_ESENZIONE_OSCURATA = "select distinct oscurato from dmacc_t_oscuramento_esenzioni a,dmacc_t_paziente b "
			+ "where a.id_paziente = b.id_paziente "
			+ "and b.codice_fiscale = :citId "
			+ "and a.chiuso ='N'";
	
	private static final String SELECT_DMACC_T_DOC_ACCESSION_NUMBER = "select distinct accession_number from dmaccidx_t_accession_number a "
			+ "where a.cod_cl = :codCl "
			+ "and a.id_doc_ilec = :idDocumentoIlec";
	
	private static final String SELECT_DMACC_T_DOC_MEDICO = "select distinct a.codice_identificativo_medico,a.cognome_medico,a.nome_medico,a.tipo_medico "
			+"from dmaccidx_t_doc_medico a "
			+ "where a.cod_cl = :codCl "
			+ "and a.id_documento_ilec = :idDocumentoIlec";
	
	private static final String SELECT_DMACC_T_DOC_MEDICO_TIPO = "select distinct a.codice_identificativo_medico,a.cognome_medico,a.nome_medico,a.tipo_medico "
			+"from dmaccidx_t_doc_medico a "
			+ "where a.cod_cl = :codCl "
			+ "and a.id_documento_ilec = :idDocumentoIlec "
			+ "and a.tipo_medico in (:tipoMedico)";
	

	private static final String SELECT_DMACC_T_DOC_NRE = "select distinct a.nre_rif_doc "
			+"from dmaccidx_t_doc_nre a "
			+ "where a.cod_cl = :codCl "
			+ "and a.id_documento_ilec = :idDocumentoIlec";
	
//	private static final String SELECT_DMACC_T_DOC_TIPO_MEDICO = "select distinct a.tipo_medico "
//			+"from dmaccidx_t_doc_medico a "
//			+ "where a.cod_cl = :codCl "
//			+ "and a.id_documento_ilec = :idDocumentoIlec "
//			+ "and a.codice_identificativo_medico = :cfMedico";


	private static final String SELECT_DMACCIDX_T_DOCUMENTO_DOCUMENTI_EPISODI_NON_OSCURATO = "select distinct c.codice_fiscale AS citId, "
		+ "a.id_documento_ilec, "
		+ "a.cod_cl, "
		+ "'FSE' as categoria, "
		+ "a.codice_documento_dipartimentale, "
		+ "a.codice_azienda_sanitaria, "
		+ "(pgp_sym_decrypt(a.descrizione_azienda_sanitaria::bytea, '@encryption_key@'))::varchar as descrizione_azienda_sanitaria , "
		+ "(pgp_sym_decrypt(a.descrizione_struttura::bytea, '@encryption_key@'))::varchar as descrizione_struttura , "
		+ "(pgp_sym_decrypt(a.descrizione_uo::bytea, '@encryption_key@'))::varchar as descrizione_uo , "
		+ "a.matricola_up_dip, "
		+ "a.data_validazione, "
		+ "a.cod_tipo_documento, "
		+ "f.descrizione_tipo_doc as descr_tipo_documento, "
		+ "a.cod_tipo_file, "
		+ "a.codice_dipartimentale, "
		+ "a.cod_tipo_documento_alto, "
		+ "a.codice_tipo_assetto_organizzativo, "
		+ "(pgp_sym_decrypt(a.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar as descrizione_tipo_assetto_organizzativo, " 
		+ "a.id_repository_cl, "
		+ "a.cod_tipo_firma, "
		+ "a.descrizione_tipo_firma, "
		+ "a.ticket_pagato, "
		+ "a.ticket_da_pagare, "
		+ "a.pagato_ticket, "
		+ "a.oscura_scarico_cittadino, "
		+ "a.leggi_speciali, "
		+ "a.autore_smediazione, "  
		+ "a.data_smediazione, "
		+ "a.coccarda, "
		+ "case when a.flag_episodi_collegati ='S' then true "
		+ "when a.flag_episodi_collegati ='N' then false "
		+ "else null "
		+ "end as flag_episodi_collegati, " 
		+ "case when a.flag_associazioni_nre ='S' then true "
		+ "when a.flag_associazioni_nre ='N' then false "
		+ "else null "
		+ "end as flag_associazioni_nre, " 
		+ "case when a.flag_associazioni_figli ='S' then true "
		+ "when a.flag_associazioni_figli ='N' then false "
		+ "else null "
		+ "end as flag_associazioni_figli, "   
		+ "a.stato_generazione_pacchetto, "
		+ "b.id_episodio_ilec, "
		+ "b.data_inizio, "
		+ "b.data_fine, "
		+ "b.codice_azienda_sanitaria_acc, "
		+ "(pgp_sym_decrypt(b.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar as descrizione_azienda_sanitaria_acc , " 
		+ "(pgp_sym_decrypt(b.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar as descrizione_struttura_acc , "
		+ "(pgp_sym_decrypt(b.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar as descrizione_uo_acc , "
		+ "(pgp_sym_decrypt(b.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar as descrizione_azienda_sanitaria_dim , " 
		+ "(pgp_sym_decrypt(b.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar as descrizione_struttura_dim , "
		+ "(pgp_sym_decrypt(b.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar as descrizione_uo_dim , "
		+ "b.codice_azienda_sanitaria_dim, "
		+ "b.matricola_up_acc_dip, "
		+ "b.matricola_up_dim_dip, "
		+ "b.codice_tipo_episodio, "
		+ "g.descrizione_tipo_episodio, "
		+ "b.numero_nosologico, "
		+ "b.numero_passaggio_ps, "
		+ "case when h.classe !='ESEN' then 'NOESEN' "
		+ "when h.classe is null then 'NOESEN' "
		+ "else h.classe "
		+ "end as classe "
		+ "from dmaccidx_t_episodio b, dmacc_t_paziente c, dmacc_d_catalogo_tipo_documento h,"
		+ "dmaccidx_t_oscuramento_documento e,dmacc_d_catalogo_tipo_documento f,dmaccidx_d_tipo_episodio g,dmaccidx_t_documento a "
		+ "where c.codice_fiscale = :citId "
		+ "and c.id_paziente=b.id_paz_irec "
		+ "and b.data_fine is not null "
		+ "and b.cod_cl = :codCl "
		+ "and a.data_annullamento is null "
		+ "and c.id_paziente=a.id_paz_irec "
		+ "and a.id_paz_irec = b.id_paz_irec " 
		+ "and b.id_episodio_ilec = a.id_episodio_ilec "
		+ "and b.cod_cl = a.cod_cl "
		+ "and e.id_documento_ilec = a.id_documento_ilec "
		+ "and e.oscurato !='S' "
		+ "and e.cod_cl = a.cod_cl "
		+ "and f.codice_tipo_doc = a.cod_tipo_documento " 
		+ "and b.id_episodio_ilec = :idEpisodio "
		+ "and h.codice_tipo_doc = a.cod_tipo_documento "
		+ "and g.codice_tipo_episodio = b.codice_tipo_episodio "
		+ "and (a.id_documento_ilec not in ( "
		+ "		select id_documento_ilec  "
		+ "		from dmaccidx_t_oscuramento_documento_genitore ogen "
		+ "		where ogen.id_documento_ilec = a.id_documento_ilec "
		+ "	    and ogen.cod_cl = a.cod_cl  "
		+ "	    and ogen.oscurato = 'S'))";
	
	private static final String SELECT_DMACCIDX_T_MIEI_REFERTI = "select c.codice_fiscale AS citId,"
			+ "a.id_documento_ilec,a.cod_cl,a.codice_documento_dipartimentale,a.codice_azienda_sanitaria, "
			+ "(pgp_sym_decrypt(a.descrizione_azienda_sanitaria::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria, "
			+ "(pgp_sym_decrypt(a.descrizione_struttura::bytea, '@encryption_key@'))::varchar AS descrizione_struttura, "
			+ "(pgp_sym_decrypt(a.descrizione_uo::bytea, '@encryption_key@'))::varchar AS descrizione_uo,a.matricola_up_dip,a.data_validazione, "
			+ "a.cod_tipo_documento,d.descr_tipo_documento,a.cod_tipo_file,a.codice_dipartimentale,a.cod_tipo_documento_alto,a.codice_tipo_assetto_organizzativo, "
			+ "(pgp_sym_decrypt(a.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar AS descrizione_tipo_assetto_organizzativo,		 "
			+ "a.id_repository_cl,a.cod_tipo_firma,a.descrizione_tipo_firma,a.ticket_pagato,a.ticket_da_pagare,a.pagato_ticket, "
			+ "a.coccarda,a.leggi_speciali,a.oscura_scarico_cittadino,a.data_smediazione,a.autore_smediazione, "
			+ "case when a.flag_episodi_collegati ='S' then true "
			+ "when a.flag_episodi_collegati ='N' then false "
			+ "else null "
			+ "end as flag_episodi_collegati, " 
			+ "case when a.flag_associazioni_nre ='S' then true "
			+ "when a.flag_associazioni_nre ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_nre, " 
			+ "case when a.flag_associazioni_figli ='S' then true "
			+ "when a.flag_associazioni_figli ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_figli, " 
			+ "x.nome_medico,x.cognome_medico,x.tipo_medico,b.id_episodio_ilec,b.data_inizio,b.data_fine,b.codice_azienda_sanitaria_acc, "
			+ "(pgp_sym_decrypt(b.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_acc,b.codice_azienda_sanitaria_dim, "
			+ "(pgp_sym_decrypt(b.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_dim, "
			+ "(pgp_sym_decrypt(b.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_acc, "
			+ "(pgp_sym_decrypt(b.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_dim, "
			+ "(pgp_sym_decrypt(b.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar AS descrizione_uo_acc, "
			+ "(pgp_sym_decrypt(b.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar AS descrizione_uo_dim,		 "
			+ "b.matricola_up_acc_dip,b.matricola_up_dim_dip,b.codice_tipo_episodio,f.descrizione_tipo_episodio "
			+ "from dmaccidx_t_documento a,dmaccidx_t_episodio b, dmacc_t_paziente c, dmaccidx_d_tipo_documento d, dmaccidx_d_tipo_episodio f,dmaccidx_t_doc_medico x "
			+ "where c.codice_fiscale = :citId "
			+ "and d.cod_tipo_documento = a.cod_tipo_documento "
			+ "and a.cod_tipo_documento not in ('57833-6','57832-8','29304-3','81223-0') "
			+ "and a.data_annullamento is null "
			+ "and c.id_paziente = a.id_paz_irec "
		    + "and x.cod_cl = a.cod_cl and x.id_documento_ilec = a.id_documento_ilec and x.codice_identificativo_medico = :codiceIdentificativoMedico and x.tipo_medico in (:tipomedico) "
			+ "and a.data_validazione between :datainizioricerca and :datafinericerca "
			+ "and b.id_episodio_ilec = a.id_episodio_ilec "
			+ "and b.id_paz_irec = a.id_paz_irec "
			+ "and b.id_paz_irec = c.id_paziente "
			+ "and b.cod_cl = a.cod_cl "
			+ "and b.data_annullamento is null  "
			+ "and f.codice_tipo_episodio = b.codice_tipo_episodio  "
			+ "and b.data_fine is not null";
	
	private static final String SELECT_DOCUMENTI_OSCURATI = "select d.id_documento_ilec  from dmaccidx_t_oscuramento_documento d " 
	        + "where d.oscurato ='S'  "
			+ "and d.id_documento_ilec = :idDocIlec "
			+ "and d.cod_cl = :codCl ";

	private static final String SELECT_OSCURATI_A_GENITORE = "select d.id_documento_ilec  from dmaccidx_t_oscuramento_documento_genitore d " 
			+ "where d.oscurato ='S'  "
			+ "and d.id_documento_ilec = :idDocIlec "
			+ "and d.cod_cl = :codCl ";
	
	private static final String SELECT_OSCURATI_BY_NRE = "select nre_osc.id_documento_ilec  "
			+ "from dmacc_t_nre_oscurati nre_osc  "
			+ "join dmaccidx_t_doc_nre doc_nre on doc_nre.nre_rif_doc=nre_osc.nre  "
			+ "join dmacc_t_paziente paz on (paz.id_paziente = nre_osc.id_paziente and paz.data_annullamento is null) "
			+ "where doc_nre.id_documento_ilec=:idDocumentoIlec "
			+ "and doc_nre.cod_cl = :codiceComponenteLocale "
			+ "and paz.codice_fiscale = :citId  ";
	
	private static final String SELECT_OSCURATI_BY_ESEN = "select osc_ese.id  "
			+ "from dmacc_t_oscuramento_esenzioni osc_ese "
			+ "join dmacc_d_catalogo_tipo_documento d_tipo_doc 	on d_tipo_doc.classe='ESEN' "
			+ "join dmaccidx_t_documento doc  "
			+ "	on (doc.cod_tipo_documento = d_tipo_doc.codice_tipo_doc  "
			+ "			and doc.id_documento_ilec=:idDocumentoIlec  "
			+ "			and doc.cod_cl=:codiceComponenteLocale) "
			+ "join dmacc_t_paziente paz on (paz.id_paziente = osc_ese.id_paziente and paz.data_annullamento is null) "
			+ "where paz.codice_fiscale = :citId "
			+ "and chiuso = 'N' "
			+ "and oscurato = 'S'";
	
	private static final String SELECT_CODICE_TIPO_DOC_ESEN = "select a.codice_tipo_doc from dmacc_d_catalogo_tipo_documento a where a.classe='ESEN'";
	
	private static final String SELECT_ESEZIONE_OSCURATA="select * from dmacc_t_oscuramento_esenzioni e where e.id_paziente = ( "
			+ "select p.id_paziente  from dmacc_t_paziente p where p.codice_fiscale  = :citId; "
			+ ") "
			+ "and e.oscurato ='S'";
	
	private static final String SELECT_AUTORE_SMEDIAZIONE ="select a.autore_smediazione from dmaccidx_t_documento a where a.autore_smediazione is not null "
			+ "and a.id_documento_ilec = :idDocIlec "
			+ "and and a.cod_cl = :codCl";
	
	private static final String SELECT_DOCUMENTO="select "  
			+ "	doc.id_documento_ilec, "
			+ "	doc.cod_cl, "
			+ "	doc.codice_documento_dipartimentale, "
			+ "	doc.codice_azienda_sanitaria, "
			+ "	(pgp_sym_decrypt(doc.descrizione_azienda_sanitaria::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria,"
			+ "	(pgp_sym_decrypt(doc.descrizione_struttura::bytea, '@encryption_key@'))::varchar AS descrizione_struttura, "
			+ "	(pgp_sym_decrypt(doc.descrizione_uo::bytea, '@encryption_key@'))::varchar AS descrizione_uo,"
			+ "	doc.matricola_up_dip,"
			+ "	doc.data_validazione, "
			+ "	doc.cod_tipo_documento, "
			+ "	tipodoc.descr_tipo_documento, "
			+ "	doc.cod_tipo_file, "
			+ "	doc.codice_dipartimentale, "
			+ "	doc.cod_tipo_documento_alto, "
			+ "	doc.codice_tipo_assetto_organizzativo, "
			+ "	(pgp_sym_decrypt(doc.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar AS descrizione_tipo_assetto_organizzativo,"
			+ "	doc.id_repository_cl, "
			+ "	doc.cod_tipo_firma, "
			+ "	doc.descrizione_tipo_firma, "
			+ "	doc.ticket_pagato, "
			+ "	doc.ticket_da_pagare, "
			+ "	doc.oscura_scarico_cittadino, "
			+ "	doc.leggi_speciali, "
			+ "	doc.autore_smediazione, "
			+ "	doc.data_smediazione, "
			+ "	doc.coccarda, "
			+ "case when doc.flag_episodi_collegati ='S' then true "
			+ "when doc.flag_episodi_collegati ='N' then false "
			+ "else null "
			+ "end as flag_episodi_collegati, " 
			+ "case when doc.flag_associazioni_nre ='S' then true "
			+ "when doc.flag_associazioni_nre ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_nre, " 
			+ "case when doc.flag_associazioni_figli ='S' then true "
			+ "when doc.flag_associazioni_figli ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_figli, " 
			+ "	doc.stato_generazione_pacchetto, "
			+ "	null as accession_number, "
			+ "	episodio.id_episodio_ilec, "
			+ "	episodio.data_inizio, "
			+ "	episodio.data_fine, "
			+ "	episodio.codice_azienda_sanitaria_acc, "
			+ " (pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_acc,"
			+ "	episodio.codice_azienda_sanitaria_dim, "
			+ "	(pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_dim,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_acc,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_dim,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar AS descrizione_uo_acc,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar AS descrizione_uo_dim, "
			+ "	episodio.matricola_up_acc_dip, "
			+ "	doc.numero_nosologico, "
			+ "	doc.numero_passaggio_ps, "
			+ "	episodio.matricola_up_dim_dip, "
			+ "	episodio.codice_tipo_episodio, "
			+ "	tipoepisodio.descrizione_tipo_episodio, "
			+ "	paziente.codice_fiscale,"
			+ " null as nome_medico,"
			+ " null as cognome_medico,"
			+ " null as tipo_medico,"
			+ " null as classe,"
			+ " null as categoria,"
			+ " null as citId,"
			+ " null as  descr_tipo_documento,"
			+ " doc.pagato_ticket "
			+ " from "
			+ "	dmacc_t_paziente paziente, "
			+ "	dmaccidx_t_documento doc, "
			+ "	dmaccidx_t_episodio episodio, "
			+ "	dmaccidx_d_tipo_documento tipodoc, "
			+ "	dmaccidx_d_tipo_episodio tipoepisodio "
			+ " where "
			+ "	paziente.id_paziente = doc.id_paz_irec "
			+ "	and episodio.id_episodio_ilec = doc.id_episodio_ilec "
			+ "	and episodio.id_paz_irec = doc.id_paz_irec "
			+ "	and episodio.cod_cl = doc.cod_cl "
			+ "	and tipodoc.cod_tipo_documento = doc.cod_tipo_documento "
			+ "	and tipoepisodio.codice_tipo_episodio = episodio.codice_tipo_episodio "
			+ " and doc.data_annullamento is null "
			+ "	and doc.id_documento_ilec = :idDocumentoIlec "
			+ "	and doc.cod_cl = :codCl";
	
	private static final String SELECT_DMACC_T_DOC_NRE_FROM_RIF_DOCUMENTO = "select distinct a.id_documento_ilec "
			+"from dmaccidx_t_doc_nre a "
			+ "where a.nre_rif_doc = :rifDoc ";
	
	private static final String SELECT_DOCUMENTO_NRE="select "  
			+ "	doc.id_documento_ilec, "
			+ "	doc.cod_cl, "
			+ "	doc.codice_documento_dipartimentale, "
			+ "	doc.codice_azienda_sanitaria, "
			+ "	(pgp_sym_decrypt(doc.descrizione_azienda_sanitaria::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria,"
			+ "	(pgp_sym_decrypt(doc.descrizione_struttura::bytea, '@encryption_key@'))::varchar AS descrizione_struttura, "
			+ "	(pgp_sym_decrypt(doc.descrizione_uo::bytea, '@encryption_key@'))::varchar AS descrizione_uo,"
			+ "	doc.matricola_up_dip,"
			+ "	doc.data_validazione, "
			+ "	doc.cod_tipo_documento, "
			+ "	tipodoc.descr_tipo_documento, "
			+ "	doc.cod_tipo_file, "
			+ "	doc.codice_dipartimentale, "
			+ "	doc.cod_tipo_documento_alto, "
			+ "	doc.codice_tipo_assetto_organizzativo, "
			+ "	(pgp_sym_decrypt(doc.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar AS descrizione_tipo_assetto_organizzativo,"
			+ "	doc.id_repository_cl, "
			+ "	doc.cod_tipo_firma, "
			+ "	doc.descrizione_tipo_firma, "
			+ "	doc.ticket_pagato, "
			+ "	doc.ticket_da_pagare, "
			+ "	doc.oscura_scarico_cittadino, "
			+ "	doc.leggi_speciali, "
			+ "	doc.autore_smediazione, "
			+ "	doc.data_smediazione, "
			+ "	doc.coccarda, "
			+ "case when doc.flag_episodi_collegati ='S' then true "
			+ "when doc.flag_episodi_collegati ='N' then false "
			+ "else null "
			+ "end as flag_episodi_collegati, " 
			+ "case when doc.flag_associazioni_nre ='S' then true "
			+ "when doc.flag_associazioni_nre ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_nre, " 
			+ "case when doc.flag_associazioni_figli ='S' then true "
			+ "when doc.flag_associazioni_figli ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_figli, " 
			+ "	doc.stato_generazione_pacchetto, "
			+ "	null as accession_number, "
			+ "	episodio.id_episodio_ilec, "
			+ "	episodio.data_inizio, "
			+ "	episodio.data_fine, "
			+ "	episodio.codice_azienda_sanitaria_acc, "
			+ " (pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_acc,"
			+ "	episodio.codice_azienda_sanitaria_dim, "
			+ "	(pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_dim,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_acc,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_dim,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar AS descrizione_uo_acc,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar AS descrizione_uo_dim, "
			+ "	episodio.matricola_up_acc_dip, "
			+ "	doc.numero_nosologico, "
			+ "	doc.numero_passaggio_ps, "
			+ "	episodio.matricola_up_dim_dip, "
			+ "	episodio.codice_tipo_episodio, "
			+ "	tipoepisodio.descrizione_tipo_episodio, "
			+ "	paziente.codice_fiscale,"
			+ " null as nome_medico,"
			+ " null as cognome_medico,"
			+ " null as tipo_medico, "
			+ " null as classe,"
			+ " null as categoria,"
			+ " null as citId, "
			+ " null as  descr_tipo_documento,"
			+ " null as pagato_ticket "
			+ " from "
			+ "	dmacc_t_paziente paziente, "
			+ "	dmaccidx_t_documento doc, "
			+ "	dmaccidx_t_episodio episodio, "
			+ "	dmaccidx_d_tipo_documento tipodoc, "
			+ "	dmaccidx_d_tipo_episodio tipoepisodio "
			+ " where "
			+ "	paziente.id_paziente = doc.id_paz_irec "
			+ "	and episodio.id_episodio_ilec = doc.id_episodio_ilec "
			+ "	and episodio.id_paz_irec = doc.id_paz_irec "
			+ "	and episodio.cod_cl = doc.cod_cl "
			+ "	and tipodoc.cod_tipo_documento = doc.cod_tipo_documento "
			+ "	and tipoepisodio.codice_tipo_episodio = episodio.codice_tipo_episodio "
			+ "	and doc.id_documento_ilec IN "
			+ " ( SELECT ID_DOCUMENTO_ILEC FROM DMACCIDX_T_DOC_NRE"
			+ " WHERE NRE_RIF_DOC IN ( SELECT NRE_RIF_DOC FROM DMACCIDX_T_DOC_NRE WHERE "
			+ " ID_DOCUMENTO_ILEC=:idDocumentoIlec AND COD_CL=:codCl ) )";
			
	private static final String SELECT_DOCUMENTO_GENERICO="select "  
			+ "	doc.id_documento_ilec, "
			+ "	doc.cod_cl, "
			+ "	doc.codice_documento_dipartimentale, "
			+ "	doc.codice_azienda_sanitaria, "
			+ "	(pgp_sym_decrypt(doc.descrizione_azienda_sanitaria::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria,"
			+ "	(pgp_sym_decrypt(doc.descrizione_struttura::bytea, '@encryption_key@'))::varchar AS descrizione_struttura, "
			+ "	(pgp_sym_decrypt(doc.descrizione_uo::bytea, '@encryption_key@'))::varchar AS descrizione_uo,"
			+ "	doc.matricola_up_dip,"
			+ "	doc.data_validazione, "
			+ "	doc.cod_tipo_documento, "
			+ "	tipodoc.descr_tipo_documento, "
			+ "	doc.cod_tipo_file, "
			+ "	doc.codice_dipartimentale, "
			+ "	doc.cod_tipo_documento_alto, "
			+ "	doc.codice_tipo_assetto_organizzativo, "
			+ "	(pgp_sym_decrypt(doc.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar AS descrizione_tipo_assetto_organizzativo,"
			+ "	doc.id_repository_cl, "
			+ "	doc.cod_tipo_firma, "
			+ "	doc.descrizione_tipo_firma, "
			+ "	doc.ticket_pagato, "
			+ "	doc.ticket_da_pagare, "
			+ "	doc.oscura_scarico_cittadino, "
			+ "	doc.leggi_speciali, "
			+ "	doc.autore_smediazione, "
			+ "	doc.data_smediazione, "
			+ "	doc.coccarda, "
			+ "case when doc.flag_episodi_collegati ='S' then true "
			+ "when doc.flag_episodi_collegati ='N' then false "
			+ "else null "
			+ "end as flag_episodi_collegati, " 
			+ "case when doc.flag_associazioni_nre ='S' then true "
			+ "when doc.flag_associazioni_nre ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_nre, " 
			+ "case when doc.flag_associazioni_figli ='S' then true "
			+ "when doc.flag_associazioni_figli ='N' then false "
			+ "else null "
			+ "end as flag_associazioni_figli, " 
			+ "	doc.stato_generazione_pacchetto, "
			+ "	null as accession_number, "
			+ "	episodio.id_episodio_ilec, "
			+ "	episodio.data_inizio, "
			+ "	episodio.data_fine, "
			+ "	episodio.codice_azienda_sanitaria_acc, "
			+ " (pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_acc::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_acc,"
			+ "	episodio.codice_azienda_sanitaria_dim, "
			+ "	(pgp_sym_decrypt(episodio.descrizione_azienda_sanitaria_dim::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria_dim,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_struttura_acc::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_acc,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_struttura_dim::bytea, '@encryption_key@'))::varchar AS descrizione_struttura_dim,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_uo_acc::bytea, '@encryption_key@'))::varchar AS descrizione_uo_acc,"
			+ "	(pgp_sym_decrypt(episodio.descrizione_uo_dim::bytea, '@encryption_key@'))::varchar AS descrizione_uo_dim, "
			+ "	episodio.matricola_up_acc_dip, "
			+ "	doc.numero_nosologico, "
			+ "	doc.numero_passaggio_ps, "
			+ "	episodio.matricola_up_dim_dip, "
			+ "	episodio.codice_tipo_episodio, "
			+ "	tipoepisodio.descrizione_tipo_episodio, "
			+ "	paziente.codice_fiscale,"
			+ " null as nome_medico,"
			+ " null as cognome_medico,"
			+ " null as tipo_medico, "
			+ " null as classe,"
			+ " null as categoria,"
			+ " null as citId, "
			+ " null as  descr_tipo_documento,"
			+ " null as pagato_ticket "
			+ " from "
			+ "	dmacc_t_paziente paziente, "
			+ "	dmaccidx_t_documento doc, "
			+ "	dmaccidx_t_episodio episodio, "
			+ "	dmaccidx_d_tipo_documento tipodoc, "
			+ "	dmaccidx_d_tipo_episodio tipoepisodio "
			+ " where "
			+ "	paziente.id_paziente = doc.id_paz_irec "
			+ "	and episodio.id_episodio_ilec = doc.id_episodio_ilec "
			+ "	and episodio.id_paz_irec = doc.id_paz_irec "
			+ "	and episodio.cod_cl = doc.cod_cl "
			+ "	and tipodoc.cod_tipo_documento = doc.cod_tipo_documento "
			+ "	and tipoepisodio.codice_tipo_episodio = episodio.codice_tipo_episodio ";
		

			
	private static final String SELECT_DMACCIDX_DETTAGLIO_DOC_FSE = "select distinct doc.id_documento_ilec, "
			+ "doc.recupero_pregresso, null AS medico, "
			+ "(pgp_sym_decrypt(doc.descrizione_uo::bytea, '@encryption_key@'))::varchar AS descrizione_uo, "
			+ "(pgp_sym_decrypt(doc.descrizione_tipo_assetto_organizzativo::bytea, '@encryption_key@'))::varchar as descrizione_tipo_assetto_organizzativo, "
			+ "doc.coccarda, doc.data_validazione::date, "
			+ "(pgp_sym_decrypt(doc.descrizione_azienda_sanitaria ::bytea, '@encryption_key@'))::varchar AS descrizione_azienda_sanitaria, " 
			+ "(pgp_sym_decrypt(doc.descrizione_struttura ::bytea, '@encryption_key@'))::varchar AS descrizione_struttura, "
			+ "doc.data_smediazione::date,doc.autore_smediazione "
			+ "from dmaccidx_t_documento doc, dmacc_t_paziente paz, dmaccidx_t_oscuramento_documento oscuradoc "
			+ "where paz.codice_fiscale = :citId and doc.id_documento_ilec = :idDocumentoIlec and doc.cod_cl = :codiceCL "
			+ "and doc.data_annullamento is null  "
			+ "and paz.id_paziente = doc.id_paz_irec "
			+ "and oscuradoc.id_documento_ilec = doc.id_documento_ilec and oscuradoc.cod_cl = doc.cod_cl ";

	
	
	private static final String SELECT_DMACCIDX_DETTAGLIO_DOC_PERSONALE = "select metadati.id_documento AS id_documento_ilec, " 
			+ "null AS recupero_pregresso, metadati.medico, "
			+ "null AS coccarda, metadati.unita_operativa AS descrizione_uo, null AS descrizione_tipo_assetto_organizzativo, "
			+ "metadati.data_emissione::date AS data_validazione, null AS descrizione_azienda_sanitaria, metadati.struttura_sanitaria AS descrizione_struttura, "
			+ "null AS data_smediazione, null AS autore_smediazione "
			+ "from dmaccidx_t_metadati_documento metadati, dmacc_t_paziente paz, dmaccidx_t_oscuramento_doc_personale doc "
			+ "where paz.id_paziente = metadati.id_paz_irec and paz.codice_fiscale  = :citId "
			+ "and metadati.id_documento = doc.id_documento_personale and doc.oscurato = 'N' and metadati.id_documento = :idDocumentoIlec";

	

	private static final String SELECT_METADATI_DOCUMENTO = "select id_documento_ilec, id_episodio_ilec, id_paz_irec, id_repository_cl, "
			+ "codice_azienda_sanitaria, cod_cl, codice_documento_dipartimentale, data_validazione, firmato_digitalmente, "
			+ "pagato_ticket, cod_tipo_documento, cod_tipo_file "
			+ "from dmaccidx_t_documento "
			+ "where id_paz_irec = :idPaziente "
			+ "and id_documento_ilec = :idDocumentoIlec "
			+ "and cod_cl = :codiceComponenteLocale "
			+ "and data_annullamento is null "
			+ "order by data_inserimento desc "
			+ "limit 1";
	
	private static final String SELECT_ID_DOCUMENTO_BY_OID = "select id_documento_ilec "
			+ "from dmaccidx_t_documento "
			+ "where id_paz_irec = :idPaziente "
			+ "and codice_documento_dipartimentale = :codiceDocumentoDipartimentale "
			+ "and cod_cl = :codiceComponenteLocale "
			+ "and data_annullamento is null "
			+ "order by data_iserimento desc "
			+ "limit 1";

	private static final String SELECT_SMEDIAZIONE_AUTORIZZATA = "select count(*)  "
			+ "from dmaccidx_t_documento tdoc "
			+ "join dmacc_t_paziente tpaz on tpaz.id_paziente = tdoc.id_paz_irec "
			+ "join dmacc_d_catalogo_tipo_documento dctd on dctd.codice_tipo_doc = tdoc.cod_tipo_documento "
			+ "where tdoc.id_documento_ilec = :idDocumentoIlec  "
			+ " and tdoc.cod_cl = :codiceComponenteLocale "
			+ "	and tpaz.codice_fiscale = :citId "
			+ "	and dctd.smediazione_autorizzata ='S'";
	
	private static final String SELECT_AZIENDA_SMEDIAZIONE = "select tdoc.codice_azienda_sanitaria "
			+ "from dmaccidx_t_documento tdoc "
			+ "join dmacc_t_paziente tpaz on tpaz.id_paziente = tdoc.id_paz_irec "
			+ "where tdoc.id_documento_ilec = :idDocumentoIlec  "
			+ " and tdoc.cod_cl = :codiceComponenteLocale "
			+ "	and tpaz.codice_fiscale = :citId";
	
	private static final String SELECT_DMACC_T_DOCUMENTI_QRCODE = "SELECT tdq.id,  "
			+ "  tdq.id_paziente,  "
			+ "  paz.codice_fiscale, "
			+ "  tdq.id_componente_locale,   "
			+ "  cl.codice as cod_cl, "
			+ "  convert_from(pgp_sym_decrypt_bytea(tdq.id_referto, '@encryption_key@'),'UTF8')::bigint as id_referto,  "
			+ "  convert_from(pgp_sym_decrypt_bytea(tdq.id_documento, '@encryption_key@'),'UTF8')::bigint as id_documento,  "
			+ "  convert_from(pgp_sym_decrypt_bytea(tdq.id_episodio, '@encryption_key@'),'UTF8')::bigint as id_episodio, "
			+ "  convert_from(pgp_sym_decrypt_bytea(tdq.codice_documento_dipartimentale, '@encryption_key@'),'UTF8')::text as codice_documento_dipartimentale, "
			+ "  tdq.token as qrcode_token, "
			+ "  tdq.data_inserimento, "
			+ "  tdq.data_aggiornamento  "
			+ "from dmacc_t_documenti_qrcode tdq "
			+ "join dmacc_t_componente_locale cl on cl.id_componente_locale = tdq.id_componente_locale  "
			+ "join dmacc_t_paziente paz on paz.id_paziente = tdq.id_paziente  "
			+ "where tdq.token = :qrcodeToken "
			+ "order by tdq.data_inserimento desc  limit 1 ";
	
	private static final String SELECT_CODICE_CL_BY_ID_REPOSITORY = "select codice "
			+ "from dmacc_t_componente_locale "
			+ "where id_repository = :idRepository "
			+ "order by data_inserimento desc limit 1";
	
	private static final String SELECT_STORICO_CF_BY_ID_PAZIENTE = "select codice_fiscale "
			+ " from dmacc_t_paziente_sto "
			+ " where data_annullamento is null "
			+ " and id_paziente = :idPaziente ";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private boolean IsNreOscurati(long idDocumentoIlec,String citId) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("citId", citId).addValue("idDocumentoIlec", idDocumentoIlec);
		try {
			Integer numeronreoscurati = jdbcTemplate.queryForObject(SELECT_DMACC_T_DOC_NRE_OSCURATI, namedParameters,Integer.class);
			if (numeronreoscurati>0) //sono oscurati da eliminare
				return true;
			else 
			    return false;
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_NRE_OSCURATI";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	public boolean IsNreOscuratiEx(long idDocumentoIlec,String citId,String codiceCL) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("citId", citId).addValue("idDocumentoIlec", idDocumentoIlec).addValue("codiceCL", codiceCL);
		try {
			Integer numeronreoscurati = jdbcTemplate.queryForObject(SELECT_DMACC_T_DOC_NRE_OSCURATI_EX, namedParameters,Integer.class);
			if (numeronreoscurati>0) //sono oscurati da eliminare
				return true;
			else 
			    return false;
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_NRE_OSCURATI";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	private boolean IsEsenzioneOscurata(String citId) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("citId", citId);
		try {
			List<String> oscurato = jdbcTemplate.queryForList(SELECT_DMACC_T_DOC_ESENZIONE_OSCURATA, namedParameters,String.class);
			if (oscurato.size()>1 || oscurato.size()==0)
				return true;
			else 
			    return Util.stringToBoolean(oscurato.get(0),Constants.S);
		} catch (EmptyResultDataAccessException e) {
			return true; 
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_ESENZIONE_OSCURATA";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	private List<String> getAccessionNumber(String codCl,long idDocumentoIlec) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codCl", codCl).addValue("idDocumentoIlec", idDocumentoIlec);
		try {
			List<String> accessionNumber = jdbcTemplate.queryForList(SELECT_DMACC_T_DOC_ACCESSION_NUMBER, namedParameters,String.class);
			return accessionNumber;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_ACCESSION_NUMBER";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	
	public List<Medico> getMedico(String codCl,long idDocumentoIlec) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codCl", codCl).addValue("idDocumentoIlec", idDocumentoIlec);
		try {
			List<Medico> medico = jdbcTemplate.query(SELECT_DMACC_T_DOC_MEDICO, namedParameters,new MedicoMapper());
			//va capito se una lista
			return medico;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_MEDICO";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	private List<Medico> getMedico(String codCl,long idDocumentoIlec,List<String> tipoMedico) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("codCl", codCl)
				.addValue("idDocumentoIlec", idDocumentoIlec)
				.addValue("tipoMedico", tipoMedico);
		try {
			String query = "";
			if (tipoMedico == null || tipoMedico.isEmpty())
				query = SELECT_DMACC_T_DOC_MEDICO;
			else
				query = SELECT_DMACC_T_DOC_MEDICO_TIPO;
			List<Medico> medico = jdbcTemplate.query(query, namedParameters,new MedicoMapper());
			return medico;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_MEDICO_TIPO";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
//	private List<String> getTipoMedico(String codCl,long idDocumentoIlec, String cfMedico) throws DatabaseException {
//
//		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codCl", codCl).addValue("idDocumentoIlec", idDocumentoIlec).addValue("cfMedico", cfMedico);
//		try {
//			List<String> tipomedico = jdbcTemplate.queryForList(SELECT_DMACC_T_DOC_TIPO_MEDICO, namedParameters,String.class);
//			return tipomedico;
//		} catch (EmptyResultDataAccessException e) {
//			return null; 
//		} catch (Exception e) {
//			String methodName = "SELECT_DMACC_T_DOC_TIPO_MEDICO";
//			logError(methodName, e.getMessage());
//			throw new DatabaseException(e);
//		}	
//	}
	
	
	private List<EpisodioEsteso> selectListaEpisodi(List<String> tipodocumento,String tipoepisodio,String citId, Date datainizioricerca, Date datafinericerca
			) throws DatabaseException {


		try {
			List<EpisodioEsteso> listaepisodi = new ArrayList<EpisodioEsteso>();
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("tipodocumento", tipodocumento).addValue("citId", citId)
					.addValue("datainizioricerca", datainizioricerca).addValue("datafinericerca", datafinericerca).addValue("tipoepisodio", tipoepisodio);
			StringBuilder sql = new StringBuilder().append(SELECT_DMACCIDX_T_DOCUMENTO_NON_OSCURATO);
			
			if (tipoepisodio !=null) {
				sql.append(CONTIPOEPISODIO);
			}
			sql.append(DAT_INI_ORDER_BY);
				listaepisodi = jdbcTemplate.query(sql.toString(), namedParameters, new EpisodioEstesoMapper());	
			return listaepisodi;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectListaEpisodi";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	 
	public List<Episodio> selectListaEpisodiNoOscurati(List<String> tipodocumento,String tipoepisodio,String citId, Date datainizioricerca, Date datafinericerca) throws DatabaseException{
		List<EpisodioEsteso> listaepisodi = new ArrayList<EpisodioEsteso>();
		List<Episodio> listaepisodinooscurati = new ArrayList<Episodio>();
		listaepisodi = selectListaEpisodi(tipodocumento,tipoepisodio,citId, datainizioricerca, datafinericerca);
		
		for (EpisodioEsteso episodio : listaepisodi) {
			if (episodio.getClasse().equalsIgnoreCase(Constants.ESENZIONE)) {
				if (!IsEsenzioneOscurata(episodio.getCitId())){
					//verifica anche nre
					if (!IsNreOscurati(episodio.getIdDocumentoIlec(),episodio.getCitId())) {
						episodio.setClasse(null);
						episodio.setIdDocumentoIlec(null);
						listaepisodinooscurati.add(episodio);	
					}
				}
			}
			else {
				if (!IsNreOscurati(episodio.getIdDocumentoIlec(),episodio.getCitId())) {
					episodio.setClasse(null);
					episodio.setIdDocumentoIlec(null);
					listaepisodinooscurati.add(episodio);
				}
			}
		}
		Collections.sort(listaepisodinooscurati);
		Collections.reverse(listaepisodinooscurati);
		return listaepisodinooscurati;
		
	}
	
	private List<SintesiDocumentoEsteso> selectListaDocumenti(long idEpisodio,String codCl,String citId) throws DatabaseException {


		try {
			List<SintesiDocumentoEsteso> listadocumenti = new ArrayList<SintesiDocumentoEsteso>();
			List<String> tipoMedico = new ArrayList<String>();
			
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idEpisodio", idEpisodio).addValue("citId", citId)
					.addValue("codCl", codCl);
			//prendo tutti i documenti senza medico
			listadocumenti = jdbcTemplate.query(SELECT_DMACCIDX_T_DOCUMENTO_DOCUMENTI_EPISODI_NON_OSCURATO, namedParameters, new SintesiDocumentoEstesoMapper());
			if (listadocumenti.size()>0) {
				//prendo il medico
				for (SintesiDocumento docsin : listadocumenti) {
					//prendo il medico
					List<Medico> medico = new ArrayList<Medico>();
					medico = getMedico(docsin.getCodiceCl(),docsin.getIdDocumentoIlec());
					//prendo il tipo medico non piu necessario per relazione uno a uno
					//aggiungo il medico ai metadati
					docsin.getMetadatiDocumento().setMedici(medico);
					//prendo accession number
					 List<String> accessionnumber = getAccessionNumber(docsin.getCodiceCl(),docsin.getIdDocumentoIlec());
					 docsin.getMetadatiDocumento().setAccessionNumber(accessionnumber);
				}
			}
			return listadocumenti;
		} catch (Exception e) {
			String methodName = "selectListaDocumenti";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}
	}
	
	public List<SintesiDocumento> selectListaDocumentiNoOscurati(long idEpisodio,String codCl,String citId) throws DatabaseException{
		List<SintesiDocumentoEsteso> listadocumenti = new ArrayList<SintesiDocumentoEsteso>();
		List<SintesiDocumento> listadocumentinooscurati = new ArrayList<SintesiDocumento>();
		listadocumenti = selectListaDocumenti(idEpisodio,codCl,citId);
		
		for (SintesiDocumentoEsteso documento : listadocumenti) {
			if (documento.getClasse().equalsIgnoreCase(Constants.ESENZIONE)) {
				if (!IsEsenzioneOscurata(documento.getCitId())){
					//verifica anche nre
					if (!IsNreOscurati(documento.getIdDocumentoIlec(),documento.getCitId())) {
						documento.setClasse(null);
						listadocumentinooscurati.add(documento);	
					}
				}
			}
			else {
				if (!IsNreOscurati(documento.getIdDocumentoIlec(),documento.getCitId())) {
					documento.setClasse(null);
					listadocumentinooscurati.add(documento);
				}
			}
		}
		return listadocumentinooscurati;
		
	}
	
	
	public List<SintesiDocumento> selectMieiReferti(String codiceIdentificativoMedico, String citId, String tipomedico, Date datainizioricerca,Date datafinericerca) throws DatabaseException {

		
		List<String> siglaTipoMedico = new ArrayList<>();
		
		if (datafinericerca != null)
			datafinericerca = Util.getEndOfDay(datafinericerca);
	
		switch (tipomedico.toUpperCase()) {
		case Constants.TIPO_MEDICO_VALIDANTE:
			siglaTipoMedico.add("V");;
			break;	
		case Constants.TIPO_MEDICO_REFERTANTE:
			siglaTipoMedico.add("R");
			break;
		case Constants.TIPO_MEDICO_VALIDANTEOREFERTANTE:
			siglaTipoMedico.add("V");
			siglaTipoMedico.add("R");
			break;
		}
		
		if (siglaTipoMedico.isEmpty())
			throw new DatabaseException("tipomedico puo assumere i seguenti valori VALIDANTEOREFERTANTE,VALIDANTE,REFERTANTE");
		
		try {
			List<SintesiDocumento> listadocumenti = new ArrayList<SintesiDocumento>();
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("citId", citId).addValue("datainizioricerca", datainizioricerca)
					.addValue("datafinericerca", datafinericerca).addValue("tipomedico", siglaTipoMedico).addValue("codiceIdentificativoMedico", codiceIdentificativoMedico);
			StringBuilder sql = new StringBuilder().append(SELECT_DMACCIDX_T_MIEI_REFERTI);
			
			
		
			listadocumenti = jdbcTemplate.query(sql.toString(), namedParameters, new SintesiMieiRefertiMapper());	
			return listadocumenti;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectListaEpisodi";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	private List<String> getNreRifDoc(String codCl,long idDocumentoIlec) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codCl", codCl).addValue("idDocumentoIlec", idDocumentoIlec);
		try {
			List<String> nreRifDoc = jdbcTemplate.queryForList(SELECT_DMACC_T_DOC_NRE, namedParameters,String.class);
			return nreRifDoc;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_DMACC_T_DOC_NRE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	public boolean isDocumentoOscurato(long idDocumentoIlec,String codiceCl)  throws DatabaseException{

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("codCl", codiceCl)
				.addValue("idDocIlec", idDocumentoIlec);
		return hasRecords(SELECT_DOCUMENTI_OSCURATI, "SELECT_DOCUMENTI_OSCURATI", namedParameters);
	}
		
	public boolean isDocumentoOscuratoAGenitore(long idDocumentoIlec,String codiceCl) throws DatabaseException  {
		
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("codCl", codiceCl)
				.addValue("idDocIlec", idDocumentoIlec);
		return hasRecords(SELECT_OSCURATI_A_GENITORE, "SELECT_OSCURATI_A_GENITORE", namedParameters);
	}
	
	public boolean isEsenzioneOscurata(String citId)  throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("citId", citId);
		try {
			List<Integer> idPaziente = jdbcTemplate.queryForList(SELECT_ESEZIONE_OSCURATA, namedParameters,Integer.class);
			if(idPaziente.size()>0) {
				return true;
			}else {
				return false;
			}
		} catch (EmptyResultDataAccessException e) {
			return false; 
		} catch (Exception e) {
			String methodName = "SELECT_DOCUMENTI_OSCURATI";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	public List<String> getCodiceEsenzioni()  throws DatabaseException{


		SqlParameterSource namedParameters = new MapSqlParameterSource();
		try {
			List<String> codiciEsen = jdbcTemplate.queryForList(SELECT_CODICE_TIPO_DOC_ESEN, namedParameters,String.class);
			return codiciEsen;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_DOCUMENTI_OSCURATI";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}		
	}
	
	
	public boolean isDocumentoOscuratoByRicetta(long idDocumentoIlec,String codiceComponenteLocale, String citId) throws DatabaseException {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("idDocumentoIlec",idDocumentoIlec)
				.addValue("codiceComponenteLocale", codiceComponenteLocale)
				.addValue("citId", citId);
		return hasRecords(SELECT_OSCURATI_BY_NRE, "SELECT_OSCURATI_BY_NRE", namedParameters);
	}
	
	public boolean isDocumentoOscuratoByEsenzione(long idDocumentoIlec,String codiceComponenteLocale, String citId) throws DatabaseException  {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("idDocumentoIlec",idDocumentoIlec)
				.addValue("codiceComponenteLocale", codiceComponenteLocale)
				.addValue("citId", citId);
		return hasRecords(SELECT_OSCURATI_BY_ESEN, "SELECT_OSCURATI_BY_ESEN", namedParameters);
	}
	
	private boolean hasRecords(String querySql, String queryName, SqlParameterSource namedParameters) throws DatabaseException {
		try {
			List<Long> result = jdbcTemplate.queryForList(querySql, namedParameters,Long.class);
			if(result.size()>0) {
				return true;
			}else {
				return false;
			}
		} catch (EmptyResultDataAccessException e) {
			return false; 
		} catch (Exception e) {
			String methodName = queryName;
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	}
	
	
	
		public String getAutoreSmedizione(long idDocumentoIlec,String codiceCl) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idDocumentoIlec", idDocumentoIlec).addValue("codCl", codiceCl);
		try {
			String autoreSmedizione = jdbcTemplate.queryForObject(SELECT_AUTORE_SMEDIAZIONE,namedParameters, String.class);
			return autoreSmedizione;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_AUTORE_SMEDIAZIONE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}	
	
		}
		
		public SintesiDocumentoEsteso selectDocumento(long idDocumentoIlec,String codiceCl) throws DatabaseException{



			try {
				SintesiDocumentoEsteso documento = new SintesiDocumentoEsteso();
				
				
				SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idDocumentoIlec", idDocumentoIlec).addValue("codCl", codiceCl);
						
				//prendo tutti i documenti senza medico
				documento = jdbcTemplate.queryForObject(SELECT_DOCUMENTO, namedParameters, new SintesiDocumentoEstesoMapper());
				
						//prendo il medico
						List<Medico> medico = new ArrayList<Medico>();
						medico = getMedico(codiceCl,idDocumentoIlec);
						//aggiungo il medico ai metadati
						documento.getMetadatiDocumento().setMedici(medico);
						//prendo accession number
						 List<String> accessionnumber = getAccessionNumber(codiceCl,idDocumentoIlec);
						 documento.getMetadatiDocumento().setAccessionNumber(accessionnumber);
				
				return documento;
			} catch (EmptyResultDataAccessException e) {
				return null; 
			} catch (Exception e) {
				String methodName = "selectDocumento";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}
		
			
		}
		
		public List<SintesiDocumentoEsteso> selectDocumentiCorrealati(long idDocumentoIlec,String codiceCl,String tipoCorrelazioneDocumento) throws DatabaseException{
			
			List<SintesiDocumentoEsteso> correlati = new ArrayList<>();
			
			try {
				
				switch (tipoCorrelazioneDocumento) {
					case Constants.DOC_NRE:
						correlati = selectDocumentiNre(idDocumentoIlec, codiceCl);
						break;

					case Constants.DOC_SDO:
						correlati = selectDocumentiCorrelatiGenerico(idDocumentoIlec, codiceCl,
								tipoCorrelazioneDocumento);
						break;
					case Constants.PADRE_FIGLIO:
						correlati = selectDocumentiCorrelatiGenerico(idDocumentoIlec, codiceCl,
								tipoCorrelazioneDocumento);
						break;
					case Constants.PASSAGGIO_PS:
						correlati = selectDocumentiCorrelatiGenerico(idDocumentoIlec, codiceCl,
								tipoCorrelazioneDocumento);
						break;

				}
				
				
			}catch (EmptyResultDataAccessException e) {
				return null; 
			} catch (Exception e) {
				String methodName = "SELECT_DMACC_T_DOC_NRE";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}	
			
			
			return correlati;
		}
		

		private List<SintesiDocumentoEsteso> selectDocumentiNre(long idDocumentoIlec,String codiceCl) throws DatabaseException{



			try {
				 List<SintesiDocumentoEsteso> documenti = new ArrayList<SintesiDocumentoEsteso>();
				
				
				SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idDocumentoIlec", idDocumentoIlec).addValue("codCl", codiceCl);
						
				//prendo tutti i documenti senza medico
				documenti = jdbcTemplate.query(SELECT_DOCUMENTO_NRE, namedParameters,  new SintesiDocumentoEstesoMapper());
			
				
				return documenti;
			} catch (EmptyResultDataAccessException e) {
				return null; 
			} catch (Exception e) {
				String methodName = "SELECT_DOCUMENTO_NRE";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}	
		
			
		}
		
		private List<SintesiDocumentoEsteso> selectDocumentiCorrelatiGenerico(long idDocumentoIlec,String codiceCl,String tipoCorrelazioneDocumento) throws DatabaseException{

			String query= SELECT_DOCUMENTO_GENERICO;

			try {
				
				 
					switch (tipoCorrelazioneDocumento) {
						case Constants.DOC_SDO:
							query = query + " AND doc.numero_nosologico IN ("
									+ " select numero_nosologico from dmaccidx_t_documento  WHERE ID_DOCUMENTO_ILEC = :idDocumentoIlec AND COD_CL = :codCl AND DATA_ANNULLAMENTO IS NULL)";
							break;
						case Constants.PASSAGGIO_PS:
							query = query + " AND doc.NUMERO_PASSAGGIO_PS IN ("
									+ " select NUMERO_PASSAGGIO_PS from dmaccidx_t_documento  WHERE ID_DOCUMENTO_ILEC = :idDocumentoIlec AND COD_CL = :codCl AND DATA_ANNULLAMENTO IS NULL)";
							break;
						case Constants.PADRE_FIGLIO:
							query = query + " AND doc.ID_DOCUMENTO_PARENT IN ("
									+ " select ID_DOCUMENTO_PARENT from dmaccidx_t_documento  WHERE ID_DOCUMENTO_ILEC = :idDocumentoIlec AND COD_CL = :codCl AND DATA_ANNULLAMENTO IS NULL)";
							break;	

					}
				
				
				SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idDocumentoIlec", idDocumentoIlec).addValue("codCl", codiceCl);
						
				
				return jdbcTemplate.query(query, namedParameters,  new SintesiDocumentoEstesoMapper());
			
				
				
			} catch (EmptyResultDataAccessException e) {
				return null; 
			} catch (Exception e) {
				String methodName = "SELECT_DOCUMENTO_GENERICO";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}	
		
			
		}
		
		public SintesiDocumento selectDettaglioDoc(String codiceIdentificativoMedico, String categoria, String tipoMedico, String citId, Long idDocumentoIlec, String codiceCL) throws DatabaseException {

			try {
				

				List<String> siglaTipoMedico = new ArrayList<>();
				
				if (tipoMedico != null && !tipoMedico.isEmpty())
				{
					switch (tipoMedico.toUpperCase()) {
					case Constants.TIPO_MEDICO_VALIDANTE:
						siglaTipoMedico.add("V");;
						break;	
					case Constants.TIPO_MEDICO_REFERTANTE:
						siglaTipoMedico.add("R");
						break;
					case Constants.TIPO_MEDICO_VALIDANTEOREFERTANTE:
						siglaTipoMedico.add("V");
						siglaTipoMedico.add("R");
						break;
					default:
						siglaTipoMedico.add("UNDEFINED");
					
					}
				}

		
					
				if (categoria.equalsIgnoreCase(Constants.CATEGORIA_FSE))
				{
					
					SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceIdentificativoMedico", codiceIdentificativoMedico).addValue("citId", citId).addValue("idDocumentoIlec", idDocumentoIlec)
							.addValue("codiceCL", codiceCL);
					StringBuilder sql = new StringBuilder().append(SELECT_DMACCIDX_DETTAGLIO_DOC_FSE);
					SintesiDocumento documento = jdbcTemplate.queryForObject(sql.toString(), namedParameters, new SintesiDocumentoDettaglioDocMapper());	
					
					List<String> accessionnumber = getAccessionNumber(codiceCL,idDocumentoIlec);
					documento.getMetadatiDocumento().setAccessionNumber(accessionnumber);
										
					
					List<String> nre = getNreRifDoc(codiceCL,idDocumentoIlec);
					documento.getMetadatiDocumento().setNre(nre);
					
					List<Medico> medici  = getMedico(codiceCL, idDocumentoIlec,siglaTipoMedico);							
					documento.getMetadatiDocumento().setMedici(medici);

					return documento;
				} 
				else if (categoria.equalsIgnoreCase(Constants.CATEGORIA_PERSONALE)) {
					SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceIdentificativoMedico", codiceIdentificativoMedico).addValue("citId", citId).addValue("idDocumentoIlec", idDocumentoIlec)
							.addValue("codiceCL", codiceCL);
					StringBuilder sql = new StringBuilder().append(SELECT_DMACCIDX_DETTAGLIO_DOC_PERSONALE);
					SintesiDocumento documento = jdbcTemplate.queryForObject(sql.toString(), namedParameters, new SintesiDocumentoDettaglioDocMapper());	
					return documento;
				
				}
				else 
					return null;
							
		        
			} catch (EmptyResultDataAccessException e) {
				return null;
			} catch (Exception e) {
				String methodName = "selectDettaglioDoc";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}

		}	
		
		public DmaccidxTDocumento selectMetadatiDocumento(long idPaziente, long idDocumentoIlec, String codiceComponenteLocale) throws DatabaseException {
			try {
				SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idPaziente",idPaziente)
						.addValue("idDocumentoIlec",idDocumentoIlec)
						.addValue("codiceComponenteLocale",codiceComponenteLocale);
				
				DmaccidxTDocumento result = jdbcTemplate.queryForObject(SELECT_METADATI_DOCUMENTO, namedParameters,new DmaccidxTDocumentoMapper());
			    return result;
			} catch (EmptyResultDataAccessException e) {
				return null;
			} catch (Exception e) {
				String methodName = "SELECT_METADATI_DOCUMENTO";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}
		}
		
		public Long selectIdDocumento(long idPaziente, String codiceDocumentoDipartimentale, String codiceComponenteLocale) throws DatabaseException {
			try {
				SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idPaziente",idPaziente)
						.addValue("codiceDocumentoDipartimentale",codiceDocumentoDipartimentale)
						.addValue("codiceComponenteLocale",codiceComponenteLocale);
				
				Long result = jdbcTemplate.queryForObject(SELECT_ID_DOCUMENTO_BY_OID, namedParameters,Long.class);
			    return result;
			} catch (EmptyResultDataAccessException e) {
				return null;
			} catch (Exception e) {
				String methodName = "SELECT_ID_DOCUMENTO_BY_OID";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}
		}
		
		public boolean isSmediazione(Long idDocumentoIlec, String codiceComponenteLocale, String citId) throws DatabaseException {

			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("idDocumentoIlec", idDocumentoIlec)
					.addValue("codiceComponenteLocale", codiceComponenteLocale)
					.addValue("citId", citId);
			try {
				Integer count = jdbcTemplate.queryForObject(SELECT_SMEDIAZIONE_AUTORIZZATA, namedParameters,Integer.class);
				return count > 0 ? true : false;
			} catch (EmptyResultDataAccessException e) {
				return false; 
			} catch (Exception e) {
				String methodName = "SELECT_SMEDIAZIONE_AUTORIZZATA";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}
		}
		
		public String aziendaSmediazione(Long idDocumentoIlec, String codiceComponenteLocale, String citId) throws DatabaseException {

			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("idDocumentoIlec", idDocumentoIlec)
					.addValue("codiceComponenteLocale", codiceComponenteLocale)
					.addValue("citId", citId);
			try {
				String azienda = jdbcTemplate.queryForObject(SELECT_AZIENDA_SMEDIAZIONE, namedParameters,String.class);
				return azienda;
			} catch (EmptyResultDataAccessException e) {
				return null; 
			} catch (Exception e) {
				String methodName = "SELECT_AZIENDA_SMEDIAZIONE";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}
		}
		
		public DmaccTDocumentiQrcode selectDocumentoByQrcode(String qrcodeToken) throws DatabaseException {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("qrcodeToken", qrcodeToken);
			try {
				DmaccTDocumentiQrcode result = jdbcTemplate.queryForObject(SELECT_DMACC_T_DOCUMENTI_QRCODE, namedParameters,new DmaccTDocumentiQrcodeMapper());
				return result;
			} catch (EmptyResultDataAccessException e) {
				return null;
			} catch (Exception e) {
				logError("SELECT_DMACC_T_DOCUMENTI_QRCODE", e.getMessage());
				throw new DatabaseException(e);
			}
		}
		
		public String selectCodiceClByIdRepository(String idRepository) throws DatabaseException {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("idRepository", idRepository);
			try {
				String codCl = jdbcTemplate.queryForObject(SELECT_CODICE_CL_BY_ID_REPOSITORY, namedParameters,String.class);
				return codCl;
			} catch (EmptyResultDataAccessException e) {
				return null;
			} catch (Exception e) {
				logError("SELECT_CODICE_CL_BY_ID_REPOSITORY", e.getMessage());
				throw new DatabaseException(e);
			}
		}
		
		public List<String> findStoricoCfByIdPaziente(long idPaziente) throws DatabaseException {

			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idPaziente", idPaziente);
			try {
				List<String> storicoCodiciFiscali = jdbcTemplate.queryForList(SELECT_STORICO_CF_BY_ID_PAZIENTE, namedParameters,String.class);
				return storicoCodiciFiscali;
			} catch (EmptyResultDataAccessException e) {
				return new ArrayList<String>(); 
			} catch (Exception e) {
				String methodName = "SELECT_STORICO_CF_BY_ID_PAZIENTE";
				logError(methodName, e.getMessage());
				throw new DatabaseException(e);
			}	
		}
	
}
