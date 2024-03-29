package ro.webdata.humanities.server.commons;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public class JsonUtils {
    // https://cassiomolin.com/2019/08/19/combining-jackson-streaming-api-with-objectmapper-for-parsing-json/
    public static JsonNode stringToJson(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();

        try {
            JsonParser parser = factory.createParser(jsonString);
            return mapper.readTree(parser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve the queried data
    public static ArrayNode getNodes(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }

        return (ArrayNode) jsonNode.get("results").get("bindings");
    }

    // Retrieve the variable names used in the SPARQL query
    public static ArrayNode getQueryVariables(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }

        return (ArrayNode) jsonNode.get("head").get("vars");
    }

    // TODO: check
    public static String getNodeDataType(JsonNode jsonNode, String accessor) {
        JsonNode node = jsonNode.get(accessor);

        if (node == null) {
            return null;
        }

        JsonNode langNode = node.get("datatype");

        return langNode != null
                ? langNode.asText()
                : null;
    }

    public static String getNodeLang(JsonNode jsonNode, String accessor) {
        JsonNode node = jsonNode.get(accessor);

        if (node == null) {
            return null;
        }

        JsonNode langNode = node.get("xml:lang");

        return langNode != null
                ? langNode.asText()
                : null;
    }

    // TODO: check
    public static String getNodeType(JsonNode jsonNode, String accessor) {
        JsonNode node = jsonNode.get(accessor);

        if (node == null) {
            return null;
        }

        JsonNode langNode = node.get("type");

        return langNode != null
                ? langNode.asText()
                : null;
    }

    // Retrieve the value of a JsonNode
    public static String getNodeValue(JsonNode jsonNode, String accessor) {
        JsonNode node = jsonNode.get(accessor);

        if (node == null) {
            return null;
        }

        JsonNode valueNode = node.get("value");

        return valueNode != null
                ? valueNode.asText()
                : null;
    }

    public static Integer getNodeValueAsInteger(JsonNode jsonNode, String accessor) {
        String value = getNodeValue(jsonNode, accessor);

        if (value == null) {
            return null;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
