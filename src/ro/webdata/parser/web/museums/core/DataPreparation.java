package ro.webdata.parser.web.museums.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ro.webdata.commons.FileUtils;
import ro.webdata.commons.JsonUtils;
import ro.webdata.parser.web.museums.commons.FilePath;
import ro.webdata.parser.web.museums.commons.constants.BackendAccessors;

public class DataPreparation {
    public static void prepareGeoLocation(String lang) {
        String inputPath = FilePath.getFinalJsonPath(lang);
        String outputPath = FilePath.getGeoLocationJsonPath(lang);

        JsonArray rawArray = JsonUtils.getJsonArray(inputPath);
        JsonArray preparedArray = new JsonArray();

        for (int i = 0; i < rawArray.size(); i++) {
            JsonElement rawElement = rawArray.get(i);
            JsonElement preparedElement = DataPreparation.prepareElement(rawElement);
            preparedArray.add(preparedElement);
        }

        FileUtils.write(preparedArray.toString(), outputPath, false);
    }

    private static JsonElement prepareElement(JsonElement element) {
        JsonObject preparedObject = new JsonObject();
        JsonObject object = element.getAsJsonObject();

        JsonElement code = object.get(BackendAccessors.CODE);
        preparedObject.add(BackendAccessors.CODE, code);

        JsonElement name = object.get(BackendAccessors.MUSEUM_NAME);
        preparedObject.add(BackendAccessors.MUSEUM_NAME, name);

        JsonElement location = object.get(BackendAccessors.LOCATION);
        JsonElement geo = location.getAsJsonObject()
                .get(BackendAccessors.GEO);
        JsonObject preparedGeo = new JsonObject();
        preparedGeo.add(BackendAccessors.GEO, geo);
        preparedObject.add(BackendAccessors.LOCATION, preparedGeo);

        return preparedObject;
    }
}
