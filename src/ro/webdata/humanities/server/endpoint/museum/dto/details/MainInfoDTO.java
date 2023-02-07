package ro.webdata.humanities.server.endpoint.museum.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainInfoDTO {
    private ArrayList<String> accreditationList = new ArrayList<>();
    private String cimec;
    private ArrayList<String> cimecUriList = new ArrayList<>();
    private String entityType;
    private String name;
    private String founded;
    private String partOf;
    private String supervisedBy;
    private ArrayList<String> supervisorForList = new ArrayList<>();

    public MainInfoDTO() {}

    public void addAccreditation(String accreditation) {
        this.accreditationList.add(accreditation);
    }

    public ArrayList<String> getAccreditationList() {
        return accreditationList;
    }

    public void setAccreditationList(ArrayList<String> accreditationList) {
        this.accreditationList = accreditationList;
    }

    public String getCimec() {
        return cimec;
    }

    public void setCimec(String cimec) {
        this.cimec = cimec;
    }

    public void addCimecUri(String cimecUri) {
        this.cimecUriList.add(cimecUri);
    }

    public ArrayList<String> getCimecUriList() {
        return cimecUriList;
    }

    public void setCimecUriList(ArrayList<String> cimecUriList) {
        this.cimecUriList = cimecUriList;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getPartOf() {
        return partOf;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }

    public String getSupervisedBy() {
        return supervisedBy;
    }

    public void setSupervisedBy(String supervisedBy) {
        this.supervisedBy = supervisedBy;
    }

    public void addSupervisorFor(String value) {
        this.supervisorForList.add(value);
    }

    public ArrayList<String> getSupervisorForList() {
        return supervisorForList;
    }

    public void setSupervisorForList(ArrayList<String> supervisorForList) {
        this.supervisorForList = supervisorForList;
    }
}
