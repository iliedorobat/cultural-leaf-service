package ro.webdata.humanities.server.endpoint.museum;

import ro.webdata.humanities.server.commons.sparql.*;
import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;

import java.util.TreeSet;

public class MuseumSparql {
    private static final String AGENT_URI_VAR_NAME = Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true);
    private static final String CHO_VAR_NAME = "?cho";

    public static String buildQuery(String uri) {
        if (uri != null) {

        }

        return null;
    }

    public static String buildMuseumsQuery(CHOFilter choFilter) {
        String agentCounty = Sparql.getVarName(PROP_KEYS.AGENT_COUNTY, true);
        String agentLatitude = Sparql.getVarName(PROP_KEYS.AGENT_LATITUDE, true);
        String agentLongitude = Sparql.getVarName(PROP_KEYS.AGENT_LONGITUDE, true);
        String agentName = Sparql.getVarName(PROP_KEYS.AGENT_NAME, true);
        String agentType = Sparql.getVarName(PROP_KEYS.AGENT_TYPE, true);
        String choLocation = Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true);

        TreeSet<String> optionalProps = new TreeSet<>() {{
            add(Sparql.PROPS.get(PROP_KEYS.AGENT_LATITUDE));
            add(Sparql.PROPS.get(PROP_KEYS.AGENT_LONGITUDE));
        }};

        SparqlFilterSet filterSet = new SparqlFilterSet(choFilter);
        SparqlTripleSet tripleSet = new SparqlTripleSet(CHO_VAR_NAME, filterSet);
        SparqlOptionalSet optionalSet = new SparqlOptionalSet(
                Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true),
                optionalProps
        );

        tripleSet.addTriple(CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_COUNTY));
        tripleSet.addTriple(AGENT_URI_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.AGENT_NAME));
        tripleSet.addTriple(AGENT_URI_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.AGENT_TYPE));
        tripleSet.addTriple(AGENT_URI_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.AGENT_COUNTY));

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
