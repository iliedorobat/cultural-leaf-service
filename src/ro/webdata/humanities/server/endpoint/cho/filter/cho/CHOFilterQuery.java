package ro.webdata.humanities.server.endpoint.cho.filter.cho;

import ro.webdata.echo.commons.Text;
import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.FilterUtils;
import ro.webdata.humanities.server.commons.sparql.Sparql;

public class CHOFilterQuery {
    public static String prepareCountyFilter(String county) {
        String value = Text.sanitizeString(county, true);
        String varName = Sparql.getVarName(PROP_KEYS.CHO_COUNTY, true);

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        return String.format("FILTER(contains(str(%s), 'county:%s'))", varName, value);
    }

    public static String prepareLocalityFilter(String locality) {
        return null;
    }

    public static String prepareDisplayStateFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.CHO_DISPLAY_STATE, true);
        String values;

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        switch (value) {
            case CHO_DISPLAY_STATE.VERY_GOOD:
                values = "'foarte bună'";
                break;
            case CHO_DISPLAY_STATE.RELATIVELY_GOOD:
                values = "'relativ bună'";
                break;
            case CHO_DISPLAY_STATE.GOOD:
                values = "'bună'";
                break;
            case CHO_DISPLAY_STATE.MEDIOCRE:
                values = "'mediocră'";
                break;
            case CHO_DISPLAY_STATE.DAMAGED:
                values = "'deteriorată'";
                break;
            case CHO_DISPLAY_STATE.VERY_DAMAGED:
                values = "'foarte deteriorată'";
                break;
            default:
                return null;
        }

        return String.format("FILTER(str(%s) = %s)", varName, values);
    }

    public static String prepareInventoryNumberFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.CHO_INVENTORY_NUMBER, true);

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        return String.format("FILTER(contains(%s, \"%s\"))", varName, value);
    }

    public static String prepareTitleFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.CHO_TITLE, true);

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        return String.format("FILTER(contains(%s, \"%s\"))", varName, value);
    }

    public static String prepareTypeFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.CHO_TYPE, true);
        String values;

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        switch (value) {
            case CHO_TYPE.BIOLOGICAL_OBJECT:
                values = "'biological object', 'obiect biologic'";
                break;
            case CHO_TYPE.MAN_MADE_OBJECT:
                values = "'man-made object', 'artefact'";
                break;
            case CHO_TYPE.PHYSICAL_OBJECT:
                values = "'physical object', 'obiect fizic'";
                break;
            default:
                return null;
        }

        return String.format("FILTER(str(%s) IN (%s))", varName, values);
    }

    // TODO:
    public static String prepareEpochFilter(String value) {
        return null;
    }
}

class CHO_COUNTY {
    public static final String ALBA = "Alba";
    public static final String ARAD = "Arad";
    public static final String ARGES = "Argeș";
    public static final String BACAU = "Bacău";
    public static final String BIHOR = "Bihor";
    public static final String BISTRITA_NASAUD = "Bistrița-Năsăud";
    public static final String BOTOSANI = "Botoșani";
    public static final String BRASOV = "Brașov";
    public static final String BRAILA = "Brăila";
    public static final String BUCURESTI = "București";
    public static final String BUZAU = "Buzău";
    public static final String CARAS_SEVERIN = "Caraș-Severin";
    public static final String CALARASI = "Călărași";
    public static final String CLUJ = "Cluj";
    public static final String CONSTANTA = "Constanța";
    public static final String COVASNA = "Covasna";
    public static final String DAMBOVITA = "Dâmbovița";
    public static final String DOLJ = "Dolj";
    public static final String GALATI = "Galați";
    public static final String GIURGIU = "Giurgiu";
    public static final String GORJ = "Gorj";
    public static final String HARGHITA = "Harghita";
    public static final String HUNEDOARA = "Hunedoara";
    public static final String IALOMITA = "Ialomița";
    public static final String IASI = "Iași";
    public static final String ILFOV = "Ilfov";
    public static final String MARAMURES = "Maramureș";
    public static final String MEHEDINTI = "Mehedinți";
    public static final String MURES = "Mureș";
    public static final String NEAMT = "Neamț";
    public static final String OLT = "Olt";
    public static final String PRAHOVA = "Prahova";
    public static final String SATU_MARE = "Satu Mare";
    public static final String SALAJ = "Sălaj";
    public static final String SIBIU = "Sibiu";
    public static final String SUCEAVA = "Suceava";
    public static final String TELEORMAN = "Teleorman";
    public static final String TIMIS = "Timiș";
    public static final String TULCEA = "Tulcea";
    public static final String VASLUI = "Vaslui";
    public static final String VALCEA = "Vâlcea";
    public static final String VRANCEA = "Vrancea";
}

class CHO_DISPLAY_STATE {
    public static final String VERY_GOOD = "Very good";
    public static final String RELATIVELY_GOOD = "Relatively good";
    public static final String GOOD = "Good";
    public static final String MEDIOCRE = "Mediocre";
    public static final String DAMAGED = "Damaged";
    public static final String VERY_DAMAGED = "Very damaged";
}

class CHO_TYPE {
    public static final String BIOLOGICAL_OBJECT = "Biological object";
    public static final String MAN_MADE_OBJECT = "Man-made object";
    public static final String PHYSICAL_OBJECT = "Physical object";
}

// TODO:
class CHO_EPOCH {
    public static final String ANTICHITATE = "Antichitate";
    public static final String CONSTANTIN_BRANCOVEANU = "Constantin Brâncoveanu";
    public static final String CULTURA_SANTANA_DE_MURES_CERNEAHOV = "Cultura Sântana de Mureș-Cerneahov";
    public static final String CULTURA_TUMULILOR_CARPATICI = "Cultura Tumulilor carpatici";
    public static final String CULTURA_DACILOR_LIBERI = "Cultura dacilor liberi";
    public static final String CULTURA_DACO_CARPICA = "Cultura daco-carpică";
    public static final String CULTURA_SARMATICA_TIMPURIE = "Cultura sarmatică timpurie";
    public static final String ENEOLITIC = "Eneolitic";
    public static final String ENEOLITIC_DEZVOLTAT = "Eneolitic dezvoltat";
    public static final String ENEOLITIC_MIJLOCIU = "Eneolitic mijlociu";
    public static final String ENEOLITIC_TIMPURIU = "Eneolitic timpuriu";
    public static final String ENEOLITIC_TARZIU = "Eneolitic târziu";
    public static final String ENEOLITIC_ = "Eneolitic?"; // TODO:
    public static final String EPIPALEOLITIC = "Epipaleolitic";
    public static final String EPOCA_PTOLEMAICA = "Epoca Ptolemaică";
    public static final String EPOCA_TARZIE = "Epoca Târzie";
    public static final String EPOCA_BRONZULUI = "Epoca bronzului";
    public static final String EPOCA_BRONZULUI_HALLSTATT_A1 = "Epoca bronzului - Hallstatt A1";
    public static final String EPOCA_BRONZULUI_ = "Epoca bronzului ?"; // TODO:
    public static final String EPOCA_DACICA = "Epoca dacică";
    public static final String EPOCA_FIERULUI = "Epoca fierului";
    public static final String EPOCA_GREACA = "Epoca greacă";
    public static final String EPOCA_MEDIEVALA = "Epoca medievală";
    public static final String EPOCA_MEDIEVALA_TIMPURIE = "Epoca medievală timpurie";
    public static final String EPOCA_MEDIEVALA_TARZIE = "Epoca medievală târzie";
    public static final String EPOCA_MIGRATIILOR = "Epoca migrațiilor";
    public static final String EPOCA_MIGRATIILOR_TIMPURIE = "Epoca migrațiilor timpurie";
    public static final String EPOCA_MODERNA = "Epoca modernă";
    public static final String EPOCA_OTOMANA = "Epoca otomană";
    public static final String EPOCA_POST_ROMANA = "Epoca post-romană";
    public static final String EPOCA_PREMEDIEVALA = "Epoca premedievală";
    public static final String EPOCA_ROMANA = "Epoca romană";
    public static final String EPOCA_ROMANA_ = "Epoca romană ?"; // TODO:
    public static final String EPOCA_ROMANA_SAU_PERIOADA_MEDIEVALA_TIMPURIE = "Epoca romană sau perioada medievală timpurie";
    public static final String GETO_DACICA = "geto-dacică";
    public static final String HALLSTATT = "Hallstatt";
    public static final String HALLSTATT_A = "Hallstatt A";
    public static final String HALLSTATT_D = "Hallstatt D";
    public static final String HALLSTATT_MIJLOCIU = "Hallstatt mijlociu";
    public static final String HALLSTATT_TIMPURIU = "Hallstatt Timpuriu";
    public static final String HALLSTATT_TIMPURIU_2 = "Hallstatt timpuriu"; // TODO:
    public static final String HALLSTATT_TARZIU = "Hallstatt târziu";
    public static final String HELLADIC_TARZIU = "Helladic târziu";
    public static final String IMPERIUL_MIJLOCIU = "Imperiul Mijlociu";
    public static final String IMPERIUL_NOU = "Imperiul Nou";
    public static final String INCEPUTUL_PERIOADEI_MIJLOCII_A_EPOCII_BRONZULUI = "Începutul perioadei mijlocii a epocii bronzului";
    public static final String JOHANN_HERMANN_I_ZIS_STUCKART_ACTIV_LA_SIBIU_INTRE_ANII_1646_1662 = "Johann Hermann I; zis Stuckart; activ la Sibiu între anii 1646-1662";
    public static final String KLEMENS_AURIFABER_SENIOR_ACTIV_IN_BRASOV_INTRE_ANII_1555_1572 = "Klemens Aurifaber senior; activ în Brașov între anii 1555-1572";
    public static final String LA_TENE = "La Tène";
    public static final String LA_TENE_B1_B2 = "La Tène B1 B2";
    public static final String LA_TENE_B2_C = "La Tène B2-C";
    public static final String LA_TENE_D = "La Tène D";
    public static final String LA_TENE_D1 = "La Tène D1";
    public static final String LA_TENE_III = "La Tène III";
    public static final String LA_TENE_GETO_DACIC = "La Tène geto-dacic";
    public static final String LA_TENE_MIJLOCIU = "La Tène mijlociu";
    public static final String LA_TENE_TIMPURIU = "La Tène timpuriu";
    public static final String MILENIUL_I = "Mileniul I";
    public static final String NEOLITIC = "Neolitic";
    public static final String NEOLITIC_ENEOLITIC = "Neolitic - Eneolitic";
    public static final String NEOLITIC_DEZVOLTAT = "Neolitic dezvoltat";
    public static final String NEOLITIC_MIJLOCIU = "Neolitic mijlociu";
    public static final String NEOLITIC_TIMPURIU = "Neolitic timpuriu";
    public static final String NEOLITIC_TARZIU = "Neolitic târziu";
    public static final String PALEOLITIC = "Paleolitic";
    public static final String PALEOLITIC_INFERIOR = "Paleolitic inferior";
    public static final String PALEOLITIC_MIJLOCIU = "Paleolitic mijlociu";
    public static final String PALEOLITIC_SUPERIOR = "Paleolitic superior";
    public static final String PERIOADA_BIZANTINA = "Perioada bizantină";
    public static final String PERIOADA_BIZANTINA_TARZIE = "Perioada bizantină târzie";
    public static final String PERIOADA_DACO_ROMANA = "Perioada daco-romană";
    public static final String PERIOADA_DE_TRANZITIE_LA_EPOCA_BRONZULUI = "Perioada de tranziție la epoca bronzului";
    public static final String PERIOADA_ELENISTICA = "Perioada elenistică";
    public static final String PERIOADA_ELENISTICA_TARZIE = "Perioada elenistică târzie";
    public static final String PERIOADA_ELENISTICA_TARZIE_SAU_ROMANA_TIMPURIE = "Perioada elenistică târzie sau romană timpurie";
    public static final String PERIOADA_GETO_DACICA = "Perioada geto-dacică";
    public static final String PERIOADA_MEDIEVALA_DEZVOLTATA = "Perioada medievală dezvoltată";
    public static final String PERIOADA_MEDIEVALA_TIMPURIE = "Perioada medievală timpurie";
    public static final String PERIOADA_MEDIEVALA_TARZIE = "Perioada medievală târzie";
    public static final String PERIOADA_MEDIEVALA_TARZIE_PERIOADA_MODERNA = "Perioada medievală târzie - perioada modernă";
    public static final String PERIOADA_MIJLOCIE_A_EPOCII_BRONZULUI = "Perioada mijlocie a epocii bronzului";
    public static final String PERIOADA_PREMEDIEVALA = "Perioada premedievală";
    public static final String PERIOADA_ROMANO_BIZANTINA = "Perioada romano-bizantină";
    public static final String PERIOADA_ROMANA_TIMPURIE = "Perioada romană timpurie";
    public static final String PERIOADA_ROMANA_TARZIE = "Perioada romană târzie";
    public static final String PERIOADA_TIMPURIE_A_EPOCII_BRONZULUI = "Perioada timpurie a epocii bronzului";
    public static final String PERIOADA_TARZIE_A_EPOCII_BRONZULUI = "Perioada târzie a epocii bronzului";
    public static final String PERIOADA_TARZIE_A_EPOCII_BRONZULUI_HALLSTATT_A1 = "Perioada târzie a epocii bronzului - Hallstatt A1";
    public static final String PERIOADA_TARZIE_A_EPOCII_BRONZULUI_HALLSTATT_TIMPURIU = "Perioada târzie a epocii bronzului - Hallstatt timpuriu";
    public static final String PREISTORIE = "Preistorie";
    public static final String TRACICA = "Tracică";
}
