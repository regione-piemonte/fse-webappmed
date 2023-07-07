/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.ServizioComponenteLocale;


public class ServizioComponenteLocaleMapper implements RowMapper<ServizioComponenteLocale> {

	@Override
    public ServizioComponenteLocale mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		ServizioComponenteLocale result = new ServizioComponenteLocale();
		result.setClCodice(resultSet.getString("cl_codice"));
		result.setClDescrizione(resultSet.getString("cl_descrizione"));
		result.setClTipo(resultSet.getString("cl_tipo"));
		result.setServizioCodice(resultSet.getString("servizio_codice"));
		result.setServizioDescrizione(resultSet.getString("servizio_descrizione"));
		result.setServizioUrl(resultSet.getString("servizio_url"));
		result.setOperazioneCodice(resultSet.getString("operazione_codice"));
		result.setOperazioneDescrizione(resultSet.getString("operazione_descrizione"));
		result.setTimeOutMaxElaborazione(resultSet.getString("timeout_max_elaborazione"));
		return result;
	}

}







