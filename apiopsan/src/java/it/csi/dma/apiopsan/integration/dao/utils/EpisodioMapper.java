/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import it.csi.dma.apiopsan.dto.Episodio;


public class EpisodioMapper implements RowMapper<Episodio> {

	@Override
    public Episodio mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Episodio result = new Episodio();
		
		result.setAziendaAccettazione(resultSet.getString("codice_azienda_sanitaria_acc"));
		result.setAziendaDimissione(resultSet.getString("codice_azienda_sanitaria_dim"));
		result.setCitId(resultSet.getString("citId"));
		result.setCodiceCl(resultSet.getString("cod_cl"));
		result.setCodiceTipoEpisodio(resultSet.getString("codice_tipo_episodio"));
		result.setDataFine(resultSet.getDate("data_fine"));
		result.setDataInizio(resultSet.getDate("data_inizio"));
		result.setDescrizioneAziendaAccettazione(resultSet.getString("descrizione_azienda_sanitaria_acc"));
		result.setDescrizioneAziendaDimissione(resultSet.getString("descrizione_azienda_sanitaria_dim"));
		result.setDescrizioneTipoEpisodio(resultSet.getString("descrizione_tipo_episodio"));
		result.setIdEpisodio(resultSet.getLong("id_episodio_ilec"));
		result.setMatricolaAccettazione(resultSet.getString("matricola_up_acc_dip"));
		result.setMatricolaDimissione(resultSet.getString("matricola_up_dim_dip"));
		result.setStrutturaAccettazione(resultSet.getString("descrizione_struttura_acc"));
		result.setStrutturaDimissione(resultSet.getString("descrizione_struttura_dim"));
		result.setUnitaOperativaAccettazione(resultSet.getString("descrizione_uo_acc"));
		result.setUnitaOperativaDimissione(resultSet.getString("descrizione_uo_dim"));
		
		return result;
	}


}