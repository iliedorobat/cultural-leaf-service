package ro.webdata.parser.web.museums.core.fetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ro.webdata.parser.web.museums.commons.FilePath;
import ro.webdata.commons.FileUtils;
import ro.webdata.commons.ThreadUtils;
import ro.webdata.parser.web.museums.commons.constants.CimecValues;
import ro.webdata.parser.web.museums.commons.constants.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class CimecFetcher {
    private static final String BASE_URI = "http://ghidulmuzeelor.cimec.ro/";
    private static final String EN_MUSEUM_PATH = "muzeeen";
    private static final String RO_MUSEUM_PATH = "muzee";
    /** The number of times the application should try to get data if an error has been encountered */
    private static final int ERROR_THRESHOLD = 5;
    private static final int DEFAULT_SLEEP_TIME = 100;
    private static final int ERROR_SLEEP_TIME = 5000;

    /**
     * Prepare the list of key-value pairs from the CIMEC dataset
     * @param lang The language used
     * @return The list of key-value pairs
     */
    public static ArrayList<TreeMap<String, String>> getPairs(String lang) {
        ArrayList<TreeMap<String, String>> list = new ArrayList<>();
        Elements anchors = getAnchors(lang);

        for (int index = 0; index < anchors.size(); index++) {
            ThreadUtils.sleep(index, DEFAULT_SLEEP_TIME);
            Element anchor = anchors.get(index);
            int errorCounter = 0;

            TreeMap<String, String> pairs = getEntryPairs(anchor, lang, index, errorCounter);
            if (!pairs.isEmpty()) {
                list.add(pairs);
            }
        }

        return list;
    }

    /**
     * Try to get the key-value pairs. If fails, get an empty map.
     * @param anchor The element which represents the anchor
     * @param lang The language used
     * @param index The index of the anchor
     * @param errorCounter How many errors has been encountered
     * @return The map which contains key-value pairs or an empty map
     */
    private static TreeMap<String, String> getEntryPairs(
            Element anchor,
            String lang,
            int index,
            int errorCounter
    ) {
        TreeMap<String, String> pairs = new TreeMap<>();
        // There are some cases when the anchor href contains carriage return. E.g.:
        // iden.asp?k=1957&-Colectia-Publica-din-cadrul-Universitatii-„1-Decembrie
        // 1918”-ALBA-IULIA-Alba
        String uri = BASE_URI + anchor.attr("href").replaceAll("\\s", "");

        try {
            System.out.println("Getting record no. " + index + "...");

            Document doc = Jsoup.connect(uri).get();
            Elements tables = doc.select("table.tabeladate");
            Elements trs = tables.select("tbody > tr");

            for (Element tr: trs) {
                addEntryPair(pairs, tr, lang);
            }
        } catch (IOException e) {
            System.err.println("Trying again to get the record no. " + index);

            if (errorCounter < ERROR_THRESHOLD) {
                ThreadUtils.sleep(ERROR_SLEEP_TIME);
                getEntryPairs(anchor, lang, index, ++errorCounter);
            } else {
                System.err.println("Parsing ERROR (index = " + index + ")\n" + uri);

                String path = FilePath.getErrorFilePath();
                String output = "lang = " + lang + "\tindex = " + index;
                FileUtils.write(output, path, true);

                return pairs;
            }
        }

        return pairs;
    }

    /**
     * Get the key-value pair for a record
     * @param pairs The key-value pairs
     * @param tr The record (a table row)
     * @param lang The language used
     */
    private static void addEntryPair(TreeMap<String, String> pairs, Element tr, String lang) {
        Elements tds = tr.select("td");

        // Ignore the records which are not key-value pairs
        if (tds.size() == 2) {
            String propName = tds.get(0).text().toLowerCase();
            boolean isMapRecord = lang.equals(Constants.LANG_EN) && propName.equals(CimecValues.EN_MAP_IT_LC) ||
                    lang.equals(Constants.LANG_RO) && propName.equals(CimecValues.RO_MAP_IT_LC);

            // Ignore the records which contain a link to the CIMEC map
            if (!isMapRecord) {
                pairs.put(tds.get(0).text(), tds.get(1).text());
            }
        }
    }

    /**
     * Get the anchor tags which contains the link to detail pages
     * @param lang The language used
     * @return The list of links
     */
    private static Elements getAnchors(String lang) {
        String museumPath = lang.equals(Constants.LANG_EN)
                ? EN_MUSEUM_PATH
                : RO_MUSEUM_PATH;
        String uri = BASE_URI + museumPath + FilePath.EXTENSION_ASP;
        Elements anchors = new Elements();

        try {
            Document doc = Jsoup.connect(uri).get();
            Element table = doc.select("body > table").get(3);
            anchors = table.select("tbody > tr > td a.fontlink");

            for (int i = anchors.size() -1; i > -1; i--) {
                String text = anchors.get(i).text().toLowerCase();
                boolean hasDetails = lang.equals(Constants.LANG_EN) && !text.equals(CimecValues.EN_DETAILS_LC) ||
                        lang.equals(Constants.LANG_RO) && !text.equals(CimecValues.RO_DETAILS_LC);

                // Keep only the links to the detail pages
                if (hasDetails) {
                    anchors.remove(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return anchors;
    }
}
