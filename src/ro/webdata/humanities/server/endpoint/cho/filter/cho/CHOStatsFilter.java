package ro.webdata.humanities.server.endpoint.cho.filter.cho;

public class CHOStatsFilter extends CHOBaseFilter {
    private CHOFilterTime creationTime;
    private CHOFilterTime foundTime;

    public CHOFilterTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(CHOFilterTime creationTime) {
        this.creationTime = creationTime;
    }

    public CHOFilterTime getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(CHOFilterTime foundTime) {
        this.foundTime = foundTime;
    }
}
