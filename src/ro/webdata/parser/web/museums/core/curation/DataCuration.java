package ro.webdata.parser.web.museums.core.curation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import ro.webdata.commons.FileUtils;
import ro.webdata.parser.web.museums.commons.DataFormatter;
import ro.webdata.parser.web.museums.commons.FilePath;
import ro.webdata.parser.web.museums.commons.constants.BackendAccessors;
import ro.webdata.parser.web.museums.commons.constants.CimecAccessors;
import ro.webdata.parser.web.museums.core.DataMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataCuration {
    public static ArrayList<String> SUBORDINATION_ACCESSORS = new ArrayList<>(
            Arrays.asList(CimecAccessors.EN_SUBORDINATION, CimecAccessors.RO_SUBORDINATION)
    );
    private static Gson gson = new Gson();

    /**
     * Write the curated CIMEC dataset to disk
     * @param lang The language used
     */
    public static void writeCimecJson(String lang) {
        String inputPath = FilePath.getCimecRawJsonPath(lang);
        String outputPath = FilePath.getCimecPreparedJsonPath(lang);

        ArrayList<Map<String, Object>> entries = CimecCuration.getEntries(inputPath, lang);
        String json = gson.toJson(entries);

        FileUtils.write(json, outputPath, false);
    }

    /**
     * Write the curated INP dataset to disk
     */
    public static void writeInpJson() {
        String inputPath = FilePath.getInpRawJsonPath();
        String outputPath = FilePath.getInpPreparedJsonPath();

        ArrayList<Map<String, Object>> entries = InpCuration.getEntries(inputPath);
        String json = gson.toJson(entries);

        FileUtils.write(json, outputPath, false);
    }

    /**
     * Prepare the entries of a record and add them to the target map
     * @param entryMap The target map
     * @param record The key-value pairs collection describing a museum
     */
    public static void prepareEntryMap(Map<String, Object> entryMap, Map.Entry<String, JsonElement> record) {
        Map<String, Object> crrObject = entryMap;
        String key = DataMapping.getKeyName(record.getKey());
        String value = record.getValue().getAsString();

        String[] accessors = Arrays.stream(
                key.split("\\.")
        ).filter(
                accessor -> BackendAccessors.ALLOWED_ACCESSORS.indexOf(accessor) != -1
        ).toArray(
                String[] :: new
        );

        for (int i = 0; i < accessors.length; i++) {
            String accessor = accessors[i];

            // If there is a simple key, add it as a simple string
            // E.g.: key = "contact"   =>   accessors = {"contract"}
            if (accessors.length == 1) {
                addValue(crrObject, accessor, key, value);
            }
            // E.g.: key = "contact.agent.name"   =>   accessors = {"contract", "agent", "name"}
            else {
                // For each key item (E.g.: "contract" and "agent") except the last one
                // (E.g.: "name"), build an empty map to use for storage
                if (i < accessors.length - 1) {
                    Map item = (Map) crrObject.get(accessor);

                    if (item == null) {
                        crrObject.put(accessor, new HashMap<>());
                    }

                    crrObject = (Map<String, Object>) crrObject.get(accessor);
                }
                // Add the last key item as a simple string
                // E.g.: key = "contact.agent.name"   =>   last key item = "name"
                else {
                    addValue(crrObject, accessor, key, value);
                }
            }
        }
    }

    /**
     * Format the value and add it to the map according to its type
     * @param map The target map
     * @param accessor One of the values of BackendAccessors.ALLOWED_ACCESSORS
     * @param keyName The key retrieved by the means of DataMapping.getKeyName (E.g.: "contact.agent.name")
     * @param value The original value
     */
    private static void addValue(Map<String, Object> map, String accessor, String keyName, String value) {
        String formattedValue = DataFormatter.format(keyName, value);

        if (BackendAccessors.DOUBLE_VALUE_ACCESSORS.indexOf(accessor) != -1) {
            map.put(accessor, Double.parseDouble(formattedValue));
        } else {
            map.put(accessor, formattedValue);
        }
    }
}
