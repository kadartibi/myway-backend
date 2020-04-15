package com.codecool.tripservice.controller;


import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import com.codecool.tripservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    @GetMapping("/recommended")
    public List<TripEntity> tripsList() {
        return tripRepository.findTop5ByOrderByRatingDesc();
    }

    @GetMapping("/in-progress")
    public List<TripEntity> tripsInProgress() {
        return tripService.getInProgressTripsByUser();

    }@GetMapping("/completed")
    public List<TripEntity> tripsCompleted() {
        return tripService.getCompletedTripsByUser();
    }

    @PostMapping("/add")
    public void addTrip(@Valid @RequestBody TripEntity trip) {
        tripService.addUserToTrip(trip);
        trip.createPlannedDaysForTrip();
        tripRepository.save(trip);
    }

    @PutMapping("/update")
    public TripEntity updateTrip(@RequestBody TripEntity trip) throws Exception {
        return null;
    }
}
