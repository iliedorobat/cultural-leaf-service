package ro.webdata.humanities.server.endpoint.cho.dto.details;

public class EntryDTO {
    private String value;
    private String lang;
    private String type;

    public EntryDTO(String value, String lang, String type) {
        setLang(lang);
        setType(type);
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
