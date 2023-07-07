/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.MetadatiDocumento;
import it.csi.dma.apiopsan.dto.SintesiDocumento;



public class SintesiDocumentoDettaglioDocMapper implements RowMapper<SintesiDocumento> {

	@Override
    public SintesiDocumento mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		SintesiDocumento result = new SintesiDocumento();
		MetadatiDocumento metadati = new MetadatiDocumento();
		
		List<String> accessionNumber = new ArrayList<String>();
		List<String> nre = new ArrayList<String>();
		List<Medico> listamedici = new ArrayList<Medico>();
		Medico medico = new Medico();
		medico.setCognome(resultSet.getString("medico"));
		listamedici.add(medico);
		result.setIdDocumentoIlec(resultSet.getLong("id_documento_ilec"));
		result.setRecuperoPregresso(resultSet.getString("recupero_pregresso"));
		metadati.setCoccarda(resultSet.getString("coccarda"));
		metadati.setDescrizioneAssettoOrganizzativo(resultSet.getString("descrizione_tipo_assetto_organizzativo"));
		metadati.setDescrizioneUnitaOperativa(resultSet.getString("descrizione_uo"));
		metadati.setAccessionNumber(accessionNumber);
		metadati.setNre(nre);
		metadati.setDataValidazione(resultSet.getDate("data_validazione"));
		metadati.setDescrizioneAzienda(resultSet.getString("descrizione_azienda_sanitaria"));
		metadati.setDescrizioneStruttura(resultSet.getString("descrizione_struttura"));
		metadati.setDataSmediazione(resultSet.getDate("data_smediazione"));
		metadati.setAutoreSmediazione(resultSet.getString("autore_smediazione"));
		//metadati.setDataE(resultSet.getDate("data_emissione"));
		metadati.setMedici(listamedici);
		result.setMetadatiDocumento(metadati);
		return result;
	}


}