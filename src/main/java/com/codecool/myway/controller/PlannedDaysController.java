package com.codecool.myway.controller;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.entities.ActivityEntity;
import com.codecool.myway.entities.PlannedDayEntity;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.model.Activity;
import com.codecool.myway.model.PlannedDay;
import com.codecool.myway.model.Trip;
import com.codecool.myway.repositories.ActivityRepository;
import com.codecool.myway.repositories.PlannedDayRepository;
import com.codecool.myway.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/trip/{tripId}")
public class PlannedDaysController {

    @Autowired
    private TripStorage tripStorage;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PlannedDayRepository plannedDayRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/list-all-days")
    public Set<PlannedDayEntity> listDaysPlanned(@PathVariable Long tripId) {
        TripEntity trip = tripRepository.findById(tripId).get();
        return trip.getPlannedDays();
    }

    @PostMapping("/add-activity-to-day/{dayId}")
    public PlannedDayEntity addActivityToDay(@PathVariable Long dayId, @RequestBody ActivityEntity activity) {
        PlannedDayEntity plannedDay = plannedDayRepository.findById(dayId).get();
        activity.setPlannedDay(plannedDay);
        activityRepository.save(activity);
        return plannedDay;
    }

    @PostMapping("/delete-from-activities/{dayId}")
    public Set<ActivityEntity> updateActivities(@PathVariable Long dayId, @RequestBody Long activityId) {
        activityRepository.deleteById(activityId);
        PlannedDayEntity plannedDay = plannedDayRepository.findById(dayId).get();
        return plannedDay.getActivities();
    }
}
