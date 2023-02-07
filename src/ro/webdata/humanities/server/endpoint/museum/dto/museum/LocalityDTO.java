package ro.webdata.humanities.server.endpoint.museum.dto.museum;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocalityDTO {
    private String name;
    private long siruta;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSiruta() {
        return siruta;
    }

    public void setSiruta(String siruta) {
        if (siruta != null) {
            try {
                this.siruta = Long.parseLong(siruta);
            } catch (NumberFormatException e) {
                System.err.println("The siruta code (" + siruta + ") cannot be converted to float!");
                e.printStackTrace();
            }
        } else {
            System.err.println("The siruta code cannot be NULL!");
        }
    }
}
