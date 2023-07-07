/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.CategoriaTipologia;
import it.csi.dma.apiopsan.dto.TipoDocumento;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.CategoriaTipologiaMapper;
import it.csi.dma.apiopsan.integration.dao.utils.TipoDocumentoMapper;
import it.csi.dma.apiopsan.util.Constants;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class TipoDocumentoDao extends LoggerUtil {

	public static final String SELECT_TIPO_DOC = "SELECT " 
											+ "codice_tipo_doc,descrizione_tipo_doc " 
											+ "FROM " 
											+ "dmacc_d_catalogo_tipo_documento "
											+ "WHERE " 
											+ "codice_tipo_doc = :tipodoc";
	
	public static final String SELECT_ALL_TIPO_DOC = "SELECT " 
			+ "codice_tipo_doc,descrizione_tipo_doc " 
			+ "FROM " 
			+ "dmacc_d_catalogo_tipo_documento";
	
	
	public static final String SELECT_ALL_TIPO_CATEGORIA_DOC = "SELECT " 
			+ "codice_tipo_doc,codice_categoria " 
			+ "FROM " 
			+ "dmacc_d_catalogo_categoria_tipo_documento  "
			;
	
	public static final String SELECT_TIPO_CATEGORIA_DOC = "SELECT " 
			+ "codice_tipo_doc,codice_categoria " 
			+ "FROM " 
			+ "dmacc_d_catalogo_categoria_tipo_documento "
			+ "WHERE " 
			+ "codice_tipo_doc = :tipodoc AND codice_categoria = :codice_categoria ";
	

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public TipoDocumento selectTipoDocumento(String tipodoc) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("tipodoc", tipodoc);

		try {
			TipoDocumento selected = jdbcTemplate.queryForObject(SELECT_TIPO_DOC, namedParameters, new TipoDocumentoMapper());
			return selected;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectErroreDescFromErroreCod";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public List<TipoDocumento> selectAllTipoDocumento() throws DatabaseException {
		try {
			List<TipoDocumento> documenti = new ArrayList<TipoDocumento>();
			List<TipoDocumento> documentiucase = new ArrayList<TipoDocumento>();
			documenti = jdbcTemplate.query(SELECT_ALL_TIPO_DOC,  new TipoDocumentoMapper());
			for (TipoDocumento documento : documenti) {
				if (documento.getCodice().equalsIgnoreCase(Constants.SCHEDA_VACCINALE_REGIONALE)) {
					documento.setDescrizione(documento.getDescrizione().substring(0, 1).toUpperCase() + documento.getDescrizione().substring(1).toLowerCase() + " (codice regionale)");
				}
				else {
					documento.setDescrizione(documento.getDescrizione().substring(0, 1).toUpperCase() + documento.getDescrizione().substring(1).toLowerCase());
				}
				documentiucase.add(documento);
			}
			return documentiucase;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = this.getClass().getEnclosingMethod().getName();
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public List<CategoriaTipologia> selectAllCategoriaTipologiaDocumento() throws DatabaseException {
		try {
			
			return jdbcTemplate.query(SELECT_ALL_TIPO_CATEGORIA_DOC,  new CategoriaTipologiaMapper());
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = this.getClass().getEnclosingMethod().getName();
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	public CategoriaTipologia selectCategoriaTipoDocumento(String categoria, String tipodoc) throws DatabaseException {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("tipodoc", tipodoc)
				.addValue("codice_categoria", categoria);

		try {
			CategoriaTipologia selected = jdbcTemplate.queryForObject(SELECT_TIPO_CATEGORIA_DOC, namedParameters, new CategoriaTipologiaMapper());
			return selected;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectCategoriaTipoDocumento";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
	
	
}
