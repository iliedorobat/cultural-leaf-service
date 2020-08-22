package ro.webdata.commons;

import com.google.gson.*;
import ro.webdata.parser.web.museums.commons.constants.BackendAccessors;

public class JsonUtils {
    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    public static JsonArray getJsonArray(String path) {
        StringBuilder sb = FileUtils.read(path);
        JsonElement json = parser.parse(sb.toString());
        return json.getAsJsonArray();
    }

    public static JsonElement getJsonElement(Object value) {
        String jsonStr = gson.toJson(value);
        JsonElement json = parser.parse(jsonStr);
        return json;
    }

    public static JsonElement getJsonElement(String value) {
        String jsonStr = gson.toJson(value);
        JsonElement json = parser.parse(jsonStr);
        return json;
    }

    public static JsonObject getJsonObject(JsonElement element) {
        String jsonStr = gson.toJson(element);
        JsonElement json = parser.parse(jsonStr);
        return json.getAsJsonObject();
    }

    public static JsonObject getJsonObject(Object objectMap) {
        String jsonStr = gson.toJson(objectMap);
        JsonElement json = parser.parse(jsonStr);
        return json.getAsJsonObject();
    }

    /**
     * E.g. of accessors:
     * <ul>
     *     <li>"location.administrative"</li>
     *     <li>"location.geo.longitude"</li>
     * </ul>
     *
     * E.g. of jsonObject:
     * <pre>
     * {
     *     "code": "7300407",
     *     "contact": {
     *         ...
     *     },
     *     "founded": "1958",
     *     "name": "Casa Memorială \"George Și Agatha Bacovia\"",
     *     "description": {
     *         ...
     *     },
     *     "location": {
     *         "geo": {
     *             "latitude": 44.397006,
     *             "target": "Muzeu",
     *             "longitude": 26.101241
     *         },
     *         "zipCode": "040691",
     *         "access": "Tramway: 7, 12, 18, 39 (Muzeul Bacovia Station), 19, 50 (Șura Mare); 11, 17 (Opriș Ilie). Bus: 116, 141, 144, 232 (Opriș Ilie); 275, 323 (Muzeul Bacovia - Șos. Giurgiului)",
     *         "address": "Str. George Bacovia nr. 63, sector 4",
     *         "locality": {
     *             "code": "179178",
     *             "name": "București"
     *         },
     *         "county": "București"
     *     },,
     *     "collection": {
     *         ...
     *     },
     *     "subordination": {
     *         "code": "7300400"
     *     }
     * }
     * </pre>
     * @param jsonObject
     * @param accessor
     * @return
     */
    public static Object getAccessorValue(JsonObject jsonObject, String accessor) {
        String[] keys = accessor.split(BackendAccessors.ESCAPED_SEPARATOR);
        JsonObject crrObject = jsonObject;
        JsonElement crrElement;

        for (int i = 0; i < keys.length; i++) {
            if (crrObject != null) {
                String key = keys[i];
                crrElement = crrObject.get(key);

                // In the provided example, for the case of accessor = "location.administrative",
                // there will not be found the "administrative" key
                if (crrElement == null) {
                    return null;
                }

                if (i < keys.length - 1) {
                    crrObject = getJsonObject(crrElement);
                } else {
                    return BackendAccessors.DOUBLE_VALUE_ACCESSORS.indexOf(key) != -1
                            ? crrElement.getAsDouble()
                            : crrElement.getAsString();
                }
            }
        }

        return null;
    }
}
