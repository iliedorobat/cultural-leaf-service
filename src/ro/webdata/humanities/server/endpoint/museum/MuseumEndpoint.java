package ro.webdata.humanities.server.endpoint.museum;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ro.webdata.humanities.server.SyncHttpClient;
import ro.webdata.humanities.server.commons.ENDPOINT;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;
import ro.webdata.humanities.server.endpoint.museum.dto.summaries.MuseumSummaryUtils;
import ro.webdata.humanities.server.endpoint.museum.dto.details.MuseumDetailsUtils;

import java.net.http.HttpResponse;
import java.util.HashMap;

@Path("/museum")
public class MuseumEndpoint {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postMuseum(@QueryParam("uri") String uri, CHOFilter choFilter) {
        if (choFilter != null) {
            return getMuseum(choFilter);
        }

        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, MuseumEndpointQuery.getMuseumPayload(uri));

        int statusCode = response != null
                ? response.statusCode()
                : -1;

        if (statusCode != -1) {
            String body = response.body();
            String output = uri != null
                    ? MuseumDetailsUtils.responseToJson(body)
                    : MuseumSummaryUtils.responseToJson(body);
            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }

    private static Response getMuseum(CHOFilter choFilter) {
        String query = MuseumSparql.buildMuseumsQuery(choFilter);
        HashMap<String, String> payload = new HashMap<>() {{
            put("query", query);
        }};
        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, payload);

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

