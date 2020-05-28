package com.codecool.tripservice.controller;

import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import com.codecool.tripservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping
public class TripController {

    public static final String TOKEN = "token";
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    @GetMapping("/recommended")
    public List<TripEntity> tripsList() { return tripRepository.findTop5ByOrderByRatingDesc(); }

    @GetMapping("/in-progress")
    public List<TripEntity> tripsInProgress(HttpServletRequest request) {
        return tripService.getInProgressTripsByUser(request);
    }

    @GetMapping("/completed")
    public List<TripEntity> tripsCompleted(HttpServletRequest request) {
        return tripService.getCompletedTripsByUser(request);
    }

    @PostMapping("/add")
    public void saveNewTrip(@Valid @RequestBody TripEntity trip, HttpServletRequest request) {
        tripService.saveNewTripToUser(trip, request);
    }

    @PutMapping("/update")
    public TripEntity updateTrip(@RequestBody TripEntity trip) {
        return null;
    }

    @PostMapping("/copy-trip/{tripId}")
    public void copyTrip(HttpServletRequest request, @PathVariable String tripId, @RequestBody Map<String, String> startingDate){
        LocalDate startingDateForTrip = LocalDate.parse(startingDate.get("date"));
        Long castTripId = Long.parseLong(tripId);
        tripService.createTripCopy(castTripId, startingDateForTrip, request);
    }

    @GetMapping("/number-of-trips/{userName}")
    public int getNumberOfTripsByUser(@PathVariable String userName){
        return tripRepository.findAllByTripUserId(userName).size();
    }

    @PostMapping("/recommendTrip/{tripId}/{userName}")
    public List<TripEntity> recommendTrip(@PathVariable Long tripId, @PathVariable String userName) {
        return tripService.recommendTrip(tripId, userName);
    }
}
