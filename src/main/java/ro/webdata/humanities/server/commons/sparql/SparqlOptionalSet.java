package ro.webdata.humanities.server.commons.sparql;

import java.util.TreeSet;

public class SparqlOptionalSet {
    private SparqlFilterSet filterSet;
    private SparqlTripleSet tripleSet;

    public SparqlOptionalSet(String subject, TreeSet<String> predicates) {
        setTripleSet(
                new SparqlTripleSet(subject, predicates, null)
        );
    }

    public SparqlOptionalSet(String subject, TreeSet<String> predicates, SparqlFilterSet filterSet) {
        setFilterSet(filterSet);
        setTripleSet(
                new SparqlTripleSet(subject, predicates, filterSet)
        );
    }

    @Override
    public String toString() {
        String triplesStr = tripleSet != null ? tripleSet.toString("\t\t") : null;
        String filtersStr = filterSet != null ? filterSet.toString("\t\t") : null;

        if (triplesStr == null && filtersStr == null) {
            return null;
        }

        return "" +
                "\tOPTIONAL {\n" +
                    (triplesStr != null ? triplesStr + "\n" : "") +
                    (filtersStr != null ? filtersStr + "\n" : "") +
                "\t}";
    }

    public void addTriple(String subject, String predicate) {
        tripleSet.addTriple(subject, predicate);
    }

    public SparqlFilterSet getFilterSet() {
        return filterSet;
    }

    public void setFilterSet(SparqlFilterSet filterSet) {
        this.filterSet = filterSet;
    }

    public SparqlTripleSet getTripleSet() {
        return tripleSet;
    }

    public void setTripleSet(SparqlTripleSet tripleSet) {
        this.tripleSet = tripleSet;
    }
}
