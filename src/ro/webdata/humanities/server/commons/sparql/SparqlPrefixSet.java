package ro.webdata.humanities.server.commons.sparql;

import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.*;
import ro.webdata.echo.commons.graph.Namespace;
import ro.webdata.echo.commons.graph.vocab.EDM;
import ro.webdata.echo.commons.graph.vocab.ORE;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SparqlPrefixSet {
    public static final Map<String, String> NAMESPACES = new HashMap<>() {{
        put("dbo", Namespace.NS_DBPEDIA_ONTOLOGY);
        put("dbp", Namespace.NS_DBPEDIA_PROPERTY);
        put("dbr", Namespace.NS_DBPEDIA_RESOURCE);
        put("dc", DC_11.getURI());
        put("dcterms", DCTerms.getURI());
        put("edm", EDM.getURI());
        put("foaf", FOAF.getURI());
        put("odp", Namespace.NS_REPO_PROPERTY);
        put("odr", Namespace.NS_REPO_RESOURCE);
        put("ore", ORE.getURI());
        put("rdf", RDF.getURI());
        put("rdfs", RDFS.getURI());
        put("skos", SKOS.getURI());
    }};

    private Set<SparqlPrefix> prefixes;

    public SparqlPrefixSet(SparqlTripleSet tripleSet, SparqlFilterSet filterSet) {
        setPrefixes(tripleSet, null, filterSet);
    }

    public SparqlPrefixSet(SparqlTripleSet tripleSet, SparqlOptionalSet optionalSet, SparqlFilterSet filterSet) {
        setPrefixes(tripleSet, optionalSet, filterSet);
    }

    @Override
    public String toString() {
        return toString("");
    }

    public String toString(String tab) {
        if (prefixes == null) {
            return null;
        }

        return prefixes.stream()
                .map(SparqlPrefix::toString)
                .reduce(null, (prev, prefix) -> prev == null
                        ? tab + prefix
                        : prev + "\n" + tab + prefix);
    }

    public Set<SparqlPrefix> getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(SparqlTripleSet tripleSet, SparqlOptionalSet optionalSet, SparqlFilterSet filterSet) {
        List<SparqlPrefix> prefixesArr = new ArrayList<>();

        if (tripleSet != null) {
            prefixesArr.addAll(getTriplesPrefixes(tripleSet.getTriples()));
        }
        if (optionalSet != null) {
            prefixesArr.addAll(getTriplesPrefixes(optionalSet.getTripleSet().getTriples()));
        }
        if (filterSet != null) {
            prefixesArr.addAll(getFiltersPrefixes(filterSet.getFilters()));
        }

        Collections.sort(prefixesArr);

        prefixes = new HashSet<>(prefixesArr);
    }

    private List<SparqlPrefix> getFiltersPrefixes(Set<String> filters) {
        List<SparqlPrefix> map = new ArrayList<>();

        Pattern pattern = Pattern.compile("[a-zA-Z]*(?=:)");
        Matcher matcher;

        for (String filter : filters) {
            matcher = pattern.matcher(filter);

            while (matcher.find()) {
                String nsAbbr = matcher.group();

                if (nsAbbr != null && nsAbbr.length() > 0) {
                    String nsUri = NAMESPACES.get(nsAbbr);

                    if (nsUri != null) {
                        map.add(new SparqlPrefix(nsAbbr, nsUri));
                    }
                }
            }
        }

        return map;
    }

    private List<SparqlPrefix> getTriplesPrefixes(Set<SparqlTriple> triples) {
        List<SparqlPrefix> arr = new ArrayList<>();

        for (SparqlTriple triple : triples) {
            String predicate = triple.getPredicate();
            String object = triple.getObject();

            addTriplePrefix(arr, predicate);
            if (object.contains(":")) {
                addTriplePrefix(arr, object);
            }
        }

        return arr;
    }

    // The item is a predicate or an object
    private void addTriplePrefix(List<SparqlPrefix> arr, String item) {
        String nsAbbr = item.split(":")[0];
        String nsUri = NAMESPACES.get(nsAbbr);

        if (nsUri != null) {
            arr.add(new SparqlPrefix(nsAbbr, nsUri));
        } else {
            System.out.println("\"" + nsAbbr + "\" " + "was not found in the list of namespaces!");
        }
    }
}
