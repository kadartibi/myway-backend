package com.codecool.myway.controller;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripController {

    @Autowired
    private TripStorage tripStorage;

    @GetMapping("/trips")
    public List<Trip> tripsList() {
        return tripStorage.getTrips();
    }

    @PostMapping("/addtrip")
    public Trip addTrip(@RequestBody Trip trip) {
        tripStorage.addTrip(trip);
        return trip;
    }

}
