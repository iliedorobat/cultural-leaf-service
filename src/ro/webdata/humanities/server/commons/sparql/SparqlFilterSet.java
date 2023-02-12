package ro.webdata.humanities.server.commons.sparql;

import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOBaseFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOFilterQuery;
import ro.webdata.humanities.server.endpoint.cho.filter.cho.CHOStatsFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.medal.MedalCHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.medal.MedalQuery;
import ro.webdata.humanities.server.endpoint.cho.filter.nature.NatureCHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.nature.NatureQuery;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SparqlFilterSet {
    private Set<String> filters;

    public SparqlFilterSet() {
        this.filters = new HashSet<>();
    }

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

    public SparqlFilterSet(CHOStatsFilter choStatsFilter) {
        setFilters(choStatsFilter);
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
        filters = getCHOBaseFilter(choFilter);
    }

    public void setFilters(CHOStatsFilter choStatsFilter) {
        filters = getCHOBaseFilter(choStatsFilter);
    }

    private Set<String> getCHOBaseFilter(CHOBaseFilter choBaseFilter) {
        Set<String> set = new HashSet<>();

        if (choBaseFilter != null) {
            MedalCHOFilter medalFilter = choBaseFilter.getMedalFilter();
            NatureCHOFilter natureFilter = choBaseFilter.getNatureFilter();

            set.add(CHOFilterQuery.prepareCountyFilter(choBaseFilter.getCounty()));
            set.add(CHOFilterQuery.prepareDisplayStateFilter(choBaseFilter.getDisplayState()));
            set.add(CHOFilterQuery.prepareEpochFilter(choBaseFilter.getEpoch())); // TODO:
            set.add(CHOFilterQuery.prepareInventoryNumberFilter(choBaseFilter.getInventoryNumber()));
            set.add(CHOFilterQuery.prepareLocalityFilter(choBaseFilter.getLocality())); // TODO:
            set.add(CHOFilterQuery.prepareTitleFilter(choBaseFilter.getTitle()));
            set.add(CHOFilterQuery.prepareTypeFilter(choBaseFilter.getType()));

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

        return set;
    }
}
