package com.codecool.tripservice.service;


import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserClientService userClientService;

    public List<TripEntity> getInProgressTripsByUser() {
        return tripRepository.findAllByTripUserNameAndDateOfReturnGreaterThan(getCurrentUser(), LocalDate.now());
    }

    public List<TripEntity> getCompletedTripsByUser() {
        return tripRepository.findAllByTripUserNameAndDateOfReturnLessThan(getCurrentUser(), LocalDate.now());
    }

    private String getCurrentUser(){
        return userClientService.getCurrentUsername();
    }

    public void addUserToTrip(TripEntity trip) {
        trip.setTripUserName(getCurrentUser());
    }
}