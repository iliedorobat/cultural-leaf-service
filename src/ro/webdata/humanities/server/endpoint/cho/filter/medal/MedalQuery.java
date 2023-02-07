package ro.webdata.humanities.server.endpoint.cho.filter.medal;

import ro.webdata.humanities.server.endpoint.cho.filter.PROP_KEYS;
import ro.webdata.humanities.server.endpoint.cho.filter.FilterUtils;
import ro.webdata.humanities.server.commons.sparql.Sparql;

public class MedalQuery {
    public static String prepareShapeFilter(String value) {
        String varName = Sparql.getVarName(PROP_KEYS.MEDAL_SHAPE, true);
        String values;

        if (FilterUtils.isEmpty(value, varName)) {
            return null;
        }

        switch (value) {
            case SHAPE.CIRCULAR:
                values = "'circulară'";
                break;
            case SHAPE.CROSS:
                values = "'cruce', 'cruce cu rozete', 'cruce de Malta', 'cruce repetată', 'cruce românească', 'cruce treflată'";
                break;
            case SHAPE.QUADRILOBATE:
                values = "'cvadrilobată'";
                break;
            case SHAPE.RECTANGULAR:
                values = "'dreptunghiulară'";
                break;
            case SHAPE.HEXAGONAL:
                values = "'hexagonală'";
                break;
            case SHAPE.ALMOND_SHAPE:
                values = "'migdaliformă'";
                break;
            case SHAPE.OCTAGONAL:
                values = "'octogonală'";
                break;
            case SHAPE.OVAL:
                values = "'ovală'";
                break;
            case SHAPE.SQUARE:
                values = "'pătrată'";
                break;
            case SHAPE.RHOMBUS_SHAPE:
                values = "'rombică'";
                break;
            case SHAPE.ROUND_SHAPE:
                values = "'rotundă'";
                break;
            case SHAPE.FOUR_POINTED_STAR:
                values = "'stea cu patru colțuri'";
                break;
            case SHAPE.STELLAR_SHAPE:
                values = "'stelată'";
                break;
            default:
                return null;
        }

        return String.format("FILTER(str(%s) IN (%s))", varName, values);
    }
}

class SHAPE {
    public static final String CIRCULAR = "Circular";
    public static final String CROSS = "Cross";
    public static final String QUADRILOBATE = "Quadrilobate";
    public static final String RECTANGULAR = "Rectangular";
    public static final String HEXAGONAL = "Hexagonal";
    public static final String ALMOND_SHAPE = "Almond Shape";
    public static final String OCTAGONAL = "Octagonal";
    public static final String OVAL = "Oval";
    public static final String SQUARE = "Square";
    public static final String RHOMBUS_SHAPE = "Rhombus Shape";
    public static final String ROUND_SHAPE = "Round Shape";
    public static final String FOUR_POINTED_STAR = "Four-pointed Star";
    public static final String STELLAR_SHAPE = "Stellar Shape";
}
