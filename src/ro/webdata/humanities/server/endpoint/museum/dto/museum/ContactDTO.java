package ro.webdata.humanities.server.endpoint.museum.dto.museum;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactDTO {
    private AgentDTO agent;
    private String director;
    private ArrayList<String> emailList = new ArrayList<>();
    private ArrayList<String> faxList = new ArrayList<>();
    private ArrayList<String> socialMediaList = new ArrayList<>();
    private ArrayList<String> phoneList = new ArrayList<>();
    private ArrayList<String> timetableList = new ArrayList<>();
    private ArrayList<String> virtualTourList = new ArrayList<>();
    private ArrayList<String> websiteList = new ArrayList<>();

    public ContactDTO() {}

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void addEmail(String email) {
        this.emailList.add(email);
    }

    public ArrayList<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }

    public void addFax(String fax) {
        this.faxList.add(fax);
    }

    public ArrayList<String> getFaxList() {
        return faxList;
    }

    public void setFaxList(ArrayList<String> faxList) {
        this.faxList = faxList;
    }

    public void addSocialMedia(String socialMedia) {
        this.socialMediaList.add(socialMedia);
    }

    public ArrayList<String> getSocialMediaList() {
        return socialMediaList;
    }

    public void setSocialMediaList(ArrayList<String> socialMediaList) {
        this.socialMediaList = socialMediaList;
    }

    public void addPhone(String phone) {
        this.phoneList.add(phone);
    }

    public ArrayList<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<String> phoneList) {
        this.phoneList = phoneList;
    }

    public void addTimetable(String timetable) {
        this.timetableList.add(timetable);
    }

    public ArrayList<String> getTimetableList() {
        return timetableList;
    }

    public void setTimetableList(ArrayList<String> timetableList) {
        this.timetableList = timetableList;
    }

    public void addVirtualTour(String virtualTour) {
        this.virtualTourList.add(virtualTour);
    }

    public ArrayList<String> getVirtualTourList() {
        return virtualTourList;
    }

    public void setVirtualTourList(ArrayList<String> virtualTourList) {
        this.virtualTourList = virtualTourList;
    }

    public void addWebsite(String  web) {
        this.websiteList.add(web);
    }

    public ArrayList<String> getWebsiteList() {
        return websiteList;
    }

    public void setWebsiteList(ArrayList<String> websiteList) {
        this.websiteList = websiteList;
    }
}
