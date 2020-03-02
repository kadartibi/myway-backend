package com.codecool.myway.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Trip {

    private String name;
    private String country;
    private String city;
    private String dateOfDeparture;
    private String dateOfArrival;
    private List<String> travelTypeList = new ArrayList<>();
    private int rating = 0;

    public Trip(String name, String country, String city, String dateOfDeparture, String dateOfArrival, List<String> travelTypeList) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.travelTypeList.addAll(travelTypeList);

    }

    public Trip() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(String dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public List<String> getTravelTypeList() {
        return travelTypeList;
    }

    public void setTravelTypeList(List<String> travelTypeList) {
        this.travelTypeList = travelTypeList;
    }

    public void setRating(int rating) {
        this.rating += rating;
    }

    public int getRating() {
        return rating;
    }



    @Override
    public String toString() {
        return "Trip{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", dateOfDeparture='" + dateOfDeparture + '\'' +
                ", dateOfArrival='" + dateOfArrival + '\'' +
                ", travelTypeList=" + travelTypeList +
                '}';
    }
}
