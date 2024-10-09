package ro.webdata.humanities.server.endpoint.cho;

import ro.webdata.humanities.server.commons.sparql.*;
import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;

import java.util.TreeSet;

public class CHOSparql {
    public static String buildCounterQuery(CHOFilter choFilter, String aggr) {
        SparqlFilterSet filterSet = new SparqlFilterSet(choFilter);
        SparqlTripleSet tripleSet = new SparqlTripleSet(Sparql.CHO_VAR_NAME, filterSet);

        if (aggr != null && aggr.equals("count")) {
            String eventType = Sparql.getEventType(choFilter);
            tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.RDF_TYPE), "edm:ProvidedCHO");
            tripleSet.addEventTriples(eventType);

            SparqlPrefixSet prefixSet = new SparqlPrefixSet(tripleSet, filterSet);

            String tripleSetStr = tripleSet.toString("\t\t");
            String filterSetStr = filterSet.toString("\t\t");

            String subSelect = "" +
                    "\tSELECT DISTINCT " + Sparql.CHO_VAR_NAME + "\n" +
                    "\tWHERE {\n" +
                        (tripleSetStr != null ? tripleSetStr + "\n" : "") +
                        (filterSetStr != null ? filterSetStr + "\n" : "") +
                    "\t}";

            return prefixSet + "\n\n" +
                    // TODO: use Sparql.getAggrSubjectConstruction
                    "SELECT " + "(count(" + Sparql.CHO_VAR_NAME + ") as ?count)" + "\n" +
                    "WHERE {\n" +
                        subSelect + "\n" +
                    "}";
        }

        return null;
    }

    public static String buildSummaryQuery(CHOFilter choFilter) {
        String choInventoryNumber = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.CHO_INVENTORY_NUMBER));
        String choLocation = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.CHO_LOCATION));
        String choOverallDescr = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.CHO_OVERALL_DESCR));
        String choTitle = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.CHO_TITLE));
        String choType = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.CHO_TYPE));
        String eventType = Sparql.getEventType(choFilter);

        SparqlFilterSet filterSet = new SparqlFilterSet(choFilter);
        SparqlTripleSet tripleSet = new SparqlTripleSet(Sparql.CHO_VAR_NAME, filterSet);

        filterSet.addFilter(
                String.format("lang(%s) = 'ro'", choTitle)
        );
        filterSet.addFilter(
                String.format("lang(%s) = 'en'", choType)
        );

        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.RDF_TYPE), "edm:ProvidedCHO");
        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_TITLE));
        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_TYPE));
        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_LOCATION));
        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_INVENTORY_NUMBER));
        tripleSet.addEventTriples(eventType);

        SparqlFilterSet optionalFilterSet = new SparqlFilterSet(
                String.format("lang(%s) = 'ro'", choOverallDescr)
        );
        TreeSet<String> optionalProps = new TreeSet<>() {{
            add(Sparql.PROPS.get(PROP_KEYS.CHO_OVERALL_DESCR));
        }};

        SparqlOptionalSet optionalSet = new SparqlOptionalSet(Sparql.CHO_VAR_NAME, optionalProps, optionalFilterSet);
        SparqlPrefixSet prefixSet = new SparqlPrefixSet(tripleSet, optionalSet, filterSet);

        String tripleSetStr = tripleSet.toString();
        String optionalSetStr = optionalSet.toString();
        String filterSetStr = filterSet.toString();

        return prefixSet + "\n\n" +
                "SELECT DISTINCT " +
                    Sparql.CHO_VAR_NAME +
                    " " + Sparql.getAggrMinSubjectConstruction(choTitle) +
                    " " + Sparql.getAggrMinSubjectConstruction(choInventoryNumber) +
                    " " + Sparql.getAggrMinSubjectConstruction(choType) +
                    " " + Sparql.getAggrMinSubjectConstruction(choLocation) +
                    " " + Sparql.getAggrMinSubjectConstruction(choOverallDescr) + "\n" +
                "WHERE {\n" +
                    (tripleSetStr != null ? tripleSetStr + " .\n" : "") +
                    (optionalSetStr != null ? optionalSetStr + " .\n" : "") +
                    (filterSetStr != null ? filterSetStr + "\n" : "") +
                "}\n" +
                "GROUP BY " + Sparql.CHO_VAR_NAME;
    }

    public static String buildDetailsQuery(String choUri) {
        SparqlFilterSet filterSet = new SparqlFilterSet(
                String.format("%s = <%s>", Sparql.CHO_VAR_NAME, choUri)
        );
        SparqlTripleSet tripleSet = new SparqlTripleSet(Sparql.CHO_VAR_NAME, filterSet);

        tripleSet.addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.RDF_TYPE), "edm:ProvidedCHO");
        tripleSet.addTriple(Sparql.CHO_VAR_NAME, "?property", "?value");

        SparqlPrefixSet prefixSet = new SparqlPrefixSet(tripleSet, filterSet);

        String tripleSetStr = tripleSet.toString();
        String filterSetStr = filterSet.toString();

        return prefixSet + "\n\n" +
                "SELECT DISTINCT *\n" +
                "WHERE {\n" +
                    (tripleSetStr != null ? tripleSetStr + " .\n" : "") +
                    (filterSetStr != null ? filterSetStr + "\n" : "") +
                "}\n" +
                "ORDER BY ?property";
    }
}
