package ro.webdata.humanities.server.endpoint.museum.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.JsonUtils;
import ro.webdata.humanities.server.endpoint.museum.dto.museum.MuseumDTO;

public class MuseumUtils {
    public static String responseToJson(String jsonString) {
        JsonNode jsonNode = JsonUtils.stringToJson(jsonString);
        ArrayNode nodes = JsonUtils.getNodes(jsonNode);

        return toString(nodes);
    }

    public static String toString(ArrayNode nodes) {
        MuseumDTO museumDTO = new MuseumDTO(nodes);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(museumDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
