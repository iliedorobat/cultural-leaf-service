package ro.webdata.humanities.server.endpoint.cho;

import ro.webdata.humanities.server.commons.Const;
import ro.webdata.humanities.server.commons.sparql.Sparql;
import ro.webdata.humanities.server.commons.sparql.SparqlFilterSet;
import ro.webdata.humanities.server.commons.sparql.SparqlPrefixSet;
import ro.webdata.humanities.server.commons.sparql.SparqlTripleSet;
import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOStatsFilter;

public class CHOStatsSparql {
    public static String buildTimespanCounterQuery(CHOStatsFilter choStatsFilter, String eventType, String timeRange, boolean countAll, int limit) {
        String eventAge = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.EVENT_AGE));

        SparqlFilterSet filterSet = new SparqlFilterSet(choStatsFilter);
        SparqlTripleSet tripleSet = new SparqlTripleSet(Sparql.CHO_VAR_NAME, filterSet);

        if (timeRange.equals(Const.TIMESPAN_CENTURY)) {
            filterSet.addFilter(
                    String.format("contains(str(%s), \"century\")", eventAge)
            );
        } else if (timeRange.equals(Const.TIMESPAN_MILLENNIUM)) {
            filterSet.addFilter(
                    String.format("contains(str(%s), \"millennium\")", eventAge)
            );
        } else {
            filterSet.addFilter(
                    String.format("!contains(str(%s), \"century\") && !contains(str(%s), \"millennium\")", eventAge, eventAge)
            );
        }

        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.RDF_TYPE), "edm:ProvidedCHO");
        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_EVENT), Sparql.EVENT_VAR_NAME);
        tripleSet.addTriple(Sparql.EVENT_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.RDF_TYPE), "edm:Event");
        tripleSet.addTriple(Sparql.EVENT_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.EVENT_TYPE), String.format("\"%s\"@en", eventType));
        tripleSet.addTriple(Sparql.EVENT_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.EVENT_AGE), eventAge);

        SparqlPrefixSet prefixSet = new SparqlPrefixSet(tripleSet, filterSet);

        String tripleSetStr = tripleSet.toString();
        String filterSetStr = filterSet.toString();

        if (countAll) {
            return prefixSet + "\n\n" +
                    "SELECT " + Sparql.getAggrSubjectConstruction(eventAge, "count") + "\n" +
                    "WHERE {\n" +
                        (tripleSetStr != null ? tripleSetStr + " .\n" : "") +
                        (filterSetStr != null ? filterSetStr + "\n" : "") +
                    "}";
        }

        String query = prefixSet + "\n\n" +
                "SELECT " + eventAge + " " + Sparql.getAggrSubjectConstruction(eventAge, "count") + "\n" +
                "WHERE {\n" +
                    (tripleSetStr != null ? tripleSetStr + " .\n" : "") +
                    (filterSetStr != null ? filterSetStr + "\n" : "") +
                "}\n" +
                "GROUP BY " + eventAge + "\n" +
                "ORDER BY DESC(" + Sparql.getAggrSubject(eventAge, "count") + ")";

        if (limit > 0) {
            query += "\n" + "LIMIT " + limit;
        }

        return query;
    }
}
