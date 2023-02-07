package ro.webdata.humanities.server.endpoint.museum.dto.museum;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocationDTO {
    private String access;
    private String address;
    private String administrative;
    private String commune;
    private String county;
    private GeolocationDTO geolocation;
    private LocalityDTO locality;
    private String postalCode;

    public LocationDTO() {}

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdministrative() {
        return administrative;
    }

    public void setAdministrative(String administrative) {
        this.administrative = administrative;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public GeolocationDTO getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeolocationDTO geolocation) {
        this.geolocation = geolocation;
    }

    public LocalityDTO getLocality() {
        return locality;
    }

    public void setLocality(LocalityDTO locality) {
        this.locality = locality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
