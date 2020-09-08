package ro.webdata.parser.web.museums.core;

import ro.webdata.parser.web.museums.commons.FilePath;
import ro.webdata.parser.web.museums.commons.constants.Constants;
import ro.webdata.parser.web.museums.core.curation.DataCuration;
import ro.webdata.parser.web.museums.core.fetch.DataFetch;

public class Parser {
    public static void parse() {
        System.out.println("START\n");

        // 1. Get the raw data from the data.gov.ro portal
        // Download manually the "inp-ghidul-muzelor-2017-05-18.csv" file
        // from https://data.gov.ro/dataset/ghidul-muzeelor-din-romania

        // 2. Get the CIMEC and INP raw data
        DataFetch.writeCimecJson(Constants.LANG_EN);
        DataFetch.writeCimecJson(Constants.LANG_RO);
        DataFetch.writeInpJson();

        // 3. Get the CIMEC and INP keys to build the DataMapping.getKeyName mapping method
        DataMapping.printKeys(
                FilePath.getCimecRawJsonPath(Constants.LANG_EN),
                Constants.LANG_EN
        );
        DataMapping.printKeys(
                FilePath.getCimecRawJsonPath(Constants.LANG_RO),
                Constants.LANG_RO
        );
        DataMapping.printKeys(
                FilePath.getInpRawJsonPath(),
                Constants.LANG_ALL
        );

        // 4. Prepare the data
        DataCuration.writeCimecJson(Constants.LANG_EN);
        DataCuration.writeCimecJson(Constants.LANG_RO);
        DataCuration.writeInpJson();

        // 5. Merge the CIMEC and INP dataset
        DataConsolidation.writeFinalJson(Constants.LANG_EN);
        DataConsolidation.writeFinalJson(Constants.LANG_RO);

        DataPreparation.prepareGeoLocation(Constants.LANG_EN);
        DataPreparation.prepareGeoLocation(Constants.LANG_RO);

        System.out.println("\nEND");
    }
}
