/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.custom.Ruolo;

public class MedicoMapper implements RowMapper<Medico> {

	@Override
    public Medico mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Medico result = new Medico();
		
		result.setCognome(resultSet.getString("cognome_medico"));
		result.setNome(resultSet.getString("nome_medico"));
		result.setCodiceFiscale(resultSet.getString("codice_identificativo_medico"));
		result.setTipoMedico(resultSet.getString("tipo_medico"));
		
		return result;
	}

}
