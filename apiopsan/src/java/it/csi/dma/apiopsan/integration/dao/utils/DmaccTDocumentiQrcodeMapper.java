/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.custom.DmaccTDocumentiQrcode;

public class DmaccTDocumentiQrcodeMapper implements RowMapper<DmaccTDocumentiQrcode> {
	
	@Override
	public DmaccTDocumentiQrcode mapRow(ResultSet resultSet, int rowNumber)  throws SQLException {
		
		DmaccTDocumentiQrcode result = new DmaccTDocumentiQrcode();
		result.setId(resultSet.getLong("id"));
		result.setIdPaziente(resultSet.getLong("id_paziente"));
		result.setCodiceFiscale(resultSet.getString("codice_fiscale"));
		result.setIdComponenteLocale(resultSet.getLong("id_componente_locale"));
		result.setCodCl(resultSet.getString("cod_cl"));
		result.setIdReferto(resultSet.getLong("id_referto"));
		result.setIdDocumento(resultSet.getLong("id_documento"));
		result.setIdEpisodio(resultSet.getLong("id_episodio"));
		result.setCodiceDocumentoDipartimentale(resultSet.getString("codice_documento_dipartimentale"));
		result.setToken(resultSet.getString("qrcode_token"));
		result.setDataAggiornamento(resultSet.getDate("data_inserimento"));
		result.setDataInserimento(resultSet.getDate("data_aggiornamento"));
		return result;	
	}

}
