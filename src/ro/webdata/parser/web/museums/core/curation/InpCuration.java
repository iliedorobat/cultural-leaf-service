package ro.webdata.parser.web.museums.core.curation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ro.webdata.commons.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class InpCuration {
    /**
     * Get the list containing the prepared key-value pairs
     * @param jsonPath The path to the raw data
     * @return The prepared key-value pairs
     */
    static ArrayList<Map<String, Object>> getEntries(String jsonPath) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        JsonArray array = JsonUtils.getJsonArray(jsonPath);

        for (JsonElement element: array) {
            list.add(getEntryMap(element));
        }

        return list;
    }

    /**
     * The map containing the prepared key-value pairs for the passed museum
     * @param element The record for one museum
     * @return The prepared key-value pairs for the passed museum
     */
    private static Map<String, Object> getEntryMap(JsonElement element) {
        Map<String, Object> entryMap = new HashMap<>();
        JsonObject object = element.getAsJsonObject();
        ArrayList<Map.Entry<String, JsonElement>> entries = new ArrayList<>(object.entrySet());

        // TODO: addEntityType(entryMap, lang);

        for (Map.Entry<String, JsonElement> entry: entries) {
            DataCuration.prepareEntryMap(entryMap, entry);
        }

        return entryMap;
    }
}
