/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.TipoDocumento;

public class CategoriaTipologiaMapper implements RowMapper<CategoriaTipologia> {

	@Override
    public CategoriaTipologia mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		CategoriaTipologia result = new CategoriaTipologia();
		

		result.setCategoria(resultSet.getString("codice_categoria"));
		result.setTipologia(resultSet.getString("codice_tipo_doc"));
				
		return result;
	}

}
