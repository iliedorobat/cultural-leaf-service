package ro.webdata.humanities.server.commons.sparql;

import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilterQuery;
import ro.webdata.humanities.server.endpoint.cho.filter.medal.MedalCHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.medal.MedalQuery;
import ro.webdata.humanities.server.endpoint.cho.filter.nature.NatureCHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.nature.NatureQuery;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SparqlFilterSet {
    private Set<String> filters;

    public SparqlFilterSet(Set<String> filters) {
        setFilters(filters);
    }

    public SparqlFilterSet(String condition) {
        setFilters(new HashSet<>());
        addFilter(condition);
    }

    public SparqlFilterSet(CHOFilter choFilter) {
        setFilters(choFilter);
    }

    @Override
    public String toString() {
        return toString("\t");
    }

    public String toString(String tab) {
        if (filters == null) {
            return null;
        }

        return filters.stream()
                .reduce(null, (prev, filter) -> prev == null
                        ? tab + filter
                        : prev + " .\n" + tab + filter);
    }

    public void addFilter(String condition) {
        String filter = "FILTER(" + condition + ")";
        filters.add(filter);
    }

    public Set<String> getFilters() {
        return filters;
    }

    public void setFilters(Set<String> filters) {
        this.filters = new HashSet<>();

        for (String filter : filters) {
            addFilter(filter);
        }
    }

    public void setFilters(CHOFilter choFilter) {
        Set<String> set = new HashSet<>();

        if (choFilter != null) {
            MedalCHOFilter medalFilter = choFilter.getMedalFilter();
            NatureCHOFilter natureFilter = choFilter.getNatureFilter();

            set.add(CHOFilterQuery.prepareCountyFilter(choFilter.getCounty()));
            set.add(CHOFilterQuery.prepareCreationTimeFilter(choFilter.getCreationInterval()));
            set.add(CHOFilterQuery.prepareDisplayStateFilter(choFilter.getDisplayState()));
            set.add(CHOFilterQuery.prepareEpochFilter(choFilter.getEpoch())); // TODO:
            set.add(CHOFilterQuery.prepareFoundTimeFilter(choFilter.getFoundInterval()));
            set.add(CHOFilterQuery.prepareInventoryNumberFilter(choFilter.getInventoryNumber()));
            set.add(CHOFilterQuery.prepareLocalityFilter(choFilter.getLocality())); // TODO:
            set.add(CHOFilterQuery.prepareTitleFilter(choFilter.getTitle()));
            set.add(CHOFilterQuery.prepareTypeFilter(choFilter.getType()));

            String medalShape = medalFilter != null ? MedalQuery.prepareShapeFilter(medalFilter.getShape()) : null;
            String natureAge = natureFilter != null ? NatureQuery.prepareAgeFilter(natureFilter.getAge()) : null;
            String natureEpoch = natureFilter != null ? NatureQuery.prepareEpochFilter(natureFilter.getEpoch()) : null;
            String natureSex = natureFilter != null ? NatureQuery.prepareSexFilter(natureFilter.getSex()) : null;

            set.add(medalShape);
            set.add(natureAge);
            set.add(natureEpoch);
            set.add(natureSex);

            set.removeIf(Objects::isNull);
            set.removeIf(String::isEmpty);
        }

        filters = set;
    }
}
