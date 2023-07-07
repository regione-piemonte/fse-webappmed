/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.TipoDocumento;

public class TipoDocumentoMapper implements RowMapper<TipoDocumento> {

	@Override
    public TipoDocumento mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		TipoDocumento result = new TipoDocumento();
		

		result.setCodice(resultSet.getString("codice_tipo_doc"));
		result.setDescrizione(resultSet.getString("descrizione_tipo_doc"));
				
		return result;
	}

}
