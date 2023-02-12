package ro.webdata.humanities.server.commons.sparql;

import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;

import java.util.HashMap;

public class Sparql {
    public static final HashMap<String, String> PROPS = new HashMap<>() {{
        put(PROP_KEYS.AGENT_COUNTY, "dbp:county");
        put(PROP_KEYS.AGENT_LATITUDE, "dbp:latitude");
        put(PROP_KEYS.AGENT_LONGITUDE, "dbp:longitude");
        put(PROP_KEYS.AGENT_NAME, "skos:prefLabel");
        put(PROP_KEYS.AGENT_TYPE, "dbo:type");

        put(PROP_KEYS.CHO_COUNTY, "edm:currentLocation");
        put(PROP_KEYS.CHO_CREATION_TIME, "dcterms:created"); // TODO: check dcterms:issued
        put(PROP_KEYS.CHO_DISPLAY_STATE, "odp:displayState");
        put(PROP_KEYS.CHO_EPOCH, null); // TODO:
        put(PROP_KEYS.CHO_EVENT, "edm:wasPresentAt");
        put(PROP_KEYS.CHO_FOUND_TIME, "odp:found");
        put(PROP_KEYS.CHO_INVENTORY_NUMBER, "odp:inventoryNumber");
        put(PROP_KEYS.CHO_LOCALITY, null); // TODO:
        put(PROP_KEYS.CHO_LOCATION, "edm:currentLocation");
        put(PROP_KEYS.CHO_OVERALL_DESCR, "odp:overallDescription");
        put(PROP_KEYS.CHO_TITLE, "dc:title");
        put(PROP_KEYS.CHO_TYPE, "dc:type");

        put(PROP_KEYS.EVENT_AGE, "edm:occuredAt");
        put(PROP_KEYS.EVENT_TYPE, "edm:hasType");

        put(PROP_KEYS.MEDAL_SHAPE, "odp:form");

        put(PROP_KEYS.NATURE_AGE, "odp:age");
        put(PROP_KEYS.NATURE_EPOCH, "odp:geologicalPeriod");
        put(PROP_KEYS.NATURE_SEX, "dbo:sex");

        put(PROP_KEYS.RDF_TYPE, "rdf:type");
    }};

    public static String getVarName(String sparqlValue) {
        if (sparqlValue == null) {
            return null;
        }

        String varName = sparqlValue.replaceAll(":", "_");

        return "?" + varName;
    }

    public static String varNameToPredicate(String varName) {
        return varName.substring(1)
                .replaceFirst("_", ":");
    }

    private static String getVarName2(String subject) {
        if (subject == null) {
            return null;
        }

        return "?" + subject.replaceAll(":", "_");
    }

    public static String getVarName(String propKey, boolean isPropKey) {
        if (isPropKey) {
            return getVarName2(PROPS.get(propKey));
        }
        return getVarName2(propKey);
    }

    public static String getAggrSubject(String subject, String aggr) {
        return subject + "_" + aggr;
    }

    public static String getAggrSubjectConstruction(String subject, String aggr) {
        return "(" + aggr + "(" + subject + ") as " + getAggrSubject(subject, aggr) + ")";
    }

    public static String getAggrMinSubjectConstruction(String subject) {
        return getAggrSubjectConstruction(subject, "min");
    }
}
