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

import it.csi.dma.apiopsan.dto.Episodio;
import it.csi.dma.apiopsan.dto.Medico;
import it.csi.dma.apiopsan.dto.MetadatiDocumento;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.custom.EpisodioEsteso;
import it.csi.dma.apiopsan.dto.custom.SintesiDocumentoEsteso;


public class SintesiDocumentoEstesoMapper implements RowMapper<SintesiDocumentoEsteso> {

	@Override
    public SintesiDocumentoEsteso mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		SintesiDocumentoEsteso result = new SintesiDocumentoEsteso();
		MetadatiDocumento metadati = new MetadatiDocumento();
		Episodio episodio = new Episodio();
		List<Medico> medico = new ArrayList<Medico>();
		List<String> accessionNumber = new ArrayList<String>();
		
		result.setClasse(resultSet.getString("classe"));
		result.setCategoria(resultSet.getString("categoria"));
		result.setCitId(resultSet.getString("citId"));
		result.setCodiceCl(resultSet.getString("cod_cl"));
		result.setIdDocumentoIlec(resultSet.getLong("id_documento_ilec"));
		result.setNumeroNosologico(resultSet.getString("numero_nosologico"));
		result.setPassaggioPs(resultSet.getString("numero_passaggio_ps"));
		metadati.setAutoreSmediazione(resultSet.getString("autore_smediazione")); 
		metadati.setCoccarda(resultSet.getString("coccarda"));
		metadati.setCodiceAssettoOrganizzativo(resultSet.getString("codice_tipo_assetto_organizzativo"));
		metadati.setCodiceAzienda(resultSet.getString("codice_azienda_sanitaria"));
		metadati.setCodiceDipartimentale(resultSet.getString("codice_dipartimentale"));
		metadati.setCodiceDocumentoDipartimentale(resultSet.getString("codice_documento_dipartimentale"));
		metadati.setCodiceTipoDocumento(resultSet.getString("cod_tipo_documento"));
		metadati.setCodiceTipoDocumentoAlto(resultSet.getString("cod_tipo_documento_alto"));
		metadati.setCodiceTipoFirma(resultSet.getString("cod_tipo_firma"));
		metadati.setDataSmediazione(resultSet.getDate("data_smediazione"));
		metadati.setDataValidazione(resultSet.getDate("data_validazione"));
		metadati.setDescrizioneAssettoOrganizzativo(resultSet.getString("descrizione_tipo_assetto_organizzativo"));
		metadati.setDescrizioneAzienda(resultSet.getString("descrizione_azienda_sanitaria"));
		metadati.setDescrizioneStruttura(resultSet.getString("descrizione_struttura"));
		metadati.setDescrizioneTipoFirma(resultSet.getString("descrizione_tipo_firma"));
		metadati.setDescrizioneUnitaOperativa(resultSet.getString("descrizione_uo"));
		metadati.setFlagAssociazioneFigli(resultSet.getBoolean("flag_associazioni_figli"));
		metadati.setFlagAssociazioniNre(resultSet.getBoolean("flag_associazioni_nre"));
		metadati.setFlagEpisodiCollegati(resultSet.getBoolean("flag_episodi_collegati"));
		metadati.setIdRepositoryCl(resultSet.getString("id_repository_cl"));
		metadati.setImportoTicketDaPagare(resultSet.getString("ticket_da_pagare"));
		metadati.setImportoTicketPagato(resultSet.getString("ticket_pagato"));
		metadati.setLeggiSpeciali(resultSet.getString("leggi_speciali"));
		metadati.setMatricolaUpDipartimentale(resultSet.getInt("matricola_up_dip"));
		metadati.setOscuraScaricoCittadino(resultSet.getString("oscura_scarico_cittadino"));
		metadati.setPagatoTicket(resultSet.getString("pagato_ticket"));
		metadati.setStatoGenerazionePacchetto(resultSet.getString("stato_generazione_pacchetto"));
		metadati.setTipoFile(resultSet.getString("cod_tipo_file"));
		metadati.setDescrizioneTipoDocumento(resultSet.getString("descr_tipo_documento"));
		metadati.setAccessionNumber(accessionNumber);
		episodio.setAziendaAccettazione(resultSet.getString("codice_azienda_sanitaria_acc"));
		episodio.setAziendaDimissione(resultSet.getString("codice_azienda_sanitaria_dim"));
		episodio.setCitId(resultSet.getString("citId"));
		episodio.setCodiceCl(resultSet.getString("cod_cl"));
		episodio.setCodiceTipoEpisodio(resultSet.getString("codice_tipo_episodio"));
		episodio.setDataFine(resultSet.getDate("data_fine"));
		episodio.setDataInizio(resultSet.getDate("data_inizio"));
		episodio.setDescrizioneAziendaAccettazione(resultSet.getString("descrizione_azienda_sanitaria_acc")); 
		episodio.setDescrizioneAziendaDimissione(resultSet.getString("descrizione_azienda_sanitaria_dim"));
		episodio.setDescrizioneTipoEpisodio(resultSet.getString("descrizione_tipo_episodio"));
		episodio.setIdEpisodio(resultSet.getLong("id_episodio_ilec"));
		episodio.setMatricolaAccettazione(resultSet.getString("matricola_up_acc_dip"));
		episodio.setMatricolaDimissione(resultSet.getString("matricola_up_dim_dip"));
		episodio.setStrutturaAccettazione(resultSet.getString("descrizione_struttura_acc"));
		episodio.setStrutturaDimissione(resultSet.getString("descrizione_struttura_dim"));
		episodio.setUnitaOperativaAccettazione(resultSet.getString("descrizione_uo_acc")); 
		episodio.setUnitaOperativaDimissione(resultSet.getString("descrizione_uo_dim"));
		metadati.setMedici(medico);
		metadati.setEpisodio(episodio);
		result.setMetadatiDocumento(metadati);
		return result;
	}


}