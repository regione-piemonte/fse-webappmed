/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.CredenzialiServizio;


public class CredenzialiServizioMapper implements RowMapper<CredenzialiServizio> {

	@Override
    public CredenzialiServizio mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		CredenzialiServizio result = new CredenzialiServizio();
		
		result.setUsername(resultSet.getString("username"));
		result.setCodiceServizio(resultSet.getString("codiceServizio"));
		result.setPassword(resultSet.getString("password"));
		
		
		return result;
	}

}
