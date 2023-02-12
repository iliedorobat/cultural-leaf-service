package ro.webdata.humanities.server.endpoint.cho.filter.cho;

public class CHOFilterTime {
    private int date;
    private String range;
    private boolean show = true;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
            this.range = range;
        }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
