package ro.webdata.humanities.server.endpoint.cho;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.CounterDTO;
import ro.webdata.humanities.server.commons.JsonUtils;

public class CHOUtils {
    public static String responseToJson(String jsonString) {
        JsonNode jsonNode = JsonUtils.stringToJson(jsonString);
        ArrayNode nodes = JsonUtils.getNodes(jsonNode); // TODO: getBindings

        return toString(nodes);
    }

    // TODO: merge with MuseumUtils.java???
    public static String toString(ArrayNode nodes) {
        CounterDTO counterDTO = new CounterDTO(nodes);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(counterDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
