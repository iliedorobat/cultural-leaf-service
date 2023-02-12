package ro.webdata.humanities.server.endpoint.cho.dto.stats;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.JsonUtils;
import ro.webdata.humanities.server.endpoint.cho.dto.StatsEntryDTO;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CHOTimespanStatsDTO {
    private List<StatsEntryDTO> entries = new ArrayList<>();
    private String eventType;
    private String timespanType;

    public CHOTimespanStatsDTO(ArrayNode nodes, ArrayNode counterNodes, String eventType, String timespanType) {
        setEventType(eventType);
        setTimespanType(timespanType);

        for (JsonNode jsonNode : nodes) {
            addEntry(jsonNode);
        }

        if (counterNodes.size() > 0) {
            addOthers(counterNodes.get(0));
        }
    }

    public void addOthers(JsonNode jsonNode) {
        String total = JsonUtils.getNodeValue(jsonNode, "edm_occuredAt_count");
        int top = this.getTop();

        try {
            int others = Integer.parseInt(total) - top;

            if (others > 0) {
                entries.add(new StatsEntryDTO("Others", others));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(JsonNode jsonNode) {
        String timespan = JsonUtils.getNodeValue(jsonNode, "edm_occuredAt");
        String count = JsonUtils.getNodeValue(jsonNode, "edm_occuredAt_count");

        entries.add(new StatsEntryDTO(timespan, count));
    }

    public List<StatsEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<StatsEntryDTO> entries) {
        this.entries = entries;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTimespanType() {
        return timespanType;
    }

    public void setTimespanType(String timespanType) {
        this.timespanType = timespanType;
    }

    private int getTop() {
        int top = 0;

        for (StatsEntryDTO entry : entries) {
            top += entry.getValue();
        }

        return top;
    }
}
