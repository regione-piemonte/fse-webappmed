/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.dma.apiopsan.util.validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.dto.custom.ErroreDettaglioExt;
import it.csi.dma.apiopsan.exception.DatabaseException;

public interface ValidateGeneric {
	//validate getregimi
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String collocazione,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	//validate getconsensi
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String collocazione, String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	//validate getmmgpaziente
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String regime, String collocazione,
			String citId, String idIrec, String idAura,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	//validate ricercapaziente
	public List<ErroreDettaglioExt> validate(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId, String cognome, String nome,String dataNascita,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
		
	//validate getdocumentiepisodio
	public List<ErroreDettaglioExt> validateDocumentoEpisodio(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId, Integer idEpisodio, String codiceComponenteLocale,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
		
	//validate postsoloepisodi
	public List<ErroreDettaglioExt> validateSoloEpisodi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String regime, String collocazione,
				String citId, PayloadSearchEpisodi payloadSearchEpisodi,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	//validate getTipoDocumento
	public List<ErroreDettaglioExt> validateTipoDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String collocazione,String regime,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	//validate getInformativa
	public List<ErroreDettaglioExt> validateInformativa(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione, String citId, String identificativoStruttura,
				String identificativoOrganizzazione, String idInformativa, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException ;
				
	//validate getDocumentiCorrellati
	public List<ErroreDettaglioExt> validateDocumentiCorrellati(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String regime, String collocazione,
			String citId, Long idDocumentoIlec, String codiceComponenteLocale,String tipoCorrelazioneDocumento,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;

	
	public List<ErroreDettaglioExt> validateRicercaDocumenti(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo,
			String ruoloFse, String regime, String collocazione, String citId,
			PayloadSearchTuttiDoc payloadSearchTuttiDoc, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	public List<ErroreDettaglioExt> validateMieiReferti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String collocazione, String regime, PayloadSearchMieiReferti payloadSearchMieiReferti,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	public List<ErroreDettaglioExt> validateDettaglioDoc(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse, String regime,String collocazione,
		    String citId, Long idDocumentoIlec, String codiceComponenteLocale, String categoria, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	//validate getDocumentiCorrellati
	public List<ErroreDettaglioExt> validateInfoScreening(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
			String regime, String collocazione,
			String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
	
	public List<ErroreDettaglioExt> validateComunicazioneConsensi(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale,Boolean consensoAlimentazione,Boolean consensoConsultazione,Boolean consensoPregresso, String ruolo,String ruoloFse,
			String regime, String collocazione,
			String citId,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest,String identificativoOrganizzazione, String IdentificativoAssistitoConsenso) throws DatabaseException;
	
	// validate getElencoTipoDocumentoCategoria
	public List<ErroreDettaglioExt> validateElencoTipoDocumentoCategoria(String shibIdentitaCodiceFiscale,
			String xRequestId, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException;
	
	//validate getDocumento --> recuperaDocumentoPdf
	public List<ErroreDettaglioExt> validateGetDocumento(String shibIdentitaCodiceFiscale, 
			String xRequestId, String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, 
			String ruolo, String ruoloFse, String regime, String collocazione,
			String citId, PayloadGetDocumento payloadGetDocumento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;

	//validate recuperadocumento
	public List<ErroreDettaglioExt> validateRecuperaDocumento(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse,
				String collocazione,String citId, PayloadRecuperaDocumento payloadRecuperaDocumento,
				SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
		
		//validate sMediazioneDocumenti
	public List<ErroreDettaglioExt> validateMediazioneDocumenti(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
				String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse, String regime, String collocazione,
				String citId, Long idDocumentoIlec, String codiceComponenteLocale, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
    
	//validate RestituibilitaDocumento
	public List<ErroreDettaglioExt> validateRestituibilitaDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String citId, Long idDocumentoIlec, String codiceComponenteLocale,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws DatabaseException;
	
	public List<ErroreDettaglioExt> validatePrenotazioneImmagini(String shibIdentitaCodiceFiscale,String xRequestId,String xForwardedFor,String xCodiceServizio,
				String xCodiceVerticale,String ruolo, String ruoloFse,String regime,String collocazione,ScaricoStudi scaricoStudi,SecurityContext securityContext, 
				HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) throws DatabaseException;
		
		//validate ultimodocumento
	public List<ErroreDettaglioExt> validateUltimoDocumento(String shibIdentitaCodiceFiscale, String xRequestId,
				String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
				String regime, String collocazione, String citId, String tipoDocumento, SecurityContext securityContext,
				HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws DatabaseException;
		
		//validate contattiStrutture
	public List<ErroreDettaglioExt> validateContattiStrutture(String shibIdentitaCodiceFiscale, String xRequestId, String xForwardedFor,
			String xCodiceServizio, String xCodiceVerticale, String ruolo,String ruoloFse, String collocazione,
			String citId,SecurityContext securityContext, HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws DatabaseException;

	// validate ScansionaQRCode
	public List<ErroreDettaglioExt> validateScansionaQRCode(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,
			String regime, String collocazione, String qrcode, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws DatabaseException;

	// validate listaDolori
	public List<ErroreDettaglioExt> validateListaDolori(String shibIdentitaCodiceFiscale, String xRequestId,
			String xForwardedFor, String xCodiceServizio, String xCodiceVerticale, String ruolo, String ruoloFse,String regime,
			String collocazione, String citId, Long taccuinoId, Integer limit, Integer offset, String ordinamento,
			String da, String a, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws DatabaseException;

}
