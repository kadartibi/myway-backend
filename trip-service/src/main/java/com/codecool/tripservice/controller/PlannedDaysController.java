package com.codecool.tripservice.controller;



import com.codecool.tripservice.entity.ActivityEntity;
import com.codecool.tripservice.entity.PlannedDayEntity;
import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.ActivityRepository;
import com.codecool.tripservice.repository.PlannedDayRepository;
import com.codecool.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        //System.out.println(plannedDayRepository.listPlannedDaysForTrip(tripId));
        Optional<TripEntity> trip = tripRepository.findById(tripId);
        return trip.map(TripEntity::getPlannedDays).orElse(null);
    }

    @PostMapping("/add-activity-to-day/{dayId}")
    public PlannedDayEntity addActivityToDay(@PathVariable Long dayId,@Valid @RequestBody ActivityEntity activity) {
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
