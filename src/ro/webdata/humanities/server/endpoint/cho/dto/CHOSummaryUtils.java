package ro.webdata.humanities.server.endpoint.cho.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ro.webdata.humanities.server.commons.JsonUtils;

import java.util.ArrayList;

public class CHOSummaryUtils {
    public static String responseToJson(String jsonString) {
        JsonNode jsonNode = JsonUtils.stringToJson(jsonString);
        ArrayNode nodes = JsonUtils.getNodes(jsonNode);

        return toString(nodes);
    }

    public static String toString(ArrayNode nodes) {
        ArrayList<CHOSummary> choSummaryDTOList = toCHOs(nodes);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(choSummaryDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    private static ArrayList<CHOSummary> toCHOs(ArrayNode nodes) {
        ArrayList<CHOSummary> choSummaryDTOList = new ArrayList<>();

        if (nodes == null) {
            return choSummaryDTOList;
        }

        for (JsonNode jsonNode : nodes) {
            choSummaryDTOList.add(new CHOSummary(jsonNode));
        }

        return choSummaryDTOList;
    }
}
