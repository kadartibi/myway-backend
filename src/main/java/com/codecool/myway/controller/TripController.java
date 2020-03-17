package com.codecool.myway.controller;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripStorage tripStorage;

    @GetMapping("/list")
    public List<Trip> tripsList() {
        return tripStorage.getTrips();
    }

    @PostMapping("/add")
    public void addTrip(@RequestBody Trip trip) {
        trip.createPlannedDaysForTrip();
        tripStorage.addTrip(trip);
    }

    @PutMapping("/update")
    public Trip updateTrip(@RequestBody Trip trip) throws Exception {
        return tripStorage.update(trip);
    }
}
