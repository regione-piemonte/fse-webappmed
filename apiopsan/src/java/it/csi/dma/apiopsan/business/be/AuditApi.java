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
import it.csi.dma.apiopsan.dto.PayloadSetAudit;

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

@Path("/api/v1/audit")




public interface AuditApi  {
   
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response setAuditOpSan(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, PayloadSetAudit payloadSetAudit,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
