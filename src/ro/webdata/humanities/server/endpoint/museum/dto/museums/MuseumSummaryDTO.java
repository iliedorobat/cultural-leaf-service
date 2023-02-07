package ro.webdata.humanities.server.endpoint.museum.dto.museums;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import ro.webdata.humanities.server.commons.JsonUtils;
import ro.webdata.humanities.server.endpoint.museum.dto.museum.GeolocationDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MuseumSummaryDTO {
    private String county;
    private String countyUri;
    private String name;
    private String type;
    private String uri;
    private GeolocationDTO geolocation;

    public MuseumSummaryDTO(JsonNode jsonNode) {
        setCounty(jsonNode);
        setCountyUri(jsonNode);
        setGeolocation(jsonNode);
        setName(jsonNode);
        setType(jsonNode);
        setUri(jsonNode);
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(JsonNode jsonNode) {
        String countyUri = JsonUtils.getNodeValue(jsonNode, "dbp_county");
        int start = countyUri.lastIndexOf("/") + 1;
        int end = countyUri.lastIndexOf("_County");
        this.county = countyUri.substring(start, end);
    }

    public String getCountyUri() {
        return countyUri;
    }

    public void setCountyUri(JsonNode jsonNode) {
        this.countyUri = JsonUtils.getNodeValue(jsonNode, "county");
    }

    public GeolocationDTO getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(JsonNode jsonNode) {
        this.geolocation = new GeolocationDTO(
                JsonUtils.getNodeValue(jsonNode, "dbp_latitude"),
                JsonUtils.getNodeValue(jsonNode, "dbp_longitude"),
                JsonUtils.getNodeValue(jsonNode, "geolocationType")
        );
    }

    public String getName() {
        return name;
    }

    public void setName(JsonNode jsonNode) {
        this.name = JsonUtils.getNodeValue(jsonNode, "skos_prefLabel");
    }

    public String getType() {
        return type;
    }

    public void setType(JsonNode jsonNode) {
        this.type = JsonUtils.getNodeValue(jsonNode, "dbo_type");
    }

    public String getUri() {
        return uri;
    }

    public void setUri(JsonNode jsonNode) {
        this.uri = JsonUtils.getNodeValue(jsonNode, "uri");
    }
}
