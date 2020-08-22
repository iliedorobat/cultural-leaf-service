package ro.webdata.parser.web.museums.core.curation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ro.webdata.commons.JsonUtils;
import ro.webdata.parser.web.museums.commons.constants.CimecAccessors;
import ro.webdata.parser.web.museums.commons.constants.Constants;

import java.util.*;

class CimecCuration {
    /**
     * Get the list containing the prepared key-value pairs
     * @param jsonPath The path to the raw data
     * @param lang The language used
     * @return The prepared key-value pairs
     */
    static ArrayList<Map<String, Object>> getEntries(String jsonPath, String lang) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        JsonArray array = JsonUtils.getJsonArray(jsonPath);

        for (JsonElement element: array) {
            list.add(getEntryMap(element, array, lang));
        }

        return list;
    }

    /**
     * Get the map containing the prepared key-value pairs for the passed museum
     * @param element The record for one museum
     * @param array The list containing all the records with the museums
     * @param lang The language used
     * @return The prepared key-value pairs for the passed museum
     */
    private static Map<String, Object> getEntryMap(JsonElement element, JsonArray array, String lang) {
        Map<String, Object> entryMap = new HashMap<>();
        JsonObject object = element.getAsJsonObject();
        ArrayList<Map.Entry<String, JsonElement>> entries = new ArrayList<>(object.entrySet());

        // TODO: addEntityType(entryMap, lang);
        addSubordinatedCode(entries, array, lang);

        for (Map.Entry<String, JsonElement> entry: entries) {
            DataCuration.prepareEntryMap(entryMap, entry);
        }

        return entryMap;
    }

    /**
     * Add the code of the institution to which the museum is subordinated
     * @param entries The set of entries of an JsonObject
     * @param array The list containing all the records with the museums
     * @param lang The language used
     */
    private static void addSubordinatedCode(ArrayList<Map.Entry<String, JsonElement>> entries, JsonArray array, String lang) {
        Object[] result = null;

        for (Map.Entry<String, JsonElement> entry: entries) {
            String key = entry.getKey();

            if (DataCuration.SUBORDINATION_ACCESSORS.indexOf(key) != -1) {
                String subordinationName = entry.getValue().getAsString();
                ArrayList<HashMap<String, String>> items = new Gson().fromJson(array, ArrayList.class);

                result = Arrays.stream(items.toArray())
                        .filter(item -> isSubordinatedTo(item, subordinationName, lang))
                        .toArray();
            }
        }

        if (result != null && result.length == 1) {
            JsonObject masterInstitution = JsonUtils.getJsonObject(result[0]);
            Map.Entry<String, JsonElement> entry = getSubordinatedCodeEntry(masterInstitution, lang);

            if (entry != null) {
                entries.add(entry);
            }
        }
    }

    /**
     * Get the Map.Entry item which contains the code of the institution to which the museum is subordinated
     * @param masterInstitution The name of the institution to which the museum is subordinated
     * @param lang The language used
     * @return The code of the institution to which the museum is subordinated or null
     */
    private static Map.Entry<String, JsonElement> getSubordinatedCodeEntry(JsonObject masterInstitution, String lang) {
        Map.Entry<String, JsonElement> entry = null;
        JsonElement codeElem = null;
        String code = null;

        if (lang != null && lang.equals(Constants.LANG_EN)) {
            codeElem = masterInstitution.get(CimecAccessors.EN_MUSEUM_CODE);
            code = codeElem.getAsString();

            entry = new AbstractMap.SimpleEntry<>(
                    CimecAccessors.EN_SUBORDINATION_CODE, JsonUtils.getJsonElement(code)
            );
        } else if (lang != null && lang.equals(Constants.LANG_RO)) {
            codeElem = masterInstitution.get(CimecAccessors.RO_MUSEUM_CODE);
            code = codeElem.getAsString();

            entry = new AbstractMap.SimpleEntry<>(
                    CimecAccessors.RO_SUBORDINATION_CODE, JsonUtils.getJsonElement(code)
            );
        }

        return entry;
    }

    /**
     * Check if the item contains the name of the institution to which the museum is subordinated
     * @param item An Object which will be converted to JsonObject
     * @param masterName The name of the institution to which the museum is subordinated
     * @param lang The language used
     * @return Returns true if there was found the institution to which the museum is subordinated
     */
    private static boolean isSubordinatedTo(Object item, String masterName, String lang) {
        JsonObject jsonObject = JsonUtils.getJsonObject(item);
        JsonElement nameElem = null;
        String name = null;

        if (lang != null && lang.equals(Constants.LANG_EN)) {
            nameElem = jsonObject.get(CimecAccessors.EN_MUSEUM_NAME);
            name = nameElem.getAsString();
        } else if (lang != null && lang.equals(Constants.LANG_RO)) {
            nameElem = jsonObject.get(CimecAccessors.RO_MUSEUM_NAME);
            name = nameElem.getAsString();
        }

        return name != null && name.equals(masterName);
    }
}
