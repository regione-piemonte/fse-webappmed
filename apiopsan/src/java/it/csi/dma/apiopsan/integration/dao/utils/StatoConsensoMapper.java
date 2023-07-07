/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.StatoConsenso;


public class StatoConsensoMapper implements RowMapper<StatoConsenso> {

	@Override
    public StatoConsenso mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		StatoConsenso result = new StatoConsenso();
		
		result.setConsensoAlimentazione(resultSet.getBoolean("consenso_alimentazione"));
		result.setConsensoConsultazione(resultSet.getBoolean("consenso_consultazione"));
		result.setConsensoPregresso(resultSet.getBoolean("consenso_pregresso"));
		result.setIdentificativoAssistitoConsenso(resultSet.getString("identificativo_assistito_consenso"));
		result.setIdentificativoInformativaConsensi(resultSet.getString("identificativo_informativa_consensi"));
		result.setIdentificativoInformativaCorrente(resultSet.getString("identificativo_informativa_corrente"));
		
		return result;
	}

}
