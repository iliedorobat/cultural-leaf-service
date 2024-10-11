package ro.webdata.humanities.server.commons.sparql;

public class SparqlPrefix implements Comparable<Object> {
    private String nsAbbr;
    private String nsUri;

    public SparqlPrefix(String nsAbbr, String nsUri) {
        setNsAbbr(nsAbbr);
        setNsUri(nsUri);
    }

    @Override
    public String toString() {
        return "PREFIX" + " " + nsAbbr + ": <" + nsUri + ">";
    }

    @Override
    public int hashCode() {
        return this.nsAbbr.hashCode() + this.nsUri.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        SparqlPrefix otherPrefix = (SparqlPrefix) other;
        return this.nsAbbr.equals(otherPrefix.nsAbbr) && this.nsUri.equals(otherPrefix.nsUri);
    }

    @Override
    public int compareTo(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return -1;
        } else {
            return this.toString().compareTo(other.toString());
        }
    }

    public String getNsAbbr() {
        return nsAbbr;
    }

    public void setNsAbbr(String nsAbbr) {
        this.nsAbbr = nsAbbr;
    }

    public String getNsUri() {
        return nsUri;
    }

    public void setNsUri(String nsUri) {
        this.nsUri = nsUri;
    }
}
