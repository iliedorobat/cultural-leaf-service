package ro.webdata.humanities.server.endpoint.museum;

import ro.webdata.humanities.server.commons.sparql.*;
import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MuseumSparql {
    private static final String AGENT_URI_VAR_NAME = Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true);

    public static String buildDetailsQuery(String uri) {
        Set<SparqlTriple> triples = new HashSet<>() {{
            add(new SparqlTriple("?uri", "?property", "?value"));
        }};
        Set<String> filters = new HashSet<>() {{
            add(String.format("?uri = <%s>", uri));
            add("lang(?value) = \"en\" || lang(?value) = \"\"");
        }};
        SparqlFilterSet filterSet = new SparqlFilterSet(filters);
        SparqlTripleSet tripleSet = new SparqlTripleSet(triples);

        String tripleSetStr = tripleSet.toString();
        String filterSetStr = filterSet.toString();

        return "" +
                "SELECT DISTINCT *" + "\n" +
                "WHERE {" + "\n" +
                    (tripleSetStr != null ? tripleSetStr + " .\n" : "") +
                    (filterSetStr != null ? filterSetStr + "\n" : "") +
                "}" + "\n" +
                "ORDER BY ?property ?value\n";
    }

    public static String buildSummariesQuery() {
        return "" +
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

    public static String buildSummariesQuery(CHOFilter choFilter) {
        String agentCounty = Sparql.getVarName(PROP_KEYS.AGENT_COUNTY, true);
        String agentLatitude = Sparql.getVarName(PROP_KEYS.AGENT_LATITUDE, true);
        String agentLongitude = Sparql.getVarName(PROP_KEYS.AGENT_LONGITUDE, true);
        String agentName = Sparql.getVarName(PROP_KEYS.AGENT_NAME, true);
        String agentType = Sparql.getVarName(PROP_KEYS.AGENT_TYPE, true);
        String choLocation = Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true);
        String eventType = Sparql.getEventType(choFilter);

        TreeSet<String> optionalProps = new TreeSet<>() {{
            add(Sparql.PROPS.get(PROP_KEYS.AGENT_LATITUDE));
            add(Sparql.PROPS.get(PROP_KEYS.AGENT_LONGITUDE));
        }};

        SparqlFilterSet filterSet = new SparqlFilterSet(choFilter);
        SparqlTripleSet tripleSet = new SparqlTripleSet(Sparql.CHO_VAR_NAME, filterSet);
        SparqlOptionalSet optionalSet = new SparqlOptionalSet(
                Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true),
                optionalProps
        );

        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_COUNTY));
        tripleSet.addTriple(AGENT_URI_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.AGENT_NAME));
        tripleSet.addTriple(AGENT_URI_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.AGENT_TYPE));
        tripleSet.addTriple(AGENT_URI_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.AGENT_COUNTY));
        tripleSet.addEventTriples(eventType);

        filterSet.addFilter(
                String.format("lang(%s) = 'en'", agentName)
        );
        filterSet.addFilter(
                String.format("%s = \"Museum\"@en", agentType)
        );

        SparqlPrefixSet prefixSet = new SparqlPrefixSet(tripleSet, optionalSet, filterSet);

        String subjects = String.format(
                "(%s as ?uri) %s %s %s %s %s",
                choLocation,
                agentName,
                agentType,
                agentCounty,
                agentLatitude,
                agentLongitude
        );

        String tripleSetStr = tripleSet.toString();
        String optionalSetStr = optionalSet.toString();
        String filterSetStr = filterSet.toString();

        return prefixSet + "\n\n" +
                "SELECT DISTINCT " + subjects + "\n" +
                "WHERE {\n" +
                    (tripleSetStr != null ? tripleSetStr + " .\n" : "") +
                    (optionalSetStr != null ? optionalSetStr + "\n" : "") +
                    (filterSetStr != null ? filterSetStr + "\n" : "") +
                "}";
    }
}
