package ro.webdata.humanities.server.endpoint.cho.filter.nature;

import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.FilterUtils;
import ro.webdata.humanities.server.commons.sparql.Sparql;

public class NatureQuery {
    public static String prepareAgeFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.NATURE_AGE, true);
        String values;

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        switch (value) {
            case AGE._23_MIL:
                values = "'23 MIL'";
                break;
            case AGE._15_MIL:
                values = "'23 mil'";
                break;
            case AGE.AD:
                values = "'AD', 'AD (adult)', 'AD SBD'";
                break;
            case AGE.EGG:
                values = "'ou'";
                break;
            case AGE.JUV:
                values = "'JUV'";
                break;
            default:
                return null;
        }

        return String.format("FILTER(str(%s) IN (%s))", varName, values);
    }

    public static String prepareEpochFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.NATURE_EPOCH, true);
        String values;

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        switch (value) {
            case EPOCH.MEZOZOIS_CRETACIC_MAASTRICHTIAN:
                values = "'Mezozois, Cretacic, Maastrichtian'";
                break;
            case EPOCH.NEOGEN:
                values = "'neogen'";
                break;
            case EPOCH.NEOZOIC_NEOGEN_SFARSITUL_ETAJULUI_BADENIAN_CCA_11MA:
                values = "'Neozoic, Neogen, sfârșitul etajului Badenian (cca. 11MA)'";
                break;
            case EPOCH.PLEISTOCEN_MEDIU:
                values = "'Pleistocen mediu'";
                break;
            default:
                return null;
        }

        return String.format("FILTER(str(%s) IN (%s))", varName, values);
    }

    public static String prepareSexFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.NATURE_SEX, true);
        String values;

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        switch (value) {
            case SEX.MALE:
                values = "'mascul', '2 masculi', '4 masculi'";
                break;
            case SEX.FEMALE:
                values = "'femel', 'femelă', 'șase femele'";
                break;
            case SEX.MANCA:
                values = "'manca'";
                break;
            case SEX.POSTMANCA:
                values = "'postmanca'";
                break;
            case SEX.UNISEX:
                values = "'nouă femele', 'patru masculi, trei femele', 'femelă, mascul', " +
                        "'un mascul, trei femele', 'un mascul, o femelă', 'un mascul, șase famele', " +
                        "'optisprezece masculi, ;ase femele', '6 masculi, 20 femele', 'un mascul, două femele', " +
                        "'opt masculi, două femele', 'un mascul, o femela', 'doi masculi, două femele', " +
                        "'1 mascul, 1 femelă', 'trei masculi, o femelă', 'trei masculi, trei femele', " +
                        "'unsprezece masculi, opt femele', 'un mascul, patru femele', 'patru masculi, zece femele', " +
                        "'8 masculi, 10 femele', 'trei masculi, nouă femele', 'doi masculi, o femelă', " +
                        "'opt masculi, dou[ femele', 'opt masculi, șaptisprezece femele', 'un mascul, opt femele', " +
                        "'trei femele', '5 masculi, 7 femele', 'opt masculi, două', 'opt masculi, 23 femele'";
                break;
            case SEX.UNKNOWN:
                values = "'nedeterminat'";
                break;
            default:
                return null;
        }

        return String.format("FILTER(str(%s) IN (%s))", varName, values);
    }
}

class AGE {
    public static final String _23_MIL = "23 MIL";
    public static final String _15_MIL = "15 MIL";
    public static final String AD = "AD";
    public static final String EGG = "EGG";
    public static final String JUV = "JUV";
}

class EPOCH {
    public static final String MEZOZOIS_CRETACIC_MAASTRICHTIAN = "Mezozois, Cretacic, Maastrichtian";
    public static final String NEOGEN = "Neogen";
    public static final String NEOZOIC_NEOGEN_SFARSITUL_ETAJULUI_BADENIAN_CCA_11MA = "Neozoic, Neogen, sfârșitul etajului Badenian (cca. 11MA)";
    public static final String PLEISTOCEN_MEDIU = "Pleistocen mediu";
}

class SEX {
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public static final String MANCA = "Manca";
    public static final String POSTMANCA = "Postmanca";
    public static final String UNISEX = "Unisex"; // male & female
    public static final String UNKNOWN = "Unknown";
}
