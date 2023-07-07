/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.ComponenteLocale;


public class ComponenteLocaleMapper implements RowMapper<ComponenteLocale> {

	@Override
    public ComponenteLocale mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		ComponenteLocale result = new ComponenteLocale();
		result.setDescrizione(resultSet.getString("descrizione"));
		result.setTipoComponenteLocale(resultSet.getString("tipo_componente_locale"));
		result.setCodice(resultSet.getString("codice"));
		result.setIdRepository(resultSet.getString("id_repository"));
		result.setCodiceUo(resultSet.getString("codice_uo"));
		result.setIdComponenteLocale(resultSet.getLong("id_componente_locale"));
		result.setIdAziendaSanitaria(resultSet.getLong("id_azienda_sanitaria"));
		return result;
	}

}







