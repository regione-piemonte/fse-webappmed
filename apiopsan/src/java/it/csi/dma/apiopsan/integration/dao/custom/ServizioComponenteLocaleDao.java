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

import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.ServizioComponenteLocaleMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class ServizioComponenteLocaleDao extends LoggerUtil {

	private static final String SELECT_SERVIZIO_COMPONENTE_LOCALE = "select "
			+ "	cl.codice as cl_codice, "
			+ "	cl.descrizione cl_descrizione, "
			+ "	cl.tipo_componente_locale cl_tipo, "
			+ "	s.codice servizio_codice, "
			+ "	s.descrizione servizio_descrizione, "
			+ "	s.url_servizio servizio_url, "
			+ "	dos.codice operazione_codice, "
			+ "	dos.descrizione operazione_descrizione, "
			+ "	i.timeout_max_elaborazione "
			+ "from dmacc_t_componente_locale cl "
			+ "	join dmacc_r_comp_locale_servizio_comp_locale r1 on r1.id_componente_locale = cl.id_componente_locale "
			+ "	join dmacc_d_servizio_componente_locale s on s.id = r1.id_servizio_componente_locale "
			+ "	join dmacc_r_serv_comp_loc_operazione_serv_comp_loc r2 on r2.id_serv_comp_loc = s.id "
			+ "	join dmacc_d_operazione_servizio_componente_locale dos on dos.id = r2.id_operazione_serv_comp_loc "
			+ "	join dmacc_r_info_servizio_componente_locale i "
			+ "		on (i.id_operazione_serv_comp_loc = dos.id and i.id_componente_locale = cl.id_componente_locale) "
			+ "where  "
			+ " dos.codice = :codiceOperazioneServizio "
			+ " and s.codice = :codiceServizio "
			+ " and cl.codice = :codiceComponenteLocale "
			+ " and now() >= cl.data_inserimento and now() >= s.data_inserimento and now() >= dos.data_inserimento  "
			+ "order by dos.data_inserimento desc, s.data_inserimento desc, cl.data_inserimento desc  "
			+ "limit 1";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ServizioComponenteLocale selectServizioByClAndServizioAndOperazione(String codiceComponenteLocale, String codiceServizio ,String codiceOperazioneServizio) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("codiceComponenteLocale", codiceComponenteLocale)
				.addValue("codiceServizio",codiceServizio)
				.addValue("codiceOperazioneServizio", codiceOperazioneServizio);
		try {
			ServizioComponenteLocale servizioComponenteLocale = jdbcTemplate.queryForObject(SELECT_SERVIZIO_COMPONENTE_LOCALE, namedParameters,new ServizioComponenteLocaleMapper());
			return servizioComponenteLocale;
		} catch (EmptyResultDataAccessException e) {
			return null; 
		} catch (Exception e) {
			String methodName = "SELECT_SERVIZIO_COMPONENTE_LOCALE";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);

		}
	}

}
