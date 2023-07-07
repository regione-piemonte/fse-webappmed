/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.integration.dao.custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import it.csi.dma.apiopsan.dto.custom.Categoria;
import it.csi.dma.apiopsan.dto.TipoDocumento;
import it.csi.dma.apiopsan.exception.DatabaseException;
import it.csi.dma.apiopsan.integration.dao.utils.CategoriaMapper;
import it.csi.dma.apiopsan.integration.dao.utils.TipoDocumentoMapper;
import it.csi.dma.apiopsan.util.LoggerUtil;

@Repository
public class TipoDocumentoCategoriaDao extends LoggerUtil {

	public static final String SELECT_CATEGORIA = "select codice_categoria, descrizione_categoria "
			+ "from dmacc_d_categoria where data_cancellazione is null";

	public static final String SELECT_TIPODOC_BY_CATEGORIA = "SELECT tipo.codice_tipo_doc, "
			+ " tipo.descrizione_tipo_doc " + "FROM dmacc_r_catalogo_tipo_documento_categoria r, "
			+ "	dmacc_d_catalogo_tipo_documento tipo, " + "	dmacc_d_categoria cat "
			+ "WHERE r.id_tipo_documento = tipo.id " + "	AND r.id_categoria = cat.id_categoria "
			+ "	AND r.data_cancellazione is null " + "	AND cat.codice_categoria = :codiceCategoria ";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<TipoDocumento> selectTipoDocumentoByCategoria(String codiceCategoria) throws DatabaseException {
		try {

			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("codiceCategoria",
					codiceCategoria);

			List<TipoDocumento> tipiDocumento = jdbcTemplate.query(SELECT_TIPODOC_BY_CATEGORIA, namedParameters,
					new TipoDocumentoMapper());

			return tipiDocumento;

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectTipoDocumentoCategoria";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}

	public List<Categoria> selectAllCategorie() throws DatabaseException {
		try {
			
			return jdbcTemplate.query(SELECT_CATEGORIA, new CategoriaMapper());

		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			String methodName = "selectAllCategorie";
			logError(methodName, e.getMessage());
			throw new DatabaseException(e);
		}

	}
}
