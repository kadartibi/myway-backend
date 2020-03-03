package com.codecool.myway.controller;

import com.codecool.myway.dao.DaysStorage;
import com.codecool.myway.model.PlannedDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip/{tripId}")
public class PlannedDaysController {

    @Autowired
    private DaysStorage daysStorage;

    @GetMapping("/list-all-days")
    public List<PlannedDay> listDaysPlanned(@PathVariable int tripId) {
        return daysStorage.getPlannedDays(tripId);
    }

    @PostMapping("/add-activity-to-day/{dayId}")
    public void addActivityToDay(@PathVariable int dayId, @RequestBody String activity, @RequestBody Double price) {
        PlannedDay day = daysStorage.getDayById(dayId);
        day.addToActivities(activity, price);
    }
}
