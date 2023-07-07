/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.dma.apiopsan.business.be;

import it.csi.dma.apiopsan.dto.*;


import it.csi.dma.apiopsan.dto.Errore;
import it.csi.dma.apiopsan.dto.ScaricoStudi;
import it.csi.dma.apiopsan.dto.VerificaStatoPacchetto;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/api/v1/referti")




public interface RefertiApi  {
   
    @POST
    @Path("/prenotazione-pacchetto-immagini/")
    
    @Produces({ "application/json" })

    public Response prenotaPacchettoImmagini(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, ScaricoStudi scaricoStudi,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/qrcode")
    
    @Produces({ "application/json" })

    public Response scansionaQRCode(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @QueryParam("qrcode") String qrcode,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/verifica-stato-pacchetto/")
    
    @Produces({ "application/json" })

    public Response verificaStatoPacchetto(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @QueryParam("codice_fiscale") String codiceFiscale, @QueryParam("id_documento_ilec") String idDocumentoIlec, @QueryParam("cod_cl") String codCl, @QueryParam("cod_documento_dipartimentale") String codDocumentoDipartimentale, @QueryParam("archivio_documento_ilec") String archivioDocumentoIlec,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
