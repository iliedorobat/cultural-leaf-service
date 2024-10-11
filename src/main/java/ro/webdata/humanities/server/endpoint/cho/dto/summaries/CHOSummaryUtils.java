package ro.webdata.humanities.server.endpoint.cho.dto.summaries;

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
        ArrayList<CHOSummaryDTO> choSummaryDTODTOList = toCHOs(nodes);
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(choSummaryDTODTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    private static ArrayList<CHOSummaryDTO> toCHOs(ArrayNode nodes) {
        ArrayList<CHOSummaryDTO> choSummaryDTODTOList = new ArrayList<>();

        if (nodes == null) {
            return choSummaryDTODTOList;
        }

        for (JsonNode jsonNode : nodes) {
            choSummaryDTODTOList.add(new CHOSummaryDTO(jsonNode));
        }

        return choSummaryDTODTOList;
    }
}
