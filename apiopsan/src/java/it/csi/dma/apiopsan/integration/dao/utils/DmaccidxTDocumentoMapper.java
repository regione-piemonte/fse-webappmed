/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.DmaccidxTDocumento;

public class DmaccidxTDocumentoMapper implements RowMapper<DmaccidxTDocumento> {
	
	@Override
	public DmaccidxTDocumento mapRow(ResultSet resultSet, int rowNumber)  throws SQLException {
		
		DmaccidxTDocumento result = new DmaccidxTDocumento();
		result.setIdDocumentoIlec(resultSet.getLong("id_documento_ilec"));
		result.setIdEpisodioIlec(resultSet.getLong("id_episodio_ilec"));
		result.setIdPazIrec(resultSet.getLong("id_paz_irec"));
		result.setIdRepositoryCl(resultSet.getString("id_repository_cl"));
		result.setCodiceAziendaSanitaria(resultSet.getString("codice_azienda_sanitaria"));
		result.setCodCl(resultSet.getString("cod_cl"));
		result.setCodiceDocumentoDipartimentale(resultSet.getString("codice_documento_dipartimentale"));
		result.setDataValidazione(resultSet.getDate("data_validazione"));
		result.setFirmatoDigitalmente(resultSet.getString("firmato_digitalmente"));
		result.setPagatoTicket(resultSet.getString("pagato_ticket"));
		result.setCodTipoDocumento(resultSet.getString("cod_tipo_documento"));
		result.setCodTipoFile(resultSet.getString("cod_tipo_file"));

		return result;	
	}

}
