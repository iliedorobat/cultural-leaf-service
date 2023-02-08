package ro.webdata.humanities.server.endpoint.cho.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TimespanDTO {
    private List<EntryDTO> age = new ArrayList<>();                   // odp:age
    private List<EntryDTO> created = new ArrayList<>();               // dcterms:created
    private List<EntryDTO> date = new ArrayList<>();                  // dc:date
    private List<EntryDTO> found = new ArrayList<>();                 // odp:found
    private List<EntryDTO> issued = new ArrayList<>();                // dcterms:issued
    private List<EntryDTO> geologicalPeriod = new ArrayList<>();      // odp:geologicalPeriod
    private List<EntryDTO> temporal = new ArrayList<>();              // dcterms:temporal

    public void addAge(String age, String lang, String type) {
        this.age.add(
                new EntryDTO(age, lang, type)
        );
    }

    public List<EntryDTO> getAge() {
        return age;
    }

    public void setAge(List<EntryDTO> age) {
        this.age = age;
    }

    public void addCreated(String created, String lang, String type) {
        this.created.add(
                new EntryDTO(created, lang, type)
        );
    }

    public List<EntryDTO> getCreated() {
        return created;
    }

    public void setCreated(List<EntryDTO> created) {
        this.created = created;
    }

    public void addDate(String date, String lang, String type) {
        this.date.add(
                new EntryDTO(date, lang, type)
        );
    }

    public List<EntryDTO> getDate() {
        return date;
    }

    public void setDate(List<EntryDTO> date) {
        this.date = date;
    }

    public void addFound(String found, String lang, String type) {
        this.found.add(
                new EntryDTO(found, lang, type)
        );
    }

    public List<EntryDTO> getFound() {
        return found;
    }

    public void setFound(List<EntryDTO> found) {
        this.found = found;
    }

    public void addIssued(String issued, String lang, String type) {
        this.issued.add(
                new EntryDTO(issued, lang, type)
        );
    }

    public List<EntryDTO> getIssued() {
        return issued;
    }

    public void setIssued(List<EntryDTO> issued) {
        this.issued = issued;
    }

    public void addGeologicalPeriod(String geologicalPeriod, String lang, String type) {
        this.geologicalPeriod.add(
                new EntryDTO(geologicalPeriod, lang, type)
        );
    }

    public List<EntryDTO> getGeologicalPeriod() {
        return geologicalPeriod;
    }

    public void setGeologicalPeriod(List<EntryDTO> geologicalPeriod) {
        this.geologicalPeriod = geologicalPeriod;
    }

    public void addTemporal(String temporal, String lang, String type) {
        this.temporal.add(
                new EntryDTO(temporal, lang, type)
        );
    }

    public List<EntryDTO> getTemporal() {
        return temporal;
    }

    public void setTemporal(List<EntryDTO> temporal) {
        this.temporal = temporal;
    }
}
