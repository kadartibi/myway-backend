package com.codecool.myway.controller;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.model.Activity;
import com.codecool.myway.model.PlannedDay;
import com.codecool.myway.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trip/{tripId}")
public class PlannedDaysController {

    @Autowired
    private TripStorage tripStorage;

    @GetMapping("/list-all-days")
    public List<PlannedDay> listDaysPlanned(@PathVariable int tripId) {
        Trip trip = tripStorage.getTrip(tripId);
        return trip.getPlannedDays();
    }

    @PostMapping("/add-activity-to-day/{dayId}")
    public void addActivityToDay(@PathVariable int tripId, @PathVariable int dayId, @RequestBody Activity activity) {
        Trip trip = tripStorage.getTrip(tripId);
        PlannedDay day = trip.getDayById(dayId);
        day.addToActivities(activity);
    }

}
