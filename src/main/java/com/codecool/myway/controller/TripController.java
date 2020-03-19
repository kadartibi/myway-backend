package com.codecool.myway.controller;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.model.Trip;
import com.codecool.myway.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripStorage tripStorage;

    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/list")
    public List<TripEntity> tripsList() {
        return tripRepository.findTop5ByOrderByRatingDesc();
    }

    @GetMapping("/in-progress")
    public List<TripEntity> tripsInProgress() {
        return tripRepository.findAllByOrderByIdDesc();
    }

    @PostMapping("/add")
    public void addTrip(@Valid @RequestBody TripEntity trip) {
        trip.createPlannedDaysForTrip();
        tripRepository.save(trip);
    }

    @PutMapping("/update")
    public Trip updateTrip(@RequestBody Trip trip) throws Exception {
        return tripStorage.update(trip);
    }
}
