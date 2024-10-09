package ro.webdata.humanities.server.endpoint.cho.filter.cho;

public class CHOFilter extends CHOBaseFilter {
    private CHOFilterInterval collectingInterval;
    private CHOFilterInterval findingInterval;
    private CHOFilterInterval productionInterval;

    public CHOFilterInterval getCollectingInterval() {
        return collectingInterval;
    }

    public void setCollectingInterval(CHOFilterInterval collectingInterval) {
        this.collectingInterval = collectingInterval;
    }

    public CHOFilterInterval getProductionInterval() {
        return productionInterval;
    }

    public void setProductionInterval(CHOFilterInterval productionInterval) {
        this.productionInterval = productionInterval;
    }

    public CHOFilterInterval getFindingInterval() {
        return findingInterval;
    }

    public void setFindingInterval(CHOFilterInterval findingInterval) {
        this.findingInterval = findingInterval;
    }
}
