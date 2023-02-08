package ro.webdata.humanities.server;

import ro.webdata.humanities.server.commons.ENDPOINT;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SyncHttpClient {
    private static final HttpClient client = HttpClient.newHttpClient();

    // https://mkyong.com/java/java-11-httpclient-examples/
    public static HttpResponse<String> get(String link) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(link))
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("The current request is canceled, but the process continues...");
            return null;
        }
    }

    public static HttpResponse<String> post(String link, String query) {
        HashMap<String, String> payload = new HashMap<>() {{
            put("query", query);
        }};
        return SyncHttpClient.post(ENDPOINT.SPARQL, payload);
    }

    // https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
    public static HttpResponse<String> post(String link, HashMap<String, String> payload) {
        System.out.println("link: " + link);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(payload))
                .uri(URI.create(link))
                .header("Accept", "application/x-sparqlstar-results+json, application/sparql-results+json;q=0.9, */*;q=0.8")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST")
                .header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("The current request is canceled, but the process continues...");
            return null;
        }
    }

    public static int getStatusCode(HttpResponse<String> response) {
        return response != null
                ? response.statusCode()
                : -1;
    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(HashMap<String, String> data) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
