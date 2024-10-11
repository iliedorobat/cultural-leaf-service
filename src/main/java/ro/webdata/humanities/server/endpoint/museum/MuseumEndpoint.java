package ro.webdata.humanities.server.endpoint.museum;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ro.webdata.humanities.server.SyncHttpClient;
import ro.webdata.humanities.server.commons.ENDPOINT;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;
import ro.webdata.humanities.server.endpoint.museum.dto.details.MuseumDetailsUtils;
import ro.webdata.humanities.server.endpoint.museum.dto.summaries.MuseumSummaryUtils;

import java.net.http.HttpResponse;

@Path("/museum")
public class MuseumEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCHO(@QueryParam("uri") String museumUri) {
        return getDetails(museumUri);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postMuseum(CHOFilter choFilter) {
        if (choFilter != null) {
            return getSummaries(choFilter);
        }

        return getSummaries(null);
    }

    private static Response getDetails(String museumUri) {
        String query = MuseumSparql.buildDetailsQuery(museumUri);
        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, query);

        int statusCode = response != null
                ? response.statusCode()
                : -1;

        if (statusCode != -1) {
            String body = response.body();
            String output = MuseumDetailsUtils.responseToJson(body);

            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }

    private static Response getSummaries(CHOFilter choFilter) {
        String query = choFilter == null
                ? MuseumSparql.buildSummariesQuery()
                : MuseumSparql.buildSummariesQuery(choFilter);
        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, query);

        int statusCode = response != null
                ? response.statusCode()
                : -1;

        if (statusCode != -1) {
            String body = response.body();
            String output = MuseumSummaryUtils.responseToJson(body);

            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }

//    @Path("/{name}")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getMuseum(@PathParam("name") String name) {
//        return "{\"name\": \"" + name + "\"}";
//    }
}

