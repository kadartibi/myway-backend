package com.codecool.myway.dao;

import com.codecool.myway.model.PlannedDay;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaysStorage {

    private List<PlannedDay> days = new ArrayList<>();

    public void addToDaysList(PlannedDay plannedDay) {
        days.add(plannedDay);
    }

    public List<PlannedDay> getPlannedDays(int tripId) {
        List<PlannedDay> plannedDaysForTrip = new ArrayList<>();
        for (PlannedDay plannedDay : days) {
            if (plannedDay.getTripId() == tripId) {
                plannedDaysForTrip.add(plannedDay);
            }
        }
        return plannedDaysForTrip;
    }

    public PlannedDay getDayById(int dayId) {
        for (PlannedDay plannedDay : days) {
            if (plannedDay.getId() == dayId) {
                return plannedDay;
            }
        }
        return null;
    }
}
