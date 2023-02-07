package ro.webdata.humanities.server.endpoint.museum.dto.museum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.SKOS;
import ro.webdata.echo.commons.graph.vocab.EDM;
import ro.webdata.humanities.server.commons.JsonUtils;

import java.util.ArrayList;

import static ro.webdata.echo.commons.graph.Namespace.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MuseumDTO {
    private BuildingDTO building;
    private CollectionDTO collection;
    private ContactDTO contact;
    private DescriptionDTO description;
    private LocationDTO location;
    private MainInfoDTO mainInfo;
    private ArrayList<String> publications = new ArrayList<>();

    public MuseumDTO(ArrayNode nodes) {
        for (JsonNode jsonNode : nodes) {
            mapNode(jsonNode);
        }
    }

    public BuildingDTO getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDTO building) {
        this.building = building;
    }

    public CollectionDTO getCollection() {
        return collection;
    }

    public void setCollection(CollectionDTO collection) {
        this.collection = collection;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public DescriptionDTO getDescription() {
        return description;
    }

    public void setDescription(DescriptionDTO description) {
        this.description = description;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public MainInfoDTO getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(MainInfoDTO mainInfo) {
        this.mainInfo = mainInfo;
    }

    public ArrayList<String> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<String> publications) {
        this.publications = publications;
    }

    private void mapNode(JsonNode jsonNode) {
        String property = JsonUtils.getNodeValue(jsonNode, "property");
        String uri = JsonUtils.getNodeValue(jsonNode, "uri");
        String value = JsonUtils.getNodeValue(jsonNode, "value");

        this.initialize(property);

        switch (property) {
            case NS_REPO_PROPERTY + "cimec":
                this.mainInfo.setCimec(value);
                break;
            case NS_REPO_PROPERTY + "cimecUri":
                this.mainInfo.addCimecUri(value);
                break;
            case NS_REPO_PROPERTY + "employee":
                this.contact.getAgent().setName(value);
                break;
            case NS_REPO_PROPERTY + "employeePosition":
                this.contact.getAgent().setPosition(value);
                break;
            case NS_REPO_PROPERTY + "importance":
                this.collection.setImportance(value);
                break;
            case NS_REPO_PROPERTY + "generalProfile":
                this.collection.setCategory(value);
                break;
            case NS_REPO_PROPERTY + "geolocationType":
                this.location.getGeolocation().setType(value);
                break;
            case NS_REPO_PROPERTY + "lmiCode":
                this.building.setLmiCode(value);
                break;
            case NS_REPO_PROPERTY + "mainProfile":
                this.collection.setProfile(value);
                break;
            case NS_REPO_PROPERTY + "siruta":
                this.location.getLocality().setSiruta(value);
                break;
            case NS_REPO_PROPERTY + "supervisedBy":
                this.mainInfo.setSupervisedBy(value);
                break;
            case NS_REPO_PROPERTY + "supervisorFor":
                this.mainInfo.addSupervisorFor(value);
                break;
            case NS_REPO_PROPERTY + "virtualTour":
                this.contact.addVirtualTour(value);
                break;
            case NS_DBPEDIA_PROPERTY + "accreditation":
                this.mainInfo.addAccreditation(value);
                break;
            case NS_DBPEDIA_PROPERTY + "administrativeDivision":
                this.location.setAdministrative(value);
                break;
            case NS_DBPEDIA_PROPERTY + "commune":
                this.location.setCommune(value);
                break;
            case NS_DBPEDIA_PROPERTY + "county":
                this.location.setCounty(value);
                break;
            case NS_DBPEDIA_PROPERTY + "details":
                this.description.setDetails(value);
                break;
            case NS_DBPEDIA_PROPERTY + "director":
                this.contact.setDirector(value);
                break;
            case NS_DBPEDIA_PROPERTY + "email":
                this.contact.addEmail(value);
                break;
            case NS_DBPEDIA_PROPERTY + "fax":
                this.contact.addFax(value);
                break;
            case NS_DBPEDIA_PROPERTY + "historic":
                this.description.setHistoric(value);
                break;
            case NS_DBPEDIA_PROPERTY + "latitude":
                this.location.getGeolocation().setLatitude(value);
                break;
            case NS_DBPEDIA_PROPERTY + "locality":
                this.location.getLocality().setName(value);
                break;
            case NS_DBPEDIA_PROPERTY + "longitude":
                this.location.getGeolocation().setLongitude(value);
                break;
            case NS_DBPEDIA_PROPERTY + "schedule":
                this.contact.addTimetable(value);
                break;
            case NS_DBPEDIA_PROPERTY + "socialMedia":
                this.contact.addSocialMedia(value);
                break;
            case NS_DBPEDIA_PROPERTY + "summary":
                this.description.setSummary(value);
                break;
            case NS_DBPEDIA_PROPERTY + "telephoneNumber":
                this.contact.addPhone(value);
                break;
            case NS_DBPEDIA_PROPERTY + "website":
                this.contact.addWebsite(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "access":
                this.location.setAccess(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "address":
                this.location.setAddress(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "construction":
                this.building.setDescription(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "picture":
                this.collection.addPicture(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "postalCode":
                this.location.setPostalCode(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "publication":
                publications.add(value);
                break;
            case NS_DBPEDIA_ONTOLOGY + "type":
                this.mainInfo.setEntityType(value);
                break;
            case SKOS.uri + "prefLabel":
                this.mainInfo.setName(value);
                break;
            case DCTerms.NS + "isPartOf":
                this.mainInfo.setPartOf(value);
                break;
            case EDM.NS + "begin":
                this.mainInfo.setFounded(value);
                break;
            default:
                break;
        }
    }

    private void initialize(String property) {
        switch (property) {
            case NS_REPO_PROPERTY + "cimec":
            case NS_REPO_PROPERTY + "cimecUri":
            case NS_REPO_PROPERTY + "supervisedBy":
            case NS_REPO_PROPERTY + "supervisorFor":
            case NS_DBPEDIA_PROPERTY + "accreditation":
            case NS_DBPEDIA_ONTOLOGY + "type":
            case SKOS.uri + "prefLabel":
            case DCTerms.NS + "isPartOf":
            case EDM.NS + "begin":
                if (this.mainInfo == null) {
                    this.mainInfo = new MainInfoDTO();
                }
                break;

            case NS_REPO_PROPERTY + "employee":
            case NS_REPO_PROPERTY + "employeePosition":
            case NS_REPO_PROPERTY + "virtualTour":
            case NS_DBPEDIA_PROPERTY + "director":
            case NS_DBPEDIA_PROPERTY + "email":
            case NS_DBPEDIA_PROPERTY + "fax":
            case NS_DBPEDIA_PROPERTY + "schedule":
            case NS_DBPEDIA_PROPERTY + "socialMedia":
            case NS_DBPEDIA_PROPERTY + "telephoneNumber":
            case NS_DBPEDIA_PROPERTY + "website":
                if (this.contact == null) {
                    this.contact = new ContactDTO();
                }
                break;

            case NS_REPO_PROPERTY + "importance":
            case NS_REPO_PROPERTY + "generalProfile":
            case NS_REPO_PROPERTY + "mainProfile":
            case NS_DBPEDIA_ONTOLOGY + "picture":
                if (this.collection == null) {
                    this.collection = new CollectionDTO();
                }
                break;

            case NS_REPO_PROPERTY + "geolocationType":
            case NS_REPO_PROPERTY + "siruta":
            case NS_DBPEDIA_PROPERTY + "administrativeDivision":
            case NS_DBPEDIA_PROPERTY + "commune":
            case NS_DBPEDIA_PROPERTY + "county":
            case NS_DBPEDIA_PROPERTY + "latitude":
            case NS_DBPEDIA_PROPERTY + "locality":
            case NS_DBPEDIA_PROPERTY + "longitude":
            case NS_DBPEDIA_ONTOLOGY + "access":
            case NS_DBPEDIA_ONTOLOGY + "address":
            case NS_DBPEDIA_ONTOLOGY + "postalCode":
                if (this.location == null) {
                    this.location = new LocationDTO();
                }
                break;

            case NS_REPO_PROPERTY + "lmiCode":
            case NS_DBPEDIA_ONTOLOGY + "construction":
                if (this.building == null) {
                    this.building = new BuildingDTO();
                }
                break;

            case NS_DBPEDIA_PROPERTY + "details":
            case NS_DBPEDIA_PROPERTY + "historic":
            case NS_DBPEDIA_PROPERTY + "summary":
                if (this.description == null) {
                    this.description = new DescriptionDTO();
                }
                break;

            case NS_DBPEDIA_ONTOLOGY + "publication":
            default:
                break;
        }

        switch (property) {
            case NS_REPO_PROPERTY + "employee":
            case NS_REPO_PROPERTY + "employeePosition":
                if (this.contact.getAgent() == null) {
                    this.contact.setAgent(new AgentDTO());
                }
                break;

            case NS_REPO_PROPERTY + "geolocationType":
            case NS_DBPEDIA_PROPERTY + "latitude":
            case NS_DBPEDIA_PROPERTY + "longitude":
                if (this.location.getGeolocation() == null) {
                    this.location.setGeolocation(new GeolocationDTO());
                }
                break;

            case NS_REPO_PROPERTY + "siruta":
            case NS_DBPEDIA_PROPERTY + "locality":
                if (this.location.getLocality() == null) {
                    this.location.setLocality(new LocalityDTO());
                }
                break;
        }
    }
}
