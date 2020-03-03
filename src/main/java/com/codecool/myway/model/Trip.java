package com.codecool.myway.model;

import com.codecool.myway.dao.DaysStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Trip {
    @Autowired
    private DaysStorage daysStorage;
    private static int idCounter=0;
    private int id = idCounter++;
    private String name;
    private String country;
    private String city;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfArrival;
    private List<String> travelTypeList = new ArrayList<>();
    private int rating = 0;

    public Trip(String name, String country, String city, LocalDate dateOfDeparture, LocalDate dateOfArrival, List<String> travelTypeList) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.travelTypeList.addAll(travelTypeList);

    }

    public Trip() {};

    public int getId() {
        return id;
    }

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

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", dateOfDeparture='" + dateOfDeparture + '\'' +
                ", dateOfArrival='" + dateOfArrival + '\'' +
                ", travelTypeList=" + travelTypeList +
                ", rating=" + rating +
                '}';
    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    public void createPlannedDaysForTrip() {
        List<LocalDate> plannedDays = getDatesBetween(dateOfDeparture, dateOfArrival);

        for (LocalDate date : plannedDays) {
            daysStorage.addToDaysList(new PlannedDay(date, id));
        }
    }
}
