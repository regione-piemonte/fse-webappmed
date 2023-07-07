/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.Categoria;

public class CategoriaMapper implements RowMapper<Categoria> {

	@Override
	public Categoria mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

		Categoria result = new Categoria();
		result.setCodiceCategoria(resultSet.getString("codice_categoria"));
		result.setDescrizioneCategoria(resultSet.getString("descrizione_categoria"));

		return result;

	}

}
