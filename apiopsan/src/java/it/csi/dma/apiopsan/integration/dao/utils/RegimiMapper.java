/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.Codice;

public class RegimiMapper implements RowMapper<Codice> {

	@Override
    public Codice mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Codice result = new Codice();
		
		result.setCodice(resultSet.getString("codice_regime_operativita"));
		result.setDescrizione(resultSet.getString("descrizione_regime_operativita"));
		
				
		return result;
	}

}
