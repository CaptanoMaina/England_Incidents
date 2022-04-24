package com.example.england_incidents;

import java.io.Serializable;

/**
 * @author Margaret Maina s1906597
 */

public class CurrentIncidents implements Serializable {

    private String title;
    private String description;
    private String road;
    private String latitude;
    private String longitude;
    private String eventStart;
    private String eventEnd;


    public CurrentIncidents(String title, String description, String road, String latitude, String longitude, String eventStart, String eventEnd){
        this.title = title;
        this.description = description;
        this.road = road;
        this.latitude = latitude;
        this.longitude = longitude;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;

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
        currentIncidents+=
                title + "\n"
                +description     + "\n"
                +road     + "\n"
                +latitude     + "\n"
                +longitude     + "\n"
                +eventStart     + "\n"
                +eventEnd + "\n";

        return currentIncidents;
    }

}
