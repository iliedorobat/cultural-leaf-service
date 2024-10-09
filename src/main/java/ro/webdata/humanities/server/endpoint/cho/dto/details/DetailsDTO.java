package ro.webdata.humanities.server.endpoint.cho.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetailsDTO {
    private List<EntryDTO> comments = new ArrayList<>();              // odp:comments
    private List<EntryDTO> description = new ArrayList<>();           // dc:description
    private List<EntryDTO> isRelatedTo = new ArrayList<>();           // edm:isRelatedTo
    private List<EntryDTO> obverseDescription = new ArrayList<>();    // odp:obverseDescription
    private List<EntryDTO> reverseDescription = new ArrayList<>();    // odp:reverseDescription
    private List<EntryDTO> subject = new ArrayList<>();               // dc:subject
    private List<EntryDTO> summary = new ArrayList<>();               // odp:overallDescription
    private List<EntryDTO> usage = new ArrayList<>();                 // odp:use

    public void addComments(String comment, String lang, String type) {
        this.comments.add(
                new EntryDTO(comment, lang, type)
        );
    }

    public List<EntryDTO> getComments() {
        return comments;
    }

    public void setComments(List<EntryDTO> comments) {
        this.comments = comments;
    }

    public void addDescription(String description, String lang, String type) {
        this.description.add(
                new EntryDTO(description, lang, type)
        );
    }

    public List<EntryDTO> getDescription() {
        return description;
    }

    public void setDescription(List<EntryDTO> description) {
        this.description = description;
    }

    public void addIsRelatedTo(String isRelatedTo, String lang, String type) {
        this.isRelatedTo.add(
                new EntryDTO(isRelatedTo, lang, type)
        );
    }

    public List<EntryDTO> getIsRelatedTo() {
        return isRelatedTo;
    }

    public void setIsRelatedTo(List<EntryDTO> isRelatedTo) {
        this.isRelatedTo = isRelatedTo;
    }

    public void addObverseDescription(String obverseDescription, String lang, String type) {
        this.obverseDescription.add(
                new EntryDTO(obverseDescription, lang, type)
        );
    }

    public List<EntryDTO> getObverseDescription() {
        return obverseDescription;
    }

    public void setObverseDescription(List<EntryDTO> obverseDescription) {
        this.obverseDescription = obverseDescription;
    }

    public void addReverseDescription(String reverseDescription, String lang, String type) {
        this.reverseDescription.add(
                new EntryDTO(reverseDescription, lang, type)
        );
    }

    public List<EntryDTO> getReverseDescription() {
        return reverseDescription;
    }

    public void setReverseDescription(List<EntryDTO> reverseDescription) {
        this.reverseDescription = reverseDescription;
    }

    public void addSubject(String subject, String lang, String type) {
        this.subject.add(
                new EntryDTO(subject, lang, type)
        );
    }

    public List<EntryDTO> getSubject() {
        return subject;
    }

    public void setSubject(List<EntryDTO> subject) {
        this.subject = subject;
    }

    public void addSummary(String summary, String lang, String type) {
        this.summary.add(
                new EntryDTO(summary, lang, type)
        );
    }

    public List<EntryDTO> getSummary() {
        return summary;
    }

    public void setSummary(List<EntryDTO> summary) {
        this.summary = summary;
    }

    public void addUsage(String usage, String lang, String type) {
        this.usage.add(
                new EntryDTO(usage, lang, type)
        );
    }

    public List<EntryDTO> getUsage() {
        return usage;
    }

    public void setUsage(List<EntryDTO> usage) {
        this.usage = usage;
    }
}
