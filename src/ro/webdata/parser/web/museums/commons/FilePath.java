package ro.webdata.parser.web.museums.commons;

import org.apache.commons.lang3.StringUtils;

public class FilePath {
    public static final String EXTENSION_ASP = ".asp";
    public static final String EXTENSION_CSV = ".csv";
    public static final String EXTENSION_JSON = ".json";
    public static final String EXTENSION_TXT = ".txt";
    public static final String SEPARATOR_PIPE = "\\|";

    private static final String MAIN_INPUT_PATH = "files/input/web/";
    private static final String MAIN_OUTPUT_PATH = "files/output/web/";

    private static final String DIRECTORY_RAW_DATA = "raw-data/";
    private static final String DIRECTORY_PREPARED_DATA = "prepared-data/";

    private static final String PREFIX_CIMEC = "cimec";
    private static final String PREFIX_INP = "inp";
    private static final String PREFIX_GEO_LOCATION = "geoLocation";
    private static final String PREFIX_MERGED = "merged";
    private static final String FILE_NAME_INP_GUIDE = "inp-ghidul-muzelor-2017-05-18";
    private static final String FILE_NAME_ERROR = "Error";
    private static final String FILE_NAME_PREPARED_DATA = "PreparedData";
    private static final String FILE_NAME_RAW_DATA = "RawData";

    public static String getErrorFilePath() {
        return FilePath.MAIN_OUTPUT_PATH
                + FilePath.FILE_NAME_ERROR
                + FilePath.EXTENSION_TXT;
    }

    public static String getInpGuidePath() {
        return MAIN_INPUT_PATH
                + FILE_NAME_INP_GUIDE
                + EXTENSION_CSV;
    }

    public static String getCimecPreparedJsonPath(String lang) {
        return MAIN_OUTPUT_PATH
                + DIRECTORY_PREPARED_DATA
                + PREFIX_CIMEC
                + StringUtils.capitalize(lang)
                + FILE_NAME_PREPARED_DATA
                + EXTENSION_JSON;
    }

    public static String getCimecRawJsonPath(String lang) {
        return MAIN_OUTPUT_PATH
                + DIRECTORY_RAW_DATA
                + PREFIX_CIMEC
                + StringUtils.capitalize(lang)
                + FILE_NAME_RAW_DATA
                + EXTENSION_JSON;
    }

    public static String getInpRawJsonPath() {
        return MAIN_OUTPUT_PATH
                + DIRECTORY_RAW_DATA
                + PREFIX_INP
                + FILE_NAME_RAW_DATA
                + EXTENSION_JSON;
    }

    public static String getInpPreparedJsonPath() {
        return MAIN_OUTPUT_PATH
                + DIRECTORY_PREPARED_DATA
                + PREFIX_INP
                + FILE_NAME_PREPARED_DATA
                + EXTENSION_JSON;
    }

    public static String getFinalJsonPath(String lang) {
        return MAIN_OUTPUT_PATH
                + DIRECTORY_PREPARED_DATA
                + PREFIX_MERGED
                + StringUtils.capitalize(lang)
                + FILE_NAME_PREPARED_DATA
                + EXTENSION_JSON;
    }

    public static String getGeoLocationJsonPath(String lang) {
        return MAIN_OUTPUT_PATH
                + DIRECTORY_PREPARED_DATA
                + PREFIX_GEO_LOCATION
                + StringUtils.capitalize(lang)
                + FILE_NAME_PREPARED_DATA
                + EXTENSION_JSON;
    }
}
