package ro.webdata.humanities.server.endpoint.cho.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MeasurementsDTO {
    private List<EntryDTO> baseDiameter = new ArrayList<>();          // odp:basediameter
    private List<EntryDTO> carats = new ArrayList<>();                // odp:carats
    private List<EntryDTO> conjugateDiameter = new ArrayList<>();     // odp:conjugatediameter
    private List<EntryDTO> diameter = new ArrayList<>();              // dbo:diameter
    private List<EntryDTO> fineness = new ArrayList<>();              // odp:fineness
    private List<EntryDTO> handleDiameter = new ArrayList<>();        // odp:handlediameter
    private List<EntryDTO> height = new ArrayList<>();                // dbo:height
    private List<EntryDTO> length = new ArrayList<>();                // dbo:length
    private List<EntryDTO> maximalDiameter = new ArrayList<>();       // odp:maximaldiameter
    private List<EntryDTO> mouthDiameter = new ArrayList<>();         // odp:mouthdiameter
    private List<EntryDTO> sleeveWidth = new ArrayList<>();           // odp:sleevewidth
    private List<EntryDTO> size = new ArrayList<>();                  // dbo:size
    private List<EntryDTO> thickness = new ArrayList<>();             // odp:thickness
    private List<EntryDTO> transverseDiameter = new ArrayList<>();    // odp:transversediameter
    private List<EntryDTO> weight = new ArrayList<>();                // dbo:weight
    private List<EntryDTO> width = new ArrayList<>();                 // dbo:width

    public void addBaseDiameter(String baseDiameter, String lang, String type) {
        this.baseDiameter.add(
                new EntryDTO(baseDiameter, lang, type)
        );
    }

    public List<EntryDTO> getBaseDiameter() {
        return baseDiameter;
    }

    public void setBaseDiameter(List<EntryDTO> baseDiameter) {
        this.baseDiameter = baseDiameter;
    }

    public void addCarats(String carats, String lang, String type) {
        this.carats.add(
                new EntryDTO(carats, lang, type)
        );
    }

    public List<EntryDTO> getCarats() {
        return carats;
    }

    public void setCarats(List<EntryDTO> carats) {
        this.carats = carats;
    }

    public void addConjugateDiameter(String conjugateDiameter, String lang, String type) {
        this.conjugateDiameter.add(
                new EntryDTO(conjugateDiameter, lang, type)
        );
    }

    public List<EntryDTO> getConjugateDiameter() {
        return conjugateDiameter;
    }

    public void setConjugateDiameter(List<EntryDTO> conjugateDiameter) {
        this.conjugateDiameter = conjugateDiameter;
    }

    public void addDiameter(String diameter, String lang, String type) {
        this.diameter.add(
                new EntryDTO(diameter, lang, type)
        );
    }

    public List<EntryDTO> getDiameter() {
        return diameter;
    }

    public void setDiameter(List<EntryDTO> diameter) {
        this.diameter = diameter;
    }

    public void addFineness(String fineness, String lang, String type) {
        this.fineness.add(
                new EntryDTO(fineness, lang, type)
        );
    }

    public List<EntryDTO> getFineness() {
        return fineness;
    }

    public void setFineness(List<EntryDTO> fineness) {
        this.fineness = fineness;
    }

    public void addHandleDiameter(String handleDiameter, String lang, String type) {
        this.handleDiameter.add(
                new EntryDTO(handleDiameter, lang, type)
        );
    }

    public List<EntryDTO> getHandleDiameter() {
        return handleDiameter;
    }

    public void setHandleDiameter(List<EntryDTO> handleDiameter) {
        this.handleDiameter = handleDiameter;
    }

    public void addHeight(String height, String lang, String type) {
        this.height.add(
                new EntryDTO(height, lang, type)
        );
    }

    public List<EntryDTO> getHeight() {
        return height;
    }

    public void setHeight(List<EntryDTO> height) {
        this.height = height;
    }

    public void addLength(String length, String lang, String type) {
        this.length.add(
                new EntryDTO(length, lang, type)
        );
    }

    public List<EntryDTO> getLength() {
        return length;
    }

    public void setLength(List<EntryDTO> length) {
        this.length = length;
    }

    public void addMaximalDiameter(String maximalDiameter, String lang, String type) {
        this.maximalDiameter.add(
                new EntryDTO(maximalDiameter, lang, type)
        );
    }

    public List<EntryDTO> getMaximalDiameter() {
        return maximalDiameter;
    }

    public void setMaximalDiameter(List<EntryDTO> maximalDiameter) {
        this.maximalDiameter = maximalDiameter;
    }

    public void addMouthDiameter(String mouthDiameter, String lang, String type) {
        this.mouthDiameter.add(
                new EntryDTO(mouthDiameter, lang, type)
        );
    }

    public List<EntryDTO> getMouthDiameter() {
        return mouthDiameter;
    }

    public void setMouthDiameter(List<EntryDTO> mouthDiameter) {
        this.mouthDiameter = mouthDiameter;
    }

    public void addSleeveWidth(String sleeveWidth, String lang, String type) {
        this.sleeveWidth.add(
                new EntryDTO(sleeveWidth, lang, type)
        );
    }

    public List<EntryDTO> getSleeveWidth() {
        return sleeveWidth;
    }

    public void setSleeveWidth(List<EntryDTO> sleeveWidth) {
        this.sleeveWidth = sleeveWidth;
    }

    public void addSize(String size, String lang, String type) {
        this.size.add(
                new EntryDTO(size, lang, type)
        );
    }

    public List<EntryDTO> getSize() {
        return size;
    }

    public void setSize(List<EntryDTO> size) {
        this.size = size;
    }

    public void addThickness(String thickness, String lang, String type) {
        this.thickness.add(
                new EntryDTO(thickness, lang, type)
        );
    }

    public List<EntryDTO> getThickness() {
        return thickness;
    }

    public void setThickness(List<EntryDTO> thickness) {
        this.thickness = thickness;
    }

    public void addTransverseDiameter(String transverseDiameter, String lang, String type) {
        this.transverseDiameter.add(
                new EntryDTO(transverseDiameter, lang, type)
        );
    }

    public List<EntryDTO> getTransverseDiameter() {
        return transverseDiameter;
    }

    public void setTransverseDiameter(List<EntryDTO> transverseDiameter) {
        this.transverseDiameter = transverseDiameter;
    }

    public void addWeight(String weight, String lang, String type) {
        this.weight.add(
                new EntryDTO(weight, lang, type)
        );
    }

    public List<EntryDTO> getWeight() {
        return weight;
    }

    public void setWeight(List<EntryDTO> weight) {
        this.weight = weight;
    }

    public void addWidth(String width, String lang, String type) {
        this.width.add(
                new EntryDTO(width, lang, type)
        );
    }

    public List<EntryDTO> getWidth() {
        return width;
    }

    public void setWidth(List<EntryDTO> width) {
        this.width = width;
    }
}
