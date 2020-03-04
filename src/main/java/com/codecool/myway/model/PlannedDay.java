package com.codecool.myway.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlannedDay {
    private static int idCounter = 0;
    private int id = idCounter++;
    private LocalDate date;
    private int tripId;
    private List<Activity> activities = new ArrayList<>();

    public PlannedDay(LocalDate date, int tripId) {
        this.date = date;
        this.tripId = tripId;
    }

    public PlannedDay() {}

    public void addToActivities(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public String getTotalCost() {
        Double sumOfCosts = 0.0;
        for (Activity activity : activities) {
            sumOfCosts += activity.getPrice();
        }
        return sumOfCosts.toString();
    }

    public int getTripId() {
        return tripId;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PlannedDay{" +
                "id=" + id +
                ", date=" + date +
                ", tripId=" + tripId +
                ", activities=" + activities +
                '}';
    }
}
