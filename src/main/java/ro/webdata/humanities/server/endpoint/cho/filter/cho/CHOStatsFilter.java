package ro.webdata.humanities.server.endpoint.cho.filter.cho;

public class CHOStatsFilter extends CHOBaseFilter {
    private CHOFilterTime collectingTime;
    private CHOFilterTime findingTime;
    private CHOFilterTime productionTime;

    public CHOFilterTime getCollectingTime() {
        return collectingTime;
    }

    public void setCollectingTime(CHOFilterTime collectingTime) {
        this.collectingTime = collectingTime;
    }

    public CHOFilterTime getFindingTime() {
        return findingTime;
    }

    public void setFindingTime(CHOFilterTime findingTime) {
        this.findingTime = findingTime;
    }

    public CHOFilterTime getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(CHOFilterTime productionTime) {
        this.productionTime = productionTime;
    }
}
