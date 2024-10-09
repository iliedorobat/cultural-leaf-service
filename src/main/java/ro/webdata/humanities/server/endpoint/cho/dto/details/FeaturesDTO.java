package ro.webdata.humanities.server.endpoint.cho.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FeaturesDTO {
    private List<EntryDTO> biotope = new ArrayList<>();               // odp:biotope
    private List<EntryDTO> composition = new ArrayList<>();           // odp:chemicalComposition
    private List<EntryDTO> colour = new ArrayList<>();                // dbo:colourName
    private List<EntryDTO> medium = new ArrayList<>();                // dcterms:medium
    private List<EntryDTO> sealColour = new ArrayList<>();            // odp:sealColour
    private List<EntryDTO> seam = new ArrayList<>();                  // odp:seam
    private List<EntryDTO> sex = new ArrayList<>();                   // dbo:sex
    private List<EntryDTO> shape = new ArrayList<>();                 // odp:form

    public void addBiotope(String biotope, String lang, String type) {
        this.biotope.add(
                new EntryDTO(biotope, lang, type)
        );
    }

    public List<EntryDTO> getBiotope() {
        return biotope;
    }

    public void setBiotope(List<EntryDTO> biotope) {
        this.biotope = biotope;
    }

    public void addComposition(String composition, String lang, String type) {
        this.composition.add(
                new EntryDTO(composition, lang, type)
        );
    }

    public List<EntryDTO> getComposition() {
        return composition;
    }

    public void setComposition(List<EntryDTO> composition) {
        this.composition = composition;
    }

    public void addColour(String colour, String lang, String type) {
        this.colour.add(
                new EntryDTO(colour, lang, type)
        );
    }

    public List<EntryDTO> getColour() {
        return colour;
    }

    public void setColour(List<EntryDTO> colour) {
        this.colour = colour;
    }

    public void addMedium(String medium, String lang, String type) {
        this.medium.add(
                new EntryDTO(medium, lang, type)
        );
    }

    public List<EntryDTO> getMedium() {
        return medium;
    }

    public void setMedium(List<EntryDTO> medium) {
        this.medium = medium;
    }

    public void addSealColour(String sealColour, String lang, String type) {
        this.sealColour.add(
                new EntryDTO(sealColour, lang, type)
        );
    }

    public List<EntryDTO> getSealColour() {
        return sealColour;
    }

    public void setSealColour(List<EntryDTO> sealColour) {
        this.sealColour = sealColour;
    }

    public void addSeam(String seam, String lang, String type) {
        this.seam.add(
                new EntryDTO(seam, lang, type)
        );
    }

    public List<EntryDTO> getSeam() {
        return seam;
    }

    public void setSeam(List<EntryDTO> seam) {
        this.seam = seam;
    }

    public void addSex(String sex, String lang, String type) {
        this.sex.add(
                new EntryDTO(sex, lang, type)
        );
    }

    public List<EntryDTO> getSex() {
        return sex;
    }

    public void setSex(List<EntryDTO> sex) {
        this.sex = sex;
    }

    public void addShape(String shape, String lang, String type) {
        this.shape.add(
                new EntryDTO(shape, lang, type)
        );
    }

    public List<EntryDTO> getShape() {
        return shape;
    }

    public void setShape(List<EntryDTO> shape) {
        this.shape = shape;
    }
}
