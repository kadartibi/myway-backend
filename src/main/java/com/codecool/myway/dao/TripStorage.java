package com.codecool.myway.dao;

import com.codecool.myway.model.Trip;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    public Trip update(Trip trip) throws Exception {
        Trip tripToUpdate = trips.stream()
                .filter(t -> t.getId() == trip.getId())
                .findFirst()
                .orElseThrow(() -> new Exception("Trip not found(by id) " + trip.getName()));

        tripToUpdate.setName(trip.getName());
        tripToUpdate.setCountry(trip.getCountry());
        tripToUpdate.setCity(trip.getCity());
        tripToUpdate.setDateOfDeparture(trip.getDateOfDeparture());
        tripToUpdate.setDateOfArrival(trip.getDateOfArrival());
        tripToUpdate.setTravelTypeList(trip.getTravelTypeList());

        return tripToUpdate;
    }

    public void addStarterTrips() {
        Trip trip1 = new Trip("Canary dream", "Canary Islands", "Corralejo",
                LocalDate.parse("2020-20-02"), LocalDate.parse("2020-02-28"), Arrays.asList("Plane", "car-rental"));

        Trip trip2 = new Trip("Spanish days", "Spain", "Malaga",
                LocalDate.parse("2020-02-05"), LocalDate.parse("2020-12-02"), Arrays.asList("Plane", "car-rental", "local train"));

        Trip trip3 = new Trip("Hungarian retro", "Hungary", "Gyula",
                LocalDate.parse("2020-02-01"), LocalDate.parse("2020-02-05"), Collections.singletonList("car"));

        Trip trip4 = new Trip("The Great of Britain", "England", "Bristol",
                LocalDate.parse("2020-01-03"), LocalDate.parse("2020-01-16"), Arrays.asList("Plane", "local transport"));

        Trip trip5 = new Trip("German cold", "Germany", "Berlin",
                LocalDate.parse("2020-01-15"), LocalDate.parse("2020-01-22"), Arrays.asList("Plane", "car-rental"));

        trips.addAll(Arrays.asList(trip1, trip2, trip3, trip4, trip5));
    }

    @Override
    public String toString() {
        return "TripStorage{" +
                "trips=" + trips +
                '}';
    }

}
