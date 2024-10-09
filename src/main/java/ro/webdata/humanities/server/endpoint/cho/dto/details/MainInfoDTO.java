package ro.webdata.humanities.server.endpoint.cho.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainInfoDTO {
    private List<EntryDTO> category = new ArrayList<>();      // dc:type
    private List<EntryDTO> id = new ArrayList<>();            // dc:identifier
    private String inventoryNumber;                           // odp:inventoryNumber
    private String location;                                  // edm:currentLocation // TODO:
    private String recordType;                                // edm:type
    private List<EntryDTO> state = new ArrayList<>();         // odp:displayState
    private List<EntryDTO> title = new ArrayList<>();         // dc:title
    private List<EntryDTO> type = new ArrayList<>();          // edm:hasType
    private List<EntryDTO> wasPresentAt = new ArrayList<>();  // edm:wasPresentAt
    
    private List<EntryDTO> hasMet = new ArrayList<>();        // edm:hasMet
    private String uri;
    
    public void addCategory(String category, String lang, String type) {
        this.category.add(
                new EntryDTO(category, lang, type)
        );
    }

    public List<EntryDTO> getCategory() {
        return category;
    }

    public void setCategory(List<EntryDTO> category) {
        this.category = category;
    }

    public void addId(String id, String lang, String type) {
        this.id.add(
                new EntryDTO(id, lang, type)
        );
    }

    public List<EntryDTO> getId() {
        return id;
    }

    public void setId(List<EntryDTO> id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public void addState(String state, String lang, String type) {
        this.state.add(
                new EntryDTO(state, lang, type)
        );
    }

    public List<EntryDTO> getState() {
        return state;
    }

    public void setState(List<EntryDTO> state) {
        this.state = state;
    }

    public void addTitle(String title, String lang, String type) {
        this.title.add(
                new EntryDTO(title, lang, type)
        );
    }

    public List<EntryDTO> getTitle() {
        return title;
    }

    public void setTitle(List<EntryDTO> title) {
        this.title = title;
    }

    public void addType(String choType, String lang, String type) {
        this.type.add(
                new EntryDTO(choType, lang, type)
        );
    }

    public List<EntryDTO> getType() {
        return type;
    }

    public void setType(List<EntryDTO> type) {
        this.type = type;
    }

    public void addWasPresentAt(String wasPresentAt, String lang, String type) {
        this.wasPresentAt.add(
                new EntryDTO(wasPresentAt, lang, type)
        );
    }

    public List<EntryDTO> getWasPresentAt() {
        return wasPresentAt;
    }

    public void setWasPresentAt(List<EntryDTO> wasPresentAt) {
        this.wasPresentAt = wasPresentAt;
    }

    public void addHasMet(String hasMet, String lang, String type) {
        this.hasMet.add(
                new EntryDTO(hasMet, lang, type)
        );
    }

    public List<EntryDTO> getHasMet() {
        return hasMet;
    }

    public void setHasMet(List<EntryDTO> hasMet) {
        this.hasMet = hasMet;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
