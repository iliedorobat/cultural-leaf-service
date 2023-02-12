package ro.webdata.humanities.server.endpoint.cho.filter.cho;

public class CHOFilter extends CHOBaseFilter {
    private CHOFilterInterval creationInterval;
    private CHOFilterInterval foundInterval;

    public CHOFilterInterval getCreationInterval() {
        return creationInterval;
    }

    public void setCreationInterval(CHOFilterInterval creationInterval) {
        this.creationInterval = creationInterval;
    }

    public CHOFilterInterval getFoundInterval() {
        return foundInterval;
    }

    public void setFoundInterval(CHOFilterInterval foundInterval) {
        this.foundInterval = foundInterval;
    }
}
