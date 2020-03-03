package com.codecool.myway.model;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class PlannedDay {
    private static int idCounter = 0;
    private int id = idCounter++;
    private LinkedHashMap<String, Double> activities = new LinkedHashMap<>();

    public void addToActivities(String activity, Double price) {
        activities.put(activity, price);
    }

    public LinkedHashMap<String, Double> getActivities() {
        return activities;
    }

    public String getTotalCost() {
        Double sumOfCosts = 0.0;
        for (String key : activities.keySet()) {
            sumOfCosts += activities.get(key);
        }
        return sumOfCosts.toString();
    }
}
