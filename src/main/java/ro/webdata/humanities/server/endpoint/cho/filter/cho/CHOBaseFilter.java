package ro.webdata.humanities.server.endpoint.cho.filter.cho;

import ro.webdata.humanities.server.endpoint.cho.filter.medal.MedalCHOFilter;
import ro.webdata.humanities.server.endpoint.cho.filter.nature.NatureCHOFilter;

public class CHOBaseFilter {
    private String county;
    private String displayState;
    private String epoch;
    private String inventoryNumber;
    private String locality;
    private String title;
    private String type;

    // used only for inp-clasate-medalistica-2014-02-02.xml
    private MedalCHOFilter medalFilter;
    // used only for inp-clasate-stiintele-naturii-2014-02-03-7.xml
    private NatureCHOFilter natureFilter;

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDisplayState() {
        return displayState;
    }

    public void setDisplayState(String displayState) {
        this.displayState = displayState;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MedalCHOFilter getMedalFilter() {
        return medalFilter;
    }

    public void setMedalFilter(MedalCHOFilter medalFilter) {
        this.medalFilter = medalFilter;
    }

    public NatureCHOFilter getNatureFilter() {
        return natureFilter;
    }

    public void setNatureFilter(NatureCHOFilter natureFilter) {
        this.natureFilter = natureFilter;
    }
}
