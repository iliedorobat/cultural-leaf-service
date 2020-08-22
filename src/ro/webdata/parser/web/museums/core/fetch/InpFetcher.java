package ro.webdata.parser.web.museums.core.fetch;

import ro.webdata.parser.web.museums.commons.FilePath;
import ro.webdata.parser.web.museums.commons.constants.BackendAccessors;
import ro.webdata.parser.web.museums.commons.constants.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InpFetcher {
    public static ArrayList<TreeMap<String, String>> getPairs() {
        ArrayList<TreeMap<String, String>> list = new ArrayList<>();
        String path = FilePath.getInpGuidePath();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String readLine;
            int lineIndex = 0;

            while ((readLine = br.readLine()) != null) {
                // The first line contains only the keys
                if (readLine.trim().length() > 0 && lineIndex > 0) {
                    TreeMap<String, String> pairs = getEntryPairs(readLine);
                    if (!pairs.isEmpty()) {
                        list.add(pairs);
                    }
                }
                lineIndex++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + path + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + path + " from the disk.");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        return list;
    }

    private static TreeMap<String, String> getEntryPairs(String csvLine) {
        TreeMap<String, String> pairs = new TreeMap<>();
        ArrayList<String> keys = new ArrayList<>(getKeys());
        String[] values = csvLine.split(FilePath.SEPARATOR_PIPE);

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null && !values[i].equals(""))
                pairs.put(keys.get(i), values[i]);
        }

        return pairs;
    }

    public static LinkedHashSet<String> getKeys() {
        LinkedHashSet<String> keys = new LinkedHashSet<>();
        String path = FilePath.getInpGuidePath();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String readLine;
            int lineIndex = 0;

            while ((readLine = br.readLine()) != null) {
                // The first line contains only the keys
                if (readLine.trim().length() > 0 && lineIndex == 0) {
                    String[] array = readLine.split(FilePath.SEPARATOR_PIPE);
                    keys.addAll(Arrays.asList(array));
                }
                lineIndex++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file " + path + " has not been found.");
        } catch (IOException e) {
            System.err.println("Error at reading the file " + path + " from the disk.");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.err.println("The file 'Buffered Reader' could not be closed.");
            }
        }

        return keys;
    }




    public static Map<String, String> getInpEntry(String inputLine, String lang) {
        String[] array = inputLine.split(FilePath.SEPARATOR_PIPE);
        Map<String, String> entryMap = new HashMap<>();

        addInpEntry(entryMap, BackendAccessors.MUSEUM_ACCREDITATION, array, 37);
        addInpEntry(entryMap, BackendAccessors.MUSEUM_CODE, array, 0);
        addInpEntry(entryMap, BackendAccessors.MUSEUM_FOUNDED, array, 17);
        addInpEntry(entryMap, BackendAccessors.MUSEUM_SUBORDINATION_CODE, array, 30);

        addInpEntry(entryMap, BackendAccessors.BUILDING_LMI_CODE, array, 33);

        addInpEntry(entryMap, BackendAccessors.CONTACT_DIRECTOR, array, 29);
        addInpEntry(entryMap, BackendAccessors.CONTACT_EMAIL, array, 28);
        addInpEntry(entryMap, BackendAccessors.CONTACT_FAX, array, 13);
        addInpEntry(entryMap, BackendAccessors.CONTACT_PHONE, array, 11);
        addInpEntry(entryMap, BackendAccessors.CONTACT_WEB, array, 27);

        addInpEntry(entryMap, BackendAccessors.LOCATION_ADDRESS, array, 9);
        addInpEntry(entryMap, BackendAccessors.LOCATION_ADM_UNIT, array, 8);
        addInpEntry(entryMap, BackendAccessors.LOCATION_COUNTY, array, 2);
        addInpEntry(entryMap, BackendAccessors.LOCATION_GEO_LATITUDE, array, 40);
        addInpEntry(entryMap, BackendAccessors.LOCATION_GEO_LONGITUDE, array, 41);
        addInpEntry(entryMap, BackendAccessors.LOCATION_GEO_TARGET, array, 42);

        addInpEntry(entryMap, BackendAccessors.LOCATION_LOCALITY_CODE, array, 34);
        addInpEntry(entryMap, BackendAccessors.LOCATION_LOCALITY_NAME, array, 7);
        addInpEntry(entryMap, BackendAccessors.LOCATION_ZIP_CODE, array, 10);

        if (lang.equals(Constants.LANG_EN)) {
            addInpEntry(entryMap, BackendAccessors.MUSEUM_NAME, array, 5);

            addInpEntry(entryMap, BackendAccessors.BUILDING_DESCRIPTION, array, 22);
            addInpEntry(entryMap, BackendAccessors.COLLECTION_CATEGORY, array, 39);
            addInpEntry(entryMap, BackendAccessors.COLLECTION_MAIN_PROFILE, array, 36);
            addInpEntry(entryMap, BackendAccessors.CONTACT_TIME_TABLE, array, 16);
            addInpEntry(entryMap, BackendAccessors.DESCRIPTION_DETAILS, array, 31);
            addInpEntry(entryMap, BackendAccessors.DESCRIPTION_SUMMARY, array, 20);
            addInpEntry(entryMap, BackendAccessors.DESCRIPTION_HISTORIC, array, 24);
            addInpEntry(entryMap, BackendAccessors.LOCATION_ACCESS, array, 26);
        } else if (lang.equals(Constants.LANG_RO)) {
            addInpEntry(entryMap, BackendAccessors.MUSEUM_NAME, array, 3);

            addInpEntry(entryMap, BackendAccessors.BUILDING_DESCRIPTION, array, 21);
            addInpEntry(entryMap, BackendAccessors.COLLECTION_CATEGORY, array, 38);
            addInpEntry(entryMap, BackendAccessors.COLLECTION_MAIN_PROFILE, array, 32);
            addInpEntry(entryMap, BackendAccessors.CONTACT_TIME_TABLE, array, 15);

            addInpEntry(entryMap, BackendAccessors.DESCRIPTION_DETAILS, array, 18);
            addInpEntry(entryMap, BackendAccessors.DESCRIPTION_SUMMARY, array, 19);
            addInpEntry(entryMap, BackendAccessors.DESCRIPTION_HISTORIC, array, 23);
            addInpEntry(entryMap, BackendAccessors.LOCATION_ACCESS, array, 25);
        } else {
            System.err.println("The \"" + lang + "\" language is not supported.");
        }

        return entryMap;
    }

    private static void addInpEntry(Map<String, String> entryMap, String key, String[] values, int index) {
        if (values.length > index)
            entryMap.put(key, values[index]);
    }
}
