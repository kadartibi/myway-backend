package com.codecool.myway.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Trip {

    private static int idCounter=0;
    private int id = idCounter++;
    private String name;
    private String country;
    private String city;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfArrival;
    private List<String> travelTypeList = new ArrayList<>();
    private List<PlannedDay> plannedDays = new ArrayList<>();
    private int rating = 0;

    public Trip(String name, String country, String city, LocalDate dateOfDeparture, LocalDate dateOfArrival, List<String> travelTypeList) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.travelTypeList.addAll(travelTypeList);
        createPlannedDaysForTrip();
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
        List<LocalDate> plannedDaysDates = getDatesBetween(dateOfDeparture, dateOfArrival);
        for (LocalDate date : plannedDaysDates) {
            plannedDays.add(new PlannedDay(date, id));
        }
    }

    public PlannedDay getDayById(int dayId) {
        for (PlannedDay plannedDay : plannedDays) {
            if (plannedDay.getId() == dayId) {
                return plannedDay;
            }
        }
        return null;
    }

    public List<PlannedDay> getPlannedDays() {
        return plannedDays;
    }
}
