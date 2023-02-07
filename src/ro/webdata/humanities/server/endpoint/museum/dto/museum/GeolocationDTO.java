package ro.webdata.humanities.server.endpoint.museum.dto.museum;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeolocationDTO {
    private float latitude;
    private float longitude;
    private String type;

    public GeolocationDTO() {}

    public GeolocationDTO(String latitude, String longitude, String type) {
        setLatitude(latitude);
        setLongitude(longitude);
        setType(type);
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        if (latitude != null) {
            try {
                this.latitude = Float.parseFloat(latitude);
            } catch (NumberFormatException e) {
                System.err.println("The latitude (" + latitude + ") cannot be converted to float!");
                e.printStackTrace();
            }
        } else {
            System.err.println("The latitude cannot be NULL!");
        }
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        if (longitude != null) {
            try {
                this.longitude = Float.parseFloat(longitude);
            } catch (NumberFormatException e) {
                System.err.println("The longitude (" + longitude + ") cannot be converted to float!");
                e.printStackTrace();
            }
        } else {
            System.err.println("The longitude cannot be NULL!");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
