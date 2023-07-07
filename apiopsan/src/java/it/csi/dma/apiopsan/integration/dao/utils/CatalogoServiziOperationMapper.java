/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.CatalogoServiziOperation;


public class CatalogoServiziOperationMapper implements RowMapper<CatalogoServiziOperation> {

	@Override
    public CatalogoServiziOperation mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		CatalogoServiziOperation result = new CatalogoServiziOperation();
		result.setCodiceServizio(resultSet.getString("codice_servizio"));
		result.setNomeServizio(resultSet.getString("nome_servizio"));
		return result;
	}

}
