package ro.webdata.humanities.server.endpoint.museum;

import java.util.HashMap;

public class MuseumEndpointQuery {
    public static HashMap<String, String> getMuseumPayload(String uri) {
        HashMap<String, String> payload = new HashMap<>();
        String queryValue = uri != null
                ? String.format(MUSEUM, uri)
                : MUSEUMS;

        payload.put("query", queryValue);

        return payload;
    }

    private static final String MUSEUM = "" +
            "SELECT DISTINCT *\n" +
            "WHERE {\n" +
            "    ?uri ?property ?value .\n" +
            "    FILTER(?uri = <%s>) .\n" +
            "    FILTER(lang(?value) = \"en\" || lang(?value) = \"\")\n" +
            "}\n" +
            "ORDER BY ?property ?value\n";

    private static final String MUSEUMS = "" +
            "PREFIX edm: <http://www.europeana.eu/schemas/edm/>\n" +
            "PREFIX dbo: <https://dbpedia.org/ontology/>\n" +
            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
            "PREFIX dbp: <https://dbpedia.org/property/>\n" +
            "PREFIX odp: <http://opendata.cs.pub.ro/property/>\n" +
            "\n" +
            "SELECT DISTINCT ?uri ?skos_prefLabel ?dbo_type ?dbp_county ?dbp_latitude ?dbp_longitude \n" +
            "WHERE {\n" +
            "    ?uri   a edm:Agent ;\n" +
            "           skos:prefLabel ?skos_prefLabel ;\n" +
            "           dbo:type ?dbo_type ;\n" +
//            "           odp:geolocationType ?geolocationType ;\n" +
            "           dbp:county ?dbp_county .\n" +
            "    OPTIONAL {\n" +
            "        ?uri   dbp:latitude ?dbp_latitude .\n" +
            "        ?uri   dbp:longitude ?dbp_longitude .\n" +
            "    }\n" +
            "    FILTER(?dbo_type = \"Museum\"@en) .\n" +
//            "    FILTER(lang(?geolocationType) = \"en\") .\n" +
            "    FILTER(lang(?skos_prefLabel) = \"en\")\n" +
            "}\n" +
            "ORDER BY ?skos_prefLabel\n";
}
