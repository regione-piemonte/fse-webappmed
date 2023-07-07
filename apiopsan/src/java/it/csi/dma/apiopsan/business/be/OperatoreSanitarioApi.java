/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.dma.apiopsan.business.be;

import it.csi.dma.apiopsan.dto.*;


import it.csi.dma.apiopsan.dto.Codice;
import it.csi.dma.apiopsan.dto.Errore;
import it.csi.dma.apiopsan.dto.PayloadSearchMieiReferti;
import it.csi.dma.apiopsan.dto.SintesiDocumento;

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

@Path("/api/v1/operatore-sanitario")




public interface OperatoreSanitarioApi  {
   
    @POST
    @Path("/miei-referti/_search")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getMieiReferti(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, PayloadSearchMieiReferti payloadSearchMieiReferti,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/regimi")
    
    @Produces({ "application/json" })

    public Response getRegimi(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Collocazione") String collocazione,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
