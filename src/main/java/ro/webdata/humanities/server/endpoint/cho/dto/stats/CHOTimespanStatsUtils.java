package ro.webdata.humanities.server.endpoint.cho.dto.stats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.JsonUtils;

public class CHOTimespanStatsUtils {
    public static String responseToJson(String jsonString, String counterJsonString, String eventType, String timespanType) {
        JsonNode jsonNode = JsonUtils.stringToJson(jsonString);
        ArrayNode nodes = JsonUtils.getNodes(jsonNode);

        JsonNode counterJsonNode = JsonUtils.stringToJson(counterJsonString);
        ArrayNode counterNodes = JsonUtils.getNodes(counterJsonNode);

        return toString(nodes, counterNodes, eventType, timespanType);
    }

    public static String toString(ArrayNode nodes, ArrayNode counterNodes, String eventType, String timespanType) {
        CHOTimespanStatsDTO choTimespanStatsDTO = new CHOTimespanStatsDTO(nodes, counterNodes, eventType, timespanType);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(choTimespanStatsDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
