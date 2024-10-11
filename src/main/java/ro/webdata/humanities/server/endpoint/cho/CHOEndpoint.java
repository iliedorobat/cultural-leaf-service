package ro.webdata.humanities.server.endpoint.cho;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ro.webdata.humanities.server.SyncHttpClient;
import ro.webdata.humanities.server.commons.Const;
import ro.webdata.humanities.server.commons.ENDPOINT;
import ro.webdata.humanities.server.endpoint.cho.dto.details.CHODetailsUtils;
import ro.webdata.humanities.server.endpoint.cho.dto.stats.CHOTimespanStatsUtils;
import ro.webdata.humanities.server.endpoint.cho.dto.summaries.CHOSummaryUtils;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOStatsFilter;

import java.net.http.HttpResponse;

@Path("/cho")
public class CHOEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCHO(@QueryParam("uri") String choUri) {
        return getDetails(choUri);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postCHO(@QueryParam("aggr") String aggr, CHOFilter choFilter) {
        if (aggr != null && aggr.equals("count")) {
            return getCounter(choFilter, aggr);
        }

        return getSummaries(choFilter);
    }

    @Path("/stats")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postCHO(@QueryParam("eventType") String eventType, @QueryParam("timespanType") String timespanType, CHOStatsFilter choStatsFilter) {
        return getTimespanStats(choStatsFilter, eventType, timespanType);
    }

    private static Response getCounter(CHOFilter choFilter, String aggr) {
        String query = CHOSparql.buildCounterQuery(choFilter, aggr);
        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, query);

        int statusCode = response != null
                ? response.statusCode()
                : -1;

        if (statusCode != -1) {
            String output = CHOUtils.responseToJson(response.body());
            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }

    private static Response getDetails(String choUri) {
        String query = CHOSparql.buildDetailsQuery(choUri);
        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, query);

        int statusCode = response != null
                ? response.statusCode()
                : -1;

        if (statusCode != -1) {
            String output = CHODetailsUtils.responseToJson(response.body());
            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }

    private static Response getSummaries(CHOFilter choFilter) {
        String query = CHOSparql.buildSummaryQuery(choFilter);
        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, query);

        int statusCode = response != null
                ? response.statusCode()
                : -1;

        if (statusCode != -1) {
            String output = CHOSummaryUtils.responseToJson(response.body());
            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }




    private static Response getTimespanStats(CHOStatsFilter choStatsFilter, String eventType, String timespanType) {
        String query = CHOStatsSparql.buildTimespanCounterQuery(choStatsFilter, eventType, timespanType, false, 10);
        String counterQuery = CHOStatsSparql.buildTimespanCounterQuery(choStatsFilter, eventType, timespanType, true, -1);

        HttpResponse<String> response = SyncHttpClient.post(ENDPOINT.SPARQL, query);
        HttpResponse<String> counterResponse = SyncHttpClient.post(ENDPOINT.SPARQL, counterQuery);

        int statusCode = response != null ? response.statusCode() : -1;
        int counterStatusCode = response != null ? response.statusCode() : -1;

        if (statusCode != -1 && counterStatusCode != -1) {
            String output = CHOTimespanStatsUtils.responseToJson(response.body(), counterResponse.body(), eventType, timespanType);
            return Response.ok().entity(output).build();
        }

        return Response.status(statusCode).build();
    }
}
