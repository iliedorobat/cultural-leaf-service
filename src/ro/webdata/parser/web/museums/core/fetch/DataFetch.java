package ro.webdata.parser.web.museums.core.fetch;

import com.google.gson.Gson;
import ro.webdata.commons.FileUtils;
import ro.webdata.parser.web.museums.commons.FilePath;
import ro.webdata.parser.web.museums.commons.constants.InpAccessors;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Download the raw data:
 * <ul>
 *     <li>CIMEC dataset: http://ghidulmuzeelor.cimec.ro/</li>
 *     <li>TODO: INP dataset: https://data.gov.ro/dataset/ghidul-muzeelor-din-romania</li>
 * </ul>
 */
public class DataFetch {
    private static Gson gson = new Gson();

    public static void writeCimecJson(String lang) {
        String path = FilePath.getCimecRawJsonPath(lang);
        ArrayList<TreeMap<String, String>> pairs = CimecFetcher.getPairs(lang);
        String json = gson.toJson(pairs);
        FileUtils.write(json, path, false);
    }

    public static void writeInpJson() {
        String path = FilePath.getInpRawJsonPath();
        ArrayList<TreeMap<String, String>> pairs = InpFetcher.getPairs();

//        // FIXME: remove the code below. It's just for testing
//        for (int i = 0; i < pairs.size(); i++) {
//            if (!pairs.get(i).get(InpAccessors.MUSEUM_CODE).equals("7300407")) {
//                pairs.remove(pairs.get(i));
//            }
//        }

        String json = gson.toJson(pairs);
        FileUtils.write(json, path, false);
    }
}
