package ro.webdata.humanities.server.endpoint.museum.dto.museum;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BuildingDTO {
    private String description;
    private String lmiCode;

    public BuildingDTO() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLmiCode() {
        return lmiCode;
    }

    public void setLmiCode(String lmiCode) {
        this.lmiCode = lmiCode;
    }
}
