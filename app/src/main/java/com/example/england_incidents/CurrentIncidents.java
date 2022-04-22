package com.example.england_incidents;

/**
 * @author Margaret Maina s1906597
 */

public class CurrentIncidents {

    private String title;
    private String description;
    private String road;
    private String latitude;
    private String longitude;
    private String eventStart;
    private String eventEnd;
    private String link;

    public CurrentIncidents(String title, String description, String road, String latitude, String longitude, String eventStart, String eventEnd){
        this.title = "";
        this.description = "";
        this.road = "";
        this.latitude = "";
        this.longitude = "";
        this.eventStart = "";
        this.eventEnd = "";
        this.link="http://m.highwaysengland.co.uk/feeds/rss/AllEvents.xml";
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoad() {

        return road;
    }

    public void setRoad(String road) {

        this.road = road;
    }


    public String getLatitude() {

        return latitude;
    }

    public void setLatitude(String latitude) {

        this.latitude = latitude;
    }

    public String getLongitude() {

        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String toString()
    {
        String currentIncidents = "";
        currentIncidents+= "==========================" + "\n"
                +title + "\n"
                +"==========================" + "\n"
                +description     + "\n"
                +"==========================" + "\n"
                +road     + "\n"
                +"==========================" + "\n"
                +latitude     + "\n"
                +"==========================" + "\n"
                +longitude     + "\n"
                +"==========================" + "\n"
                +eventStart     + "\n"
                +"==========================" + "\n"
                +eventEnd + "\n"
                +"==========================" + "\n";

        return currentIncidents;
    }

}
