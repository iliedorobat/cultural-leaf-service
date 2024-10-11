package ro.webdata.humanities.server.endpoint.cho.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.DC_11;
import ro.webdata.echo.commons.graph.vocab.EDM;
import ro.webdata.humanities.server.commons.JsonUtils;

import static ro.webdata.echo.commons.graph.Namespace.NS_DBPEDIA_ONTOLOGY;
import static ro.webdata.echo.commons.graph.Namespace.NS_REPO_PROPERTY;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CHODetailsDTO {
    private DetailsDTO details;
    private FeaturesDTO features;
    private MainInfoDTO mainInfo;
    private MeasurementsDTO measurements;
    private TimespanDTO timespan;

    public CHODetailsDTO(ArrayNode nodes) {
        for (JsonNode jsonNode : nodes) {
            mapNode(jsonNode);
        }
    }

    public DetailsDTO getDetails() {
        return details;
    }

    public void setDetails(DetailsDTO details) {
        this.details = details;
    }

    public FeaturesDTO getFeatures() {
        return features;
    }

    public void setFeatures(FeaturesDTO features) {
        this.features = features;
    }

    public MainInfoDTO getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(MainInfoDTO mainInfo) {
        this.mainInfo = mainInfo;
    }

    public MeasurementsDTO getMeasurements() {
        return measurements;
    }

    public void setMeasurements(MeasurementsDTO measurements) {
        this.measurements = measurements;
    }

    public TimespanDTO getTimespan() {
        return timespan;
    }

    public void setTimespan(TimespanDTO timespan) {
        this.timespan = timespan;
    }

    private void mapNode(JsonNode jsonNode) {
        String cho = JsonUtils.getNodeValue(jsonNode, "cho");
        String property = JsonUtils.getNodeValue(jsonNode, "property");
        String value = JsonUtils.getNodeValue(jsonNode, "value");
        String lang = JsonUtils.getNodeLang(jsonNode, "value");
        String type = JsonUtils.getNodeType(jsonNode, "value");

        if (this.mainInfo == null) {
            this.mainInfo = new MainInfoDTO();
            this.mainInfo.setUri(cho);
        }

        this.initialize(property);

        switch (property) {
            case DC_11.NS + "type":
                this.mainInfo.addCategory(value, lang, type);
                break;
            case DC_11.NS + "identifier":
                this.mainInfo.addId(value, lang, type);
                break;
            case DC_11.NS + "title":
                this.mainInfo.addTitle(value, lang, type);
                break;
            case EDM.NS + "currentLocation":
                this.mainInfo.setLocation(value);
                break;
            case EDM.NS + "hasMet":
                this.mainInfo.addHasMet(value, lang, type);
                break;
            case EDM.NS + "hasType":
                this.mainInfo.addType(value, lang, type);
                break;
            case EDM.NS + "type":
                this.mainInfo.setRecordType(value);
                break;
            case EDM.NS + "wasPresentAt":
                this.mainInfo.addWasPresentAt(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "displayState":
                this.mainInfo.addState(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "inventoryNumber":
                this.mainInfo.setInventoryNumber(value);
                break;
            case DC_11.NS + "description":
                this.details.addDescription(value, lang, type);
                break;
            case DC_11.NS + "subject":
                this.details.addSubject(value, lang, type);
                break;
            case EDM.NS + "isRelatedTo":
                this.details.addIsRelatedTo(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "comments":
                this.details.addComments(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "obverseDescription":
                this.details.addObverseDescription(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "overallDescription":
                this.details.addSummary(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "reverseDescription":
                this.details.addReverseDescription(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "use":
                this.details.addUsage(value, lang, type);
                break;
            case DCTerms.NS + "medium":
                this.features.addMedium(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "colourName":
                this.features.addColour(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "sex":
                this.features.addSex(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "biotope":
                this.features.addBiotope(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "chemicalComposition":
                this.features.addComposition(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "form":
                this.features.addShape(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "sealColour":
                this.features.addSealColour(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "seam":
                this.features.addSeam(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "diameter":
                this.measurements.addDiameter(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "height":
                this.measurements.addHeight(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "length":
                this.measurements.addLength(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "size":
                this.measurements.addSize(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "weight":
                this.measurements.addWeight(value, lang, type);
                break;
            case NS_DBPEDIA_ONTOLOGY + "width":
                this.measurements.addWidth(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "basediameter":
                this.measurements.addBaseDiameter(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "carats":
                this.measurements.addCarats(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "conjugatediameter":
                this.measurements.addConjugateDiameter(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "fineness":
                this.measurements.addFineness(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "handlediameter":
                this.measurements.addHandleDiameter(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "maximaldiameter":
                this.measurements.addMaximalDiameter(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "mouthdiameter":
                this.measurements.addMouthDiameter(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "sleevewidth":
                this.measurements.addSleeveWidth(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "thickness":
                this.measurements.addThickness(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "transversediameter":
                this.measurements.addTransverseDiameter(value, lang, type);
                break;
            case DC_11.NS + "date":
                this.timespan.addDate(value, lang, type);
                break;
            case DCTerms.NS + "issued":
                this.timespan.addIssued(value, lang, type);
                break;
            case DCTerms.NS + "created":
                this.timespan.addCreated(value, lang, type);
                break;
            case DCTerms.NS + "temporal":
                this.timespan.addTemporal(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "age":
                this.timespan.addAge(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "found":
                this.timespan.addFound(value, lang, type);
                break;
            case NS_REPO_PROPERTY + "geologicalPeriod":
                this.timespan.addGeologicalPeriod(value, lang, type);
                break;
            default:
                break;
        }
    }

    private void initialize(String property) {
        switch (property) {
            case DC_11.NS + "type":
            case DC_11.NS + "identifier":
            case DC_11.NS + "title":
            case EDM.NS + "currentLocation":
            case EDM.NS + "hasMet":
            case EDM.NS + "hasType":
            case EDM.NS + "type":
            case EDM.NS + "wasPresentAt":
            case NS_REPO_PROPERTY + "displayState":
            case NS_REPO_PROPERTY + "inventoryNumber":
                if (this.mainInfo == null) {
                    this.mainInfo = new MainInfoDTO();
                }
                break;
            case DC_11.NS + "description":
            case DC_11.NS + "subject":
            case EDM.NS + "isRelatedTo":
            case NS_REPO_PROPERTY + "comments":
            case NS_REPO_PROPERTY + "obverseDescription":
            case NS_REPO_PROPERTY + "overallDescription":
            case NS_REPO_PROPERTY + "reverseDescription":
            case NS_REPO_PROPERTY + "use":
                if (this.details == null) {
                    this.details = new DetailsDTO();
                }
                break;
            case DCTerms.NS + "medium":
            case NS_DBPEDIA_ONTOLOGY + "colourName":
            case NS_DBPEDIA_ONTOLOGY + "sex":
            case NS_REPO_PROPERTY + "biotope":
            case NS_REPO_PROPERTY + "chemicalComposition":
            case NS_REPO_PROPERTY + "form":
            case NS_REPO_PROPERTY + "sealColour":
            case NS_REPO_PROPERTY + "seam":
                if (this.features == null) {
                    this.features = new FeaturesDTO();
                }
                break;
            case NS_DBPEDIA_ONTOLOGY + "diameter":
            case NS_DBPEDIA_ONTOLOGY + "height":
            case NS_DBPEDIA_ONTOLOGY + "length":
            case NS_DBPEDIA_ONTOLOGY + "size":
            case NS_DBPEDIA_ONTOLOGY + "weight":
            case NS_DBPEDIA_ONTOLOGY + "width":
            case NS_REPO_PROPERTY + "basediameter":
            case NS_REPO_PROPERTY + "carats":
            case NS_REPO_PROPERTY + "conjugatediameter":
            case NS_REPO_PROPERTY + "fineness":
            case NS_REPO_PROPERTY + "handlediameter":
            case NS_REPO_PROPERTY + "maximaldiameter":
            case NS_REPO_PROPERTY + "mouthdiameter":
            case NS_REPO_PROPERTY + "sleevewidth":
            case NS_REPO_PROPERTY + "thickness":
            case NS_REPO_PROPERTY + "transversediameter":
                if (this.measurements == null) {
                    this.measurements = new MeasurementsDTO();
                }
                break;
            case DC_11.NS + "date":
            case DCTerms.NS + "issued":
            case DCTerms.NS + "created":
            case DCTerms.NS + "temporal":
            case NS_REPO_PROPERTY + "age":
            case NS_REPO_PROPERTY + "found":
            case NS_REPO_PROPERTY + "geologicalPeriod":
                if (this.timespan == null) {
                    this.timespan = new TimespanDTO();
                }
            default:
                break;
        }
    }
}
