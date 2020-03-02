package com.codecool.myway.dao;

import com.codecool.myway.model.Trip;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class TripStorage {

    private List<Trip> trips = new LinkedList<>();


    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addStarterTrips() {
        Trip trip1 = new Trip("Canary dream", "Canary Islands", "Corralejo",
                "20/02/2020", "28/02/2020", Arrays.asList("Plane", "car-rental"));

        Trip trip2 = new Trip("Spanish days", "Spain", "Malaga",
                "05/02/2020", "12/02/2020", Arrays.asList("Plane", "car-rental", "local train"));

        Trip trip3 = new Trip("Hungarian retro", "Hungary", "Gyula",
                "01/02/2020", "05/02/2020", Collections.singletonList("car"));

        Trip trip4 = new Trip("The Great of Britain", "England", "Bristol",
                "03/01/2020", "16/01/2020", Arrays.asList("Plane", "local transport"));

        Trip trip5 = new Trip("German cold", "Germany", "Berlin",
                "15/01/2020", "22/01/2020", Arrays.asList("Plane", "car-rental"));

        trips.addAll(Arrays.asList(trip1, trip2, trip3, trip4, trip5));
    }

    @Override
    public String toString() {
        return "TripStorage{" +
                "trips=" + trips +
                '}';
    }
}
