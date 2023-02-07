package ro.webdata.humanities.server.endpoint.cho.filter.cho;

import ro.webdata.echo.commons.graph.Namespace;
import ro.webdata.normalization.timespan.ro.TimeUtils;
import ro.webdata.normalization.timespan.ro.TimespanType;
import ro.webdata.normalization.timespan.ro.model.TimePeriodModel;

import java.util.TreeSet;

public class CHOFilterInterval {
    private CHOFilterTime start;
    private CHOFilterTime end;

    public String toDBpediaString() {
        return this.getTimePeriodSet().stream()
                .reduce(null, (prev, crrValue) -> {
                    if (prev == null) {
                        return "<" + Namespace.NS_DBPEDIA_RESOURCE + crrValue + ">";
                    }
                    return prev + ", <" + Namespace.NS_DBPEDIA_RESOURCE + crrValue + ">";
                });
    }

    private TreeSet<String> getTimePeriodSet() {
        int startDate = Math.abs(this.start.getDate());
        int endDate = Math.abs(this.end.getDate());

        String startRange = this.start.getRange();
        String endRange = this.end.getRange();
        String timespanType = startRange != null && startRange.equals(endRange)
                ? getTimespanType(startRange)
                : null;

        if (timespanType != null) {
            String startEra = getEra(this.start);
            String endEra = getEra(this.end);

            if (timespanType.equals(TimespanType.CENTURY)) {
                return TimePeriodModel.getCenturySet(startEra, endEra, startDate, endDate, true);
            } else if (timespanType.equals(TimespanType.MILLENNIUM)) {
                return TimePeriodModel.getMillenniumSet(startEra, endEra, startDate, endDate, true);
            }
        }

        return new TreeSet<>();
    }

    public CHOFilterTime getStart() {
        return start;
    }

    public void setStart(CHOFilterTime start) {
        this.start = start;
    }

    public CHOFilterTime getEnd() {
        return end;
    }

    public void setEnd(CHOFilterTime end) {
        this.end = end;
    }

    private static String getEra(CHOFilterTime time) {
        return time.getDate() >= 0
                ? TimeUtils.CHRISTUM_AD_PLACEHOLDER
                : TimeUtils.CHRISTUM_BC_PLACEHOLDER;
    }

    private static String getTimespanType(String range) {
        if (range.equals(CHO_DATE_RANGE.CENTURY)) {
            return TimespanType.CENTURY;
        } else if (range.equals(CHO_DATE_RANGE.MILLENNIUM)) {
            return TimespanType.MILLENNIUM;
        }

        return null;
    }
}

class CHOFilterTime {
    private int date;
    private String range;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}

class CHO_DATE_RANGE {
    public static final String CENTURY = "Century";
    public static final String MILLENNIUM = "Millennium";
}
