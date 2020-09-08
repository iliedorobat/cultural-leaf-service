package ro.webdata.parser.web.museums.commons.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class BackendAccessors {
    public static final String SEPARATOR = ".";
    public static final String ESCAPED_SEPARATOR = "\\.";

    private static final String ACCESS = "access";
    private static final String ACCREDITATION = "accreditation";
    private static final String ADDRESS = "address";
    private static final String ADMINISTRATIVE = "administrative";
    private static final String AGENT = "agent";
    private static final String BUILDING = "building";
    private static final String CATEGORY = "category";
    public static final String CODE = "code";
    private static final String COLLECTION = "collection";
    private static final String COMMUNE = "commune";
    private static final String CONTACT = "contact";
    private static final String COUNTY = "county";
    private static final String DESCRIPTION = "description";
    private static final String DETAILS = "details";
    private static final String DIRECTOR = "director";
    private static final String EMAIL = "email";
    private static final String FAX = "fax";
    private static final String FOUNDED = "founded";
    private static final String GENERAL = "general";
    public static final String GEO = "geo";
    private static final String HISTORIC = "historic";
    private static final String LATITUDE = "latitude";
    private static final String LMI_CODE = "lmiCode";
    private static final String LOCALITY = "locality";
    public static final String LOCATION = "location";
    private static final String LONGITUDE = "longitude";
    private static final String MAIN = "main";
    private static final String MATERIAL = "material";
    private static final String NAME = "name";
    private static final String ONLINE = "online";
    private static final String PHONE = "phone";
    private static final String POSITION = "position";
    private static final String PUBLICATIONS = "publications";
    private static final String PROFILE = "profile";
    private static final String SOCIAL_MEDIA = "socialMedia";
    private static final String SUBORDINATION = "subordination";
    private static final String SUMMARY = "summary";
    private static final String TARGET = "target";
    private static final String TIMETABLE = "timetable";
    private static final String VIRTUAL_TOUR = "virtualTour";
    private static final String WEB = "web";
    private static final String ZIP_CODE = "zipCode";

    public static ArrayList<String> ALLOWED_ACCESSORS = new ArrayList<>(
            Arrays.asList(
                    ACCESS,
                    ACCREDITATION,
                    ADDRESS,
                    ADMINISTRATIVE,
                    AGENT,
                    BUILDING,
                    CATEGORY,
                    CODE,
                    COLLECTION,
                    COMMUNE,
                    CONTACT,
                    COUNTY,
                    DESCRIPTION,
                    DETAILS,
                    DIRECTOR,
                    EMAIL,
                    FAX,
                    FOUNDED,
                    GENERAL,
                    GEO,
                    HISTORIC,
                    LATITUDE,
                    LMI_CODE,
                    LOCALITY,
                    LOCATION,
                    LONGITUDE,
                    MAIN,
                    NAME,
                    MATERIAL,
                    ONLINE,
                    PHONE,
                    POSITION,
                    PROFILE,
                    PUBLICATIONS,
                    SOCIAL_MEDIA,
                    SUBORDINATION,
                    SUMMARY,
                    TARGET,
                    TIMETABLE,
                    VIRTUAL_TOUR,
                    WEB,
                    ZIP_CODE
            )
    );

    public static final ArrayList<String> DOUBLE_VALUE_ACCESSORS = new ArrayList<>(
            Arrays.asList(
                    LATITUDE,
                    LONGITUDE
            )
    );

    public static final String MUSEUM_ACCREDITATION = ACCREDITATION;
    public static final String MUSEUM_FOUNDED = FOUNDED;
    public static final String MUSEUM_CODE = CODE;
    public static final String MUSEUM_NAME = NAME;
    public static final String MUSEUM_SUBORDINATION_CODE = SUBORDINATION + SEPARATOR + CODE; // data.gov.ro & cimec processing
    public static final String MUSEUM_SUBORDINATION_NAME = SUBORDINATION + SEPARATOR + NAME;

    public static final String BUILDING_DESCRIPTION = BUILDING + SEPARATOR + DESCRIPTION; // data.gov.ro
    public static final String BUILDING_LMI_CODE = BUILDING + SEPARATOR + LMI_CODE;

    public static final String COLLECTION_CATEGORY = COLLECTION + SEPARATOR + CATEGORY;
    public static final String COLLECTION_GENERAL_PROFILE = COLLECTION + SEPARATOR + PROFILE + SEPARATOR + GENERAL;
    public static final String COLLECTION_MAIN_PROFILE = COLLECTION + SEPARATOR + PROFILE + SEPARATOR + MAIN;

    public static final String CONTACT_PERSON_NAME = CONTACT + SEPARATOR + AGENT + SEPARATOR + NAME;
    public static final String CONTACT_DIRECTOR = CONTACT + SEPARATOR + DIRECTOR;
    public static final String CONTACT_EMAIL = CONTACT + SEPARATOR + EMAIL;
    public static final String CONTACT_FAX = CONTACT + SEPARATOR + FAX;
    public static final String CONTACT_PERSON_POSITION = CONTACT + SEPARATOR + AGENT + SEPARATOR + POSITION;
    public static final String CONTACT_SOCIAL_MEDIA = CONTACT + SEPARATOR + SOCIAL_MEDIA;
    public static final String CONTACT_PHONE = CONTACT + SEPARATOR + PHONE;
    public static final String CONTACT_TIME_TABLE = CONTACT + SEPARATOR + TIMETABLE;
    public static final String CONTACT_VIRTUAL_TOUR = CONTACT + SEPARATOR + VIRTUAL_TOUR;
    public static final String CONTACT_WEB = CONTACT + SEPARATOR + WEB;

    public static final String DESCRIPTION_DETAILS = DESCRIPTION + SEPARATOR + DETAILS;
    public static final String DESCRIPTION_HISTORIC = DESCRIPTION + SEPARATOR + HISTORIC; // data.gov.ro
    public static final String DESCRIPTION_SUMMARY = DESCRIPTION + SEPARATOR + SUMMARY; // data.gov.ro

    public static final String LOCATION_ACCESS = LOCATION + SEPARATOR + ACCESS;
    public static final String LOCATION_ADDRESS = LOCATION + SEPARATOR + ADDRESS;
    public static final String LOCATION_ADM_UNIT = LOCATION + SEPARATOR + ADMINISTRATIVE; // data.gov.ro
    public static final String LOCATION_COMMUNE = LOCATION + SEPARATOR + COMMUNE;
    public static final String LOCATION_COUNTY = LOCATION + SEPARATOR + COUNTY;
    public static final String LOCATION_GEO_LATITUDE = LOCATION + SEPARATOR + GEO + SEPARATOR + LATITUDE; // data.gov.ro
    public static final String LOCATION_GEO_LONGITUDE = LOCATION + SEPARATOR + GEO + SEPARATOR + LONGITUDE; // data.gov.ro
    public static final String LOCATION_GEO_TARGET = LOCATION + SEPARATOR + GEO + SEPARATOR + TARGET; // data.gov.ro
    public static final String LOCATION_LOCALITY_NAME = LOCATION + SEPARATOR + LOCALITY + SEPARATOR + NAME;
    public static final String LOCATION_LOCALITY_CODE = LOCATION + SEPARATOR + LOCALITY + SEPARATOR + CODE; // data.gov.ro = SIRUTA code
    public static final String LOCATION_ZIP_CODE = LOCATION + SEPARATOR + ZIP_CODE;

    public static final String PUBLICATIONS_MATERIAL = PUBLICATIONS + SEPARATOR + MATERIAL;
    public static final String PUBLICATIONS_ONLINE = PUBLICATIONS + SEPARATOR + ONLINE;
}
