package com.codecool.tripservice.controller;

import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import com.codecool.tripservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping
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
    }

    @GetMapping("/completed")
    public List<TripEntity> tripsCompleted() {
        return tripService.getCompletedTripsByUser();
    }

    @PostMapping("/add")
    public void saveNewTrip(@Valid @RequestBody TripEntity trip) {
        tripService.saveNewTripToUser(trip);
    }

    @PutMapping("/update")
    public TripEntity updateTrip(@RequestBody TripEntity trip) throws Exception {
        return null;
    }

    @PostMapping("/copy-trip/{tripId}")
    public void copyTrip(@PathVariable String tripId, @RequestBody Map<String, String> startingDate){
        LocalDate startingDateForTrip = LocalDate.parse(startingDate.get("date"));
        Long castTripId = Long.parseLong(tripId);
        tripService.createTripCopy(castTripId, startingDateForTrip);
    }

    @GetMapping("/number-of-trips/{userName}")
    public int getNumberOfTripsByUser(@PathVariable String userName){
        return tripRepository.findAllByTripUserId(userName).size();
    }
}
