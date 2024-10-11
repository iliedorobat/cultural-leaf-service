package ro.webdata.humanities.server.endpoint.cho.dto;

public class StatsEntryDTO {
    String name;
    int value = 0;

    public StatsEntryDTO(String name, int value) {
        setValue(value);
        setName(name);
    }

    public StatsEntryDTO(String name, String value) {
        setCount(value);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            int startIndex = name.lastIndexOf("/") + 1;

            this.name = startIndex > 0
                    ? name.substring(startIndex).replace("_", " ")
                    : name;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCount(String countStr) {
        try {
            setValue(Integer.parseInt(countStr));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
