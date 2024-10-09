package ro.webdata.humanities.server.endpoint.museum.dto.details;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.JsonUtils;

public class MuseumDetailsUtils {
    public static String responseToJson(String jsonString) {
        JsonNode jsonNode = JsonUtils.stringToJson(jsonString);
        ArrayNode nodes = JsonUtils.getNodes(jsonNode);

        return toString(nodes);
    }

    public static String toString(ArrayNode nodes) {
        MuseumDetailsDTO museumDetailsDTO = new MuseumDetailsDTO(nodes);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(museumDetailsDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
