package com.codecool.myway.controller;

import com.codecool.myway.entities.ActivityEntity;
import com.codecool.myway.entities.PlannedDayEntity;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.repositories.ActivityRepository;
import com.codecool.myway.repositories.PlannedDayRepository;
import com.codecool.myway.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/trip/{tripId}")
public class PlannedDaysController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PlannedDayRepository plannedDayRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/list-all-days")
    public List<PlannedDayEntity> listDaysPlanned(@PathVariable Long tripId) {
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        return trip.map(TripEntity::getPlannedDays).orElse(null);
    }

    @PostMapping("/add-activity-to-day/{dayId}")
    public PlannedDayEntity addActivityToDay(@PathVariable Long dayId, @RequestBody ActivityEntity activity) {
        Optional<PlannedDayEntity> plannedDay = plannedDayRepository.findById(dayId);
        plannedDay.ifPresent(activity::setPlannedDay);
        activityRepository.save(activity);
        return plannedDay.orElse(null);
    }

    @PostMapping("/delete-from-activities/{dayId}")
    public PlannedDayEntity updateActivities(@PathVariable Long dayId, @RequestBody ActivityEntity activity) {
        activityRepository.deleteById(activity.getId());
        Optional<PlannedDayEntity> plannedDay = plannedDayRepository.findById(dayId);
        return plannedDay.orElse(null);
    }
}
