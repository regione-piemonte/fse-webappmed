/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Prestazione;

public class PrestazioneMapper implements RowMapper<Prestazione> {

	@Override
	public Prestazione mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Prestazione result = new Prestazione();	

		result.setCodice(resultSet.getString("codice"));
		result.setDescrizione(resultSet.getString("descrizione"));
		result.setDataPrestazione(resultSet.getDate("data_prestazione"));
		result.setId(resultSet.getLong("id"));
		
		Codice branca = new Codice();
		branca.setCodice(resultSet.getString("codice_branca"));
		branca.setDescrizione(resultSet.getString("descrizione_branca"));
		
		result.setBranca(branca);
		
		return result;
	}

}
