package ro.webdata.humanities.server.endpoint.museum.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.JsonUtils;
import ro.webdata.humanities.server.endpoint.museum.dto.museums.MuseumSummaryDTO;

import java.util.ArrayList;

public class MuseumSummaryUtils {
    public static String responseToJson(String jsonString) {
        JsonNode jsonNode = JsonUtils.stringToJson(jsonString);
        ArrayNode nodes = JsonUtils.getNodes(jsonNode);

        return toString(nodes);
    }

    public static String toString(ArrayNode nodes) {
        ArrayList<MuseumSummaryDTO> museumSummaryDTOList = toMuseums(nodes);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(museumSummaryDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    private static ArrayList<MuseumSummaryDTO> toMuseums(ArrayNode nodes) {
        ArrayList<MuseumSummaryDTO> museumSummaryDTOList = new ArrayList<>();

        if (nodes == null) {
            return museumSummaryDTOList;
        }

        for (JsonNode jsonNode : nodes) {
            museumSummaryDTOList.add(new MuseumSummaryDTO(jsonNode));
        }

        return museumSummaryDTOList;
    }
}
