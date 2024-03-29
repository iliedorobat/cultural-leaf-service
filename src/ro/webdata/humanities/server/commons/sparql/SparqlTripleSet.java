package ro.webdata.humanities.server.commons.sparql;

import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SparqlTripleSet {
    private Set<SparqlTriple> triples;

    public SparqlTripleSet(Set<SparqlTriple> triples) {
        setTriples(triples);
    }

    public SparqlTripleSet(String subject, SparqlFilterSet filterSet) {
        Set<String> filters = filterSet != null ? filterSet.getFilters() : null;
        setTriples(subject, null, filters);
    }

    public SparqlTripleSet(String subject, TreeSet<String> predicates, SparqlFilterSet filterSet) {
        Set<String> filters = filterSet != null ? filterSet.getFilters() : null;
        setTriples(subject, predicates, filters);
    }

    @Override
    public String toString() {
        return toString("\t");
    }

    public String toString(String tab) {
        if (triples == null) {
            return null;
        }

        String output = null;

        for (SparqlTriple triple : triples) {
            if (output == null) {
                output = tab + triple;
            } else {
                output += " .\n" + tab + triple;
            }
        }

        return output;
    }

    public void addTriple(String subject, String predicate) {
        triples.add(
                new SparqlTriple(subject, predicate)
        );
    }

    public void addTriple(String subject, String predicate, String object) {
        triples.add(
                new SparqlTriple(subject, predicate, object)
        );
    }

    public void addEventTriples(String eventType) {
        if (eventType != null) {
            String eventAge = Sparql.getVarName(Sparql.PROPS.get(PROP_KEYS.EVENT_AGE));

            addTriple(Sparql.CHO_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.CHO_EVENT), Sparql.EVENT_VAR_NAME);
            addTriple(Sparql.EVENT_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.RDF_TYPE), "edm:Event");
            addTriple(Sparql.EVENT_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.EVENT_TYPE), String.format("\"%s\"@en", eventType));
            addTriple(Sparql.EVENT_VAR_NAME, Sparql.PROPS.get(PROP_KEYS.EVENT_AGE), eventAge);
        }
    }

    public Set<SparqlTriple> getTriples() {
        return triples;
    }

    public void setTriples(Set<SparqlTriple> triples) {
        this.triples = triples;
    }

    public void setTriples(String subject, TreeSet<String> predicates, Set<String> filters) {
        Set<SparqlTriple> set = new HashSet<>();
        addFiltersTriples(set, subject, filters);
        addPredicatesTriples(set, subject, predicates);
        triples = set;
    }

    private void addFiltersTriples(Set<SparqlTriple> set, String subject, Set<String> filters) {
        if (filters != null) {
            Pattern pattern = Pattern.compile("(?<=[\\(\\s])\\p{Punct}[a-zA-Z]+_[a-zA-Z]+");
            Matcher matcher;

            for (String filter : filters) {
                // Avoid adding <?cho edm:occuredAt ?edm_occuredAt> triple
                String occurredAt = Sparql.getVarName(PROP_KEYS.EVENT_AGE, true);
                if (filter.contains(occurredAt)) {
                    continue;
                }

                matcher = pattern.matcher(filter);

                while (matcher.find()) {
                    String varName = matcher.group();
                    String predicate = Sparql.varNameToPredicate(varName);
                    set.add(
                            new SparqlTriple(subject, predicate, varName)
                    );
                }
            }
        }
    }

    public void addPredicatesTriples(Set<SparqlTriple> set, String subject, TreeSet<String> predicates) {
        if (predicates != null) {
            for (String predicate : predicates) {
                set.add(
                        new SparqlTriple(subject, predicate)
                );
            }
        }
    }
}
