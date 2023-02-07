package ro.webdata.humanities.server.commons.sparql;

public class SparqlTriple {
    private String subject;
    private String predicate;
    private String object;

    public SparqlTriple(String subject, String predicate) {
        String varName = Sparql.getVarName(predicate);

        setSubject(subject);
        setPredicate(predicate);
        setObject(varName);
    }

    public SparqlTriple(String subject, String predicate, String object) {
        setSubject(subject);
        setPredicate(predicate);
        setObject(object);
    }

    @Override
    public String toString() {
        return toString("");
    }

    public String toString(String tab) {
        return tab + subject + " " + predicate + " " + object;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
