/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.Ruolo;

public class RuoloMapper implements RowMapper<Ruolo> {

	@Override
    public Ruolo mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Ruolo result = new Ruolo();
		
		result.setId(resultSet.getInt("id"));
		result.setDataInserimento(resultSet.getDate("data_inserimento"));
		result.setRuoloDpcm(resultSet.getString("ruolo_dpcm"));
		result.setCategoriaRuolo(resultSet.getString("categoria_ruolo"));
		
		result.setDescrizioneRuoloIni(resultSet.getString("descrizione_ruolo_ini"));
		result.setDescrizioneRuolo(resultSet.getString("descrizione_ruolo"));
		
		result.setCodiceRuolo(resultSet.getString("codice_ruolo"));
		result.setCodiceRuoloIni(resultSet.getString("codice_ruolo_ini"));
		
		result.setDataInserimento(resultSet.getDate("dataaggiornamento"));
		result.setFlagVisibilePerConsenso(resultSet.getString("flag_visibile_per_consenso"));
				
		return result;
	}

}
