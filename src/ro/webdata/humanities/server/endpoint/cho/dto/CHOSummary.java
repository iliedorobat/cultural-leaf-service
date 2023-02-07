package ro.webdata.humanities.server.endpoint.cho.dto;

import com.fasterxml.jackson.databind.JsonNode;
import ro.webdata.humanities.server.commons.JsonUtils;

// TODO: add edm:hasType?
public class CHOSummary {
    String category;
    String inventoryNumber;
    String location; // TODO:
    String summary;
    String title;
    String uri;

    public CHOSummary(JsonNode jsonNode) {
        setInventoryNumber(jsonNode);
        setLocation(jsonNode);
        setSummary(jsonNode);
        setTitle(jsonNode);
        setCategory(jsonNode);
        setUri(jsonNode);
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(JsonNode jsonNode) {
        this.inventoryNumber = JsonUtils.getNodeValue(jsonNode, "odp_inventoryNumber_min");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(JsonNode jsonNode) {
        this.location = JsonUtils.getNodeValue(jsonNode, "edm_currentLocation_min");
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(JsonNode jsonNode) {
        this.summary = JsonUtils.getNodeValue(jsonNode, "odp_overallDescription_min");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(JsonNode jsonNode) {
        this.title = JsonUtils.getNodeValue(jsonNode, "dc_title_min");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(JsonNode jsonNode) {
        this.category = JsonUtils.getNodeValue(jsonNode, "dc_type_min");
    }

    public String getUri() {
        return uri;
    }

    public void setUri(JsonNode jsonNode) {
        this.uri = JsonUtils.getNodeValue(jsonNode, "cho");
    }
}
