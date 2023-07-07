/*******************************************************************************
* Copyright Regione Piemonte - 2023
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

/**********************************************
 * CSI PIEMONTE 
 **********************************************/
package it.csi.dma.apiopsan.business.be;

import it.csi.dma.apiopsan.dto.*;


import it.csi.dma.apiopsan.dto.ConteggioDocumenti;
import it.csi.dma.apiopsan.dto.Documento;
import it.csi.dma.apiopsan.dto.DocumentoFr;
import it.csi.dma.apiopsan.dto.ElencoEsenzioni;
import it.csi.dma.apiopsan.dto.Errore;
import it.csi.dma.apiopsan.dto.EsitoGetContattiStrutture;
import it.csi.dma.apiopsan.dto.EsitoGetDiete;
import it.csi.dma.apiopsan.dto.EsitoGetDolori;
import it.csi.dma.apiopsan.dto.EsitoGetEventi;
import it.csi.dma.apiopsan.dto.EsitoGetFarmaci;
import it.csi.dma.apiopsan.dto.EsitoGetRilevazioni;
import it.csi.dma.apiopsan.dto.EsitoGetSintomi;
import it.csi.dma.apiopsan.dto.EsitoGetSoloEpisodi;
import it.csi.dma.apiopsan.dto.EsitoInfoScreening;
import it.csi.dma.apiopsan.dto.EsitoIsDocumentoMediabile;
import it.csi.dma.apiopsan.dto.EsitoIsDocumentoRestituibile;
import it.csi.dma.apiopsan.dto.EsitoUltimoDocumento;
import it.csi.dma.apiopsan.dto.InfoMmgPaziente;
import it.csi.dma.apiopsan.dto.PayloadComunicazioneConsensi;
import it.csi.dma.apiopsan.dto.PayloadGetDocumento;
import it.csi.dma.apiopsan.dto.PayloadGetRilevazioni;
import it.csi.dma.apiopsan.dto.PayloadRecuperaDocumento;
import it.csi.dma.apiopsan.dto.PayloadSearchEpisodi;
import it.csi.dma.apiopsan.dto.PayloadSearchTuttiDoc;
import it.csi.dma.apiopsan.dto.Paziente;
import it.csi.dma.apiopsan.dto.Prestazione;
import it.csi.dma.apiopsan.dto.SintesiDocumento;
import it.csi.dma.apiopsan.dto.StatoConsenso;
import it.csi.dma.apiopsan.dto.Taccuino;

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

@Path("/api/v1/cittadini")




public interface CittadiniApi  {
   
    @POST
    @Path("/{cit_id}/comunicazione-consensi")
    
    @Produces({ "application/json" })

    public Response comunicazioneConsenso(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, PayloadComunicazioneConsensi payloadComunicazioneConsensi,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuini/{taccuino_id}/contatti-strutture")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getContattiStrutture(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/{id_documento_ilec}")
    
    @Produces({ "application/json" })

    public Response getDettaglioDoc(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec, @QueryParam("codice_componente_locale") String codiceComponenteLocale, @QueryParam("categoria") String categoria, @QueryParam("tipo_medico") String tipoMedico,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/{id_documento_ilec}/prestazioni")
    
    @Produces({ "application/json" })

    public Response getDettaglioPrestazioniDoc(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec, @QueryParam("codice_componente_locale") String codiceComponenteLocale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuini/{taccuino_id}/diete")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getDiete(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/{id_documento_ilec}/correlati")
    
    @Produces({ "application/json" })

    public Response getDocumentiCorrelati(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec, @QueryParam("codice_componente_locale") String codiceComponenteLocale, @QueryParam("tipo_correlazione_documento") String tipoCorrelazioneDocumento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/episodi/{id_episodio}/documenti")
    
    @Produces({ "application/json" })

    public Response getDocumentiEpisodio(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_episodio") Integer idEpisodio, @QueryParam("codice_componente_locale") String codiceComponenteLocale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{cit_id}/documento/_search")
    
    @Produces({ "application/json", "application/pdf" })

    public Response getDocumento(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, PayloadGetDocumento payloadGetDocumento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/{id_documento_ilec}/personale")
    
    @Produces({ "application/json", "application/pdf" })

    public Response getDocumentoPersonale(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuini/{taccuino_id}/dolori")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getDolori(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/esenzioni")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getEsenzioni(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuini/{taccuino_id}/eventi")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getEventi(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuini/{taccuino_id}/farmaci")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getFarmaci(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/informativa")
    
    @Produces({ "application/json", "application/pdf" })

    public Response getInformativa(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @QueryParam("identificativo_struttura") String identificativoStruttura, @QueryParam("identificativo_organizzazione") String identificativoOrganizzazione, @QueryParam("id_informativa") String idInformativa,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/info-mmg")
    
    @Produces({ "application/json" })

    public Response getMMGPaziente(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @QueryParam("id_irec") String idIrec, @QueryParam("id_aura") String idAura,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{cit_id}/taccuini/{taccuino_id}/rilevazioni/_search")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getRilevazioni(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, PayloadGetRilevazioni payloadGetRilevazioni, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuini/{taccuino_id}/sintomi")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getSintomi(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("taccuino_id") Long taccuinoId, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset, @QueryParam("ordinamento") String ordinamento, @QueryParam("data_da") String dataDa, @QueryParam("data_a") String dataA,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{cit_id}/episodi/_search")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getSoloEpisodi(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, PayloadSearchEpisodi payloadSearchEpisodi,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/taccuino")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getTaccuino(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{cit_id}/documenti/_search")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response getTuttiDoc(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, PayloadSearchTuttiDoc payloadSearchTuttiDoc,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/ultimo")
    
    @Produces({ "application/json" })

    public Response getUltimoDocumento(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @QueryParam("tipo_documento") String tipoDocumento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/screening")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response infoScreening(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/{id_documento_ilec}/restituibile")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response isDocumentoRestituibile(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec, @QueryParam("codice_componente_locale") String codiceComponenteLocale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/documenti/{id_documento_ilec}/smediazione")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response isDocumentoSmediabile(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec, @QueryParam("codice_componente_locale") String codiceComponenteLocale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @PUT
    @Path("/{cit_id}/documenti/{id_documento_ilec}/mediazione")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })

    public Response mediazioneDocumento(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, @PathParam("id_documento_ilec") Long idDocumentoIlec, @QueryParam("codice_componente_locale") String codiceComponenteLocale,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @POST
    @Path("/{cit_id}/documenti/fo/documento")
    
    @Produces({ "application/json", "application/pdf" })

    public Response recuperaDocumento(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId, PayloadRecuperaDocumento payloadRecuperaDocumento,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/_search")
    
    @Produces({ "application/json" })

    public Response ricercaPaziente(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @QueryParam("cit_id") String citId, @QueryParam("nome") String nome, @QueryParam("cognome") String cognome, @QueryParam("data_nascita") String dataNascita,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
    @GET
    @Path("/{cit_id}/consenso")
    
    @Produces({ "application/json" })

    public Response statoConsenso(@HeaderParam("Shib-Identita-CodiceFiscale") String shibIdentitaCodiceFiscale,@HeaderParam("X-Request-Id") String xRequestId,@HeaderParam("X-Forwarded-For") String xForwardedFor,@HeaderParam("X-Codice-Servizio") String xCodiceServizio,@HeaderParam("X-Codice-Verticale") String xCodiceVerticale,@HeaderParam("Ruolo") String ruolo,@HeaderParam("Regime") String regime,@HeaderParam("Collocazione") String collocazione, @PathParam("cit_id") String citId,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
}
