/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.dma.apiopsan.dto.custom.RuoloPua;

public class RuoloPuaMapper implements RowMapper<RuoloPua> {

	@Override
    public RuoloPua mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		RuoloPua result = new RuoloPua();
		
		result.setId(resultSet.getInt("id"));
		result.setDataInserimento(resultSet.getDate("data_inserimento"));
		result.setSol(resultSet.getString("sol"));
		
		result.setDescrizioneRuoloPua(resultSet.getString("descrizione_ruolo_pua"));
		result.setDescrizioneRuoloFse(resultSet.getString("descrizione_ruolo_fse"));
		
		result.setCodiceRuoloFse(resultSet.getString("codice_ruolo_fse"));
		result.setCodiceRuoloPua(resultSet.getString("codice_ruolo_pua"));
				
		return result;
	}

}
