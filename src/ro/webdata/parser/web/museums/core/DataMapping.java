package ro.webdata.parser.web.museums.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ro.webdata.commons.JsonUtils;
import ro.webdata.parser.web.museums.commons.constants.BackendAccessors;
import ro.webdata.parser.web.museums.commons.constants.CimecAccessors;
import ro.webdata.parser.web.museums.commons.constants.InpAccessors;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DataMapping {
    /**
     * Map the keys from CIMEC and data.gov.ro dataset to backend accessors
     * @param key The key name from the CIMEC or data.gov.ro dataset
     * @return The backend accessor
     */
    public static String getKeyName(String key) {
        switch (key) {
            case CimecAccessors.EN_ACCESS:
            case CimecAccessors.RO_ACCESS:
            case InpAccessors.EN_ACCESS:
            case InpAccessors.RO_ACCESS:
                return BackendAccessors.LOCATION_ACCESS;
            case CimecAccessors.EN_ACCREDITATION:
            case CimecAccessors.RO_ACCREDITATION:
            case InpAccessors.ACCREDITATION:
                return BackendAccessors.MUSEUM_ACCREDITATION;
            case CimecAccessors.EN_ADDRESS:
            case CimecAccessors.RO_ADDRESS:
            case InpAccessors.ADDRESS:
                return BackendAccessors.LOCATION_ADDRESS;
            case InpAccessors.ADMINISTRATIVE_UNIT:
                return BackendAccessors.LOCATION_ADM_UNIT;
            case InpAccessors.EN_BUILDING_DESCRIPTION:
            case InpAccessors.RO_BUILDING_DESCRIPTION:
                return BackendAccessors.BUILDING_DESCRIPTION;
            case CimecAccessors.EN_BUILDING_LMI_CODE:
            case CimecAccessors.RO_BUILDING_LMI_CODE:
            case InpAccessors.BUILDING_LMI_CODE:
                return BackendAccessors.BUILDING_LMI_CODE;
            case CimecAccessors.EN_CATEGORY:
            case CimecAccessors.RO_CATEGORY:
            case InpAccessors.EN_CATEGORY:
            case InpAccessors.RO_CATEGORY:
                return BackendAccessors.COLLECTION_CATEGORY;
            case CimecAccessors.EN_COMMUNE:
            case CimecAccessors.RO_COMMUNE:
                return BackendAccessors.LOCATION_COMMUNE;
            case CimecAccessors.EN_CONTACT_PERSON:
            case CimecAccessors.RO_CONTACT_PERSON:
                return BackendAccessors.CONTACT_PERSON_NAME;
            case CimecAccessors.EN_COUNTY:
            case CimecAccessors.RO_COUNTY:
            case InpAccessors.COUNTY:
                return BackendAccessors.LOCATION_COUNTY;
            case CimecAccessors.EN_DESCRIPTION:
            case CimecAccessors.RO_DESCRIPTION:
            case InpAccessors.EN_DESCRIPTION:
            case InpAccessors.RO_DESCRIPTION:
                return BackendAccessors.DESCRIPTION_DETAILS;
            case CimecAccessors.DIRECTOR:
            case InpAccessors.DIRECTOR:
                return BackendAccessors.CONTACT_DIRECTOR;
            case CimecAccessors.EN_EMAIL:
            case CimecAccessors.RO_EMAIL:
            case InpAccessors.EMAIL:
                return BackendAccessors.CONTACT_EMAIL;
            case CimecAccessors.FAX:
            case InpAccessors.FAX:
                return BackendAccessors.CONTACT_FAX;
            case CimecAccessors.EN_FOUNDED:
            case CimecAccessors.RO_FOUNDED:
            case InpAccessors.FOUNDED:
                return BackendAccessors.MUSEUM_FOUNDED;
            case CimecAccessors.EN_GENERAL_PROFILE:
            case CimecAccessors.RO_GENERAL_PROFILE:
                return BackendAccessors.COLLECTION_GENERAL_PROFILE;
            case InpAccessors.GEO_LATITUDE:
                return BackendAccessors.LOCATION_GEO_LATITUDE;
            case InpAccessors.GEO_LONGITUDE:
                return BackendAccessors.LOCATION_GEO_LONGITUDE;
            case InpAccessors.GEO_TARGET:
                return BackendAccessors.LOCATION_GEO_TARGET;
            case InpAccessors.EN_HISTORIC:
            case InpAccessors.RO_HISTORIC:
                return BackendAccessors.DESCRIPTION_HISTORIC;
            case CimecAccessors.EN_LOCALITY_NAME:
            case CimecAccessors.RO_LOCALITY_NAME:
            case InpAccessors.LOCALITY_NAME:
                return BackendAccessors.LOCATION_LOCALITY_NAME;
            case InpAccessors.LOCALITY_CODE:
                return BackendAccessors.LOCATION_LOCALITY_CODE;
            case CimecAccessors.EN_MAIN_PROFILE:
            case CimecAccessors.RO_MAIN_PROFILE:
            case InpAccessors.EN_MAIN_PROFILE:
            case InpAccessors.RO_MAIN_PROFILE:
                return BackendAccessors.COLLECTION_MAIN_PROFILE;
            case CimecAccessors.EN_MUSEUM_CODE:
            case CimecAccessors.RO_MUSEUM_CODE:
            case InpAccessors.MUSEUM_CODE:
                return BackendAccessors.MUSEUM_CODE;
            case CimecAccessors.EN_MUSEUM_NAME:
            case CimecAccessors.RO_MUSEUM_NAME:
            case InpAccessors.EN_MUSEUM_NAME:
            case InpAccessors.RO_MUSEUM_NAME:
                return BackendAccessors.MUSEUM_NAME;
            case CimecAccessors.EN_ONLINE_PUBLICATIONS:
            case CimecAccessors.RO_ONLINE_PUBLICATIONS:
                return BackendAccessors.PUBLICATIONS_ONLINE;
            case CimecAccessors.EN_POSITION:
            case CimecAccessors.RO_POSITION:
                return BackendAccessors.CONTACT_PERSON_POSITION;
            case CimecAccessors.EN_POSTAL_CODE:
            case CimecAccessors.RO_POSTAL_CODE:
            case InpAccessors.POSTAL_CODE:
                return BackendAccessors.LOCATION_ZIP_CODE;
            case CimecAccessors.EN_PUBLICATIONS:
            case CimecAccessors.RO_PUBLICATIONS:
                return BackendAccessors.PUBLICATIONS_MATERIAL;
            case CimecAccessors.EN_SOCIAL_MEDIA:
            case CimecAccessors.RO_SOCIAL_MEDIA:
                return BackendAccessors.CONTACT_SOCIAL_MEDIA;
            case CimecAccessors.EN_SUBORDINATION_CODE:
            case CimecAccessors.RO_SUBORDINATION_CODE:
            case InpAccessors.SUBORDINATION_CODE:
                return BackendAccessors.MUSEUM_SUBORDINATION_CODE;
            case CimecAccessors.EN_SUBORDINATION:
            case CimecAccessors.RO_SUBORDINATION:
                return BackendAccessors.MUSEUM_SUBORDINATION_NAME;
            case InpAccessors.EN_SUMMARY:
            case InpAccessors.RO_SUMMARY:
                return BackendAccessors.DESCRIPTION_SUMMARY;
            case CimecAccessors.EN_TELEPHONE:
            case CimecAccessors.RO_TELEPHONE:
            case InpAccessors.PHONE:
                return BackendAccessors.CONTACT_PHONE;
            case CimecAccessors.EN_TIME_TABLE:
            case CimecAccessors.RO_TIME_TABLE:
            case InpAccessors.EN_TIME_TABLE:
            case InpAccessors.RO_TIME_TABLE:
                return BackendAccessors.CONTACT_TIME_TABLE;
            case CimecAccessors.EN_VIRTUAL_TOUR:
            case CimecAccessors.RO_VIRTUAL_TOUR:
                return BackendAccessors.CONTACT_VIRTUAL_TOUR;
            case CimecAccessors.EN_WEB:
            case CimecAccessors.RO_WEB:
            case InpAccessors.WEB:
                return BackendAccessors.CONTACT_WEB;
            default:
                return key;
        }
    }

    public static void printKeys(String path, String lang) {
        Set<String> keys = getKeys(path);

        System.out.println("LANG: " + lang);
        for (String key: keys) {
            System.out.println(key);
        }
        System.out.println();
    }

    /**
     * Get the unique CIMEC keys extracted from the parsed dataset
     * @param path The path to the input file
     * @return The list of unique keys
     */
    public static Set<String> getKeys(String path) {
        Set<String> keys = new TreeSet<>();
        JsonArray array = JsonUtils.getJsonArray(path);

        for (JsonElement element: array) {
            JsonObject object = element.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();

            for (Map.Entry<String, JsonElement> entry: entrySet) {
                keys.add(entry.getKey());
            }
        }

        return keys;
    }
}
